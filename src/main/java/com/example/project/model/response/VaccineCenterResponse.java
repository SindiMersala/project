package com.example.project.model.response;

import com.example.project.model.User;
import com.example.project.model.VaccineCenter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter

public class VaccineCenterResponse {

    private long id;

    private String name;
    public static VaccineCenterResponse fromEntity(VaccineCenter v) {
        return new VaccineCenterResponse(
                v.getId(),
                v.getName()
        );
    }



}