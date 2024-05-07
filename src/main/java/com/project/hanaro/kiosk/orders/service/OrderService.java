package com.project.hanaro.kiosk.orders.service;

import com.project.hanaro.kiosk.orders.domain.Order;
import com.project.hanaro.kiosk.orders.domain.OrderProduct;
import com.project.hanaro.kiosk.orders.dto.*;
import com.project.hanaro.kiosk.orders.projection.OrderSummary;
import com.project.hanaro.kiosk.orders.repository.OrderProductRepository;
import com.project.hanaro.kiosk.orders.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;

    public List<OrderGetResponse> findOrderList() {
        List<OrderSummary> summaryList = orderRepository.getOrdersWithSumPriceAndSumCnt();

        return summaryList.stream().map(summary -> {
            return new OrderGetResponse(summary.getOrderId(), summary.getCode(), summary.getTempId(), summary.getStatus(),
                    summary.getCreatedAt(), summary.getSumPrice(), summary.getSumCnt());
        }).collect(Collectors.toList());

    }

    public OrderGetResponse findOrder(Long id) {
        return orderRepository.getOrder(id)
                .map(order -> new OrderGetResponse(order.getOrderId(), order.getCode(), order.getTempId(), order.getStatus(),
                        order.getCreatedAt(), order.getSumPrice(), order.getSumCnt()))
                .orElse(null);
    }


    public OrderDeleteResponse deleteOrder(Long id) {
        Optional<Order> optional = orderRepository.findById(id);
        if (optional.isPresent()) {
            orderRepository.deleteById(optional.get().getOrderId());
            return new OrderDeleteResponse("order deleted");
        } else {
            return new OrderDeleteResponse("order not found");
        }
    }

    @Transactional
    public OrderSaveResponse saveOrder(OrderInsertRequest orderInsertRequest, List<OrderProductInsertRequest> orderProductInsertRequests) {
        Order savedOrder = orderRepository.save(OrderInsertRequest.toEntity(orderInsertRequest));

        List<Long> orderProductIds = orderProductInsertRequests.stream()
                .map(request -> {
                    request.setOrder(savedOrder);
                    OrderProduct orderProduct = orderProductRepository.save(OrderProductInsertRequest.toEntity(request));
                    return orderProduct.getOrderProductId();
                })
                .collect(Collectors.toList());

        return new OrderSaveResponse(orderProductIds);
    }
}