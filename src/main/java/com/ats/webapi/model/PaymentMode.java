package com.ats.webapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "m_payment_mode")
public class PaymentMode {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "mode_id")
	private int modeId;

	@Column(name = "mode_name")
	private String modeName;
 
	@Column(name = "del_status")
	private int delStatus;

	public int getModeId() {
		return modeId;
	}

	public void setModeId(int modeId) {
		this.modeId = modeId;
	}

	public String getModeName() {
		return modeName;
	}

	public void setModeName(String modeName) {
		this.modeName = modeName;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	@Override
	public String toString() {
		return "PaymentMode [modeId=" + modeId + ", modeName=" + modeName + ", delStatus=" + delStatus + "]";
	}
	
	
	
	

}
