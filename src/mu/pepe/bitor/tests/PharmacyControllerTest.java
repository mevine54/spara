package mu.pepe.bitor.tests;

import mu.pepe.bitor.controllers.PharmacyController;
import mu.pepe.bitor.models.*;
import mu.pepe.bitor.enums.MedicineCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;

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
    void testAddClient() {
        Client client = new Client("Jean", "Dupont", "Rue de la paix", "Paris", "75001",
                "0102030405", "jean.dupont@gmail.com", "1234567890123", "01/01/1980", null, null);
        controller.addClient(client);

        assertNotNull(controller.getClientByName("Jean"));
    }

    @Test
    void testAddMedicine() {
        Medicine medicine = new Medicine("Doliprane", MedicineCategory.ANALGESIC, 2.5, new Date(), 100);
        controller.addMedicine(medicine);

        assertNotNull(controller.getMedicineByName("Doliprane"));
    }
}
