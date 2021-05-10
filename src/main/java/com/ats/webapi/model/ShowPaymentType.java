package com.ats.webapi.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ShowPaymentType {
	@Id
	private int paymentTypeId;
	private String typeName;
	private String modeName;
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
	public String getModeName() {
		return modeName;
	}
	public void setModeName(String modeName) {
		this.modeName = modeName;
	}
	@Override
	public String toString() {
		return "ShowPaymentType [paymentTypeId=" + paymentTypeId + ", typeName=" + typeName + ", modeName=" + modeName
				+ "]";
	}
	
	
}
