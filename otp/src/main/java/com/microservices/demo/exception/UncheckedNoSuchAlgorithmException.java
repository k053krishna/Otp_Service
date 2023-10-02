package com.microservices.demo.exception;

import java.security.NoSuchAlgorithmException;

public class UncheckedNoSuchAlgorithmException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Constructs a new unchecked {@code NoSuchAlgorithmException} instance.
     *
     * @param cause the underlying {@code NoSuchAlgorithmException}
     */
    public UncheckedNoSuchAlgorithmException(final NoSuchAlgorithmException cause) {
        super(cause);
    }


}
