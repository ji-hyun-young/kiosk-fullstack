package com.project.hanaro.kiosk.products.repository;

import com.project.hanaro.kiosk.products.domain.Product;
import com.project.hanaro.kiosk.products.vo.ProductOption;
import com.project.hanaro.kiosk.products.vo.ProductType;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    @DisplayName("상품 조회 By Name")
    void findByName_ReturnsProduct() {

        //given
        String name = "pizza";
        Product plainBurger = Product.builder().name("plain burger").imageUrl("").price(1000L)
            .type(ProductType.BURGER).option(
                ProductOption.SINGLE).build();

        productRepository.save(plainBurger);

        //when
        Optional<Product> product = productRepository.findByName(name);

        //then
        Assertions.assertThat(product).isNotNull();
    }
}