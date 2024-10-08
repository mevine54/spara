package fr.pompey.dev.afpa.views;

import fr.pompey.dev.afpa.controllers.PharmacyController;
import fr.pompey.dev.afpa.models.Doctor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DoctorPage extends JFrame {

    private PharmacyController controller;

    public DoctorPage(PharmacyController controller) {
        this.controller = controller;
        setTitle("Pharmacie Sparadrap - Détails Médecin");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));

        // ComboBox pour sélectionner un médecin
        panel.add(new JLabel("Sélectionner un médecin : "));
        JComboBox<Doctor> comboDoctor = new JComboBox<>();
        List<Doctor> doctors = controller.getDoctors();
        for (Doctor doctor : doctors) {
            comboDoctor.addItem(doctor);
        }
        panel.add(comboDoctor);

        // Zone pour afficher les détails du médecin
        JTextArea detailsArea = new JTextArea(5, 20);
        detailsArea.setEditable(false);
        panel.add(new JScrollPane(detailsArea));

        // Bouton pour afficher les détails du médecin
        JButton btnDoctors = new JButton("Afficher les détails");
        btnDoctors.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Doctor selectedDoctor = (Doctor) comboDoctor.getSelectedItem();
                if (selectedDoctor != null) {
                    detailsArea.setText("Nom : " + selectedDoctor.getFirstName() + " " + selectedDoctor.getLastName() + "\n" +
                            "Adresse : " + selectedDoctor.getAddress() + "\n" +
                            "Téléphone : " + selectedDoctor.getPhone() + "\n" +
                            "Email : " + selectedDoctor.getEmail() + "\n" +
                            "Numéro d'agréement : " + selectedDoctor.getRegistrationNumber());
                }
            }
        });
        panel.add(btnDoctors);

        // Bouton pour revenir à la page d'accueil
        JButton btnRetour = new JButton("Retour");
        btnRetour.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                HomePage homePage = new HomePage(controller);
                homePage.setVisible(true);
                dispose();  // Fermer la page actuelle
            }
        });
        panel.add(btnRetour);

        add(panel);
    }
}
