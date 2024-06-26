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
import lombok.Builder;
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

    @Column(name="delete_yn")
    private Boolean deleteYn = false;

    @Builder
    public Product(Long productId, String name, String imageUrl, Long price, ProductOption option,
        ProductType type, Boolean deleteYn) {
        this.productId = productId;
        this.name = name;
        this.imageUrl = imageUrl;
        this.price = price;
        this.option = option;
        this.type = type;
        this.deleteYn = deleteYn;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public void setOption(ProductOption option) {
        this.option = option;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public void setDeleteYn(boolean deleteYn) {
        this.deleteYn = deleteYn;
    }
}
