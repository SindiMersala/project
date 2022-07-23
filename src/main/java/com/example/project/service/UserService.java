package com.example.project.service;
import com.example.project.model.response.SimpleUserResponse;
import com.example.project.repository.UserRepository;
import com.example.project.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.Optional;

@Service
public class UserService {
	private final UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository)
	{
		this.userRepository = userRepository;
	}

	@Transactional

	public Optional<SimpleUserResponse> getUserById(long id) {
		return userRepository.findById(id).map(SimpleUserResponse::fromEntity);
	}

	public Optional<User> getUserByPrincipal(Principal principal) {
		return userRepository.findByEmail(principal.getName());
	}



}

