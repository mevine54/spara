package fr.pompey.dev.afpa.views;


import fr.pompey.dev.afpa.controllers.PharmacyController;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends JFrame {

    private PharmacyController controller;

    public HomePage(PharmacyController controller) {
        this.controller = controller;
        setTitle("Pharmacie Sparadrap - Accueil");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10));  // Ajouter de l'espace entre les boutons

        // Bouton pour effectuer un achat
        JButton btnPurchase = new JButton("Effectuer un achat");
        btnPurchase.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openPurchasePage();
            }
        });
        panel.add(btnPurchase);

        // Bouton pour consulter l'historique des achats
        JButton btnHistory = new JButton("Consulter l'historique des achats");
        btnHistory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openHistoryPage();
            }
        });
        panel.add(btnHistory);

        // Bouton pour consulter un client
        JButton btnClients = new JButton("Consulter un client");
        btnClients.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openClientPage();
            }
        });
        panel.add(btnClients);

        // Bouton pour consulter un médecin
        JButton btnDoctors = new JButton("Consulter un médecin");
        btnDoctors.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openDoctorPage();
            }
        });
        panel.add(btnDoctors);

        // Bouton pour quitter
        JButton btnQuit = new JButton("Quitter");
        btnQuit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        panel.add(btnQuit);

        add(panel);
    }

    private void openPurchasePage() {
        PurchasePage purchasePage = new PurchasePage(controller);
        purchasePage.setVisible(true);
        dispose();
    }

    private void openHistoryPage() {
        HistoryPage historyPage = new HistoryPage(controller);
        historyPage.setVisible(true);
        dispose();
    }

    private void openClientPage() {
        ClientPage clientPage = new ClientPage(controller);
        clientPage.setVisible(true);
        dispose();
    }

    private void openDoctorPage() {
        DoctorPage doctorPage = new DoctorPage(controller);
        doctorPage.setVisible(true);
        dispose();
    }
}
