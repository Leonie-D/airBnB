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
     * @throws Exception si le contrat du séjour n'est pas respecté
     */
    public Reservation(Sejour paramSejour, Voyageur paramVoyageur, int paramIdentifiant) throws Exception {
        if(!paramSejour.verficationDateArrivee()) {
            throw new Exception("La date d'arrivée ne peut être antérieure à la date du jour");
        } else if(!paramSejour.verificationNombreDeNuits()) {
            throw new Exception("Le nombre de nuits doit être compris entre 1 et 31");
        } else if(!paramSejour.verificationNombreDeVoyageurs()) {
            throw new Exception("Le nombre de voyageurs ne peut excédé la capacité du logement (" + paramSejour.getLogement().getNbVoyageursMax() + ")");
        } else {
            sejour = paramSejour;
            voyageur = paramVoyageur;
            identifiant = paramIdentifiant;
            dateDeReservation = new Date();
            estValidee = false;
        }
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
