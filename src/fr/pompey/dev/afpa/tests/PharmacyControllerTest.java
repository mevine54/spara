package fr.pompey.dev.afpa.tests;

import fr.pompey.dev.afpa.controllers.PharmacyController;
import fr.pompey.dev.afpa.exceptions.SaisieException;
import fr.pompey.dev.afpa.models.Client;
import fr.pompey.dev.afpa.models.Medicine;
import fr.pompey.dev.afpa.models.Pharmacy;
import fr.pompey.dev.afpa.enums.MedicineCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PharmacyControllerTest {

    private PharmacyController controller;
    private Pharmacy pharmacy;

    @BeforeEach
    void setUp() {
        pharmacy = new Pharmacy();
        controller = new PharmacyController(pharmacy);
    }

    @Test
    void testAddClient() throws SaisieException {
        Client client = new Client("Jean", "Dupont", "Rue de la paix", "Paris", "75001",
                "0102030405", "jean.dupont@gmail.com", "123456789012123", LocalDate.now(), null, null);
        controller.addClient(client);

        assertNotNull(controller.getClientByName("Jean"));
    }

    @Test
    void testAddMedicine() throws SaisieException {
        Medicine medicine = new Medicine("Doliprane", MedicineCategory.ANALGESIC, 2.5, LocalDate.now(), 100);
        controller.addMedicine(medicine);

        assertNotNull(controller.getMedicineByName("Doliprane"));
    }
}
