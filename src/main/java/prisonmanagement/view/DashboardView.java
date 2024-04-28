/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package prisonmanagement.view;

import java.io.IOException;
import java.util.ArrayList;
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
import prisonmanagement.utils.TaoXML;

/**
 *
 * @author Admin
 */
public class DashboardView extends javax.swing.JFrame {

    /**
     * Creates new form DashboardView
     */
    DocumentBuilderFactory dbf;
    DocumentBuilder db;
    Document doc;
    String ten_file = "src/Visitor.xml";
    ArrayList<Visitor> viList;
    DefaultTableModel dfModel1;

    private void display() {
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

                for (Visitor vi : viList) {
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
                Logger.getLogger(DashboardView.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(DashboardView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public DashboardView() {
        initComponents();
        setLocationRelativeTo(null);
        display();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel49 = new javax.swing.JLabel();
        jTextField16 = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        editButton = new javax.swing.JButton();
        addButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        visitorIDTextField = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        criminalIDTextField = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        dateOfVisitTextField = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        timeTextField = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        amountOfTimeTextField = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        locationTextField = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        relationshipTextField = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jTextField17 = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(214, 179, 107));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(60, 47, 29));
        jLabel1.setIcon(new javax.swing.ImageIcon("src/main/java/prisonmanagement/icon/user.png"));
        jLabel1.setText("  VISITOR");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, -1, -1));

        jLabel2.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(60, 47, 29));
        jLabel2.setIcon(new javax.swing.ImageIcon("src/main/java/prisonmanagement/icon/home.png"));
        jLabel2.setText("   GENERAL ");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, -1, -1));

        jLabel3.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(60, 47, 29));
        jLabel3.setIcon(new javax.swing.ImageIcon("src/main/java/prisonmanagement/icon/user.png"));
        jLabel3.setText("  CRIMINAL ");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, -1, -1));

        jLabel46.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(60, 47, 29));
        jLabel46.setIcon(new javax.swing.ImageIcon("src/main/java/prisonmanagement/icon/exit.png"));
        jLabel46.setText("  EXIT");
        jPanel1.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 460, 70, 20));

        jLabel52.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(60, 47, 29));
        jLabel52.setIcon(new javax.swing.ImageIcon("src/main/java/prisonmanagement/icon/menu.png"));
        jLabel52.setText("  PSA PRISON");
        jLabel52.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel52MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 140, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 30, 500));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 880, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 499, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab3", jPanel4);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(60, 47, 29));
        jLabel4.setText("CRIME");
        jPanel5.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, -1, -1));

        jTextField5.setBorder(null);
        jTextField5.setCaretColor(new java.awt.Color(255, 255, 255));
        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });
        jPanel5.add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 180, 20));

        jLabel11.setText("_______________________________________");
        jPanel5.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, -1, -1));

        jLabel7.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(60, 47, 29));
        jLabel7.setText("DATE OF BIRTH");
        jPanel5.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, -1));

        jLabel8.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(60, 47, 29));
        jLabel8.setText("GENDER");
        jPanel5.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, -1, -1));

        jLabel9.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(60, 47, 29));
        jLabel9.setText("PRISON SENTENCE");
        jPanel5.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, -1, -1));

        jLabel10.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(60, 47, 29));
        jLabel10.setText("ROOM");
        jPanel5.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, -1, -1));

        jButton1.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(60, 47, 29));
        jButton1.setText("CLEAR");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel5.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 460, 80, -1));

        jButton2.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(60, 47, 29));
        jButton2.setText("ADD");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel5.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, 90, -1));

        jButton3.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(60, 47, 29));
        jButton3.setText("EDIT");
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel5.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 400, 80, -1));

        jButton4.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(60, 47, 29));
        jButton4.setText("DELETE");
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel5.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 460, 90, -1));

        jTextField2.setBorder(null);
        jTextField2.setCaretColor(new java.awt.Color(255, 255, 255));
        jPanel5.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 180, 20));

        jLabel13.setText("_______________________________________");
        jPanel5.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, -1));

        jTextField4.setBorder(null);
        jTextField4.setCaretColor(new java.awt.Color(255, 255, 255));
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });
        jPanel5.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 180, -1));

        jLabel14.setText("_______________________________________");
        jPanel5.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));

        jTextField6.setBorder(null);
        jTextField6.setCaretColor(new java.awt.Color(255, 255, 255));
        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });
        jPanel5.add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 180, 20));

        jLabel15.setText("_______________________________________");
        jPanel5.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, -1, -1));

        jTextField7.setBorder(null);
        jTextField7.setCaretColor(new java.awt.Color(255, 255, 255));
        jTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });
        jPanel5.add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 180, 20));

        jLabel16.setText("_______________________________________");
        jPanel5.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, -1, -1));

        jTextField8.setBorder(null);
        jTextField8.setCaretColor(new java.awt.Color(255, 255, 255));
        jTextField8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField8ActionPerformed(evt);
            }
        });
        jPanel5.add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 180, 20));

        jLabel17.setText("_______________________________________");
        jPanel5.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, -1, -1));

        jTextField3.setBorder(null);
        jTextField3.setCaretColor(new java.awt.Color(255, 255, 255));
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        jPanel5.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 180, 20));

        jLabel12.setText("_______________________________________");
        jPanel5.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        jLabel18.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(60, 47, 29));
        jLabel18.setText("ID");
        jPanel5.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 17, -1, -1));

        jLabel19.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(60, 47, 29));
        jLabel19.setText("NAME");
        jPanel5.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        jPanel3.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 510));

        jLabel6.setFont(new java.awt.Font("Arial Black", 1, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(204, 0, 0));
        jLabel6.setText("CRIMINAL INFORMATION");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 20, -1, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Name", "D.O.B", "Gender", "Year", "Room", "Crime"
            }
        ));
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(10);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(100);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(55);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(10);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(45);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(10);
            jTable1.getColumnModel().getColumn(6).setPreferredWidth(45);
        }

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 110, 660, 400));

        jLabel49.setIcon(new javax.swing.ImageIcon("src/main/java/prisonmanagement/icon/search-icon-256x256-1ihlz8ty.png"));
        jPanel3.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 60, -1, -1));

        jTextField16.setBorder(null);
        jTextField16.setCaretColor(new java.awt.Color(255, 255, 255));
        jPanel3.add(jTextField16, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 60, 330, -1));

        jLabel48.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        jLabel48.setText("_____________________________________________________________________________________________________________________");
        jPanel3.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 70, 390, 20));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "ID", "Name", "Gender" }));
        jComboBox1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel3.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 60, -1, -1));

        jTabbedPane1.addTab("tab2", jPanel3);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));
        jPanel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(60, 47, 29));
        jLabel5.setText("RELATIONSHIP");
        jPanel6.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, -1, -1));

        jLabel21.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(60, 47, 29));
        jLabel21.setText("CRIMINAL ID");
        jPanel6.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        jLabel22.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(60, 47, 29));
        jLabel22.setText("DATE OF VISIT");
        jPanel6.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        jLabel23.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(60, 47, 29));
        jLabel23.setText("TIME");
        jPanel6.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, -1, -1));

        jLabel24.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(60, 47, 29));
        jLabel24.setText("AMOUNT OF TIME");
        jPanel6.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, -1, -1));

        jLabel25.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(60, 47, 29));
        jLabel25.setText("LOCATION");
        jPanel6.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, -1, -1));

        jLabel26.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(60, 47, 29));
        jLabel26.setText("NAME");
        jPanel6.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, -1, -1));

        editButton.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        editButton.setForeground(new java.awt.Color(60, 47, 29));
        editButton.setText("EDIT");
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });
        jPanel6.add(editButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 460, 90, -1));

        addButton.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        addButton.setForeground(new java.awt.Color(60, 47, 29));
        addButton.setText("ADD");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });
        jPanel6.add(addButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 420, 90, -1));

        deleteButton.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        deleteButton.setForeground(new java.awt.Color(60, 47, 29));
        deleteButton.setText("DELETE");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });
        jPanel6.add(deleteButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, 90, -1));

        clearButton.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        clearButton.setForeground(new java.awt.Color(60, 47, 29));
        clearButton.setText("CLEAR");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });
        jPanel6.add(clearButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 460, 90, -1));

        visitorIDTextField.setBorder(null);
        jPanel6.add(visitorIDTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 180, -1));

        jLabel27.setText("______________________________________");
        jPanel6.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        criminalIDTextField.setBorder(null);
        criminalIDTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                criminalIDTextFieldActionPerformed(evt);
            }
        });
        jPanel6.add(criminalIDTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 180, -1));

        jLabel28.setText("______________________________________");
        jPanel6.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        dateOfVisitTextField.setBorder(null);
        dateOfVisitTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateOfVisitTextFieldActionPerformed(evt);
            }
        });
        jPanel6.add(dateOfVisitTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 180, -1));

        jLabel29.setText("______________________________________");
        jPanel6.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, -1, -1));

        timeTextField.setBorder(null);
        jPanel6.add(timeTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 180, -1));

        jLabel30.setText("______________________________________");
        jPanel6.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, -1));

        amountOfTimeTextField.setBorder(null);
        jPanel6.add(amountOfTimeTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 180, -1));

        jLabel31.setText("______________________________________");
        jPanel6.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, -1, -1));

        locationTextField.setBorder(null);
        locationTextField.setCaretColor(new java.awt.Color(255, 255, 255));
        jPanel6.add(locationTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 180, -1));

        jLabel32.setText("______________________________________");
        jPanel6.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, -1, -1));

        nameTextField.setBorder(null);
        nameTextField.setCaretColor(new java.awt.Color(255, 255, 255));
        jPanel6.add(nameTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 180, -1));

        jLabel33.setText("______________________________________");
        jPanel6.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, -1, -1));

        relationshipTextField.setBorder(null);
        relationshipTextField.setCaretColor(new java.awt.Color(255, 255, 255));
        jPanel6.add(relationshipTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, 180, -1));

        jLabel34.setText("______________________________________");
        jPanel6.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, -1, -1));

        jLabel47.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(60, 47, 29));
        jLabel47.setText("VISITOR ID");
        jPanel6.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jPanel2.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 219, 510));

        jLabel20.setFont(new java.awt.Font("Arial Black", 1, 20)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(204, 0, 0));
        jLabel20.setText("VISITOR INFORMATION");
        jPanel2.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 20, -1, -1));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Cri ID", "D.O.V", "Time", "Amount", "Location", "Name", "Relationship"
            }
        ));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setPreferredWidth(10);
            jTable2.getColumnModel().getColumn(1).setPreferredWidth(20);
            jTable2.getColumnModel().getColumn(2).setPreferredWidth(55);
            jTable2.getColumnModel().getColumn(3).setPreferredWidth(10);
            jTable2.getColumnModel().getColumn(4).setPreferredWidth(10);
            jTable2.getColumnModel().getColumn(5).setPreferredWidth(45);
            jTable2.getColumnModel().getColumn(6).setPreferredWidth(100);
            jTable2.getColumnModel().getColumn(7).setPreferredWidth(45);
        }

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 110, 660, 400));

        jTextField17.setBorder(null);
        jTextField17.setCaretColor(new java.awt.Color(255, 255, 255));
        jTextField17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField17ActionPerformed(evt);
            }
        });
        jPanel2.add(jTextField17, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 60, 390, -1));

        jLabel50.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        jLabel50.setText("_____________________________________________________________________________________________________________________");
        jPanel2.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 70, 390, 20));

        jLabel51.setIcon(new javax.swing.ImageIcon("src/main/java/prisonmanagement/icon/search-icon-256x256-1ihlz8ty.png"));
        jPanel2.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 60, -1, -1));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "ID", "Name", "Gender" }));
        jComboBox2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel2.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 60, -1, -1));

        jTabbedPane1.addTab("tab1", jPanel2);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, -40, 880, 540));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7ActionPerformed

    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField8ActionPerformed

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_jLabel3MouseClicked

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
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

            if (visitorID.trim().isEmpty() || !daTrungVistorID(visitorID)) {
                JOptionPane.showMessageDialog(this, "Mã nhân viên không hợp lê", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
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
                display();
            }

        } catch (XPathExpressionException ex) {
            Logger.getLogger(DashboardView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_editButtonActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
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
                display();
            }

        }

    }//GEN-LAST:event_addButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        // TODO add your handling code here:
        String visitorID, path;
        visitorID = visitorIDTextField.getText();

        if (visitorID.trim().isEmpty() || !daTrungVistorID(visitorID)) {
            JOptionPane.showMessageDialog(this, "Mã nhân viên không hợp lê", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
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
                    display();
                    JOptionPane.showMessageDialog(this, "Xóa thành công!", "Information", JOptionPane.INFORMATION_MESSAGE);

                }
            } catch (XPathExpressionException ex) {
                Logger.getLogger(DashboardView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


    }//GEN-LAST:event_deleteButtonActionPerformed

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        // TODO add your handling code here:
        delete();
    }//GEN-LAST:event_clearButtonActionPerformed

    private void criminalIDTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_criminalIDTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_criminalIDTextFieldActionPerformed

    private void dateOfVisitTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateOfVisitTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dateOfVisitTextFieldActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(2);
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel52MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel52MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel52MouseClicked

    private void jTextField17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField17ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField17ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:
        int row_selected = jTable2.getSelectedRow();
        String visitorID, criminalID, dateOfVisit, time, amountOfTime, location, name, relationship;
        visitorID = (String) jTable2.getValueAt(row_selected, 0);
        criminalID = (String) jTable2.getValueAt(row_selected, 1);
        dateOfVisit = (String) jTable2.getValueAt(row_selected, 2);
        amountOfTime = (String) jTable2.getValueAt(row_selected, 3);
        name = (String) jTable2.getValueAt(row_selected, 4);
        time = (String) jTable2.getValueAt(row_selected, 5);
        relationship = (String) jTable2.getValueAt(row_selected, 6);
        location = (String) jTable2.getValueAt(row_selected, 7);

        visitorIDTextField.setText(visitorID);
        criminalIDTextField.setText(criminalID);
        dateOfVisitTextField.setText(dateOfVisit);
        timeTextField.setText(time);
        amountOfTimeTextField.setText(amountOfTime);
        locationTextField.setText(location);
        nameTextField.setText(name);
        relationshipTextField.setText(relationship);


    }//GEN-LAST:event_jTable2MouseClicked

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
                new DashboardView().setVisible(true);
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

        Visitor.setAttribute("visitorID", vi.getVisitorID());
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
            Logger.getLogger(TaoXML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(TaoXML.class.getName()).log(Level.SEVERE, null, ex);
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
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
    private javax.swing.JLabel jLabel51;
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
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
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
    // End of variables declaration//GEN-END:variables
}
