package com.microservices.demo.utils;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import org.springframework.stereotype.Component;
import com.microservices.demo.service.TimeBasedOneTimePasswordGenerator;
import lombok.Data;
@Data
@Component
public class OtpKey {

	final Key key;

	TimeBasedOneTimePasswordGenerator mytotp = new TimeBasedOneTimePasswordGenerator();

	public OtpKey() throws NoSuchAlgorithmException {

		int macLengthInBytes = 0;

		KeyGenerator keyGenerator = KeyGenerator.getInstance(mytotp.getAlgorithm());
		macLengthInBytes = Mac.getInstance(mytotp.getAlgorithm()).getMacLength();
		keyGenerator.init(macLengthInBytes * 8);

		this.key = keyGenerator.generateKey();

	}

}
