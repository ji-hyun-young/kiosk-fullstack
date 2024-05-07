package com.project.hanaro.kiosk.products.dto;

import com.project.hanaro.kiosk.products.domain.Product;

public record ProductImageUpsertResponse(Long productId) {

    public static ProductImageUpsertResponse fromEntity(Product product) {
        return new ProductImageUpsertResponse(product.getProductId());
    }
}
