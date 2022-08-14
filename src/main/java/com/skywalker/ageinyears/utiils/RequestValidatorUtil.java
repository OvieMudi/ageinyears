package com.skywalker.ageinyears.utiils;

import java.util.Date;
import java.util.Objects;

public class RequestValidatorUtil {
    public static boolean validRequestParam(String dob) {
        boolean validDigits = Objects.requireNonNull(dob).matches("^\\d{3,13}$");

        long currentTimeInMilli = new Date().getTime();
        long dobInMilli = Long.parseLong(dob);
        boolean validDate = currentTimeInMilli - dobInMilli  > 0;

        System.out.println("validDate: " + (currentTimeInMilli - dobInMilli));

        return validDigits && validDate;
    }
}
