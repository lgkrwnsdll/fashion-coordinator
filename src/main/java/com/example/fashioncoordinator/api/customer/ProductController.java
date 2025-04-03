package com.example.fashioncoordinator.api.customer;

import com.example.fashioncoordinator.api.customer.response.LowestPriceBrandProductResponseDto;
import com.example.fashioncoordinator.api.customer.response.LowestPriceBrandProductResponseWrapper;
import com.example.fashioncoordinator.api.customer.response.LowestPriceCombinationResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "product", description = "고객 API")
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
    public ResponseEntity<LowestPriceCombinationResponseDto> getLowestPriceCombination() {
        LowestPriceCombinationResponseDto lowestPriceCombination = productService.getLowestPriceCombination();
        return ResponseEntity.ok(lowestPriceCombination);
    }

    @Operation(summary = "최저가 단일 브랜드 조회")
    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "상품 데이터 부족", content = @Content)
        }
    )
    @GetMapping("/lowest-price/brand")
    public ResponseEntity<LowestPriceBrandProductResponseWrapper> getLowestPriceBrandProducts() {
        LowestPriceBrandProductResponseDto lowestPriceBrandProducts = productService.getLowestPriceBrandProducts();
        return ResponseEntity.ok(
            LowestPriceBrandProductResponseWrapper.from(lowestPriceBrandProducts));
    }
}
