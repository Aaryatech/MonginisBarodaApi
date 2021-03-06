package com.ats.webapi.repository.ggreport;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.grngvnreport.GGReportGrpByFrId;

public interface GGReportGrpByFrIdRepo extends JpaRepository<GGReportGrpByFrId, Integer> {
	
	//sumit Sir Query report 2
	
	@Query(value="SELECT t1.is_grn,t1.fr_id,COALESCE(t2.apr_grand_total , 0) AS apr_grand_total,t1.total_amt,t1.fr_name,t1.req_qty,COALESCE(t2.apr_qty,0) AS apr_qty FROM\n" + 
			"(SELECT\n" + 
			"        t_grn_gvn_header.is_grn,\n" + 
			"        t_grn_gvn_header.fr_id,\n" + 
			"        sum(t_grn_gvn.apr_grand_total)as apr_grand_total,\n" + 
			"        sum(t_grn_gvn.grn_gvn_amt) as total_amt,\n" + 
			"        m_franchisee.fr_name,\n" + 
			"        sum(t_grn_gvn.grn_gvn_qty) as req_qty,\n" + 
			"        sum(t_grn_gvn.apr_qty_acc)  as apr_qty \n" + 
			"    from\n" + 
			"        t_grn_gvn_header,\n" + 
			"        m_franchisee,\n" + 
			"        t_grn_gvn \n" + 
			"    WHERE\n" + 
			"        t_grn_gvn_header.fr_id IN (\n" + 
			"          :frIdList \n" + 
			"        )  \n" + 
			"        AND m_franchisee.fr_id=t_grn_gvn_header.fr_id \n" + 
			"        AND t_grn_gvn_header.grngvn_date BETWEEN :fromDate and :toDate  \n" + 
			"        AND t_grn_gvn_header.is_grn IN (\n" + 
			"            :isGrn\n" + 
			"        ) \n" + 
			"        AND t_grn_gvn_header.grn_gvn_header_id=t_grn_gvn.grn_gvn_header_id \n" + 
			"        AND t_grn_gvn.fr_id=m_franchisee.fr_id \n" + 
			"    GROUP by\n" + 
			"        t_grn_gvn.fr_id) t1\n" + 
			"        LEFT JOIN\n" + 
			"        (SELECT\n" + 
			"        t_grn_gvn_header.is_grn,\n" + 
			"        t_grn_gvn_header.fr_id,\n" + 
			"        sum(t_grn_gvn.apr_grand_total)as apr_grand_total,\n" + 
			"        sum(t_grn_gvn.grn_gvn_amt) as total_amt,\n" + 
			"        m_franchisee.fr_name,\n" + 
			"        sum(t_grn_gvn.grn_gvn_qty) as req_qty,\n" + 
			"        sum(t_grn_gvn.apr_qty_acc)  as apr_qty \n" + 
			"    from\n" + 
			"        t_grn_gvn_header,\n" + 
			"        m_franchisee,\n" + 
			"        t_grn_gvn \n" + 
			"    WHERE\n" + 
			"        t_grn_gvn_header.fr_id IN (\n" + 
			"          :frIdList\n" + 
			"        )  \n" + 
			"        AND m_franchisee.fr_id=t_grn_gvn_header.fr_id \n" + 
			"        AND t_grn_gvn_header.grngvn_date BETWEEN :fromDate and :toDate  \n" + 
			"        AND t_grn_gvn_header.is_grn IN (\n" + 
			"            :isGrn\n" + 
			"        ) \n" + 
			"        AND t_grn_gvn_header.grn_gvn_header_id=t_grn_gvn.grn_gvn_header_id \n" + 
			"        AND t_grn_gvn.fr_id=m_franchisee.fr_id  AND t_grn_gvn.grn_gvn_status=6\n" + 
			"    GROUP by\n" + 
			"        t_grn_gvn.fr_id) t2 \n" + 
			"        ON t1.fr_id=t2.fr_id",nativeQuery=true)
	
		List<GGReportGrpByFrId> getGGReportGrpByFrIdSelFr(@Param("fromDate") 
		String fromDate,@Param("toDate") String toDate, @Param("isGrn") List<String> isGrn, @Param("frIdList") List<String> frIdList);
	
	/*@Query(value=" SELECT t_grn_gvn_header.is_grn,t_grn_gvn_header.fr_id, "
			+ " sum(t_grn_gvn.apr_grand_total)as apr_grand_total, sum(t_grn_gvn.grn_gvn_amt) as total_amt,"
			+ " m_franchisee.fr_name,sum(t_grn_gvn.grn_gvn_qty) as req_qty,sum(t_grn_gvn.apr_qty_acc) " + 
			" as apr_qty from t_grn_gvn_header,m_franchisee,t_grn_gvn WHERE t_grn_gvn_header.fr_id IN (:frIdList) "
			+ " AND m_franchisee.fr_id=t_grn_gvn_header.fr_id AND t_grn_gvn_header.grngvn_date BETWEEN :fromDate and :toDate "
			+ " AND t_grn_gvn_header.is_grn IN (:isGrn) AND t_grn_gvn_header.grn_gvn_header_id=t_grn_gvn.grn_gvn_header_id AND "
			+ "t_grn_gvn.fr_id=m_franchisee.fr_id GROUP by t_grn_gvn.fr_id",nativeQuery=true)
	
		List<GGReportGrpByFrId> getGGReportGrpByFrIdSelFr(@Param("fromDate") 
		String fromDate,@Param("toDate") String toDate, @Param("isGrn") List<String> isGrn, @Param("frIdList") List<String> frIdList);*/
	
