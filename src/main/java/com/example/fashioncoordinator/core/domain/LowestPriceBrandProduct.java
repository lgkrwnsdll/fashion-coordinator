package com.example.fashioncoordinator.core.domain;

import com.example.fashioncoordinator.db.ProductEntity;
import com.example.fashioncoordinator.enums.ProductCategory;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LowestPriceBrandProduct {

    private String brand;

    private List<Product> productList;

    private int totalPrice;

    public static LowestPriceBrandProduct from(ProductEntity product) {
        int totalPrice = 0;
        List<Product> productList = new ArrayList<>();
        for (ProductCategory category : ProductCategory.values()) {
            Integer price = category.getPrice(product);
            productList.add(Product.builder()
                .category(category)
                .price(price)
                .build()
            );

            totalPrice += price;
        }

        return LowestPriceBrandProduct.builder()
            .brand(product.getBrand())
            .productList(productList)
            .totalPrice(totalPrice)
            .build();
    }

}
