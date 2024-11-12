package com.LibraryProject.LibraryProject.Models.Languages;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Languages {
    EN("en"),
    FR("fr"),
    FI("fi");

    private String language;
    private Languages(String language) {
        this.language = language;
    }

    @JsonCreator
    public static Languages fromCode(String code) {
        for (Languages lang : Languages.values()) {
            if (lang.language.equalsIgnoreCase(code)) { // Ignora mayúsculas y minúsculas
                return lang;
            }
        }
        throw new IllegalArgumentException("Idioma no soportado: " + code);
    }

    @JsonValue
    public String getCode() {
        return language;
    }
}
