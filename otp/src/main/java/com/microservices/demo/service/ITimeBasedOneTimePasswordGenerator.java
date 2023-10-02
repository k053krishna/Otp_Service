package com.microservices.demo.service;

import java.security.InvalidKeyException;
import java.security.Key;
import java.time.Duration;
import java.time.Instant;
import java.util.Locale;

public interface ITimeBasedOneTimePasswordGenerator {
	
	public int generateOneTimePassword(final Key key, final Instant timestamp)throws InvalidKeyException;
	public String generateOneTimePasswordString(final Key key, final Instant timestamp)throws InvalidKeyException;
	public String generateOneTimePasswordString(final Key key, final Instant timestamp, final Locale locale)throws InvalidKeyException;
	public Duration getTimeStep();
	public int getPasswordLength();
	public String getAlgorithm();
}
