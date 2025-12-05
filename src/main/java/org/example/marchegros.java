// ==== CLASSE ABSTRAITE Personne ====
package org.example;
import java.util.List;
import java.util.ArrayList;

abstract class Personne {
    private String identifiant;
    private String nom;
    private String adresse;
    private String contact;

    public Personne(String identifiant, String nom, String adresse, String contact) {
        this.identifiant = identifiant;
        this.nom = nom;
        this.adresse = adresse;
        this.contact = contact;
    }

    public String obtenirInfos() {
        return "ID: " + identifiant + ", Nom: " + nom + ", Adresse: " + adresse + ", Contact: " + contact;
    }

    // Getters et setters
    public String getIdentifiant() { return identifiant; }
    public void setIdentifiant(String identifiant) { this.identifiant = identifiant; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }

    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }
}

// ==== INTERFACE IEmploye ====
interface IEmploye {
    String obtenirRole();
}

// ==== CLASSE Employe (hérite de Personne et implémente IEmploye) ====
class Employe extends Personne implements IEmploye {
    private String numeroEmploye;
    private String dateEmbauche;

    public Employe(String identifiant, String nom, String adresse, String contact,
                   String numeroEmploye, String dateEmbauche) {
        super(identifiant, nom, adresse, contact);
        this.numeroEmploye = numeroEmploye;
        this.dateEmbauche = dateEmbauche;
    }

    public String obtenirInfos() {
        return super.obtenirInfos() + ", Numéro Employé: " + numeroEmploye + ", Date Embauche: " + dateEmbauche;
    }

    public String obtenirRole() {
        return "Employé";
    }

    // CRUD Méthodes
    public void modifierEmploye(String nom, String adresse, String contact) {
        setNom(nom);
        setAdresse(adresse);
        setContact(contact);
    }

    // Getters et setters
    public String getNumeroEmploye() { return numeroEmploye; }
    public void setNumeroEmploye(String numeroEmploye) { this.numeroEmploye = numeroEmploye; }

    public String getDateEmbauche() { return dateEmbauche; }
    public void setDateEmbauche(String dateEmbauche) { this.dateEmbauche = dateEmbauche; }
}

// ==== CLASSE Passager (hérite de Personne) ====
class Passager extends Personne {
    private String passeport;
    private List<Reservation> reservations;

    public Passager(String identifiant, String nom, String adresse, String contact, String passeport) {
        super(identifiant, nom, adresse, contact);
        this.passeport = passeport;
        this.reservations = new ArrayList<>();
    }

    // Méthode pour réserver un vol (reserverVol())
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

    // Méthode pour annuler une réservation (annulerReservation())
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

    // Méthode pour obtenir toutes les réservations (obtenirReservations())
    public List<Reservation> obtenirReservations() {
        return new ArrayList<>(reservations);
    }

    // CRUD Méthodes
    public void modifierPassager(String nom, String adresse, String contact, String passeport) {
        setNom(nom);
        setAdresse(adresse);
        setContact(contact);
        this.passeport = passeport;
    }

    // Getters et setters
    public String getPasseport() { return passeport; }
    public void setPasseport(String passeport) { this.passeport = passeport; }
}

// ==== CLASSE Pilote (hérite de Employe) ====
class Pilote extends Employe {
    private String licence;
    private int heuresDeVol;

    public Pilote(String identifiant, String nom, String adresse, String contact,
                  String numeroEmploye, String dateEmbauche, String licence, int heuresDeVol) {
        super(identifiant, nom, adresse, contact, numeroEmploye, dateEmbauche);
        this.licence = licence;
        this.heuresDeVol = heuresDeVol;
    }

    public String obtenirInfos() {
        return super.obtenirInfos() + ", Licence: " + licence + ", Heures de vol: " + heuresDeVol;
    }

    public String obtenirRole() {
        return "Pilote";
    }

    // CRUD Méthodes
    public void modifierPilote(String licence, int heuresDeVol) {
        this.licence = licence;
        this.heuresDeVol = heuresDeVol;
    }

    // Getters et setters
    public String getLicence() { return licence; }
    public void setLicence(String licence) { this.licence = licence; }

    public int getHeuresDeVol() { return heuresDeVol; }
    public void setHeuresDeVol(int heuresDeVol) { this.heuresDeVol = heuresDeVol; }
}

// ==== CLASSE PersonnelCabine (hérite de Employe) ====
class PersonnelCabine extends Employe {
    private String qualification;
    private List<Vol> volsAffectes;

