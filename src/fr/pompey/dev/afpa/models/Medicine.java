package fr.pompey.dev.afpa.models;

import java.time.LocalDate;
import fr.pompey.dev.afpa.enums.MedicineCategory;
import fr.pompey.dev.afpa.exceptions.SaisieException;

/**
 * Classe représentant un médicament.
 */
public class Medicine {
    private String name;
    private MedicineCategory category;
    private double price;
    private LocalDate releaseDate;
    private int quantity;

    public Medicine(String name, MedicineCategory category, double price, LocalDate releaseDate, int quantity) throws SaisieException {
        if (name == null || name.trim().isEmpty()) {
            throw new SaisieException("Le nom du médicament ne peut pas être vide !");
        }
        if (category == null) {
            throw new SaisieException("La catégorie du médicament ne peut pas être nulle !");
        }
        if (price < 0) {
            throw new SaisieException("Le prix ne peut pas être négatif !");
        }
        if (releaseDate == null || releaseDate.isAfter(LocalDate.now())) {
            throw new SaisieException("La date de sortie ne peut pas être dans le futur !");
        }
        if (quantity < 0) {
            throw new SaisieException("La quantité ne peut pas être négative !");
        }
        this.name = name;
        this.category = category;
        this.price = price;
        this.releaseDate = releaseDate;
        this.quantity = quantity;
    }

    // Calculer le prix total pour une quantité donnée
    public double calculateTotalPrice() {
        return price * quantity;
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

    @Override
    public Medicine clone() {
        try {
            return new Medicine(this.name, this.category, this.price, this.releaseDate, this.quantity);
        } catch (SaisieException e) {
            throw new RuntimeException(e);
        }
    }

    // Calculer le prix total pour une quantité donnée


    @Override
    public String toString() {
        return name;  // Retourne le nom du médicament
    }
}
