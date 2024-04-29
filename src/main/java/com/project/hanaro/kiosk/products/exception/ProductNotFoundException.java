package com.project.hanaro.kiosk.products.exception;

import com.project.hanaro.kiosk.common.exception.EntityNotFoundException;

public class ProductNotFoundException extends EntityNotFoundException {

    public ProductNotFoundException() {
        super("Could not find Product");
    }

    public ProductNotFoundException(Long id) {
        super("Could not find Product: " + id);
    }

}
