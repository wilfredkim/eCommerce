package com.ecommerce.configurations.payload;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class MessageResponse {
    private String message;

    public MessageResponse(String message) {
        this.message = message;
    }
}
