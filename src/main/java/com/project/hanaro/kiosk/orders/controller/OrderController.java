package com.project.hanaro.kiosk.orders.controller;

import com.project.hanaro.kiosk.orders.dto.OrderGetResponse;
import com.project.hanaro.kiosk.orders.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/api/v1/orders")
    public ResponseEntity<List<OrderGetResponse>> findOrderList() {
        List<OrderGetResponse> response = orderService.findOrderList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/api/v1/orders/{id}")
    public ResponseEntity<OrderGetResponse> findOrder(@PathVariable Long id) {
        OrderGetResponse response = orderService.findOrder(id);
        return ResponseEntity.ok(response);
    }
    
}
