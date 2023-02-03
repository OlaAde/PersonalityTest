package com.teamway.personalitytest.controller;

import com.google.gson.Gson;
import com.teamway.personalitytest.dto.AnswerDto;
import com.teamway.personalitytest.dto.QuestionDto;
import com.teamway.personalitytest.service.QuestionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class QuestionControllerIntegrationTests {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private QuestionService questionService;

    @Test
    public void whenPostQuestion_thenCreateQuestion() throws Exception {
        QuestionDto questionDto = new QuestionDto("You’re really busy at work and a colleague is telling you their life story and personal woes. You:", new ArrayList<>() {{
            add(new AnswerDto("Don’t dare to interrupt them ", 10));
            add(new AnswerDto("Think it’s more important to give them some of your time; work can wait", 20));
            add(new AnswerDto("Listen, but with only with half an ear", 30));
            add(new AnswerDto("Interrupt and explain that you are really busy at the moment", 40));
        }});

        given(questionService.createQuestion(Mockito.any(QuestionDto.class))).willReturn(questionDto);

        mvc.perform(post("/api/questions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(questionDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.question", is(questionDto.getQuestion())))
                .andExpect(jsonPath("$.answers[0].answer", is(questionDto.getAnswers().get(0).getAnswer())))
                .andExpect(jsonPath("$.answers[0].answerWeight", is(questionDto.getAnswers().get(0).getAnswerWeight())));
        verify(questionService, VerificationModeFactory.times(1)).createQuestion(questionDto);
        reset(questionService);
    }

    @Test
    public void givenQuestions_whenGetQuestions_thenReturnJsonList() throws Exception {
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
        List<QuestionDto> allQuestions = Arrays.asList(question1Dto, question2Dto, question3Dto);

        given(questionService.getQuestions()).willReturn(allQuestions);

        mvc.perform(get("/api/questions").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].question", is(question1Dto.getQuestion())))
                .andExpect(jsonPath("$[1].question", is(question2Dto.getQuestion())))
                .andExpect(jsonPath("$[2].question", is(question3Dto.getQuestion())));
        verify(questionService, VerificationModeFactory.times(1)).getQuestions();
        reset(questionService);
    }

    @Test
    public void givenId_whenGetQuestion_thenReturnJson() throws Exception {
        QuestionDto questionDto = new QuestionDto("You’re really busy at work and a colleague is telling you their life story and personal woes. You:", new ArrayList<>() {{
            add(new AnswerDto("Don’t dare to interrupt them ", 10));
            add(new AnswerDto("Think it’s more important to give them some of your time; work can wait", 20));
            add(new AnswerDto("Listen, but with only with half an ear", 30));
            add(new AnswerDto("Interrupt and explain that you are really busy at the moment", 40));
        }});

        given(questionService.getQuestion(Mockito.anyLong())).willReturn(questionDto);

        mvc.perform(get("/api/questions/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.question", is(questionDto.getQuestion())))
                .andExpect(jsonPath("$.answers[0].answer", is(questionDto.getAnswers().get(0).getAnswer())))
                .andExpect(jsonPath("$.answers[0].answerWeight", is(questionDto.getAnswers().get(0).getAnswerWeight())));
        verify(questionService, VerificationModeFactory.times(1)).getQuestion(1L);
        reset(questionService);
    }

    @Test
    public void whenPatchQuestion_thenUpdateQuestion() throws Exception {

        QuestionDto payload = new QuestionDto(null, null);

        QuestionDto updatedQuestion = new QuestionDto("You’re really busy at work and a colleague is telling you their life story and personal woes. You:", new ArrayList<>() {{
            add(new AnswerDto("Don’t dare to interrupt them ", 10));
            add(new AnswerDto("Think it’s more important to give them some of your time; work can wait", 20));
            add(new AnswerDto("Listen, but with only with half an ear", 30));
            add(new AnswerDto("Interrupt and explain that you are really busy at the moment", 40));
        }});

        given(questionService.updateQuestion(Mockito.anyLong(), Mockito.any(QuestionDto.class))).willReturn(updatedQuestion);

        mvc.perform(patch("/api/questions/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(payload)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.question", is(updatedQuestion.getQuestion())))
                .andExpect(jsonPath("$.answers[0].answer", is(updatedQuestion.getAnswers().get(0).getAnswer())))
                .andExpect(jsonPath("$.answers[0].answerWeight", is(updatedQuestion.getAnswers().get(0).getAnswerWeight())));
        verify(questionService, VerificationModeFactory.times(1)).updateQuestion(1L, payload);
        reset(questionService);
    }


}
