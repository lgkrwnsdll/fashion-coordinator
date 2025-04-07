package com.example.fashioncoordinator.admin.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ui/admin/products")
public class AdminProductUiController {

    @GetMapping
    public String viewProductManagement() {
        return "admin-product";
    }

}
