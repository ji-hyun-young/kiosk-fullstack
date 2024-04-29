package com.project.hanaro.kiosk.orders.vo;

public enum OrderStatus {
    READY("READY"),
    PICK_UP("PICK_UP");

    private final String status;

    OrderStatus(String type) {
        this.status = type;
    }

    public String getStatus(){
        return status;
    }
}
