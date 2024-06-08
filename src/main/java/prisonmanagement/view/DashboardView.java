/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package prisonmanagement.view;
//package prisonmanagement.controller;
import com.raven.mainChart.CriminalChartMain;
import com.raven.mainChart.VisitorChartMain;
import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.util.stream.DoubleStream.builder;
import static java.util.stream.Stream.builder;
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
import prisonmanagement.controller.CriminalController;
import prisonmanagement.controller.LoginController;
import prisonmanagement.model.Visitor;
import prisonmanagement.utils.TaoXMLVisitor;
import prisonmanagement.controller.VisitorController;
import prisonmanagement.model.Criminal;
import prisonmanagement.utils.TaoXMLCriminal;
import prisonmanagement.slideshow.Slideshow;

/**
 *
 * @author Admin
 */
public class DashboardView extends javax.swing.JFrame {

    private DocumentBuilderFactory dbf1;
    private DocumentBuilder db1;
    private Document doc1;
    private DocumentBuilderFactory dbf;
    private DocumentBuilder db;
    private Document doc;
    private String ten_file = "src/Visitor.xml";
    private String ten_file1 = "src/Criminal.xml";

    private ArrayList<Visitor> viList;
    private ArrayList<Criminal> crList;

    public DefaultTableModel dfModel1;
    public DefaultTableModel dfModel2;

    private final VisitorController visitorController;
    private final CriminalController criminalController;

    public DashboardView(VisitorController visitorController, CriminalController criminalController) {
        initComponents();
        //jPanel7.initSlideshow(new Slide1(), new Slide2());
        slideshow1.initSlideshow(new Slide1(), new Slide3(), new Slide2());   
        setLocationRelativeTo(null);
        this.visitorController = visitorController;
        this.criminalController = criminalController;
        displayVisitor(this.visitorController.getAllVisitor());
        displayCriminal(this.criminalController.getAllCriminal());
    }

    private void displayVisitor(ArrayList<Visitor> visitorList) {
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
                Logger.getLogger(DashboardView.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(DashboardView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

       public final ArrayList<Criminal> getAllCriminal() {
        dbf1 = DocumentBuilderFactory.newInstance();
        try {
            db1 = dbf1.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(DashboardView.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            doc1 = db1.parse(ten_file1);
            doc1.getDocumentElement().normalize();
        } catch (SAXException ex) {
            Logger.getLogger(DashboardView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DashboardView.class.getName()).log(Level.SEVERE, null, ex);
        }
        NodeList crCriminalID = doc1.getElementsByTagName("Criminal");
//        NodeList crID = doc.getElementsByTagName("ID");
        NodeList crName = doc1.getElementsByTagName("criminalName");
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
            cr.setcriminalName(crName.item(i).getTextContent());
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

    private void displayCriminal(ArrayList<Criminal> criminalList) {
        try {
            try {
                dbf1 = DocumentBuilderFactory.newInstance();
                db1 = dbf1.newDocumentBuilder();
                doc1 = db1.parse(ten_file1);
                doc1.getDocumentElement().normalize();
                NodeList crCriminalID = doc1.getElementsByTagName("Criminal");
//              NodeList crID = doc.getElementsByTagName("ID");
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
                dfModel2 = new DefaultTableModel();
                dfModel2.setColumnCount(0);
                dfModel2.setRowCount(0);
                dfModel2.addColumn("CriminalID");
                dfModel2.addColumn("CriminalName");
                dfModel2.addColumn("DateOfBirth");
                dfModel2.addColumn("Gender");
                dfModel2.addColumn("Prison Sentence");
                dfModel2.addColumn("Room");
                dfModel2.addColumn("Crime");
                dfModel2.addColumn("CrimeScale");

                for (Criminal cr : criminalList) {
                    Vector c = new Vector();
                    c.add(cr.getcriminalID());
                    c.add(cr.getcriminalName());
                    c.add(cr.getDateOfBirth());
                    c.add(cr.getGender());
                    c.add(cr.getYear());
                    c.add(cr.getRoom());
                    c.add(cr.getCrime());
                    c.add(cr.getCrimeScale());
                    dfModel2.addRow(c);
                }
                TableCriminal.setModel(dfModel2);
            } catch (SAXException | IOException ex) {
                Logger.getLogger(DashboardView.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(DashboardView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel52 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        DashBoard = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        panelRound2 = new prisonmanagement.view.PanelRound();
        jLabel35 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        panelRound1 = new prisonmanagement.view.PanelRound();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        panelRound3 = new prisonmanagement.view.PanelRound();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        panelRound4 = new prisonmanagement.view.PanelRound();
        jLabel45 = new javax.swing.JLabel();
        panelRound5 = new prisonmanagement.view.PanelRound();
        jLabel36 = new javax.swing.JLabel();
        slideshow1 = new prisonmanagement.slideshow.Slideshow();
        jLabel51 = new javax.swing.JLabel();
        Criminal = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        CrimeText = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        criminalNameText = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        DateOfBirthText = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        YearText = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        RoomText = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        criminalIDText = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        criminaladd = new prisonmanagement.view.MyButton();
        criminaledit = new prisonmanagement.view.MyButton();
        criminaldelete = new prisonmanagement.view.MyButton();
        criminalclear = new prisonmanagement.view.MyButton();
        CriminalChartButton = new prisonmanagement.view.MyButton();
        GenderComboBox = new javax.swing.JComboBox<>();
        CrimeScaleComboBox = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableCriminal = new javax.swing.JTable();
        jLabel49 = new javax.swing.JLabel();
        CriminalInformationText = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        SearchCriminal = new javax.swing.JButton();
        SortComboCriminal = new javax.swing.JComboBox<>();
        SortCriminal = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        Visitor = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
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
        addButton = new prisonmanagement.view.MyButton();
        editButton = new prisonmanagement.view.MyButton();
        deleteButton = new prisonmanagement.view.MyButton();
        clearButton = new prisonmanagement.view.MyButton();
        ChartButton = new prisonmanagement.view.MyButton();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jSearchText = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        SearchButton = new javax.swing.JButton();
        SortButton = new javax.swing.JButton();
        SortComboBox = new javax.swing.JComboBox<>();
        jLabel55 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(214, 179, 107));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(214, 179, 107));

        jLabel52.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(60, 47, 29));
        jLabel52.setIcon(new javax.swing.ImageIcon("src/main/java/prisonmanagement/icon/menu.png"));
        jLabel52.setText(" PSA PRISON");
        jLabel52.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel52MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel52MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel52MouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(jLabel52, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addComponent(jLabel52)
                .addContainerGap(11, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 110, 40));

        jPanel3.setBackground(new java.awt.Color(214, 179, 107));

        jLabel2.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(60, 47, 29));
        jLabel2.setIcon(new javax.swing.ImageIcon("src/main/java/prisonmanagement/icon/home.png"));
        jLabel2.setText("  GENERAL ");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel2MouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel2)
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap(11, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 110, 40));

        jPanel8.setBackground(new java.awt.Color(214, 179, 107));

        jLabel3.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(60, 47, 29));
        jLabel3.setIcon(new javax.swing.ImageIcon("src/main/java/prisonmanagement/icon/user.png"));
        jLabel3.setText("  CRIMINAL ");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel3MouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(jLabel3)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addContainerGap(11, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 110, 40));

        jPanel9.setBackground(new java.awt.Color(214, 179, 107));

        jLabel1.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(60, 47, 29));
        jLabel1.setIcon(new javax.swing.ImageIcon("src/main/java/prisonmanagement/icon/user.png"));
        jLabel1.setText("  VISITOR ");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel1MouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(jLabel1)
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap(11, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 110, 40));

