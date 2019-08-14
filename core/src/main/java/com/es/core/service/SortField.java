package com.es.core.service;

public enum SortField {
    BRAND("brand"),
    MODEL("model"),
    PRICE("price"),
    DISPLAY_SIZE("displaySize");

    public final String field;

    SortField(String field) {
        this.field = field;
    }
}
