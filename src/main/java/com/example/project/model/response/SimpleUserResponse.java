package com.example.project.model.response;
import com.example.project.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class SimpleUserResponse {
	private long id;
	private String fullName;
	private String email;

	public static SimpleUserResponse fromEntity(User u) {
		return new SimpleUserResponse(
				u.getId(),
				u.getFirstName() + ' ' + u.getLastName(),
				u.getEmail()
		);
	}
	public String getFullName(){
		return this.fullName;
	}
}
