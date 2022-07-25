package com.example.project.model.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BookAppRequest {
    private long id;
    @NotNull
    private String date ="";

    private long userId;
    @NotNull
    private long vaccineCenterId;

        @NotNull
        private long vaccineId;


public void setUserId(long userId){
    this.userId=userId;
}
    public String getDate() {
        return date;
    }

    public long getId() {
             return this.id;
    }
   public long getUserId(){
        return this.userId;
   }

}
