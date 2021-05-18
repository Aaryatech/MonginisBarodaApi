package com.ats.webapi.model;


import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class RegSpCakeReportResponse {
	@Id
	private int rspId;
	private String frName;
	private String itemName;
    private String qty;
    private String rspCustMobileNo;
    private String rspCustName;
	public int getRspId() {
		return rspId;
	}
	public void setRspId(int rspId) {
		this.rspId = rspId;
	}
	public String getFrName() {
		return frName;
	}
	public void setFrName(String frName) {
		this.frName = frName;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getQty() {
		return qty;
	}
	public void setQty(String qty) {
		this.qty = qty;
	}
	public String getRspCustMobileNo() {
		return rspCustMobileNo;
	}
	public void setRspCustMobileNo(String rspCustMobileNo) {
		this.rspCustMobileNo = rspCustMobileNo;
	}
	public String getRspCustName() {
		return rspCustName;
	}
	public void setRspCustName(String rspCustName) {
		this.rspCustName = rspCustName;
	}
	@Override
	public String toString() {
		return "RegSpCakeReportResponse [rspId=" + rspId + ", frName=" + frName + ", itemName=" + itemName + ", qty="
				+ qty + ", rspCustMobileNo=" + rspCustMobileNo + ", rspCustName=" + rspCustName + "]";
	}
	
    
}
