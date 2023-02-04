package com.teamway.personalitytest.service;

import com.teamway.personalitytest.dto.AnswerDto;
import com.teamway.personalitytest.dto.QuestionDto;
import com.teamway.personalitytest.entity.Answer;
import com.teamway.personalitytest.repository.AnswerRepository;
import com.teamway.personalitytest.entity.Question;
import com.teamway.personalitytest.exception.ResourceNotFoundException;
import com.teamway.personalitytest.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    public QuestionDto createQuestion(QuestionDto payload) {
        Question question = new ModelMapper().map(payload, Question.class);
        List<Answer> answers = saveNewAnswersToDb(payload.getAnswers());
        question.setAnswers(answers);
        return QuestionDto.buildFrom(questionRepository.save(question));
    }

    public List<QuestionDto> getQuestions() {
        return ((List<Question>) questionRepository.findAll()).stream().map(QuestionDto::buildFrom)
                .collect(Collectors.toList());
    }

    public QuestionDto getQuestion(Long id) throws ResourceNotFoundException {
        return questionRepository.findById(id).map(QuestionDto::buildFrom).orElseThrow(ResourceNotFoundException::new);
    }

    public void deleteQuestion(Long id) throws ResourceNotFoundException {
        Question entity = questionRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        deleteOldAnswersFromDb(entity);
        questionRepository.deleteById(id);
    }

    public QuestionDto updateQuestion(Long id, QuestionDto payload) throws ResourceNotFoundException {
        Question entity = questionRepository.findById(id).orElseThrow(ResourceNotFoundException::new);

        deleteOldAnswersFromDb(entity);

        List<Answer> answers = saveNewAnswersToDb(payload.getAnswers());
        entity.setAnswers(answers);
        entity = questionRepository.save(entity);

        return QuestionDto.buildFrom(entity);
    }

    private List<Answer> saveNewAnswersToDb(List<AnswerDto> answersDto) {
        List<Answer> answers = answersDto.stream().map(a -> new Answer(a.getAnswer(), a.getAnswerWeight())).collect(Collectors.toList());
        return (List<Answer>) answerRepository.saveAll(answers);
    }

    private void deleteOldAnswersFromDb(Question entity) {
        List<Answer> oldAnswers = entity.getAnswers();
        answerRepository.deleteAll(oldAnswers);
    }

}
