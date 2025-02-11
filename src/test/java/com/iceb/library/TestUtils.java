package com.iceb.library;

import com.iceb.library.dto.GenreRequestDto;
import com.iceb.library.dto.GenreResponseDto;
import com.iceb.library.dto.PublisherRequestDto;
import com.iceb.library.dto.PublisherResponseDto;
import com.iceb.library.entity.Genre;
import com.iceb.library.dto.TopicRequestDto;
import com.iceb.library.dto.TopicResponseDto;
import com.iceb.library.entity.Publisher;
import com.iceb.library.entity.Topic;

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

    public static Topic topic(Boolean archived) {
        return Topic.builder()
                .id(UUID.randomUUID())
                .name("Test Topic")
                .archived(archived)
                .build();
    }

    public static TopicRequestDto topicRequestDto() {
        return TopicRequestDto.builder()
                .name("Test Topic")
                .build();
    }

    public static TopicResponseDto topicResponseDto(Boolean archived) {
        return TopicResponseDto.builder()
                .id(UUID.randomUUID())
                .name("Test Topic")
                .archived(archived)
                .build();
    }

}
