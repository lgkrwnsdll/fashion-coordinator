package com.example.fashioncoordinator.api.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/product")
public class AdminProductController {

    private final AdminProductService adminProductService;

    @Operation(summary = "브랜드 추가 및 상품 추가/수정/삭제", description = "존재하는 브랜드는 덮어쓰기, 신규 브랜드는 전체 생성")
    @ApiResponse(responseCode = "201")
    @PostMapping("")
    public ResponseEntity<ManageProductResponseDto> manageProduct(
        @Valid @RequestBody ManageProductRequestDto requestDto) {
        adminProductService.manageProduct(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ManageProductResponseDto.from("적용 완료"));
    }
}
