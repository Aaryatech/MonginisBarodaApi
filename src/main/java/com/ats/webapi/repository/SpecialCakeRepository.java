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
	//@Query(value="select * from m_sp_cake where m_sp_cake.del_status=:delStatus order by sp_name Asc",nativeQuery=true)
	public List<SpecialCake> findByDelStatusOrderBySpNameAsc(int i);
	
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
			"            m_fr_menu_configure.fr_id =:frId AND m_fr_configure.menu_id = m_fr_menu_configure.menu_id\n" + 
			"    )\n" + 
			"    )",nativeQuery=true)
	public List<SpecialCake> getSpcakeByFrId(@Param("frId") int frId);
	
	
	

}
