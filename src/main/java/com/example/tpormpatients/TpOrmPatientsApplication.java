package com.example.tpormpatients;

import com.example.tpormpatients.entities.Patient;
import com.example.tpormpatients.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class TpOrmPatientsApplication implements CommandLineRunner {
    @Autowired
    private PatientRepository patientRepository;
    public static void main(String[] args) {

        SpringApplication.run(TpOrmPatientsApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        patientRepository.save(new Patient(null,"sara",new Date(1992-1900,10,20),false,1));
        patientRepository.save(new Patient(null,"ilham",new Date(2003-1900, 9,5),true,5));
        patientRepository.save(new Patient(null,"youssef",new Date(1995-1900,3,13),false,7));
        patientRepository.save(new Patient(null,"salma",new Date(2003-1900,02,19),true,2));

        System.out.println("\n------Liste complete des patients------\n----------------------------------------\n");
        List<Patient> patients = patientRepository.findAll();
        patients.forEach(p -> {
            System.out.println(p.toString());
        });

        System.out.println("\n------Le patient : ------\n-------------------------\n");
        Patient patient = patientRepository.findPatientByNom("youssef");
        System.out.print(patient.getId()+"\t\t");
        System.out.print(patient.getNom()+"\t\t");
        System.out.print(patient.getDateNaissance()+"\t\t");

        System.out.println("\n\n------Liste des patients avec le nom contient ------\n----------------------------------------------------\n");

        List<Patient> patientList = patientRepository.findPatientsByNomContaining("sa");
        patientList.forEach(p -> {
            System.out.println(p.toString());
        });

        Optional<Patient> patientToUpdate = patientRepository.findById(Long.valueOf(9));
        patientToUpdate.ifPresent(p-> {
            p.setScore(p.getScore()+1);
            patientRepository.save(p);
            System.out.println("\nModification bien effectuée");
            System.out.println(p.toString()+"\n");
        }  );


        patientRepository.deleteById(Long.valueOf(3));
        System.out.println("\n------Liste Finale des patients------\n----------------------------------------\n");
        List<Patient> patientF = patientRepository.findAll();
        patientF.forEach(p -> {
            System.out.println(p.toString());
        });




    }
}
