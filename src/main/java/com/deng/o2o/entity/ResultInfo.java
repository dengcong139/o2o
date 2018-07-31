package com.deng.o2o.entity;

public class ResultInfo<T> {

	private boolean success;
	
	private String errMsg;
	
	private ResultInfo(boolean success,String errMsg){
		this.success = success;
		this.errMsg = errMsg;
	}
	
	private ResultInfo(boolean success){
		this.success = success;
	}
	
	
	public static ResultInfo fail() {
		return new ResultInfo<>(false,ResultMessage.VALIDATE_FAIL.getMessage());
	}
	
	
	public static ResultInfo success() {
		return new ResultInfo<>(true);
	}
	
	
	
}
