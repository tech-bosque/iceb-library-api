package com.iceb.library.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    @OneToMany
    private List<Author> authors;

    @OneToOne
    private Publisher publisher;

    @OneToMany
    private List<Genre> genres;

    @OneToMany
    private List<Topic> topics;

    private String edition;

    private String language;

    private Integer year;

    private Integer pages;

    private String observation;

    private Boolean donation;

    private String assetNumber;

    private String isbn;

    private String urlCover;

    private Boolean archived;
}
