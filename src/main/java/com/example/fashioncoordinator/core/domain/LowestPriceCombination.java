package com.example.fashioncoordinator.core.domain;

import java.util.List;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@EqualsAndHashCode
public class LowestPriceCombination {

    private List<Product> productList;

    private int totalPrice;

}
