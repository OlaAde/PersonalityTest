package com.teamway.personalitytest.dto;

import com.teamway.personalitytest.entity.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDto {
    private String question;
    private List<AnswerDto> answers;
    public static QuestionDto buildFrom(Question question) {
        return new ModelMapper().map(question, QuestionDto.class);
    }
}
