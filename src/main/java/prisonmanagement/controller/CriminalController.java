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
import prisonmanagement.model.Criminal;
import prisonmanagement.model.Visitor;
import prisonmanagement.utils.TaoXMLCriminal;
import prisonmanagement.view.DashboardView;

/**
 *
 * @author t460
 */
public class CriminalController {

    DocumentBuilderFactory dbf1;
    DocumentBuilder db1;
    Document doc1;
    String ten_file1 = "src/Criminal.xml";
    ArrayList<Criminal> crList;

    public ArrayList<Criminal> getAllCriminal() {
        dbf1 = DocumentBuilderFactory.newInstance();
        try {
            db1 = dbf1.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(DashboardView.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            doc1 = db1.parse(ten_file1);
        } catch (SAXException ex) {
            Logger.getLogger(DashboardView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DashboardView.class.getName()).log(Level.SEVERE, null, ex);
        }
        NodeList crCriminalID = doc1.getElementsByTagName("Criminal");
//      NodeList crID = doc.getElementsByTagName("ID");
        NodeList crcriminalName = doc1.getElementsByTagName("criminalName");
        NodeList crDateOfBirth = doc1.getElementsByTagName("DateOfBirth");
        NodeList crGender = doc1.getElementsByTagName("Gender");
        NodeList crYear = doc1.getElementsByTagName("Year");
        NodeList crRoom = doc1.getElementsByTagName("Room");
        NodeList crCrime = doc1.getElementsByTagName("Crime");
        NodeList crCrimeScale = doc1.getElementsByTagName("CrimeScale");

        crList = new ArrayList<>();
        for (int i = 0; i < crCriminalID.getLength(); i++) {
            Criminal cr = new Criminal();
            cr.setcriminalID(crCriminalID.item(i).getAttributes().getNamedItem("criminalID").getNodeValue());
            cr.setcriminalName(crcriminalName.item(i).getTextContent());
            cr.setDateOfBirth(crDateOfBirth.item(i).getTextContent());
            cr.setGender(crGender.item(i).getTextContent());
            cr.setYear(crYear.item(i).getTextContent());
            cr.setRoom(crRoom.item(i).getTextContent());
            cr.setCrime(crCrime.item(i).getTextContent());
            cr.setCrimeScale(crCrimeScale.item(i).getTextContent());
            crList.add(cr);
        }
        return crList;
    }

    public void addCriminal(Document doc1, Element CriminalInformation, Criminal Cr) {
        Element Criminal = doc1.createElement("Criminal");

        Criminal.setAttribute("criminalID", Cr.getcriminalID());

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

    public boolean fullInformation(String criminalID, String criminalName, String dateOfBirth, String gender, String year, String room, String crime, String crimeScale) {
        boolean full = true;
        if (criminalID.trim().isEmpty()
                || criminalName.trim().isEmpty()
                || dateOfBirth.trim().isEmpty()
                || gender.trim().isEmpty()
                || year.trim().isEmpty()
                || room.trim().isEmpty()
                || crime.trim().isEmpty()
                || crimeScale.trim().isEmpty()) {
            full = false;
        }
        return full;
    }

    public boolean daTrungCriminalID(String criminalID) {
        boolean daTrung = false;
        crList = this.getAllCriminal();
        for (Criminal cr : crList) {
            if (cr.getcriminalID().compareToIgnoreCase(criminalID) == 0) {
                daTrung = true;
                break;
            }
        }
        return daTrung;
    }

    public ArrayList<Criminal> findByName(String name) {
        System.out.println("CHECK");
        crList = this.getAllCriminal();
        ArrayList<Criminal> res = new ArrayList<>();
        for (Criminal cr : crList) {
            if (cr.getcriminalName().contains(name)) {
                res.add(cr);
            }
        }
        return res;
    }

    public ArrayList<Criminal> findById(String id) {
        crList = this.getAllCriminal();
        ArrayList<Criminal> res = new ArrayList<>();
        for (Criminal cr : crList) {
            if (cr.getcriminalID().equals(id)) {
                res.add(cr);
            }
        }
        return res;
    }

    public ArrayList<Criminal> findByDate(String date) {
        crList = this.getAllCriminal();
        ArrayList<Criminal> res = new ArrayList<>();
        for (Criminal cr : crList) {
            if (cr.getDateOfBirth().contains(date)) {
                res.add(cr);
            }
        }
        return res;
    }

    public ArrayList<Criminal> findByGender(String gender) {
        crList = this.getAllCriminal();
        ArrayList<Criminal> res = new ArrayList<>();
        for (Criminal cr : crList) {
            if (cr.getGender().contains(gender)) {
                res.add(cr);
            }
        }
        return res;
    }

    public ArrayList<Criminal> sortByName() {
        crList = this.getAllCriminal();
        Collections.sort(crList, new Comparator<Criminal>() {
            @Override
            public int compare(Criminal criminal1, Criminal criminal2) {
                return criminal1.getcriminalName().compareTo(criminal2.getcriminalName());
            }
        });
        return crList;
    }
    
    public ArrayList<Criminal> sortById() {
        crList = this.getAllCriminal();
        Collections.sort(crList, new Comparator<Criminal>() {
            @Override
            public int compare(Criminal cr1, Criminal cr2) {
                Integer id1 = Integer.parseInt(cr1.getcriminalID());
                Integer id2 = Integer.parseInt(cr2.getcriminalID());
                return id1 - id2;
            }
        });
        return crList;
    }

    public ArrayList<Criminal> sortByDate() {
        crList = this.getAllCriminal();
        Collections.sort(crList, new Comparator<Criminal>() {
            @Override
            public int compare(Criminal criminal1, Criminal criminal2) {
                return compareDate(criminal1.getDateOfBirth(), criminal2.getDateOfBirth());
            }
        });
        return crList;
    }

    public int compareDate(String date1, String date2) {
        String[] date1Parts = date1.split("/");
        String d1 = date1Parts[2] + date1Parts[1] + date1Parts[0];
        String[] date2Parts = date2.split("/");
        String d2 = date2Parts[2] + date2Parts[1] + date2Parts[0];
        return d1.compareTo(d2);
    }

    public double[] countCriminal() {
        double[] res = new double[4];
        ArrayList<Criminal> list = this.getAllCriminal();
        for (Criminal cr : list) {

            if (cr.getCrimeScale().equals("Ít nghiêm trọng")) {
                res[0]++;
            }
            if (cr.getCrimeScale().equals("Nghiêm trọng")) {
                res[1]++;
            }
            if (cr.getCrimeScale().equals("Rất nghiêm trọng")) {
                res[2]++;
            }
            if (cr.getCrimeScale().equals("Đặc biệt nghiêm trọng")) {
                res[3]++;
            }

        }
        return res;
    }
}
