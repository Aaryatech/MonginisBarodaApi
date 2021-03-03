package com.ats.webapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "m_fr_configure")
public class FrMenus {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "setting_id")
	private int settingId;
	
	@Column(name = "from_time")
	private String fromTime;

	@Column(name = "to_time")
	private String toTime;
	
	@Column(name = "item_show")
	private String itemShow;
	
	@Column(name = "menu_desc")
	private String menuDesc;
	
	@Column(name = "menu_image")
	private String menuImage;
	
	@Column(name = "selected_menu_image")
	private String selectedMenuImage;
	
	@Column(name = "menu_title")
	private String menuTitle;
	@Column(name = "setting_type")
	private int settingType;
	
	@Column(name = "fr_id")
	private int frId;

	@Column(name = "menu_id")
	private int menuId;

	@Column(name = "cat_id")
	private int catId;

	@Column(name = "is_same_day_applicable")
	private int isSameDayApplicable;

	@Column(name = "day")
	private String day;
	
	@Column(name = "date")
	private String date;
	
	//Sachin 03-03-2021
	
	//New fields 21-01-2021 -sachin
		@Column(name="rate_setting_from")
		int rateSettingFrom;
		@Column(name="profit_per")
		float profitPer;
		@Column(name="rate_setting_type")
		int rateSettingType;
		@Column(name="del_days")
		int delDays;
		@Column(name="prod_days")
		int prodDays;
		@Column(name="is_disc_app")
		int isDiscApp;
		@Column(name="disc_per")
		float discPer;
		@Column(name="grn_per")
		int grnPer;
		//New fields 21-01-2021 -sachin code end
		
	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public int getIsSameDayApplicable() {
		return isSameDayApplicable;
	}

	public void setIsSameDayApplicable(int isSameDayApplicable) {
		this.isSameDayApplicable = isSameDayApplicable;
	}

	public int getSettingId() {
		return settingId;
	}

	public void setSettingId(int settingId) {
		this.settingId = settingId;
	}

	public int getFrId() {
		return frId;
	}

	public void setFrId(int frId) {
		this.frId = frId;
	}

	public String getFromTime() {
		return fromTime;
	}

	public void setFromTime(String fromTime) {
		this.fromTime = fromTime;
	}

	public String getToTime() {
		return toTime;
	}

	public void setToTime(String toTime) {
		this.toTime = toTime;
	}

	public String getItemShow() {
		return itemShow;
	}

	public void setItemShow(String itemShow) {
		this.itemShow = itemShow;
	}

	public String getMenuDesc() {
		return menuDesc;
	}

	public void setMenuDesc(String menuDesc) {
		this.menuDesc = menuDesc;
	}

	public String getMenuImage() {
		return menuImage;
	}

	public void setMenuImage(String menuImage) {
		this.menuImage = menuImage;
	}

	public String getMenuTitle() {
		return menuTitle;
	}

	public void setMenuTitle(String menuTitle) {
		this.menuTitle = menuTitle;
	}

	public int getSettingType() {
		return settingType;
	}

	public void setSettingType(int settingType) {
		this.settingType = settingType;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public int getCatId() {
		return catId;
	}

	public void setCatId(int catId) {
		this.catId = catId;
	}

	public String getSelectedMenuImage() {
		return selectedMenuImage;
	}

	public void setSelectedMenuImage(String selectedMenuImage) {
		this.selectedMenuImage = selectedMenuImage;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "FrMenus [settingId=" + settingId + ", fromTime=" + fromTime + ", toTime=" + toTime + ", itemShow="
				+ itemShow + ", menuDesc=" + menuDesc + ", menuImage=" + menuImage + ", selectedMenuImage="
				+ selectedMenuImage + ", menuTitle=" + menuTitle + ", settingType=" + settingType + ", frId=" + frId
				+ ", menuId=" + menuId + ", catId=" + catId + ", isSameDayApplicable=" + isSameDayApplicable + ", day="
				+ day + ", date=" + date + ", rateSettingFrom=" + rateSettingFrom + ", profitPer=" + profitPer
				+ ", rateSettingType=" + rateSettingType + ", delDays=" + delDays + ", prodDays=" + prodDays
				+ ", isDiscApp=" + isDiscApp + ", discPer=" + discPer + ", grnPer=" + grnPer + "]";
	}

	public int getRateSettingFrom() {
		return rateSettingFrom;
	}

	public void setRateSettingFrom(int rateSettingFrom) {
		this.rateSettingFrom = rateSettingFrom;
	}

	public float getProfitPer() {
		return profitPer;
	}

	public void setProfitPer(float profitPer) {
		this.profitPer = profitPer;
	}

	public int getRateSettingType() {
		return rateSettingType;
	}

	public void setRateSettingType(int rateSettingType) {
		this.rateSettingType = rateSettingType;
	}

	public int getDelDays() {
		return delDays;
	}

	public void setDelDays(int delDays) {
		this.delDays = delDays;
	}

	public int getProdDays() {
		return prodDays;
	}

	public void setProdDays(int prodDays) {
		this.prodDays = prodDays;
	}

	public int getIsDiscApp() {
		return isDiscApp;
	}

	public void setIsDiscApp(int isDiscApp) {
		this.isDiscApp = isDiscApp;
	}

	public float getDiscPer() {
		return discPer;
	}

	public void setDiscPer(float discPer) {
		this.discPer = discPer;
	}

	public int getGrnPer() {
		return grnPer;
	}

	public void setGrnPer(int grnPer) {
		this.grnPer = grnPer;
	}

	 

	

	
	
	
}
