package com.iceb.library.dto.book;

import com.iceb.library.dto.author.AuthorResponseDto;
import com.iceb.library.dto.genre.GenreResponseDto;
import com.iceb.library.dto.publisher.PublisherResponseDto;
import com.iceb.library.dto.topic.TopicResponseDto;
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

    private List<AuthorResponseDto> authors;

    private PublisherResponseDto publisher;

    private List<GenreResponseDto> genres;

    private List<TopicResponseDto> topics;

    private String edition;

    private String language;

    private Integer publicationYear;

    private Integer pages;

    private String observation;

    private Boolean donation;

    private String assetNumber;

    private String isbn;

    private String urlCover;

    private Boolean available;

    private Boolean archived;

}
