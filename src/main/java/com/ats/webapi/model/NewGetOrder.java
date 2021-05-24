package com.ats.webapi.model;

import java.util.Date;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class NewGetOrder {

	
	
	

		
		@javax.persistence.Id
		private int orderId;
		
		
		private String frName;
		
		
		private String catName;
		
		private String itemName;
		
		
		
		private int orderQty;
		

		private int Id;
		
		
		private String deliveryDate;
		
		
		
		
		private int isEdit;
		
		
		
		private int isPositive;
		
		
		private float editQty;
		
		
		private String menuTitle;
		
		private float discPer;
		
		private float grnPer;
		
		
		private float itemRate1;
		


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

		


		public String getDeliveryDate() {
			return deliveryDate;
		}


		public void setDeliveryDate(String deliveryDate) {
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


		public String getMenuTitle() {
			return menuTitle;
		}


		public void setMenuTitle(String menuTitle) {
			this.menuTitle = menuTitle;
		}


		public float getDiscPer() {
			return discPer;
		}


		public void setDiscPer(float discPer) {
			this.discPer = discPer;
		}


		public float getGrnPer() {
			return grnPer;
		}


		public void setGrnPer(float grnPer) {
			this.grnPer = grnPer;
		}


		public float getItemRate1() {
			return itemRate1;
		}


		public void setItemRate1(float itemRate1) {
			this.itemRate1 = itemRate1;
		}


		@Override
		public String toString() {
			return "NewGetOrder [orderId=" + orderId + ", frName=" + frName + ", catName=" + catName + ", itemName="
					+ itemName + ", orderQty=" + orderQty + ", Id=" + Id + ", deliveryDate=" + deliveryDate
					+ ", isEdit=" + isEdit + ", isPositive=" + isPositive + ", editQty=" + editQty + ", menuTitle="
					+ menuTitle + ", discPer=" + discPer + ", grnPer=" + grnPer + ", itemRate1=" + itemRate1 + "]";
		}


	


		



}

