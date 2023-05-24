package com.cg.model.auth.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum StatusOrder {
    PENDING("PENDING"),
    PAID("PAID"),
    SHIPPED("SHIPPED"),
    CANCELLED("CANCELLED");

    private final String value;


    StatusOrder(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static StatusOrder parseStatusPosts(String value) {
        StatusOrder[] values = values();
        for (StatusOrder statusPost : values) {
            if (statusPost.value.equals(value)) return statusPost;
        }
        throw new IllegalArgumentException(value + "invalid");
    }

}
