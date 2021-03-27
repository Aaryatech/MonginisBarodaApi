package com.ats.webapi.model.einv;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class EInvBillDetail {
	@Id
	private int billDetailNo;
	private int billNo;
	private int orderId;
	private int itemId;
	private double billQty;
	private double orderQty;
	
	private double mrp;
	private double rate;
	private double baseRate;
	
	private double taxableAmt;
	
	private double sgstRs;
	private double cgstRs;
	private double igstRs;
	
	private double totalTax;
	
	private double grandTotal;
	
	private Date expiryDate;
	
	
	private double discPer;
	
	private String itemName;
	private String itemUom;
	private String hsnCode;
	
	public String getHsnCode() {
		return hsnCode;
	}
	public void setHsnCode(String hsnCode) {
		this.hsnCode = hsnCode;
	}
	public int getBillDetailNo() {
		return billDetailNo;
	}
	public void setBillDetailNo(int billDetailNo) {
		this.billDetailNo = billDetailNo;
	}
	public int getBillNo() {
		return billNo;
	}
	public void setBillNo(int billNo) {
		this.billNo = billNo;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public double getBillQty() {
		return billQty;
	}
	public void setBillQty(double billQty) {
		this.billQty = billQty;
	}
	public double getOrderQty() {
		return orderQty;
	}
	public void setOrderQty(double orderQty) {
		this.orderQty = orderQty;
	}
	public double getMrp() {
		return mrp;
	}
	public void setMrp(double mrp) {
		this.mrp = mrp;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public double getBaseRate() {
		return baseRate;
	}
	public void setBaseRate(double baseRate) {
		this.baseRate = baseRate;
	}
	public double getTaxableAmt() {
		return taxableAmt;
	}
	public void setTaxableAmt(double taxableAmt) {
		this.taxableAmt = taxableAmt;
	}
	public double getSgstRs() {
		return sgstRs;
	}
	public void setSgstRs(double sgstRs) {
		this.sgstRs = sgstRs;
	}
	public double getCgstRs() {
		return cgstRs;
	}
	public void setCgstRs(double cgstRs) {
		this.cgstRs = cgstRs;
	}
	public double getIgstRs() {
		return igstRs;
	}
	public void setIgstRs(double igstRs) {
		this.igstRs = igstRs;
	}
	public double getTotalTax() {
		return totalTax;
	}
	public void setTotalTax(double totalTax) {
		this.totalTax = totalTax;
	}
	public double getGrandTotal() {
		return grandTotal;
	}
	public void setGrandTotal(double grandTotal) {
		this.grandTotal = grandTotal;
	}
	@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd/MM/yyyy")
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	public double getDiscPer() {
		return discPer;
	}
	public void setDiscPer(double discPer) {
		this.discPer = discPer;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemUom() {
		return itemUom;
	}
	public void setItemUom(String itemUom) {
		this.itemUom = itemUom;
	}
	
	@Override
	public String toString() {
		return "EInvBillDetail [billDetailNo=" + billDetailNo + ", billNo=" + billNo + ", orderId=" + orderId
				+ ", itemId=" + itemId + ", billQty=" + billQty + ", orderQty=" + orderQty + ", mrp=" + mrp + ", rate="
				+ rate + ", baseRate=" + baseRate + ", taxableAmt=" + taxableAmt + ", sgstRs=" + sgstRs + ", cgstRs="
				+ cgstRs + ", igstRs=" + igstRs + ", totalTax=" + totalTax + ", grandTotal=" + grandTotal
				+ ", expiryDate=" + expiryDate + ", discPer=" + discPer + ", itemName=" + itemName + ", itemUom="
				+ itemUom + "]";
	}
	
	
}
