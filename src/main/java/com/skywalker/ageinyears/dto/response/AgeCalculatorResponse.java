package com.skywalker.ageinyears.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class AgeCalculatorResponse extends ResponseEntity<Object> {
    public AgeCalculatorResponse(long age) {
        super(age, HttpStatus.OK);
    }

}
