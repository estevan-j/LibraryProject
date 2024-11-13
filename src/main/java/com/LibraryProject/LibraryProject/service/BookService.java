package com.LibraryProject.LibraryProject.service;

import com.LibraryProject.LibraryProject.DTO.BookConsultDTO;
import com.LibraryProject.LibraryProject.Models.Languages.Languages;
import com.LibraryProject.LibraryProject.Models.book.Book;
import com.LibraryProject.LibraryProject.Models.book.BookCountByLanguage;
import com.LibraryProject.LibraryProject.repository.BookRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    private BookRespository bookRespository;


    public List<BookConsultDTO> getAllBooks(){
        return convertToDTO(bookRespository.findBooksWithAuthorName()).stream().sorted(Comparator.comparing(BookConsultDTO::title)).collect(Collectors.toList());
    }

    public List<BookConsultDTO> getTop10Books(){
        return convertToDTO(bookRespository.findTop10MoreDonwloadsBooks());
    }

    public List<BookCountByLanguage> getBookCountByLanguage(String language){
        Languages languageCon = Languages.fromCode(language);
        System.out.println(languageCon.toString());
        return bookRespository.findBooksByLanguage(languageCon.toString());
    }

    private List<BookConsultDTO> convertToDTO(List<Book> books){
        return books.stream()
                .map(book -> new BookConsultDTO(book.getTitle(),
                        book.getAuthors().stream().map(author -> author.getName()).collect(Collectors.joining(",")),
                        book.getLanguages().stream().map(language -> language.getCode()).collect(Collectors.joining(", ")),
                        book.getDownload_count()))
                .toList();
    }

    public void saveNewBooks(List<Book> books) {
        bookRespository.saveAll(books);
    }
}
