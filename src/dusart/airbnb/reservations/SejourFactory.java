package dusart.airbnb.reservations;

import dusart.airbnb.logements.Logement;

import java.util.Date;

public final class SejourFactory { // final sur une classe empêche l'héritage
    private static final int NB_NUITS_PIVOT = 6;

    private SejourFactory() {} // empêche de faire un new SejourFactory

    /**
     * @param dateArrivee
     * @param nbNuits
     * @param logement
     * @param nbVoyageurs
     * @return un séjour court ou long selon nbNuits (ou rien si pb)
     */
    public static Sejour getSejour(Date dateArrivee, int nbNuits, Logement logement, int nbVoyageurs) {
        Sejour sejour = null;
        try {
            if(nbNuits < NB_NUITS_PIVOT) {
                sejour = new SejourCourt(dateArrivee, nbNuits, logement, nbVoyageurs);
            } else {
                sejour = new SejourLong(dateArrivee, nbNuits, logement, nbVoyageurs);
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return sejour;
    }
}
