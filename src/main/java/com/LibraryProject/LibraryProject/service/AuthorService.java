package com.LibraryProject.LibraryProject.service;

import com.LibraryProject.LibraryProject.DTO.AuthorDTO;
import com.LibraryProject.LibraryProject.Models.author.Author;
import com.LibraryProject.LibraryProject.repository.AuthorRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    @Autowired
    private AuthorRespository authorRespository;


    public List<AuthorDTO> getAllAuthors(){
        return convertAuthorsToDTO(authorRespository.findAll());
    }

    public List<AuthorDTO> AuthorsLivedInYear(Integer year){
        return convertAuthorsToDTO(authorRespository.findByYear(year));
    }


    private List<AuthorDTO> convertAuthorsToDTO(List<Author> authors){
        return authors.stream().map(author -> new AuthorDTO(author.getName(), author.getBithYear().toString(), author.getDeathYear().toString())).toList();
    }

    public void saveNewAuthors(List<Author> newAuthors) {
        authorRespository.saveAll(newAuthors);
    }
}
