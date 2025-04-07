package com.example.fashioncoordinator.db.query;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PriceUpdateQueryDto {

    private Integer tops;

    private Integer outerwear;

    private Integer pants;

    private Integer sneakers;

    private Integer bag;

    private Integer hat;

    private Integer socks;

    private Integer accessories;

}
