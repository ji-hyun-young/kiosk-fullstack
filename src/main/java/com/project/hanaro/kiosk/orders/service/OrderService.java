package com.project.hanaro.kiosk.orders.service;

import com.project.hanaro.kiosk.orders.domain.Order;
import com.project.hanaro.kiosk.orders.domain.OrderProduct;
import com.project.hanaro.kiosk.orders.domain.TempIdManager;
import com.project.hanaro.kiosk.orders.dto.*;
import com.project.hanaro.kiosk.orders.exception.OrderNotFoundException;
import com.project.hanaro.kiosk.orders.projection.OrderSummary;
import com.project.hanaro.kiosk.orders.repository.OrderProductRepository;
import com.project.hanaro.kiosk.orders.repository.OrderRepository;
import com.project.hanaro.kiosk.orders.repository.TempIdManagerRepository;
import com.project.hanaro.kiosk.products.domain.Product;
import com.project.hanaro.kiosk.products.dto.ProductUpsertResponse;
import com.project.hanaro.kiosk.products.exception.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;
    private final TempIdManagerRepository tempIdManagerRepository;


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
                .orElseThrow(OrderNotFoundException::new);
    }

    @Transactional
    public OrderDeleteResponse deleteOrder(Long id) {
        Order order = orderRepository.findById(id)
            .orElseThrow(() -> new OrderNotFoundException(id));
        order.setDeleteYn(true);
        return OrderDeleteResponse.fromEntity(order);
    }

    @Transactional
    public OrderSaveResponse saveOrder(OrderInsertRequest orderInsertRequest, List<OrderProductInsertRequest> orderProductInsertRequests) {
        Order savedOrder = orderRepository.save(OrderInsertRequest.toEntity(orderInsertRequest));

        //상품코드
        UUID uuid = UUID.randomUUID();
        savedOrder.setCode(uuid.toString());

        //주문번호
        TempIdManager tempIdManager = tempIdManagerRepository.findAll().stream().findFirst()
                .orElse(TempIdManager.builder().currentTempId(1).build());
        Integer currentTempId = tempIdManager.getCurrentTempId();
        savedOrder.setTempId(currentTempId);

        tempIdManagerRepository.save(tempIdManager);

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
