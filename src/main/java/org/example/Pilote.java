package org.example;

public class Pilote extends Employe {
    private String licence;
    private int heuresDeVol;

    public Pilote(String identifiant, String nom, String adresse, String contact,
                  String numeroEmploye, String dateEmbauche, String licence, int heuresDeVol) {
        super(identifiant, nom, adresse, contact, numeroEmploye, dateEmbauche);
        this.licence = licence;
        this.heuresDeVol = heuresDeVol;
    }

    @Override
    public String obtenirInfos() {
        return super.obtenirInfos()
                + ", Licence: " + licence
                + ", Heures de vol: " + heuresDeVol;
    }

    @Override
    public String obtenirRole() {
        return "Pilote";
    }

    // CRUD
    public void modifierPilote(String licence, int heuresDeVol) {
        this.licence = licence;
        this.heuresDeVol = heuresDeVol;
    }

    // Getters / setters
    public String getLicence() { return licence; }
    public void setLicence(String licence) { this.licence = licence; }

    public int getHeuresDeVol() { return heuresDeVol; }
    public void setHeuresDeVol(int heuresDeVol) { this.heuresDeVol = heuresDeVol; }
}
