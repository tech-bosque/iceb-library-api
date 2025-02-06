package com.iceb.library.service.impl;

import com.iceb.library.TestUtils;
import com.iceb.library.dto.PublisherRequestDto;
import com.iceb.library.dto.PublisherResponseDto;
import com.iceb.library.entity.Publisher;
import com.iceb.library.repository.PublisherRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PublisherServiceImplTest {

    @InjectMocks
    private PublisherServiceImpl publisherServiceImpl;

    @Mock
    private PublisherRepository publisherRepository;

    @Test
    void createPublisherTest() {
        when(publisherRepository.save(Mockito.any(Publisher.class))).thenReturn(TestUtils.publisher(false));

        PublisherRequestDto publisherRequestDto = TestUtils.publisherRequestDto();

        PublisherResponseDto publisherResponseDto = publisherServiceImpl.createPublisher(publisherRequestDto);

        Assertions.assertEquals(publisherResponseDto.getName(), publisherRequestDto.getName());
        Assertions.assertFalse(publisherResponseDto.getArchived());
    }

    @Test
    void getPublisherByIdTest() {

        Publisher publisher = TestUtils.publisher(false);

        when(publisherRepository.findById(publisher.getId())).thenReturn(Optional.of(publisher));

        PublisherResponseDto publisherResponseDto = publisherServiceImpl.getPublisherById(publisher.getId());

        Assertions.assertEquals(publisherResponseDto.getName(), publisher.getName());
        Assertions.assertFalse(publisherResponseDto.getArchived());
    }

    @Test
    void searchPublishersTest() {
        List<Publisher> publishers = List.of(TestUtils.publisher(false), TestUtils.publisher(true));

        when(publisherRepository.findAllPublisher(false)).thenReturn(List.of(publishers.getFirst()));

        List<PublisherResponseDto> publisherResponseDtos = publisherServiceImpl.searchPublishers(null, false);

        Assertions.assertEquals(1, publisherResponseDtos.size());
        Assertions.assertFalse(publishers.getFirst().getArchived());


        when(publisherRepository.findSimilarNames("Test Publisher",true)).thenReturn(List.of(publishers.getLast()));

        List<PublisherResponseDto> publisherResponseDtos2 = publisherServiceImpl.searchPublishers("Test Publisher", true);

        Assertions.assertEquals(1, publisherResponseDtos2.size());
        Assertions.assertTrue(publishers.getLast().getArchived());
    }

    @Test
    void updatePublisherTest() {
        Publisher publisher = TestUtils.publisher(false);
        PublisherRequestDto publisherRequestDto = TestUtils.publisherRequestDto();
        publisherRequestDto.setName("Updated Publisher");

        when(publisherRepository.findById(publisher.getId())).thenReturn(Optional.of(publisher));
        when(publisherRepository.save(Mockito.any(Publisher.class))).thenReturn(publisher);

        PublisherResponseDto publisherResponseDto = publisherServiceImpl.updatePublisher(publisher.getId(), publisherRequestDto);

        Assertions.assertEquals(publisherResponseDto.getName(), publisherRequestDto.getName());
        Assertions.assertFalse(publisherResponseDto.getArchived());
    }

    @Test
    void deletePublisherTest() {
        Publisher publisher = TestUtils.publisher(false);

        when(publisherRepository.findById(publisher.getId())).thenReturn(Optional.of(publisher));
        when(publisherRepository.save(Mockito.any(Publisher.class))).thenReturn(publisher);

        PublisherResponseDto publisherResponseDto = publisherServiceImpl.deletePublisher(publisher.getId());

        Assertions.assertEquals(publisher.getName(), publisherResponseDto.getName());
        Assertions.assertTrue(publisherResponseDto.getArchived());
    }


}
