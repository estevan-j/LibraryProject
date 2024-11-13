package com.LibraryProject.LibraryProject.repository;

import com.LibraryProject.LibraryProject.Models.Languages.Languages;
import com.LibraryProject.LibraryProject.Models.book.Book;
import com.LibraryProject.LibraryProject.Models.book.BookCountByLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRespository extends JpaRepository<Book, Long> {

    @Query("select b, a FROM Book b JOIN b.authors a")
    List<Book> findBooksWithAuthorName();

    @Query("select b, a from Book b join b.authors a order by b.download_count DESC LIMIT 10")
    List<Book> findTop10MoreDonwloadsBooks();

    @Query(value = "SELECT b.languages AS language, COUNT(b) AS count FROM books b WHERE b.languages = :language GROUP BY b.languages", nativeQuery = true)
    List<BookCountByLanguage> findBooksByLanguage(@Param("language") String language);


}
