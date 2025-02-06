package com.iceb.library.service;

import com.iceb.library.dto.PublisherRequestDto;
import com.iceb.library.dto.PublisherResponseDto;
import java.util.List;
import java.util.UUID;

public interface PublisherService {

    PublisherResponseDto createPublisher(PublisherRequestDto publisherRequestDto);
    PublisherResponseDto getPublisherById(UUID id);
    List<PublisherResponseDto> searchPublishers(String name, boolean archived);
    PublisherResponseDto updatePublisher(UUID id, PublisherRequestDto publisherRequestDto);
    PublisherResponseDto deletePublisher(UUID id);
}
