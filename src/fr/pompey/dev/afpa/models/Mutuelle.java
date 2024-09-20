package fr.pompey.dev.afpa.models;

/**
 * Classe représentant une mutuelle.
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

    public double getReimbursementRateDouble() {
        return reimbursementRateDouble;
    }

    public void setReimbursementRate(double reimbursementRateDouble) {
        this.reimbursementRateDouble = reimbursementRateDouble;
    }

    @Override
    public String toString() {
        return "Mutuelle{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", department='" + department + '\'' +
                ", reimbursementRate=" + reimbursementRateDouble +
                '}';
    }
}