    public PersonnelCabine(String identifiant, String nom, String adresse, String contact,
                           String numeroEmploye, String dateEmbauche, String qualification) {
        super(identifiant, nom, adresse, contact, numeroEmploye, dateEmbauche);
        this.qualification = qualification;
        this.volsAffectes = new ArrayList<>();
    }

    public String obtenirInfos() {
        return super.obtenirInfos() + ", Qualification: " + qualification;
    }

    public String obtenirRole() {
        return "Personnel de Cabine";
    }

    // Méthode pour affecter un vol (affecterVol())
    public void affecterVol(Vol vol) {
        if (!volsAffectes.contains(vol)) {
            volsAffectes.add(vol);
        }
    }

    // Méthode pour obtenir les vols (obtenirVol())
    public List<Vol> obtenirVols() {
        return new ArrayList<>(volsAffectes);
    }

    // CRUD Méthodes
    public void modifierPersonnelCabine(String qualification) {
        this.qualification = qualification;
    }

    // Getters et setters
    public String getQualification() { return qualification; }
    public void setQualification(String qualification) { this.qualification = qualification; }
}

// ==== CLASSE Avion ====
class Avion {
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

    // Méthode pour affecter un vol (affecterVol())
    public boolean affecterVol(Vol vol) {
        if (verifierDisponibilite(vol.getDateHeureDepart())) {
            volsAffectes.add(vol);
            return true;
        }
        return false;
    }

    // Méthode pour vérifier la disponibilité (verifierDisponibilite())
    public boolean verifierDisponibilite(String dateHeure) {
        // Logique simplifiée - vérifie s'il n'y a pas de vol à la même date/heure
        for (Vol vol : volsAffectes) {
            if (vol.getDateHeureDepart().equals(dateHeure)) {
                return false;
            }
        }
        return true;
    }

    // CRUD Méthodes
    public void modifierAvion(String modele, int capacite) {
        this.modele = modele;
        this.capacite = capacite;
    }

    // Getters et setters
    public String getImmatriculation() { return immatriculation; }
    public void setImmatriculation(String immatriculation) { this.immatriculation = immatriculation; }

    public String getModele() { return modele; }
    public void setModele(String modele) { this.modele = modele; }

    public int getCapacite() { return capacite; }
    public void setCapacite(int capacite) { this.capacite = capacite; }

    public List<Vol> getVolsAffectes() { return new ArrayList<>(volsAffectes); }
}

// ==== CLASSE Reservation ====
class Reservation {
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

    // Méthode pour confirmer la réservation (confirmerReservation())
    public void confirmerReservation() {
        this.statut = "confirmée";
    }

    // Méthode pour annuler la réservation (annulerReservation())
    public void annulerReservation() {
        this.statut = "annulée";
        if (vol != null) {
            vol.supprimerReservation(this);
        }
    }

    // Méthode pour modifier la réservation (modifierReservation())
    public void modifierReservation(Vol nouveauVol) {
        if (vol != null) {
            vol.supprimerReservation(this);
        }
        this.vol = nouveauVol;
        if (nouveauVol != null) {
            nouveauVol.ajouterReservation(this);
        }
    }

    // Méthode obtenirInfosReservation() - comme demandé dans le document
    public String obtenirInfosReservation() {
        return "Réservation " + numeroReservation +
                ", Date: " + dateReservation +
                ", Statut: " + statut +
                ", Passager: " + (passager != null ? passager.getNom() : "N/A") +
                ", Vol: " + (vol != null ? vol.getNumeroVol() : "N/A");
    }

    // CRUD Méthodes
    public void modifierReservationDetails(String dateReservation, String statut) {
        this.dateReservation = dateReservation;
        this.statut = statut;
    }

    // Getters et setters
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

// ==== CLASSE Vol ====
class Vol {
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

    // Méthode pour planifier le vol (planifierVol())
    public void planifierVol() {
        this.etat = "planifié";
    }

    // Méthode pour annuler le vol (annulerVol())
    public void annulerVol() {
        this.etat = "annulé";
        for (Reservation res : reservations) {
            res.annulerReservation();
        }
        reservations.clear();
    }

    // Méthode pour modifier le vol (modifierVol())
    public void modifierVol(String nouvelleDestination, String nouvelleDateDepart, String nouvelleDateArrivee) {
        this.destination = nouvelleDestination;
        this.dateHeureDepart = nouvelleDateDepart;
        this.dateHeureArrivee = nouvelleDateArrivee;
    }

