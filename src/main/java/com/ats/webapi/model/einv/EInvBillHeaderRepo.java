package com.ats.webapi.model.einv;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface EInvBillHeaderRepo extends JpaRepository<EInvBillHeader, Integer>{
	
	@Query(value=" SELECT t_bill_header.fr_code as irn_ack_data, t_bill_header.bill_no,t_bill_header.invoice_no,t_bill_header.bill_date, " + 
			" t_bill_header.fr_id,t_bill_header.tax_applicable,t_bill_header.taxable_amt,t_bill_header.total_tax, " + 
			" t_bill_header.grand_total,t_bill_header.disc_amt,t_bill_header.round_off, " + 
			" t_bill_header.sgst_sum,t_bill_header.cgst_sum,t_bill_header.igst_sum,t_bill_header.party_name, " + 
			" t_bill_header.party_gstin,t_bill_header.party_address,t_bill_header.veh_no,t_bill_header.ex_varchar2 as cess_sum " + 
			" " + 
			" FROM t_bill_header WHERE t_bill_header.del_status=0 and t_bill_header.bill_no IN(:billIdList) ",nativeQuery=true)
	public List<EInvBillHeader>  getBillHeaderforEInv(@Param("billIdList") List<String> billIdList);

	
	@Transactional
	@Modifying
	@Query(value="UPDATE t_bill_header  SET fr_code='NA' WHERE bill_no=:billNo ", nativeQuery=true)
	int EInvCancel(@Param("billNo")int billNo);

	
	@Transactional
	@Modifying
	@Query(value="UPDATE t_bill_header  SET fr_code=:irnAckData WHERE bill_no=:billNo ", nativeQuery=true)
	int updateEInvDataInBill(@Param("billNo")int billNo ,@Param("irnAckData") String irnAckData);
	

}
