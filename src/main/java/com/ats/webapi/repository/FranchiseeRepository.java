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
		
		//Akhilesh 2021-03-18 
		public List<Franchisee> findAll();
		
		
		@Query(value="SELECT\n" + 
				"    `fr_id`,\n" + 
				"    `fr_name`,\n" + 
				"    `fr_code`,\n" + 
				"    `fr_opening_date`,\n" + 
				"    `fr_rate`,\n" + 
				"    `fr_image`,\n" + 
				"    `fr_route_id`,\n" + 
				"    `fr_city`,\n" + 
				"    `kg_1`,\n" + 
				"    `kg_2`,\n" + 
				"    `kg_3`,\n" + 
				"    `kg_4`,\n" + 
				"    `fr_email`,\n" + 
				"    `fr_password`,\n" + 
				"    `fr_mob`,\n" + 
				"    m_stock_type.stock_type_name  AS   fr_rmn_1,\n" + 
				"    `fr_owner`,\n" + 
				"    `fr_rate_cat`,\n" + 
				"    `show_items`,\n" + 
				"    `not_show_items`,\n" + 
				"    `grn_two`,\n" + 
				"    `fr_opening`,\n" + 
				"    `fr_password_key`,\n" + 
				"    m_franchisee.del_status,\n" + 
				"    `is_same_day_applicable`,\n" + 
				"    `owner_birth_date`,\n" + 
				"    `fba_license_date`,\n" + 
				"    `fr_agreement_date`,\n" + 
				"    `fr_gst_type`,\n" + 
				"    `fr_gst_no`,\n" + 
				"    `stock_type`,\n" + 
				"    `fr_address`,\n" + 
				"    `fr_target`,\n" + 
				"    `is_same_state`\n" + 
				"FROM\n" + 
				"    `m_franchisee`,\n" + 
				"    m_stock_type\n" + 
				"WHERE\n" + 
				"m_stock_type.id=m_franchisee.stock_type  ORDER BY\n" + 
				"m_franchisee.fr_id DESC",nativeQuery=true)
		public List<Franchisee> findAllWithStockType();
		
		

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
		
		
		//Akhilesh 2021-02-24  Add Stock Type  To  Multiple Franchisee 
				@Modifying
				@Transactional
				@Query(value="Update Franchisee  SET stock_type=:stId WHERE fr_id IN:frIds")
				int AddStockTypeToMultiFr(@Param("stId") int stId,@Param("frIds") List<String>  frIds);
		
		
		
		
		//Akhilesh 2021-03-03  Get Franchisee By Route Id
		@Query(value="SELECT * FROM m_franchisee WHERE fr_route_id=:routeId AND del_status=0",nativeQuery=true)
				List<Franchisee> getFranchiseeByRouteId(@Param("routeId") int routeId);
		
		
		
		//Akhilesh 2021-05-05  
				@Query(value="SELECT * FROM m_franchisee WHERE fba_license_date BETWEEN :Cdate AND :Tdate AND del_status=0",nativeQuery=true)
						List<Franchisee> getExpFdaLicenceDate(@Param("Cdate") String Cdate,@Param("Tdate") String Tdate);
				
				//Akhilesh 2021-05-05  
				@Query(value="SELECT * FROM m_franchisee WHERE fr_agreement_date BETWEEN :Cdate AND :Tdate AND del_status=0",nativeQuery=true)
						List<Franchisee> getExpAgreementDate(@Param("Cdate") String Cdate,@Param("Tdate") String Tdate);
				
				
				//Akhilesh 2021-05-05  
				@Query(value="SELECT * FROM m_franchisee WHERE DATE_FORMAT(m_franchisee.owner_birth_date, '%m-%d') = DATE_FORMAT(:Cdate, '%m-%d') AND del_status=0",nativeQuery=true)
						List<Franchisee> getOwnerBirthDate(@Param("Cdate") String Cdate);
				
				
				//Akhilesh 2021-05-05  
				@Query(value="SELECT * FROM m_franchisee WHERE DATE_FORMAT(m_franchisee.fr_opening_date, '%m-%d') = DATE_FORMAT(:Cdate, '%m-%d') AND del_status=0",nativeQuery=true)
						List<Franchisee> getShopOpningDate(@Param("Cdate") String Cdate);
				
						 
		
		@Query(value="SELECT DISTINCT fr_route_id FROM `m_franchisee` WHERE del_status=0",nativeQuery=true)
		public List<Integer> getfrRouteIds();
		
		
		@Query(value="SELECT DISTINCT kg_1 FROM `m_franchisee` WHERE del_status=0",nativeQuery=true)
		public List<Integer> getInUseVehicleList();
		
		@Query(value="SELECT DISTINCT stock_type FROM `m_franchisee` WHERE del_status=0",nativeQuery=true)
		public List<Integer> getInUseStockType();
		

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
		
		@Transactional
		@Modifying
		@Query(value="UPDATE m_franchisee SET del_status=1 WHERE fr_id IN(:frIds)",nativeQuery=true)
		public int deleteMultiFrByFrId(@Param("frIds") List<String> frids);
		
		
		
		
		
	}