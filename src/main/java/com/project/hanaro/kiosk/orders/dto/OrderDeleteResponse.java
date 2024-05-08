package com.project.hanaro.kiosk.orders.dto;

import com.project.hanaro.kiosk.orders.domain.Order;

public record OrderDeleteResponse(Long id) {

    public static OrderDeleteResponse fromEntity(Order order) {
        return new OrderDeleteResponse(order.getOrderId());
    }
}
