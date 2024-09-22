package fr.pompey.dev.afpa.controllers;

import fr.pompey.dev.afpa.exceptions.SaisieException;
import fr.pompey.dev.afpa.exceptions.SystemeException;
import fr.pompey.dev.afpa.models.*;

import java.time.LocalDate;
import java.util.List;

public class PharmacyController {
    private Pharmacy pharmacy;

    public PharmacyController(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    // Gestion des achats
    public void addPurchase(Purchase purchase) throws SystemeException {
        if (purchase.getMedicines() == null || purchase.getMedicines().isEmpty()) {
            throw new SystemeException("Aucun médicament n'a été sélectionné pour cet achat !");
        }
        if (purchase.getTotalPrice() <= 0) {
            throw new SystemeException("Le prix total de l'achat ne peut pas être nul ou négatif !");
        }
        pharmacy.addPurchase(purchase);  // Enregistrer l'achat dans la pharmacie
    }

    public List<Purchase> getPurchases() {
        return pharmacy.getPurchases();  // Récupérer la liste des achats
    }

    // Gestion des clients
    public void addClient(Client client) throws SaisieException {
        if (client == null || client.getFirstName().isEmpty() || client.getLastName().isEmpty()) {
            throw new SaisieException("Le nom ou le prénom est invalide !");
        }
        if (!client.getEmail().contains("@")) {
            throw new SaisieException("L' email du client est invalide !");
        }
        pharmacy.addClient(client);
    }

    public List<Client> getClients() {
        return pharmacy.getClients();
    }

    public Client getClientByName(String name) {
        return pharmacy.getClientByName(name);
    }

    // Gestion des médecins
    public void addDoctor(Doctor doctor) throws SaisieException {
        if (doctor == null) {
            throw new SaisieException("Le médecin ne peut pas être nul.");
        }
        pharmacy.addDoctor(doctor);
    }

    public List<Doctor> getDoctors() {
        return pharmacy.getDoctors();
    }

    public Doctor getDoctorByName(String name) {
        return pharmacy.getDoctorByName(name);
    }

    // Gestion des médicaments
    public void addMedicine(Medicine medicine) throws SaisieException {
        if (medicine == null) {
            throw new SaisieException("Le médicament ne peut pas être nul.");
        }
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

    public void updatePurchase(Purchase purchase, List<Medicine> newMedicines, LocalDate newDate) throws SaisieException {
        if (purchase == null) {
            throw new SaisieException("L'achat à modifier est introuvable !");
        }
        purchase.setMedicines(newMedicines); // Vérification des nouveaux médicaments
        purchase.setDate(newDate); // Vérification de la nouvelle date
    }

    public  void deleteClient(Client client) {
        pharmacy.removeClient(client);
    }

    public void removePurchase(Purchase selectedPurchase) {
        pharmacy.removePurchase(selectedPurchase);
    }

    public void removeClient(Client selectedClient) {
        pharmacy.removeClient(selectedClient);
    }
}
