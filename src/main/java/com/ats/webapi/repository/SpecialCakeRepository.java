package com.ats.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.SpecialCake;

public interface SpecialCakeRepository extends JpaRepository<SpecialCake, Integer>{

	public SpecialCake save(SpecialCake specialcake);
	//@Query(value="select * from m_sp_cake where m_sp_cake.sp_id=:spId",nativeQuery=true)
	public SpecialCake findOne(int spId);
	//@Query(value="select * from m_sp_cake where m_sp_cake.del_status=:delStatus",nativeQuery=true)
	public List<SpecialCake> findByDelStatus(int i);
	@Query(value="SELECT\n" + 
			"   m_sp_cake.sp_id,\n" + 
			"    `sp_code`,\n" + 
			"    `sp_name`,\n" + 
			"    `sp_type`,\n" + 
			"    `sp_min_wt`,\n" + 
			"    `sp_max_wt`,\n" + 
			"    `sp_book_b4`,\n" + 
			"    `spr_id`,\n" + 
			"    `sp_image`,\n" + 
			"    `sp_tax1`,\n" + 
			"    `sp_tax2`,\n" + 
			"    `sp_tax3`,\n" + 
			"    `spe_id_list`,\n" + 
			"    `erp_link_code`,\n" + 
			"    `is_used`,\n" + 
			"    `sp_pho_upload`,\n" + 
			"    `time_two_appli`,\n" + 
			"  m_sp_cake.del_status,\n" + 
			"    `base_code`,\n" + 
			"    `sp_desc`,\n" + 
			"    m_spcake_sup.cut_section AS order_qty,\n" + 
			"    `order_discount`,\n" + 
			"    `is_cust_choice_ck`,\n" + 
			"    `is_addon_rate_appli`,\n" + 
			"    `mrp_rate1`,\n" + 
			"    `mrp_rate2`,\n" + 
			"    `mrp_rate3`,\n" + 
			"    `sp_rate1`,\n" + 
			"    `sp_rate2`,\n" + 
			"    `sp_rate3`,\n" + 
			"    `is_slot_used`,\n" + 
			"    `no_of_chars`\n" + 
			"FROM\n" + 
			"    `m_sp_cake`,\n" + 
			"    m_spcake_sup\n" + 
			"WHERE\n" + 
			"    m_sp_cake.del_status=:delStatus  AND m_sp_cake.sp_id=m_spcake_sup.sp_id  order by sp_name Asc",nativeQuery=true)
	public List<SpecialCake> findByDelStatusOrderBySpNameAsc(@Param("delStatus") int delStatus);
	
	//@Query(value="select * from m_sp_cake where m_sp_cake.sp_id IN(:spId)",nativeQuery=true)
	public List<SpecialCake> findBySpIdIn(List<Integer> spId);
	
	//@Query(value="select * from m_sp_cake where m_sp_cake.del_status=:delStatus and m_sp_cake.sp_id IN(:itemids)",nativeQuery=true)
	public List<SpecialCake> findByDelStatusAndSpIdIn(int i, List<Integer> itemids);
	
	//@Query(value="select * from m_sp_cake where m_sp_cake.del_status=:delStatus order by sp_code asc",nativeQuery=true)
	public List<SpecialCake> findByDelStatusOrderBySpCodeAsc(int i);
	
	@Query(value="SELECT * FROM m_sp_cake WHERE spe_id_list =:shapeId",nativeQuery=true)
	public List<SpecialCake>  findCakeByShapeId(@Param("shapeId") String shapeId);
	
	@Query(value="SELECT\n" + 
			"    *\n" + 
			"FROM\n" + 
			"    m_sp_cake\n" + 
			"WHERE\n" + 
			"    FIND_IN_SET(\n" + 
			"        m_sp_cake.sp_id,\n" + 
			"        (\n" + 
			"        SELECT\n" + 
			"            GROUP_CONCAT(m_fr_configure.item_show)\n" + 
			"        FROM\n" + 
			"            `m_fr_menu_configure`,\n" + 
			"            m_fr_configure\n" + 
			"        WHERE\n" + 
			"            m_fr_menu_configure.fr_id =:frId AND m_fr_configure.menu_id = m_fr_menu_configure.menu_id  AND m_sp_cake.del_status=0  AND m_sp_cake.is_used=1\n" + 
			"    )\n" + 
			"    )",nativeQuery=true)
	public List<SpecialCake> getSpcakeByFrId(@Param("frId") int frId);
	
	
	

	
	
	

}
