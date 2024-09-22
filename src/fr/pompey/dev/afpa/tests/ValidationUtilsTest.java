package fr.pompey.dev.afpa.tests;

import fr.pompey.dev.afpa.utilities.ValidationUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ValidationUtilsTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    /**
     * Teste la validation des noms valides.
     */
    @Test
    void testValidName() {
        assertTrue(ValidationUtils.isValidName("John"));
        assertFalse(ValidationUtils.isValidName("John123"));
        assertFalse(ValidationUtils.isValidName(null));
    }

    /**
     * Teste la validation des adresses.
     */
    @Test
    void testValidAddress() {
        assertTrue(ValidationUtils.isValidAddress("123 Main St"));
        assertFalse(ValidationUtils.isValidAddress(""));
        assertFalse(ValidationUtils.isValidAddress(null));
    }

    /**
     * Teste la validation des adresses email.
     */
    @Test
    void testValidEmail() {
        assertTrue(ValidationUtils.isValidEmail("john.doe@example.com"));
        assertFalse(ValidationUtils.isValidEmail("john.doe@.com"));
        assertFalse(ValidationUtils.isValidEmail(null));
    }

    @Test
    void isValidQuantity() {
    }

    @Test
    void isValidCity() {
    }

    /**
     * Teste la validation des codes postaux.
     */
    @Test
    void testValidPostalCode() {
        assertTrue(ValidationUtils.isValidPostalCode("75001"));
        assertFalse(ValidationUtils.isValidPostalCode("ABC"));
        assertFalse(ValidationUtils.isValidPostalCode(null));
    }

    /**
     * Teste la validation des numéros de téléphone.
     */
    @Test
    void testValidPhone() {
        assertTrue(ValidationUtils.isValidPhone("0123456789"));
        assertFalse(ValidationUtils.isValidPhone("12345"));
        assertFalse(ValidationUtils.isValidPhone(null));
    }

    /**
     * Teste la validation des numéros de sécurité sociale.
     */
    @Test
    void testValidSsn() {
        assertTrue(ValidationUtils.isValidSsn("123456789012345"));
        assertFalse(ValidationUtils.isValidSsn("12345"));
        assertFalse(ValidationUtils.isValidSsn(null));
    }

    /**
     * Teste la validation des dates de naissance.
     * Une date de naissance valide doit être antérieure ou égale à la date du jour.
     */
    @Test
    void isValidBirthDate() {
        // Date de naissance valide (dans le passé)
        assertTrue(ValidationUtils.isValidBirthDate(String.valueOf(LocalDate.of(1990, 5, 10))));

        // Date de naissance égale à aujourd'hui (valide)
        assertTrue(ValidationUtils.isValidBirthDate(String.valueOf(LocalDate.now())));

        // Date de naissance dans le futur (invalide)
        assertFalse(ValidationUtils.isValidBirthDate(String.valueOf(LocalDate.now().plusDays(1))));

        // Date de naissance nulle (invalide)
        assertFalse(ValidationUtils.isValidBirthDate(null));
    }
}