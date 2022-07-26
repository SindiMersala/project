package com.example.project.repository;

import com.example.project.model.BookApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookAppRepository extends JpaRepository<BookApp, Long> {

}
