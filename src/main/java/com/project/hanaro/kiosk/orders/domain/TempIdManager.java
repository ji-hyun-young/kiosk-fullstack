package com.project.hanaro.kiosk.orders.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "temp_id_manager")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TempIdManager {

    private static final Integer MAX_VALUE = 9999;
    private static final Integer INIT_VALUE = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="current_temp_id")
    private Integer currentTempId;

    public Long getId(){
        return id;
    }

    public Integer getCurrentTempId() {
        Integer tmp = currentTempId >= MAX_VALUE ? INIT_VALUE : currentTempId + INIT_VALUE;
        setCurrentTempId(tmp);
        return currentTempId;
    }

    public void setCurrentTempId(Integer currentTempId) {
        this.currentTempId = currentTempId;
    }

    @Builder
    public TempIdManager(Integer currentTempId) {
        this.currentTempId = currentTempId;
    }
}