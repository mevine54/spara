package mu.pepe.bitor.models;

import mu.pepe.bitor.models.Client;
import mu.pepe.bitor.models.Medicine;

import java.util.Date;

/**
 * Classe représentant un achat de médicament par un client.
 */
public class Purchase {
    private Client client;
    private Medicine medicine;
    private int quantity;
    private double totalPrice;
    private Date date;

    public Purchase(Client client, Medicine medicine, int quantity, double totalPrice) {
        this.client = client;
        this.medicine = medicine;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.date = new Date();  // Enregistre la date d'achat au moment de la création
    }

    // Getters et Setters
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

