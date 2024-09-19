package fr.pompey.dev.afpa.views;

import fr.pompey.dev.afpa.controllers.PharmacyController;
import fr.pompey.dev.afpa.models.*;
import fr.pompey.dev.afpa.models.Doctor;


import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class Dashboard extends JFrame {
    private PharmacyController controller;
    private JPanel contentPanel;
    private List<Medicine> selectedMedicines = new ArrayList<>();
    private double totalPrice = 0;
    private  JComboBox<Client> clientCombo;
    private  JComboBox<Doctor> doctorCombo;


    public Dashboard(PharmacyController controller) {
        this.controller = controller;
        setTitle("Pharmacie Sparadrap - Dashboard");
        setSize(600, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Layout principal avec barre latérale et contenu central
        setLayout(new BorderLayout());

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

        // Sidebar avec boutons
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new GridLayout(6, 1));
        sidebar.setBackground(new Color(242, 242, 242));
        sidebar.setPreferredSize(new Dimension(200, 600));

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
            // Logique pour la validation
            String selectedType = (String) typeAchatCombo.getSelectedItem();
            if (selectedType.equals("Achat direct")) {
                // Achat direct

                Client client = null;
//                Client client = new Client(
//
//                );

                Medicine selectedMedicineTemp = (Medicine) medicineCombo.getSelectedItem();
                Medicine selectedMedicine = selectedMedicineTemp.clone();
                int quantity = Integer.parseInt(quantityField.getText());
                totalPrice = Double.parseDouble(totalValueLabel.getText().replace(" €", ""));
                LocalDate date = LocalDate.now();
                Purchase purchase = new Purchase(selectedMedicines);
                controller.addPurchase(purchase);
                totalPrice = 0;
                selectedMedicines.clear();
                JOptionPane.showMessageDialog(null, "Achat validé avec succès !");
            } else if (selectedType.equals("Achat avec ordonnance")) {
                // Achat avec ordonnance
                Client client = (Client) clientCombo.getSelectedItem();
                Doctor doctor = (Doctor) doctorCombo.getSelectedItem();



                Medicine selectedMedicine = (Medicine) medicineCombo.getSelectedItem();
                int quantity = Integer.parseInt(quantityField.getText());
                totalPrice = Double.parseDouble(totalValueLabel.getText().replace(" €", ""));
                LocalDate date = LocalDate.now();
                Ordonnance ordonnance;
                if (doctor instanceof Specialist) {
                    ordonnance = new Ordonnance(LocalDate.now(),client.getDoctor(),client,selectedMedicines,(Specialist) doctor);
                }else{
                    ordonnance = new Ordonnance(LocalDate.now(),doctor,client,selectedMedicines, doctor instanceof Specialist ? (Specialist) doctor : null);
                }
                Purchase purchase = new Purchase(ordonnance);

                controller.addPurchase(purchase);
                System.out.println(purchase.getOrdonnance().getDoctor());
                System.out.println(purchase.getOrdonnance().getSpecialist());
//                totalPrice = 0;
                JOptionPane.showMessageDialog(null, "Achat validé avec succès !");
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
        // Champs spécifiques pour un achat avec ordonnance (client details)

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

    public void openHistoryPage() {
        contentPanel.removeAll();

        JPanel historyPage = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        // Label pour l'historique des achats
        JLabel historyLabel = new JLabel("Historique des achats");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        historyPage.add(historyLabel, gbc);

        // Liste pour afficher les achats
        DefaultListModel<Purchase> purchaseListModel = new DefaultListModel<>();
        List<Purchase> purchases = controller.getPurchases();
        for (Purchase purchase : purchases) {
            purchaseListModel.addElement(purchase);
        }
        JList<Purchase> purchaseList = new JList<>(purchaseListModel);
        purchaseList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(purchaseList);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        historyPage.add(scrollPane, gbc);

        // Zone de texte pour afficher les détails de l'achat sélectionné
        JTextArea detailsArea = new JTextArea(10, 30);
        detailsArea.setEditable(false);
        JScrollPane detailsScrollPane = new JScrollPane(detailsArea);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        historyPage.add(detailsScrollPane, gbc);

        // Écouteur pour afficher les détails lorsque l'achat est sélectionné
        purchaseList.addListSelectionListener(e -> {
            Purchase selectedPurchase = purchaseList.getSelectedValue();
            if (selectedPurchase != null) {
                StringBuilder detailsText = new StringBuilder();
                Client client = selectedPurchase.getOrdonnance() != null ? selectedPurchase.getOrdonnance().getClient() : null;
                Doctor doctor = selectedPurchase.getOrdonnance() != null ? selectedPurchase.getOrdonnance().getDoctor() : null;

                String medicamentsStr = "";
                for (Medicine medicine : selectedPurchase.getMedicines()) {
                    medicamentsStr += medicine.getName() + " - Quantité: " + medicine.getQuantity() + ", ";
                }

                String doctorName = (doctor != null) ? "\nDocteur : " + doctor.getLastName() + " " + doctor.getFirstName() : "";
                String clientName = (client != null) ? client.getFirstName() + " " + client.getLastName() : "Client inconnu";

                detailsText.append("Client : ").append(clientName)
                        .append(doctorName)
                        .append("\nMédicaments : ").append(medicamentsStr)
                        .append("\nTotal : ").append(selectedPurchase.getTotalPrice()).append(" €")
                        .append("\nDate : ").append(selectedPurchase.getDate()).append("\n\n");

                detailsArea.setText(detailsText.toString());
            }
        });

        // Bouton Modifier
        JButton editButton = new JButton("Modifier");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        historyPage.add(editButton, gbc);

        editButton.addActionListener(e -> {
            Purchase selectedPurchase = purchaseList.getSelectedValue();
            if (selectedPurchase != null) {
                // Logique pour modifier l'achat
                modifyPurchase(selectedPurchase);
            } else {
                JOptionPane.showMessageDialog(null, "Veuillez sélectionner un achat à modifier.");
            }
        });

        // Bouton Supprimer
        JButton deleteButton = new JButton("Supprimer");
        gbc.gridx = 1;
        gbc.gridy = 3;
        historyPage.add(deleteButton, gbc);

        deleteButton.addActionListener(e -> {
            Purchase selectedPurchase = purchaseList.getSelectedValue();
            if (selectedPurchase != null) {
                // Logique pour supprimer l'achat
                int confirm = JOptionPane.showConfirmDialog(null, "Êtes-vous sûr de vouloir supprimer cet achat ?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    controller.removePurchase(selectedPurchase);
                    purchaseListModel.removeElement(selectedPurchase);
                    detailsArea.setText("");
                    JOptionPane.showMessageDialog(null, "Achat supprimé avec succès.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Veuillez sélectionner un achat à supprimer.");
            }
        });

        // Bouton Retour
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
        // Logique pour modifier l'achat (ouvrir un nouveau formulaire de modification, etc.)
        JOptionPane.showMessageDialog(null, "Fonctionnalité de modification à implémenter.");
    }


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

        addButton.addActionListener(e -> {
            // Logique pour créer un nouveau client
            createOrModifyClient(null, clientListModel);
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
                createOrModifyClient(selectedClient, clientListModel);
            } else {
                JOptionPane.showMessageDialog(null, "Veuillez sélectionner un client à modifier.");
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
                int confirm = JOptionPane.showConfirmDialog(null, "Êtes-vous sûr de vouloir supprimer ce client ?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    controller.removeClient(selectedClient);  // Méthode pour supprimer le client depuis le contrôleur
                    clientListModel.removeElement(selectedClient);
                    detailsArea.setText("");
                    JOptionPane.showMessageDialog(null, "Client supprimé avec succès.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Veuillez sélectionner un client à supprimer.");
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
    private void createOrModifyClient(Client client, DefaultListModel<Client> clientListModel) {
        JTextField lastNameField = new JTextField(20);
        JTextField firstNameField = new JTextField(20);
        JTextField addressField = new JTextField(20);
        JTextField phoneNumberField = new JTextField(20);
        JTextField emailField = new JTextField(20);

        if (client != null) {
            lastNameField.setText(client.getLastName());
            firstNameField.setText(client.getFirstName());
            addressField.setText(client.getAddress());
            phoneNumberField.setText(client.getPhone());
            emailField.setText(client.getEmail());
        }

        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.add(new JLabel("Nom :"));
        panel.add(lastNameField);
        panel.add(new JLabel("Prénom :"));
        panel.add(firstNameField);
        panel.add(new JLabel("Adresse :"));
        panel.add(addressField);
        panel.add(new JLabel("Téléphone :"));
        panel.add(phoneNumberField);
        panel.add(new JLabel("Email :"));
        panel.add(emailField);

        int result = JOptionPane.showConfirmDialog(null, panel, client == null ? "Créer un nouveau client" : "Modifier le client", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            if (client == null) {
                client = new Client(lastNameField.getText(), firstNameField.getText(), addressField.getText(), phoneNumberField.getText(), emailField.getText());
                controller.addClient(client);  // Méthode pour ajouter le client via le contrôleur
                clientListModel.addElement(client);
                JOptionPane.showMessageDialog(null, "Client créé avec succès.");
            } else {
                client.setLastName(lastNameField.getText());
                client.setFirstName(firstNameField.getText());
                client.setAddress(addressField.getText());
                client.setPhone(phoneNumberField.getText());
                client.setEmail(emailField.getText());
                controller.updateClient(client);  // Méthode pour mettre à jour le client via le contrôleur
                clientListModel.setElementAt(client, clientListModel.indexOf(client));
                JOptionPane.showMessageDialog(null, "Client modifié avec succès.");
            }
        }
    }


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


