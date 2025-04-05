package com.example.fashioncoordinator.core.api.response;

import com.example.fashioncoordinator.core.api.serializer.NumberWithCommaSerializer;
import com.example.fashioncoordinator.core.domain.LowestPriceBrandProduct;
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
public class LowestPriceBrandProductResponseDto {

    @Getter
    @Builder
    public static class ProductResponseDto {

        @JsonProperty("카테고리")
        private ProductCategory category;

        @JsonProperty("가격")
        @Schema(implementation = String.class)
        @JsonSerialize(using = NumberWithCommaSerializer.class)
        private int price;

        public static ProductResponseDto from(Product product) {
            return ProductResponseDto.builder()
                .category(product.getCategory())
                .price(product.getPrice())
                .build();
        }
    }

    @JsonProperty("브랜드")
    private String brand;

    @JsonProperty("카테고리")
    private List<ProductResponseDto> productList;

    @JsonProperty("총액")
    @Schema(implementation = String.class)
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private int totalPrice;

    public static LowestPriceBrandProductResponseDto from(
        LowestPriceBrandProduct lowestPriceBrandProduct) {
        return LowestPriceBrandProductResponseDto.builder()
            .brand(lowestPriceBrandProduct.getBrand())
            .productList(
                lowestPriceBrandProduct.getProductList().stream().map(ProductResponseDto::from)
                    .toList())
            .totalPrice(lowestPriceBrandProduct.getTotalPrice())
            .build();
    }

}
