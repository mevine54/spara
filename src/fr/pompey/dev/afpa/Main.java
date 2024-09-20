package fr.pompey.dev.afpa;

import fr.pompey.dev.afpa.controllers.PharmacyController;
import fr.pompey.dev.afpa.enums.MedicineCategory;
import fr.pompey.dev.afpa.enums.Specialty;
import fr.pompey.dev.afpa.models.*;
import fr.pompey.dev.afpa.views.Dashboard;


import java.time.LocalDate;


public class Main {
    public static void main(String[] args) {
        // Initialisation des objets pour la pharmacie
        Pharmacy pharmacy = new Pharmacy();
        PharmacyController controller = new PharmacyController(pharmacy);

        // Création de quelques médecins, clients, médicaments et mutuelles pour tester
        Doctor doctor1 = new Doctor("Jean", "Martin", "12 rue de la santé", "Paris",
                "75000", "0102030405", "jean.martin@docteur.com", "123456",
                LocalDate.now(), "012345");
        Specialist specialist1 = new Specialist("Alice", "Dupont", "34 avenue des soins",
                "Lyon", "69000", "0607080910", "alice.dupont@specialiste.com",
                "654321", Specialty.CARDIOLOGIST.toString());

        Mutuelle mutuelle1 = new Mutuelle("Harmonie", "10 rue de l'assurance", "Lille",
                "59000", "0101010101", "contact@harmonie.com", "Nord",
                70.0);

        Client client1 = new Client("Marie", "Curie", "42 rue de la science",
                "Paris", "75001", "0606060606", "marie.curie@science.com",
                "9876543210987", LocalDate.now(), mutuelle1, doctor1);
        client1.addSpecialist(specialist1);

        // Création de quelques médicaments
        Medicine med1 = new Medicine("Doliprane", MedicineCategory.ANALGESIC, 2.5, LocalDate.now(),
                100);
        Medicine med2 = new Medicine("Amoxicilline", MedicineCategory.ANTIBIOTIC, 12.0, LocalDate.now(),
                50);

        // Ajout des objets créés à la pharmacie
        pharmacy.addClient(client1);
        pharmacy.addDoctor(doctor1);
        pharmacy.addDoctor(specialist1);
        pharmacy.addMedicine(med1);
        pharmacy.addMedicine(med2);


        // Lancer l'interface graphique avec Dashboard
        Dashboard dashboard = new Dashboard(controller);
        dashboard.setVisible(true);

    }
}