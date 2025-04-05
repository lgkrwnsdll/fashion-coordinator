package com.example.fashioncoordinator.admin.domain;

import com.example.fashioncoordinator.db.query.PriceUpdateQueryDto;
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

    public PriceUpdateQueryDto toUpdatePriceQuery() {
        return PriceUpdateQueryDto.builder()
            .tops(tops)
            .outerwear(outerwear)
            .pants(pants)
            .sneakers(sneakers)
            .bag(bag)
            .hat(hat)
            .socks(socks)
            .accessories(accessories)
            .build();
    }

}
