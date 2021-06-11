package com.ats.webapi.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class SellBillHeaderNew {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;

	private int sellBillNo;

	private String invoiceNo;

	private Date billDate;

	private float taxableAmt;

	private float totalTax;

	private float grandTotal;

	private float paidAmt;

	private float remainingAmt;
	private float discountAmt;

	private String paymentMode;

	private int discountPer;

	private float payableAmt;

	private String frName;

	private int custId;
	private String custName;
	private String phoneNumber;
	private String gstNo;
	private String address;
	private float cash;
	private float card;
	private float ePay;

	private float roundOff;// 15-4-2020

	private int orderId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getSellBillNo() {
		return sellBillNo;
	}

	public void setSellBillNo(int sellBillNo) {
		this.sellBillNo = sellBillNo;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public Date getBillDate() {
		return billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	public float getTaxableAmt() {
		return taxableAmt;
	}

	public void setTaxableAmt(float taxableAmt) {
		this.taxableAmt = taxableAmt;
	}

	public float getTotalTax() {
		return totalTax;
	}

	public void setTotalTax(float totalTax) {
		this.totalTax = totalTax;
	}

	public float getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(float grandTotal) {
		this.grandTotal = grandTotal;
	}

	public float getPaidAmt() {
		return paidAmt;
	}

	public void setPaidAmt(float paidAmt) {
		this.paidAmt = paidAmt;
	}

	public float getRemainingAmt() {
		return remainingAmt;
	}

	public void setRemainingAmt(float remainingAmt) {
		this.remainingAmt = remainingAmt;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public int getDiscountPer() {
		return discountPer;
	}

	public void setDiscountPer(int discountPer) {
		this.discountPer = discountPer;
	}

	public float getPayableAmt() {
		return payableAmt;
	}

	public void setPayableAmt(float payableAmt) {
		this.payableAmt = payableAmt;
	}

	public String getFrName() {
		return frName;
	}

	public void setFrName(String frName) {
		this.frName = frName;
	}

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getGstNo() {
		return gstNo;
	}

	public void setGstNo(String gstNo) {
		this.gstNo = gstNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public float getCash() {
		return cash;
	}

	public void setCash(float cash) {
		this.cash = cash;
	}

	public float getCard() {
		return card;
	}

	public void setCard(float card) {
		this.card = card;
	}

	public float getePay() {
		return ePay;
	}

	public void setePay(float ePay) {
		this.ePay = ePay;
	}

	public float getDiscountAmt() {
		return discountAmt;
	}

	public void setDiscountAmt(float discountAmt) {
		this.discountAmt = discountAmt;
	}

	public float getRoundOff() {
		return roundOff;
	}

	public void setRoundOff(float roundOff) {
		this.roundOff = roundOff;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	@Override
	public String toString() {
		return "SellBillHeaderNew [id=" + id + ", sellBillNo=" + sellBillNo + ", invoiceNo=" + invoiceNo + ", billDate="
				+ billDate + ", taxableAmt=" + taxableAmt + ", totalTax=" + totalTax + ", grandTotal=" + grandTotal
				+ ", paidAmt=" + paidAmt + ", remainingAmt=" + remainingAmt + ", discountAmt=" + discountAmt
				+ ", paymentMode=" + paymentMode + ", discountPer=" + discountPer + ", payableAmt=" + payableAmt
				+ ", frName=" + frName + ", custId=" + custId + ", custName=" + custName + ", phoneNumber="
				+ phoneNumber + ", gstNo=" + gstNo + ", address=" + address + ", cash=" + cash + ", card=" + card
				+ ", ePay=" + ePay + ", roundOff=" + roundOff + ", orderId=" + orderId + "]";
	}

}
