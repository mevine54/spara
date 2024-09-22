package fr.pompey.dev.afpa.utilities;



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
    public static boolean isValidName(String name) {
        return name.matches(NAME_REGEX);
    }

    public static boolean isValidAddress(String address) {
        return address.matches(ADDRESS_REGEX);
    }

    public static boolean isValidEmail(String email) {
        return email.matches(EMAIL_REGEX);
    }

    public static boolean isValidQuantity(String quantity) {
        return quantity.matches(QUANTITY_REGEX);
    }


    public static boolean isValidCity(String city) {
        return city.matches(CITY_REGEX);
    }

    public static boolean isValidPostalCode(String postalCode) {
        return postalCode.matches(POSTAL_CODE_REGEX);
    }

    public static boolean isValidPhone(String phone) {
        return phone.matches(PHONE_REGEX);
    }

    public static boolean isValidSsn(String ssn) {
        return ssn.matches(SSN_REGEX);
    }

    public static boolean isValidBirthDate(String birthDate) {
        return birthDate.matches(BIRTH_DATE_REGEX);
    }
}

