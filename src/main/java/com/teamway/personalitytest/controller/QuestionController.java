package com.teamway.personalitytest.controller;

import com.teamway.personalitytest.dto.QuestionDto;
import com.teamway.personalitytest.exception.ResourceNotFoundException;
import com.teamway.personalitytest.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/questions")
public class QuestionController {
    private final QuestionService questionService;

    @PostMapping
    private ResponseEntity<QuestionDto> createQuestion(@RequestBody QuestionDto payload) {
        QuestionDto question = questionService.createQuestion(payload);
        return ResponseEntity.status(HttpStatus.CREATED).body(question);
    }

    @GetMapping
    private ResponseEntity<List<QuestionDto>> getQuestions() {
        return ResponseEntity.ok(questionService.getQuestions());
    }

    @GetMapping("/{id}")
    private ResponseEntity<QuestionDto> getQuestion(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(questionService.getQuestion(id));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    private ResponseEntity<QuestionDto> updateQuestion(@PathVariable Long id, @RequestBody QuestionDto payload) {
        try {
            return ResponseEntity.ok(questionService.updateQuestion(id, payload));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Object> deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
        return ResponseEntity.status(204).build();
    }
}
