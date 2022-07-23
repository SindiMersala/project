package com.example.project.repository;

import com.example.project.util.SqlMapper;
import com.example.project.model.ApplicationRole;
import com.example.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);

	@Query(value = "select id from user where email = ?1", nativeQuery = true)
	long getUserIdByEmail(String email);


}
