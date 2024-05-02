package com.project.hanaro.kiosk.orders.service;

import com.project.hanaro.kiosk.orders.domain.Order;
import com.project.hanaro.kiosk.orders.dto.OrderDeleteResponse;
import com.project.hanaro.kiosk.orders.dto.OrderGetResponse;
import com.project.hanaro.kiosk.orders.projection.OrderSummary;
import com.project.hanaro.kiosk.orders.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

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
}
