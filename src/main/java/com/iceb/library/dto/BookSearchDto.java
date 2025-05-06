package com.iceb.library.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookSearchDto {

    private String name;

    private String author;

    private String publisher;

    private String genre;

    private String topic;

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
