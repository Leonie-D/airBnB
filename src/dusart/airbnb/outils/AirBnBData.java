package dusart.airbnb.outils;

import dusart.airbnb.logements.Logement;
import dusart.airbnb.reservations.Reservation;
import dusart.airbnb.utilisateurs.Hote;
import dusart.airbnb.utilisateurs.Voyageur;

import java.util.ArrayList;

public class AirBnBData {
    private static final AirBnBData INSTANCE = new AirBnBData();
    private final ArrayList<Logement> listeLogements;
    private final ArrayList<Hote> listeHotes;
    private final ArrayList<Voyageur> listeVoyageurs;
    private final ArrayList<Reservation> listeReservations;

    // private constructor
    private AirBnBData() {
        listeLogements = new ArrayList<>();
        listeHotes = new ArrayList<>();
        listeVoyageurs = new ArrayList<>();
        listeReservations = new ArrayList<>();
    }

    public static AirBnBData getInstance() {
        return INSTANCE;
    }

    /**
     * @param c une classe parmi Logement, Hote, Voyageur et Reservation
     * @return une ArrayListe composée d'éléments de la classe c ou une ArrayListe<Object> vide
     */
    public ArrayList<?> getListe(Class c) {
        switch(c.getSimpleName()) {
            case "Logement" :
                return listeLogements;
            case "Hote" :
                return listeHotes;
            case "Voyageur" :
                return listeVoyageurs;
            case "Reservation" :
                return listeReservations;
            default:
                return new ArrayList<>();
        }
    }
}
