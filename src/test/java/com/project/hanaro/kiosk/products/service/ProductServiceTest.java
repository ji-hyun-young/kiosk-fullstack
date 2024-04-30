package com.project.hanaro.kiosk.products.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.project.hanaro.kiosk.products.domain.Product;
import com.project.hanaro.kiosk.products.dto.ProductGetResponse;
import com.project.hanaro.kiosk.products.dto.ProductUpsertRequest;
import com.project.hanaro.kiosk.products.dto.ProductUpsertResponse;
import com.project.hanaro.kiosk.products.repository.ProductRepository;
import com.project.hanaro.kiosk.products.vo.ProductOption;
import com.project.hanaro.kiosk.products.vo.ProductType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void findProducts() {
    }

    @Test
    void saveProduct() {
        Product product = Product.builder().name("plain burger").imageUrl("").price(1000L)
            .type(ProductType.BURGER).option(
                ProductOption.SINGLE).build();
        ProductUpsertRequest request = new ProductUpsertRequest("hamberger", "url",
            1000L, ProductOption.SINGLE, ProductType.BURGER);

        when(productRepository.save(Mockito.any(Product.class))).thenReturn(product);

        ProductUpsertResponse savedProduct = productService.saveProduct(request);

        Assertions.assertThat(savedProduct).isNotNull();
    }
}