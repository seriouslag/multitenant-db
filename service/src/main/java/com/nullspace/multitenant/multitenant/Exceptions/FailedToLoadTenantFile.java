package com.nullspace.multitenant.multitenant.Exceptions;

public class FailedToLoadTenantFile extends RuntimeException {
	public FailedToLoadTenantFile(String message, Exception e) {
		super(message, e);
	}
}
