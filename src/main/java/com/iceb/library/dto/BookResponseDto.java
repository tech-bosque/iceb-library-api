package com.iceb.library.dto;

import com.iceb.library.entity.Author;
import com.iceb.library.entity.Genre;
import com.iceb.library.entity.Publisher;
import com.iceb.library.entity.Topic;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookResponseDto {

    private UUID id;

    private String name;

    private List<Author> authors;

    private Publisher publisher;

    private List<Genre> genres;

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
