/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package prisonmanagement.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static javax.swing.text.html.HTML.Attribute.ID;
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
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import prisonmanagement.model.Visitor;
import prisonmanagement.utils.TaoXMLVisitor;
import prisonmanagement.view.DashboardView;

/**
 *
 * @author Admin
 */
public class VisitorManagement extends javax.swing.JFrame {

    /**
     * Creates new form VistorManagement
     */
    DocumentBuilderFactory dbf;
    DocumentBuilder db;
    Document doc;
    String ten_file = "src/Visitor.xml";
    ArrayList<Visitor> viList;
    DefaultTableModel dfModel1;

    ArrayList<Visitor> getAllVisitor() {
        dbf = DocumentBuilderFactory.newInstance();
        try {
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(VisitorManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            doc = db.parse(ten_file);
        } catch (SAXException ex) {
            Logger.getLogger(VisitorManagement.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(VisitorManagement.class.getName()).log(Level.SEVERE, null, ex);
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

    private void display(ArrayList<Visitor> visitorList) {
        try {

            try {
                dbf = DocumentBuilderFactory.newInstance();
                db = dbf.newDocumentBuilder();
                doc = db.parse(ten_file);

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

                dfModel1 = new DefaultTableModel();
                dfModel1.setColumnCount(0);
                dfModel1.setRowCount(0);
                dfModel1.addColumn("VisitorID");
                dfModel1.addColumn("CriminalID");
                dfModel1.addColumn("DateOfVisit");
                dfModel1.addColumn("AmountOfTime");
                dfModel1.addColumn("Name");
                dfModel1.addColumn("Time");
                dfModel1.addColumn("Relationship");
                dfModel1.addColumn("Location");

                for (Visitor vi : visitorList) {
                    Vector v = new Vector();
                    v.add(vi.getVisitorID());
                    v.add(vi.getCriminalID());
                    v.add(vi.getDateOfVisit());
                    v.add(vi.getAmountOfTime());
                    v.add(vi.getNameVisitor());
                    v.add(vi.getTime());
                    v.add(vi.getRelationship());
                    v.add(vi.getLocation());

                    dfModel1.addRow(v);

                }
                jTable2.setModel(dfModel1);

            } catch (SAXException | IOException ex) {
                Logger.getLogger(VisitorManagement.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(VisitorManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {                                           
        try {
            String visitorID, criminalID, dateOfVisit, time, amountOfTime, location, name, relationship, path;
            visitorID = visitorIDTextField.getText();
            criminalID = criminalIDTextField.getText();
            dateOfVisit = dateOfVisitTextField.getText();
            time = timeTextField.getText();
            amountOfTime = amountOfTimeTextField.getText();
            location = locationTextField.getText();
            name = nameTextField.getText();
            relationship = relationshipTextField.getText();

            path = "VisitorInformation/Visitor[@visitorID='" + visitorID + "']";
            XPathFactory xpf = XPathFactory.newInstance();
            XPath xp = xpf.newXPath();
            Node chose = (Node) xp.evaluate(path, doc, XPathConstants.NODE);
            NodeList n1Visitor = chose.getChildNodes();

            n1Visitor.item(1).setTextContent(criminalID);
            n1Visitor.item(3).setTextContent(dateOfVisit);
            n1Visitor.item(5).setTextContent(time);
            n1Visitor.item(7).setTextContent(amountOfTime);
            n1Visitor.item(9).setTextContent(location);
            n1Visitor.item(11).setTextContent(name);
            n1Visitor.item(13).setTextContent(relationship);

            saveFile();
            display(this.getAllVisitor());

        } catch (XPathExpressionException ex) {
            Logger.getLogger(DashboardView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }                                          

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // TODO add your handling code here:
        String visitorID, criminalID, dateOfVisit, time, amountOfTime, location, name, relationship;
        visitorID = visitorIDTextField.getText();
        criminalID = criminalIDTextField.getText();
        dateOfVisit = dateOfVisitTextField.getText();
        time = timeTextField.getText();
        amountOfTime = amountOfTimeTextField.getText();
        location = locationTextField.getText();
        name = nameTextField.getText();
        relationship = relationshipTextField.getText();

        if (!fullInformation(visitorID, criminalID, dateOfVisit, time, amountOfTime, location, name, relationship)) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đủ thông tin ", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            if (daTrungVistorID(visitorID)) {
                JOptionPane.showMessageDialog(this, "Đã trùng mã VisitorID", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                Visitor visitor = new Visitor(visitorID, criminalID, dateOfVisit, time, amountOfTime, location, name, relationship);
                Element VisitorInformation = doc.getDocumentElement();
                addVisitor(doc, VisitorInformation, visitor);
                saveFile();

                JOptionPane.showMessageDialog(this, "Thêm thành công", "Information", JOptionPane.INFORMATION_MESSAGE);
                delete();
                display(this.getAllVisitor());

            }
        }
    }                                         

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // TODO add your handling code here:
        String visitorID, path;
        visitorID = visitorIDTextField.getText();

        try {
            int result;
            path = "VisitorInformation/Visitor[@visitorID='" + visitorID + "']";
            XPathFactory xpf = XPathFactory.newInstance();
            XPath xp = xpf.newXPath();
            Node chose = (Node) xp.evaluate(path, doc, XPathConstants.NODE);
            Node parent = chose.getParentNode();
            result = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa", "Confirm", JOptionPane.YES_NO_OPTION);
            if (result == 0) {
                parent.removeChild(chose);
                saveFile();
                display(this.getAllVisitor());
                JOptionPane.showMessageDialog(this, "Xóa thành công!", "Information", JOptionPane.INFORMATION_MESSAGE);

            }
        } catch (XPathExpressionException ex) {
            Logger.getLogger(DashboardView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }          
    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {                                     
        // TODO add your handling code here:
        int row_selected = jTable2.getSelectedRow();
        Long visitorID = null, criminalID = null;
        String dateOfVisit, time, amountOfTime, location, name, relationship;
        Object visitorIDValue = jTable2.getValueAt(row_selected, 0);
        Object criminalIDValue = jTable2.getValueAt(row_selected, 1);

        if (visitorIDValue instanceof Long) {
            visitorID = (Long) visitorIDValue;
        } else if (visitorIDValue instanceof String) {
            visitorID = Long.parseLong((String) visitorIDValue);
        } else {
            // Xử lý lỗi - giá trị không hợp lệ
        }

        if (criminalIDValue instanceof Long) {
            criminalID = (Long) criminalIDValue;
        } else if (criminalIDValue instanceof String) {
            criminalID = Long.parseLong((String) criminalIDValue);
        } else {
            // Xử lý lỗi - giá trị không hợp lệ
        }

        dateOfVisit = (String) jTable2.getValueAt(row_selected, 2);
        amountOfTime = (String) jTable2.getValueAt(row_selected, 3);
        name = (String) jTable2.getValueAt(row_selected, 4);
        time = (String) jTable2.getValueAt(row_selected, 5);
        relationship = (String) jTable2.getValueAt(row_selected, 6);
        location = (String) jTable2.getValueAt(row_selected, 7);

        visitorIDTextField.setText(visitorID.toString());
        criminalIDTextField.setText(criminalID.toString());
        dateOfVisitTextField.setText(dateOfVisit);
        timeTextField.setText(time);
        amountOfTimeTextField.setText(amountOfTime);
        locationTextField.setText(location);
        nameTextField.setText(name);
        relationshipTextField.setText(relationship);


    }                                    

    private void SearchButtonActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // TODO add your handling code here:
        String type = (String) jComboBox2.getSelectedItem();
        System.out.println(type);
        if (type == "All") {
            display(this.getAllVisitor());
        } else if (type == "Name") {
            String name = this.jSearchText.getText();
            display(this.findByName(name));
        } else if (type == "ID") {
            String id = this.jSearchText.getText();
            display(this.findById(id));
        } else if (type == "Date"){
            String date = this.jSearchText.getText();
            display(this.findByDate(date));
        }
    }                                            

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
    }                                          

    private void SortButtonActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
        String type = (String) SortComboBox.getSelectedItem();
        if (type == "Name") {
            display(this.sortByName());
        } else if (type == "Date") {
            display(this.sortByDate());
        }
    }                                          

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DashboardView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DashboardView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DashboardView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DashboardView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DashboardView(null, null).setVisible(true);
            }
        });
    }

    private boolean fullInformation(String visitorID, String criminalID, String dateOfVisit, String time, String amountOfTime, String location, String name, String relationship) {
        boolean full = true;
        if (visitorID.trim().isEmpty() || criminalID.trim().isEmpty() || dateOfVisit.trim().isEmpty() || time.trim().isEmpty() || amountOfTime.trim().isEmpty() || location.trim().isEmpty() || name.trim().isEmpty()
                || relationship.trim().isEmpty()) {
            full = false;
        }
        return full;
    }

    private boolean daTrungVistorID(String visitorID) {
        boolean daTrung = false;
        for (Visitor vi : viList) {
            if (vi.getVisitorID().compareToIgnoreCase(visitorID) == 0) {
                daTrung = true;
                break;
            }
        }
        return daTrung;
    }

    static private void addVisitor(Document doc, Element VisitorInformation, Visitor vi) {
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

    private void saveFile() {
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
            Logger.getLogger(TaoXMLVisitor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(TaoXMLVisitor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void delete() {
        visitorIDTextField.setText("");
        criminalIDTextField.setText("");
        dateOfVisitTextField.setText("");
        timeTextField.setText("");
        amountOfTimeTextField.setText("");
        locationTextField.setText("");
        nameTextField.setText("");
        relationshipTextField.setText("");
    }

    ArrayList<Visitor> findByName(String name) {
        viList = this.getAllVisitor();
        ArrayList<Visitor> res = new ArrayList<>();
        for (Visitor vi : viList) {
            if (vi.getNameVisitor().contains(name)) {
                res.add(vi);
            }
        }
        return res;
    }

    ArrayList<Visitor> findById(String id) {
        viList = this.getAllVisitor();
        ArrayList<Visitor> res = new ArrayList<>();
        for (Visitor vi : viList) {
            if (vi.getVisitorID().equals(id)) {
                res.add(vi);
            }
        }
        return res;
    }
    
    ArrayList<Visitor> findByDate(String date) {
        viList = this.getAllVisitor();
        ArrayList<Visitor> res = new ArrayList<>();
        for (Visitor vi : viList) {
            if (vi.getDateOfVisit().contains(date)) {
                res.add(vi);
            }
        }
        return res;
    }

    ArrayList<Visitor> sortByName() {
        viList = this.getAllVisitor();
        Collections.sort(viList, new Comparator<Visitor>() {
            @Override
            public int compare(Visitor visitor1, Visitor visitor2) {
                return visitor1.getNameVisitor().compareTo(visitor2.getNameVisitor());
            }
        });
        return viList;
    }

    ArrayList<Visitor> sortByDate() {
        viList = this.getAllVisitor();
        Collections.sort(viList, new Comparator<Visitor>() {
            @Override
            public int compare(Visitor visitor1, Visitor visitor2) {
                return compareDate(visitor1.getDateOfVisit(), visitor2.getDateOfVisit());
            }
        });
        return viList;
    }

    private int compareDate(String date1, String date2) {      
        String[] date1Parts = date1.split("/");
        String d1 = date1Parts[2] + date1Parts[1] + date1Parts[0];
        String[] date2Parts = date2.split("/");
        String d2 = date2Parts[2] + date2Parts[1] + date2Parts[0];
        return d1.compareTo(d2);
    }
    // Variables declaration - do not modify                     
    private javax.swing.JButton SearchButton;
    private javax.swing.JButton SortButton;
    private javax.swing.JComboBox<String> SortComboBox;
    private javax.swing.JButton addButton;
    private javax.swing.JTextField amountOfTimeTextField;
    private javax.swing.JButton clearButton;
    private javax.swing.JTextField criminalIDTextField;
    private javax.swing.JTextField dateOfVisitTextField;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton editButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jSearchText;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField locationTextField;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JTextField relationshipTextField;
    private javax.swing.JTextField timeTextField;
    private javax.swing.JTextField visitorIDTextField;
    // End of variables declaration                   

}

