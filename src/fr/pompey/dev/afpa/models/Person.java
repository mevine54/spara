package fr.pompey.dev.afpa.models;

import java.time.LocalDate;

/**
 * Classe abstraite représentant une personne générique.
 * Cette classe est héritée par les classes spécifiques telles que Client et Doctor.
 */
public abstract class Person {
    protected String firstName;
    protected String lastName;
    protected String address;
    protected String city;
    protected String postalCode;
    protected String phone;
    protected String email;
    protected String socialSecurityNumber;
    protected LocalDate birthDate;

    /**
     * Constructeur de la classe Person.
     *
     * @param firstName            Le prénom de la personne.
     * @param lastName             Le nom de la personne.
     * @param address              L'adresse de la personne.
     * @param city                 La ville de résidence.
     * @param postalCode           Le code postal.
     * @param phone                Le numéro de téléphone.
     * @param email                L'email de la personne.
     * @param socialSecurityNumber Le numéro de sécurité sociale.
     * @param birthDate            La date de naissance.
     */
    public Person(String firstName, String lastName, String address, String city, String postalCode,
                  String phone, String email, String socialSecurityNumber, LocalDate birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
        this.phone = phone;
        this.email = email;
        this.socialSecurityNumber = socialSecurityNumber;
        this.birthDate = birthDate;
    }

    // Getters et Setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public abstract void addDoctor(Doctor doctor1);

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", socialSecurityNumber='" + socialSecurityNumber + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }

}
