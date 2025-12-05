package org.example;

import java.util.ArrayList;
import java.util.List;

public class Avion {
    private String immatriculation;
    private String modele;
    private int capacite;
    private List<Vol> volsAffectes;

    public Avion(String immatriculation, String modele, int capacite) {
        this.immatriculation = immatriculation;
        this.modele = modele;
        this.capacite = capacite;
        this.volsAffectes = new ArrayList<>();
    }

    public boolean affecterVol(Vol vol) {
        if (verifierDisponibilite(vol.getDateHeureDepart())) {
            volsAffectes.add(vol);
            return true;
        }
        return false;
    }

    public boolean verifierDisponibilite(String dateHeure) {
        for (Vol vol : volsAffectes) {
            if (vol.getDateHeureDepart().equals(dateHeure)) {
                return false;
            }
        }
        return true;
    }

    // CRUD
    public void modifierAvion(String modele, int capacite) {
        this.modele = modele;
        this.capacite = capacite;
    }

    // Getters / setters
    public String getImmatriculation() { return immatriculation; }
    public void setImmatriculation(String immatriculation) { this.immatriculation = immatriculation; }

    public String getModele() { return modele; }
    public void setModele(String modele) { this.modele = modele; }

    public int getCapacite() { return capacite; }
    public void setCapacite(int capacite) { this.capacite = capacite; }

    public List<Vol> getVolsAffectes() { return new ArrayList<>(volsAffectes); }
}
