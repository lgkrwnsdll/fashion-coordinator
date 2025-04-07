package com.example.fashioncoordinator.exception;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(annotations = Controller.class)
public class CustomUiExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public String handleException(CustomException ex, Model model) {
        ErrorCode errorCode = ex.getErrorCode();

        model.addAttribute("status", errorCode.getHttpStatus().value());
        model.addAttribute("reasonPhrase", errorCode.getHttpStatus().getReasonPhrase());
        model.addAttribute("message", errorCode.getMessage());

        return "error";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Model model) {
        ErrorCode errorCode = CommonErrorCode.INTERNAL_SERVER_ERROR;

        model.addAttribute("status", errorCode.getHttpStatus().value());
        model.addAttribute("reasonPhrase", errorCode.getHttpStatus().getReasonPhrase());
        model.addAttribute("message", errorCode.getMessage());

        return "error";
    }
}
