package com.example.fashioncoordinator.integration.customer;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.fashioncoordinator.api.customer.response.LowestPriceBrandProductResponseDto;
import com.example.fashioncoordinator.api.customer.response.LowestPriceBrandProductResponseWrapper;
import com.example.fashioncoordinator.api.customer.response.LowestPriceCombinationResponseDto;
import com.example.fashioncoordinator.enums.ProductCategory;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("카테고리 별 최저가격 브랜드 조회")
    public void testGetLowestPriceCombination() throws Exception {
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

        // when
        ResultActions perform = mockMvc.perform(
            get("/product/lowest-price")
        );

        // then
        perform
            .andExpect(status().isOk())
            .andExpect(content().json(objectMapper.writeValueAsString(expected)))
            .andDo(print());

    }

    @Test
    @DisplayName("최저가 단일 브랜드 조회")
    public void testGetLowestPriceBrandProducts() throws Exception {
        // given
        LowestPriceBrandProductResponseDto responseDto = LowestPriceBrandProductResponseDto.builder()
            .brand("D")
            .productList(List.of(
                LowestPriceBrandProductResponseDto.ProductResponseDto.builder()
                    .category(ProductCategory.TOPS)
                    .price(10100)
                    .build(),
                LowestPriceBrandProductResponseDto.ProductResponseDto.builder()
                    .category(ProductCategory.OUTERWEAR)
                    .price(5100)
                    .build(),
                LowestPriceBrandProductResponseDto.ProductResponseDto.builder()
                    .category(ProductCategory.PANTS)
                    .price(3000)
                    .build(),
                LowestPriceBrandProductResponseDto.ProductResponseDto.builder()
                    .category(ProductCategory.SNEAKERS)
                    .price(9500)
                    .build(),
                LowestPriceBrandProductResponseDto.ProductResponseDto.builder()
                    .category(ProductCategory.BAG)
                    .price(2500)
                    .build(),
                LowestPriceBrandProductResponseDto.ProductResponseDto.builder()
                    .category(ProductCategory.HAT)
                    .price(1500)
                    .build(),
                LowestPriceBrandProductResponseDto.ProductResponseDto.builder()
                    .category(ProductCategory.SOCKS)
                    .price(2400)
                    .build(),
                LowestPriceBrandProductResponseDto.ProductResponseDto.builder()
                    .category(ProductCategory.ACCESSORIES)
                    .price(2000)
                    .build()
            ))
            .totalPrice(36100)
            .build();

        LowestPriceBrandProductResponseWrapper expected = LowestPriceBrandProductResponseWrapper.from(
            responseDto);

        // when
        ResultActions perform = mockMvc.perform(
            get("/product/lowest-price/brand")
        );

        // then
        perform
            .andExpect(status().isOk())
            .andExpect(content().json(objectMapper.writeValueAsString(expected)))
            .andDo(print());

    }
}
