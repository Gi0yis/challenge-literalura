package com.gioyisapplication.challenge_literalura.model;

import java.util.List;

public class Book {
    private String title;
    private List<AuthorDetails> author;
    private String languages;
    private Integer downloadCount;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }
}
