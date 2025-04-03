package com.example.fashioncoordinator.exception;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.OBJECT;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@JsonFormat(shape = OBJECT)
@AllArgsConstructor
@Getter
public enum CommonErrorCode implements ErrorCode {
    UNPROCESSABLE_ENTITY(HttpStatus.UNPROCESSABLE_ENTITY, "요청 데이터를 확인해주세요."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러 발생!"),
    ;

    private final HttpStatus httpStatus;
    private final String message;
}

