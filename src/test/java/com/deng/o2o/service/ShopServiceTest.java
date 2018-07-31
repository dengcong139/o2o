package com.deng.o2o.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.deng.o2o.BaseTest;
import com.deng.o2o.dto.ImageHolder;
import com.deng.o2o.dto.ShopExecution;
import com.deng.o2o.entity.Area;
import com.deng.o2o.entity.PersonInfo;
import com.deng.o2o.entity.Shop;
import com.deng.o2o.entity.ShopCategory;
import com.deng.o2o.enums.ShopStateEnum;
import com.deng.o2o.exceptions.ProductCategoryOperationException;


public class ShopServiceTest extends BaseTest {

		@Autowired
		private  ShopService shopService;
		
		@Test
		public void testGetShopList() {
			Shop shopCondition =new Shop();
			ShopCategory sc=new ShopCategory();
			sc.setShopCategoryId(2L);
			shopCondition.setShopCategory(sc);
			ShopExecution list = shopService.getShopList(shopCondition, 2, 3);
			System.out.println("店铺列表数为:" + list.getShopList().size());
			System.out.println("店铺总数为:" + list.getCount());
		}
		@Test
		public void testModifyShop() throws ProductCategoryOperationException,FileNotFoundException{
			Shop shop =new Shop();
			shop.setShopId(1L);
			shop.setShopName("修改后的名字");
			File shopImg =new File("C:\\Users\\Lenovo\\Pictures\\imgs\\xiaokeji.jpg");
			InputStream is=new FileInputStream(shopImg);
			ImageHolder imageHolder = new ImageHolder("xiaokeji.jpg", is);
			ShopExecution shopExecution = shopService.modifyShop(shop, imageHolder );
			System.out.println("新的图片地址:" + shopExecution.getShop().getShopImg());
		}
		@Test
		public  void testAddShop() throws FileNotFoundException {
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
			shop.setShopName("测试的店铺3");
			shop.setShopDesc("test3");
			shop.setShopAddr("test3");
			shop.setPhone("test3");
			shop.setCreateTime(new Date());
			shop.setEnableStatus(ShopStateEnum.CHECK.getState());
			shop.setAdvice("审核中");
			File shopImg=new File("C:\\Users\\Lenovo\\Pictures\\imgs\\keji.jpg");
			InputStream is=new FileInputStream(shopImg);
			ImageHolder imageHolder = new ImageHolder(shopImg.getName(), is);
			ShopExecution se = shopService.addShop(shop, imageHolder);
			assertEquals(ShopStateEnum.CHECK.getState(), se.getState());
			
		}
		
}
