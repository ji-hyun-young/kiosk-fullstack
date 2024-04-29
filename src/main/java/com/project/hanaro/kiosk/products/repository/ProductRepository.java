package com.project.hanaro.kiosk.products.repository;

import com.project.hanaro.kiosk.products.domain.Product;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByName(String name);
}
