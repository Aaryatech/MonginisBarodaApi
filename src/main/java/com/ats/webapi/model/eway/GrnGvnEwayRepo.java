package com.ats.webapi.model.eway;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface GrnGvnEwayRepo extends JpaRepository<GrnGvnEway, Integer>{

	
	@Transactional
	@Modifying
	@Query("UPDATE GrnGvnEway s SET s.ewayNo =:isTallySync  WHERE s.grnGvnHeaderId=:billNo")
	int updateEwayBillNo(@Param("billNo")int billNo,@Param("isTallySync") long isTallySync);

	
}
