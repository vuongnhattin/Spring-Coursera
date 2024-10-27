package com.tin.springcoursera.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AppException extends RuntimeException {
    private int status;
    private String message;
}
