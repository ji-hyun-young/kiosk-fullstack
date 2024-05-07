package com.project.hanaro.kiosk.orders.repository;

import com.project.hanaro.kiosk.orders.domain.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
}
