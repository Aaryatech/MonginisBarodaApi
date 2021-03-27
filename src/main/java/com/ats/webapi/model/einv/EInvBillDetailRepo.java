package com.ats.webapi.model.einv;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EInvBillDetailRepo extends JpaRepository<EInvBillDetail, Integer> {
	
	@Query(value = " SELECT t_bill_detail.bill_detail_no,t_bill_detail.bill_no, " + 
			" t_bill_detail.order_id,t_bill_detail.item_id,t_bill_detail.bill_qty,t_bill_detail.order_qty, " + 
			" t_bill_detail.mrp,t_bill_detail.rate,t_bill_detail.base_rate,t_bill_detail.taxable_amt,t_bill_detail.sgst_rs, " + 
			" t_bill_detail.cgst_rs,t_bill_detail.igst_rs,t_bill_detail.cess_rs,t_bill_detail.hsn_code, " + 
			" t_bill_detail.total_tax,t_bill_detail.grand_total,t_bill_detail.expiry_date,t_bill_detail.disc_per, " + 
			" CASE  WHEN t_bill_detail.cat_id=5 THEN (SELECT m_sp_cake.sp_name  FROM  m_sp_cake WHERE m_sp_cake.sp_id= t_bill_detail.item_id) "
			+ " ELSE (SELECT  m_item.item_name FROM m_item WHERE t_bill_detail.item_id=m_item.id)END AS item_name , " + 
			" CASE WHEN t_bill_detail.cat_id=5 THEN (SELECT m_spcake_sup.sp_uom  FROM m_spcake_sup  " + 
			" WHERE  m_spcake_sup.sp_id=t_bill_detail.item_id)  " + 
			" ELSE (SELECT m_item_sup.item_uom FROM m_item_sup   " + 
			" WHERE  m_item_sup.item_id=t_bill_detail.item_id)   " + 
			" END AS item_uom from t_bill_detail  " + 
			" WHERE bill_no=:billNo ", nativeQuery = true)
	List<EInvBillDetail> getEInvBillDetail(@Param("billNo") int billNo);
	

}
