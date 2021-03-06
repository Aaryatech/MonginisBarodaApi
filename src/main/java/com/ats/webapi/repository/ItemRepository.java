package com.ats.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ats.webapi.model.Info;
import com.ats.webapi.model.Item;
import com.ats.webapi.model.ItemIdOnly;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

	public Item save(Item item);

	public Item findOne(int id);

	public List<Item> findByItemGrp1AndDelStatusOrderByItemGrp2Asc(String itemGrp1, int i);

	@Query(value = "\n"
			+ "select i.id,i.item_id,i.item_name as item_name,i.item_grp1,i.item_grp2,i.item_grp3,i.item_rate1,i.item_rate2,i.item_rate3,i.item_mrp1,i.item_mrp2,i.item_mrp3,s.item_hsncd AS item_image,i.item_tax1,i.item_tax2,i.item_tax3,i.item_is_used,i.item_sort_id,i.grn_two,i.del_status,i.min_qty,i.item_shelf_life from m_item i,m_item_sup s where s.item_id=i.id and i.id IN (:itemList) AND i.del_status=0", nativeQuery = true)
	public List<Item> findByDelStatusAndItemIdIn(@Param("itemList") List<Integer> itemList);

	public List<Item> findByDelStatusOrderByItemGrp2(int i);// changed to order by subcatId 21/Apr

	@Query(value = "select MAX(CAST(SUBSTRING(item_id,1,LENGTH(item_id)-0) AS SIGNED))+1  from m_item\n"
			+ "", nativeQuery = true)
	public int findMaxId();

	public List<Item> findByItemGrp1AndDelStatusOrderByItemGrp1AscItemGrp2AscItemNameAsc(String itemGrp1, int i);

