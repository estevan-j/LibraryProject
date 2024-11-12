package com.LibraryProject.LibraryProject.DTO;

import com.LibraryProject.LibraryProject.Models.Languages.Languages;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookDTO(@JsonAlias("id")
                      Long book_id,
                      String title,
                      List<AuthorDTO> authors,
                      List<Languages> languages,
                      Boolean copyright,
                      String media_type,
                      Integer download_count) {
}
