package com.example.fashioncoordinator.unit.customer;

import com.example.fashioncoordinator.db.ProductEntity;
import com.example.fashioncoordinator.db.ProductJpaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class ProductRepositoryUnitTest {

    @Autowired
    private ProductJpaRepository productJpaRepository;

    @Nested
    @DisplayName("상품 가격 총합이 최저인 브랜드&상품 정보 조회")
    public class LowestPriceBrandTest {

        @Test
        @DisplayName("초기 데이터에서 조회")
        public void testFromInitialData() {
            // given

            // when
            ProductEntity productEntity = productJpaRepository.findLowestPriceBrand().orElseThrow();

            // then
            String actual = productEntity.getBrand();
            Assertions.assertEquals("D", actual);

        }

        @Test
        @DisplayName("최저가 브랜드 입점")
        public void testWithNewCheapestBrand() {
            // given
            ProductEntity newProductEntity = ProductEntity.builder()
                .brand("test")
                .tops(1000)
                .outerwear(1000)
                .pants(1000)
                .sneakers(1000)
                .bag(1000)
                .hat(1000)
                .socks(1000)
                .accessories(1000)
                .build();
            productJpaRepository.save(newProductEntity);

            // when
            ProductEntity productEntity = productJpaRepository.findLowestPriceBrand().orElseThrow();

            // then
            Assertions.assertEquals(newProductEntity.getBrand(), productEntity.getBrand());

        }

        @Test
        @DisplayName("상품이 부족한 최저가 브랜드 입점")
        public void testWithProductMissingBrand() {
            // given
            ProductEntity newProductEntity = ProductEntity.builder()
                .brand("test")
                .tops(1000)
                .outerwear(1000)
                .pants(1000)
                .sneakers(1000)
                .bag(1000)
                .hat(1000)
                .socks(1000)
                .accessories(null)
                .build();
            productJpaRepository.save(newProductEntity);

            // when
            ProductEntity productEntity = productJpaRepository.findLowestPriceBrand().orElseThrow();

            // then
            Assertions.assertEquals("D", productEntity.getBrand());

        }

    }

}
