package com.nullspace.multitenant.multitenant.Exceptions;

public class TenantResolving extends Exception {
	public TenantResolving(Throwable throwable, String message) {
		super(message, throwable);
	}
}
