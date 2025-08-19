package com.iceb.library.dto.book;

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
public class BookRequestDto {

    private String name;

    private List<UUID> authorsId;

    private UUID publisherId;

    private List<UUID> genresId;

    private List<UUID> topicsId;

    private String edition;

    private String language;

    private Integer publicationYear;

    private Integer pages;

    private String observation;

    private Boolean donation;

    private String assetNumber;

    private String isbn;

    private String urlCover;

    private Boolean archived;

}
