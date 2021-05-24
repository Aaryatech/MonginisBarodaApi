package com.ats.webapi.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ats.webapi.model.GetConfiguredSpDayCk;

@Repository
public interface GetConfSpDayCakeRepository extends JpaRepository<GetConfiguredSpDayCk, Long>{


	List<GetConfiguredSpDayCk> findAllByDelStatus(int delStatus);
	
	
	@Query(value="SELECT\n" + 
			"    `spday_id`,\n" + 
			"    `fr_id`,\n" + 
			"    m_fr_menu_show.menu_title AS  item_id,\n" + 
			"    `order_from_date`,\n" + 
			"    `order_to_date`,\n" + 
			"      delivery_from_date,\n" + 
			"    delivery_to_date,\n" + 
			"    `spday_name`,\n" + 
			"    `from_time`,\n" + 
			"    `to_time`,\n" + 
			"    m_spday_configure.del_status,\n" + 
			"   m_spday_configure.menu_id,\n" + 
			"    m_spday_configure.cat_id,\n" + 
			"    `m_spday_configure`.`sub_cat_id`\n" + 
			"FROM\n" + 
			"    `m_spday_configure`,\n" + 
			"    m_fr_menu_show\n" + 
			"WHERE\n" + 
			"    m_spday_configure.del_status=0 AND m_spday_configure.menu_id=m_fr_menu_show.menu_id",nativeQuery=true)
	List<GetConfiguredSpDayCk> findAllByDelStatus();

	GetConfiguredSpDayCk findBySpdayId(int spdayId);

    @Query(value="SELECT * from m_spday_configure where :cDate BETWEEN order_from_date AND order_to_date  AND del_status=0 And fr_id Like %:frId% ",nativeQuery=true)
	List<GetConfiguredSpDayCk> findAllSpDayCake(@Param("cDate")java.util.Date cDate,@Param("frId") int frId);

}
