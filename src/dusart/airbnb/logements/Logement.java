package dusart.airbnb.logements;

import dusart.airbnb.outils.Affichable;
import dusart.airbnb.outils.Comparatif;
import dusart.airbnb.utilisateurs.Hote;

public abstract class Logement implements Comparatif<Logement>, Affichable, Comparable<Logement> {
    private String name;
    private final Hote hote;
    private final int tarifParNuit;
    private final String adresse;
    private final int superficie;
    private final int nbVoyageursMax;

    /**
     * Unique constructeur pour définir un logement et ses conditions de location à partir de 5 paramètres
     *
     * @param paramHote le propriétaire du logement
     * @param paramTarif le tarif pour une nuit dans le logement
     * @param paramAdresse l'adresse du logement
     * @param paramSuperficie la superficie du logement
     * @param paramNbVoyageurs le nombre maximal de voyageurs que le logement peut accueillir simultanément
     */
    public Logement(Hote paramHote, int paramTarif, String paramAdresse, int paramSuperficie, int paramNbVoyageurs) {
        hote = paramHote;
        tarifParNuit = paramTarif;
        adresse = paramAdresse;
        superficie = paramSuperficie;
        nbVoyageursMax = paramNbVoyageurs;
    }

    public void setName(String pName) {
        name = pName;
    }

    public String getName() {
        return name;
    }

    /**
     * Renvoie le tarif d'une nuit dans le logement
     *
     * @return (int) le tarif pour une nuit
     */
    public int getTarifParNuit() {
        return tarifParNuit;
    }

    public Hote getHote() {
        return hote;
    }

    public String getAdresse() {
        return adresse;
    }

    public int getSuperficie() {
        return superficie;
    }

    public int getNbVoyageursMax() {
        return nbVoyageursMax;
    }

    public abstract int getSuperficieTotale();

    public abstract void afficher();

    /**
     * Affiche le propriétaire, l'adresse et la surface du logement dans la console
     */
    protected void afficherLogement() {
        hote.afficher();
        System.out.println();
        System.out.println("Le logement est situé " + adresse);
        System.out.println("Superficie : " + superficie + "m2");
        System.out.println("Tarif : " + tarifParNuit + "€");
        System.out.println("NbVoyageurs : " + nbVoyageursMax);
    }

    public Logement getHigher(Logement logement2) {
        return tarifParNuit > logement2.tarifParNuit ? this : logement2;
    }

    public Logement getLower(Logement logement2) {
        return tarifParNuit < logement2.tarifParNuit ? this : logement2;
    }

    @Override
    public int compareTo(Logement l) {
        return tarifParNuit - l.tarifParNuit;
    }
}
