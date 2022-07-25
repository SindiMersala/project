package com.example.project.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private Set<BookApp> bookApps = new HashSet<>();

    @OneToMany(
            mappedBy = "user",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonManagedReference
    private List<Answer> answers = new ArrayList<>();

public void addBookApp(BookApp bookApp) {
    bookApps.add(bookApp);
    bookApp.setUser(this);
}

    public void removeBookApp(BookApp bookApp) {
        bookApps.remove(bookApp);
        bookApp.setUser(null);
    }
    public void addAnswer(Answer a) {
        answers.add(a);
        a.setUser(this);
    }

    public void removeAnswer(Answer a) {
        answers.remove(a);
        a.setUser(null);
    }


    public User(Set<BookApp> bookApps){
 this.bookApps.forEach(bookApp-> bookApp.setUser(this));}

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

    public Set<BookApp> getName() {
    return this.getName();
    }
}