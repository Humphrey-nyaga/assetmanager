package com.assetmanager.util;

import java.time.Year;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class YearConverter implements AttributeConverter<Year, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Year year) {
        return (year != null) ? year.getValue() : null;
    }

    @Override
    public Year convertToEntityAttribute(Integer yearValue) {
        return (yearValue != null) ? Year.of(yearValue) : null;
    }
}
