package com.example.fashioncoordinator.core.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LowestTotalPriceBrandResponseWrapper {

    @JsonProperty("최저가")
    private LowestTotalPriceBrandResponseDto responseDto;

    public static LowestTotalPriceBrandResponseWrapper from(
        LowestTotalPriceBrandResponseDto responseDto) {
        return builder().responseDto(responseDto).build();
    }

}
