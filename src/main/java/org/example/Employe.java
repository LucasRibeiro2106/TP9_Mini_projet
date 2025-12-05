package org.example;

public class Employe extends Personne implements IEmploye {
    private String numeroEmploye;
    private String dateEmbauche;

    public Employe(String identifiant, String nom, String adresse, String contact,
                   String numeroEmploye, String dateEmbauche) {
        super(identifiant, nom, adresse, contact);
        this.numeroEmploye = numeroEmploye;
        this.dateEmbauche = dateEmbauche;
    }

    @Override
    public String obtenirInfos() {
        return super.obtenirInfos()
                + ", Numéro Employé: " + numeroEmploye
                + ", Date Embauche: " + dateEmbauche;
    }

    @Override
    public String obtenirRole() {
        return "Employé";
    }

    // CRUD
    public void modifierEmploye(String nom, String adresse, String contact) {
        setNom(nom);
        setAdresse(adresse);
        setContact(contact);
    }

    // Getters / setters
    public String getNumeroEmploye() { return numeroEmploye; }
    public void setNumeroEmploye(String numeroEmploye) { this.numeroEmploye = numeroEmploye; }

    public String getDateEmbauche() { return dateEmbauche; }
    public void setDateEmbauche(String dateEmbauche) { this.dateEmbauche = dateEmbauche; }
}
