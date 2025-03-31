/*
 * LDG30.java
 *
 * Created on 23.01.2009, 13:55:11
 *
 * @author Kersten Tams Copyright 2009-2018
 * @author Lothar Roth  Copyright 2012-2018
 *
 */

package my.CVNavi;

import static java.awt.Toolkit.getDefaultToolkit;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ResourceBundle;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;
import static my.CVNavi.CVNavi.debugLevel;

/**
 *
 * @author ktams
 */
public class LDG30 extends javax.swing.JFrame {

    /** Creates new form LDG30 */
    public CVNavi CVNavi;
    private String CVs;
    public String ReturnString;
    private int CV[][];
    private int c1[];
    private ResourceBundle bundle;

    public LDG30(CVNavi cvnaviThis) {
        if( cvnaviThis == null ) {
            return;
        }
        CVNavi = cvnaviThis;
        if( CVNavi.frameInstanceDEVICE != null ) {
            CVNavi.frameInstanceDEVICE.toFront();
            CVNavi.frameInstanceDEVICE.repaint();
            return;
        }

        ReturnString = "Tams Elektronik";
        CV = new int[2][125];
        c1 = new int[28];
        for( int i = 0 ; i < c1.length; i++) {
            c1[i] = i*9;
        }
        initComponents();
        bundle = java.util.ResourceBundle.getBundle("my.CVNavi/Bundle");

        ImageIcon II = new ImageIcon(getClass().getResource("/decoder.gif"));
        this.setIconImage(II.getImage());

        switch(CVNavi.Decoder)
        {
            case c.LD_G30: //LD-G-30
                jFahreigenschaften.remove(j480Hz);
                jFahreigenschaften.remove(j80Hz);
                jBild.setIcon(new ImageIcon(getClass().getResource("/LD-G-30.gif")));
                setTitle( CVNavi.getMenutext( decoderList.LD_G30 ).trim() );
                break;

            case c.LD_W32_2: //LD-W-32
            case c.LD_W32: //LD-W-32
                TitledBorder b = (TitledBorder)jPanel1.getBorder();
                b.setTitle("LD-W-32.2");
                jCV117_CV9_Text.setText("Motorfrequenz");
                jUeberlast.setText("480 Hz");
                jUeberlast.setEditable(false);
                jMotorArt.setEditable(false);
                jKp.setEditable(false);
                jKi.setEditable(false);
                jKd.setEditable(false);
                jMotorListe.setEnabled(false);
                jBild.setIcon(new ImageIcon(getClass().getResource("/LD-W-32-2.gif")));
                setTitle( CVNavi.getMenutext( decoderList.LD_W32_2 ).trim() );
                if(CVNavi.Decoder == c.LD_W32)
                {
                    jLabel66.setVisible(false);
                    jFL_3.setVisible(false);
                    jFR_3.setVisible(false);
                    jF1_3.setVisible(false);
                    jF2_3.setVisible(false);
                    jF3_3.setVisible(false);
                    jF4_3.setVisible(false);
                    jF5_3.setVisible(false);
                    jF6_3.setVisible(false);
                    jF7_3.setVisible(false);
                    jF8_3.setVisible(false);
                    jF9_3.setVisible(false);
                    jF10_3.setVisible(false);
                    jF11_3.setVisible(false);
                    jF12_3.setVisible(false);
                    
                    jLabel64.setVisible(false);
                    jLabel65.setVisible(false);
                    jLabel67.setVisible(false);
                    jLabel68.setVisible(false);
                    jLabel69.setVisible(false);
                    jDimmen3.setVisible(false);
                    jAltDim3aus.setVisible(false);
                    jAltDim3ein.setVisible(false);
                    jAltDimmenFS3.setVisible(false);
                    jAltDimmen3.setVisible(false);

                    jLabel70.setVisible(false);
                    jLabel71.setVisible(false);
                    jLabel72.setVisible(false);
                    jTast3.setVisible(false);
                    jInvertBlink3.setVisible(false);
                    jVor3.setVisible(false);
                    jRueck3.setVisible(false);

                    jBild.setIcon(new ImageIcon(getClass().getResource("/LD-W-32.gif")));
                    b.setTitle("LD-W-32");
                    setTitle( CVNavi.getMenutext( decoderList.LD_W32 ).trim() );
                }
                break;

            case c.LD_G32_2: //LD-G-32
            case c.LD_G32: //LD-G-32
                b = (TitledBorder)jPanel1.getBorder();
                b.setTitle("LD-G-32.2");
                jFahreigenschaften.remove(j480Hz);
                jFahreigenschaften.remove(j80Hz);
                jCV117_CV9_Text.setText("Motorfrequenz");
                jUeberlast.setText("32 kHz");
                jUeberlast.setEditable(false);
                jBild.setIcon(new ImageIcon(getClass().getResource("/LD-G-32-2.gif")));
                setTitle( CVNavi.getMenutext( decoderList.LD_G32_2 ).trim() );
                if(CVNavi.Decoder == c.LD_G32)
                {
                    jLabel66.setVisible(false);
                    jFL_3.setVisible(false);
                    jFR_3.setVisible(false);
                    jF1_3.setVisible(false);
                    jF2_3.setVisible(false);
                    jF3_3.setVisible(false);
                    jF4_3.setVisible(false);
                    jF5_3.setVisible(false);
                    jF6_3.setVisible(false);
                    jF7_3.setVisible(false);
                    jF8_3.setVisible(false);
                    jF9_3.setVisible(false);
                    jF10_3.setVisible(false);
                    jF11_3.setVisible(false);
                    jF12_3.setVisible(false);
                    
                    jLabel64.setVisible(false);
                    jLabel65.setVisible(false);
                    jLabel67.setVisible(false);
                    jLabel68.setVisible(false);
                    jLabel69.setVisible(false);
                    jDimmen3.setVisible(false);
                    jAltDim3aus.setVisible(false);
                    jAltDim3ein.setVisible(false);
                    jAltDimmenFS3.setVisible(false);
                    jAltDimmen3.setVisible(false);

                    jLabel70.setVisible(false);
                    jLabel71.setVisible(false);
                    jLabel72.setVisible(false);
                    jTast3.setVisible(false);
                    jInvertBlink3.setVisible(false);
                    jVor3.setVisible(false);
                    jRueck3.setVisible(false);

                    jBild.setIcon(new ImageIcon(getClass().getResource("/LD-G-32.gif")));
                    b.setTitle("LD-G-32");
                    setTitle( CVNavi.getMenutext( decoderList.LD_G32 ).trim() );
                }
                break;
            default:
                return;
        }

        //---- CV-default-Werte -----
        // Vom Decoder verwendete CVs markieren und mit Default-Werten besetzen
        initCV( 0, 0 ); // reset jCV_Anzeige (clean all entries)

        initCV( 1, 3 );
        initCV( 2, 5 );
        initCV( 3, 20 );
        initCV( 4, 15 );
        initCV( 5, 0 );
        initCV( 7, 10 );
        initCV( 8, 62 );
        initCV( 9, 0 );
        initCV( 12, 0 );
        initCV( 13, 0 );
        initCV( 17, 192 );
        initCV( 18, 255 );
        initCV( 19, 0 );
        initCV( 27, 0 );
        initCV( 29, 14 );
        initCV( 33, 1 );
        initCV( 34, 2 );
        if((CVNavi.Decoder == c.LD_G32_2) || (CVNavi.Decoder == c.LD_W32_2)) {
            initCV( 35, 4 );
        } else {
            initCV( 35, 0 );
        }
        for( int cv = 36; cv <= 46 ; cv++) {
            initCV( cv, 0 );
        }
        initCV( 49, 73 );
        initCV( 50, 40 );
        initCV( 51, 30 );
        initCV( 52, 40 );
        initCV( 53, 0 );
        initCV( 54, 0 );
        if((CVNavi.Decoder == c.LD_G32_2) || (CVNavi.Decoder == c.LD_W32_2))
            initCV( 55, 0 );
        initCV( 62, 255 );
        if((CVNavi.Decoder == c.LD_G32_2) || (CVNavi.Decoder == c.LD_W32_2))
            initCV( 63, 15 );
        initCV( 65, 0 );
        for( int i = 0 ; i < 28 ; i++ ) { // Motor Kennlinie
            initCV( 67+i, i*6);
        }
        initCV( 112, 200 );
        initCV( 113, 16 );
        initCV( 114, 4 );
        initCV( 115, 0 );
        initCV( 116, 0 );
        initCV( 117, 64 );
        initCV( 118, 255 );
        if((CVNavi.Decoder == c.LD_G32_2) || (CVNavi.Decoder == c.LD_W32_2))
            initCV( 119, 15 );
        initCV( 124, 3 );
        //---------------------------
        setLocationRelativeTo(cvnaviThis);
        setVisible(true);
        CVNavi.frameInstanceDEVICE = this;
    }

