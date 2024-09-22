package fr.pompey.dev.afpa.views;

import fr.pompey.dev.afpa.models.Specialist;
import fr.pompey.dev.afpa.controllers.PharmacyController;
import fr.pompey.dev.afpa.models.Client;
import fr.pompey.dev.afpa.models.Doctor;
import fr.pompey.dev.afpa.models.Medicine;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PurchasePage extends JFrame {
    private final PharmacyController controller;
    private final JPanel contentPanel;
    private JTextArea addedMedicinesArea;
    private List<Medicine> selectedMedicines = new ArrayList<>();
    private double totalPrice = 0;
    private JLabel doctorTypeLabel;  // Label pour indiquer si c'est un médecin ou un spécialiste
    private JComboBox<Client> clientCombo;
    private JComboBox<Doctor> doctorCombo;

    public PurchasePage(PharmacyController controller) {
        this.controller = controller;
        setTitle("Pharmacie Sparadrap - Achat");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout principal
        setLayout(new BorderLayout());
        contentPanel = new JPanel(new GridBagLayout());
        add(contentPanel, BorderLayout.CENTER);

        initPurchaseView();
    }

    private void initPurchaseView() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        // Label Type d'achat
        JLabel typeLabel = new JLabel("Type d'achat :");
        gbc.gridx = 0;
        gbc.gridy = 0;
        contentPanel.add(typeLabel, gbc);

        // ComboBox Type d'achat
        String[] typesAchat = {"Achat direct", "Achat avec ordonnance"};
        JComboBox<String> typeAchatCombo = new JComboBox<>(typesAchat);
        gbc.gridx = 1;
        gbc.gridy = 0;
        contentPanel.add(typeAchatCombo, gbc);

        // Combobox pour les médicaments
        JLabel medicineLabel = new JLabel("Médicament :");
        gbc.gridx = 0;
        gbc.gridy = 1;
        contentPanel.add(medicineLabel, gbc);

        JComboBox<Medicine> medicineCombo = new JComboBox<>();
        List<Medicine> medicines = controller.getMedicines();
        for (Medicine med : medicines) {
            medicineCombo.addItem(med);
        }
        gbc.gridx = 1;
        gbc.gridy = 1;
        contentPanel.add(medicineCombo, gbc);

        // Prix unitaire du médicament
        JLabel priceLabel = new JLabel("Prix unitaire :");
        gbc.gridx = 0;
        gbc.gridy = 2;
        contentPanel.add(priceLabel, gbc);

        JLabel priceValueLabel = new JLabel("0.00 €");
        gbc.gridx = 1;
        gbc.gridy = 2;
        contentPanel.add(priceValueLabel, gbc);

        medicineCombo.addActionListener(e -> {
            Medicine selectedMedicine = (Medicine) medicineCombo.getSelectedItem();
            if (selectedMedicine != null) {
                priceValueLabel.setText(selectedMedicine.getPrice() + " €");
            }
        });

        // Champ quantité
        JLabel quantityLabel = new JLabel("Quantité :");
        gbc.gridx = 0;
        gbc.gridy = 3;
        contentPanel.add(quantityLabel, gbc);

        JTextField quantityField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 3;
        contentPanel.add(quantityField, gbc);

        // Zone d'affichage des médicaments ajoutés
        addedMedicinesArea = new JTextArea(5, 20);
        addedMedicinesArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(addedMedicinesArea);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        contentPanel.add(scrollPane, gbc);

        // Bouton Ajouter Médicament
        JButton addMedicineButton = new JButton("Ajouter Médicament");
        gbc.gridx = 1;
        gbc.gridy = 6;
        contentPanel.add(addMedicineButton, gbc);

        addMedicineButton.addActionListener(e -> {
            try {
                int quantity = Integer.parseInt(quantityField.getText());
                Medicine selectedMedicine = (Medicine) medicineCombo.getSelectedItem();
                if (selectedMedicine != null) {
                    selectedMedicine.setQuantity(quantity);
                    double totalMedicinePrice = selectedMedicine.getPrice() * quantity;
                    totalPrice += totalMedicinePrice;
                    selectedMedicines.add(selectedMedicine);
                    addedMedicinesArea.append(selectedMedicine.getName() + " x " + quantity + " : " + totalMedicinePrice + " €\n");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Quantité invalide.");
            }
        });

        // Label Médecin/Specialiste
        doctorTypeLabel = new JLabel();
        gbc.gridx = 0;
        gbc.gridy = 8;
        contentPanel.add(doctorTypeLabel, gbc);

        // Listener pour changer le type d'achat
        typeAchatCombo.addActionListener(e -> {
            String selectedType = (String) typeAchatCombo.getSelectedItem();
            if ("Achat direct".equals(selectedType)) {
                clearOrdonnanceFields();
            } else {
                showOrdonnanceFields(gbc);
            }
        });

        // Bouton pour valider l'achat
        JButton validateButton = new JButton("Valider l'achat");
        gbc.gridx = 1;
        gbc.gridy = 9;
        contentPanel.add(validateButton, gbc);

        validateButton.addActionListener(e -> {
            handlePurchaseValidation();
        });

        // Bouton pour retourner
        JButton retourButton = new JButton("Retour");
        gbc.gridx = 0;
        gbc.gridy = 9;
        contentPanel.add(retourButton, gbc);

        retourButton.addActionListener(e -> {
            HomePage homePage = new HomePage(controller);
            homePage.setVisible(true);
            dispose();
        });
    }

    private void showOrdonnanceFields(GridBagConstraints gbc) {
        // Champ pour sélectionner le client
        JLabel clientLabel = new JLabel("Client :");
        gbc.gridx = 0;
        gbc.gridy = 7;
        contentPanel.add(clientLabel, gbc);

        clientCombo = new JComboBox<>();
        List<Client> clients = controller.getClients();
        for (Client client : clients) {
            clientCombo.addItem(client);
        }
        gbc.gridx = 1;
        gbc.gridy = 7;
        contentPanel.add(clientCombo, gbc);

        // Champ pour sélectionner le médecin
        JLabel doctorLabel = new JLabel("Médecin :");
        gbc.gridx = 0;
        gbc.gridy = 8;
        contentPanel.add(doctorLabel, gbc);

        doctorCombo = new JComboBox<>();
        List<Doctor> doctors = controller.getDoctors();
        for (Doctor doctor : doctors) {
            doctorCombo.addItem(doctor);
        }
        gbc.gridx = 1;
        gbc.gridy = 8;
        contentPanel.add(doctorCombo, gbc);

        // Mise à jour du label en fonction du type de médecin
        doctorCombo.addActionListener(e -> {
            Doctor selectedDoctor = (Doctor) doctorCombo.getSelectedItem();
            if (selectedDoctor != null) {
                if (selectedDoctor instanceof Specialist) {
                    doctorTypeLabel.setText("Spécialiste : " + ((Specialist) selectedDoctor).getSpecialty());
                } else {
                    doctorTypeLabel.setText("Médecin généraliste");
                }
            }
        });

        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void clearOrdonnanceFields() {
        // Effacer les champs relatifs à l'ordonnance
        contentPanel.remove(clientCombo);
        contentPanel.remove(doctorCombo);
        doctorTypeLabel.setText("");
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void handlePurchaseValidation() {
        // Validation de l'achat
        addedMedicinesArea.setText("");  // Vider la zone de texte
        selectedMedicines.clear();       // Réinitialiser la liste des médicaments sélectionnés
        totalPrice = 0;                  // Réinitialiser le total
        JOptionPane.showMessageDialog(null, "Achat validé avec succès !");
    }
}