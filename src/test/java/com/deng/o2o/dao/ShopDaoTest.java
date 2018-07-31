package com.deng.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.deng.o2o.BaseTest;
import com.deng.o2o.entity.Area;
import com.deng.o2o.entity.PersonInfo;
import com.deng.o2o.entity.Shop;
import com.deng.o2o.entity.ShopCategory;

public class ShopDaoTest extends BaseTest{

		@Autowired 
		private ShopDao shopDao;
		
		@Test
		
		public void testQueryShopList() {
			Shop shopCondition =new Shop();
			PersonInfo owner=new PersonInfo();
			owner.setUserId(1L);
			shopCondition.setOwner(owner);
			List<Shop> shopList = shopDao.queryShopList(shopCondition, 0, 5);
			System.out.println(shopList.size());
		}
		
		@Test
		public void testQueryShopListAndCount() {
			Shop shopCondition =new Shop();
			PersonInfo owner=new PersonInfo();
			owner.setUserId(1L);
			shopCondition.setOwner(owner);
			List<Shop> shopList = shopDao.queryShopList(shopCondition, 0, 5);
			int count = shopDao.queryShopCount(shopCondition);
			System.out.println("店铺的总数为:" + count);
			//测试shopCategoryId为3的店铺列表
			ShopCategory shopCategory=new ShopCategory();
			shopCategory.setShopCategoryId(2L);
			//将shopcayegory对象添加到Shop对象中
			shopCondition.setShopCategory(shopCategory);
			//分页查询新店铺的列表
			List<Shop> shopList2 = shopDao.queryShopList(shopCondition, 0, 3);
			System.out.println("新店铺列表的大小为:" + shopList2.size());
			//新店铺的总数
			int count2 = shopDao.queryShopCount(shopCondition);
			System.out.println("新店铺的总数为:" + count2);
		}
		@Test
		@Ignore
		public void testQueryByShopId() {
			long shopId=1;
			Shop shop = shopDao.queryByShopId(shopId);
			System.out.println("araeId:" + shop.getArea().getAreaId());
			System.out.println("areaName:" + shop.getArea().getAreaName());
		}
		@Test
		@Ignore
		public  void testInsertShop() {
			Shop shop=new Shop();
			PersonInfo owner=new PersonInfo();
			Area  area=new Area();
			ShopCategory shopCategory=new ShopCategory();
			owner.setUserId(1L);
			area.setAreaId(2);
			shopCategory.setShopCategoryId(1L);
			shop.setOwner(owner);
			shop.setArea(area);
			shop.setShopCategory(shopCategory);
			shop.setShopName("测试的店铺");
			shop.setShopDesc("test");
			shop.setShopAddr("test");
			shop.setPhone("test");
			shop.setEnableStatus(1);
			
			int i = shopDao.insertShop(shop);
			assertEquals(1,i);
			System.out.println(shop);
		}
		
		@Test
		@Ignore
		public  void testUpdateShop() {
			Shop shop=new Shop();
			shop.setShopId(1L);
			shop.setShopDesc("测试描述2");
			shop.setShopAddr("测试地址2");
			shop.setLastEditTime(new Date());
			int i = shopDao.updateShop(shop);
			assertEquals(1,i);
		}
		
		@Test
		public void testFQueryShopListAndCount() {
			Shop shopCondition=new Shop();
			ShopCategory childCategory=new ShopCategory();
			ShopCategory parentCategory=new ShopCategory();
			parentCategory.setShopCategoryId(3L);
			childCategory.setParent(parentCategory);
			shopCondition.setShopCategory(childCategory);
			List<Shop> shopList = shopDao.queryShopList(shopCondition, 0, 5);
			int count = shopDao.queryShopCount(shopCondition);
			System.out.println("店铺列表的大小为:" + shopList.size());
			System.out.println("店铺总数为:" + count);
		}
		
		
}
