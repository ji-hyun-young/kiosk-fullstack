package com.project.hanaro.kiosk.orders.dto;

import java.util.List;

public record OrderSaveResponse(List<Long> orderProductIds) {
}
