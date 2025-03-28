package com.iceb.library.repository;

import com.iceb.library.entity.Publisher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PublisherRepositoryTest extends TestRepositoryHelper{

    @Test
    void findPublisherByIdTest() {
        Publisher savedPublisher = publisherRepository.findById(publisherTest.getId()).orElse(null);
        Assertions.assertNotNull(savedPublisher);
        assertThat(savedPublisher).usingRecursiveComparison().isEqualTo(publisherTest);
    }

    @Test
    void findAllPublishersTest() {
        Publisher secondPublisher = Publisher.builder()
                .id(null)
                .name("Second Publisher")
                .archived(false)
                .build();

        publisherRepository.save(secondPublisher);

        List<Publisher> savedPublishers = publisherRepository.findAllPublisher(false);
        Assertions.assertNotNull(savedPublishers);
        Assertions.assertFalse(savedPublishers.isEmpty());
        Assertions.assertEquals(2, savedPublishers.size());
    }

    @Test
    void findSimilarNamesTest() {
        List<Publisher> savedPublishers = publisherRepository.findSimilarNames(publisherTest.getName(), false);
        Assertions.assertNotNull(savedPublishers);
        Assertions.assertFalse(savedPublishers.isEmpty());
        Assertions.assertEquals(1, savedPublishers.size());
    }
}
