package com.project.hanaro.kiosk.products.dto;

import com.project.hanaro.kiosk.products.domain.Product;
import com.project.hanaro.kiosk.products.vo.ProductOption;
import com.project.hanaro.kiosk.products.vo.ProductType;
import org.openapitools.jackson.nullable.JsonNullable;

public record ProductUpsertRequest(JsonNullable<String> name, JsonNullable<String> imageUrl, JsonNullable<Long> price, JsonNullable<ProductOption> option, JsonNullable<ProductType> type) {

    public static Product toEntity(ProductUpsertRequest request){
        return Product.builder()
            .name(request.name.get())
            .imageUrl(request.imageUrl.get())
            .price(request.price.get())
            .option(request.option.get())
            .type(request.type.get())
            .build();
    }

    public Product updateEntity(Product product) {
        name.ifPresent(product::setName);
        imageUrl.ifPresent(product::setImageUrl);
        price.ifPresent(product::setPrice);
        option.ifPresent(product::setOption);
        type.ifPresent(product::setType);
        return product;
    }
}
