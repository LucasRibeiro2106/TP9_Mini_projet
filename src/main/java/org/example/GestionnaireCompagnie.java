package org.example;

import java.util.ArrayList;
import java.util.List;

public class GestionnaireCompagnie {
    private List<Vol> vols;
    private List<Passager> passagers;
    private List<Avion> avions;

    public GestionnaireCompagnie() {
        this.vols = new ArrayList<>();
        this.passagers = new ArrayList<>();
        this.avions = new ArrayList<>();
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

    public List<Vol> getVols() { return new ArrayList<>(vols); }
    public List<Passager> getPassagers() { return new ArrayList<>(passagers); }
    public List<Avion> getAvions() { return new ArrayList<>(avions); }
}
