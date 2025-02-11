package com.iceb.library;

import com.iceb.library.dto.GenreRequestDto;
import com.iceb.library.dto.GenreResponseDto;
import com.iceb.library.dto.PublisherRequestDto;
import com.iceb.library.dto.PublisherResponseDto;
import com.iceb.library.entity.Genre;
import com.iceb.library.entity.Publisher;

import java.util.UUID;

public class TestUtils {


    public static Publisher publisher(Boolean archived) {
        return Publisher.builder()
                .id(UUID.randomUUID())
                .name("Test Publisher")
                .archived(archived)
                .build();
    }

    public static PublisherRequestDto publisherRequestDto() {
        return PublisherRequestDto.builder()
                .name("Test Publisher")
                .build();
    }

    public static PublisherResponseDto publisherResponseDto(Boolean archived) {
        return PublisherResponseDto.builder()
                .id(UUID.randomUUID())
                .name("Test Publisher")
                .archived(archived)
                .build();
    }

    public static Genre genre(Boolean archived) {
        return Genre.builder()
                .id(UUID.randomUUID())
                .name("Test Genre")
                .archived(archived)
                .build();
    }

    public static GenreRequestDto genreRequestDto() {
        return GenreRequestDto.builder()
                .name("Test Genre")
                .build();
    }

    public static GenreResponseDto genreResponseDto(Boolean archived) {
        return GenreResponseDto.builder()
                .id(UUID.randomUUID())
                .name("Test Genre")
                .archived(archived)
                .build();
    }

}
