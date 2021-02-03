package dusart.airbnb.reservations;

import dusart.airbnb.logements.Logement;

import java.util.Date;

public abstract class Sejour implements SejourInterface, Cloneable {
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
        // dateArrivee est protégée des modifications utilisant les méthodes de Date (ex. setYear)
        // copie défensive
        setDateArrivee(paramDateArrivee);
        setNbNuits(paramNbNuits);
        nbVoyageurs = paramNbVoyageurs;
        setLogement(paramLogement);
        miseAJourDuTarif();
    }

    /**
     * @return true si la date d'arrivée est antérieure à la date du jour
     */
    @Override
    public boolean verficationDateArrivee() {
        Date today = new Date();
        return dateArrivee.compareTo(today) > 0;
    }

    /**
     * @return true si le nombre de voyageurs respecte la capacité d'accueil du logement
     */
    @Override
    public boolean verificationNombreDeVoyageurs() {
        return nbVoyageurs > 0 && nbVoyageurs <= logement.getNbVoyageursMax();
    }

    /**
     * @return la date d'arrivée
     */
    public Date getDateArrivee() {
        // dateArrivee est protégée des modifications utilisant les méthodes de Date (ex. setYear)
        // copie défensive
        return (Date) dateArrivee.clone();
    }


    /**
     * Modifie la date d'arrivée
     * @param paramDateArrivee la nouvelle date d'arrivée
     * @throws IllegalArgumentException si la date d'arrivée est antérieure à la date du jour
     */
    public void setDateArrivee(Date paramDateArrivee) throws IllegalArgumentException {
        if(paramDateArrivee.compareTo(new Date()) > 0) {
            // dateArrivee est protégée des modifications utilisant les méthodes de Date (ex. setYear)
            // copie défensive
            dateArrivee = (Date) paramDateArrivee.clone();
        } else {
            throw new IllegalArgumentException("La date d'arrivée ne peut être antérieure à la date du jour");
        }
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
     * Modifie le nombre de nuits
     * @param paramNbNuits le nouveau nombre de nuits
     * @throws IllegalArgumentException si le nombre de nuits ne respecte pas les conditions du séjour (long ou court)
     */
    public void setNbNuits(int paramNbNuits) throws IllegalArgumentException {
        if(verificationNombreDeNuits(paramNbNuits)) {
            nbNuits = paramNbNuits;
        } else {
            if(this instanceof SejourCourt) {
                throw new IllegalArgumentException("Le nombre de nuits doit être compris entre 1 et 5");
            } else {
                throw new IllegalArgumentException("Le nombre de nuits doit être compris entre 6 et 31");
            }
        }
    }

    /**
     * @return le nombre de voyageurs
     */
    public int getNbVoyageurs() {
        return nbVoyageurs;
    }

    /**
     * @return le nombre de voyageurs
     */
    public void setNbVoyageurs(int paramNbVoyageurs) {
        nbVoyageurs = paramNbVoyageurs;
    }

    /**
     * @return le logement
     */
    public Logement getLogement() {
        return logement;
    }


    /**
     * Modifie le logement si les conditions de réservation sont validées
     * @param paramLogement le nouveau logement
     * @throws IllegalArgumentException dans le cas où le logement n'a pas la capicité d'accueil requise
     */
    public void setLogement(Logement paramLogement) throws IllegalArgumentException {
        if(nbVoyageurs > 0 && nbVoyageurs <= paramLogement.getNbVoyageursMax()) {
            logement = paramLogement;
        } else {
            throw new IllegalArgumentException("Le nombre de voyageurs ne peut excédé la capacité du logement (" + paramLogement.getNbVoyageursMax() + ")");
        }
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

    @Override
    public Object clone() {
        Sejour s = null;
        try {
            s = (Sejour) super.clone();
        } catch (CloneNotSupportedException e) {} // Won't happen
        return s;
    }
}
