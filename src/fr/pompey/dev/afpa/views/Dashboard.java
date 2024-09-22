package fr.pompey.dev.afpa.views;

import fr.pompey.dev.afpa.controllers.PharmacyController;
import fr.pompey.dev.afpa.exceptions.SaisieException;
import fr.pompey.dev.afpa.exceptions.SystemeException;
import fr.pompey.dev.afpa.utilities.DialogUtils;
import fr.pompey.dev.afpa.models.*;
import fr.pompey.dev.afpa.models.Doctor;



import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;

/**
 * Classe représentant le tableau de bord de la pharmacie.
 */
public class Dashboard extends JFrame {
    private PharmacyController controller;
    private JPanel contentPanel;
    private List<Medicine> selectedMedicines = new ArrayList<>();
    private double totalPrice = 0;
    private  JComboBox<Client> clientCombo;
    private  JComboBox<Doctor> doctorCombo;
    private Label nameField;

    /**
     * Constructeur initialisant le tableau de bord avec le contrôleur.
     *
     * @param controller Le contrôleur de la pharmacie.
     */
    public Dashboard(PharmacyController controller) {
        this.controller = controller;
        setTitle("Pharmacie Sparadrap - Dashboard");
        setSize(600, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Layout principal avec barre latérale et contenu central
        setLayout(new BorderLayout());

        /**
         * Crée et retourne la barre de navigation.
         *
         * @return Le JPanel de la barre de navigation.
         */
        // Barre de navigation (navbar)
        JPanel navbar = new JPanel();
        navbar.setLayout(new FlowLayout(FlowLayout.LEFT));
        navbar.setBackground(new Color(21, 32, 28));
        navbar.setPreferredSize(new Dimension(800, 50));
        JLabel title = new JLabel("Dashboard Pharmacie Sparadrap");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 16));
        navbar.add(title);
        add(navbar, BorderLayout.NORTH);

