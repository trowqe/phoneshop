package com.es.core.dao.phone;



public class ItemNotFoundException extends RuntimeException{

    private static final long  serialVersionUID = 1462190646166272903L;

    public ItemNotFoundException(String message) {
    super(message);
    }
}
