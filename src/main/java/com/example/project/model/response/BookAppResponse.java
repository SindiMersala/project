package com.example.project.model.response;

import com.example.project.model.BookApp;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BookAppResponse {

    public final long id;
    public final String date;
    public final String vaccineName;
    public final List<VaccineCenterResponse> vaccineCenterResponses;

    public static BookAppResponse fromEntity(BookApp b) {
        var date = b.getDate();

        var vaccineCenterResponses = b.getVaccineCenters()
                .stream()
                .map(VaccineCenterResponse::fromEntity)
                .collect(Collectors.toList());

        return new BookAppResponse(
                b.getId(),
                date,
                b.getVaccine().getType(),
                vaccineCenterResponses
        );
    }
}