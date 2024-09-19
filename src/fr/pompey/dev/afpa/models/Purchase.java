package fr.pompey.dev.afpa.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Classe représentant un achat de médicament par un client.
 */
public class Purchase {
    private Client client;
//    private Medicine medicine;
//    private int quantity;
    private double totalPrice;
    private LocalDate date;


    private Ordonnance ordonnance;
    private List<Medicine> medicines;

    public Purchase(Ordonnance ordonnance, LocalDate date) {
        this.ordonnance = ordonnance;
        this.medicines = ordonnance.getMedicines();
        this.date = date;
        setTotalPrice();
    }

    public Purchase(Ordonnance ordonnance) {
        this.ordonnance = ordonnance;
        this.medicines = ordonnance.getMedicines();
        this.date = LocalDate.now();
        setTotalPrice();
    }

    public Purchase(List<Medicine> medicines, LocalDate date) {
        this.medicines = medicines;
        this.date = date;
        setTotalPrice();

    }

    public Purchase(List<Medicine> medicines) {
        this.medicines = medicines;
        this.date = LocalDate.now();
        setTotalPrice();
    }

    // Getters et Setters

    public Ordonnance getOrdonnance() {
        return ordonnance;
    }

    public List<Medicine> getMedicines() {
        return medicines;
    }


    public double getTotalPrice() {
        return this.totalPrice;
    }

    private void setTotalPrice() {
        double totalPrice = 0;
        for (Medicine medicine : medicines) {
            totalPrice += medicine.calculateTotalPrice();
        }
        this.totalPrice = totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "client=" + client +
                ", date=" + date +
                '}';
    }
}

