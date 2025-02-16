package com.diegoAgudo.triviaV2_api.repository;

import com.diegoAgudo.triviaV2_api.model.Question;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class QuestionsSeeder {

    private static final Logger logger = LoggerFactory.getLogger(QuestionsSeeder.class);

    @Autowired
    QuestionRepo questionRepo;

    public String seed() {
        logger.info("Inicio - seed");
        try {
            if(questionRepo.count() == 0){
                logger.info("El seeder se comienza a ejecutar");
                loadQuestions();
                logger.info("El seeder se termino de ejecutar");
            }else{
                logger.info("El seeder NO se ejecutara");
                return "El seeder NO se ejecutara";
            }
            logger.info("fin - seed");
            return "Tabla cargada";
        } catch (IOException e){
            logger.info("problema de conexion con BD");
            return "No se pudo cargar la tabla";
        }
    }

    public void loadQuestions() throws IOException {
        // Cargar archivo JSON desde resources
        logger.info("cargando archivo de preguntas");
        ClassPathResource resource = new ClassPathResource("base-preguntas.json");
        ObjectMapper objectMapper = new ObjectMapper();

        // Leer el archivo como un InputStream (funciona tanto en JAR como en desarrollo)
        InputStream inputStream = resource.getInputStream();

        // Convertir JSON a lista de objetos Question
        List<Question> questions = objectMapper.readValue(inputStream, new TypeReference<List<Question>>(){});

        // Insertar las preguntas en la base de datos
        questionRepo.saveAll(questions);
    }

}
