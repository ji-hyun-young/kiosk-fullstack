package com.project.hanaro.kiosk.orders.dto;

import com.project.hanaro.kiosk.orders.vo.OrderStatus;

import java.time.LocalDateTime;

public record OrderGetResponse(Long orderId, String code, Long tempId,
                               OrderStatus status, LocalDateTime createdAt, Long sumPrice, Integer sumCnt) {

}
