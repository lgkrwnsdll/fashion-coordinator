package com.example.fashioncoordinator.exception;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.OBJECT;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@JsonFormat(shape = OBJECT)
@AllArgsConstructor
@Getter
public enum ProductErrorCode implements ErrorCode {
    DATA_MISSING(HttpStatus.NOT_FOUND, "상품 데이터가 부족합니다."),
    ;

    private final HttpStatus httpStatus;
    private final String message;
}

