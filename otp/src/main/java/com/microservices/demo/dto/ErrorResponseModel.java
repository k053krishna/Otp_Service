package com.microservices.demo.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseModel {
	
	@NotNull
	private long timestamp;
	@NotNull
	private Integer statusCode;
	private Integer errorCode;
	@NotNull
	private String message;
	private long traceID;
	private String trace;
	private String path;

}
