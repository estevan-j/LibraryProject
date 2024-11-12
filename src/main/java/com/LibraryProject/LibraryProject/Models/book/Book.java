package com.LibraryProject.LibraryProject.Models.book;
import com.LibraryProject.LibraryProject.DTO.BookDTO;
import com.LibraryProject.LibraryProject.Models.Languages.Languages;
import com.LibraryProject.LibraryProject.Models.Languages.LanguagesConverter;
import com.LibraryProject.LibraryProject.Models.author.Author;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity(name = "Book")
@Table(name = "books")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Book {
    @Id
    private Long book_id;
    private String title;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "name")
    )
    private List<Author> authors;
    @Convert(converter = LanguagesConverter.class)
    private List<Languages> languages;
    private Boolean copyright;
    private String media_type;
    private Integer download_count;

    public Book(BookDTO bookDTO) {
        this.book_id = bookDTO.book_id();
        this.title = bookDTO.title();
        this.authors = bookDTO.authors().stream().map(Author::new).toList();
        this.languages = bookDTO.languages();
        this.copyright = bookDTO.copyright();
        this.media_type = bookDTO.media_type();
        this.download_count = bookDTO.download_count();
    }

}
