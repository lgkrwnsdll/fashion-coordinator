package com.example.fashioncoordinator.core.domain;

import com.example.fashioncoordinator.db.query.CategorizedProductSelectQueryDto;
import com.example.fashioncoordinator.enums.ProductCategory;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@EqualsAndHashCode
public class Product {

    private ProductCategory category;

    private String brand;

    private int price;

    public static Product from(CategorizedProductSelectQueryDto queryDto) {
        return Product.builder()
            .brand(queryDto.getBrand())
            .price(queryDto.getPrice())
            .build();
    }
}
