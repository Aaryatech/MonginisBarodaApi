package com.ats.webapi.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="menuShow")
@Table(name = "m_fr_menu_show")



public class OrderCounts implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="menu_id")
	int menuId;
	
	@Column(name="menu_title")
	String menuTitle;
	
	float total;
	
	String toTime;
	
	int settingType;
	
	String menuSch;
	
	
	
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
	
	
	
	

	
	public String getToTime() {
		return toTime;
	}
	public void setToTime(String toTime) {
		this.toTime = toTime;
	}
	public int getSettingType() {
		return settingType;
	}
	public void setSettingType(int settingType) {
		this.settingType = settingType;
	}
	public String getMenuSch() {
		return menuSch;
	}
	public void setMenuSch(String menuSch) {
		this.menuSch = menuSch;
	}
	
	
	
	
	
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	@Override
	public String toString() {
		return "OrderCounts [menuId=" + menuId + ", menuTitle=" + menuTitle + ", total=" + total + ", toTime=" + toTime
				+ ", settingType=" + settingType + ", menuSch=" + menuSch + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
