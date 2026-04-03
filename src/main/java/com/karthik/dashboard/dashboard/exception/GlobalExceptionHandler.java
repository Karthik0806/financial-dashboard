package com.karthik.dashboard.dashboard.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(com.karthik.dashboard.dashboard.exception.BadRequestException.class)
    public ResponseEntity<?> handleBAdRequest(BadRequestException ex){

        log.error("Bad Request:{}" ,ex.getMessage());
        return ResponseEntity
                .badRequest()
                .body(Map.of(
                        "error",ex.getMessage(),
                        "status",400
                ));

    }@ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneral(Exception ex){
        log.error("Unexpected error",ex);
        return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("error","something went wrong","status",500));
    }
    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<?> handleAccessDenied(AuthorizationDeniedException ex) {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(Map.of(
                        "error", "Access Denied",
                        "message", "You are not allowed to access this resource"
                ));
    }
    @ExceptionHandler(com.karthik.dashboard.dashboard.exception.AccessDeniedException.class)
    public ResponseEntity<?> handleAccessDeniedCustom(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(Map.of(
                        "error", ex.getMessage(),
                        "status", 403
                ));
    }
    @ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(
            org.springframework.web.bind.MethodArgumentNotValidException ex) {

        var errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(err -> Map.of(
                        "field", err.getField(),
                        "message", err.getDefaultMessage()
                ))
                .toList();

        return ResponseEntity.badRequest().body(Map.of(
                "errors", errors,
                "status", 400
        ));
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleNotFound(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of(
                        "error", ex.getMessage(),
                        "status", 404
                ));
    }
    @ExceptionHandler(org.springframework.web.HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<?> handleMethodNotSupported(
            org.springframework.web.HttpRequestMethodNotSupportedException ex) {

        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(Map.of(
                        "error", "Method Not Allowed",
                        "message", "Use correct HTTP method (POST, GET, PUT, DELETE)",
                        "status", 405
                ));
    }

}
