package com.example.project.repository;

import com.example.project.model.VaccineCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VaccineCenterRepository extends JpaRepository<VaccineCenter, Long> {
    @Query(value = "select vaccine_id from vaccineCenter where id = ?", nativeQuery = true)
    long findVaccineForVaccineCenter(long vaccineCenterId);

}
