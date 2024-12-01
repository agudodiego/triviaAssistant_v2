package com.diegoAgudo.triviaV2_api.repository;

import com.diegoAgudo.triviaV2_api.model.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface QuestionRepo extends CrudRepository<Question, Integer> {
    Optional<Question> findById(Integer id);
}
