package com.skywalker.ageinyears.controller;

import com.skywalker.ageinyears.dto.response.AgeCalculatorResponse;
import com.skywalker.ageinyears.service.AgeCalculatorService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Validated
@RequestMapping("/")
public class AgeCalculatorController {

    private final AgeCalculatorService ageCalculatorService;

    @GetMapping({"/", "/helloworld"})
    public String helloThere() {
        return "Hello there!!!";
    }

    @GetMapping("/howold")
    public AgeCalculatorResponse howOld(@RequestParam("dob") String dob) {
        return ageCalculatorService.calculateAge(dob);
    }
}
