package com.azish.nasa.presentation.controller;

import lombok.Data;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    private String description;

    public BusinessException(String message, String description) {
        super(message);
        this.description = description;
    }


}
