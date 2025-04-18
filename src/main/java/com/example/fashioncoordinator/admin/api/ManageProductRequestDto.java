package com.example.fashioncoordinator.admin.api;

import com.example.fashioncoordinator.admin.domain.BrandPriceInfo;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ManageProductRequestDto {

    @NotBlank
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
