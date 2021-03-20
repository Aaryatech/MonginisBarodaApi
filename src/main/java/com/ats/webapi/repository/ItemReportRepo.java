package com.ats.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.ItemReport;

public interface ItemReportRepo extends JpaRepository<ItemReport, Integer> {

	@Query(value = "SELECT d.item_id,SUM(d.bill_qty) as bill_qty,SUM(d.order_qty) as order_qty,i.item_name,h.bill_no,h.invoice_no  "
			+ " FROM t_bill_header h,t_bill_detail d,m_item i WHERE h.del_status=0 AND d.del_status=0 AND i.id=d.item_id  AND d.cat_id!=5  AND "
			+ "h.bill_no=d.bill_no AND h.bill_date BETWEEN :fromDate AND :toDate AND h.fr_id=:frId GROUP BY i.item_grp1, i.item_grp2, i.item_grp2 "
			+ "", nativeQuery = true)

	List<ItemReport> getItemReportByFrId(@Param("fromDate") String fromDate, @Param("toDate") String toDate,
			@Param("frId") int frId);

	@Query(value = "SELECT\n" + 
			"        d.item_id,\n" + 
			"        SUM(d.bill_qty) as bill_qty,\n" + 
			"        SUM(d.order_qty) as order_qty,\n" + 
			"        i.item_name,\n" + 
			"        h.bill_no,\n" + 
			"        h.invoice_no   \n" + 
			"    FROM\n" + 
			"        t_bill_header h,\n" + 
			"        t_bill_detail d,\n" + 
			"        m_item i \n" + 
			"    WHERE\n" + 
			"        h.del_status=0 \n" + 
			"        AND d.del_status=0 \n" + 
			"        AND i.id=d.item_id \n" + 
			"        AND d.cat_id!=5  \n" + 
			"        AND h.bill_no=d.bill_no \n" + 
			"        AND h.bill_date BETWEEN :fromDate AND :toDate \n" + 
			"    GROUP BY\n" + 
			"        i.item_grp1,\n" + 
			"        i.item_grp2,i.item_name", nativeQuery = true)

	List<ItemReport> getItemReport(@Param("fromDate") String fromDate, @Param("toDate") String toDate);

}
//Replaced for -: group By clause - Selected Franchise - 20-03-2021
//@Query(value = "SELECT d.item_id,SUM(d.bill_qty) as bill_qty,SUM(d.order_qty) as order_qty,i.item_name,h.bill_no,h.invoice_no  "
//		+ " FROM t_bill_header h,t_bill_detail d,m_item i WHERE h.del_status=0 AND d.del_status=0 AND i.id=d.item_id  AND d.cat_id!=5  AND "
//		+ "h.bill_no=d.bill_no AND h.bill_date BETWEEN :fromDate AND :toDate AND h.fr_id=:frId GROUP BY d.item_id "
//		+ "", nativeQuery = true)
//
//List<ItemReport> getItemReportByFrId(@Param("fromDate") String fromDate, @Param("toDate") String toDate,
//		@Param("frId") int frId);


//Replaced for -: group By clause - All Franchise - 20-03-2021
//@Query(value = "SELECT d.item_id,SUM(d.bill_qty) as bill_qty,SUM(d.order_qty) as order_qty,i.item_name,h.bill_no,h.invoice_no  "
//		+ " FROM t_bill_header h,t_bill_detail d,m_item i WHERE h.del_status=0 AND d.del_status=0 AND i.id=d.item_id AND d.cat_id!=5  AND "
//		+ "h.bill_no=d.bill_no AND h.bill_date BETWEEN :fromDate AND :toDate GROUP BY d.item_id "
//		+ "", nativeQuery = true)
//
//List<ItemReport> getItemReport(@Param("fromDate") String fromDate, @Param("toDate") String toDate);