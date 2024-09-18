package fr.pompey.dev.afpa.models;

import java.time.LocalDate;
import java.util.List;

/**
 * Classe représentant une ordonnance.
 */
public class Ordonnance {
    private LocalDate date;
    private Doctor doctor;
    private Client client;
    private List<Medicine> medicines;
    private Specialist specialist;

    public Ordonnance(LocalDate date, Doctor doctor, Client client, List<Medicine> medicines, Specialist specialist) {
        this.date = date;
        this.doctor = doctor;
        this.client = client;
        this.medicines = medicines;
        this.specialist = specialist;
    }

    // Getters et Setters
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Medicine> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<Medicine> medicines) {
        this.medicines = medicines;
    }

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
