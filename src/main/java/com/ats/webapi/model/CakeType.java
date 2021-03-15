package com.ats.webapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "m_cake_type")
public class CakeType {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column(name="cake_type_id")
	private int cakeTypeId;	
	
	@Column(name="type_name")
	private String typeName;	
	
	@Column(name="extra_field_applicable")
	private int extraFieldApplicable;
	
	@Column(name="type_condition")
	private int typeCondition;
	
	@Column(name="ex_int1")
	private int exInt1;
	
	@Column(name="ex_int2")
	private int exInt2;
	
	@Column(name="ex_var1")
	private String exVar1;
	
	@Column(name="ex_var2")
	private String exVar2;
	
	@Column(name="del_status")
	private int delStatus;
	
	@Column(name="is_active")
	private int isActive;

	public int getCakeTypeId() {
		return cakeTypeId;
	}

	public void setCakeTypeId(int cakeTypeId) {
		this.cakeTypeId = cakeTypeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public int getExtraFieldApplicable() {
		return extraFieldApplicable;
	}

	public void setExtraFieldApplicable(int extraFieldApplicable) {
		this.extraFieldApplicable = extraFieldApplicable;
	}

	public int getTypeCondition() {
		return typeCondition;
	}

	public void setTypeCondition(int typeCondition) {
		this.typeCondition = typeCondition;
	}

	public int getExInt1() {
		return exInt1;
	}

	public void setExInt1(int exInt1) {
		this.exInt1 = exInt1;
	}

	public int getExInt2() {
		return exInt2;
	}

	public void setExInt2(int exInt2) {
		this.exInt2 = exInt2;
	}

	public String getExVar1() {
		return exVar1;
	}

	public void setExVar1(String exVar1) {
		this.exVar1 = exVar1;
	}

	public String getExVar2() {
		return exVar2;
	}

	public void setExVar2(String exVar2) {
		this.exVar2 = exVar2;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "CakeType [cakeTypeId=" + cakeTypeId + ", typeName=" + typeName + ", extraFieldApplicable="
				+ extraFieldApplicable + ", typeCondition=" + typeCondition + ", exInt1=" + exInt1 + ", exInt2="
				+ exInt2 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + ", delStatus=" + delStatus + ", isActive="
				+ isActive + "]";
	}
	
	
}
