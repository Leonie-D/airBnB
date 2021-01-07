package dusart.airbnb.reservations;

import dusart.airbnb.logements.Logement;

import java.util.Date;

public abstract class Sejour implements SejourInterface {
    private Date dateArrivee;
    protected int nbNuits;
    protected Logement logement;
    private int nbVoyageurs;
    protected double tarif;

    /**
     * Le constructeur unique pour définir un séjour à partir de 4 paramètres
     *
     * @param paramDateArrivee la date d'arrivée dans le logement
     * @param paramNbNuits le nombre de nuits dans le logement
     * @param paramLogement le logement loué
     * @param paramNbVoyageurs le nombre de voyageurs qui seront logés
     */
    public Sejour(Date paramDateArrivee, int paramNbNuits, Logement paramLogement, int paramNbVoyageurs) {
        dateArrivee = paramDateArrivee;
        nbNuits = paramNbNuits;
        logement = paramLogement;
        nbVoyageurs = paramNbVoyageurs;
        miseAJourDuTarif();
    }

    @Override
    public boolean verficationDateArrivee() {
        Date today = new Date();
        return dateArrivee.compareTo(today) > 0;
    }

    @Override
    public abstract boolean verificationNombreDeNuits();

    @Override
    public boolean verificationNombreDeVoyageurs() {
        return nbVoyageurs > 0 && nbVoyageurs <= logement.getNbVoyageursMax();
    }

    /**
     * @return le tarif du séjour (promotion déduite le cas échéant)
     */
    public double getTarif() {
        return tarif;
    }

    @Override
    /**
     * Affiche le détail du séjour dans la console (hôte, adresse du logement, durée du séjour et prix total)
     */
    public void afficher() {
        logement.afficher();
        System.out.println("La date d'arrivée est le " + dateArrivee.toString() + " pour " + nbNuits + " nuit(s).");
    }

    protected abstract void miseAJourDuTarif();
}
