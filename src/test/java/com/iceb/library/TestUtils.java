package com.iceb.library;

import com.iceb.library.dto.PublisherRequestDto;
import com.iceb.library.dto.PublisherResponseDto;
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

}
