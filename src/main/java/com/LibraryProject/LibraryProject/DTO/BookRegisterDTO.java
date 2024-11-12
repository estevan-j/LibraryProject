package com.LibraryProject.LibraryProject.DTO;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookRegisterDTO(
        @JsonAlias("results")
        List<BookDTO> results
        ) {
}
