package it.polito.tdp.crimes.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.crimes.model.Event;


public class EventsDao {
	
	public void listAllEvents(Map<Long, Event> idMap){
		String sql = "SELECT * FROM events" ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					Long key=res.getLong("incident_id");
					if (!idMap.containsKey(key))
					idMap.put(key,new Event(res.getLong("incident_id"),
							res.getInt("offense_code"),
							res.getInt("offense_code_extension"), 
							res.getString("offense_type_id"), 
							res.getString("offense_category_id"),
							res.getTimestamp("reported_date").toLocalDateTime(),
							res.getString("incident_address"),
							res.getDouble("geo_lon"),
							res.getDouble("geo_lat"),
							res.getInt("district_id"),
							res.getInt("precinct_id"), 
							res.getString("neighborhood_id"),
							res.getInt("is_crime"),
							res.getInt("is_traffic")));
				} catch (Throwable t) {
					t.printStackTrace();
					System.out.println(res.getInt("id"));
				}
			}
			
			conn.close();
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public void listAllEventsType(List<String> tipoCrimini){
		String sql = "SELECT  DISTINCT offense_category_id FROM events GROUP BY offense_category_id;" ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					tipoCrimini.add(res.getString(1));
					
				} catch (Throwable t) {
					t.printStackTrace();
		
				}
			}
			
			conn.close();
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public void ListAllNeigh(List<String> quartieri) {
		String sql = "SELECT  DISTINCT neighborhood_id FROM events GROUP BY neighborhood_id;" ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					String quartiere =res.getString(1);
					if (quartiere!=null || quartiere!="" )
					quartieri.add(res.getString(1));
					
				} catch (Throwable t) {
					t.printStackTrace();
					
				}
			}
			
			conn.close();
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
