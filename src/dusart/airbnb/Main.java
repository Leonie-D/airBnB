package dusart.airbnb;

import dusart.airbnb.logements.Maison;
import dusart.airbnb.logements.Appartement;
import dusart.airbnb.outils.MaDate;
import dusart.airbnb.reservations.Reservation;
import dusart.airbnb.reservations.Sejour;
import dusart.airbnb.reservations.SejourCourt;
import dusart.airbnb.reservations.SejourLong;
import dusart.airbnb.utilisateurs.Hote;
import dusart.airbnb.utilisateurs.Voyageur;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Hote leonie = new Hote("Léonie", "Dusart", 29, 24);
        Hote edouard = new Hote("Edouard", "Coudert", 32, 1);
        Voyageur camille = new Voyageur("Camille", "Gérard", 28);

        Maison maison = new Maison(leonie, 55, "6 avenue Maginot, 37100 TOURS", 70, 2, 100, false);
        Appartement appart = new Appartement(edouard, 70, "8 avenue Maginot, 37100 TOURS", 90, 2, 3, 50);

        MaDate dateWe = new MaDate(23, 2, 2020);
        SejourCourt weekend = new SejourCourt(dateWe, 2, appart, 2);
        weekend.afficher();

        System.out.println();

        MaDate dateVacances = new MaDate(30, 5, 2020);
        SejourLong vacances = new SejourLong(dateVacances, 14, maison, 2);
        vacances.afficher();

        System.out.println();

        MaDate dateVacances2 = new MaDate(30, 5, 2020);
        SejourLong vacances2 = new SejourLong(dateVacances2, 140, maison, 2);
        vacances2.afficher();

        System.out.println();

        /*
        Reservation resa1 = new Reservation(weekend, camille, 1234);
        resa1.afficher();
        Reservation resa2 = new Reservation(vacances, camille, 4321);
        resa2.afficher();
        */
    }
}
