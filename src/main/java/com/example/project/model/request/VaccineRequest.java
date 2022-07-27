package com.example.project.model.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
    @Setter
    @NoArgsConstructor
    public class VaccineRequest {
        @NotBlank
        private String type;
        @NotBlank
        private String characteristic;
        @NotNull
        private long inventory;


        public void setType(String type) {
            this.type = type.trim();
        }

    public void setCharacteristic(String characteristic) {
        this.characteristic = characteristic.trim();
    }

    public void setInventory(long inventory) {
        this.inventory = inventory;
    }
    }


