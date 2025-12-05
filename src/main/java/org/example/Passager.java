package org.example;

import java.util.ArrayList;
import java.util.List;

public class Passager extends Personne {
    private String passeport;
    private List<Reservation> reservations;

    public Passager(String identifiant, String nom, String adresse, String contact, String passeport) {
        super(identifiant, nom, adresse, contact);
        this.passeport = passeport;
        this.reservations = new ArrayList<>();
    }

    public Reservation reserverVol(Vol vol, String dateReservation) {
        Reservation reservation = new Reservation(
                "RES" + System.currentTimeMillis(),
                dateReservation,
                this,
                vol
        );
        reservations.add(reservation);
        vol.ajouterReservation(reservation);
        return reservation;
    }

    public boolean annulerReservation(String numeroReservation) {
        for (int i = 0; i < reservations.size(); i++) {
            Reservation res = reservations.get(i);
            if (res.getNumeroReservation().equals(numeroReservation)) {
                res.annulerReservation();
                reservations.remove(i);
                return true;
            }
        }
        return false;
    }

    public List<Reservation> obtenirReservations() {
        return new ArrayList<>(reservations);
    }

    // CRUD
    public void modifierPassager(String nom, String adresse, String contact, String passeport) {
        setNom(nom);
        setAdresse(adresse);
        setContact(contact);
        this.passeport = passeport;
    }

    // Getters / setters
    public String getPasseport() { return passeport; }
    public void setPasseport(String passeport) { this.passeport = passeport; }
}
