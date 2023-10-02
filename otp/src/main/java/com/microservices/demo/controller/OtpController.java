package com.microservices.demo.controller;

import java.security.InvalidKeyException;
import java.time.Instant;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.microservices.demo.dto.SuccessResponseModel;
import com.microservices.demo.exception.OtpException;
import com.microservices.demo.service.TimeBasedOneTimePasswordGenerator;
import com.microservices.demo.utils.OtpKey;

@RestController
public class OtpController {

	@Autowired
	private TimeBasedOneTimePasswordGenerator mytotp;

	@Autowired
	private OtpKey key;

	private Integer responseOtp;
	
	/**
	 * 
	 * @return this will return the otp in response with other details
	 * @throws InvalidKeyException
	 */

	@PostMapping(value = "/api/v1.0.0/otp/generateOTP")
	public ResponseEntity<SuccessResponseModel> generateOtp()
			throws InvalidKeyException {


		responseOtp = mytotp.generateOneTimePassword(key.getKey(), Instant.now());

		SuccessResponseModel response = SuccessResponseModel.builder().traceID(Instant.now().toEpochMilli())
				.statusCode(HttpStatus.OK.value()).otp(responseOtp).message("Otp is generated successfully")
				.build();

		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	/**
	 * 
	 * @param otp user have to send the otp which we have to validate
	 * @return it return the details of response
	 * @throws InvalidKeyException 
	 */
	@PostMapping(value = "/api/v2.0.0/otp/validateOTP/{otp}")
	public ResponseEntity<SuccessResponseModel> validateOtp(@PathVariable Integer otp) throws InvalidKeyException {

		if (Objects.equals(mytotp.generateOneTimePassword(key.getKey(), Instant.now()), otp)) {
			SuccessResponseModel response = SuccessResponseModel.builder().traceID(Instant.now().toEpochMilli())
					.statusCode(HttpStatus.OK.value()).message("Otp is validated successfully")
					.otp(responseOtp).build();

			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			throw new OtpException("Otp does not matches","/api/v2.0.0/otp/validateOTP/{otp}");
		}
	}

}
