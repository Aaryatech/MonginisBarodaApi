package com.ats.webapi.model.einv;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class EInvBillHeader {
	
	@Id
	private int billNo;
	private String invoiceNo;
	private Date billDate;
	
	private int frId;
	private double taxApplicable;
	
	private double taxableAmt;
	private double totalTax;
	private double grandTotal;
	private double discAmt;
	
	private double roundOff;
	
	private double sgstSum;
	private double cgstSum;
	private double igstSum;
	private double cessSum;
	
	private String partyName;
	private String partyGstin;
	private String partyAddress;
	
	private String vehNo;
	
	
	@Transient
	List<EInvBillDetail> eInvBillDetail;
	

	public List<EInvBillDetail> geteInvBillDetail() {
		return eInvBillDetail;
	}

	public void seteInvBillDetail(List<EInvBillDetail> eInvBillDetail) {
		this.eInvBillDetail = eInvBillDetail;
	}

	public int getBillNo() {
		return billNo;
	}

	public void setBillNo(int billNo) {
		this.billNo = billNo;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd/MM/yyyy")
	public Date getBillDate() {
		return billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	public int getFrId() {
		return frId;
	}

	public void setFrId(int frId) {
		this.frId = frId;
	}

	/*
	 * public double getTaxAppicable() { return taxAppicable; }
	 * 
	 * public void setTaxAppicable(double taxAppicable) { this.taxAppicable =
	 * taxAppicable; }
	 */
	
	

	public double getTaxableAmt() {
		return taxableAmt;
	}

	public double getTaxApplicable() {
		return taxApplicable;
	}

	public void setTaxApplicable(double taxApplicable) {
		this.taxApplicable = taxApplicable;
	}

	public void setTaxableAmt(double taxableAmt) {
		this.taxableAmt = taxableAmt;
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

	public double getDiscAmt() {
		return discAmt;
	}

	public void setDiscAmt(double discAmt) {
		this.discAmt = discAmt;
	}

	public double getRoundOff() {
		return roundOff;
	}

	public void setRoundOff(double roundOff) {
		this.roundOff = roundOff;
	}

	public double getSgstSum() {
		return sgstSum;
	}

	public void setSgstSum(double sgstSum) {
		this.sgstSum = sgstSum;
	}

	public double getCgstSum() {
		return cgstSum;
	}

	public void setCgstSum(double cgstSum) {
		this.cgstSum = cgstSum;
	}

	public double getIgstSum() {
		return igstSum;
	}

	public void setIgstSum(double igstSum) {
		this.igstSum = igstSum;
	}

	public double getCessSum() {
		return cessSum;
	}

	public void setCessSum(double cessSum) {
		this.cessSum = cessSum;
	}

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public String getPartyGstin() {
		return partyGstin;
	}

	public void setPartyGstin(String partyGstin) {
		this.partyGstin = partyGstin;
	}

	public String getPartyAddress() {
		return partyAddress;
	}

	public void setPartyAddress(String partyAddress) {
		this.partyAddress = partyAddress;
	}

	public String getVehNo() {
		return vehNo;
	}

	public void setVehNo(String vehNo) {
		this.vehNo = vehNo;
	}

	@Override
	public String toString() {
		return "EInvBillHeader [billNo=" + billNo + ", invoiceNo=" + invoiceNo + ", billDate=" + billDate + ", frId="
				+ frId + ", taxApplicable=" + taxApplicable + ", taxableAmt=" + taxableAmt + ", totalTax=" + totalTax
				+ ", grandTotal=" + grandTotal + ", discAmt=" + discAmt + ", roundOff=" + roundOff + ", sgstSum="
				+ sgstSum + ", cgstSum=" + cgstSum + ", igstSum=" + igstSum + ", cessSum=" + cessSum + ", partyName="
				+ partyName + ", partyGstin=" + partyGstin + ", partyAddress=" + partyAddress + ", vehNo=" + vehNo
				+ ", eInvBillDetail=" + eInvBillDetail + "]";
	}
	
}
