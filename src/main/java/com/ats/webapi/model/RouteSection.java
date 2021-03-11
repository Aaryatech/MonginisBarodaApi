package com.ats.webapi.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.ats.webapi.model.AllMenus;


//Akhilesh 2020-01-20
@Entity
@Table(name="m_route_section")
public class RouteSection {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int sectionId;	
	private String routeIds;	
	private String sectionName;	
	private int secType;	
	private int makerUserId;	
	private String makerDatetime;	
	private int exInt1,exInt2;	
	private String exVar1,exVar2;	
	private int delStatus;

	public int getSectionId() {
		return sectionId;
	}

	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
	}

	public String getRouteIds() {
		return routeIds;
	}

	public void setRouteIds(String routeIds) {
		this.routeIds = routeIds;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public int getMakerUserId() {
		return makerUserId;
	}

	public void setMakerUserId(int makerUserId) {
		this.makerUserId = makerUserId;
	}

	public String getMakerDatetime() {
		return makerDatetime;
	}

	public void setMakerDatetime(String makerDatetime) {
		this.makerDatetime = makerDatetime;
	}

	public int getExInt1() {
		return exInt1;
	}

	public void setExInt1(int exInt1) {
		this.exInt1 = exInt1;
	}

	public int getExInt2() {
		return exInt2;
	}

	public void setExInt2(int exInt2) {
		this.exInt2 = exInt2;
	}

	public String getExVar1() {
		return exVar1;
	}

	public void setExVar1(String exVar1) {
		this.exVar1 = exVar1;
	}

	public String getExVar2() {
		return exVar2;
	}

	public void setExVar2(String exVar2) {
		this.exVar2 = exVar2;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	public int getSecType() {
		return secType;
	}

	public void setSecType(int secType) {
		this.secType = secType;
	}

	@Override
	public String toString() {
		return "RouteSection [sectionId=" + sectionId + ", routeIds=" + routeIds + ", sectionName=" + sectionName
				+ ", secType=" + secType + ", makerUserId=" + makerUserId + ", makerDatetime=" + makerDatetime
				+ ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2
				+ ", delStatus=" + delStatus + "]";
	}
	
}
