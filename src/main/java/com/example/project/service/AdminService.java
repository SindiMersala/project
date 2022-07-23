package com.example.project.service;
import com.example.project.repository.ApplicationRoleRepository;
import com.example.project.repository.UserRepository;
import com.example.project.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class AdminService {
	private final UserRepository userRepo;
	private final ApplicationRoleRepository applicationRoleRepo;

	@Autowired
	public AdminService(UserRepository userRepo,ApplicationRoleRepository applicationRoleRepo)
	{
		this.userRepo = userRepo;
		this.applicationRoleRepo = applicationRoleRepo;
	}

	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

}

