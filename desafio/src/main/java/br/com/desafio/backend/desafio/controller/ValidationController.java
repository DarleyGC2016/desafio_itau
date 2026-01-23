package br.com.desafio.backend.desafio.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ValidationController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationBadRequestExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex
        .getBindingResult()
        .getFieldErrors()
        .forEach(error ->
                    errors.put(
                        "Bad_Request",
                        "Erro ao cadastrar transação!")
        );
                
        return ResponseEntity.badRequest().body(errors);
    }
}
