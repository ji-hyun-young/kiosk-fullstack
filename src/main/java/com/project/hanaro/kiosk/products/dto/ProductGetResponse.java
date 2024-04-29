package com.project.hanaro.kiosk.products.dto;

import com.project.hanaro.kiosk.products.domain.Product;
import com.project.hanaro.kiosk.products.vo.ProductOption;
import com.project.hanaro.kiosk.products.vo.ProductType;

public record ProductGetResponse(Long productId, String name, String imageUrl, Long price, ProductOption option, ProductType productType) {

    public static ProductGetResponse fromEntity(Product product){
        return new ProductGetResponse(product.getProductId(), product.getName(), product.getImageUrl(),
            product.getPrice(), product.getOption(), product.getType());
    }
}
