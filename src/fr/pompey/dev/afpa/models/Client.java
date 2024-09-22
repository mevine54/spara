package fr.pompey.dev.afpa.models;

import fr.pompey.dev.afpa.exceptions.SaisieException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Représente un client de la pharmacie.
 * Chaque client possède des informations personnelles, un numéro de sécurité sociale,
 * une date de naissance, une mutuelle et un médecin traitant.
 */
public class Client extends Person {
    private String socialSecurityNumber;
    private LocalDate birthDate;
    private Mutuelle mutuelle;
    private Doctor doctor;
    private final List<Specialist> specialists;

    /**
     * Constructeur de la classe Client.
     *
     * @param firstName            Le prénom du client.
     * @param lastName             Le nom du client.
     * @param address              L'adresse du client.
     * @param city                 La ville du client.
     * @param postalCode           Le code postal du client.
     * @param phone                Le numéro de téléphone du client.
     * @param email                L'email du client.
     * @param socialSecurityNumber Le numéro de sécurité sociale (doit comporter 15 chiffres).
     * @param birthDate            La date de naissance du client.
     * @param mutuelle             La mutuelle du client.
     * @param Doctor               Le médecin traitant du client.
     * @throws SaisieException Si les informations saisies sont invalides (ex : numéro de sécurité sociale incorrect).
     */
    public Client(String firstName, String lastName, String address, String city, String postalCode,
                  String phone, String email, String socialSecurityNumber, LocalDate birthDate,
                  Mutuelle mutuelle, Doctor Doctor) throws SaisieException {
        super(firstName, lastName, address, city, postalCode, phone, email, socialSecurityNumber, LocalDate.now());
        if (socialSecurityNumber == null || socialSecurityNumber.length() != 15) {
            throw new SaisieException("Le numéro de sécurité sociale doit comporter 15 chiffres !");
        }
        if (birthDate == null || birthDate.isAfter(LocalDate.now())) {
            throw new SaisieException("La date de naissance ne peut pas être ultérieure à la date d' aujourd' hui !");
        }
        if (mutuelle == null) {
            throw new SaisieException("La mutuelle ne peut pas être nulle !");
        }
//        if (doctor == null) {
//            throw new SaisieException("Le médecin traitant ne peut pas être nul !");
//        }

        this.socialSecurityNumber = socialSecurityNumber;
        this.birthDate = birthDate;
        this.mutuelle = mutuelle;
        this.doctor = doctor;
        this.specialists = new ArrayList<>();
    }

    /**
     * Retourne le numéro de sécurité sociale du client.
     *
     * @return Le numéro de sécurité sociale du client.
     */
    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    /**
     * Modifie le numéro de sécurité sociale du client.
     *
     * @param socialSecurityNumber Le nouveau numéro de sécurité sociale.
     */
    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    /**
     * Retourne la date de naissance du client.
     *
     * @return La date de naissance du client.
     */
    public LocalDate getBirthDate() {
        return LocalDate.now();
    }

    /**
     * Modifie la date de naissance du client.
     *
     * @param birthDate La nouvelle date de naissance.
     */
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = LocalDate.parse(String.valueOf(birthDate));
    }

    /**
     * Ajoute un médecin traitant pour le client (méthode non utilisée ici).
     *
     * @param doctor1 Le médecin à ajouter (méthode à implémenter selon le contexte).
     */
    @Override
    public void addDoctor(Doctor doctor1) {
        // Non implémenté
    }

    /**
     * Retourne la mutuelle du client.
     *
     * @return La mutuelle du client.
     */
    public Mutuelle getMutuelle() {
        return mutuelle;
    }

    /**
     * Modifie la mutuelle du client.
     *
     * @param mutuelle La nouvelle mutuelle.
     */
    public void setMutuelle(Mutuelle mutuelle) {
        this.mutuelle = mutuelle;
    }

    /**
     * Retourne le médecin traitant du client.
     *
     * @return Le médecin traitant.
     */
    public Doctor getDoctor() {
        return doctor;
    }

    /**
     * Modifie le médecin traitant du client.
     *
     * @param generalDoctor Le nouveau médecin traitant.
     */
    public void setDoctor(Doctor generalDoctor) {
        this.doctor = generalDoctor;
    }

    /**
     * Retourne la liste des spécialistes associés au client.
     *
     * @return La liste des spécialistes.
     */
    public List<Specialist> getSpecialists() {
        return specialists;
    }

    /**
     * Ajoute un spécialiste à la liste des spécialistes du client.
     *
     * @param specialist Le spécialiste à ajouter.
     */
    public void addSpecialist(Specialist specialist) {
        specialists.add(specialist);
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères du client (nom + prénom).
     *
     * @return Le nom complet du client.
     */
    @Override
    public String toString() {
        return lastName + " " + firstName;
    }
}
