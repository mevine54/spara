package fr.pompey.dev.afpa.models;

import fr.pompey.dev.afpa.exceptions.SaisieException;

import java.time.LocalDate;

/**
 * Représente un médecin spécialiste qui hérite des attributs de la classe {@link Doctor}.
 * Un spécialiste est un médecin ayant une spécialité médicale spécifique.
 */
public class Specialist extends Doctor {
    private String specialty;

    /**
     * Constructeur de la classe Specialist.
     *
     * @param firstName         Le prénom du spécialiste.
     * @param lastName          Le nom du spécialiste.
     * @param address           L'adresse du spécialiste.
     * @param city              La ville où exerce le spécialiste.
     * @param postalCode        Le code postal du spécialiste.
     * @param phone             Le numéro de téléphone du spécialiste.
     * @param email             L'email du spécialiste.
     * @param registrationNumber Le numéro d'inscription du spécialiste.
     * @param specialty         La spécialité médicale du spécialiste (ex: Cardiologue, Neurologue, etc.).
     * @throws SaisieException Si la spécialité est nulle ou vide.
     */
    public Specialist(String firstName, String lastName, String address, String city, String postalCode,
                      String phone, String email, String registrationNumber, String specialty) throws SaisieException {
        super(firstName, lastName, address, city, postalCode, phone, email, registrationNumber, LocalDate.now(),registrationNumber);
        if (specialty == null || specialty.isEmpty()) {
            throw new SaisieException("La spécialité ne peut pas être nulle !");
        }
        this.specialty = specialty;
    }

    // Getter et Setter pour la spécialité
    /**
     * Retourne la spécialité médicale du spécialiste.
     *
     * @return La spécialité médicale.
     */
    public String getSpecialty() {
        return specialty;
    }

    /**
     * Définit la spécialité médicale du spécialiste.
     *
     * @param specialty La nouvelle spécialité médicale à attribuer.
     */
    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    /**
     * Retourne une représentation sous forme de chaîne du nom complet du spécialiste.
     * Cette méthode affiche le nom et le prénom du spécialiste.
     *
     * @return Le nom complet du spécialiste.
     */
    @Override
    public String toString() {
        return lastName + " " + firstName;
    }
}
