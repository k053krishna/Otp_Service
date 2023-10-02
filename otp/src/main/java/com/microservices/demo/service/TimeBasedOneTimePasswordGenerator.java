package com.microservices.demo.service;

import java.security.InvalidKeyException;
import java.security.Key;
import java.time.Duration;
import java.time.Instant;
import java.util.Locale;

import org.springframework.stereotype.Service;

import com.microservices.demo.exception.UncheckedNoSuchAlgorithmException;

@Service
public class TimeBasedOneTimePasswordGenerator implements ITimeBasedOneTimePasswordGenerator{
	

	private final HmacOneTimePasswordGenerator hotp;
	private final Duration timeStep;

	/**
	 * The default time-step for a time-based one-time password generator (30
	 * seconds).
	 */
	public static final Duration DEFAULT_TIME_STEP = Duration.ofSeconds(30);

	/**
	 * A string identifier for the HMAC-SHA1 algorithm; HMAC-SHA1 is the default
	 * algorithm for TOTP.
	 */
	public static final String TOTP_ALGORITHM_HMAC_SHA1 = "HmacSHA1";

	   
	/**
     * Constructs a new time-based one-time password generator with a default time-step (30 seconds), password length
     *  and HMAC algorithm.
     */
	public TimeBasedOneTimePasswordGenerator() {
		this(DEFAULT_TIME_STEP);
	}

	/**
     * Constructs a new time-based one-time password generator with the given time-step and a default password length
     * and HMAC algorithm.
     * @param timeStep the time-step for this generator
     */
	public TimeBasedOneTimePasswordGenerator(final Duration timeStep) {
		this(timeStep, HmacOneTimePasswordGenerator.DEFAULT_PASSWORD_LENGTH);
	}
	/**
     * Constructs a new time-based one-time password generator with the given time-step and password length and a
     * default HMAC algorithm .
     *
     * @param timeStep the time-step for this generator
     * @param passwordLength the length, in decimal digits, of the one-time passwords to be generated; must be between
     * 6 and 8, inclusive
     */
	
	public TimeBasedOneTimePasswordGenerator(final Duration timeStep, final int passwordLength) {
		this(timeStep, passwordLength, TOTP_ALGORITHM_HMAC_SHA1);
	}

	 /**
     * Constructs a new time-based one-time password generator with the given time-step, password length, and HMAC
     * algorithm.
     * @param timeStep the time-step for this generator
     * @param passwordLength the length, in decimal digits, of the one-time passwords to be generated; must be between
     * 6 and 8, inclusive
     * @param algorithm the name of the {@link javax.crypto.Mac} algorithm to use when generating passwords; TOTP allows
     * @throws UncheckedNoSuchAlgorithmException if the given algorithm
     * @see #TOTP_ALGORITHM_HMAC_SHA1
     */
	public TimeBasedOneTimePasswordGenerator(final Duration timeStep, final int passwordLength, final String algorithm)
			throws UncheckedNoSuchAlgorithmException {

		this.hotp = new HmacOneTimePasswordGenerator(passwordLength, algorithm);
		this.timeStep = timeStep;
	}
	
	/**
     * Generates a one-time password using the given key and timestamp.
     *
     * @param key the key to be used to generate the password
     * @param timestamp the timestamp for which to generate the password
     *
     * @return an integer representation of a one-time password; callers will need to format the password for display
     * on their own
     *
     * @throws InvalidKeyException if the given key is inappropriate for initializing the {@link Mac} for this generator
     */
	@Override
	public int generateOneTimePassword(final Key key, final Instant timestamp) throws InvalidKeyException  {
		return this.hotp.generateOneTimePassword(key, timestamp.toEpochMilli() / this.timeStep.toMillis());
	}
	
	 /**
     * Generates a one-time password using the given key and timestamp and formats it as a string with the system
     * default locale.
     *
     * @param key the key to be used to generate the password
     * @param timestamp the timestamp for which to generate the password
     *
     * @return a string representation of a one-time password
     *
     * @throws InvalidKeyException if the given key is inappropriate for initializing the {@link Mac} for this generator
     *
     * @see Locale#getDefault()
     */
	@Override
	public String generateOneTimePasswordString(final Key key, final Instant timestamp) throws InvalidKeyException {
		return this.generateOneTimePasswordString(key, timestamp, Locale.getDefault());
	}

	/**
     * Generates a one-time password using the given key and timestamp and formats it as a string with the given locale.
     *
     * @param key the key to be used to generate the password
     * @param timestamp the timestamp for which to generate the password
     * @param locale the locale to apply during formatting
     *
     * @return a string representation of a one-time password
     *
     * @throws InvalidKeyException if the given key is inappropriate for initializing the {@link Mac} for this generator
     */
	@Override
	public String generateOneTimePasswordString(final Key key, final Instant timestamp, final Locale locale)
			throws InvalidKeyException {
		return this.hotp.formatOneTimePassword(this.generateOneTimePassword(key, timestamp), locale);
	}

	/**
	 * Returns the time step used by this generator.
	 *
	 * @return the time step used by this generator
	 */
	@Override
	public Duration getTimeStep() {
		return this.timeStep;
	}

	/**
	 * Returns the length, in decimal digits, of passwords produced by this
	 * generator.
	 *
	 * @return the length, in decimal digits, of passwords produced by this
	 *         generator
	 */
	@Override
	public int getPasswordLength() {
		return this.hotp.getPasswordLength();
	}

	/**
	 * Returns the name of the HMAC algorithm used by this generator.
	 * 
	 * @return the name of the HMAC algorithm used by this generator
	 */
	@Override
	public String getAlgorithm() {
		return this.hotp.getAlgorithm();
	}

}
