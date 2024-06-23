package com.gioyisapplication.challenge_literalura.service;

import com.gioyisapplication.challenge_literalura.model.Author;
import com.gioyisapplication.challenge_literalura.repository.AuthorDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    @Autowired
    private AuthorDetailsRepository authorDetailsRepository;

    public List<Object[]> getAllAuthors() {
        return authorDetailsRepository.getSavedAuthors();
    }
}
