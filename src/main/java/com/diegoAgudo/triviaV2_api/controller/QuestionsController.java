package com.diegoAgudo.triviaV2_api.controller;

import com.diegoAgudo.triviaV2_api.exceptions.ErrorProcessException;
import com.diegoAgudo.triviaV2_api.model.Question;
import com.diegoAgudo.triviaV2_api.repository.QuestionsSeeder;
import com.diegoAgudo.triviaV2_api.service.QuestionsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/apiTrivia")
public class QuestionsController {

    @Autowired
    private QuestionsService questionsService;
    @Autowired
    private QuestionsSeeder questionsSeeder;

    @GetMapping("/seed")
    public ResponseEntity<String> startSeeding() throws ErrorProcessException {
        return ResponseEntity.status(HttpStatus.OK).body(questionsSeeder.seed());
    }

    @GetMapping("/public")
    public ResponseEntity<Question> getOneQuestionRandomly() throws ErrorProcessException {
        return ResponseEntity.status(HttpStatus.OK).body(questionsService.getOneRandom());
    }

    @GetMapping("/question/{id}")
    public ResponseEntity<Question> getOneQuestionById(@PathVariable Integer id) throws ErrorProcessException {
        return ResponseEntity.status(HttpStatus.OK).body(questionsService.getOneById(id));
    }

    @PostMapping("/question")
    public ResponseEntity<Question> saveOneQuestion(@Valid @RequestBody Question question) throws ErrorProcessException {
        return ResponseEntity.status(HttpStatus.CREATED).body(questionsService.saveOne(question));
    }

    @PutMapping("/question")
    public ResponseEntity<Question> updateOneQuestion(@Valid @RequestBody Question question) throws ErrorProcessException {
        return ResponseEntity.status(HttpStatus.ACCEPTED.value())
                .body(questionsService.updateOne(question));
    }

}
