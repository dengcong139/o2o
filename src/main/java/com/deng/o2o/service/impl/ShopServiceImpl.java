package com.deng.o2o.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deng.o2o.dao.ShopDao;
import com.deng.o2o.dto.ImageHolder;
import com.deng.o2o.dto.ShopExecution;
import com.deng.o2o.entity.Shop;
import com.deng.o2o.enums.ShopStateEnum;
import com.deng.o2o.exceptions.ProductCategoryOperationException;
import com.deng.o2o.service.ShopService;
import com.deng.o2o.util.ImageUtil;
import com.deng.o2o.util.PageCalculator;
import com.deng.o2o.util.PathUtil;
@Service
public class ShopServiceImpl implements ShopService {

	@Autowired
	private  ShopDao shopDao;
	
	/*@Transactional//添加事务的相关操作
	@Override
	public ShopExecution addShop(ShopRegisterVo shopRegisterVo, MultipartFile file, String fileName)
			throws Exception {
		//空值判断
		if(!StringUtils.isEmpty(shopRegisterVo)) {
			return new ShopExecution(ShopStateEnum.NULL_SHOP);//将枚举类型作为参数进行传递,可以进行检查
		}
		//String addShopImg = addShopImg(Long.parseLong(UUID.randomUUID().toString()), file.getInputStream(), fileName);
		//shopRegisterVo.setShopImg(addShopImg);
		shopDao.insertShop(shopRegisterVo);
		return new ShopExecution(ShopStateEnum.CHECK,new Shop());
	}*/
	
	
	
	
	
	@Override
	@Transactional//添加事务的相关操作
	public ShopExecution addShop(Shop shop, ImageHolder thumbnail) {//添加店铺方法的返回值为dto中的一个具体类,可以收集相关信息
		//空值判断
		if(shop == null) {
			return new ShopExecution(ShopStateEnum.NULL_SHOP);//将枚举类型作为参数进行传递,可以进行检查
		}
		try {
			//给店赋值初始值
			shop.setEnableStatus(0);//0表示店铺审核中 
			shop.setCreateTime(new Date());
			shop.setLastEditTime(new Date());
			//1.添加店铺信息
			int effectedNum=shopDao.insertShop(shop);
			//throw new ShopOperationException("店铺创建失败!");
			
			/* * Spring默认对RuntimeException进行事务回滚
			 * 使用RuntimeException来维持事务的原子性,
			 * 保证1.添加店铺信息;2.图片处理;3.处理完图片之后添加到店铺;
			 * 这三个操作要么全部成功执行,要么全部执行失败
			 * */
			if(effectedNum <=0) {
				throw new ProductCategoryOperationException("店铺创建失败!");
			}else {
				if(thumbnail !=null) {
					//2.存储图片
					try {
						addShopImg(shop, thumbnail );
					}catch(Exception e) {
						throw new ProductCategoryOperationException("addShopImg error:" + e.getMessage());
					}
					//3.更新店铺的图片地址
					effectedNum=shopDao.updateShop(shop);
					if(effectedNum <=0) {
						throw new ProductCategoryOperationException("更新图片地址失败!");
					}
				}
			}
		}catch(Exception e) {
			throw new ProductCategoryOperationException("addShop error:" + e.getMessage());
		}
		return new ShopExecution(ShopStateEnum.CHECK,shop);
	}//除了shop的判断,还需要对category以及area等做判断(后期添加)	
	
	private void addShopImg(Shop shop, ImageHolder thumbnail) {
		//获取shop图片目录的相对值路径
		String dest = PathUtil.getShopImagePath(shop.getShopId());
		String shopImgAddr =ImageUtil.generateThumbnail( thumbnail,dest);
		shop.setShopImg(shopImgAddr);
	}

	/*private String addShopImg(Long id, InputStream shopImgInputStream,String fileName) {
		//获取shop图片目录的相对值路径
		String dest = PathUtil.getShopImagePath(id);
		return ImageUtil.generateThumbnail(shopImgInputStream, fileName,dest);
	}*/
	
	
	/*
	 * 通过id查找店铺信息
	 * */
	@Override
	public Shop getByShopId(Long shopId) {
		return shopDao.queryByShopId(shopId);
	}

	/*
	 * 根据传入的店铺信息,以及图片流来修改店铺信息
	 * */
	@Override
	public ShopExecution modifyShop(Shop shop, ImageHolder thumbnail)
			throws ProductCategoryOperationException {
		if(shop ==null ||shop.getShopId() ==null) {
			return new ShopExecution(ShopStateEnum.NULL_SHOP);
		}else {
			//1.判断是否需要处理图片
			try {
			if(thumbnail.getImage() !=null && thumbnail.getImageName() != null && !"" .equals(thumbnail.getImageName())) {
				//如果图片不为空先获取shop之前的地址
				Shop tempShop = shopDao.queryByShopId(shop.getShopId());
				if(tempShop.getShopImg() !=null) {
					//如果之前的图片地址不为空,需要使用工具类将之前的图片地址删除掉
					ImageUtil.deleteFileOrPath(tempShop.getShopImg());
				}
				//生成新的图片
				addShopImg(shop, thumbnail); 
			}
			//2.更新店铺信息
			shop.setLastEditTime(new Date());
			int effectedNum = shopDao.updateShop(shop);
			if(effectedNum <=0) {
				return new ShopExecution(ShopStateEnum.INNER_ERROR);
			}else {
				shop = shopDao.queryByShopId(shop.getShopId());
				return new ShopExecution(ShopStateEnum.SUCCESS,shop);
			}}catch(Exception e) {
				throw new ProductCategoryOperationException("modifyShop error:" + e.getMessage());
			}
		}
	}

	
	/*
	 * 根据shopCondition分页返回相应店铺列表
	 * */
	@Override
	public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize) {
		int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
		List<Shop> shopList = shopDao.queryShopList(shopCondition, rowIndex, pageSize);
		int count = shopDao.queryShopCount(shopCondition);
		ShopExecution se=new ShopExecution();
		if(shopList!=null) {
			se.setShopList(shopList);
			se.setCount(count);
		}else {
			se.setState(ShopStateEnum.INNER_ERROR.getState());
		}
		return se;
	}


	
	
}
