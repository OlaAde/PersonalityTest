package com.teamway.personalitytest.service;

import com.teamway.personalitytest.dto.QuestionDto;
import com.teamway.personalitytest.entity.Question;
import com.teamway.personalitytest.exception.ResourceNotFoundException;
import com.teamway.personalitytest.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;


    public List<QuestionDto> getQuestions() {
        return ((List<Question>) questionRepository.findAll()).stream().map(QuestionDto::buildFrom)
                .collect(Collectors.toList());
    }

    public QuestionDto getQuestion(Long id) throws ResourceNotFoundException {
        return questionRepository.findById(id).map(QuestionDto::buildFrom).orElseThrow(ResourceNotFoundException::new);
    }

    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }

    public QuestionDto updateQuestion(Long id, QuestionDto payload) throws ResourceNotFoundException {
        Question entity = questionRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        updateEntityFieldsBasedOnPayload(entity, payload);

        entity = questionRepository.save(entity);

        return QuestionDto.buildFrom(entity);
    }

    private void updateEntityFieldsBasedOnPayload(Question entity, QuestionDto payload) {
        if (payload.getQuestion() != null && !payload.getQuestion().isEmpty()) {
            entity.setQuestion(payload.getQuestion());
        }

        if (payload.getAnswer1() != null && !payload.getAnswer1().isEmpty()) {
            entity.setAnswer1(payload.getAnswer1());
        }
        if (payload.getAnswer2() != null && !payload.getAnswer2().isEmpty()) {
            entity.setAnswer2(payload.getAnswer2());
        }
        if (payload.getAnswer3() != null && !payload.getAnswer3().isEmpty()) {
            entity.setAnswer3(payload.getAnswer3());
        }
        if (payload.getAnswer4() != null && !payload.getAnswer4().isEmpty()) {
            entity.setAnswer4(payload.getAnswer4());
        }

        if (payload.getAnswer1weight() != null) {
            entity.setAnswer1weight(payload.getAnswer1weight());
        }
        if (payload.getAnswer2weight() != null) {
            entity.setAnswer2weight(payload.getAnswer2weight());
        }
        if (payload.getAnswer3weight() != null) {
            entity.setAnswer3weight(payload.getAnswer3weight());
        }
        if (payload.getAnswer4weight() != null) {
            entity.setAnswer4weight(payload.getAnswer4weight());
        }
    }

}
