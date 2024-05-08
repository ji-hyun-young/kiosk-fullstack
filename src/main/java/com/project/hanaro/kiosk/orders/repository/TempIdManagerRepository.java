package com.project.hanaro.kiosk.orders.repository;

import com.project.hanaro.kiosk.orders.domain.TempIdManager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TempIdManagerRepository extends JpaRepository<TempIdManager, Long> {
}
