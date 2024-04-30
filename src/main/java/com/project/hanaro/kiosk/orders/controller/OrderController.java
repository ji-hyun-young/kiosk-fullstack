package com.project.hanaro.kiosk.orders.controller;

import com.project.hanaro.kiosk.orders.dto.OrderGetResponse;
import com.project.hanaro.kiosk.orders.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/orders")
    public ResponseEntity<List<OrderGetResponse>> findOrderList() {
        List<OrderGetResponse> response = orderService.findOrderList();
        return ResponseEntity.ok(response);
    }

}
