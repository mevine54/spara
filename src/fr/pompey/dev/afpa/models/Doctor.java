package fr.pompey.dev.afpa.models;

import fr.pompey.dev.afpa.exceptions.SaisieException;

import java.time.LocalDate;

/**
 * Classe représentant un médecin traitant.
 */
public class Doctor extends Person {
    private String registrationNumber;

    public Doctor(String firstName, String lastName, String address, String city, String postalCode,
                  String phone, String email, String socialSecurityNumber, LocalDate birthDate, String registrationNumber) throws SaisieException {
        super(firstName, lastName, address, city, postalCode, phone, email, socialSecurityNumber, LocalDate.now());
        if (registrationNumber == null || registrationNumber.isEmpty()) {
            throw new SaisieException("Le numéro d'inscription du médecin ne peut pas être vide !");
        }
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

    @Override
    public void addDoctor(Doctor doctor1) {

    }
}
