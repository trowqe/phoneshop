package com.es.core.dao.phone;

public enum SortField {
    PHONE_ID("phones.id"),
    BRAND("phones.brand"),
    MODEL("phones.model"),
    PRICE("phones.price"),
    DISPLAY_SIZE("phones.displaySizeInches");

    public final String field;

    SortField(String field) {
        this.field = field;
    }


}
