package dusart.airbnb.utilisateurs;

public class Personne {
    private String prenom;
    private String nom;
    private int age;

    /**
     * Unique constructeur pour définir une nouvelle personne à partir de trois paramètres
     *
     * @param paramPrenom le prénom de la personne
     * @param paramNom le nom de la personne
     * @param paramAge l'âge de la personne
     */
    public Personne(String paramPrenom, String paramNom, int paramAge) {
        prenom = paramPrenom;
        nom = paramNom;
        age = paramAge;
    }

    /**
     * Affiche les informations personnelles de la personne dans la console
     */
    public void afficher() {
        System.out.print(prenom + " " + nom + " (" + age + " ans)");
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Personne personneComparee = (Personne) object;
        return nom.equals(personneComparee.nom) &&
                prenom.equals(personneComparee.prenom) &&
                age == personneComparee.age;
    }
}
