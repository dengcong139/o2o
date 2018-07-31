package com.deng.o2o.dao;

import java.util.List;

import com.deng.o2o.entity.ProductImg;

public interface ProductImgDao {
	/**
	 * 列出某个商品的详情图列表
	 * 
	 * @param productId
	 * @return
	 */
	List<ProductImg> queryProductImgList(long productId);
	/*
	 * 批量添加图片
	 * */
	int batchInsertProductImg(List<ProductImg> productImgList);
	/**
	 * 删除指定商品下的所有详情图
	 * 
	 * @param productId
	 * @return
	 */
	int deleteProductImgByProductId(long productId);
}
