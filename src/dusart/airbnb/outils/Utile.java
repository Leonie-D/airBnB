package dusart.airbnb.outils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utile {
    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * Crée une instance de Date à partir du jour, du mois et de l'année
     *
     * @param jour date du jour (entre 1 et 31)
     * @param mois numéro du mois (entre 1 et 12)
     * @param annee année au format YYYY
     * @return une instance de Date ou null si la date n'existe pas
     */
    public static Date createDate(int jour, int mois, int annee) {
        if(jour > 31 || jour < 1 || mois < 1 || mois > 12) {
            return null;
        }

        return new Date(annee - 1900, mois - 1, jour);
    }

    /**
     * Convertit une Date en String en utilisant SimpleDateFormat
     *
     * @param date la date à convertir
     * @return la date au format String "DD/MM/YYY"
     */
    public static String DateToNiceString(Date date) {
        /* Première tentative :
        String jour = date.getDate() < 10 ? "0" + date.getDate() : String.valueOf(date.getDate());
        String mois = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : String.valueOf(date.getMonth() + 1);
        int annee = date.getYear() + 1900;
        return jour + "/" + mois + "/" + annee;
         */

        return FORMAT.format(date);
    }
}
