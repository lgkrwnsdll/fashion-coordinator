package com.example.fashioncoordinator.core.domain;

import com.example.fashioncoordinator.enums.ProductCategory;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class HighestLowestPriceBrand {

    private ProductCategory category;

    private List<Product> lowestPriceProductList;

    private List<Product> highestPriceProductList;

}
