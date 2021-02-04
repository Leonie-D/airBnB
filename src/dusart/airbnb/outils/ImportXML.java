package dusart.airbnb.outils;

import dusart.airbnb.logements.Appartement;
import dusart.airbnb.logements.Logement;
import dusart.airbnb.logements.Maison;
import dusart.airbnb.utilisateurs.Hote;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;

public class ImportXML {
    public static void importLogements(String pathToFile, ArrayList<Logement> listeLogements, ArrayList<Hote> listeHotes) {
        try {

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(pathToFile);

            doc.getDocumentElement().normalize();
            NodeList appartList = doc.getElementsByTagName("Appartement");
            NodeList maisonList = doc.getElementsByTagName("Maison");

            for (int temp = 0; temp < appartList.getLength(); temp++) {

                Node nNode = appartList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;
                    Element hoteElement = (Element) eElement.getElementsByTagName("hote").item(0);

                    Hote hote = createHote(hoteElement, listeHotes);
                    Appartement appart = new Appartement(hote, Integer.parseInt(eElement.getElementsByTagName("tarifParNuit").item(0).getTextContent()), eElement.getElementsByTagName("adresse").item(0).getTextContent(), Integer.parseInt(eElement.getElementsByTagName("superficie").item(0).getTextContent()), Integer.parseInt(eElement.getElementsByTagName("nbVoyageursMax").item(0).getTextContent()), Integer.parseInt(eElement.getElementsByTagName("numeroEtage").item(0).getTextContent()), Integer.parseInt(eElement.getElementsByTagName("superficieBalcon").item(0).getTextContent()));
                    listeLogements.add(appart);
                }
            }

            for (int temp = 0; temp < maisonList.getLength(); temp++) {

                Node nNode = maisonList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;
                    Element hoteElement = (Element) eElement.getElementsByTagName("hote").item(0);

                    Hote hote = createHote(hoteElement, listeHotes);
                    Maison maison = new Maison(hote, Integer.parseInt(eElement.getElementsByTagName("tarifParNuit").item(0).getTextContent()), eElement.getElementsByTagName("adresse").item(0).getTextContent(), Integer.parseInt(eElement.getElementsByTagName("superficie").item(0).getTextContent()), Integer.parseInt(eElement.getElementsByTagName("nbVoyageursMax").item(0).getTextContent()), Integer.parseInt(eElement.getElementsByTagName("superficieJardin").item(0).getTextContent()), eElement.getElementsByTagName("possedePiscine").item(0).getTextContent().equals("1"));
                    listeLogements.add(maison);
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static Hote createHote(Element hoteElement, ArrayList<Hote> listeHotes) {
        Hote hote = new Hote(hoteElement.getElementsByTagName("nom").item(0).getTextContent(), hoteElement.getElementsByTagName("prenom").item(0).getTextContent(), Integer.parseInt(hoteElement.getElementsByTagName("age").item(0).getTextContent()), Integer.parseInt(hoteElement.getElementsByTagName("delaiReponse").item(0).getTextContent()));
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
        return hote;
    }
}
