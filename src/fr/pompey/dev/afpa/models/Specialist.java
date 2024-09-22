package fr.pompey.dev.afpa.models;

import fr.pompey.dev.afpa.exceptions.SaisieException;

import java.time.LocalDate;

/**
 * Classe représentant un spécialiste.
 */
public class Specialist extends Doctor {
    private String specialty;

    public Specialist(String firstName, String lastName, String address, String city, String postalCode,
                      String phone, String email, String registrationNumber, String specialty) throws SaisieException {
        super(firstName, lastName, address, city, postalCode, phone, email, registrationNumber, LocalDate.now(),registrationNumber);
        if (specialty == null || specialty.isEmpty()) {
            throw new SaisieException("La spécialité ne peut pas être nulle !");
        }
        this.specialty = specialty;
    }

    // Getter et Setter pour la spécialité
    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    @Override
    public String toString() {
        return lastName +" "+ firstName;
//        return "Specialist{" +
//                "email='" + email + '\'' +
//                ", phone='" + phone + '\'' +
//                ", postalCode='" + postalCode + '\'' +
//                ", city='" + city + '\'' +
//                ", address='" + address + '\'' +
//                ", lastName='" + lastName + '\'' +
//                ", firstName='" + firstName + '\'' +
//                ", specialty='" + specialty + '\'' +
//                '}';
    }
}
