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
public class TaoXMLVisitor {

//    public static void main(String[] args) {
//        try {
//            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//            DocumentBuilder db = dbf.newDocumentBuilder();
//            Document doc = db.newDocument();
//        
//            Element VisitorInformation = doc.createElement("VisitorInformation");
//            doc.appendChild(VisitorInformation);
//            
//            TransformerFactory tff = TransformerFactory.newInstance();
//            try {
//                Transformer tf = tff.newTransformer();
//                tf.setOutputProperty(OutputKeys.INDENT, "yes");
//                tf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "3");
//                DOMSource source = new DOMSource(doc);
//                StreamResult result = new StreamResult("src/Visitor.xml");
//                tf.transform(source, result);
//                System.out.println("Ghi file thanh cong");
//
//            } catch (TransformerConfigurationException ex) {
//                Logger.getLogger(TaoXMLVisitor.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (TransformerException ex) {
//                Logger.getLogger(TaoXMLVisitor.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//        } catch (ParserConfigurationException ex) {
//            Logger.getLogger(TaoXMLVisitor.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    


}
