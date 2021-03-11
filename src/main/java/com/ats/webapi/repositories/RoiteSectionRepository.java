package com.ats.webapi.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.RouteSection;

//Mahendra 2021-02-26
public interface RoiteSectionRepository extends JpaRepository<RouteSection, Integer> {

	@Query(value="SELECT * FROM m_route_section WHERE del_status=0 AND section_id=:sectionId",nativeQuery=true)
	RouteSection getSingleSectionById(@Param("sectionId") Integer sectionId);
	
	@Query(value="SELECT * FROM m_route_section WHERE del_status=0 ORDER BY section_id DESC",nativeQuery=true)
	List<RouteSection> getAllSection();
	
	@Transactional
	@Modifying
	@Query(value="UPDATE m_route_section SET del_status=1 WHERE section_id=:sectionId",nativeQuery=true)
	int deleteRouteSection(@Param("sectionId") Integer sectionId);

	List<RouteSection> findByDelStatus(int i);

	RouteSection findBySectionIdAndDelStatus(int sectionId, int i);
	
	
	@Query(value="SELECT\n" + 
			"    s.section_id,\n" + 
			"    s.route_ids,\n" + 
			"    s.section_name,\n" + 
			"    s.sec_type,\n" + 
			"    s.maker_user_id,\n" + 
			"    s.maker_datetime,\n" + 
			"    s.ex_int1,\n" + 
			"    s.ex_int2,\n" + 
			"    s.ex_var1,\n" + 
			"    GROUP_CONCAT(r.route_name) AS ex_var2,\n" + 
			"    s.del_status\n" + 
			"FROM\n" + 
			"    m_route_section s,\n" + 
			"    m_fr_route r\n" + 
			"WHERE\n" + 
			"    s.del_status=0 AND\n" + 
			"    r.del_status=0 AND\n" + 
			"    FIND_IN_SET(r.route_id, s.route_ids)\n" + 
			"GROUP BY s.route_ids ORDER BY s.section_id DESC",nativeQuery=true)
	List<RouteSection> getSectionAndRouteList();
	//Section With Menu id And Menu title In Extra Field
	
	
}
