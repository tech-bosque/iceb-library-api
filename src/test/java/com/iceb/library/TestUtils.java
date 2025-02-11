package com.iceb.library;

import com.iceb.library.dto.AuthorRequestDto;
import com.iceb.library.dto.AuthorResponseDto;
import com.iceb.library.dto.PublisherRequestDto;
import com.iceb.library.dto.PublisherResponseDto;
import com.iceb.library.entity.Author;
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

    public static Author author(Boolean archived) {
        return Author.builder()
                .id(UUID.randomUUID())
                .name("Test Author")
                .archived(archived)
                .build();
    }

    public static AuthorRequestDto authorRequestDto() {
        return AuthorRequestDto.builder()
                .name("Test Author")
                .build();
    }

    public static AuthorResponseDto authorResponseDto(Boolean archived) {
        return AuthorResponseDto.builder()
                .id(UUID.randomUUID())
                .name("Test Author")
                .archived(archived)
                .build();
    }

}
