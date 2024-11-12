package com.LibraryProject.LibraryProject;

import com.LibraryProject.LibraryProject.repository.AuthorRespository;
import com.LibraryProject.LibraryProject.main.Main;
import com.LibraryProject.LibraryProject.repository.BookRespository;
import com.LibraryProject.LibraryProject.service.AuthorService;
import com.LibraryProject.LibraryProject.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class LibraryProjectApplication implements CommandLineRunner {

	@Autowired
	private AuthorService authorService;
	@Autowired
	private BookService bookService;

	public static void main(String[] args) {
		SpringApplication.run(LibraryProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Main main = new Main(authorService, bookService);
		main.startProject();
	}
}
