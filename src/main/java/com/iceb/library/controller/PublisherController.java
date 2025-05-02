package com.iceb.library.controller;


import com.iceb.library.dto.PublisherRequestDto;
import com.iceb.library.dto.PublisherResponseDto;
import com.iceb.library.service.PublisherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/publisher")
@Tag(name = "Publisher Management", description = "APIs for managing Publisher")
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    @PostMapping
    @Operation(summary = "Create a new publisher")
    public ResponseEntity<PublisherResponseDto> createPublisher(@Valid @RequestBody PublisherRequestDto publisherRequestDto) {
        PublisherResponseDto createdPublisher = publisherService.createPublisher(publisherRequestDto);
        return ResponseEntity.ok(createdPublisher);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a publisher by ID")
    public ResponseEntity<PublisherResponseDto> getPublisherById(@PathVariable UUID id) {
        PublisherResponseDto publisher = publisherService.getPublisherById(id);
        return ResponseEntity.ok(publisher);
    }

    @GetMapping
    @Operation(summary = "Get all publishers or search by name")
    public ResponseEntity<List<PublisherResponseDto>> searchPublisher(
        @RequestParam(required = false) String name,
        @RequestParam(defaultValue = "false") String archivedStr
    ) {
        
        List<Boolean> archived = Arrays.stream(archivedStr.split(","))
                                .map(Boolean::parseBoolean)
                                .collect(Collectors.toList());
        
        List<PublisherResponseDto> publishers = publisherService.searchPublishers(name, archived);
        return ResponseEntity.ok(publishers);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a publisher")
    public ResponseEntity<PublisherResponseDto> updatePublisher(@PathVariable UUID id, @Valid @RequestBody PublisherRequestDto publisherRequestDto) {
        PublisherResponseDto updatedPublisher = publisherService.updatePublisher(id, publisherRequestDto);
        return ResponseEntity.ok(updatedPublisher);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a publisher")
    public ResponseEntity<PublisherResponseDto> deletePublisher(@PathVariable UUID id) {
        PublisherResponseDto archivedPublisher = publisherService.deletePublisher(id);
        return ResponseEntity.ok(archivedPublisher);
    }

}
