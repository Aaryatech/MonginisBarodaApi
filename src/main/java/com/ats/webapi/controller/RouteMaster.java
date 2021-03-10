package com.ats.webapi.controller;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "m_fr_route")
public class RouteMaster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	//@Column(name="route_id")
	private int routeId;
	
	@Column(name="route_name")
	private String routeName;
	
    @Column(name="del_status")
    private int delStatus;
    
    @Column(name="abc_type")
    private int abcType;
    
    @Column(name="seq_no")
    private int seqNo;
    
    @Column(name="short_name")
    private String shortName;
    
    @Column(name="route_prefix")
    private String routePrefix;
    
    @Column(name="max_km")
    private float maxKm;
    
    @Column(name="min_km")
    private float minKm;
    
    @Column(name="route_type")
    private int routeType;
    
    @Column(name="ex_int1")
    private int exInt1;
    
    @Column(name="ex_int2")
    private int exInt2;
    
    @Column(name="ex_var1")
    private String exVar1;
    
    @Column(name="ex_var2")
    private String exVar2;

	public int getRouteId() {
		return routeId;
	}

	public void setRouteId(int routeId) {
		this.routeId = routeId;
	}

	public String getRouteName() {
		return routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	public int getAbcType() {
		return abcType;
	}

	public void setAbcType(int abcType) {
		this.abcType = abcType;
	}

	public int getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(int seqNo) {
		this.seqNo = seqNo;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getRoutePrefix() {
		return routePrefix;
	}

	public void setRoutePrefix(String routePrefix) {
		this.routePrefix = routePrefix;
	}

	public float getMaxKm() {
		return maxKm;
	}

	public void setMaxKm(float maxKm) {
		this.maxKm = maxKm;
	}

	public float getMinKm() {
		return minKm;
	}

	public void setMinKm(float minKm) {
		this.minKm = minKm;
	}

	public int getRouteType() {
		return routeType;
	}

	public void setRouteType(int routeType) {
		this.routeType = routeType;
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

	@Override
	public String toString() {
		return "RouteMaster [routeId=" + routeId + ", routeName=" + routeName + ", delStatus=" + delStatus
				+ ", abcType=" + abcType + ", seqNo=" + seqNo + ", shortName=" + shortName + ", routePrefix="
				+ routePrefix + ", maxKm=" + maxKm + ", minKm=" + minKm + ", routeType=" + routeType + ", exInt1="
				+ exInt1 + ", exInt2=" + exInt2 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + "]";
	}

}
