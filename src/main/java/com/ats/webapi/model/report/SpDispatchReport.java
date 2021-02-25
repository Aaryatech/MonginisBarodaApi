package com.ats.webapi.model.report;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SpDispatchReport implements Serializable {
	
	//SpDispatchReport for Baroda SP Cake Dispatch PDF.Sachin 24-02-2021
	@Id
	private String uuid;
	
	private String spName;
	private int orderQty;
	private String ordNos;
	private int spIds;
	private float spSelectedWeight;
	private int frId;
	private int spFlavourId;
	private String newItem;
	
	
	public String getNewItem() {
		return newItem;
	}
	public void setNewItem(String newItem) {
		this.newItem = newItem;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getSpName() {
		return spName;
	}
	public void setSpName(String spName) {
		this.spName = spName;
	}
	public int getOrderQty() {
		return orderQty;
	}
	public void setOrderQty(int orderQty) {
		this.orderQty = orderQty;
	}
	public String getOrdNos() {
		return ordNos;
	}
	public void setOrdNos(String ordNos) {
		this.ordNos = ordNos;
	}
	public int getSpIds() {
		return spIds;
	}
	public void setSpIds(int spIds) {
		this.spIds = spIds;
	}
	public float getSpSelectedWeight() {
		return spSelectedWeight;
	}
	public void setSpSelectedWeight(float spSelectedWeight) {
		this.spSelectedWeight = spSelectedWeight;
	}
	public int getFrId() {
		return frId;
	}
	public void setFrId(int frId) {
		this.frId = frId;
	}
	public int getSpFlavourId() {
		return spFlavourId;
	}
	public void setSpFlavourId(int spFlavourId) {
		this.spFlavourId = spFlavourId;
	}
	@Override
	public String toString() {
		return "SpDispatchReport [uuid=" + uuid + ", spName=" + spName + ", orderQty=" + orderQty + ", ordNos=" + ordNos
				+ ", spIds=" + spIds + ", spSelectedWeight=" + spSelectedWeight + ", frId=" + frId + ", spFlavourId="
				+ spFlavourId + ", newItem=" + newItem + "]";
	}
	
	
	
	/*
	 SELECT UUID() as uuid, CONCAT(m_sp_cake.sp_name,' [', m_sp_flavour.spf_name,'] ',t_sp_cake.sp_selected_weight) as sp_name,COUNT(t_sp_cake.sp_order_no) AS order_qty, GROUP_CONCAT(t_sp_cake.sp_order_no)AS ord_nos, 
GROUP_CONCAT(t_sp_cake.sp_id) as sp_ids,
sp_selected_weight,fr_id,sp_flavour_id FROM t_sp_cake,m_sp_cake,m_sp_flavour WHERE m_sp_cake.sp_id=t_sp_cake.sp_id and m_sp_flavour.spf_id=t_sp_cake.sp_flavour_id AND t_sp_cake.sp_delivery_date='2021-02-18' AND t_sp_cake.fr_id in (1,2,3,4,5,6) AND t_sp_cake.menu_id in (40)
GROUP by t_sp_cake.sp_id, t_sp_cake.sp_flavour_id, sp_selected_weight,fr_id
ORDER by fr_id, t_sp_cake.sp_id
	 */

}
