package com.project.hanaro.kiosk.orders.exception;

import com.project.hanaro.kiosk.common.exception.EntityNotFoundException;

public class OrderNotFoundException extends EntityNotFoundException {
    public OrderNotFoundException() {
        super("Could not find Order");
    }

    public OrderNotFoundException(Long id) {
        super("Could not find Order: " + id);
    }
}
