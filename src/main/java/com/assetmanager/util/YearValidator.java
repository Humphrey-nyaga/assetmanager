package com.assetmanager.util;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
/*
* Custom class to validate a year. Year must be 4 digits and not greater than current year
* or less than 1900(arbitrarily chosen)
* */
public class YearValidator implements ConstraintValidator<ValidYear, Integer> {
    @Override
    public void initialize(ValidYear constraintAnnotation) {
    }
    @Override
    public boolean isValid(Integer year, ConstraintValidatorContext context) {
        System.out.println("<<<<<<<<<<<<<<<<sYEAR VALIDATOR METHOD INVOKED>>>>>>>>");
        String yearString = String.valueOf(year);
        if (yearString.length() != 4) {
            return false;
        }

        int currentYear = java.time.Year.now().getValue();
        return year >= 1900 && year <= currentYear;
    }
}