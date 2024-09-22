package fr.pompey.dev.afpa.models;

/**
 * Représente une mutuelle à laquelle un client peut souscrire.
 * Une mutuelle offre un taux de remboursement pour les frais de santé.
 */
public class Mutuelle {
    private String name;
    private String address;
    private String city;
    private String postalCode;
    private String phone;
    private String email;
    private String department;
    private double reimbursementRateDouble;

    /**
     * Constructeur de la classe Mutuelle.
     *
     * @param name Le nom de la mutuelle.
     * @param address L'adresse de la mutuelle.
     * @param city La ville de la mutuelle.
     * @param postalCode Le code postal de la mutuelle.
     * @param phone Le numéro de téléphone de la mutuelle.
     * @param email L'email de la mutuelle.
     * @param department Le département de la mutuelle.
     * @param reimbursementRateDouble Le taux de remboursement de la mutuelle.
     */
    public Mutuelle(String name, String address, String city, String postalCode, String phone, String email,
                    String department, double reimbursementRateDouble) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
        this.phone = phone;
        this.email = email;
        this.department = department;
        this.reimbursementRateDouble = reimbursementRateDouble;
    }

    // Getters et Setters
    /**
     * Retourne le nom de la mutuelle.
     *
     * @return Le nom de la mutuelle.
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * Retourne le taux de remboursement de la mutuelle.
     *
     * @return Le taux de remboursement.
     */
    public double getReimbursementRateDouble() {
        return reimbursementRateDouble;
    }

    public void setReimbursementRate(double reimbursementRateDouble) {
        this.reimbursementRateDouble = reimbursementRateDouble;
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères de la mutuelle.
     *
     * @return Les informations de la mutuelle sous forme de chaîne.
     */
    @Override
    public String toString() {
        return name + " - " + reimbursementRateDouble + "% de remboursement";
    }
}
