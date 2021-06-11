package com.ats.webapi.repository.ggreport;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.grngvnreport.GGReportGrpByMonthDate;

public interface GGreportGrpByDateMonthRepo  extends JpaRepository<GGReportGrpByMonthDate, Integer>{
	
	//r3 group by date
	
		//r3 sumit sir query
		
		@Query(value=" SELECT t_grn_gvn.grn_gvn_date,monthname(t_grn_gvn.grn_gvn_date) as month, t_grn_gvn.is_grn," + 
				"			sum(t_grn_gvn.apr_grand_total) as apr_grand_total , sum(t_grn_gvn.grn_gvn_amt)as total_amt ," + 
				"			sum(t_grn_gvn.grn_gvn_qty) as req_qty, sum(t_grn_gvn.apr_qty_acc) as apr_qty " + 
				"			from t_grn_gvn " + 
				"		WHERE " + 
				"			t_grn_gvn.grn_gvn_date BETWEEN :fromDate AND :toDate AND t_grn_gvn.fr_id IN(:frIdList) AND t_grn_gvn.is_grn IN ( :isGrn)" + 
				"			GROUP BY t_grn_gvn.grn_gvn_date",nativeQuery=true)
		
			List<GGReportGrpByMonthDate> getGrnGvnReportByDateSelFrGroupByDate(@Param("fromDate") 
			String fromDate,@Param("toDate") String toDate, @Param("isGrn") List<String> isGrn, @Param("frIdList") List<String> frIdList);
		
	//r3
		/*@Query(value="SELECT t_grn_gvn.grn_gvn_date,monthname(t_grn_gvn.grn_gvn_date) as month, t_grn_gvn.is_grn," + 
				"							sum(t_grn_gvn.apr_grand_total) as apr_grand_total , sum(t_grn_gvn.grn_gvn_amt)as total_amt, " + 
				"							sum(t_grn_gvn.grn_gvn_qty) as req_qty, sum(t_grn_gvn.apr_qty_acc) as apr_qty " + 
				"							from t_grn_gvn " + 
				"						WHERE" + 
				"						t_grn_gvn.grn_gvn_date BETWEEN :fromDate AND :toDate AND t_grn_gvn.is_grn IN ( :isGrn)" + 
				"						GROUP BY t_grn_gvn.grn_gvn_date ",nativeQuery=true)
		
			List<GGReportGrpByMonthDate> getGrnGvnReportByDateAllFrAllGGGroupByDate(@Param("fromDate") 
			String fromDate,@Param("toDate") String toDate,@Param("isGrn") List<String> isGrn);*/
		
		
		//r3
		@Query(value="SELECT t1.grn_gvn_date,t1.month,t1.is_grn,COALESCE(t2.apr_grand_total , 0) AS apr_grand_total,t1.total_amt,t1.req_qty,COALESCE(t2.apr_qty ,0) AS apr_qty FROM\n" + 
				"(SELECT\n" + 
				"        t_grn_gvn.grn_gvn_date,\n" + 
				"        monthname(t_grn_gvn.grn_gvn_date) as month,\n" + 
				"        t_grn_gvn.is_grn,\n" + 
				"        sum(t_grn_gvn.apr_grand_total) as apr_grand_total ,\n" + 
				"        sum(t_grn_gvn.grn_gvn_amt)as total_amt,\n" + 
				"        sum(t_grn_gvn.grn_gvn_qty) as req_qty,\n" + 
				"        sum(t_grn_gvn.apr_qty_acc) as apr_qty        \n" + 
				"    from\n" + 
				"        t_grn_gvn       \n" + 
				"    WHERE\n" + 
				"        t_grn_gvn.grn_gvn_date BETWEEN :fromDate AND :toDate \n" + 
				"        AND t_grn_gvn.is_grn IN (\n" + 
				"            :isGrn\n" + 
				"        )      \n" + 
				"    GROUP BY\n" + 
				"        t_grn_gvn.grn_gvn_date)t1\n" + 
				"        LEFT JOIN(SELECT\n" + 
				"        t_grn_gvn.grn_gvn_date,\n" + 
				"        monthname(t_grn_gvn.grn_gvn_date) as month,\n" + 
				"        t_grn_gvn.is_grn,\n" + 
				"        sum(t_grn_gvn.apr_grand_total) as apr_grand_total ,\n" + 
				"        sum(t_grn_gvn.grn_gvn_amt)as total_amt,\n" + 
				"        sum(t_grn_gvn.grn_gvn_qty) as req_qty,\n" + 
				"        sum(t_grn_gvn.apr_qty_acc) as apr_qty        \n" + 
				"    from\n" + 
				"        t_grn_gvn       \n" + 
				"    WHERE\n" + 
				"        t_grn_gvn.grn_gvn_date BETWEEN :fromDate AND :toDate \n" + 
				"        AND t_grn_gvn.is_grn IN (\n" + 
				"            :isGrn\n" + 
				"        )    AND t_grn_gvn.grn_gvn_status=6  \n" + 
				"    GROUP BY\n" + 
				"        t_grn_gvn.grn_gvn_date\n" + 
				"        \n" + 
				"        ) t2 on t1.grn_gvn_date=t2.grn_gvn_date",nativeQuery=true)
		
