package dusart.airbnb.utilisateurs;

public class Voyageur extends Personne {
    /**
     * Unique constructeur pour définir un nouveau voyageur à partir de trois paramètres
     *
     * @param paramPrenom le prénom du voyageur
     * @param paramNom le nom du voyageur
     * @param paramAge l'âge du voyageur
     */
    public Voyageur(String paramPrenom, String paramNom, int paramAge) {
        super(paramPrenom, paramNom, paramAge);
    }
}
