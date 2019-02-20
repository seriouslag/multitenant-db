package com.nullspace.multitenant.demo.multitenant.Exceptions;

public class TenantNotFound extends Exception {
	public TenantNotFound(String message) {
		super(message);
	}
}
