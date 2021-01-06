package dusart.airbnb.logements;

import dusart.airbnb.utilisateurs.Hote;

public class Maison extends Logement {
    private final int superficieJardin;
    private boolean possedePiscine;

    /**
     * Unique constructeur pour définir une maison et ses conditions de location à partir de 7 paramètres
     *
     * @param paramHote        le propriétaire du logement
     * @param paramTarif       le tarif pour une nuit dans le logement
     * @param paramAdresse     l'adresse du logement
     * @param paramSuperficie  la superficie du logement
     * @param paramNbVoyageurs le nombre maximal de voyageurs que le logement peut accueillir simultanément
     * @param paramSuperficieJardin la superficie du jardin
     * @param paramPossedePiscine la présence/abscence d'une piscine
     */
    public Maison(Hote paramHote, int paramTarif, String paramAdresse, int paramSuperficie, int paramNbVoyageurs, int paramSuperficieJardin, boolean paramPossedePiscine) {
        super(paramHote, paramTarif, paramAdresse, paramSuperficie, paramNbVoyageurs);
        superficieJardin = paramSuperficieJardin;
        possedePiscine = paramPossedePiscine;
    }

    @Override
    public int getSuperficieTotale() {
        return super.getSuperficie() + superficieJardin;
    }

    @Override
    public void afficher() {
        super.afficherLogement();
        System.out.println("Type : Maison");
        if(superficieJardin > 0) {
            System.out.println("Jardin : oui (" + superficieJardin + "m2)");
        } else  {
            System.out.println("Jardin : non");
        }
        System.out.println("Piscine : " + (possedePiscine ? "oui" : "non"));
    }
}
