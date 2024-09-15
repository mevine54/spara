package mu.pepe.bitor;

import mu.pepe.bitor.controllers.PharmacyController;
import mu.pepe.bitor.models.*;
import mu.pepe.bitor.views.ConsoleView;
import mu.pepe.bitor.enums.MedicineCategory;
import mu.pepe.bitor.enums.MutuelleType;
import mu.pepe.bitor.enums.Specialty;

import java.util.ArrayList;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        // Initialisation des objets pour la pharmacie
        Pharmacy pharmacy = new Pharmacy();

        // Création de quelques médecins, clients, médicaments et mutuelles pour tester
        Doctor doctor1 = new Doctor("Jean", "Martin", "12 rue de la santé", "Paris", "75000", "0102030405", "jean.martin@docteur.com", "123456");
        Specialist specialist1 = new Specialist("Alice", "Dupont", "34 avenue des soins", "Lyon", "69000", "0607080910", "alice.dupont@specialiste.com", "654321", Specialty.CARDIOLOGIST.toString());

        Mutuelle mutuelle1 = new Mutuelle("Harmonie", "10 rue de l'assurance", "Lille", "59000", "0101010101", "contact@harmonie.com", "Nord", 70.0);

        Client client1 = new Client("Marie", "Curie", "42 rue de la science", "Paris", "75001", "0606060606", "marie.curie@science.com", "9876543210987", "10/10/1980", mutuelle1, doctor1);
        client1.addSpecialist(specialist1);

        // Création de quelques médicaments
        Medicine med1 = new Medicine("Doliprane", MedicineCategory.ANALGESIC, 2.5, new Date(), 100);
        Medicine med2 = new Medicine("Amoxicilline", MedicineCategory.ANTIBIOTIC, 12.0, new Date(), 50);

        // Ajout des objets créés à la pharmacie
        pharmacy.addClient(client1);
        pharmacy.addDoctor(doctor1);
        pharmacy.addMedicine(med1);
        pharmacy.addMedicine(med2);

        // Création du contrôleur de la pharmacie
        PharmacyController controller = new PharmacyController(pharmacy);

        // Lancement de l'interface console
        ConsoleView view = new ConsoleView(controller);
        view.start();  // Lancement de l'application console
    }
}