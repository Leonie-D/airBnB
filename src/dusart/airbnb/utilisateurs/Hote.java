package dusart.airbnb.utilisateurs;

public class Hote extends Personne {
    private final int delaiDeReponse;

    /**
     * Unique constructeur pour définir un nouvel hôte à partir de 4 paramètres
     *
     * @param paramPrenom le prénom de l'hôte
     * @param paramNom le nom de l'hôte
     * @param paramAge l'âge de l'hôte
     * @param paramDelaiDeReponse delai de réponse maximal de l'hôte
     */
    public Hote(String paramPrenom, String paramNom, int paramAge, int paramDelaiDeReponse) {
        super(paramPrenom, paramNom, paramAge);
        delaiDeReponse = paramDelaiDeReponse;
    }

    /**
     * Affiche les informations personnelles et le delai de réponse de l'hôte' dans la console
     */
    @Override
    public void afficher() {
        super.afficher();
        if(delaiDeReponse == 1) {
            System.out.print(" qui s'engage à répondre dans l'heure.");
        } else {
            System.out.print(" qui s'engage à répondre dans les " + delaiDeReponse + " heures.");
        }
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Hote hoteCompare = (Hote) object;
        return super.equals(hoteCompare) && delaiDeReponse == hoteCompare.delaiDeReponse;
    }

    @Override
    public Personne getHigher(Personne hote2){
        if(hote2 instanceof Hote) {
            return delaiDeReponse > ((Hote) hote2).delaiDeReponse ? this : hote2;
        } else {
            return super.getHigher(hote2);
        }
    }

    @Override
    public Personne getLower(Personne hote2){
        if(hote2 instanceof Hote) {
            return delaiDeReponse < ((Hote) hote2).delaiDeReponse ? this : hote2;
        } else {
            return super.getLower(hote2);
        }
    }
}
