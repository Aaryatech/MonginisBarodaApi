package com.ats.webapi.model;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class FgsOrderToProduction {
	@Id
	private int id;
	private String itemCode;
	private int subCatId;
	private String subCatName;
	private String catName;
	private int catId;
	private String itemName;
	private int openingStock;
	
	private int productionItemId;
	private int productionQty;
	
	private int billItemId;
	private int billQty;	
	private int orderQty;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getSubCatName() {
		return subCatName;
	}
	public void setSubCatName(String subCatName) {
		this.subCatName = subCatName;
	}
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	public int getCatId() {
		return catId;
	}
	public void setCatId(int catId) {
		this.catId = catId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getOpeningStock() {
		return openingStock;
	}
	public void setOpeningStock(int openingStock) {
		this.openingStock = openingStock;
	}
	
	public int getProductionItemId() {
		return productionItemId;
	}
	public void setProductionItemId(int productionItemId) {
		this.productionItemId = productionItemId;
	}
	public int getProductionQty() {
		return productionQty;
	}
	public void setProductionQty(int productionQty) {
		this.productionQty = productionQty;
	}
	public int getBillItemId() {
		return billItemId;
	}
	public void setBillItemId(int billItemId) {
		this.billItemId = billItemId;
	}
	public int getBillQty() {
		return billQty;
	}
	public void setBillQty(int billQty) {
		this.billQty = billQty;
	}
	public int getOrderQty() {
		return orderQty;
	}
	public void setOrderQty(int orderQty) {
		this.orderQty = orderQty;
	}
	public int getSubCatId() {
		return subCatId;
	}
	public void setSubCatId(int subCatId) {
		this.subCatId = subCatId;
	}
	@Override
	public String toString() {
		return "FgsOrderToProduction [id=" + id + ", itemCode=" + itemCode + ", subCatId=" + subCatId + ", subCatName="
				+ subCatName + ", catName=" + catName + ", catId=" + catId + ", itemName=" + itemName
				+ ", openingStock=" + openingStock + ", productionItemId=" + productionItemId + ", productionQty="
				+ productionQty + ", billItemId=" + billItemId + ", billQty=" + billQty + ", orderQty=" + orderQty
				+ "]";
	}	
}
