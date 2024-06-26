package com.project.hanaro.kiosk.orders.domain;

import com.project.hanaro.kiosk.products.domain.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "orders_products_mapping")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_product_id")
    private Long orderProductId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "order_product_cnt")
    private Integer orderProductCount;

    @Column(name = "order_product_price")
    private Long orderProductPrice;

    @Builder
    public OrderProduct(Order order, Product product, Integer orderProductCount, Long orderProductPrice) {
        this.order = order;
        this.product = product;
        this.orderProductCount = orderProductCount;
        this.orderProductPrice = orderProductPrice;
    }
}
