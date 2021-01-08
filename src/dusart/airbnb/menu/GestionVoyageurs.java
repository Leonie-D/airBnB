package dusart.airbnb.menu;

import dusart.airbnb.utilisateurs.Voyageur;

import java.util.InputMismatchException;

public class GestionVoyageurs {
    /**
     * Affiche la liste des voyageurs et le sous-menu "voyageur"
     */
    static void listerVoyageurs() {
        System.out.println("-------------------------------------");
        System.out.println("Liste des voyageurs ");
        Menu.afficherListeVoyageurs();
        System.out.println("-------------------------------------");
        System.out.println("Saisir une option : ");
        System.out.println("1 : Ajouter un voyageur");
        System.out.println("2 : Supprimer un voyageur");
        System.out.println("3 : Retour");
        switch (Menu.choix(3)) {
            case 1:
                try {
                    ajouterVoyageur();
                } catch(InputMismatchException e) {
                    Menu.scanner.next();
                    System.out.println("Un problème est survenu lors de la saisie");
                } catch(Exception e) {
                    System.out.println(e.getMessage());
                }
                listerVoyageurs();
                break;
            case 2:
                try {
                    supprimerVoyageur();
                } catch(InputMismatchException e) {
                    Menu.scanner.next();
                    System.out.println("Un problème est survenu lors de la saisie");
                } catch(Exception e) {
                    System.out.println(e.getMessage());
                }
                listerVoyageurs();
                break;
            case 3:
                Menu.listerMenu();
                break;

        }
    }

    /**
     * Demande la saisie des informations et crée l'voyageur associé
     * @throws Exception dans le cas ou le prénom contient des caractères autres que des lettres, ou si la longueur n'est pas valide
     */
    private static void ajouterVoyageur() throws Exception {
        System.out.print("Prénom de le voyageur : ");
        String prenom = Menu.scanner.next();
        if(!prenom.matches("[a-zA-Z]*")) {
            throw new Exception("Merci de saisir un prénom conforme");
        } else if(prenom.length() < 3 || prenom.length() > 20) {
            throw new Exception("Merci de saisir un prénom contenant entre 3 et 20 caractères");
        }

        System.out.print("Nom de le voyageur : ");
        String nom = Menu.scanner.next();
        if(!nom.matches("[a-zA-Z]*")) {
            throw new Exception("Merci de saisir un nom conforme");
        } else if(nom.length() < 3 || nom.length() > 20) {
            throw new Exception("Merci de saisir un nom contenant entre 3 et 20 caractères");
        }

        System.out.print("Age de le voyageur : ");
        int age = Menu.scanner.nextInt();
        if(age < 18) {
            throw new Exception("Le voyageur doit être majeur");
        } else if (age > 120) {
            throw new Exception("Etes-vous certain d'avoir plus de 120 ans ?");
        }

        Voyageur voyageur = new Voyageur(prenom, nom, age);
        System.out.println("Un nouveau voyageur à bien été enregistré : ");
        voyageur.afficher();
        System.out.println();
        Menu.listeVoyageurs.add(voyageur);
    }

    /**
     * Demande et supprime le voyageur correspondant à l'indice saisi
     * @throws Exception si l'indice ne correspond pas à un voyageur existant ou si n'est pas un entier
     */
    private static void supprimerVoyageur() throws Exception {
        System.out.println("-------------------------------------");
        Menu.afficherListeVoyageurs();
        System.out.println("Merci d'indiquer le numéro du voyageur à supprimer : ");
        int indiceVoyageur = Menu.scanner.nextInt() - 1;
        if(indiceVoyageur < 0 || indiceVoyageur >= Menu.listeVoyageurs.size()) {
            throw new Exception("Aucun voyageur à cet indice");
        }

        Menu.listeVoyageurs.get(indiceVoyageur).afficher();
        System.out.println(" a bien été supprimé.");
        Menu.listeVoyageurs.remove(indiceVoyageur);
        Menu.afficherListeVoyageurs();
    }
}
