package com.ats.webapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.GetFrMenuExlPdf;

public interface FrMenuExlPdfRepo extends JpaRepository<GetFrMenuExlPdf, Integer> {
	@Query(value="SELECT\n" + 
			"    m.*,\n" + 
			"    c.cat_name, '' AS fr_name\n" + 
			"FROM\n" + 
			"    m_fr_menu_show m,\n" + 
			"    m_category c\n" + 
			"WHERE\n" + 
			"    m.del_status = 0 AND m.cat_id = c.cat_id ORDER BY c.cat_id",nativeQuery=true)
	public List<GetFrMenuExlPdf> getAllFrMenus();
}
