package com.ats.webapi.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ats.webapi.model.bill.Company;
import com.ats.webapi.model.grngvn.PostCreditNoteDetails;
import com.ats.webapi.model.grngvn.PostCreditNoteHeader;
import com.ats.webapi.repository.CompanyRepository;
import com.ats.webapi.repository.FrItemStockConfigureRepository;
import com.ats.webapi.repository.PostCreditNoteDetailsRepository;
import com.ats.webapi.repository.PostCreditNoteHeaderRepository;
import com.ats.webapi.repository.UpdateGrnGvnForCreditNoteRepository;
import com.ats.webapi.repository.UpdateSeetingForPBRepo;
import com.ats.webapi.repository.grngvnheader.UpdateGrnGvnHeaderForCNRepo;

@Service
public class PostCreditNoteServiceImpl implements PostCreditNoteService {

	@Autowired
	PostCreditNoteHeaderRepository postCreditNoteHeaderRepository;

	@Autowired
	PostCreditNoteDetailsRepository postCreditNoteDetailsRepository;

	@Autowired
	UpdateGrnGvnForCreditNoteRepository updateGrnGvnForCreditNoteRepository;

	@Autowired
	UpdateGrnGvnHeaderForCNRepo updateGrnGvnHeaderForCNRepo;

	@Autowired
	FrItemStockConfigureRepository frItemStockConfRepo;

	@Autowired
	UpdateSeetingForPBRepo updateSeetingForPBRepo;

	@Autowired
	CompanyRepository companyRepository;

