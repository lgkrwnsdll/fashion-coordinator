package com.example.fashioncoordinator.api.admin;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BrandPriceInfo {

    private Integer tops;

    private Integer outerwear;

    private Integer pants;

    private Integer sneakers;

    private Integer bag;

    private Integer hat;

    private Integer socks;

    private Integer accessories;

}
