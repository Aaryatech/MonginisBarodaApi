package com.ats.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ats.webapi.model.CrnHsnwiseExcelReport;
@Repository
public interface CrnHsnwiseExcelReportRepository extends JpaRepository<CrnHsnwiseExcelReport, Integer>{

	//@Query(value="select UUID() as id,t_credit_note_header.crn_id, t_credit_note_header.ex_varchar1 as supplier_invoice_no,t_credit_note_details.bill_date as supplier_invoice_date,t_credit_note_header.crn_no as invoice_no,t_credit_note_header.crn_date as invoice_date,t_credit_note_header.fr_id,m_franchisee.fr_name,m_item_sup.item_hsncd,SUM(t_credit_note_details.grn_gvn_qty) as qty,round(SUM(t_credit_note_details.taxable_amt),2) as taxable_amt,round(SUM(t_credit_note_details.cgst_rs),2) as cgst_rs,round(SUM(t_credit_note_details.sgst_rs),2) as sgst_rs,0 as igst_rs,round((t_credit_note_details.cgst_per+t_credit_note_details.sgst_per),2) as tax_rate,((select (SUM(d.taxable_amt)+SUM(d.cgst_rs)+SUM(d.sgst_rs)) from t_credit_note_details d where d.crn_id=t_credit_note_header.crn_id)) as document_amount,m_franchisee.fr_gst_no,'INDIA' as country,'MAHARASHTRA' as state from t_credit_note_header,t_credit_note_details,m_item_sup,m_franchisee  where m_item_sup.item_id=t_credit_note_details.item_id and t_credit_note_header.crn_id=t_credit_note_details.crn_id and t_credit_note_header.crn_id IN(:crnIdList) And m_franchisee.fr_id=t_credit_note_header.fr_id group by t_credit_note_header.crn_id,item_hsncd",nativeQuery=true)
	//@Query(value="select UUID() as id,t_credit_note_header.crn_id, t_credit_note_header.ex_varchar1 as supplier_invoice_no,t_credit_note_details.bill_date as supplier_invoice_date,t_credit_note_header.crn_no as invoice_no,t_credit_note_header.crn_date as invoice_date,t_credit_note_header.fr_id,m_franchisee.fr_name,m_item_sup.item_hsncd,SUM(t_credit_note_details.grn_gvn_qty) as qty,round(SUM(t_credit_note_details.taxable_amt),2) as taxable_amt,round(SUM(t_credit_note_details.cgst_rs),2) as cgst_rs,round(SUM(t_credit_note_details.sgst_rs),2) as sgst_rs,0 as igst_rs,round((t_credit_note_details.cgst_per+t_credit_note_details.sgst_per),2) as tax_rate,((select (SUM(d.taxable_amt)+SUM(d.cgst_rs)+SUM(d.sgst_rs)) from t_credit_note_details d where d.crn_id=t_credit_note_header.crn_id)) as document_amount,m_franchisee.fr_gst_no,'INDIA' as country,'MAHARASHTRA' as state from t_credit_note_header,t_credit_note_details,m_item_sup,m_franchisee  where m_item_sup.item_id=t_credit_note_details.item_id and t_credit_note_header.crn_id=t_credit_note_details.crn_id and t_credit_note_header.crn_id IN(:crnIdList) And m_franchisee.fr_id=t_credit_note_header.fr_id group by t_credit_note_header.crn_id,item_hsncd\n" + 
	//		"UNION ALL\n" + 
	//		"select UUID() as id,t_credit_note_header.crn_id, t_credit_note_header.ex_varchar1 as supplier_invoice_no,t_credit_note_details.bill_date as supplier_invoice_date,t_credit_note_header.crn_no as invoice_no,t_credit_note_header.crn_date as invoice_date,t_credit_note_header.fr_id,m_franchisee.fr_name,m_spcake_sup.sp_hsncd as item_hsncd,SUM(t_credit_note_details.grn_gvn_qty) as qty,round(SUM(t_credit_note_details.taxable_amt),2) as taxable_amt,round(SUM(t_credit_note_details.cgst_rs),2) as cgst_rs,round(SUM(t_credit_note_details.sgst_rs),2) as sgst_rs,0 as igst_rs,round((t_credit_note_details.cgst_per+t_credit_note_details.sgst_per),2) as tax_rate,((select (SUM(d.taxable_amt)+SUM(d.cgst_rs)+SUM(d.sgst_rs)) from t_credit_note_details d where d.crn_id=t_credit_note_header.crn_id)) as document_amount,m_franchisee.fr_gst_no,'INDIA' as country,'MAHARASHTRA' as state from t_credit_note_header,t_credit_note_details,m_spcake_sup,m_franchisee  where m_spcake_sup.sp_id=t_credit_note_details.item_id and t_credit_note_header.crn_id=t_credit_note_details.crn_id and t_credit_note_header.crn_id IN(:crnIdList) And m_franchisee.fr_id=t_credit_note_header.fr_id group by t_credit_note_header.crn_id,item_hsncd",nativeQuery=true)
	
	@Query(value="select UUID() as id,t_credit_note_header.crn_id, t_credit_note_header.ex_varchar1 as supplier_invoice_no,t_credit_note_details.bill_date as supplier_invoice_date,t_credit_note_header.crn_no as invoice_no,t_credit_note_header.crn_date as invoice_date,t_credit_note_header.fr_id,m_franchisee.fr_name,t_credit_note_details.hsn_code as item_hsncd,SUM(t_credit_note_details.grn_gvn_qty) as qty,round(SUM(t_credit_note_details.taxable_amt),2) as taxable_amt,round(SUM(t_credit_note_details.cgst_rs),2) as cgst_rs,round(SUM(t_credit_note_details.sgst_rs),2) as sgst_rs,round(SUM(t_credit_note_details.cess_rs),2) as cess_rs,0 as igst_rs,round((t_credit_note_details.cgst_per+t_credit_note_details.sgst_per+t_credit_note_details.cess_per),2) as tax_rate,((select (SUM(d.taxable_amt)+SUM(d.cgst_rs)+SUM(d.sgst_rs)+SUM(d.cess_rs)) from t_credit_note_details d where d.crn_id=t_credit_note_header.crn_id)) as document_amount,m_franchisee.fr_gst_no,'INDIA' as country,'MAHARASHTRA' as state from t_credit_note_header,t_credit_note_details,m_franchisee  where  t_credit_note_header.crn_id=t_credit_note_details.crn_id and t_credit_note_header.crn_id IN(:crnIdList) And m_franchisee.fr_id=t_credit_note_header.fr_id group by t_credit_note_header.crn_id,item_hsncd",nativeQuery=true)
	List<CrnHsnwiseExcelReport> getCrnHsnwiseExcelReport(@Param("crnIdList")List<String> crnIdList);

}
