package com.project.hanaro.kiosk.products.dto;

import com.project.hanaro.kiosk.products.domain.Product;

public record ProductUpsertResponse(Long productId) {

    public static ProductUpsertResponse fromEntity(Product product){
        return new ProductUpsertResponse(product.getProductId());
    }
}
