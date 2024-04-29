package com.project.hanaro.kiosk.products.vo;

public enum ProductType {

    BURGER("BURGER"),
    HAPPY_MEAL("HAPPY_MEAL"),
    COFFEE("COFFEE"),
    DESSERT("DESSERT"),
    DRINK("DRINK");

    private final String type;

    ProductType(String type) {
        this.type = type;
    }

    public String getType(){
        return type;
    }
}
