package dusart.airbnb.menu;

import dusart.airbnb.utilisateurs.Hote;

import java.util.InputMismatchException;

public class GestionHotes {
    static void listerHotes() {
        System.out.println("-------------------------------------");
        System.out.println("Liste des hôtes ");
        afficherListeHotes();
        System.out.println("-------------------------------------");
        System.out.println("Saisir une option : ");
        System.out.println("1 : Ajouter un hôte");
        System.out.println("2 : Supprimer un hôte");
        System.out.println("3 : Retour");
        switch (Menu.choix(3)) {
            case 1:
                try {
                    ajouterHote();
                } catch(InputMismatchException e) {
                    Menu.scanner.next();
                    System.out.println("Un problème est survenu lors de la saisie");
                } catch(Exception e) {
                    System.out.println(e.getMessage());
                }
                Menu.listerMenu();
                break;
            case 2:
                try {
                    supprimerHote();
                } catch(InputMismatchException e) {
                    Menu.scanner.next();
                    System.out.println("Un problème est survenu lors de la saisie");
                } catch(Exception e) {
                    System.out.println(e.getMessage());
                }
                Menu.listerMenu();
                break;
            case 3:
                Menu.listerMenu();
                break;

        }
    }

    private static void afficherListeHotes() {
        for (int i = 0; i < Menu.listeHotes.size(); i++) {
            System.out.print((i + 1) + " : ");
            Menu.listeHotes.get(i).afficher();
            System.out.println();
        }
    }

    private static void ajouterHote() throws Exception {
        System.out.print("Prénom de l'hôte : ");
        String prenom = Menu.scanner.next();
        if(!prenom.matches("[a-zA-Z]*")) {
            throw new Exception("Merci de saisir un prénom conforme");
        } else if(prenom.length() < 3 || prenom.length() > 20) {
            throw new Exception("Merci de saisir un prénom contenant entre 3 et 20 caractères");
        }

        System.out.print("Nom de l'hôte : ");
        String nom = Menu.scanner.next();
        if(!nom.matches("[a-zA-Z]*")) {
            throw new Exception("Merci de saisir un nom conforme");
        } else if(nom.length() < 3 || nom.length() > 20) {
            throw new Exception("Merci de saisir un nom contenant entre 3 et 20 caractères");
        }

        System.out.print("Age de l'hôte : ");
        int age = Menu.scanner.nextInt();
        if(age < 18) {
            throw new Exception("L'hôte doit être majeur");
        } else if (age > 120) {
            throw new Exception("Etes-vous certain d'avoir plus de 120 ans ?");
        }

        System.out.print("Delai de réponse de l'hôte (en heures) : ");
        int delaiDeReponse = Menu.scanner.nextInt();
        if(delaiDeReponse < 1 || delaiDeReponse > 72) {
            throw new Exception("Le delai de réponse doit être compris entre 1 et 72 heures");
        }

        Hote hote = new Hote(prenom, nom, age, delaiDeReponse);
        System.out.println("Un nouvel hôte à bien été enregistré : ");
        hote.afficher();
        System.out.println();
        Menu.listeHotes.add(hote);
    }

    private static void supprimerHote() throws Exception {
        System.out.println("-------------------------------------");
        afficherListeHotes();
        System.out.println("Merci d'indiquer le numéro de l'hôte à supprimer : ");
        int indiceHote = Menu.scanner.nextInt() - 1;
        if(indiceHote < 0 || indiceHote >= Menu.listeHotes.size()) {
            throw new Exception("Aucun hôte à cet indice");
        }

        Menu.listeHotes.get(indiceHote).afficher();
        System.out.println(" a bien été supprimé.");
        Menu.listeHotes.remove(indiceHote);
        afficherListeHotes();
    }
}
