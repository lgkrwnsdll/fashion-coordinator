package com.example.fashioncoordinator.customer;

import com.example.fashioncoordinator.customer.response.LowestPriceCombinationResponseDto;
import io.swagger.v3.oas.annotations.Operation;
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
    @GetMapping("/lowest-prices")
    public ResponseEntity<LowestPriceCombinationResponseDto> getLowestPriceCombination() {
        LowestPriceCombinationResponseDto lowestPriceCombination = productService.getLowestPriceCombination();
        return ResponseEntity.ok(lowestPriceCombination);
    }
}
