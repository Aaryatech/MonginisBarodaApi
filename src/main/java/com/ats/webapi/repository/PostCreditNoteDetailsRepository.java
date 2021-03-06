package com.ats.webapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.webapi.model.grngvn.PostCreditNoteDetails;

public interface PostCreditNoteDetailsRepository extends JpaRepository<PostCreditNoteDetails, Integer>{
	
	PostCreditNoteDetails save(PostCreditNoteDetails postCreditNoteDetails);
	PostCreditNoteDetails findByGrnGvnIdAndDelStatus(int grnGvnId,int delStatus);//sac 29-05-2021
	
	
}
