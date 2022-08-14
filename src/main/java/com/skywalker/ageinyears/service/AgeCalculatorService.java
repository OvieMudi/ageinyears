package com.skywalker.ageinyears.service;

import com.skywalker.ageinyears.dto.response.AgeCalculatorResponse;

public interface AgeCalculatorService {
    AgeCalculatorResponse calculateAge(String dob);
}
