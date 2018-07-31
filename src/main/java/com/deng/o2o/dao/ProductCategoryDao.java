package com.deng.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.deng.o2o.entity.ProductCategory;

public interface ProductCategoryDao {
	/*
	 * 通过shop id查询店铺类别
	 * */
	
	List<ProductCategory> queryProductCategoryList(Long shopId);
	/*
	 * 批量新增产品类别
	 * */
	int batchInsertProductCategory(List<ProductCategory> productCategoryList);
	/*
	 * 删除指定商品类别
	 * */
	int deleteProductCategory(@Param("productCategoryId") long productCategoryId,@Param("shopId") long shopId);
}
