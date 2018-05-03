package it.polito.tdp.poweroutages.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import it.polito.tdp.poweroutages.model.Nerc;
import it.polito.tdp.poweroutages.model.NercIdMap;
import it.polito.tdp.poweroutages.model.PowerOutages;
import it.polito.tdp.poweroutages.model.PowerOutagesIdMap;

public class PowerOutageDAO {

	public List<Nerc> getNercList(NercIdMap nercMap) {

		String sql = "SELECT id, value FROM nerc";
		List<Nerc> nercList = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Nerc n = new Nerc(res.getInt("id"), res.getString("value"));
				nercList.add(nercMap.get(n));
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return nercList;
	}

	public List<PowerOutages> getPOList(PowerOutagesIdMap powerOutagesMap, NercIdMap nercMap) {
		String sql = "SELECT * FROM poweroutages";
		List<PowerOutages> result = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				PowerOutages po = new PowerOutages(res.getInt("id"), res.getInt("event_type_id"),
						res.getInt("tag_id"), res.getInt("area_id"), nercMap.get(res.getInt("nerc_id")), 
						res.getInt("responsible_id"), res.getInt("customers_affected"), 
						res.getTimestamp("date_event_began").toLocalDateTime(),
						res.getTimestamp("date_event_finished").toLocalDateTime(), 
						res.getInt("demand_loss")) ;
				result.add(powerOutagesMap.get(po));
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return result;
	}

	public void getPOFromNerc(Nerc n, PowerOutagesIdMap powerOutagesMap, NercIdMap nercMap) {
		
		String sql = "SELECT * FROM poweroutages AS p WHERE p.nerc_id = ?" ;
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, n.getId());
			ResultSet res = st.executeQuery();
			
			while(res.next()) {
				PowerOutages po = new PowerOutages(res.getInt("id"), res.getInt("event_type_id"),
						res.getInt("tag_id"), res.getInt("area_id"), nercMap.get(res.getInt("nerc_id")), 
						res.getInt("responsible_id"), res.getInt("customers_affected"), 
						res.getTimestamp("date_event_began").toLocalDateTime(),
						res.getTimestamp("date_event_finished").toLocalDateTime(), 
						res.getInt("demand_loss")) ;
				
				n.getPoList().add(powerOutagesMap.get(po));
				
			}
			
			conn.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e) ;
		}
		
	}

}
