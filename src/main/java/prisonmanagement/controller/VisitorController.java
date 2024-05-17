/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prisonmanagement.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import prisonmanagement.model.Visitor;
import prisonmanagement.view.DashboardView;

/**
 *
 * @author t460
 */
public class VisitorController {
    DocumentBuilderFactory dbf;
    DocumentBuilder db;
    Document doc;
    String ten_file = "src/Visitor.xml";
    ArrayList<Visitor> viList;
    
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
    
    public boolean fullInformation(String visitorID, String criminalID, String dateOfVisit, String time, String amountOfTime, String location, String name, String relationship) {
        boolean full = true;
        if (visitorID.trim().isEmpty() || criminalID.trim().isEmpty() || dateOfVisit.trim().isEmpty() || time.trim().isEmpty() || amountOfTime.trim().isEmpty() || location.trim().isEmpty() || name.trim().isEmpty()
                || relationship.trim().isEmpty()) {
            full = false;
        }
        return full;
    }

    public boolean daTrungVistorID(String visitorID) {
        boolean daTrung = false;
        viList = this.getAllVisitor();
        for (Visitor vi : viList) {
            if (vi.getVisitorID().compareToIgnoreCase(visitorID) == 0) {
                daTrung = true;
                break;
            }
        }
        return daTrung;
    }

    public ArrayList<Visitor> findByName(String name) {
        System.out.println("CHECK");
        viList = this.getAllVisitor();
        ArrayList<Visitor> res = new ArrayList<>();
        for (Visitor vi : viList) {
            if (vi.getNameVisitor().contains(name)) {
                res.add(vi);
            }
        }
        return res;
    }

    public ArrayList<Visitor> findById(String id) {
        viList = this.getAllVisitor();
        ArrayList<Visitor> res = new ArrayList<>();
        for (Visitor vi : viList) {
            if (vi.getVisitorID().equals(id)) {
                res.add(vi);
            }
        }
        return res;
    }
    
    public ArrayList<Visitor> findByDate(String date) {
        viList = this.getAllVisitor();
        ArrayList<Visitor> res = new ArrayList<>();
        for (Visitor vi : viList) {
            if (vi.getDateOfVisit().contains(date)) {
                res.add(vi);
            }
        }
        return res;
    }

    public ArrayList<Visitor> sortByName() {
        viList = this.getAllVisitor();
        Collections.sort(viList, new Comparator<Visitor>() {
            @Override
            public int compare(Visitor visitor1, Visitor visitor2) {
                return visitor1.getNameVisitor().compareTo(visitor2.getNameVisitor());
            }
        });
        return viList;
    }

    public ArrayList<Visitor> sortByDate() {
        viList = this.getAllVisitor();
        Collections.sort(viList, new Comparator<Visitor>() {
            @Override
            public int compare(Visitor visitor1, Visitor visitor2) {
                return compareDate(visitor1.getDateOfVisit(), visitor2.getDateOfVisit());
            }
        });
        return viList;
    }

    public int compareDate(String date1, String date2) {      
        String[] date1Parts = date1.split("/");
        String d1 = date1Parts[2] + date1Parts[1] + date1Parts[0];
        String[] date2Parts = date2.split("/");
        String d2 = date2Parts[2] + date2Parts[1] + date2Parts[0];
        return d1.compareTo(d2);
    }
}
