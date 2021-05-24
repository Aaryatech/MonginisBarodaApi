package com.ats.webapi.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.OrderCounts;

public interface OrderCountsRepository extends JpaRepository<OrderCounts, Integer>{
	/*
	@Query (value=" SELECT COALESCE(sum(t_order.order_qty),0) as total,m_fr_menu_show.menu_id,"
			+ " m_fr_menu_show.menu_title FROM m_fr_menu_show LEFT JOIN t_order "
			+ "ON m_fr_menu_show.menu_id=t_order.menu_id AND "
			+ "production_date =:cDate GROUP BY menu_id" + 
			" ",nativeQuery=true)
	List<OrderCounts> getOrderTotal(@Param("cDate") Date cDate);
	 */
	
    @Query (value="SELECT\n" + 
    		"    SUM(t_order.order_qty) AS total,\n" + 
    		"    m_fr_menu_show.menu_id,\n" + 
    		"    m_fr_configure.setting_type,\n" + 
    		"    CONCAT(\n" + 
    		"        m_fr_menu_show.menu_title,\n" + 
    		"        '|',\n" + 
    		"        m_fr_configure.from_time,\n" + 
    		"        '~',\n" + 
    		"        m_fr_configure.to_time\n" + 
    		"    ) AS menu_title,\n" + 
    		"    m_fr_configure.to_time,\n" + 
    		"    CASE WHEN m_fr_configure.setting_type = 2 THEN m_fr_configure.date WHEN m_fr_configure.setting_type = 3 THEN m_fr_configure.day ELSE 'DAILY'\n" + 
    		"END AS menu_sch\n" + 
    		"FROM\n" + 
    		"    t_order,\n" + 
    		"    m_fr_menu_show,\n" + 
    		"    m_fr_configure\n" + 
    		"WHERE\n" + 
    		"    t_order.production_date = :cDate AND m_fr_menu_show.menu_id = t_order.menu_id AND m_fr_configure.menu_id = t_order.menu_id\n" + 
    		"GROUP BY\n" + 
    		"    menu_id",nativeQuery=true)

	List<OrderCounts> getOrderTotal(@Param("cDate") String cDate);
    
    
    
    @Query (value="SELECT\n" + 
    		"    SUM(t_sp_cake.sp_selected_weight) AS total,\n" + 
    		"    m_fr_menu_show.menu_id,\n" + 
    		"    m_fr_configure.setting_type,\n" + 
    		"    CONCAT(\n" + 
    		"        m_fr_menu_show.menu_title,\n" + 
    		"        '|',\n" + 
    		"        m_fr_configure.from_time,\n" + 
    		"        '~',\n" + 
    		"        m_fr_configure.to_time\n" + 
    		"    ) AS menu_title,\n" + 
    		"    m_fr_configure.to_time,\n" + 
    		"    CASE WHEN m_fr_configure.setting_type = 2 THEN m_fr_configure.date WHEN m_fr_configure.setting_type = 3 THEN m_fr_configure.day ELSE 'DAILY'\n" + 
    		"END AS menu_sch\n" + 
    		"FROM\n" + 
    		"    t_sp_cake,\n" + 
    		"    m_fr_menu_show,\n" + 
    		"    m_fr_configure\n" + 
    		"WHERE\n" + 
    		"    t_sp_cake.sp_prod_date = :cDate AND m_fr_menu_show.menu_id = t_sp_cake.menu_id AND m_fr_configure.menu_id = t_sp_cake.menu_id\n" + 
    		"GROUP BY\n" + 
    		"    menu_id",nativeQuery=true)
    List<OrderCounts> getSpCakeOrderTotal(@Param("cDate") String cDate);
    
    
    
    
    @Query (value="    SELECT\n" + 
    		"        SUM(t_regular_sp_cake.qty) as total,\n" + 
    		"        m_fr_menu_show.menu_id,\n" + 
    		"        m_fr_configure.setting_type,\n" + 
    		"         CONCAT(m_fr_menu_show.menu_title,'|',m_fr_configure.from_time,'~',m_fr_configure.to_time) AS menu_title,\n" + 
    		"      m_fr_configure.to_time,\n" + 
    		"\n" + 
    		"      CASE\n" + 
    		"   	    WHEN m_fr_configure.setting_type=2 THEN  m_fr_configure.date  \n" + 
    		"        \n" + 
    		"        WHEN m_fr_configure.setting_type=3 THEN  m_fr_configure.day \n" + 
    		"       \n" + 
    		"       ELSE 'DAILY'\n" + 
    		"        \n" + 
    		"      END AS menu_sch\n" + 
    		"    FROM\n" + 
    		"        t_regular_sp_cake,\n" + 
    		"        m_fr_menu_show,\n" + 
    		"        m_fr_configure \n" + 
    		"    where\n" + 
    		"        t_regular_sp_cake.rsp_produ_date =:cDate\n" + 
    		"        and  m_fr_menu_show.menu_id=t_regular_sp_cake.menu_id \n" + 
    		"        and  m_fr_configure.menu_id=t_regular_sp_cake.menu_id  \n" + 
    		"    GROUP BY\n" + 
    		"        menu_id  ",nativeQuery=true)
    List<OrderCounts> getBulkOrderTotal(@Param("cDate") String cDate);
	 
	
	
	
}