    // Méthode pour lister les passagers (ListingPassager())
    public List<Passager> ListingPassager() {
        List<Passager> passagers = new ArrayList<>();
        for (Reservation res : reservations) {
            if ("confirmée".equals(res.getStatut())) {
                passagers.add(res.getPassager());
            }
        }
        return passagers;
    }

    // Méthode pour affecter un équipage (affecterEquipage())
    public void affecterEquipage(Pilote pilote, List<PersonnelCabine> equipeCabine) {
        this.pilote = pilote;
        this.equipeCabine = new ArrayList<>(equipeCabine);
    }

    // Méthode pour affecter un avion (affecterAvion())
    public boolean affecterAvion(Avion avion) {
        if (avion.verifierDisponibilite(dateHeureDepart)) {
            this.avion = avion;
            avion.affecterVol(this);
            return true;
        }
        return false;
    }

    // Méthode pour obtenir les informations du vol (obtenirInfosVol())
    public String obtenirInfosVol() {
        return "Vol " + numeroVol + ": " + origine + " -> " + destination +
                ", Départ: " + dateHeureDepart + ", Arrivée: " + dateHeureArrivee +
                ", État: " + etat + ", Avion: " + (avion != null ? avion.getImmatriculation() : "Non affecté");
    }

    // Méthode pour ajouter une réservation
    public void ajouterReservation(Reservation reservation) {
        if (!reservations.contains(reservation)) {
            reservations.add(reservation);
        }
    }

    // Méthode pour supprimer une réservation
    public void supprimerReservation(Reservation reservation) {
        reservations.remove(reservation);
    }

    // CRUD Méthodes
    public void modifierVolDetails(String origine, String destination, String etat) {
        this.origine = origine;
        this.destination = destination;
        this.etat = etat;
    }

    // Getters et setters
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
}

// ==== CLASSE Aeroport ====
class Aeroport {
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

    // Méthode pour affecter un vol (affecterVol())
    public void affecterVol(Vol vol) {
        if (!vols.contains(vol)) {
            vols.add(vol);
        }
    }

    // CRUD Méthodes
    public void modifierAeroport(String ville, String description) {
        this.ville = ville;
        this.description = description;
    }

    // Getters et setters
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getVille() { return ville; }
    public void setVille(String ville) { this.ville = ville; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public List<Vol> getVols() { return new ArrayList<>(vols); }
}

// ==== CLASSE GestionnaireCompagnie (pour les statistiques) ====
class GestionnaireCompagnie {
    private List<Vol> vols;
    private List<Passager> passagers;
    private List<Avion> avions;

    public GestionnaireCompagnie() {
        this.vols = new ArrayList<>();
        this.passagers = new ArrayList<>();
        this.avions = new ArrayList<>();
    }

    // Méthodes pour générer des rapports (Fonctionnalités avancées)
    public String genererRapportVols() {
        int totalVols = vols.size();
        int passagersTransportes = 0;
        double revenusEstimes = 0.0;

        for (Vol vol : vols) {
            if ("terminé".equals(vol.getEtat())) {
                passagersTransportes += vol.getReservations().size();
                revenusEstimes += vol.getReservations().size() * 300.0; // Estimation 300€ par passager
            }
        }

        return "Rapport Statistiques:\n" +
                "Nombre total de vols: " + totalVols + "\n" +
                "Passagers transportés: " + passagersTransportes + "\n" +
                "Revenus estimés: " + revenusEstimes + "€\n";
    }

    public List<String> destinationsPopulaires() {
        // Logique pour trouver les destinations les plus populaires
        List<String> destinations = new ArrayList<>();
        for (Vol vol : vols) {
            destinations.add(vol.getDestination());
        }
        return destinations;
    }

    // Méthodes d'ajout pour la gestion
    public void ajouterVol(Vol vol) {
        vols.add(vol);
    }

    public void ajouterPassager(Passager passager) {
        passagers.add(passager);
    }

    public void ajouterAvion(Avion avion) {
        avions.add(avion);
    }

