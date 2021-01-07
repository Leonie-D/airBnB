package dusart.airbnb.menu;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    static Scanner scanner;

    public static void main(String[] args) {
        System.out.println("Bienvenue chez AirBnB");
        scanner = new Scanner(System.in);
        listerMenu();
        scanner.close();
    }

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
                System.out.println("Liste des logements");
                break;
            case 3:
                System.out.println("Liste des voyageurs");
                break;
            case 4:
                System.out.println("Liste des réservations");
                break;
            case 5:
                break;
        }
    }

    static int choix(int maxValue) {
        int valueChoix;
        do {
            try {
                valueChoix = scanner.nextInt();
            } catch (InputMismatchException e) {
                scanner.next();
                System.out.println("Merci de saisir un entier");
                valueChoix = choix(maxValue);
            }
        } while (!verifierChoix(valueChoix, maxValue));
        return valueChoix;
    }

    private static boolean verifierChoix(int valueChoix, int maxValue) {
        if(valueChoix > 0 && valueChoix <= maxValue) {
            return true;
        } else {
            System.out.println("Merci de saisir un nombre entre 1 et " + maxValue);
            return false;
        }
    }
}
