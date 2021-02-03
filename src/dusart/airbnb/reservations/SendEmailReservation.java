package dusart.airbnb.reservations;

public class SendEmailReservation {

    /**
     * @param reservation non null
     */
    public static void sendEmail(Reservation reservation) {
        if(reservation == null) {
            throw new IllegalArgumentException("Pas de réservation...");
        } else {
            System.out.println("Envoi de l'email");
            reservation.setEstValidee(true);
        }
    }

    public static void sendEmail(Sejour sejour) {
        sejour.setNbNuits(12);
    }
}
