package com.example.project.repository;

import com.example.project.model.ApplicationRole;
import com.example.project.model.BookApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookAppRepository extends JpaRepository<BookApp, Long> {
    @Query(value = "select vaccine_id from bookApp where id = ?", nativeQuery = true)
     long findVaccineForBookApp(long bookAppId);
}
