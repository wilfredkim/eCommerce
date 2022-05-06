package com.ecommerce.entities;

import lombok.Getter;

@Getter
public enum PaymentStatus {
    PAID("Paid"),
    NOT_PAID("Not Paid");

    String name;

    PaymentStatus(String name) {
        this.name = name;
    }

}
