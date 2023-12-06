package com.monteroantonio.PDAM23.model.utils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Converter
public class StringListConverter implements AttributeConverter<List<String>, String> {

    @Override
    public String convertToDatabaseColumn(List<String> attribute) {
        // Convertir la lista de cadenas a una cadena
        if (attribute.isEmpty()) {
            return null;
        }
        return String.join(",", attribute);
    }

    @Override
    public List<String> convertToEntityAttribute(String dbData) {
        // Convertir la cadena a una lista de cadenas
        if (dbData.isEmpty()){
            return new ArrayList<>();
        }
        return Arrays.asList(dbData.split(","));
    }
}