//coalesce((select m_item_sup.short_name from m_item_sup where m_item_sup.item_id=i.id),0) as
	// Sachin 25 FEB
	@Query(value = "SELECT m_item.* FROM m_item WHERE  FIND_IN_SET(m_item.id ,(SELECT "
			+ "        m_fr_configure.item_show " + "        FROM " + "        m_fr_configure     WHERE "
			+ "        m_fr_configure.cat_id=:catId AND m_fr_configure.is_del=0 "
			+ "        AND m_fr_configure.setting_id in ( " + "            select " + "                menu_id  "
			+ "            from " + "                m_fr_menu_configure " + "            where "
			+ "                fr_id=:frId" + "        ))  )", nativeQuery = true)
	public List<Item> getOtherItemsForFr(@Param("frId") int frId, @Param("catId") int catId);

	//
	@Query(value = "select i.id,i.item_id,i.item_grp1,i.item_grp2,i.item_grp3,i.item_rate1,i.item_rate2,"
			+ "i.item_rate3,i.item_mrp1,i.item_mrp2,i.item_mrp3,i.item_image,i.item_tax1,i.item_tax2,"
			+ "i.item_tax3,i.item_is_used,i.item_sort_id,i.grn_two,i.del_status,i.min_qty,i.item_shelf_life "
			+ " ,coalesce((select m_item_sup.short_name from m_item_sup where m_item_sup.item_id=i.id),0) "
			+ "as item_name from m_item i  where i.del_status=:delStatus order by i.item_grp2 asc ,i.item_sort_id asc ", nativeQuery = true)
	public List<Item> findByDelStatusOrderByItemGrp2AscItemSortIdAsc(@Param("delStatus") int i);

	@Query(value = "select i.id,i.item_id,i.item_grp1,i.item_grp2,i.item_grp3,i.item_rate1,i.item_rate2,"
			+ "i.item_rate3,i.item_mrp1,i.item_mrp2,i.item_mrp3,i.item_image,i.item_tax1,i.item_tax2,"
			+ "i.item_tax3,i.item_is_used,i.item_sort_id,i.grn_two,i.del_status,i.min_qty,i.item_shelf_life "
			+ " ,i.item_name from m_item i  where i.del_status=:delStatus order by i.item_grp1, i.item_grp2 asc ", nativeQuery = true)
	public List<Item> findByDelStatusOrderByItemGrp2AscItemSortIdAsc1(@Param("delStatus") int i);

	
	@Query(value = "select * from m_item where m_item.id IN (Select m_item_sup.item_id from m_item_sup where m_item_sup.is_allow_bday=:isAllowBday) AND m_item.del_status=:delStatus", nativeQuery = true)
	public List<Item> findByIsAllowBirthayAndDelStatus(@Param("isAllowBday") int isAllowBday,
			@Param("delStatus") int delStatus);

	@Query(value = "select m_item.* from m_item where m_item.del_status=0 And m_item.item_grp1=:itemGrp1 And m_item.id not in(select m_item_sup.item_id from m_item_sup where m_item_sup.del_status=0) order by m_item.item_name ", nativeQuery = true)
	public List<Item> findByItemGrp1(@Param("itemGrp1") String itemGrp1);

	@Query(value = "select * from m_item where m_item.id IN (:itemList) Order By item_grp1,item_grp2,item_name", nativeQuery = true)
	public List<Item> findAllItems(@Param("itemList") List<Integer> itemList);

	/*
	 * public List<Item>
	 * findByItemGrp1InAndDelStatusOrderByItemGrp2AscItemSortIdAsc(List<String>
	 * catIdList, int i);
	 */
	
	
	//Akhilesh 2021-03-22 For Stock Type Configuration 
	public List<Item> findByItemGrp2AndDelStatusOrderByItemGrp2AscItemNameAsc(String subCatId, int delStatus);

	
	//Akhilesh 2021-03-22 For Stock Type Configuration 
		@Query(value="SELECT * FROM  m_item WHERE item_grp2 IN(:subCatId) AND del_status=:delStatus ",nativeQuery=true)
		public List<Item> findInSubcat(@Param("subCatId") List<String> subCatId,@Param("delStatus") int delStatus);
	
	
	
	public List<Item> findByIdIn(List<String> id);

	@Transactional
	@Modifying
	@Query("UPDATE Item i SET i.delStatus=1  WHERE i.id IN (:idList)")
	public int deleteItems(@Param("idList") List<Integer> id);

	@Transactional
	@Modifying
	@Query("UPDATE Item i SET i.itemIsUsed=4  WHERE i.id IN (:idList)")
	public int inactivateItems(@Param("idList") List<Integer> id);

	@Transactional
	@Modifying
	@Query("UPDATE Item i SET itemTax1=:itemTax1,itemTax2=:itemTax2,itemTax3=:itemTax3  WHERE i.id=:id")
	public int updateItemHsnAndPerInItem(@Param("id") Integer id, @Param("itemTax1") double itemTax1,
			@Param("itemTax2") double itemTax2, @Param("itemTax3") double itemTax3);

	public List<Item> findByDelStatusOrderByItemGrp1AscItemGrp2AscItemNameAsc(int i);
	
	
	@Query(value="SELECT\n" + 
			"    m_item.id,\n" + 
			"    m_item.item_id,\n" + 
			"   CONCAT(m_item.item_name,'(',m_item_sup.item_uom,')') AS item_name,\n" + 
			"    `item_grp1`,\n" + 
			"    `item_grp2`,\n" + 
			"    `item_grp3`,\n" + 
			"    `item_rate1`,\n" + 
			"    `item_rate2`,\n" + 
			"    `item_rate3`,\n" + 
			"    `item_mrp1`,\n" + 
			"    `item_mrp2`,\n" + 
			"    `item_mrp3`,\n" + 
			"      m_item_sup.item_hsncd AS item_image,\n" + 
			"    `item_tax1`,\n" + 
			"    `item_tax2`,\n" + 
			"    `item_tax3`,\n" + 
			"    `item_is_used`,\n" + 
			"    `item_sort_id`,\n" + 
			"    `grn_two`,\n" + 
			"    m_item.del_status,\n" + 
			"    `min_qty`,\n" + 
			"    `item_shelf_life`\n" + 
			"FROM\n" + 
			"    `m_item`,\n" + 
			"    m_item_sup\n" + 
			"WHERE\n" + 
			"  m_item.del_status=0  AND  m_item_sup.item_id=m_item.id \n" + 
			" ORDER BY\n" + 
			" m_item.item_grp1 ASC,m_item.item_grp2 ASC ,m_item.item_name ASC\n" + 
			" \n" + 
			"  ",nativeQuery=true)
	public List<Item> findByDelStatusOrderByItemGrp1AscItemGrp2AscItemNameAsc();

	public List<Item> findByItemGrp1InAndDelStatusOrderByItemGrp2AscItemNameAsc(List<String> catIdList, int i);

	public List<Item> findByDelStatusAndIdIn(int i, List<Integer> itemids);

	@Query(value = "select item_mrp2 from m_item where del_status=:delStatus group by item_mrp2", nativeQuery = true)
	public List<Double> itemListGroupByStationNo(@Param("delStatus") int delStatus);

