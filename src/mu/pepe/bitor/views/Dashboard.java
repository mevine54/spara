package mu.pepe.bitor.views;

import mu.pepe.bitor.models.Client;
import mu.pepe.bitor.models.*;
import mu.pepe.bitor.models.Medicine;
import mu.pepe.bitor.controllers.PharmacyController;
import mu.pepe.bitor.models.Pharmacy;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dashboard extends JFrame {
    private PharmacyController controller;
    private JPanel contentPanel;
    private List<Medicine> selectedMedicines = new ArrayList<>();
    private double totalPrice = 0;

    public Dashboard(PharmacyController controller) {
        this.controller = controller;
        setTitle("Pharmacie Sparadrap - Dashboard");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout principal avec barre latérale et contenu central
        setLayout(new BorderLayout());

        // Barre de navigation (navbar)
        JPanel navbar = new JPanel();
        navbar.setLayout(new FlowLayout(FlowLayout.LEFT));
        navbar.setBackground(new Color(34, 45, 65));
        navbar.setPreferredSize(new Dimension(800, 50));
        JLabel title = new JLabel("Dashboard Pharmacie Sparadrap");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 16));
        navbar.add(title);
        add(navbar, BorderLayout.NORTH);

        // Sidebar avec boutons
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new GridLayout(6, 1));
        sidebar.setBackground(new Color(242, 242, 242));
        sidebar.setPreferredSize(new Dimension(200, 600));

        // Boutons de la sidebar
        JButton btnPurchase = new JButton("Effectuer un achat");
        btnPurchase.setBackground(new Color(34, 150, 243));
        btnPurchase.setForeground(Color.WHITE);

        JButton btnHistory = new JButton("Historique des achats");
        btnHistory.setBackground(new Color(34, 150, 243));
        btnHistory.setForeground(Color.WHITE);

        JButton btnClients = new JButton("Consulter clients");
        btnClients.setBackground(new Color(34, 150, 243));
        btnClients.setForeground(Color.WHITE);

        JButton btnDoctors = new JButton("Consulter médecins");
        btnDoctors.setBackground(new Color(34, 150, 243));
        btnDoctors.setForeground(Color.WHITE);

        JButton btnQuit = new JButton("Quitter");
        btnQuit.setBackground(new Color(255, 69, 58));
        btnQuit.setForeground(Color.WHITE);

        // Ajout des boutons à la sidebar
        sidebar.add(btnPurchase);
        sidebar.add(btnHistory);
        sidebar.add(btnClients);
        sidebar.add(btnDoctors);
        sidebar.add(btnQuit);  // Bouton Quitter

        add(sidebar, BorderLayout.WEST);

        // Panneau central où le contenu change
        contentPanel = new JPanel();
        contentPanel.setLayout(new CardLayout());
        add(contentPanel, BorderLayout.CENTER);

        // Listener pour le bouton d'achat
        btnPurchase.addActionListener(e -> showPurchaseView());

        // Listener pour quitter l'application
        btnQuit.addActionListener(e -> System.exit(0));  // Fermer l'application
    }

    private void showPurchaseView() {
        contentPanel.removeAll();

        JPanel purchasePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        // Label pour indiquer le type d'achat sélectionné
        JLabel purchaseTypeLabel = new JLabel("ACHAT DIRECT");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        purchasePanel.add(purchaseTypeLabel, gbc);

        // Combobox pour choisir le type d'achat
        JLabel typeLabel = new JLabel("Type d'achat :");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        purchasePanel.add(typeLabel, gbc);

        String[] typesAchat = {"Achat direct", "Achat avec ordonnance"};
        JComboBox<String> typeAchatCombo = new JComboBox<>(typesAchat);
        gbc.gridx = 1;
        gbc.gridy = 1;
        purchasePanel.add(typeAchatCombo, gbc);

        // Combobox pour choisir un médicament
        JLabel medicineLabel = new JLabel("Médicament :");
        gbc.gridx = 0;
        gbc.gridy = 2;
        purchasePanel.add(medicineLabel, gbc);

        JComboBox<Medicine> medicineCombo = new JComboBox<>();
        List<Medicine> medicines = controller.getMedicines();
        for (Medicine med : medicines) {
            medicineCombo.addItem(med);
        }
        gbc.gridx = 1;
        gbc.gridy = 2;
        purchasePanel.add(medicineCombo, gbc);

        // Prix unitaire
        JLabel priceLabel = new JLabel("Prix unitaire :");
        gbc.gridx = 0;
        gbc.gridy = 3;
        purchasePanel.add(priceLabel, gbc);

        JLabel priceValueLabel = new JLabel("0.00 €");
        gbc.gridx = 1;
        gbc.gridy = 3;
        purchasePanel.add(priceValueLabel, gbc);

        medicineCombo.addActionListener(e -> {
            Medicine selectedMedicine = (Medicine) medicineCombo.getSelectedItem();
            if (selectedMedicine != null) {
                priceValueLabel.setText(selectedMedicine.getPrice() + " €");
            }
        });

        // Champ quantité
        JLabel quantityLabel = new JLabel("Quantité :");
        gbc.gridx = 0;
        gbc.gridy = 4;
        purchasePanel.add(quantityLabel, gbc);

        JTextField quantityField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 4;
        purchasePanel.add(quantityField, gbc);

        // Prix total
        JLabel totalLabel = new JLabel("Prix total :");
        gbc.gridx = 0;
        gbc.gridy = 5;
        purchasePanel.add(totalLabel, gbc);

        JLabel totalValueLabel = new JLabel("0.00 €");
        gbc.gridx = 1;
        gbc.gridy = 5;
        purchasePanel.add(totalValueLabel, gbc);

        // Zone d'affichage des médicaments ajoutés
        JTextArea addedMedicinesArea = new JTextArea(5, 20);
        addedMedicinesArea.setEditable(false);
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        purchasePanel.add(new JScrollPane(addedMedicinesArea), gbc);

        // Bouton Ajouter Médicament
        JButton addMedicineButton = new JButton("Ajouter Médicament");
        gbc.gridx = 1;
        gbc.gridy = 7;
        purchasePanel.add(addMedicineButton, gbc);

        addMedicineButton.addActionListener(e -> {
            try {
                int quantity = Integer.parseInt(quantityField.getText());
                Medicine selectedMedicine = (Medicine) medicineCombo.getSelectedItem();
                if (selectedMedicine != null) {
                    double medicineTotalPrice = selectedMedicine.calculateTotalPrice(quantity);
                    totalPrice += medicineTotalPrice;
                    selectedMedicines.add(selectedMedicine);
                    addedMedicinesArea.append(selectedMedicine.getName() + " x " + quantity + " : " + medicineTotalPrice + " €\n");
                    totalValueLabel.setText(totalPrice + " €");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Quantité invalide.");
            }
        });

        // Listener pour changer entre Achat Direct et Achat Ordonnance
        typeAchatCombo.addActionListener(e -> {
            String selectedType = (String) typeAchatCombo.getSelectedItem();
            if (selectedType.equals("Achat direct")) {
                purchaseTypeLabel.setText("ACHAT DIRECT");
                // Masquer les champs clients (si achat direct)
            } else if (selectedType.equals("Achat avec ordonnance")) {
                purchaseTypeLabel.setText("ACHAT AVEC ORDONNANCE");
                // Ajouter ici les autres champs (si ordonnance) et rendre visibles
                showOrdonnanceFields(purchasePanel, gbc);
            }
        });

        // Bouton Valider l'achat
        JButton validateButton = new JButton("Valider l'achat");
        gbc.gridx = 1;
        gbc.gridy = 8;
        purchasePanel.add(validateButton, gbc);

        validateButton.addActionListener(e -> {
            // Validation logic
            JOptionPane.showMessageDialog(null, "Achat validé avec succès !");
        });

        // Bouton Retour
        JButton backButton = new JButton("Retour");
        gbc.gridx = 0;
        gbc.gridy = 8;
        purchasePanel.add(backButton, gbc);

        backButton.addActionListener(e -> {
            contentPanel.removeAll();
            contentPanel.revalidate();
            contentPanel.repaint();
        });

        // Ajouter le panneau au contentPanel
        contentPanel.add(purchasePanel);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void showOrdonnanceFields(JPanel panel, GridBagConstraints gbc) {
        // Champs spécifiques pour un achat avec ordonnance (client details)

        // Nom du client
        JLabel clientNameLabel = new JLabel("Nom du client :");
        gbc.gridx = 0;
        gbc.gridy = 9;
        panel.add(clientNameLabel, gbc);

        JTextField clientNameField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 9;
        panel.add(clientNameField, gbc);

        // Téléphone
        JLabel phoneLabel = new JLabel("Téléphone :");
        gbc.gridx = 0;
        gbc.gridy = 10;
        panel.add(phoneLabel, gbc);

        JTextField phoneField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 10;
        panel.add(phoneField, gbc);

        // Email
        JLabel emailLabel = new JLabel("Email :");
        gbc.gridx = 0;
        gbc.gridy = 11;
        panel.add(emailLabel, gbc);

        JTextField emailField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 11;
        panel.add(emailField, gbc);

        // Numéro de sécurité sociale
        JLabel ssnLabel = new JLabel("Numéro de sécurité sociale :");
        gbc.gridx = 0;
        gbc.gridy = 12;
        panel.add(ssnLabel, gbc);

        JTextField ssnField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 12;
        panel.add(ssnField, gbc);

        // Mutuelle
        JLabel mutuelleLabel = new JLabel("Mutuelle :");
        gbc.gridx = 0;
        gbc.gridy = 13;
        panel.add(mutuelleLabel, gbc);

        JTextField mutuelleField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 13;
        panel.add(mutuelleField, gbc);

        // Rafraîchir le panneau
        panel.revalidate();
        panel.repaint();
    }

    public static void main(String[] args) {
        Pharmacy pharmacy = new Pharmacy();
        PharmacyController controller = new PharmacyController(pharmacy);
        Dashboard dashboard = new Dashboard(controller);
        dashboard.setVisible(true);
    }
}


