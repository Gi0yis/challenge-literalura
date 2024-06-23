package com.gioyisapplication.challenge_literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authorsId;

    @JsonAlias("name") private String name;

    @JsonAlias("birth_year") private Integer birthYear;
    @JsonAlias("death_year") private Integer deathYear;

    @ManyToMany(mappedBy = "authors", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
   //        joinColumns = @JoinColumn(name = "author_id"),
   //         inverseJoinColumns = @JoinColumn(name = "book_id")
    //)
    private List<Book> books;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public Integer getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(Integer deathYear) {
        this.deathYear = deathYear;
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", birthYear=" + birthYear +
                ", deathYear=" + deathYear;
    }

}
