package com.teamway.personalitytest.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "questions")
@Getter
@Setter
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String question;
    @Column(name = "answer_1")
    private String answer1;
    @Column(name = "answer_2")
    private String answer2;
    @Column(name = "answer_3")
    private String answer3;
    @Column(name = "answer_4")
    private String answer4;
    @Column(name = "answer_1_weight")
    private Integer answer1weight;
    @Column(name = "answer_2_weight")
    private Integer answer2weight;
    @Column(name = "answer_3_weight")
    private Integer answer3weight;
    @Column(name = "answer_4_weight")
    private Integer answer4weight;

}
