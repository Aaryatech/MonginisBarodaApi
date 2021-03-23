package com.ats.webapi.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.bill.GetBillAmtGroupByFr;

public interface GetBillAmtGroupByFrRepo extends JpaRepository<GetBillAmtGroupByFr, Integer> {
	
	@Query(value = "SELECT * FROM ( SELECT h.fr_id,SUM(h.grand_total) AS total FROM t_bill_header h WHERE h.bill_date BETWEEN :fromDate AND :toDate AND h.del_status=0 GROUP BY h.fr_id) t WHERE t.total>(SELECT COALESCE(s.setting_value1,0) FROM t_setting_new s WHERE s.del_status=0 AND s.setting_key='TCS_FR_LIMIT')", nativeQuery = true)
	List<GetBillAmtGroupByFr> getBillTotalByFr(@Param("fromDate") String fromDate,@Param("toDate") String toDate);

	

}
