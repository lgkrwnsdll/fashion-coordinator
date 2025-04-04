package com.example.fashioncoordinator.db;

import com.example.fashioncoordinator.api.admin.BrandPriceInfo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "product")
public class ProductEntity extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String brand;

    private Integer tops;

    private Integer outerwear;

    private Integer pants;

    private Integer sneakers;

    private Integer bag;

    private Integer hat;

    private Integer socks;

    private Integer accessories;

    public void updateAllCategory(BrandPriceInfo brandPriceInfo) {
        this.tops = brandPriceInfo.getTops();
        this.outerwear = brandPriceInfo.getOuterwear();
        this.pants = brandPriceInfo.getPants();
        this.sneakers = brandPriceInfo.getSneakers();
        this.bag = brandPriceInfo.getBag();
        this.hat = brandPriceInfo.getHat();
        this.socks = brandPriceInfo.getSocks();
        this.accessories = brandPriceInfo.getAccessories();
    }

}
