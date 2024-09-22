package fr.pompey.dev.afpa.models;

import fr.pompey.dev.afpa.exceptions.SaisieException;

import java.time.LocalDate;

/**
 * Représente un médecin traitant dans le système de gestion de la pharmacie.
 */
public class Doctor extends Person {
    private String registrationNumber;

    /**
     * Constructeur de la classe Doctor.
     *
     * @param firstName          Le prénom du médecin.
     * @param lastName           Le nom du médecin.
     * @param address            L'adresse du médecin.
     * @param city               La ville du médecin.
     * @param postalCode         Le code postal du médecin.
     * @param phone              Le numéro de téléphone du médecin.
     * @param email              L'email du médecin.
     * @param socialSecurityNumber Le numéro de sécurité sociale du médecin.
     * @param birthDate          La date de naissance du médecin.
     * @param registrationNumber Le numéro d'agrément du médecin (obligatoire).
     * @throws SaisieException Si le numéro d'inscription est vide.
     */
    public Doctor(String firstName, String lastName, String address, String city, String postalCode,
                  String phone, String email, String socialSecurityNumber, LocalDate birthDate, String registrationNumber) throws SaisieException {
        super(firstName, lastName, address, city, postalCode, phone, email, socialSecurityNumber, LocalDate.now());
        if (registrationNumber == null || registrationNumber.isEmpty()) {
            throw new SaisieException("Le numéro d'inscription du médecin ne peut pas être vide !");
        }
        this.registrationNumber = registrationNumber;
    }

    // Getters et Setters

    /**
     * Retourne le numéro d'agrément du médecin.
     *
     * @return Le numéro d'agrément.
     */

    /**
     * Modifie le numéro d'agrément du médecin.
     *
     * @param registrationNumber Le nouveau numéro d'agrément.
     */
    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères du médecin (nom + prénom).
     *
     * @return Le nom complet du médecin.
     */
    @Override
    public String toString() {
        return lastName + ' ' + firstName;
    }

    /**
     * Méthode pour ajouter un médecin traitant (non implémentée ici).
     *
     * @param doctor1 Le médecin à ajouter (méthode à implémenter selon le contexte).
     */
    @Override
    public void addDoctor(Doctor doctor1) {
    }

    public String getRegistrationNumber() {
        return registrationNumber;

    }
}