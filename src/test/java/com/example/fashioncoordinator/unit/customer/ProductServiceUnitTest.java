package com.example.fashioncoordinator.unit.customer;


import static org.mockito.BDDMockito.given;

import com.example.fashioncoordinator.api.customer.ProductService;
import com.example.fashioncoordinator.api.customer.response.LowestPriceCombinationResponseDto;
import com.example.fashioncoordinator.db.ProductEntity;
import com.example.fashioncoordinator.db.ProductJpaRepository;
import com.example.fashioncoordinator.enums.ProductCategory;
import com.example.fashioncoordinator.exception.CustomException;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ProductServiceUnitTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductJpaRepository productJpaRepository;

    @Nested
    @DisplayName("전체 상품 데이터에서 카테고리 별 최저가격 브랜드 추출")
    public class LowestPriceCombinationTest {

        @Test
        @DisplayName("추출 성공")
        public void testWithEnoughProduct() {
            // given
            LowestPriceCombinationResponseDto expected = LowestPriceCombinationResponseDto.builder()
                .productList(List.of(
                    LowestPriceCombinationResponseDto.ProductResponseDto.builder()
                        .category(ProductCategory.TOPS)
                        .brand("C")
                        .price(10_000)
                        .build(),
                    LowestPriceCombinationResponseDto.ProductResponseDto.builder()
                        .category(ProductCategory.OUTERWEAR)
                        .brand("E")
                        .price(5_000)
                        .build(),
                    LowestPriceCombinationResponseDto.ProductResponseDto.builder()
                        .category(ProductCategory.PANTS)
                        .brand("D")
                        .price(3_000)
                        .build(),
                    LowestPriceCombinationResponseDto.ProductResponseDto.builder()
                        .category(ProductCategory.SNEAKERS)
                        .brand("G")
                        .price(9_000)
                        .build(),
                    LowestPriceCombinationResponseDto.ProductResponseDto.builder()
                        .category(ProductCategory.BAG)
                        .brand("A")
                        .price(2_000)
                        .build(),
                    LowestPriceCombinationResponseDto.ProductResponseDto.builder()
                        .category(ProductCategory.HAT)
                        .brand("D")
                        .price(1_500)
                        .build(),
                    LowestPriceCombinationResponseDto.ProductResponseDto.builder()
                        .category(ProductCategory.SOCKS)
                        .brand("I")
                        .price(1_700)
                        .build(),
                    LowestPriceCombinationResponseDto.ProductResponseDto.builder()
                        .category(ProductCategory.ACCESSORIES)
                        .brand("F")
                        .price(1_900)
                        .build()
                ))
                .totalPrice(34_100)
                .build();

            List<ProductEntity> productEntityList = List.of(
                ProductEntity.builder().brand("A").tops(11200).outerwear(5500).pants(4200)
                    .sneakers(9000).bag(2000).hat(1700).socks(1800).accessories(2300).build(),
                ProductEntity.builder().brand("B").tops(10500).outerwear(5900).pants(3800)
                    .sneakers(9100).bag(2100).hat(2000).socks(2000).accessories(2200).build(),
                ProductEntity.builder().brand("C").tops(10000).outerwear(6200).pants(3300)
                    .sneakers(9200).bag(2200).hat(1900).socks(2200).accessories(2100).build(),
                ProductEntity.builder().brand("D").tops(10100).outerwear(5100).pants(3000)
                    .sneakers(9500).bag(2500).hat(1500).socks(2400).accessories(2000).build(),
                ProductEntity.builder().brand("E").tops(10700).outerwear(5000).pants(3800)
                    .sneakers(9900).bag(2300).hat(1800).socks(2100).accessories(2100).build(),
                ProductEntity.builder().brand("F").tops(11200).outerwear(7200).pants(4000)
                    .sneakers(9300).bag(2100).hat(1600).socks(2300).accessories(1900).build(),
                ProductEntity.builder().brand("G").tops(10500).outerwear(5800).pants(3900)
                    .sneakers(9000).bag(2200).hat(1700).socks(2100).accessories(2000).build(),
                ProductEntity.builder().brand("H").tops(10800).outerwear(6300).pants(3100)
                    .sneakers(9700).bag(2100).hat(1600).socks(2000).accessories(2000).build(),
                ProductEntity.builder().brand("I").tops(11400).outerwear(6700).pants(3200)
                    .sneakers(9500).bag(2400).hat(1700).socks(1700).accessories(2400).build()
            );
            given(productJpaRepository.findAll()).willReturn(productEntityList);

            // when
            LowestPriceCombinationResponseDto actual = productService.getLowestPriceCombination();

            // then
            Assertions.assertEquals(expected,actual);

        }

        @Test
        @DisplayName("추출 실패")
        public void testWithoutEnoughProduct() {
            // given
            List<ProductEntity> productEntityList = List.of(
                ProductEntity.builder().brand("A").tops(11200).outerwear(5500).pants(4200)
                    .sneakers(9000).bag(2000).hat(1700).socks(1800).build(),
                ProductEntity.builder().brand("B").tops(10500).outerwear(5900).pants(3800)
                    .sneakers(9100).bag(2100).hat(2000).socks(2000).build(),
                ProductEntity.builder().brand("C").tops(10000).outerwear(6200).pants(3300)
                    .sneakers(9200).bag(2200).hat(1900).socks(2200).build(),
                ProductEntity.builder().brand("D").tops(10100).outerwear(5100).pants(3000)
                    .sneakers(9500).bag(2500).hat(1500).socks(2400).build(),
                ProductEntity.builder().brand("E").tops(10700).outerwear(5000).pants(3800)
                    .sneakers(9900).bag(2300).hat(1800).socks(2100).build(),
                ProductEntity.builder().brand("F").tops(11200).outerwear(7200).pants(4000)
                    .sneakers(9300).bag(2100).hat(1600).socks(2300).build(),
                ProductEntity.builder().brand("G").tops(10500).outerwear(5800).pants(3900)
                    .sneakers(9000).bag(2200).hat(1700).socks(2100).build(),
                ProductEntity.builder().brand("H").tops(10800).outerwear(6300).pants(3100)
                    .sneakers(9700).bag(2100).hat(1600).socks(2000).build(),
                ProductEntity.builder().brand("I").tops(11400).outerwear(6700).pants(3200)
                    .sneakers(9500).bag(2400).hat(1700).socks(1700).build()
            );
            given(productJpaRepository.findAll()).willReturn(productEntityList);

            // when

            // then
            Assertions.assertThrows(CustomException.class, productService::getLowestPriceCombination);

        }
    }

}
