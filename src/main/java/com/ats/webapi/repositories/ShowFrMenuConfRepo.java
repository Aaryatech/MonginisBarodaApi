package com.ats.webapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.ShowFrMenuConfExlPdf;

public interface ShowFrMenuConfRepo extends JpaRepository<ShowFrMenuConfExlPdf, Integer> {

	
	@Query(value="SELECT\n" + 
			"    m.menu_id,\n" + 
			"    m.menu_title,\n" + 
			"    m.is_same_day_applicable AS type,\n" + 
			"    c.cat_name,\n" + 
			"    f.profit_per,\n" + 
			"    f.grn_per,\n" + 
			"    f.disc_per,\n" + 
			"    '' AS fr_name,\n" + 
			"    '' AS from_time,\n" + 
			"    '' AS to_time\n" + 
			"FROM\n" + 
			"    m_fr_menu_show m,\n" + 
			"    m_fr_configure f,\n" + 
			"    m_category c\n" + 
			"WHERE\n" + 
			"    m.del_status = 0 AND m.cat_id = c.cat_id AND m.menu_id = f.menu_id",nativeQuery=true)
	List<ShowFrMenuConfExlPdf> getfrMenuConfigList();

	@Query(value=" SELECT\n" + 
			"        m.menu_id,\n" + 
			"        m.menu_title,\n" + 
			"        m.is_same_day_applicable AS type,\n" + 
			"        c.cat_name,\n" + 
			"        f.profit_per,\n" + 
			"        f.grn_per,\n" + 
			"        f.disc_per,\n" + 
			"        CONCAT(fr.fr_name,\n" + 
			"        '-',\n" + 
			"        fr.fr_code) AS fr_name,\n" + 
			"        f.from_time,\n" + 
			"        f.to_time \n" + 
			"    FROM\n" + 
			"        m_fr_menu_show m,\n" + 
			"        m_fr_configure f,\n" + 
			"        m_category c,\n" + 
			"        m_franchisee fr \n" + 
			"    WHERE\n" + 
			"        m.del_status = 0 \n" + 
			"        AND m.cat_id = c.cat_id \n" + 
			"        AND m.menu_id = f.menu_id \n" + 
			"        AND f.fr_id=fr.fr_id\n" + 
			"        AND fr.fr_id IN (:frIds) \n" + 
			"        AND m.menu_id IN (:menuIds)",nativeQuery=true)
	List<ShowFrMenuConfExlPdf> getAllFrMenusExlPdfList(@Param("menuIds") List<String> menuIds, @Param("frIds") List<String> frIds);
}
