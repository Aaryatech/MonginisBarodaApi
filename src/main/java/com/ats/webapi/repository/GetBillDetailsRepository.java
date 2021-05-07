package com.ats.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.GetBillDetails;

public interface GetBillDetailsRepository extends JpaRepository<GetBillDetails, Integer> {
	
	@Query(value="SELECT\n" + 
			"        t_bill_detail.* ,\n" + 
			"       CASE \n" + 
			"            WHEN t_bill_detail.cat_id=5 THEN (select m_sp_cake.sp_name FROM m_sp_cake \n" + 
			"WHERE t_bill_detail.item_id=m_sp_cake.sp_id)\n" + 
			"            ELSE (SELECT  m_item.item_name FROM m_item WHERE t_bill_detail.item_id=m_item.id )\n" + 
			"        END AS item_name,\n" + 
			"        m_category.cat_name,\n" + 
			"        t_bill_header.bill_date  \n" + 
			"    FROM\n" + 
			"        t_bill_detail,\n" + 
			"        m_category,\n" + 
			"        t_bill_header\n" + 
			"\n" + 
			"    WHERE\n" + 
			"        t_bill_detail.bill_no=:billNo"+
			"        AND m_category.cat_id=t_bill_detail.cat_id \n" + 
			"        AND t_bill_detail.bill_no=t_bill_header.bill_no",nativeQuery=true)
	
	List<GetBillDetails> showBillDetails(@Param("billNo") int billNo);
	
	
	@Query(value="SELECT t_bill_detail.* ,m_item.item_name,m_category.cat_name,t_bill_header.bill_date "
			+ " FROM t_bill_detail,m_item,m_category,t_bill_header WHERE t_bill_detail.bill_no IN (:billNoList)  "
			+ "AND t_bill_detail.item_id=m_item.id AND m_category.cat_id=t_bill_detail.cat_id "
			+ "AND t_bill_detail.bill_no=t_bill_header.bill_no",nativeQuery=true)
	
	List<GetBillDetails> getBillDetailsForPrint(@Param("billNoList") List<String> billNoList);
	
	
	@Query(value="SELECT t_bill_detail.hsn_code, t_bill_detail.bill_detail_no,t_bill_detail.bill_no,t_bill_detail.order_id,t_bill_detail.menu_id,t_bill_detail.cat_id,t_bill_detail.item_id,\n" + 
			"t_bill_detail.order_qty,t_bill_detail.bill_qty,t_bill_detail.mrp,t_bill_detail.rate_type,t_bill_detail.rate,t_bill_detail.base_rate,t_bill_detail.taxable_amt,t_bill_detail.sgst_per,t_bill_detail.sgst_rs, t_bill_detail.cgst_per,t_bill_detail.cgst_rs,t_bill_detail.igst_per,t_bill_detail.igst_rs,t_bill_detail.total_tax,t_bill_detail.grand_total,t_bill_detail.del_status,t_bill_detail.grn_type,t_bill_detail.expiry_date,t_bill_detail.is_grngvn_applied,t_bill_detail.disc_per,t_bill_detail.cess_per,\n" + 
			"  t_bill_detail.cess_rs ,t_bill_header.invoice_no as remark,m_item.item_name,m_category.cat_name,t_bill_header.bill_date FROM t_bill_detail,m_item,m_category,t_bill_header WHERE t_bill_detail.cat_id IN (1,2,4) and t_bill_header.fr_id=:frId and t_bill_detail.expiry_date=:expiryDate and  t_bill_detail.item_id=m_item.id AND m_category.cat_id=t_bill_detail.cat_id AND t_bill_detail.bill_no=t_bill_header.bill_no",nativeQuery=true)
	List<GetBillDetails> getGrnItemsByExpiryDate(@Param("frId")int frId,@Param("expiryDate")String expiryDate);

