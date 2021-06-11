package com.ats.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.report.GetRepTaxSell;
import com.ats.webapi.model.report.GetSellTaxRepSummary;

public interface GetSellTAxRepSummaryRepo extends JpaRepository<GetSellTaxRepSummary, Integer> {	
	
	
	@Query(value="SELECT\n" + 
			"    t1.user_gst_no,\n" + 
			"    t1.bill_date,\n" + 
			"    t1.sell_bill_detail_no,\n" + 
			"    t1.sell_bill_no,\n" + 
			"    t1.tax_per,\n" + 
			"    t1.tax_amount,\n" + 
			"    t1.cgst,\n" + 
			"    t1.sgst,\n" + 
			"    t1.igst,\n" + 
			"    t1.fr_id,\n" + 
			"    t1.fr_name,\n" + 
			"    t1.bill_amount,\n" + 
			"    t2.from_bill,\n" + 
			"    t2.to_bill\n" + 
			"FROM\n" + 
			"    (\n" + 
			"    SELECT\n" + 
			"        1 AS id,\n" + 
			"        h.user_gst_no,\n" + 
			"        h.bill_date,\n" + 
			"        d.sell_bill_detail_no,\n" + 
			"        h.invoice_no AS sell_bill_no,\n" + 
			"        d.cgst_per + d.sgst_per AS tax_per,\n" + 
			"        SUM(d.taxable_amt) AS tax_amount,\n" + 
			"        SUM(d.cgst_rs) AS cgst,\n" + 
			"        SUM(d.sgst_rs) AS sgst,\n" + 
			"        SUM(d.igst_rs) AS igst,\n" + 
			"        h.fr_id,\n" + 
			"        f.fr_name,\n" + 
			"        SUM(d.grand_total) AS bill_amount\n" + 
			"    FROM\n" + 
			"        t_sell_bill_detail d,\n" + 
			"        t_sell_bill_header h,\n" + 
			"        m_franchisee f\n" + 
			"    WHERE\n" + 
			"        h.fr_id IN(:frId) AND h.sell_bill_no = d.sell_bill_no AND h.bill_date BETWEEN :fromDate AND :toDate AND f.fr_id = h.fr_id AND h.user_gst_no = '' AND h.del_status=0 \n" + 
			"    GROUP BY\n" + 
			"        (d.cgst_per + d.sgst_per),\n" + 
			"        h.fr_id\n" + 
			"    ORDER BY\n" + 
			"        d.cgst_per + d.sgst_per\n" + 
			") t1\n" + 
			"LEFT JOIN(\n" + 
			"    SELECT\n" + 
			"        MIN(hs.invoice_no) AS from_bill,\n" + 
			"        MAX(hs.invoice_no) AS to_bill\n" + 
			"    FROM\n" + 
			"        t_sell_bill_header hs\n" + 
			"    WHERE\n" + 
			"        hs.del_status = 0 AND hs.bill_date BETWEEN :fromDate AND :toDate AND hs.fr_id =:frId AND hs.user_gst_no = ''\n" + 
			") t2\n" + 
			"ON\n" + 
			"    t1.id = 1",nativeQuery=true)
	List<GetSellTaxRepSummary> getTaxSellSummaryReport(@Param("fromDate")String fromDate, @Param("toDate")String toDate, @Param("frId")List<String> frId); 

	
	
	@Query(value="SELECT\n" + 
			"    t1.gst_no AS user_gst_no,\n" + 
			"    t1.crn_date AS bill_date,\n" + 
			"    t1.crn_detail_no AS sell_bill_detail_no,\n" + 
			"    t1.sell_bill_no,\n" + 
			"    t1.tax_per,\n" + 
			"    t1.tax_amount,\n" + 
			"    t1.cgst,\n" + 
			"    t1.sgst,\n" + 
			"    t1.igst,\n" + 
			"    t1.fr_id,\n" + 
			"    t1.fr_name,\n" + 
			"    t1.bill_amount,\n" + 
			"    t2.from_bill,\n" + 
			"    t2.to_bill\n" + 
			"FROM\n" + 
			"    (\n" + 
			"    SELECT\n" + 
			"        1 AS id,\n" + 
			"        c.gst_no,\n" + 
			"        cr.crn_date,\n" + 
			"        cr.crn_detail_no,\n" + 
			"        cr.crn_invoice_no AS sell_bill_no,\n" + 
			"        cr.cgst_per + cr.sgst_per AS tax_per,\n" + 
			"        SUM(cr.taxable_amt) AS tax_amount,\n" + 
			"        SUM(cr.cgst_amt) AS cgst,\n" + 
			"        SUM(cr.sgst_amt) AS sgst,\n" + 
			"        SUM(cr.igst_amt) AS igst,\n" + 
			"        cr.ex_int1 AS fr_id,\n" + 
			"        f.fr_name,\n" + 
			"        SUM(cr.grand_total) AS bill_amount\n" + 
			"    FROM\n" + 
			"        t_credit_note_pos cr,\n" + 
			"        m_franchisee f,\n" + 
			"        m_customer c\n" + 
			"    WHERE\n" + 
			"        cr.ex_int1 IN(:frId) AND cr.cust_id = c.cust_id AND cr.crn_date BETWEEN :fromDate AND :toDate AND f.fr_id = cr.ex_int1 AND c.gst_no = '' AND cr.del_status=0 \n" + 
			"    GROUP BY\n" + 
			"        (cr.cgst_per + cr.sgst_per),\n" + 
			"        cr.ex_int1\n" + 
			"    ORDER BY\n" + 
			"        cr.cgst_per + cr.sgst_per\n" + 
			") t1\n" + 
			"LEFT JOIN(\n" + 
			"    SELECT\n" + 
			"        MIN(cr.crn_invoice_no) AS from_bill,\n" + 
			"        MAX(cr.crn_invoice_no) AS to_bill\n" + 
			"    FROM\n" + 
			"        t_credit_note_pos cr,\n" + 
			"        m_customer c\n" + 
			"    WHERE\n" + 
			"        cr.del_status = 0 AND cr.crn_date BETWEEN :fromDate AND :toDate AND cr.ex_int1 = :frId AND cr.cust_id = c.cust_id AND c.gst_no = ''\n" + 
			") t2\n" + 
			"ON\n" + 
			"    t1.id = 1",nativeQuery=true)
	List<GetSellTaxRepSummary> getCRNTaxSellSummaryReport(@Param("fromDate")String fromDate, @Param("toDate")String toDate, @Param("frId")List<String> frId); 

}
