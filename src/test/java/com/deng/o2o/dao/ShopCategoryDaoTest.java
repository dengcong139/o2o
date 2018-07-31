package com.deng.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.deng.o2o.BaseTest;
import com.deng.o2o.entity.ShopCategory;


public class ShopCategoryDaoTest extends BaseTest{
	
	@Autowired
	private ShopCategoryDao shopCategoryDao;
	
	@Test
	public  void testQueryCategory() {
		List<ShopCategory> shopCategoryList = shopCategoryDao.queryShopCategory(new ShopCategory());
		//assertEquals(2, shopCategoryList.size());
		//创建两个shopCategory对象
		ShopCategory testCategory = new ShopCategory();
		ShopCategory parentCategory = new ShopCategory();
		
		parentCategory.setShopCategoryId(1L);//设置上级目录parent_id为1,因为在实体类中上级目录引用为复合变量,所以需要创建两个ShopCategory对象
		testCategory.setParent(parentCategory);
		shopCategoryList = shopCategoryDao.queryShopCategory(testCategory);
		System.out.println(shopCategoryList.get(0));
		assertEquals(1, shopCategoryList.size());//断言parent_id为1的店铺类型

	}
	
	@Test
	public void QueryNull() {
		List<ShopCategory> shopCategoryList = shopCategoryDao.queryShopCategory(null);
		System.out.println(shopCategoryList.size());
	}
}
