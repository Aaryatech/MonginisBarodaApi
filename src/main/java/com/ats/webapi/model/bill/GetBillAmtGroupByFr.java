package com.ats.webapi.model.bill;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class GetBillAmtGroupByFr {

	@Id
	private int frId;
	private float total;

	public int getFrId() {
		return frId;
	}

	public void setFrId(int frId) {
		this.frId = frId;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "GetBillAmtGroupByFr [frId=" + frId + ", total=" + total + "]";
	}
	
}
