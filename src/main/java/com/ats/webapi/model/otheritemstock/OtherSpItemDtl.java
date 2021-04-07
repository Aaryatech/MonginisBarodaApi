package com.ats.webapi.model.otheritemstock;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class OtherSpItemDtl {
	
	
	@Id
	private int itemId;
	private int frId;
	private String itemCode;
	private String itemName;
	private String spfName;
	private float orderQty;	
	private int menuId;
	private String menuTitle;
	private float orderAmt;
	private String frEmail;
	
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getSpfName() {
		return spfName;
	}
	public void setSpfName(String spfName) {
		this.spfName = spfName;
	}
	public float getOrderQty() {
		return orderQty;
	}
	public void setOrderQty(float orderQty) {
		this.orderQty = orderQty;
	}
	public int getMenuId() {
		return menuId;
	}
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	public String getMenuTitle() {
		return menuTitle;
	}
	public void setMenuTitle(String menuTitle) {
		this.menuTitle = menuTitle;
	}
	public float getOrderAmt() {
		return orderAmt;
	}
	public void setOrderAmt(float orderAmt) {
		this.orderAmt = orderAmt;
	}
	public String getFrEmail() {
		return frEmail;
	}
	public void setFrEmail(String frEmail) {
		this.frEmail = frEmail;
	}	
	public int getFrId() {
		return frId;
	}
	public void setFrId(int frId) {
		this.frId = frId;
	}
	
	@Override
	public String toString() {
		return "OtherSpItemDtl [frId=" + frId + ", itemId=" + itemId + ", itemCode=" + itemCode + ", itemName="
				+ itemName + ", spfName=" + spfName + ", orderQty=" + orderQty + ", menuId=" + menuId + ", menuTitle="
				+ menuTitle + ", orderAmt=" + orderAmt + ", frEmail=" + frEmail + "]";
	}
	
}
