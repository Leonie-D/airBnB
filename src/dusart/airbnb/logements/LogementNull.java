package dusart.airbnb.logements;

import dusart.airbnb.utilisateurs.Hote;

public class LogementNull extends Logement {

    public LogementNull() {
        super(null, 0, "", 0, 0);
    }

    @Override
    public int getSuperficieTotale() {
        return 0;
    }

    @Override
    public void afficher() {
        System.out.println();
    }
}
