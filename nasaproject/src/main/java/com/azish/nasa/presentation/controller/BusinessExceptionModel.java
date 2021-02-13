package com.azish.nasa.presentation.controller;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class BusinessExceptionModel implements Serializable {
    private String message;
    private String description;
    private String stackTrace;
}
