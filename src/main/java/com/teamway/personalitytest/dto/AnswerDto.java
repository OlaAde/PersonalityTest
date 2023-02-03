package com.teamway.personalitytest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AnswerDto {
    private Long id;
    private String answer;
    private Integer answerWeight;

    public AnswerDto(String answer, Integer answerWeight) {
        this.answer = answer;
        this.answerWeight = answerWeight;
    }
}
