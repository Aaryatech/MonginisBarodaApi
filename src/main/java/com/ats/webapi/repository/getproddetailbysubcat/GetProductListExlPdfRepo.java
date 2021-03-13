package com.ats.webapi.repository.getproddetailbysubcat;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ats.webapi.model.prod.GetProductListExlPdf;

public interface GetProductListExlPdfRepo extends JpaRepository<GetProductListExlPdf, Integer> {

	@Query(value="SELECT\n" + 
			"    i.id,\n" + 
			"    CONCAT(i.item_name,'-', i.item_id) AS item_name,\n" + 
			"    i.item_mrp1,\n" + 
			"    i.item_mrp2,\n" + 
			"    i.item_mrp3,\n" + 
			"    i.item_tax3,\n" + 
			"    i.item_shelf_life,\n" + 
			"    i.item_sort_id,\n" + 
			"    i.item_is_used,\n" + 
			"    s.item_hsncd,\n" + 
			"    u.uom,\n" + 
			"    c.sub_cat_name\n" + 
			"FROM\n" + 
			"    m_item i,\n" + 
			"    m_rm_uom u,\n" + 
			"    m_cat_sub c,\n" + 
			"    m_item_sup s\n" + 
			"WHERE\n" + 
			"    i.del_status=0 AND\n" + 
			"    i.id=s.item_id AND\n" + 
			"    i.item_grp2=c.sub_cat_id AND\n" + 
			"    s.uom_id=u.uom_id\n" + 
			"ORDER BY i.id DESC",nativeQuery=true)
	
	List<GetProductListExlPdf> getProductExlPdfList();
	
}
