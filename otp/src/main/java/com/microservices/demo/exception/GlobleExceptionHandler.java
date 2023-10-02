package com.microservices.demo.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.microservices.demo.dto.ErrorResponseModel;

@ControllerAdvice
public class GlobleExceptionHandler {
	
	@ExceptionHandler(OtpException.class)
	public ResponseEntity<ErrorResponseModel> handleDriverException(OtpException exp, WebRequest req,Exception e) {
		StringWriter stringWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter(stringWriter);
		e.printStackTrace(printWriter);
		String stackTrace = stringWriter.toString();
		
		
		ErrorResponseModel errorResponse = ErrorResponseModel.builder()
											.traceID(Instant.now().toEpochMilli())
											.timestamp(Instant.now().getEpochSecond())
											.statusCode(HttpStatus.NOT_ACCEPTABLE.value())
											.errorCode(422)
											.message(exp.getMessage())
											.trace(stackTrace)
											.path(exp.getPath())
											.build();
											

		return new ResponseEntity<>(errorResponse,HttpStatus.NOT_ACCEPTABLE);
	}

}
