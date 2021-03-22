package com.ats.webapi.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ats.webapi.model.StockType;

public interface StockTypeRepository extends JpaRepository<StockType, Integer> {

	@Query(value="SELECT * FROM m_stock_type WHERE del_status=0",nativeQuery=true)
	List<StockType> findAllStockTypesBydelStatus();
	
	
	
	
}
