package com.example.fashioncoordinator.api.customer.response;

import com.example.fashioncoordinator.db.ProductEntity;
import com.example.fashioncoordinator.enums.ProductCategory;
import com.example.fashioncoordinator.serializer.NumberWithCommaSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LowestPriceBrandProductResponseDto {

    @Getter
    @Builder
    @Schema(name = "상품:카테고리,가격")
    public static class ProductResponseDto {

        @JsonProperty("카테고리")
        private ProductCategory category;

        @JsonProperty("가격")
        @Schema(implementation = String.class)
        @JsonSerialize(using = NumberWithCommaSerializer.class)
        private int price;
    }

    @JsonProperty("브랜드")
    private String brand;

    @JsonProperty("카테고리")
    private List<ProductResponseDto> productList;

    @JsonProperty("총액")
    @Schema(implementation = String.class)
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private int totalPrice;

    public static LowestPriceBrandProductResponseDto from(ProductEntity product) {
        int totalPrice = 0;
        List<LowestPriceBrandProductResponseDto.ProductResponseDto> productList = new ArrayList<>();
        for (ProductCategory category : ProductCategory.values()) {
            Integer price = category.getPrice(product);
            productList.add(LowestPriceBrandProductResponseDto.ProductResponseDto.builder()
                .category(category)
                .price(price)
                .build()
            );

            totalPrice += price;
        }

        return LowestPriceBrandProductResponseDto.builder()
            .brand(product.getBrand())
            .productList(productList)
            .totalPrice(totalPrice)
            .build();
    }

}
