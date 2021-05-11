package com.ats.webapi.model;


import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class RegSpCakeReportResponse {
	@Id
	
	private String itemName;
	private String frName;
    private int qty;
    private String rspCustMobileNo;
    private String rspCustName;
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getFrName() {
		return frName;
	}
	public void setFrName(String frName) {
		this.frName = frName;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
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
		return "RegSpCakeReportResponse [itemName=" + itemName + ", frName=" + frName + ", qty=" + qty
				+ ", rspCustMobileNo=" + rspCustMobileNo + ", rspCustName=" + rspCustName + "]";
	}
	
    
}
