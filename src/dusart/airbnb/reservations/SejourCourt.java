package dusart.airbnb.reservations;

import dusart.airbnb.logements.Logement;

import java.util.Date;

public class SejourCourt extends Sejour implements ConditionsTarifairesInterace {
    /**
     * Le constructeur unique pour définir un séjour à partir de 4 paramètres
     *
     * @param paramDateArrivee la date d'arrivée dans le logement
     * @param paramNbNuits     le nombre de nuits dans le logement
     * @param paramLogement    le logement loué
     * @param paramNbVoyageurs le nombre de voyageurs qui seront logés
     */
    public SejourCourt(Date paramDateArrivee, int paramNbNuits, Logement paramLogement, int paramNbVoyageurs) {
        super(paramDateArrivee, paramNbNuits, paramLogement, paramNbVoyageurs);
    }

    /**
     * Vérifie que le nombre de nuit est compris entre 1 et 5 (inclus)
     * @return true si la condition est bien respectée, false sinon
     */
    @Override
    public boolean verificationNombreDeNuits() {
        return nbNuits > 0 && nbNuits <= 5;
    }

    /**
     * Précise si le séjour bénéficie d'une promotion
     * @return toujours false dans le cas d'un séjour court
     */
    @Override
    public boolean beneficiePromotion() {
        return false;
    }

    @Override
    /**
     * Affiche le détail du séjour dans la console (hôte, adresse du logement, durée du séjour et prix total)
     */
    public void afficher() {
        super.afficher();
        System.out.println("Le prix de ce séjour est de " + tarif + "€.");
    }

    /**
     * Met à jour le tarif compte tenu des conditions tarifaires appliquées à ce séjour
     */
    @Override
    protected void miseAJourDuTarif() {
        tarif = nbNuits * logement.getTarifParNuit();
    }
}
