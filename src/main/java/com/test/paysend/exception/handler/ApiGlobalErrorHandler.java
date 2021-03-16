package com.test.paysend.exception.handler;

import com.test.paysend.dto.ApiErrorResponse;
import com.test.paysend.exception.BusinessException;
import com.test.paysend.exception.DuplicateRecordDAOException;
import com.test.paysend.exception.InvalidCredentialsException;
import com.test.paysend.exception.InvalidUserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.dao.DuplicateKeyException;

/**
 * Global error handler
 */
@Slf4j
@ControllerAdvice
public class ApiGlobalErrorHandler {

    @ExceptionHandler(value = {DuplicateRecordDAOException.class, DuplicateKeyException.class})
    public ResponseEntity<ApiErrorResponse> handleDuplicateRegistrationUserException(Exception ex) {
        log.error("An duplicate login error happened while calling API: \n", ex);
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .contentType(MediaType.APPLICATION_XML)
                .body(ApiErrorResponse.builder().resultCode(1).build());
    }

    @ExceptionHandler(value = InvalidUserException.class)
    public ResponseEntity<ApiErrorResponse> handleNonExistingUserException(InvalidUserException ex) {
        log.error("User by provided username does not exists: \n", ex);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_XML)
                .body(ApiErrorResponse.builder().resultCode(3).build());
    }

    @ExceptionHandler(value = InvalidCredentialsException.class)
    public ResponseEntity<ApiErrorResponse> handleInvalidCredentialsException(InvalidCredentialsException ex) {
        log.error("User credentials are invalid: \n", ex);
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .contentType(MediaType.APPLICATION_XML)
                .body(ApiErrorResponse.builder().resultCode(4).build());
    }

    @ExceptionHandler(value = BusinessException.class)
    public ResponseEntity<ApiErrorResponse> handleInvalidRequestErrors(BusinessException ex) {
        log.error("Invalid request by syntax has been sent to server: \n", ex);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_XML)
                .body(ApiErrorResponse.builder().resultCode(2).build());
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ApiErrorResponse> handleInternalServerErrors(Exception ex) {
        log.error("An server internal error happened while calling API: \n", ex);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_XML)
                .body(ApiErrorResponse.builder().resultCode(2).build());
    }

}