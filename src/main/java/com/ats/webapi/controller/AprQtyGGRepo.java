package com.ats.webapi.controller;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AprQtyGGRepo extends JpaRepository<AprQtyGG, Integer>{
	
	/*@Query(value = " SELECT SUM(gg.apr_qty_acc) as all_apr_qty,bd.bill_qty, gg.bill_detail_no " + 
			"		FROM t_grn_gvn gg,t_bill_detail bd  " + 
			"		WHERE gg.bill_detail_no IN (:billDetailNoList) and gg.grn_gvn_id NOT IN (:grnGvnIdList) and gg.grn_gvn_status=6 and bd.bill_detail_no=gg.bill_detail_no " + 
			"		GROUP by gg.bill_detail_no ", nativeQuery = true)
	List<AprQtyGG> getAprQtyGG(@Param("billDetailNoList") List<Integer> billDetailNoList,
			@Param("grnGvnIdList") List<Integer> grnGvnIdList);
*/
	
	/*
	 * SELECT t1.*,t2.all_apr_qty FROM (SELECT
	 * t_bill_detail.bill_detail_no,t_bill_detail.bill_qty FROM t_bill_detail WHERE
	 * t_bill_detail.bill_detail_no In (12249) ) t1 LEFT JOIN (SELECT
	 * SUM(gg.apr_qty_acc) as all_apr_qty,gg.bill_detail_no FROM t_grn_gvn gg WHERE
	 * gg.grn_gvn_status=6 and gg.bill_detail_no In(12249) and gg.grn_gvn_id NOT
	 * IN(3369)) t2 on t1.bill_detail_no=t2.bill_detail_no
	 */
    @Query(value = " SELECT t1.*,IFNULL(t2.all_apr_qty,0) as all_apr_qty " + 
    		"    FROM (SELECT t_bill_detail.bill_detail_no,t_bill_detail.bill_qty FROM t_bill_detail WHERE t_bill_detail.bill_detail_no In (:billDetailNoList) ) t1\n" + 
    		"    LEFT JOIN (SELECT IFNULL(SUM(gg.apr_qty_acc),0) as all_apr_qty,gg.bill_detail_no FROM t_grn_gvn gg WHERE gg.grn_gvn_status=6 and gg.bill_detail_no In (:billDetailNoList) and gg.grn_gvn_id NOT IN (:grnGvnIdList) group by gg.bill_detail_no) t2 on t1.bill_detail_no=t2.bill_detail_no\n" + 
    		"", nativeQuery = true)
	List<AprQtyGG> getAprQtyGG(@Param("billDetailNoList") List<Integer> billDetailNoList,
			@Param("grnGvnIdList") List<Integer> grnGvnIdList);

    
}
