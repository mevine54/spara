package fr.pompey.dev.afpa.controllers;

import fr.pompey.dev.afpa.models.*;

import java.time.LocalDate;
import java.util.List;

public class PharmacyController {
    private Pharmacy pharmacy;

    public PharmacyController(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    // Gestion des achats
    public void addPurchase(Purchase purchase) {
        pharmacy.addPurchase(purchase);  // Enregistrer l'achat dans la pharmacie
    }

    public List<Purchase> getPurchases() {
        return pharmacy.getPurchases();  // Récupérer la liste des achats
    }

    // Gestion des clients
    public void addClient(Client client) {
        pharmacy.addClient(client);
    }

    public List<Client> getClients() {
        return pharmacy.getClients();
    }

    public Client getClientByName(String name) {
        return pharmacy.getClientByName(name);
    }

    // Gestion des médecins
    public void addDoctor(Doctor doctor) {
        pharmacy.addDoctor(doctor);
    }

    public List<Doctor> getDoctors() {
        return pharmacy.getDoctors();
    }

    public Doctor getDoctorByName(String name) {
        return pharmacy.getDoctorByName(name);
    }

    // Gestion des médicaments
    public void addMedicine(Medicine medicine) {
        pharmacy.addMedicine(medicine);
    }

    public List<Medicine> getMedicines() {
        return pharmacy.getMedicines();
    }

    // Gestion des ordonnances
    public void addOrdonnance(Ordonnance ordonnance) {
        pharmacy.addOrdonnance(ordonnance);
    }

    public List<Ordonnance> getOrdonnances() {
        return pharmacy.getOrdonnances();
    }

    public List<Ordonnance> getOrdonnancesByDoctor(String doctorName) {
        return pharmacy.getOrdonnancesByDoctor(doctorName);
    }

    public Medicine getMedicineByName(String doliprane) {
        return pharmacy.getMedicineByName(doliprane);
    }


    // Récupérer toutes les mutuelles
    public List<Mutuelle> getMutuelles() {
        return pharmacy.getMutuelles();
    }

    // Ajouter une mutuelle
    public void addMutuelle(Mutuelle mutuelle) {
        pharmacy.addMutuelle(mutuelle);
    }


    public void updateClient(Client client) {
    pharmacy.updateClient(client);
    }

    public  void deleteClient(Client client) {
        pharmacy.removeClient(client);
    }
}
