package com.example.project.config;

import com.example.project.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class UserDetailsServiceImpl implements UserDetailsService {
	private final UserRepository repo;

	public UserDetailsServiceImpl(UserRepository repo) {
		this.repo = repo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		var user = repo.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("Cannot find user: " + username));

		var authority = new SimpleGrantedAuthority(user.getApplicationRole().getName());

		return new User(user.getEmail(), user.getPassword(), List.of(authority));
	}
}
