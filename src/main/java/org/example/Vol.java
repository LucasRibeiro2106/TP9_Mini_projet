package org.example;

import java.util.ArrayList;
import java.util.List;

public class Vol {
    private String numeroVol;
    private String origine;
    private String destination;
    private String dateHeureDepart;
    private String dateHeureArrivee;
    private String etat; // "planifié", "en cours", "terminé", "annulé"
    private Pilote pilote;
    private List<PersonnelCabine> equipeCabine;
    private Avion avion;
    private List<Reservation> reservations;
    private Aeroport aeroportDepart;
    private Aeroport aeroportArrivee;

    public Vol(String numeroVol, String origine, String destination,
               String dateHeureDepart, String dateHeureArrivee) {
        this.numeroVol = numeroVol;
        this.origine = origine;
        this.destination = destination;
        this.dateHeureDepart = dateHeureDepart;
        this.dateHeureArrivee = dateHeureArrivee;
        this.etat = "planifié";
        this.equipeCabine = new ArrayList<>();
        this.reservations = new ArrayList<>();
    }

    public void planifierVol() {
        this.etat = "planifié";
    }

    public void annulerVol() {
        this.etat = "annulé";
        for (Reservation res : reservations) {
            res.annulerReservation();
        }
        reservations.clear();
    }

    public void modifierVol(String nouvelleDestination, String nouvelleDateDepart, String nouvelleDateArrivee) {
        this.destination = nouvelleDestination;
        this.dateHeureDepart = nouvelleDateDepart;
        this.dateHeureArrivee = nouvelleDateArrivee;
    }

    public List<Passager> ListingPassager() {
        List<Passager> passagers = new ArrayList<>();
        for (Reservation res : reservations) {
            if ("confirmée".equals(res.getStatut())) {
                passagers.add(res.getPassager());
            }
        }
        return passagers;
    }

    public void affecterEquipage(Pilote pilote, List<PersonnelCabine> equipeCabine) {
        this.pilote = pilote;
        this.equipeCabine = new ArrayList<>(equipeCabine);
    }

    public boolean affecterAvion(Avion avion) {
        if (avion.verifierDisponibilite(dateHeureDepart)) {
            this.avion = avion;
            avion.affecterVol(this);
            return true;
        }
        return false;
    }

    public String obtenirInfosVol() {
        return "Vol " + numeroVol + ": " + origine + " -> " + destination +
                ", Départ: " + dateHeureDepart + ", Arrivée: " + dateHeureArrivee +
                ", État: " + etat + ", Avion: " +
                (avion != null ? avion.getImmatriculation() : "Non affecté");
    }

    public void ajouterReservation(Reservation reservation) {
        if (!reservations.contains(reservation)) {
            reservations.add(reservation);
        }
    }

    public void supprimerReservation(Reservation reservation) {
        reservations.remove(reservation);
    }

    // CRUD
    public void modifierVolDetails(String origine, String destination, String etat) {
        this.origine = origine;
        this.destination = destination;
        this.etat = etat;
    }

    // Getters / setters
    public String getNumeroVol() { return numeroVol; }
    public void setNumeroVol(String numeroVol) { this.numeroVol = numeroVol; }

    public String getOrigine() { return origine; }
    public void setOrigine(String origine) { this.origine = origine; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public String getDateHeureDepart() { return dateHeureDepart; }
    public void setDateHeureDepart(String dateHeureDepart) { this.dateHeureDepart = dateHeureDepart; }

    public String getDateHeureArrivee() { return dateHeureArrivee; }
    public void setDateHeureArrivee(String dateHeureArrivee) { this.dateHeureArrivee = dateHeureArrivee; }

    public String getEtat() { return etat; }
    public void setEtat(String etat) { this.etat = etat; }

    public Pilote getPilote() { return pilote; }
    public void setPilote(Pilote pilote) { this.pilote = pilote; }

    public List<PersonnelCabine> getEquipeCabine() { return new ArrayList<>(equipeCabine); }

    public Avion getAvion() { return avion; }
    public void setAvion(Avion avion) { this.avion = avion; }

    public List<Reservation> getReservations() { return new ArrayList<>(reservations); }

    public Aeroport getAeroportDepart() { return aeroportDepart; }
    public void setAeroportDepart(Aeroport aeroportDepart) { this.aeroportDepart = aeroportDepart; }

    public Aeroport getAeroportArrivee() { return aeroportArrivee; }
    public void setAeroportArrivee(Aeroport aeroportArrivee) { this.aeroportArrivee = aeroportArrivee; }
}
