package com.example.project.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import java.io.Serializable;

@Validated
@Entity
@Table(name = "user")
@NoArgsConstructor
@Getter
@Setter
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "id_card")
    private String IDCard;

    @Column(name = "city")
    private String city;
    @Column(name = "state")
    private String state;
    @Column(name = "address")
    private String address;
    @Column(name = "coordinates")
    private String coordinates;


    @ManyToOne
    @JoinColumn(name = "application_role_id", nullable = false)
    private ApplicationRole applicationRole;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(id);
    }
}