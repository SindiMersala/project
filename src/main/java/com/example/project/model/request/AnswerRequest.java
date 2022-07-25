package com.example.project.model.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;


    @Getter
    @Setter
    @NoArgsConstructor
    public class AnswerRequest {
        private long id;
        @NotNull
        private String answer1 ="";
        @NotNull
        private String answer2 ="";
        @NotNull
        private String answer3 ="";

        private long userId;


        public void setAnswer1(String answer1) {
            this.answer1 = answer1.trim();
        }

        public void setAnswer2(String answer2) {
            this.answer2 = answer2.trim();
        }

        public void setAnswer3(String answer3) {
            this.answer3 = answer3.trim();
        }
}
