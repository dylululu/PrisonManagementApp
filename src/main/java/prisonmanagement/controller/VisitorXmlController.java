/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prisonmanagement.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
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
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import prisonmanagement.model.Visitor;
import prisonmanagement.utils.TaoXMLVisitor;
import prisonmanagement.view.DashboardView;

/**
 *
 * @author t460
 */
public class VisitorXmlController {

    private DocumentBuilderFactory dbf;
    private DocumentBuilder db;
    private Document doc;
    private String ten_file = "src/Visitor.xml";
    private ArrayList<Visitor> viList;
    private VisitorController visitorController;

    public ArrayList<Visitor> getAllVisitor() {
        dbf = DocumentBuilderFactory.newInstance();
        try {
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(DashboardView.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            doc = db.parse(ten_file);
        } catch (SAXException ex) {
            Logger.getLogger(DashboardView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DashboardView.class.getName()).log(Level.SEVERE, null, ex);
        }

        NodeList viVistor = doc.getElementsByTagName("Visitor");

        NodeList viCriminalID = doc.getElementsByTagName("CriminalID");
        NodeList viDateOfVisit = doc.getElementsByTagName("DateOfVisit");
        NodeList viAmountOfTime = doc.getElementsByTagName("AmountOfTime");
        NodeList viName = doc.getElementsByTagName("Name");
        NodeList viRelationship = doc.getElementsByTagName("Relationship");
        NodeList viLocation = doc.getElementsByTagName("Location");
        NodeList viTime = doc.getElementsByTagName("Time");

        viList = new ArrayList<>();
        for (int i = 0; i < viVistor.getLength(); i++) {
            Visitor vi = new Visitor();

            vi.setVisitorID(viVistor.item(i).getAttributes().getNamedItem("visitorID").getNodeValue());
            vi.setCriminalID(viCriminalID.item(i).getTextContent());
            vi.setDateOfVisit(viDateOfVisit.item(i).getTextContent());
            vi.setAmountOfTime(viAmountOfTime.item(i).getTextContent());
            vi.setNameVisitor(viName.item(i).getTextContent());
            vi.setRelationship(viRelationship.item(i).getTextContent());
            vi.setLocation(viLocation.item(i).getTextContent());
            vi.setTime(viTime.item(i).getTextContent());

            viList.add(vi);
        }
        return viList;
    }
    
    public void addVisitor(Document doc, Element VisitorInformation, Visitor vi) {
        Element Visitor = doc.createElement("Visitor");

        Element CriminalID = doc.createElement("CriminalID");
        CriminalID.setTextContent(vi.getCriminalID().toString());

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

        Visitor.setAttribute("visitorID", vi.getVisitorID().toString());
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
