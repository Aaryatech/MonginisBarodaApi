package com.ats.webapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "reject_remark")
public class rejectRemark {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int rejectId;
	
	private String rejectRemark;
	
	private String rejectDesc;
	
	private int exInt1,exInt2;
	
	private String exVar1,exVar2;
	
	private int delStatus;

	public int getRejectId() {
		return rejectId;
	}

	public void setRejectId(int rejectId) {
		this.rejectId = rejectId;
	}

	public String getRejectRemark() {
		return rejectRemark;
	}

	public void setRejectRemark(String rejectRemark) {
		this.rejectRemark = rejectRemark;
	}

	public String getRejectDesc() {
		return rejectDesc;
	}

	public void setRejectDesc(String rejectDesc) {
		this.rejectDesc = rejectDesc;
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

	@Override
	public String toString() {
		return "rejectRemark [rejectId=" + rejectId + ", rejectRemark=" + rejectRemark + ", rejectDesc=" + rejectDesc
				+ ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2
				+ ", delStatus=" + delStatus + "]";
	}
	
	
	
	
	
	
	

}
