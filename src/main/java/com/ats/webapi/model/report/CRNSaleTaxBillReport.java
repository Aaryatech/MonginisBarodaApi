package com.ats.webapi.model.report;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CRNSaleTaxBillReport {

	@Id
	private String id;
	private String UserGstNo;
	private Date billDate;
	private int crnNo;
	private String crnInvoiceNo;
	private String crnDate;
	private float taxPer;
	private String frName;
	private float taxAmount;
	private float billAmount;
	private float igst;
	private float cgst;
	private float sgst;
	private int frId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserGstNo() {
		return UserGstNo;
	}

	public void setUserGstNo(String userGstNo) {
		UserGstNo = userGstNo;
	}

	public Date getBillDate() {
		return billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	public int getCrnNo() {
		return crnNo;
	}

	public void setCrnNo(int crnNo) {
		this.crnNo = crnNo;
	}

	public String getCrnInvoiceNo() {
		return crnInvoiceNo;
	}

	public void setCrnInvoiceNo(String crnInvoiceNo) {
		this.crnInvoiceNo = crnInvoiceNo;
	}

	public String getCrnDate() {
		return crnDate;
	}

	public void setCrnDate(String crnDate) {
		this.crnDate = crnDate;
	}

	public float getTaxPer() {
		return taxPer;
	}

	public void setTaxPer(float taxPer) {
		this.taxPer = taxPer;
	}

	public String getFrName() {
		return frName;
	}

	public void setFrName(String frName) {
		this.frName = frName;
	}

	public float getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(float taxAmount) {
		this.taxAmount = taxAmount;
	}

	public float getBillAmount() {
		return billAmount;
	}

	public void setBillAmount(float billAmount) {
		this.billAmount = billAmount;
	}

	public float getIgst() {
		return igst;
	}

	public void setIgst(float igst) {
		this.igst = igst;
	}

	public float getCgst() {
		return cgst;
	}

	public void setCgst(float cgst) {
		this.cgst = cgst;
	}

	public float getSgst() {
		return sgst;
	}

	public void setSgst(float sgst) {
		this.sgst = sgst;
	}

	public int getFrId() {
		return frId;
	}

	public void setFrId(int frId) {
		this.frId = frId;
	}

	@Override
	public String toString() {
		return "CRNSaleTaxBillReport [id=" + id + ", UserGstNo=" + UserGstNo + ", billDate=" + billDate + ", crnNo="
				+ crnNo + ", crnInvoiceNo=" + crnInvoiceNo + ", crnDate=" + crnDate + ", taxPer=" + taxPer + ", frName="
				+ frName + ", taxAmount=" + taxAmount + ", billAmount=" + billAmount + ", igst=" + igst + ", cgst="
				+ cgst + ", sgst=" + sgst + ", frId=" + frId + "]";
	}

}
