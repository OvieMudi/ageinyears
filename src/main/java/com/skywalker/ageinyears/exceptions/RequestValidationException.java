package com.skywalker.ageinyears.exceptions;

import com.skywalker.ageinyears.dto.response.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class RequestValidationException {

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ResponseEntity<Object> handleInvalidQueryParameter(Exception exception, WebRequest request) {
        String message = exception.getLocalizedMessage();
        message = message == null ? "One or more of the request parameters are invalid" : message;

        ErrorMessage errorMessage = new ErrorMessage(message, request.getDescription(false), HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
}
