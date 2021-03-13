package com.ats.webapi.model.prod;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class GetProductListExlPdf {

	@Id
	private int id;
	private String itemName;
	private float itemMrp1;
	private float itemMrp2;
	private float itemMrp3;
	private float itemTax3;
	private int itemShelfLife;
	private int itemSortId;
	private int itemIsUsed;
	private String itemHsncd;
	private String uom;
	private String subCatName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public float getItemMrp1() {
		return itemMrp1;
	}
	public void setItemMrp1(float itemMrp1) {
		this.itemMrp1 = itemMrp1;
	}
	public float getItemMrp2() {
		return itemMrp2;
	}
	public void setItemMrp2(float itemMrp2) {
		this.itemMrp2 = itemMrp2;
	}
	public float getItemMrp3() {
		return itemMrp3;
	}
	public void setItemMrp3(float itemMrp3) {
		this.itemMrp3 = itemMrp3;
	}
	public float getItemTax3() {
		return itemTax3;
	}
	public void setItemTax3(float itemTax3) {
		this.itemTax3 = itemTax3;
	}
	public int getItemShelfLife() {
		return itemShelfLife;
	}
	public void setItemShelfLife(int itemShelfLife) {
		this.itemShelfLife = itemShelfLife;
	}
	public int getItemSortId() {
		return itemSortId;
	}
	public void setItemSortId(int itemSortId) {
		this.itemSortId = itemSortId;
	}
	public int getItemIsUsed() {
		return itemIsUsed;
	}
	public void setItemIsUsed(int itemIsUsed) {
		this.itemIsUsed = itemIsUsed;
	}
	public String getItemHsncd() {
		return itemHsncd;
	}
	public void setItemHsncd(String itemHsncd) {
		this.itemHsncd = itemHsncd;
	}
	public String getUom() {
		return uom;
	}
	public void setUom(String uom) {
		this.uom = uom;
	}
	public String getSubCatName() {
		return subCatName;
	}
	public void setSubCatName(String subCatName) {
		this.subCatName = subCatName;
	}
	@Override
	public String toString() {
		return "GetProductListExlPdf [id=" + id + ", itemName=" + itemName + ", itemMrp1=" + itemMrp1 + ", itemMrp2="
				+ itemMrp2 + ", itemMrp3=" + itemMrp3 + ", itemTax3=" + itemTax3 + ", itemShelfLife=" + itemShelfLife
				+ ", itemSortId=" + itemSortId + ", itemIsUsed=" + itemIsUsed + ", itemHsncd=" + itemHsncd + ", uom="
				+ uom + ", subCatName=" + subCatName + "]";
	}
	
	
	
}
