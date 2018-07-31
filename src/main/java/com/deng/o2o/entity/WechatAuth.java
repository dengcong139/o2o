package com.deng.o2o.entity;

import java.io.Serializable;
import java.util.Date;
/*
 * 该类是微信的实体类
 * */
public class WechatAuth implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Long wechatAuthId;
	private String openId;
	private Date createTime;
	private PersonInfo personInfo;
	public Long getWechatAuthId() {
		return wechatAuthId;
	}
	public void setWechatAuthId(Long wechatAuthId) {
		this.wechatAuthId = wechatAuthId;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public PersonInfo getPersonInfo() {
		return personInfo;
	}
	public void setPersonInfo(PersonInfo personInfo) {
		this.personInfo = personInfo;
	}
	
}