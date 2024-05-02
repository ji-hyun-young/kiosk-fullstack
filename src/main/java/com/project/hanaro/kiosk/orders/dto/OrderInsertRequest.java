package com.project.hanaro.kiosk.orders.dto;

import com.project.hanaro.kiosk.members.domain.Member;
import com.project.hanaro.kiosk.orders.vo.OrderStatus;
import com.project.hanaro.kiosk.products.domain.Product;

public record OrderInsertRequest(Member member, OrderStatus status, Product product, Integer orderProductCount, Long orderProductPrice) {
}
