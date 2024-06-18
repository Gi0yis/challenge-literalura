package com.gioyisapplication.challenge_literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "Books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonAlias("title") private String title;
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonAlias("authors") private List<AuthorDetails> authors;
    @JsonAlias("languages") List<String> languages;
    @JsonAlias("download_count") private Integer downloadCount;


    public List<AuthorDetails> getAuthors() {
        return authors;
    }

    public void setAuthor(List<AuthorDetails> authors) {
        this.authors = authors;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }

    @Override
    public String toString() {
        return "title='" + title + '\'' +
                ", author=" + authors +
                ", languages=" + languages +
                ", downloadCount=" + downloadCount;
    }
}
