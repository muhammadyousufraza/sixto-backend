package com.example.practice.project.customexception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessException extends RuntimeException {

    private transient Object customObject;

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message,Object customObject) {
        super(message);
        this.customObject = customObject;
    }
}
