package com.skywalker.ageinyears.service.implementation;

import com.skywalker.ageinyears.dto.response.AgeCalculatorResponse;
import com.skywalker.ageinyears.service.AgeCalculatorService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Service
public class AgeCalculatorImpl implements AgeCalculatorService {

    @Override
    public AgeCalculatorResponse calculateAge(String dob) {
        long yearsBetween = getYearsBetween(dob);
        return new AgeCalculatorResponse(yearsBetween);
    }

    private static long getYearsBetween(String dob) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dob, format);

        return ChronoUnit.YEARS.between(
                localDate,
                LocalDate.now(ZoneId.systemDefault())
        );
    }
}
