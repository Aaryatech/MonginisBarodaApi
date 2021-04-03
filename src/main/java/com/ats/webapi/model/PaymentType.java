package com.ats.webapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "m_payment_type")
public class PaymentType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "payment_type_id")
	private int paymentTypeId;

	@Column(name = "type_name")
	private String typeName;
	 
	@Column(name = "payment_mode_id")
	private int paymentModeId;
	
	@Column(name = "del_status")
	private int delStatus;

	public int getPaymentTypeId() {
		return paymentTypeId;
	}

	public void setPaymentTypeId(int paymentTypeId) {
		this.paymentTypeId = paymentTypeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public int getPaymentModeId() {
		return paymentModeId;
	}

	public void setPaymentModeId(int paymentModeId) {
		this.paymentModeId = paymentModeId;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	@Override
	public String toString() {
		return "PaymentType [paymentTypeId=" + paymentTypeId + ", typeName=" + typeName + ", paymentModeId="
				+ paymentModeId + ", delStatus=" + delStatus + "]";
	}
	
	

}
