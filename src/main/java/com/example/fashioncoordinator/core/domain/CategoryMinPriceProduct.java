package com.example.fashioncoordinator.core.domain;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CategoryMinPriceProduct {

    private List<Product> productList;

    private int totalPrice;

}
