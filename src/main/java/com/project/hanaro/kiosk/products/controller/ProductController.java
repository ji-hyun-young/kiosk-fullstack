package com.project.hanaro.kiosk.products.controller;

import com.project.hanaro.kiosk.products.dto.ProductGetResponse;
import com.project.hanaro.kiosk.products.service.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<ProductGetResponse>> findProductList() {
        List<ProductGetResponse> response = productService.findProducts();
        return ResponseEntity.ok(response);
    }

}
