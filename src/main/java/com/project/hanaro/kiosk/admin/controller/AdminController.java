package com.project.hanaro.kiosk.admin.controller;

import com.project.hanaro.kiosk.admin.service.AdminService;
import com.project.hanaro.kiosk.members.dto.MemberGetResponse;
import com.project.hanaro.kiosk.members.service.MemberService;
import com.project.hanaro.kiosk.orders.dto.OrderGetResponse;
import com.project.hanaro.kiosk.orders.service.OrderService;
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
    private final MemberService memberService;
    private final OrderService orderService;

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

    @GetMapping("/admin/member/mem-upd/{memberId}")
    public String adminMemberUpdatePage(@PathVariable Long memberId, Model model){
        MemberGetResponse member = memberService.findMember(memberId);
        model.addAttribute("member", member);
        return "/admin/member_management_update";
    }

    @GetMapping("/admin/order")
    public String adminOrderPage(){
        return "/admin/order_management";
    }

    @GetMapping("/admin/order/ord-upd/{orderId}")
    public String adminOrderUpdatePage(@PathVariable Long orderId, Model model){
        OrderGetResponse order = orderService.findOrder(orderId);
        model.addAttribute("order", order);
        return "/admin/order_management_update";
    }
}
