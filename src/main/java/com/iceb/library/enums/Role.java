package com.iceb.library.enums;

public enum Role {
    ADMIN, LIBRARIAN, CUSTOMER;

    public String getValue() {
        return name();
    }
}
