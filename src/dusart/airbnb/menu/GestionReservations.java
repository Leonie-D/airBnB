package dusart.airbnb.menu;

import dusart.airbnb.outils.MaDate;
import dusart.airbnb.reservations.Reservation;
import dusart.airbnb.reservations.Sejour;
import dusart.airbnb.reservations.SejourCourt;
import dusart.airbnb.reservations.SejourLong;

import java.util.InputMismatchException;

public class GestionReservations {
    /**
     * Affiche la liste des réservations et le sous-menu "réservation"
     */
    static void listerReservations() {
        System.out.println("-------------------------------------");
        System.out.println("Liste des réservations ");
        Menu.afficherListeReservations();
        System.out.println("-------------------------------------");
        System.out.println("Saisir une option : ");
        System.out.println("1 : Ajouter une réservation");
        System.out.println("2 : Supprimer une réservation");
        System.out.println("3 : Retour");
        switch (Menu.choix(3)) {
            case 1:
                try {
                    ajouterReservation();
                } catch(InputMismatchException e) {
                    Menu.scanner.next();
                    System.out.println("Un problème est survenu lors de la saisie");
                } catch(Exception e) {
                    System.out.println(e.getMessage());
                }
                listerReservations();
                break;
            case 2:
                try {
                    supprimerReservation();
                } catch(InputMismatchException e) {
                    Menu.scanner.next();
                    System.out.println("Un problème est survenu lors de la saisie");
                } catch(Exception e) {
                    System.out.println(e.getMessage());
                }
                listerReservations();
                break;
            case 3:
                Menu.listerMenu();
                break;

        }
    }

    private  static void ajouterReservation() throws Exception {
        System.out.println("Veuillez saisir le numéro du voyageur pour lequel vous effectuez la réservation :");
        Menu.afficherListeVoyageurs();
        int indiceVoyageur = Menu.scanner.nextInt() - 1;
        if(indiceVoyageur < 0 || indiceVoyageur >= Menu.listeVoyageurs.size()) {
            throw new Exception("Aucun voyageur à cet indice");
        }

        System.out.println("Veuillez saisir le numéro du logement souhaité :");
        Menu.afficherListeLogements();
        int indiceLogement = Menu.scanner.nextInt() - 1;
        if(indiceLogement < 0 || indiceLogement >= Menu.listeLogements.size()) {
            throw new Exception("Aucun hôte à cet indice");
        }

        System.out.println("Quand voulez-vous arriver ?");
        System.out.println("Jour : ");
        int jour = Menu.scanner.nextInt();
        if(jour < 0 || jour > 31) {
            throw new Exception("Le jour doit être compris entre 1 et 31");
        }
        System.out.println("Mois :");
        int mois = Menu.scanner.nextInt();
        if(mois < 1 || mois > 12) {
            throw new Exception("Le mois doit être compris entre 1 et 12");
        }
        System.out.println("Annee :");
        int annee = Menu.scanner.nextInt();
        MaDate dateArrivee = new MaDate(jour, mois, annee);

        System.out.println("Combien de nuits souhaitez-vous rester ?");
        int nbNuits = Menu.scanner.nextInt();

        System.out.println("Combien de voyageurs serez-vous ?");
        int nbVoyageurs = Menu.scanner.nextInt();

        Sejour sejour;
        if(nbNuits < 6) {
            sejour = new SejourCourt(dateArrivee, nbNuits, Menu.listeLogements.get(indiceLogement), nbVoyageurs);
        } else {
            sejour = new SejourLong(dateArrivee, nbNuits, Menu.listeLogements.get(indiceLogement), nbVoyageurs);
        }

        try {
            Reservation reservation = new Reservation(sejour, Menu.listeVoyageurs.get(indiceVoyageur), Menu.listeReservations.size());
            System.out.println("Une nouvelle réservation à bien été enregistrée.");
            Menu.listeReservations.add(reservation);
            listerReservations();
        } catch (Exception e) {
            throw e;
        }
    }

    private static void supprimerReservation() {

    }
}