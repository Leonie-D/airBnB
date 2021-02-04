package dusart.airbnb.logements;

import dusart.airbnb.outils.AirBnBData;

import java.util.ArrayList;

public class Search {
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
            boolean keep = true;
            Logement logement = full.get(i);

            if(logement.getNbVoyageursMax() < nbVoyageurs) {
                keep = false;
            }

            if(logement.getTarifParNuit() < tarifMinParNuit || logement.getTarifParNuit() > tarifMaxParNuit) {
                keep = false;
            }

            if(possedePiscine == 1 && (logement instanceof Appartement || !((Maison) logement).getPossedePiscine())) {
                keep = false;
            } else if(possedePiscine == 0 && logement instanceof Maison && ((Maison) logement).getPossedePiscine()) {
                keep = false;
            }

            if(possedeJardin == 1 && (logement instanceof Appartement || !((Maison) logement).getPossedeJardin())) {
                keep = false;
            } else if(possedeJardin == 0 && logement instanceof Maison && ((Maison) logement).getPossedeJardin()) {
                keep = false;
            }

            if(possedeBalcon == 1 && (logement instanceof Maison || !((Appartement) logement).getPossedeBalcon())) {
                keep = false;
            } else if(possedeBalcon == 0 && logement instanceof Appartement && ((Appartement) logement).getPossedeBalcon()) {
                keep = false;
            }

            if(keep) {
                result.add(logement);
            }
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

    public void afficher() {
        System.out.println("nbVoyageurs : " + nbVoyageurs);
        System.out.println("tarifMinParNuit : " + tarifMinParNuit);
        System.out.println("tarifMaxParNuit : " + tarifMaxParNuit);
        System.out.println("possedePiscine : " + possedePiscine);
        System.out.println("possedeJardin : " + possedeJardin);
        System.out.println("possedeBalcon : " + possedeBalcon);
    }

    // Builder Class
    public static class SearchBuilder {
        // required
        private final int nbVoyageurs;

        // optional
        private int tarifMinParNuit = 0; // initialisation au tarif min autorisé pour une nuit
        private int tarifMaxParNuit = 1000; // initialisation au tarif max autorisé pour une nuit
        private int possedePiscine = 3; // initialisation à "indifférent"
        private int possedeJardin = 3; // initialisation à "indifférent"
        private int possedeBalcon = 3; // initialisation à "indifférent"

        public SearchBuilder(int pNbVoyageurs) {
            nbVoyageurs = pNbVoyageurs;
        }

        /**
         * @param pTarifMinParNuit entre 0 et 1000 / par défaut 0
         * @return lui-même
         */
        public SearchBuilder tarifMinParNuit(int pTarifMinParNuit) {
            if(pTarifMinParNuit >= 0 && pTarifMinParNuit <= 1000) {
                tarifMinParNuit = pTarifMinParNuit;
            }
            return this;
        }

        /**
         * @param pTarifMaxParNuit entre 0 et 1000 / par défaut 1000
         * @return lui-même
         */
        public SearchBuilder tarifMaxParNuit(int pTarifMaxParNuit) {
            if(pTarifMaxParNuit >= 0 && pTarifMaxParNuit <= 1000) {
                tarifMaxParNuit = pTarifMaxParNuit;
            }
            return this;
        }

        /**
         * @param pPossedePiscine 0 pour non, 1 pour oui / par défaut 3
         * @return lui-même
         */
        public SearchBuilder possedePiscine(int pPossedePiscine) {
            if(pPossedePiscine >= 0 && pPossedePiscine <= 1) {
                possedePiscine = pPossedePiscine;
            }
            return this;
        }

        /**
         * @param pPossedeJardin 0 pour non et 1 pour oui / par défaut 3
         * @return lui-même
         */
        public SearchBuilder possedeJardin(int pPossedeJardin) {
            if(pPossedeJardin >= 0 && pPossedeJardin <= 1) {
                possedeJardin = pPossedeJardin;
            }
            return this;
        }

        /**
         * @param pPossedeBalcon 0 pour non et 1 pour oui / par défaut 3
         * @return lui-même
         */
        public SearchBuilder possedeBalcon(int pPossedeBalcon) {
            if(pPossedeBalcon >= 0 && pPossedeBalcon <= 1) {
                possedeBalcon = pPossedeBalcon;
            }
            return this;
        }

        public Search build() {
            Search search =  new Search(this);
            return search;
        }
    }
}
