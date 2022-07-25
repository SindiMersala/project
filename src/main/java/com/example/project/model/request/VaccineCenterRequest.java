package com.example.project.model.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor

public class VaccineCenterRequest {
@NotBlank
private String name;

    public void setName(String name) {
        this.name = name.trim();
    }
}
