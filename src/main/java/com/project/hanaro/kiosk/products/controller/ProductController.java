package com.project.hanaro.kiosk.products.controller;

import com.project.hanaro.kiosk.products.dto.ProductGetResponse;
import com.project.hanaro.kiosk.products.dto.ProductUpsertRequest;
import com.project.hanaro.kiosk.products.dto.ProductUpsertResponse;
import com.project.hanaro.kiosk.products.service.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/api/v1/products")
    public ResponseEntity<List<ProductGetResponse>> findProductList() {
        List<ProductGetResponse> response = productService.findProducts();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/api/v1/products")
    public ResponseEntity<ProductUpsertResponse> saveProduct(@RequestBody ProductUpsertRequest request) {
        ProductUpsertResponse response = productService.saveProduct(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/api/v1/products/{productId}")
    public ResponseEntity<ProductGetResponse> findProduct(@PathVariable Long productId) {
        ProductGetResponse response = productService.findProduct(productId);
        return ResponseEntity.ok(response);
    }
}
