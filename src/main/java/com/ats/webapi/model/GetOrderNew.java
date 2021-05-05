package com.ats.webapi.model;

import java.util.Date;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class GetOrderNew {
	
	@Id
	private int orderId;

	private String frName;

	private String catName;

	private String itemName;

	private int orderQty;

	private int Id;

	private Date deliveryDate;

	private int isEdit;

	private int isPositive;

	private float editQty;
	
	private String menuName;
	
	private float menuDiscper;
	
	private float grnPer;
	
	private float billingRate;
	

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getFrName() {
		return frName;
	}

	public void setFrName(String frName) {
		this.frName = frName;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getOrderQty() {
		return orderQty;
	}

	public void setOrderQty(int orderQty) {
		this.orderQty = orderQty;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public int getIsEdit() {
		return isEdit;
	}

	public void setIsEdit(int isEdit) {
		this.isEdit = isEdit;
	}

	public int getIsPositive() {
		return isPositive;
	}

	public void setIsPositive(int isPositive) {
		this.isPositive = isPositive;
	}

	public float getEditQty() {
		return editQty;
	}

	public void setEditQty(float editQty) {
		this.editQty = editQty;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public float getMenuDiscper() {
		return menuDiscper;
	}

	public void setMenuDiscper(float menuDiscper) {
		this.menuDiscper = menuDiscper;
	}

	public float getGrnPer() {
		return grnPer;
	}

	public void setGrnPer(float grnPer) {
		this.grnPer = grnPer;
	}

	public float getBillingRate() {
		return billingRate;
	}

	public void setBillingRate(float billingRate) {
		this.billingRate = billingRate;
	}

	@Override
	public String toString() {
		return "GetOrderNew [orderId=" + orderId + ", frName=" + frName + ", catName=" + catName + ", itemName="
				+ itemName + ", orderQty=" + orderQty + ", Id=" + Id + ", deliveryDate=" + deliveryDate + ", isEdit="
				+ isEdit + ", isPositive=" + isPositive + ", editQty=" + editQty + ", menuName=" + menuName
				+ ", menuDiscper=" + menuDiscper + ", grnPer=" + grnPer + ", billingRate=" + billingRate + "]";
	}

	
	
	
	
	
	
	
	
	
	
}
