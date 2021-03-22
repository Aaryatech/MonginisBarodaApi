package com.ats.webapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_album_enquiry")
public class AlbumEnquiry {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int enquiryNo;
	
	private int frId;
	
	private String custName;
	
	private String mobNo;
	
	private String photo;
	
	private String enqDt;
	
	private String enqDtTime;
	
	private String approveDtTime;
	
	private int approveUserId;
	
	private String approveUserName;
	
	private int albumId;
	
	private int status;
	
	private int weight;
	
	private int minWeight;
	
	private int flavourId;
	
	private int shapeId;
	
	private int selectedSpId;
	
	private String message;
	
	private String instruction;
	
	private int rsPerKg;
	
private int addToProd;
	
	private int rejectId;
	
	private String rejectRemark;
	
	private int exInt1,exInt2,exInt3;
	
	private String exVar1,exVar2,exVar3;
	
	
	private int delStatus;



	public int getRsPerKg() {
		return rsPerKg;
	}


	public void setRsPerKg(int rsPerKg) {
		this.rsPerKg = rsPerKg;
	}


	

	public int getEnquiryNo() {
		return enquiryNo;
	}


	public void setEnquiryNo(int enquiryNo) {
		this.enquiryNo = enquiryNo;
	}


	public int getFrId() {
		return frId;
	}


	public void setFrId(int frId) {
		this.frId = frId;
	}


	public String getCustName() {
		return custName;
	}


	public void setCustName(String custName) {
		this.custName = custName;
	}


	public String getMobNo() {
		return mobNo;
	}


	public void setMobNo(String mobNo) {
		this.mobNo = mobNo;
	}


	public String getPhoto() {
		return photo;
	}


	public void setPhoto(String photo) {
		this.photo = photo;
	}


	public String getEnqDt() {
		return enqDt;
	}


	public void setEnqDt(String enqDt) {
		this.enqDt = enqDt;
	}


	public String getEnqDtTime() {
		return enqDtTime;
	}


	public void setEnqDtTime(String enqDtTime) {
		this.enqDtTime = enqDtTime;
	}


	public String getApproveDtTime() {
		return approveDtTime;
	}


	public void setApproveDtTime(String approveDtTime) {
		this.approveDtTime = approveDtTime;
	}


	public int getApproveUserId() {
		return approveUserId;
	}


	public void setApproveUserId(int approveUserId) {
		this.approveUserId = approveUserId;
	}


	public String getApproveUserName() {
		return approveUserName;
	}


	public void setApproveUserName(String approveUserName) {
		this.approveUserName = approveUserName;
	}


	public int getAlbumId() {
		return albumId;
	}


	public void setAlbumId(int albumId) {
		this.albumId = albumId;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}









	public int getFlavourId() {
		return flavourId;
	}


	public void setFlavourId(int flavourId) {
		this.flavourId = flavourId;
	}


	public int getShapeId() {
		return shapeId;
	}


	public void setShapeId(int shapeId) {
		this.shapeId = shapeId;
	}


	public int getSelectedSpId() {
		return selectedSpId;
	}


	public void setSelectedSpId(int selectedSpId) {
		this.selectedSpId = selectedSpId;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public String getInstruction() {
		return instruction;
	}


	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}


	


	public int getAddToProd() {
		return addToProd;
	}


	public void setAddToProd(int addToProd) {
		this.addToProd = addToProd;
	}


	public int getRejectId() {
		return rejectId;
	}


	public void setRejectId(int rejectId) {
		this.rejectId = rejectId;
	}


	public String getRejectRemark() {
		return rejectRemark;
	}


	public void setRejectRemark(String rejectRemark) {
		this.rejectRemark = rejectRemark;
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


	public int getDelStatus() {
		return delStatus;
	}


	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}




	
	
	
	
	
	


	public int getWeight() {
		return weight;
	}


	public void setWeight(int weight) {
		this.weight = weight;
	}


	public int getMinWeight() {
		return minWeight;
	}


	public void setMinWeight(int minWeight) {
		this.minWeight = minWeight;
	}


	@Override
	public String toString() {
		return "AlbumEnquiry [enquiryNo=" + enquiryNo + ", frId=" + frId + ", custName=" + custName + ", mobNo=" + mobNo
				+ ", photo=" + photo + ", enqDt=" + enqDt + ", enqDtTime=" + enqDtTime + ", approveDtTime="
				+ approveDtTime + ", approveUserId=" + approveUserId + ", approveUserName=" + approveUserName
				+ ", albumId=" + albumId + ", status=" + status + ", weight=" + weight + ", minWeight=" + minWeight
				+ ", flavourId=" + flavourId + ", shapeId=" + shapeId + ", selectedSpId=" + selectedSpId + ", message="
				+ message + ", instruction=" + instruction + ", rsPerKg=" + rsPerKg + ", addToProd=" + addToProd
				+ ", rejectId=" + rejectId + ", rejectRemark=" + rejectRemark + ", exInt1=" + exInt1 + ", exInt2="
				+ exInt2 + ", exInt3=" + exInt3 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + ", exVar3=" + exVar3
				+ ", delStatus=" + delStatus + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
