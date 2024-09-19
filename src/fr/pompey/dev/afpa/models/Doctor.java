package fr.pompey.dev.afpa.models;

import java.time.LocalDate;

/**
 * Classe représentant un médecin traitant.
 */
public class Doctor extends Person {
    private String registrationNumber;

    public Doctor(String firstName, String lastName, String address, String city, String postalCode,
                  String phone, String email, String socialSecurityNumber, LocalDate birthDate, String registrationNumber) {
        super(firstName, lastName, address, city, postalCode, phone, email, socialSecurityNumber, LocalDate.now());
        this.registrationNumber = registrationNumber;
    }

    // Getters et Setters

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    @Override
    public String toString() {
        return lastName + ' ' + firstName;
    }
}
