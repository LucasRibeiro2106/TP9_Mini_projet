package org.example;

public class Reservation {
    private String numeroReservation;
    private String dateReservation;
    private String statut; // "confirmée", "annulée", "en attente"
    private Passager passager;
    private Vol vol;

    public Reservation(String numeroReservation, String dateReservation, Passager passager, Vol vol) {
        this.numeroReservation = numeroReservation;
        this.dateReservation = dateReservation;
        this.passager = passager;
        this.vol = vol;
        this.statut = "confirmée";
    }

    public void confirmerReservation() {
        this.statut = "confirmée";
    }

    public void annulerReservation() {
        this.statut = "annulée";
        if (vol != null) {
            vol.supprimerReservation(this);
        }
    }

    public void modifierReservation(Vol nouveauVol) {
        if (vol != null) {
            vol.supprimerReservation(this);
        }
        this.vol = nouveauVol;
        if (nouveauVol != null) {
            nouveauVol.ajouterReservation(this);
        }
    }

    public String obtenirInfosReservation() {
        return "Réservation " + numeroReservation +
                ", Date: " + dateReservation +
                ", Statut: " + statut +
                ", Passager: " + (passager != null ? passager.getNom() : "N/A") +
                ", Vol: " + (vol != null ? vol.getNumeroVol() : "N/A");
    }

    // CRUD
    public void modifierReservationDetails(String dateReservation, String statut) {
        this.dateReservation = dateReservation;
        this.statut = statut;
    }

    // Getters / setters
    public String getNumeroReservation() { return numeroReservation; }
    public void setNumeroReservation(String numeroReservation) { this.numeroReservation = numeroReservation; }

    public String getDateReservation() { return dateReservation; }
    public void setDateReservation(String dateReservation) { this.dateReservation = dateReservation; }

    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }

    public Passager getPassager() { return passager; }
    public void setPassager(Passager passager) { this.passager = passager; }

    public Vol getVol() { return vol; }
    public void setVol(Vol vol) { this.vol = vol; }
}
