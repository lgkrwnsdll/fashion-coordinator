package com.example.fashioncoordinator.admin.domain;

import com.example.fashioncoordinator.admin.api.ManageProductRequestDto;
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
    public void manageProduct(ManageProductRequestDto requestDto) {
        ProductEntity productEntity = productJpaRepository.findByBrand(requestDto.getBrand())
            .orElse(null);

        if (productEntity != null) {
            productEntity.updateAllCategory(requestDto.toPriceInfo());
        } else {
            ProductEntity newProductEntity = ProductEntity.builder()
                .brand(requestDto.getBrand())
                .tops(requestDto.getTops())
                .outerwear(requestDto.getOuterwear())
                .pants(requestDto.getPants())
                .sneakers(requestDto.getSneakers())
                .bag(requestDto.getBag())
                .hat(requestDto.getHat())
                .socks(requestDto.getSocks())
                .accessories(requestDto.getAccessories())
                .build();

            productJpaRepository.save(newProductEntity);
        }
    }
}
