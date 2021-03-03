package com.ats.webapi.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="m_sp_flavour_conf")
public class FlavourConf implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int spFlavConfId;
	
	private int spfId;
	private int spId;
	private int spType;
	
	private float rate;
	
	private float mrp1;
	private float mrp2;
	private float mrp3;
	
	private int delStatus;
	
	private String exVar1;
	private int exInt1;
	/*
	 * @Id
	 * 
	 * @GeneratedValue(strategy = GenerationType.AUTO)
	 * 
	 * @Column(name="flav_id") private int flavId;
	 * 
	 * @Column(name="spf_id") private int spfId;
	 * 
	 * @Column(name="sp_type") private int spType;
	 * 
	 * @Column(name="rate") private float rate;
	 * 
	 * @Column(name="mrp") private float mrp;
	 * 
	 * @Column(name="del_status") private int delStatus;
	 * 
	 * @Column(name="ex_var1") private String exVar1;
	 * 
	 * @Column(name="ex_int1") private int exInt1;
	 * 
	 * @Column(name="sp_id") private int spId;
	 */
	public int getSpFlavConfId() {
		return spFlavConfId;
	}
	public void setSpFlavConfId(int spFlavConfId) {
		this.spFlavConfId = spFlavConfId;
	}
	public int getSpfId() {
		return spfId;
	}
	public void setSpfId(int spfId) {
		this.spfId = spfId;
	}
	public int getSpId() {
		return spId;
	}
	public void setSpId(int spId) {
		this.spId = spId;
	}
	public int getSpType() {
		return spType;
	}
	public void setSpType(int spType) {
		this.spType = spType;
	}
	public float getRate() {
		return rate;
	}
	public void setRate(float rate) {
		this.rate = rate;
	}
	public float getMrp1() {
		return mrp1;
	}
	public void setMrp1(float mrp1) {
		this.mrp1 = mrp1;
	}
	public float getMrp2() {
		return mrp2;
	}
	public void setMrp2(float mrp2) {
		this.mrp2 = mrp2;
	}
	public float getMrp3() {
		return mrp3;
	}
	public void setMrp3(float mrp3) {
		this.mrp3 = mrp3;
	}
	public int getDelStatus() {
		return delStatus;
	}
	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}
	public String getExVar1() {
		return exVar1;
	}
	public void setExVar1(String exVar1) {
		this.exVar1 = exVar1;
	}
	public int getExInt1() {
		return exInt1;
	}
	public void setExInt1(int exInt1) {
		this.exInt1 = exInt1;
	}
	@Override
	public String toString() {
		return "FlavourConf [spFlavConfId=" + spFlavConfId + ", spfId=" + spfId + ", spId=" + spId + ", spType="
				+ spType + ", rate=" + rate + ", mrp1=" + mrp1 + ", mrp2=" + mrp2 + ", mrp3=" + mrp3 + ", delStatus="
				+ delStatus + ", exVar1=" + exVar1 + ", exInt1=" + exInt1 + "]";
	}

	
	
	

	
}
