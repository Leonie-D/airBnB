package dusart.airbnb.logements;

import dusart.airbnb.utilisateurs.Hote;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.util.ArrayList;

public class LogementsHandler extends DefaultHandler {

    private static final String APPARTEMENT = "Appartement";
    private static final String MAISON = "Maison";
    private static final String HOTE = "hote";
    private static final String TARIF_NUIT = "tarifParNuit";
    private static final String NOM = "nom";
    private static final String PRENOM = "prenom";
    private static final String AGE = "age";
    private static final String DELAI_REPONSE = "delaiReponse";
    private static final String ADRESSE = "adresse";
    private static final String SUPERFICIE = "superficie";
    private static final String SUPERFICIE_JARDIN = "superficieJardin";
    private static final String SUPERFICIE_BALCON = "superficieBalcon";
    private static final String NB_MAX_VOYAGEUR = "nbVoyageursMax";
    private static final String NUM_ETAGE = "numeroEtage";
    private static final String POSSEDE_PISCINE = "possedePiscine";

    private ArrayList<Logement> listeLogements = new ArrayList<Logement>();
    private ArrayList<Hote> listeHotes = new ArrayList<Hote>();

    private String elementValue;
    private Hote hote;
    private String nomHote;
    private String prenomHote;
    private int ageHote;
    private int delaiDeReponseHote;
    private int tarifLogement;
    private String adresseLogement;
    private int superficieLogement;
    private int nbVoyageursMax;
    private int numeroEtage;
    private int superficieBalcon;
    private boolean possedePiscine;
    private int superficieJardin;


    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        elementValue = new String(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case APPARTEMENT:
                Appartement appart = new Appartement(hote, tarifLogement, adresseLogement, superficieLogement, nbVoyageursMax, numeroEtage, superficieBalcon);
                listeLogements.add(appart);
                break;
            case MAISON:
                Maison maison = new Maison(hote, tarifLogement, adresseLogement, superficieLogement, nbVoyageursMax, superficieJardin, possedePiscine);
                listeLogements.add(maison);
                break;
            case HOTE :
                hote = new Hote(prenomHote, nomHote, ageHote, delaiDeReponseHote);
                // vérifier si l'hôte existe déjà avant ajout à la liste
                boolean ajouter = true;
                for (int i = 0; i < listeHotes.size(); i++) {
                    if(listeHotes.get(i).equals(hote)) {
                        ajouter = false;
                        hote = listeHotes.get(i);
                    }
                }
                if(ajouter) {
                    listeHotes.add(hote);
                }
                break;
            case NOM:
                nomHote = elementValue;
                break;
            case PRENOM:
                prenomHote = elementValue;
                break;
            case AGE:
                ageHote = Integer.parseInt(elementValue);
                break;
            case DELAI_REPONSE:
                delaiDeReponseHote = Integer.parseInt(elementValue);
                break;
            case TARIF_NUIT:
                tarifLogement = Integer.parseInt(elementValue);
                break;
            case ADRESSE:
                adresseLogement = elementValue;
                break;
            case SUPERFICIE:
                superficieLogement = Integer.parseInt(elementValue);
                break;
            case SUPERFICIE_JARDIN:
                superficieJardin = Integer.parseInt(elementValue);
                break;
            case SUPERFICIE_BALCON:
                superficieBalcon = Integer.parseInt(elementValue);
                break;
            case NB_MAX_VOYAGEUR:
                nbVoyageursMax = Integer.parseInt(elementValue);
                break;
            case NUM_ETAGE:
                numeroEtage = Integer.parseInt(elementValue);
                break;
            case POSSEDE_PISCINE:
                possedePiscine = elementValue.equals("1");
                break;
        }
    }

    public ArrayList<Logement> getListeLogements() {
        return listeLogements;
    }

    public ArrayList<Hote> getListeHotes() {
        return listeHotes;
    }

}