	@Query(value="SELECT t_bill_detail.hsn_code,  t_bill_detail.bill_detail_no,t_bill_detail.bill_no,t_bill_detail.order_id,t_bill_detail.menu_id,t_bill_detail.cat_id,t_bill_detail.item_id,\n" + 
			"t_bill_detail.order_qty,t_bill_detail.bill_qty,t_bill_detail.mrp,t_bill_detail.rate_type,t_bill_detail.rate,t_bill_detail.base_rate,t_bill_detail.taxable_amt,t_bill_detail.sgst_per,t_bill_detail.sgst_rs, t_bill_detail.cgst_per,t_bill_detail.cgst_rs,t_bill_detail.igst_per,t_bill_detail.igst_rs,t_bill_detail.total_tax,t_bill_detail.grand_total,t_bill_detail.del_status,t_bill_detail.grn_type,t_bill_detail.expiry_date,t_bill_detail.is_grngvn_applied,t_bill_detail.disc_per,t_bill_detail.cess_per,\n" + 
			"  t_bill_detail.cess_rs ,t_bill_header.invoice_no as remark,m_item.item_name,m_category.cat_name,t_bill_header.bill_date FROM t_bill_detail,m_item,m_category,t_bill_header WHERE t_bill_detail.cat_id IN (1,2,4) and t_bill_header.fr_id=:frId and t_bill_detail.expiry_date>CURDATE()   and t_bill_detail.item_id in (:idList) and  t_bill_detail.item_id=m_item.id AND m_category.cat_id=t_bill_detail.cat_id AND t_bill_detail.bill_no=t_bill_header.bill_no order by t_bill_header.bill_no",nativeQuery=true)
	List<GetBillDetails> getGrnItemsByIds(@Param("frId")int frId,@Param("idList")List<Integer> idList);

	
	//SAC 06-05-2021
	@Query(value="SELECT t_bill_detail.hsn_code,  t_bill_detail.bill_detail_no,t_bill_detail.bill_no,t_bill_detail.order_id,t_bill_detail.menu_id,t_bill_detail.cat_id,t_bill_detail.item_id,\n" + 
			"t_bill_detail.order_qty,t_bill_detail.bill_qty,t_bill_detail.mrp,t_bill_detail.rate_type,t_bill_detail.rate,t_bill_detail.base_rate,t_bill_detail.taxable_amt,t_bill_detail.sgst_per,t_bill_detail.sgst_rs, t_bill_detail.cgst_per,t_bill_detail.cgst_rs,t_bill_detail.igst_per,t_bill_detail.igst_rs,t_bill_detail.total_tax,t_bill_detail.grand_total,t_bill_detail.del_status,t_bill_detail.grn_type,t_bill_detail.expiry_date,t_bill_detail.is_grngvn_applied,t_bill_detail.disc_per,t_bill_detail.cess_per,\n" + 
			"  t_bill_detail.cess_rs ,t_bill_header.invoice_no as remark,m_item.item_name,m_category.cat_name,t_bill_header.bill_date FROM t_bill_detail,m_item,m_category,t_bill_header WHERE   t_bill_header.fr_id=:frId and t_bill_detail.expiry_date=:expiryDate   and t_bill_detail.item_id in (:idList) and  t_bill_detail.item_id=m_item.id AND m_category.cat_id=t_bill_detail.cat_id AND t_bill_detail.bill_no=t_bill_header.bill_no order by t_bill_header.bill_no",nativeQuery=true)
	List<GetBillDetails> getGrnItemsByExpDateAndItems(@Param("frId")int frId, 
			@Param("expiryDate")String expiryDate,@Param("idList")List<Integer> idList);
	
	@Query(value="SELECT t_bill_detail.hsn_code,  t_bill_detail.bill_detail_no,t_bill_detail.bill_no,t_bill_detail.order_id,t_bill_detail.menu_id,t_bill_detail.cat_id,t_bill_detail.item_id,\n" + 
			"t_bill_detail.order_qty,t_bill_detail.bill_qty,t_bill_detail.mrp,t_bill_detail.rate_type,t_bill_detail.rate,t_bill_detail.base_rate,t_bill_detail.taxable_amt,t_bill_detail.sgst_per,t_bill_detail.sgst_rs, t_bill_detail.cgst_per,t_bill_detail.cgst_rs,t_bill_detail.igst_per,t_bill_detail.igst_rs,t_bill_detail.total_tax,t_bill_detail.grand_total,t_bill_detail.del_status,t_bill_detail.grn_type,t_bill_detail.expiry_date,t_bill_detail.is_grngvn_applied,t_bill_detail.disc_per,t_bill_detail.cess_per,\n" + 
			"  t_bill_detail.cess_rs ,t_bill_header.invoice_no as remark,m_item.item_name,m_category.cat_name,t_bill_header.bill_date FROM t_bill_detail,m_item,m_category,t_bill_header WHERE   "
			+ "t_bill_header.fr_id=:frId and t_bill_detail.expiry_date=:expiryDate and t_bill_detail.cat_id=:catId  and   t_bill_detail.item_id=m_item.id AND m_category.cat_id=t_bill_detail.cat_id AND t_bill_detail.bill_no=t_bill_header.bill_no order by t_bill_header.bill_no",nativeQuery=true)
	List<GetBillDetails> getGrnItemsByExpDateAndCatId(@Param("frId")int frId, 
			@Param("expiryDate")String expiryDate,@Param("catId")int  catId);
	
	
	
}
