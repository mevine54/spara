package fr.pompey.dev.afpa.models;

import java.time.LocalDate;
import fr.pompey.dev.afpa.enums.MedicineCategory;

/**
 * Classe représentant un médicament.
 */
public class Medicine {
    private String name;
    private MedicineCategory category;
    private double price;
    private LocalDate releaseDate;
    private int quantity;

    public Medicine(String name, MedicineCategory category, double price, LocalDate releaseDate, int quantity) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.releaseDate = releaseDate;
        this.quantity = quantity;
    }

    // Getters et Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MedicineCategory getCategory() {
        return category;
    }

    public void setCategory(MedicineCategory category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Calculer le prix total pour une quantité donnée
    public double calculateTotalPrice(int quantity) {
        return this.price * quantity;
    }

    @Override
    public String toString() {
        return name;  // Retourner le nom du médicament
    }
}
