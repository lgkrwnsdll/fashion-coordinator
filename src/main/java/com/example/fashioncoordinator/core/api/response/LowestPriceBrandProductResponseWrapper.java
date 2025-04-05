package com.example.fashioncoordinator.core.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LowestPriceBrandProductResponseWrapper {

    @JsonProperty("최저가")
    private LowestPriceBrandProductResponseDto responseDto;

    public static LowestPriceBrandProductResponseWrapper from(
        LowestPriceBrandProductResponseDto responseDto) {
        return builder().responseDto(responseDto).build();
    }

}
