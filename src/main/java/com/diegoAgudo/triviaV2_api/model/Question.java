package com.diegoAgudo.triviaV2_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder // lo necesito en la entidad para poder mapear luego en el dto
@AllArgsConstructor // @Builder necesita de esta annotation
@NoArgsConstructor
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Integer questionId;
    @Column(name = "categoria", nullable = false)
    private String categoria;
    @Column(name = "pregunta", nullable = false)
    private String pregunta;
    @Column(name = "respuesta", nullable = false)
    private String respuesta;
    @Column(name = "incorrecta1", nullable = false)
    private String incorrecta1;
    @Column(name = "incorrecta2", nullable = false)
    private String incorrecta2;
    @Column(name = "incorrecta3", nullable = false)
    private String incorrecta3;
    @Column(name = "imagen")
    private String imagen;

}
