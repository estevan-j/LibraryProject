package com.LibraryProject.LibraryProject.repository;

import com.LibraryProject.LibraryProject.Models.author.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuthorRespository extends JpaRepository<Author, Long> {
    @Query("SELECT a From Author a where a.deathYear >:year")
    List<Author> findByYear(Integer year);
}
