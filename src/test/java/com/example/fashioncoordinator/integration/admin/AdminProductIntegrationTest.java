package com.example.fashioncoordinator.integration.admin;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.fashioncoordinator.api.admin.ManageProductRequestDto;
import com.example.fashioncoordinator.api.admin.ManageProductResponseDto;
import com.example.fashioncoordinator.db.ProductEntity;
import com.example.fashioncoordinator.db.ProductJpaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class AdminProductIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProductJpaRepository productJpaRepository;

    @ParameterizedTest
    @ValueSource(strings = {"A", "newBrand"})
    @DisplayName("브랜드 추가 및 상품 추가/수정/삭제 - 201 성공")
    public void testManageProduct_200(String brand) throws Exception {
        // given
        ManageProductRequestDto requestDto = new ManageProductRequestDto();
        ReflectionTestUtils.setField(requestDto, "brand", brand);

        ManageProductResponseDto expected = ManageProductResponseDto.from("적용 완료");

        // when
        ResultActions perform = mockMvc.perform(
            post("/admin/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto))
        );

        // then
        perform
            .andExpect(status().isCreated())
            .andExpect(content().json(objectMapper.writeValueAsString(expected)))
            .andDo(print());

        ProductEntity productEntity = productJpaRepository.findByBrand(brand).orElse(null);
        Assertions.assertNotNull(productEntity);

    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"", " "})
    @DisplayName("브랜드 추가 및 상품 추가/수정/삭제 - 422 실패")
    public void testManageProduct_422(String brand) throws Exception {
        // given
        ManageProductRequestDto requestDto = new ManageProductRequestDto();
        ReflectionTestUtils.setField(requestDto, "brand", brand);

        // when
        ResultActions perform = mockMvc.perform(
            post("/admin/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto))
        );

        // then
        perform
            .andExpect(status().isUnprocessableEntity())
            .andDo(print());

    }

}
