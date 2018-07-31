package com.deng.o2o.dao;



import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import com.deng.o2o.BaseTest;
import com.deng.o2o.entity.ProductImg;
@WebAppConfiguration 
@FixMethodOrder(MethodSorters.NAME_ASCENDING)//按照测试方法顺序形成测试回环
public class ProductImgDaoTest extends BaseTest{

	@Autowired//使用spring的自动注入,添加mybatis配置文件中该类的方法
	private ProductImgDao productImgDao;
	
	@Autowired
    private WebApplicationContext wac;
 //   protected MockMvc mockMvc;
//    @Before
//    public void setup() {
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();   //构造MockMvc
//    }
	
	@Test
	public void testABatchInsertProductImg() throws Exception {
		//创建ProductImg对象
		ProductImg productImg1=new ProductImg();
		productImg1.setImgAddr("图片1地址");
		productImg1.setImgDesc("图片1描述");
		productImg1.setPriority(1);
		productImg1.setCreateTime(new Date());
		productImg1.setProductId(1L);
		
		ProductImg productImg2=new ProductImg();
		productImg2.setImgAddr("图片2地址");
		productImg2.setImgDesc("图片2简介");
		productImg2.setPriority(1);
		productImg2.setCreateTime(new Date());
		productImg2.setProductId(1L);
		
		//创建List集合
		List<ProductImg> productImgList=new ArrayList<ProductImg>();
		productImgList.add(productImg1);
		productImgList.add(productImg2);
		//System.out.println(productImgList.get(0).getImgDesc());
		int effected = productImgDao.batchInsertProductImg(productImgList);
		//assertEquals(2, effected);
	}
	
//	@Test
//	public void testABatchInsertProductImg1() throws Exception {
//		mockMvc.perform(get("/o2o/shopadmin/getshopinitinfo").accept(MediaType.APPLICATION_JSON)) //执行请求  
//		.andExpect(status().isOk())
//        .andDo(print()); //输出MvcResult到控制台
//	}
//	
	@Test
	public  void testBQueryProductImgList() {
		//检查productId为1的商品是否有且仅有两张详情图片
		List<ProductImg> productImgList = productImgDao.queryProductImgList(1L);
		assertEquals(2, productImgList.size());
	}
	
	@Test
	public void testCDeleteProductImgByProductId()throws Exception{
		//删除新增的两条商品详情图片列表
		long productId=1;
		int effectedNum = productImgDao.deleteProductImgByProductId(1L);
		assertEquals(2,effectedNum );
	}
	
}
