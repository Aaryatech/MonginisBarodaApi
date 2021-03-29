package com.ats.webapi.model.eway;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface EwayItemListRepo extends JpaRepository<EwayItemList, Integer> {

	@Query(value = " SELECT t_bill_detail.bill_detail_no,t_bill_detail.bill_qty as quantity,"
			+ "t_bill_detail.cgst_per as cgst_rate, " + "t_bill_detail.sgst_per as sgst_rate, "
			+ "t_bill_detail.igst_per as igst_rate, " + "t_bill_detail.cess_per as cess_rate, "
			+ "t_bill_detail.cess_rs as cess_non_advol, " + "t_bill_detail.taxable_amt as taxable_amount, "
		//	+ "m_item.item_name as product_name, " + "m_item.item_name as product_desc, "
			+ "t_bill_detail.hsn_code as hsn_code, " //+ "m_item_sup.item_uom as qty_unit, "
			+ ""
			+ " CASE  WHEN t_bill_detail.cat_id=5 THEN (SELECT m_sp_cake.sp_name  FROM  m_sp_cake WHERE m_sp_cake.sp_id= t_bill_detail.item_id) \"\n" + 
			"  ELSE (SELECT  m_item.item_name FROM m_item WHERE t_bill_detail.item_id=m_item.id)END AS product_name ,"
			+ ""
			+ "CASE  WHEN t_bill_detail.cat_id=5 THEN (SELECT m_sp_cake.sp_name  FROM  m_sp_cake WHERE m_sp_cake.sp_id= t_bill_detail.item_id) \\\"\\n\" + \n" + 
			"  ELSE (SELECT  m_item.item_name FROM m_item WHERE t_bill_detail.item_id=m_item.id)END AS product_desc , \" + \n" + 
			"			\""
			+ " CASE WHEN t_bill_detail.cat_id=5 THEN (SELECT m_spcake_sup.sp_uom  FROM m_spcake_sup  \" + \n" + 
			"			\" WHERE  m_spcake_sup.sp_id=t_bill_detail.item_id)  \" + \n" + 
			"			\" ELSE (SELECT m_item_sup.item_uom FROM m_item_sup   \" + \n" + 
			"			\" WHERE  m_item_sup.item_id=t_bill_detail.item_id)   \" + \n" + 
			"			\" END AS qty_unit"
			+ ""
			+ "	FROM m_item_sup,m_item,t_bill_detail "
			+ "WHERE m_item_sup.item_id=m_item.id AND m_item.id=t_bill_detail.item_id and t_bill_detail.bill_qty>0 "
			+ "AND t_bill_detail.bill_no=:billNo", nativeQuery = true)
	public List<EwayItemList> getBillDetailForEwayBill(@Param("billNo") int billNo);

	@Query(value = " SELECT t_credit_note_details.crnd_id as bill_detail_no,t_credit_note_details.grn_gvn_qty "
			+ "as quantity, t_credit_note_details.cgst_per as cgst_rate, t_credit_note_details.sgst_per as sgst_rate,"
			+ " t_credit_note_details.igst_per as igst_rate, t_credit_note_details.cess_per as cess_rate, "
			+ "t_credit_note_details.cess_rs as cess_non_advol, t_credit_note_details.taxable_amt as "
			+ "taxable_amount, m_item.item_name as product_name, m_item.item_name as product_desc, "
			+ "m_item_sup.item_hsncd as hsn_code, m_item_sup.item_uom as qty_unit "
			+ "FROM m_item_sup,m_item, t_credit_note_details "
			+ "WHERE m_item_sup.item_id=m_item.id AND m_item.id=t_credit_note_details.item_id AND t_credit_note_details.grn_gvn_qty>0 and "
			+ "t_credit_note_details.crn_id=:crnId", nativeQuery = true)
	public List<EwayItemList> getCreditNoteDetailForEwayBill(@Param("crnId") int crnId);
	
	
	//Anmol
	@Query(value = " SELECT g.grn_gvn_id as bill_detail_no, i.item_name as product_name,i.item_name as product_desc,s.item_hsncd as hsn_code,g.grn_gvn_qty as quantity, s.item_uom as qty_unit, g.cgst_per cgst_rate,g.sgst_per as sgst_rate, 0 as igst_rate,0 as cess_rate, 0 as cess_non_advol,g.taxable_amt as taxable_amount \n" + 
			"FROM t_grn_gvn g , m_item i, m_item_sup s WHERE g.item_id=i.id AND i.id=s.item_id AND g.item_id=s.item_id AND g.grn_gvn_header_id = :billNo", nativeQuery = true)
	public List<EwayItemList> getGrnGvnDetailForEwayBill(@Param("billNo") int billNo);

}
