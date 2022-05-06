package com.ecommerce.entities;

import lombok.Getter;

@Getter
public enum DeliveryStatus {
    DELIVERED("Delivered"),
    NOT_DELIVERED("Not Delivered");

    String name;

    DeliveryStatus(String name) {
        this.name = name;
    }
}
