package com.nullspace.multitenant.demo.multitenant;

public class TenantResolvingException extends Exception {
	public TenantResolvingException(Throwable throwable, String message) {
		super(message, throwable);
	}
}
