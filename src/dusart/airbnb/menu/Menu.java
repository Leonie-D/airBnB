package dusart.airbnb.menu;

import dusart.airbnb.logements.Appartement;
import dusart.airbnb.logements.Logement;
import dusart.airbnb.logements.LogementsHandler;
import dusart.airbnb.logements.Maison;
import dusart.airbnb.reservations.Reservation;
import dusart.airbnb.utilisateurs.Hote;
import dusart.airbnb.utilisateurs.Voyageur;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    static Scanner scanner;
    static ArrayList<Hote> listeHotes;
    static ArrayList<Logement> listeLogements;
    static ArrayList<Voyageur> listeVoyageurs;
    static ArrayList<Reservation> listeReservations;

    public static void main(String[] args) {
        // initialisation des listes;
        listeHotes = new ArrayList<Hote>();
        listeLogements = new ArrayList<Logement>();
        listeVoyageurs = new ArrayList<Voyageur>();
        listeReservations = new ArrayList<Reservation>();

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            LogementsHandler logementsHandler = new LogementsHandler();

            saxParser.parse("file:///Users/leonie/Downloads/logements.xml", logementsHandler);
            listeLogements = logementsHandler.getListeLogements();
            listeHotes = logementsHandler.getListeHotes();

        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

        ImportXML.importLogements("file:///Users/leonie/Downloads/logements.xml", listeLogements, listeHotes);

        Voyageur camille = new Voyageur("Camille", "Gérard", 28);
        listeVoyageurs.add(camille);

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
                System.out.println("Liste des voyageurs");
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
     * Affiche la liste des hôtes
     */
    static void afficherListeHotes() {
        for (int i = 0; i < listeHotes.size(); i++) {
            System.out.print((i + 1) + " : ");
            listeHotes.get(i).afficher();
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
     * Affiche la liste des voyageurs
     */
    static void afficherListeVoyageurs() {
        for (int i = 0; i < listeVoyageurs.size(); i++) {
            System.out.print((i + 1) + " : ");
            listeVoyageurs.get(i).afficher();
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
