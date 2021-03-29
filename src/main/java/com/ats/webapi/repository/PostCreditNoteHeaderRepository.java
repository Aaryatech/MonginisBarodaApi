package com.ats.webapi.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ats.webapi.model.grngvn.PostCreditNoteHeader;

public interface PostCreditNoteHeaderRepository extends JpaRepository<PostCreditNoteHeader, Integer> {
	
	PostCreditNoteHeader save(PostCreditNoteHeader postCreditNoteHeader);
	
	@Query(value="SELECT SUM(t_credit_note_header.crn_grand_total) FROM t_credit_note_header WHERE t_credit_note_header.crn_date BETWEEN :fromDate AND :toDate and t_credit_note_header.del_status=0 GROUP by t_credit_note_header.crn_date",nativeQuery=true)
    int getTotalCrnBetFdTd(@Param("fromDate") String fromDate,@Param("toDate") String toDate);
	
	//SAC 29-03-2021 for EwayBill
	@Transactional
	@Modifying
	@Query("UPDATE PostCreditNoteHeader t SET t.exVarchar2 =:ewayBillNo  WHERE t.crnId=:crnId")
	int updateCNoteForEwayBill(@Param("ewayBillNo") String ewayBillNo,@Param("crnId") int crnId);
	
}
