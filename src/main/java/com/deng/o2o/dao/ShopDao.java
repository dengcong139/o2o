package com.deng.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.deng.o2o.entity.Shop;

public interface ShopDao {
	
	/*
	 * 分页查询店铺,可输入的条件有:店铺名(模糊查询),店铺状态,店铺类别,区域Idowner
	 * shopCondition查询的条件
	 * rowIndex 从第几行开始取数据
	 * pageSize 返回的条数
	 * */
	List<Shop> queryShopList(@Param("shopCondition") Shop shopCondition,@Param("rowIndex") int rowIndex,
			@Param("pageSize") int pageSize);
	/*
	 * 返回queryList总数
	 * */
	int queryShopCount(@Param("shopCondition") Shop shopCondition);
	/*
	 * 通过shopId查询店铺
	 * */
	Shop queryByShopId(Long shopId);
	/*
	 * 新增店铺
	 */
	int insertShop(Shop shop);
	//int insertShop(ShopRegisterVo shopRegisterVo);
	/*
	 * 更新店铺
	 * 
	 * */
	int updateShop(Shop shop);
}
