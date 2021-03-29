package com.ats.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.GetReorderByStockType;
import com.ats.webapi.model.GetitemStockConfig;

public interface GetitemStockConfigRepository extends JpaRepository<GetitemStockConfig, Integer> {

	
	//Akhilesh 2021-03-23 
		@Query(value=" select * from m_fr_item_stock where  type IN (:stockType)",nativeQuery=true)
		List<GetitemStockConfig> GetItemStockByType(@Param("stockType")List<String>  stockType);

	
	
	
}
