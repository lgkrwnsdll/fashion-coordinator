package com.example.fashioncoordinator.integration.core;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.fashioncoordinator.core.api.response.MaxMinPriceProductResponseDto;
import com.example.fashioncoordinator.core.api.response.MinTotalPriceBrandProductResponseDto;
import com.example.fashioncoordinator.core.api.response.MinTotalPriceBrandResponseWrapper;
import com.example.fashioncoordinator.core.api.response.CategoryMinPriceProductResponseDto;
import com.example.fashioncoordinator.enums.ProductCategory;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ProductIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("카테고리 별 최저가 상품 조회 - 200 성공")
    public void testGetCheapestProductsForAllCategories_200() throws Exception {
        // given
        CategoryMinPriceProductResponseDto expected = CategoryMinPriceProductResponseDto.builder()
            .productList(List.of(
                CategoryMinPriceProductResponseDto.ProductResponseDto.builder()
                    .category(ProductCategory.TOPS)
                    .brand("C")
                    .price(10_000)
                    .build(),
                CategoryMinPriceProductResponseDto.ProductResponseDto.builder()
                    .category(ProductCategory.OUTERWEAR)
                    .brand("E")
                    .price(5_000)
                    .build(),
                CategoryMinPriceProductResponseDto.ProductResponseDto.builder()
                    .category(ProductCategory.PANTS)
                    .brand("D")
                    .price(3_000)
                    .build(),
                CategoryMinPriceProductResponseDto.ProductResponseDto.builder()
                    .category(ProductCategory.SNEAKERS)
                    .brand("G")
                    .price(9_000)
                    .build(),
                CategoryMinPriceProductResponseDto.ProductResponseDto.builder()
                    .category(ProductCategory.BAG)
                    .brand("A")
                    .price(2_000)
                    .build(),
                CategoryMinPriceProductResponseDto.ProductResponseDto.builder()
                    .category(ProductCategory.HAT)
                    .brand("D")
                    .price(1_500)
                    .build(),
                CategoryMinPriceProductResponseDto.ProductResponseDto.builder()
                    .category(ProductCategory.SOCKS)
                    .brand("I")
                    .price(1_700)
                    .build(),
                CategoryMinPriceProductResponseDto.ProductResponseDto.builder()
                    .category(ProductCategory.ACCESSORIES)
                    .brand("F")
                    .price(1_900)
                    .build()
            ))
            .totalPrice(34_100)
            .build();

        // when
        ResultActions perform = mockMvc.perform(
            get("/products/cheapest-by-category")
        );

        // then
        perform
            .andExpect(status().isOk())
            .andExpect(content().json(objectMapper.writeValueAsString(expected)))
            .andDo(print());

    }

    @Test
    @DisplayName("카테고리 별 최저가 상품 조회 - 404 실패")
    @Sql(scripts = "/clear-tops.sql")
    public void testGetCheapestProductsForAllCategories_404() throws Exception {
        // given

        // when
        ResultActions perform = mockMvc.perform(
            get("/products/cheapest-by-category")
        );

        // then
        perform
            .andExpect(status().isNotFound())
            .andDo(print());

    }

    @Test
    @DisplayName("최저가 단일 브랜드의 상품 조회 - 200 성공")
    public void testGetCheapestBrandProducts_200() throws Exception {
        // given
        MinTotalPriceBrandProductResponseDto responseDto = MinTotalPriceBrandProductResponseDto.builder()
            .brand("D")
            .productList(List.of(
                MinTotalPriceBrandProductResponseDto.ProductResponseDto.builder()
                    .category(ProductCategory.TOPS)
                    .price(10100)
                    .build(),
                MinTotalPriceBrandProductResponseDto.ProductResponseDto.builder()
                    .category(ProductCategory.OUTERWEAR)
                    .price(5100)
                    .build(),
                MinTotalPriceBrandProductResponseDto.ProductResponseDto.builder()
                    .category(ProductCategory.PANTS)
                    .price(3000)
                    .build(),
                MinTotalPriceBrandProductResponseDto.ProductResponseDto.builder()
                    .category(ProductCategory.SNEAKERS)
                    .price(9500)
                    .build(),
                MinTotalPriceBrandProductResponseDto.ProductResponseDto.builder()
                    .category(ProductCategory.BAG)
                    .price(2500)
                    .build(),
                MinTotalPriceBrandProductResponseDto.ProductResponseDto.builder()
                    .category(ProductCategory.HAT)
                    .price(1500)
                    .build(),
                MinTotalPriceBrandProductResponseDto.ProductResponseDto.builder()
                    .category(ProductCategory.SOCKS)
                    .price(2400)
                    .build(),
                MinTotalPriceBrandProductResponseDto.ProductResponseDto.builder()
                    .category(ProductCategory.ACCESSORIES)
                    .price(2000)
                    .build()
            ))
            .totalPrice(36100)
            .build();

        MinTotalPriceBrandResponseWrapper expected = MinTotalPriceBrandResponseWrapper.from(
            responseDto);

        // when
        ResultActions perform = mockMvc.perform(
            get("/products/cheapest-brand")
        );

        // then
        perform
            .andExpect(status().isOk())
            .andExpect(content().json(objectMapper.writeValueAsString(expected)))
            .andDo(print());

    }

    @Test
    @DisplayName("최저가 단일 브랜드의 상품 조회 - 404 실패")
    @Sql(scripts = "/clear-tops.sql")
    public void testGetCheapestBrandProducts_404() throws Exception {
        // given

        // when
        ResultActions perform = mockMvc.perform(
            get("/products/cheapest-brand")
        );

        // then
        perform
            .andExpect(status().isNotFound())
            .andDo(print());

    }

    @Test
    @DisplayName("특정 카테고리의 최고 및 최저가 상품 조회 - 200 성공")
    public void testGetMaxAndMinPriceProducts_200() throws Exception {
        // given
        ProductCategory category = ProductCategory.TOPS;

        MaxMinPriceProductResponseDto expected = MaxMinPriceProductResponseDto.builder()
            .category(category)
            .minPriceProductList(
                List.of(MaxMinPriceProductResponseDto.ProductResponseDto.builder().brand("C")
                    .price(10000).build()))
            .maxPriceProductList(
                List.of(MaxMinPriceProductResponseDto.ProductResponseDto.builder().brand("I")
                    .price(11400).build()))
            .build();

        // when
        ResultActions perform = mockMvc.perform(
            get("/products/categories/{category}/price-range", category.getKoreanName())
        );

        // then
        perform
            .andExpect(status().isOk())
            .andExpect(content().json(objectMapper.writeValueAsString(expected)))
            .andDo(print());

    }

    @Test
    @DisplayName("특정 카테고리의 최고 및 최저가 상품 조회 - 404 실패")
    @Sql(scripts = "/clear-tops.sql")
    public void testGetMaxAndMinPriceProducts_404() throws Exception {
        // given
        ProductCategory category = ProductCategory.TOPS;

        // when
        ResultActions perform = mockMvc.perform(
            get("/products/categories/{category}/price-range", category.getKoreanName())
        );

        // then
        perform
            .andExpect(status().isNotFound())
            .andDo(print());

    }

    @Test
    @DisplayName("특정 카테고리의 최고 및 최저가 상품 조회 - 422 실패")
    public void testGetMaxAndMinPriceProducts_422() throws Exception {
        // given

        // when
        ResultActions perform = mockMvc.perform(
            get("/products/categories/{category}/price-range", "anyString")
        );

        // then
        perform
            .andExpect(status().isUnprocessableEntity())
            .andDo(print());

    }
}
