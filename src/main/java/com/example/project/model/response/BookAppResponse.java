package com.example.project.model.response;

import com.example.project.model.BookApp;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BookAppResponse {

    public final long id;
    public final String date;
    public final String vaccineName;
    public final String vaccineCenterName;
    public static BookAppResponse fromEntity(BookApp b) {
        var date = b.getDate();

        return new BookAppResponse(
                b.getId(),
                date,
                b.getVaccine().getType(),
                b.getVaccineCenter().getName()
        );
    }
}