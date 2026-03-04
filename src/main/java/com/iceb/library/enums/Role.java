package com.iceb.library.enums;

public enum Role {
    ROLE_ADMIN, ROLE_LIBRARIAN, ROLE_CUSTOMER;

    public String getValue() {
        return name();
    }
}
