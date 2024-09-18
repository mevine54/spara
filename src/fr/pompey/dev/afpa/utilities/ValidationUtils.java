package fr.pompey.dev.afpa.utilities;


public class ValidationUtils {

    // Regex pour différents champs
    private static final String NAME_REGEX = "^[A-Za-zÀ-ÖØ-öø-ÿ]+$";
    private static final String EMAIL_REGEX = "^[a-z0-9+_.-]+@[a-z0-9.-]+$";
    private static final String QUANTITY_REGEX = "^[1-9]{1}[0-9]{0,2}$";


    // Méthode statique pour validation des champs
    public static boolean isValidName(String name) {
        return name.matches(NAME_REGEX);
    }

    public static boolean isValidEmail(String email) {
        return email.matches(EMAIL_REGEX);
    }

    public static boolean isValidQuantity(String quantity) {
        return quantity.matches(QUANTITY_REGEX);
    }


}
