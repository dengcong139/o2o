package com.deng.o2o.exceptions;

public class ProductOperationException  extends RuntimeException{
	private static final long serialVersionUID = 829751446932221203L;
	public ProductOperationException(String msg) {
		super(msg);
	}
}
