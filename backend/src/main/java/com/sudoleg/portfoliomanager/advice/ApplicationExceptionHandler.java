package com.sudoleg.portfoliomanager.advice;

import com.sudoleg.portfoliomanager.ApiError;
import com.sudoleg.portfoliomanager.exceptions.UserAlreadyExistsException;
import org.junit.jupiter.api.Order;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles exceptions that are thrown during execution of controllers.
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    protected ResponseEntity<Object> handleUserAlreadyExistsException(UserAlreadyExistsException e) {
        List<String> errors = new ArrayList<>();
        errors.add(e.getMessage());
        ApiError apiError = new ApiError(HttpStatus.CONFLICT, errors);
        return buildResponseEntity(apiError);
    }

}
