package com.project.hanaro.kiosk.products.vo;

public enum ProductOption {

    SINGLE("SINGLE"), SET("SET");

    private final String option;

    ProductOption(String option) {
        this.option = option;
    }

    public String getOption(){
        return option;
    }
}
