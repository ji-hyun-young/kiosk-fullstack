package com.project.hanaro.kiosk.products.dto;

import com.project.hanaro.kiosk.products.domain.Product;
import com.project.hanaro.kiosk.products.vo.ProductOption;
import com.project.hanaro.kiosk.products.vo.ProductType;

public record ProductUpsertRequest(String name, String imageUrl, Long price, ProductOption option, ProductType productType) {

    public static Product toEntity(ProductUpsertRequest request){
        return Product.builder().name(request.name).imageUrl(request.imageUrl).price(request.price).option(request.option).type(request.productType).build();
    }
}
