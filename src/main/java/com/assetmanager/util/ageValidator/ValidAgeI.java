package com.assetmanager.util.ageValidator;

import java.time.LocalDate;

public interface ValidAgeI {
    Boolean validWorkingAge(LocalDate dateOfBirth);
}
