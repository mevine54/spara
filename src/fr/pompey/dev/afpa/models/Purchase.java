package fr.pompey.dev.afpa.models;

import fr.pompey.dev.afpa.exceptions.SaisieException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Classe représentant un achat de médicament par un client.
 */
public class Purchase {
    private Client client;
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

    /**
     * Constructeur de la classe Purchase.
     *
     * @param medicines La liste de médicaments achetés.
     * @param date      La date de l'achat.
     * @throws SaisieException Si la liste de médicaments est vide ou si la date est invalide.
     */
    public Purchase(List<Medicine> medicines, LocalDate date) throws SaisieException {
        if (medicines == null || medicines.isEmpty()) {
            throw new SaisieException("La liste de médicaments ne peut pas être vide !");
        }
        if (date == null) {
            throw new SaisieException("La date de l'achat ne peut pas être nulle !");
        }
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
        return new ArrayList<>(medicines); // Retourne une copie défensive
    }

    // Setter pour les médicaments avec vérification
    public void setMedicines(List<Medicine> medicines) throws SaisieException {
        if (medicines == null || medicines.isEmpty()) {
            throw new SaisieException("La liste de médicaments ne peut pas être vide.");
        }
        this.medicines = medicines;
        setTotalPrice(); // Recalculer le total quand les médicaments sont modifiés
    }

    /**
     * Calcule et définit le prix total de l'achat.
     */
    private void setTotalPrice() {
        this.totalPrice = medicines.stream().mapToDouble(Medicine::calculateTotalPrice).sum();
    }

    public double getTotalPrice() {
        return this.totalPrice;
    }

    public LocalDate getDate() {
        return date;
    }

    // Setter pour la date avec vérification
    public void setDate(LocalDate date) throws SaisieException {
        if (date == null) {
            throw new SaisieException("La date de l'achat ne peut pas être nulle !");
        }
        this.date = date;
    }



    @Override
    public String toString() {
        StringBuilder details = new StringBuilder("Achat du : " + date.toString() + "\n");
        for (Medicine medicine : medicines) {
            details.append("Médicament : ").append(medicine.getName())
                    .append(", Quantité : ").append(medicine.getQuantity())
                    .append(", Prix : ").append(medicine.calculateTotalPrice())
                    .append(" €\n");
        }
        details.append("Prix total : ").append(totalPrice).append(" €");
        return details.toString();
    }
}

