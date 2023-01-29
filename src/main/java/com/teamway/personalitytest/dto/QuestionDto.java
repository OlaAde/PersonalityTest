package com.teamway.personalitytest.dto;

import com.teamway.personalitytest.entity.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDto {
    private String question;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private Integer answer1weight;
    private Integer answer2weight;
    private Integer answer3weight;
    private Integer answer4weight;

    public static QuestionDto buildFrom(Question question) {
        return new ModelMapper().map(question, QuestionDto.class);
    }
}
