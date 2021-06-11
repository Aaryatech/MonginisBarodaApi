package com.ats.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.report.CRNSaleTaxBillReport;

public interface CRNSaleTaxBillReportRepo extends JpaRepository<CRNSaleTaxBillReport, Integer>{
	
	
	@Query(value=" SELECT\r\n" + 
			"	uuid() as id,\r\n" + 
			"    c.gst_no as user_gst_no,\r\n" + 
			"    h.bill_date,\r\n" + 
			"    cr.crn_no,\r\n" + 
			"    cr.crn_invoice_no,\r\n" + 
			"    cr.crn_date,\r\n" + 
			"    cr.cgst_per+cr.sgst_per AS tax_per,\r\n" + 
			"    SUM(cr.taxable_amt) AS tax_amount,\r\n" + 
			"    SUM(cr.cgst_amt) AS cgst,\r\n" + 
			"    SUM(cr.sgst_amt) AS sgst,\r\n" + 
			"    SUM(cr.igst_amt) AS igst,\r\n" + 
			"    cr.ex_int1 as fr_id,\r\n" + 
			"    f.fr_name,\r\n" + 
			"    SUM(cr.grand_total) AS bill_amount\r\n" + 
			"FROM\r\n" + 
			"    t_sell_bill_header h,\r\n" + 
			"    t_credit_note_pos cr,\r\n" + 
			"    m_customer c,\r\n" + 
			"    m_franchisee f\r\n" + 
			"WHERE\r\n" + 
			"    cr.ex_int1 IN(:frId) AND h.sell_bill_no = cr.bill_no AND cr.crn_date BETWEEN :fromDate AND :toDate AND f.fr_id = cr.ex_int1 AND  cr.cust_id=c.cust_id AND  c.gst_no != '' AND h.del_status=0 AND cr.del_status=0 \r\n" + 
			"GROUP BY\r\n" + 
			"    (cr.cgst_per+cr.sgst_per)\r\n" + 
			""
			+ "",nativeQuery=true)
	List<CRNSaleTaxBillReport> getRepFrCrnwiseTaxSell(@Param("fromDate") String fromDate,@Param("toDate") String toDate, @Param("frId") List<String> frId);


}
