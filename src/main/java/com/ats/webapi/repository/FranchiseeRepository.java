package com.ats.webapi.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ats.webapi.model.Franchise;
import com.ats.webapi.model.Franchisee;
import com.ats.webapi.model.User;

@Repository("franchiseeRepository")
public interface FranchiseeRepository extends JpaRepository<Franchisee, Integer> {
	 
		public Franchisee save(Franchisee franchisee);

		public Franchisee findOne(int frId);

		//public Franchisee findByFrCode(String frCode);

		public List<Franchisee> findAllByDelStatus(int i);
		

		@Modifying
		@Transactional
		@Query("Update Franchisee  SET fr_password=:adminPwd WHERE fr_id=:frId")
		public int updateAdminPwd(@Param("frId")int frId,@Param("adminPwd")String adminPwd);

		@Query(value="select MAX(fr_id)+1 from m_franchisee",nativeQuery=true)
		public int getUnigueFrCode();

		public List<Franchisee> findAllByDelStatusOrderByFrNameAsc(int i);

		public List<Franchisee> findByDelStatusAndFrIdIn(int i, List<Integer> frids);

		public Franchisee findByfrCodeAndDelStatus(String frCode, int del);
		
		@Modifying
		@Transactional
		@Query("Update Franchisee  SET fr_password=:newPass WHERE fr_id=:frId")
		public int changeOPSPassword(@Param("frId")int frId,@Param("newPass")String newPass);
		@Query(value="SELECT * FROM `m_franchisee` ORDER BY fr_name ASC",nativeQuery=true)
		public List<Franchisee> findAllFranchisee();
		
		
		
		
		//Akhilesh 2021-02-24  Add Vehicle To  Multiple Franchisee 
		@Modifying
		@Transactional
		@Query(value="Update Franchisee  SET frKg1=:vehicleNo WHERE fr_id IN:frIds")
		int AddVehiceleNoToMultiFr(@Param("vehicleNo") int vehicleNo,@Param("frIds") List<String>  frIds);
		
		
		
		
		//Akhilesh 2021-03-03  Get Franchisee By Route Id
		@Query(value="SELECT * FROM m_franchisee WHERE fr_route_id=:routeId AND del_status=0",nativeQuery=true)
				List<Franchisee> getFranchiseeByRouteId(@Param("routeId") int routeId);
		
		
		
		@Query(value="SELECT DISTINCT fr_route_id FROM `m_franchisee` WHERE del_status=0",nativeQuery=true)
		public List<Integer> getfrRouteIds();

		@Query(value="SELECT\n" + 
				"    fr.*\n" + 
				"FROM\n" + 
				"    m_fr_menu_configure m,\n" + 
				"    m_franchisee fr\n" + 
				"WHERE\n" + 
				"    m.is_del=0 AND\n" + 
				"    m.menu_id=:menuId AND\n" + 
				"    m.fr_id=fr.fr_id",nativeQuery=true)
		public List<Franchisee> getFrListyMenuId(@Param("menuId") int menuId);
		
	}