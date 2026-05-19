package com.iceb.library.dto.book;

import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "Name is required and cannot be empty or whitespace.")
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

    @NotBlank(message = "ISBN is required and cannot be empty or whitespace.")
    private String isbn;

    private String urlCover;

    private Boolean archived;

}
