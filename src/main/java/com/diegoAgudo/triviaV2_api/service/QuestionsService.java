package com.diegoAgudo.triviaV2_api.service;

import com.diegoAgudo.triviaV2_api.exceptions.ErrorProcessException;
import com.diegoAgudo.triviaV2_api.exceptions.NotFoundException;
import com.diegoAgudo.triviaV2_api.model.Question;
import com.diegoAgudo.triviaV2_api.repository.QuestionRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class QuestionsService {

    private static final Logger logger = LoggerFactory.getLogger(QuestionsService.class);
    @Autowired
    private QuestionRepo questionRepo;

    public Question getOneById(Integer id) throws ErrorProcessException {
        logger.info("Inicio - getOneById - id: {}", id);
        try {
            return questionRepo.findById(id)
                    .orElseThrow(() -> new NotFoundException("This question doesn´t exist in the DB"));

        }catch (RuntimeException e){
            throw new ErrorProcessException(e.getMessage());
        }
    }

    public Question getOneRandom() throws ErrorProcessException {
        logger.info("Inicio - getOneRandom");
        try {
            int cantidadPreguntas = (int) questionRepo.count();
            if (cantidadPreguntas == 0) {
                throw new NotFoundException("No questions available in the database.");
            }

            Integer id = ThreadLocalRandom.current().nextInt(1, cantidadPreguntas + 1);  // Genera un número entre 1 y cantidadPreguntas (inclusive)
            logger.info("getOneRandom - Random id: {}", id);
            return questionRepo.findById(id)
                    .orElseThrow(() -> new NotFoundException("This question doesn´t exist in the DB"));

        }catch (RuntimeException e){
            throw new ErrorProcessException(e.getMessage());
        }
    }

    public Question saveOne(Question question) throws ErrorProcessException {
        logger.info("Inicio - saveOne - Question: {}", question);
        try {
            if(question == null) throw new ErrorProcessException("The object is NULL");
            return questionRepo.save(question);
        }catch (ErrorProcessException e) {
            logger.error("The object is NULL: {}", e.getMessage());
            throw e;
        }catch (Exception e){
            logger.error("Error saving question: {}", e.getMessage());
            throw new ErrorProcessException("An error occurred while processing the request");
        }
    }

    public Question updateOne(Question question) throws ErrorProcessException {
        logger.info("Inicio - updateOne - Question: {}", question);
        try {
            if (!questionRepo.existsById(question.getQuestionId())) throw new NotFoundException("This question doesn´t exist in the DB");
            return questionRepo.save(question);
        }catch (NotFoundException e) {
            logger.error("Question not found: {}", e.getMessage());
            throw e;
        }catch (Exception e){
            logger.error("Error updating question: {}", e.getMessage());
            throw new ErrorProcessException("An error occurred while processing the request");
        }
    }


}
