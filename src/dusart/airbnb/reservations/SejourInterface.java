package dusart.airbnb.reservations;

public interface SejourInterface {
    public boolean verficationDateArrivee();
    public boolean verificationNombreDeNuits();
    public boolean verificationNombreDeNuits(int nbNuits);
    public boolean verificationNombreDeVoyageurs();
    public void afficher();
}
