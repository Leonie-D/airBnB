package dusart.airbnb.logements;

import dusart.airbnb.utilisateurs.Hote;

public class Appartement extends Logement {
    private final int superficieBalcon;
    private final int numeroEtage;

    /**
     * Unique constructeur pour définir un appartement et ses conditions de location à partir de 7 paramètres
     *
     * @param paramHote        le propriétaire du logement
     * @param paramTarif       le tarif pour une nuit dans le logement
     * @param paramAdresse     l'adresse du logement
     * @param paramSuperficie  la superficie du logement
     * @param paramNbVoyageurs le nombre maximal de voyageurs que le logement peut accueillir simultanément
     * @param paramNumeroEtage l'étage de l'appartement
     * @param paramSuperficieBalcon la superficie du balcon
     */
    public Appartement(Hote paramHote, int paramTarif, String paramAdresse, int paramSuperficie, int paramNbVoyageurs, int paramNumeroEtage, int paramSuperficieBalcon) {
        super(paramHote, paramTarif, paramAdresse, paramSuperficie, paramNbVoyageurs);
        superficieBalcon = paramSuperficieBalcon;
        numeroEtage = paramNumeroEtage;
    }

    @Override
    public int getSuperficieTotale() {
        return super.getSuperficie() + superficieBalcon;
    }

    @Override
    public void afficher() {
        super.afficherLogement();
        if(numeroEtage == 0) {
            System.out.println("Type : Appartement (au rez-de-chaussée)");
        } else if(numeroEtage == 1) {
            System.out.println("Type : Appartement (au 1er étage)");
        } else {
            System.out.println("Type : Appartement (au " + numeroEtage + "ième étage)");
        }
        if(superficieBalcon > 0) {
            System.out.println("Balcon : oui (" + superficieBalcon + "m2)");
        } else  {
            System.out.println("Balcon : non");
        }
    }
}
