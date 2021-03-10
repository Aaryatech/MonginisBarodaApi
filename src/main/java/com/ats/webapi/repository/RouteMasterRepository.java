package com.ats.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.controller.RouteMaster;

public interface RouteMasterRepository extends JpaRepository<RouteMaster, Integer>{

	List<RouteMaster> findByDelStatusOrderByRouteNameAsc(int i);

	RouteMaster findByRouteId(int routeId);

	List<RouteMaster> findByDelStatusAndAbcTypeOrderByRouteNameAsc(int i, int abcType);
	
	
	List<RouteMaster> findByDelStatusAndAbcTypeInOrderByRouteNameAsc(int i, List<Integer> abcType);
	List<RouteMaster> findByRouteIdInAndDelStatusOrderByRouteNameAsc(List<Integer> routeIds, int delStatus);

	List<RouteMaster> findByRouteIdAndDelStatusOrderByRouteNameAsc(int routId, int i);
	
	RouteMaster findByShortNameIgnoreCase(@Param("shortName") String shortName);

	RouteMaster findByShortNameIgnoreCaseAndRouteIdNotIn(@Param("shortName") String shortName, @Param("routeId") int routeId);
	
	RouteMaster findByRoutePrefixIgnoreCase(@Param("routeprefix") String routeprefix);
	
	RouteMaster findByRoutePrefixIgnoreCaseAndRouteIdNotIn(@Param("routePrefix") String routePrefix, @Param("routeId") int routeId);

	@Query(value="SELECT\n" + 
			"    rt.route_id,\n" + 
			"    rt.route_name,\n" + 
			"    rt.abc_type,\n" + 
			"    rt.seq_no,\n" + 
			"    rt.short_name,\n" + 
			"    rt.route_prefix,\n" + 
			"    rt.max_km,\n" + 
			"    rt.min_km,\n" + 
			"    rt.ex_int1,\n" + 
			"    rt.ex_int2,\n" + 
			"    rt.ex_var1, rt.del_status,\n" + 
			"    abc.abc_val AS ex_var2,\n" + 
			"    rt.route_type\n" + 
			"FROM\n" + 
			"    m_fr_route rt,\n" + 
			"	m_abc_type abc\n" + 
			"WHERE\n" + 
			"    rt.del_status=0 AND\n" + 
			"    rt.abc_type=abc.abc_id", nativeQuery=true)
	List<RouteMaster> getFrRouteAndAbcType();
}
