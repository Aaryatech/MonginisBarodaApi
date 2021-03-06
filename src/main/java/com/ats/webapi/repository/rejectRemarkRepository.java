package com.ats.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ats.webapi.model.rejectRemark;

public interface rejectRemarkRepository extends JpaRepository<rejectRemark, Integer> {

@Query(value="SELECT * FROM reject_remark WHERE del_status=0",nativeQuery=true)	
List<rejectRemark> getAllRejectRemarksByDelStatus();

}
