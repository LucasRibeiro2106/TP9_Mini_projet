package org.example;

import java.util.ArrayList;
import java.util.List;

public class Aeroport {
    private String nom;
    private String ville;
    private String description;
    private List<Vol> vols;

    public Aeroport(String nom, String ville, String description) {
        this.nom = nom;
        this.ville = ville;
        this.description = description;
        this.vols = new ArrayList<>();
    }

    public void affecterVol(Vol vol) {
        if (!vols.contains(vol)) {
            vols.add(vol);
        }
    }

    // CRUD
    public void modifierAeroport(String ville, String description) {
        this.ville = ville;
        this.description = description;
    }

    // Getters / setters
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getVille() { return ville; }
    public void setVille(String ville) { this.ville = ville; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public List<Vol> getVols() { return new ArrayList<>(vols); }
}
