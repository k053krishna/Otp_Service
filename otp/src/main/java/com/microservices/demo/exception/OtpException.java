package com.microservices.demo.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OtpException extends RuntimeException {
	
	private final String path;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OtpException() {
		this.path = "";
		
	}

	public OtpException(String message) {
		super(message);
		this.path = "";
		
	}
	
	public OtpException(String message ,String path) {
		super(message);
		this.path=path;
	}

}