        jPanel10.setBackground(new java.awt.Color(214, 179, 107));

        jLabel46.setBackground(new java.awt.Color(214, 179, 107));
        jLabel46.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(60, 47, 29));
        jLabel46.setIcon(new javax.swing.ImageIcon("src/main/java/prisonmanagement/icon/exit.png"));
        jLabel46.setText("  EXIT");
        jLabel46.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel46MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel46MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel46MouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel46)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 520, 110, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 30, 560));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel11.setBackground(new java.awt.Color(255, 255, 255, 50));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelRound2.setBackground(new java.awt.Color(214, 179, 107));
        panelRound2.setRoundBottomLeft(30);
        panelRound2.setRoundBottomRight(30);
        panelRound2.setRoundTopLeft(30);
        panelRound2.setRoundTopRight(30);

        jLabel35.setIcon(new javax.swing.ImageIcon("src/main/java/prisonmanagement/icon/main.png"));
        jLabel35.setText("jLabel35");

        jLabel37.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(60, 47, 29));
        jLabel37.setText("PSA PRISON");

        jLabel38.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(60, 47, 29));
        jLabel38.setText("Contact: 911");

        jLabel39.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(60, 47, 29));
        jLabel39.setText("Address: Hanoi");

        javax.swing.GroupLayout panelRound2Layout = new javax.swing.GroupLayout(panelRound2);
        panelRound2.setLayout(panelRound2Layout);
        panelRound2Layout.setHorizontalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel39))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelRound2Layout.setVerticalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound2Layout.createSequentialGroup()
                        .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel38))
                        .addContainerGap(18, Short.MAX_VALUE))
                    .addGroup(panelRound2Layout.createSequentialGroup()
                        .addComponent(jLabel37)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel39)
                        .addGap(23, 23, 23))))
        );

        jPanel11.add(panelRound2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 223, 130));

        panelRound1.setBackground(new java.awt.Color(214, 179, 107));
        panelRound1.setRoundBottomLeft(30);
        panelRound1.setRoundBottomRight(30);
        panelRound1.setRoundTopLeft(30);
        panelRound1.setRoundTopRight(30);
        panelRound1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel40.setIcon(new javax.swing.ImageIcon("src/main/java/prisonmanagement/icon/visitcelen.png"));
        panelRound1.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 9, 90, 110));

        jLabel41.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(60, 47, 29));
        jLabel41.setText("VISITOR");
        panelRound1.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, -1, -1));

        jLabel42.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(60, 47, 29));
        jLabel42.setText("Visit turn: 1000");
        panelRound1.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, -1, -1));

        jPanel11.add(panelRound1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 10, 220, 130));

        panelRound3.setBackground(new java.awt.Color(214, 179, 107));
        panelRound3.setRoundBottomLeft(30);
        panelRound3.setRoundBottomRight(30);
        panelRound3.setRoundTopLeft(30);
        panelRound3.setRoundTopRight(30);
        panelRound3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel43.setIcon(new javax.swing.ImageIcon("src/main/java/prisonmanagement/icon/crimenum.png"));
        panelRound3.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 16, -1, -1));

        jLabel44.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(60, 47, 29));
        jLabel44.setText("CRIMINAL");
        panelRound3.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, -1, -1));

        jLabel53.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(60, 47, 29));
        jLabel53.setText("Amount: 1500");
        panelRound3.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, -1, -1));

        jPanel11.add(panelRound3, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 10, 210, 130));

        jPanel7.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 840, 150));

        panelRound4.setBackground(new java.awt.Color(255, 255, 240));
        panelRound4.setRoundBottomLeft(30);
        panelRound4.setRoundBottomRight(30);
        panelRound4.setRoundTopLeft(30);
        panelRound4.setRoundTopRight(30);

        jLabel45.setBackground(new java.awt.Color(255, 255, 255));
        jLabel45.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(60, 47, 29));
        jLabel45.setText("INFORMATION");

        javax.swing.GroupLayout panelRound4Layout = new javax.swing.GroupLayout(panelRound4);
        panelRound4.setLayout(panelRound4Layout);
        panelRound4Layout.setHorizontalGroup(
            panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 130, Short.MAX_VALUE)
            .addGroup(panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelRound4Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel45)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        panelRound4Layout.setVerticalGroup(
            panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
            .addGroup(panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelRound4Layout.createSequentialGroup()
                    .addGap(0, 4, Short.MAX_VALUE)
                    .addComponent(jLabel45)
                    .addGap(0, 5, Short.MAX_VALUE)))
        );

        jPanel7.add(panelRound4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 130, 30));

        panelRound5.setBackground(new java.awt.Color(255, 255, 240));
        panelRound5.setRoundBottomLeft(30);
        panelRound5.setRoundBottomRight(30);
        panelRound5.setRoundTopLeft(30);
        panelRound5.setRoundTopRight(30);

        jLabel36.setBackground(new java.awt.Color(255, 255, 255));
        jLabel36.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(60, 47, 29));
        jLabel36.setText("ACTIVITIES");

        javax.swing.GroupLayout panelRound5Layout = new javax.swing.GroupLayout(panelRound5);
        panelRound5.setLayout(panelRound5Layout);
        panelRound5Layout.setHorizontalGroup(
            panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 130, Short.MAX_VALUE)
            .addGroup(panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelRound5Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel36)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        panelRound5Layout.setVerticalGroup(
            panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
            .addGroup(panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelRound5Layout.createSequentialGroup()
                    .addGap(0, 4, Short.MAX_VALUE)
                    .addComponent(jLabel36)
                    .addGap(0, 5, Short.MAX_VALUE)))
        );

        jPanel7.add(panelRound5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 130, 30));
        jPanel7.add(slideshow1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 840, 290));

        jLabel51.setIcon(new javax.swing.ImageIcon("src/main/java/prisonmanagement/icon/prison-e13110f1cdefe7ba9c6932602cc111e7959ad0f0.jpg"));
        jLabel51.setText("jLabel51");
        jPanel7.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(-320, -60, -1, -1));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE))
        );

        DashBoard.addTab("tab3", jPanel4);

        Criminal.setBackground(new java.awt.Color(255, 255, 255));
        Criminal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(60, 47, 29));
        jLabel4.setText("CRIME");
        jPanel5.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, -1, -1));

        CrimeText.setBorder(null);
        CrimeText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CrimeTextActionPerformed(evt);
            }
        });
        jPanel5.add(CrimeText, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 180, 20));

        jLabel11.setText("_______________________________________");
        jPanel5.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, -1, -1));

        jLabel7.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(60, 47, 29));
        jLabel7.setText("DATE OF BIRTH");
        jPanel5.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        jLabel8.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(60, 47, 29));
        jLabel8.setText("GENDER");
        jPanel5.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, -1, -1));

        jLabel9.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(60, 47, 29));
        jLabel9.setText("PRISON SENTENCE");
        jPanel5.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, -1, -1));

        jLabel10.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(60, 47, 29));
        jLabel10.setText("ROOM");
        jPanel5.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, -1, -1));

        jLabel56.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(60, 47, 29));
        jLabel56.setText("CRIME SCALE");
        jPanel5.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, -1, -1));

        criminalNameText.setBorder(null);
        jPanel5.add(criminalNameText, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 180, 20));

        jLabel13.setText("_______________________________________");
        jPanel5.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        DateOfBirthText.setBorder(null);
        DateOfBirthText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DateOfBirthTextActionPerformed(evt);
            }
        });
        jPanel5.add(DateOfBirthText, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 180, -1));

        jLabel14.setText("_______________________________________");
        jPanel5.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, -1, -1));

        YearText.setBorder(null);
        YearText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                YearTextActionPerformed(evt);
            }
        });
        jPanel5.add(YearText, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 180, 20));

        jLabel16.setText("_______________________________________");
        jPanel5.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, -1, -1));

        RoomText.setBorder(null);
        RoomText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RoomTextActionPerformed(evt);
            }
        });
        jPanel5.add(RoomText, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 180, 20));

        jLabel17.setText("_______________________________________");
        jPanel5.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, -1, -1));

        criminalIDText.setBorder(null);
        criminalIDText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                criminalIDTextActionPerformed(evt);
            }
        });
        jPanel5.add(criminalIDText, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 180, 20));

        jLabel12.setText("_______________________________________");
        jPanel5.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        jLabel18.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(60, 47, 29));
        jLabel18.setText("ID");
        jPanel5.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel19.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(60, 47, 29));
        jLabel19.setText("NAME");
        jPanel5.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        criminaladd.setBorder(null);
        criminaladd.setForeground(new java.awt.Color(60, 47, 29));
        criminaladd.setText("ADD");
        criminaladd.setFocusPainted(false);
        criminaladd.setFont(new java.awt.Font("Arial Black", 1, 13)); // NOI18N
        criminaladd.setRadius(25);
        criminaladd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                criminaladdActionPerformed(evt);
            }
        });
        jPanel5.add(criminaladd, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 420, 90, 40));

        criminaledit.setBorder(null);
        criminaledit.setForeground(new java.awt.Color(60, 47, 29));
        criminaledit.setText("EDIT");
        criminaledit.setFocusPainted(false);
        criminaledit.setFont(new java.awt.Font("Arial Black", 1, 13)); // NOI18N
        criminaledit.setRadius(25);
        criminaledit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                criminaleditActionPerformed(evt);
            }
        });
        jPanel5.add(criminaledit, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, 90, 40));

        criminaldelete.setBorder(null);
        criminaldelete.setForeground(new java.awt.Color(60, 47, 29));
        criminaldelete.setText("DELETE");
        criminaldelete.setFocusPainted(false);
        criminaldelete.setFont(new java.awt.Font("Arial Black", 1, 13)); // NOI18N
        criminaldelete.setRadius(25);
        criminaldelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                criminaldeleteActionPerformed(evt);
            }
        });
        jPanel5.add(criminaldelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 470, 90, 40));

        criminalclear.setBorder(null);
        criminalclear.setForeground(new java.awt.Color(60, 47, 29));
        criminalclear.setText("CLEAR");
        criminalclear.setFocusPainted(false);
        criminalclear.setFont(new java.awt.Font("Arial Black", 1, 13)); // NOI18N
        criminalclear.setRadius(25);
        criminalclear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                criminalclearActionPerformed(evt);
            }
        });
        jPanel5.add(criminalclear, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 470, 90, 40));

        CriminalChartButton.setBorder(null);
        CriminalChartButton.setForeground(new java.awt.Color(60, 47, 29));
        CriminalChartButton.setText("EXPORT CHART");
        CriminalChartButton.setFocusPainted(false);
        CriminalChartButton.setFont(new java.awt.Font("Arial Black", 1, 13)); // NOI18N
        CriminalChartButton.setRadius(25);
        CriminalChartButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CriminalChartButtonActionPerformed(evt);
            }
        });
        jPanel5.add(CriminalChartButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 520, 200, 40));

        GenderComboBox.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        GenderComboBox.setForeground(new java.awt.Color(255, 0, 51));
        GenderComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ", "Khác", " " }));
        GenderComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GenderComboBoxActionPerformed(evt);
            }
        });
        jPanel5.add(GenderComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 180, -1));

        CrimeScaleComboBox.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        CrimeScaleComboBox.setForeground(new java.awt.Color(255, 51, 51));
        CrimeScaleComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ít nghiêm trọng", "Nghiêm trọng", "Rất nghiêm trọng", "Đặc biệt nghiêm trọng" }));
        jPanel5.add(CrimeScaleComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, 190, 30));

        Criminal.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 570));

        jScrollPane1.setVerticalScrollBar(new prisonmanagement.view.ScrollBarCustom());

        TableCriminal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Name", "D.O.B", "Gender", "Year", "Room", "Crime", "Crime Scale"
            }
        ));
        TableCriminal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        TableCriminal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableCriminalMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TableCriminal);
        if (TableCriminal.getColumnModel().getColumnCount() > 0) {
            TableCriminal.getColumnModel().getColumn(0).setPreferredWidth(10);
            TableCriminal.getColumnModel().getColumn(1).setPreferredWidth(100);
            TableCriminal.getColumnModel().getColumn(2).setPreferredWidth(55);
            TableCriminal.getColumnModel().getColumn(3).setPreferredWidth(10);
            TableCriminal.getColumnModel().getColumn(4).setPreferredWidth(45);
            TableCriminal.getColumnModel().getColumn(5).setPreferredWidth(10);
            TableCriminal.getColumnModel().getColumn(6).setPreferredWidth(45);
        }

        Criminal.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 110, 660, 460));

        jLabel49.setIcon(new javax.swing.ImageIcon("src/main/java/prisonmanagement/icon/search-icon-256x256-1ihlz8ty.png"));
        Criminal.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 60, 10, 10));

        CriminalInformationText.setBorder(null);
        CriminalInformationText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CriminalInformationTextActionPerformed(evt);
            }
        });
        Criminal.add(CriminalInformationText, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 60, 330, -1));

        jLabel48.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        jLabel48.setText("_____________________________________________________________________________________________________________________");
        Criminal.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 70, 390, 20));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "ID", "Name", "Gender" }));
        jComboBox1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        Criminal.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 50, -1, -1));

        SearchCriminal.setText("Search");
        SearchCriminal.setFocusPainted(false);
        SearchCriminal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchCriminalActionPerformed(evt);
            }
        });
        Criminal.add(SearchCriminal, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 80, -1, -1));

        SortComboCriminal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Name", "Date", "Id" }));
        SortComboCriminal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SortComboCriminalActionPerformed(evt);
            }
        });
        Criminal.add(SortComboCriminal, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 50, -1, -1));

        SortCriminal.setText("Sort");
        SortCriminal.setFocusPainted(false);
        SortCriminal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SortCriminalActionPerformed(evt);
            }
        });
        Criminal.add(SortCriminal, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 80, -1, -1));

        jLabel6.setFont(new java.awt.Font("Arial Black", 1, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(204, 0, 0));
        jLabel6.setText("CRIMINAL INFORMATION");
        Criminal.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 20, -1, -1));

        jLabel54.setIcon(new javax.swing.ImageIcon("src/main/java/prisonmanagement/icon/search-icon-256x256-1ihlz8ty.png"));
        Criminal.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 50, 30, 30));

        DashBoard.addTab("tab2", Criminal);

        Visitor.setBackground(new java.awt.Color(255, 255, 255));
        Visitor.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        jPanel6.add(locationTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 180, -1));

        jLabel32.setText("______________________________________");
        jPanel6.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, -1, -1));

        nameTextField.setBorder(null);
        jPanel6.add(nameTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 180, -1));

        jLabel33.setText("______________________________________");
        jPanel6.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, -1, -1));

        relationshipTextField.setBorder(null);
        jPanel6.add(relationshipTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, 180, -1));

        jLabel34.setText("______________________________________");
        jPanel6.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, -1, -1));

        jLabel47.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(60, 47, 29));
        jLabel47.setText("VISITOR ID");
        jPanel6.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        addButton.setBorder(null);
        addButton.setForeground(new java.awt.Color(60, 47, 29));
        addButton.setText("ADD");
        addButton.setFocusPainted(false);
        addButton.setFont(new java.awt.Font("Arial Black", 1, 13)); // NOI18N
        addButton.setRadius(25);
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });
        jPanel6.add(addButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 420, 90, 40));

        editButton.setBorder(null);
        editButton.setForeground(new java.awt.Color(60, 47, 29));
        editButton.setText("EDIT");
        editButton.setFocusPainted(false);
        editButton.setFont(new java.awt.Font("Arial Black", 1, 13)); // NOI18N
        editButton.setRadius(25);
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });
        jPanel6.add(editButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, 90, 40));

        deleteButton.setBorder(null);
        deleteButton.setForeground(new java.awt.Color(60, 47, 29));
        deleteButton.setText("DELETE");
        deleteButton.setFocusPainted(false);
        deleteButton.setFont(new java.awt.Font("Arial Black", 1, 13)); // NOI18N
        deleteButton.setRadius(25);
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });
        jPanel6.add(deleteButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 470, 90, 40));

        clearButton.setBorder(null);
        clearButton.setForeground(new java.awt.Color(60, 47, 29));
        clearButton.setText("CLEAR");
        clearButton.setToolTipText("");
        clearButton.setFocusPainted(false);
        clearButton.setFont(new java.awt.Font("Arial Black", 1, 13)); // NOI18N
        clearButton.setRadius(25);
        jPanel6.add(clearButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 470, 90, 40));

        ChartButton.setBorder(null);
        ChartButton.setForeground(new java.awt.Color(60, 47, 29));
        ChartButton.setText("EXPORT CHART");
        ChartButton.setFocusPainted(false);
        ChartButton.setFont(new java.awt.Font("Arial Black", 1, 13)); // NOI18N
        ChartButton.setRadius(25);
        ChartButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChartButtonActionPerformed(evt);
            }
        });
        jPanel6.add(ChartButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 519, 200, 40));

        Visitor.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 219, 570));

        jLabel20.setFont(new java.awt.Font("Arial Black", 1, 20)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(204, 0, 0));
        jLabel20.setText("VISITOR INFORMATION");
        Visitor.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 20, -1, -1));

        jScrollPane1.setVerticalScrollBar(new prisonmanagement.view.ScrollBarCustom());

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
            jTable2.getColumnModel().getColumn(4).setPreferredWidth(14);
            jTable2.getColumnModel().getColumn(5).setPreferredWidth(45);
            jTable2.getColumnModel().getColumn(6).setPreferredWidth(100);
            jTable2.getColumnModel().getColumn(7).setPreferredWidth(45);
        }

        Visitor.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 110, 660, 460));

        jSearchText.setBorder(null);
        jSearchText.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jSearchText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSearchTextActionPerformed(evt);
            }
        });
        Visitor.add(jSearchText, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 60, 370, -1));

        jLabel50.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        jLabel50.setText("_____________________________________________________________________________________________________________________");
        Visitor.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 70, 390, 20));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "ID", "Name", "Date" }));
        jComboBox2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        Visitor.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 50, -1, -1));

        SearchButton.setText("Search");
        SearchButton.setFocusPainted(false);
        SearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchButtonActionPerformed(evt);
            }
        });
        Visitor.add(SearchButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 80, -1, -1));

        SortButton.setText("Sort");
        SortButton.setFocusPainted(false);
        SortButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SortButtonActionPerformed(evt);
            }
        });
        Visitor.add(SortButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 80, 70, -1));

        SortComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Name", "Date", "Id" }));
        SortComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SortComboBoxActionPerformed(evt);
            }
        });
        Visitor.add(SortComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 50, 70, -1));

        jLabel55.setIcon(new javax.swing.ImageIcon("src/main/java/prisonmanagement/icon/search-icon-256x256-1ihlz8ty.png"));
        Visitor.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 50, 30, 30));

        DashBoard.addTab("tab1", Visitor);

        getContentPane().add(DashBoard, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, -40, 880, 600));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void criminalIDTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_criminalIDTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_criminalIDTextActionPerformed

    private void DateOfBirthTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DateOfBirthTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DateOfBirthTextActionPerformed

    private void CrimeTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CrimeTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CrimeTextActionPerformed

    private void YearTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_YearTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_YearTextActionPerformed

    private void RoomTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RoomTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RoomTextActionPerformed

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:
        DashBoard.setSelectedIndex(1);
    }//GEN-LAST:event_jLabel3MouseClicked

    private void criminalIDTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_criminalIDTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_criminalIDTextFieldActionPerformed

    private void dateOfVisitTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateOfVisitTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dateOfVisitTextFieldActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        DashBoard.setSelectedIndex(2);
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
        DashBoard.setSelectedIndex(0);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel52MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel52MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel52MouseClicked

    private void jSearchTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSearchTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jSearchTextActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
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

    }//GEN-LAST:event_jTable2MouseClicked

    private void SearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchButtonActionPerformed
        // TODO add your handling code here:
        String type = (String) jComboBox2.getSelectedItem();
        if (type == "All") {
            this.displayVisitor(this.visitorController.getAllVisitor());
        } else if (type == "Name") {
            String name = this.jSearchText.getText();
            this.displayVisitor(this.visitorController.findByName(name));
        } else if (type == "ID") {
            String id = this.jSearchText.getText();
            this.displayVisitor(this.visitorController.findById(id));
        } else if (type == "Date") {
            String date = this.jSearchText.getText();
            this.displayVisitor(this.visitorController.findByDate(date));
        }
    }//GEN-LAST:event_SearchButtonActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void SortButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SortButtonActionPerformed
        // TODO add your handling code here:
        String type = (String) SortComboBox.getSelectedItem();
        if (type == "Name") {
            displayVisitor(this.visitorController.sortByName());
        } else if (type == "Date") {
            displayVisitor(this.visitorController.sortByDate());
        } else if (type == "Id"){
            displayVisitor(this.visitorController.sortById());
        }
    }//GEN-LAST:event_SortButtonActionPerformed

    private void TableCriminalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableCriminalMouseClicked
        // TODO add your handling code here:
        int row_selected = TableCriminal.getSelectedRow();
        Long ID = null;
        String dateOfBirth, gender, criminalName, year, room, crime, crimeScale;
        Object IDValue = TableCriminal.getValueAt(row_selected, 0);

        if (IDValue instanceof Long) {
            ID = (Long) IDValue;
        } else if (IDValue instanceof String) {
            ID = Long.parseLong((String) IDValue);
        } else {
            // Xử lý lỗi - giá trị không hợp lệ
        }

        criminalName = (String) TableCriminal.getValueAt(row_selected, 1);
        dateOfBirth = (String) TableCriminal.getValueAt(row_selected, 2);
        gender = (String) TableCriminal.getValueAt(row_selected, 3);
        year = (String) TableCriminal.getValueAt(row_selected, 4);
        room = (String) TableCriminal.getValueAt(row_selected, 5);
        crime = (String) TableCriminal.getValueAt(row_selected, 6);
        crimeScale = (String) TableCriminal.getValueAt(row_selected, 7);

        criminalIDText.setText(ID.toString());
        criminalNameText.setText(criminalName);
        DateOfBirthText.setText(dateOfBirth);
        GenderComboBox.setSelectedItem(gender);
        YearText.setText(year);
        RoomText.setText(room);
        CrimeText.setText(crime);
        CrimeScaleComboBox.setSelectedItem(crimeScale);
    }//GEN-LAST:event_TableCriminalMouseClicked

    private void SortComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SortComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SortComboBoxActionPerformed

    private void SortComboCriminalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SortComboCriminalActionPerformed
        // TODO add your handling code here:
         String type = (String) SortComboCriminal.getSelectedItem();
        if (type == "Name") {
            displayCriminal(this.criminalController.sortByName());
        } else if (type == "Date") {
            displayCriminal(this.criminalController.sortByDate());
        }
    }//GEN-LAST:event_SortComboCriminalActionPerformed

    private void SearchCriminalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchCriminalActionPerformed
        // TODO add your handling code here:
         String type = (String) jComboBox1.getSelectedItem();
        if (type == "All") {
            this.displayCriminal(this.criminalController.getAllCriminal());
        } else if (type == "Name") {
            String name = this.CriminalInformationText.getText();
            this.displayCriminal(this.criminalController.findByName(name));
        } else if (type == "ID") {
            String id = this.CriminalInformationText.getText();
            this.displayCriminal(this.criminalController.findById(id));
        } else if (type == "Date") {
            String date = this.CriminalInformationText.getText();
            this.displayCriminal(this.criminalController.findByDate(date));
        }else if (type == "Gender") {
            String gender = this.CriminalInformationText.getText();
            this.displayCriminal(this.criminalController.findByGender(gender));
    }//GEN-LAST:event_SearchCriminalActionPerformed
}
    private void CriminalInformationTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CriminalInformationTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CriminalInformationTextActionPerformed

    private void jLabel52MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel52MouseEntered
        // TODO add your handling code here:
        jPanel2.setBackground(new Color(214, 158, 43));
    }//GEN-LAST:event_jLabel52MouseEntered

    private void jLabel52MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel52MouseExited
        // TODO add your handling code here:
        jPanel2.setBackground(new Color(214, 179, 107));
    }//GEN-LAST:event_jLabel52MouseExited

    private void jLabel46MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel46MouseEntered
        // TODO add your handling code here:
        jPanel10.setBackground(new Color(214, 158, 43));
    }//GEN-LAST:event_jLabel46MouseEntered

    private void jLabel46MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel46MouseExited
        // TODO add your handling code here:
        jPanel10.setBackground(new Color(214, 179, 107));
    }//GEN-LAST:event_jLabel46MouseExited

    private void jLabel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseEntered
        // TODO add your handling code here:
        jPanel3.setBackground(new Color(214, 158, 43));
    }//GEN-LAST:event_jLabel2MouseEntered

    private void jLabel2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseExited
        // TODO add your handling code here:
         jPanel3.setBackground(new Color(214, 179, 107));
    }//GEN-LAST:event_jLabel2MouseExited

    private void jLabel3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseEntered
        // TODO add your handling code here:
        jPanel8.setBackground(new Color(214, 158, 43));
    }//GEN-LAST:event_jLabel3MouseEntered

    private void jLabel3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseExited
        // TODO add your handling code here:
         jPanel8.setBackground(new Color(214, 179, 107));
    }//GEN-LAST:event_jLabel3MouseExited

    private void jLabel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseEntered
        // TODO add your handling code here:
        jPanel9.setBackground(new Color(214, 158, 43));
    }//GEN-LAST:event_jLabel1MouseEntered

    private void jLabel1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseExited
        // TODO add your handling code here:
        jPanel9.setBackground(new Color(214, 179, 107));
    }//GEN-LAST:event_jLabel1MouseExited

    private void criminaladdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_criminaladdActionPerformed
        // TODO add your handling code here:
        String criminalID, criminalName, dateOfBirth, gender, year, room, crime, crimeScale;
        criminalID = criminalIDText.getText();
        criminalName = criminalNameText.getText();
        dateOfBirth = DateOfBirthText.getText();
        gender = (String)GenderComboBox.getSelectedItem();
        year = YearText.getText();
        room = RoomText.getText();
        crime = CrimeText.getText();
        crimeScale = (String)CrimeScaleComboBox.getSelectedItem();

        if (!this.criminalController.fullInformation(criminalID, criminalName, dateOfBirth, gender, year, room, crime, crimeScale)) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đủ thông tin ", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            if (this.criminalController.daTrungCriminalID(criminalID)) {
                JOptionPane.showMessageDialog(this, "Đã trùng mã ID", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                Criminal criminal = new Criminal(criminalID, criminalName, dateOfBirth, gender, year, room, crime, crimeScale);
                Element CriminalInformation = doc1.getDocumentElement();
                this.criminalController.addCriminal(doc1, CriminalInformation, criminal);
                saveFileCriminal();
                JOptionPane.showMessageDialog(this, "Thêm thành công", "Information", JOptionPane.INFORMATION_MESSAGE);
                deleteCriminal();
                displayCriminal(this.criminalController.getAllCriminal());

            }
        }
    }//GEN-LAST:event_criminaladdActionPerformed

    private void criminaleditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_criminaleditActionPerformed
        // TODO add your handling code here:
          try {
            String criminalID, criminalName, dateOfBirth, gender, year, room, crime, crimeScale, path1;
            criminalID = criminalIDText.getText();
            criminalName = criminalNameText.getText();
            dateOfBirth = DateOfBirthText.getText();
            gender = (String)GenderComboBox.getSelectedItem();
            year = YearText.getText();
            room = RoomText.getText();
            crime = CrimeText.getText();
            crimeScale = (String)CrimeScaleComboBox.getSelectedItem();
            path1 = "CriminalInformation/Criminal[@criminalID='" + criminalID + "']";
            XPathFactory xpf1 = XPathFactory.newInstance();
            XPath xp1 = xpf1.newXPath();
            Node chose1 = (Node) xp1.evaluate(path1, doc1, XPathConstants.NODE);
            NodeList cr1Criminal = chose1.getChildNodes();
            cr1Criminal.item(1).setTextContent(criminalName);
            cr1Criminal.item(3).setTextContent(dateOfBirth);
            cr1Criminal.item(5).setTextContent(gender);
            cr1Criminal.item(7).setTextContent(year);
            cr1Criminal.item(9).setTextContent(room);
            cr1Criminal.item(11).setTextContent(crime);
            cr1Criminal.item(13).setTextContent(crimeScale);
            saveFileCriminal();
            displayCriminal(this.criminalController.getAllCriminal());
        } catch (XPathExpressionException ex) {
            Logger.getLogger(DashboardView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_criminaleditActionPerformed

    private void criminaldeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_criminaldeleteActionPerformed
        // TODO add your handling code here:
         try {
            String criminalID, path1;
            criminalID = criminalIDText.getText();
            int result1;
            path1 = "CriminalInformation/Criminal[@criminalID='" + criminalID + "']";
            XPathFactory xpf1 = XPathFactory.newInstance();
            XPath xp1 = xpf1.newXPath();
            Node chose1 = (Node) xp1.evaluate(path1, doc1, XPathConstants.NODE);
            Node parent1 = chose1.getParentNode();
            result1 = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa", "Confirm", JOptionPane.YES_NO_OPTION);
            if (result1 == 0) {
                parent1.removeChild(chose1);
                saveFileCriminal();
                displayCriminal(this.criminalController.getAllCriminal());
                JOptionPane.showMessageDialog(this, "Xóa thành công!", "Information", JOptionPane.INFORMATION_MESSAGE);

            }
        } catch (XPathExpressionException ex) {
            Logger.getLogger(DashboardView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_criminaldeleteActionPerformed

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

        if (!this.visitorController.fullInformation(visitorID, criminalID, dateOfVisit, time, amountOfTime, location, name, relationship)) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đủ thông tin ", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            if (this.visitorController.daTrungVistorID(visitorID)) {
                JOptionPane.showMessageDialog(this, "Đã trùng mã VisitorID", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                Visitor visitor = new Visitor(visitorID, criminalID, dateOfVisit, time, amountOfTime, location, name, relationship);
                Element VisitorInformation = doc.getDocumentElement();
                this.visitorController.addVisitor(doc, VisitorInformation, visitor);
                saveFileVisitor();

                JOptionPane.showMessageDialog(this, "Thêm thành công", "Information", JOptionPane.INFORMATION_MESSAGE);
                deleteVisitor();
                this.displayVisitor(this.visitorController.getAllVisitor());

            }
        }
    }//GEN-LAST:event_addButtonActionPerformed

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        // TODO add your handling code here:
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

            saveFileVisitor();
            this.displayVisitor(this.visitorController.getAllVisitor());

        } catch (XPathExpressionException ex) {
            Logger.getLogger(DashboardView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_editButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
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
                saveFileVisitor();
                this.displayVisitor(this.visitorController.getAllVisitor());
                JOptionPane.showMessageDialog(this, "Xóa thành công!", "Information", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (XPathExpressionException ex) {
            Logger.getLogger(DashboardView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void SortCriminalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SortCriminalActionPerformed
//        // TODO add your handling code here:
           String type = (String) SortComboCriminal.getSelectedItem();
        if (type == "Name") {
            displayCriminal(this.criminalController.sortByName());
        } else if (type == "Date") {
            displayCriminal(this.criminalController.sortByDate());
        } else if (type == "Id"){
            displayCriminal(this.criminalController.sortById());
        }
    }//GEN-LAST:event_SortCriminalActionPerformed

    private void jLabel46MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel46MouseClicked
        // TODO add your handling code here:
                LoginView view = new LoginView();
        view.setVisible(true);
        LoginController controller = new LoginController(view);
        // hiển thị màn hình login
        controller.showLoginView();

        // ẩn dashboardView
        this.dispose();
    }//GEN-LAST:event_jLabel46MouseClicked

    private void ChartButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChartButtonActionPerformed
        // TODO add your handling code here:
        new VisitorChartMain().setVisible(true);
    }//GEN-LAST:event_ChartButtonActionPerformed

    private void GenderComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GenderComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_GenderComboBoxActionPerformed

    private void CriminalChartButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CriminalChartButtonActionPerformed
        // TODO add your handling code here:
        new CriminalChartMain().setVisible(true);
    }//GEN-LAST:event_CriminalChartButtonActionPerformed

    private void criminalclearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_criminalclearActionPerformed
        // TODO add your handling code here:
        deleteCriminal();
    }//GEN-LAST:event_criminalclearActionPerformed
  
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(DashboardView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(DashboardView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(DashboardView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(DashboardView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and displayVisitor the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                VisitorController visitorController = new VisitorController();
//                CriminalController criminalController = new CriminalController();
//                new DashboardView(visitorController, criminalController).setVisible(true);
//            }
//        });
//    }
    private void saveFileVisitor() {
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
    private void saveFileCriminal() {
        TransformerFactory tff1 = TransformerFactory.newInstance();
        try {
            Transformer tf1 = tff1.newTransformer();
            tf1.setOutputProperty(OutputKeys.INDENT, "yes");
            tf1.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "3");
            DOMSource source1 = new DOMSource(doc1);
            StreamResult result1 = new StreamResult("src/Criminal.xml");
            tf1.transform(source1, result1);
            System.out.println("Ghi file thanh cong");

        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(TaoXMLCriminal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(TaoXMLCriminal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void deleteVisitor() {
        visitorIDTextField.setText("");
        criminalIDTextField.setText("");
        dateOfVisitTextField.setText("");
        timeTextField.setText("");
        amountOfTimeTextField.setText("");
        locationTextField.setText("");
        nameTextField.setText("");
        relationshipTextField.setText("");
    }
    private void deleteCriminal() {
        criminalIDText.setText("");
        criminalNameText.setText("");
        DateOfBirthText.setText("");
        GenderComboBox.setSelectedItem("");
        YearText.setText("");
        RoomText.setText("");
        CrimeText.setText("");
        CrimeScaleComboBox.setSelectedItem("");
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private prisonmanagement.view.MyButton ChartButton;
    private javax.swing.JComboBox<String> CrimeScaleComboBox;
    private javax.swing.JTextField CrimeText;
    private javax.swing.JPanel Criminal;
    private prisonmanagement.view.MyButton CriminalChartButton;
    private javax.swing.JTextField CriminalInformationText;
    public javax.swing.JTabbedPane DashBoard;
    private javax.swing.JTextField DateOfBirthText;
    private javax.swing.JComboBox<String> GenderComboBox;
    private javax.swing.JTextField RoomText;
    private javax.swing.JButton SearchButton;
    private javax.swing.JButton SearchCriminal;
    private javax.swing.JButton SortButton;
    private javax.swing.JComboBox<String> SortComboBox;
    private javax.swing.JComboBox<String> SortComboCriminal;
    private javax.swing.JButton SortCriminal;
    private javax.swing.JTable TableCriminal;
    public javax.swing.JPanel Visitor;
    private javax.swing.JTextField YearText;
    private prisonmanagement.view.MyButton addButton;
    private javax.swing.JTextField amountOfTimeTextField;
    private prisonmanagement.view.MyButton clearButton;
    private javax.swing.JTextField criminalIDText;
    private javax.swing.JTextField criminalIDTextField;
    private javax.swing.JTextField criminalNameText;
    private prisonmanagement.view.MyButton criminaladd;
    private prisonmanagement.view.MyButton criminalclear;
    private prisonmanagement.view.MyButton criminaldelete;
    private prisonmanagement.view.MyButton criminaledit;
    private javax.swing.JTextField dateOfVisitTextField;
    private prisonmanagement.view.MyButton deleteButton;
    private prisonmanagement.view.MyButton editButton;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
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
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    public javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jSearchText;
    public javax.swing.JTable jTable2;
    private javax.swing.JTextField locationTextField;
    private javax.swing.JTextField nameTextField;
    private prisonmanagement.view.PanelRound panelRound1;
    private prisonmanagement.view.PanelRound panelRound2;
    private prisonmanagement.view.PanelRound panelRound3;
    private prisonmanagement.view.PanelRound panelRound4;
    private prisonmanagement.view.PanelRound panelRound5;
    private javax.swing.JTextField relationshipTextField;
    private prisonmanagement.slideshow.Slideshow slideshow1;
    private javax.swing.JTextField timeTextField;
    private javax.swing.JTextField visitorIDTextField;
    // End of variables declaration//GEN-END:variables
}
