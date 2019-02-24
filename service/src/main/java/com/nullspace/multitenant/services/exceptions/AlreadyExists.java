package com.nullspace.multitenant.services.exceptions;

public class AlreadyExists extends Exception{
    public AlreadyExists() {
        super("Item already exists.");
    }

}
