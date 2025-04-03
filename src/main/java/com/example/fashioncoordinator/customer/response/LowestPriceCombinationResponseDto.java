package com.example.fashioncoordinator.customer.response;

import com.example.fashioncoordinator.enums.ProductCategory;
import com.example.fashioncoordinator.serializer.NumberWithCommaSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LowestPriceCombinationResponseDto {

    @Getter
    @Builder
    public static class ProductResponseDto {

        @JsonProperty("카테고리")
        private ProductCategory category;
        @JsonProperty("브랜드")
        private String brand;
        @JsonProperty("가격")
        @JsonSerialize(using = NumberWithCommaSerializer.class)
        private int price;
    }

    @JsonProperty("상품")
    private List<ProductResponseDto> productList;
    @JsonProperty("총액")
    @JsonSerialize(using = NumberWithCommaSerializer.class)
    private int totalPrice;

}
