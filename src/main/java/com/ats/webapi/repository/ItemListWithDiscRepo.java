package com.ats.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.ItemListWithDisc;
import com.ats.webapi.model.ItemReport;
import com.ats.webapi.model.ItemReportDetail;

public interface ItemListWithDiscRepo extends JpaRepository<ItemListWithDisc, Integer> {

	@Query(value = " select m_item.id,\n"
			+ " coalesce((select\n"
			+ "        disc_per \n"
			+ "    from\n"
			+ "        m_fr_discount \n"
			+ "    where\n"
			+ "        is_active=1 \n"
			+ "        and del_status=0  \n"
			+ "        and FIND_IN_SET(m_item.id,item_id) \n"
			+ "        and FIND_IN_SET(:frId,franch_id) \n"
			+ "    order by\n"
			+ "        disc_id desc limit 1),0) as disc_per\n"
			+ "    FROM\n"
			+ "        m_item  \n"
			+ "    WHERE\n"
			+ "        m_item.id IN(:items)  \n"
			+ "        AND m_item.del_status=0  \n"
			+ "    ", nativeQuery = true)  
	List<ItemListWithDisc> findItemListWithDisc(@Param("items")List<Integer> items, @Param("frId")String frId);
}
