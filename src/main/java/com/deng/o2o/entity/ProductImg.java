package com.deng.o2o.entity;

import java.io.Serializable;
import java.util.Date;
/*
 * 该类是商品详情图片实体类
 * */
public class ProductImg implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long productImgId;
	private String imgAddr;//图片地址
	private String imgDesc;//图片详情
	private Integer priority;
	private Date createTime;
	private Long productId;//属于哪一个商品的详情图片(一对多)
	public Long getProductImgId() {
		return productImgId;
	}
	public void setProductImgId(Long productImgId) {
		this.productImgId = productImgId;
	}
	public String getImgAddr() {
		return imgAddr;
	}
	public void setImgAddr(String imgAddr) {
		this.imgAddr = imgAddr;
	}
	public String getImgDesc() {
		return imgDesc;
	}
	public void setImgDesc(String imgDesc) {
		this.imgDesc = imgDesc;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	
	
}
