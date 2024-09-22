package fr.pompey.dev.afpa.utilities;


/**
 * Classe utilitaire pour la validation des saisies de l'utilisateur.
 * Contient des méthodes statiques pour vérifier les formats des entrées.
 */
public class ValidationUtils {

    // Regex pour différents champs
    private static final String NAME_REGEX = "^[A-Za-zÀ-ÖØ-öø-ÿ]+$";
    private static final String EMAIL_REGEX = "^[a-z0-9+_.-]+@[a-z0-9.-]+$";
    private static final String QUANTITY_REGEX = "^[1-9]{1}[0-9]{0,2}$";
    private static final String ADDRESS_REGEX = "^[A-Za-z0-9À-ÖØ-öø-ÿ\\s\\-\\'\\(\\)]+$";
    private static final String CITY_REGEX = "^[A-Za-z0-9À-ÖØ-öø-ÿ\\s\\-\\'\\(\\)]+$";
    private static final String POSTAL_CODE_REGEX = "^[1-9]{1}[0-9]{0,2}$";
    private static final String PHONE_REGEX = "^(0|(\\+[0-9]{2}[. -]?))[1-9]([. -]?[0-9][0-9]){4}$";
    private static final String SSN_REGEX = "^[12][0-9]{2}(0[1-9]|1[0-2])(2[AB]|[0-9]{2})[0-9]{3}[0-9]{3}([0-9]{2})$";
    private static final String BIRTH_DATE_REGEX = "^(0[1-9]|1[012])[-/.](0[1-9]|[12][0-9]|3[01])[-/.](19|20)\\\\d\\\\d$";


    // Méthode statique pour validation des champs
    /**
     * Valide si le nom fourni est valide.
     * Un nom valide doit être non nul, non vide et ne contenir que des lettres.
     *
     * @param name Le nom à valider.
     * @return {@code true} si le nom est valide, {@code false} sinon.
     */
    public static boolean isValidName(String name) {
        return name != null && name.matches("[A-Za-z]+");
    }

    /**
     * Valide si l'adresse fournie est valide.
     * Une adresse valide ne doit pas être vide ou nulle.
     *
     * @param address L'adresse à valider.
     * @return {@code true} si l'adresse est valide, {@code false} sinon.
     */
    public static boolean isValidAddress(String address) {
        return address != null && !address.trim().isEmpty();
    }

    /**
     * Valide si l'adresse email est valide.
     * Une adresse email valide doit respecter le format "exemple@domaine.com".
     *
     * @param email L'email à valider.
     * @return {@code true} si l'email est valide, {@code false} sinon.
     */
    public static boolean isValidEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

    public static boolean isValidQuantity(String quantity) {
        return quantity.matches(QUANTITY_REGEX);
    }


    public static boolean isValidCity(String city) {
        return city.matches(CITY_REGEX);
    }

    /**
     * Valide si le code postal est valide.
     * Le code postal doit être composé de 5 chiffres.
     *
     * @param postalCode Le code postal à valider.
     * @return {@code true} si le code postal est valide, {@code false} sinon.
     */
    public static boolean isValidPostalCode(String postalCode) {
        return postalCode != null && postalCode.matches("\\d{5}");
    }

    /**
     * Valide si le numéro de téléphone est valide.
     * Le numéro doit être composé de 10 chiffres.
     *
     * @param phone Le numéro de téléphone à valider.
     * @return {@code true} si le numéro est valide, {@code false} sinon.
     */
    public static boolean isValidPhone(String phone) {
        return phone != null && phone.matches("\\d{10}");
    }

    /**
     * Valide si le numéro de sécurité sociale est valide.
     * Le numéro doit contenir 15 chiffres.
     *
     * @param ssn Le numéro de sécurité sociale à valider.
     * @return {@code true} si le numéro est valide, {@code false} sinon.
     */
    public static boolean isValidSsn(String ssn) {
        return ssn != null && ssn.matches("\\d{15}");
    }

    public static boolean isValidBirthDate(String birthDate) {
        return birthDate.matches(BIRTH_DATE_REGEX);
    }
}

