package com.project.hanaro.kiosk.products.repository;

import com.project.hanaro.kiosk.products.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
