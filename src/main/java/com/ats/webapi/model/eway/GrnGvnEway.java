package com.ats.webapi.model.eway;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_grn_gvn_eway")
public class GrnGvnEway {

	@Id
	private int grnGvnEwayId;

	private int grnGvnHeaderId;
	private long ewayNo;
	private String fromCust;
	private String fromGst;
	private String fromAdd;
	private String fromPincode;
	private String fromKm;

	private String toCust;
	private String toGst;
	private String toAdd;
	private String toPincode;
	private String toKm;

	private int exInt1;
	private int exInt2;
	private String exVar1;
	private String exVar2;
	
	

	public GrnGvnEway() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public GrnGvnEway(int grnGvnEwayId, int grnGvnHeaderId, long ewayNo, String fromCust, String fromGst, String fromAdd,
			String fromPincode, String fromKm, String toCust, String toGst, String toAdd, String toPincode, String toKm,
			int exInt1, int exInt2, String exVar1, String exVar2) {
		super();
		this.grnGvnEwayId = grnGvnEwayId;
		this.grnGvnHeaderId = grnGvnHeaderId;
		this.ewayNo = ewayNo;
		this.fromCust = fromCust;
		this.fromGst = fromGst;
		this.fromAdd = fromAdd;
		this.fromPincode = fromPincode;
		this.fromKm = fromKm;
		this.toCust = toCust;
		this.toGst = toGst;
		this.toAdd = toAdd;
		this.toPincode = toPincode;
		this.toKm = toKm;
		this.exInt1 = exInt1;
		this.exInt2 = exInt2;
		this.exVar1 = exVar1;
		this.exVar2 = exVar2;
	}



	public int getGrnGvnEwayId() {
		return grnGvnEwayId;
	}

	public void setGrnGvnEwayId(int grnGvnEwayId) {
		this.grnGvnEwayId = grnGvnEwayId;
	}

	public int getGrnGvnHeaderId() {
		return grnGvnHeaderId;
	}

	public void setGrnGvnHeaderId(int grnGvnHeaderId) {
		this.grnGvnHeaderId = grnGvnHeaderId;
	}

	public long getEwayNo() {
		return ewayNo;
	}

	public void setEwayNo(long ewayNo) {
		this.ewayNo = ewayNo;
	}

	public String getFromCust() {
		return fromCust;
	}

	public void setFromCust(String fromCust) {
		this.fromCust = fromCust;
	}

	public String getFromGst() {
		return fromGst;
	}

	public void setFromGst(String fromGst) {
		this.fromGst = fromGst;
	}

	public String getFromAdd() {
		return fromAdd;
	}

	public void setFromAdd(String fromAdd) {
		this.fromAdd = fromAdd;
	}

	public String getFromPincode() {
		return fromPincode;
	}

	public void setFromPincode(String fromPincode) {
		this.fromPincode = fromPincode;
	}

	public String getFromKm() {
		return fromKm;
	}

	public void setFromKm(String fromKm) {
		this.fromKm = fromKm;
	}

	public String getToCust() {
		return toCust;
	}

	public void setToCust(String toCust) {
		this.toCust = toCust;
	}

	public String getToGst() {
		return toGst;
	}

	public void setToGst(String toGst) {
		this.toGst = toGst;
	}

	public String getToAdd() {
		return toAdd;
	}

	public void setToAdd(String toAdd) {
		this.toAdd = toAdd;
	}

	public String getToPincode() {
		return toPincode;
	}

	public void setToPincode(String toPincode) {
		this.toPincode = toPincode;
	}

	public String getToKm() {
		return toKm;
	}

	public void setToKm(String toKm) {
		this.toKm = toKm;
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
		return "GrnGvnEway [grnGvnEwayId=" + grnGvnEwayId + ", grnGvnHeaderId=" + grnGvnHeaderId + ", ewayNo=" + ewayNo
				+ ", fromCust=" + fromCust + ", fromGst=" + fromGst + ", fromAdd=" + fromAdd + ", fromPincode="
				+ fromPincode + ", fromKm=" + fromKm + ", toCust=" + toCust + ", toGst=" + toGst + ", toAdd=" + toAdd
				+ ", toPincode=" + toPincode + ", toKm=" + toKm + ", exInt1=" + exInt1 + ", exInt2=" + exInt2
				+ ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + "]";
	}

}
