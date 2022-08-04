package com.ecommerce.ecommerceM.exception;

public class BadRequestException extends RuntimeException {

	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	private String massage;

	public BadRequestException() {
	}

	public BadRequestException(String massage) {
		super(massage);
		this.massage = massage;
	}
}
