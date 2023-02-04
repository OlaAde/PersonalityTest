package com.teamway.personalitytest;

import com.teamway.personalitytest.dto.AnswerDto;
import com.teamway.personalitytest.dto.QuestionDto;
import com.teamway.personalitytest.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;


@Component
@RequiredArgsConstructor
@Profile("!test")
public class DataIngestionRunner implements CommandLineRunner {
    private final QuestionService questionService;

    @Override
    public void run(String... args) {
        questionService.createQuestion(new QuestionDto("You’re really busy at work and a colleague is telling you their life story and personal woes. You:", new ArrayList<>() {{
            add(new AnswerDto("Don’t dare to interrupt them ", 10));
            add(new AnswerDto("Think it’s more important to give them some of your time; work can wait", 20));
            add(new AnswerDto("Listen, but with only with half an ear", 30));
            add(new AnswerDto("Interrupt and explain that you are really busy at the moment", 40));
        }}));

        questionService.createQuestion(new QuestionDto("You’ve been sitting in the doctor’s waiting room for more than 25 minutes. You:", new ArrayList<>() {{
            add(new AnswerDto("Look at your watch every two minutes", 10));
            add(new AnswerDto("Bubble with inner anger, but keep quiet", 20));
            add(new AnswerDto("Explain to other equally impatient people in the room that the doctor is always running late", 30));
            add(new AnswerDto("Complain in a loud voice, while tapping your foot impatiently", 40));
        }}));

        questionService.createQuestion(new QuestionDto("You’re having an animated discussion with a colleague regarding a project that you’re in charge of. You:", new ArrayList<>() {{
            add(new AnswerDto("Don’t dare contradict them", 10));
            add(new AnswerDto("Think that they are obviously right", 20));
            add(new AnswerDto("Defend your own point of view, tooth and nail", 30));
            add(new AnswerDto("Continuously interrupt your colleague", 40));
        }}));

        questionService.createQuestion(new QuestionDto("You are taking part in a guided tour of a museum. You:", new ArrayList<>() {{
            add(new AnswerDto("Are a bit too far towards the back so don’t really hear what the guide is saying", 10));
            add(new AnswerDto("Follow the group without question", 20));
            add(new AnswerDto("Make sure that everyone is able to hear properly", 30));
            add(new AnswerDto("Are right up the front, adding your own comments in a loud voice", 40));
        }}));


        questionService.createQuestion(new QuestionDto("During dinner parties at your home, you have a hard time with people who:", new ArrayList<>() {{
            add(new AnswerDto("Ask you to tell a story in front of everyone else", 10));
            add(new AnswerDto("Talk privately between themselves", 20));
            add(new AnswerDto("Hang around you all evening", 30));
            add(new AnswerDto("Always drag the conversation back to themselves", 40));
        }}));

    }

}
