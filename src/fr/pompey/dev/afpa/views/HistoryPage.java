package fr.pompey.dev.afpa.views;

import fr.pompey.dev.afpa.controllers.PharmacyController;
import fr.pompey.dev.afpa.models.Doctor;
import fr.pompey.dev.afpa.models.Medicine;
import fr.pompey.dev.afpa.models.Purchase;

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
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Zone pour afficher l'historique des achats
        JTextArea historyArea = new JTextArea();
        historyArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(historyArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Bouton pour afficher l'historique des achats
        JButton btnAfficher = new JButton("Afficher l'historique");
        btnAfficher.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<Purchase> purchases = controller.getPurchases();
                StringBuilder historyText = new StringBuilder();
                for (Purchase purchase : purchases) {

                    String medicamentsStr = "";

                    for (Medicine medicine : purchase.getMedicines()) {
                        medicamentsStr += medicine.getName() + " - Quantité: " + medicine.getQuantity() +", ";
                    }


                    if(purchase.getOrdonnance()!= null){
                        historyText.append("Client : ").append(purchase.getOrdonnance().getClient().getFirstName()).append(" ").append(purchase.getOrdonnance().getClient().getLastName())
                                .append("\nMédicament : ").append(medicamentsStr)
                                .append("\nDate : ").append(purchase.getDate())
                                .append("\nPrix : ").append(purchase.getTotalPrice()).append(" €\n--------------------\n");
                    } else{

                    }

                }
                historyArea.setText(historyText.toString());
            }
        });
        panel.add(btnAfficher, BorderLayout.SOUTH);

        // Bouton retour
        JButton btnRetour = new JButton("Retour");
        btnRetour.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Dashboard dashboard = new Dashboard(controller);
                dashboard.setVisible(true);
                dispose();  // Fermer la page actuelle
            }
        });
        panel.add(btnRetour, BorderLayout.NORTH);

        add(panel);
    }
}
