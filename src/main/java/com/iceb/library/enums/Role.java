package com.iceb.library.enums;

public enum Role {
    ROLE_ADMIN, ROLE_LIBRARIAN;

    public String getValue() {
        return name();
    }
}
