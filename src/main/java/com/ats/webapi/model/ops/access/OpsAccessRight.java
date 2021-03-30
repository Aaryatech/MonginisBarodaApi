package com.ats.webapi.model.ops.access;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ops_module")
public class OpsAccessRight {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="module_id")
	private int moduleId;
	
	@Column(name="module_name")
	private String moduleName;
	
	@Column(name="mapping_name")
	private String mappingName;
	
	@Column(name="icons")
	private String icons;
	
	@Column(name="view")
	private int view;
	
	@Column(name="setting_value")
	private int settingValue;
	
	@Column(name="ex_int1")
	private int exInt1;
	
	@Column(name="ex_var1")
	private String exVar1;

	public int getModuleId() {
		return moduleId;
	}

	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getMappingName() {
		return mappingName;
	}

	public void setMappingName(String mappingName) {
		this.mappingName = mappingName;
	}

	public String getIcons() {
		return icons;
	}

	public void setIcons(String icons) {
		this.icons = icons;
	}

	public int getView() {
		return view;
	}

	public void setView(int view) {
		this.view = view;
	}

	public int getSettingValue() {
		return settingValue;
	}

	public void setSettingValue(int settingValue) {
		this.settingValue = settingValue;
	}

	public int getExInt1() {
		return exInt1;
	}

	public void setExInt1(int exInt1) {
		this.exInt1 = exInt1;
	}

	public String getExVar1() {
		return exVar1;
	}

	public void setExVar1(String exVar1) {
		this.exVar1 = exVar1;
	}

	@Override
	public String toString() {
		return "OpsAccessRight [moduleId=" + moduleId + ", moduleName=" + moduleName + ", mappingName=" + mappingName
				+ ", icons=" + icons + ", view=" + view + ", settingValue=" + settingValue + ", exInt1=" + exInt1
				+ ", exVar1=" + exVar1 + "]";
	}

}
