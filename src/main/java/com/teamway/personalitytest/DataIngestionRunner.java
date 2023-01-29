package com.teamway.personalitytest;

import com.teamway.personalitytest.dto.QuestionDto;
import com.teamway.personalitytest.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class DataIngestionRunner implements CommandLineRunner {
    private final QuestionService questionService;

    @Override
    public void run(String... args) {
        questionService.createQuestion(new QuestionDto("You’re really busy at work and a colleague is telling you their life story and personal woes. You:",
                "Don’t dare to interrupt them ",
                "Think it’s more important to give them some of your time; work can wait",
                "Listen, but with only with half an ear",
                "Interrupt and explain that you are really busy at the moment",
                10, 20, 30, 40));
        questionService.createQuestion(new QuestionDto("You’ve been sitting in the doctor’s waiting room for more than 25 minutes. You:",
                "Look at your watch every two minutes",
                "Bubble with inner anger, but keep quiet",
                "Explain to other equally impatient people in the room that the doctor is always running late",
                "Complain in a loud voice, while tapping your foot impatiently",
                10, 20, 30, 40));
        questionService.createQuestion(new QuestionDto("You’re having an animated discussion with a colleague regarding a project that you’re in charge of. You:",
                "Don’t dare contradict them",
                "Think that they are obviously right",
                "Defend your own point of view, tooth and nail",
                "Continuously interrupt your colleague",
                10, 20, 30, 40));
        questionService.createQuestion(new QuestionDto("You are taking part in a guided tour of a museum. You:",
                "Are a bit too far towards the back so don’t really hear what the guide is saying",
                "Follow the group without question",
                "Make sure that everyone is able to hear properly",
                "Are right up the front, adding your own comments in a loud voice",
                10, 20, 30, 40));
        questionService.createQuestion(new QuestionDto("During dinner parties at your home, you have a hard time with people who:",
                "Ask you to tell a story in front of everyone else",
                "Talk privately between themselves",
                "Hang around you all evening",
                "Always drag the conversation back to themselves",
                10, 20, 30, 40));
    }

}
