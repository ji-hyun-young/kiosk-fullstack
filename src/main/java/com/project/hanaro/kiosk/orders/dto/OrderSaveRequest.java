package com.project.hanaro.kiosk.orders.dto;

import java.util.List;

public record OrderSaveRequest(OrderInsertRequest orderInsertRequest, List<OrderProductInsertRequest> orderProductInsertRequests) {
}
