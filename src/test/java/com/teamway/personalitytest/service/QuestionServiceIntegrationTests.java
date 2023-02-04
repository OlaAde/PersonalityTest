package com.teamway.personalitytest.service;

import com.teamway.personalitytest.dto.AnswerDto;
import com.teamway.personalitytest.dto.QuestionDto;
import com.teamway.personalitytest.entity.Question;
import com.teamway.personalitytest.exception.ResourceNotFoundException;
import com.teamway.personalitytest.repository.QuestionRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatException;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class QuestionServiceIntegrationTests {
    @Autowired
    private QuestionService questionService;

    @MockBean
    private QuestionRepository questionRepository;


    @Before
    public void setUp() {
        QuestionDto question1Dto = new QuestionDto("You’re really busy at work and a colleague is telling you their life story and personal woes. You:", new ArrayList<>() {{
            add(new AnswerDto("Don’t dare to interrupt them ", 10));
            add(new AnswerDto("Think it’s more important to give them some of your time; work can wait", 20));
            add(new AnswerDto("Listen, but with only with half an ear", 30));
            add(new AnswerDto("Interrupt and explain that you are really busy at the moment", 40));
        }});

        QuestionDto question2Dto = new QuestionDto("You’ve been sitting in the doctor’s waiting room for more than 25 minutes. You:", new ArrayList<>() {{
            add(new AnswerDto("Look at your watch every two minutes", 10));
            add(new AnswerDto("Bubble with inner anger, but keep quiet", 20));
            add(new AnswerDto("Explain to other equally impatient people in the room that the doctor is always running late", 30));
            add(new AnswerDto("Complain in a loud voice, while tapping your foot impatiently", 40));
        }});
        QuestionDto question3Dto = new QuestionDto("You’re having an animated discussion with a colleague regarding a project that you’re in charge of. You:", new ArrayList<>() {{
            add(new AnswerDto("Don’t dare contradict them", 10));
            add(new AnswerDto("Think that they are obviously right", 20));
            add(new AnswerDto("Defend your own point of view, tooth and nail", 30));
            add(new AnswerDto("Continuously interrupt your colleague", 40));
        }});


        Question question1Entity = new ModelMapper().map(question1Dto, Question.class);
        question1Entity.setId(1L);
        Question question2Entity = new ModelMapper().map(question2Dto, Question.class);
        question2Entity.setId(2L);
        Question question3Entity = new ModelMapper().map(question3Dto, Question.class);
        question3Entity.setId(3L);

        List<Question> allQuestions = Arrays.asList(question1Entity, question2Entity, question3Entity);

        Mockito.when(questionRepository.findById(question1Entity.getId())).thenReturn(Optional.of(question1Entity));
        Mockito.when(questionRepository.findById(0L)).thenReturn(Optional.empty());
        Mockito.when(questionRepository.findAll()).thenReturn(allQuestions);
        Mockito.when(questionRepository.findById(-99L)).thenReturn(Optional.empty());
    }

    @Test
    public void whenValidId_thenQuestionShouldBeFound() throws ResourceNotFoundException {
        QuestionDto fromDb = questionService.getQuestion(1L);
        assertThat(fromDb.getQuestion()).isEqualTo("You’re really busy at work and a colleague is telling you their life story and personal woes. You:");

        verifyFindByIdIsCalledOnce();
    }

    @Test
    public void whenInValidId_thenQuestionShouldNotBeFound() {
        assertThatException().isThrownBy(() -> {
            QuestionDto fromDb = questionService.getQuestion(-99L);
            verifyFindByIdIsCalledOnce();
            assertThat(fromDb).isNull();
        });
    }


    private void verifyFindByIdIsCalledOnce() {
        Mockito.verify(questionRepository, VerificationModeFactory.times(1)).findById(Mockito.anyLong());
        Mockito.reset(questionRepository);
    }


    @Test
    public void given3Questions_whengetAll_thenReturn3Records() {
        QuestionDto question1Dto = new QuestionDto("You’re really busy at work and a colleague is telling you their life story and personal woes. You:", new ArrayList<>() {{
            add(new AnswerDto("Don’t dare to interrupt them ", 10));
            add(new AnswerDto("Think it’s more important to give them some of your time; work can wait", 20));
            add(new AnswerDto("Listen, but with only with half an ear", 30));
            add(new AnswerDto("Interrupt and explain that you are really busy at the moment", 40));
        }});

        QuestionDto question2Dto = new QuestionDto("You’ve been sitting in the doctor’s waiting room for more than 25 minutes. You:", new ArrayList<>() {{
            add(new AnswerDto("Look at your watch every two minutes", 10));
            add(new AnswerDto("Bubble with inner anger, but keep quiet", 20));
            add(new AnswerDto("Explain to other equally impatient people in the room that the doctor is always running late", 30));
            add(new AnswerDto("Complain in a loud voice, while tapping your foot impatiently", 40));
        }});
        QuestionDto question3Dto = new QuestionDto("You’re having an animated discussion with a colleague regarding a project that you’re in charge of. You:", new ArrayList<>() {{
            add(new AnswerDto("Don’t dare contradict them", 10));
            add(new AnswerDto("Think that they are obviously right", 20));
            add(new AnswerDto("Defend your own point of view, tooth and nail", 30));
            add(new AnswerDto("Continuously interrupt your colleague", 40));
        }});

        List<QuestionDto> allQuestions = questionService.getQuestions();
        verifyFindAllQuestionsIsCalledOnce();
        assertThat(allQuestions).hasSize(3).extracting(QuestionDto::getQuestion)
                .contains(question1Dto.getQuestion(), question2Dto.getQuestion(), question3Dto.getQuestion());
    }

    private void verifyFindAllQuestionsIsCalledOnce() {
        Mockito.verify(questionRepository, VerificationModeFactory.times(1)).findAll();
        Mockito.reset(questionRepository);
    }

}
