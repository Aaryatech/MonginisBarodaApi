package com.ats.webapi.model.eway;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EwayItemList {

	@Id
	private int billDetailNo;
	
	private String productName; 
	private String productDesc;
	private int hsnCode;
	private double quantity;
	private String qtyUnit; // maxLen 3 char
	private double cgstRate;
	private double sgstRate;
	private double igstRate;
	private double cessRate;
	private double cessNonAdvol;
	private double taxableAmount;


	public int getBillDetailNo() {
		return billDetailNo;
	}

	public void setBillDetailNo(int billDetailNo) {
		this.billDetailNo = billDetailNo;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public int getHsnCode() {
		return hsnCode;
	}

	public void setHsnCode(int hsnCode) {
		this.hsnCode = hsnCode;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public String getQtyUnit() {
		return qtyUnit;
	}

	public void setQtyUnit(String qtyUnit) {
		this.qtyUnit = qtyUnit;
	}

	public double getCgstRate() {
		return cgstRate;
	}

	public void setCgstRate(double cgstRate) {
		this.cgstRate = cgstRate;
	}

	public double getSgstRate() {
		return sgstRate;
	}

	public void setSgstRate(double sgstRate) {
		this.sgstRate = sgstRate;
	}

	public double getIgstRate() {
		return igstRate;
	}

	public void setIgstRate(double igstRate) {
		this.igstRate = igstRate;
	}

	public double getCessRate() {
		return cessRate;
	}

	public void setCessRate(double cessRate) {
		this.cessRate = cessRate;
	}

	public double getCessNonAdvol() {
		return cessNonAdvol;
	}

	public void setCessNonAdvol(double cessNonAdvol) {
		this.cessNonAdvol = cessNonAdvol;
	}

	public double getTaxableAmount() {
		return taxableAmount;
	}

	public void setTaxableAmount(double taxableAmount) {
		this.taxableAmount = taxableAmount;
	}

	@Override
	public String toString() {
		return "EwayItemList [productName=" + productName + ", productDesc=" + productDesc + ", hsnCode=" + hsnCode
				+ ", quantity=" + quantity + ", qtyUnit=" + qtyUnit + ", cgstRate=" + cgstRate + ", sgstRate="
				+ sgstRate + ", igstRate=" + igstRate + ", cessRate=" + cessRate + ", cessNonAdvol=" + cessNonAdvol
				+ ", taxableAmount=" + taxableAmount + "]";
	}

}
