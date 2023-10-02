package com.microservices.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;


import java.time.Duration;

import org.junit.jupiter.api.Test;

import com.microservices.demo.service.TimeBasedOneTimePasswordGenerator;

class TimeBasedOneTimePasswordGeneratorTest {

//	private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";
	
//	 private static final byte[] HMAC_SHA1_KEY_BYTES =
//	            "12345678901234567890".getBytes(StandardCharsets.US_ASCII);
	 
	
	 @Test
	    void testGetPasswordLength() {
	        final int passwordLength = 7;
	        assertEquals(passwordLength, new TimeBasedOneTimePasswordGenerator(Duration.ofSeconds(30), passwordLength).getPasswordLength());
	    }
	 
	 @Test
	    void testGetAlgorithm() {
	        final String algorithm = TimeBasedOneTimePasswordGenerator.TOTP_ALGORITHM_HMAC_SHA1;
	        assertEquals(algorithm, new TimeBasedOneTimePasswordGenerator(Duration.ofSeconds(30), 6, algorithm).getAlgorithm());
	    }
	 @Test
	    void testGetTimeStep() {
	        final Duration timeStep = Duration.ofSeconds(97);
	        assertEquals(timeStep, new TimeBasedOneTimePasswordGenerator(timeStep).getTimeStep());
	    }
	 
}
