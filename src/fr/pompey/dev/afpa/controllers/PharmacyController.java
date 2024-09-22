package fr.pompey.dev.afpa.controllers;

import fr.pompey.dev.afpa.exceptions.SaisieException;
import fr.pompey.dev.afpa.exceptions.SystemeException;
import fr.pompey.dev.afpa.models.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Contrôleur de la pharmacie, gère les interactions entre les modèles (Client, Doctor, Medicine, etc.)
 * et les vues (interface utilisateur).
 */
public class PharmacyController {
    private Pharmacy pharmacy;

    /**
     * Constructeur de la classe PharmacyController.
     *
     * @param pharmacy L'instance de la pharmacie à contrôler.
     */
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

    /**
     * Retourne la liste de tous les achats effectués dans la pharmacie.
     *
     * @return La liste des achats.
     */
    public List<Purchase> getPurchases() {
        return pharmacy.getPurchases();
    }

    /**
     * Ajoute un client à la pharmacie.
     *
     * @param client Le client à ajouter.
     * @throws SaisieException Si les informations du client sont invalides.
     */
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

    /**
     * Retourne la liste de tous les clients de la pharmacie.
     *
     * @return La liste des clients.
     */
    public List<Client> getClients() {
        return pharmacy.getClients();
    }

    /**
     * Retourne un client basé sur son nom.
     *
     * @param name Le nom du client.
     * @return Le client correspondant au nom ou null si non trouvé.
     */
    public Client getClientByName(String name) {
        return pharmacy.getClientByName(name);
    }

    /**
     * Ajoute un docteur à la pharmacie.
     *
     * @param doctor Le docteur à ajouter.
     * @throws SaisieException Si les informations du docteur sont invalides.
     */
    public void addDoctor(Doctor doctor) throws SaisieException {
        if (doctor == null) {
            throw new SaisieException("Le docteur ne peut pas être nul.");
        }
        pharmacy.addDoctor(doctor);
    }

    /**
     * Retourne la liste de tous les docteurs de la pharmacie.
     *
     * @return La liste des docteurs.
     */
    public List<Doctor> getDoctors() {
        return pharmacy.getDoctors();
    }

    /**
     * Retourne un docteur basé sur son nom.
     *
     * @param name Le nom du docteur.
     * @return Le docteur correspondant au nom ou null si non trouvé.
     */
    public Doctor getDoctorByName(String name) {
        return pharmacy.getDoctorByName(name);
    }

    /**
     * Ajoute un médicament à la pharmacie.
     *
     * @param medicine Le médicament à ajouter.
     * @throws SaisieException Si les informations du médicament sont invalides.
     */
    public void addMedicine(Medicine medicine) throws SaisieException {
        if (medicine == null) {
            throw new SaisieException("Le médicament ne peut pas être nul.");
        }
        pharmacy.addMedicine(medicine);
    }

    /**
     * Retourne la liste de tous les médicaments disponibles dans la pharmacie.
     *
     * @return La liste des médicaments.
     */
    public List<Medicine> getMedicines() {
        return pharmacy.getMedicines();
    }

    /**
     * Ajoute une ordonnance à la pharmacie.
     *
     * @param ordonnance L'ordonnance à ajouter.
     * @throws SaisieException Si les informations de l'ordonnance sont invalides.
     */
    public void addOrdonnance(Ordonnance ordonnance) throws SaisieException {
        if (ordonnance == null) {
            throw new SaisieException("L'ordonnance ne peut pas être nulle.");
        }
        pharmacy.addOrdonnance(ordonnance);
    }

    /**
     * Retourne la liste de toutes les ordonnances dans la pharmacie.
     *
     * @return La liste des ordonnances.
     */
    public List<Ordonnance> getOrdonnances() {
        return pharmacy.getOrdonnances();
    }

    /**
     * Retourne les ordonnances prescrites par un docteur en particulier.
     *
     * @param doctorName Le nom du docteur.
     * @return La liste des ordonnances du docteur spécifié.
     */
    public List<Ordonnance> getOrdonnancesByDoctor(String doctorName) {
        return pharmacy.getOrdonnancesByDoctor(doctorName);
    }

    /**
     * Retourne un médicament basé sur son nom.
     *
     * @param name Le nom du médicament.
     * @return Le médicament correspondant au nom ou null si non trouvé.
     */
    public Medicine getMedicineByName(String name) {
        return pharmacy.getMedicineByName(name);
    }


    // Récupérer toutes les mutuelles
    public List<Mutuelle> getMutuelles() {
        return pharmacy.getMutuelles();
    }

    // Ajouter une mutuelle
    public void addMutuelle(Mutuelle mutuelle) {
        pharmacy.addMutuelle(mutuelle);
    }

    /**
     * Met à jour les informations d'un client existant.
     *
     * @param updatedClient Le client avec les nouvelles informations.
     * @throws SaisieException Si les informations du client sont invalides.
     */
    public void updateClient(Client updatedClient) throws SaisieException {
        if (updatedClient == null) {
            throw new SaisieException("Le client ne peut pas être nul.");
        }
        pharmacy.updateClient(updatedClient);
    }

    public void updatePurchase(Purchase purchase, List<Medicine> newMedicines, LocalDate newDate) throws SaisieException {
        if (purchase == null) {
            throw new SaisieException("L'achat à modifier est introuvable !");
        }
        purchase.setMedicines(newMedicines); // Vérification des nouveaux médicaments
        purchase.setDate(newDate); // Vérification de la nouvelle date
    }

    public void deleteClient(Client client) {
        pharmacy.removeClient(client);
    }

    /**
     * Supprime un achat de la liste des achats.
     *
     * @param selectedPurchase L'achat à supprimer.
     * @throws SystemeException Si l'achat n'existe pas dans la base.
     */
    public void removePurchase(Purchase selectedPurchase) throws SystemeException {
        if (selectedPurchase == null) {
            throw new SystemeException("L'achat sélectionné ne peut pas être nul.");
        }
        pharmacy.removePurchase(selectedPurchase);
    }

    /**
     * Supprime un client de la pharmacie.
     *
     * @param client Le client à supprimer.
     * @throws SaisieException Si le client à supprimer est introuvable.
     */
    public void removeClient(Client client) throws SaisieException {
        if (client == null) {
            throw new SaisieException("Le client ne peut pas être nul.");
        }
        pharmacy.removeClient(client);
    }
}
