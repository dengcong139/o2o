package com.deng.o2o.service;


import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.deng.o2o.BaseTest;
import com.deng.o2o.dto.ImageHolder;
import com.deng.o2o.dto.ProductExecution;
import com.deng.o2o.entity.Product;
import com.deng.o2o.entity.ProductCategory;
import com.deng.o2o.entity.Shop;
import com.deng.o2o.enums.ProductStateEnum;
import com.deng.o2o.exceptions.ProductOperationException;
import com.deng.o2o.exceptions.ShopOperationException;

public class ProductServiceTest extends BaseTest{
	@Autowired
	private ProductService productService;
	
	@Test
	public void testAddProduct() throws ProductOperationException, FileNotFoundException{
		//创建shopId为1,且productCategoryId为1的商品实例并给器成员变量赋值
		Product product=new Product();
		Shop shop =new Shop();
		shop.setShopId(14L);
		ProductCategory pc=new ProductCategory();
		pc.setProductCategoryId(1L);
		product.setShop(shop);
		product.setProductCategory(pc);
		product.setProductName("测试商品1");
		product.setProductDesc("测试商品1的详情");
		product.setCreateTime(new Date());
		product.setPriority(20);
		product.setEnableStatus(ProductStateEnum.SUCCESS.getState());
		//创建缩略图文件流
		File thumbnailFile = new File("C:\\Users\\Lenovo\\Pictures\\images\\llll.jpg");
		FileInputStream is = new FileInputStream(thumbnailFile);
		ImageHolder thumbnail = new ImageHolder(thumbnailFile.getName(), is);
		//创建两个商品详情文件流并将他们添加到详情列表中
		File productImg1 = new File("C:\\Users\\Lenovo\\Pictures\\imgs\\keji.jpg");
		FileInputStream is1 = new FileInputStream(productImg1);
		File productImg2 = new File("C:\\Users\\Lenovo\\Pictures\\imgs\\watermark.jpg");
		FileInputStream is2 = new FileInputStream(productImg2);
		ArrayList<ImageHolder> productImgList = new ArrayList<ImageHolder>();
		productImgList.add(new ImageHolder(productImg1.getName(), is1));
		productImgList.add(new ImageHolder(productImg2.getName(), is2));
		//添加商品并验证
		ProductExecution pe = productService.addProduct(product, thumbnail, productImgList);
		assertEquals(ProductStateEnum.SUCCESS.getState(), pe.getState());
	}
	
	@Test
	public void testModifyProduct() throws ShopOperationException, FileNotFoundException{
		//创建shopId为1且productCategoryId为1的商品实例并给其成员变量赋值
		Product product=new Product();
		Shop shop=new Shop();
		shop.setShopId(1L);
		ProductCategory pc=new ProductCategory();
		pc.setProductCategoryId(1L);
		product.setProductId(1L);
		product.setShop(shop);
		product.setProductCategory(pc);
		product.setProductName("柯基柯基");
		product.setProductDesc("宠物");
		//创建缩略图文件
		File thumbnailFile = new File("C:\\Users\\Lenovo\\Pictures\\images\\llll.jpg");
		FileInputStream is = new FileInputStream(thumbnailFile);
		ImageHolder thumbnail = new ImageHolder(thumbnailFile.getName(), is);
		//创建两个商品详情图文件流并将它们添加到详情图列表中
		File productImg1 = new File("C:\\Users\\Lenovo\\Pictures\\images\\beauty.jpg");
		FileInputStream is1 = new FileInputStream(productImg1);
		File productImg2 = new File("C:\\Users\\Lenovo\\Pictures\\images\\mass.jpg");
		FileInputStream is2 = new FileInputStream(productImg2);
		List<ImageHolder> productImgList =new ArrayList<ImageHolder>();
		productImgList.add(new ImageHolder(productImg1.getName(), is1));
		productImgList.add(new ImageHolder(productImg2.getName(), is2));
		//添加商品并验证
		ProductExecution pe = productService.modifyProduct(product, thumbnail, productImgList);
		assertEquals(ProductStateEnum.SUCCESS.getState(), pe.getState());
	}

}
