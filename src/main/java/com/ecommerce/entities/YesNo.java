package com.ecommerce.entities;

import lombok.Getter;

@Getter
public enum YesNo {
    YES("Yes"),
    NO("No");

    String name;

    YesNo(String name) {
        this.name = name;
    }
}
