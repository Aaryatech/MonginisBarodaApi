package com.ats.webapi.repository.reportv2;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.reportv2.HSNWiseReport;

public interface HSNWiseReportRepo extends JpaRepository<HSNWiseReport, Integer> {

	@Query(value = "SELECT" + "            t_bill_detail.hsn_code as id,"
			+ "            t_bill_detail.hsn_code as item_hsncd," + "            t_bill_detail.sgst_per as item_tax1,"
			+ "             t_bill_detail.cgst_per as item_tax2,t_bill_detail.cess_per,"
			+ "            SUM(t_bill_detail.bill_qty) as bill_qty,"
			+ "            SUM(t_bill_detail.taxable_amt) as taxable_amt,"
			+ "            SUM(t_bill_detail.cgst_rs) as cgst_rs,'NA' AS sub_cat_name, 0 AS sub_cat_id,  " + "            SUM(t_bill_detail.sgst_rs) as sgst_rs,SUM(t_bill_detail.cess_rs) as cess_rs "
			+ "        FROM t_bill_header," + "            t_bill_detail" + "        WHERE       t_bill_header.bill_no=t_bill_detail.bill_no"
			+ "            AND      t_bill_header.bill_date BETWEEN :fromDate AND :toDate and t_bill_header.del_status=0 and t_bill_detail.del_status=0 " + "        GROUP BY"
			+ "            item_hsncd", nativeQuery = true)

	List<HSNWiseReport> getReport(@Param("fromDate") String fromDate, @Param("toDate") String toDate);
	
	
	
	
	@Query(value="SELECT\r\n" + 
			"        uuid() AS id,\r\n" + 
			"        t_bill_detail.hsn_code AS item_hsncd,\r\n" + 
			"        t_bill_detail.sgst_per AS item_tax1,\r\n" + 
			"        t_bill_detail.cgst_per AS item_tax2,\r\n" + 
			"        t_bill_detail.cess_per,\r\n" + 
			"        SUM(t_bill_detail.bill_qty) AS bill_qty,\r\n" + 
			"        SUM(t_bill_detail.taxable_amt) AS taxable_amt,\r\n" + 
			"        SUM(t_bill_detail.cgst_rs) AS cgst_rs,\r\n" + 
			"        SUM(t_bill_detail.sgst_rs) AS sgst_rs,\r\n" + 
			"        SUM(t_bill_detail.cess_rs) AS cess_rs,\r\n" + 
			"        m_cat_sub.sub_cat_name,\r\n" + 
			"        m_cat_sub.sub_cat_id\r\n" + 
			"    FROM\r\n" + 
			"        t_bill_header,\r\n" + 
			"        t_bill_detail,\r\n" + 
			"        m_item,\r\n" + 
			"        m_cat_sub,\r\n" + 
			"        m_category\r\n" + 
			"    WHERE\r\n" + 
			"        t_bill_header.bill_no = t_bill_detail.bill_no\r\n" + 
			"        AND t_bill_header.bill_date BETWEEN :fromDate AND :toDate\r\n" + 
			"        AND t_bill_header.del_status = 0\r\n" + 
			"        AND t_bill_detail.del_status = 0\r\n" + 
			"        AND t_bill_detail.item_id = m_item.id\r\n" + 
			"        AND m_item.item_grp2 = m_cat_sub.sub_cat_id\r\n" + 
			"        AND m_category.cat_id=m_cat_sub.cat_id\r\n" + 
			"        and m_category.cat_id=t_bill_detail.cat_id\r\n" + 
			"        and t_bill_detail.cat_id !=5\r\n" + 
			"    GROUP BY\r\n" + 
			"        item_hsncd,\r\n" + 
			"        m_cat_sub.sub_cat_id\r\n" + 
			"    UNION\r\n" + 
			"    ALL               SELECT\r\n" + 
			"        uuid() AS id,\r\n" + 
			"        t_bill_detail.hsn_code AS item_hsncd,\r\n" + 
			"        t_bill_detail.sgst_per AS item_tax1,\r\n" + 
			"        t_bill_detail.cgst_per AS item_tax2,\r\n" + 
			"        t_bill_detail.cess_per,\r\n" + 
			"        SUM(t_bill_detail.bill_qty) AS bill_qty,\r\n" + 
			"        SUM(t_bill_detail.taxable_amt) AS taxable_amt,\r\n" + 
			"        SUM(t_bill_detail.cgst_rs) AS cgst_rs,\r\n" + 
			"        SUM(t_bill_detail.sgst_rs) AS sgst_rs,\r\n" + 
			"        SUM(t_bill_detail.cess_rs) AS cess_rs,\r\n" + 
			"        m_cat_sub.sub_cat_name,\r\n" + 
			"        m_cat_sub.sub_cat_id\r\n" + 
			"    FROM\r\n" + 
			"        t_bill_header,\r\n" + 
			"        t_bill_detail,\r\n" + 
			"        m_sp_cake,\r\n" + 
			"        m_cat_sub,\r\n" + 
			"        m_category\r\n" + 
			"    WHERE\r\n" + 
			"        t_bill_header.bill_no = t_bill_detail.bill_no\r\n" + 
			"        AND t_bill_header.bill_date BETWEEN :fromDate AND :fromDate\r\n" + 
			"        AND t_bill_header.del_status = 0\r\n" + 
			"        AND t_bill_detail.del_status = 0\r\n" + 
			"        AND t_bill_detail.item_id = m_sp_cake.sp_id\r\n" + 
			"        AND 5=m_cat_sub.cat_id\r\n" + 
			"        and m_category.cat_id=5\r\n" + 
			"        and t_bill_detail.cat_id=5\r\n" + 
			"    GROUP BY\r\n" + 
			"        item_hsncd,\r\n" + 
			"        m_cat_sub.sub_cat_id",nativeQuery=true)
	List<HSNWiseReport> getReportSubcat(@Param("fromDate") String fromDate, @Param("toDate") String toDate);
	
	
	
	

