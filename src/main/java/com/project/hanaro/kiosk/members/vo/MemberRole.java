package com.project.hanaro.kiosk.members.vo;

public enum MemberRole {

    ADMIN("ADMIN"), USER("USER");

    private final String role;

    MemberRole(String role) {
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
