package com.ats.webapi.model.eway;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface BillHeadEwayBillRepo extends JpaRepository<BillHeadEwayBill, Integer>{
	
	
	@Query(value="SELECT t_bill_header.bill_no,t_bill_header.invoice_no,t_bill_header.bill_date, t_bill_header.fr_id,t_bill_header.fr_code,t_bill_header.taxable_amt, t_bill_header.grand_total, \n" + 
			"CASE WHEN m_franchisee.is_same_state=1 THEN t_bill_header.sgst_sum ELSE 0 END as sgst_sum,\n" + 
			"CASE WHEN m_franchisee.is_same_state=1 THEN t_bill_header.cgst_sum ELSE 0 END as cgst_sum,\n" + 
			"CASE WHEN m_franchisee.is_same_state=0 THEN t_bill_header.igst_sum ELSE 0 END as igst_sum,"
			+ ""
			+ "          COALESCE(t_bill_header.party_name,'') as party_name,\n" + 
			"COALESCE(t_bill_header.party_gstin,'') as party_gstin,\n" + 
			"COALESCE(t_bill_header.party_address,'') as party_address,\n" + 
			"COALESCE(t_bill_header.ex_varchar3,'') as ex_varchar3,\n" + 
			"COALESCE(t_bill_header.ex_varchar4,'') as ex_varchar4,\n" + 
			"COALESCE(t_bill_header.ex_varchar5,'') as ex_varchar5, " + 
			" t_bill_header.is_tally_sync as ewb_no " +
			"FROM t_bill_header,m_franchisee WHERE t_bill_header.fr_id=m_franchisee.fr_id and t_bill_header.bill_no IN(:billIdList)",nativeQuery=true)
	public List<BillHeadEwayBill>  getBillHeaderForEwayBill(@Param("billIdList") List<String> billIdList);

	 
	@Query(value="	SELECT t_credit_note_header.crn_id as bill_no,t_credit_note_header.crn_taxable_amt as taxable_amt ,t_credit_note_header.crn_grand_total as grand_total,\n" + 
			"	t_credit_note_header.crn_no as invoice_no,\n" + 
			"	t_credit_note_header.crn_date as bill_date,\n" + 
			"\n" + 
			"	CASE WHEN m_franchisee.is_same_state=1 THEN t_credit_note_header.crn_total_tax/2 ELSE 0 END as sgst_sum,\n" + 
			"	CASE WHEN m_franchisee.is_same_state=1 THEN t_credit_note_header.crn_total_tax/2 ELSE 0 END as cgst_sum,\n" + 
			"	CASE WHEN m_franchisee.is_same_state=0 THEN t_credit_note_header.crn_total_tax ELSE 0 END as igst_sum,\n" + 
			"\n" + 
			"	m_franchisee.fr_code,m_franchisee.fr_id\n" + 
			"	FROM m_franchisee,t_credit_note_header\n" + 
			"	WHERE t_credit_note_header.fr_id=m_franchisee.fr_id AND t_credit_note_header.crn_id IN (:crnIdList)",nativeQuery=true)
	public List<BillHeadEwayBill>  getCreditNoteHeaderForEwayBill(@Param("crnIdList") List<String> crnIdList);
	
	
	
	//Anmol
	@Query(value="SELECT h.grn_gvn_header_id as bill_no,h.grngvn_srno as invoice_no,h.grngvn_date as bill_date,h.fr_id,f.fr_code as fr_code,h.taxable_amt,h.total_amt as grand_total,\n" + 
			"CASE WHEN f.is_same_state=1 THEN h.apr_sgst_rs ELSE 0 END as sgst_sum,\n" + 
			"CASE WHEN f.is_same_state=1 THEN h.apr_cgst_rs ELSE 0 END as cgst_sum,\n" + 
			"CASE WHEN f.is_same_state=0 THEN h.apr_igst_rs ELSE 0 END as igst_sum,\n" + 
			"e.to_cust as party_name,e.to_gst as party_gstin,\n" + 
			"e.to_add as party_address,(SELECT c.comp_name FROM m_company c ORDER BY c.comp_id DESC LIMIT 1) as ex_varchar3,(SELECT c.gstin FROM m_company c ORDER BY c.comp_id DESC LIMIT 1) as ex_varchar4,(SELECT c.fact_address FROM m_company c ORDER BY c.comp_id DESC LIMIT 1) as ex_varchar5, e.eway_no as ewb_no\n" + 
			"FROM t_grn_gvn_header h, m_franchisee f,t_grn_gvn_eway e WHERE h.fr_id=f.fr_id AND h.grn_gvn_header_id=e.grn_gvn_header_id\n" + 
			"AND h.grn_gvn_header_id IN(:billIdList)",nativeQuery=true)
	public List<BillHeadEwayBill>  getGrnGvnHeaderForEwayBill(@Param("billIdList") List<String> billIdList);

	
	
	/*
	 * @Query(value=" SELECT " +
	 * "	  t_bill_header.bill_no,t_bill_header.invoice_no,t_bill_header.bill_date, "
	 * + "	 t_bill_header.fr_id,t_bill_header.fr_code,t_bill_header.taxable_amt, "
	 * + "	  t_bill_header.grand_total, " +
	 * "	  t_bill_header.sgst_sum,t_bill_header.cgst_sum,t_bill_header.igst_sum FROM"
	 * + "	  t_bill_header WHERE t_bill_header.bill_no IN(:billIdList)",nativeQuery
	 * =true) public List<BillHeadEwayBill>
	 * getBillHeaderForEwayBill(@Param("billIdList") List<String> billIdList);
	 */
	/*
	 SELECT t_bill_header.bill_no,t_bill_header.invoice_no,t_bill_header.bill_date, t_bill_header.fr_id,t_bill_header.fr_code,t_bill_header.taxable_amt, t_bill_header.grand_total, 

CASE WHEN m_franchisee.is_same_state=1 THEN t_bill_header.sgst_sum ELSE 0 END as sgst_sum,
CASE WHEN m_franchisee.is_same_state=1 THEN t_bill_header.cgst_sum ELSE 0 END as cgst_sum,
CASE WHEN m_franchisee.is_same_state=0 THEN t_bill_header.igst_sum ELSE 0 END as igst_sum


FROM t_bill_header,m_franchisee WHERE t_bill_header.fr_id=m_franchisee.fr_id
	 */
}
