package com.es.core.dao.phone;

public enum SortField {
    BRAND("brand"),
    MODEL("model"),
    PRICE("price"),
    DISPLAY_SIZE("displaySizeInches");

    public final String field;

    SortField(String field) {
        this.field = field;
    }


}
