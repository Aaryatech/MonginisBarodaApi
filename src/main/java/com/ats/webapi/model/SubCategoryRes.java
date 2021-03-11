package com.ats.webapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="m_cat_sub")
public class SubCategoryRes {

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
		@Override
		public String toString() {
			return "SubCategoryRes [subCatId=" + subCatId + ", subCatName=" + subCatName + ", catId=" + catId
					+ ", delStatus=" + delStatus + ", prefix=" + prefix + ", seqNo=" + seqNo + "]";
		}
	    
	    
}
