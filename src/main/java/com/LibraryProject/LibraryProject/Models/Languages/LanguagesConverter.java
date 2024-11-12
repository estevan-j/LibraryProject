package com.LibraryProject.LibraryProject.Models.Languages;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Converter
public class LanguagesConverter implements AttributeConverter<List<Languages>, String> {

    @Override
    public String convertToDatabaseColumn(List<Languages> languages) {
        if (languages == null || languages.isEmpty()) {
            return null;
        }
        return languages.stream()
                .map(Languages::name)
                .collect(Collectors.joining(","));
    }

    @Override
    public List<Languages> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return null;
        }
        return Arrays.stream(dbData.split(","))
                .map(Languages::valueOf)
                .collect(Collectors.toList());
    }
}
