package com.project.hanaro.kiosk.products.repository;

import com.project.hanaro.kiosk.products.domain.Product;
import com.project.hanaro.kiosk.products.vo.ProductOption;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByName(String name);

    @Query(value = "SELECT * FROM products p "
        + "WHERE p.product_option = :productOption "
        + "ORDER BY p.product_id",
        countQuery = "SELECT COUNT(*) FROM products",
        nativeQuery = true
    )
    Page<Product> getPageByProductOption(ProductOption productOption, Pageable pageable);

    @Query(value = "SELECT * FROM products p "
        + "ORDER BY p.price",
        countQuery = "SELECT COUNT(*) FROM products",
        nativeQuery = true
    )
    Page<Product> getSuggestProductsPage(Pageable pageable);
}
