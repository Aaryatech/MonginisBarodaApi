package com.ats.webapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="m_menu_type")
public class MenuType {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int menuTypeId;
	
	private int menuType;
	
	private String menuTypeDesc;
	
	private int isActive;
	
	private int delStatus;
	
	private int exInt1;
	
	private String exVar1;
	
	private float exFloat1;

	public int getMenuTypeId() {
		return menuTypeId;
	}

	public void setMenuTypeId(int menuTypeId) {
		this.menuTypeId = menuTypeId;
	}

	public int getMenuType() {
		return menuType;
	}

	public void setMenuType(int menuType) {
		this.menuType = menuType;
	}

	public String getMenuTypeDesc() {
		return menuTypeDesc;
	}

	public void setMenuTypeDesc(String menuTypeDesc) {
		this.menuTypeDesc = menuTypeDesc;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	public int getExInt1() {
		return exInt1;
	}

	public void setExInt1(int exInt1) {
		this.exInt1 = exInt1;
	}

	public String getExVar1() {
		return exVar1;
	}

	public void setExVar1(String exVar1) {
		this.exVar1 = exVar1;
	}

	public float getExFloat1() {
		return exFloat1;
	}

	public void setExFloat1(float exFloat1) {
		this.exFloat1 = exFloat1;
	}

	@Override
	public String toString() {
		return "MenuType [menuTypeId=" + menuTypeId + ", menuType=" + menuType + ", menuTypeDesc=" + menuTypeDesc
				+ ", isActive=" + isActive + ", delStatus=" + delStatus + ", exInt1=" + exInt1 + ", exVar1=" + exVar1
				+ ", exFloat1=" + exFloat1 + "]";
	}
	
	
	
	
	
	
	

}
