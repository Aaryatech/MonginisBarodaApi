package com.ats.webapi.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "m_customer1")
public class CustomerForOps {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int custId;

	private String custName;
	private String phoneNumber;
	private String whatsappNo;
	private String emailId;
	private String profilePic;
	private int gender;
	private Date custDob;
	private String ageGroup;
	private int langId;
	private int cityId;
	private int compId;
	private int frId;
	private int isBuissHead;
	private String companyName;
	private String gstNo;
	private String address;
	private int isActive;
	private int delStatus;
	private int custAddPlatform;
	private String custAddDatetime;
	private int addedFromType;
	private int userId;
	private int isPremiumCust;
	private int exInt1;
	private int exInt2;
	private int exInt3;
	private int exInt4;
	private int exInt5;
	private String exVar1;
	private String exVar2;
	private String exVar3;
	private String exVar4;
	private String exVar5;
	private float exFloat1;
	private float exFloat2;
	private float exFloat3;
	private float exFloat4;
	private float exFloat5;

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getWhatsappNo() {
		return whatsappNo;
	}

	public void setWhatsappNo(String whatsappNo) {
		this.whatsappNo = whatsappNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public Date getCustDob() {
		return custDob;
	}

	public void setCustDob(Date custDob) {
		this.custDob = custDob;
	}

	public String getAgeGroup() {
		return ageGroup;
	}

	public void setAgeGroup(String ageGroup) {
		this.ageGroup = ageGroup;
	}

	public int getLangId() {
		return langId;
	}

	public void setLangId(int langId) {
		this.langId = langId;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public int getCompId() {
		return compId;
	}

	public void setCompId(int compId) {
		this.compId = compId;
	}

	public int getFrId() {
		return frId;
	}

	public void setFrId(int frId) {
		this.frId = frId;
	}

	public int getIsBuissHead() {
		return isBuissHead;
	}

	public void setIsBuissHead(int isBuissHead) {
		this.isBuissHead = isBuissHead;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getGstNo() {
		return gstNo;
	}

	public void setGstNo(String gstNo) {
		this.gstNo = gstNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	public int getCustAddPlatform() {
		return custAddPlatform;
	}

	public void setCustAddPlatform(int custAddPlatform) {
		this.custAddPlatform = custAddPlatform;
	}

	public String getCustAddDatetime() {
		return custAddDatetime;
	}

	public void setCustAddDatetime(String custAddDatetime) {
		this.custAddDatetime = custAddDatetime;
	}

	public int getAddedFromType() {
		return addedFromType;
	}

	public void setAddedFromType(int addedFromType) {
		this.addedFromType = addedFromType;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getIsPremiumCust() {
		return isPremiumCust;
	}

	public void setIsPremiumCust(int isPremiumCust) {
		this.isPremiumCust = isPremiumCust;
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

	public int getExInt3() {
		return exInt3;
	}

	public void setExInt3(int exInt3) {
		this.exInt3 = exInt3;
	}

	public int getExInt4() {
		return exInt4;
	}

	public void setExInt4(int exInt4) {
		this.exInt4 = exInt4;
	}

	public int getExInt5() {
		return exInt5;
	}

	public void setExInt5(int exInt5) {
		this.exInt5 = exInt5;
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

	public String getExVar3() {
		return exVar3;
	}

	public void setExVar3(String exVar3) {
		this.exVar3 = exVar3;
	}

	public String getExVar4() {
		return exVar4;
	}

	public void setExVar4(String exVar4) {
		this.exVar4 = exVar4;
	}

	public String getExVar5() {
		return exVar5;
	}

	public void setExVar5(String exVar5) {
		this.exVar5 = exVar5;
	}

	public float getExFloat1() {
		return exFloat1;
	}

	public void setExFloat1(float exFloat1) {
		this.exFloat1 = exFloat1;
	}

	public float getExFloat2() {
		return exFloat2;
	}

	public void setExFloat2(float exFloat2) {
		this.exFloat2 = exFloat2;
	}

	public float getExFloat3() {
		return exFloat3;
	}

	public void setExFloat3(float exFloat3) {
		this.exFloat3 = exFloat3;
	}

	public float getExFloat4() {
		return exFloat4;
	}

	public void setExFloat4(float exFloat4) {
		this.exFloat4 = exFloat4;
	}

	public float getExFloat5() {
		return exFloat5;
	}

	public void setExFloat5(float exFloat5) {
		this.exFloat5 = exFloat5;
	}

	@Override
	public String toString() {
		return "Customer [custId=" + custId + ", custName=" + custName + ", phoneNumber=" + phoneNumber
				+ ", whatsappNo=" + whatsappNo + ", emailId=" + emailId + ", profilePic=" + profilePic + ", gender="
				+ gender + ", custDob=" + custDob + ", ageGroup=" + ageGroup + ", langId=" + langId + ", cityId="
				+ cityId + ", compId=" + compId + ", frId=" + frId + ", isBuissHead=" + isBuissHead + ", companyName="
				+ companyName + ", gstNo=" + gstNo + ", address=" + address + ", isActive=" + isActive + ", delStatus="
				+ delStatus + ", custAddPlatform=" + custAddPlatform + ", custAddDatetime=" + custAddDatetime
				+ ", addedFromType=" + addedFromType + ", userId=" + userId + ", isPremiumCust=" + isPremiumCust
				+ ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exInt3=" + exInt3 + ", exInt4=" + exInt4
				+ ", exInt5=" + exInt5 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + ", exVar3=" + exVar3
				+ ", exVar4=" + exVar4 + ", exVar5=" + exVar5 + ", exFloat1=" + exFloat1 + ", exFloat2=" + exFloat2
				+ ", exFloat3=" + exFloat3 + ", exFloat4=" + exFloat4 + ", exFloat5=" + exFloat5 + "]";
	}

}
