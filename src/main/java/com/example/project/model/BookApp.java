package com.example.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import java.io.Serializable;
@Validated
@Entity
@Table(name = "bookApp")
@NoArgsConstructor
@Getter
@Setter
public class BookApp implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "id", nullable = false)
    private long id;
    @Column(name="date")
    private String date;

    @ManyToOne
    @JoinColumn(name = "vaccine_id")
    private Vaccine vaccine;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;


    @ManyToOne
    @JoinColumn(name="vaccineCenter_id")
    private VaccineCenter vaccineCenter;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookApp bookApp = (BookApp) o;
        return id == bookApp.id;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(id);
    }

}
