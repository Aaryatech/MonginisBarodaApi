package com.ats.webapi.model.rawmaterial;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "m_rm_item_group")
public class RmItemGroup {

	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="grp_id")
	private int grpId;
	
	@Column(name="del_status")
	private int delStatus;
	
	@Column(name="grp_name")
	private String grpName;
	
	@Column(name="ex_int1")
	private int exInt1;//CATID SAC 10-07-2021
	

	public int getExInt1() {
		return exInt1;
	}

	public void setExInt1(int exInt1) {
		this.exInt1 = exInt1;
	}

	public int getGrpId() {
		return grpId;
	}

	public void setGrpId(int grpId) {
		this.grpId = grpId;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	public String getGrpName() {
		return grpName;
	}

	public void setGrpName(String grpName) {
		this.grpName = grpName;
	}

	@Override
	public String toString() {
		return "RmItemGroup [grpId=" + grpId + ", delStatus=" + delStatus + ", grpName=" + grpName + ", exInt1="
				+ exInt1 + "]";
	}
	
	
}
