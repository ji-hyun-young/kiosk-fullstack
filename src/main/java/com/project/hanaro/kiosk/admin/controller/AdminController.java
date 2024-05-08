package com.project.hanaro.kiosk.admin.controller;

import com.project.hanaro.kiosk.admin.service.AdminService;
import com.project.hanaro.kiosk.products.dto.ProductGetResponse;
import com.project.hanaro.kiosk.products.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;
    private final ProductService productService;

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

    @GetMapping("/admin/product/prod-upd/{productId}")
    public String adminProductUpdatePage(@PathVariable Long productId, Model model){
        ProductGetResponse product = productService.findProduct(productId);
        model.addAttribute("product", product);
        return "/admin/product_management_update";
    }

    @GetMapping("/admin/order")
    public String adminOrderPage(){
        return "/admin/order_management";
    }
}
