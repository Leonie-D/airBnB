package dusart.airbnb.reservations;

import dusart.airbnb.logements.Logement;

import java.util.Date;

public class SejourLong extends Sejour implements ConditionsTarifairesInterace {
    private static final double PROMOTION_EN_POURCENTAGE = 0.2;
    protected double promotion;

    /**
     * Le constructeur unique pour définir un séjour à partir de 4 paramètres
     *
     * @param paramDateArrivee la date d'arrivée dans le logement
     * @param paramNbNuits     le nombre de nuits dans le logement
     * @param paramLogement    le logement loué
     * @param paramNbVoyageurs le nombre de voyageurs qui seront logés
     */
    public SejourLong(Date paramDateArrivee, int paramNbNuits, Logement paramLogement, int paramNbVoyageurs) {
        super(paramDateArrivee, paramNbNuits, paramLogement, paramNbVoyageurs);
    }

    /**
     * Vérifie que le nombre de nuit est compris entre 6 et 31 (inclus)
     * @return true si la condition est bien respectée, false sinon
     */
    @Override
    public boolean verificationNombreDeNuits() {
        if(nbNuits < 6 || nbNuits > 31) {
            return false;
        }
        return true;
    }

    /**
     * Précise si le séjour bénéficie d'une promotion
     * @return toujours true dans le cas d'un séjour long
     */
    @Override
    public boolean beneficiePromotion() {
        return true;
    }

    @Override
    /**
     * Affiche le détail du séjour dans la console (hôte, adresse du logement, durée du séjour et prix total)
     */
    public void afficher() {
        super.afficher();
        System.out.println("Le prix de ce séjour est de " + tarif + "€ (promotion de " + promotion + "€ déduite).");
    }

    /**
     * Met à jour le tarif compte tenu des conditions tarifaires appliquées à ce séjour
     * @return le tarif final
     */
    @Override
    protected void miseAJourDuTarif() {
        int tarifAvantPromotion = nbNuits * logement.getTarifParNuit();
        promotion = tarifAvantPromotion * PROMOTION_EN_POURCENTAGE;
        tarif = tarifAvantPromotion - promotion;
    }
}
