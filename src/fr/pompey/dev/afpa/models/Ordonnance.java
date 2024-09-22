package fr.pompey.dev.afpa.models;

import java.time.LocalDate;
import java.util.List;

/**
 * Représente une ordonnance délivrée par un médecin pour un client.
 * Une ordonnance contient une liste de médicaments prescrits.
 */
public class Ordonnance {
    private LocalDate date;
    private Doctor doctor;
    private Client client;
    private List<Medicine> medicines;
    private Specialist specialist;

    /**
     * Constructeur de la classe Ordonnance.
     *
     * @param date        La date de l'ordonnance.
     * @param doctor      Le médecin ayant prescrit l'ordonnance.
     * @param client      Le client pour qui l'ordonnance est prescrite.
     * @param medicines   La liste des médicaments prescrits.
     * @param specialist  Le spécialiste associé à l'ordonnance (facultatif).
     */
    public Ordonnance(LocalDate date, Doctor doctor, Client client, List<Medicine> medicines, Specialist specialist) {
        this.date = date;
        this.doctor = doctor;
        this.client = client;
        this.medicines = medicines;
        this.specialist = specialist;
    }

    // Getters et Setters
    /**
     * Retourne la date de l'ordonnance.
     *
     * @return La date de l'ordonnance.
     */
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Retourne le médecin ayant prescrit l'ordonnance.
     *
     * @return Le médecin.
     */
    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    /**
     * Retourne le client associé à l'ordonnance.
     *
     * @return Le client.
     */
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    /**
     * Retourne la liste des médicaments prescrits dans l'ordonnance.
     *
     * @return La liste des médicaments.
     */
    public List<Medicine> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<Medicine> medicines) {
        this.medicines = medicines;
    }

    /**
     * Retourne le spécialiste associé à l'ordonnance, le cas échéant.
     *
     * @return Le spécialiste ou null si aucun spécialiste n'est associé.
     */
    public Specialist getSpecialist() {
        return specialist;
    }

    public void setSpecialist(Specialist specialist) {
        this.specialist = specialist;
    }

    @Override
    public String toString() {
        return "Ordonnance{" +
                "date=" + date +
                ", doctor=" + doctor +
                ", client=" + client +
                ", medicines=" + medicines +
                ", specialist=" + specialist +
                '}';
    }
}
