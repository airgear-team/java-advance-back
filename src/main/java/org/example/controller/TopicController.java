package org.example.controller;

import org.example.dto.TopicDTO;
import org.example.dto.TopicCreateDTO;
import org.example.service.TopicService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topics")
public class TopicController {

    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @PostMapping
    public ResponseEntity<TopicDTO> createTopic(@RequestBody TopicCreateDTO dto) {
        return new ResponseEntity<>(topicService.createTopic(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TopicDTO>> getAllTopics() {
        return ResponseEntity.ok(topicService.getAllTopics());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicDTO> getTopicById(@PathVariable Long id) {
        return topicService.getTopicById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}