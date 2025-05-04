package com.iceb.library.repository;

import com.iceb.library.entity.Topic;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TopicRepositoryTest extends TestRepositoryHelper{

    @Test
    void findTopicByIdTest() {
        Topic savedTopic = topicRepository.findById(topicTest.getId()).orElse(null);
        Assertions.assertNotNull(savedTopic);
        assertThat(savedTopic).usingRecursiveComparison().isEqualTo(topicTest);
    }

    @Test
    void findAllTopicsTest() {
        Topic secondTopic = Topic.builder()
                .id(null)
                .name("Second Topic")
                .archived(false)
                .build();

        topicRepository.save(secondTopic);

        List<Topic> savedTopics = topicRepository.findAllTopics(Arrays.asList(false));
        Assertions.assertNotNull(savedTopics);
        Assertions.assertFalse(savedTopics.isEmpty());
        Assertions.assertEquals(2, savedTopics.size());
    }

    @Test
    void findSimilarNamesTest() {
        List<Topic> savedTopics = topicRepository.findSimilarNames(topicTest.getName(), Arrays.asList(false));
        Assertions.assertNotNull(savedTopics);
        Assertions.assertFalse(savedTopics.isEmpty());
        Assertions.assertEquals(1, savedTopics.size());
    }
}
