package com.project.hanaro.kiosk.admin.controller;

import com.project.hanaro.kiosk.admin.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/admin")
    public String adminPage() {
        return "index";
    }

    @GetMapping("/admin/member")
    public String adminMemberPage(){
        return "/admin/member_management";
    }

    @GetMapping("/admin/product")
    public String adminProductPage(){
        return "/admin/product_management";
    }

    @GetMapping("/admin/order")
    public String adminOrderPage(){
        return "/admin/order_management";
    }
}
