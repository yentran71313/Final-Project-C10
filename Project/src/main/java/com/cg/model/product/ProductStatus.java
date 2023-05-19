package com.cg.model.product;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ProductStatus {
    IN_STOCK("IN_STOCK"),
    OUT_STOCK("OUT_STOCK");

    private final String value;


    ProductStatus(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static ProductStatus parseProductStatus(String value) {
        ProductStatus[] values = values();
        for (ProductStatus productStatus : values) {
            if (productStatus.value.equals(value)) return productStatus;
        }
        throw new IllegalArgumentException(value + "invalid");
    }
}
