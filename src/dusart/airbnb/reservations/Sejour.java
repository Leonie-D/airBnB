package dusart.airbnb.reservations;

import dusart.airbnb.logements.Logement;

import java.util.Date;

public abstract class Sejour implements SejourInterface {
    private Date dateArrivee;
    private int nbNuits;
    private Logement logement;
    private int nbVoyageurs;
    private double tarif;

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
    public boolean verificationNombreDeVoyageurs() {
        return nbVoyageurs > 0 && nbVoyageurs <= logement.getNbVoyageursMax();
    }

    /**
     * @return le tarif du séjour (promotion déduite le cas échéant)
     */
    public double getTarif() {
        return tarif;
    }

    /**
     * Modifie la valeur du tarif
     */
    public void setTarif(double paramTarif) {
        tarif = paramTarif;
    }

    /**
     * @return le nombre de nuits
     */
    public int getNbNuits() {
        return nbNuits;
    }

    /**
     * @return le nombre de voyageurs
     */
    public int getNbVoyageurs() {
        return nbVoyageurs;
    }

    /**
     * @return le logement
     */
    public Logement getLogement() {
        return logement;
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
