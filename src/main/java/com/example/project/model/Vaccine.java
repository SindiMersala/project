package com.example.project.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Validated
@Entity
@Table(name = "vaccine")
@NoArgsConstructor
@Getter
@Setter
public class Vaccine implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "type")
    private String type;

    @Column(name = "characteristic")
    private String characteristic;

    @Column(name = "inventory")
    private String inventory;

    @OneToMany(mappedBy = "vaccine")
    private Set<BookApp> bookApps = new HashSet<>();

    @OneToMany(mappedBy = "vaccine")
    private List<Status> statuses = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
       Vaccine vaccine = (Vaccine) o;
        return id == vaccine.id;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(id);
    }
}
