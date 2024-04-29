package com.project.hanaro.kiosk.products.exception;

import com.project.hanaro.kiosk.common.exception.ValueInvalidException;

public class ProductInvalidException extends ValueInvalidException {

    public ProductInvalidException() {
        super("Invalid Product Value");
    }

    public ProductInvalidException(Long id) {
        super("Invalid Product Value: " + id);
    }
}
