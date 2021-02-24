package com.ats.webapi.model;
 
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id; 

@Entity
public class ItemListWithDisc {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) 
	private int id; 
	private float discPer;
	 
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getDiscPer() {
		return discPer;
	}
	public void setDiscPer(float discPer) {
		this.discPer = discPer;
	}
	@Override
	public String toString() {
		return "ItemListWithDisc [id=" + id + ", discPer=" + discPer + "]";
	}
	 
	
	
	
	

}
