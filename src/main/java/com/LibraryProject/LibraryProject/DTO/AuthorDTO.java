package com.LibraryProject.LibraryProject.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AuthorDTO(
        @JsonProperty("name") String name,
        @JsonProperty("birth_year") String birthYear,
        @JsonProperty("death_year") String deathYear
) {
    @Override
    public String toString() {
        return "=============================================\n"+
                "\t\t" + name +
                "\nBith Year=" + birthYear +
                "\nDeath Year=" + deathYear +
                "\n===============================================";
    }
}
