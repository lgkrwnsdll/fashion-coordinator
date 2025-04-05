package com.example.fashioncoordinator.core.api.response;

import com.example.fashioncoordinator.core.api.serializer.NumberWithCommaSerializer;
import com.example.fashioncoordinator.core.domain.HighestLowestPriceBrand;
import com.example.fashioncoordinator.core.domain.Product;
import com.example.fashioncoordinator.enums.ProductCategory;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class HighestLowestPriceBrandResponseDto {

    @Getter
    @Builder
    public static class ProductResponseDto {

        @JsonProperty("브랜드")
        private String brand;

        @JsonProperty("가격")
        @Schema(implementation = String.class)
        @JsonSerialize(using = NumberWithCommaSerializer.class)
        private int price;

        public static ProductResponseDto from(Product product) {
            return ProductResponseDto.builder()
                .brand(product.getBrand())
                .price(product.getPrice())
                .build();
        }
    }

    @JsonProperty("카테고리")
    private ProductCategory category;

    @JsonProperty("최저가")
    private List<ProductResponseDto> lowestPriceProductList;

    @JsonProperty("최고가")
    private List<ProductResponseDto> highestPriceProductList;

    public static HighestLowestPriceBrandResponseDto from(
        HighestLowestPriceBrand highestLowestPriceBrand) {
        return HighestLowestPriceBrandResponseDto.builder()
            .category(highestLowestPriceBrand.getCategory())
            .lowestPriceProductList(highestLowestPriceBrand.getLowestPriceProductList().stream()
                .map(ProductResponseDto::from).toList())
            .highestPriceProductList(highestLowestPriceBrand.getHighestPriceProductList().stream()
                .map(ProductResponseDto::from).toList())
            .build();
    }

}
