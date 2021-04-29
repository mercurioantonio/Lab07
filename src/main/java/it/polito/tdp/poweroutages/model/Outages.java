package it.polito.tdp.poweroutages.model;

import java.sql.Time;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Outages {
	
	Date inizio;
	Date fine;
	long durata;
	int people;
	
	public Outages(int people, Date inizio, Date fine) {
		super();
		this.inizio = inizio;
		this.fine = fine;
		this.durata = fine.getTime() - inizio.getTime();
		this.people = people;
	}

	public long getDurata() {
		return durata;
	}

	public void setDurata(long durata) {
		this.durata = durata;
	}

	public Date getInizio() {
		return inizio;
	}

	public void setInizio(Date inizio) {
		this.inizio = inizio;
	}

	public Date getFine() {
		return fine;
	}

	public void setFine(Date fine) {
		this.fine = fine;
	}

	public int getPeople() {
		return people;
	}

	public void setPeople(int people) {
		this.people = people;
	}
	
	
	

}
