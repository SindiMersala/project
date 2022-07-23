package com.example.project.repository;

import com.example.project.model.ApplicationRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRoleRepository extends JpaRepository<ApplicationRole, Integer> {}
