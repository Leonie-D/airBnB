package dusart.airbnb.menu;

import dusart.airbnb.logements.*;
import dusart.airbnb.outils.*;
import dusart.airbnb.reservations.*;
import dusart.airbnb.utilisateurs.*;

import java.util.*;

public class Menu {
    static Scanner scanner;
    static ArrayList<Hote> listeHotes;
    static ArrayList<Logement> listeLogements;
    static ArrayList<Voyageur> listeVoyageurs;
    static ArrayList<Reservation> listeReservations;

    public static void main(String[] args) {
        /*ArrayList<String> participants = new ArrayList<>();
        participants.add("Louis");
        participants.add("Jean");
        participants.add("Julien");
        participants.add("Margaux");
        participants.add("Manon");
        participants.add("Juliette");
        participants.add("Marc");
        participants.add("Tom");
        participants.add("Jean-Philippe");

        participants.stream().sorted((nom1, nom2) -> Integer.compare(nom1.length(), nom2.length())).forEach(System.out::println);*/

        // initialisation des listes;
        AirBnBData data = AirBnBData.getInstance();
        listeLogements = (ArrayList<Logement>) data.getListe(Logement.class);
        listeHotes = (ArrayList<Hote>) data.getListe(Hote.class);
        listeVoyageurs = (ArrayList<Voyageur>) data.getListe(Voyageur.class);
        listeReservations = (ArrayList<Reservation>) data.getListe(Reservation.class);

        Voyageur camille = new Voyageur("Camille", "Gérard", 28);
        listeVoyageurs.add(camille);

        Search.SearchBuilder builder = new Search.SearchBuilder(2)
                .possedePiscine(false)
                .tarifMaxParNuit(100)
                .possedeJardin(false);
        Search search = builder.build();

        System.out.println("///--------------------------///");
        ArrayList<Logement> recherche3 = search.result4();
        for(Logement logement : recherche3) {
            System.out.println("--------------------------");
            logement.afficher();
        }
        System.out.println("///--------------------------///");

        recherche3.stream().sorted((l1, l2) -> Integer.reverse((Integer) Integer.compare(l1.getTarifParNuit(), l2.getTarifParNuit()))).forEach(Logement::afficher);
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

        /*MaDate dateArrivee = new MaDate(12, 2, 2021);
        SejourLong sejour = new SejourLong(dateArrivee, 15, listeLogements.get(0), 4);*/

        //sejour.setLogement(listeLogements.get(2));
        //sejour.setDateArrivee(new MaDate(1, 11, 2020));
        //sejour.setDateArrivee(new MaDate(1, 11, 2021));
        //sejour.setNbNuits(17);
        //sejour.setNbNuits(3);
        //sejour.setNbNuits(35);

        /*try {
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
        }*/

        //sejour.afficher();
        // copie défensive
        //SendEmailReservation.sendEmail((Sejour) sejour.clone());
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
     * Affiche les listes
     */
    static void afficherListe(ArrayList<? extends Affichable> liste) {
        for (int i = 0; i < liste.size(); i++) {
            System.out.print((i + 1) + " : ");
            liste.get(i).afficher();
            System.out.println();
        }
    }
}
