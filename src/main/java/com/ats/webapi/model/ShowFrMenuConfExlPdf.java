package com.ats.webapi.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ShowFrMenuConfExlPdf {

	@Id
	private int  menuId;
	private String menuTitle;
	private int type;
	private String catName;
	private float profitPer;	
	private float grnPer;
	private float discPer;
	private String frName;
	private String fromTime;
	private String toTime;
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
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	public float getProfitPer() {
		return profitPer;
	}
	public void setProfitPer(float profitPer) {
		this.profitPer = profitPer;
	}
	public float getGrnPer() {
		return grnPer;
	}
	public void setGrnPer(float grnPer) {
		this.grnPer = grnPer;
	}
	public float getDiscPer() {
		return discPer;
	}
	public void setDiscPer(float discPer) {
		this.discPer = discPer;
	}
	public String getFrName() {
		return frName;
	}
	public void setFrName(String frName) {
		this.frName = frName;
	}
	public String getFromTime() {
		return fromTime;
	}
	public void setFromTime(String fromTime) {
		this.fromTime = fromTime;
	}
	public String getToTime() {
		return toTime;
	}
	public void setToTime(String toTime) {
		this.toTime = toTime;
	}
	@Override
	public String toString() {
		return "ShowFrMenuConfExlPdf [menuId=" + menuId + ", menuTitle=" + menuTitle + ", type=" + type + ", catName="
				+ catName + ", profitPer=" + profitPer + ", grnPer=" + grnPer + ", discPer=" + discPer + ", frName="
				+ frName + ", fromTime=" + fromTime + ", toTime=" + toTime + "]";
	}
	
	
}
