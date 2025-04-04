package com.example.fashioncoordinator.api.admin;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ManageProductResponseDto {

    private String message;

    public static ManageProductResponseDto from(String message) {
        return ManageProductResponseDto.builder().message(message).build();
    }

}
