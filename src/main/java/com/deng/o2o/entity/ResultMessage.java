package com.deng.o2o.entity;

public enum ResultMessage {
	
	VALIDATE_FAIL(1,"验证码输入有误!");
	
	private Integer status;
	private String message;
	
	private ResultMessage(Integer status, String message) {
		this.status = status;
		this.message = message;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
	
	
}
