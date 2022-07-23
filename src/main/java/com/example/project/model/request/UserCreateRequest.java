package com.example.project.model.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserCreateRequest {
	@Email
	@NotBlank
	private String email;

	@NotBlank
	private String firstName;

	@NotBlank
	private String lastName;

	@NotBlank
	private String password;
	private String jobPosition;
	private MultipartFile avatar;

	public void setEmail(String email) {
		this.email = email.trim();
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName.trim();
	}

	public void setLastName(String lastName) {
		this.lastName = lastName.trim();
	}

	}
