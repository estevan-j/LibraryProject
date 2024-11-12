package com.LibraryProject.LibraryProject.main;

import com.LibraryProject.LibraryProject.DTO.AuthorDTO;
import com.LibraryProject.LibraryProject.Models.author.Author;
import com.LibraryProject.LibraryProject.Models.book.Book;
import com.LibraryProject.LibraryProject.DTO.BookDTO;
import com.LibraryProject.LibraryProject.DTO.BookRegisterDTO;
import com.LibraryProject.LibraryProject.service.ApiService;
import com.LibraryProject.LibraryProject.service.AuthorService;
import com.LibraryProject.LibraryProject.service.BookService;
import com.LibraryProject.LibraryProject.service.ConvertData;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class Main {

    private ApiService apiService = new ApiService();
    private Scanner entry = new Scanner(System.in);
    private ConvertData convertData = new ConvertData();
    @Value("${library.uri}")
    private final String url = "https://gutendex.com/books/";
    private BookService bookService;
    private AuthorService authorService;

    public Main(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    public void startProject() {
        var opcion = -1;
        while (opcion != 0) {
            showMenu();
            opcion = entry.nextInt();
            entry.nextLine();

            switch (opcion) {
                case 1:
                    searchBookInWeb();
                    break;
                case 2:
                    searchBooksInDB();
                    break;
                case 3:
                    searchAuthorsInDB();
                    break;
                case 4:
                    searchAuthorsLivedInYear();
                    break;
                case 5:
                    searchBooksByLanguages();
                    break;
                case 6:
                    get3DonwloadMovies();
                    break;
                case 7:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private void get3DonwloadMovies() {
        System.out.println("\n==============Top 10 Books=====================");
        bookService.getTop10Books().stream().forEach(System.out::println);
    }

    private void searchBooksByLanguages() {
        System.out.println("\nLanguage ========================= Download Counts");
        System.out.println("Enter the language: ");
        System.out.print("==> ");
        String language = entry.nextLine();
        bookService.getBookCountByLanguage(language).forEach(bookCountByLanguage -> {
            System.out.println(bookCountByLanguage.getLanguage()+" ========================= "+bookCountByLanguage.getCount());
        });
    }

    @Transactional
    protected void searchBooksInDB() {
        System.out.println("\n==============List of Books=====================");
        bookService.getAllBooks().forEach(System.out::println);
    }

    private void searchAuthorsLivedInYear() {
        System.out.println("Enter the year base: ");
        System.out.print("==> ");
        Integer year = entry.nextInt();
        System.out.println("\n==============List of Authors=====================");
        for (AuthorDTO authorDTO : authorService.AuthorsLivedInYear(year)) {
            System.out.println(authorDTO);
        }
    }

    @Transactional
    protected void searchAuthorsInDB() {
        System.out.println("\n==============List of Authors=====================");
        List<AuthorDTO> authors = authorService.getAllAuthors();
        authors.stream()
                .sorted(Comparator.comparing(AuthorDTO::name))
                .forEach(System.out::println);
    }



    private void showMenu() {
        System.out.println("\n//////////////////////////////////////////////");
        System.out.println("Welcome to Library Project");
        System.out.println("1. Search Book By name");
        System.out.println("2. Consult Books");
        System.out.println("3. Consult Authors");
        System.out.println("4. Consult Living authors of the year");
        System.out.println("5. Consult Books by Languages");
        System.out.println("6. Consult the 10 Most Donwloaded books");
        System.out.println("7. Close");
        System.out.print("---> ");
    }

    @SneakyThrows
    private List<BookDTO> searchBookByNameFromAPI() {
        System.out.println("Write the name of the seria to search: ");
        var serieName = entry.nextLine();
        String encodedPath = URLEncoder.encode(serieName, StandardCharsets.UTF_8);
        var json = apiService.getDataFromAPI(url + "?search=" + encodedPath.replace("+", "%20"));

        BookRegisterDTO bookList = convertData.getData(json, BookRegisterDTO.class);
        return bookList.results();
    }

    private void searchBookInWeb() {
        List<BookDTO> booksDTO = searchBookByNameFromAPI();
        List<Author> authors = booksDTO.stream()
                .flatMap(bookDTO -> bookDTO.authors().stream())
                .distinct()
                .map(Author::new)
                .toList();
        saveAuthorsInDB(authors);
        List<Book> books = createBooksFromDTO(booksDTO, authors);
        bookService.saveNewBooks(books);
    }

    private void saveAuthorsInDB(List<Author> authors) {
        // Obtener los nombres de los autores ya existentes en la base de datos
        List<String> existingAuthorNames = authorService.getAllAuthors().stream()
                .map(AuthorDTO::name)
                .toList();

        // Filtrar y crear solo autores nuevos
        List<Author> newAuthors = authors.stream()
                .filter(author -> !existingAuthorNames.contains(author.getName()))
                .collect(Collectors.toList());

        // Guardar solo los autores nuevos
        if (!newAuthors.isEmpty()) {
            authorService.saveNewAuthors(newAuthors);
        }
    }


    private List<Book> createBooksFromDTO(List<BookDTO> booksDTO, List<Author> authors) {
        Map<String, Author> authorMap = authors.stream()
                .collect(Collectors.toMap(Author::getName, author -> author));

        List<Book> books = booksDTO.stream()
                .map(Book::new)
                .collect(Collectors.toList());
        books.forEach(System.out::println);
        return books;
    }


}
