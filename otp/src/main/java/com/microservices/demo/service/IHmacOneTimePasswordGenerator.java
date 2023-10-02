package com.microservices.demo.service;

import java.security.InvalidKeyException;
import java.security.Key;
import java.util.Locale;

public interface IHmacOneTimePasswordGenerator {

	public int generateOneTimePassword(final Key key, final long counter) throws InvalidKeyException;

	public String generateOneTimePasswordString(final Key key, final long counter) throws InvalidKeyException;

	public String generateOneTimePasswordString(final Key key, final long counter, final Locale locale)
			throws InvalidKeyException;

	public int getPasswordLength();

	public String getAlgorithm();
}
