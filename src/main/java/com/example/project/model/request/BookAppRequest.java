package com.example.project.model.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotNull;

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
    public String getDate() {
        return date;
    }
    public void setDate(String date){this.date=date;}
    public long getId() {
             return this.id;
    }
    public void setId(long id) {
        this.id=id;
    }

}
