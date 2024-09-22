package fr.pompey.dev.afpa.tests;

import fr.pompey.dev.afpa.enums.MedicineCategory;
import fr.pompey.dev.afpa.exceptions.SaisieException;
import fr.pompey.dev.afpa.models.Medicine;
import fr.pompey.dev.afpa.models.Purchase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PurchaseTest {
    private Purchase purchase;
    private List<Medicine> medicines;

    @BeforeEach
    void setUp() throws SaisieException {
        // Préparation des objets avant chaque test
        medicines = new ArrayList<>();
        medicines.add(new Medicine("Doliprane", MedicineCategory.ANALGESIC,
                2.5, LocalDate.now(), 10));
        purchase = new Purchase(medicines, LocalDate.now());
    }

    @AfterEach
    void tearDown() {
        // Nettoyage après chaque test
        purchase = null;
        medicines = null;
    }

    @Test
    void getOrdonnance() {
        // Vous n'avez pas encore implémenté de méthode pour gérer l'ordonnance dans Purchase.
        // Il faudra soit retirer ce test, soit l'ajouter à la classe Purchase si nécessaire.
    }

    @Test
    void getMedicines() {
        assertNotNull(purchase.getMedicines());
        assertEquals(1, purchase.getMedicines().size());
        assertEquals("Doliprane", purchase.getMedicines().get(0).getName());
    }

    @Test
    void setMedicines() throws SaisieException {
        List<Medicine> newMedicines = new ArrayList<>();
        newMedicines.add(new Medicine("Amoxicilline", MedicineCategory.ANTIBIOTIC,
                12.0, LocalDate.now(), 5));

        purchase.setMedicines(newMedicines);

        assertEquals(1, purchase.getMedicines().size());
        assertEquals("Amoxicilline", purchase.getMedicines().get(0).getName());
        assertEquals(60.0, purchase.getTotalPrice()); // 5 * 12.0
    }

    @Test
    void setMedicinesThrowsExceptionForEmptyList() {
        Exception exception = assertThrows(SaisieException.class, () -> {
            purchase.setMedicines(new ArrayList<>());
        });
        assertEquals("La liste de médicaments ne peut pas être vide !", exception.getMessage());
    }

    @Test
    void getTotalPrice() {
        assertEquals(25.0, purchase.getTotalPrice()); // 10 * 2.5
    }

    @Test
    void getDate() {
        assertNotNull(purchase.getDate());
        assertEquals(LocalDate.now(), purchase.getDate());
    }

    @Test
    void setDate() throws SaisieException {
        LocalDate newDate = LocalDate.of(2024, 1, 1);
        purchase.setDate(newDate);

        assertEquals(newDate, purchase.getDate());
    }

    @Test
    void setDateThrowsExceptionForNullDate() {
        Exception exception = assertThrows(SaisieException.class, () -> {
            purchase.setDate(null);
        });
        assertEquals("La date de l'achat ne peut pas être nulle.", exception.getMessage());
    }

    @Test
    void testToString() {
        String expectedString = "Achat du : " + LocalDate.now().toString() + "\n" +
                "Médicament : Doliprane, Quantité : 10, Prix : 25.0 €\n" +
                "Prix total : 25.0 €";

        assertEquals(expectedString, purchase.toString());
    }
}