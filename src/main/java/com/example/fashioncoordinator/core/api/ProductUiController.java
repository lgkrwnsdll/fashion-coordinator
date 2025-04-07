package com.example.fashioncoordinator.core.api;

import com.example.fashioncoordinator.core.api.response.MaxMinPriceProductResponseDto;
import com.example.fashioncoordinator.core.domain.CategoryMinPriceProduct;
import com.example.fashioncoordinator.core.domain.MinTotalPriceBrandProduct;
import com.example.fashioncoordinator.core.domain.ProductService;
import com.example.fashioncoordinator.enums.ProductCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/ui/products")
public class ProductUiController {

    private final ProductService productService;

    @GetMapping("/cheapest-by-category")
    public String viewCheapestProductsForAllCategories(Model model) {
        CategoryMinPriceProduct product = productService.getCheapestProductsForAllCategories();

        model.addAttribute("data", product);

        return "core/cheapest-by-category";
    }

    @GetMapping("/cheapest-brand")
    public String viewCheapestBrandProducts(Model model) {
        MinTotalPriceBrandProduct product = productService.getCheapestBrandProducts();

        model.addAttribute("data", product);

        return "core/cheapest-brand";
    }

    @GetMapping("/categories/{category}/price-range")
    public String viewMaxAndMinPriceProducts(@PathVariable ProductCategory category, Model model) {
        MaxMinPriceProductResponseDto response = MaxMinPriceProductResponseDto.from(
            productService.getMaxAndMinPriceProducts(category)
        );

        model.addAttribute("data", response);
        model.addAttribute("selectedCategory", category);

        return "core/category-price-range";
    }

    @ModelAttribute
    public void addDefaultAttributes(Model model) {
        model.addAttribute("defaultCategory", ProductCategory.TOPS.getKoreanName());
    }

}