	@Query(value = "SELECT" + "            t_bill_detail.hsn_code as id,"
			+ "            t_bill_detail.hsn_code as item_hsncd," + "            t_bill_detail.sgst_per as item_tax1,"
			+ "             t_bill_detail.cgst_per as item_tax2,t_bill_detail.cess_per,"
			+ "            SUM(t_bill_detail.bill_qty) as bill_qty,"
			+ "            SUM(t_bill_detail.taxable_amt) as taxable_amt,"
			+ "            SUM(t_bill_detail.cgst_rs) as cgst_rs,'NA' AS sub_cat_name, 0 AS sub_cat_id ,    " + "            SUM(t_bill_detail.sgst_rs) as sgst_rs,SUM(t_bill_detail.cess_rs) as cess_rs "
			+ "        FROM t_bill_header," + "            t_bill_detail" + "        WHERE      t_bill_detail.cat_id!=5"
			+ "            AND t_bill_header.bill_no=t_bill_detail.bill_no"
			+ "            AND      t_bill_header.bill_date BETWEEN :fromDate AND :toDate AND t_bill_header.fr_id=:frId "
			+ "        GROUP BY" + "            item_hsncd", nativeQuery = true)

	List<HSNWiseReport> getReportByFrId(@Param("frId") int frId, @Param("fromDate") String fromDate,
			@Param("toDate") String toDate);

	@Query(value = "SELECT" + "        t_credit_note_details.hsn_code AS id,"
			+ "           t_credit_note_details.hsn_code as item_hsncd,"
			+ "            t_credit_note_details.sgst_per as item_tax1,"
			+ "            t_credit_note_details.cgst_per as item_tax2,t_credit_note_details.cess_per,"
			+ "            SUM(t_credit_note_details.grn_gvn_qty) as bill_qty,"
			+ "            SUM(t_credit_note_details.taxable_amt) as taxable_amt,"
			+ "            SUM(t_credit_note_details.cgst_rs) as cgst_rs,"
			+ "            SUM(t_credit_note_details.sgst_rs) as sgst_rs, SUM(t_credit_note_details.cess_rs) as cess_rs ,'NA' AS sub_cat_name, 0 AS sub_cat_id   " + "        FROM t_credit_note_details,"
			+ "            t_credit_note_header" + "        WHERE" + "  t_credit_note_header.crn_id=t_credit_note_details.crn_id"
			+ "            AND     t_credit_note_header.crn_date BETWEEN :fromDate AND :toDate and t_credit_note_details.del_status=0 " + "        GROUP BY"
			+ "            item_hsncd", nativeQuery = true)

