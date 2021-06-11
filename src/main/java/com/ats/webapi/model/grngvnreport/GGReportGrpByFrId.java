package com.ats.webapi.model.grngvnreport;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity //report 2 Bean
public class GGReportGrpByFrId {
	
	
	@Id
	private int frId;
	
	private int isGrn;
	
	Float aprGrandTotal;
	Float totalAmt;
	String frName;
	int reqQty;
	Float aprQty ;
	public int getFrId() {
		return frId;
	}
	public void setFrId(int frId) {
		this.frId = frId;
	}
	public int getIsGrn() {
		return isGrn;
	}
	public void setIsGrn(int isGrn) {
		this.isGrn = isGrn;
	}
	public Float getAprGrandTotal() {
		return aprGrandTotal;
	}
	public void setAprGrandTotal(Float aprGrandTotal) {
		this.aprGrandTotal = aprGrandTotal;
	}
	public Float getTotalAmt() {
		return totalAmt;
	}
	public void setTotalAmt(Float totalAmt) {
		this.totalAmt = totalAmt;
	}
	public String getFrName() {
		return frName;
	}
	public void setFrName(String frName) {
		this.frName = frName;
	}
	public int getReqQty() {
		return reqQty;
	}
	public void setReqQty(int reqQty) {
		this.reqQty = reqQty;
	}
	
	
	
	
	public Float getAprQty() {
		return aprQty;
	}
	public void setAprQty(Float aprQty) {
		this.aprQty = aprQty;
	}
	@Override
	public String toString() {
		return "GGReportGrpByFrId [frId=" + frId + ", isGrn=" + isGrn + ", aprGrandTotal=" + aprGrandTotal
				+ ", totalAmt=" + totalAmt + ", frName=" + frName + ", reqQty=" + reqQty + ", aprQty=" + aprQty + "]";
	}
	
	

}
