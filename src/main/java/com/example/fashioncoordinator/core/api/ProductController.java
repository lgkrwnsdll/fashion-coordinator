package com.example.fashioncoordinator.core.api;

import com.example.fashioncoordinator.core.api.response.MaxMinPriceProductResponseDto;
import com.example.fashioncoordinator.core.api.response.MinTotalPriceBrandProductResponseDto;
import com.example.fashioncoordinator.core.api.response.MinTotalPriceBrandResponseWrapper;
import com.example.fashioncoordinator.core.api.response.CategoryMinPriceProductResponseDto;
import com.example.fashioncoordinator.core.domain.ProductService;
import com.example.fashioncoordinator.enums.ProductCategory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "카테고리 별 최저가 상품 조회")
    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "상품 데이터 부족", content = @Content)
        }
    )
    @GetMapping("/cheapest-by-category")
    public ResponseEntity<CategoryMinPriceProductResponseDto> getCheapestProductsForAllCategories() {
        return ResponseEntity.ok(
            CategoryMinPriceProductResponseDto.from(
                productService.getCheapestProductsForAllCategories()));
    }

    @Operation(summary = "최저가 단일 브랜드의 상품 조회")
    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "상품 데이터 부족", content = @Content)
        }
    )
    @GetMapping("/cheapest-brand")
    public ResponseEntity<MinTotalPriceBrandResponseWrapper> getCheapestBrandProducts() {
        return ResponseEntity.ok(
            MinTotalPriceBrandResponseWrapper.from(
                MinTotalPriceBrandProductResponseDto.from(
                    productService.getCheapestBrandProducts())));
    }

    @Operation(summary = "특정 카테고리의 최고 및 최저가 상품 조회")
    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "상품 데이터 부족", content = @Content)
        }
    )
    @GetMapping("/categories/{category}/price-range")
    public ResponseEntity<MaxMinPriceProductResponseDto> getMaxAndMinPriceProducts(
        @PathVariable ProductCategory category) {
        return ResponseEntity.ok(
            MaxMinPriceProductResponseDto.from(
                productService.getMaxAndMinPriceProducts(category)));
    }
}
