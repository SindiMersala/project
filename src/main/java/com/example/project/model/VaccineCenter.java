package com.example.project.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Validated
@Entity
@Table(name = "vaccine_center")
@NoArgsConstructor
@Getter
@Setter
public class VaccineCenter implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name="name")
    private String name;



    @OneToMany(
            mappedBy = "vaccineCenter",
            fetch = FetchType.EAGER,
            cascade = CascadeType.PERSIST
    )
    @JsonManagedReference
    private Set<BookApp> bookApps = new HashSet<>();

    public void addBookApp(BookApp b) {
        bookApps.add(b);
        b.setVaccineCenter(this);
    }


    public VaccineCenter(Set<BookApp> bookApps){
        this.bookApps.forEach(bookApp-> bookApp.setVaccineCenter(this));}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VaccineCenter that = (VaccineCenter) o;
        return id == that.id ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
