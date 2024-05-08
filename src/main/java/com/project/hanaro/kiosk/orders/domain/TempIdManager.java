package com.project.hanaro.kiosk.orders.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "temp_id_manager")
@NoArgsConstructor
@Getter
@Setter
public class TempIdManager {
    @Id
    private Long id;

    @Column(name="current_temp_id")
    private Integer currentTempId = 1; // 초기값 1

    public TempIdManager(Long id) {
        this.id = id;
    }
}