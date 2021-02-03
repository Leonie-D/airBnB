package dusart.airbnb.menu;

import dusart.airbnb.logements.Appartement;
import dusart.airbnb.logements.Logement;
import dusart.airbnb.logements.Maison;
import dusart.airbnb.outils.Comparateur;
import dusart.airbnb.outils.ComparateurMultiple;
import dusart.airbnb.outils.ImportXML;
import dusart.airbnb.outils.MaDate;
import dusart.airbnb.reservations.*;
import dusart.airbnb.utilisateurs.Hote;
import dusart.airbnb.utilisateurs.Personne;
import dusart.airbnb.utilisateurs.Voyageur;

import java.util.*;

public class Menu {
    static Scanner scanner;
    static ArrayList<Hote> listeHotes;
    static ArrayList<Logement> listeLogements;
    static ArrayList<Voyageur> listeVoyageurs;
    static ArrayList<Reservation> listeReservations;

    public static void main(String[] args) {
        // initialisation des listes;
        listeHotes = new ArrayList<>();
        listeLogements = new ArrayList<>();
        listeVoyageurs = new ArrayList<>();
        listeReservations = new ArrayList<>();

        ImportXML.importLogements("file:///Users/leonie/Downloads/logements.xml", listeLogements, listeHotes);

        Voyageur camille = new Voyageur("Camille", "Gérard", 28);
        listeVoyageurs.add(camille);

        /*try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            LogementsHandler logementsHandler = new LogementsHandler();

            saxParser.parse("file:///Users/leonie/Downloads/logements.xml", logementsHandler);
            listeLogements = logementsHandler.getListeLogements();
            listeHotes = logementsHandler.getListeHotes();

        } catch(Exception e) {
            System.out.println(e.getMessage());
        }*/

        /*Maison maison = new Maison(listeHotes.get(0), 30, "5 place Coty", 50, 3, 20, true);
        Appartement appart = new Appartement(listeHotes.get(2), 40, "6 place Coty", 100, 2, 3, 45);
        maison.setName("Domicile");
        appart.setName("AirBnB");
        listeLogements.add(maison);
        listeLogements.add(appart);

        Maison maison1 = new Maison(listeHotes.get(0), 30, "5 place Coty", 50, 3, 20, true);
        maison1.afficher();

        Logement logement1 = GestionLogements.findLogementByName("Domicile");
        if(logement1 != null) {
            logement1.afficher();
        }

        Maison logement2 = GestionLogements.findLogementByNameWithGenericity("Domicile");
        logement2.afficher();

        Optional<Logement> test = GestionLogements.findLogementByNameWithGenericity("AirBnB");
        if(test.isPresent()) {
            Logement logement = test.get();
            logement.afficher();
        }



        Hote leonie = new Hote("Leonie", "Dusart", 25, 7);
        Hote achille = new Hote("Achille", "Dusart", 28, 3);

        Comparateur<Logement> compLogements = new Comparateur<>(maison, appart);
        Logement resultL = compLogements.getHigher();
        resultL.afficher();

        Comparateur<Personne> compPersonnes1 = new Comparateur<>(leonie, camille);
        Personne resultP = compPersonnes1.getHigher();
        resultP.afficher();
        System.out.println();

        Comparateur<Personne> compPersonnes2 = new Comparateur<>(leonie, achille);
        Personne resultP2 = compPersonnes2.getHigher();
        resultP2.afficher();
        System.out.println();

        System.out.println("-------------------------------------");
        afficherListeLogements();
        System.out.println("-------------------------------------");
        ComparateurMultiple<Logement> compListeLogements = new ComparateurMultiple<>(listeLogements);
        compListeLogements.getHigher().afficher();*/

        MaDate dateArrivee = new MaDate(12, 2, 2021);
        SejourLong sejour = new SejourLong(dateArrivee, 15, listeLogements.get(0), 4);

        //sejour.setLogement(listeLogements.get(2));
        //sejour.setDateArrivee(new MaDate(1, 11, 2020));
        //sejour.setDateArrivee(new MaDate(1, 11, 2021));
        //sejour.setNbNuits(17);
        //sejour.setNbNuits(3);
        //sejour.setNbNuits(35);

        try {
            Reservation resa = new Reservation(sejour, camille, 123);
            //resa.afficher();
            // copie défensive ici pour éviter de modifier réservation
            SendEmailReservation.sendEmail((Reservation) resa.clone());
            //resa.afficher();
            //sejour.afficher();
            SendEmailReservation.sendEmail(resa.getSejour());
            //sejour.afficher();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

        //sejour.afficher();
        // copie défensive
        SendEmailReservation.sendEmail((Sejour) sejour.clone());
        //sejour.afficher();

        System.out.println("Bienvenue chez AirBnB");
        scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");
        listerMenu();
        scanner.close();
    }

    /**
     * Affiche le menu principal du airBnB
     */
    static void listerMenu() {
        System.out.println(
                "-------------------------------------\n" +
                "Saisir une option :\n" +
                "1 : Liste des hôtes\n" +
                "2 : Liste des logements\n" +
                "3 : Liste des voyageurs\n" +
                "4 : Liste des réservations\n" +
                "5 : Fermer le programme"
        );
        switch (Menu.choix(5)) {
            case 1:
                GestionHotes.listerHotes();
                break;
            case 2:
                GestionLogements.listerLogements();
                break;
            case 3:
                GestionVoyageurs.listerVoyageurs();
                break;
            case 4:
                GestionReservations.listerReservations();
                break;
            case 5:
                System.exit(0);
                break;
        }
    }

    /**
     * Renvoie la valeur du choix saisi (le redemande tant qu'il n'est pas cohérent)
     * @param maxValue valeur maximale possible
     * @return la valeur du choix
     */
    static int choix(int maxValue) { // voir correction...
        int valueChoix;
        do {
            try {
                valueChoix = scanner.nextInt();
            } catch (InputMismatchException e) {
                scanner.next();
                System.out.println("Merci de saisir un entier");
                valueChoix = 0;
            }
        } while (!verifierChoix(valueChoix, maxValue));
        return valueChoix;
    }

    /**
     * Vérifie que le choix est cohérent
     * @param valueChoix choix saisi par l'utilisateur
     * @param maxValue choix max du menu
     * @return true si le choix appartient à la liste des possibles, false sinon
     */
    private static boolean verifierChoix(int valueChoix, int maxValue) {
        if(valueChoix > 0 && valueChoix <= maxValue) {
            return true;
        } else {
            System.out.println("Merci de saisir un nombre entre 1 et " + maxValue);
            return false;
        }
    }

    /**
     * Affiche les listes des hôtes et voyageurs
     */
    static void afficherListePersonnes(ArrayList<? extends Personne> liste) {
        for (int i = 0; i < liste.size(); i++) {
            System.out.print((i + 1) + " : ");
            liste.get(i).afficher();
            System.out.println();
        }
    }

    /**
     * Affiche la liste des logements
     */
    static void afficherListeLogements() {
        for (int i = 0; i < listeLogements.size(); i++) {
            System.out.print((i + 1) + " : ");
            listeLogements.get(i).afficher();
            System.out.println();
        }
    }

    /**
     * Affiche la liste des réservations
     */
    static void afficherListeReservations() {
        for (int i = 0; i < listeReservations.size(); i++) {
            System.out.print((i + 1) + " : ");
            listeReservations.get(i).afficher();
            System.out.println();
        }
    }
}
