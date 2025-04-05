package com.example.fashioncoordinator.core.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MinTotalPriceBrandResponseWrapper {

    @JsonProperty("최저가")
    private MinTotalPriceBrandProductResponseDto responseDto;

    public static MinTotalPriceBrandResponseWrapper from(
        MinTotalPriceBrandProductResponseDto responseDto) {
        return builder().responseDto(responseDto).build();
    }

}
