package com.ats.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.ItemWithSubCat;

public interface GetFrItemRepository extends  CrudRepository<ItemWithSubCat, Long>{
		

	 @Query(value = " SELECT\n" + 
	 		"      a.*,ifnull(b.min_qty,0) as min_qty_by_item_config,ifnull(b.max_qty,0) as max_qty_by_item_config,ifnull(b.reorder_qty,0) as reorder_qty_by_item_config  FROM (SELECT  m_item.* ,\n" + 
	 		"        m_cat_sub.sub_cat_name \n" + 
	 		"    FROM\n" + 
	 		"        m_item ,\n" + 
	 		"        m_cat_sub \n" + 
	 		"    WHERE\n" + 
	 		"        m_item.id IN(\n" + 
	 		"         :items \n" + 
	 		"        ) \n" + 
	 		"        AND m_cat_sub.sub_cat_id = m_item.item_grp2 \n" + 
	 		"        AND m_item.del_status=0 \n" + 
	 		"        and item_is_used IN(\n" + 
	 		"           :itemIsUsed\n" + 
	 		"        ) \n" + 
	 		"    order by\n" + 
	 		"        m_item.item_grp1,\n" + 
	 		"        m_item.item_grp2,\n" + 
	 		"        m_item.item_name) a \n" + 
	 		"       left join(\n" + 
	 		"SELECT st.item_id,st.min_qty,st.`max_qty`,st.`reorder_qty` FROM m_fr_item_stock st,m_franchisee fr WHERE fr_id=:frId and st.`type`=fr.stock_type\n" + 
	 		" GROUP BY st.item_id)b on b.item_id=a.id", nativeQuery = true)
	    List<ItemWithSubCat> findFrItems(@Param ("itemIsUsed") List<Integer> itemIsUsed,@Param ("items") List<Integer>items,@Param("frId") int frId );
	
	
	// List<ItemWithSubCat> findFrItems(@Param ("itemIsUsed") List<Integer> itemIsUsed,@Param ("items") List<Integer>items,@Param("frId") int frId );
	 
	 
}



