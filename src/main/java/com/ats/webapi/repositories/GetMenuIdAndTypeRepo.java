package com.ats.webapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ats.webapi.model.GetMenuIdAndType;

public interface GetMenuIdAndTypeRepo extends JpaRepository<GetMenuIdAndType, Integer> {

	@Query(value="SELECT DISTINCT o.menu_id,f.is_same_day_applicable AS type FROM t_order o, m_fr_menu_show f WHERE o.menu_id=f.menu_id AND f.is_same_day_applicable=0\n" + 
			"UNION\n" + 
			"SELECT DISTINCT t.menu_id,f.is_same_day_applicable AS type FROM t_regular_sp_cake t, m_fr_menu_show f WHERE t.menu_id = f.menu_id AND f.is_same_day_applicable = 3\n" + 
			"UNION\n" + 
			"SELECT DISTINCT s.menu_id,f.is_same_day_applicable AS type FROM t_sp_cake s, m_fr_menu_show f WHERE s.menu_id=f.menu_id AND f.is_same_day_applicable=4",nativeQuery=true)

		List<GetMenuIdAndType> getSavedMenuIds();
}
