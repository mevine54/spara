# Pharmacie Sparadrap - Gestion de Pharmacie

## Description

Le projet **Pharmacie Sparadrap** est une application de gestion de pharmacie développée en Java, utilisant le modèle MVC (Modèle-Vue-Contrôleur) avec une interface utilisateur basée sur **Swing**. Elle permet de gérer les achats de médicaments par des clients, soit via un achat direct, soit via une ordonnance. L'application offre une interface graphique intuitive.

## Fonctionnalités

### Page d'accueil
- **Effectuer un achat** : L'utilisateur peut choisir d'effectuer un achat direct ou un achat via ordonnance. Les informations relatives au client, aux médicaments et au prix total sont automatiquement affichées et calculées.
- **Consulter l'historique des achats** : Permet à l'utilisateur de consulter tous les achats effectués avec des détails tels que le client, les médicaments achetés et le prix total.
- **Consulter un client** : Affiche la liste des clients enregistrés dans la base de données avec leurs informations détaillées (nom, adresse, mutuelle, etc.).
- **Consulter un médecin** : Permet de voir les détails des médecins et spécialistes (nom, spécialité, etc.) enregistrés dans la pharmacie.

### Page d'achat
- **Achat direct** : L'utilisateur peut sélectionner un ou plusieurs médicaments dans une liste déroulante, entrer la quantité souhaitée et le prix total est automatiquement calculé.
- **Achat avec ordonnance** : En plus de choisir des médicaments, l'utilisateur peut saisir des informations détaillées sur le client (nom, adresse, numéro de sécurité sociale, mutuelle, médecin traitant, etc.).
- **Gestion des médicaments** : Permet d'ajouter des médicaments et de gérer leur stock.
  
### Gestion des données
- **Clients** : Ajout, consultation, modification et suppression de clients.
- **Médicaments** : Consultation et gestion des médicaments disponibles dans la pharmacie.
- **Médecins** : Consultation des médecins et spécialistes enregistrés.

## Technologies utilisées

- **Java** : Langage principal utilisé pour le développement.
- **Swing** : Bibliothèque utilisée pour créer l'interface graphique de l'application.
- **MVC (Modèle-Vue-Contrôleur)** : Architecture de conception utilisée pour organiser le code en trois parties distinctes : modèle (données), vue (interface utilisateur), et contrôleur (logique d'application).
- **JUnit** : Framework utilisé pour les tests unitaires des fonctionnalités de l'application.

## Installation et utilisation

### Prérequis
- Java 8 ou supérieur doit être installé sur votre machine.
