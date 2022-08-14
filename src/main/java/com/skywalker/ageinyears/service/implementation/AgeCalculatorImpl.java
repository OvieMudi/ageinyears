package com.skywalker.ageinyears.service.implementation;

import com.skywalker.ageinyears.dto.response.AgeCalculatorResponse;
import com.skywalker.ageinyears.service.AgeCalculatorService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

@Service
public class AgeCalculatorImpl implements AgeCalculatorService {

    @Override
    public AgeCalculatorResponse calculateAge(String dob) {
        long yearsBetween = getYearsBetween(dob);
        return new AgeCalculatorResponse(yearsBetween);
    }

    private static long getYearsBetween(String dob) {
        long timestamp = Long.parseLong(dob);
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDate localDate = Instant.ofEpochMilli(timestamp).atZone(zoneId).toLocalDate();

        return ChronoUnit.YEARS.between(
                localDate,
                LocalDate.now(zoneId)
        );
    }
}
