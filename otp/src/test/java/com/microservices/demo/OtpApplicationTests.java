package com.microservices.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.microservices.demo.service.TimeBasedOneTimePasswordGenerator;

@SpringBootTest
class OtpApplicationTests {
	Integer r=23;

	@Test
	void contextLoads() {
		final int passwordLength = 7;
        assertEquals(passwordLength, new TimeBasedOneTimePasswordGenerator(Duration.ofSeconds(30), passwordLength).getPasswordLength());
    
	}

}
