/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prisonmanagement.model;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author t460
 */
public class CriminalManagement {
    private ArrayList<Criminal> jail;
    
    public CriminalManagement(){
        jail = new ArrayList<>();
    }

    public CriminalManagement(ArrayList<Criminal> jail) {
        this.jail = jail;
    }

    public ArrayList<Criminal> getJail() {
        return jail;
    }

    public void setJail(ArrayList<Criminal> jail) {
        this.jail = jail;
    }
    
    public void Add(Criminal cr){
        if(!check(cr)){
            jail.add(cr);
        }
    }
    
    public boolean delete(Criminal cr) {
    for (int i = 0; i < jail.size(); i++) {
        if (jail.get(i).getID().equals(cr.getID())) {
            jail.remove(i);
            return true; // Trả về true khi phạm nhân được xóa thành công
        }
    }
    return false; // Trả về false nếu không tìm thấy phạm nhân cần xóa
    }
    
    public void editCr(Criminal cr) {
        int size = jail.size();
        for (int i = 0; i < size; i++) {
            if (jail.get(i).getID().equals(cr.getID())) {
               jail.get(i).setName(cr.getName());
               jail.get(i).setDateOfBitrh(cr.getDateOfBitrh());
               jail.get(i).setGender(cr.getGender());
               jail.get(i).setYear(cr.getYear());
               jail.get(i).setYearStart(cr.getYearStart());
               jail.get(i).setRoom(cr.getRoom());
               jail.get(i).setCrime(cr.getCrime());
                break;
            }
        }
    }
    /**
     *
     * @param cr
     * @return
     */
    public boolean check(Criminal cr) {
        for (Criminal crime : jail) {
           if(crime.getID() == cr.getID()){
                return true;
            }       
        }
        return false;
    }
    
    public Criminal SearchFollowID(String s) {
    if (jail != null) {
        for (Criminal cr : jail) {
            if (!s.equals(cr.getID()) || (cr == null || cr.getID() == null)){
            } else {
                return cr;
            }
        }
    }
    return null;
}
    //tạo một file mới để đọc file xml
   public void readXMLFile(String filePath) throws SAXException, IOException, ParseException {
        try {
            File xmlFile = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            org.w3c.dom.Document doc = dBuilder.parse(xmlFile);
            
            doc.getDocumentElement().normalize();
            
            NodeList nodeList = doc.getElementsByTagName("Criminal");
            
            for(int i = 0; i < nodeList.getLength(); i++){
                Element element = (Element) nodeList.item(i);
                String ID = element.getElementsByTagName("ID").item(0).getTextContent();
                String name = element.getElementsByTagName("name").item(0).getTextContent();
                Date dateOfBirth = new SimpleDateFormat("dd/MM/yyyy").parse(element.getElementsByTagName("dateOfBirth").item(0).getTextContent());
                String gender = element.getElementsByTagName("gender").item(0).getTextContent();
                String year = element.getElementsByTagName("year").item(0).getTextContent();
                Date yearStart = new SimpleDateFormat("dd/MM/yyyy").parse(element.getElementsByTagName("yearStart").item(0).getTextContent());
                String room = element.getElementsByTagName("room").item(0).getTextContent();
                String crime = element.getElementsByTagName("crime").item(0).getTextContent();
                
                Criminal cr = new Criminal(ID, name, dateOfBirth, gender, year, yearStart, room, crime);
                Add(cr);
            }
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(CriminalManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
    // tạo têp xml mới và lưu trữ dữ liệu từ danh sách các phamNhan vào tệp
   public void saveXMLFile(String filePath) throws TransformerConfigurationException, TransformerException{
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            // Tạo một tài liệu XML mới
            org.w3c.dom.Document doc = docBuilder.newDocument();
            Element rootElement =doc.createElement("jail");
            doc.appendChild(rootElement);
            
            // Duyệt qua danh sách phạm nhân và tạo các phần tử XML tương ứng
            for(Criminal cr : jail){
                Element CriminalElement = doc.createElement("Criminal");
                rootElement.appendChild(CriminalElement);
                
                 // Thêm các phần tử thông tin phạm nhân vào phần tử Criminal
                 CriminalElement.appendChild(createElementWithTextContent(doc, "ID", cr.getID()));
                 CriminalElement.appendChild(createElementWithTextContent(doc, "name", cr.getName()));
                 CriminalElement.appendChild(createElementWithTextContent(doc, "dateOfBirth", new SimpleDateFormat("dd/MM/yyyy").format(cr.getDateOfBitrh())));
                 CriminalElement.appendChild(createElementWithTextContent(doc, "gender", cr.getGender()));
                 CriminalElement.appendChild(createElementWithTextContent(doc, "year", cr.getYear()));
                 CriminalElement.appendChild(createElementWithTextContent(doc, "yearStart", new SimpleDateFormat("dd/MM/yyyy").format(cr.getYearStart())));
                 CriminalElement.appendChild(createElementWithTextContent(doc, "room", cr.getRoom()));
                 CriminalElement.appendChild(createElementWithTextContent(doc, "crime", cr.getCrime()));
                 
                  // Ghi tài liệu XML vào file
                 TransformerFactory transformerFactory = TransformerFactory.newInstance();
                 Transformer transformer = transformerFactory.newTransformer();
                 transformer.setOutputProperty(javax.xml.transform.OutputKeys.INDENT, "yes"); // Thiết lập tự động ngắt dòng


                 DOMSource source = new DOMSource(doc);
                 StreamResult result = new StreamResult(new File(filePath));
                 transformer.transform(source, result);

                 System.out.println("Dữ liệu đã được lưu vào file XML thành công!");
            }
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(CriminalManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
   }

    private Node createElementWithTextContent(org.w3c.dom.Document doc, String id, String id0) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
