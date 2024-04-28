///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package prisonmanagement.model;
//
//import java.io.File;
//import java.io.IOException;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.parsers.ParserConfigurationException;
//import javax.xml.transform.Transformer;
//import javax.xml.transform.TransformerConfigurationException;
//import javax.xml.transform.TransformerException;
//import javax.xml.transform.TransformerFactory;
//import javax.xml.transform.dom.DOMSource;
//import javax.xml.transform.stream.StreamResult;
//import org.w3c.dom.Element;
//import org.w3c.dom.Node;
//import org.w3c.dom.NodeList;
//import org.xml.sax.SAXException;
//
///**
// *
// * @author t460
// */
//public class VisitorManagement {
//    private ArrayList<Visitor> visitorList;
//    
//    public VisitorManagement(){
//        visitorList = new ArrayList<>();
//    }
//
//    public VisitorManagement(ArrayList<Visitor> visitorList) {
//        this.visitorList = visitorList;
//    }
//
//    public ArrayList<Visitor> getVisitorList() {
//        return visitorList;
//    }
//
//    public void setVisitorList(ArrayList<Visitor> visitorList) {
//        this.visitorList = visitorList;
//    }
//    
//      public void Add(Visitor vi){
//        if(!check(vi)){
//            visitorList.add(vi);
//        }
//    }
//    
//    public boolean delete(Visitor vi) {
//    for (int i = 0; i < visitorList.size(); i++) {
//        if (visitorList.get(i).getID().equals(vi.getID())) {
//            visitorList.remove(i);
//            return true; // Trả về true khi phạm nhân được xóa thành công
//        }
//    }
//    return false; // Trả về false nếu không tìm thấy phạm nhân cần xóa
//    }
//    
//    public void editVi(Visitor vi) {
//        int size = visitorList.size();
//        for (int i = 0; i < size; i++) {
//            if (visitorList.get(i).getVisitorID().equals(vi.getVisitorID())) {
//               visitorList.get(i).setDateOfVisit(vi.getDateOfVisit());
//               visitorList.get(i).setTime(vi.getTime());
//               visitorList.get(i).setAmountOfTime(vi.getAmountOfTime());
//               visitorList.get(i).setLocation(vi.getLocation());
//               visitorList.get(i).setNameVisitor(vi.getNameVisitor());
//               visitorList.get(i).setRelationship(vi.getRelationship());
//                break;
//            }
//        }
//    }
//  
//    public boolean check(Visitor vi) {
//        for (Visitor visi : visitorList) {
//           if(visi.getVisitorID() == vi.getVisitorID()){
//                return true;
//            }       
//        }
//        return false;
//    }
//    
//    public Visitor SearchFollowID(String s) {
//    if (visitorList != null) {
//        for (Visitor vi : visitorList) {
//            if (!s.equals(vi.getID()) || (vi == null || vi.getID() == null)){
//            } else {
//                return vi;
//            }
//        }
//    }
//    return null;
//}
//     //tạo một file mới để đọc file xml
//   public void readXMLFile(String filePath) throws SAXException, IOException, ParseException {
//        try {
//            File xmlFile = new File(filePath);
//            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//            org.w3c.dom.Document doc = dBuilder.parse(xmlFile);
//            
//            doc.getDocumentElement().normalize();
//            
//            NodeList nodeList = doc.getElementsByTagName("Visitor");
//            
//            for(int i = 0; i < nodeList.getLength(); i++){
//                Element element = (Element) nodeList.item(i);
//                String ID = element.getElementsByTagName("ID").item(0).getTextContent();
//                String vistorID = element.getElementsByTagName("visitorID").item(0).getTextContent();
//                Date dateOfVisit = new SimpleDateFormat("dd/MM/yyyy").parse(element.getElementsByTagName("dateOfVisit").item(0).getTextContent());
//                String time = element.getElementsByTagName("time").item(0).getTextContent();
//                String amountOfTime = element.getElementsByTagName("amountOfTime").item(0).getTextContent();
//                String location = element.getElementsByTagName("location").item(0).getTextContent();
//                String name = element.getElementsByTagName("name").item(0).getTextContent();
//                String relationship = element.getElementsByTagName("relationship").item(0).getTextContent();
//                
//                Visitor vi = new Visitor(vistorID, ID, time, amountOfTime, location, name, relationship, "Nguoi yeu");
//                Add(vi);
//            }
//        } catch (ParserConfigurationException ex) {
//            Logger.getLogger(CriminalManagement.class.getName()).log(Level.SEVERE, null, ex);
//        }
//   }
//    // tạo têp xml mới và lưu trữ dữ liệu từ danh sách các phamNhan vào tệp
//   public void saveXMLFile(String filePath) throws TransformerConfigurationException, TransformerException{
//        try {
//            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
//            // Tạo một tài liệu XML mới
//            org.w3c.dom.Document doc = docBuilder.newDocument();
//            Element rootElement =doc.createElement("visitorList");
//            doc.appendChild(rootElement);
//            
//            // Duyệt qua danh sách phạm nhân và tạo các phần tử XML tương ứng
//            for(Visitor vi : visitorList){
//                Element VisitorElement = doc.createElement("Visitor");
//                rootElement.appendChild(VisitorElement);
//                
//                 // Thêm các phần tử thông tin người thăm vào phần tử Visitor
//                 VisitorElement.appendChild(createElementWithTextContent(doc, "ID", vi.getID()));
//                 VisitorElement.appendChild(createElementWithTextContent(doc, "visitorID", vi.getVisitorID()));
//                 VisitorElement.appendChild(createElementWithTextContent(doc, "dateOfVisit", new SimpleDateFormat("dd/MM/yyyy").format(vi.getDateOfVisit())));
//                 VisitorElement.appendChild(createElementWithTextContent(doc, "time", vi.getTime()));
//                 VisitorElement.appendChild(createElementWithTextContent(doc, "amountOfTime", vi.getAmountOfTime()));
//                 VisitorElement.appendChild(createElementWithTextContent(doc, "location", vi.getLocation()));
//                 VisitorElement.appendChild(createElementWithTextContent(doc, "nameVisitor", vi.getNameVisitor()));
//                 VisitorElement.appendChild(createElementWithTextContent(doc, "relationship", vi.getRelationship()));
//
//                  // Ghi tài liệu XML vào file
//                 TransformerFactory transformerFactory = TransformerFactory.newInstance();
//                 Transformer transformer = transformerFactory.newTransformer();
//                 transformer.setOutputProperty(javax.xml.transform.OutputKeys.INDENT, "yes"); // Thiết lập tự động ngắt dòng
//
//
//                 DOMSource source = new DOMSource(doc);
//                 StreamResult result = new StreamResult(new File(filePath));
//                 transformer.transform(source, result);
//
//                 System.out.println("Dữ liệu đã được lưu vào file XML thành công!");
//            }
//        } catch (ParserConfigurationException ex) {
//            Logger.getLogger(CriminalManagement.class.getName()).log(Level.SEVERE, null, ex);
//        }
//   }
//
//    private Node createElementWithTextContent(org.w3c.dom.Document doc, String id, String id0) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//}