    private Boolean initCV( int cv, int value ) {
        if( cv == 0 ) {
            jCV_Anzeige.removeAllItems();
            return true;
        }
        if( value == -1 ) {
            jCV_Anzeige.removeItem("CV#"+cv);
            return( CVNavi.unsetCVvalue(CV, cv) );
        }
        jCV_Anzeige.addItem("CV#"+cv);
        return( CVNavi.setCVvalue(CV, cv, value) );
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        buttonGroup5 = new javax.swing.ButtonGroup();
        buttonGroup6 = new javax.swing.ButtonGroup();
        buttonGroup7 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jCV_Anzeige = new javax.swing.JComboBox();
        jCV_Inhalt = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jDecoderAdresse = new javax.swing.JTextField();
        jKurzeAdr = new javax.swing.JRadioButton();
        jlangeAdr = new javax.swing.JRadioButton();
        jDirekteingabe = new javax.swing.JToggleButton();
        jButton1 = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        jDecoderAdresse1 = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jDecodereigenschaften = new javax.swing.JTabbedPane();
        jCV29 = new javax.swing.JPanel();
        jRichtung = new javax.swing.JCheckBox();
        jFS = new javax.swing.JCheckBox();
        jAnalog1 = new javax.swing.JCheckBox();
        jRailCom = new javax.swing.JCheckBox();
        jAltKennlinie = new javax.swing.JCheckBox();
        jLongAddr = new javax.swing.JCheckBox();
        jLongAddr1 = new javax.swing.JCheckBox();
        jLongAddr2 = new javax.swing.JCheckBox();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jBlinkFrequenz = new javax.swing.JTextField();
        jMM_Addr_2 = new javax.swing.JTextField();
        jVersion = new javax.swing.JLabel();
        jManID = new javax.swing.JLabel();
        jBild = new javax.swing.JLabel();
        jFunctionMapping = new javax.swing.JPanel();
        jFL_1 = new javax.swing.JCheckBox();
        jFR_1 = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jF1_1 = new javax.swing.JCheckBox();
        jF2_1 = new javax.swing.JCheckBox();
        jF3_1 = new javax.swing.JCheckBox();
        jF4_1 = new javax.swing.JCheckBox();
        jF5_1 = new javax.swing.JCheckBox();
        jF9_1 = new javax.swing.JCheckBox();
        jF8_1 = new javax.swing.JCheckBox();
        jF7_1 = new javax.swing.JCheckBox();
        jF10_1 = new javax.swing.JCheckBox();
        jF11_1 = new javax.swing.JCheckBox();
        jF6_1 = new javax.swing.JCheckBox();
        jF12_1 = new javax.swing.JCheckBox();
        jF12_2 = new javax.swing.JCheckBox();
        jFL_2 = new javax.swing.JCheckBox();
        jFR_2 = new javax.swing.JCheckBox();
        jF1_2 = new javax.swing.JCheckBox();
        jF2_2 = new javax.swing.JCheckBox();
        jF3_2 = new javax.swing.JCheckBox();
        jF4_2 = new javax.swing.JCheckBox();
        jF5_2 = new javax.swing.JCheckBox();
        jF6_2 = new javax.swing.JCheckBox();
        jF7_2 = new javax.swing.JCheckBox();
        jF8_2 = new javax.swing.JCheckBox();
        jF9_2 = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jF10_2 = new javax.swing.JCheckBox();
        jF11_2 = new javax.swing.JCheckBox();
        jLabel20 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jF12_3 = new javax.swing.JCheckBox();
        jFL_3 = new javax.swing.JCheckBox();
        jFR_3 = new javax.swing.JCheckBox();
        jF1_3 = new javax.swing.JCheckBox();
        jF2_3 = new javax.swing.JCheckBox();
        jF3_3 = new javax.swing.JCheckBox();
        jF4_3 = new javax.swing.JCheckBox();
        jF5_3 = new javax.swing.JCheckBox();
        jF6_3 = new javax.swing.JCheckBox();
        jF7_3 = new javax.swing.JCheckBox();
        jF8_3 = new javax.swing.JCheckBox();
        jF9_3 = new javax.swing.JCheckBox();
        jF10_3 = new javax.swing.JCheckBox();
        jF11_3 = new javax.swing.JCheckBox();
        jLabel66 = new javax.swing.JLabel();
        jDimmen = new javax.swing.JPanel();
        jDimmen1 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jDimmen2 = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel42 = new javax.swing.JLabel();
        jAltDim1ein = new javax.swing.JRadioButton();
        jAltDim2ein = new javax.swing.JRadioButton();
        jAltDim1aus = new javax.swing.JRadioButton();
        jAltDim2aus = new javax.swing.JRadioButton();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jAltDimmenFS1 = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        jAltDimmenFS2 = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        jAltDimmen1 = new javax.swing.JTextField();
        jAltDimmen2 = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jAltDim3ein = new javax.swing.JRadioButton();
        jAltDimmen3 = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel65 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jAltDimmenFS3 = new javax.swing.JTextField();
        jDimmen3 = new javax.swing.JTextField();
        jAltDim3aus = new javax.swing.JRadioButton();
        jLabel69 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jEffekte = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel21 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jVor1 = new javax.swing.JCheckBox();
        jRueck1 = new javax.swing.JCheckBox();
        jTast1 = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jTast2 = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jRueck2 = new javax.swing.JCheckBox();
        jVor2 = new javax.swing.JCheckBox();
        jInvertBlink1 = new javax.swing.JCheckBox();
        jInvertBlink2 = new javax.swing.JCheckBox();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel71 = new javax.swing.JLabel();
        jTast3 = new javax.swing.JTextField();
        jLabel72 = new javax.swing.JLabel();
        jVor3 = new javax.swing.JCheckBox();
        jRueck3 = new javax.swing.JCheckBox();
        jInvertBlink3 = new javax.swing.JCheckBox();
        jLabel70 = new javax.swing.JLabel();
        jRangieren = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jRL_F4 = new javax.swing.JCheckBox();
        jRL_F3 = new javax.swing.JCheckBox();
        jLabel29 = new javax.swing.JLabel();
        jRL_1 = new javax.swing.JCheckBox();
        jRL_2 = new javax.swing.JCheckBox();
        jLabel58 = new javax.swing.JLabel();
        jR_F3 = new javax.swing.JCheckBox();
        jR_F4 = new javax.swing.JCheckBox();
        jLabel59 = new javax.swing.JLabel();
        jAB_F3 = new javax.swing.JCheckBox();
        jAB_F4 = new javax.swing.JCheckBox();
        jR_F1 = new javax.swing.JCheckBox();
        jR_F2 = new javax.swing.JCheckBox();
        jRL_3 = new javax.swing.JCheckBox();
        jFahreigenschaften = new javax.swing.JPanel();
        jAnfahrGeschw = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        jAnfahrVerz = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        jBremsVerz = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jVMax = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        jKp = new javax.swing.JTextField();
        jKi = new javax.swing.JTextField();
        jKd = new javax.swing.JTextField();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jMotorListe = new javax.swing.JList();
        jLabel57 = new javax.swing.JLabel();
        jPosBrems = new javax.swing.JCheckBox();
        jNegBrems = new javax.swing.JCheckBox();
        jLabel60 = new javax.swing.JLabel();
        jMotorArt = new javax.swing.JTextField();
        jCV117_CV9_Text = new javax.swing.JLabel();
        jUeberlast = new javax.swing.JTextField();
        j480Hz = new javax.swing.JRadioButton();
        j80Hz = new javax.swing.JRadioButton();
        jLabel61 = new javax.swing.JLabel();
        jAnfahrKick = new javax.swing.JTextField();
        jKennlinie = new javax.swing.JPanel();
        jFS1 = new javax.swing.JSlider();
        jLabel34 = new javax.swing.JLabel();
        jFS2 = new javax.swing.JSlider();
        jFS3 = new javax.swing.JSlider();
        jFS4 = new javax.swing.JSlider();
        jFS5 = new javax.swing.JSlider();
        jFS6 = new javax.swing.JSlider();
        jFS7 = new javax.swing.JSlider();
        jFS8 = new javax.swing.JSlider();
        jFS9 = new javax.swing.JSlider();
        jFS10 = new javax.swing.JSlider();
        jFS11 = new javax.swing.JSlider();
        jFS12 = new javax.swing.JSlider();
        jFS13 = new javax.swing.JSlider();
        jFS14 = new javax.swing.JSlider();
        jFS15 = new javax.swing.JSlider();
        jFS16 = new javax.swing.JSlider();
        jFS17 = new javax.swing.JSlider();
        jFS18 = new javax.swing.JSlider();
        jFS19 = new javax.swing.JSlider();
        jFS20 = new javax.swing.JSlider();
        jFS21 = new javax.swing.JSlider();
        jFS22 = new javax.swing.JSlider();
        jFS23 = new javax.swing.JSlider();
        jFS24 = new javax.swing.JSlider();
        jFS25 = new javax.swing.JSlider();
        jFS26 = new javax.swing.JSlider();
        jFS27 = new javax.swing.JSlider();
        jFS28 = new javax.swing.JSlider();
        jLabel35 = new javax.swing.JLabel();
        jKurve1 = new javax.swing.JRadioButton();
        jKurve2 = new javax.swing.JRadioButton();
        jKurve3 = new javax.swing.JRadioButton();
        jKurve4 = new javax.swing.JRadioButton();
        jCustom1sichern = new javax.swing.JButton();
        jCustom1laden = new javax.swing.JButton();
        jFS_Anzeige = new javax.swing.JLabel();
        jFS_AnzeigeWert = new javax.swing.JTextField();
        jAnalog = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jF1 = new javax.swing.JCheckBox();
        jF2 = new javax.swing.JCheckBox();
        jF3 = new javax.swing.JCheckBox();
        jF4 = new javax.swing.JCheckBox();
        jF5 = new javax.swing.JCheckBox();
        jF6 = new javax.swing.JCheckBox();
        jF7 = new javax.swing.JCheckBox();
        jF8 = new javax.swing.JCheckBox();
        jLabel73 = new javax.swing.JLabel();
        jAnalogRW_W = new javax.swing.JRadioButton();
        jAnalogRW_G = new javax.swing.JRadioButton();
        jKommentar = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jComment = new javax.swing.JTextArea();
        jLabel36 = new javax.swing.JLabel();
        jSave = new javax.swing.JButton();
        jOpen = new javax.swing.JButton();
        jCV_LesenSchreiben = new javax.swing.JButton();
        jAbbrechen = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("my/CVNavi/Bundle"); // NOI18N
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, bundle.getString("LDG30.jPanel1.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        jPanel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jCV_Anzeige.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCV_Anzeige.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("LDG30.jCV_Anzeige.border.title"))); // NOI18N
        jCV_Anzeige.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCV_AnzeigeActionPerformed(evt);
            }
        });

        jCV_Inhalt.setEditable(false);
        jCV_Inhalt.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCV_Inhalt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jCV_Inhalt.setText(bundle.getString("LDG30.jCV_Inhalt.text")); // NOI18N
        jCV_Inhalt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jCV_InhaltFocusLost(evt);
            }
        });
        jCV_Inhalt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jCV_InhaltKeyReleased(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText(bundle.getString("LDG30.jLabel1.text")); // NOI18N

        jDecoderAdresse.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jDecoderAdresse.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jDecoderAdresse.setText(bundle.getString("LDG30.jDecoderAdresse.text")); // NOI18N
        jDecoderAdresse.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jDecoderAdresseFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jDecoderAdresseFocusLost(evt);
            }
        });
        jDecoderAdresse.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jDecoderAdresseKeyReleased(evt);
            }
        });

        buttonGroup1.add(jKurzeAdr);
        jKurzeAdr.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jKurzeAdr.setSelected(true);
        jKurzeAdr.setText(bundle.getString("LDG30.jKurzeAdr.text")); // NOI18N
        jKurzeAdr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jKurzeAdrActionPerformed(evt);
            }
        });

        buttonGroup1.add(jlangeAdr);
        jlangeAdr.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jlangeAdr.setText(bundle.getString("LDG30.jlangeAdr.text")); // NOI18N
        jlangeAdr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jlangeAdrActionPerformed(evt);
            }
        });

        jDirekteingabe.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jDirekteingabe.setText(bundle.getString("LDG30.jDirekteingabe.text")); // NOI18N
        jDirekteingabe.setToolTipText(bundle.getString("LDG30.jDirekteingabe.toolTipText")); // NOI18N
        jDirekteingabe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDirekteingabeActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton1.setText(bundle.getString("LDG30.jButton1.text")); // NOI18N
        jButton1.setToolTipText(bundle.getString("LDG30.jButton1.toolTipText")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel27.setText(bundle.getString("LDG30.jLabel27.text")); // NOI18N

        jDecoderAdresse1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jDecoderAdresse1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jDecoderAdresse1.setText(bundle.getString("LDG30.jDecoderAdresse1.text")); // NOI18N
        jDecoderAdresse1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jDecoderAdresse1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jDecoderAdresse1FocusLost(evt);
            }
        });
        jDecoderAdresse1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jDecoderAdresse1KeyReleased(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel28.setText(bundle.getString("LDG30.jLabel28.text")); // NOI18N

        jDecodereigenschaften.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jDecodereigenschaften.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jCV29.setToolTipText(bundle.getString("LDG30.jCV29.toolTipText")); // NOI18N
        jCV29.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jCV29ComponentShown(evt);
            }
        });

        jRichtung.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRichtung.setText(bundle.getString("LDG30.jRichtung.text")); // NOI18N
        jRichtung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRichtungActionPerformed(evt);
            }
        });

        jFS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS.setSelected(true);
        jFS.setText(bundle.getString("LDG30.jFS.text")); // NOI18N
        jFS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFSActionPerformed(evt);
            }
        });

        jAnalog1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAnalog1.setSelected(true);
        jAnalog1.setText(bundle.getString("LDG30.jAnalog1.text")); // NOI18N
        jAnalog1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAnalog1ActionPerformed(evt);
            }
        });

        jRailCom.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRailCom.setSelected(true);
        jRailCom.setText(bundle.getString("LDG30.jRailCom.text")); // NOI18N
        jRailCom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRailComActionPerformed(evt);
            }
        });

        jAltKennlinie.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAltKennlinie.setText(bundle.getString("LDG30.jAltKennlinie.text")); // NOI18N
        jAltKennlinie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAltKennlinieActionPerformed(evt);
            }
        });

        jLongAddr.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLongAddr.setText(bundle.getString("LDG30.jLongAddr.text")); // NOI18N
        jLongAddr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jLongAddrActionPerformed(evt);
            }
        });

        jLongAddr1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLongAddr1.setText(bundle.getString("LDG30.jLongAddr1.text")); // NOI18N
        jLongAddr1.setEnabled(false);

        jLongAddr2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLongAddr2.setText(bundle.getString("LDG30.jLongAddr2.text")); // NOI18N
        jLongAddr2.setEnabled(false);

        jLabel38.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel38.setText(bundle.getString("LDG30.jLabel38.text")); // NOI18N

        jLabel39.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel39.setText(bundle.getString("LDG30.jLabel39.text")); // NOI18N

        jLabel40.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel40.setText(bundle.getString("LDG30.jLabel40.text")); // NOI18N

        jLabel41.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel41.setText(bundle.getString("LDG30.jLabel41.text")); // NOI18N

        jBlinkFrequenz.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jBlinkFrequenz.setText(bundle.getString("LDG30.jBlinkFrequenz.text")); // NOI18N
        jBlinkFrequenz.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jBlinkFrequenzFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jBlinkFrequenzFocusLost(evt);
            }
        });
        jBlinkFrequenz.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jBlinkFrequenzKeyReleased(evt);
            }
        });

        jMM_Addr_2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jMM_Addr_2.setText(bundle.getString("LDG30.jMM_Addr_2.text")); // NOI18N
        jMM_Addr_2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jMM_Addr_2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jMM_Addr_2FocusLost(evt);
            }
        });
        jMM_Addr_2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jMM_Addr_2KeyReleased(evt);
            }
        });

        jVersion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jVersion.setText(bundle.getString("LDG30.jVersion.text")); // NOI18N

        jManID.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jManID.setText(bundle.getString("LDG30.jManID.text")); // NOI18N

        jBild.setMaximumSize(new java.awt.Dimension(160, 100));

        javax.swing.GroupLayout jCV29Layout = new javax.swing.GroupLayout(jCV29);
        jCV29.setLayout(jCV29Layout);
        jCV29Layout.setHorizontalGroup(
            jCV29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jCV29Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jCV29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRailCom)
                    .addComponent(jAltKennlinie)
                    .addComponent(jLongAddr)
                    .addComponent(jRichtung)
                    .addComponent(jFS)
                    .addComponent(jAnalog1)
                    .addComponent(jLongAddr1)
                    .addComponent(jLongAddr2)
                    .addComponent(jManID)
                    .addComponent(jVersion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                .addGroup(jCV29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jCV29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel41)
                    .addComponent(jBlinkFrequenz, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jMM_Addr_2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBild, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31))
        );
        jCV29Layout.setVerticalGroup(
            jCV29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jCV29Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jCV29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jCV29Layout.createSequentialGroup()
                        .addComponent(jRichtung)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFS)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jAnalog1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRailCom)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jAltKennlinie))
                    .addGroup(jCV29Layout.createSequentialGroup()
                        .addComponent(jLabel38)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel40)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel39)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBlinkFrequenz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jCV29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLongAddr)
                    .addComponent(jLabel41))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jCV29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLongAddr1)
                    .addComponent(jMM_Addr_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jCV29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jCV29Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLongAddr2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jVersion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jManID))
                    .addGroup(jCV29Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jBild, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(140, Short.MAX_VALUE))
        );

        jDecodereigenschaften.addTab(bundle.getString("LDG30.jCV29.TabConstraints.tabTitle"), jCV29); // NOI18N

        jFunctionMapping.setToolTipText(bundle.getString("LDG30.jFunctionMapping.toolTipText")); // NOI18N
        jFunctionMapping.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFunctionMapping.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jFunctionMappingComponentShown(evt);
            }
        });
        jFunctionMapping.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jFL_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFL_1.setSelected(true);
        jFL_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFL_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jFL_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        jFR_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFR_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFR_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jFR_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText(bundle.getString("LDG30.jLabel4.text")); // NOI18N
        jFunctionMapping.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText(bundle.getString("LDG30.jLabel5.text")); // NOI18N
        jFunctionMapping.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 10, -1));

        jF1_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF1_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF1_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF1_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, -1, -1));

        jF2_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF2_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF2_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF2_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 80, -1, -1));

        jF3_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF3_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF3_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF3_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, -1, -1));

        jF4_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF4_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF4_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF4_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 80, -1, -1));

        jF5_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF5_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF5_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF5_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 80, -1, -1));

        jF9_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF9_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF9_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF9_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 80, -1, -1));

        jF8_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF8_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF8_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF8_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 80, -1, -1));

        jF7_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF7_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF7_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF7_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 80, -1, -1));

        jF10_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF10_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF10_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF10_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 80, -1, -1));

        jF11_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF11_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF11_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF11_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 80, -1, -1));

        jF6_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF6_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF6_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF6_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 80, -1, -1));

        jF12_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF12_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF12_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF12_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 80, -1, -1));

        jF12_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF12_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF12_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF12_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 110, -1, -1));

        jFL_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFL_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFL_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jFL_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        jFR_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFR_2.setSelected(true);
        jFR_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFR_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jFR_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, -1, -1));

        jF1_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF1_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF1_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF1_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, -1, -1));

        jF2_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF2_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF2_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF2_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 110, -1, -1));

        jF3_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF3_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF3_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF3_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, -1, -1));

        jF4_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF4_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF4_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF4_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 110, -1, -1));

        jF5_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF5_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF5_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF5_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 110, -1, -1));

        jF6_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF6_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF6_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF6_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 110, -1, -1));

        jF7_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF7_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF7_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF7_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 110, -1, -1));

        jF8_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF8_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF8_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF8_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 110, -1, -1));

        jF9_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF9_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF9_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF9_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 110, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel6.setText(bundle.getString("LDG30.jLabel6.text")); // NOI18N
        jFunctionMapping.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel7.setText(bundle.getString("LDG30.jLabel7.text")); // NOI18N
        jFunctionMapping.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel8.setText(bundle.getString("LDG30.jLabel8.text")); // NOI18N
        jFunctionMapping.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel9.setText(bundle.getString("LDG30.jLabel9.text")); // NOI18N
        jFunctionMapping.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, -1, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel10.setText(bundle.getString("LDG30.jLabel10.text")); // NOI18N
        jFunctionMapping.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, -1, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel11.setText(bundle.getString("LDG30.jLabel11.text")); // NOI18N
        jFunctionMapping.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 60, 20, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel12.setText(bundle.getString("LDG30.jLabel12.text")); // NOI18N
        jFunctionMapping.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, -1, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel13.setText(bundle.getString("LDG30.jLabel13.text")); // NOI18N
        jFunctionMapping.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 60, -1, -1));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel14.setText(bundle.getString("LDG30.jLabel14.text")); // NOI18N
        jFunctionMapping.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 60, -1, -1));

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel15.setText(bundle.getString("LDG30.jLabel15.text")); // NOI18N
        jFunctionMapping.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 60, -1, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel16.setText(bundle.getString("LDG30.jLabel16.text")); // NOI18N
        jFunctionMapping.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 60, -1, -1));

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel17.setText(bundle.getString("LDG30.jLabel17.text")); // NOI18N
        jFunctionMapping.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 60, -1, -1));

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel18.setText(bundle.getString("LDG30.jLabel18.text")); // NOI18N
        jFunctionMapping.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 60, -1, -1));

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel19.setText(bundle.getString("LDG30.jLabel19.text")); // NOI18N
        jFunctionMapping.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 60, -1, -1));

        jF10_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF10_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF10_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF10_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 110, -1, -1));

        jF11_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF11_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF11_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF11_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 110, -1, -1));

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel20.setText(bundle.getString("LDG30.jLabel20.text")); // NOI18N
        jFunctionMapping.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText(bundle.getString("LDG30.jLabel3.text")); // NOI18N
        jFunctionMapping.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 170, -1, -1));

        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel33.setText(bundle.getString("LDG30.jLabel33.text")); // NOI18N
        jFunctionMapping.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, -1, -1));

        jF12_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF12_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF12_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF12_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 140, -1, -1));

        jFL_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFL_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFL_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jFL_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, -1));

        jFR_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFR_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFR_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jFR_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, -1, -1));

        jF1_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF1_3.setSelected(true);
        jF1_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF1_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF1_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, -1, -1));

        jF2_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF2_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF2_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF2_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 140, -1, -1));

        jF3_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF3_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF3_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF3_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, -1, -1));

        jF4_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF4_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF4_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF4_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 140, -1, -1));

        jF5_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF5_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF5_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF5_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 140, -1, -1));

        jF6_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF6_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF6_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF6_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 140, -1, -1));

        jF7_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF7_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF7_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF7_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 140, -1, -1));

        jF8_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF8_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF8_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF8_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 140, -1, -1));

        jF9_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF9_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF9_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF9_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 140, -1, -1));

        jF10_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF10_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF10_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF10_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 140, -1, -1));

        jF11_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF11_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF11_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF11_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 140, -1, -1));

        jLabel66.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel66.setText(bundle.getString("LDG30.jLabel66.text")); // NOI18N
        jFunctionMapping.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, -1, -1));

        jDecodereigenschaften.addTab(bundle.getString("LDG30.jFunctionMapping.TabConstraints.tabTitle"), jFunctionMapping); // NOI18N

        jDimmen.setToolTipText(bundle.getString("LDG30.jDimmen.toolTipText")); // NOI18N
        jDimmen.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jDimmenComponentShown(evt);
            }
        });

        jDimmen1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jDimmen1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jDimmen1.setText(bundle.getString("LDG30.jDimmen1.text")); // NOI18N
        jDimmen1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jDimmen1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jDimmen1FocusLost(evt);
            }
        });
        jDimmen1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jDimmen1KeyReleased(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel23.setText(bundle.getString("LDG30.jLabel23.text")); // NOI18N

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel24.setText(bundle.getString("LDG30.jLabel24.text")); // NOI18N

        jDimmen2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jDimmen2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jDimmen2.setText(bundle.getString("LDG30.jDimmen2.text")); // NOI18N
        jDimmen2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jDimmen2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jDimmen2FocusLost(evt);
            }
        });
        jDimmen2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jDimmen2KeyReleased(evt);
            }
        });

        jLabel37.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel37.setText(bundle.getString("LDG30.jLabel37.text")); // NOI18N
        jLabel37.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel42.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel42.setText(bundle.getString("LDG30.jLabel42.text")); // NOI18N
        jLabel42.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        buttonGroup2.add(jAltDim1ein);
        jAltDim1ein.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAltDim1ein.setText(bundle.getString("LDG30.jAltDim1ein.text")); // NOI18N
        jAltDim1ein.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAltDim1einActionPerformed(evt);
            }
        });

        buttonGroup3.add(jAltDim2ein);
        jAltDim2ein.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAltDim2ein.setText(bundle.getString("LDG30.jAltDim2ein.text")); // NOI18N
        jAltDim2ein.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAltDim2einActionPerformed(evt);
            }
        });

        buttonGroup2.add(jAltDim1aus);
        jAltDim1aus.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAltDim1aus.setSelected(true);
        jAltDim1aus.setText(bundle.getString("LDG30.jAltDim1aus.text")); // NOI18N
        jAltDim1aus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAltDim1ausActionPerformed(evt);
            }
        });

        buttonGroup3.add(jAltDim2aus);
        jAltDim2aus.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAltDim2aus.setSelected(true);
        jAltDim2aus.setText(bundle.getString("LDG30.jAltDim2aus.text")); // NOI18N
        jAltDim2aus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAltDim2ausActionPerformed(evt);
            }
        });

        jLabel43.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel43.setText(bundle.getString("LDG30.jLabel43.text")); // NOI18N

        jLabel44.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel44.setText(bundle.getString("LDG30.jLabel44.text")); // NOI18N

        jLabel45.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel45.setText(bundle.getString("LDG30.jLabel45.text")); // NOI18N

        jAltDimmenFS1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAltDimmenFS1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jAltDimmenFS1.setText(bundle.getString("LDG30.jAltDimmenFS1.text")); // NOI18N
        jAltDimmenFS1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jAltDimmenFS1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jAltDimmenFS1FocusLost(evt);
            }
        });
        jAltDimmenFS1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jAltDimmenFS1KeyReleased(evt);
            }
        });

        jLabel46.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel46.setText(bundle.getString("LDG30.jLabel46.text")); // NOI18N

        jAltDimmenFS2.setEditable(false);
        jAltDimmenFS2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAltDimmenFS2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jAltDimmenFS2.setText(bundle.getString("LDG30.jAltDimmenFS2.text")); // NOI18N
        jAltDimmenFS2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jAltDimmenFS2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jAltDimmenFS2FocusLost(evt);
            }
        });

        jLabel47.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel47.setText(bundle.getString("LDG30.jLabel47.text")); // NOI18N

        jAltDimmen1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAltDimmen1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jAltDimmen1.setText(bundle.getString("LDG30.jAltDimmen1.text")); // NOI18N
        jAltDimmen1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jAltDimmen1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jAltDimmen1FocusLost(evt);
            }
        });
        jAltDimmen1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jAltDimmen1KeyReleased(evt);
            }
        });

        jAltDimmen2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAltDimmen2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jAltDimmen2.setText(bundle.getString("LDG30.jAltDimmen2.text")); // NOI18N
        jAltDimmen2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jAltDimmen2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jAltDimmen2FocusLost(evt);
            }
        });
        jAltDimmen2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jAltDimmen2KeyReleased(evt);
            }
        });

        jLabel48.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel48.setText(bundle.getString("LDG30.jLabel48.text")); // NOI18N

        jLabel62.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel62.setText(bundle.getString("LDG30.jLabel62.text")); // NOI18N

        buttonGroup6.add(jAltDim3ein);
        jAltDim3ein.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAltDim3ein.setText(bundle.getString("LDG30.jAltDim3ein.text")); // NOI18N
        jAltDim3ein.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAltDim3einActionPerformed(evt);
            }
        });

        jAltDimmen3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAltDimmen3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jAltDimmen3.setText(bundle.getString("LDG30.jAltDimmen3.text")); // NOI18N
        jAltDimmen3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jAltDimmen3FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jAltDimmen3FocusLost(evt);
            }
        });
        jAltDimmen3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jAltDimmen3KeyReleased(evt);
            }
        });

        jLabel65.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel65.setText(bundle.getString("LDG30.jLabel65.text")); // NOI18N

        jLabel67.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel67.setText(bundle.getString("LDG30.jLabel67.text")); // NOI18N

        jLabel68.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel68.setText(bundle.getString("LDG30.jLabel68.text")); // NOI18N

        jAltDimmenFS3.setEditable(false);
        jAltDimmenFS3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAltDimmenFS3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jAltDimmenFS3.setText(bundle.getString("LDG30.jAltDimmenFS3.text")); // NOI18N
        jAltDimmenFS3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jAltDimmenFS3FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jAltDimmenFS3FocusLost(evt);
            }
        });
        jAltDimmenFS3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jAltDimmenFS3KeyReleased(evt);
            }
        });

        jDimmen3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jDimmen3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jDimmen3.setText(bundle.getString("LDG30.jDimmen3.text")); // NOI18N
        jDimmen3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jDimmen3FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jDimmen3FocusLost(evt);
            }
        });
        jDimmen3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jDimmen3KeyReleased(evt);
            }
        });

        buttonGroup6.add(jAltDim3aus);
        jAltDim3aus.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAltDim3aus.setSelected(true);
        jAltDim3aus.setText(bundle.getString("LDG30.jAltDim3aus.text")); // NOI18N
        jAltDim3aus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAltDim3ausActionPerformed(evt);
            }
        });

        jLabel69.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel69.setText(bundle.getString("LDG30.jLabel69.text")); // NOI18N

        jLabel64.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel64.setText(bundle.getString("LDG30.jLabel64.text")); // NOI18N
        jLabel64.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel63.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel63.setText(bundle.getString("LDG30.jLabel63.text")); // NOI18N

        javax.swing.GroupLayout jDimmenLayout = new javax.swing.GroupLayout(jDimmen);
        jDimmen.setLayout(jDimmenLayout);
        jDimmenLayout.setHorizontalGroup(
            jDimmenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDimmenLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jAltDimmenFS3, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jDimmenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel65)
                    .addComponent(jAltDimmen3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
            .addGroup(jDimmenLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDimmenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDimmenLayout.createSequentialGroup()
                        .addGroup(jDimmenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel42)
                            .addComponent(jLabel37)
                            .addGroup(jDimmenLayout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addGroup(jDimmenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jDimmen1)
                                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jDimmen2))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jDimmenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jDimmenLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jDimmenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jDimmenLayout.createSequentialGroup()
                                        .addGroup(jDimmenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jAltDim1aus)
                                            .addComponent(jAltDim1ein))
                                        .addGap(37, 37, 37)
                                        .addGroup(jDimmenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jAltDimmenFS1))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jDimmenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jAltDimmen1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel47)))
                                    .addComponent(jLabel43))
                                .addContainerGap())
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDimmenLayout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addGroup(jDimmenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jDimmenLayout.createSequentialGroup()
                                        .addGroup(jDimmenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jAltDim2ein)
                                            .addComponent(jAltDim2aus))
                                        .addGap(37, 37, 37)
                                        .addGroup(jDimmenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jDimmenLayout.createSequentialGroup()
                                                .addComponent(jLabel62)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                            .addGroup(jDimmenLayout.createSequentialGroup()
                                                .addGroup(jDimmenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jDimmenLayout.createSequentialGroup()
                                                        .addComponent(jLabel46)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                    .addGroup(jDimmenLayout.createSequentialGroup()
                                                        .addComponent(jAltDimmenFS2)
                                                        .addGap(15, 15, 15)))
                                                .addGroup(jDimmenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel48)
                                                    .addComponent(jAltDimmen2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(28, 28, 28))))
                                    .addGroup(jDimmenLayout.createSequentialGroup()
                                        .addComponent(jLabel44)
                                        .addGap(0, 0, Short.MAX_VALUE))))))
                    .addGroup(jDimmenLayout.createSequentialGroup()
                        .addGroup(jDimmenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator3)
                            .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDimmenLayout.createSequentialGroup()
                                .addGroup(jDimmenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jDimmenLayout.createSequentialGroup()
                                        .addGap(40, 40, 40)
                                        .addGroup(jDimmenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jDimmen3, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel67)))
                                    .addComponent(jLabel64))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                                .addGroup(jDimmenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel69)
                                    .addGroup(jDimmenLayout.createSequentialGroup()
                                        .addGroup(jDimmenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jAltDim3ein)
                                            .addComponent(jAltDim3aus))
                                        .addGap(40, 40, 40)
                                        .addGroup(jDimmenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel68)
                                            .addComponent(jLabel63))))
                                .addGap(66, 66, 66)))
                        .addContainerGap())))
        );
        jDimmenLayout.setVerticalGroup(
            jDimmenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDimmenLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDimmenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDimmenLayout.createSequentialGroup()
                        .addGroup(jDimmenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel37)
                            .addComponent(jLabel43))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDimmen1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jDimmenLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jDimmenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jDimmenLayout.createSequentialGroup()
                                .addComponent(jAltDim1ein)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jAltDim1aus))
                            .addGroup(jDimmenLayout.createSequentialGroup()
                                .addGroup(jDimmenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel45)
                                    .addComponent(jLabel47))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jDimmenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jAltDimmenFS1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jAltDimmen1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(39, 39, 39)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jLabel44)
                .addGap(18, 18, 18)
                .addGroup(jDimmenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDimmenLayout.createSequentialGroup()
                        .addComponent(jLabel42)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDimmen2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jDimmenLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jAltDim2ein)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jAltDim2aus))
                    .addGroup(jDimmenLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel62)
                        .addGap(2, 2, 2)
                        .addGroup(jDimmenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel46)
                            .addComponent(jLabel48))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jDimmenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jAltDimmenFS2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jAltDimmen2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(11, 11, 11)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jDimmenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDimmenLayout.createSequentialGroup()
                        .addGroup(jDimmenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jDimmenLayout.createSequentialGroup()
                                .addGap(66, 66, 66)
                                .addComponent(jLabel65))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDimmenLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel69)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel68)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel63)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jDimmenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jAltDimmenFS3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jAltDimmen3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDimmenLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel64)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jDimmenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDimmenLayout.createSequentialGroup()
                                .addComponent(jLabel67)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jDimmen3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDimmenLayout.createSequentialGroup()
                                .addComponent(jAltDim3ein)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jAltDim3aus)))))
                .addGap(47, 47, 47))
        );

        jDecodereigenschaften.addTab(bundle.getString("LDG30.jDimmen.TabConstraints.tabTitle"), jDimmen); // NOI18N

        jEffekte.setToolTipText(bundle.getString("LDG30.jEffekte.toolTipText")); // NOI18N
        jEffekte.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jEffekte.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jEffekteComponentShown(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel21.setText(bundle.getString("LDG30.jLabel21.text")); // NOI18N
        jLabel21.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel25.setText(bundle.getString("LDG30.jLabel25.text")); // NOI18N

        jVor1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jVor1.setSelected(true);
        jVor1.setText(bundle.getString("LDG30.jVor1.text")); // NOI18N
        jVor1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jVor1ActionPerformed(evt);
            }
        });

        jRueck1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRueck1.setSelected(true);
        jRueck1.setText(bundle.getString("LDG30.jRueck1.text")); // NOI18N
        jRueck1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRueck1ActionPerformed(evt);
            }
        });

        jTast1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTast1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTast1.setText(bundle.getString("LDG30.jTast1.text")); // NOI18N
        jTast1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTast1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTast1FocusLost(evt);
            }
        });
        jTast1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTast1KeyReleased(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel30.setText(bundle.getString("LDG30.jLabel30.text")); // NOI18N

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel22.setText(bundle.getString("LDG30.jLabel22.text")); // NOI18N
        jLabel22.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel26.setText(bundle.getString("LDG30.jLabel26.text")); // NOI18N

        jTast2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTast2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTast2.setText(bundle.getString("LDG30.jTast2.text")); // NOI18N
        jTast2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTast2ActionPerformed(evt);
            }
        });
        jTast2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTast2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTast2FocusLost(evt);
            }
        });
        jTast2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTast2KeyReleased(evt);
            }
        });

        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel32.setText(bundle.getString("LDG30.jLabel32.text")); // NOI18N

        jRueck2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRueck2.setSelected(true);
        jRueck2.setText(bundle.getString("LDG30.jRueck2.text")); // NOI18N
        jRueck2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRueck2ActionPerformed(evt);
            }
        });

        jVor2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jVor2.setSelected(true);
        jVor2.setText(bundle.getString("LDG30.jVor2.text")); // NOI18N
        jVor2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jVor2ActionPerformed(evt);
            }
        });

        jInvertBlink1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jInvertBlink1.setText(bundle.getString("LDG30.jInvertBlink1.text")); // NOI18N
        jInvertBlink1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jInvertBlink1ActionPerformed(evt);
            }
        });

        jInvertBlink2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jInvertBlink2.setText(bundle.getString("LDG30.jInvertBlink2.text")); // NOI18N
        jInvertBlink2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jInvertBlink2ActionPerformed(evt);
            }
        });

        jLabel71.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel71.setText(bundle.getString("LDG30.jLabel71.text")); // NOI18N

        jTast3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTast3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTast3.setText(bundle.getString("LDG30.jTast3.text")); // NOI18N
        jTast3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTast3FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTast3FocusLost(evt);
            }
        });
        jTast3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTast3KeyReleased(evt);
            }
        });

        jLabel72.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel72.setText(bundle.getString("LDG30.jLabel72.text")); // NOI18N

        jVor3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jVor3.setSelected(true);
        jVor3.setText(bundle.getString("LDG30.jVor3.text")); // NOI18N
        jVor3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jVor3ActionPerformed(evt);
            }
        });

        jRueck3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRueck3.setSelected(true);
        jRueck3.setText(bundle.getString("LDG30.jRueck3.text")); // NOI18N
        jRueck3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRueck3ActionPerformed(evt);
            }
        });

        jInvertBlink3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jInvertBlink3.setText(bundle.getString("LDG30.jInvertBlink3.text")); // NOI18N
        jInvertBlink3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jInvertBlink3ActionPerformed(evt);
            }
        });

        jLabel70.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel70.setText(bundle.getString("LDG30.jLabel70.text")); // NOI18N
        jLabel70.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jEffekteLayout = new javax.swing.GroupLayout(jEffekte);
        jEffekte.setLayout(jEffekteLayout);
        jEffekteLayout.setHorizontalGroup(
            jEffekteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jEffekteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jEffekteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel70)
                    .addGroup(jEffekteLayout.createSequentialGroup()
                        .addGroup(jEffekteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1)
                            .addComponent(jSeparator2)
                            .addGroup(jEffekteLayout.createSequentialGroup()
                                .addGroup(jEffekteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel26)
                                    .addGroup(jEffekteLayout.createSequentialGroup()
                                        .addGroup(jEffekteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel71)
                                            .addGroup(jEffekteLayout.createSequentialGroup()
                                                .addGap(40, 40, 40)
                                                .addComponent(jTast3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(90, 90, 90)
                                        .addGroup(jEffekteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel72)
                                            .addComponent(jVor3)
                                            .addComponent(jRueck3)))
                                    .addGroup(jEffekteLayout.createSequentialGroup()
                                        .addGroup(jEffekteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel25)
                                            .addGroup(jEffekteLayout.createSequentialGroup()
                                                .addGap(40, 40, 40)
                                                .addGroup(jEffekteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jInvertBlink1)
                                                    .addComponent(jTast1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGap(90, 90, 90)
                                        .addGroup(jEffekteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel30)
                                            .addComponent(jVor1)
                                            .addComponent(jRueck1)))
                                    .addComponent(jLabel21)
                                    .addComponent(jLabel22)
                                    .addGroup(jEffekteLayout.createSequentialGroup()
                                        .addGap(40, 40, 40)
                                        .addGroup(jEffekteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jInvertBlink3)
                                            .addGroup(jEffekteLayout.createSequentialGroup()
                                                .addGroup(jEffekteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jInvertBlink2)
                                                    .addComponent(jTast2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(105, 105, 105)
                                                .addGroup(jEffekteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jVor2)
                                                    .addComponent(jRueck2)
                                                    .addComponent(jLabel32))))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addContainerGap())))
        );
        jEffekteLayout.setVerticalGroup(
            jEffekteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jEffekteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21)
                .addGap(18, 18, 18)
                .addGroup(jEffekteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(jLabel30))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jEffekteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTast1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jVor1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jEffekteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jInvertBlink1)
                    .addComponent(jRueck1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel22)
                .addGap(18, 18, 18)
                .addGroup(jEffekteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(jLabel32))
                .addGap(6, 6, 6)
                .addGroup(jEffekteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTast2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jVor2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jEffekteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jInvertBlink2)
                    .addComponent(jRueck2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel70)
                .addGap(20, 20, 20)
                .addGroup(jEffekteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel71)
                    .addComponent(jLabel72))
                .addGap(6, 6, 6)
                .addGroup(jEffekteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTast3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jVor3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jEffekteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRueck3)
                    .addComponent(jInvertBlink3))
                .addContainerGap(62, Short.MAX_VALUE))
        );

        jDecodereigenschaften.addTab(bundle.getString("LDG30.jEffekte.TabConstraints.tabTitle"), jEffekte); // NOI18N

        jRangieren.setToolTipText(bundle.getString("LDG30.jRangieren.toolTipText")); // NOI18N
        jRangieren.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jRangierenComponentShown(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel31.setText(bundle.getString("LDG30.jLabel31.text")); // NOI18N

        jRL_F4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRL_F4.setText(bundle.getString("LDG30.jRL_F4.text")); // NOI18N
        jRL_F4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRL_F4ActionPerformed(evt);
            }
        });

        jRL_F3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRL_F3.setText(bundle.getString("LDG30.jRL_F3.text")); // NOI18N
        jRL_F3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRL_F3ActionPerformed(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel29.setText(bundle.getString("LDG30.jLabel29.text")); // NOI18N

        jRL_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRL_1.setText(bundle.getString("LDG30.jRL_1.text")); // NOI18N
        jRL_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRL_1ActionPerformed(evt);
            }
        });

        jRL_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRL_2.setText(bundle.getString("LDG30.jRL_2.text")); // NOI18N
        jRL_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRL_2ActionPerformed(evt);
            }
        });

        jLabel58.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel58.setText(bundle.getString("LDG30.jLabel58.text")); // NOI18N

        jR_F3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jR_F3.setText(bundle.getString("LDG30.jR_F3.text")); // NOI18N
        jR_F3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jR_F3ActionPerformed(evt);
            }
        });

        jR_F4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jR_F4.setText(bundle.getString("LDG30.jR_F4.text")); // NOI18N
        jR_F4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jR_F4ActionPerformed(evt);
            }
        });

        jLabel59.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel59.setText(bundle.getString("LDG30.jLabel59.text")); // NOI18N

        jAB_F3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAB_F3.setText(bundle.getString("LDG30.jAB_F3.text")); // NOI18N
        jAB_F3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAB_F3ActionPerformed(evt);
            }
        });

        jAB_F4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAB_F4.setText(bundle.getString("LDG30.jAB_F4.text")); // NOI18N
        jAB_F4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAB_F4ActionPerformed(evt);
            }
        });

        jR_F1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jR_F1.setText(bundle.getString("LDG30.jR_F1.text")); // NOI18N
        jR_F1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jR_F1ActionPerformed(evt);
            }
        });

        jR_F2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jR_F2.setText(bundle.getString("LDG30.jR_F2.text")); // NOI18N
        jR_F2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jR_F2ActionPerformed(evt);
            }
        });

        jRL_3.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jRL_3.setText(bundle.getString("LDG30.jRL_3.text")); // NOI18N
        jRL_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRL_3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jRangierenLayout = new javax.swing.GroupLayout(jRangieren);
        jRangieren.setLayout(jRangierenLayout);
        jRangierenLayout.setHorizontalGroup(
            jRangierenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jRangierenLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jRangierenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel31)
                    .addComponent(jLabel29)
                    .addGroup(jRangierenLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jRangierenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRL_1)
                            .addComponent(jRL_2)
                            .addComponent(jRL_F4)
                            .addComponent(jRL_F3)
                            .addComponent(jRL_3))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(jRangierenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel58)
                    .addComponent(jLabel59)
                    .addGroup(jRangierenLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jRangierenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jRangierenLayout.createSequentialGroup()
                                .addGroup(jRangierenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jR_F1)
                                    .addComponent(jR_F2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jRangierenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jR_F3)
                                    .addComponent(jR_F4)))
                            .addComponent(jAB_F3)
                            .addComponent(jAB_F4))))
                .addGap(42, 42, 42))
        );
        jRangierenLayout.setVerticalGroup(
            jRangierenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jRangierenLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jRangierenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jRangierenLayout.createSequentialGroup()
                        .addComponent(jLabel58)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jRangierenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jRangierenLayout.createSequentialGroup()
                                .addComponent(jR_F1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jR_F2))
                            .addGroup(jRangierenLayout.createSequentialGroup()
                                .addComponent(jR_F3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jR_F4)))
                        .addGap(46, 46, 46)
                        .addComponent(jLabel59)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jAB_F3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jAB_F4))
                    .addGroup(jRangierenLayout.createSequentialGroup()
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRL_1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRL_2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRL_3)
                        .addGap(23, 23, 23)
                        .addComponent(jLabel31)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRL_F3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRL_F4)))
                .addContainerGap(228, Short.MAX_VALUE))
        );

        jDecodereigenschaften.addTab(bundle.getString("LDG30.jRangieren.TabConstraints.tabTitle"), jRangieren); // NOI18N

        jFahreigenschaften.setToolTipText(bundle.getString("LDG30.jFahreigenschaften.toolTipText")); // NOI18N
        jFahreigenschaften.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFahreigenschaften.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jFahreigenschaftenComponentShown(evt);
            }
        });

        jAnfahrGeschw.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAnfahrGeschw.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jAnfahrGeschw.setText(bundle.getString("LDG30.jAnfahrGeschw.text")); // NOI18N
        jAnfahrGeschw.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jAnfahrGeschwFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jAnfahrGeschwFocusLost(evt);
            }
        });
        jAnfahrGeschw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jAnfahrGeschwKeyReleased(evt);
            }
        });

        jLabel49.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel49.setText(bundle.getString("LDG30.jLabel49.text")); // NOI18N

        jAnfahrVerz.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAnfahrVerz.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jAnfahrVerz.setText(bundle.getString("LDG30.jAnfahrVerz.text")); // NOI18N
        jAnfahrVerz.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jAnfahrVerzFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jAnfahrVerzFocusLost(evt);
            }
        });
        jAnfahrVerz.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jAnfahrVerzKeyReleased(evt);
            }
        });

        jLabel50.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel50.setText(bundle.getString("LDG30.jLabel50.text")); // NOI18N

        jBremsVerz.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBremsVerz.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jBremsVerz.setText(bundle.getString("LDG30.jBremsVerz.text")); // NOI18N
        jBremsVerz.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jBremsVerzFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jBremsVerzFocusLost(evt);
            }
        });
        jBremsVerz.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jBremsVerzKeyReleased(evt);
            }
        });

        jLabel51.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel51.setText(bundle.getString("LDG30.jLabel51.text")); // NOI18N

        jLabel52.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel52.setText(bundle.getString("LDG30.jLabel52.text")); // NOI18N

        jVMax.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jVMax.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jVMax.setText(bundle.getString("LDG30.jVMax.text")); // NOI18N
        jVMax.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jVMaxFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jVMaxFocusLost(evt);
            }
        });
        jVMax.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jVMaxKeyReleased(evt);
            }
        });

        jLabel53.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel53.setText(bundle.getString("LDG30.jLabel53.text")); // NOI18N

        jKp.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jKp.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jKp.setText(bundle.getString("LDG30.jKp.text")); // NOI18N
        jKp.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jKpFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jKpFocusLost(evt);
            }
        });
        jKp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jKpKeyReleased(evt);
            }
        });

        jKi.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jKi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jKi.setText(bundle.getString("LDG30.jKi.text")); // NOI18N
        jKi.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jKiFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jKiFocusLost(evt);
            }
        });
        jKi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jKiKeyReleased(evt);
            }
        });

        jKd.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jKd.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jKd.setText(bundle.getString("LDG30.jKd.text")); // NOI18N
        jKd.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jKdFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jKdFocusLost(evt);
            }
        });
        jKd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jKdKeyReleased(evt);
            }
        });

        jLabel54.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel54.setText(bundle.getString("LDG30.jLabel54.text")); // NOI18N

        jLabel55.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel55.setText(bundle.getString("LDG30.jLabel55.text")); // NOI18N

        jLabel56.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel56.setText(bundle.getString("LDG30.jLabel56.text")); // NOI18N

        jMotorListe.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMotorListe.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "HLA", "Hamo-Magnet", "Roco", "Fleischmann", "Piko", "Brawa", "Gützold", "Mehano", "(Mini)Trix", "Arnold" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jMotorListe.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jMotorListe.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jMotorListeFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jMotorListeFocusLost(evt);
            }
        });
        jMotorListe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jMotorListeMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(jMotorListe);

        jLabel57.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel57.setText(bundle.getString("LDG30.jLabel57.text")); // NOI18N

        jPosBrems.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPosBrems.setText(bundle.getString("LDG30.jPosBrems.text")); // NOI18N
        jPosBrems.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPosBremsActionPerformed(evt);
            }
        });

        jNegBrems.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jNegBrems.setText(bundle.getString("LDG30.jNegBrems.text")); // NOI18N
        jNegBrems.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jNegBremsActionPerformed(evt);
            }
        });

        jLabel60.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel60.setText(bundle.getString("LDG30.jLabel60.text")); // NOI18N

        jMotorArt.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMotorArt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jMotorArt.setText(bundle.getString("LDG30.jMotorArt.text")); // NOI18N
        jMotorArt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jMotorArtFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jMotorArtFocusLost(evt);
            }
        });
        jMotorArt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jMotorArtKeyReleased(evt);
            }
        });

        jCV117_CV9_Text.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCV117_CV9_Text.setText(bundle.getString("LDG30.jCV117_CV9_Text.text")); // NOI18N

        jUeberlast.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jUeberlast.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jUeberlast.setText(bundle.getString("LDG30.jUeberlast.text")); // NOI18N
        jUeberlast.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jUeberlastFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jUeberlastFocusLost(evt);
            }
        });
        jUeberlast.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jUeberlastKeyReleased(evt);
            }
        });

        buttonGroup5.add(j480Hz);
        j480Hz.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        j480Hz.setSelected(true);
        j480Hz.setText(bundle.getString("LDG30.j480Hz.text")); // NOI18N
        j480Hz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                j480HzActionPerformed(evt);
            }
        });

        buttonGroup5.add(j80Hz);
        j80Hz.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        j80Hz.setText(bundle.getString("LDG30.j80Hz.text")); // NOI18N
        j80Hz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                j80HzActionPerformed(evt);
            }
        });

        jLabel61.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel61.setText(bundle.getString("LDG30.jLabel61.text")); // NOI18N

        jAnfahrKick.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAnfahrKick.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jAnfahrKick.setText(bundle.getString("LDG30.jAnfahrKick.text")); // NOI18N
        jAnfahrKick.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jAnfahrKickFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jAnfahrKickFocusLost(evt);
            }
        });
        jAnfahrKick.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jAnfahrKickKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jFahreigenschaftenLayout = new javax.swing.GroupLayout(jFahreigenschaften);
        jFahreigenschaften.setLayout(jFahreigenschaftenLayout);
        jFahreigenschaftenLayout.setHorizontalGroup(
            jFahreigenschaftenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFahreigenschaftenLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jFahreigenschaftenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPosBrems)
                    .addComponent(jNegBrems)
                    .addGroup(jFahreigenschaftenLayout.createSequentialGroup()
                        .addGroup(jFahreigenschaftenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel52)
                            .addComponent(jLabel49)
                            .addComponent(jLabel50)
                            .addComponent(jLabel51)
                            .addComponent(jLabel60)
                            .addGroup(jFahreigenschaftenLayout.createSequentialGroup()
                                .addComponent(jCV117_CV9_Text)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jUeberlast, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel61))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jFahreigenschaftenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jFahreigenschaftenLayout.createSequentialGroup()
                                .addGap(64, 64, 64)
                                .addGroup(jFahreigenschaftenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(j80Hz)
                                    .addComponent(j480Hz)))
                            .addComponent(jMotorArt, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jFahreigenschaftenLayout.createSequentialGroup()
                                .addGroup(jFahreigenschaftenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jBremsVerz, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jAnfahrVerz, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jAnfahrGeschw, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(46, 46, 46)
                                .addGroup(jFahreigenschaftenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jFahreigenschaftenLayout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addGroup(jFahreigenschaftenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jFahreigenschaftenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel55)
                                                .addComponent(jLabel54))
                                            .addComponent(jLabel56))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jFahreigenschaftenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jKd, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jKi, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jKp, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jLabel53, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addComponent(jVMax, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jAnfahrKick, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jFahreigenschaftenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel57)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jFahreigenschaftenLayout.setVerticalGroup(
            jFahreigenschaftenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFahreigenschaftenLayout.createSequentialGroup()
                .addGroup(jFahreigenschaftenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jFahreigenschaftenLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel57)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE))
                    .addGroup(jFahreigenschaftenLayout.createSequentialGroup()
                        .addGroup(jFahreigenschaftenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jFahreigenschaftenLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel53)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jFahreigenschaftenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jKp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel54))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jFahreigenschaftenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jKi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel55))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jFahreigenschaftenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jKd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel56)))
                            .addGroup(jFahreigenschaftenLayout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(jFahreigenschaftenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jFahreigenschaftenLayout.createSequentialGroup()
                                        .addComponent(jAnfahrVerz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jFahreigenschaftenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jBremsVerz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel51))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jVMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jFahreigenschaftenLayout.createSequentialGroup()
                                        .addComponent(jLabel50)
                                        .addGap(57, 57, 57))
                                    .addGroup(jFahreigenschaftenLayout.createSequentialGroup()
                                        .addGroup(jFahreigenschaftenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel49)
                                            .addComponent(jAnfahrGeschw, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(82, 82, 82))
                                    .addComponent(jLabel52))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jFahreigenschaftenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel60)
                                    .addComponent(jMotorArt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jFahreigenschaftenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jAnfahrKick, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel61))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 141, Short.MAX_VALUE)
                        .addGroup(jFahreigenschaftenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jFahreigenschaftenLayout.createSequentialGroup()
                                .addGroup(jFahreigenschaftenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jCV117_CV9_Text)
                                    .addComponent(jUeberlast, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(14, 14, 14))
                            .addGroup(jFahreigenschaftenLayout.createSequentialGroup()
                                .addComponent(j480Hz)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(j80Hz)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addComponent(jPosBrems)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jNegBrems)))
                .addContainerGap())
        );

        jDecodereigenschaften.addTab(bundle.getString("LDG30.jFahreigenschaften.TabConstraints.tabTitle"), jFahreigenschaften); // NOI18N

        jKennlinie.setToolTipText(bundle.getString("LDG30.jKennlinie.toolTipText")); // NOI18N
        jKennlinie.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jKennlinie.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jKennlinieComponentShown(evt);
            }
        });
        jKennlinie.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jFS1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS1.setMaximum(255);
        jFS1.setOrientation(javax.swing.JSlider.VERTICAL);
        jFS1.setValue(0);
        jFS1.setAlignmentX(0.0F);
        jFS1.setAlignmentY(0.0F);
        jFS1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jFS1MouseReleased(evt);
            }
        });
        jKennlinie.add(jFS1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 10, 250));

        jLabel34.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel34.setText(bundle.getString("LDG30.jLabel34.text")); // NOI18N
        jKennlinie.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 270, -1, -1));

        jFS2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS2.setMaximum(255);
        jFS2.setOrientation(javax.swing.JSlider.VERTICAL);
        jFS2.setValue(2);
        jFS2.setAlignmentX(0.0F);
        jFS2.setAlignmentY(0.0F);
        jFS2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jFS2MouseReleased(evt);
            }
        });
        jKennlinie.add(jFS2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 10, 250));

        jFS3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS3.setMaximum(255);
        jFS3.setOrientation(javax.swing.JSlider.VERTICAL);
        jFS3.setValue(4);
        jFS3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jFS3MouseReleased(evt);
            }
        });
        jKennlinie.add(jFS3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 10, 250));

        jFS4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS4.setMaximum(255);
        jFS4.setOrientation(javax.swing.JSlider.VERTICAL);
        jFS4.setValue(6);
        jFS4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jFS4MouseReleased(evt);
            }
        });
        jKennlinie.add(jFS4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 10, 250));

        jFS5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS5.setMaximum(255);
        jFS5.setOrientation(javax.swing.JSlider.VERTICAL);
        jFS5.setValue(9);
        jFS5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jFS5MouseReleased(evt);
            }
        });
        jKennlinie.add(jFS5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, 10, 250));

        jFS6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS6.setMaximum(255);
        jFS6.setOrientation(javax.swing.JSlider.VERTICAL);
        jFS6.setValue(12);
        jFS6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jFS6MouseReleased(evt);
            }
        });
        jKennlinie.add(jFS6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, 10, 250));

        jFS7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS7.setMaximum(255);
        jFS7.setOrientation(javax.swing.JSlider.VERTICAL);
        jFS7.setValue(15);
        jFS7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jFS7MouseReleased(evt);
            }
        });
        jKennlinie.add(jFS7, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 10, 250));

        jFS8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS8.setMaximum(255);
        jFS8.setOrientation(javax.swing.JSlider.VERTICAL);
        jFS8.setValue(19);
        jFS8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jFS8MouseReleased(evt);
            }
        });
        jKennlinie.add(jFS8, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, 10, 250));

        jFS9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS9.setMaximum(255);
        jFS9.setOrientation(javax.swing.JSlider.VERTICAL);
        jFS9.setValue(23);
        jFS9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jFS9MouseReleased(evt);
            }
        });
        jKennlinie.add(jFS9, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, 10, 250));

        jFS10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS10.setMaximum(255);
        jFS10.setOrientation(javax.swing.JSlider.VERTICAL);
        jFS10.setValue(27);
        jFS10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jFS10MouseReleased(evt);
            }
        });
        jKennlinie.add(jFS10, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 10, 250));

        jFS11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS11.setMaximum(255);
        jFS11.setOrientation(javax.swing.JSlider.VERTICAL);
        jFS11.setValue(33);
        jFS11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jFS11MouseReleased(evt);
            }
        });
        jKennlinie.add(jFS11, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, 10, 250));

        jFS12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS12.setMaximum(255);
        jFS12.setOrientation(javax.swing.JSlider.VERTICAL);
        jFS12.setValue(39);
        jFS12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jFS12MouseReleased(evt);
            }
        });
        jKennlinie.add(jFS12, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, 10, 250));

        jFS13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS13.setMaximum(255);
        jFS13.setOrientation(javax.swing.JSlider.VERTICAL);
        jFS13.setValue(45);
        jFS13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jFS13MouseReleased(evt);
            }
        });
        jKennlinie.add(jFS13, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, 10, 250));

        jFS14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS14.setMaximum(255);
        jFS14.setOrientation(javax.swing.JSlider.VERTICAL);
        jFS14.setValue(53);
        jFS14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jFS14MouseReleased(evt);
            }
        });
        jKennlinie.add(jFS14, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, 10, 250));

        jFS15.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS15.setMaximum(255);
        jFS15.setOrientation(javax.swing.JSlider.VERTICAL);
        jFS15.setValue(61);
        jFS15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jFS15MouseReleased(evt);
            }
        });
        jKennlinie.add(jFS15, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, 10, 250));

        jFS16.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS16.setMaximum(255);
        jFS16.setOrientation(javax.swing.JSlider.VERTICAL);
        jFS16.setValue(69);
        jFS16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jFS16MouseReleased(evt);
            }
        });
        jKennlinie.add(jFS16, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, 10, 250));

        jFS17.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS17.setMaximum(255);
        jFS17.setOrientation(javax.swing.JSlider.VERTICAL);
        jFS17.setValue(79);
        jFS17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jFS17MouseReleased(evt);
            }
        });
        jKennlinie.add(jFS17, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, 10, 250));

        jFS18.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS18.setMaximum(255);
        jFS18.setOrientation(javax.swing.JSlider.VERTICAL);
        jFS18.setValue(89);
        jFS18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jFS18MouseReleased(evt);
            }
        });
        jKennlinie.add(jFS18, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, 10, 250));

        jFS19.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS19.setMaximum(255);
        jFS19.setOrientation(javax.swing.JSlider.VERTICAL);
        jFS19.setValue(99);
        jFS19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jFS19MouseReleased(evt);
            }
        });
        jKennlinie.add(jFS19, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 20, 10, 250));

        jFS20.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS20.setMaximum(255);
        jFS20.setOrientation(javax.swing.JSlider.VERTICAL);
        jFS20.setValue(110);
        jFS20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jFS20MouseReleased(evt);
            }
        });
        jKennlinie.add(jFS20, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 20, 10, 250));

        jFS21.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS21.setMaximum(255);
        jFS21.setOrientation(javax.swing.JSlider.VERTICAL);
        jFS21.setValue(122);
        jFS21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jFS21MouseReleased(evt);
            }
        });
        jKennlinie.add(jFS21, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 20, 10, 250));

        jFS22.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS22.setMaximum(255);
        jFS22.setOrientation(javax.swing.JSlider.VERTICAL);
        jFS22.setValue(134);
        jFS22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jFS22MouseReleased(evt);
            }
        });
        jKennlinie.add(jFS22, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 20, 10, 250));

        jFS23.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS23.setMaximum(255);
        jFS23.setOrientation(javax.swing.JSlider.VERTICAL);
        jFS23.setValue(146);
        jFS23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jFS23MouseReleased(evt);
            }
        });
        jKennlinie.add(jFS23, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 20, 10, 250));

        jFS24.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS24.setMaximum(255);
        jFS24.setOrientation(javax.swing.JSlider.VERTICAL);
        jFS24.setValue(160);
        jFS24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jFS24MouseReleased(evt);
            }
        });
        jKennlinie.add(jFS24, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 20, 10, 250));

        jFS25.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS25.setMaximum(255);
        jFS25.setOrientation(javax.swing.JSlider.VERTICAL);
        jFS25.setValue(176);
        jFS25.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jFS25MouseReleased(evt);
            }
        });
        jKennlinie.add(jFS25, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 20, 10, 250));

        jFS26.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS26.setMaximum(255);
        jFS26.setOrientation(javax.swing.JSlider.VERTICAL);
        jFS26.setValue(194);
        jFS26.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jFS26MouseReleased(evt);
            }
        });
        jKennlinie.add(jFS26, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 20, 10, 250));

        jFS27.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS27.setMaximum(255);
        jFS27.setOrientation(javax.swing.JSlider.VERTICAL);
        jFS27.setValue(214);
        jFS27.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jFS27MouseReleased(evt);
            }
        });
        jKennlinie.add(jFS27, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 20, 10, 250));

        jFS28.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jFS28.setMaximum(255);
        jFS28.setOrientation(javax.swing.JSlider.VERTICAL);
        jFS28.setValue(244);
        jFS28.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jFS28MouseReleased(evt);
            }
        });
        jKennlinie.add(jFS28, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 20, 10, 250));

        jLabel35.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel35.setText(bundle.getString("LDG30.jLabel35.text")); // NOI18N
        jKennlinie.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, -1, -1));

        buttonGroup4.add(jKurve1);
        jKurve1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jKurve1.setText(bundle.getString("LDG30.jKurve1.text")); // NOI18N
        jKurve1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jKurve1ActionPerformed(evt);
            }
        });
        jKennlinie.add(jKurve1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 30, -1, -1));

        buttonGroup4.add(jKurve2);
        jKurve2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jKurve2.setText(bundle.getString("LDG30.jKurve2.text")); // NOI18N
        jKurve2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jKurve2ActionPerformed(evt);
            }
        });
        jKennlinie.add(jKurve2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 50, -1, -1));

        buttonGroup4.add(jKurve3);
        jKurve3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jKurve3.setText(bundle.getString("LDG30.jKurve3.text")); // NOI18N
        jKurve3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jKurve3ActionPerformed(evt);
            }
        });
        jKennlinie.add(jKurve3, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 70, -1, -1));

        buttonGroup4.add(jKurve4);
        jKurve4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jKurve4.setText(bundle.getString("LDG30.jKurve4.text")); // NOI18N
        jKurve4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jKurve4ActionPerformed(evt);
            }
        });
        jKennlinie.add(jKurve4, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 90, -1, -1));

        jCustom1sichern.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCustom1sichern.setText(bundle.getString("LDG30.jCustom1sichern.text")); // NOI18N
        jCustom1sichern.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCustom1sichernActionPerformed(evt);
            }
        });
        jKennlinie.add(jCustom1sichern, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 130, 130, -1));

        jCustom1laden.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCustom1laden.setText(bundle.getString("LDG30.jCustom1laden.text")); // NOI18N
        jCustom1laden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCustom1ladenActionPerformed(evt);
            }
        });
        jKennlinie.add(jCustom1laden, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 170, 130, -1));

        jFS_Anzeige.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jFS_Anzeige.setText(bundle.getString("LDG30.jFS_Anzeige.text")); // NOI18N
        jKennlinie.add(jFS_Anzeige, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 240, -1, 20));

        jFS_AnzeigeWert.setEditable(false);
        jFS_AnzeigeWert.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS_AnzeigeWert.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jFS_AnzeigeWert.setText(bundle.getString("LDG30.jFS_AnzeigeWert.text")); // NOI18N
        jKennlinie.add(jFS_AnzeigeWert, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 240, 30, -1));

        jDecodereigenschaften.addTab(bundle.getString("LDG30.jKennlinie.TabConstraints.tabTitle"), jKennlinie); // NOI18N

        jAnalog.setToolTipText(bundle.getString("LDG30.jAnalog.toolTipText")); // NOI18N
        jAnalog.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAnalog.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jAnalogComponentShown(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText(bundle.getString("LDG30.jLabel2.text")); // NOI18N

        jF1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF1.setText(bundle.getString("LDG30.jF1.text")); // NOI18N
        jF1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF1ActionPerformed(evt);
            }
        });

        jF2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF2.setText(bundle.getString("LDG30.jF2.text")); // NOI18N
        jF2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF2ActionPerformed(evt);
            }
        });

        jF3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF3.setText(bundle.getString("LDG30.jF3.text")); // NOI18N
        jF3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF3ActionPerformed(evt);
            }
        });

        jF4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF4.setText(bundle.getString("LDG30.jF4.text")); // NOI18N
        jF4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF4ActionPerformed(evt);
            }
        });

        jF5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF5.setText(bundle.getString("LDG30.jF5.text")); // NOI18N
        jF5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF5ActionPerformed(evt);
            }
        });

        jF6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF6.setText(bundle.getString("LDG30.jF6.text")); // NOI18N
        jF6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF6ActionPerformed(evt);
            }
        });

        jF7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF7.setText(bundle.getString("LDG30.jF7.text")); // NOI18N
        jF7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF7ActionPerformed(evt);
            }
        });

        jF8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF8.setText(bundle.getString("LDG30.jF8.text")); // NOI18N
        jF8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF8ActionPerformed(evt);
            }
        });

        jLabel73.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel73.setText(bundle.getString("LDG30.jLabel73.text")); // NOI18N

        buttonGroup7.add(jAnalogRW_W);
        jAnalogRW_W.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAnalogRW_W.setSelected(true);
        jAnalogRW_W.setText(bundle.getString("LDG30.jAnalogRW_W.text")); // NOI18N
        jAnalogRW_W.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAnalogRW_WActionPerformed(evt);
            }
        });

        buttonGroup7.add(jAnalogRW_G);
        jAnalogRW_G.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAnalogRW_G.setText(bundle.getString("LDG30.jAnalogRW_G.text")); // NOI18N
        jAnalogRW_G.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAnalogRW_GActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jAnalogLayout = new javax.swing.GroupLayout(jAnalog);
        jAnalog.setLayout(jAnalogLayout);
        jAnalogLayout.setHorizontalGroup(
            jAnalogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jAnalogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jAnalogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jAnalogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jAnalogLayout.createSequentialGroup()
                            .addGroup(jAnalogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jF4)
                                .addComponent(jF3)
                                .addComponent(jF2))
                            .addGap(41, 41, 41)
                            .addGroup(jAnalogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jF8)
                                .addComponent(jF6)
                                .addComponent(jF7)))
                        .addGroup(jAnalogLayout.createSequentialGroup()
                            .addComponent(jF1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jF5)))
                    .addComponent(jLabel2)
                    .addGroup(jAnalogLayout.createSequentialGroup()
                        .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jAnalogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jAnalogRW_G, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jAnalogRW_W, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jAnalogLayout.setVerticalGroup(
            jAnalogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jAnalogLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jAnalogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jAnalogLayout.createSequentialGroup()
                        .addGroup(jAnalogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jF5)
                            .addComponent(jF1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jAnalogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jF2)
                            .addComponent(jF6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jAnalogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jF3)
                            .addComponent(jF7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jF4))
                    .addComponent(jF8))
                .addGap(49, 49, 49)
                .addGroup(jAnalogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel73)
                    .addComponent(jAnalogRW_W))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jAnalogRW_G)
                .addContainerGap(185, Short.MAX_VALUE))
        );

        jAnalogLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jAnalogRW_G, jAnalogRW_W});

        jDecodereigenschaften.addTab(bundle.getString("LDG30.jAnalog.TabConstraints.tabTitle"), jAnalog); // NOI18N

        jKommentar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jKommentar.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jKommentarComponentShown(evt);
            }
        });

        jComment.setColumns(20);
        jComment.setRows(5);
        jScrollPane1.setViewportView(jComment);

        jLabel36.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel36.setText(bundle.getString("LDG30.jLabel36.text")); // NOI18N

        javax.swing.GroupLayout jKommentarLayout = new javax.swing.GroupLayout(jKommentar);
        jKommentar.setLayout(jKommentarLayout);
        jKommentarLayout.setHorizontalGroup(
            jKommentarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jKommentarLayout.createSequentialGroup()
                .addGroup(jKommentarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jKommentarLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE))
                    .addGroup(jKommentarLayout.createSequentialGroup()
                        .addGap(155, 155, 155)
                        .addComponent(jLabel36)))
                .addContainerGap())
        );
        jKommentarLayout.setVerticalGroup(
            jKommentarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jKommentarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel36)
                .addGap(11, 11, 11)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                .addContainerGap())
        );

        jDecodereigenschaften.addTab(bundle.getString("LDG30.jKommentar.TabConstraints.tabTitle"), jKommentar); // NOI18N

        jSave.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jSave.setText(bundle.getString("LDG30.jSave.text")); // NOI18N
        jSave.setToolTipText(bundle.getString("LDG30.jSave.toolTipText")); // NOI18N
        jSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSaveActionPerformed(evt);
            }
        });

        jOpen.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jOpen.setText(bundle.getString("LDG30.jOpen.text")); // NOI18N
        jOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jOpenActionPerformed(evt);
            }
        });

        jCV_LesenSchreiben.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCV_LesenSchreiben.setText(bundle.getString("LDG30.jCV_LesenSchreiben.text")); // NOI18N
        jCV_LesenSchreiben.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCV_LesenSchreibenActionPerformed(evt);
            }
        });

        jAbbrechen.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAbbrechen.setText(bundle.getString("LDG30.jAbbrechen.text")); // NOI18N
        jAbbrechen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAbbrechenActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jDecoderAdresse, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jKurzeAdr)
                                    .addComponent(jlangeAdr))
                                .addGap(24, 24, 24))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jCV_Anzeige, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jCV_Inhalt, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jDirekteingabe)
                                .addGap(30, 30, 30)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel27)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jDecoderAdresse1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel28))))
                    .addComponent(jDecodereigenschaften))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSave, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jOpen, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jCV_LesenSchreiben, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jAbbrechen, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(jOpen, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSave, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jCV_LesenSchreiben, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(133, 133, 133)
                        .addComponent(jAbbrechen, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCV_Anzeige, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCV_Inhalt, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDirekteingabe, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(jKurzeAdr))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jDecoderAdresse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlangeAdr)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(jLabel27)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel28)
                                    .addComponent(jDecoderAdresse1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDecodereigenschaften)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCV_AnzeigeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCV_AnzeigeActionPerformed
        int currCV = getCVfromIndexString(jCV_Anzeige, "CV#");
        jCV_Inhalt.setText("" + CV[1][currCV]);
}//GEN-LAST:event_jCV_AnzeigeActionPerformed

    private void jKurzeAdrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jKurzeAdrActionPerformed
        int j = CV[1][1];
        if( j < 1 || j > 255 )
        {
            CVNavi.mbValueNaN( this, 1, 255, true);
            j = 1;
        }
        else if (j > 127)
        {
            CVNavi.mbAdr128MMonly( this );
        }
        CV[1][1] = j;
        jLongAddr.setSelected(false);
        CV[1][29] &= ~32;
        jCV_Anzeige.setSelectedItem( "CV#"+1 );
        jDecoderAdresse.setText("" + j);
    }//GEN-LAST:event_jKurzeAdrActionPerformed

    private void jlangeAdrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jlangeAdrActionPerformed
        int j = (CV[1][17] - 192)*256 + CV[1][18];
        if (j < 128 || j > 10239)
        {
            CVNavi.mbValueNaN( this, 128, 10239, true);
            j = 128;
        }
        CV[1][29] |= 32;
        CV[1][17] = j/256 + 192;
        CV[1][18] = j%256;
        jLongAddr.setSelected(true);
        CV[1][29] |= 32;
        jCV_Anzeige.setSelectedItem( "CV#"+17 );
        jDecoderAdresse.setText("" + j);
    }//GEN-LAST:event_jlangeAdrActionPerformed

    private void jDirekteingabeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDirekteingabeActionPerformed
        // hier wird die Direkteingabe (de)aktiviert
        String str = jDirekteingabe.getText();
        if(str.equals(bundle.getString("FD_LED.jDirekteingabe.text"))) {
            jCV_Inhalt.setEditable(true);
            str = bundle.getString("FD_LED.jDirekteingabe_aus.text");
            jDirekteingabe.setText(str);
        } else {
            jCV_Inhalt.setEditable(false);
            str = bundle.getString("FD_LED.jDirekteingabe.text");
            jDirekteingabe.setText(str);
        }
        jCV_Inhalt.validate();
        jDirekteingabe.validate();
}//GEN-LAST:event_jDirekteingabeActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        DecTest dt = new DecTest(this, true, CVNavi);

        if (CVNavi.bSpracheDE) {
            switch(CVNavi.Decoder)
            {
                case c.LD_G30:
                    dt.jDecType.setText("Decoder: LD-G-30, Adresse: " + jDecoderAdresse.getText());
                    break;

                case c.LD_W32:
                    dt.jDecType.setText("Decoder: LD-W-32, Adresse: " + jDecoderAdresse.getText());
                    break;

                case c.LD_G32:
                    dt.jDecType.setText("Decoder: LD-G-32, Adresse: " + jDecoderAdresse.getText());
                    break;

                case c.LD_W32_2:
                    dt.jDecType.setText("Decoder: LD-W-32.2, Adresse: " + jDecoderAdresse.getText());
                    break;

                case c.LD_G32_2:
                    dt.jDecType.setText("Decoder: LD-G-32.2, Adresse: " + jDecoderAdresse.getText());
                    break;
            }
        } else {
            switch(CVNavi.Decoder)
            {
                case c.LD_G30:
                    dt.jDecType.setText("decoder: LD-G-30, address: " + jDecoderAdresse.getText());
                    break;

                case c.LD_W32:
                    dt.jDecType.setText("decoder: LD-W-32, address: " + jDecoderAdresse.getText());
                    break;

                case c.LD_G32:
                    dt.jDecType.setText("decoder: LD-G-32, address: " + jDecoderAdresse.getText());
                    break;

                case c.LD_W32_2:
                    dt.jDecType.setText("decoder: LD-W-32.2, address: " + jDecoderAdresse.getText());
                    break;

                case c.LD_G32_2:
                    dt.jDecType.setText("decoder: LD-G-32.2, address: " + jDecoderAdresse.getText());
                    break;
            }
        }
        if(jKurzeAdr.isSelected()) {
            dt.DecAddr = CV[1][1];
        } else {
            int n = (CV[1][17] - 192)*256 + CV[1][18];
            dt.DecAddr = n;
        }
        dt.setLocationRelativeTo(this);
        dt.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jRichtungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRichtungActionPerformed
        if(jRichtung.isSelected()) {
            CV[1][29] |= 1;
        } else {
            CV[1][29] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+29 );
    }//GEN-LAST:event_jRichtungActionPerformed

    private void jFSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFSActionPerformed
        if(jFS.isSelected()) {
            CV[1][29] |= 2;
        } else {
            CV[1][29] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+29 );
    }//GEN-LAST:event_jFSActionPerformed

    private void jAnalog1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAnalog1ActionPerformed
        if(jAnalog1.isSelected()) {
            CV[1][29] |= 4;
        } else {
            CV[1][29] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+29 );
    }//GEN-LAST:event_jAnalog1ActionPerformed

    private void jRailComActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRailComActionPerformed
        if(jRailCom.isSelected()) {
            CV[1][29] |= 8;
        } else {
            CV[1][29] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+29 );
    }//GEN-LAST:event_jRailComActionPerformed

    private void jLongAddrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jLongAddrActionPerformed
        if(jLongAddr.isSelected()) {
            CV[1][29] |= 32;
            jKurzeAdr.setSelected(false);
            jlangeAdr.setSelected(true);
            int n = (CV[1][17] - 192)*256 + CV[1][18];
            jDecoderAdresse.setText( "" + n);
        } else {
            CV[1][29] &= ~32;
            jKurzeAdr.setSelected(true);
            jlangeAdr.setSelected(false);
            jDecoderAdresse.setText( "" + CV[1][1]);
        }
        jCV_Anzeige.setSelectedItem( "CV#"+29 );
    }//GEN-LAST:event_jLongAddrActionPerformed

    private void jCV29ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jCV29ComponentShown
        jCV_Anzeige.setSelectedItem( "CV#"+29 );
        if((CV[1][29] & 1) == 1)
            jRichtung.setSelected(true);
        else
            jRichtung.setSelected(false);

        if((CV[1][29] & 2) == 2)
            jFS.setSelected(true);
        else
            jFS.setSelected(false);

        if((CV[1][29] & 4) == 4)
            jAnalog1.setSelected(true);
        else
            jAnalog1.setSelected(false);

        if((CV[1][29] & 8) == 8)
            jRailCom.setSelected(true);
        else
            jRailCom.setSelected(false);

        if((CV[1][29] & 32) == 32)
        {
            jLongAddr.setSelected(true);
            jKurzeAdr.setSelected(false);
            jlangeAdr.setSelected(true);
            int n = (CV[1][17] - 192)*256 + CV[1][18];
            jDecoderAdresse.setText( "" + n);
        }
        else
        {
            jLongAddr.setSelected(false);
            jKurzeAdr.setSelected(true);
            jlangeAdr.setSelected(false);
            jDecoderAdresse.setText("" + CV[1][1]);
        }
        jBlinkFrequenz.setText("" + CV[1][112]);
        jMM_Addr_2.setText("" + CV[1][114]);
        jVersion.setText("Decoder-Version: " + CV[1][7]);
        jManID.setText("NMRA Man-ID: " + CV[1][8]);
    }//GEN-LAST:event_jCV29ComponentShown

    private void jFL_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFL_1ActionPerformed
        if(jFL_1.isSelected()) {
            CV[1][33] |= 1;
        } else {
            CV[1][33] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+33 );
}//GEN-LAST:event_jFL_1ActionPerformed

    private void jFR_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFR_1ActionPerformed
        if(jFR_1.isSelected()) {
            CV[1][34] |= 1;
        } else {
            CV[1][34] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+34 );
    }//GEN-LAST:event_jFR_1ActionPerformed

    private void jF1_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF1_1ActionPerformed
        if(jF1_1.isSelected()) {
            CV[1][35] |= 1;
        } else {
            CV[1][35] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+35 );
    }//GEN-LAST:event_jF1_1ActionPerformed

    private void jF2_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF2_1ActionPerformed
        if(jF2_1.isSelected()) {
            CV[1][36] |= 1;
        } else {
            CV[1][36] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+36 );
    }//GEN-LAST:event_jF2_1ActionPerformed

    private void jF3_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF3_1ActionPerformed
        if(jF3_1.isSelected()) {
            CV[1][37] |= 1;
        } else {
            CV[1][37] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+37 );
    }//GEN-LAST:event_jF3_1ActionPerformed

    private void jF4_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF4_1ActionPerformed
        if(jF4_1.isSelected()) {
            CV[1][38] |= 1;
        } else {
            CV[1][38] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+38 );
    }//GEN-LAST:event_jF4_1ActionPerformed

    private void jF5_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF5_1ActionPerformed
        if(jF5_1.isSelected()) {
            CV[1][39] |= 1;
        } else {
            CV[1][39] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+39 );
    }//GEN-LAST:event_jF5_1ActionPerformed

    private void jF9_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF9_1ActionPerformed
        if(jF9_1.isSelected()) {
            CV[1][43] |= 1;
        } else {
            CV[1][43] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+43 );
    }//GEN-LAST:event_jF9_1ActionPerformed

    private void jF8_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF8_1ActionPerformed
        if(jF8_1.isSelected()) {
            CV[1][42] |= 1;
        } else {
            CV[1][42] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+42 );
    }//GEN-LAST:event_jF8_1ActionPerformed

    private void jF7_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF7_1ActionPerformed
        if(jF7_1.isSelected()) {
            CV[1][41] |= 1;
        } else {
            CV[1][41] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+41 );
    }//GEN-LAST:event_jF7_1ActionPerformed

    private void jF10_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF10_1ActionPerformed
        if(jF10_1.isSelected()) {
            CV[1][44] |= 1;
        } else {
            CV[1][44] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+44 );
    }//GEN-LAST:event_jF10_1ActionPerformed

    private void jF11_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF11_1ActionPerformed
        if(jF11_1.isSelected()) {
            CV[1][45] |= 1;
        } else {
            CV[1][45] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+45 );
    }//GEN-LAST:event_jF11_1ActionPerformed

    private void jF6_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF6_1ActionPerformed
        if(jF6_1.isSelected()) {
            CV[1][40] |= 1;
        } else {
            CV[1][40] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+40 );
    }//GEN-LAST:event_jF6_1ActionPerformed

    private void jF12_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF12_1ActionPerformed
        if(jF12_1.isSelected()) {
            CV[1][46] |= 1;
        } else {
            CV[1][46] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+46 );
    }//GEN-LAST:event_jF12_1ActionPerformed

    private void jF12_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF12_2ActionPerformed
        if(jF12_2.isSelected()) {
            CV[1][46] |= 2;
        } else {
            CV[1][46] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+46 );
    }//GEN-LAST:event_jF12_2ActionPerformed

    private void jFL_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFL_2ActionPerformed
        if(jFL_2.isSelected()) {
            CV[1][33] |= 2;
        } else {
            CV[1][33] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+33 );
    }//GEN-LAST:event_jFL_2ActionPerformed

    private void jFR_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFR_2ActionPerformed
        if(jFR_2.isSelected()) {
            CV[1][34] |= 2;
        } else {
            CV[1][34] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+34 );
    }//GEN-LAST:event_jFR_2ActionPerformed

    private void jF1_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF1_2ActionPerformed
        if(jF1_2.isSelected()) {
            CV[1][35] |= 2;
        } else {
            CV[1][35] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+35 );
    }//GEN-LAST:event_jF1_2ActionPerformed

    private void jF2_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF2_2ActionPerformed
        if(jF2_2.isSelected()) {
            CV[1][36] |= 2;
        } else {
            CV[1][36] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+36 );
    }//GEN-LAST:event_jF2_2ActionPerformed

    private void jF3_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF3_2ActionPerformed
        if(jF3_2.isSelected()) {
            CV[1][37] |= 2;
        } else {
            CV[1][37] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+37 );
    }//GEN-LAST:event_jF3_2ActionPerformed

    private void jF4_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF4_2ActionPerformed
        if(jF4_2.isSelected()) {
            CV[1][38] |= 2;
        } else {
            CV[1][38] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+38 );
    }//GEN-LAST:event_jF4_2ActionPerformed

    private void jF5_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF5_2ActionPerformed
        if(jF5_2.isSelected()) {
            CV[1][39] |= 2;
        } else {
            CV[1][39] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+39 );
    }//GEN-LAST:event_jF5_2ActionPerformed

    private void jF6_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF6_2ActionPerformed
        if(jF6_2.isSelected()) {
            CV[1][40] |= 2;
        } else {
            CV[1][40] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+40 );
    }//GEN-LAST:event_jF6_2ActionPerformed

    private void jF7_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF7_2ActionPerformed
        if(jF7_2.isSelected()) {
            CV[1][41] |= 2;
        } else {
            CV[1][41] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+41 );
    }//GEN-LAST:event_jF7_2ActionPerformed

    private void jF8_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF8_2ActionPerformed
        if(jF8_2.isSelected()) {
            CV[1][42] |= 2;
        } else {
            CV[1][42] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+42 );
    }//GEN-LAST:event_jF8_2ActionPerformed

    private void jF9_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF9_2ActionPerformed
        if(jF9_2.isSelected()) {
            CV[1][43] |= 2;
        } else {
            CV[1][43] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+43 );
    }//GEN-LAST:event_jF9_2ActionPerformed

    private void jF10_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF10_2ActionPerformed
        if(jF10_2.isSelected()) {
            CV[1][44] |= 2;
        } else {
            CV[1][44] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+44 );
    }//GEN-LAST:event_jF10_2ActionPerformed

    private void jF11_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF11_2ActionPerformed
        if(jF11_2.isSelected()) {
            CV[1][45] |= 2;
        } else {
            CV[1][45] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+45 );
    }//GEN-LAST:event_jF11_2ActionPerformed

    private void jFunctionMappingComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jFunctionMappingComponentShown
        jCV_Anzeige.setSelectedItem( "CV#"+33 );
        if((CV[1][33] & 1) == 1)
            jFL_1.setSelected(true);
        else
            jFL_1.setSelected(false);

        if((CV[1][33] & 2) == 2)
            jFL_2.setSelected(true);
        else
            jFL_2.setSelected(false);

        if((CV[1][33] & 4) == 4)
            jFL_3.setSelected(true);
        else
            jFL_3.setSelected(false);

        if((CV[1][34] & 1) == 1)
            jFR_1.setSelected(true);
        else
            jFR_1.setSelected(false);

        if((CV[1][34] & 2) == 2)
            jFR_2.setSelected(true);
        else
            jFR_2.setSelected(false);

        if((CV[1][34] & 4) == 4)
            jFR_3.setSelected(true);
        else
            jFR_3.setSelected(false);

        if((CV[1][35] & 1) == 1)
            jF1_1.setSelected(true);
        else
            jF1_1.setSelected(false);

        if((CV[1][35] & 2) == 2)
            jF1_2.setSelected(true);
        else
            jF1_2.setSelected(false);

        if((CV[1][35] & 4) == 4)
            jF1_3.setSelected(true);
        else
            jF1_3.setSelected(false);

        if((CV[1][36] & 1) == 1)
            jF2_1.setSelected(true);
        else
            jF2_1.setSelected(false);

        if((CV[1][36] & 2) == 2)
            jF2_2.setSelected(true);
        else
            jF2_2.setSelected(false);

        if((CV[1][36] & 4) == 4)
            jF2_3.setSelected(true);
        else
            jF2_3.setSelected(false);

        if((CV[1][37] & 1) == 1)
            jF3_1.setSelected(true);
        else
            jF3_1.setSelected(false);

        if((CV[1][37] & 2) == 2)
            jF3_2.setSelected(true);
        else
            jF3_2.setSelected(false);

        if((CV[1][37] & 4) == 4)
            jF3_3.setSelected(true);
        else
            jF3_3.setSelected(false);

        if((CV[1][38] & 1) == 1)
            jF4_1.setSelected(true);
        else
            jF4_1.setSelected(false);

        if((CV[1][38] & 2) == 2)
            jF4_2.setSelected(true);
        else
            jF4_2.setSelected(false);

        if((CV[1][38] & 4) == 4)
            jF4_3.setSelected(true);
        else
            jF4_3.setSelected(false);

        if((CV[1][39] & 1) == 1)
            jF5_1.setSelected(true);
        else
            jF5_1.setSelected(false);

        if((CV[1][39] & 2) == 2)
            jF5_2.setSelected(true);
        else
            jF5_2.setSelected(false);

        if((CV[1][39] & 4) == 4)
            jF5_3.setSelected(true);
        else
            jF5_3.setSelected(false);

        if((CV[1][40] & 1) == 1)
            jF6_1.setSelected(true);
        else
            jF6_1.setSelected(false);

        if((CV[1][40] & 2) == 2)
            jF6_2.setSelected(true);
        else
            jF6_2.setSelected(false);

        if((CV[1][40] & 4) == 4)
            jF6_3.setSelected(true);
        else
            jF6_3.setSelected(false);

        if((CV[1][41] & 1) == 1)
            jF7_1.setSelected(true);
        else
            jF7_1.setSelected(false);

        if((CV[1][41] & 2) == 2)
            jF7_2.setSelected(true);
        else
            jF7_2.setSelected(false);

        if((CV[1][41] & 4) == 4)
            jF7_3.setSelected(true);
        else
            jF7_3.setSelected(false);

        if((CV[1][42] & 1) == 1)
            jF8_1.setSelected(true);
        else
            jF8_1.setSelected(false);

        if((CV[1][42] & 2) == 2)
            jF8_2.setSelected(true);
        else
            jF8_2.setSelected(false);

        if((CV[1][42] & 4) == 4)
            jF8_3.setSelected(true);
        else
            jF8_3.setSelected(false);

        if((CV[1][43] & 1) == 1)
            jF9_1.setSelected(true);
        else
            jF9_1.setSelected(false);

        if((CV[1][43] & 2) == 2)
            jF9_2.setSelected(true);
        else
            jF9_2.setSelected(false);

        if((CV[1][43] & 4) == 4)
            jF9_3.setSelected(true);
        else
            jF9_3.setSelected(false);

        if((CV[1][44] & 1) == 1)
            jF10_1.setSelected(true);
        else
            jF10_1.setSelected(false);

        if((CV[1][44] & 2) == 2)
            jF10_2.setSelected(true);
        else
            jF10_2.setSelected(false);

        if((CV[1][44] & 4) == 4)
            jF10_3.setSelected(true);
        else
            jF10_3.setSelected(false);

        if((CV[1][45] & 1) == 1)
            jF11_1.setSelected(true);
        else
            jF11_1.setSelected(false);

        if((CV[1][45] & 2) == 2)
            jF11_2.setSelected(true);
        else
            jF11_2.setSelected(false);

        if((CV[1][45] & 4) == 4)
            jF11_3.setSelected(true);
        else
            jF11_3.setSelected(false);

        if((CV[1][46] & 1) == 1)
            jF12_1.setSelected(true);
        else
            jF12_1.setSelected(false);

        if((CV[1][46] & 2) == 2)
            jF12_2.setSelected(true);
        else
            jF12_2.setSelected(false);

        if((CV[1][46] & 4) == 4)
            jF12_3.setSelected(true);
        else
            jF12_3.setSelected(false);

        jAnalogRW_G.setSelected(    (CV[1][12] & 1) == 1);
        jAnalogRW_W.setSelected( ! ((CV[1][12] & 1) == 1));

    }//GEN-LAST:event_jFunctionMappingComponentShown

    private void jDimmen1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDimmen1KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus();
        }
    }//GEN-LAST:event_jDimmen1KeyReleased

    private void jRL_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRL_2ActionPerformed
        if(jRL_2.isSelected()) {
            CV[1][115] |= 2;
        } else {
            CV[1][115] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+115 );
}//GEN-LAST:event_jRL_2ActionPerformed

    private void jRL_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRL_1ActionPerformed
        if(jRL_1.isSelected()) {
            CV[1][115] |= 1;
        } else {
            CV[1][115] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+115 );
}//GEN-LAST:event_jRL_1ActionPerformed

    private void jDimmen2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDimmen2KeyReleased
        if( evt.getKeyCode() == KeyEvent.VK_ENTER ) {
            transferFocus();
        }
    }//GEN-LAST:event_jDimmen2KeyReleased

    private void jRL_F4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRL_F4ActionPerformed
        if(jRL_F4.isSelected()) {
            CV[1][115] |= 128;
        } else {
            CV[1][115] &= ~128;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+115 );
}//GEN-LAST:event_jRL_F4ActionPerformed

    private void jRL_F3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRL_F3ActionPerformed
        if(jRL_F3.isSelected()) {
            CV[1][115] |= 64;
        } else {
            CV[1][115] &= ~64;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+115 );
}//GEN-LAST:event_jRL_F3ActionPerformed

    private void jAnalogComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jAnalogComponentShown
        if((CV[1][13] & 1) == 1)
            jF1.setSelected(true);
        else
            jF1.setSelected(false);

        if((CV[1][13] & 2) == 2)
            jF2.setSelected(true);
        else
            jF2.setSelected(false);

        if((CV[1][13] & 4) == 4)
            jF3.setSelected(true);
        else
            jF3.setSelected(false);

        if((CV[1][13] & 8) == 8)
            jF4.setSelected(true);
        else
            jF4.setSelected(false);

        if((CV[1][13] & 16) == 16)
            jF5.setSelected(true);
        else
            jF5.setSelected(false);

        if((CV[1][13] & 32) == 32)
            jF6.setSelected(true);
        else
            jF6.setSelected(false);

        if((CV[1][13] & 64) == 64)
            jF7.setSelected(true);
        else
            jF7.setSelected(false);

        if((CV[1][13] & 128) == 128)
            jF8.setSelected(true);
        else
            jF8.setSelected(false);
        jCV_Anzeige.setSelectedItem( "CV#"+13 );
    }//GEN-LAST:event_jAnalogComponentShown

    private void jKommentarComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jKommentarComponentShown

}//GEN-LAST:event_jKommentarComponentShown

    private void jSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSaveActionPerformed
        // Alle CVs werden in einer Datei gespeichert
        SaveOpenDialog od;
        String extension;
        switch(CVNavi.Decoder)
        {
            case c.LD_G30:     //LD-G-30
                CVs = "LD-G-30\r\n";
                extension = "ld30";
                break;
                
            case c.LD_W32:     //LD-W-32
                CVs = "LD-W-32\r\n";
                extension = "ld32";
                break;
                
            case c.LD_G32:     //LD-G-32
                CVs = "LD-G-32\r\n";
                extension = "ld32";
                break;
            case c.LD_W32_2:     //LD-W-32
                CVs = "LD-W-32.2\r\n";
                extension = "ld32_2";
                break;
                
            case c.LD_G32_2:     //LD-G-32
                CVs = "LD-G-32.2\r\n";
                extension = "ld32_2";
                break;

            default:
                return;
        }
        CVs += "Version 1.1\r\n";
        for(int i = 0; i < CV[0].length; i++) {
            if( CV[0][i] > 0 ) { // only write used CVs (CV[0][cv] != 0 ) to file
                CVs += "\r\nCV#" + CV[0][i] + " = " + CV[1][i];
            }
        }
        CVs += "\r\n\r\nKommentar:\r\n";
        CVs += jComment.getText();
        od = new SaveOpenDialog( this, true, false, CVs, this, extension);
}//GEN-LAST:event_jSaveActionPerformed

    private void jOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jOpenActionPerformed
        // gespeicherte CVs werden gelesen
        SaveOpenDialog od;
        jCV_Inhalt.setText("62");
        jCV_Anzeige.setSelectedItem( "CV#"+8 );
        switch(CVNavi.Decoder)
        {
            case c.LD_G30:
                od = new SaveOpenDialog( this, true, true, CVs, this, "ld30");
                break;

            case c.LD_W32:
            case c.LD_G32:
                od = new SaveOpenDialog( this, true, true, CVs, this, "ld32");
                break;

            case c.LD_W32_2:
            case c.LD_G32_2:
                od = new SaveOpenDialog( this, true, true, CVs, this, "ld32_2");
                break;
        }
        updateTabs();
}//GEN-LAST:event_jOpenActionPerformed

    private void jCV_LesenSchreibenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCV_LesenSchreibenActionPerformed
        try {
            // Hier wird die Schnittstelle geöffnet und die CVs werden zur Zentrale gesendet
            ReadWriteCV cvwr = new ReadWriteCV(this, true, CVNavi, CV);
        } catch (IOException ex) {
            CVNavi.mbDeviceReadProblem( this );
        }
    }//GEN-LAST:event_jCV_LesenSchreibenActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        CVNavi.frameInstanceDEVICE = null;
        CVNavi.setFocus();
    }//GEN-LAST:event_formWindowClosed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // Sprache in Buttons einstellen
        if(!CVNavi.bSpracheDE) {
            // TODO da FEHLT noch die ENGLISCHE Übersetzung der angezeigten Texte
        }

    }//GEN-LAST:event_formWindowOpened

    private void jAltDimmenFS1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jAltDimmenFS1KeyReleased
        if( evt.getKeyCode() == KeyEvent.VK_ENTER ) {
            transferFocus();
        }
}//GEN-LAST:event_jAltDimmenFS1KeyReleased

    private void jAltDimmen1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jAltDimmen1KeyReleased
        if( evt.getKeyCode() == KeyEvent.VK_ENTER ) {
            transferFocus();
        }
}//GEN-LAST:event_jAltDimmen1KeyReleased

    private void jAltDimmen2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jAltDimmen2KeyReleased
        if( evt.getKeyCode() == KeyEvent.VK_ENTER ) {
            transferFocus();
        }
}//GEN-LAST:event_jAltDimmen2KeyReleased

    private void jR_F3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jR_F3ActionPerformed
        if(!jR_F3.isSelected())
            CV[1][49] &= ~8;
        else
            CV[1][49] |= 8;
        jCV_Anzeige.setSelectedItem( "CV#"+49 );
}//GEN-LAST:event_jR_F3ActionPerformed

    private void jR_F4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jR_F4ActionPerformed
        if(!jR_F4.isSelected())
            CV[1][49] &= ~16;
        else
            CV[1][49] |= 16;
        jCV_Anzeige.setSelectedItem( "CV#"+49 );
}//GEN-LAST:event_jR_F4ActionPerformed

    private void jAB_F3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAB_F3ActionPerformed
        if(!jAB_F3.isSelected())
            CV[1][49] &= ~32;
        else
            CV[1][49] |= 32;
        jCV_Anzeige.setSelectedItem( "CV#"+49 );
}//GEN-LAST:event_jAB_F3ActionPerformed

    private void jAB_F4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAB_F4ActionPerformed
        if(!jAB_F4.isSelected())
            CV[1][49] &= ~64;
        else
            CV[1][49] |= 64;
        jCV_Anzeige.setSelectedItem( "CV#"+49 );
}//GEN-LAST:event_jAB_F4ActionPerformed

    private void jKurve1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jKurve1ActionPerformed
        jFS1.setValue(0);
        jFS2.setValue(2);
        jFS3.setValue(4);
        jFS4.setValue(6);
        jFS5.setValue(9);
        jFS6.setValue(12);
        jFS7.setValue(15);
        jFS8.setValue(19);
        jFS9.setValue(23);
        jFS10.setValue(27);
        jFS11.setValue(33);
        jFS12.setValue(39);
        jFS13.setValue(45);
        jFS14.setValue(53);
        jFS15.setValue(61);
        jFS16.setValue(69);
        jFS17.setValue(79);
        jFS18.setValue(89);
        jFS19.setValue(99);
        jFS20.setValue(110);
        jFS21.setValue(122);
        jFS22.setValue(134);
        jFS23.setValue(146);
        jFS24.setValue(160);
        jFS25.setValue(176);
        jFS26.setValue(194);
        jFS27.setValue(214);
        jFS28.setValue(244);
        CV[1][67] = 0;   //Default-Wert
        CV[1][68] = 2;   //Default-Wert
        CV[1][69] = 4;   //Default-Wert
        CV[1][70] = 6;   //Default-Wert
        CV[1][71] = 9;   //Default-Wert
        CV[1][72] = 12;   //Default-Wert
        CV[1][73] = 15;   //Default-Wert
        CV[1][74] = 19;   //Default-Wert
        CV[1][75] = 23;   //Default-Wert
        CV[1][76] = 27;   //Default-Wert
        CV[1][77] = 33;   //Default-Wert
        CV[1][78] = 39;   //Default-Wert
        CV[1][79] = 45;   //Default-Wert
        CV[1][80] = 53;   //Default-Wert
        CV[1][81] = 61;   //Default-Wert
        CV[1][82] = 69;   //Default-Wert
        CV[1][83] = 79;   //Default-Wert
        CV[1][84] = 89;   //Default-Wert
        CV[1][85] = 99;   //Default-Wert
        CV[1][86] = 110;   //Default-Wert
        CV[1][87] = 122;   //Default-Wert
        CV[1][88] = 134;   //Default-Wert
        CV[1][89] = 146;   //Default-Wert
        CV[1][90] = 160;   //Default-Wert
        CV[1][91] = 176;   //Default-Wert
        CV[1][92] = 194;   //Default-Wert
        CV[1][93] = 214;   //Default-Wert
        CV[1][94] = 244;   //Default-Wert
        jCV_Anzeige.setSelectedItem( "CV#"+67 );
    }//GEN-LAST:event_jKurve1ActionPerformed

    private void jKurve2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jKurve2ActionPerformed
        jFS1.setValue(0);
        jFS2.setValue(1);
        jFS3.setValue(2);
        jFS4.setValue(3);
        jFS5.setValue(5);
        jFS6.setValue(7);
        jFS7.setValue(9);
        jFS8.setValue(12);
        jFS9.setValue(15);
        jFS10.setValue(18);
        jFS11.setValue(22);
        jFS12.setValue(26);
        jFS13.setValue(31);
        jFS14.setValue(36);
        jFS15.setValue(42);
        jFS16.setValue(49);
        jFS17.setValue(57);
        jFS18.setValue(66);
        jFS19.setValue(76);
        jFS20.setValue(87);
        jFS21.setValue(99);
        jFS22.setValue(113);
        jFS23.setValue(129);
        jFS24.setValue(147);
        jFS25.setValue(166);
        jFS26.setValue(189);
        jFS27.setValue(214);
        jFS28.setValue(244);
        CV[1][67] = 0;   //Default-Wert
        CV[1][68] = 1;   //Default-Wert
        CV[1][69] = 2;   //Default-Wert
        CV[1][70] = 3;   //Default-Wert
        CV[1][71] = 5;   //Default-Wert
        CV[1][72] = 7;   //Default-Wert
        CV[1][73] = 9;   //Default-Wert
        CV[1][74] = 12;   //Default-Wert
        CV[1][75] = 15;   //Default-Wert
        CV[1][76] = 18;   //Default-Wert
        CV[1][77] = 22;   //Default-Wert
        CV[1][78] = 26;   //Default-Wert
        CV[1][79] = 31;   //Default-Wert
        CV[1][80] = 36;   //Default-Wert
        CV[1][81] = 42;   //Default-Wert
        CV[1][82] = 49;   //Default-Wert
        CV[1][83] = 57;   //Default-Wert
        CV[1][84] = 66;   //Default-Wert
        CV[1][85] = 76;   //Default-Wert
        CV[1][86] = 87;   //Default-Wert
        CV[1][87] = 99;   //Default-Wert
        CV[1][88] = 113;   //Default-Wert
        CV[1][89] = 129;   //Default-Wert
        CV[1][90] = 147;   //Default-Wert
        CV[1][91] = 166;   //Default-Wert
        CV[1][92] = 189;   //Default-Wert
        CV[1][93] = 214;   //Default-Wert
        CV[1][94] = 244;   //Default-Wert
        jCV_Anzeige.setSelectedItem( "CV#"+67 );
    }//GEN-LAST:event_jKurve2ActionPerformed

    private void jKurve3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jKurve3ActionPerformed
        jFS1.setValue(0);
        jFS2.setValue(6);
        jFS3.setValue(12);
        jFS4.setValue(18);
        jFS5.setValue(24);
        jFS6.setValue(30);
        jFS7.setValue(36);
        jFS8.setValue(42);
        jFS9.setValue(48);
        jFS10.setValue(54);
        jFS11.setValue(60);
        jFS12.setValue(66);
        jFS13.setValue(72);
        jFS14.setValue(78);
        jFS15.setValue(90);
        jFS16.setValue(102);
        jFS17.setValue(114);
        jFS18.setValue(126);
        jFS19.setValue(138);
        jFS20.setValue(150);
        jFS21.setValue(162);
        jFS22.setValue(174);
        jFS23.setValue(186);
        jFS24.setValue(198);
        jFS25.setValue(210);
        jFS26.setValue(222);
        jFS27.setValue(234);
        jFS28.setValue(246);
        CV[1][67] = 0;   //Default-Wert
        CV[1][68] = 6;   //Default-Wert
        CV[1][69] = 12;   //Default-Wert
        CV[1][70] = 18;   //Default-Wert
        CV[1][71] = 24;   //Default-Wert
        CV[1][72] = 30;   //Default-Wert
        CV[1][73] = 36;   //Default-Wert
        CV[1][74] = 42;   //Default-Wert
        CV[1][75] = 48;   //Default-Wert
        CV[1][76] = 54;   //Default-Wert
        CV[1][77] = 60;   //Default-Wert
        CV[1][78] = 66;   //Default-Wert
        CV[1][79] = 72;   //Default-Wert
        CV[1][80] = 78;   //Default-Wert
        CV[1][81] = 90;   //Default-Wert
        CV[1][82] = 102;   //Default-Wert
        CV[1][83] = 114;   //Default-Wert
        CV[1][84] = 126;   //Default-Wert
        CV[1][85] = 138;   //Default-Wert
        CV[1][86] = 150;   //Default-Wert
        CV[1][87] = 162;   //Default-Wert
        CV[1][88] = 174;   //Default-Wert
        CV[1][89] = 186;   //Default-Wert
        CV[1][90] = 189;   //Default-Wert
        CV[1][91] = 210;   //Default-Wert
        CV[1][92] = 222;   //Default-Wert
        CV[1][93] = 234;   //Default-Wert
        CV[1][94] = 246;   //Default-Wert
        jCV_Anzeige.setSelectedItem( "CV#"+67 );
    }//GEN-LAST:event_jKurve3ActionPerformed

    private void jKurve4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jKurve4ActionPerformed
        jFS1.setValue(c1[0]);
        jFS2.setValue(c1[1]);
        jFS3.setValue(c1[2]);
        jFS4.setValue(c1[3]);
        jFS5.setValue(c1[4]);
        jFS6.setValue(c1[5]);
        jFS7.setValue(c1[6]);
        jFS8.setValue(c1[7]);
        jFS9.setValue(c1[8]);
        jFS10.setValue(c1[9]);
        jFS11.setValue(c1[10]);
        jFS12.setValue(c1[11]);
        jFS13.setValue(c1[12]);
        jFS14.setValue(c1[13]);
        jFS15.setValue(c1[14]);
        jFS16.setValue(c1[15]);
        jFS17.setValue(c1[16]);
        jFS18.setValue(c1[17]);
        jFS19.setValue(c1[18]);
        jFS20.setValue(c1[19]);
        jFS21.setValue(c1[20]);
        jFS22.setValue(c1[21]);
        jFS23.setValue(c1[22]);
        jFS24.setValue(c1[23]);
        jFS25.setValue(c1[24]);
        jFS26.setValue(c1[25]);
        jFS27.setValue(c1[26]);
        jFS28.setValue(c1[27]);
        System.arraycopy(c1, 0, CV[1], 67, 28);
        jCV_Anzeige.setSelectedItem( "CV#"+67 );
    }//GEN-LAST:event_jKurve4ActionPerformed

    private void jNegBremsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jNegBremsActionPerformed
        if(!jNegBrems.isSelected())
            CV[1][27] &= ~32;
        else
            CV[1][27] |= 32;
        jCV_Anzeige.setSelectedItem( "CV#"+27 );
    }//GEN-LAST:event_jNegBremsActionPerformed

    private void jPosBremsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPosBremsActionPerformed
        if(!jPosBrems.isSelected())
            CV[1][27] &= ~16;
        else
            CV[1][27] |= 16;
        jCV_Anzeige.setSelectedItem( "CV#"+27 );
    }//GEN-LAST:event_jPosBremsActionPerformed

    private void jAltKennlinieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAltKennlinieActionPerformed
        if(!jAltKennlinie.isSelected())
            CV[1][29] &= ~16;
        else
            CV[1][29] |= 16;
        jCV_Anzeige.setSelectedItem( "CV#"+29 );
}//GEN-LAST:event_jAltKennlinieActionPerformed

    private void jR_F1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jR_F1ActionPerformed
        if(!jR_F1.isSelected())
            CV[1][49] &= ~2;
        else
            CV[1][49] |= 2;
        jCV_Anzeige.setSelectedItem( "CV#"+49 );
}//GEN-LAST:event_jR_F1ActionPerformed

    private void jR_F2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jR_F2ActionPerformed
        if(!jR_F2.isSelected())
            CV[1][49] &= ~4;
        else
            CV[1][49] |= 4;
        jCV_Anzeige.setSelectedItem( "CV#"+49 );
}//GEN-LAST:event_jR_F2ActionPerformed

    private void jF1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF1ActionPerformed
        if(jF1.isSelected()) {
            CV[1][13] |= 1;
        } else {
            CV[1][13] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+13 );
        jCV_Inhalt.setText("" + CV[1][13]);
    }//GEN-LAST:event_jF1ActionPerformed

    private void jF2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF2ActionPerformed
        if(jF2.isSelected()) {
            CV[1][13] |= 2;
        } else {
            CV[1][13] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+13 );
        jCV_Inhalt.setText("" + CV[1][13]);
    }//GEN-LAST:event_jF2ActionPerformed

    private void jF3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF3ActionPerformed
        if(jF3.isSelected()) {
            CV[1][13] |= 4;
        } else {
            CV[1][13] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+13 );
        jCV_Inhalt.setText("" + CV[1][13]);
    }//GEN-LAST:event_jF3ActionPerformed

    private void jF4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF4ActionPerformed
        if(jF4.isSelected()) {
            CV[1][13] |= 8;
        } else {
            CV[1][13] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+13 );
        jCV_Inhalt.setText("" + CV[1][13]);
    }//GEN-LAST:event_jF4ActionPerformed

    private void jF5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF5ActionPerformed
        if(jF5.isSelected()) {
            CV[1][13] |= 16;
        } else {
            CV[1][13] &= ~16;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+13 );
        jCV_Inhalt.setText("" + CV[1][13]);
    }//GEN-LAST:event_jF5ActionPerformed

    private void jF6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF6ActionPerformed
        if(jF6.isSelected()) {
            CV[1][13] |= 32;
        } else {
            CV[1][13] &= ~32;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+13 );
        jCV_Inhalt.setText("" + CV[1][13]);
    }//GEN-LAST:event_jF6ActionPerformed

    private void jF7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF7ActionPerformed
        if(jF7.isSelected()) {
            CV[1][13] |= 64;
        } else {
            CV[1][13] &= ~64;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+13 );
        jCV_Inhalt.setText("" + CV[1][13]);
    }//GEN-LAST:event_jF7ActionPerformed

    private void jF8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF8ActionPerformed
        if(jF8.isSelected()) {
            CV[1][13] |= 128;
        } else {
            CV[1][13] &= ~128;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+13 );
        jCV_Inhalt.setText("" + CV[1][13]);
    }//GEN-LAST:event_jF8ActionPerformed

    private void jDimmenComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jDimmenComponentShown
        jDimmen1.setText("" + (CV[1][62] & 0x0F));
        jDimmen2.setText("" + ((CV[1][62] & 0xF0)>>4));
        jDimmen3.setText("" + (CV[1][63] & 0x0F));
        jAltDimmenFS2.setText("" + CV[1][113]);
        jAltDimmenFS1.setText("" + CV[1][113]);
        jAltDimmenFS3.setText("" + CV[1][113]);
        jAltDimmen1.setText("" + (CV[1][118] & 0x0F));
        jAltDimmen2.setText("" + ((CV[1][118] & 0xF0)>>4));
        jAltDimmen3.setText("" + (CV[1][119] & 0x0F));
        if((CV[1][116] & 1) == 1)
            jF8.setSelected(true);
        else
            jF8.setSelected(false);
        if((CV[1][116] & 2) == 2)
            jF8.setSelected(true);
        else
            jF8.setSelected(false);
        jCV_Anzeige.setSelectedItem( "CV#"+62 );
    }//GEN-LAST:event_jDimmenComponentShown

    private void jAltDim1einActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAltDim1einActionPerformed
        CV[1][116] |= 1;
        jCV_Anzeige.setSelectedItem( "CV#"+116 );
        jCV_Inhalt.setText("" + CV[1][116]);
    }//GEN-LAST:event_jAltDim1einActionPerformed

    private void jAltDim1ausActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAltDim1ausActionPerformed
        CV[1][116] &= ~1;
        jCV_Anzeige.setSelectedItem( "CV#"+116 );
        jCV_Inhalt.setText("" + CV[1][116]);

    }//GEN-LAST:event_jAltDim1ausActionPerformed

    private void jAltDim2einActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAltDim2einActionPerformed
        CV[1][116] |= 2;
        jCV_Inhalt.setText("" + CV[1][116]);
        jCV_Inhalt.setText("" + CV[1][116]);
    }//GEN-LAST:event_jAltDim2einActionPerformed

    private void jAltDim2ausActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAltDim2ausActionPerformed
        CV[1][116] &= ~2;
        jCV_Inhalt.setText("" + CV[1][116]);
        jCV_Inhalt.setText("" + CV[1][116]);
    }//GEN-LAST:event_jAltDim2ausActionPerformed

    private void jRangierenComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jRangierenComponentShown
        jCV_Inhalt.setText("" + CV[1][115]);
        if((CV[1][115] & 1) == 1)
            jRL_1.setSelected(true);
        else
            jRL_1.setSelected(false);

        if((CV[1][115] & 2) == 2)
            jRL_2.setSelected(true);
        else
            jRL_2.setSelected(false);

        if((CV[1][115] & 4) == 4)
            jRL_3.setSelected(true);
        else
            jRL_3.setSelected(false);

        if((CV[1][115] & 64) == 64)
            jRL_F3.setSelected(true);
        else
            jRL_F3.setSelected(false);

        if((CV[1][115] & 128) == 128)
            jRL_F4.setSelected(true);
        else
            jRL_F4.setSelected(false);

        if((CV[1][49] & 2) == 2)
            jR_F1.setSelected(true);
        else
            jR_F1.setSelected(false);

        if((CV[1][49] & 4) == 4)
            jR_F2.setSelected(true);
        else
            jR_F2.setSelected(false);

        if((CV[1][49] & 8) == 8)
            jR_F3.setSelected(true);
        else
            jR_F3.setSelected(false);

        if((CV[1][49] & 16) == 16)
            jR_F4.setSelected(true);
        else
            jR_F4.setSelected(false);

        if((CV[1][49] & 32) == 32)
            jAB_F3.setSelected(true);
        else
            jAB_F3.setSelected(false);

        if((CV[1][49] & 64) == 64)
            jAB_F4.setSelected(true);
        else
            jAB_F4.setSelected(false);
    }//GEN-LAST:event_jRangierenComponentShown

    private void jFahreigenschaftenComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jFahreigenschaftenComponentShown
        jAnfahrKick.setText("" + CV[1][65]);
        jAnfahrGeschw.setText("" + CV[1][2]);
        jAnfahrVerz.setText("" + CV[1][3]);
        jBremsVerz.setText("" + CV[1][4]);
        jVMax.setText("" + CV[1][5]);
        jMotorArt.setText("" + CV[1][124]);
        switch(CVNavi.Decoder)
        {
            case c.LD_G30:
                jUeberlast.setText("" + CV[1][117]);
                break;

            case c.LD_W32:
            case c.LD_W32_2:
                if(CV[1][9] == 0)
                {
                    jUeberlast.setText("480 Hz");
                    j480Hz.setSelected(true);
                    j80Hz.setSelected(false);
                }
                else
                {
                    jUeberlast.setText("80 Hz");
                    j80Hz.setSelected(true);
                    j480Hz.setSelected(false);
                }
                break;

            case c.LD_G32:
            case c.LD_G32_2:
                jUeberlast.setText("32 kHz");
                break;
        }
        jKp.setText("" + CV[1][50]);
        jKi.setText("" + CV[1][51]);
        jKd.setText("" + CV[1][52]);

        if((CV[1][27] & 16) == 16)
            jPosBrems.setSelected(true);
        else
            jPosBrems.setSelected(false);

        if((CV[1][27] & 32) == 32)
            jNegBrems.setSelected(true);
        else
            jNegBrems.setSelected(false);

        jCV_Anzeige.setSelectedItem( "CV#"+2 );
    }//GEN-LAST:event_jFahreigenschaftenComponentShown

    private void jKennlinieComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jKennlinieComponentShown
        jFS1.setValue(CV[1][67]);
        jFS2.setValue(CV[1][68]);
        jFS3.setValue(CV[1][69]);
        jFS4.setValue(CV[1][70]);
        jFS5.setValue(CV[1][71]);
        jFS6.setValue(CV[1][72]);
        jFS7.setValue(CV[1][73]);
        jFS8.setValue(CV[1][74]);
        jFS9.setValue(CV[1][75]);
        jFS10.setValue(CV[1][76]);
        jFS11.setValue(CV[1][77]);
        jFS12.setValue(CV[1][78]);
        jFS13.setValue(CV[1][79]);
        jFS14.setValue(CV[1][80]);
        jFS15.setValue(CV[1][81]);
        jFS16.setValue(CV[1][82]);
        jFS17.setValue(CV[1][83]);
        jFS18.setValue(CV[1][84]);
        jFS19.setValue(CV[1][85]);
        jFS20.setValue(CV[1][86]);
        jFS21.setValue(CV[1][87]);
        jFS22.setValue(CV[1][88]);
        jFS23.setValue(CV[1][89]);
        jFS24.setValue(CV[1][90]);
        jFS25.setValue(CV[1][91]);
        jFS26.setValue(CV[1][92]);
        jFS27.setValue(CV[1][93]);
        jFS28.setValue(CV[1][94]);
        jCV_Anzeige.setSelectedItem( "CV#"+67 );
        jFS_Anzeige.setText("FS 1:");
        jFS_AnzeigeWert.setText("" + CV[1][67]);
    }//GEN-LAST:event_jKennlinieComponentShown

    private void jCustom1sichernActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCustom1sichernActionPerformed
        String str = "CV-Navi - Kennlinie\r\n";
        str += "FS01=" + jFS1.getValue() + "\r\n";
        str += "FS02=" + jFS2.getValue() + "\r\n";
        str += "FS03=" + jFS3.getValue() + "\r\n";
        str += "FS04=" + jFS4.getValue() + "\r\n";
        str += "FS05=" + jFS5.getValue() + "\r\n";
        str += "FS06=" + jFS6.getValue() + "\r\n";
        str += "FS07=" + jFS7.getValue() + "\r\n";
        str += "FS08=" + jFS8.getValue() + "\r\n";
        str += "FS09=" + jFS9.getValue() + "\r\n";
        str += "FS10=" + jFS10.getValue() + "\r\n";
        str += "FS11=" + jFS11.getValue() + "\r\n";
        str += "FS12=" + jFS12.getValue() + "\r\n";
        str += "FS13=" + jFS13.getValue() + "\r\n";
        str += "FS14=" + jFS14.getValue() + "\r\n";
        str += "FS15=" + jFS15.getValue() + "\r\n";
        str += "FS16=" + jFS16.getValue() + "\r\n";
        str += "FS17=" + jFS17.getValue() + "\r\n";
        str += "FS18=" + jFS18.getValue() + "\r\n";
        str += "FS19=" + jFS19.getValue() + "\r\n";
        str += "FS20=" + jFS20.getValue() + "\r\n";
        str += "FS21=" + jFS21.getValue() + "\r\n";
        str += "FS22=" + jFS22.getValue() + "\r\n";
        str += "FS23=" + jFS23.getValue() + "\r\n";
        str += "FS24=" + jFS24.getValue() + "\r\n";
        str += "FS25=" + jFS25.getValue() + "\r\n";
        str += "FS26=" + jFS26.getValue() + "\r\n";
        str += "FS27=" + jFS27.getValue() + "\r\n";
        str += "FS28=" + jFS28.getValue() + "\r\n";

        SaveOpenDialog od = new SaveOpenDialog( this, true, false, str, this );
        jKurve4.setSelected(true);
}//GEN-LAST:event_jCustom1sichernActionPerformed

    public void filfilKENN() {
        // this.ReturnString is filled and has to be parsed

        Boolean b ;
        String[] keys = { "CV-Navi - Kennlinie" };
        b = parseString2CVs.convertString2Kennlinie( this.ReturnString, keys, CV, 67, CVNavi );
        if( b ) {
            jKurve4.setSelected(true);
            System.out.println("filfilKENN: parse OK");
        } else {
            System.out.println("filfilKENN: parse failed");
        }
    }

    private void jCustom1ladenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCustom1ladenActionPerformed
        SaveOpenDialog od = new SaveOpenDialog( this, true, true, null, this );
}//GEN-LAST:event_jCustom1ladenActionPerformed

    private void jFS1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jFS1MouseReleased
        CV[1][67] = jFS1.getValue();
        jFS_Anzeige.setText("FS 1:");
        jFS_AnzeigeWert.setText("" + CV[1][67]);
    }//GEN-LAST:event_jFS1MouseReleased

    private void jFS2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jFS2MouseReleased
        CV[1][68] = jFS2.getValue();
        jFS_Anzeige.setText("FS 2:");
        jFS_AnzeigeWert.setText("" + CV[1][68]);
    }//GEN-LAST:event_jFS2MouseReleased

    private void jFS3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jFS3MouseReleased
        CV[1][69] = jFS3.getValue();
        jFS_Anzeige.setText("FS 3:");
        jFS_AnzeigeWert.setText("" + CV[1][69]);
    }//GEN-LAST:event_jFS3MouseReleased

    private void jFS4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jFS4MouseReleased
        CV[1][70] = jFS4.getValue();
        jFS_Anzeige.setText("FS 4:");
        jFS_AnzeigeWert.setText("" + CV[1][70]);
    }//GEN-LAST:event_jFS4MouseReleased

    private void jFS5MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jFS5MouseReleased
        CV[1][71] = jFS5.getValue();
        jFS_Anzeige.setText("FS 5:");
        jFS_AnzeigeWert.setText("" + CV[1][71]);
    }//GEN-LAST:event_jFS5MouseReleased

    private void jFS6MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jFS6MouseReleased
        CV[1][72] = jFS6.getValue();
        jFS_Anzeige.setText("FS 6:");
        jFS_AnzeigeWert.setText("" + CV[1][72]);
    }//GEN-LAST:event_jFS6MouseReleased

    private void jFS7MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jFS7MouseReleased
        CV[1][73] = jFS7.getValue();
        jFS_Anzeige.setText("FS 7:");
        jFS_AnzeigeWert.setText("" + CV[1][73]);
    }//GEN-LAST:event_jFS7MouseReleased

    private void jFS8MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jFS8MouseReleased
        CV[1][74] = jFS8.getValue();
        jFS_Anzeige.setText("FS 8:");
        jFS_AnzeigeWert.setText("" + CV[1][74]);
    }//GEN-LAST:event_jFS8MouseReleased

    private void jFS9MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jFS9MouseReleased
        CV[1][75] = jFS9.getValue();
        jFS_Anzeige.setText("FS 9:");
        jFS_AnzeigeWert.setText("" + CV[1][75]);
    }//GEN-LAST:event_jFS9MouseReleased

    private void jFS10MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jFS10MouseReleased
        CV[1][76] = jFS10.getValue();
        jFS_Anzeige.setText("FS 10:");
        jFS_AnzeigeWert.setText("" + CV[1][76]);
    }//GEN-LAST:event_jFS10MouseReleased

    private void jFS11MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jFS11MouseReleased
        CV[1][77] = jFS11.getValue();
        jFS_Anzeige.setText("FS 11:");
        jFS_AnzeigeWert.setText("" + CV[1][77]);
    }//GEN-LAST:event_jFS11MouseReleased

    private void jFS12MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jFS12MouseReleased
        CV[1][78] = jFS12.getValue();
        jFS_Anzeige.setText("FS 12:");
        jFS_AnzeigeWert.setText("" + CV[1][78]);
    }//GEN-LAST:event_jFS12MouseReleased

    private void jFS13MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jFS13MouseReleased
        CV[1][79] = jFS13.getValue();
        jFS_Anzeige.setText("FS 13:");
        jFS_AnzeigeWert.setText("" + CV[1][79]);
    }//GEN-LAST:event_jFS13MouseReleased

    private void jFS14MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jFS14MouseReleased
        CV[1][80] = jFS14.getValue();
        jFS_Anzeige.setText("FS 14:");
        jFS_AnzeigeWert.setText("" + CV[1][80]);
    }//GEN-LAST:event_jFS14MouseReleased

    private void jFS15MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jFS15MouseReleased
        CV[1][81] = jFS15.getValue();
        jFS_Anzeige.setText("FS 15:");
        jFS_AnzeigeWert.setText("" + CV[1][81]);
    }//GEN-LAST:event_jFS15MouseReleased

    private void jFS16MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jFS16MouseReleased
        CV[1][82] = jFS16.getValue();
        jFS_Anzeige.setText("FS 16:");
        jFS_AnzeigeWert.setText("" + CV[1][82]);
    }//GEN-LAST:event_jFS16MouseReleased

    private void jFS17MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jFS17MouseReleased
        CV[1][83] = jFS17.getValue();
        jFS_Anzeige.setText("FS 17:");
        jFS_AnzeigeWert.setText("" + CV[1][83]);
    }//GEN-LAST:event_jFS17MouseReleased

    private void jFS18MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jFS18MouseReleased
        CV[1][84] = jFS18.getValue();
        jFS_Anzeige.setText("FS 18:");
        jFS_AnzeigeWert.setText("" + CV[1][84]);
    }//GEN-LAST:event_jFS18MouseReleased

    private void jFS19MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jFS19MouseReleased
        CV[1][85] = jFS19.getValue();
        jFS_Anzeige.setText("FS 19:");
        jFS_AnzeigeWert.setText("" + CV[1][85]);
    }//GEN-LAST:event_jFS19MouseReleased

    private void jFS20MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jFS20MouseReleased
        CV[1][86] = jFS20.getValue();
        jFS_Anzeige.setText("FS 20:");
        jFS_AnzeigeWert.setText("" + CV[1][86]);
    }//GEN-LAST:event_jFS20MouseReleased

    private void jFS21MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jFS21MouseReleased
        CV[1][87] = jFS21.getValue();
        jFS_Anzeige.setText("FS 21:");
        jFS_AnzeigeWert.setText("" + CV[1][87]);
    }//GEN-LAST:event_jFS21MouseReleased

    private void jFS22MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jFS22MouseReleased
        CV[1][88] = jFS22.getValue();
        jFS_Anzeige.setText("FS 22:");
        jFS_AnzeigeWert.setText("" + CV[1][88]);
    }//GEN-LAST:event_jFS22MouseReleased

    private void jFS23MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jFS23MouseReleased
        CV[1][89] = jFS23.getValue();
        jFS_Anzeige.setText("FS 23:");
        jFS_AnzeigeWert.setText("" + CV[1][89]);
    }//GEN-LAST:event_jFS23MouseReleased

    private void jFS24MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jFS24MouseReleased
        CV[1][90] = jFS24.getValue();
        jFS_Anzeige.setText("FS 24:");
        jFS_AnzeigeWert.setText("" + CV[1][90]);
    }//GEN-LAST:event_jFS24MouseReleased

    private void jFS25MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jFS25MouseReleased
        CV[1][91] = jFS25.getValue();
        jFS_Anzeige.setText("FS 25:");
        jFS_AnzeigeWert.setText("" + CV[1][91]);
    }//GEN-LAST:event_jFS25MouseReleased

    private void jFS26MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jFS26MouseReleased
        CV[1][92] = jFS26.getValue();
        jFS_Anzeige.setText("FS 26:");
        jFS_AnzeigeWert.setText("" + CV[1][92]);
    }//GEN-LAST:event_jFS26MouseReleased

    private void jFS27MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jFS27MouseReleased
        CV[1][93] = jFS27.getValue();
        jFS_Anzeige.setText("FS 27:");
        jFS_AnzeigeWert.setText("" + CV[1][93]);
    }//GEN-LAST:event_jFS27MouseReleased

    private void jFS28MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jFS28MouseReleased
        CV[1][94] = jFS28.getValue();
        jFS_Anzeige.setText("FS 28:");
        jFS_AnzeigeWert.setText("" + CV[1][94]);
    }//GEN-LAST:event_jFS28MouseReleased

    private void jAnfahrGeschwKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jAnfahrGeschwKeyReleased
        if( evt.getKeyCode() == KeyEvent.VK_ENTER ) {
            transferFocus();
        }
    }//GEN-LAST:event_jAnfahrGeschwKeyReleased

    private void jAnfahrVerzKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jAnfahrVerzKeyReleased
        if( evt.getKeyCode() == KeyEvent.VK_ENTER ) {
            transferFocus();
        }
    }//GEN-LAST:event_jAnfahrVerzKeyReleased

    private void jBremsVerzKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBremsVerzKeyReleased
        if( evt.getKeyCode() == KeyEvent.VK_ENTER ) {
            transferFocus();
        }
    }//GEN-LAST:event_jBremsVerzKeyReleased

    private void jVMaxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jVMaxKeyReleased
        if( evt.getKeyCode() == KeyEvent.VK_ENTER ) {
            transferFocus();
        }
    }//GEN-LAST:event_jVMaxKeyReleased

    private void jMotorArtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jMotorArtKeyReleased
        if( evt.getKeyCode() == KeyEvent.VK_ENTER ) {
            transferFocus();
        }
    }//GEN-LAST:event_jMotorArtKeyReleased

    private void jUeberlastKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jUeberlastKeyReleased
        if( evt.getKeyCode() == KeyEvent.VK_ENTER ) {
            transferFocus();
        }
    }//GEN-LAST:event_jUeberlastKeyReleased

    private void jKpKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jKpKeyReleased
        if( evt.getKeyCode() == KeyEvent.VK_ENTER ) {
            transferFocus();
        }
    }//GEN-LAST:event_jKpKeyReleased

    private void jKiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jKiKeyReleased
        if( evt.getKeyCode() == KeyEvent.VK_ENTER ) {
            transferFocus();
        }
    }//GEN-LAST:event_jKiKeyReleased

    private void jKdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jKdKeyReleased
        if( evt.getKeyCode() == KeyEvent.VK_ENTER ) {
            transferFocus();
        }
    }//GEN-LAST:event_jKdKeyReleased

    private void j80HzActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_j80HzActionPerformed
        jUeberlast.setText("80 Hz");
        CV[1][9] = 1;
        jCV_Anzeige.setSelectedItem( "CV#"+9 );
        jCV_Inhalt.setText("" + CV[1][9]);
    }//GEN-LAST:event_j80HzActionPerformed

    private void j480HzActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_j480HzActionPerformed
        jUeberlast.setText("480 Hz");
        CV[1][9] = 0;
        jCV_Anzeige.setSelectedItem( "CV#"+9 );
        jCV_Inhalt.setText("" + CV[1][9]);
    }//GEN-LAST:event_j480HzActionPerformed

    private void jAnfahrKickKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jAnfahrKickKeyReleased
        if( evt.getKeyCode() == KeyEvent.VK_ENTER ) {
            transferFocus();
        }
}//GEN-LAST:event_jAnfahrKickKeyReleased

    private void jMotorListeMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMotorListeMouseReleased
        setMotorTyp();
}//GEN-LAST:event_jMotorListeMouseReleased

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        updateTabs();
    }//GEN-LAST:event_formWindowActivated

    private void jDecoderAdresseFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDecoderAdresseFocusLost
        int j = CVNavi.checkTextField( this, jDecoderAdresse, 1, 10239, 3, true );
        String s = jDecoderAdresse.getText();
        if (jKurzeAdr.isSelected()) {
            if( j < 1 ||  j > 255 )
            {
                CVNavi.mbValueNaN( this, 1, 255, true);
                j = 1;
                jDecoderAdresse.setText("3");
            }
            else if (j > 127)
            {
                CVNavi.mbAdr128MMonly( this );
            }
            CV[1][1] = j;
            CV[1][29] &= ~32;
            jCV_Anzeige.setSelectedItem( "CV#"+1 );
        } else { // lange Adresse
            if (j < 128 || j > 10239) {
                CVNavi.mbValueNaN( this, 128, 10239, true);
                j = 128;
                jDecoderAdresse.setText("128");
            }
            CV[1][29] |= 32;
            CV[1][17] = j/256 + 192;
            CV[1][18] = j%256;
            jCV_Anzeige.setSelectedItem( "CV#"+17 );
        }
    }//GEN-LAST:event_jDecoderAdresseFocusLost

    private void jDecoderAdresse1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDecoderAdresse1FocusLost
        CV[1][19] = CVNavi.checkTextField( this, jDecoderAdresse1, 0, 127, 0, true);
        jCV_Anzeige.setSelectedItem( "CV#"+19 );
    }//GEN-LAST:event_jDecoderAdresse1FocusLost

    private void jBlinkFrequenzFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlinkFrequenzFocusLost
        CV[1][112] = CVNavi.checkTextField( this, jBlinkFrequenz, 10, 255, 200, true );
        jCV_Anzeige.setSelectedItem( "CV#"+112 );
    }//GEN-LAST:event_jBlinkFrequenzFocusLost

    private void jMM_Addr_2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jMM_Addr_2FocusLost
        CV[1][114] = CVNavi.checkTextField( this, jMM_Addr_2, 1, 255, 4, true );
        jCV_Anzeige.setSelectedItem( "CV#"+114 );
    }//GEN-LAST:event_jMM_Addr_2FocusLost

    private void jCV_InhaltFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jCV_InhaltFocusLost
        int adr;
        int oldValue;
        int currCV = getCVfromIndexString(jCV_Anzeige, "CV#");
        String oriEingabe = jCV_Inhalt.getText();
        // int cvValue = CVNavi.checkTextField( this, jCV_Inhalt, 0, 255, 22222, false);
        int cvValue = CVNavi.checkTextField( this, jCV_Inhalt, 0, 255, -1, false);
        if( cvValue == -1 ) {
            if( debugLevel >= 1 ) {
                System.out.println("jCV_InhaltFocusLost CV="+currCV+" Wert=\""+oriEingabe+"\" IGNORIERT");
            }
            jCV_Inhalt.setText(oriEingabe);
            getDefaultToolkit().beep();
            jCV_Inhalt.grabFocus();
            return;
        }
        String s = jCV_Inhalt.getText();
        switch(currCV) {
            case 1: //CV#1
                cvValue = CVNavi.checkTextField( this, jCV_Inhalt, 1, 255, 3, true );
                s = jCV_Inhalt.getText();
                if( cvValue > 127 ) {
                    CVNavi.mbAdr128MMonly( this );
                }
                jDecoderAdresse.setText(s);
                jKurzeAdr.setSelected(true);
                jlangeAdr.setSelected(false);
                CV[1][currCV] = cvValue;
                break;

            case 2: //CV#2
                jAnfahrGeschw.setText(s);
                break;

            case 3: //CV#3
                jAnfahrVerz.setText(s);
                break;

            case 4: //CV#4
                jBremsVerz.setText(s);
                break;

            case 5: //CV#5
                jVMax.setText(s);
                break;

             case 9: //CV#9
                switch(CVNavi.Decoder)
                {
                    case c.LD_W32:
                    case c.LD_W32_2:
                        cvValue = CVNavi.checkTextField( this, jCV_Inhalt, 0, 1, 1, true );
                        s = jCV_Inhalt.getText();
                        CV[1][currCV] = cvValue;
                        if(cvValue == 0)
                        {
                            jUeberlast.setText("480 Hz");
                            j480Hz.setSelected(true);
                            j80Hz.setSelected(false);
                        }
                        else
                        {
                            jUeberlast.setText("80 Hz");
                            j80Hz.setSelected(true);
                            j480Hz.setSelected(false);
                        }
                        break;

                    case c.LD_G32:
                    case c.LD_G32_2:
                        jUeberlast.setText("32 kHz");
                        break;
                }
                break;

            case 12:
                cvValue = CVNavi.checkTextField( this, jCV_Inhalt, 0, 1, 1, true );
                jAnalogRW_G.setSelected(    (cvValue & 1) == 1);
                jAnalogRW_W.setSelected( ! ((cvValue & 1) == 1));
                break;

            case 13: //CV#13
                CV[1][currCV] = cvValue;
                if((CV[1][13] & 1) == 1)
                    jF1.setSelected(true);
                else
                    jF1.setSelected(false);

                if((CV[1][13] & 2) == 2)
                    jF2.setSelected(true);
                else
                    jF2.setSelected(false);

                if((CV[1][13] & 4) == 4)
                    jF3.setSelected(true);
                else
                    jF3.setSelected(false);

                if((CV[1][13] & 8) == 8)
                    jF4.setSelected(true);
                else
                    jF4.setSelected(false);

                if((CV[1][13] & 16) == 16)
                    jF5.setSelected(true);
                else
                    jF5.setSelected(false);

                if((CV[1][13] & 32) == 32)
                    jF6.setSelected(true);
                else
                    jF6.setSelected(false);

                if((CV[1][13] & 64) == 64)
                    jF7.setSelected(true);
                else
                    jF7.setSelected(false);

                if((CV[1][13] & 128) == 128)
                    jF8.setSelected(true);
                else
                    jF8.setSelected(false);
                break;

            case 17: //CV#17
                cvValue = CVNavi.checkTextField( this, jCV_Inhalt, 192, 255, 192, true );
                if (cvValue < 192) {
                    CVNavi.mbValueNaNcv( this, 192, 255, 17, true);
                    cvValue = 192;
                    jCV_Inhalt.setText("192");
                }
                CV[1][currCV] = cvValue;
                jKurzeAdr.setSelected(false);
                jlangeAdr.setSelected(true);
                adr = (CV[1][17] - 192)*256 + CV[1][18];
                jDecoderAdresse.setText(""+adr);
                break;

            case 18: //CV#18
                s = jCV_Inhalt.getText();
                jDecoderAdresse.setText(s);
                jKurzeAdr.setSelected(false);
                jlangeAdr.setSelected(true);
                break;

            case 19: //CV#19
                if (cvValue > 127)
                {
                    CVNavi.mbValueConsist( this, 0, 127 );
                    cvValue = 127;
                    s = "127";
                    jCV_Inhalt.setText(s);
                }
                jDecoderAdresse1.setText(s);
                break;

             case 27: //CV#27
                CV[1][currCV] = cvValue;
                if((CV[1][27] & 16) == 16)
                    jPosBrems.setSelected(true);
                else
                    jPosBrems.setSelected(false);
                if((CV[1][27] & 32) == 32)
                    jNegBrems.setSelected(true);
                else
                    jNegBrems.setSelected(false);
                break;

             case 29: //CV#29
                CV[1][currCV] = cvValue;
                if((CV[1][29] & 1) == 1)
                    jRichtung.setSelected(true);
                else
                    jRichtung.setSelected(false);

                if((CV[1][29] & 2) == 2)
                    jFS.setSelected(true);
                else
                    jFS.setSelected(false);

                if((CV[1][29] & 4) == 4)
                    jAnalog1.setSelected(true);
                else
                    jAnalog1.setSelected(false);

                if((CV[1][29] & 8) == 8)
                    jRailCom.setSelected(true);
                else
                    jRailCom.setSelected(false);

                if((CV[1][29] & 16) == 16)
                    jAltKennlinie.setSelected(true);
                else
                    jAltKennlinie.setSelected(false);

                if((CV[1][29] & 32) == 32)
                    jLongAddr.setSelected(true);
                else
                    jLongAddr.setSelected(false);
                jMM_Addr_2.setText("" + CV[1][114]);
                break;

            case 33: //CV#33
                CV[1][currCV] = cvValue;
                if((cvValue & 1) == 1 )
                    jFL_1.setSelected(true);
                else
                    jFL_1.setSelected(false);

                if((cvValue & 2) == 2 )
                    jFL_2.setSelected(true);
                else
                    jFL_2.setSelected(false);

                if((cvValue & 4) == 4 )
                    jFL_3.setSelected(true);
                else
                    jFL_3.setSelected(false);
                break;

            case 34: //CV#34
                CV[1][currCV] = cvValue;
                if((cvValue & 1) == 1 )
                    jFR_1.setSelected(true);
                else
                    jFR_1.setSelected(false);

                if((cvValue & 2) == 2 )
                    jFR_2.setSelected(true);
                else
                    jFR_2.setSelected(false);

                if((cvValue & 4) == 4 )
                    jFR_3.setSelected(true);
                else
                    jFR_3.setSelected(false);
                break;

            case 35: //CV#35
                CV[1][currCV] = cvValue;
                if((cvValue & 1) == 1 )
                    jF1_1.setSelected(true);
                else
                    jF1_1.setSelected(false);

                if((cvValue & 2) == 2 )
                    jF1_2.setSelected(true);
                else
                    jF1_2.setSelected(false);

                if((cvValue & 4) == 4 )
                    jF1_3.setSelected(true);
                else
                    jF1_3.setSelected(false);
                break;

            case 36: //CV#36
                CV[1][currCV] = cvValue;
                if((cvValue & 1) == 1 )
                    jF2_1.setSelected(true);
                else
                    jF2_1.setSelected(false);

                if((cvValue & 2) == 2 )
                    jF2_2.setSelected(true);
                else
                    jF2_2.setSelected(false);

                if((cvValue & 4) == 4 )
                    jF2_3.setSelected(true);
                else
                    jF2_3.setSelected(false);
                break;

            case 37: //CV#37
                CV[1][currCV] = cvValue;
                if((cvValue & 1) == 1 )
                    jF3_1.setSelected(true);
                else
                    jF3_1.setSelected(false);

                if((cvValue & 2) == 2 )
                    jF3_2.setSelected(true);
                else
                    jF3_2.setSelected(false);

                if((cvValue & 4) == 4 )
                    jF3_3.setSelected(true);
                else
                    jF3_3.setSelected(false);
                break;

            case 38: //CV#38
                CV[1][currCV] = cvValue;
                if((cvValue & 1) == 1 )
                    jF4_1.setSelected(true);
                else
                    jF4_1.setSelected(false);

                if((cvValue & 2) == 2 )
                    jF4_2.setSelected(true);
                else
                    jF4_2.setSelected(false);

                if((cvValue & 4) == 4 )
                    jF4_3.setSelected(true);
                else
                    jF4_3.setSelected(false);
                break;

            case 39: //CV#39
                CV[1][currCV] = cvValue;
                if((cvValue & 1) == 1 )
                    jF5_1.setSelected(true);
                else
                    jF5_1.setSelected(false);

                if((cvValue & 2) == 2 )
                    jF5_2.setSelected(true);
                else
                    jF5_2.setSelected(false);

                if((cvValue & 4) == 4 )
                    jF5_3.setSelected(true);
                else
                    jF5_3.setSelected(false);
                break;

            case 40: //CV#40
                CV[1][currCV] = cvValue;
                if((cvValue & 1) == 1 )
                    jF6_1.setSelected(true);
                else
                    jF6_1.setSelected(false);

                if((cvValue & 2) == 2 )
                    jF6_2.setSelected(true);
                else
                    jF6_2.setSelected(false);

                if((cvValue & 4) == 4 )
                    jF6_3.setSelected(true);
                else
                    jF6_3.setSelected(false);
                break;

            case 41: //CV#41
                CV[1][currCV] = cvValue;
                if((cvValue & 1) == 1 )
                    jF7_1.setSelected(true);
                else
                    jF7_1.setSelected(false);

                if((cvValue & 2) == 2 )
                    jF7_2.setSelected(true);
                else
                    jF7_2.setSelected(false);

                if((cvValue & 4) == 4 )
                    jF7_3.setSelected(true);
                else
                    jF7_3.setSelected(false);
                break;

            case 42: //CV#42
                CV[1][currCV] = cvValue;
                if((cvValue & 1) == 1 )
                    jF8_1.setSelected(true);
                else
                    jF8_1.setSelected(false);

                if((cvValue & 2) == 2 )
                    jF8_2.setSelected(true);
                else
                    jF8_2.setSelected(false);

                if((cvValue & 4) == 4 )
                    jF8_3.setSelected(true);
                else
                    jF8_3.setSelected(false);
                break;

            case 43: //CV#43
                CV[1][currCV] = cvValue;
                if((cvValue & 1) == 1 )
                    jF9_1.setSelected(true);
                else
                    jF9_1.setSelected(false);

                if((cvValue & 2) == 2 )
                    jF9_2.setSelected(true);
                else
                    jF9_2.setSelected(false);

                if((cvValue & 4) == 4 )
                    jF9_3.setSelected(true);
                else
                    jF9_3.setSelected(false);
                break;

            case 44: //CV#44
                CV[1][currCV] = cvValue;
                if((cvValue & 1) == 1 )
                    jF10_1.setSelected(true);
                else
                    jF10_1.setSelected(false);

                if((cvValue & 2) == 2 )
                    jF10_2.setSelected(true);
                else
                    jF10_2.setSelected(false);

                if((cvValue & 4) == 4 )
                    jF10_3.setSelected(true);
                else
                    jF10_3.setSelected(false);
                break;

            case 45: //CV#45
                CV[1][currCV] = cvValue;
                if((cvValue & 1) == 1 )
                    jF11_1.setSelected(true);
                else
                    jF11_1.setSelected(false);

                if((cvValue & 2) == 2 )
                    jF11_2.setSelected(true);
                else
                    jF11_2.setSelected(false);

                if((cvValue & 4) == 4 )
                    jF11_3.setSelected(true);
                else
                    jF11_3.setSelected(false);
                break;

            case 46: //CV#46
                CV[1][currCV] = cvValue;
                if((cvValue & 1) == 1 )
                    jF12_1.setSelected(true);
                else
                    jF12_1.setSelected(false);

                if((cvValue & 2) == 2 )
                    jF12_2.setSelected(true);
                else
                    jF12_2.setSelected(false);

                if((cvValue & 4) == 4 )
                    jF12_3.setSelected(true);
                else
                    jF12_3.setSelected(false);
                break;

            case 49: //CV#49
                CV[1][currCV] = cvValue;
                if((cvValue & 2) == 2)
                    jR_F1.setSelected(true);
                else
                    jR_F1.setSelected(false);

                if((cvValue & 4) == 4)
                    jR_F2.setSelected(true);
                else
                    jR_F2.setSelected(false);

                if((cvValue & 8) == 8)
                    jR_F3.setSelected(true);
                else
                    jR_F3.setSelected(false);

                if((cvValue & 16) == 16)
                    jR_F4.setSelected(true);
                else
                    jR_F4.setSelected(false);

                if((cvValue & 32) == 32)
                    jAB_F3.setSelected(true);
                else
                    jAB_F3.setSelected(false);

                if((cvValue & 64) == 64)
                    jAB_F4.setSelected(true);
                else
                    jAB_F4.setSelected(false);
                break;


            case 50: //CV#50
                jKp.setText(s);
                break;

            case 51: //CV#51
                jKi.setText(s);
                break;

            case 52: //CV#52
                jKd.setText(s);
                break;

            case 53: //CV53
            case 54: //CV54
            case 55: //CV55
                CV[1][currCV] = cvValue;
                jTast1.setText( "" + ((CV[1][53] & 0xF0) >> 4));
                jTast2.setText( "" + ((CV[1][54] & 0xF0) >> 4));
                jTast3.setText( "" + ((CV[1][55] & 0xF0) >> 4));
                if((CV[1][53] & 1) == 1) {
                    jVor1.setSelected(false);
                } else {
                    jVor1.setSelected(true);
                }
                if((CV[1][53] & 2) == 2) {
                    jRueck1.setSelected(false);
                } else {
                    jRueck1.setSelected(true);
                }
                if((CV[1][53] & 8) == 8) {
                    jInvertBlink1.setSelected(true);
                } else {
                    jInvertBlink1.setSelected(false);
                }

                if((CV[1][54] & 1) == 1) {
                    jVor2.setSelected(false);
                } else {
                    jVor2.setSelected(true);
                }
                if((CV[1][54] & 2) == 2) {
                    jRueck2.setSelected(false);
                } else {
                    jRueck2.setSelected(true);
                }
                if((CV[1][54] & 8) == 8) {
                    jInvertBlink2.setSelected(true);
                } else {
                    jInvertBlink2.setSelected(false);
                }

                if((CV[1][55] & 1) == 1) {
                    jVor3.setSelected(false);
                } else {
                    jVor3.setSelected(true);
                }
                if((CV[1][55] & 2) == 2) {
                    jRueck3.setSelected(false);
                } else {
                    jRueck3.setSelected(true);
                }
                if((CV[1][55] & 8) == 8) {
                    jInvertBlink3.setSelected(true);
                } else {
                    jInvertBlink3.setSelected(false);
                }
                break;

            case 62: //CV62 (Dimmen)
                cvValue = CVNavi.checkTextField( this, jCV_Inhalt, 1, 255, 255, true );
                s = jCV_Inhalt.getText();
                CV[1][currCV] = cvValue;
                jDimmen1.setText("" + (CV[1][62] & 0x0F));
                jDimmen2.setText("" + ((CV[1][62] & 0xF0)>>4));
                break;

            case 63: //CV63 (Dimmen)
                cvValue = CVNavi.checkTextField( this, jCV_Inhalt, 1, 15, 15, true );
                s = jCV_Inhalt.getText();
                CV[1][currCV] = cvValue;
                jDimmen3.setText("" + (CV[1][63] & 0x0F));
                break;

            case 65: //CV#65
                jAnfahrKick.setText(s);
                break;

            case 112: //CV112 (Blinken)
                cvValue = CVNavi.checkTextField( this, jCV_Inhalt, 10, 255, 200, true );
                s = jCV_Inhalt.getText();
                CV[1][currCV] = cvValue;
                jBlinkFrequenz.setText(s);
                break;

            case 113: //CV113 (AltDimmen - Fahrstufe)
                cvValue = CVNavi.checkTextField( this, jCV_Inhalt, 1, 126, 16, true );
                s = jCV_Inhalt.getText();
                CV[1][currCV] = cvValue;
                jAltDimmenFS1.setText(s);
                jAltDimmenFS2.setText(s);
                jAltDimmenFS3.setText(s);
                break;

            case 114: //CV114 (2te MM-Adresse)
                cvValue = CVNavi.checkTextField( this, jCV_Inhalt, 1, 255, 4, true );
                s = jCV_Inhalt.getText();
                jMM_Addr_2.setText(s);
                break;

            case 115: //CV#115
                CV[1][currCV] = cvValue;
                if((cvValue & 1) == 1)
                    jRL_1.setSelected(true);
                else
                    jRL_1.setSelected(false);

                if((cvValue & 2) == 2)
                    jRL_2.setSelected(true);
                else
                    jRL_2.setSelected(false);

                if((cvValue & 4) == 4)
                    jRL_3.setSelected(true);
                else
                    jRL_3.setSelected(false);

                if((cvValue & 64) == 64)
                    jRL_F3.setSelected(true);
                else
                    jRL_F3.setSelected(false);

                if((cvValue & 128) == 128)
                    jRL_F4.setSelected(true);
                else
                    jRL_F4.setSelected(false);
                break;

            case 116: //CV#116
                cvValue = CVNavi.checkTextField( this, jCV_Inhalt, 0, 3, 0, true );
                s = jCV_Inhalt.getText();
                CV[1][currCV] = cvValue;
                if((cvValue & 1) == 1) {
                    jAltDim1ein.setSelected(true);
                    jAltDim1aus.setSelected(false);
                } else {
                    jAltDim1ein.setSelected(false);
                    jAltDim1aus.setSelected(true);
                }
                if((cvValue & 2) == 2) {
                    jAltDim2ein.setSelected(true);
                    jAltDim2aus.setSelected(false);
                } else {
                    jAltDim2ein.setSelected(false);
                    jAltDim2aus.setSelected(true);
                }
                if((cvValue & 4) == 4) {
                    jAltDim3ein.setSelected(true);
                    jAltDim3aus.setSelected(false);
                } else {
                    jAltDim3ein.setSelected(false);
                    jAltDim3aus.setSelected(true);
                }
                break;

            case 117: //CV117 (Überlast)
                switch(CVNavi.Decoder) {
                    case c.LD_G30:
                        cvValue = CVNavi.checkTextField( this, jCV_Inhalt, 0, 255, 64, true );
                        s = jCV_Inhalt.getText();
                        CV[1][currCV] = cvValue;
                        jUeberlast.setText(s);
                        break;
                }
                break;

            case 118: //CV118 (AltDimmen)
                cvValue = CVNavi.checkTextField( this, jCV_Inhalt, 1, 255, 255, true );
                s = jCV_Inhalt.getText();
                CV[1][currCV] = cvValue;
                jAltDimmen1.setText("" + (CV[1][118] & 0x0F));
                jAltDimmen2.setText("" + ((CV[1][118] & 0xF0)>>4));
                break;

            case 119: //CV119 (AltDimmen)
                cvValue = CVNavi.checkTextField( this, jCV_Inhalt, 1, 15, 15, true );
                s = jCV_Inhalt.getText();
                CV[1][currCV] = cvValue;
                jAltDimmen3.setText("" + (CV[1][119] & 0x0F));
                break;

            case 124: //CV#124 Optimierung der Lastregelung / MotorArt
                int defValue;
                switch(CVNavi.Decoder) {
                    case c.LD_G30:
                        defValue = 3;
                        break;
                    case c.LD_G32:
                    case c.LD_G32_2:
                        defValue = 2;
                        break;
                    default:
                        // not on LD-W decoders !
                        return;
                }
                cvValue = CVNavi.checkTextField( this, jCV_Inhalt, 1, 15, defValue, true );
                s = jCV_Inhalt.getText();
                CV[1][currCV] = cvValue;
                jMotorArt.setText(s);
                break;

            default:
                System.out.println("jCV_InhaltFocusLost CV="+currCV+" Wert="+cvValue+" IGNORIERT");
                return;

        }
        if( cvValue == -1 ) {
            System.out.println("jCV_InhaltFocusLost CV="+currCV+" Wert=\""+oriEingabe+"\" IGNORIERT");
            jCV_Inhalt.setText(oriEingabe);
            getDefaultToolkit().beep();
            jCV_Inhalt.grabFocus();
        } else {
            CV[1][currCV] = cvValue;
           jCV_Inhalt.setText("" + cvValue);
        }
    }//GEN-LAST:event_jCV_InhaltFocusLost

    private void jAbbrechenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAbbrechenActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jAbbrechenActionPerformed

    private void jDecoderAdresseFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDecoderAdresseFocusGained
        if (jKurzeAdr.isSelected()) {
            jCV_Anzeige.setSelectedItem( "CV#"+1 );
        } else {
            jCV_Anzeige.setSelectedItem( "CV#"+17 );
        }
    }//GEN-LAST:event_jDecoderAdresseFocusGained

    private void jDecoderAdresse1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDecoderAdresse1FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+19 );
    }//GEN-LAST:event_jDecoderAdresse1FocusGained

    private void jBlinkFrequenzFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlinkFrequenzFocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+112 );
    }//GEN-LAST:event_jBlinkFrequenzFocusGained

    private void jMM_Addr_2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jMM_Addr_2FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+114 );
    }//GEN-LAST:event_jMM_Addr_2FocusGained

    private void jDimmen1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmen1FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+62 );
    }//GEN-LAST:event_jDimmen1FocusGained

    private void jDimmen2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmen2FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+62 );
    }//GEN-LAST:event_jDimmen2FocusGained

    private void jAltDimmenFS1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jAltDimmenFS1FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+113 );
    }//GEN-LAST:event_jAltDimmenFS1FocusGained

    private void jAltDimmenFS2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jAltDimmenFS2FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+113 );
    }//GEN-LAST:event_jAltDimmenFS2FocusGained

    private void jAltDimmen1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jAltDimmen1FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+118 );
    }//GEN-LAST:event_jAltDimmen1FocusGained

    private void jAltDimmen2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jAltDimmen2FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+118 );
    }//GEN-LAST:event_jAltDimmen2FocusGained

    private void jAnfahrGeschwFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jAnfahrGeschwFocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+2 );
    }//GEN-LAST:event_jAnfahrGeschwFocusGained

    private void jAnfahrVerzFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jAnfahrVerzFocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+3 );
    }//GEN-LAST:event_jAnfahrVerzFocusGained

    private void jBremsVerzFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBremsVerzFocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+4 );
    }//GEN-LAST:event_jBremsVerzFocusGained

    private void jVMaxFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jVMaxFocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+5 );
    }//GEN-LAST:event_jVMaxFocusGained

    private void jMotorArtFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jMotorArtFocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+124 );
    }//GEN-LAST:event_jMotorArtFocusGained

    private void jAnfahrKickFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jAnfahrKickFocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+65 );
    }//GEN-LAST:event_jAnfahrKickFocusGained

    private void jUeberlastFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jUeberlastFocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+117 );
    }//GEN-LAST:event_jUeberlastFocusGained

    private void jKpFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jKpFocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+50 );
    }//GEN-LAST:event_jKpFocusGained

    private void jKiFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jKiFocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+51 );
    }//GEN-LAST:event_jKiFocusGained

    private void jKdFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jKdFocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+52 );
    }//GEN-LAST:event_jKdFocusGained

    private void jMotorListeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jMotorListeFocusGained
        // keine Aktion
    }//GEN-LAST:event_jMotorListeFocusGained

    private void jDimmen1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmen1FocusLost
        int cvValue = CVNavi.checkTextField( this, jDimmen1, 0, 15, 15, true );
        CV[1][62] = (CV[1][62] & 0xF0) + cvValue;
        jCV_Anzeige.setSelectedItem( "CV#"+62 );
    }//GEN-LAST:event_jDimmen1FocusLost

    private void jDimmen2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmen2FocusLost
        int cvValue = CVNavi.checkTextField( this, jDimmen2, 0, 15, 15, true );
        CV[1][62] = (CV[1][62] & 0x0F) + cvValue*16;
        jCV_Anzeige.setSelectedItem( "CV#"+62 );
    }//GEN-LAST:event_jDimmen2FocusLost

    private void jAltDimmenFS1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jAltDimmenFS1FocusLost
        int cvValue = CVNavi.checkTextField( this, jAltDimmenFS1, 1, 126, 16, true );
        CV[1][113] = cvValue;
        jCV_Anzeige.setSelectedItem( "CV#"+113 );
    }//GEN-LAST:event_jAltDimmenFS1FocusLost

    private void jAltDimmenFS2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jAltDimmenFS2FocusLost
        // nicht editierbar
    }//GEN-LAST:event_jAltDimmenFS2FocusLost

    private void jAltDimmen1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jAltDimmen1FocusLost
        int cvValue = CVNavi.checkTextField( this, jAltDimmen1, 0, 15, 15, true );
        CV[1][118] = (CV[1][118] & 0xF0) + cvValue;
        jCV_Anzeige.setSelectedItem( "CV#"+118 );
    }//GEN-LAST:event_jAltDimmen1FocusLost

    private void jAltDimmen2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jAltDimmen2FocusLost
        int cvValue = CVNavi.checkTextField( this, jAltDimmen2, 0, 15, 15, true );
        CV[1][118] = (CV[1][118] & 0x0F) + cvValue*16;
        jCV_Anzeige.setSelectedItem( "CV#"+118 );
    }//GEN-LAST:event_jAltDimmen2FocusLost

    private void jAnfahrGeschwFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jAnfahrGeschwFocusLost
        int defValue = 5; // LD-G-30, LD-G-32
        switch( CVNavi.Decoder) {
            case c.LD_W32:
                defValue = 50;
                break;
        }
        int cvValue = CVNavi.checkTextField( this, jAnfahrGeschw, 0, 255, defValue, true );
        CV[1][2] = cvValue;
        jCV_Anzeige.setSelectedItem( "CV#"+2 );
    }//GEN-LAST:event_jAnfahrGeschwFocusLost

    private void jAnfahrVerzFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jAnfahrVerzFocusLost
        int defValue = 20; // LD-G-30, LD-G-32
        switch( CVNavi.Decoder) {
            case c.LD_W32:
                defValue = 16;
                break;
        }
        int cvValue = CVNavi.checkTextField( this, jAnfahrVerz, 0, 255, defValue, true );
        CV[1][3] = cvValue;
        jCV_Anzeige.setSelectedItem( "CV#"+3 );
    }//GEN-LAST:event_jAnfahrVerzFocusLost

    private void jBremsVerzFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBremsVerzFocusLost
        int defValue = 15; // LD-G-30, LD-G-32
        switch( CVNavi.Decoder) {
            case c.LD_W32:
                defValue = 5 ;
                break;
        }
        int cvValue = CVNavi.checkTextField( this, jBremsVerz, 0, 255, defValue, true );
        CV[1][4] = cvValue;
        jCV_Anzeige.setSelectedItem( "CV#"+4 );
    }//GEN-LAST:event_jBremsVerzFocusLost

    private void jVMaxFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jVMaxFocusLost
        int cvValue = CVNavi.checkTextField( this, jVMax, 0, 255, 255, true );        
        CV[1][5] = cvValue;
        jCV_Anzeige.setSelectedItem( "CV#"+5 );
    }//GEN-LAST:event_jVMaxFocusLost

    private void jMotorArtFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jMotorArtFocusLost
        int defValue = 3;
        switch( CVNavi.Decoder) {
            case c.LD_G30:
                defValue = 3;
                break;
            case c.LD_G32:
            case c.LD_G32_2:
                defValue = 2;
                break;
        }
        int cvValue = CVNavi.checkTextField( this, jMotorArt, 0, 255, defValue, true );
        CV[1][124] = cvValue;
        jCV_Anzeige.setSelectedItem( "CV#"+124 );
    }//GEN-LAST:event_jMotorArtFocusLost

    private void jAnfahrKickFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jAnfahrKickFocusLost
        int defValue = 0; // LD-G-30, LD-G-32
        switch( CVNavi.Decoder) {
            case c.LD_W32:
                defValue = 55 ;
                break;
        }
        int cvValue = CVNavi.checkTextField( this, jAnfahrKick, 0, 255, defValue, true );
        CV[1][65] = cvValue;
        jCV_Anzeige.setSelectedItem( "CV#"+65 );
    }//GEN-LAST:event_jAnfahrKickFocusLost

    private void jUeberlastFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jUeberlastFocusLost
        int defValue; // not all decoders 
        switch( CVNavi.Decoder) {
            case c.LD_G30:
                defValue = 64 ;
                break;
            default:
                // not on all other decoders !
                return;
        }
        int cvValue = CVNavi.checkTextField( this, jUeberlast, 0, 255, defValue, true );
        CV[1][117] = cvValue;
        jCV_Anzeige.setSelectedItem( "CV#"+117 );
    }//GEN-LAST:event_jUeberlastFocusLost

    private void jKpFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jKpFocusLost
        int defValue = 0;
        switch( CVNavi.Decoder) {
            case c.LD_G30:
                defValue = 40 ;
                break;
            case c.LD_G32:
            case c.LD_G32_2:
                defValue = 90 ;
                break;
            default:
                // not on LD-W decoders !
                return;
        }
        int cvValue = CVNavi.checkTextField( this, jKp, 0, 255, defValue, true );
        CV[1][50] = cvValue;
        jCV_Anzeige.setSelectedItem( "CV#"+50 );
    }//GEN-LAST:event_jKpFocusLost

    private void jKiFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jKiFocusLost
        int defValue;
        switch( CVNavi.Decoder) {
            case c.LD_G30:
                defValue = 30 ;
                break;
            case c.LD_G32:
            case c.LD_G32_2:
                defValue = 70 ;
                break;
            default:
                // not on LD-W decoders !
                return;
        }
        int cvValue = CVNavi.checkTextField( this, jKi, 0, 255, defValue, true );
        CV[1][51] = cvValue;
        jCV_Anzeige.setSelectedItem( "CV#"+51 );
    }//GEN-LAST:event_jKiFocusLost

    private void jKdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jKdFocusLost
        int defValue;
        switch( CVNavi.Decoder) {
            case c.LD_G30:
            case c.LD_G32:
            case c.LD_G32_2:
                defValue = 40 ;
                break;
            default:
                // not on LD-W decoders !
                return;
        }
        int cvValue = CVNavi.checkTextField( this, jKd, 0, 255, defValue, true );
        CV[1][52] = cvValue;
        jCV_Anzeige.setSelectedItem( "CV#"+52 );
    }//GEN-LAST:event_jKdFocusLost

    private void jMotorListeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jMotorListeFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jMotorListeFocusLost

    private void jBlinkFrequenzKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBlinkFrequenzKeyReleased
        if( evt.getKeyCode() == KeyEvent.VK_ENTER ) {
            transferFocus();
        }
    }//GEN-LAST:event_jBlinkFrequenzKeyReleased

    private void jMM_Addr_2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jMM_Addr_2KeyReleased
        if( evt.getKeyCode() == KeyEvent.VK_ENTER ) {
            transferFocus();
        }
    }//GEN-LAST:event_jMM_Addr_2KeyReleased

    private void jCV_InhaltKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCV_InhaltKeyReleased
        if( evt.getKeyCode() == KeyEvent.VK_ENTER ) {
            transferFocus();
        }
    }//GEN-LAST:event_jCV_InhaltKeyReleased

    private void jDecoderAdresseKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDecoderAdresseKeyReleased
        if( evt.getKeyCode() == KeyEvent.VK_ENTER ) {
            transferFocus();
        }
    }//GEN-LAST:event_jDecoderAdresseKeyReleased

    private void jDecoderAdresse1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDecoderAdresse1KeyReleased
        if( evt.getKeyCode() == KeyEvent.VK_ENTER ) {
            transferFocus();
        }
    }//GEN-LAST:event_jDecoderAdresse1KeyReleased

    private void jF12_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF12_3ActionPerformed
        if(jF12_3.isSelected()) {
            CV[1][46] |= 4;
        } else {
            CV[1][46] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+46 );
    }//GEN-LAST:event_jF12_3ActionPerformed

    private void jFL_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFL_3ActionPerformed
        if(jFL_3.isSelected()) {
            CV[1][33] |= 4;
        } else {
            CV[1][33] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+33 );
    }//GEN-LAST:event_jFL_3ActionPerformed

    private void jFR_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFR_3ActionPerformed
        if(jFR_3.isSelected()) {
            CV[1][34] |= 4;
        } else {
            CV[1][34] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+34 );
    }//GEN-LAST:event_jFR_3ActionPerformed

    private void jF1_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF1_3ActionPerformed
        if(jF1_3.isSelected()) {
            CV[1][35] |= 4;
        } else {
            CV[1][35] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+35 );
    }//GEN-LAST:event_jF1_3ActionPerformed

    private void jF2_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF2_3ActionPerformed
        if(jF2_3.isSelected()) {
            CV[1][36] |= 4;
        } else {
            CV[1][36] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+36 );
    }//GEN-LAST:event_jF2_3ActionPerformed

    private void jF3_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF3_3ActionPerformed
        if(jF3_3.isSelected()) {
            CV[1][37] |= 4;
        } else {
            CV[1][37] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+37 );
    }//GEN-LAST:event_jF3_3ActionPerformed

    private void jF4_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF4_3ActionPerformed
        if(jF4_3.isSelected()) {
            CV[1][38] |= 4;
        } else {
            CV[1][38] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+38 );
    }//GEN-LAST:event_jF4_3ActionPerformed

    private void jF5_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF5_3ActionPerformed
        if(jF5_3.isSelected()) {
            CV[1][39] |= 4;
        } else {
            CV[1][39] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+39 );
    }//GEN-LAST:event_jF5_3ActionPerformed

    private void jF6_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF6_3ActionPerformed
        if(jF6_3.isSelected()) {
            CV[1][40] |= 4;
        } else {
            CV[1][40] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+40 );
    }//GEN-LAST:event_jF6_3ActionPerformed

    private void jF7_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF7_3ActionPerformed
        if(jF7_3.isSelected()) {
            CV[1][41] |= 4;
        } else {
            CV[1][41] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+41 );
    }//GEN-LAST:event_jF7_3ActionPerformed

    private void jF8_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF8_3ActionPerformed
        if(jF8_3.isSelected()) {
            CV[1][42] |= 4;
        } else {
            CV[1][42] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+42 );
    }//GEN-LAST:event_jF8_3ActionPerformed

    private void jF9_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF9_3ActionPerformed
        if(jF9_3.isSelected()) {
            CV[1][43] |= 4;
        } else {
            CV[1][43] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+43 );
    }//GEN-LAST:event_jF9_3ActionPerformed

    private void jF10_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF10_3ActionPerformed
        if(jF10_3.isSelected()) {
            CV[1][44] |= 4;
        } else {
            CV[1][44] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+44 );
    }//GEN-LAST:event_jF10_3ActionPerformed

    private void jF11_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF11_3ActionPerformed
        if(jF11_3.isSelected()) {
            CV[1][45] |= 4;
        } else {
            CV[1][45] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+45 );
    }//GEN-LAST:event_jF11_3ActionPerformed

    private void jAltDim3einActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAltDim3einActionPerformed
        CV[1][116] |= 4;
        jCV_Inhalt.setText("" + CV[1][116]);
        jCV_Inhalt.setText("" + CV[1][116]);
    }//GEN-LAST:event_jAltDim3einActionPerformed

    private void jAltDimmen3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jAltDimmen3FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+119 );
    }//GEN-LAST:event_jAltDimmen3FocusGained

    private void jAltDimmen3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jAltDimmen3FocusLost
        int cvValue = CVNavi.checkTextField( this, jAltDimmen3, 0, 15, 15, true );
        CV[1][119] = (CV[1][119] & 0xF0) + cvValue;
        jCV_Anzeige.setSelectedItem( "CV#"+119 );
    }//GEN-LAST:event_jAltDimmen3FocusLost

    private void jAltDimmen3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jAltDimmen3KeyReleased
        if( evt.getKeyCode() == KeyEvent.VK_ENTER ) {
            transferFocus();
        }
    }//GEN-LAST:event_jAltDimmen3KeyReleased

    private void jAltDimmenFS3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jAltDimmenFS3FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+113 );
    }//GEN-LAST:event_jAltDimmenFS3FocusGained

    private void jAltDimmenFS3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jAltDimmenFS3FocusLost
        // nicht editierbar
    }//GEN-LAST:event_jAltDimmenFS3FocusLost

    private void jDimmen3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmen3FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+63 );
    }//GEN-LAST:event_jDimmen3FocusGained

    private void jDimmen3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmen3FocusLost
        int cvValue = CVNavi.checkTextField( this, jDimmen3, 0, 15, 15, true );
        CV[1][63] = (CV[1][63] & 0xF0) + cvValue;
        jCV_Anzeige.setSelectedItem( "CV#"+63 );
    }//GEN-LAST:event_jDimmen3FocusLost

    private void jDimmen3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDimmen3KeyReleased
        if( evt.getKeyCode() == KeyEvent.VK_ENTER ) {
            transferFocus();
        }
    }//GEN-LAST:event_jDimmen3KeyReleased

    private void jAltDim3ausActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAltDim3ausActionPerformed
        CV[1][116] &= ~4;
        jCV_Inhalt.setText("" + CV[1][116]);
        jCV_Inhalt.setText("" + CV[1][116]);
    }//GEN-LAST:event_jAltDim3ausActionPerformed

    private void jAltDimmenFS3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jAltDimmenFS3KeyReleased
        if( evt.getKeyCode() == KeyEvent.VK_ENTER ) {
            transferFocus();
        }
    }//GEN-LAST:event_jAltDimmenFS3KeyReleased

    private void jEffekteComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jEffekteComponentShown
        jCV_Anzeige.setSelectedItem( "CV#"+53 );
        jTast1.setText( "" + ((CV[1][53] & 0xF0) >> 4));
        jTast2.setText( "" + ((CV[1][54] & 0xF0) >> 4));
        jTast3.setText( "" + ((CV[1][55] & 0xF0) >> 4));

        if((CV[1][53] & 8) == 8) {
            jInvertBlink1.setSelected(true);
        } else {
            jInvertBlink1.setSelected(false);
        }
        if((CV[1][54] & 8) == 8) {
            jInvertBlink2.setSelected(true);
        } else {
            jInvertBlink2.setSelected(false);
        }
        if((CV[1][55] & 8) == 8) {
            jInvertBlink3.setSelected(true);
        } else {
            jInvertBlink3.setSelected(false);
        }

        if((CV[1][53] & 1) == 1) {
            jVor1.setSelected(false);
        } else {
            jVor1.setSelected(true);
        }
        if((CV[1][53] & 2) == 2) {
            jRueck1.setSelected(false);
        } else {
            jRueck1.setSelected(true);
        }

        if((CV[1][54] & 1) == 1) {
            jVor2.setSelected(false);
        } else {
            jVor2.setSelected(true);
        }
        if((CV[1][54] & 2) == 2) {
            jRueck2.setSelected(false);
        } else {
            jRueck2.setSelected(true);
        }
        
        if((CV[1][55] & 1) == 1) {
            jVor3.setSelected(false);
        } else {
            jVor3.setSelected(true);
        }
        if((CV[1][55] & 2) == 2) {
            jRueck3.setSelected(false);
        } else {
            jRueck3.setSelected(true);
        }
    }//GEN-LAST:event_jEffekteComponentShown

    private void jInvertBlink3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jInvertBlink3ActionPerformed
        if(jInvertBlink3.isSelected())
        CV[1][55] |= 8;
        else
        CV[1][55] &= ~8;
        jCV_Anzeige.setSelectedItem( "CV#"+55 );
    }//GEN-LAST:event_jInvertBlink3ActionPerformed

    private void jRueck3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRueck3ActionPerformed
        if(!jRueck3.isSelected()) {
            CV[1][55] |= 2;
        } else {
            CV[1][55] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+55 );
    }//GEN-LAST:event_jRueck3ActionPerformed

    private void jVor3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jVor3ActionPerformed
        if(!jVor3.isSelected()) {
            CV[1][55] |= 1;
        } else {
            CV[1][55] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+55 );
    }//GEN-LAST:event_jVor3ActionPerformed

    private void jTast3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTast3KeyReleased
        if( evt.getKeyCode() == KeyEvent.VK_ENTER ) {
            transferFocus();
        }
    }//GEN-LAST:event_jTast3KeyReleased

    private void jTast3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTast3FocusLost
        int cvValue = CVNavi.checkTextField( this, jTast3, 0, 15, 15, true );
        CV[1][53] = (CV[1][55] & 0x0F) + cvValue*16;
        jCV_Anzeige.setSelectedItem( "CV#"+55 );
    }//GEN-LAST:event_jTast3FocusLost

    private void jTast3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTast3FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+55 );
    }//GEN-LAST:event_jTast3FocusGained

    private void jInvertBlink2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jInvertBlink2ActionPerformed
        if(jInvertBlink2.isSelected())
        CV[1][54] |= 8;
        else
        CV[1][54] &= ~8;
        jCV_Anzeige.setSelectedItem( "CV#"+54 );
    }//GEN-LAST:event_jInvertBlink2ActionPerformed

    private void jInvertBlink1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jInvertBlink1ActionPerformed
        if(jInvertBlink1.isSelected())
        CV[1][53] |= 8;
        else
        CV[1][53] &= ~8;
        jCV_Anzeige.setSelectedItem( "CV#"+53 );
    }//GEN-LAST:event_jInvertBlink1ActionPerformed

    private void jVor2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jVor2ActionPerformed
        if(!jVor2.isSelected()) {
            CV[1][54] |= 1;
        } else {
            CV[1][54] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+54 );
    }//GEN-LAST:event_jVor2ActionPerformed

    private void jRueck2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRueck2ActionPerformed
        if(!jRueck2.isSelected()) {
            CV[1][54] |= 2;
        } else {
            CV[1][54] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+54 );
    }//GEN-LAST:event_jRueck2ActionPerformed

    private void jTast2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTast2KeyReleased
        if( evt.getKeyCode() == KeyEvent.VK_ENTER ) {
            transferFocus();
        }
    }//GEN-LAST:event_jTast2KeyReleased

    private void jTast2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTast2FocusLost
        int cvValue = CVNavi.checkTextField( this, jTast2, 0, 15, 15, true );
        CV[1][54] = (CV[1][54] & 0x0F) + cvValue*16;
        jCV_Anzeige.setSelectedItem( "CV#"+54 );
    }//GEN-LAST:event_jTast2FocusLost

    private void jTast2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTast2FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+54 );
    }//GEN-LAST:event_jTast2FocusGained

    private void jTast2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTast2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTast2ActionPerformed

    private void jTast1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTast1KeyReleased
        if( evt.getKeyCode() == KeyEvent.VK_ENTER ) {
            transferFocus();
        }
    }//GEN-LAST:event_jTast1KeyReleased

    private void jTast1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTast1FocusLost
        int cvValue = CVNavi.checkTextField( this, jTast1, 0, 15, 15, true );
        CV[1][53] = (CV[1][53] & 0x0F) + cvValue*16;
        jCV_Anzeige.setSelectedItem( "CV#"+53 );
    }//GEN-LAST:event_jTast1FocusLost

    private void jTast1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTast1FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+53 );
    }//GEN-LAST:event_jTast1FocusGained

    private void jRueck1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRueck1ActionPerformed
        if(!jRueck1.isSelected()) {
            CV[1][53] |= 2;
        } else {
            CV[1][53] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+53 );
    }//GEN-LAST:event_jRueck1ActionPerformed

    private void jVor1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jVor1ActionPerformed
        if(!jVor1.isSelected()) {
            CV[1][53] |= 1;
        } else {
            CV[1][53] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+53 );
    }//GEN-LAST:event_jVor1ActionPerformed

    private void jRL_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRL_3ActionPerformed
        if(jRL_3.isSelected()) {
            CV[1][115] |= 4;
        } else {
            CV[1][115] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+115 );
    }//GEN-LAST:event_jRL_3ActionPerformed

    private void jAnalogRW_WActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAnalogRW_WActionPerformed
        CV[1][12] = 0;
        jCV_Anzeige.setSelectedItem( "CV#"+12 );
    }//GEN-LAST:event_jAnalogRW_WActionPerformed

    private void jAnalogRW_GActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAnalogRW_GActionPerformed
        CV[1][12] = 1;
        jCV_Anzeige.setSelectedItem( "CV#"+12 );
    }//GEN-LAST:event_jAnalogRW_GActionPerformed

    private void setMotorTyp() {
        int iMotorTyp = jMotorListe.getSelectedIndex();
        switch(iMotorTyp)
        {
            case 0:     //HLA
                switch(CVNavi.Decoder)
                {
                    case c.LD_G30: //LD-G-30
                    case c.LD_G32: //LD-G-32
                    case c.LD_G32_2: //LD-G-32
                        jKp.setText("150");
                        jKi.setText("80");
                        jKd.setText("90");
                        jMotorArt.setText("3");
                        CV[1][50] = 150;
                        CV[1][51] = 80;
                        CV[1][52] = 90;
                        CV[1][124] = 3;
                        break;

                }
                break;

            case 1:     //Hamo
                switch(CVNavi.Decoder)
                {
                    case c.LD_G30: //LD-G-30
                    case c.LD_G32: //LD-G-32
                    case c.LD_G32_2: //LD-G-32
                        jKp.setText("90");
                        jKi.setText("55");
                        jKd.setText("60");
                        jMotorArt.setText("5");
                        CV[1][50] = 90;
                        CV[1][51] = 55;
                        CV[1][52] = 60;
                        CV[1][124] = 5;
                        break;
                }
                break;

            case 2:     //Roco
                switch(CVNavi.Decoder)
                {
                    case c.LD_G30: //LD-G-30
                    case c.LD_G32: //LD-G-32
                    case c.LD_G32_2: //LD-G-32
                        jKp.setText("95");
                        jKi.setText("80");
                        jKd.setText("70");
                        jMotorArt.setText("3");
                        CV[1][50] = 95;
                        CV[1][51] = 80;
                        CV[1][52] = 70;
                        CV[1][124] = 3;
                        break;
                }
                break;

            case 3:     //Fleischmann
                switch(CVNavi.Decoder)
                {
                    case c.LD_G30: //LD-G-30
                    case c.LD_G32: //LD-G-32
                    case c.LD_G32_2: //LD-G-32
                        jKp.setText("90");
                        jKi.setText("80");
                        jKd.setText("90");
                        jMotorArt.setText("4");
                        CV[1][50] = 90;
                        CV[1][51] = 80;
                        CV[1][52] = 90;
                        CV[1][124] = 4;
                        break;
                }
                break;

            case 4:     //Piko
                switch(CVNavi.Decoder)
                {
                    case c.LD_G30: //LD-G-30
                    case c.LD_G32: //LD-G-32
                    case c.LD_G32_2: //LD-G-32
                        jKp.setText("90");
                        jKi.setText("70");
                        jKd.setText("80");
                        jMotorArt.setText("4");
                        CV[1][50] = 90;
                        CV[1][51] = 70;
                        CV[1][52] = 80;
                        CV[1][124] = 4;
                        break;
                }
                break;

            case 5:     //Brawa
                switch(CVNavi.Decoder)
                {
                    case c.LD_G30: //LD-G-30
                    case c.LD_G32: //LD-G-32
                    case c.LD_G32_2: //LD-G-32
                        jKp.setText("90");
                        jKi.setText("70");
                        jKd.setText("90");
                        jMotorArt.setText("3");
                        CV[1][50] = 90;
                        CV[1][51] = 70;
                        CV[1][52] = 90;
                        CV[1][124] = 3;
                        break;
                }
                break;

            case 6:     //Gützold
                switch(CVNavi.Decoder)
                {
                    case c.LD_G30: //LD-G-30
                    case c.LD_G32: //LD-G-32
                    case c.LD_G32_2: //LD-G-32
                        jKp.setText("180");
                        jKi.setText("60");
                        jKd.setText("90");
                        jMotorArt.setText("3");
                        CV[1][50] = 180;
                        CV[1][51] = 60;
                        CV[1][52] = 90;
                        CV[1][124] = 3;
                        break;
                }
                break;

            case 7:     //Mehano
                switch(CVNavi.Decoder)
                {
                    case c.LD_G30: //LD-G-30
                    case c.LD_G32: //LD-G-32
                    case c.LD_G32_2: //LD-G-32
                        jKp.setText("180");
                        jKi.setText("70");
                        jKd.setText("90");
                        jMotorArt.setText("2");
                        CV[1][50] = 180;
                        CV[1][51] = 70;
                        CV[1][52] = 90;
                        CV[1][124] = 2;
                        break;
                }
                break;

            case 8:     //Trix
                switch(CVNavi.Decoder)
                {
                    case c.LD_G30: //LD-G-30
                    case c.LD_G32: //LD-G-32
                    case c.LD_G32_2: //LD-G-32
                        jKp.setText("140");
                        jKi.setText("70");
                        jKd.setText("90");
                        jMotorArt.setText("3");
                        CV[1][50] = 140;
                        CV[1][51] = 70;
                        CV[1][52] = 90;
                        CV[1][124] = 3;
                        break;
                }
                break;

            case 9:     //Arnold
                switch(CVNavi.Decoder)
                {
                    case c.LD_G30: //LD-G-30
                    case c.LD_G32: //LD-G-32
                    case c.LD_G32_2: //LD-G-32
                        jKp.setText("100");
                        jKi.setText("80");
                        jKd.setText("90");
                        jMotorArt.setText("3");
                        CV[1][50] = 100;
                        CV[1][51] = 80;
                        CV[1][52] = 90;
                        CV[1][124] = 3;
                        break;
                }
                break;

        }
    }

    void filfilCVs() {
        Boolean b ;
        String[] keys = { "LD-G-30", "LD-W-32",  "LD-G-32", "LD-W-32_2",  "LD-G-32_2" };
        b = parseString2CVs.convertString2CV( ReturnString, keys, CV, jComment, CVNavi );
    }

    void updateTabs() {
        int idx = jDecodereigenschaften.getSelectedIndex();

        for( int i = 0 ; i < jDecodereigenschaften.getComponentCount() ; i ++ ) {
            jDecodereigenschaften.setSelectedIndex(i);
        }

        jDecodereigenschaften.setSelectedIndex(idx);
    }

    private int getCVfromIndexString( JComboBox jCB, String prefix) {
        int CV = 0;
        Object oSel = jCB.getSelectedItem();
        String sSel = "" + oSel ;
        if( sSel.startsWith(prefix)) {
            String sT = sSel.substring(prefix.length()) ;
            try {
                CV = Integer.parseInt(sT);
            } catch (NumberFormatException numberFormatException) {
                CV = -1;
            }
        }
        return CV;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.ButtonGroup buttonGroup6;
    private javax.swing.ButtonGroup buttonGroup7;
    private javax.swing.JRadioButton j480Hz;
    private javax.swing.JRadioButton j80Hz;
    private javax.swing.JCheckBox jAB_F3;
    private javax.swing.JCheckBox jAB_F4;
    private javax.swing.JButton jAbbrechen;
    private javax.swing.JRadioButton jAltDim1aus;
    private javax.swing.JRadioButton jAltDim1ein;
    private javax.swing.JRadioButton jAltDim2aus;
    private javax.swing.JRadioButton jAltDim2ein;
    private javax.swing.JRadioButton jAltDim3aus;
    private javax.swing.JRadioButton jAltDim3ein;
    private javax.swing.JTextField jAltDimmen1;
    private javax.swing.JTextField jAltDimmen2;
    private javax.swing.JTextField jAltDimmen3;
    private javax.swing.JTextField jAltDimmenFS1;
    private javax.swing.JTextField jAltDimmenFS2;
    private javax.swing.JTextField jAltDimmenFS3;
    private javax.swing.JCheckBox jAltKennlinie;
    private javax.swing.JPanel jAnalog;
    private javax.swing.JCheckBox jAnalog1;
    private javax.swing.JRadioButton jAnalogRW_G;
    private javax.swing.JRadioButton jAnalogRW_W;
    private javax.swing.JTextField jAnfahrGeschw;
    private javax.swing.JTextField jAnfahrKick;
    private javax.swing.JTextField jAnfahrVerz;
    private javax.swing.JLabel jBild;
    private javax.swing.JTextField jBlinkFrequenz;
    private javax.swing.JTextField jBremsVerz;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jCV117_CV9_Text;
    private javax.swing.JPanel jCV29;
    private javax.swing.JComboBox jCV_Anzeige;
    private javax.swing.JTextField jCV_Inhalt;
    private javax.swing.JButton jCV_LesenSchreiben;
    private javax.swing.JTextArea jComment;
    private javax.swing.JButton jCustom1laden;
    private javax.swing.JButton jCustom1sichern;
    private javax.swing.JTextField jDecoderAdresse;
    private javax.swing.JTextField jDecoderAdresse1;
    private javax.swing.JTabbedPane jDecodereigenschaften;
    private javax.swing.JPanel jDimmen;
    private javax.swing.JTextField jDimmen1;
    private javax.swing.JTextField jDimmen2;
    private javax.swing.JTextField jDimmen3;
    private javax.swing.JToggleButton jDirekteingabe;
    private javax.swing.JPanel jEffekte;
    private javax.swing.JCheckBox jF1;
    private javax.swing.JCheckBox jF10_1;
    private javax.swing.JCheckBox jF10_2;
    private javax.swing.JCheckBox jF10_3;
    private javax.swing.JCheckBox jF11_1;
    private javax.swing.JCheckBox jF11_2;
    private javax.swing.JCheckBox jF11_3;
    private javax.swing.JCheckBox jF12_1;
    private javax.swing.JCheckBox jF12_2;
    private javax.swing.JCheckBox jF12_3;
    private javax.swing.JCheckBox jF1_1;
    private javax.swing.JCheckBox jF1_2;
    private javax.swing.JCheckBox jF1_3;
    private javax.swing.JCheckBox jF2;
    private javax.swing.JCheckBox jF2_1;
    private javax.swing.JCheckBox jF2_2;
    private javax.swing.JCheckBox jF2_3;
    private javax.swing.JCheckBox jF3;
    private javax.swing.JCheckBox jF3_1;
    private javax.swing.JCheckBox jF3_2;
    private javax.swing.JCheckBox jF3_3;
    private javax.swing.JCheckBox jF4;
    private javax.swing.JCheckBox jF4_1;
    private javax.swing.JCheckBox jF4_2;
    private javax.swing.JCheckBox jF4_3;
    private javax.swing.JCheckBox jF5;
    private javax.swing.JCheckBox jF5_1;
    private javax.swing.JCheckBox jF5_2;
    private javax.swing.JCheckBox jF5_3;
    private javax.swing.JCheckBox jF6;
    private javax.swing.JCheckBox jF6_1;
    private javax.swing.JCheckBox jF6_2;
    private javax.swing.JCheckBox jF6_3;
    private javax.swing.JCheckBox jF7;
    private javax.swing.JCheckBox jF7_1;
    private javax.swing.JCheckBox jF7_2;
    private javax.swing.JCheckBox jF7_3;
    private javax.swing.JCheckBox jF8;
    private javax.swing.JCheckBox jF8_1;
    private javax.swing.JCheckBox jF8_2;
    private javax.swing.JCheckBox jF8_3;
    private javax.swing.JCheckBox jF9_1;
    private javax.swing.JCheckBox jF9_2;
    private javax.swing.JCheckBox jF9_3;
    private javax.swing.JCheckBox jFL_1;
    private javax.swing.JCheckBox jFL_2;
    private javax.swing.JCheckBox jFL_3;
    private javax.swing.JCheckBox jFR_1;
    private javax.swing.JCheckBox jFR_2;
    private javax.swing.JCheckBox jFR_3;
    private javax.swing.JCheckBox jFS;
    private javax.swing.JSlider jFS1;
    private javax.swing.JSlider jFS10;
    private javax.swing.JSlider jFS11;
    private javax.swing.JSlider jFS12;
    private javax.swing.JSlider jFS13;
    private javax.swing.JSlider jFS14;
    private javax.swing.JSlider jFS15;
    private javax.swing.JSlider jFS16;
    private javax.swing.JSlider jFS17;
    private javax.swing.JSlider jFS18;
    private javax.swing.JSlider jFS19;
    private javax.swing.JSlider jFS2;
    private javax.swing.JSlider jFS20;
    private javax.swing.JSlider jFS21;
    private javax.swing.JSlider jFS22;
    private javax.swing.JSlider jFS23;
    private javax.swing.JSlider jFS24;
    private javax.swing.JSlider jFS25;
    private javax.swing.JSlider jFS26;
    private javax.swing.JSlider jFS27;
    private javax.swing.JSlider jFS28;
    private javax.swing.JSlider jFS3;
    private javax.swing.JSlider jFS4;
    private javax.swing.JSlider jFS5;
    private javax.swing.JSlider jFS6;
    private javax.swing.JSlider jFS7;
    private javax.swing.JSlider jFS8;
    private javax.swing.JSlider jFS9;
    private javax.swing.JLabel jFS_Anzeige;
    private javax.swing.JTextField jFS_AnzeigeWert;
    private javax.swing.JPanel jFahreigenschaften;
    private javax.swing.JPanel jFunctionMapping;
    private javax.swing.JCheckBox jInvertBlink1;
    private javax.swing.JCheckBox jInvertBlink2;
    private javax.swing.JCheckBox jInvertBlink3;
    private javax.swing.JTextField jKd;
    private javax.swing.JPanel jKennlinie;
    private javax.swing.JTextField jKi;
    private javax.swing.JPanel jKommentar;
    private javax.swing.JTextField jKp;
    private javax.swing.JRadioButton jKurve1;
    private javax.swing.JRadioButton jKurve2;
    private javax.swing.JRadioButton jKurve3;
    private javax.swing.JRadioButton jKurve4;
    private javax.swing.JRadioButton jKurzeAdr;
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
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JCheckBox jLongAddr;
    private javax.swing.JCheckBox jLongAddr1;
    private javax.swing.JCheckBox jLongAddr2;
    private javax.swing.JTextField jMM_Addr_2;
    private javax.swing.JLabel jManID;
    private javax.swing.JTextField jMotorArt;
    private javax.swing.JList jMotorListe;
    private javax.swing.JCheckBox jNegBrems;
    private javax.swing.JButton jOpen;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JCheckBox jPosBrems;
    private javax.swing.JCheckBox jRL_1;
    private javax.swing.JCheckBox jRL_2;
    private javax.swing.JCheckBox jRL_3;
    private javax.swing.JCheckBox jRL_F3;
    private javax.swing.JCheckBox jRL_F4;
    private javax.swing.JCheckBox jR_F1;
    private javax.swing.JCheckBox jR_F2;
    private javax.swing.JCheckBox jR_F3;
    private javax.swing.JCheckBox jR_F4;
    private javax.swing.JCheckBox jRailCom;
    private javax.swing.JPanel jRangieren;
    private javax.swing.JCheckBox jRichtung;
    private javax.swing.JCheckBox jRueck1;
    private javax.swing.JCheckBox jRueck2;
    private javax.swing.JCheckBox jRueck3;
    private javax.swing.JButton jSave;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTextField jTast1;
    private javax.swing.JTextField jTast2;
    private javax.swing.JTextField jTast3;
    private javax.swing.JTextField jUeberlast;
    private javax.swing.JTextField jVMax;
    private javax.swing.JLabel jVersion;
    private javax.swing.JCheckBox jVor1;
    private javax.swing.JCheckBox jVor2;
    private javax.swing.JCheckBox jVor3;
    private javax.swing.JRadioButton jlangeAdr;
    // End of variables declaration//GEN-END:variables

}
