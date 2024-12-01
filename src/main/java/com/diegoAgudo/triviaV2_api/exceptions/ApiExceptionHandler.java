package com.diegoAgudo.triviaV2_api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionHandler {

    /* 500 */
    @ExceptionHandler(ErrorProcessException.class)
    @ResponseBody
    public ResponseEntity handleErrorProcessException(ErrorProcessException e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .body(Map.of("error", "Internal Server Error", "message", e.getMessage()));
    }

    /* Elementos no encontrados */
    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    public ResponseEntity handleNotFoundException(NotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value())
                .body(Map.of("error", "Not Found", "message", e.getMessage()));
    }

    // Este metodo maneja los errores que se generan en las @Validations
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity handleArgumentNotValidException(MethodArgumentNotValidException notValidEx){
        List<String> errorsList = notValidEx.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
                .body(errorsList);
    }
}
