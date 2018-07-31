package com.deng.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import com.deng.o2o.BaseTest;
import com.deng.o2o.entity.Product;
import com.deng.o2o.entity.ProductCategory;
import com.deng.o2o.entity.ProductImg;
import com.deng.o2o.entity.Shop;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductDaoTest extends BaseTest{

	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private ProductImgDao productImgDao;
	@Test
	public void testAInsertProduct() throws Exception{
		Shop shop1=new Shop();
		shop1.setShopId(1L);
		ProductCategory pc1=new ProductCategory();
		pc1.setProductCategoryId(2L);
		
		//初始化三个商品实例,并添加进shopId为1.商品类别也为1的店铺里
		Product product1=new Product();
		product1.setProductName("1号测试商品");
		product1.setImgAddr("1号地址");
		product1.setProductDesc("1号简介");
		product1.setEnableStatus(1);
		product1.setCreateTime(new Date());
		product1.setLastEditTime(new Date());
		product1.setPriority(2);
		product1.setShop(shop1);
		product1.setProductCategory(pc1);
		
		Product product2=new Product();
		product2.setProductName("2号测试商品");
		product2.setImgAddr("2号地址");
		product2.setProductDesc("2号简介");
		product2.setEnableStatus(2);
		product2.setCreateTime(new Date());
		product2.setLastEditTime(new Date());
		product2.setPriority(3);
		product2.setShop(shop1);
		product2.setProductCategory(pc1);
		
		int effectedNum = productDao.insertProduct(product1);
		assertEquals(1, effectedNum);
		effectedNum=productDao.insertProduct(product2);
		assertEquals(1, effectedNum);
	}
	
	@Test
	public void testBQueryProductList() throws Exception{
		Product productCondition=new Product();
		//分页查询,预期返回三条结果
		List<Product> productList =productDao.queryProductList(productCondition, 0, 3);
		assertEquals(3, productList.size());
		//查询商品为测试的商品总数
		int count = productDao.queryProductCount(productCondition);
		assertEquals(7, count);
		//使用商品名称的模糊查询,预期返回两条结果
		productCondition.setProductName("测试");
		productList=productDao.queryProductList(productCondition, 0, 3);
		assertEquals(2, productList.size());
		count = productDao.queryProductCount(productCondition);
		assertEquals(2, count);
	}
	@Test
	public void testCQueryProductByProductId() throws Exception{
		long productId=1;
		//初始化两个商品详情图实例作为productId为1的商品下的详情图片
		//批量插入到商品详情表中
		ProductImg productImg1=new ProductImg();
		productImg1.setImgAddr("图片1地址");
		productImg1.setImgDesc("图片1描述");
		productImg1.setPriority(1);
		productImg1.setCreateTime(new Date());
		productImg1.setProductId(productId);
		
		ProductImg productImg2=new ProductImg();
		productImg2.setImgAddr("图片2地址");
		productImg2.setImgDesc("图片2简介");
		productImg2.setPriority(1);
		productImg2.setCreateTime(new Date());
		productImg2.setProductId(productId);
		
		//创建List集合
		List<ProductImg> productImgList=new ArrayList<ProductImg>();
		productImgList.add(productImg1);
		productImgList.add(productImg2);
		
		int effectedNum = productImgDao.batchInsertProductImg(productImgList);
		assertEquals(2, effectedNum);
		//查询productId为1的商品信息并校验返回的详情实例列表size是否为2
		Product product = productDao.queryProductById(productId);
		assertEquals(2, product.getProductImgList().size());
		//删除这两个新增的商品商品详情图实例
		effectedNum = productImgDao.deleteProductImgByProductId(productId);
		assertEquals(2, effectedNum);
		
	}
	
	@Test
	public void testDUpdateProduct() throws Exception{
		Product product=new Product();
		ProductCategory pc=new ProductCategory();
		Shop shop=new Shop();
		shop.setShopId(1L);
		pc.setProductCategoryId(2L);
		product.setProductId(1L);
		product.setShop(shop);
		product.setProductName("雅马哈");
		product.setProductCategory(pc);
		//修改productId为1的商品的名称
		//以及商品类别并校验影响的行数为1
		int effectedNum = productDao.updateProduct(product);
		assertEquals(1, effectedNum);
	}
	
	@Test
	public void testEUpdateProductCategoryToNull() {
		//将productCategoryId为2的商品类别下面的商品的商品类别置为null
		int effectedNum = productDao.updateProductCategoryToNull(1L);
		assertEquals(1, effectedNum);
	}
	
}
