package com.example.fashioncoordinator.exception;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice(annotations = RestController.class)
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    private ResponseEntity<Object> handleExceptionInternal(ErrorCode errorCode) {
        return ResponseEntity.status(errorCode.getHttpStatus())
            .body(ErrorResponseDto.builder()
                .code(errorCode.name())
                .message(errorCode.getMessage())
                .build()
            );
    }

    private ResponseEntity<Object> handleExceptionInternal(ErrorCode errorCode, String message) {
        return ResponseEntity.status(errorCode.getHttpStatus())
            .body(ErrorResponseDto.builder()
                .code(errorCode.name())
                .message(message)
                .build()
            );
    }

    private ResponseEntity<Object> handleExceptionInternal(MethodArgumentNotValidException e,
        ErrorCode errorCode) {
        List<ErrorResponseDto.ValidationError> validationErrorList = e.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(ErrorResponseDto.ValidationError::of)
            .collect(Collectors.toList());

        return ResponseEntity.status(errorCode.getHttpStatus())
            .body(ErrorResponseDto.builder()
                .code(errorCode.name())
                .message(errorCode.getMessage())
                .errors(validationErrorList)
                .build()
            );
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(
        MethodArgumentNotValidException e, HttpHeaders headers, HttpStatusCode status,
        WebRequest request
    ) {
        ErrorCode errorCode = CommonErrorCode.UNPROCESSABLE_ENTITY;
        return handleExceptionInternal(e, errorCode);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> handleCustomException(CustomException e) {
        ErrorCode errorCode = e.getErrorCode();
        String message = e.getMessage();
        if (message == null) {
            return handleExceptionInternal(errorCode);
        } else {
            return handleExceptionInternal(errorCode, message);
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllException(Exception e) {
        // TODO 에러 내역 로깅
        ErrorCode errorCode = CommonErrorCode.INTERNAL_SERVER_ERROR;
        return handleExceptionInternal(errorCode, errorCode.getMessage());
    }
}