	List<HSNWiseReport> getReportHsn(@Param("fromDate") String fromDate, @Param("toDate") String toDate);

	@Query(value = "SELECT" + "        t_credit_note_details.hsn_code AS id,"
			+ "           t_credit_note_details.hsn_code as item_hsncd,"
			+ "            t_credit_note_details.sgst_per as item_tax1,"
			+ "            t_credit_note_details.cgst_per as item_tax2,t_credit_note_details.cess_per,"
			+ "            SUM(t_credit_note_details.grn_gvn_qty) as bill_qty,"
			+ "            SUM(t_credit_note_details.taxable_amt) as taxable_amt,"
			+ "            SUM(t_credit_note_details.cgst_rs) as cgst_rs,"
			+ "            SUM(t_credit_note_details.sgst_rs) as sgst_rs,SUM(t_credit_note_details.cess_rs) as cess_rs,NA' AS sub_cat_name, 0 AS sub_cat_id " + "        FROM t_credit_note_details,"
			+ "            t_credit_note_header" + "        WHERE" + "              t_credit_note_details.cat_id!=5"
			+ "            and t_credit_note_header.crn_id=t_credit_note_details.crn_id"
			+ "            AND     t_credit_note_header.crn_date BETWEEN :fromDate AND :toDate AND t_credit_note_header.fr_id=:frId "
			+ "        GROUP BY" + "            item_hsncd", nativeQuery = true)

	List<HSNWiseReport> getReportHsnByFrId(@Param("frId") int frId, @Param("fromDate") String fromDate,
			@Param("toDate") String toDate);
	
	
	
	
	//Anmol----->5/12/2019------------->filter by grn or gvn
	@Query(value = "SELECT\r\n" + 
			"    t_credit_note_details.hsn_code AS id,\r\n" + 
			"    t_credit_note_details.hsn_code AS item_hsncd,\r\n" + 
			"    t_credit_note_details.sgst_per AS item_tax1,\r\n" + 
			"    t_credit_note_details.cgst_per AS item_tax2,t_credit_note_details.cess_per," + 
			"    SUM(\r\n" + 
			"        t_credit_note_details.grn_gvn_qty\r\n" + 
			"    ) AS bill_qty,\r\n" + 
			"    SUM(\r\n" + 
			"        t_credit_note_details.taxable_amt\r\n" + 
			"    ) AS taxable_amt,\r\n" + 
			"    SUM(t_credit_note_details.cgst_rs) AS cgst_rs,\r\n" + 
			"    SUM(t_credit_note_details.sgst_rs) AS sgst_rs,SUM(t_credit_note_details.cess_rs) as cess_rs ,'NA' AS sub_cat_name, 0 AS sub_cat_id " + 
			"FROM\r\n" + 
			"    t_credit_note_details,\r\n" + 
			"    t_credit_note_header\r\n" + 
			"WHERE\r\n" + 
			"    t_credit_note_header.crn_id = t_credit_note_details.crn_id AND t_credit_note_header.crn_date BETWEEN :fromDate AND :toDate AND t_credit_note_details.del_status = 0 AND t_credit_note_details.is_grn IN(:grngvnType)\r\n" + 
			"GROUP BY\r\n" + 
			"    item_hsncd", nativeQuery = true)