//	@Query(value="select * from m_item where m_item.item_grp2 IN (:catIdList) and m_item.del_status=:i",nativeQuery=true)
	public List<Item> findByItemGrp2InAndDelStatusOrderByItemGrp2AscItemNameAsc(List<String> catIdList, int delStatus);

	@Query(value = "\n"
			+ "select i.id,i.item_id,i.item_name,i.item_grp1,i.item_grp2,i.item_grp3,i.item_rate1,i.item_rate2,i.item_rate3,i.item_mrp1,i.item_mrp2,i.item_mrp3,i.item_image,i.item_tax1,i.item_tax2,i.item_tax3,i.item_is_used,i.item_sort_id,i.grn_two,i.del_status,i.min_qty,i.item_shelf_life from m_item i where  i.id IN (:itemList) AND i.del_status=0  ORDER BY \n" + 
			"        i.item_grp1,i.item_grp2,i.item_name", nativeQuery = true)
	public List<Item> findItemsNameByItemId(@Param("itemList") List<Integer> itemList);
	

	@Query(value = "select id,item_id,item_name,item_grp1,item_grp2,item_grp3,item_rate1,item_rate2,item_rate3,item_mrp1,item_mrp2,item_mrp3,item_image,item_tax1,item_tax2,item_tax3,item_is_used,item_sort_id,grn_two,del_status,min_qty,item_shelf_life  \n" + 
			"			from  \n" + 
			"			(select i.id,i.item_id,i.item_name,i.item_grp1,i.item_grp2,i.item_grp3,i.item_rate1,i.item_rate2,i.item_rate3,i.item_mrp1,i.item_mrp2,i.item_mrp3,s.item_hsncd as item_image,i.item_tax1,i.item_tax2,i.item_tax3,i.item_is_used,i.item_sort_id,i.grn_two,i.del_status,i.min_qty,i.item_shelf_life from m_item i,m_item_sup s where  s.item_id=i.id and i.id IN (:itemList) AND i.del_status=0    \n" + 
			"			UNION ALL  \n" + 
			"			  \n" + 
			"			select i.id,i.item_id,i.item_name,i.item_grp1,i.item_grp2,i.item_grp3,i.item_rate1,i.item_rate2,i.item_rate3,i.item_mrp1,i.item_mrp2,i.item_mrp3,s.item_hsncd as item_image,i.item_tax1,i.item_tax2,i.item_tax3,i.item_is_used,i.item_sort_id,i.grn_two,i.del_status,i.min_qty,i.item_shelf_life from m_item i,m_item_sup s where s.item_id=i.id and   i.del_status=0 and i.item_grp1=:catId and i.item_rate2=:frId) a   ORDER BY a.item_grp1,a.item_grp2,a.item_name", nativeQuery = true)
	public List<Item> getItemsNameByIdWithOtherItem(@Param("itemList") List<Integer> itemList,@Param("catId")int catId,@Param("frId")int frId);


	@Query(value = "select m_item.* from m_item where m_item.del_status=0 and m_item.item_grp1=(select cat_id from t_production_plan_header where production_header_id=:prodHeaderId)  ", nativeQuery = true)
	public List<Item> getItemsByProductionIdCatId(@Param("prodHeaderId")int prodHeaderId);
	
	
	@Query(value = "select m_item.* from m_item where m_item.del_status=0 and m_item.id In(select m_item_detail.item_id from m_item_detail where del_status=0 and m_item_detail.rm_id=:rmId and rm_type=:rmType)",nativeQuery=true)
	public List<Item> getItemsByRmIdAndRmType(@Param("rmId")int rmId,@Param("rmType") int rmType);

	
	@Query(value = "select\n" + 
			"a.*,\n" + 
			"coalesce(b.item_id,0) as item_id\n" + 
			"from ( select\n" + 
			"       id, \n" + 
			"  item_name ,\n" + 
			"  item_grp1 ,\n" + 
			"  item_grp2 ,\n" + 
			"  item_grp3, \n" + 
			"  item_rate1,\n" + 
			"  item_rate2 , \n" + 
			"  item_rate3,\n" + 
			"  item_mrp1 ,\n" + 
			"  item_mrp2 ,\n" + 
			"  item_mrp3 ,\n" + 
			"  item_image , \n" + 
			"  item_tax1 ,\n" + 
			"  item_tax2 ,\n" + 
			"  item_tax3 ,\n" + 
			"  item_is_used ,\n" + 
			"  item_sort_id , \n" + 
			"  grn_two ,\n" + 
			"  del_status,\n" + 
			"  min_qty , \n" + 
			"  item_shelf_life\n" + 
			"    from\n" + 
			"        m_item \n" + 
			"    where\n" + 
			"        m_item.del_status=0 \n" + 
			"        And m_item.item_grp3=:itemGrp3" + 
			"        and m_item.id IN (\n" + 
			"            select\n" + 
			"                d.item_id \n" + 
			"            from\n" + 
			"                t_production_plan_detail d \n" + 
			"            where\n" + 
			"                d.production_header_id=:prodHeaderId\n" + 
			"            group by\n" + 
			"                d.item_id\n" + 
			"        ) \n" + 
			"    order by\n" + 
			"        m_item.item_name \n" + 
			") a LEFT JOIN\n" + 
			"(\n" + 
			"select id,1 as item_id from m_item where FIND_IN_SET(id,(select GROUP_CONCAT(ex_varchar2) from t_req_bom where production_id=:prodHeaderId  and is_manual=0  and from_dept_id=:fromDept and to_dept_id=:toDept and ex_int2=1))  and m_item.del_status=0\n" + 
			") b ON a.id=b.id", nativeQuery = true)
	public List<Item> findByItemGrp3(@Param("itemGrp3") String itemGrp3,@Param("prodHeaderId") int prodHeaderId,@Param("fromDept")int fromDept,@Param("toDept") int toDept);

	
	

	@Query(value="SELECT\n" + 
			"    id,\n" + 
			"    item_id,\n" + 
			"    item_name,\n" + 
			"    item_grp1,\n" + 
			"    item_grp2,\n" + 
			"    item_grp3,\n" + 
			"    0 AS item_rate1  ,\n" + 
			"  0 AS  item_rate2  ,\n" + 
			" 0 AS   item_rate3  ,\n" + 
			"   CASE\n" + 
			"    WHEN :itemMrp=1 THEN item_mrp1   WHEN :itemMrp=2 THEN item_mrp2 "
			+ " WHEN :itemMrp=3 THEN item_mrp3  ELSE 0  END as  item_mrp1 ,\n" + 
			"    item_mrp2,\n" + 
			"    item_mrp3,\n" + 
			"    item_image,\n" + 
			"    item_tax1,\n" + 
			"    item_tax2,\n" + 
			"    item_tax3,\n" + 
			"    item_is_used,\n" + 
			"    item_sort_id,\n" + 
			"    grn_two,\n" + 
			"    del_status,\n" + 
			"    min_qty,\n" + 
			"    item_shelf_life\n" + 
			"FROM\n" + 
			"    m_item\n" + 
			"WHERE\n" + 
			"  m_item.item_grp2 IN (:subCatids)",nativeQuery=true)
	public List<Item> getItemsBySubCatIdWithMrp(@Param("subCatids") List<String> subCatids,@Param("itemMrp") int itemMrp);
	
	
	
	@Query(value="select\n" + 
			"        id,\n" + 
			"        item_id,\n" + 
			"        item_name,\n" + 
			"        item_grp1,\n" + 
			"        item_grp2,\n" + 
			"        item_grp3,\n" + 
			"        item_rate1,\n" + 
			"        item_rate2,\n" + 
			"        item_rate3,\n" + 
			"        item_mrp1,\n" + 
			"        item_mrp2,\n" + 
			"        item_mrp3,\n" + 
			"        ext_var3 as item_image,\n" + 
			"        item_tax1,\n" + 
			"        item_tax2,\n" + 
			"        item_tax3,\n" + 
			"        item_is_used,\n" + 
			"        item_sort_id,\n" + 
			"        grn_two,\n" + 
			"        del_status,\n" + 
			"        min_qty,\n" + 
			"        item_shelf_life \n" + 
			"    from\n" + 
			"        (select\n" + 
			"            i.id,\n" + 
			"            i.item_id,\n" + 
			"            i.item_name,\n" + 
			"            i.item_grp1,\n" + 
			"            i.item_grp2,\n" + 
			"            i.item_grp3,\n" + 
			"            i.item_rate1,\n" + 
			"            i.item_rate2,\n" + 
			"            i.item_rate3,\n" + 
			"            i.item_mrp1,\n" + 
			"            i.item_mrp2,\n" + 
			"            i.item_mrp3,\n" + 
			"            i.item_image,\n" + 
			"            i.item_tax1,\n" + 
			"            i.item_tax2,\n" + 
			"            i.item_tax3,\n" + 
			"            i.item_is_used,\n" + 
			"            i.item_sort_id,\n" + 
			"            i.del_status,\n" + 
			"            i.min_qty,\n" + 
			"            i.item_shelf_life,\n" + 
			"            i.grn_two,\n" + 
			"            s.item_uom as ext_var3 \n" + 
			"        from\n" + 
			"            m_item i,\n" + 
			"            m_item_sup s \n" + 
			"        where\n" + 
			"            s.item_id=i.id \n" + 
			"            AND i.del_status=0 \n" + 
			"        	 and i.item_grp1=:cat and i.item_is_used=1\n" + 
			"        UNION\n" + 
			"        ALL            select\n" + 
			"            i.id,\n" + 
			"            i.item_id,\n" + 
			"            i.item_name,\n" + 
			"            i.item_grp1,\n" + 
			"            i.item_grp2,\n" + 
			"            i.item_grp3,\n" + 
			"            i.item_rate1,\n" + 
			"            i.item_rate2,\n" + 
			"            i.item_rate3,\n" + 
			"            i.item_mrp1,\n" + 
			"            i.item_mrp2,\n" + 
			"            i.item_mrp3,\n" + 
			"            i.item_image,\n" + 
			"            i.item_tax1,\n" + 
			"            i.item_tax2,\n" + 
			"            i.item_tax3,\n" + 
			"            i.item_is_used,\n" + 
			"            i.item_sort_id,\n" + 
			"            i.del_status,\n" + 
			"            i.min_qty,\n" + 
			"            i.item_shelf_life,\n" + 
			"            i.grn_two,\n" + 
			"            s.item_uom as ext_var3 \n" + 
			"        from\n" + 
			"            m_item i,\n" + 
			"            m_item_sup s \n" + 
			"        where\n" + 
			"            s.item_id=i.id \n" + 
			"            and  i.del_status=0 \n" + 
			"            and i.item_grp1=:catId \n" +
			"            and i.item_rate2=:frId\n" + 
			"         	 and i.item_is_used=1 \n" + 
			"    ) a  \n" + 
			"ORDER BY\n" + 
			"    a.item_grp1,\n" + 
			"    a.item_grp2,\n" + 
			"    a.item_sort_id",nativeQuery=true)
	public List<Item> getItemsNameByIdWithOtherItemCateId(@Param("catId")int catId,@Param("frId")int frId,
			@Param("cat") int cat);
	
	@Query(value="select\n" + 
			"        id,\n" + 
			"        item_id,\n" + 
			"        item_name,\n" + 
			"        item_grp1,\n" + 
			"        item_grp2,\n" + 
			"        item_grp3,\n" + 
			"        item_rate1,\n" + 
			"        item_rate2,\n" + 
			"        item_rate3,\n" + 
			"        item_mrp1,\n" + 
			"        item_mrp2,\n" + 
			"        item_mrp3,\n" + 
			"        ext_var3 as item_image,\n" + 
			"        item_tax1,\n" + 
			"        item_tax2,\n" + 
			"        item_tax3,\n" + 
			"        item_is_used,\n" + 
			"        item_sort_id,\n" + 
			"        grn_two,\n" + 
			"        del_status,\n" + 
			"        min_qty,\n" + 
			"        item_shelf_life \n" + 
			"    from\n" + 
			"        (select\n" + 
			"            i.id,\n" + 
			"            i.item_id,\n" + 
			"            i.item_name,\n" + 
			"            i.item_grp1,\n" + 
			"            i.item_grp2,\n" + 
			"            i.item_grp3,\n" + 
			"            i.item_rate1,\n" + 
			"            i.item_rate2,\n" + 
			"            i.item_rate3,\n" + 
			"            i.item_mrp1,\n" + 
			"            i.item_mrp2,\n" + 
			"            i.item_mrp3,\n" + 
			"            i.item_image,\n" + 
			"            i.item_tax1,\n" + 
			"            i.item_tax2,\n" + 
			"            i.item_tax3,\n" + 
			"            i.item_is_used,\n" + 
			"            i.item_sort_id,\n" + 
			"            i.del_status,\n" + 
			"            i.min_qty,\n" + 
			"            i.item_shelf_life,\n" + 
			"            i.grn_two,\n" + 
			"            s.item_uom as ext_var3 \n" + 
			"        from\n" + 
			"            m_item i,\n" + 
			"            m_item_sup s \n" + 
			"        where\n" + 
			"            s.item_id=i.id \n" + 
			"            AND i.del_status=0 \n" + 
			"        	 and i.item_grp2=:cat and i.item_is_used=1\n" + 
			"        UNION\n" + 
			"        ALL            select\n" + 
			"            i.id,\n" + 
			"            i.item_id,\n" + 
			"            i.item_name,\n" + 
			"            i.item_grp1,\n" + 
			"            i.item_grp2,\n" + 
			"            i.item_grp3,\n" + 
			"            i.item_rate1,\n" + 
			"            i.item_rate2,\n" + 
			"            i.item_rate3,\n" + 
			"            i.item_mrp1,\n" + 
			"            i.item_mrp2,\n" + 
			"            i.item_mrp3,\n" + 
			"            i.item_image,\n" + 
			"            i.item_tax1,\n" + 
			"            i.item_tax2,\n" + 
			"            i.item_tax3,\n" + 
			"            i.item_is_used,\n" + 
			"            i.item_sort_id,\n" + 
			"            i.del_status,\n" + 
			"            i.min_qty,\n" + 
			"            i.item_shelf_life,\n" + 
			"            i.grn_two,\n" + 
			"            s.item_uom as ext_var3 \n" + 
			"        from\n" + 
			"            m_item i,\n" + 
			"            m_item_sup s \n" + 
			"        where\n" + 
			"            s.item_id=i.id \n" + 
			"            and  i.del_status=0 \n" + 
			"            and i.item_grp1=:catId \n" +
			"            and i.item_rate2=:frId\n" + 
			"         	 and i.item_is_used=1 \n" + 
			"    ) a  \n" + 
			"ORDER BY\n" + 
			"    a.item_grp1,\n" + 
			"    a.item_grp2,\n" + 
			"    a.item_sort_id",nativeQuery=true)
	public List<Item> getItemsNameByIdWithOtherItemSubCatId(@Param("catId")int catId,@Param("frId")int frId,
			@Param("cat") int cat);
	
	
	@Query(value="SELECT\n" + 
			"    id,\n" + 
			"    item_id,\n" + 
			"    item_name,\n" + 
			"    item_grp1,\n" + 
			"    item_grp2,\n" + 
			"    item_grp3,\n" + 
			"    item_rate1,\n" + 
			"    item_rate2,\n" + 
			"    item_rate3,\n" + 
			"    item_mrp1,\n" + 
			"    item_mrp2,\n" + 
			"    item_mrp3,\n" + 
			"    ext_var3 AS item_image,\n" + 
			"    item_tax1,\n" + 
			"    item_tax2,\n" + 
			"    item_tax3,\n" + 
			"    item_is_used,\n" + 
			"    item_sort_id,\n" + 
			"    grn_two,\n" + 
			"    del_status,\n" + 
			"    min_qty,\n" + 
			"    item_shelf_life\n" + 
			"FROM\n" + 
			"    (\n" + 
			"    SELECT\n" + 
			"        i.id,\n" + 
			"        i.item_id,\n" + 
			"        i.item_name,\n" + 
			"        i.item_grp1,\n" + 
			"        i.item_grp2,\n" + 
			"        i.item_grp3,\n" + 
			"        i.item_rate1,\n" + 
			"        i.item_rate2,\n" + 
			"        i.item_rate3,\n" + 
			"        i.item_mrp1,\n" + 
			"        i.item_mrp2,\n" + 
			"        i.item_mrp3,\n" + 
			"        i.item_image,\n" + 
			"        i.item_tax1,\n" + 
			"        i.item_tax2,\n" + 
			"        i.item_tax3,\n" + 
			"        i.item_is_used,\n" + 
			"        i.item_sort_id,\n" + 
			"        i.del_status,\n" + 
			"        i.min_qty,\n" + 
			"        i.item_shelf_life,\n" + 
			"        i.grn_two,\n" + 
			"        s.item_uom AS ext_var3\n" + 
			"    FROM\n" + 
			"        m_item i,\n" + 
			"        m_item_sup s\n" + 
			"    WHERE\n" + 
			"        s.item_id = i.id AND i.del_status = 0 AND i.item_grp1 =:catId AND i.item_is_used = 1\n" + 
			") a\n" + 
			"ORDER BY\n" + 
			"	a.item_name,\n" + 
			"    a.item_grp2,\n" + 
			"    a.item_sort_id",nativeQuery=true)
	public List<Item> getItemsByCatIdForPos(@Param("catId")int catId);
	
	
	@Query(value="SELECT\n" + 
			"    id,\n" + 
			"    item_id,\n" + 
			"    item_name,\n" + 
			"    item_grp1,\n" + 
			"    item_grp2,\n" + 
			"    item_grp3,\n" + 
			"    item_rate1,\n" + 
			"    item_rate2,\n" + 
			"    item_rate3,\n" + 
			"    item_mrp1,\n" + 
			"    item_mrp2,\n" + 
			"    item_mrp3,\n" + 
			"    ext_var3 AS item_image,\n" + 
			"    item_tax1,\n" + 
			"    item_tax2,\n" + 
			"    item_tax3,\n" + 
			"    item_is_used,\n" + 
			"    item_sort_id,\n" + 
			"    grn_two,\n" + 
			"    del_status,\n" + 
			"    min_qty,\n" + 
			"    item_shelf_life\n" + 
			"FROM\n" + 
			"    (\n" + 
			"    SELECT\n" + 
			"        i.id,\n" + 
			"        i.item_id,\n" + 
			"        i.item_name,\n" + 
			"        i.item_grp1,\n" + 
			"        i.item_grp2,\n" + 
			"        i.item_grp3,\n" + 
			"        i.item_rate1,\n" + 
			"        i.item_rate2,\n" + 
			"        i.item_rate3,\n" + 
			"        i.item_mrp1,\n" + 
			"        i.item_mrp2,\n" + 
			"        i.item_mrp3,\n" + 
			"        i.item_image,\n" + 
			"        i.item_tax1,\n" + 
			"        i.item_tax2,\n" + 
			"        i.item_tax3,\n" + 
			"        i.item_is_used,\n" + 
			"        i.item_sort_id,\n" + 
			"        i.del_status,\n" + 
			"        i.min_qty,\n" + 
			"        i.item_shelf_life,\n" + 
			"        i.grn_two,\n" + 
			"        s.item_uom AS ext_var3\n" + 
			"    FROM\n" + 
			"        m_item i,\n" + 
			"        m_item_sup s, m_category c\n" + 
			"    WHERE\n" + 
			"        s.item_id = i.id AND i.del_status = 0 AND i.item_is_used = 1 AND i.item_grp1=c.cat_id AND c.is_same_day=0\n" + 
			") a\n" + 
			"ORDER BY\n" + 
			"	a.item_name,\n" + 
			"    a.item_grp2,\n" + 
			"    a.item_sort_id",nativeQuery=true)
	public List<Item> getAllItemsForPos();
	
	
	@Query(value="SELECT DISTINCT item_grp2 FROM `m_item` WHERE del_status=0",nativeQuery=true)
	public List<Integer> getItemAllotedSubCategoryId();

	@Transactional
	@Modifying
	@Query(value="UPDATE m_item SET item_mrp1=:mrp1, item_mrp2=:mrp2, item_mrp3=:mrp3 WHERE id=:itemId",nativeQuery=true)
	public int editItemMrps(@Param("itemId") int itemId, @Param("mrp1") float mrp1, @Param("mrp2") float mrp2, @Param("mrp3") float mrp3);
	
	
	
	@Query(value="select\n" + 
			"        m_item.* \n" + 
			"    from\n" + 
			"        m_item,\n" + 
			"        m_cat_sub\n" + 
			"    where\n" + 
			"        item_grp2 IN (:subCatId) \n" + 
			"        AND m_item.del_status = 0  AND\n" + 
			"        m_cat_sub.sub_cat_id=m_item.item_grp2\n" + 
			"    Order By\n" + 
			"        item_grp1,\n" + 
			"        m_cat_sub.sub_cat_id,\n" + 
			"        m_cat_sub.seq_no,\n" + 
			"        item_name",nativeQuery=true)
	public List<Item> getAllItemsFinishGoodsStock(@Param("subCatId") List<String> subCatId);
	//@Query(value = "select * from m_item where item_grp2 IN (:subCatId) AND del_status = 0 Order By item_grp1, item_grp2, item_sort_id, item_name", nativeQuery = true)

	public List<Item> findByItemGrp1AndDelStatus(String catId, int i);
	
	
	@Query(value="SELECT\n" + 
			"    i.*\n" + 
			"FROM\n" + 
			"	m_item i,\n" + 
			"    m_item_sup s\n" + 
			"WHERE\n" + 
			"    i.del_status=0 AND\n" + 
			"    i.id=s.item_id AND\n" + 
			"    s.is_gate_sale=:frId AND\n" + 
			"    i.item_grp1=:subCatId",nativeQuery=true)
	public List<Item> findByItemGrp1AndItemRate2AndDelStatus(@Param("subCatId") String catId,@Param("frId") double frId);
	
	
	//Items for Menu ID In
	//SACHIN 14-04-2021
	
			
			@Query(value=" SELECT DISTINCT m_item.* FROM m_item,m_fr_configure WHERE m_item.del_status=0 AND "
					+ " find_in_set(m_item.id,m_fr_configure.item_show) and m_fr_configure.is_del=0 and menu_id IN (:menuIdList) " + 
					"	",nativeQuery=true)
			public List<Item> getItemsConfiguredToMenuIdIn(@Param("menuIdList") List<String> menuIdList);

}
