package com.ats.webapi.model.spprod;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class GetSpCakeExlPdf {
	@Id
	private int spId;
	private String spName;
	
	@Column(name="sp_min_wt")
	private String spMinwt;
	
	@Column(name="sp_max_wt")
	private String spMaxwt;
	
	@Column(name="sp_book_b4")
	private String spBookB4;
	
	@Column(name="is_cust_choice_ck")
	private int isCustChoiceCk;
	
	@Column(name="is_addon_rate_appli")
	private int isAddonRateAppli;
	
	@Column(name="mrp_rate1")
	private float mrpRate1;
	
	@Column(name="mrp_rate2")
	private float mrpRate2;
	
	@Column(name="mrp_rate3")
	private float mrpRate3;
	
	private int increamentedBy;
	
	@Column(name="sp_tax3")
	private int spTax3;
	
	private String spHsncd;
	
	private String uom;
	private String typeName;
	private String shapeName;
	private String eventName;
	private String flavour;
	private int isUsed;
	public int getSpId() {
		return spId;
	}
	public void setSpId(int spId) {
		this.spId = spId;
	}
	public String getSpName() {
		return spName;
	}
	public void setSpName(String spName) {
		this.spName = spName;
	}
	public String getSpMinwt() {
		return spMinwt;
	}
	public void setSpMinwt(String spMinwt) {
		this.spMinwt = spMinwt;
	}
	public String getSpMaxwt() {
		return spMaxwt;
	}
	public void setSpMaxwt(String spMaxwt) {
		this.spMaxwt = spMaxwt;
	}
	public String getSpBookB4() {
		return spBookB4;
	}
	public void setSpBookB4(String spBookB4) {
		this.spBookB4 = spBookB4;
	}
	public int getIsCustChoiceCk() {
		return isCustChoiceCk;
	}
	public void setIsCustChoiceCk(int isCustChoiceCk) {
		this.isCustChoiceCk = isCustChoiceCk;
	}
	public int getIsAddonRateAppli() {
		return isAddonRateAppli;
	}
	public void setIsAddonRateAppli(int isAddonRateAppli) {
		this.isAddonRateAppli = isAddonRateAppli;
	}
	public float getMrpRate1() {
		return mrpRate1;
	}
	public void setMrpRate1(float mrpRate1) {
		this.mrpRate1 = mrpRate1;
	}
	public float getMrpRate2() {
		return mrpRate2;
	}
	public void setMrpRate2(float mrpRate2) {
		this.mrpRate2 = mrpRate2;
	}
	public float getMrpRate3() {
		return mrpRate3;
	}
	public void setMrpRate3(float mrpRate3) {
		this.mrpRate3 = mrpRate3;
	}
	public int getIncreamentedBy() {
		return increamentedBy;
	}
	public void setIncreamentedBy(int increamentedBy) {
		this.increamentedBy = increamentedBy;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getShapeName() {
		return shapeName;
	}
	public void setShapeName(String shapeName) {
		this.shapeName = shapeName;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getFlavour() {
		return flavour;
	}
	public void setFlavour(String flavour) {
		this.flavour = flavour;
	}
	public int getIsUsed() {
		return isUsed;
	}
	public void setIsUsed(int isUsed) {
		this.isUsed = isUsed;
	}
	public String getUom() {
		return uom;
	}
	public void setUom(String uom) {
		this.uom = uom;
	}
	public int getSpTax3() {
		return spTax3;
	}
	public void setSpTax3(int spTax3) {
		this.spTax3 = spTax3;
	}
	public String getSpHsncd() {
		return spHsncd;
	}
	public void setSpHsncd(String spHsncd) {
		this.spHsncd = spHsncd;
	}
	@Override
	public String toString() {
		return "GetSpCakeExlPdf [spId=" + spId + ", spName=" + spName + ", spMinwt=" + spMinwt + ", spMaxwt=" + spMaxwt
				+ ", spBookB4=" + spBookB4 + ", isCustChoiceCk=" + isCustChoiceCk + ", isAddonRateAppli="
				+ isAddonRateAppli + ", mrpRate1=" + mrpRate1 + ", mrpRate2=" + mrpRate2 + ", mrpRate3=" + mrpRate3
				+ ", increamentedBy=" + increamentedBy + ", spTax3=" + spTax3 + ", spHsncd=" + spHsncd + ", uom=" + uom
				+ ", typeName=" + typeName + ", shapeName=" + shapeName + ", eventName=" + eventName + ", flavour="
				+ flavour + ", isUsed=" + isUsed + "]";
	}
	
}
