package com.example.fashioncoordinator.admin.domain;

import com.example.fashioncoordinator.db.ProductEntity;
import com.example.fashioncoordinator.db.ProductJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminProductService {

    private final ProductJpaRepository productJpaRepository;

    @Transactional
    public void manageProduct(String brand, BrandPriceInfo brandPriceInfo) {
        ProductEntity productEntity = productJpaRepository.findByBrand(brand)
            .orElse(null);

        if (productEntity != null) {
            productEntity.updateAllCategory(brandPriceInfo.toUpdatePriceQuery());
        } else {
            ProductEntity newProductEntity = ProductEntity.builder()
                .brand(brand)
                .tops(brandPriceInfo.getTops())
                .outerwear(brandPriceInfo.getOuterwear())
                .pants(brandPriceInfo.getPants())
                .sneakers(brandPriceInfo.getSneakers())
                .bag(brandPriceInfo.getBag())
                .hat(brandPriceInfo.getHat())
                .socks(brandPriceInfo.getSocks())
                .accessories(brandPriceInfo.getAccessories())
                .build();

            productJpaRepository.save(newProductEntity);
        }
    }
}