	List<HSNWiseReport> getReportHsnIn(@Param("fromDate") String fromDate, @Param("toDate") String toDate, @Param("grngvnType") List<Integer> grngvnType);
	
	
	
	
	@Query(value="SELECT\r\n" + 
			"        uuid() AS id,\r\n" + 
			"        t_credit_note_details.hsn_code AS item_hsncd,\r\n" + 
			"        t_credit_note_details.sgst_per AS item_tax1,\r\n" + 
			"        t_credit_note_details.cgst_per AS item_tax2,\r\n" + 
			"        t_credit_note_details.cess_per,\r\n" + 
			"        SUM(              t_credit_note_details.grn_gvn_qty          ) AS bill_qty,\r\n" + 
			"        SUM(              t_credit_note_details.taxable_amt          ) AS taxable_amt,\r\n" + 
			"        SUM(t_credit_note_details.cgst_rs) AS cgst_rs,\r\n" + 
			"        SUM(t_credit_note_details.sgst_rs) AS sgst_rs,\r\n" + 
			"        SUM(t_credit_note_details.cess_rs) AS cess_rs,\r\n" + 
			"         m_cat_sub.sub_cat_name,\r\n" + 
			"        m_cat_sub.sub_cat_id\r\n" + 
			"    FROM\r\n" + 
			"        t_credit_note_details,\r\n" + 
			"        t_credit_note_header,\r\n" + 
			"        m_item,\r\n" + 
			"        m_cat_sub ,\r\n" + 
			"        m_category\r\n" + 
			"    WHERE\r\n" + 
			"        t_credit_note_header.crn_id = t_credit_note_details.crn_id\r\n" + 
			"        AND t_credit_note_header.crn_date BETWEEN :fromDate AND :toDate\r\n" + 
			"        AND t_credit_note_details.del_status = 0\r\n" + 
			"        AND t_credit_note_details.is_grn IN(\r\n" + 
			"           :grngvnType\n" + 
			"        )\r\n" + 
			"        AND t_credit_note_details.item_id=m_item.id\r\n" + 
			"        AND m_item.item_grp2=m_cat_sub.sub_cat_id\r\n" + 
			"        and m_category.cat_id=m_cat_sub.cat_id\r\n" + 
			"        and t_credit_note_details.cat_id=m_category.cat_id\r\n" + 
			"        and t_credit_note_details.cat_id  !=5\r\n" + 
			"        and t_credit_note_details.cat_id=m_category.cat_id\r\n" + 
			"    GROUP BY\r\n" + 
			"        item_hsncd,\r\n" + 
			"        m_cat_sub.sub_cat_id\r\n" + 
			"    UNION\r\n" + 
			"    ALL                  SELECT\r\n" + 
			"        uuid() AS id,\r\n" + 
			"        t_credit_note_details.hsn_code AS item_hsncd,\r\n" + 
			"        t_credit_note_details.sgst_per AS item_tax1,\r\n" + 
			"        t_credit_note_details.cgst_per AS item_tax2,\r\n" + 
			"        t_credit_note_details.cess_per,\r\n" + 
			"        SUM(              t_credit_note_details.grn_gvn_qty          ) AS bill_qty,\r\n" + 
			"        SUM(              t_credit_note_details.taxable_amt          ) AS taxable_amt,\r\n" + 
			"        SUM(t_credit_note_details.cgst_rs) AS cgst_rs,\r\n" + 
			"        SUM(t_credit_note_details.sgst_rs) AS sgst_rs,\r\n" + 
			"        SUM(t_credit_note_details.cess_rs) AS cess_rs,\r\n" + 
			"        m_cat_sub.sub_cat_name,\r\n" + 
			"        m_cat_sub.sub_cat_id\r\n" + 
			"    FROM\r\n" + 
			"        t_credit_note_details,\r\n" + 
			"        t_credit_note_header,\r\n" + 
			"        m_sp_cake,\r\n" + 
			"        m_cat_sub ,\r\n" + 
			"        m_category\r\n" + 
			"    WHERE\r\n" + 
			"        t_credit_note_header.crn_id = t_credit_note_details.crn_id\r\n" + 
			"        AND t_credit_note_header.crn_date BETWEEN :fromDate AND :toDate\r\n" + 
			"        AND t_credit_note_details.del_status = 0\r\n" + 
			"        AND t_credit_note_details.is_grn IN(\r\n" + 
			"          :grngvnType\n" + 
			"        )\r\n" + 
			"        AND t_credit_note_details.item_id=m_sp_cake.sp_id\r\n" + 
			"        and m_category.cat_id=m_cat_sub.cat_id\r\n" + 
			"        and t_credit_note_details.cat_id=m_category.cat_id\r\n" + 
			"        and t_credit_note_details.cat_id  =5\r\n" + 
			"    GROUP BY\r\n" + 
			"        item_hsncd,\r\n" + 
			"        m_cat_sub.sub_cat_id",nativeQuery=true)
	List<HSNWiseReport> getReportHsnInForSubcat(@Param("fromDate") String fromDate, @Param("toDate") String toDate, @Param("grngvnType") List<Integer> grngvnType);
	
	
	
	

}
