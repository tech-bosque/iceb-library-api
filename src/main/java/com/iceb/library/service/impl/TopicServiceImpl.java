package com.iceb.library.service.impl;

import com.iceb.library.dto.topic.TopicRequestDto;
import com.iceb.library.dto.topic.TopicResponseDto;
import com.iceb.library.entity.Topic;
import com.iceb.library.exception.TopicNotFoundException;
import com.iceb.library.repository.TopicRepository;
import com.iceb.library.service.TopicService;
import com.iceb.library.utils.TranslatorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TopicServiceImpl implements TopicService {

    private static final Logger logger = LoggerFactory.getLogger(TopicServiceImpl.class);

    @Autowired
    private TopicRepository topicRepository;

    @Override
    public TopicResponseDto createTopic(TopicRequestDto topicRequestDto) {
        logger.info("Creating topic");
        logger.debug("Creating topic with details: {}", topicRequestDto);

        Topic topic = Topic.builder()
                .name(topicRequestDto.getName())
                .archived(false)
                .build();
        Topic savedTopic = topicRepository.save(topic);

        logger.info("Created topic successfully");
        logger.debug("Created topic: {}", savedTopic);

        return TranslatorUtils.topicToTopicResponseDto(savedTopic);
    }

    @Override
    public TopicResponseDto getTopicById(UUID id) {
        logger.info("Fetching topic by ID");
        logger.debug("Fetching topic with ID: {}", id);

        Topic topic = findTopicById(id);
        TopicResponseDto responseDto = TranslatorUtils.topicToTopicResponseDto(topic);

        logger.debug("Fetched topic: {}", responseDto);
        logger.info("Fetched topic by ID successfully");
        return responseDto;
    }

    @Override
    public List<TopicResponseDto> searchTopics(String name, boolean archived) {
        logger.info("Fetching topics by name containing");
        logger.debug("Fetching topics with name containing: {}", name);

        List<Topic> topics;
        if (name == null || name.trim().isEmpty()) {
            topics = topicRepository.findAllTopics(archived);
        } else {
            topics = topicRepository.findSimilarNames(name, archived);
        }

        List<TopicResponseDto> responseDtos = topics.stream()
                .map(TranslatorUtils::topicToTopicResponseDto)
                .collect(Collectors.toList());

        logger.debug("Fetched topics: {}", responseDtos);
        logger.info("Fetched topics by name containing successfully");
        return responseDtos;
    }

    @Override
    public TopicResponseDto updateTopic(UUID id, TopicRequestDto topicRequestDto) {
        logger.info("Updating topic");
        logger.debug("Updating topic with ID: {} with details: {}", id, topicRequestDto);

        Topic existingTopic = findTopicById(id);
        existingTopic.setName(topicRequestDto.getName());
        Topic updatedTopic = topicRepository.save(existingTopic);

        logger.info("Updated topic successfully");
        logger.debug("Updated topic: {}", updatedTopic);

        return TranslatorUtils.topicToTopicResponseDto(updatedTopic);
    }

    @Override
    public TopicResponseDto deleteTopic(UUID id) {
        logger.info("Archiving topic");
        logger.debug("Archiving topic with ID: {}", id);

        Topic topic = findTopicById(id);
        topic.setArchived(true);
        Topic archivedTopic = topicRepository.save(topic);

        logger.info("Archived topic successfully");
        logger.debug("Archived topic with ID: {}", id);

        return TranslatorUtils.topicToTopicResponseDto(archivedTopic);
    }

    private Topic findTopicById(UUID id) {
        logger.info("Finding topic by ID");
        logger.debug("Finding topic with ID: {}", id);

        return topicRepository.findById(id)
                .orElseThrow(() -> new TopicNotFoundException("The topic with the provided ID does not exist"));
    }

}
