package org.example;

import java.util.ArrayList;
import java.util.List;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GestionnaireCompagnie {

    private List<Vol> vols;
    private List<Passager> passagers;
    private List<Avion> avions;

    public GestionnaireCompagnie() {
        this.vols = new ArrayList<>();
        this.passagers = new ArrayList<>();
        this.avions = new ArrayList<>();
    }

    public void importFlights(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String line;
            boolean firstLine = true;

            while ((line = reader.readLine()) != null) {

                if (firstLine) {
                    firstLine = false;
                    continue;
                }

                String[] parts = line.split(";");

                if (parts.length != 5) {
                    System.out.println("Ligne ignorée (format incorrect) : " + line);
                    continue;
                }

                String codeVol = parts[0];
                String origine = parts[1];
                String destination = parts[2];
                String dateDepart = parts[3];
                String dateArrivee = parts[4];

                Vol vol = new Vol(codeVol, origine, destination, dateDepart, dateArrivee);
                vols.add(vol);
            }

            System.out.println("Import des vols terminé. Nombre de vols importés : " + vols.size());

        } catch (IOException e) {
            System.out.println("Erreur lors de la lecture du fichier : " + e.getMessage());
        }
    }

    public void saveReservationToFile(Reservation res, String filePath) {

        File file = new File(filePath);
        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }

        boolean newFile = !file.exists();

        String numRes = res.getNumeroReservation();
        String dateRes = res.getDateReservation();
        String statut = res.getStatut();

        Passager p = res.getPassager();
        String nomPassager = (p != null) ? p.getNom() : "";

        Vol v = res.getVol();
        String codeVol = (v != null) ? v.getNumeroVol() : "";

        String ligne = numRes + ";" + dateRes + ";" + statut + ";" + nomPassager + ";" + codeVol;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {

            if (newFile) {
                writer.write("NumeroReservation;DateReservation;Statut;NomPassager;CodeVol");
                writer.newLine();
            }

            writer.write(ligne);
            writer.newLine();

            System.out.println("Réservation sauvegardée dans " + filePath);

        } catch (IOException e) {
            System.out.println("Erreur lors de l’écriture du fichier : " + e.getMessage());
        }
    }

    public String genererRapportVols() {
        int totalVols = vols.size();
        int passagersTransportes = 0;
        double revenusEstimes = 0.0;

        for (Vol vol : vols) {
            if ("terminé".equals(vol.getEtat())) {
                passagersTransportes += vol.getReservations().size();
                revenusEstimes += vol.getReservations().size() * 300.0;
            }
        }

        return "Rapport Statistiques:\n" +
                "Nombre total de vols: " + totalVols + "\n" +
                "Passagers transportés: " + passagersTransportes + "\n" +
                "Revenus estimés: " + revenusEstimes + "€\n";
    }

    public List<String> destinationsPopulaires() {
        List<String> destinations = new ArrayList<>();
        for (Vol vol : vols) {
            destinations.add(vol.getDestination());
        }
        return destinations;
    }

    public void ajouterVol(Vol vol) {
        vols.add(vol);
    }

    public void ajouterPassager(Passager passager) {
        passagers.add(passager);
    }

    public void ajouterAvion(Avion avion) {
        avions.add(avion);
    }

    public List<Vol> getVols() {
        return new ArrayList<>(vols);
    }

    public List<Passager> getPassagers() {
        return new ArrayList<>(passagers);
    }

    public List<Avion> getAvions() {
        return new ArrayList<>(avions);
    }
}
