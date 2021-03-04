package com.ats.webapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "m_shape")
public class Shape {
	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="shape_id")
	private Integer shapeId;
	
	@Column(name="shape_name")
	private String shapeName;
	
	@Column(name="shape_desc")
	private String shapeDesc;
	
	@Column(name="ext_int1")
	private Integer extInt1;
	
	@Column(name="ext_int2")
	private Integer extInt2;
	
	@Column(name="ext_var1")
	private String extVar1;
	
	@Column(name="ext_var2")
	private String extVar2;
	
	@Column(name="del_status")
	private Integer delStatus;
	

	
	
	
	public Integer getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(Integer delStatus) {
		this.delStatus = delStatus;
	}

	public Integer getShapeId() {
		return shapeId;
	}

	public void setShapeId(Integer shapeId) {
		this.shapeId = shapeId;
	}

	public String getShapeName() {
		return shapeName;
	}

	public void setShapeName(String shapeName) {
		this.shapeName = shapeName;
	}

	public String getShapeDesc() {
		return shapeDesc;
	}

	public void setShapeDesc(String shapeDesc) {
		this.shapeDesc = shapeDesc;
	}

	public Integer getExtInt1() {
		return extInt1;
	}

	public void setExtInt1(Integer extInt1) {
		this.extInt1 = extInt1;
	}

	public Integer getExtInt2() {
		return extInt2;
	}

	public void setExtInt2(Integer extInt2) {
		this.extInt2 = extInt2;
	}

	public String getExtVar1() {
		return extVar1;
	}

	public void setExtVar1(String extVar1) {
		this.extVar1 = extVar1;
	}

	public String getExtVar2() {
		return extVar2;
	}

	public void setExtVar2(String extVar2) {
		this.extVar2 = extVar2;
	}

	@Override
	public String toString() {
		return "Shape [shapeId=" + shapeId + ", shapeName=" + shapeName + ", shapeDesc=" + shapeDesc + ", extInt1="
				+ extInt1 + ", extInt2=" + extInt2 + ", extVar1=" + extVar1 + ", extVar2=" + extVar2 + ", delStatus="
				+ delStatus + "]";
	}


	


}
