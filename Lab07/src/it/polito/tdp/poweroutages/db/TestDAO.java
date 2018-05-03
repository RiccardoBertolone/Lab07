package it.polito.tdp.poweroutages.db;

import java.util.Collections;
import java.util.List;

import it.polito.tdp.poweroutages.model.Nerc;
import it.polito.tdp.poweroutages.model.NercIdMap;
import it.polito.tdp.poweroutages.model.PowerOutages;
import it.polito.tdp.poweroutages.model.PowerOutagesIdMap;

public class TestDAO {
	
public static void main(String[] args) {
		
		PowerOutageDAO dao = new PowerOutageDAO() ;
		
		NercIdMap nercMap = new NercIdMap() ;
		PowerOutagesIdMap powerOutagesMap = new PowerOutagesIdMap() ;

		List<Nerc> nerc = dao.getNercList(nercMap) ;
		//System.out.println(nerc);
//		Collections.sort(nerc);
//		for (Nerc n : nerc) {
//			System.out.println(n.getId());
//		}
		
		List<PowerOutages> po = dao.getPOList(powerOutagesMap, nercMap) ;
		//System.out.println(po);
		
		for (Nerc n : nerc) {
			dao.getPOFromNerc(n, powerOutagesMap, nercMap);
			System.out.println(n.getId()+"    "+n.getPoList().size());
		}
				
		
		
	
	}

}
