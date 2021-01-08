package dusart.airbnb.menu;

import dusart.airbnb.logements.Appartement;
import dusart.airbnb.logements.Logement;
import dusart.airbnb.logements.Maison;

import java.util.InputMismatchException;

public class GestionLogements {
    /**
     * Affiche la liste des logements et le sous-menu "logement"
     */
    static void listerLogements() {
        System.out.println("-------------------------------------");
        System.out.println("Liste des logements ");
        Menu.afficherListeLogements();
        System.out.println("-------------------------------------");
        System.out.println("Saisir une option : ");
        System.out.println("1 : Ajouter un logement");
        System.out.println("2 : Supprimer un logement");
        System.out.println("3 : Retour");
        switch (Menu.choix(3)) {
            case 1:
                try {
                    if(Menu.listeHotes.size() == 0) {
                        System.out.println("Veuillez au préalable renseigner un hôte");
                        GestionHotes.listerHotes();
                    } else {
                        ajouterLogement();
                    }
                } catch(InputMismatchException e) {
                    Menu.scanner.next();
                    System.out.println("Un problème est survenu lors de la saisie");
                } catch(Exception e) {
                    System.out.println(e.getMessage());
                }
                listerLogements();
                break;
            case 2:
                try {
                    supprimerLogement();
                } catch(InputMismatchException e) {
                    Menu.scanner.next();
                    System.out.println("Un problème est survenu lors de la saisie");
                } catch(Exception e) {
                    System.out.println(e.getMessage());
                }
                listerLogements();
                break;
            case 3:
                Menu.listerMenu();
                break;

        }
    }

    private static void ajouterLogement() throws Exception {
        int superficieJardin = 0;
        boolean disposePiscine = false;
        int superficieBalcon = 0;
        int numeroEtage = 0;

        System.out.println("A qui appartient le logement ?");
        Menu.afficherListeHotes();
        int indiceHote = Menu.scanner.nextInt() - 1;
        if(indiceHote < 0 || indiceHote >= Menu.listeHotes.size()) {
            throw new Exception("Aucun hôte à cet indice");
        }

        System.out.println("Ou se situe le logement ?");
        String adresse = Menu.scanner.next();

        System.out.println("Est-ce une maison : tapez 1 ou un appartement : tapez 2 ?");
        int typeLogement = Menu.scanner.nextInt();
        if(typeLogement < 1 || typeLogement > 2) {
            throw new Exception("Pour une maison tapez 1, pour un appartement tapez 2");
        } else if(typeLogement == 2) {
            System.out.println("A quel étage se situe l'appartement ?");
            numeroEtage = Menu.scanner.nextInt();
            if(numeroEtage < 0 || numeroEtage > 30) {
                throw new Exception("Merci de saisir un numéro d'étage plausible");
            }

            System.out.println("Quelle est la superficie du balcon (0 si abscence) ?");
            superficieBalcon = Menu.scanner.nextInt();
            if(superficieBalcon < 0) {
                throw new Exception("Une superficie ne peut-être négative");
            }
        } else {
            System.out.println("Quelle superficie pour le jardin ?");
            superficieJardin = Menu.scanner.nextInt();
            if(superficieJardin < 0) {
                throw new Exception("Une superficie ne peut-être négative");
            }

            System.out.println("La maison dispose-t-elle d'une piscine (1 pour oui, 0 pour non)?");
            int presencePiscine = Menu.scanner.nextInt();
            if(presencePiscine < 0 || presencePiscine > 1) {
                throw new Exception("Tapez 1 si la maison dispose d'une piscine, 0 sinon");
            } else if(presencePiscine == 0) {
                disposePiscine = false;
            } else {
                disposePiscine = true;
            }
        }

        System.out.println("Quel est la superficie du logement ?");
        int superficie = Menu.scanner.nextInt();
        if(superficie < 0) {
            throw new Exception("Une superficie ne peut-être négative");
        }

        System.out.println("Combien le logement peut-il accueillir de voyageurs au maximum ?");
        int nbVoyageursMax = Menu.scanner.nextInt();

        System.out.println("Quel tarif pour une nuit ?");
        int tarif = Menu.scanner.nextInt();
        if(tarif < 0) {
            throw new Exception("Vous souhaitez réellement payer des voyageurs pour qu'ils dorment dans votre logement ?");
        }

        Logement logement;
        if(typeLogement == 1) {
            logement = new Maison(Menu.listeHotes.get(indiceHote), tarif, adresse, superficie, nbVoyageursMax, superficieJardin, disposePiscine);
        } else {
            logement = new Appartement(Menu.listeHotes.get(indiceHote), tarif, adresse, superficie, nbVoyageursMax, numeroEtage, superficieBalcon);
        }
        System.out.println("Un nouveau logement à bien été enregistré : ");
        logement.afficher();
        System.out.println();
        Menu.listeLogements.add(logement);
    }

    private static void supprimerLogement() throws Exception {
        System.out.println("-------------------------------------");
        Menu.afficherListeLogements();
        System.out.println("Merci d'indiquer le numéro du logement à supprimer : ");
        int indiceLogement = Menu.scanner.nextInt() - 1;
        if(indiceLogement < 0 || indiceLogement >= Menu.listeLogements.size()) {
            throw new Exception("Aucun logement à cet indice");
        }

        Menu.listeLogements.remove(indiceLogement);
        System.out.println("Le logement a bien été supprimé.");
        Menu.afficherListeLogements();
    }
}
