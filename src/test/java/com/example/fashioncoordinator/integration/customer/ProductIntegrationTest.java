package com.example.fashioncoordinator.integration.customer;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
            get("/product/lowest-prices")
        );

        // then
        perform
            .andExpect(status().isOk())
            .andExpect(content().json(objectMapper.writeValueAsString(expected)))
            .andDo(print());

    }
}
