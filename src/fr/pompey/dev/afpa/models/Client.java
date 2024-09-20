package fr.pompey.dev.afpa.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Client extends Person {
    private String socialSecurityNumber;
    private LocalDate birthDate;
    private Mutuelle mutuelle;
    private Doctor doctor;
    private final List<Specialist> specialists;

    public Client(String firstName, String lastName, String address, String city, String postalCode,
                  String phone, String email, String socialSecurityNumber, LocalDate birthDate,
                  Mutuelle mutuelle, Doctor Doctor) {
        super(firstName, lastName, address, city, postalCode, phone, email, socialSecurityNumber, LocalDate.now());
        this.socialSecurityNumber = socialSecurityNumber;
        this.birthDate = birthDate;
        this.mutuelle = mutuelle;
        this.doctor = doctor;
        this.specialists = new ArrayList<>();
    }

    // Getters et Setters
    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public LocalDate getBirthDate() {
        return  LocalDate.now();
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = LocalDate.parse(String.valueOf(birthDate));
    }

    public Mutuelle getMutuelle() {
        return mutuelle;
    }

    public void setMutuelle(Mutuelle mutuelle) {
        this.mutuelle = mutuelle;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor generalDoctor) {
        this.doctor = generalDoctor;
    }

    public List<Specialist> getSpecialists() {
        return specialists;
    }

    public void addSpecialist(Specialist specialist) {
        specialists.add(specialist);
    }

    @Override
    public String toString() {
        return  lastName + " " + firstName;
    }
}
