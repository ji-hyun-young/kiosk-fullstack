package com.project.hanaro.kiosk.products.service;

import static org.mockito.Mockito.when;

import com.project.hanaro.kiosk.products.domain.Product;
import com.project.hanaro.kiosk.products.dto.ProductGetResponse;
import com.project.hanaro.kiosk.products.dto.ProductUpsertRequest;
import com.project.hanaro.kiosk.products.dto.ProductUpsertResponse;
import com.project.hanaro.kiosk.products.repository.ProductRepository;
import com.project.hanaro.kiosk.products.vo.ProductOption;
import com.project.hanaro.kiosk.products.vo.ProductType;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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

    private Product product;

    @BeforeEach
    void beforeEach() {
        product = Product.builder().name("plain burger").imageUrl("").price(1000L)
            .type(ProductType.BURGER).option(
                ProductOption.SINGLE).build();
    }

    @Test
    @DisplayName("상품 저장")
    void saveProduct() {
        ProductUpsertRequest request = new ProductUpsertRequest("plain burger", "url",
            1000L, ProductOption.SINGLE, ProductType.BURGER);

        when(productRepository.save(Mockito.any(Product.class))).thenReturn(product);

        ProductUpsertResponse savedProduct = productService.saveProduct(request);

        Assertions.assertThat(savedProduct).isNotNull();
    }

    @Test
    @DisplayName("상품 상세 조회")
    void findProduct() {
        Long productId = 1L;

        when(productRepository.findById(Mockito.any(Long.class))).thenReturn(
            Optional.ofNullable(product));

        ProductGetResponse foundProduct = productService.findProduct(productId);

        Assertions.assertThat(foundProduct)
            .isNotNull()
            .extracting("name","price","type","option")
            .contains("plain burger",1000L, ProductType.BURGER, ProductOption.SINGLE);
    }
}