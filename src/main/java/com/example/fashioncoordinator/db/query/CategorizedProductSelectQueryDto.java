package com.example.fashioncoordinator.db.query;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CategorizedProductSelectQueryDto {

    private String brand;
    private int price;
}
