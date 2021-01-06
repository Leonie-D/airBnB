package dusart.airbnb.outils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MaDate extends Date {
    /**
     * Format pour la convertion de la date en String
     */
    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * Crée une instance de MaDate à partir du jour, du mois et de l'année
     *
     * @param jour date du jour (entre 1 et 31)
     * @param mois numéro du mois (entre 1 et 12)
     * @param annee année au format YYYY
     * @return une instance de MaDate
     */
    public MaDate(int jour, int mois, int annee){
        super(annee - 1900, mois - 1, jour);
    }

    /**
     * Convertit une Date en String en utilisant SimpleDateFormat
     *
     * @return la date au format String "DD/MM/YYY"
     */
    @Override
    public String toString() {
        return FORMAT.format(this);
    }
}
