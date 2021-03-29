package com.ats.webapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="m_stock_type")
public class StockType {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private  int id;
	
	private String stockTypeName;
	
	private String stockTypeDesc;
	
	private int type;
	
	private int exInt1,exInt2;
	
	private String exVar1,exVar2;
	
	private int delStatus;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStockTypeName() {
		return stockTypeName;
	}

	public void setStockTypeName(String stockTypeName) {
		this.stockTypeName = stockTypeName;
	}

	public String getStockTypeDesc() {
		return stockTypeDesc;
	}

	public void setStockTypeDesc(String stockTypeDesc) {
		this.stockTypeDesc = stockTypeDesc;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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

	@Override
	public String toString() {
		return "StockType [id=" + id + ", stockTypeName=" + stockTypeName + ", stockTypeDesc=" + stockTypeDesc
				+ ", type=" + type + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exVar1=" + exVar1 + ", exVar2="
				+ exVar2 + ", delStatus=" + delStatus + "]";
	}

	
	
	
	
	
	
	
	
	
	
	
	

}
