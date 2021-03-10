package com.ats.webapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "m_abc_type")
public class RouteAbcVal {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column(name="abc_id")
	private int abcId;
	
	@Column(name="abc_val")
	private String abcVal;
	
	@Column(name="del_status")
	private int delStatus;
	
	@Column(name="ex_int1")
	private int exInt1;
	
	@Column(name="ex_var1")
	private String exVar1;

	public int getAbcId() {
		return abcId;
	}

	public void setAbcId(int abcId) {
		this.abcId = abcId;
	}

	public String getAbcVal() {
		return abcVal;
	}

	public void setAbcVal(String abcVal) {
		this.abcVal = abcVal;
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

	@Override
	public String toString() {
		return "RouteAbcVal [abcId=" + abcId + ", abcVal=" + abcVal + ", delStatus=" + delStatus + ", exInt1=" + exInt1
				+ ", exVar1=" + exVar1 + "]";
	}
	
	
}
