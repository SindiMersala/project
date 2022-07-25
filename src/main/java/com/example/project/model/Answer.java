package com.example.project.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Validated
@Entity
@Table(name = "answer")
@NoArgsConstructor
@Getter
@Setter
public class Answer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "answer1")
    private String answer1;

    @Column(name = "answer2")
    private String answer2;
    @Column(name = "answer3")
    private String answer3;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_id")
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer = (Answer) o;
        return id == answer.id && Objects.equals(answer1, answer.answer1) && Objects.equals(answer2, answer.answer2) && Objects.equals(answer3, answer.answer3) && Objects.equals(user, answer.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, answer1, answer2, answer3, user);
    }
}
