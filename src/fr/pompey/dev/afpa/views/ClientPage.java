package fr.pompey.dev.afpa.views;


import fr.pompey.dev.afpa.controllers.PharmacyController;
import fr.pompey.dev.afpa.models.Client;
import fr.pompey.dev.afpa.models.Doctor;
import fr.pompey.dev.afpa.models.Mutuelle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;
import java.util.Date;

public class ClientPage extends JFrame {

    private PharmacyController controller;
    private JComboBox<Client> comboClient;
    private JTextArea detailsArea;

    public ClientPage(PharmacyController controller) {
        this.controller = controller;
        setTitle("Pharmacie Sparadrap - Gestion Clients");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Zone pour afficher les clients
        JPanel clientPanel = new JPanel(new GridLayout(2, 2));

        // ComboBox pour sélectionner un client
        comboClient = new JComboBox<>();
        updateClientList();
        clientPanel.add(new JLabel("Sélectionner un client :"));
        clientPanel.add(comboClient);

        // Zone pour afficher les détails du client
        detailsArea = new JTextArea(10, 30);
        detailsArea.setEditable(false);
        clientPanel.add(new JLabel("Détails du client :"));
        clientPanel.add(new JScrollPane(detailsArea));

        // Ajouter le panneau des clients à la fenêtre
        panel.add(clientPanel, BorderLayout.CENTER);

        // Boutons pour les actions sur les clients
        JPanel buttonPanel = new JPanel(new GridLayout(1, 4));
        JButton btnCreate = new JButton("Créer");
        JButton btnAfficher = new JButton("Afficher");
        JButton btnModifier = new JButton("Modifier");
        JButton btnSupprimer = new JButton("Supprimer");

        buttonPanel.add(btnCreate);
        buttonPanel.add(btnAfficher);
        buttonPanel.add(btnModifier);
        buttonPanel.add(btnSupprimer);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Ajouter un bouton retour
        JButton btnRetour = new JButton("Retour");
        panel.add(btnRetour, BorderLayout.NORTH);

        // Action pour créer un client
        btnCreate.addActionListener(e -> {
            createClient();
        });

        // Action pour afficher les détails du client sélectionné
        btnAfficher.addActionListener(e -> {
            displayClientDetails();
        });

        // Action pour modifier un client
        btnModifier.addActionListener(e -> {
            modifyClient();
        });

        // Action pour supprimer un client
        btnSupprimer.addActionListener(e -> {
            removeClient();
        });

        // Action pour retourner à la page d'accueil
        btnRetour.addActionListener(e -> {
            Dashboard dashboard = new Dashboard(controller);
            dashboard.setVisible(true);
            dispose();  // Fermer la page actuelle
        });

        add(panel);
    }

    private void updateClientList() {
        comboClient.removeAllItems();
        List<Client> clients = controller.getClients();
        for (Client client : clients) {
            comboClient.addItem(client);
        }
    }

    private void createClient() {
        // Ouvrir une boîte de dialogue pour entrer les informations du client
        JTextField firstNameField = new JTextField();
        JTextField lastNameField = new JTextField();
        JTextField addressField = new JTextField();
        JTextField cityField = new JTextField();
        JTextField postalCodeField = new JTextField();
        JTextField phoneField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField ssnField = new JTextField();
        JTextField birthDateField = new JTextField();
        JComboBox<Mutuelle> mutuelleCombo = new JComboBox<>();
        JComboBox<Doctor> doctorCombo = new JComboBox<>();

        Object[] fields = {
                "Prénom:", firstNameField,
                "Nom:", lastNameField,
                "Adresse:", addressField,
                "City:", cityField,
                "PostalCode:", postalCodeField,
                "Téléphone:", phoneField,
                "Email:", emailField,
                "Numéro de sécurité sociale:", ssnField,
                "Date de naissance:", birthDateField,
                "Mutuelle:", mutuelleCombo,
                "Médecin traitant:", doctorCombo
        };

        int option = JOptionPane.showConfirmDialog(null, fields, "Créer un nouveau client", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            Mutuelle selectedMutuelle = (Mutuelle) mutuelleCombo.getSelectedItem();
            Doctor selectedDoctor = (Doctor) doctorCombo.getSelectedItem();

            Client newClient = new Client(firstNameField.getText(), lastNameField.getText(), addressField.getText(), cityField.getText(), postalCodeField.getText(),
                    phoneField.getText(), emailField.getText(), ssnField.getText(), LocalDate.parse(birthDateField.getText()), selectedMutuelle, selectedDoctor );
            controller.addClient(newClient);
            updateClientList();
            JOptionPane.showMessageDialog(this, "Client créé avec succès !");
        }
    }

    private void displayClientDetails() {
        Client selectedClient = (Client) comboClient.getSelectedItem();
        if (selectedClient != null) {
            detailsArea.setText("Nom : " + selectedClient.getFirstName() + " " + selectedClient.getLastName() + "\n" +
                    "Adresse : " + selectedClient.getAddress() + "\n" +
                    "Téléphone : " + selectedClient.getPhone() + "\n" +
                    "Email : " + selectedClient.getEmail() + "\n" +
                    "Numéro de sécurité sociale : " + selectedClient.getSocialSecurityNumber());
        }
    }

    private void modifyClient() {
        Client selectedClient = (Client) comboClient.getSelectedItem();
        if (selectedClient != null) {
            JTextField firstNameField = new JTextField(selectedClient.getFirstName());
            JTextField lastNameField = new JTextField(selectedClient.getLastName());
            JTextField addressField = new JTextField(selectedClient.getAddress());
            JTextField phoneField = new JTextField(selectedClient.getPhone());
            JTextField emailField = new JTextField(selectedClient.getEmail());
            JTextField ssnField = new JTextField(selectedClient.getSocialSecurityNumber());

            Object[] fields = {
                    "Prénom:", firstNameField,
                    "Nom:", lastNameField,
                    "Adresse:", addressField,
                    "Téléphone:", phoneField,
                    "Email:", emailField,
                    "Numéro de sécurité sociale:", ssnField
            };

            int option = JOptionPane.showConfirmDialog(null, fields, "Modifier le client", JOptionPane.OK_CANCEL_OPTION);

            if (option == JOptionPane.OK_OPTION) {
                selectedClient.setFirstName(firstNameField.getText());
                selectedClient.setLastName(lastNameField.getText());
                selectedClient.setAddress(addressField.getText());
                selectedClient.setPhone(phoneField.getText());
                selectedClient.setEmail(emailField.getText());
                selectedClient.setSocialSecurityNumber(ssnField.getText());

                // Mettre à jour les informations du client dans la liste
                controller.updateClient(selectedClient);
                updateClientList();  // Rafraîchir la liste des clients
                JOptionPane.showMessageDialog(this, "Client modifié avec succès !");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un client à modifier.");
        }
    }

    private void removeClient() {
        Client selectedClient = (Client) comboClient.getSelectedItem();
        if (selectedClient != null) {
            int option = JOptionPane.showConfirmDialog(this, "Êtes-vous sûr de vouloir supprimer ce client ?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                controller.deleteClient(selectedClient);
                updateClientList();  // Rafraîchir la liste des clients
                detailsArea.setText("");  // Vider les détails du client
                JOptionPane.showMessageDialog(this, "Client supprimé avec succès !");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un client à supprimer.");
        }
    }
}

