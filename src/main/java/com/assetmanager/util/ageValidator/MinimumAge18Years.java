package com.assetmanager.util.ageValidator;

import javax.annotation.Priority;
import javax.enterprise.inject.Alternative;
import java.time.LocalDate;
import java.time.Period;
@Priority(100)
@Alternative
public class MinimumAge18Years implements ValidAgeI{
    @Override
    public Boolean validWorkingAge(LocalDate dateOfBirth) {
        return Period.between(dateOfBirth, LocalDate.now()).getYears() >= 18;
    }
}
