package com.iceb.library.service.impl;

import com.iceb.library.dto.publisher.PublisherRequestDto;
import com.iceb.library.dto.publisher.PublisherResponseDto;
import com.iceb.library.entity.Publisher;
import com.iceb.library.exception.PublisherNotFoundException;
import com.iceb.library.repository.PublisherRepository;
import com.iceb.library.service.PublisherService;
import com.iceb.library.utils.TranslatorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PublisherServiceImpl implements PublisherService {

    private static final Logger logger = LoggerFactory.getLogger(PublisherServiceImpl.class);

    @Autowired
    private PublisherRepository publisherRepository;

    @Override
    public PublisherResponseDto createPublisher(PublisherRequestDto publisherRequestDto) {
        logger.info("Creating publisher");
        logger.debug("Creating publisher with details: {}", publisherRequestDto);

        Publisher publisher = Publisher.builder()
                .name(publisherRequestDto.getName())
                .archived(false)
                .build();
        Publisher savedPublisher = publisherRepository.save(publisher);

        logger.info("Created publisher successfully");
        logger.debug("Created publisher: {}", savedPublisher);

        return TranslatorUtils.publisherToPublisherResponseDto(savedPublisher);
    }

    @Override
    public PublisherResponseDto getPublisherById(UUID id) {
        logger.info("Fetching publisher by ID");
        logger.debug("Fetching publisher with ID: {}", id);

        Publisher publisher = findPublisherById(id);
        PublisherResponseDto responseDto = TranslatorUtils.publisherToPublisherResponseDto(publisher);

        logger.debug("Fetched publisher: {}", responseDto);
        logger.info("Fetched publisher by ID successfully");
        return responseDto;
    }

    @Override
    public List<PublisherResponseDto> searchPublishers(String name, boolean archived) {
        logger.info("Fetching publishers by name containing");
        logger.debug("Fetching publishers with name containing: {}", name);

        List<Publisher> publishers;
        if (name == null || name.trim().isEmpty()) {
            publishers = publisherRepository.findAllPublisher(archived);
        } else {
            publishers = publisherRepository.findSimilarNames(name, archived);
        }

        List<PublisherResponseDto> responseDtos = publishers.stream()
                .map(TranslatorUtils::publisherToPublisherResponseDto)
                .collect(Collectors.toList());

        logger.debug("Fetched publishers: {}", responseDtos);
        logger.info("Fetched publishers by name containing successfully");
        return responseDtos;
    }

    @Override
    public PublisherResponseDto updatePublisher(UUID id, PublisherRequestDto publisherRequestDto) {
        logger.info("Updating publisher");
        logger.debug("Updating publisher with ID: {} with details: {}", id, publisherRequestDto);

        Publisher existingPublisher = findPublisherById(id);
        existingPublisher.setName(publisherRequestDto.getName());
        Publisher updatedPublisher = publisherRepository.save(existingPublisher);

        logger.info("Updated publisher successfully");
        logger.debug("Updated publisher: {}", updatedPublisher);

        return TranslatorUtils.publisherToPublisherResponseDto(updatedPublisher);
    }

    @Override
    public PublisherResponseDto deletePublisher(UUID id) {
        logger.info("Archiving publisher");
        logger.debug("Archiving publisher with ID: {}", id);

        Publisher publisher = findPublisherById(id);
        publisher.setArchived(true);
        Publisher archivedPublisher = publisherRepository.save(publisher);

        logger.info("Archived publisher successfully");
        logger.debug("Archived publisher with ID: {}", id);

        return TranslatorUtils.publisherToPublisherResponseDto(archivedPublisher);
    }

    private Publisher findPublisherById(UUID id) {
        logger.info("Finding publisher by ID");
        logger.debug("Finding publisher with ID: {}", id);

        return publisherRepository.findById(id)
                .orElseThrow(() -> new PublisherNotFoundException("The publisher with the provided ID does not exist"));
    }

}