	@Override
	public List<PostCreditNoteHeader> savePostCreditNote(List<PostCreditNoteHeader> postCreditNoteHeader) {

		PostCreditNoteHeader creditNoteHeader = null;

		List<PostCreditNoteHeader> postCreditNoteHeaderList = new ArrayList<PostCreditNoteHeader>();
		for (int i = 0; i < postCreditNoteHeader.size(); i++) {

			creditNoteHeader = new PostCreditNoteHeader();
			int isgrn = postCreditNoteHeader.get(i).getIsGrn();
			int crnSrNo = 0;
			String invoiceNo = null;

			String pattern = "yyyy-MM-dd";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

			Company company = new Company();
			String date = simpleDateFormat.format(postCreditNoteHeader.get(i).getCrnDate());
			company = companyRepository.findByBillDate(date);

			if (isgrn == 1) {

				crnSrNo = frItemStockConfRepo.findBySettingKey("CRE_NOTE_NO");

				invoiceNo = company.getExVar5();
				invoiceNo = invoiceNo + crnSrNo;

				postCreditNoteHeader.get(i).setCrnNo("" + invoiceNo);
			} else {

				crnSrNo = frItemStockConfRepo.findBySettingKey("CRE_NOTE_NO_GVN");
				invoiceNo = company.getExVar6();
				invoiceNo = invoiceNo + crnSrNo;

				postCreditNoteHeader.get(i).setCrnNo("" + invoiceNo);
			}
			// System.err.println("crnSrNo"+crnSrNo);

			creditNoteHeader = postCreditNoteHeaderRepository.save(postCreditNoteHeader.get(i));

			/* sac comm 29-05-2021
			 * if(creditNoteHeader.getCrnId()!=0) {
			 * 
			 * int result=
			 * updateGrnGvnForCreditNoteRepository.updateGrnGvnForCreditNoteInsert(
			 * creditNoteHeader.getGrnGvnId(), 1);
			 * System.err.println("crnSrNo  while update " +crnSrNo); int result= 0;
			 * if(isgrn==1) { result=
			 * updateSeetingForPBRepo.updateSeetingForPurBill(crnSrNo+1, "CRE_NOTE_NO"); }
			 * else { result= updateSeetingForPBRepo.updateSeetingForPurBill(crnSrNo+1,
			 * "CRE_NOTE_NO_GVN"); }
			 * 
			 * 
			 * }
			 */

			postCreditNoteHeaderList.add(creditNoteHeader);

			int res = 0;

			int crnId = creditNoteHeader.getCrnId();

			List<PostCreditNoteDetails> postCreditNoteDetailsList = postCreditNoteHeader.get(i)
					.getPostCreditNoteDetails();
			int isHeaderSave = 0;
			for (int j = 0; j < postCreditNoteDetailsList.size(); j++) {
				boolean updateFlag = false;
				PostCreditNoteDetails postCreditNoteDetails = postCreditNoteDetailsList.get(j);
				PostCreditNoteDetails prevRecord = postCreditNoteDetailsRepository
						.findByGrnGvnIdAndDelStatus(postCreditNoteDetails.getGrnGvnId(), 0);
				System.err.println("prevRecord" + prevRecord);

				if (prevRecord == null) {

					postCreditNoteDetails.setCrnId(crnId);

					postCreditNoteDetailsRepository.save(postCreditNoteDetails);
					updateFlag = true;
					isHeaderSave = 1;
					if (updateFlag) {
						int result = updateGrnGvnForCreditNoteRepository
								.updateGrnGvnForCreditNoteInsert(postCreditNoteDetails.getGrnGvnId(), 1);

						int isCrnNoPresent = 0;
						try {
							System.err.println(
									"Grn Header Updated1" + crnSrNo + postCreditNoteDetails.getGrnGvnHeaderId());
							isCrnNoPresent = updateGrnGvnHeaderForCNRepo.isCrnNoPresent(crnSrNo,
									postCreditNoteDetails.getGrnGvnHeaderId());
						} catch (Exception e) {
							isCrnNoPresent = 0;
						}
						System.err.println("isCrnNoPresent" + isCrnNoPresent);
						if (crnId > 0) {
							System.err.println(
									"Grn Header Updated2" + crnSrNo + postCreditNoteDetails.getGrnGvnHeaderId());
							res = updateGrnGvnHeaderForCNRepo.updateGrnGvnHeaderForCN(crnId, 1,
									postCreditNoteDetails.getGrnGvnHeaderId());
						}
					} // end of if updateFlag true
				} // end of if prevRecord == null
			} // end of for postCreditNoteDetailsList loop
			System.err.println("isHeaderSave" +isHeaderSave + "creditNoteHeader" +creditNoteHeader);
			if (isHeaderSave < 1) {
				// delete header
				postCreditNoteHeaderRepository.delete(crnId);
			} else {
				// keep header and update sr no in setting k
				
				if(creditNoteHeader.getCrnId()!=0) {
					/*	
						int result= updateGrnGvnForCreditNoteRepository.updateGrnGvnForCreditNoteInsert(
								creditNoteHeader.getGrnGvnId(), 1);*/
						System.err.println("crnSrNo  while update " +crnSrNo);
						int result= 0;
						if(isgrn==1)
						{	
						result= updateSeetingForPBRepo.updateSeetingForPurBill(crnSrNo+1, "CRE_NOTE_NO");
						}	
						else
						{
							result= updateSeetingForPBRepo.updateSeetingForPurBill(crnSrNo+1, "CRE_NOTE_NO_GVN");
						}
						
						
					}

			}
		} // end of postCreditNoteHeader loop;

		return postCreditNoteHeaderList;
	}

	@Override
	public List<PostCreditNoteHeader> postCreditNoteForUpdate(List<PostCreditNoteHeader> postCreditNoteHeader) {

		PostCreditNoteHeader creditNoteHeader = null;

		List<PostCreditNoteHeader> postCreditNoteHeaderList = new ArrayList<PostCreditNoteHeader>();
		for (int i = 0; i < postCreditNoteHeader.size(); i++) {

			creditNoteHeader = new PostCreditNoteHeader();

			creditNoteHeader = postCreditNoteHeaderRepository.save(postCreditNoteHeader.get(i));

			postCreditNoteHeaderList.add(creditNoteHeader);

			int crnId = creditNoteHeader.getCrnId();

			List<PostCreditNoteDetails> postCreditNoteDetailsList = postCreditNoteHeader.get(i)
					.getPostCreditNoteDetails();

			for (int j = 0; j < postCreditNoteDetailsList.size(); j++) {

				PostCreditNoteDetails postCreditNoteDetails = postCreditNoteDetailsList.get(j);

				postCreditNoteDetails.setCrnId(crnId);

				postCreditNoteDetailsRepository.save(postCreditNoteDetails);

			}
		}

		return postCreditNoteHeaderList;
	}

}
