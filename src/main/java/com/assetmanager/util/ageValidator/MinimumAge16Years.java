package com.assetmanager.util.ageValidator;

import com.assetmanager.database.helper.PrimaryKey;

import javax.annotation.Priority;
import javax.enterprise.inject.Alternative;
import java.time.LocalDate;
import java.time.Period;

// TODO - The class needs to be collapsed to one with @MinimumAge18Years.
 //Pass the age through configurations
@Priority(1000)
@Alternative
public class MinimumAge16Years implements ValidAgeI {
    @Override
    public Boolean validWorkingAge(LocalDate dateOfBirth) {
        return Period.between(dateOfBirth, LocalDate.now()).getYears() >= 16;
    }
}
