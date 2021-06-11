package com.ats.webapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="m_cat_sub")
public class SubCategoryResNew {

    	@Id
    	@Column(name="sub_cat_id")
	    private int subCatId;
    	
    	@Column(name="sub_cat_name")
	    private String subCatName;

    	@Column(name="cat_id")
	    private int catId;
    	
    	@Column(name="del_status")
	    private int delStatus;
    	
    	@Column(name="prefix")
	    private String prefix;
    	
    	@Column(name="seq_no")
	    private int seqNo;
    	
    	private int exInt1,exInt2;
    	
    	private String exVar1,exVar2;
    	
    	private float exFloat1,exFloat2;
    	
    	
    	
		public int getSubCatId() {
			return subCatId;
		}
		public void setSubCatId(int subCatId) {
			this.subCatId = subCatId;
		}
		public String getSubCatName() {
			return subCatName;
		}
		public void setSubCatName(String subCatName) {
			this.subCatName = subCatName;
		}
		public int getCatId() {
			return catId;
		}
		public void setCatId(int catId) {
			this.catId = catId;
		}
		public int getDelStatus() {
			return delStatus;
		}
		public void setDelStatus(int delStatus) {
			this.delStatus = delStatus;
		}
		public String getPrefix() {
			return prefix;
		}
		public void setPrefix(String prefix) {
			this.prefix = prefix;
		}
		public int getSeqNo() {
			return seqNo;
		}
		public void setSeqNo(int seqNo) {
			this.seqNo = seqNo;
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
		@Override
		public String toString() {
			return "SubCategoryResNew [subCatId=" + subCatId + ", subCatName=" + subCatName + ", catId=" + catId
					+ ", delStatus=" + delStatus + ", prefix=" + prefix + ", seqNo=" + seqNo + ", exInt1=" + exInt1
					+ ", exInt2=" + exInt2 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + ", exFloat1=" + exFloat1
					+ ", exFloat2=" + exFloat2 + "]";
		}
		
		
		
		
		
		
		
		
	    
	    
}
