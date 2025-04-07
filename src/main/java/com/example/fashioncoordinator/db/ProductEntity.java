package com.example.fashioncoordinator.db;

import com.example.fashioncoordinator.db.query.PriceUpdateQueryDto;
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

    public void updateAllCategory(PriceUpdateQueryDto queryDto) {
        this.tops = queryDto.getTops();
        this.outerwear = queryDto.getOuterwear();
        this.pants = queryDto.getPants();
        this.sneakers = queryDto.getSneakers();
        this.bag = queryDto.getBag();
        this.hat = queryDto.getHat();
        this.socks = queryDto.getSocks();
        this.accessories = queryDto.getAccessories();
    }

}
