package com.iceb.library.service.impl;

import com.iceb.library.TestUtils;
import com.iceb.library.dto.TopicRequestDto;
import com.iceb.library.dto.TopicResponseDto;
import com.iceb.library.entity.Topic;
import com.iceb.library.exception.TopicNotFoundException;
import com.iceb.library.repository.TopicRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TopicServiceImplTest {

    @InjectMocks
    private TopicServiceImpl topicServiceImpl;

    @Mock
    private TopicRepository topicRepository;

    @Test
    void createTopicTest() {
        when(topicRepository.save(Mockito.any(Topic.class))).thenReturn(TestUtils.topic(false));

        TopicRequestDto topicRequestDto = TestUtils.topicRequestDto();

        TopicResponseDto topicResponseDto = topicServiceImpl.createTopic(topicRequestDto);

        Assertions.assertEquals(topicResponseDto.getName(), topicRequestDto.getName());
        Assertions.assertFalse(topicResponseDto.getArchived());
    }

    @Test
    void getTopicByIdTest() {

        Topic topic = TestUtils.topic(false);

        when(topicRepository.findById(topic.getId())).thenReturn(Optional.of(topic));

        TopicResponseDto topicResponseDto = topicServiceImpl.getTopicById(topic.getId());

        Assertions.assertEquals(topicResponseDto.getName(), topic.getName());
        Assertions.assertFalse(topicResponseDto.getArchived());
    }

    @Test
    void getTopicByIdNotFoundTest() {

        UUID topicId = UUID.randomUUID();

        when(topicRepository.findById(topicId)).thenReturn(Optional.empty());

        Assertions.assertThrows(TopicNotFoundException.class, () -> {
            topicServiceImpl.getTopicById(topicId);
        });
    }

    @Test
    void searchTopicsTest() {
        List<Topic> topics = List.of(TestUtils.topic(false), TestUtils.topic(true));

        when(topicRepository.findAllTopics(false)).thenReturn(List.of(topics.get(0)));

        List<TopicResponseDto> topicResponseDtos = topicServiceImpl.searchTopics(null, false);

        Assertions.assertEquals(1, topicResponseDtos.size());
        Assertions.assertFalse(topics.get(0).getArchived());

        when(topicRepository.findSimilarNames("Test Topic", true)).thenReturn(List.of(topics.get(1)));

        List<TopicResponseDto> topicResponseDtos2 = topicServiceImpl.searchTopics("Test Topic", true);

        Assertions.assertEquals(1, topicResponseDtos2.size());
        Assertions.assertTrue(topics.get(1).getArchived());
    }

    @Test
    void updateTopicTest() {
        Topic topic = TestUtils.topic(false);
        TopicRequestDto topicRequestDto = TestUtils.topicRequestDto();
        topicRequestDto.setName("Updated Topic");

        when(topicRepository.findById(topic.getId())).thenReturn(Optional.of(topic));
        when(topicRepository.save(Mockito.any(Topic.class))).thenReturn(topic);

        TopicResponseDto topicResponseDto = topicServiceImpl.updateTopic(topic.getId(), topicRequestDto);

        Assertions.assertEquals(topicResponseDto.getName(), topicRequestDto.getName());
        Assertions.assertFalse(topicResponseDto.getArchived());
    }

    @Test
    void deleteTopicTest() {
        Topic topic = TestUtils.topic(false);

        when(topicRepository.findById(topic.getId())).thenReturn(Optional.of(topic));
        when(topicRepository.save(Mockito.any(Topic.class))).thenReturn(topic);

        TopicResponseDto topicResponseDto = topicServiceImpl.deleteTopic(topic.getId());

        Assertions.assertEquals(topic.getName(), topicResponseDto.getName());
        Assertions.assertTrue(topicResponseDto.getArchived());
    }

}
