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

//    public static void main(String[] args) {
//        try {
//            DocumentBuilderFactory dbf1 = DocumentBuilderFactory.newInstance();
//            DocumentBuilder db1 = dbf1.newDocumentBuilder();
//            Document doc1 = db1.newDocument();
//
//            Element CriminalInformation = doc1.createElement("CriminalInformation");
//            Criminal Cr1 = new Criminal("1", "Phạm Hải Nam", "19/10/2003", "nam", "10", "613", "Qua dep trai", "It nghiem trong");
//            addCriminal(doc1, CriminalInformation, Cr1);
//
//            doc1.appendChild(CriminalInformation);
//
//            TransformerFactory tff1 = TransformerFactory.newInstance();
//            try {
//                Transformer tf1 = tff1.newTransformer();
//                tf1.setOutputProperty(OutputKeys.INDENT, "yes");
//                tf1.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "3");
//                DOMSource source1 = new DOMSource(doc1);
//                StreamResult result1 = new StreamResult("src/Criminal.xml");
//                tf1.transform(source1, result1);
//                System.out.println("Ghi file thanh cong");
//
//            } catch (TransformerConfigurationException ex) {
//                Logger.getLogger(TaoXMLCriminal.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (TransformerException ex) {
//                Logger.getLogger(TaoXMLCriminal.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//        } catch (ParserConfigurationException ex) {
//            Logger.getLogger(TaoXMLCriminal.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }

    static private void addCriminal(Document doc1, Element CriminalInformation, Criminal Cr) {
        Element Criminal = doc1.createElement("Criminal");

        Element criminalID = doc1.createElement("criminalID");
        criminalID.setTextContent(Cr.getcriminalID().toString());

        Element criminalName = doc1.createElement("criminalName");
        criminalName.setTextContent(Cr.getcriminalName());

        Element DateOfBirth = doc1.createElement("DateOfBirth");
        DateOfBirth.setTextContent(Cr.getDateOfBirth());

        Element Gender = doc1.createElement("Gender");
        Gender.setTextContent(Cr.getGender());

        Element Year = doc1.createElement("Year");
        Year.setTextContent(Cr.getYear());

        Element Room = doc1.createElement("Room");
        Room.setTextContent(Cr.getRoom());

        Element Crime = doc1.createElement("Crime");
        Crime.setTextContent(Cr.getCrime());

        Element CrimeScale = doc1.createElement("CrimeScale");
        CrimeScale.setTextContent(Cr.getCrimeScale());

        Criminal.setAttribute("criminalID", Cr.getcriminalID().toString());
        Criminal.appendChild(criminalName);
        Criminal.appendChild(DateOfBirth);
        Criminal.appendChild(Gender);
        Criminal.appendChild(Year);
        Criminal.appendChild(Room);
        Criminal.appendChild(Crime);
        Criminal.appendChild(CrimeScale);

        CriminalInformation.appendChild(Criminal);

    }

}
