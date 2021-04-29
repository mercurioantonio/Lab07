package it.polito.tdp.poweroutages.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {
	
	PowerOutageDAO podao;
	
	List<Outages> soluzioni;
	int j;
	int peopleMigliore;
	long durataNow;
	int annoMax;
	int annoMin;
	int peopleParziale;
	Calendar calFine;
	Calendar calInizio;
	List<Outages> outages ;
	
	public Model() {
		podao = new PowerOutageDAO();
		calFine = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"));
		calInizio = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"));
	}
	
	public List<Nerc> getNercList() {
		return podao.getNercList();
	}
	
	public String getOutagesList(Nerc n, int anniMax, int oreMax){
		
		outages = new ArrayList<>(podao.getOutagesEvents(n));
		
		List<Outages> parziale = new ArrayList<>();
		String soluzioniStringa = "";
		peopleMigliore = -1;
		j=0;
		durataNow=0;
		annoMax=0;
		annoMin=3000;
		peopleParziale = 0;
		long oreMaxMills = (long) oreMax*(3600000);
		
		cerca(parziale, 0, 0, anniMax, oreMaxMills);
		
		if(soluzioni == null)
			soluzioniStringa = "Centro non presente nel database!!";
		else {
		
		for(Outages ooooo : soluzioni)
			soluzioniStringa += ooooo.getPeople() + "\n";
		}
			
		
		return soluzioniStringa;
	}
	
	private void cerca (List<Outages> parziale, long livello, int cnt, int anniMax, long oreMax) {
		
		
		
		 if(livello > oreMax) {
			 
			 for(Outages o : parziale) {
				 calFine.setTime(o.fine);
				 calInizio.setTime(o.inizio);
				 if(calFine.get(Calendar.YEAR)>annoMax) {
					 annoMax = calFine.get(Calendar.YEAR);
				 }
				 if(calInizio.get(Calendar.YEAR) < annoMin) {
					 annoMin = calInizio.get(Calendar.YEAR);
				 }
			 }
			 
			 if((annoMax-annoMin)>anniMax)
				 return;
			 
			 for(Outages oo : parziale)
				 peopleParziale += oo.getPeople();
			 
			 if(peopleParziale > peopleMigliore) {
			 soluzioni = new ArrayList<>(parziale);
			
			 }
			 
			 return;
			 
		 }
		 
		 else {
			 
			
						
			 
			 for (int i =j; i<outages.size(); i++) {
				
				 if(parziale.isEmpty()) {
					 durataNow = outages.get(i).durata;
				 }
				 else {
					 durataNow += outages.get(i).durata;
				 }
				 parziale.add(outages.get(i));
				 cerca(parziale, durataNow, j++, anniMax, oreMax);
				 durataNow -= outages.get(i).durata;
				 parziale.remove(parziale.size()-1);
				 
			 }
			 
			 return;
		 }
	}
	


}
