package com.iceb.library.controller;

import com.iceb.library.dto.TopicRequestDto;
import com.iceb.library.dto.TopicResponseDto;
import com.iceb.library.service.TopicService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/topic")
@Tag(name = "Topic Management", description = "APIs for managing Topic")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @PostMapping
    @Operation(summary = "Create a new topic")
    public ResponseEntity<TopicResponseDto> createTopic(@Valid @RequestBody TopicRequestDto topicRequestDto) {
        TopicResponseDto createdTopic = topicService.createTopic(topicRequestDto);
        return ResponseEntity.ok(createdTopic);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a topic by ID")
    public ResponseEntity<TopicResponseDto> getTopicById(@PathVariable UUID id) {
        TopicResponseDto topic = topicService.getTopicById(id);
        return ResponseEntity.ok(topic);
    }

    @GetMapping
    @Operation(summary = "Get all topics or search by name")
    public ResponseEntity<List<TopicResponseDto>> searchTopics(@RequestParam(required = false) String name, @RequestParam(defaultValue = "false") boolean archived) {
        List<TopicResponseDto> topics = topicService.searchTopics(name, archived);
        return ResponseEntity.ok(topics);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a topic")
    public ResponseEntity<TopicResponseDto> updateTopic(@PathVariable UUID id, @Valid @RequestBody TopicRequestDto topicRequestDto) {
        TopicResponseDto updatedTopic = topicService.updateTopic(id, topicRequestDto);
        return ResponseEntity.ok(updatedTopic);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a topic")
    public ResponseEntity<TopicResponseDto> deleteTopic(@PathVariable UUID id) {
        TopicResponseDto archivedTopic = topicService.deleteTopic(id);
        return ResponseEntity.ok(archivedTopic);
    }
}
