package dusart.airbnb.logements;

import dusart.airbnb.outils.AirBnBData;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Search {
    private static final int NO = 0;
    private static final int YES = 1;
    private static final int NEITHER = 2;

    // alternative (pas idéale ici) aux constantes ci-dessus
    // pour exemple (Rq : on peut aussi ajouter des méthodes à chaque option, etc)
    public enum SearchChoice {
        YES(0, "YES"),
        NO(1, "NO"),
        NEITHER(2, "NEITHER");

        private String name;
        private int value;

        private SearchChoice(int value, String name) {
            this.value = value;
            this.name = name;
        }

        public int getValue() {
            return value;
        }
    }

    // required
    private final int nbVoyageurs;

    // optional
    private final int tarifMinParNuit;
    private final int tarifMaxParNuit;
    private final int possedePiscine;
    private final int possedeJardin;
    private final int possedeBalcon;

    private Search(SearchBuilder builder) {
        nbVoyageurs = builder.nbVoyageurs;
        tarifMinParNuit = builder.tarifMinParNuit;
        tarifMaxParNuit = builder.tarifMaxParNuit;
        possedePiscine = builder.possedePiscine;
        possedeJardin = builder.possedeJardin;
        possedeBalcon = builder.possedeBalcon;
    }

    public ArrayList<Logement> result() {
        ArrayList<Logement> result = new ArrayList<>();
        ArrayList<Logement> full = (ArrayList<Logement>) AirBnBData.getInstance().getListe(Logement.class);

        for(int i = 0; i < full.size(); i++) {
            Logement logement = full.get(i);

            if(logement.getNbVoyageursMax() < nbVoyageurs) {
                continue;
            }

            if(logement.getTarifParNuit() < tarifMinParNuit || logement.getTarifParNuit() > tarifMaxParNuit) {
                continue;
            }

            if(possedePiscine == 1 && (logement instanceof Appartement || !((Maison) logement).getPossedePiscine())) {
                continue;
            } else if(possedePiscine == 0 && logement instanceof Maison && ((Maison) logement).getPossedePiscine()) {
                continue;
            }

            if(possedeJardin == 1 && (logement instanceof Appartement || !((Maison) logement).getPossedeJardin())) {
                continue;
            } else if(possedeJardin == 0 && logement instanceof Maison && ((Maison) logement).getPossedeJardin()) {
                continue;
            }

            if(possedeBalcon == 1 && (logement instanceof Maison || !((Appartement) logement).getPossedeBalcon())) {
                continue;
            } else if(possedeBalcon == 0 && logement instanceof Appartement && ((Appartement) logement).getPossedeBalcon()) {
                continue;
            }

            result.add(logement);
        }
        return result;
    }

    public ArrayList<Logement> result2() {
        ArrayList<Logement> result = (ArrayList<Logement>) AirBnBData.getInstance().getListe(Logement.class).clone();
        result.removeIf(l -> (l.getNbVoyageursMax() < nbVoyageurs));
        result.removeIf(l -> (l.getTarifParNuit() < tarifMinParNuit || l.getTarifParNuit() > tarifMaxParNuit));
        if(possedePiscine == 1) {
            result.removeIf(l -> (l instanceof Appartement || !((Maison) l).getPossedePiscine()));
        } else if (possedePiscine == 0) {
            result.removeIf(l -> (l instanceof Maison && ((Maison) l).getPossedePiscine()));
        }
        if(possedeJardin == 1) {
            result.removeIf(l -> (l instanceof Appartement || !((Maison) l).getPossedeJardin()));
        } else if (possedeJardin == 0) {
            result.removeIf(l -> (l instanceof Maison && ((Maison) l).getPossedeJardin()));
        }
        if(possedeBalcon == 1) {
            result.removeIf(l -> (l instanceof Maison || !((Appartement) l).getPossedeBalcon()));
        } else if (possedeBalcon == 0) {
            result.removeIf(l -> (l instanceof Appartement && ((Appartement) l).getPossedeBalcon()));
        }
        return result;
    }

    public ArrayList<Logement> result3() {
        // test avec filter (uniquement sur nbVoyageurs ici)
        ArrayList<Logement> result = (ArrayList<Logement>) AirBnBData.getInstance().getListe(Logement.class);
        return (ArrayList<Logement>) result.stream().filter(l -> l.getNbVoyageursMax() >= nbVoyageurs).collect(Collectors.toList());
    }

    // avec Predicates
    public ArrayList<Logement> result4() {
        ArrayList<Logement> result = (ArrayList<Logement>) AirBnBData.getInstance().getListe(Logement.class);
        return (ArrayList<Logement>) result.stream()
                .filter(predicateNbVoyageurs()
                        .and(predicateTarif())
                        .and(predicateJardin())
                        .and(predicatePiscine())
                        .and(predicateBalcon())
                )
                .collect(Collectors.toList());
    }

    public Predicate<Logement> predicateNbVoyageurs() {
        return logement -> nbVoyageurs <= logement.getNbVoyageursMax();
    }

    public Predicate<Logement> predicateTarif() {
        return logement -> logement.getTarifParNuit() >= tarifMinParNuit && logement.getTarifParNuit() <= tarifMaxParNuit;
    }

    public Predicate<Logement> predicatePiscine() {
        if(possedePiscine == 0) {
            return logement -> logement instanceof Appartement || !((Maison) logement).getPossedePiscine();
        } else if (possedePiscine == 1) {
            return logement -> logement instanceof Maison && ((Maison) logement).getPossedePiscine();
        }
        return logement -> true;
    }

    public Predicate<Logement> predicateJardin() {
        if(possedeJardin == 0) {
            return logement -> logement instanceof Appartement || !((Maison) logement).getPossedeJardin();
        } else if (possedeJardin == 1) {
            return logement -> logement instanceof Maison && ((Maison) logement).getPossedeJardin();
        }
        return logement -> true;
    }

    public Predicate<Logement> predicateBalcon() {
        if(possedeBalcon == 0) {
            return logement -> logement instanceof Maison || !((Appartement) logement).getPossedeBalcon();
        } else if (possedeBalcon == 1) {
            return logement -> logement instanceof Appartement && ((Appartement) logement).getPossedeBalcon();
        }
        return logement -> true;
    }

    // Builder Class
    public static class SearchBuilder {
        // required
        private final int nbVoyageurs;

        // optional
        private int tarifMinParNuit;
        private int tarifMaxParNuit;
        private int possedePiscine;
        private int possedeJardin;
        private int possedeBalcon;

        public SearchBuilder(int pNbVoyageurs) {
            nbVoyageurs = pNbVoyageurs;
            tarifMinParNuit = 0; // initialisation au tarif min autorisé pour une nuit
            tarifMaxParNuit = Integer.MAX_VALUE; // initialisation au max possible
            possedePiscine = NEITHER; // initialisation à "indifférent"
            possedeJardin = NEITHER; // initialisation à "indifférent"
            possedeBalcon = NEITHER; // initialisation à "indifférent"
        }

        /**
         * @param pTarifMinParNuit
         * @return lui-même
         */
        public SearchBuilder tarifMinParNuit(int pTarifMinParNuit) {
            if(pTarifMinParNuit >= 0) {
                tarifMinParNuit = pTarifMinParNuit;
            }
            return this;
        }

        /**
         * @param pTarifMaxParNuit
         * @return lui-même
         */
        public SearchBuilder tarifMaxParNuit(int pTarifMaxParNuit) {
            if(pTarifMaxParNuit >= 0) {
                tarifMaxParNuit = pTarifMaxParNuit;
            }
            return this;
        }

        /**
         * @param pPossedePiscine
         * @return lui-même
         */
        public SearchBuilder possedePiscine(boolean pPossedePiscine) {
            possedePiscine = pPossedePiscine ? YES : NO;
            return this;
        }

        /**
         * @param pPossedeJardin
         * @return lui-même
         */
        public SearchBuilder possedeJardin(boolean pPossedeJardin) {
            possedeJardin = pPossedeJardin ? YES : NO;
            return this;
        }

        /**
         * @param pPossedeBalcon
         * @return lui-même
         */
        public SearchBuilder possedeBalcon(boolean pPossedeBalcon) {
            possedeBalcon = pPossedeBalcon ? YES : NO;
            return this;
        }

        public Search build() {
            Search search =  new Search(this);
            return search;
        }
    }
}
