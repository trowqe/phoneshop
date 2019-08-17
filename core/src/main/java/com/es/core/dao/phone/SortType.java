package com.es.core.dao.phone;

public enum SortType {
    DESC("desc"),
    ASC("asc");

    public final String type;

    SortType(String type) {
        this.type = type;
    }
}
