package com.deng.o2o.service;

import java.io.InputStream;

import com.deng.o2o.dto.ImageHolder;
import com.deng.o2o.dto.ShopExecution;
import com.deng.o2o.entity.Shop;
import com.deng.o2o.exceptions.ProductCategoryOperationException;

public interface ShopService {
	/*
	 * 根据shopCondition分页返回相应店铺列表
	 * 返回时为ShopExecution,方便整合shop列表和总数一块返回
	 * */
	public ShopExecution getShopList(Shop shopCondition,int pageIndex,int pageSize);
	/*
	 * 通过店铺Id获取店铺信息
	 */
	Shop getByShopId(Long shopId);

	/*
	 * 修改店铺信息
	 */

	ShopExecution modifyShop(Shop shop, ImageHolder thumbnail) throws ProductCategoryOperationException;

	/*
	 * 添加店铺信息,针对店铺的操作返回值结果都为ShopExecution,需要实时收集店铺的信息
	 */
	ShopExecution addShop(Shop shop, ImageHolder thumbnail) throws ProductCategoryOperationException;
	
	
	//ShopExecution addShop(ShopRegisterVo shopRegisterVo, MultipartFile file, String fileName) throws Exception;
}
