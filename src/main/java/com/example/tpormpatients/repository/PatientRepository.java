package com.example.tpormpatients.repository;

import com.example.tpormpatients.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface PatientRepository extends JpaRepository<Patient,Long> {

        Patient findPatientByNom(String nom);

        List<Patient> findPatientsByNomContaining(String  c);

        @Query("update Patient p set p.score = :val where p.nom like :x")
        void updateScoreByNom(@Param("x") String Nom,@Param("val") int val);



}
