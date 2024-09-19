package fr.pompey.dev.afpa.views;

import fr.pompey.dev.afpa.controllers.PharmacyController;
import fr.pompey.dev.afpa.models.*;


import java.time.LocalDate;
import java.util.Scanner;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Interface console pour l'utilisateur.
 */
public class ConsoleView {
    private PharmacyController controller;
    private Scanner scanner;

    public ConsoleView(PharmacyController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    // Méthode pour ajouter un client
    private void addClient() {
        System.out.println("Ajout d'un nouveau client");

        // Saisie des informations de base du client
        System.out.println("Prénom :");
        String firstName = scanner.nextLine();

        System.out.println("Nom :");
        String lastName = scanner.nextLine();

        System.out.println("Adresse :");
        String address = scanner.nextLine();

        System.out.println("Ville :");
        String city = scanner.nextLine();

        System.out.println("Code postal :");
        String postalCode = scanner.nextLine();

        System.out.println("Téléphone :");
        String phone = scanner.nextLine();

        System.out.println("Email :");
        String email = scanner.nextLine();

        System.out.println("Numéro de sécurité sociale :");
        String socialSecurityNumber = scanner.nextLine();

        System.out.println("Date de naissance (JJ/MM/AAAA) :");
        String birthDate = scanner.nextLine();

        // Appel de la méthode selectMutuelle pour sélectionner la mutuelle
        Mutuelle selectedMutuelle = selectMutuelle();
        if (selectedMutuelle == null) {
            System.out.println("Aucune mutuelle sélectionnée. Abandon de la création du client.");
            return;
        }

        // Demander le nom du médecin traitant
        System.out.println("Nom du médecin traitant :");
        String doctorName = scanner.nextLine();
        Doctor doctor = controller.getDoctorByName(doctorName);

        // Vérifier si le médecin existe
        if (doctor == null) {
            System.out.println("Médecin non trouvé. Création du client annulée.");
            return;
        }

        // Création du nouveau client avec les informations fournies
        Client newClient = new Client(firstName, lastName, address, city, postalCode, phone, email, socialSecurityNumber, LocalDate.now(), selectedMutuelle, doctor);

        // Ajout du client via le contrôleur
        controller.addClient(newClient);

        System.out.println("Client ajouté avec succès !");
    }

    // Méthode de sélection d'une mutuelle
    private Mutuelle selectMutuelle() {
        List<Mutuelle> availableMutuelles = controller.getMutuelles();
        if (availableMutuelles.isEmpty()) {
            System.out.println("Aucune mutuelle disponible.");
            return null;
        }

        System.out.println("Veuillez sélectionner une mutuelle parmi la liste suivante :");
        for (int i = 0; i < availableMutuelles.size(); i++) {
            Mutuelle mutuelle = availableMutuelles.get(i);
            System.out.println((i + 1) + ". " + mutuelle.getName() + " - Taux de remboursement : " + mutuelle.getReimbursementRate() + "%");
        }

        int mutuelleIndex = scanner.nextInt() - 1;
        scanner.nextLine();  // Consommer la ligne restante

        if (mutuelleIndex >= 0 && mutuelleIndex < availableMutuelles.size()) {
            return availableMutuelles.get(mutuelleIndex);
        } else {
            System.out.println("Sélection non valide.");
            return null;
        }
    }


    public void start() {
        System.out.println("=== Bienvenue dans le système de gestion de la pharmacie Sparadrap ===");
        while (true) {
            System.out.println("1. Effectuer un achat");
            System.out.println("2. Consulter l'historique des achats");
            System.out.println("3. Consulter un client");
            System.out.println("4. Consulter un médecin");
            System.out.println("5. Quitter");

            int choix = scanner.nextInt();
            scanner.nextLine();  // Consommer la ligne restante après l'input

            switch (choix) {
                case 1:
                    handlePurchase();
                    break;
                case 2:
                    displayPurchases();
                    break;
                case 3:
                    displayClient();
                    break;
                case 4:
                    displayDoctor();
                    break;
                case 5:
                    System.out.println("Au revoir !");
                    return;
                default:
                    System.out.println("Choix invalide, veuillez réessayer.");
            }
        }
    }

    private void handlePurchase() {
        System.out.println("Effectuer un achat par :");
        System.out.println("1. Achat direct");
        System.out.println("2. Achat via ordonnance");

        int choix = scanner.nextInt();
        scanner.nextLine();  // Consommer la ligne restante

        if (choix == 1) {
            purchaseDirect();
        } else if (choix == 2) {
            purchaseViaPrescription();
        } else {
            System.out.println("Choix invalide.");
        }
    }

    private void purchaseDirect() {
        System.out.println("Nom du médicament :");
        String medName = scanner.nextLine();
        System.out.println("Quantité :");
        int quantity = scanner.nextInt();
        scanner.nextLine();  // Consommer la ligne restante

        Medicine medicine = controller.getMedicineByName(medName);
        if (medicine != null) {
            double unitPrice = medicine.getPrice(); // Récupérer le prix unitaire
            System.out.println("Prix unitaire du médicament : " + unitPrice + " €");

            double totalPrice = medicine.calculateTotalPrice();
            System.out.println("Achat réussi ! Prix total : " + totalPrice + " €");
        } else {
            System.out.println("Médicament non trouvé.");
        }
    }

    private void purchaseViaPrescription() {
        // Afficher la liste des clients disponibles
        List<Client> availableClients = controller.getClients();
        if (availableClients.isEmpty()) {
            System.out.println("Aucun client disponible.");
            return;
        }

        System.out.println("Voici les clients disponibles :");
        for (int i = 0; i < availableClients.size(); i++) {
            Client client = availableClients.get(i);
            System.out.println((i + 1) + ". " + client.getFirstName() + " " + client.getLastName());
        }

        System.out.println("Sélectionnez un client (1 à " + availableClients.size() + "):");
        int clientIndex = scanner.nextInt() - 1;
        scanner.nextLine();  // Consommer la ligne restante

        if (clientIndex < 0 || clientIndex >= availableClients.size()) {
            System.out.println("Client non valide.");
            return;
        }

        Client client = availableClients.get(clientIndex);

        // Vérifier si le client existe
        if (client == null) {
            System.out.println("Client non trouvé.");
            return;
        }

        // Demander le nom du médecin traitant
        System.out.println("Nom du médecin :");
        String doctorName = scanner.nextLine();
        Doctor doctor = controller.getDoctorByName(doctorName);

        // Vérifier si le médecin existe
        if (doctor == null) {
            System.out.println("Médecin non trouvé.");
            return;
        }

        // Afficher la liste des médicaments disponibles
        List<Medicine> availableMedicines = controller.getMedicines();
        if (availableMedicines.isEmpty()) {
            System.out.println("Aucun médicament disponible.");
            return;
        }

        System.out.println("Voici les médicaments disponibles :");
        for (int i = 0; i < availableMedicines.size(); i++) {
            Medicine med = availableMedicines.get(i);
            System.out.println((i + 1) + ". " + med.getName() + " (" + med.getCategory() + ") - Prix unitaire : " + med.getPrice() + " €");
        }

        List<Medicine> selectedMedicines = new ArrayList<>();
        double totalPrescriptionPrice = 0;

        // Demander à l'utilisateur de sélectionner des médicaments et saisir les quantités
        System.out.println("Combien de médicaments souhaitez-vous ajouter à l'ordonnance ?");
        int medCount = scanner.nextInt();
        scanner.nextLine();  // Consommer la ligne restante

        for (int i = 0; i < medCount; i++) {
            System.out.println("Sélectionnez le médicament (1 à " + availableMedicines.size() + "):");
            int medIndex = scanner.nextInt() - 1;
            scanner.nextLine();  // Consommer la ligne restante

            if (medIndex >= 0 && medIndex < availableMedicines.size()) {
                Medicine selectedMedicine = availableMedicines.get(medIndex);

                // Demander la quantité pour ce médicament
                System.out.println("Entrez la quantité pour " + selectedMedicine.getName() + " :");
                int quantity = scanner.nextInt();
                scanner.nextLine();  // Consommer la ligne restante

                // Calculer le prix total pour ce médicament et ajouter à l'ordonnance
                double totalPriceForMedicine = selectedMedicine.calculateTotalPrice();
                System.out.println("Total pour " + selectedMedicine.getName() + " : " + totalPriceForMedicine + " €");

                totalPrescriptionPrice += totalPriceForMedicine;
                selectedMedicines.add(selectedMedicine);
            } else {
                System.out.println("Médicament non valide.");
            }
        }

        // Créer l'ordonnance avec le client et le médecin associés
        Ordonnance ordonnance = new Ordonnance(LocalDate.now(), doctor, client, selectedMedicines, null);
        controller.addOrdonnance(ordonnance);

        System.out.println("Ordonnance enregistrée avec succès !");
        System.out.println("Prix total de l'ordonnance : " + totalPrescriptionPrice + " €");
    }

    private void displayPurchases() {
        // Récupérer la liste des ordonnances
        List<Ordonnance> ordonnances = controller.getOrdonnances();

        if (ordonnances.isEmpty()) {
            System.out.println("Aucun achat enregistré.");
            return;
        }

        // Afficher les détails des ordonnances
        for (Ordonnance ordonnance : ordonnances) {
            Client client = ordonnance.getClient();
            Doctor doctor = ordonnance.getDoctor();

            // Vérifier si le client ou le médecin est null avant d'afficher
            if (client != null && doctor != null) {
                System.out.println("Ordonnance pour le client : " + client.getFirstName() + " " + client.getLastName() +
                        ", Médecin : " + doctor.getFirstName() + " " + doctor.getLastName() +
                        ", Date : " + ordonnance.getDate());
            } else {
                System.out.println("Ordonnance avec des informations manquantes.");
            }
        }
    }

    private void displayClient() {
        System.out.println("Nom du client :");
        String clientName = scanner.nextLine();
        Client client = controller.getClientByName(clientName);

        if (client != null) {
            System.out.println("=== Détails du client ===");
            System.out.println("Nom : " + client.getFirstName() + " " + client.getLastName());
            System.out.println("Adresse : " + client.getAddress());
            System.out.println("Ville : " + client.getCity());
            System.out.println("Code postal : " + client.getPostalCode());
            System.out.println("Téléphone : " + client.getPhone());
            System.out.println("Email : " + client.getEmail());
            System.out.println("Numéro de sécurité sociale : " + client.getSocialSecurityNumber());
            System.out.println("Date de naissance : " + client.getBirthDate());
            System.out.println("Mutuelle : " + client.getMutuelle().getName() + " - Taux de remboursement : " + client.getMutuelle().getReimbursementRate() + "%");
            System.out.println("Médecin traitant : " + client.getDoctor().getFirstName() + " " + client.getDoctor().getLastName());
        } else {
            System.out.println("Client non trouvé.");
        }
    }

    private void displayDoctor() {
        System.out.println("Nom du médecin :");
        String doctorName = scanner.nextLine();
        Doctor doctor = controller.getDoctorByName(doctorName);

        if (doctor != null) {
            System.out.println("=== Détails du médecin ===");
            System.out.println("Nom : " + doctor.getFirstName() + " " + doctor.getLastName());
            System.out.println("Adresse : " + doctor.getAddress());
            System.out.println("Ville : " + doctor.getCity());
            System.out.println("Code postal : " + doctor.getPostalCode());
            System.out.println("Téléphone : " + doctor.getPhone());
            System.out.println("Email : " + doctor.getEmail());
            System.out.println("Numéro d'agrément : " + doctor.getRegistrationNumber());
        } else {
            System.out.println("Médecin non trouvé.");
        }
    }
}
