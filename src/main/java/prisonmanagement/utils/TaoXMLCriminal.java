/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prisonmanagement.utils;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import prisonmanagement.model.Criminal;

/**
 *
 * @author 84768
 */
public class TaoXMLCriminal {

    public static void main(String[] args) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.newDocument();

            Element CriminalInformation = doc.createElement("CriminalInformation");
            Criminal Cr1 = new Criminal("1", "Phạm Hải Nam", "19/10/2003", "nam", "10", "613", "Qua dep trai");
            addCriminal(doc, CriminalInformation, Cr1);

            doc.appendChild(CriminalInformation);

            TransformerFactory tff = TransformerFactory.newInstance();
            try {
                Transformer tf = tff.newTransformer();
                tf.setOutputProperty(OutputKeys.INDENT, "yes");
                tf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "3");
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult("src/Criminal.xml");
                tf.transform(source, result);
                System.out.println("Ghi file thanh cong");

            } catch (TransformerConfigurationException ex) {
                Logger.getLogger(TaoXMLCriminal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (TransformerException ex) {
                Logger.getLogger(TaoXMLCriminal.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (ParserConfigurationException ex) {
            Logger.getLogger(TaoXMLCriminal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    static private void addCriminal(Document doc, Element CriminalInformation, Criminal Cr) {
        Element Criminal = doc.createElement("Criminal");

        Element ID = doc.createElement("ID");
        ID.setTextContent(Cr.getID().toString());

        Element Name = doc.createElement("Name");
        Name.setTextContent(Cr.getName());

        Element DateOfBirth = doc.createElement("DateOfBirth");
        DateOfBirth.setTextContent(Cr.getDateOfBitrh());

        Element Gender = doc.createElement("Gender");
        Gender.setTextContent(Cr.getGender());

        Element Year = doc.createElement("Year");
        Year.setTextContent(Cr.getYear());

        Element Room = doc.createElement("Room");
        Room.setTextContent(Cr.getRoom());

        Element Crime = doc.createElement("Crime");
        Crime.setTextContent(Cr.getCrime());

        Criminal.setAttribute("ID", Cr.getID().toString());
        Criminal.appendChild(Name);
        Criminal.appendChild(DateOfBirth);
        Criminal.appendChild(Gender);
        Criminal.appendChild(Year);
        Criminal.appendChild(Room);
        Criminal.appendChild(Crime);

        CriminalInformation.appendChild(Criminal);

    }
     
}
