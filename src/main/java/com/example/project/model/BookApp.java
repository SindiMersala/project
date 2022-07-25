package com.example.project.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
@JoinTable(
        name = "bookApp_vaccineCenter",
        joinColumns = @JoinColumn(name = "bookApp_id"),
        inverseJoinColumns = @JoinColumn(name = "vaccineCenter_id")
)
private Set<VaccineCenter>vaccineCenters=new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "vaccine_id")
    private Vaccine vaccine;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;

//    @OneToMany(
//            mappedBy = "bookApp",
//            fetch = FetchType.EAGER,
//            cascade = CascadeType.PERSIST
//    )
//    @JsonManagedReference
//    private List<VaccineCenter> vaccineCenters = new ArrayList<>();

//    public void addVaccineCenter(VaccineCenter v) {
//        vaccineCenters.add(v);
//        v.setBookApps(this);
//    }

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
