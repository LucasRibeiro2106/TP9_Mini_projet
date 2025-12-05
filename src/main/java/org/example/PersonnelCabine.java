package org.example;

import java.util.ArrayList;
import java.util.List;

public class PersonnelCabine extends Employe {
    private String qualification;
    private List<Vol> volsAffectes;

    public PersonnelCabine(String identifiant, String nom, String adresse, String contact,
                           String numeroEmploye, String dateEmbauche, String qualification) {
        super(identifiant, nom, adresse, contact, numeroEmploye, dateEmbauche);
        this.qualification = qualification;
        this.volsAffectes = new ArrayList<>();
    }

    @Override
    public String obtenirInfos() {
        return super.obtenirInfos() + ", Qualification: " + qualification;
    }

    @Override
    public String obtenirRole() {
        return "Personnel de Cabine";
    }

    public void affecterVol(Vol vol) {
        if (!volsAffectes.contains(vol)) {
            volsAffectes.add(vol);
        }
    }

    public List<Vol> obtenirVols() {
        return new ArrayList<>(volsAffectes);
    }

    // CRUD
    public void modifierPersonnelCabine(String qualification) {
        this.qualification = qualification;
    }

    // Getters / setters
    public String getQualification() { return qualification; }
    public void setQualification(String qualification) { this.qualification = qualification; }
}
