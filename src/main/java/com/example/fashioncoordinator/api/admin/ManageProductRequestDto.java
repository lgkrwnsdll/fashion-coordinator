package com.example.fashioncoordinator.api.admin;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ManageProductRequestDto {

    @NotEmpty
    private String brand;

    private Integer tops;

    private Integer outerwear;

    private Integer pants;

    private Integer sneakers;

    private Integer bag;

    private Integer hat;

    private Integer socks;

    private Integer accessories;

    public BrandPriceInfo toPriceInfo() {
        return BrandPriceInfo.builder()
            .tops(getTops())
            .outerwear(getOuterwear())
            .pants(getPants())
            .sneakers(getSneakers())
            .bag(getBag())
            .hat(getHat())
            .socks(getSocks())
            .accessories(getAccessories())
            .build();
    }

}
