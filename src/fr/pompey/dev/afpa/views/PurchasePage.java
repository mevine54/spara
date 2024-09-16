package fr.pompey.dev.afpa.views;

import fr.pompey.dev.afpa.controllers.PharmacyController;
import fr.pompey.dev.afpa.models.Medicine;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PurchasePage extends JFrame {

    private PharmacyController controller;
    private JPanel contentPanel;

    public PurchasePage(PharmacyController controller) {
        this.controller = controller;
        setTitle("Pharmacie Sparadrap - Achat");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Configuration du layout principal
        setLayout(new BorderLayout());

        // Création du panneau de contenu central
        contentPanel = new JPanel(new GridBagLayout());
        add(contentPanel, BorderLayout.CENTER);

        // Configuration de GridBagConstraints
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

        // Label et ComboBox pour le médicament
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

        JLabel quantityLabel = new JLabel("Quantité :");
        gbc.gridx = 0;
        gbc.gridy = 3;
        contentPanel.add(quantityLabel, gbc);

        JTextField quantityField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 3;
        contentPanel.add(quantityField, gbc);

        JLabel totalLabel = new JLabel("Prix total :");
        gbc.gridx = 0;
        gbc.gridy = 4;
        contentPanel.add(totalLabel, gbc);

        JLabel totalValueLabel = new JLabel("0.00 €");
        gbc.gridx = 1;
        gbc.gridy = 4;
        contentPanel.add(totalValueLabel, gbc);

        quantityField.addActionListener(e -> {
            try {
                int quantity = Integer.parseInt(quantityField.getText());
                Medicine selectedMedicine = (Medicine) medicineCombo.getSelectedItem();
                if (selectedMedicine != null) {
                    double totalPrice = selectedMedicine.calculateTotalPrice(quantity);
                    totalValueLabel.setText(totalPrice + " €");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Quantité invalide.");
            }
        });

        // Ajout d'un bouton de validation
        JButton validateButton = new JButton("Valider l'achat");
        gbc.gridx = 1;
        gbc.gridy = 5;
        contentPanel.add(validateButton, gbc);

        // Action pour la validation de l'achat
        validateButton.addActionListener(e -> {
            Medicine selectedMedicine = (Medicine) medicineCombo.getSelectedItem();
            int quantity;
            try {
                quantity = Integer.parseInt(quantityField.getText());
                if (quantity <= 0) throw new NumberFormatException();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Quantité invalide.");
                return;
            }

            // Calcul du prix total
            double totalPrice = selectedMedicine.calculateTotalPrice(quantity);
            JOptionPane.showMessageDialog(null, "Achat validé de " + quantity + " unités de " + selectedMedicine.getName() + " pour un total de " + totalPrice + " €");
        });

        // Ajout du bouton retour
        JButton retourButton = new JButton("Retour");
        gbc.gridx = 0;
        gbc.gridy = 6;
        contentPanel.add(retourButton, gbc);
        retourButton.addActionListener(e -> {
            HomePage homePage = new HomePage(controller);
            homePage.setVisible(true);
            dispose();
        });
    }
}
