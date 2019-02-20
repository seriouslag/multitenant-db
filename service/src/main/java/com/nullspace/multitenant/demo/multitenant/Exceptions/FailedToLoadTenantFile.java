package com.nullspace.multitenant.demo.multitenant.Exceptions;

public class FailedToLoadTenantFile extends RuntimeException {
	public FailedToLoadTenantFile(String message, Exception e) {
		super(message, e);
	}
}
