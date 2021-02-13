package com.ats.webapi.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FrNameIdByRouteId implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="fr_id")
	private int frId;
	
	@Column(name="fr_route_id")
	int  frRouteId;
	
	@Column(name="fr_name")
	String  frName;

	public int getFrId() {
		return frId;
	}

	public void setFrId(int frId) {
		this.frId = frId;
	}

	public int getFrRouteId() {
		return frRouteId;
	}

	public void setFrRouteId(int frRouteId) {
		this.frRouteId = frRouteId;
	}

	public String getFrName() {
		return frName;
	}

	public void setFrName(String frName) {
		this.frName = frName;
	}
	
	
	
	

}
