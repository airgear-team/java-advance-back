package org.example.controller;

import org.example.dto.QuestionDTO;
import org.example.dto.QuestionCreateDTO;
import org.example.dto.QuestionsByTopicDTO;
import org.example.service.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping
    public ResponseEntity<QuestionDTO> createQuestion(@RequestBody QuestionCreateDTO dto) {
        return new ResponseEntity<>(questionService.createQuestion(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<QuestionDTO>> getAllQuestions() {
        return ResponseEntity.ok(questionService.getAllQuestions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionDTO> getQuestionById(@PathVariable Long id) {
        return questionService.getQuestionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuestionDTO> updateQuestion(@PathVariable Long id, @RequestBody QuestionCreateDTO dto) {
        try {
            return ResponseEntity.ok(questionService.updateQuestion(id, dto));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/grouped-by-topic")
    public ResponseEntity<List<QuestionsByTopicDTO>> getQuestionsGroupedByTopic() {
        return ResponseEntity.ok(questionService.getQuestionsGroupedByTopic());
    }

    @GetMapping("/by-topic/{topicId}")
    public ResponseEntity<List<QuestionDTO>> getQuestionsByTopic(@PathVariable Long topicId) {
        return ResponseEntity.ok(questionService.getQuestionsByTopicId(topicId));
    }

}