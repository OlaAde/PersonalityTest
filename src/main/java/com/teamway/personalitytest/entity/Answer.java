package com.teamway.personalitytest.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "answers")
@Getter
@Setter
@NoArgsConstructor
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String answer;

    @Column(name = "answer_weight")
    private Integer answerWeight;

    public Answer(String answer, Integer answerWeight) {
        this.answer = answer;
        this.answerWeight = answerWeight;
    }
}
