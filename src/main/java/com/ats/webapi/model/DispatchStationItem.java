package com.ats.webapi.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DispatchStationItem implements Serializable{

	@Id
	private String uuid;
	
	
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	//@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@Column(name="item_id")
	private int itemId;
	
	@Column(name="item_name")
	private String itemName;
	
	@Column(name="item_mrp2")
	private Double itemMrp2;
	
	@Column(name="order_qty")
	private float orderQty;
	
	@Column(name="fr_id")
	private int frId;
	
	@Column(name="fr_name")
	private String frName;
	
	private int frRouteId;//23-02-21-Sac
	private int itemGrp2;//23-02-21-Sac
	
	private int itemGrp1;//23-03-21-Sac
	
	
	public int getItemGrp1() {
		return itemGrp1;
	}

	public void setItemGrp1(int itemGrp1) {
		this.itemGrp1 = itemGrp1;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Double getItemMrp2() {
		return itemMrp2;
	}

	public void setItemMrp2(Double itemMrp2) {
		this.itemMrp2 = itemMrp2;
	}

	public float getOrderQty() {
		return orderQty;
	}

	public void setOrderQty(float orderQty) {
		this.orderQty = orderQty;
	}

	public int getFrId() {
		return frId;
	}

	public void setFrId(int frId) {
		this.frId = frId;
	}

	public String getFrName() {
		return frName;
	}

	public void setFrName(String frName) {
		this.frName = frName;
	}

	
	
	public int getFrRouteId() {
		return frRouteId;
	}

	public void setFrRouteId(int frRouteId) {
		this.frRouteId = frRouteId;
	}

	public int getItemGrp2() {
		return itemGrp2;
	}

	public void setItemGrp2(int itemGrp2) {
		this.itemGrp2 = itemGrp2;
	}

	@Override
	public String toString() {
		return "DispatchStationItem [id=" + id + ", itemId=" + itemId + ", itemName=" + itemName + ", itemMrp2="
				+ itemMrp2 + ", orderQty=" + orderQty + ", frId=" + frId + ", frName=" + frName + ", frRouteId="
				+ frRouteId + ", itemGrp2=" + itemGrp2 + "]";
	}
	
}
