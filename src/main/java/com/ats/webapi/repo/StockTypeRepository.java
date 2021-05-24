package com.ats.webapi.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ats.webapi.model.StockType;

public interface StockTypeRepository extends JpaRepository<StockType, Integer> {

	@Query(value="SELECT * FROM m_stock_type WHERE del_status=0",nativeQuery=true)
	List<StockType> findAllStockTypesBydelStatus();
	
	@Query(value="SELECT\n" + 
			"    `id`,\n" + 
			"    `stock_type_name`,\n" + 
			"    `stock_type_desc`,\n" + 
			"    `type`,\n" + 
			"    `ex_int1`,\n" + 
			"    `ex_int2`,\n" + 
			"    `ex_var1`,\n" + 
			"   GROUP_CONCAT(m_franchisee.fr_name ) AS `ex_var2`,\n" + 
			"    m_stock_type.del_status\n" + 
			"FROM\n" + 
			"    `m_stock_type`,\n" + 
			"    m_franchisee\n" + 
			"WHERE\n" + 
			"  m_stock_type.del_status=0 AND m_franchisee.stock_type=m_stock_type.id GROUP BY m_franchisee.stock_type",nativeQuery=true)
	List<StockType> findAllStockTypesBydelStatusWithFrname();
	
	
	@Query(value="SELECT * FROM m_stock_type WHERE del_status=0 AND id IN (:sTypeIds)",nativeQuery=true)
	List<StockType> findAllStockTypesByIndelStatus(@Param("sTypeIds") List<String>  sTypeIds);
	
	
	
	StockType save(StockType stock);

	@Query(value="SELECT * FROM m_stock_type WHERE id =:id",nativeQuery=true)	
    StockType getAllStockTypeById(@Param("id") Integer id);
	
	@Transactional
	@Modifying
	@Query(value="UPDATE m_stock_type s SET s.del_status=1  WHERE s.id =:rejectId",nativeQuery=true)
	public int deleteItems(@Param("rejectId") Integer rejectId);
	
	
	@Transactional
	@Modifying
	@Query(value="UPDATE m_stock_type s SET s.del_status=1  WHERE s.id IN (:stckIds)",nativeQuery=true)
	public int deleteMultipleStock(@Param("stckIds") List<String> stckIds);
	
	
	
	
}
