package com.iceb.library.service;

import com.iceb.library.dto.TopicRequestDto;
import com.iceb.library.dto.TopicResponseDto;

import java.util.List;
import java.util.UUID;

public interface TopicService {

    TopicResponseDto createTopic(TopicRequestDto topicRequestDto);
    TopicResponseDto getTopicById(UUID id);
    List<TopicResponseDto> searchTopics(String name, boolean archived);
    TopicResponseDto updateTopic(UUID id, TopicRequestDto topicRequestDto);
    TopicResponseDto deleteTopic(UUID id);
}
