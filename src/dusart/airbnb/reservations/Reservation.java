package dusart.airbnb.reservations;

import dusart.airbnb.utilisateurs.Voyageur;

import java.util.Date;

public class Reservation {
    private final int identifiant;
    private final Sejour sejour;
    private final Voyageur voyageur;
    private boolean estValidee;
    private final Date dateDeReservation;

    /**
     * Le constructuer unique pour définir une réservation à partir de 3 paramètres
     *
     * @param paramSejour le séjour à réserver
     * @param paramVoyageur le voyageur qui effectue la réservation
     * @param paramIdentifiant l'identifiant unique de la réservation
     */
    public Reservation(Sejour paramSejour, Voyageur paramVoyageur, int paramIdentifiant) {
        sejour = paramSejour;
        voyageur = paramVoyageur;
        identifiant = paramIdentifiant;
        dateDeReservation = new Date();
        estValidee = false;
    }

    /**
     * Affiche le détail de la réservation (voyageur, hôte, adresse du logement, durée du séjour et prix total) dans la console
     */
    public void afficher() {
        voyageur.afficher();
        System.out.print(" a fait une réservation chez ");
        sejour.afficher();
    }
}
