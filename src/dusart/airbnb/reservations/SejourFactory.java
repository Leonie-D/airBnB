package dusart.airbnb.reservations;

import dusart.airbnb.logements.Logement;

import java.util.Date;

public class SejourFactory {
    public static Sejour getSejour(Date dateArrivee, int nbNuits, Logement logement, int nbVoyageurs) {
        Sejour sejour = null;
        try {
            if(nbNuits < 6) {
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
