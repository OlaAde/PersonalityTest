package com.teamway.personalitytest.repository;

import com.teamway.personalitytest.entity.Question;
import org.springframework.data.repository.CrudRepository;

public interface QuestionRepository extends CrudRepository<Question, Long> {
}
