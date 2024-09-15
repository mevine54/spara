package mu.pepe.bitor.views;

import mu.pepe.bitor.controllers.PharmacyController;
import mu.pepe.bitor.models.Purchase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class HistoryPage extends JFrame {

    private PharmacyController controller;

    public HistoryPage(PharmacyController controller) {
        this.controller = controller;
        setTitle("Pharmacie Sparadrap - Historique des Achats");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));

        // Zone pour afficher l'historique des achats
        JTextArea historyArea = new JTextArea(10, 30);
        historyArea.setEditable(false);
        panel.add(new JScrollPane(historyArea));

        // Bouton pour afficher l'historique des achats
        JButton btnAfficher = new JButton("Afficher l'historique");
        btnAfficher.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<Purchase> purchases = controller.getPurchases();
                StringBuilder historyText = new StringBuilder();
                for (Purchase purchase : purchases) {
                    historyText.append("Client : ").append(purchase.getClient().getFirstName()).append(" ").append(purchase.getClient().getLastName())
                            .append("\nMédicament : ").append(purchase.getMedicine().getName())
                            .append("\nDate : ").append(purchase.getDate())
                            .append("\nPrix : ").append(purchase.getTotalPrice()).append("\n\n");
                }
                historyArea.setText(historyText.toString());
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
