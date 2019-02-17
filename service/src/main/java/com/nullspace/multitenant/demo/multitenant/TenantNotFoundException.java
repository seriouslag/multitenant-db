package com.nullspace.multitenant.demo.multitenant;

public class TenantNotFoundException extends Exception {
	public TenantNotFoundException(String message) {
		super(message);
	}
}
