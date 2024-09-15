package mu.pepe.bitor.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe de gestion principale de la pharmacie, stockant tous les clients, médecins, médicaments, et ordonnances.
 */
public class Pharmacy {
    private List<Client> clients;
    private List<Doctor> doctors;
    private List<Medicine> medicines;
    private List<Ordonnance> ordonnances;
    private List<Mutuelle> mutuelles;

    public Pharmacy() {
        this.clients = new ArrayList<>();
        this.doctors = new ArrayList<>();
        this.medicines = new ArrayList<>();
        this.ordonnances = new ArrayList<>();
    }

    // Gestion des clients
    public void addClient(Client client) {
        clients.add(client);
    }

    public List<Client> getClients() {
        return clients;
    }

//    public Client getClientByName(String name) {
//        return clients.stream().filter(c -> c.getFirstName().equalsIgnoreCase(name)).findFirst().orElse(null);
//    }

    public Client getClientByName(String name) {
        // Parcourt la liste des clients et retourne celui dont le nom correspond
        for (Client client : clients) {
            if (client.getFirstName().equalsIgnoreCase(name) || client.getLastName().equalsIgnoreCase(name)) {
                return client;
            }
        }
        return null; // Si aucun client n'est trouvé
    }

    // Gestion des médecins
    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public Doctor getDoctorByName(String name) {
        return doctors.stream().filter(d -> d.getFirstName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    // Gestion des médicaments
    public void addMedicine(Medicine medicine) {
        medicines.add(medicine);
    }

    public List<Medicine> getMedicines() {
        return medicines;
    }

    // Gestion des ordonnances
    public void addOrdonnance(Ordonnance ordonnance) {
        ordonnances.add(ordonnance);
    }

    public List<Ordonnance> getOrdonnances() {
        return ordonnances;
    }

    public List<Ordonnance> getOrdonnancesByDoctor(String doctorName) {
        return ordonnances.stream().filter(o -> o.getDoctor().getFirstName().equalsIgnoreCase(doctorName)).toList();
    }

    public Medicine getMedicineByName(String name) {
        return medicines.stream().filter(d -> d.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    // Ajouter une mutuelle
    public void addMutuelle(Mutuelle mutuelle) {
        mutuelles.add(mutuelle);
    }

    // Récupérer la liste de toutes les mutuelles
    public List<Mutuelle> getMutuelles() {
        return mutuelles;
    }
}
