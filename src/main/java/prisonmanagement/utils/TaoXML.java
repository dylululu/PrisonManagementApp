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
import prisonmanagement.model.Visitor;

/**
 *
 * @author 84768
 */
public class TaoXML {

    public static void main(String[] args) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.newDocument();

            Visitor vi1 = new Visitor("1", "1", "19/2/2024", "13:00", "50 min", "Phong 1", "Hai Nam", "Nguoi yeu");

            Element VisitorInformation = doc.createElement("VisitorInformation");

            addVisitor(doc, VisitorInformation, vi1);

            doc.appendChild(VisitorInformation);

            TransformerFactory tff = TransformerFactory.newInstance();
            try {
                Transformer tf = tff.newTransformer();
                tf.setOutputProperty(OutputKeys.INDENT, "yes");
                tf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "3");
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult("src/Visitor.xml");
                tf.transform(source, result);
                System.out.println("Ghi file thanh cong");

            } catch (TransformerConfigurationException ex) {
                Logger.getLogger(TaoXML.class.getName()).log(Level.SEVERE, null, ex);
            } catch (TransformerException ex) {
                Logger.getLogger(TaoXML.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (ParserConfigurationException ex) {
            Logger.getLogger(TaoXML.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    static private void addVisitor(Document doc, Element VisitorInformation, Visitor vi) {
        Element Visitor = doc.createElement("Visitor");

        Element VisitorID = doc.createElement("VisitorID");
        VisitorID.setTextContent(vi.getVisitorID());
        Element CriminalID = doc.createElement("CriminalID");
        CriminalID.setTextContent(vi.getCriminalID());

        Element DateOfVisit = doc.createElement("DateOfVisit");
        DateOfVisit.setTextContent(vi.getDateOfVisit());

        Element Time = doc.createElement("Time");
        Time.setTextContent(vi.getTime());

        Element AmountOfTime = doc.createElement("AmountOfTime");
        AmountOfTime.setTextContent(vi.getAmountOfTime());

        Element Location = doc.createElement("Location");
        Location.setTextContent(vi.getLocation());

        Element Name = doc.createElement("Name");
        Name.setTextContent(vi.getNameVisitor());

        Element Relationship = doc.createElement("Relationship");
        Relationship.setTextContent(vi.getRelationship());

        Visitor.appendChild(VisitorID);
        Visitor.appendChild(CriminalID);
        Visitor.appendChild(DateOfVisit);
        Visitor.appendChild(Time);
        Visitor.appendChild(AmountOfTime);
        Visitor.appendChild(Location);
        Visitor.appendChild(Name);
        Visitor.appendChild(Relationship);

        VisitorInformation.appendChild(Visitor);

    }
}
