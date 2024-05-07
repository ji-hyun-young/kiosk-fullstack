package com.project.hanaro.kiosk.orders.dto;

import com.project.hanaro.kiosk.orders.domain.Order;
import com.project.hanaro.kiosk.orders.domain.OrderProduct;
import com.project.hanaro.kiosk.products.domain.Product;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Builder
public class OrderProductInsertRequest {
    private Order order;
    private Product product;
    private Integer orderProductCount;
    private Long orderProductPrice;
    public static OrderProduct toEntity(OrderProductInsertRequest request) {
        return OrderProduct.builder().order(request.order).product(request.product).orderProductCount(request.orderProductCount).orderProductPrice(request.orderProductPrice).build();
    }
}
