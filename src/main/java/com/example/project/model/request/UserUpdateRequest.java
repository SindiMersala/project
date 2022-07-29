package com.example.project.model.request;

import com.example.project.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserUpdateRequest {
    private long id;

    @Email(message = "Email error")
    @NotBlank
    private String email;
    @NotBlank(message = "First Name error")
    private String firstName;
    @NotBlank(message = "Last Name error")
    private String lastName;
    @NotBlank
    private String password;
    @NotBlank
    private String IDCard;
    @NotNull
    private long age;
    @NotBlank
    private String city;

    @NotBlank
    private String state;

    @NotBlank
    private String address;

    @NotBlank
    private String coordinates;
    public static UserUpdateRequest fromEntity(User usr) {
        var out = new UserUpdateRequest();
        out.setId(usr.getId());
        out.setEmail(usr.getEmail());
        out.setFirstName(usr.getFirstName());
        out.setLastName(usr.getLastName());
        out.setPassword(usr.getPassword());
        out.setIDCard(usr.getIDCard());
        out.setAge(usr.getAge());
        out.setCity(usr.getCity());
        out.setState(usr.getState());
        out.setAddress(usr.getAddress());
        out.setCoordinates(usr.getCoordinates());

        return out;
    }
    public void setEmail(String email) {
        this.email = email.trim();
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName.trim();
    }
    public void setLastName(String lastName) {
        this.lastName = lastName.trim();
    }
    public void setPassword(String password) {
        this.password = password.trim();
    }
    public void setIDCard(String IDCard) {
        this.IDCard = IDCard.trim();
    }
    public void setAge(long age) {this.age = age;}
    public void setCity(String city) {
        this.city = city.trim();
    }
    public void setState(String state) {
        this.state = state.trim();
    }
    public void setAddress(String address) {
        this.address = address.trim();
    }
    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates.trim();
    }

 }
