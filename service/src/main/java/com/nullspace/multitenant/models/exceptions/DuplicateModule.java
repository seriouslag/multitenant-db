package com.nullspace.multitenant.models.exceptions;

public class DuplicateModule extends Exception {
    public DuplicateModule() {}

    public DuplicateModule(String message) {
        super(message);
    }
}
