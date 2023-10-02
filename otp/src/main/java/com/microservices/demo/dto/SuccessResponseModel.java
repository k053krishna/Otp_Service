package com.microservices.demo.dto;

import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SuccessResponseModel {
	
	@NotNull
	private Long traceID;
	@NotNull
	private Integer statusCode;
	@NotNull
	private Integer otp;
	@NotNull
	private String message;
	
	

}
