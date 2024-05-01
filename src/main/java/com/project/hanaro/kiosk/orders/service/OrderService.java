package com.project.hanaro.kiosk.orders.service;

import com.project.hanaro.kiosk.orders.domain.Order;
import com.project.hanaro.kiosk.orders.dto.OrderGetResponse;
import com.project.hanaro.kiosk.orders.projection.OrderSummary;
import com.project.hanaro.kiosk.orders.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public List<OrderGetResponse> findOrderList(){
        List<OrderSummary> summaryList = orderRepository.getOrderWithSumPriceAndSumCnt();


        return summaryList.stream().map(summary->{
            return new OrderGetResponse(summary.getOrderId(),summary.getCode(), summary.getTempId(),summary.getStatus(),
                    summary.getCreatedAt(), summary.getSumPrice(),  summary.getSumCnt());
        }).collect(Collectors.toList());

    }

}
