package fr.pompey.dev.afpa.models;

import java.util.ArrayList;
import java.util.List;

public class Client extends Person {
    private String socialSecurityNumber;
    private String birthDate;
    private Mutuelle mutuelle;
    private Doctor generalDoctor;
    private final List<Specialist> specialists;

    public Client(String firstName, String lastName, String address, String city, String postalCode,
                  String phone, String email, String socialSecurityNumber, String birthDate, Mutuelle mutuelle, Doctor generalDoctor) {
        super(firstName, lastName, address, city, postalCode, phone, email);
        this.socialSecurityNumber = socialSecurityNumber;
        this.birthDate = birthDate;
        this.mutuelle = mutuelle;
        this.generalDoctor = generalDoctor;
        this.specialists = new ArrayList<>();
    }

    // Getters et Setters
    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public Mutuelle getMutuelle() {
        return mutuelle;
    }

    public void setMutuelle(Mutuelle mutuelle) {
        this.mutuelle = mutuelle;
    }

    public Doctor getGeneralDoctor() {
        return generalDoctor;
    }

    public void setGeneralDoctor(Doctor generalDoctor) {
        this.generalDoctor = generalDoctor;
    }

    public List<Specialist> getSpecialists() {
        return specialists;
    }

    public void addSpecialist(Specialist specialist) {
        specialists.add(specialist);
    }

    @Override
    public String toString() {
        return  lastName + " " + firstName;  // Retourner le nom complet
    }
}
