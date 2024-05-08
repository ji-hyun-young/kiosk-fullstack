package com.project.hanaro.kiosk.orders.dto;

import com.project.hanaro.kiosk.members.domain.Member;
import com.project.hanaro.kiosk.orders.domain.Order;
import com.project.hanaro.kiosk.orders.domain.OrderProduct;
import com.project.hanaro.kiosk.orders.vo.OrderStatus;
import com.project.hanaro.kiosk.products.domain.Product;

import java.util.List;
import java.util.UUID;

public record OrderInsertRequest(Member member, OrderStatus status) {
    public static Order toEntity(OrderInsertRequest request){
        return Order.builder().member(request.member).code("").tempId(0).status(request.status).build();
    }

}
