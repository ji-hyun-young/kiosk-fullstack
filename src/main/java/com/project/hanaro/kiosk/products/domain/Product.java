package com.project.hanaro.kiosk.products.domain;

import com.project.hanaro.kiosk.common.domain.BaseEntity;
import com.project.hanaro.kiosk.products.vo.ProductOption;
import com.project.hanaro.kiosk.products.vo.ProductType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "products")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(name="name")
    private String name;

    @Column(name="image_url")
    private String imageUrl;

    @Column(name="price")
    private Long price;

    @Column(name="product_option")
    @Enumerated(EnumType.STRING)
    private ProductOption option;

    @Column(name="product_type")
    @Enumerated(EnumType.STRING)
    private ProductType type;
}