    // Getters
    public List<Vol> getVols() { return new ArrayList<>(vols); }
    public List<Passager> getPassagers() { return new ArrayList<>(passagers); }
    public List<Avion> getAvions() { return new ArrayList<>(avions); }
}

// ==== CLASSE PRINCIPALE pour tester ====
public class marchegros {
    public static void main(String[] args) {
        System.out.println("=== SYSTÈME DE COMPAGNIE AÉRIENNE ===\n");

        // Création d'aéroports
        Aeroport aeroportParis = new Aeroport("CDG", "Paris", "Aéroport Charles de Gaulle");
        Aeroport aeroportNewYork = new Aeroport("JFK", "New York", "John F. Kennedy International Airport");

        // Création d'un avion
        Avion avion1 = new Avion("F-GABC", "Airbus A320", 180);

        // Création d'employés
        Pilote pilote1 = new Pilote("P001", "Jean Dupont", "Paris", "0102030405",
                "EMP001", "2020-01-15", "LIC123", 5000);

        PersonnelCabine pnc1 = new PersonnelCabine("P002", "Marie Curie", "Lyon", "0607080910",
                "EMP002", "2021-03-20", "Hôtesse principale");

        PersonnelCabine pnc2 = new PersonnelCabine("P003", "Pierre Durand", "Nice", "0611223344",
                "EMP003", "2022-06-10", "Steward");

        // Création de passagers
        Passager passager1 = new Passager("PA001", "Paul Martin", "Marseille", "0612345678", "AB123456");
        Passager passager2 = new Passager("PA002", "Sophie Bernard", "Lille", "0698765432", "CD789012");

        // Création de vols
        Vol vol1 = new Vol("AF123", "Paris", "New York", "2024-12-05 10:00", "2024-12-05 13:00");
        Vol vol2 = new Vol("AF456", "Paris", "Tokyo", "2024-12-06 14:00", "2024-12-06 22:00");

        // Affectation des aéroports aux vols
        aeroportParis.affecterVol(vol1);
        aeroportNewYork.affecterVol(vol1);

        // Affectation de l'équipage au vol 1
        List<PersonnelCabine> equipe1 = new ArrayList<>();
        equipe1.add(pnc1);
        equipe1.add(pnc2);
        vol1.affecterEquipage(pilote1, equipe1);

        // Affectation de l'avion au vol 1
        boolean avionAffecte = vol1.affecterAvion(avion1);
        System.out.println("Avion affecté au vol AF123: " + (avionAffecte ? "OUI" : "NON"));

        // Réservations
        System.out.println("\n=== RÉSERVATIONS ===");
        Reservation res1 = passager1.reserverVol(vol1, "2024-12-01");
        Reservation res2 = passager2.reserverVol(vol1, "2024-12-02");

        System.out.println("Réservation 1: " + res1.obtenirInfosReservation());
        System.out.println("Réservation 2: " + res2.obtenirInfosReservation());

        // Affichage des informations
        System.out.println("\n=== INFORMATIONS DU VOL ===");
        System.out.println(vol1.obtenirInfosVol());

        System.out.println("\n=== INFORMATIONS DES PERSONNES ===");
        System.out.println("Pilote: " + pilote1.obtenirInfos());
        System.out.println("Passager 1: " + passager1.obtenirInfos());

        System.out.println("\n=== LISTE DES PASSAGERS DU VOL AF123 ===");
        List<Passager> passagersVol1 = vol1.ListingPassager();
        for (Passager p : passagersVol1) {
            System.out.println("  - " + p.getNom());
        }

        // Test d'annulation
        System.out.println("\n=== TEST D'ANNULATION ===");
        boolean annulation = passager1.annulerReservation(res1.getNumeroReservation());
        System.out.println("Annulation de la réservation " + res1.getNumeroReservation() + ": " +
                (annulation ? "RÉUSSIE" : "ÉCHOUÉE"));

        // Test des rôles
        System.out.println("\n=== RÔLES DES EMPLOYÉS ===");
        System.out.println("Rôle de " + pilote1.getNom() + ": " + pilote1.obtenirRole());
        System.out.println("Rôle de " + pnc1.getNom() + ": " + pnc1.obtenirRole());

        // Test des statistiques
        System.out.println("\n=== STATISTIQUES ===");
        GestionnaireCompagnie gestionnaire = new GestionnaireCompagnie();
        gestionnaire.ajouterVol(vol1);
        gestionnaire.ajouterVol(vol2);
        gestionnaire.ajouterPassager(passager1);
        gestionnaire.ajouterPassager(passager2);
        gestionnaire.ajouterAvion(avion1);

        System.out.println(gestionnaire.genererRapportVols());

        // Test de disponibilité d'avion
        System.out.println("=== VÉRIFICATION DISPONIBILITÉ AVION ===");
        boolean disponible = avion1.verifierDisponibilite("2024-12-05 10:00");
        System.out.println("Avion disponible le 2024-12-05 10:00: " + (disponible ? "OUI" : "NON"));

        System.out.println("\n=== SYSTÈME PRÊT À FONCTIONNER ===");
    }
}
