package dusart.airbnb.reservations;

import dusart.airbnb.outils.Affichable;
import dusart.airbnb.utilisateurs.Voyageur;

import java.util.Date;

public class Reservation implements Cloneable, Affichable {
    private final int identifiant;
    private final Sejour sejour;
    private final Voyageur voyageur;
    private boolean estValidee;
    private final Date dateDeReservation;

    /**
     * Le constructuer unique pour définir une réservation à partir de 3 paramètres
     *
     * @param paramSejour le séjour à réserver, non null
     * @param paramVoyageur le voyageur qui effectue la réservation, non null
     * @param paramIdentifiant l'identifiant unique de la réservation
     * @throws Exception si le séjour est null ou si le voyageur est null ou si le contrat du séjour n'est pas respecté
     */
    public Reservation(Sejour paramSejour, Voyageur paramVoyageur, int paramIdentifiant) throws Exception {
        if(paramSejour == null || paramVoyageur == null) {
            throw new Exception("Un ou plusieurs objects null...");
        } else if(!paramSejour.verficationDateArrivee()) {
            throw new Exception("La date d'arrivée ne peut être antérieure à la date du jour");
        } else if(!paramSejour.verificationNombreDeNuits()) {
            throw new Exception("Le nombre de nuits doit être compris entre 1 et 31");
        } else if(!paramSejour.verificationNombreDeVoyageurs()) {
            throw new Exception("Le nombre de voyageurs ne peut excédé la capacité du logement (" + paramSejour.getLogement().getNbVoyageursMax() + ")");
        } else {
            sejour = (Sejour) paramSejour.clone();
            voyageur = paramVoyageur;
            identifiant = paramIdentifiant;
            dateDeReservation = new Date();
            estValidee = false;
        }
    }

    public Sejour getSejour() {
        // copie défensive
        return (Sejour) sejour.clone();
    }

    public void setEstValidee(boolean paramEstValidee) {
        estValidee = paramEstValidee;
    }

    /**
     * Affiche le détail de la réservation (voyageur, hôte, adresse du logement, durée du séjour et prix total) dans la console
     */
    public void afficher() {
        voyageur.afficher();
        System.out.print(" a fait une réservation chez ");
        sejour.afficher();
    }

    @Override
    public Object clone() {
        Reservation r = null;
        try {
            r = (Reservation) super.clone();
        } catch (CloneNotSupportedException e) {} // Won't happen
        return r;
    }
}
