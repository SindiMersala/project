package com.example.project.model.response;

import com.example.project.model.Answer;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class AnswerResponse {


        public final long id;
        public final String answer1;
    public final String answer2;
    public final String answer3;



    public static AnswerResponse fromEntity(Answer a) {

        return new AnswerResponse(
                a.getId(),
                a.getAnswer1(),
                a.getAnswer2(),
                a.getAnswer3()
        );
    }
}
