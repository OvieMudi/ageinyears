package com.skywalker.ageinyears.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Getter
@Setter
public class ErrorMessage {
    String timestamp;
    int status;
    String error;
    String message;
    String path;

    public ErrorMessage(String message, String path, HttpStatus httpStatus) {
        timestamp = new Date().toInstant().toString();
        this.status = httpStatus.value();
        this.error = httpStatus.getReasonPhrase();
        this.message = message;
        this.path = path.split("=")[1];
    }
}
