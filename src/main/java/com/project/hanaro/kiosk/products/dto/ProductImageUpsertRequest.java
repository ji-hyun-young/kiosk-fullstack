package com.project.hanaro.kiosk.products.dto;

import com.project.hanaro.kiosk.products.domain.Product;
import org.openapitools.jackson.nullable.JsonNullable;

public record ProductImageUpsertRequest(JsonNullable<String> imageUrl) {

    public Product updateEntity(Product product) {
        imageUrl.ifPresent(product::setImageUrl);
        return product;
    }
}
