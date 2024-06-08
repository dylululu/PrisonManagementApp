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
import org.w3c.dom.Element;
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
    
    public ArrayList<Visitor> sortById() {
        viList = this.getAllVisitor();
        Collections.sort(viList, new Comparator<Visitor>() {
            @Override
            public int compare(Visitor visitor1, Visitor visitor2) {
                Integer id1 = Integer.parseInt(visitor1.getVisitorID());
                Integer id2 = Integer.parseInt(visitor2.getVisitorID());
                return id1 - id2;
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
    
    public double[] countVisitorByYear(String year){
        double[] res = new double[4];
        ArrayList<Visitor> list = this.getAllVisitor();
        for(Visitor vi : list){
            String date = vi.getDateOfVisit();
            System.out.println(date);
            if(date.substring(6).equals(year)){
                String month = date.substring(3, 5);
                System.out.println(month);
                if(month.equals("01") || month.equals("02") || month.equals("03")) res[0]++;
                if(month.equals("04") || month.equals("05") || month.equals("06")) res[1]++;
                if(month.equals("07") || month.equals("09") || month.equals("09")) res[2]++;
                if(month.equals("10") || month.equals("11") || month.equals("12")) res[3]++;
            }
        }
        return res;
    }
}