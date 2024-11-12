package com.LibraryProject.LibraryProject.Models.author;

import com.LibraryProject.LibraryProject.DTO.AuthorDTO;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.OptionalInt;

@Entity
@Table(name = "authors")
@NoArgsConstructor
@Getter
@EqualsAndHashCode
public class Author {
    @Id
    private String name;
    private Integer bithYear;
    private Integer deathYear;

    public Author(AuthorDTO author) {
        this.name = author.name();
        this.bithYear = OptionalInt.of(Integer.valueOf(author.birthYear())).orElse(0);
        this.deathYear = OptionalInt.of(Integer.valueOf(author.deathYear())).orElse(0);
    }
}
