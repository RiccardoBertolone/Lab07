package it.polito.tdp.poweroutages.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.poweroutages.db.PowerOutageDAO;

public class Model {

	PowerOutageDAO podao;
	
	List<Nerc> nercs;
	List<PowerOutages> poweroutageses;
	
	NercIdMap nercMap;
	PowerOutagesIdMap powerOutagesMap;
	
	List<PowerOutages> solOttima = new ArrayList<PowerOutages>();
	int maxPersone = 0;
	
	public Model() {
		podao = new PowerOutageDAO() ;
		
		nercMap = new NercIdMap() ;
		powerOutagesMap = new PowerOutagesIdMap() ;
		
		nercs = podao.getNercList(nercMap) ;
		poweroutageses = podao.getPOList(powerOutagesMap, nercMap) ;
		
		for (Nerc n : nercs) {
			podao.getPOFromNerc(n, powerOutagesMap, nercMap);
		}
	}
	
	public List<Nerc> getNercList() {
		return podao.getNercList(nercMap);
	}

	public List<PowerOutages> risolvi(Nerc n, int x, int y) {
		solOttima = new ArrayList<PowerOutages>();
		maxPersone = 0;
		this.recursive(n, x, y, new ArrayList<PowerOutages>()) ;
		return solOttima;
	}

	private void recursive(Nerc n, int x, int y, List<PowerOutages> parziale) {
		
		if(this.isCompleta(parziale, x, y, n)) {
			if(this.isAnniSuperati(parziale, x) || this.isOreSuperate(parziale, y))
				parziale.remove(parziale.size()-1);
			if (this.isOttima(parziale)) {
				solOttima = new ArrayList<PowerOutages>(parziale) ;
				maxPersone = this.calcolaPersone(parziale) ;
			}
			return;
		}
		
		for(PowerOutages p : n.getPoList()) {
			if (!parziale.contains(p)) {
				parziale.add(p) ;
				this.recursive(n, x, y, parziale) ;
				parziale.remove(p) ;
			}
		}
		
	}

	private int calcolaPersone(List<PowerOutages> parziale) {
		int result = 0;
		for (PowerOutages p : parziale) {
			result+=p.getCustumersAffected();
		}
		return result;
	}

	private boolean isOttima(List<PowerOutages> parziale) {
		int npersone=0 ;
		for (PowerOutages p : parziale) {
			npersone+=p.getCustumersAffected() ;
		}
		if (npersone>maxPersone)
			return true;
		return false;
	}

	private boolean isCompleta(List<PowerOutages> parziale, int x, int y, Nerc n) {
		if(parziale.size()==0)
			return false;
		return this.isOreSuperate(parziale, y) || this.isAnniSuperati(parziale, x) || this.aggiuntetutte(parziale, n);
	}

	private boolean aggiuntetutte(List<PowerOutages> parziale, Nerc n) {
		if (parziale.size()==n.getPoList().size())
			return true;
		return false;
	}

	private boolean isAnniSuperati(List<PowerOutages> parziale, int x) {
		LocalDateTime vecchia = parziale.get(0).getDateEventBegan() ;
		LocalDateTime recente = parziale.get(0).getDateEventFinished() ;
		for (PowerOutages p : parziale) {
			if(vecchia.compareTo(p.getDateEventBegan())<0)
				vecchia = p.getDateEventBegan();
			if(recente.compareTo(p.getDateEventFinished())>0)
				recente = p.getDateEventFinished();
		}
		int differenza = (int) vecchia.until(recente, ChronoUnit.YEARS);
		if (differenza > x)
			return true;
		return false;
	}

	private boolean isOreSuperate(List<PowerOutages> parziale, int y) {
		int ore = 0;
		for (PowerOutages p : parziale) {
			LocalDateTime t1 = p.getDateEventBegan() ;
    		ore+=t1.until(p.getDateEventFinished(), ChronoUnit.MINUTES) ;
		}
		ore = ore/60;
		if(ore>y)
			return true;
		return false;
	}

}
