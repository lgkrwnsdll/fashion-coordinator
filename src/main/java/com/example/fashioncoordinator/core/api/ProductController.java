package com.example.fashioncoordinator.core.api;

import com.example.fashioncoordinator.core.api.response.HighestLowestPriceProductResponseDto;
import com.example.fashioncoordinator.core.api.response.LowestTotalPriceBrandResponseDto;
import com.example.fashioncoordinator.core.api.response.LowestTotalPriceBrandResponseWrapper;
import com.example.fashioncoordinator.core.api.response.CategoryLowestPriceProductResponseDto;
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
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "카테고리 별 최저가격 브랜드 조회")
    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "상품 데이터 부족", content = @Content)
        }
    )
    @GetMapping("/lowest-price")
    public ResponseEntity<CategoryLowestPriceProductResponseDto> getLowestPriceProductsForAllCategories() {
        return ResponseEntity.ok(
            CategoryLowestPriceProductResponseDto.from(
                productService.getLowestPriceProductsForAllCategories()));
    }

    @Operation(summary = "최저가 단일 브랜드 조회")
    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "상품 데이터 부족", content = @Content)
        }
    )
    @GetMapping("/lowest-price/brand")
    public ResponseEntity<LowestTotalPriceBrandResponseWrapper> getLowestPriceBrand() {
        return ResponseEntity.ok(
            LowestTotalPriceBrandResponseWrapper.from(
                LowestTotalPriceBrandResponseDto.from(
                    productService.getLowestPriceBrand())));
    }

    @Operation(summary = "특정 카테고리의 최고 및 최저가 브랜드 조회")
    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "상품 데이터 부족", content = @Content)
        }
    )
    @GetMapping("/category/{category}/price")
    public ResponseEntity<HighestLowestPriceProductResponseDto> getHighestAndLowestPriceProducts(
        @PathVariable ProductCategory category) {
        return ResponseEntity.ok(
            HighestLowestPriceProductResponseDto.from(
                productService.getHighestAndLowestPriceProducts(category)));
    }
}
