package com.ll.exam.sbb.user;

import lombok.Getter;

@Getter
public enum UserRole {

    SUPER_ADMIN("ROLE_SUPER_ADMIN"),
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");


    UserRole(String value) {
        this.value = value;
    }

    private String value;
}