			List<GGReportGrpByMonthDate> getGrnGvnReportByDateAllFrAllGGGroupByDate(@Param("fromDate") 
			String fromDate,@Param("toDate") String toDate,@Param("isGrn") List<String> isGrn);
		
		//r4 group by month
		
		//r4
		
		@Query(value="SELECT t_grn_gvn.grn_gvn_date,monthname(t_grn_gvn.grn_gvn_date) as month, t_grn_gvn.is_grn," + 
				"											sum(t_grn_gvn.apr_grand_total) as apr_grand_total , sum(t_grn_gvn.grn_gvn_amt)as total_amt, " + 
				"										sum(t_grn_gvn.grn_gvn_qty) as req_qty, sum(t_grn_gvn.apr_qty_acc) as apr_qty " + 
				"											from t_grn_gvn " + 
				"									WHERE " + 
				"										t_grn_gvn.grn_gvn_date BETWEEN :fromDate AND :toDate AND t_grn_gvn.is_grn IN ( :isGrn) AND t_grn_gvn.fr_id IN(:frIdList)" + 
				"									GROUP BY month" + 
				"",nativeQuery=true)
		
			List<GGReportGrpByMonthDate> getGrnGvnReportByDateSelFrGroupByMonth(@Param("fromDate") 
			String fromDate,@Param("toDate") String toDate,@Param("frIdList") List<String> frIdList,@Param("isGrn") List<String> isGrn);
		//r4

		
	//r4
		
		
		@Query(value="SELECT t1.grn_gvn_date,t1.month,t1.is_grn,COALESCE(t2.apr_grand_total,0) AS apr_grand_total,t1.total_amt,t1.req_qty,COALESCE(t2.apr_qty,0)AS apr_qty   FROM\n" + 
				"( SELECT\n" + 
				"        t_grn_gvn.grn_gvn_date,\n" + 
				"        monthname(t_grn_gvn.grn_gvn_date) as month,\n" + 
				"        t_grn_gvn.is_grn,\n" + 
				"        sum(t_grn_gvn.apr_grand_total) as apr_grand_total ,\n" + 
				"        sum(t_grn_gvn.grn_gvn_amt)as total_amt,\n" + 
				"        sum(t_grn_gvn.grn_gvn_qty) as req_qty,\n" + 
				"        sum(t_grn_gvn.apr_qty_acc) as apr_qty         \n" + 
				"    from\n" + 
				"        t_grn_gvn          \n" + 
				"    WHERE\n" + 
				"        t_grn_gvn.grn_gvn_date BETWEEN :fromDate AND :toDate \n" + 
				"        AND t_grn_gvn.is_grn IN (\n" + 
				"          :isGrn \n" + 
				"        )         \n" + 
				"    GROUP BY\n" + 
				"        month )t1\n" + 
				"     LEFT JOIN\n" + 
				"     (\n" + 
				"     SELECT\n" + 
				"        t_grn_gvn.grn_gvn_date,\n" + 
				"        monthname(t_grn_gvn.grn_gvn_date) as month,\n" + 
				"        t_grn_gvn.is_grn,\n" + 
				"        sum(t_grn_gvn.apr_grand_total) as apr_grand_total ,\n" + 
				"        sum(t_grn_gvn.grn_gvn_amt)as total_amt,\n" + 
				"        sum(t_grn_gvn.grn_gvn_qty) as req_qty,\n" + 
				"        sum(t_grn_gvn.apr_qty_acc) as apr_qty         \n" + 
				"    from\n" + 
				"        t_grn_gvn          \n" + 
				"    WHERE\n" + 
				"        t_grn_gvn.grn_gvn_date BETWEEN :fromDate AND :toDate \n" + 
				"        AND t_grn_gvn.is_grn IN (\n" + 
				"          :isGrn \n" + 
				"        ) AND t_grn_gvn.grn_gvn_status=6     \n" + 
				"    GROUP BY\n" + 
				"        month\n" + 
				"     \n" + 
				"     )t2 ON t1.month=t2.month\n" + 
				"        \n" + 
				"        ",nativeQuery=true)
		
			List<GGReportGrpByMonthDate> getGrnGvnReportByDateAllFrAllGGGroupByMonth(@Param("fromDate") 
			String fromDate,@Param("toDate") String toDate,@Param("isGrn") List<String> isGrn);
		
		
	/*	@Query(value="SELECT t_grn_gvn.grn_gvn_date,monthname(t_grn_gvn.grn_gvn_date) as month, t_grn_gvn.is_grn," + 
				"								sum(t_grn_gvn.apr_grand_total) as apr_grand_total , sum(t_grn_gvn.grn_gvn_amt)as total_amt, " + 
				"									sum(t_grn_gvn.grn_gvn_qty) as req_qty, sum(t_grn_gvn.apr_qty_acc) as apr_qty " + 
				"								from t_grn_gvn " + 
				"									WHERE" + 
				"									t_grn_gvn.grn_gvn_date BETWEEN :fromDate AND :toDate AND t_grn_gvn.is_grn IN ( :isGrn) " + 
				"								GROUP BY month " + 
				"",nativeQuery=true)
		
			List<GGReportGrpByMonthDate> getGrnGvnReportByDateAllFrAllGGGroupByMonth(@Param("fromDate") 
			String fromDate,@Param("toDate") String toDate,@Param("isGrn") List<String> isGrn);*/
		

}
