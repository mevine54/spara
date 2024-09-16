package fr.pompey.dev.afpa.views;


import fr.pompey.dev.afpa.controllers.PharmacyController;
import fr.pompey.dev.afpa.models.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ClientPage extends JFrame {

    private PharmacyController controller;

    public ClientPage(PharmacyController controller) {
        this.controller = controller;
        setTitle("Pharmacie Sparadrap - Détails Client");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));

        // ComboBox pour sélectionner un client
        panel.add(new JLabel("Sélectionner un client : "));
        JComboBox<Client> comboClient = new JComboBox<>();
        List<Client> clients = controller.getClients();
        for (Client client : clients) {
            comboClient.addItem(client);
        }
        panel.add(comboClient);

        // Zone pour afficher les détails du client
        JTextArea detailsArea = new JTextArea(5, 20);
        detailsArea.setEditable(false);
        panel.add(new JScrollPane(detailsArea));

        // Bouton pour afficher les détails du client
        JButton btnAfficher = new JButton("Afficher les détails");
        btnAfficher.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Client selectedClient = (Client) comboClient.getSelectedItem();
                if (selectedClient != null) {
                    detailsArea.setText("Nom : " + selectedClient.getFirstName() + " " + selectedClient.getLastName() + "\n" +
                            "Adresse : " + selectedClient.getAddress() + "\n" +
                            "Téléphone : " + selectedClient.getPhone() + "\n" +
                            "Email : " + selectedClient.getEmail() + "\n" +
                            "Mutuelle : " + selectedClient.getMutuelle().getName() + "\n" +
                            "Numéro de sécurité sociale : " + selectedClient.getSocialSecurityNumber());
                }
            }
        });
        panel.add(btnAfficher);

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

