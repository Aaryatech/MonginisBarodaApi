package com.ats.webapi.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.Scheduler;
import com.ats.webapi.model.User;

public interface SchedulerRepository extends JpaRepository<Scheduler, Integer>  {
	Scheduler save(Scheduler schedular);
	List<Scheduler> findByDelStatus(int schId);
	@Query(value="SELECT * from t_scheduler WHERE  :cDate BETWEEN sch_date AND sch_to_date AND is_active=1 AND del_status=0 order by sch_to_date Asc"			
			,nativeQuery=true)
			List<Scheduler> findLatestNews(@Param("cDate")java.util.Date cDate);
	
	
	@Transactional
	@Modifying
	@Query(value="UPDATE  t_scheduler SET del_status=1 WHERE sch_id IN (:scIds)",nativeQuery=true)
	int DeleteMutiScheduler(@Param("scIds") List<String> scIds);
	
}
