package com.ats.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ats.webapi.model.AllMenus;
import com.ats.webapi.model.SubCategory;
@Repository
public interface MainMenuConfigurationRepository extends JpaRepository<AllMenus, Integer> {
	
	public AllMenus save(AllMenus  allMenus);

	public List<AllMenus> findByDelStatusOrderByMenuTitleAsc(int i);
	
	@Query(value="SELECT m_fr_menu_show.* from m_fr_menu_show WHERE m_fr_menu_show.cat_id=:catId AND m_fr_menu_show.del_status=:i",nativeQuery=true)
	public List<AllMenus> findByMainCatId(@Param("catId") int catId, @Param("i")int i);

	
	@Query(value="select menu_id from m_fr_menu_show where cat_id=:catId",nativeQuery=true)
	public List<Integer> findMenuIdByMainCatId(@Param("catId")int catId);

	@Query(value="SELECT m_fr_menu_show.* from m_fr_menu_show WHERE  m_fr_menu_show.del_status=0 and m_fr_menu_show.menu_id NOT IN(select menu_id from m_fr_configure where is_del=0)",nativeQuery=true)
	public List<AllMenus> getAllNonConfMenus();

	@Query(value="select * from m_fr_menu_show where menu_id in (:menuIds)",nativeQuery=true)
	public List<AllMenus> findByMenuIdIn(@Param("menuIds") String[] menuIds);

	
	//Sachin 11-03-2021
		@Query(value="SELECT m_fr_menu_show.* from m_fr_menu_show WHERE m_fr_menu_show.menu_id IN(:menuIds) AND m_fr_menu_show.del_status=0",nativeQuery=true)
		public List<AllMenus> findByMenuIdIn(@Param("menuIds") List<Integer> menuIds);
		
		//Sachin 12-03-2021 25-01-2021 For Menu Id in Manual Order Page Impl At Sachin Work Controller RestAPI
		@Query(value=" SELECT m_fr_menu_show.* from m_fr_menu_show,m_fr_menu_configure,m_section WHERE "
				+ " m_fr_menu_show.menu_id=m_fr_menu_configure.menu_id and m_fr_menu_configure.fr_id=:frId "
				+ "and m_fr_menu_configure.menu_id=m_fr_menu_show.menu_id and FIND_IN_SET(m_fr_menu_configure.menu_id,m_section.menu_ids) "
				+ " AND m_fr_menu_show.del_status=0 and m_section.section_id=:sectionId  ",nativeQuery=true)
		public List<AllMenus> findByFrIdAndSectionId(@Param("sectionId") int sectionId,@Param("frId") int frId);
		
		
		@Query(value="SELECT * FROM m_fr_menu_show WHERE is_same_day_applicable=:isSameDayAppl AND del_status=0",nativeQuery=true)
		public List<AllMenus>  getMenusByIsSameDayAppl(@Param("isSameDayAppl") int isSameDayAppl );

		@Query(value="SELECT\n" + 
				"        m_fr_menu_show.* \n" + 
				"    from\n" + 
				"        m_fr_menu_show,\n" + 
				"        m_section\n" + 
				"    WHERE\n" + 
				"        m_fr_menu_show.cat_id=:catId AND\n" + 
				"        Find_IN_SET(m_fr_menu_show.menu_id, m_section.menu_ids)\n" + 
				"        AND m_fr_menu_show.del_status=0\n" + 
				"        AND m_section.section_id=:sectionId\n" + 
				"        ",nativeQuery=true)
		public List<AllMenus> findMenuByCatIdAndSectnId(@Param("catId") int catId, @Param("sectionId") int sectionId);
		
		//Sachin 23-03-2021
		public List<AllMenus> findByDelStatus(int delStatus);
		
		
		


}
