package org.example;

import java.util.ArrayList;
import java.util.List;

public class SEANCE1 {
    public static void main(String[] args) {

        System.out.println("=== SYSTÈME DE COMPAGNIE AÉRIENNE ===\n");

        // Aéroports
        Aeroport aeroportParis = new Aeroport("CDG", "Paris", "Aéroport Charles de Gaulle");
        Aeroport aeroportNewYork = new Aeroport("JFK", "New York", "John F. Kennedy International Airport");

        // Avion
        Avion avion1 = new Avion("F-GABC", "Airbus A320", 180);

        // Employés
        Pilote pilote1 = new Pilote(
                "P001", "Jean Dupont", "Paris", "0102030405",
                "EMP001", "2020-01-15", "LIC123", 5000
        );

        PersonnelCabine pnc1 = new PersonnelCabine(
                "P002", "Marie Curie", "Lyon", "0607080910",
                "EMP002", "2021-03-20", "Hôtesse principale"
        );

        PersonnelCabine pnc2 = new PersonnelCabine(
                "P003", "Pierre Durand", "Nice", "0611223344",
                "EMP003", "2022-06-10", "Steward"
        );

        // Passagers
        Passager passager1 = new Passager("PA001", "Paul Martin", "Marseille", "0612345678", "AB123456");
        Passager passager2 = new Passager("PA002", "Sophie Bernard", "Lille", "0698765432", "CD789012");

        // Vols
        Vol vol1 = new Vol("AF123", "Paris", "New York", "2024-12-05 10:00", "2024-12-05 13:00");
        Vol vol2 = new Vol("AF456", "Paris", "Tokyo", "2024-12-06 14:00", "2024-12-06 22:00");

        // Affectation des aéroports
        aeroportParis.affecterVol(vol1);
        aeroportNewYork.affecterVol(vol1);

        // Équipage du vol1
        List<PersonnelCabine> equipe1 = new ArrayList<>();
        equipe1.add(pnc1);
        equipe1.add(pnc2);
        vol1.affecterEquipage(pilote1, equipe1);

        // Affectation de l'avion au vol1
        boolean avionAffecte = vol1.affecterAvion(avion1);
        System.out.println("Avion affecté au vol AF123: " + (avionAffecte ? "OUI" : "NON"));

        // Réservations
        System.out.println("\n=== RÉSERVATIONS ===");
        Reservation res1 = passager1.reserverVol(vol1, "2024-12-01");
        Reservation res2 = passager2.reserverVol(vol1, "2024-12-02");

        System.out.println("Réservation 1: " + res1.obtenirInfosReservation());
        System.out.println("Réservation 2: " + res2.obtenirInfosReservation());

        // Infos vol
        System.out.println("\n=== INFORMATIONS DU VOL ===");
        System.out.println(vol1.obtenirInfosVol());

        // Infos personnes
        System.out.println("\n=== INFORMATIONS DES PERSONNES ===");
        System.out.println("Pilote: " + pilote1.obtenirInfos());
        System.out.println("Passager 1: " + passager1.obtenirInfos());

        // Liste des passagers
        System.out.println("\n=== LISTE DES PASSAGERS DU VOL AF123 ===");
        List<Passager> passagersVol1 = vol1.ListingPassager();
        for (Passager p : passagersVol1) {
            System.out.println("  - " + p.getNom());
        }

        // Annulation réservation
        System.out.println("\n=== TEST D'ANNULATION ===");
        boolean annulation = passager1.annulerReservation(res1.getNumeroReservation());
        System.out.println("Annulation de la réservation " + res1.getNumeroReservation() +
                ": " + (annulation ? "RÉUSSIE" : "ÉCHOUÉE"));

        // Rôles
        System.out.println("\n=== RÔLES DES EMPLOYÉS ===");
        System.out.println("Rôle de " + pilote1.getNom() + ": " + pilote1.obtenirRole());
        System.out.println("Rôle de " + pnc1.getNom() + ": " + pnc1.obtenirRole());

        // Statistiques
        System.out.println("\n=== STATISTIQUES ===");
        GestionnaireCompagnie gestionnaire = new GestionnaireCompagnie();
        gestionnaire.ajouterVol(vol1);
        gestionnaire.ajouterVol(vol2);
        gestionnaire.ajouterPassager(passager1);
        gestionnaire.ajouterPassager(passager2);
        gestionnaire.ajouterAvion(avion1);

        System.out.println(gestionnaire.genererRapportVols());

        // Disponibilité avion
        System.out.println("\n=== VÉRIFICATION DISPONIBILITÉ AVION ===");
        boolean disponible = avion1.verifierDisponibilite("2024-12-05 10:00");
        System.out.println("Avion disponible le 2024-12-05 10:00: " + (disponible ? "OUI" : "NON"));

        System.out.println("\n=== SYSTÈME PRÊT À FONCTIONNER ===");
    }
}
