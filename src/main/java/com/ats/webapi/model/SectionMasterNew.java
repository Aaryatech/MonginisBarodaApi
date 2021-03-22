package com.ats.webapi.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.ats.webapi.model.AllMenus;


@Entity
@Table(name = "m_section")
public class SectionMasterNew {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="section_id")
	private int sectionId; 
	
	@Column(name = "section_name")
	private String sectionName; 
	
	@Column(name = "del_status")
	private int delStatus;

	@Column(name = "menu_ids")
	private String menuIds;
	
	@Column(name = "section_type")
	private int sectionType;
	
	
	@Column(name ="menuType")
	private int menuType;
	
	
	@Column(name ="is_active")
	private int isActive;
	
	
	@Transient
	List<AllMenus> menuList;


	public int getSectionId() {
		return sectionId;
	}


	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
	}


	public String getSectionName() {
		return sectionName;
	}


	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}


	public int getDelStatus() {
		return delStatus;
	}


	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}


	public String getMenuIds() {
		return menuIds;
	}


	public void setMenuIds(String menuIds) {
		this.menuIds = menuIds;
	}


	public int getSectionType() {
		return sectionType;
	}


	public void setSectionType(int sectionType) {
		this.sectionType = sectionType;
	}


	public int getMenuType() {
		return menuType;
	}


	public void setMenuType(int menuType) {
		this.menuType = menuType;
	}


	public int getIsActive() {
		return isActive;
	}


	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}


	public List<AllMenus> getMenuList() {
		return menuList;
	}


	public void setMenuList(List<AllMenus> menuList) {
		this.menuList = menuList;
	}


	@Override
	public String toString() {
		return "SectionMasterNew [sectionId=" + sectionId + ", sectionName=" + sectionName + ", delStatus=" + delStatus
				+ ", menuIds=" + menuIds + ", sectionType=" + sectionType + ", menuType=" + menuType + ", isActive="
				+ isActive + ", menuList=" + menuList + "]";
	}
	
	
	
	
	
	
	
	
	
	

}
