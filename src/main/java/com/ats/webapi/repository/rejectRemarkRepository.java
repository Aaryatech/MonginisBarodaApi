package com.ats.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ats.webapi.model.rejectRemark;

public interface rejectRemarkRepository extends JpaRepository<rejectRemark, Integer> {

@Query(value="SELECT * FROM reject_remark WHERE del_status=0",nativeQuery=true)	
List<rejectRemark> getAllRejectRemarksByDelStatus();

@Query(value="SELECT * FROM reject_remark WHERE reject_id =:rejectId",nativeQuery=true)	
  rejectRemark getAllRejectRemarkById(@Param("rejectId") Integer rejectId);

rejectRemark save(rejectRemark map);

@Transactional
	@Modifying
	@Query(value="UPDATE reject_remark r SET r.del_status=1  WHERE r.reject_id =:rejectId",nativeQuery=true)
	public int deleteItems(@Param("rejectId") Integer rejectId);
}