        /**
         * Crée et retourne la barre latérale.
         *
         * @return Le JPanel de la barre latérale.
         */
        // Sidebar avec boutons
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new GridLayout(6, 1));
        sidebar.setBackground(new Color(242, 242, 242));
        sidebar.setPreferredSize(new Dimension(200, 600));

        /**
         * Crée un bouton pour la barre latérale.
         *
         * @param text Le texte du bouton.
         * @return Le JButton configuré.
         */
        // Boutons de la sidebar
        JButton btnPurchase = new JButton("Effectuer un achat");
        btnPurchase.setBackground(new Color(32, 119, 18));
        btnPurchase.setForeground(Color.WHITE);

        JButton btnHistory = new JButton("Historique des achats");
        btnHistory.setBackground(new Color(32, 119, 18));
        btnHistory.setForeground(Color.WHITE);

        JButton btnClients = new JButton("Consulter clients");
        btnClients.setBackground(new Color(32, 119, 18));
        btnClients.setForeground(Color.WHITE);

        JButton btnDoctors = new JButton("Consulter médecins");
        btnDoctors.setBackground(new Color(32, 119, 18));
        btnDoctors.setForeground(Color.WHITE);

        JButton btnQuit = new JButton("Quitter");
        btnQuit.setBackground(new Color(0, 0, 0));
        btnQuit.setForeground(Color.WHITE);

        // Ajout des boutons à la sidebar
        sidebar.add(btnPurchase);
        sidebar.add(btnHistory);
        sidebar.add(btnClients);
        sidebar.add(btnDoctors);
        sidebar.add(btnQuit);

        btnHistory.addActionListener(e -> openHistoryPage());
        btnClients.addActionListener(e -> openClientPage());
        btnDoctors.addActionListener(e -> openDoctorPage());

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
                Medicine selectedMedicineTemp =  (Medicine) medicineCombo.getSelectedItem();
                Medicine selectedMedicine = selectedMedicineTemp.clone();
                if (selectedMedicine != null) {
                    selectedMedicine.setQuantity(quantity);
                    double medicineTotalPrice = selectedMedicine.calculateTotalPrice();
                    totalPrice += medicineTotalPrice;
                    selectedMedicines.add(selectedMedicine);
                    addedMedicinesArea.append(selectedMedicine.getName() + " x " + quantity + " : "
                            + medicineTotalPrice + " €\n");
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
            String selectedType = (String) typeAchatCombo.getSelectedItem();
            if (selectedType.equals("Achat direct")) {
                // Handle Achat direct
                try {
                    controller.addPurchase(new Purchase(selectedMedicines));
                    totalPrice = 0;
                    selectedMedicines.clear();
                    addedMedicinesArea.setText("");  // Clear the added medicines area
                    totalValueLabel.setText("0.00 €");  // Reset total price
                    quantityField.setText("");  // Clear the quantity field
                    JOptionPane.showMessageDialog(null, "Achat validé avec succès !");
                } catch (SystemeException ex) {
                    throw new RuntimeException(ex);
                }
            } else if (selectedType.equals("Achat avec ordonnance")) {
                // Handle Achat avec ordonnance
                try {
                    Client client = (Client) clientCombo.getSelectedItem();
                    Doctor doctor = (Doctor) doctorCombo.getSelectedItem();
                    Purchase purchase = new Purchase(new Ordonnance(LocalDate.now(), doctor, client, selectedMedicines, null));
                    controller.addPurchase(purchase);
                    totalPrice = 0;
                    selectedMedicines.clear();
                    addedMedicinesArea.setText("");
                    totalValueLabel.setText("0.00 €");
                    quantityField.setText("");
                    JOptionPane.showMessageDialog(null, "Achat validé avec succès !");
                } catch (SystemeException ex) {
                    throw new RuntimeException(ex);
                }
            }
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

        // Nom du client
        JLabel clientNameLabel = new JLabel("Nom du client :");
        gbc.gridx = 0;
        gbc.gridy = 9;
        panel.add(clientNameLabel, gbc);

        clientCombo = new JComboBox<>();
        List<Client> clients = controller.getClients();
        for (Client client : clients) {
            clientCombo.addItem(client);
        }
        gbc.gridx = 1;
        gbc.gridy = 9;
        panel.add(clientCombo, gbc);

        // Médecin traitant
        JLabel doctorLabel = new JLabel("Médecin traitant :");
        gbc.gridx = 0;
        gbc.gridy = 10;
        panel.add(doctorLabel, gbc);


        doctorCombo = new JComboBox<>();
        List<Doctor> doctors = controller.getDoctors();
        for (Doctor doctor : doctors) {
            doctorCombo.addItem(doctor);
        }
        gbc.gridx = 1;
        gbc.gridy = 10;
        panel.add(doctorCombo, gbc);

        // Rafraîchir le panneau
        panel.revalidate();
        panel.repaint();
    }

    /**
     * Ouvre la vue d'historique des achats.
     */
    public void openHistoryPage() {
        contentPanel.removeAll();

        JPanel historyPage = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        // Label for Purchase History
        JLabel historyLabel = new JLabel("Historique des achats");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        historyPage.add(historyLabel, gbc);

        // List to display purchases
        DefaultListModel<Purchase> purchaseListModel = new DefaultListModel<>();
        List<Purchase> purchases = controller.getPurchases();
        if (purchases != null && !purchases.isEmpty()) {
            for (Purchase purchase : purchases) {
                purchaseListModel.addElement(purchase);  // Add each purchase to the list model
            }
        } else {
            System.out.println("No purchases found.");
        }

        JList<Purchase> purchaseList = new JList<>(purchaseListModel);
        purchaseList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(purchaseList);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        historyPage.add(scrollPane, gbc);

        // TextArea to display details of selected purchase
        JTextArea detailsArea = new JTextArea(10, 30);
        detailsArea.setEditable(false);
        JScrollPane detailsScrollPane = new JScrollPane(detailsArea);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        historyPage.add(detailsScrollPane, gbc);

        // List selection listener to display purchase details
        purchaseList.addListSelectionListener(e -> {
            Purchase selectedPurchase = purchaseList.getSelectedValue();
            if (selectedPurchase != null) {
                detailsArea.setText(selectedPurchase.toString());  // Display selected purchase details
            }
        });

        // Modify Button
        JButton editButton = new JButton("Modifier");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        historyPage.add(editButton, gbc);

        editButton.addActionListener(e -> {
            Purchase selectedPurchase = purchaseList.getSelectedValue();
            if (selectedPurchase != null) {
                modifyPurchase(selectedPurchase);  // Logic to modify purchase
            } else {
                JOptionPane.showMessageDialog(null,
                        "Veuillez sélectionner un achat à modifier.");
            }
        });

        // Delete Button
        JButton deleteButton = new JButton("Supprimer");
        gbc.gridx = 1;
        gbc.gridy = 3;
        historyPage.add(deleteButton, gbc);

        deleteButton.addActionListener(e -> {
            Purchase selectedPurchase = purchaseList.getSelectedValue();
            if (selectedPurchase != null) {
                int confirm = JOptionPane.showConfirmDialog(null,
                        "Êtes-vous sûr de vouloir supprimer cet achat ?", "Confirmation",
                        JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        controller.removePurchase(selectedPurchase);
                    } catch (SystemeException ex) {
                        throw new RuntimeException(ex);
                    }
                    purchaseListModel.removeElement(selectedPurchase);
                    detailsArea.setText("");
                    JOptionPane.showMessageDialog(null,
                            "Achat supprimé avec succès.");
                }
            } else {
                JOptionPane.showMessageDialog(null,
                        "Veuillez sélectionner un achat à supprimer.");
            }
        });

        // Back Button
        JButton backButton = new JButton("Retour");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        historyPage.add(backButton, gbc);

        backButton.addActionListener(e -> {
            contentPanel.removeAll();
            contentPanel.revalidate();
            contentPanel.repaint();
        });

        contentPanel.add(historyPage);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    // Méthode pour modifier l'achat (à implémenter)
    private void modifyPurchase(Purchase purchase) {
        // Logique pour modifier l'achat (ouvrir un nouveau formulaire de modification)
        JOptionPane.showMessageDialog(null, "Fonctionnalité de modification à implémenter.");
    }

    /**
     * Ouvre la page des clients.
     */
    public void openClientPage() {
        contentPanel.removeAll();

        JPanel clientPage = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        // Label pour les détails des clients
        JLabel clientLabel = new JLabel("Détails des clients");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        clientPage.add(clientLabel, gbc);

        // Liste pour afficher les clients
        DefaultListModel<Client> clientListModel = new DefaultListModel<>();
        List<Client> clients = controller.getClients();  // Méthode pour obtenir les clients depuis le contrôleur
        for (Client client : clients) {
            clientListModel.addElement(client);
        }
        JList<Client> clientList = new JList<>(clientListModel);
        clientList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(clientList);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        clientPage.add(scrollPane, gbc);

        // Zone de texte pour afficher les détails du client sélectionné
        JTextArea detailsArea = new JTextArea(10, 30);
        detailsArea.setEditable(false);
        JScrollPane detailsScrollPane = new JScrollPane(detailsArea);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        clientPage.add(detailsScrollPane, gbc);

        // Écouteur pour afficher les détails lorsque le client est sélectionné
        clientList.addListSelectionListener(e -> {
            Client selectedClient = clientList.getSelectedValue();
            if (selectedClient != null) {
                StringBuilder detailsText = new StringBuilder();
                detailsText.append("Nom : ").append(selectedClient.getLastName())
                        .append("\nPrénom : ").append(selectedClient.getFirstName())
                        .append("\nAdresse : ").append(selectedClient.getAddress())
                        .append("\nTéléphone : ").append(selectedClient.getPhone())
                        .append("\nEmail : ").append(selectedClient.getEmail());

                detailsArea.setText(detailsText.toString());
            }
        });

        // Bouton Ajouter
        JButton addButton = new JButton("Créer");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        clientPage.add(addButton, gbc);

        DateTimeFormatter formatter = null;
        addButton.addActionListener(e -> {
            // Logique pour créer un nouveau client
            try {
                createOrModifyClient(null, clientListModel, formatter);
            } catch (SaisieException ex) {
                throw new RuntimeException(ex);
            }
        });

        // Bouton Modifier
        JButton editButton = new JButton("Modifier");
        gbc.gridx = 1;
        gbc.gridy = 3;
        clientPage.add(editButton, gbc);

        editButton.addActionListener(e -> {
            Client selectedClient = clientList.getSelectedValue();
            if (selectedClient != null) {
                // Logique pour modifier le client
                try {
                    createOrModifyClient(selectedClient, clientListModel, formatter);
                } catch (SaisieException ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                JOptionPane.showMessageDialog(null,
                        "Veuillez sélectionner un client à modifier.");
            }
        });

        // Bouton Supprimer
        JButton deleteButton = new JButton("Supprimer");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        clientPage.add(deleteButton, gbc);

        deleteButton.addActionListener(e -> {
            Client selectedClient = clientList.getSelectedValue();
            if (selectedClient != null) {
                int confirm = JOptionPane.showConfirmDialog(null,
                        "Êtes-vous sûr de vouloir supprimer ce client ?",
                        "Confirmation", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        controller.removeClient(selectedClient);  // Méthode pour supprimer le client depuis le contrôleur
                    } catch (SaisieException ex) {
                        throw new RuntimeException(ex);
                    }
                    clientListModel.removeElement(selectedClient);
                    detailsArea.setText("");
                    JOptionPane.showMessageDialog(null, "Client supprimé avec succès.");
                }
            } else {
                JOptionPane.showMessageDialog(null,
                        "Veuillez sélectionner un client à supprimer.");
            }
        });

        // Bouton Retour
        JButton backButton = new JButton("Retour");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        clientPage.add(backButton, gbc);

        backButton.addActionListener(e -> {
            contentPanel.removeAll();
            contentPanel.revalidate();
            contentPanel.repaint();
        });

        contentPanel.add(clientPage);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    // Méthode pour créer ou modifier un client
    private void createOrModifyClient(Client client, DefaultListModel<Client> clientListModel, DateTimeFormatter formatter) throws SaisieException {
        JTextField lastNameField = new JTextField(20);
        JTextField firstNameField = new JTextField(20);
        JTextField addressField = new JTextField(20);
        JTextField cityField = new JTextField(20);
        JTextField postalCodeField = new JTextField(20);
        JTextField phoneField = new JTextField(20);
        JTextField emailField = new JTextField(20);
        JTextField socialSecurityNumberField = new JTextField(20);
        JTextField birthDateField = new JTextField(20);
        JTextField mutuelleField = new JTextField(20);
        JTextField doctorField = new JTextField(20);

        if (client != null) {
            lastNameField.setText(client.getLastName());
            firstNameField.setText(client.getFirstName());
            addressField.setText(client.getAddress());
            cityField.setText(client.getCity());
            postalCodeField.setText(client.getPostalCode());
            phoneField.setText(client.getPhone());
            emailField.setText(client.getEmail());
            socialSecurityNumberField.setText(client.getSocialSecurityNumber());
            birthDateField.setText(String.valueOf(client.getBirthDate()));
            mutuelleField.setText(String.valueOf(client.getMutuelle()));
            doctorField.setText(String.valueOf(client.getDoctor()));
        }

        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.add(new JLabel("Nom :"));
        panel.add(lastNameField);
        panel.add(new JLabel("Prénom :"));
        panel.add(firstNameField);
        panel.add(new JLabel("Adresse :"));
        panel.add(addressField);
        panel.add(new JLabel("City :"));
        panel.add(cityField);
        panel.add(new JLabel("PostalCode :"));
        panel.add(postalCodeField);
        panel.add(new JLabel("Téléphone :"));
        panel.add(phoneField);
        panel.add(new JLabel("Email :"));
        panel.add(emailField);
        panel.add(new JLabel("Social Security Number :"));
        panel.add(socialSecurityNumberField);
        panel.add(new JLabel("Birth Date :"));
        panel.add(birthDateField);
        panel.add(new JLabel("Mutuelle :"));
        panel.add(mutuelleField);
        panel.add(new JLabel("Doctor :"));
        panel.add(doctorField);

        LocalDate birthDate = LocalDate.parse(birthDateField.getText(), formatter);
        Label departmentField = new Label();
        double reimbursementRateDouble = Double.parseDouble(mutuelleField.getText());
        Mutuelle mutuelle = new Mutuelle(nameField.getText(), addressField.getText(), cityField.getText(),
                postalCodeField.getText(), phoneField.getText(), emailField.getText(), departmentField.getText(),
                reimbursementRateDouble);
        Label registrationNumber = new Label();
        Doctor doctor = new Doctor(firstNameField.getText(), lastNameField.getText(), addressField.getText(),
                cityField.getText(), postalCodeField.getText(), phoneField.getText(), emailField.getText(),
                socialSecurityNumberField.getText(), birthDate, registrationNumber.getText());


        int result = JOptionPane.showConfirmDialog(null, panel, client == null ?
                "Créer un nouveau client" : "Modifier le client", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            if (client == null) {
                client = new Client(lastNameField.getText(), firstNameField.getText(),
                        addressField.getText(), cityField.getText(), postalCodeField.getText(), phoneField.getText(),
                        emailField.getText(), socialSecurityNumberField.getText(), birthDate,
                        mutuelle, doctor);
                controller.addClient(client);  // Méthode pour ajouter le client via le contrôleur
                clientListModel.addElement(client);
                JOptionPane.showMessageDialog(null, "Client créé avec succès.");
            } else {
                client.setLastName(lastNameField.getText());
                client.setFirstName(firstNameField.getText());
                client.setAddress(addressField.getText());
                client.setPhone(phoneField.getText());
                client.setEmail(emailField.getText());
                controller.updateClient(client);  // Méthode pour mettre à jour le client via le contrôleur
                clientListModel.setElementAt(client, clientListModel.indexOf(client));
                JOptionPane.showMessageDialog(null, "Client modifié avec succès.");
            }
        }
    }

    /**
     * Ouvre la page des médecins.
     */
    public void openDoctorPage() {
        contentPanel.removeAll();

        JPanel doctorPage = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        // Label pour sélectionner un docteur
        JLabel doctorLabel = new JLabel("sélectionner un docteur");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        doctorPage.add(doctorLabel, gbc);

        JComboBox<Doctor> doctorCombo = new JComboBox<>();
        List<Doctor> doctors = controller.getDoctors();
        for (Doctor doctor : doctors) {
            doctorCombo.addItem(doctor);
        }
        gbc.gridx = 1;
        gbc.gridy = 0;
        doctorPage.add(doctorCombo, gbc);

        // Zone de texte pour afficher les détails du docteur
        JTextArea doctorDetailsArea = new JTextArea(10, 30);
        doctorDetailsArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(doctorDetailsArea);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        doctorPage.add(scrollPane, gbc);

        // Action pour afficher les détails du docteur sélectionné
        doctorCombo.addActionListener(e -> {
            Doctor selectedDoctor = (Doctor) doctorCombo.getSelectedItem();
            if (selectedDoctor != null) {
                doctorDetailsArea.setText("Nom : " + selectedDoctor.getFirstName() + " " + selectedDoctor.getLastName() + "\n" +
                        "Adresse : " + selectedDoctor.getAddress() + "\n" +
                        "Téléphone : " + selectedDoctor.getPhone() + "\n" +
                        "Email : " + selectedDoctor.getEmail() + "\n" +
                        "Numéro d'agréement : " + selectedDoctor.getRegistrationNumber());

            }
        });

        // Ajout du bouton retour
        JButton backButton = new JButton("Retour");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        doctorPage.add(backButton, gbc);
        backButton.addActionListener(e -> {
            contentPanel.removeAll();
            contentPanel.revalidate();
            contentPanel.repaint();
        });

        contentPanel.add(doctorPage);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

//    public static void main(String[] args) {
//        Pharmacy pharmacy = new Pharmacy();
//        PharmacyController controller = new PharmacyController(pharmacy);
//        Dashboard dashboard = new Dashboard(controller);
//        dashboard.setVisible(true);
//    }
}


