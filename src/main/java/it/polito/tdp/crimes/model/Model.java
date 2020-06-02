package it.polito.tdp.crimes.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sun.javafx.collections.ObservableListWrapper;

import it.polito.tdp.crimes.db.EventsDao;
import javafx.collections.ObservableList;

public class Model {
	
	private List<String> elencoCrimini;
	private List<String> quartieri;
	

	private EventsDao dao;
	private Map<Long, Event> idMap;
	
	public Model() {
		this.dao=new EventsDao();
		this.idMap=new HashMap<>();
		this.elencoCrimini=new ArrayList<String>();
		this.quartieri = new ArrayList<String>();
		elencoCrimini.add(0, "");
		quartieri.add(0, "");
		dao.listAllEventsType(elencoCrimini);
		dao.ListAllNeigh(quartieri);
		
		
	}
	

	public List<String> getElencoCrimini() {
		return elencoCrimini;
	}
	
	public List<String> getQuartieri() {
		return quartieri;
	}
	
	
}
