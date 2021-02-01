package dusart.airbnb.menu;

import dusart.airbnb.logements.Appartement;
import dusart.airbnb.logements.Logement;
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

                    Hote hote = new Hote(hoteElement.getElementsByTagName("nom").item(0).getTextContent(), hoteElement.getElementsByTagName("prenom").item(0).getTextContent(), Integer.parseInt(hoteElement.getElementsByTagName("age").item(0).getTextContent()), Integer.parseInt(hoteElement.getElementsByTagName("delaiReponse").item(0).getTextContent()));
                    // Attention, tester si hote deja dans liste
                    listeHotes.add(hote);
                    Appartement appart = new Appartement(hote, Integer.parseInt(eElement.getElementsByTagName("tarifParNuit").item(0).getTextContent()), eElement.getElementsByTagName("adresse").item(0).getTextContent(), Integer.parseInt(eElement.getElementsByTagName("superficie").item(0).getTextContent()), Integer.parseInt(eElement.getElementsByTagName("nbVoyageursMax").item(0).getTextContent()), Integer.parseInt(eElement.getElementsByTagName("numeroEtage").item(0).getTextContent()), Integer.parseInt(eElement.getElementsByTagName("superficieBalcon").item(0).getTextContent()));
                    listeLogements.add(appart);
                }
            }

            // idem parcourir les maisons

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
