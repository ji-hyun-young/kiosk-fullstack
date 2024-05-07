package com.project.hanaro.kiosk.orders.projection;

import com.project.hanaro.kiosk.orders.vo.OrderStatus;

import java.time.LocalDateTime;

public interface OrderSummary {
    Long getOrderId();

    String getCode();

    Long getTempId();

    OrderStatus getStatus();

    LocalDateTime getCreatedAt();

    Long getSumPrice();

    Integer getSumCnt();

}
