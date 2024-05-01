package com.project.hanaro.kiosk.orders.repository;

import com.project.hanaro.kiosk.orders.domain.Order;
import com.project.hanaro.kiosk.orders.projection.OrderSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(value = "select a.order_id as orderId, a.code as code, a.temp_id as tempId, a.status as status, a.created_at as createdAt, " +
            "sum(b.order_product_price) as sumPrice, sum(b.order_product_cnt) as sumCnt " +
            "from orders a " +
            "join orders_products_mapping b on a.order_id = b.order_id " +
            "join products c on b.product_id = c.product_id " +
            "GROUP BY a.order_id;", nativeQuery = true)
    List<OrderSummary> getOrderWithSumPriceAndSumCnt();
}