	//sumit Sir Query report 2 all fr Selected 

	/*@Query(value=" SELECT t_grn_gvn_header.is_grn,t_grn_gvn_header.fr_id, "
			+ " sum(t_grn_gvn.apr_grand_total)as apr_grand_total, sum(t_grn_gvn.grn_gvn_amt) as total_amt,"
			+ " m_franchisee.fr_name,sum(t_grn_gvn.grn_gvn_qty) as req_qty,sum(t_grn_gvn.apr_qty_acc)" + 
			" as apr_qty from t_grn_gvn_header,m_franchisee,t_grn_gvn WHERE "
			+ " m_franchisee.fr_id=t_grn_gvn_header.fr_id AND t_grn_gvn_header.grngvn_date BETWEEN :fromDate and :toDate "
			+ " AND t_grn_gvn_header.is_grn IN (:isGrn) AND t_grn_gvn_header.grn_gvn_header_id=t_grn_gvn.grn_gvn_header_id AND "
			+ " t_grn_gvn.fr_id=m_franchisee.fr_id GROUP by t_grn_gvn.fr_id",nativeQuery=true)
	
		List<GGReportGrpByFrId> getGGReportGrpByFrIdSelFrAllFr(@Param("fromDate") 
		String fromDate,@Param("toDate") String toDate, @Param("isGrn") List<String> isGrn);*/
	
	@Query(value="SELECT t1.is_grn,t1.fr_id,COALESCE(t2.apr_grand_total , 0) AS apr_grand_total,t1.total_amt,t1.fr_name,t1.req_qty,COALESCE(t2.apr_qty,0) AS apr_qty FROM \n" + 
			"(SELECT\n" + 
			"        t_grn_gvn_header.is_grn,\n" + 
			"        t_grn_gvn_header.fr_id,\n" + 
			"        sum(t_grn_gvn.apr_grand_total)as apr_grand_total,\n" + 
			"        sum(t_grn_gvn.grn_gvn_amt) as total_amt,\n" + 
			"        m_franchisee.fr_name,\n" + 
			"        sum(t_grn_gvn.grn_gvn_qty) as req_qty,\n" + 
			"        sum(t_grn_gvn.apr_qty_acc) as apr_qty\n" + 
			"    from\n" + 
			"        t_grn_gvn_header,\n" + 
			"        m_franchisee,\n" + 
			"        t_grn_gvn\n" + 
			"    WHERE\n" + 
			"        m_franchisee.fr_id=t_grn_gvn_header.fr_id\n" + 
			"        AND t_grn_gvn_header.grngvn_date BETWEEN :fromDate and :toDate  \n" + 
			"        AND t_grn_gvn_header.is_grn IN (\n" + 
			"            :isGrn  \n" + 
			"        )\n" + 
			"        AND t_grn_gvn_header.grn_gvn_header_id=t_grn_gvn.grn_gvn_header_id\n" + 
			"        AND  t_grn_gvn.fr_id=m_franchisee.fr_id\n" + 
			"    GROUP by\n" + 
			"        t_grn_gvn.fr_id) t1\n" + 
			"        LEFT JOIN(\n" + 
			"        SELECT\n" + 
			"        t_grn_gvn_header.is_grn,\n" + 
			"        t_grn_gvn_header.fr_id,\n" + 
			"        sum(t_grn_gvn.apr_grand_total)as apr_grand_total,\n" + 
			"        sum(t_grn_gvn.grn_gvn_amt) as total_amt,\n" + 
			"        m_franchisee.fr_name,\n" + 
			"        sum(t_grn_gvn.grn_gvn_qty) as req_qty,\n" + 
			"        sum(t_grn_gvn.apr_qty_acc) as apr_qty\n" + 
			"    from\n" + 
			"        t_grn_gvn_header,\n" + 
			"        m_franchisee,\n" + 
			"        t_grn_gvn\n" + 
			"    WHERE\n" + 
			"        m_franchisee.fr_id=t_grn_gvn_header.fr_id\n" + 
			"        AND t_grn_gvn_header.grngvn_date BETWEEN :fromDate and :toDate  \n" + 
			"        AND t_grn_gvn_header.is_grn IN (\n" + 
			"            :isGrn \n" + 
			"        )\n" + 
			"        AND t_grn_gvn_header.grn_gvn_header_id=t_grn_gvn.grn_gvn_header_id\n" + 
			"        AND  t_grn_gvn.fr_id=m_franchisee.fr_id AND t_grn_gvn.grn_gvn_status=6\n" + 
			"    GROUP by\n" + 
			"        t_grn_gvn.fr_id)t2 on t1.fr_id=t2.fr_id\n" + 
			"",nativeQuery=true)
	
		List<GGReportGrpByFrId> getGGReportGrpByFrIdSelFrAllFr(@Param("fromDate") 
		String fromDate,@Param("toDate") String toDate, @Param("isGrn") List<String> isGrn);
	
	
	
	

}
