package fr.pompey.dev.afpa.models;

/**
 * Classe représentant un spécialiste.
 */
public class Specialist extends Doctor {
    private String specialty;

    public Specialist(String firstName, String lastName, String address, String city, String postalCode,
                      String phone, String email, String registrationNumber, String specialty) {
        super(firstName, lastName, address, city, postalCode, phone, email, registrationNumber);
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
        return "Specialist{" +
                "email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", specialty='" + specialty + '\'' +
                '}';
    }
}
