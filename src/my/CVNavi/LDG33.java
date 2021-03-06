/*
 * LDG33.java
 *
 * Created on 18.09.2009, 06:42:22
 *
 * @author Kersten Tams Copyright 2009-2018
 * @author Lothar Roth  Copyright 2012-2018
 *
 */

package my.CVNavi;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Arrays;
import java.util.ResourceBundle;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;

/**
 *
 * @author ktams
 */
public class LDG33 extends javax.swing.JFrame {
    public  CVNavi CVNavi;
    private String CVs;
    public String ReturnString;
    private int CV[][];
    private int c1[];
    private Boolean CV_InhaltCheckInProgress;
    private ResourceBundle bundle;

    /** Creates new form LDG33 */
    public LDG33(CVNavi cvnaviThis) {
        if( cvnaviThis == null ) {
            return;
        }
        CVNavi = cvnaviThis;
        if( CVNavi.frameInstanceDEVICE != null ) {
            CVNavi.frameInstanceDEVICE.toFront();
            CVNavi.frameInstanceDEVICE.repaint();
            return;
        }

        this.CV_InhaltCheckInProgress = false;

        ReturnString = "Tams Elektronik";
        CV = new int[2][127];
        c1 = new int[28];
        for( int i = 0 ; i < c1.length; i++) {
            c1[i] = i*9;
        }
        initComponents();
        bundle = java.util.ResourceBundle.getBundle("my.CVNavi/Bundle");

        jLabel88.setVisible(false);
        jLabel89.setVisible(false);
        jFL_7.setVisible(false);
        jFL_8.setVisible(false);
        jFR_7.setVisible(false);
        jFR_8.setVisible(false);
        jF1_7.setVisible(false);
        jF1_8.setVisible(false);
        jF2_7.setVisible(false);
        jF2_8.setVisible(false);
        jF3_7.setVisible(false);
        jF3_8.setVisible(false);
        jF4_7.setVisible(false);
        jF4_8.setVisible(false);
        jF5_7.setVisible(false);
        jF5_8.setVisible(false);
        jF6_7.setVisible(false);
        jF6_8.setVisible(false);
        jF7_7.setVisible(false);
        jF7_8.setVisible(false);
        jF8_7.setVisible(false);
        jF8_8.setVisible(false);
        jF9_7.setVisible(false);
        jF9_8.setVisible(false);
        jF10_7.setVisible(false);
        jF10_8.setVisible(false);
        jF11_7.setVisible(false);
        jF11_8.setVisible(false);
        jF12_7.setVisible(false);
        jF12_8.setVisible(false);
        jINV_7.setVisible(false);
        jINV_8.setVisible(false);

        ImageIcon II = new ImageIcon(getClass().getResource("/decoder.gif"));
        setIconImage(II.getImage());

        switch(CVNavi.Decoder)
        {
            case c.FD_XL: //FD-XL
                TitledBorder b = (TitledBorder)jPanel1.getBorder();
                b.setTitle("FD-XL");
                jBild.setIcon(new ImageIcon(getClass().getResource("/FD-XL.gif")));
                setTitle( CVNavi.getMenutext( decoderList.FD_XL ).trim() );
                jDecodereigenschaften.remove(jFahreigenschaften);
                jDecodereigenschaften.remove(jKennlinie);
                jAltKennlinie.setEnabled(false);
                jRangieren.remove(jLabel29);
                jRangieren.remove(jLabel74);
                jAbrueckGeschw.setVisible(false);
                jAR_1.setVisible(false);
                jAR_2.setVisible(false);
                jAR_3.setVisible(false);
                jAR_4.setVisible(false);
                jAR_5.setVisible(false);
                jAR_6.setVisible(false);
                jLabel88.setVisible(true);
                jLabel89.setVisible(true);
                jFL_7.setVisible(true);
                jFL_8.setVisible(true);
                jFR_7.setVisible(true);
                jFR_8.setVisible(true);
                jF1_7.setVisible(true);
                jF1_8.setVisible(true);
                jF2_7.setVisible(true);
                jF2_8.setVisible(true);
                jF3_7.setVisible(true);
                jF3_8.setVisible(true);
                jF4_7.setVisible(true);
                jF4_8.setVisible(true);
                jF5_7.setVisible(true);
                jF5_8.setVisible(true);
                jF6_7.setVisible(true);
                jF6_8.setVisible(true);
                jF7_7.setVisible(true);
                jF7_8.setVisible(true);
                jF8_7.setVisible(true);
                jF8_8.setVisible(true);
                jF9_7.setVisible(true);
                jF9_8.setVisible(true);
                jF10_7.setVisible(true);
                jF10_8.setVisible(true);
                jF11_7.setVisible(true);
                jF11_8.setVisible(true);
                jF12_7.setVisible(true);
                jF12_8.setVisible(true);
                jINV_7.setVisible(true);
                jINV_8.setVisible(true);
                break;

            case c.LD_W33: //LD-W-33
                jUeberlast.setEditable(false);
                jMotorArt.setEditable(false);
                jKp.setEditable(false);
                jKi.setEditable(false);
                jKd.setEditable(false);
                jMotorListe.setEnabled(false);
                b = (TitledBorder)jPanel1.getBorder();
                b.setTitle("LD-W-33");
                jBild.setIcon(new ImageIcon(getClass().getResource("/LD-G-33.gif")));
                setTitle( CVNavi.getMenutext( decoderList.LD_W33 ).trim() );
                break;

            case c.LD_G33: //LD-G-33
                b = (TitledBorder)jPanel1.getBorder();
                b.setTitle("LD-G-33");
                jBild.setIcon(new ImageIcon(getClass().getResource("/LD-G-33.gif")));
                setTitle( CVNavi.getMenutext( decoderList.LD_G33 ).trim() );
                break;

            case c.LD_G34: //LD-G-34
                b = (TitledBorder)jPanel1.getBorder();
                b.setTitle("LD-G-34");
                jBild.setIcon(new ImageIcon(getClass().getResource("/LD-G-34.gif")));
                setTitle( CVNavi.getMenutext( decoderList.LD_G34 ).trim() );
                break;
        }

        //---- CV-default-Werte -----
        // Vom Decoder verwendete CVs markieren und mit Default-Werten besetzen
        initCV( 0, 0 ); // reset jCV_Anzeige (clean all entries)

        initCV( 1, 3 );
        initCV( 2, 5); // ZZZ nicht beim FD-XL
        initCV( 3, 16);
        initCV( 4, 8);
        initCV( 5, 0); // ZZZ
        initCV( 7, 10);
        initCV( 8, 62);
        initCV( 9, 0); // ZZZ
        initCV( 12, 0);
        initCV( 13, 0);
        initCV( 17, 192);
        initCV( 18, 255);
        initCV( 19, 0);
        initCV( 27, 0); // ZZZ
        initCV( 29, 14);
        initCV( 33, 1);
        initCV( 34, 2);
        initCV( 35, 4);
        initCV( 36, 8);

        if (CVNavi.Decoder == c.FD_XL) { // FD-XL
            initCV( 37, 16);
            initCV( 38, 32);
            initCV( 39, 64);
            initCV( 40, 128);
        } else {
            initCV( 37, 0);
            initCV( 38, 0);
            initCV( 39, 16);
            initCV( 40, 32);
        }
        initCV( 41, 0);
        initCV( 42, 0);
        initCV( 43, 0);
        initCV( 44, 0);
        initCV( 45, 0);
        initCV( 46, 0);
        initCV( 47, 0); // ZZZ
        initCV( 48, 0); // ZZZ
        initCV( 49, 73);
        initCV( 50, 80); // ZZZ
        initCV( 51, 45); // ZZZ
        initCV( 52, 50); // ZZZ
        initCV( 53, 0);
        initCV( 54, 0);
        initCV( 55, 0);
        initCV( 56, 0);
        initCV( 57, 0);
        initCV( 58, 0);
        initCV( 59, 0);
        initCV( 60, 0);
        initCV( 61, 0);
        initCV( 62, 255);
        initCV( 63, 255);
        initCV( 64, 255);
        initCV( 65, 0); // ZZZ
        for( int i = 0 ; i < 28 ; i++ ) { // Motor Kennlinie
            initCV( 67+i, i*6);
        }
        initCV( 112, 200);
        initCV( 113, 16);
        initCV( 114, 4);
        initCV( 115, 0);
        initCV( 116, 0);
        initCV( 117, 69); // ZZZ
        initCV( 118, 255);
        initCV( 119, 255);
        initCV( 120, 255);
        initCV( 121, 0);
        initCV( 122, 0);
        initCV( 123, 0);
        initCV( 124, 2);
        initCV( 126, 0);
        //---------------------------
        if (CVNavi.Decoder == c.FD_XL) {
            // beim FD_XL nicht vorhandene CVs entfernen
            for (Integer i : Arrays.asList(2,5,9,27,47,48,50,51,52,65,117)) {
                initCV( i, -1 );
            }
            for( int i = 0 ; i < 28 ; i++ ) { // Motor Kennlinie
                initCV( 67+i, -1 );
            }
        }

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
        buttonGroup8 = new javax.swing.ButtonGroup();
        buttonGroup9 = new javax.swing.ButtonGroup();
        buttonGroup10 = new javax.swing.ButtonGroup();
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
        jFL_3 = new javax.swing.JCheckBox();
        jFR_3 = new javax.swing.JCheckBox();
        jLabel63 = new javax.swing.JLabel();
        jF1_3 = new javax.swing.JCheckBox();
        jF2_3 = new javax.swing.JCheckBox();
        jF3_3 = new javax.swing.JCheckBox();
        jF4_3 = new javax.swing.JCheckBox();
        jF5_3 = new javax.swing.JCheckBox();
        jF9_3 = new javax.swing.JCheckBox();
        jF8_3 = new javax.swing.JCheckBox();
        jF7_3 = new javax.swing.JCheckBox();
        jF10_3 = new javax.swing.JCheckBox();
        jF11_3 = new javax.swing.JCheckBox();
        jF6_3 = new javax.swing.JCheckBox();
        jF12_3 = new javax.swing.JCheckBox();
        jF12_4 = new javax.swing.JCheckBox();
        jFL_4 = new javax.swing.JCheckBox();
        jFR_4 = new javax.swing.JCheckBox();
        jF1_4 = new javax.swing.JCheckBox();
        jF2_4 = new javax.swing.JCheckBox();
        jF3_4 = new javax.swing.JCheckBox();
        jF4_4 = new javax.swing.JCheckBox();
        jF5_4 = new javax.swing.JCheckBox();
        jF6_4 = new javax.swing.JCheckBox();
        jF7_4 = new javax.swing.JCheckBox();
        jF8_4 = new javax.swing.JCheckBox();
        jF9_4 = new javax.swing.JCheckBox();
        jF10_4 = new javax.swing.JCheckBox();
        jF11_4 = new javax.swing.JCheckBox();
        jLabel64 = new javax.swing.JLabel();
        jFL_5 = new javax.swing.JCheckBox();
        jFR_5 = new javax.swing.JCheckBox();
        jLabel65 = new javax.swing.JLabel();
        jF1_5 = new javax.swing.JCheckBox();
        jF2_5 = new javax.swing.JCheckBox();
        jF3_5 = new javax.swing.JCheckBox();
        jF4_5 = new javax.swing.JCheckBox();
        jF5_5 = new javax.swing.JCheckBox();
        jF9_5 = new javax.swing.JCheckBox();
        jF8_5 = new javax.swing.JCheckBox();
        jF7_5 = new javax.swing.JCheckBox();
        jF10_5 = new javax.swing.JCheckBox();
        jF11_5 = new javax.swing.JCheckBox();
        jF6_5 = new javax.swing.JCheckBox();
        jF12_5 = new javax.swing.JCheckBox();
        jF12_6 = new javax.swing.JCheckBox();
        jFL_6 = new javax.swing.JCheckBox();
        jFR_6 = new javax.swing.JCheckBox();
        jF1_6 = new javax.swing.JCheckBox();
        jF2_6 = new javax.swing.JCheckBox();
        jF3_6 = new javax.swing.JCheckBox();
        jF4_6 = new javax.swing.JCheckBox();
        jF5_6 = new javax.swing.JCheckBox();
        jF6_6 = new javax.swing.JCheckBox();
        jF7_6 = new javax.swing.JCheckBox();
        jF8_6 = new javax.swing.JCheckBox();
        jF9_6 = new javax.swing.JCheckBox();
        jF10_6 = new javax.swing.JCheckBox();
        jF11_6 = new javax.swing.JCheckBox();
        jLabel66 = new javax.swing.JLabel();
        jFL_7 = new javax.swing.JCheckBox();
        jFR_7 = new javax.swing.JCheckBox();
        jLabel88 = new javax.swing.JLabel();
        jF1_7 = new javax.swing.JCheckBox();
        jF2_7 = new javax.swing.JCheckBox();
        jF3_7 = new javax.swing.JCheckBox();
        jF4_7 = new javax.swing.JCheckBox();
        jF5_7 = new javax.swing.JCheckBox();
        jF9_7 = new javax.swing.JCheckBox();
        jF8_7 = new javax.swing.JCheckBox();
        jF7_7 = new javax.swing.JCheckBox();
        jF10_7 = new javax.swing.JCheckBox();
        jF11_7 = new javax.swing.JCheckBox();
        jF6_7 = new javax.swing.JCheckBox();
        jF12_7 = new javax.swing.JCheckBox();
        jF12_8 = new javax.swing.JCheckBox();
        jFL_8 = new javax.swing.JCheckBox();
        jFR_8 = new javax.swing.JCheckBox();
        jF1_8 = new javax.swing.JCheckBox();
        jF2_8 = new javax.swing.JCheckBox();
        jF3_8 = new javax.swing.JCheckBox();
        jF4_8 = new javax.swing.JCheckBox();
        jF5_8 = new javax.swing.JCheckBox();
        jF6_8 = new javax.swing.JCheckBox();
        jF7_8 = new javax.swing.JCheckBox();
        jF8_8 = new javax.swing.JCheckBox();
        jF9_8 = new javax.swing.JCheckBox();
        jF10_8 = new javax.swing.JCheckBox();
        jF11_8 = new javax.swing.JCheckBox();
        jLabel89 = new javax.swing.JLabel();
        jINV_1 = new javax.swing.JCheckBox();
        jINV_2 = new javax.swing.JCheckBox();
        jLabel91 = new javax.swing.JLabel();
        jINV_3 = new javax.swing.JCheckBox();
        jINV_4 = new javax.swing.JCheckBox();
        jINV_5 = new javax.swing.JCheckBox();
        jINV_6 = new javax.swing.JCheckBox();
        jINV_7 = new javax.swing.JCheckBox();
        jINV_8 = new javax.swing.JCheckBox();
        jDimmen = new javax.swing.JPanel();
        jDimmen1 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jDimmen2 = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel42 = new javax.swing.JLabel();
        jAltDim1ein = new javax.swing.JRadioButton();
        jAltDim2ein = new javax.swing.JRadioButton();
        jAltDim1aus = new javax.swing.JRadioButton();
        jAltDim2aus = new javax.swing.JRadioButton();
        jLabel43 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jAltDimmenFS1 = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        jAltDimmen1 = new javax.swing.JTextField();
        jAltDimmen2 = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jDimmen3 = new javax.swing.JTextField();
        jDimmen4 = new javax.swing.JTextField();
        jDimmen5 = new javax.swing.JTextField();
        jDimmen6 = new javax.swing.JTextField();
        jAltDim3ein = new javax.swing.JRadioButton();
        jAltDim3aus = new javax.swing.JRadioButton();
        jAltDim4ein = new javax.swing.JRadioButton();
        jAltDim4aus = new javax.swing.JRadioButton();
        jAltDim5ein = new javax.swing.JRadioButton();
        jAltDim5aus = new javax.swing.JRadioButton();
        jAltDim6ein = new javax.swing.JRadioButton();
        jAltDim6aus = new javax.swing.JRadioButton();
        jAltDimmen3 = new javax.swing.JTextField();
        jAltDimmen4 = new javax.swing.JTextField();
        jAltDimmen5 = new javax.swing.JTextField();
        jAltDimmen6 = new javax.swing.JTextField();
        jEffekte = new javax.swing.JPanel();
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
        jLabel24 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jTast3 = new javax.swing.JTextField();
        jTast4 = new javax.swing.JTextField();
        jTast5 = new javax.swing.JTextField();
        jTast6 = new javax.swing.JTextField();
        jInvertBlink3 = new javax.swing.JCheckBox();
        jInvertBlink4 = new javax.swing.JCheckBox();
        jInvertBlink5 = new javax.swing.JCheckBox();
        jInvertBlink6 = new javax.swing.JCheckBox();
        jVor3 = new javax.swing.JCheckBox();
        jVor4 = new javax.swing.JCheckBox();
        jVor5 = new javax.swing.JCheckBox();
        jVor6 = new javax.swing.JCheckBox();
        jRueck3 = new javax.swing.JCheckBox();
        jRueck4 = new javax.swing.JCheckBox();
        jRueck5 = new javax.swing.JCheckBox();
        jRueck6 = new javax.swing.JCheckBox();
        jLabel62 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel82 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel83 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        jF0AusF2 = new javax.swing.JCheckBox();
        jF0AusF1 = new javax.swing.JCheckBox();
        jF0AusF3 = new javax.swing.JCheckBox();
        jF0AusF4 = new javax.swing.JCheckBox();
        jF0AusF5 = new javax.swing.JCheckBox();
        jF0AusF6 = new javax.swing.JCheckBox();
        jF0AusF7 = new javax.swing.JCheckBox();
        jF0AusF8 = new javax.swing.JCheckBox();
        jLabel85 = new javax.swing.JLabel();
        jF0AusAUX1 = new javax.swing.JCheckBox();
        jF0AusAUX2 = new javax.swing.JCheckBox();
        jF0AusAUX3 = new javax.swing.JCheckBox();
        jF0AusAUX4 = new javax.swing.JCheckBox();
        jF0AusAUX5 = new javax.swing.JCheckBox();
        jF0AusAUX6 = new javax.swing.JCheckBox();
        jLabel86 = new javax.swing.JLabel();
        jF0AusF1Aus = new javax.swing.JCheckBox();
        jF0AusF2Aus = new javax.swing.JCheckBox();
        jF0AusF3Aus = new javax.swing.JCheckBox();
        jF0AusF4Aus = new javax.swing.JCheckBox();
        jSchleiferumschaltung = new javax.swing.JCheckBox();
        jLabel90 = new javax.swing.JLabel();
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
        jRL_4 = new javax.swing.JCheckBox();
        jRL_5 = new javax.swing.JCheckBox();
        jRL_6 = new javax.swing.JCheckBox();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jAR_1 = new javax.swing.JCheckBox();
        jAR_2 = new javax.swing.JCheckBox();
        jAR_3 = new javax.swing.JCheckBox();
        jAR_4 = new javax.swing.JCheckBox();
        jAR_5 = new javax.swing.JCheckBox();
        jAR_6 = new javax.swing.JCheckBox();
        jLabel74 = new javax.swing.JLabel();
        jAbrueckGeschw = new javax.swing.JTextField();
        jLabel75 = new javax.swing.JLabel();
        jKickZeit1 = new javax.swing.JTextField();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jKickZeit2 = new javax.swing.JTextField();
        jKickZeit3 = new javax.swing.JTextField();
        jLabel78 = new javax.swing.JLabel();
        jKickZeit4 = new javax.swing.JTextField();
        jLabel79 = new javax.swing.JLabel();
        jKickZeit5 = new javax.swing.JTextField();
        jLabel80 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jKickZeit6 = new javax.swing.JTextField();
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
        jLabel87 = new javax.swing.JLabel();
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
        jAnaSpannung_G = new javax.swing.JRadioButton();
        jAnaSpannung_W = new javax.swing.JRadioButton();
        jLabel92 = new javax.swing.JLabel();
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
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, bundle.getString("LDG33.jPanel1.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        jPanel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jCV_Anzeige.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCV_Anzeige.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("LDG33.jCV_Anzeige.border.title"))); // NOI18N
        jCV_Anzeige.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCV_AnzeigeActionPerformed(evt);
            }
        });

        jCV_Inhalt.setEditable(false);
        jCV_Inhalt.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCV_Inhalt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jCV_Inhalt.setText(bundle.getString("LDG33.jCV_Inhalt.text")); // NOI18N
        jCV_Inhalt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jCV_InhaltFocusGained(evt);
            }
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
        jLabel1.setText(bundle.getString("LDG33.jLabel1.text")); // NOI18N

        jDecoderAdresse.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jDecoderAdresse.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jDecoderAdresse.setText(bundle.getString("LDG33.jDecoderAdresse.text")); // NOI18N
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
        jKurzeAdr.setText(bundle.getString("LDG33.jKurzeAdr.text")); // NOI18N
        jKurzeAdr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jKurzeAdrActionPerformed(evt);
            }
        });

        buttonGroup1.add(jlangeAdr);
        jlangeAdr.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jlangeAdr.setText(bundle.getString("LDG33.jlangeAdr.text")); // NOI18N
        jlangeAdr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jlangeAdrActionPerformed(evt);
            }
        });

        jDirekteingabe.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jDirekteingabe.setText(bundle.getString("LDG33.jDirekteingabe.text")); // NOI18N
        jDirekteingabe.setToolTipText(bundle.getString("LDG33.jDirekteingabe.toolTipText")); // NOI18N
        jDirekteingabe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDirekteingabeActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton1.setText(bundle.getString("LDG33.jButton1.text")); // NOI18N
        jButton1.setToolTipText(bundle.getString("LDG33.jButton1.toolTipText")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel27.setText(bundle.getString("LDG33.jLabel27.text")); // NOI18N

        jDecoderAdresse1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jDecoderAdresse1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jDecoderAdresse1.setText(bundle.getString("LDG33.jDecoderAdresse1.text")); // NOI18N
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
        jLabel28.setText(bundle.getString("LDG33.jLabel28.text")); // NOI18N

        jDecodereigenschaften.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jDecodereigenschaften.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jCV29.setToolTipText(bundle.getString("LDG33.jCV29.toolTipText")); // NOI18N
        jCV29.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jCV29ComponentShown(evt);
            }
        });

        jRichtung.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRichtung.setText(bundle.getString("LDG33.jRichtung.text")); // NOI18N
        jRichtung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRichtungActionPerformed(evt);
            }
        });

        jFS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS.setSelected(true);
        jFS.setText(bundle.getString("LDG33.jFS.text")); // NOI18N
        jFS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFSActionPerformed(evt);
            }
        });

        jAnalog1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAnalog1.setSelected(true);
        jAnalog1.setText(bundle.getString("LDG33.jAnalog1.text")); // NOI18N
        jAnalog1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAnalog1ActionPerformed(evt);
            }
        });

        jRailCom.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRailCom.setSelected(true);
        jRailCom.setText(bundle.getString("LDG33.jRailCom.text")); // NOI18N
        jRailCom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRailComActionPerformed(evt);
            }
        });

        jAltKennlinie.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAltKennlinie.setText(bundle.getString("LDG33.jAltKennlinie.text")); // NOI18N
        jAltKennlinie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAltKennlinieActionPerformed(evt);
            }
        });

        jLongAddr.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLongAddr.setText(bundle.getString("LDG33.jLongAddr.text")); // NOI18N
        jLongAddr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jLongAddrActionPerformed(evt);
            }
        });

        jLongAddr1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLongAddr1.setText(bundle.getString("LDG33.jLongAddr1.text")); // NOI18N
        jLongAddr1.setEnabled(false);

        jLongAddr2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLongAddr2.setText(bundle.getString("LDG33.jLongAddr2.text")); // NOI18N
        jLongAddr2.setEnabled(false);

        jLabel38.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel38.setText(bundle.getString("LDG33.jLabel38.text")); // NOI18N

        jLabel39.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel39.setText(bundle.getString("LDG33.jLabel39.text")); // NOI18N

        jLabel40.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel40.setText(bundle.getString("LDG33.jLabel40.text")); // NOI18N

        jLabel41.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel41.setText(bundle.getString("LDG33.jLabel41.text")); // NOI18N

        jBlinkFrequenz.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jBlinkFrequenz.setText(bundle.getString("LDG33.jBlinkFrequenz.text")); // NOI18N
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
        jMM_Addr_2.setText(bundle.getString("LDG33.jMM_Addr_2.text")); // NOI18N
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
        jVersion.setText(bundle.getString("LDG33.jVersion.text")); // NOI18N

        jManID.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jManID.setText(bundle.getString("LDG33.jManID.text")); // NOI18N

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 140, Short.MAX_VALUE)
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
                .addGap(21, 21, 21)
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
                .addContainerGap(62, Short.MAX_VALUE))
        );

        jDecodereigenschaften.addTab(bundle.getString("LDG33.jCV29.TabConstraints.tabTitle"), jCV29); // NOI18N

        jFunctionMapping.setToolTipText(bundle.getString("LDG33.jFunctionMapping.toolTipText")); // NOI18N
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
        jFunctionMapping.add(jFL_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, -1));

        jFR_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFR_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFR_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jFR_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText(bundle.getString("LDG33.jLabel4.text")); // NOI18N
        jFunctionMapping.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText(bundle.getString("LDG33.jLabel5.text")); // NOI18N
        jFunctionMapping.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 10, -1));

        jF1_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF1_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF1_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF1_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 40, -1, -1));

        jF2_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF2_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF2_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF2_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, -1, -1));

        jF3_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF3_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF3_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF3_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, -1, -1));

        jF4_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF4_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF4_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF4_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 40, -1, -1));

        jF5_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF5_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF5_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF5_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 40, -1, -1));

        jF9_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF9_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF9_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF9_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 40, -1, -1));

        jF8_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF8_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF8_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF8_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, -1, -1));

        jF7_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF7_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF7_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF7_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 40, -1, -1));

        jF10_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF10_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF10_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF10_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 40, -1, -1));

        jF11_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF11_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF11_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF11_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 40, -1, -1));

        jF6_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF6_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF6_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF6_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 40, -1, -1));

        jF12_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF12_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF12_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF12_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 40, -1, -1));

        jF12_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF12_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF12_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF12_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 70, -1, -1));

        jFL_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFL_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFL_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jFL_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        jFR_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFR_2.setSelected(true);
        jFR_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFR_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jFR_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, -1));

        jF1_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF1_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF1_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF1_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, -1, -1));

        jF2_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF2_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF2_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF2_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, -1, -1));

        jF3_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF3_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF3_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF3_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, -1, -1));

        jF4_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF4_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF4_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF4_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, -1, -1));

        jF5_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF5_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF5_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF5_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, -1, -1));

        jF6_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF6_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF6_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF6_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 70, -1, -1));

        jF7_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF7_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF7_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF7_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 70, -1, -1));

        jF8_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF8_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF8_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF8_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 70, -1, -1));

        jF9_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF9_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF9_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF9_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 70, -1, -1));

        jLabel6.setText(bundle.getString("LDG33.jLabel6.text")); // NOI18N
        jFunctionMapping.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        jLabel7.setText(bundle.getString("LDG33.jLabel7.text")); // NOI18N
        jFunctionMapping.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));

        jLabel8.setText(bundle.getString("LDG33.jLabel8.text")); // NOI18N
        jFunctionMapping.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, -1, -1));

        jLabel9.setText(bundle.getString("LDG33.jLabel9.text")); // NOI18N
        jFunctionMapping.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 30, -1, -1));

        jLabel10.setText(bundle.getString("LDG33.jLabel10.text")); // NOI18N
        jFunctionMapping.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, -1, -1));

        jLabel11.setText(bundle.getString("LDG33.jLabel11.text")); // NOI18N
        jFunctionMapping.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, 20, -1));

        jLabel12.setText(bundle.getString("LDG33.jLabel12.text")); // NOI18N
        jFunctionMapping.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 30, -1, -1));

        jLabel13.setText(bundle.getString("LDG33.jLabel13.text")); // NOI18N
        jFunctionMapping.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 30, -1, -1));

        jLabel14.setText(bundle.getString("LDG33.jLabel14.text")); // NOI18N
        jFunctionMapping.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 30, -1, -1));

        jLabel15.setText(bundle.getString("LDG33.jLabel15.text")); // NOI18N
        jFunctionMapping.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, -1, -1));

        jLabel16.setText(bundle.getString("LDG33.jLabel16.text")); // NOI18N
        jFunctionMapping.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 30, -1, -1));

        jLabel17.setText(bundle.getString("LDG33.jLabel17.text")); // NOI18N
        jFunctionMapping.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 30, -1, -1));

        jLabel18.setText(bundle.getString("LDG33.jLabel18.text")); // NOI18N
        jFunctionMapping.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 30, -1, -1));

        jLabel19.setText(bundle.getString("LDG33.jLabel19.text")); // NOI18N
        jFunctionMapping.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 30, -1, -1));

        jF10_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF10_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF10_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF10_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 70, -1, -1));

        jF11_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF11_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF11_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF11_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 70, -1, -1));

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel20.setText(bundle.getString("LDG33.jLabel20.text")); // NOI18N
        jFunctionMapping.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText(bundle.getString("LDG33.jLabel3.text")); // NOI18N
        jFunctionMapping.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 290, -1, -1));

        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel33.setText(bundle.getString("LDG33.jLabel33.text")); // NOI18N
        jFunctionMapping.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, -1, -1));

        jFL_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFL_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFL_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jFL_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));

        jFR_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFR_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFR_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jFR_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, -1, -1));

        jLabel63.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel63.setText(bundle.getString("LDG33.jLabel63.text")); // NOI18N
        jFunctionMapping.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 10, -1));

        jF1_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF1_3.setSelected(true);
        jF1_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF1_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF1_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, -1, -1));

        jF2_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF2_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF2_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF2_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, -1, -1));

        jF3_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF3_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF3_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF3_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, -1, -1));

        jF4_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF4_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF4_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF4_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, -1, -1));

        jF5_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF5_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF5_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF5_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 100, -1, -1));

        jF9_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF9_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF9_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF9_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 100, -1, -1));

        jF8_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF8_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF8_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF8_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 100, -1, -1));

        jF7_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF7_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF7_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF7_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 100, -1, -1));

        jF10_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF10_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF10_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF10_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 100, -1, -1));

        jF11_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF11_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF11_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF11_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 100, -1, -1));

        jF6_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF6_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF6_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF6_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 100, -1, -1));

        jF12_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF12_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF12_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF12_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 100, -1, -1));

        jF12_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF12_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF12_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF12_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 130, -1, -1));

        jFL_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFL_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFL_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jFL_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, -1));

        jFR_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFR_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFR_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jFR_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, -1, -1));

        jF1_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF1_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF1_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF1_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 130, -1, -1));

        jF2_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF2_4.setSelected(true);
        jF2_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF2_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF2_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 130, -1, -1));

        jF3_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF3_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF3_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF3_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, -1, -1));

        jF4_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF4_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF4_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF4_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 130, -1, -1));

        jF5_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF5_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF5_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF5_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 130, -1, -1));

        jF6_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF6_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF6_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF6_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 130, -1, -1));

        jF7_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF7_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF7_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF7_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 130, -1, -1));

        jF8_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF8_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF8_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF8_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 130, -1, -1));

        jF9_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF9_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF9_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF9_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 130, -1, -1));

        jF10_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF10_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF10_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF10_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 130, -1, -1));

        jF11_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF11_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF11_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF11_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 130, -1, -1));

        jLabel64.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel64.setText(bundle.getString("LDG33.jLabel64.text")); // NOI18N
        jFunctionMapping.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

        jFL_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFL_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFL_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jFL_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));

        jFR_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFR_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFR_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jFR_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, -1, -1));

        jLabel65.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel65.setText(bundle.getString("LDG33.jLabel65.text")); // NOI18N
        jFunctionMapping.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 10, -1));

        jF1_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF1_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF1_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF1_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 160, -1, -1));

        jF2_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF2_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF2_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF2_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 160, -1, -1));

        jF3_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF3_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF3_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF3_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 160, -1, -1));

        jF4_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF4_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF4_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF4_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 160, -1, -1));

        jF5_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF5_5.setSelected(true);
        jF5_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF5_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF5_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 160, -1, -1));

        jF9_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF9_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF9_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF9_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 160, -1, -1));

        jF8_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF8_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF8_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF8_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 160, -1, -1));

        jF7_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF7_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF7_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF7_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 160, -1, -1));

        jF10_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF10_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF10_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF10_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 160, -1, -1));

        jF11_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF11_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF11_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF11_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 160, -1, -1));

        jF6_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF6_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF6_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF6_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 160, -1, -1));

        jF12_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF12_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF12_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF12_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 160, -1, -1));

        jF12_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF12_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF12_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF12_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 190, -1, -1));

        jFL_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFL_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFL_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jFL_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, -1, -1));

        jFR_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFR_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFR_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jFR_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, -1, -1));

        jF1_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF1_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF1_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF1_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 190, -1, -1));

        jF2_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF2_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF2_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF2_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 190, -1, -1));

        jF3_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF3_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF3_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF3_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 190, -1, -1));

        jF4_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF4_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF4_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF4_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 190, -1, -1));

        jF5_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF5_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF5_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF5_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 190, -1, -1));

        jF6_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF6_6.setSelected(true);
        jF6_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF6_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF6_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 190, -1, -1));

        jF7_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF7_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF7_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF7_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 190, -1, -1));

        jF8_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF8_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF8_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF8_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 190, -1, -1));

        jF9_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF9_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF9_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF9_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 190, -1, -1));

        jF10_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF10_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF10_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF10_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 190, -1, -1));

        jF11_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF11_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF11_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF11_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 190, -1, -1));

        jLabel66.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel66.setText(bundle.getString("LDG33.jLabel66.text")); // NOI18N
        jFunctionMapping.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, -1));

        jFL_7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFL_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFL_7ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jFL_7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, -1, -1));

        jFR_7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFR_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFR_7ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jFR_7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, -1, -1));

        jLabel88.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel88.setText(bundle.getString("LDG33.jLabel88.text")); // NOI18N
        jFunctionMapping.add(jLabel88, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 10, -1));

        jF1_7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF1_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF1_7ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF1_7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 220, -1, -1));

        jF2_7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF2_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF2_7ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF2_7, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 220, -1, -1));

        jF3_7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF3_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF3_7ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF3_7, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 220, -1, -1));

        jF4_7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF4_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF4_7ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF4_7, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 220, -1, -1));

        jF5_7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF5_7.setSelected(true);
        jF5_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF5_7ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF5_7, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 220, -1, -1));

        jF9_7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF9_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF9_7ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF9_7, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 220, -1, -1));

        jF8_7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF8_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF8_7ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF8_7, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 220, -1, -1));

        jF7_7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF7_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF7_7ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF7_7, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 220, -1, -1));

        jF10_7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF10_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF10_7ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF10_7, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 220, -1, -1));

        jF11_7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF11_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF11_7ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF11_7, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 220, -1, -1));

        jF6_7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF6_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF6_7ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF6_7, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 220, -1, -1));

        jF12_7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF12_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF12_7ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF12_7, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 220, -1, -1));

        jF12_8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF12_8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF12_8ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF12_8, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 250, -1, -1));

        jFL_8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFL_8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFL_8ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jFL_8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, -1, -1));

        jFR_8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFR_8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFR_8ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jFR_8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, -1, -1));

        jF1_8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF1_8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF1_8ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF1_8, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 250, -1, -1));

        jF2_8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF2_8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF2_8ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF2_8, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 250, -1, -1));

        jF3_8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF3_8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF3_8ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF3_8, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 250, -1, -1));

        jF4_8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF4_8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF4_8ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF4_8, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 250, -1, -1));

        jF5_8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF5_8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF5_8ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF5_8, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 250, -1, -1));

        jF6_8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF6_8.setSelected(true);
        jF6_8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF6_8ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF6_8, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 250, -1, -1));

        jF7_8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF7_8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF7_8ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF7_8, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 250, -1, -1));

        jF8_8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF8_8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF8_8ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF8_8, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 250, -1, -1));

        jF9_8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF9_8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF9_8ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF9_8, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 250, -1, -1));

        jF10_8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF10_8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF10_8ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF10_8, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 250, -1, -1));

        jF11_8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF11_8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF11_8ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF11_8, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 250, -1, -1));

        jLabel89.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel89.setText(bundle.getString("LDG33.jLabel89.text")); // NOI18N
        jFunctionMapping.add(jLabel89, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, -1, -1));

        jINV_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jINV_1.setToolTipText(bundle.getString("LDG33.jINV_1.toolTipText")); // NOI18N
        jINV_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jINV_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jINV_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 40, -1, -1));

        jINV_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jINV_2.setToolTipText(bundle.getString("LDG33.jINV_2.toolTipText")); // NOI18N
        jINV_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jINV_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jINV_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 70, -1, -1));

        jLabel91.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel91.setText(bundle.getString("LDG33.jLabel91.text")); // NOI18N
        jLabel91.setToolTipText(bundle.getString("LDG33.jLabel91.toolTipText")); // NOI18N
        jFunctionMapping.add(jLabel91, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 10, 40, 30));

        jINV_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jINV_3.setToolTipText(bundle.getString("LDG33.jINV_3.toolTipText")); // NOI18N
        jINV_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jINV_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jINV_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 100, -1, -1));

        jINV_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jINV_4.setToolTipText(bundle.getString("LDG33.jINV_4.toolTipText")); // NOI18N
        jINV_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jINV_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jINV_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 130, -1, -1));

        jINV_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jINV_5.setToolTipText(bundle.getString("LDG33.jINV_5.toolTipText")); // NOI18N
        jINV_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jINV_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jINV_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 160, -1, -1));

        jINV_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jINV_6.setToolTipText(bundle.getString("LDG33.jINV_6.toolTipText")); // NOI18N
        jINV_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jINV_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jINV_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 190, -1, -1));

        jINV_7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jINV_7.setToolTipText(bundle.getString("LDG33.jINV_7.toolTipText")); // NOI18N
        jINV_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jINV_7ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jINV_7, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 220, -1, -1));

        jINV_8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jINV_8.setToolTipText(bundle.getString("LDG33.jINV_8.toolTipText")); // NOI18N
        jINV_8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jINV_8ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jINV_8, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 250, -1, -1));

        jDecodereigenschaften.addTab(bundle.getString("LDG33.jFunctionMapping.TabConstraints.tabTitle"), jFunctionMapping); // NOI18N

        jDimmen.setToolTipText(bundle.getString("LDG33.jDimmen.toolTipText")); // NOI18N
        jDimmen.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jDimmenComponentShown(evt);
            }
        });
        jDimmen.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jDimmen1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jDimmen1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jDimmen1.setText(bundle.getString("LDG33.jDimmen1.text")); // NOI18N
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
        jDimmen.add(jDimmen1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, 30, -1));

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel23.setText(bundle.getString("LDG33.jLabel23.text")); // NOI18N
        jDimmen.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, -1, -1));

        jDimmen2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jDimmen2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jDimmen2.setText(bundle.getString("LDG33.jDimmen2.text")); // NOI18N
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
        jDimmen.add(jDimmen2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, 30, -1));

        jLabel37.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel37.setText(bundle.getString("LDG33.jLabel37.text")); // NOI18N
        jLabel37.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jDimmen.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 40, -1));
        jDimmen.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel42.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel42.setText(bundle.getString("LDG33.jLabel42.text")); // NOI18N
        jLabel42.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jDimmen.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 40, -1));

        buttonGroup2.add(jAltDim1ein);
        jAltDim1ein.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAltDim1ein.setText(bundle.getString("LDG33.jAltDim1ein.text")); // NOI18N
        jAltDim1ein.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAltDim1einActionPerformed(evt);
            }
        });
        jDimmen.add(jAltDim1ein, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, -1, -1));

        buttonGroup3.add(jAltDim2ein);
        jAltDim2ein.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAltDim2ein.setText(bundle.getString("LDG33.jAltDim2ein.text")); // NOI18N
        jAltDim2ein.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAltDim2einActionPerformed(evt);
            }
        });
        jDimmen.add(jAltDim2ein, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 90, -1, -1));

        buttonGroup2.add(jAltDim1aus);
        jAltDim1aus.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAltDim1aus.setSelected(true);
        jAltDim1aus.setText(bundle.getString("LDG33.jAltDim1aus.text")); // NOI18N
        jAltDim1aus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAltDim1ausActionPerformed(evt);
            }
        });
        jDimmen.add(jAltDim1aus, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 60, -1, -1));

        buttonGroup3.add(jAltDim2aus);
        jAltDim2aus.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAltDim2aus.setSelected(true);
        jAltDim2aus.setText(bundle.getString("LDG33.jAltDim2aus.text")); // NOI18N
        jAltDim2aus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAltDim2ausActionPerformed(evt);
            }
        });
        jDimmen.add(jAltDim2aus, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 90, -1, -1));

        jLabel43.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel43.setText(bundle.getString("LDG33.jLabel43.text")); // NOI18N
        jDimmen.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 30, -1, -1));

        jLabel45.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel45.setText(bundle.getString("LDG33.jLabel45.text")); // NOI18N
        jDimmen.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 280, 270, -1));

        jAltDimmenFS1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAltDimmenFS1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jAltDimmenFS1.setText(bundle.getString("LDG33.jAltDimmenFS1.text")); // NOI18N
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
        jDimmen.add(jAltDimmenFS1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 280, 30, -1));

        jLabel46.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel46.setText(bundle.getString("LDG33.jLabel46.text")); // NOI18N
        jDimmen.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 280, -1, -1));

        jAltDimmen1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAltDimmen1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jAltDimmen1.setText(bundle.getString("LDG33.jAltDimmen1.text")); // NOI18N
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
        jDimmen.add(jAltDimmen1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 60, 30, -1));

        jAltDimmen2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAltDimmen2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jAltDimmen2.setText(bundle.getString("LDG33.jAltDimmen2.text")); // NOI18N
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
        jDimmen.add(jAltDimmen2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 90, 30, -1));
        jDimmen.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 420, 10));

        jLabel67.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel67.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel67.setText(bundle.getString("LDG33.jLabel67.text")); // NOI18N
        jLabel67.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jDimmen.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 40, -1));

        jLabel68.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel68.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel68.setText(bundle.getString("LDG33.jLabel68.text")); // NOI18N
        jLabel68.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jDimmen.add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 40, -1));

        jLabel69.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel69.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel69.setText(bundle.getString("LDG33.jLabel69.text")); // NOI18N
        jLabel69.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jDimmen.add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 40, -1));

        jLabel70.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel70.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel70.setText(bundle.getString("LDG33.jLabel70.text")); // NOI18N
        jLabel70.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jDimmen.add(jLabel70, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 40, -1));

        jDimmen3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jDimmen3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jDimmen3.setText(bundle.getString("LDG33.jDimmen3.text")); // NOI18N
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
        jDimmen.add(jDimmen3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, 30, -1));

        jDimmen4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jDimmen4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jDimmen4.setText(bundle.getString("LDG33.jDimmen4.text")); // NOI18N
        jDimmen4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jDimmen4FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jDimmen4FocusLost(evt);
            }
        });
        jDimmen4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jDimmen4KeyReleased(evt);
            }
        });
        jDimmen.add(jDimmen4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, 30, -1));

        jDimmen5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jDimmen5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jDimmen5.setText(bundle.getString("LDG33.jDimmen5.text")); // NOI18N
        jDimmen5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jDimmen5FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jDimmen5FocusLost(evt);
            }
        });
        jDimmen5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jDimmen5KeyReleased(evt);
            }
        });
        jDimmen.add(jDimmen5, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 180, 30, -1));

        jDimmen6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jDimmen6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jDimmen6.setText(bundle.getString("LDG33.jDimmen6.text")); // NOI18N
        jDimmen6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jDimmen6FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jDimmen6FocusLost(evt);
            }
        });
        jDimmen6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jDimmen6KeyReleased(evt);
            }
        });
        jDimmen.add(jDimmen6, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 210, 30, -1));

        buttonGroup4.add(jAltDim3ein);
        jAltDim3ein.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAltDim3ein.setText(bundle.getString("LDG33.jAltDim3ein.text")); // NOI18N
        jAltDim3ein.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAltDim3einActionPerformed(evt);
            }
        });
        jDimmen.add(jAltDim3ein, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 120, -1, -1));

        buttonGroup4.add(jAltDim3aus);
        jAltDim3aus.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAltDim3aus.setSelected(true);
        jAltDim3aus.setText(bundle.getString("LDG33.jAltDim3aus.text")); // NOI18N
        jAltDim3aus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAltDim3ausActionPerformed(evt);
            }
        });
        jDimmen.add(jAltDim3aus, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 120, -1, -1));

        buttonGroup5.add(jAltDim4ein);
        jAltDim4ein.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAltDim4ein.setText(bundle.getString("LDG33.jAltDim4ein.text")); // NOI18N
        jAltDim4ein.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAltDim4einActionPerformed(evt);
            }
        });
        jDimmen.add(jAltDim4ein, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 150, -1, -1));

        buttonGroup5.add(jAltDim4aus);
        jAltDim4aus.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAltDim4aus.setSelected(true);
        jAltDim4aus.setText(bundle.getString("LDG33.jAltDim4aus.text")); // NOI18N
        jAltDim4aus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAltDim4ausActionPerformed(evt);
            }
        });
        jDimmen.add(jAltDim4aus, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 150, -1, -1));

        buttonGroup6.add(jAltDim5ein);
        jAltDim5ein.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAltDim5ein.setText(bundle.getString("LDG33.jAltDim5ein.text")); // NOI18N
        jAltDim5ein.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAltDim5einActionPerformed(evt);
            }
        });
        jDimmen.add(jAltDim5ein, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 180, -1, -1));

        buttonGroup6.add(jAltDim5aus);
        jAltDim5aus.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAltDim5aus.setSelected(true);
        jAltDim5aus.setText(bundle.getString("LDG33.jAltDim5aus.text")); // NOI18N
        jAltDim5aus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAltDim5ausActionPerformed(evt);
            }
        });
        jDimmen.add(jAltDim5aus, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 180, -1, -1));

        buttonGroup7.add(jAltDim6ein);
        jAltDim6ein.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAltDim6ein.setText(bundle.getString("LDG33.jAltDim6ein.text")); // NOI18N
        jAltDim6ein.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAltDim6einActionPerformed(evt);
            }
        });
        jDimmen.add(jAltDim6ein, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 210, -1, -1));

        buttonGroup7.add(jAltDim6aus);
        jAltDim6aus.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAltDim6aus.setSelected(true);
        jAltDim6aus.setText(bundle.getString("LDG33.jAltDim6aus.text")); // NOI18N
        jAltDim6aus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAltDim6ausActionPerformed(evt);
            }
        });
        jDimmen.add(jAltDim6aus, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 210, -1, -1));

        jAltDimmen3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAltDimmen3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jAltDimmen3.setText(bundle.getString("LDG33.jAltDimmen3.text")); // NOI18N
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
        jDimmen.add(jAltDimmen3, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 120, 30, -1));

        jAltDimmen4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAltDimmen4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jAltDimmen4.setText(bundle.getString("LDG33.jAltDimmen4.text")); // NOI18N
        jAltDimmen4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jAltDimmen4FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jAltDimmen4FocusLost(evt);
            }
        });
        jAltDimmen4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jAltDimmen4KeyReleased(evt);
            }
        });
        jDimmen.add(jAltDimmen4, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 150, 30, -1));

        jAltDimmen5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAltDimmen5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jAltDimmen5.setText(bundle.getString("LDG33.jAltDimmen5.text")); // NOI18N
        jAltDimmen5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jAltDimmen5FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jAltDimmen5FocusLost(evt);
            }
        });
        jAltDimmen5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jAltDimmen5KeyReleased(evt);
            }
        });
        jDimmen.add(jAltDimmen5, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 180, 30, -1));

        jAltDimmen6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAltDimmen6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jAltDimmen6.setText(bundle.getString("LDG33.jAltDimmen6.text")); // NOI18N
        jAltDimmen6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jAltDimmen6FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jAltDimmen6FocusLost(evt);
            }
        });
        jAltDimmen6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jAltDimmen6KeyReleased(evt);
            }
        });
        jDimmen.add(jAltDimmen6, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 210, 30, -1));

        jDecodereigenschaften.addTab(bundle.getString("LDG33.jDimmen.TabConstraints.tabTitle"), jDimmen); // NOI18N

        jEffekte.setToolTipText(bundle.getString("LDG33.jEffekte.toolTipText")); // NOI18N
        jEffekte.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jEffekte.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jEffekteComponentShown(evt);
            }
        });
        jEffekte.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText(bundle.getString("LDG33.jLabel21.text")); // NOI18N
        jLabel21.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jEffekte.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 40, 20));

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel25.setText(bundle.getString("LDG33.jLabel25.text")); // NOI18N
        jEffekte.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, -1, -1));

        jVor1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jVor1.setSelected(true);
        jVor1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jVor1ActionPerformed(evt);
            }
        });
        jEffekte.add(jVor1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 90, -1, -1));

        jRueck1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRueck1.setSelected(true);
        jRueck1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRueck1ActionPerformed(evt);
            }
        });
        jEffekte.add(jRueck1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 90, -1, -1));

        jTast1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTast1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTast1.setText(bundle.getString("LDG33.jTast1.text")); // NOI18N
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
        jEffekte.add(jTast1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, 30, -1));

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel30.setText(bundle.getString("LDG33.jLabel30.text")); // NOI18N
        jEffekte.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 50, -1, -1));

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText(bundle.getString("LDG33.jLabel22.text")); // NOI18N
        jLabel22.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jEffekte.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 40, 20));

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel26.setText(bundle.getString("LDG33.jLabel26.text")); // NOI18N
        jEffekte.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, -1, -1));

        jTast2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTast2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTast2.setText(bundle.getString("LDG33.jTast2.text")); // NOI18N
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
        jEffekte.add(jTast2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 120, 30, -1));

        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel32.setText(bundle.getString("LDG33.jLabel32.text")); // NOI18N
        jEffekte.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 70, -1, -1));

        jRueck2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRueck2.setSelected(true);
        jRueck2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRueck2ActionPerformed(evt);
            }
        });
        jEffekte.add(jRueck2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 120, -1, -1));

        jVor2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jVor2.setSelected(true);
        jVor2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jVor2ActionPerformed(evt);
            }
        });
        jEffekte.add(jVor2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 120, -1, -1));

        jInvertBlink1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jInvertBlink1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jInvertBlink1ActionPerformed(evt);
            }
        });
        jEffekte.add(jInvertBlink1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 90, -1, 20));

        jInvertBlink2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jInvertBlink2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jInvertBlink2ActionPerformed(evt);
            }
        });
        jEffekte.add(jInvertBlink2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 120, -1, 20));

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText(bundle.getString("LDG33.jLabel24.text")); // NOI18N
        jLabel24.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jEffekte.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 40, 20));

        jLabel44.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel44.setText(bundle.getString("LDG33.jLabel44.text")); // NOI18N
        jLabel44.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jEffekte.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 40, 20));

        jLabel47.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel47.setText(bundle.getString("LDG33.jLabel47.text")); // NOI18N
        jLabel47.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jEffekte.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 40, 20));

        jLabel48.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel48.setText(bundle.getString("LDG33.jLabel48.text")); // NOI18N
        jLabel48.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jEffekte.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 40, 20));

        jTast3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTast3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTast3.setText(bundle.getString("LDG33.jTast3.text")); // NOI18N
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
        jEffekte.add(jTast3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, 30, -1));

        jTast4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTast4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTast4.setText(bundle.getString("LDG33.jTast4.text")); // NOI18N
        jTast4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTast4FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTast4FocusLost(evt);
            }
        });
        jTast4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTast4KeyReleased(evt);
            }
        });
        jEffekte.add(jTast4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 180, 30, -1));

        jTast5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTast5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTast5.setText(bundle.getString("LDG33.jTast5.text")); // NOI18N
        jTast5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTast5FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTast5FocusLost(evt);
            }
        });
        jTast5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTast5KeyReleased(evt);
            }
        });
        jEffekte.add(jTast5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 210, 30, -1));

        jTast6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTast6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTast6.setText(bundle.getString("LDG33.jTast6.text")); // NOI18N
        jTast6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTast6FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTast6FocusLost(evt);
            }
        });
        jTast6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTast6KeyReleased(evt);
            }
        });
        jEffekte.add(jTast6, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 240, 30, -1));

        jInvertBlink3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jInvertBlink3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jInvertBlink3ActionPerformed(evt);
            }
        });
        jEffekte.add(jInvertBlink3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 150, -1, 20));

        jInvertBlink4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jInvertBlink4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jInvertBlink4ActionPerformed(evt);
            }
        });
        jEffekte.add(jInvertBlink4, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 180, -1, 20));

        jInvertBlink5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jInvertBlink5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jInvertBlink5ActionPerformed(evt);
            }
        });
        jEffekte.add(jInvertBlink5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 210, -1, 20));

        jInvertBlink6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jInvertBlink6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jInvertBlink6ActionPerformed(evt);
            }
        });
        jEffekte.add(jInvertBlink6, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 240, -1, 20));

        jVor3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jVor3.setSelected(true);
        jVor3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jVor3ActionPerformed(evt);
            }
        });
        jEffekte.add(jVor3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 150, -1, -1));

        jVor4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jVor4.setSelected(true);
        jVor4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jVor4ActionPerformed(evt);
            }
        });
        jEffekte.add(jVor4, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 180, -1, -1));

        jVor5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jVor5.setSelected(true);
        jVor5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jVor5ActionPerformed(evt);
            }
        });
        jEffekte.add(jVor5, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 210, -1, -1));

        jVor6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jVor6.setSelected(true);
        jVor6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jVor6ActionPerformed(evt);
            }
        });
        jEffekte.add(jVor6, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 240, -1, -1));

        jRueck3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRueck3.setSelected(true);
        jRueck3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRueck3ActionPerformed(evt);
            }
        });
        jEffekte.add(jRueck3, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 150, -1, -1));

        jRueck4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRueck4.setSelected(true);
        jRueck4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRueck4ActionPerformed(evt);
            }
        });
        jEffekte.add(jRueck4, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 180, -1, -1));

        jRueck5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRueck5.setSelected(true);
        jRueck5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRueck5ActionPerformed(evt);
            }
        });
        jEffekte.add(jRueck5, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 210, -1, -1));

        jRueck6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRueck6.setSelected(true);
        jRueck6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRueck6ActionPerformed(evt);
            }
        });
        jEffekte.add(jRueck6, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, -1, -1));

        jLabel62.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel62.setText(bundle.getString("LDG33.jLabel62.text")); // NOI18N
        jEffekte.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, 60, -1));

        jLabel71.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel71.setText(bundle.getString("LDG33.jLabel71.text")); // NOI18N
        jEffekte.add(jLabel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, 50, -1));

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jEffekte.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 30, 10, 230));

        jLabel82.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel82.setText(bundle.getString("LDG33.jLabel82.text")); // NOI18N
        jEffekte.add(jLabel82, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 220, -1, -1));

        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jEffekte.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, 10, 230));

        jLabel83.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel83.setText(bundle.getString("LDG33.jLabel83.text")); // NOI18N
        jEffekte.add(jLabel83, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 30, -1, -1));

        jLabel84.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel84.setText(bundle.getString("LDG33.jLabel84.text")); // NOI18N
        jEffekte.add(jLabel84, new org.netbeans.lib.awtextra.AbsoluteConstraints(282, 30, 170, -1));

        jF0AusF2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF0AusF2.setText(bundle.getString("LDG33.jF0AusF2.text")); // NOI18N
        jF0AusF2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF0AusF2ActionPerformed(evt);
            }
        });
        jEffekte.add(jF0AusF2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 90, -1, -1));

        jF0AusF1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF0AusF1.setText(bundle.getString("LDG33.jF0AusF1.text")); // NOI18N
        jF0AusF1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF0AusF1ActionPerformed(evt);
            }
        });
        jEffekte.add(jF0AusF1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 90, -1, -1));

        jF0AusF3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF0AusF3.setText(bundle.getString("LDG33.jF0AusF3.text")); // NOI18N
        jF0AusF3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF0AusF3ActionPerformed(evt);
            }
        });
        jEffekte.add(jF0AusF3, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 90, -1, -1));

        jF0AusF4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF0AusF4.setText(bundle.getString("LDG33.jF0AusF4.text")); // NOI18N
        jF0AusF4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF0AusF4ActionPerformed(evt);
            }
        });
        jEffekte.add(jF0AusF4, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 90, -1, -1));

        jF0AusF5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF0AusF5.setText(bundle.getString("LDG33.jF0AusF5.text")); // NOI18N
        jF0AusF5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF0AusF5ActionPerformed(evt);
            }
        });
        jEffekte.add(jF0AusF5, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 110, -1, -1));

        jF0AusF6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF0AusF6.setText(bundle.getString("LDG33.jF0AusF6.text")); // NOI18N
        jF0AusF6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF0AusF6ActionPerformed(evt);
            }
        });
        jEffekte.add(jF0AusF6, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 110, -1, -1));

        jF0AusF7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF0AusF7.setText(bundle.getString("LDG33.jF0AusF7.text")); // NOI18N
        jF0AusF7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF0AusF7ActionPerformed(evt);
            }
        });
        jEffekte.add(jF0AusF7, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 110, -1, -1));

        jF0AusF8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF0AusF8.setText(bundle.getString("LDG33.jF0AusF8.text")); // NOI18N
        jF0AusF8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF0AusF8ActionPerformed(evt);
            }
        });
        jEffekte.add(jF0AusF8, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 110, -1, -1));

        jLabel85.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel85.setText(bundle.getString("LDG33.jLabel85.text")); // NOI18N
        jEffekte.add(jLabel85, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 70, 130, -1));

        jF0AusAUX1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF0AusAUX1.setText(bundle.getString("LDG33.jF0AusAUX1.text")); // NOI18N
        jF0AusAUX1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF0AusAUX1ActionPerformed(evt);
            }
        });
        jEffekte.add(jF0AusAUX1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 160, -1, -1));

        jF0AusAUX2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF0AusAUX2.setText(bundle.getString("LDG33.jF0AusAUX2.text")); // NOI18N
        jF0AusAUX2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF0AusAUX2ActionPerformed(evt);
            }
        });
        jEffekte.add(jF0AusAUX2, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 160, -1, -1));

        jF0AusAUX3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF0AusAUX3.setText(bundle.getString("LDG33.jF0AusAUX3.text")); // NOI18N
        jF0AusAUX3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF0AusAUX3ActionPerformed(evt);
            }
        });
        jEffekte.add(jF0AusAUX3, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 160, -1, -1));

        jF0AusAUX4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF0AusAUX4.setText(bundle.getString("LDG33.jF0AusAUX4.text")); // NOI18N
        jF0AusAUX4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF0AusAUX4ActionPerformed(evt);
            }
        });
        jEffekte.add(jF0AusAUX4, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 180, -1, -1));

        jF0AusAUX5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF0AusAUX5.setText(bundle.getString("LDG33.jF0AusAUX5.text")); // NOI18N
        jF0AusAUX5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF0AusAUX5ActionPerformed(evt);
            }
        });
        jEffekte.add(jF0AusAUX5, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 180, -1, -1));

        jF0AusAUX6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF0AusAUX6.setText(bundle.getString("LDG33.jF0AusAUX6.text")); // NOI18N
        jF0AusAUX6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF0AusAUX6ActionPerformed(evt);
            }
        });
        jEffekte.add(jF0AusAUX6, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 180, -1, -1));

        jLabel86.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel86.setText(bundle.getString("LDG33.jLabel86.text")); // NOI18N
        jEffekte.add(jLabel86, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 140, -1, -1));

        jF0AusF1Aus.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF0AusF1Aus.setText(bundle.getString("LDG33.jF0AusF1Aus.text")); // NOI18N
        jF0AusF1Aus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF0AusF1AusActionPerformed(evt);
            }
        });
        jEffekte.add(jF0AusF1Aus, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 240, -1, -1));

        jF0AusF2Aus.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF0AusF2Aus.setText(bundle.getString("LDG33.jF0AusF2Aus.text")); // NOI18N
        jF0AusF2Aus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF0AusF2AusActionPerformed(evt);
            }
        });
        jEffekte.add(jF0AusF2Aus, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 240, -1, -1));

        jF0AusF3Aus.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF0AusF3Aus.setText(bundle.getString("LDG33.jF0AusF3Aus.text")); // NOI18N
        jF0AusF3Aus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF0AusF3AusActionPerformed(evt);
            }
        });
        jEffekte.add(jF0AusF3Aus, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 240, -1, -1));

        jF0AusF4Aus.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF0AusF4Aus.setText(bundle.getString("LDG33.jF0AusF4Aus.text")); // NOI18N
        jF0AusF4Aus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF0AusF4AusActionPerformed(evt);
            }
        });
        jEffekte.add(jF0AusF4Aus, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 240, -1, -1));

        jSchleiferumschaltung.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jSchleiferumschaltung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSchleiferumschaltungActionPerformed(evt);
            }
        });
        jEffekte.add(jSchleiferumschaltung, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 280, -1, 20));

        jLabel90.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel90.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel90.setText(bundle.getString("LDG33.jLabel90.text")); // NOI18N
        jLabel90.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jEffekte.add(jLabel90, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, -1, 20));

        jDecodereigenschaften.addTab(bundle.getString("LDG33.jEffekte.TabConstraints.tabTitle"), jEffekte); // NOI18N

        jRangieren.setToolTipText(bundle.getString("LDG33.jRangieren.toolTipText")); // NOI18N
        jRangieren.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jRangierenComponentShown(evt);
            }
        });
        jRangieren.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel31.setText(bundle.getString("LDG33.jLabel31.text")); // NOI18N
        jRangieren.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 200, 20));

        jRL_F4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRL_F4.setText(bundle.getString("LDG33.jRL_F4.text")); // NOI18N
        jRL_F4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRL_F4ActionPerformed(evt);
            }
        });
        jRangieren.add(jRL_F4, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 50, -1, -1));

        jRL_F3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRL_F3.setText(bundle.getString("LDG33.jRL_F3.text")); // NOI18N
        jRL_F3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRL_F3ActionPerformed(evt);
            }
        });
        jRangieren.add(jRL_F3, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 50, -1, -1));

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel29.setText(bundle.getString("LDG33.jLabel29.text")); // NOI18N
        jRangieren.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 200, -1));

        jRL_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRL_1.setText(bundle.getString("LDG33.jRL_1.text")); // NOI18N
        jRL_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRL_1ActionPerformed(evt);
            }
        });
        jRangieren.add(jRL_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 110, -1, -1));

        jRL_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRL_2.setText(bundle.getString("LDG33.jRL_2.text")); // NOI18N
        jRL_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRL_2ActionPerformed(evt);
            }
        });
        jRangieren.add(jRL_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 110, -1, -1));

        jLabel58.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel58.setText(bundle.getString("LDG33.jLabel58.text")); // NOI18N
        jRangieren.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 170, 40, 20));

        jR_F3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jR_F3.setText(bundle.getString("LDG33.jR_F3.text")); // NOI18N
        jR_F3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jR_F3ActionPerformed(evt);
            }
        });
        jRangieren.add(jR_F3, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 80, -1, -1));

        jR_F4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jR_F4.setText(bundle.getString("LDG33.jR_F4.text")); // NOI18N
        jR_F4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jR_F4ActionPerformed(evt);
            }
        });
        jRangieren.add(jR_F4, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 80, -1, -1));

        jLabel59.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel59.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel59.setText(bundle.getString("LDG33.jLabel59.text")); // NOI18N
        jRangieren.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 210, 20));

        jAB_F3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAB_F3.setText(bundle.getString("LDG33.jAB_F3.text")); // NOI18N
        jAB_F3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAB_F3ActionPerformed(evt);
            }
        });
        jRangieren.add(jAB_F3, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 20, -1, -1));

        jAB_F4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAB_F4.setText(bundle.getString("LDG33.jAB_F4.text")); // NOI18N
        jAB_F4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAB_F4ActionPerformed(evt);
            }
        });
        jRangieren.add(jAB_F4, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 20, -1, -1));

        jR_F1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jR_F1.setText(bundle.getString("LDG33.jR_F1.text")); // NOI18N
        jR_F1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jR_F1ActionPerformed(evt);
            }
        });
        jRangieren.add(jR_F1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 80, -1, -1));

        jR_F2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jR_F2.setText(bundle.getString("LDG33.jR_F2.text")); // NOI18N
        jR_F2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jR_F2ActionPerformed(evt);
            }
        });
        jRangieren.add(jR_F2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 80, -1, -1));

        jRL_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRL_3.setText(bundle.getString("LDG33.jRL_3.text")); // NOI18N
        jRL_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRL_3ActionPerformed(evt);
            }
        });
        jRangieren.add(jRL_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 110, -1, -1));

        jRL_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRL_4.setText(bundle.getString("LDG33.jRL_4.text")); // NOI18N
        jRL_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRL_4ActionPerformed(evt);
            }
        });
        jRangieren.add(jRL_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 110, -1, -1));

        jRL_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRL_5.setText(bundle.getString("LDG33.jRL_5.text")); // NOI18N
        jRL_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRL_5ActionPerformed(evt);
            }
        });
        jRangieren.add(jRL_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 110, 40, 20));

        jRL_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRL_6.setText(bundle.getString("LDG33.jRL_6.text")); // NOI18N
        jRL_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRL_6ActionPerformed(evt);
            }
        });
        jRangieren.add(jRL_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 110, -1, -1));

        jLabel72.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel72.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel72.setText(bundle.getString("LDG33.jLabel72.text")); // NOI18N
        jRangieren.add(jLabel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 210, 20));

        jLabel73.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel73.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel73.setText(bundle.getString("LDG33.jLabel73.text")); // NOI18N
        jRangieren.add(jLabel73, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 210, -1));

        jAR_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAR_1.setText(bundle.getString("LDG33.jAR_1.text")); // NOI18N
        jAR_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAR_1ActionPerformed(evt);
            }
        });
        jRangieren.add(jAR_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 230, -1, -1));

        jAR_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAR_2.setText(bundle.getString("LDG33.jAR_2.text")); // NOI18N
        jAR_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAR_2ActionPerformed(evt);
            }
        });
        jRangieren.add(jAR_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 230, -1, -1));

        jAR_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAR_3.setText(bundle.getString("LDG33.jAR_3.text")); // NOI18N
        jAR_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAR_3ActionPerformed(evt);
            }
        });
        jRangieren.add(jAR_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 230, -1, -1));

        jAR_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAR_4.setText(bundle.getString("LDG33.jAR_4.text")); // NOI18N
        jAR_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAR_4ActionPerformed(evt);
            }
        });
        jRangieren.add(jAR_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 230, -1, -1));

        jAR_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAR_5.setText(bundle.getString("LDG33.jAR_5.text")); // NOI18N
        jAR_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAR_5ActionPerformed(evt);
            }
        });
        jRangieren.add(jAR_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 230, 40, -1));

        jAR_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAR_6.setText(bundle.getString("LDG33.jAR_6.text")); // NOI18N
        jAR_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAR_6ActionPerformed(evt);
            }
        });
        jRangieren.add(jAR_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 230, -1, -1));

        jLabel74.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel74.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel74.setText(bundle.getString("LDG33.jLabel74.text")); // NOI18N
        jRangieren.add(jLabel74, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 220, 20));

        jAbrueckGeschw.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jAbrueckGeschw.setText(bundle.getString("LDG33.jAbrueckGeschw.text")); // NOI18N
        jAbrueckGeschw.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jAbrueckGeschwFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jAbrueckGeschwFocusLost(evt);
            }
        });
        jAbrueckGeschw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jAbrueckGeschwKeyReleased(evt);
            }
        });
        jRangieren.add(jAbrueckGeschw, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 260, 40, -1));

        jLabel75.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel75.setText(bundle.getString("LDG33.jLabel75.text")); // NOI18N
        jRangieren.add(jLabel75, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 500, -1));

        jKickZeit1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jKickZeit1.setText(bundle.getString("LDG33.jKickZeit1.text")); // NOI18N
        jKickZeit1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jKickZeit1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jKickZeit1FocusLost(evt);
            }
        });
        jKickZeit1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jKickZeit1KeyReleased(evt);
            }
        });
        jRangieren.add(jKickZeit1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 170, 30, -1));

        jLabel76.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel76.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel76.setText(bundle.getString("LDG33.jLabel76.text")); // NOI18N
        jRangieren.add(jLabel76, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 210, 20));

        jLabel77.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel77.setText(bundle.getString("LDG33.jLabel77.text")); // NOI18N
        jRangieren.add(jLabel77, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 170, 40, 20));

        jKickZeit2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jKickZeit2.setText(bundle.getString("LDG33.jKickZeit2.text")); // NOI18N
        jKickZeit2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jKickZeit2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jKickZeit2FocusLost(evt);
            }
        });
        jKickZeit2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jKickZeit2KeyReleased(evt);
            }
        });
        jRangieren.add(jKickZeit2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 170, 30, -1));

        jKickZeit3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jKickZeit3.setText(bundle.getString("LDG33.jKickZeit3.text")); // NOI18N
        jKickZeit3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jKickZeit3FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jKickZeit3FocusLost(evt);
            }
        });
        jKickZeit3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jKickZeit3KeyReleased(evt);
            }
        });
        jRangieren.add(jKickZeit3, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 170, 30, -1));

        jLabel78.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel78.setText(bundle.getString("LDG33.jLabel78.text")); // NOI18N
        jRangieren.add(jLabel78, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 170, 40, 20));

        jKickZeit4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jKickZeit4.setText(bundle.getString("LDG33.jKickZeit4.text")); // NOI18N
        jKickZeit4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jKickZeit4FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jKickZeit4FocusLost(evt);
            }
        });
        jKickZeit4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jKickZeit4KeyReleased(evt);
            }
        });
        jRangieren.add(jKickZeit4, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 200, 30, -1));

        jLabel79.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel79.setText(bundle.getString("LDG33.jLabel79.text")); // NOI18N
        jRangieren.add(jLabel79, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 200, 40, 20));

        jKickZeit5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jKickZeit5.setText(bundle.getString("LDG33.jKickZeit5.text")); // NOI18N
        jKickZeit5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jKickZeit5FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jKickZeit5FocusLost(evt);
            }
        });
        jKickZeit5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jKickZeit5KeyReleased(evt);
            }
        });
        jRangieren.add(jKickZeit5, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 200, 30, -1));

        jLabel80.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel80.setText(bundle.getString("LDG33.jLabel80.text")); // NOI18N
        jRangieren.add(jLabel80, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 200, 40, 20));

        jLabel81.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel81.setText(bundle.getString("LDG33.jLabel81.text")); // NOI18N
        jRangieren.add(jLabel81, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 200, 40, 20));

        jKickZeit6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jKickZeit6.setText(bundle.getString("LDG33.jKickZeit6.text")); // NOI18N
        jKickZeit6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jKickZeit6FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jKickZeit6FocusLost(evt);
            }
        });
        jKickZeit6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jKickZeit6KeyReleased(evt);
            }
        });
        jRangieren.add(jKickZeit6, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 200, 30, -1));

        jDecodereigenschaften.addTab(bundle.getString("LDG33.jRangieren.TabConstraints.tabTitle"), jRangieren); // NOI18N

        jFahreigenschaften.setToolTipText(bundle.getString("LDG33.jFahreigenschaften.toolTipText")); // NOI18N
        jFahreigenschaften.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFahreigenschaften.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jFahreigenschaftenComponentShown(evt);
            }
        });

        jAnfahrGeschw.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAnfahrGeschw.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jAnfahrGeschw.setText(bundle.getString("LDG33.jAnfahrGeschw.text")); // NOI18N
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
        jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel49.setText(bundle.getString("LDG33.jLabel49.text")); // NOI18N

        jAnfahrVerz.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAnfahrVerz.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jAnfahrVerz.setText(bundle.getString("LDG33.jAnfahrVerz.text")); // NOI18N
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
        jLabel50.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel50.setText(bundle.getString("LDG33.jLabel50.text")); // NOI18N

        jBremsVerz.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBremsVerz.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jBremsVerz.setText(bundle.getString("LDG33.jBremsVerz.text")); // NOI18N
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
        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel51.setText(bundle.getString("LDG33.jLabel51.text")); // NOI18N

        jLabel52.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel52.setText(bundle.getString("LDG33.jLabel52.text")); // NOI18N

        jVMax.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jVMax.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jVMax.setText(bundle.getString("LDG33.jVMax.text")); // NOI18N
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
        jLabel53.setText(bundle.getString("LDG33.jLabel53.text")); // NOI18N

        jKp.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jKp.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jKp.setText(bundle.getString("LDG33.jKp.text")); // NOI18N
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
        jKi.setText(bundle.getString("LDG33.jKi.text")); // NOI18N
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
        jKd.setText(bundle.getString("LDG33.jKd.text")); // NOI18N
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
        jLabel54.setText(bundle.getString("LDG33.jLabel54.text")); // NOI18N

        jLabel55.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel55.setText(bundle.getString("LDG33.jLabel55.text")); // NOI18N

        jLabel56.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel56.setText(bundle.getString("LDG33.jLabel56.text")); // NOI18N

        jMotorListe.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMotorListe.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "HLA", "Hamo-Magnet", "Roco", "Fleischmann", "Piko", "Brawa", "Gützold", "Mehano", "(Mini)Trix", "Arnold" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jMotorListe.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jMotorListe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jMotorListeMouseReleased(evt);
            }
        });
        jMotorListe.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jMotorListeValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(jMotorListe);

        jLabel57.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel57.setText(bundle.getString("LDG33.jLabel57.text")); // NOI18N

        jPosBrems.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPosBrems.setText(bundle.getString("LDG33.jPosBrems.text")); // NOI18N
        jPosBrems.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPosBremsActionPerformed(evt);
            }
        });

        jNegBrems.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jNegBrems.setText(bundle.getString("LDG33.jNegBrems.text")); // NOI18N
        jNegBrems.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jNegBremsActionPerformed(evt);
            }
        });

        jLabel60.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel60.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel60.setText(bundle.getString("LDG33.jLabel60.text")); // NOI18N
        jLabel60.setToolTipText(bundle.getString("LDG33.jLabel60.toolTipText")); // NOI18N

        jMotorArt.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMotorArt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jMotorArt.setText(bundle.getString("LDG33.jMotorArt.text")); // NOI18N
        jMotorArt.setToolTipText(bundle.getString("LDG33.jMotorArt.toolTipText")); // NOI18N
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
        jCV117_CV9_Text.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jCV117_CV9_Text.setText(bundle.getString("LDG33.jCV117_CV9_Text.text")); // NOI18N

        jUeberlast.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jUeberlast.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jUeberlast.setText(bundle.getString("LDG33.jUeberlast.text")); // NOI18N
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

        buttonGroup8.add(j480Hz);
        j480Hz.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        j480Hz.setSelected(true);
        j480Hz.setText(bundle.getString("LDG33.j480Hz.text")); // NOI18N
        j480Hz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                j480HzActionPerformed(evt);
            }
        });

        buttonGroup8.add(j80Hz);
        j80Hz.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        j80Hz.setText(bundle.getString("LDG33.j80Hz.text")); // NOI18N
        j80Hz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                j80HzActionPerformed(evt);
            }
        });

        jLabel61.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel61.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel61.setText(bundle.getString("LDG33.jLabel61.text")); // NOI18N
        jLabel61.setToolTipText(bundle.getString("LDG33.jLabel61.toolTipText")); // NOI18N

        jAnfahrKick.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAnfahrKick.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jAnfahrKick.setText(bundle.getString("LDG33.jAnfahrKick.text")); // NOI18N
        jAnfahrKick.setToolTipText(bundle.getString("LDG33.jAnfahrKick.toolTipText")); // NOI18N
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

        jLabel87.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel87.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel87.setText(bundle.getString("LDG33.jLabel87.text")); // NOI18N

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
                        .addGroup(jFahreigenschaftenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel87, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel52, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel51, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel50, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel49, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel60, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel61, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jCV117_CV9_Text, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jFahreigenschaftenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jFahreigenschaftenLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jFahreigenschaftenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jUeberlast, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jMotorArt, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jFahreigenschaftenLayout.createSequentialGroup()
                                        .addGroup(jFahreigenschaftenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jBremsVerz, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jAnfahrVerz, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jAnfahrGeschw, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(50, 50, 50)
                                        .addGroup(jFahreigenschaftenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jFahreigenschaftenLayout.createSequentialGroup()
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
                                            .addComponent(jLabel53, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)))
                                    .addComponent(jVMax, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jAnfahrKick, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jFahreigenschaftenLayout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(j480Hz)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(j80Hz)))))
                .addGap(50, 50, 50)
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
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE))
                    .addGroup(jFahreigenschaftenLayout.createSequentialGroup()
                        .addGroup(jFahreigenschaftenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jFahreigenschaftenLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                                .addGap(32, 32, 32)
                                .addGroup(jFahreigenschaftenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jFahreigenschaftenLayout.createSequentialGroup()
                                        .addComponent(jAnfahrGeschw, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jAnfahrVerz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jFahreigenschaftenLayout.createSequentialGroup()
                                        .addComponent(jLabel49)
                                        .addGap(12, 12, 12)
                                        .addComponent(jLabel50)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jFahreigenschaftenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jFahreigenschaftenLayout.createSequentialGroup()
                                        .addGroup(jFahreigenschaftenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jBremsVerz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel51))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jVMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel52, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jFahreigenschaftenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jMotorArt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel60))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jFahreigenschaftenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jAnfahrKick, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel61))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                        .addGroup(jFahreigenschaftenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel87)
                            .addComponent(j80Hz)
                            .addComponent(j480Hz))
                        .addGroup(jFahreigenschaftenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jFahreigenschaftenLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jUeberlast, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jFahreigenschaftenLayout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(jCV117_CV9_Text)))
                        .addGap(15, 15, 15)
                        .addComponent(jPosBrems)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jNegBrems)))
                .addContainerGap())
        );

        jDecodereigenschaften.addTab(bundle.getString("LDG33.jFahreigenschaften.TabConstraints.tabTitle"), jFahreigenschaften); // NOI18N

        jKennlinie.setToolTipText(bundle.getString("LDG33.jKennlinie.toolTipText")); // NOI18N
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

        jLabel34.setText(bundle.getString("LDG33.jLabel34.text")); // NOI18N
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

        jFS28.setMaximum(255);
        jFS28.setOrientation(javax.swing.JSlider.VERTICAL);
        jFS28.setValue(244);
        jFS28.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jFS28MouseReleased(evt);
            }
        });
        jKennlinie.add(jFS28, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 20, 10, 250));

        jLabel35.setText(bundle.getString("LDG33.jLabel35.text")); // NOI18N
        jKennlinie.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, -1, -1));

        buttonGroup9.add(jKurve1);
        jKurve1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jKurve1.setText(bundle.getString("LDG33.jKurve1.text")); // NOI18N
        jKurve1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jKurve1ActionPerformed(evt);
            }
        });
        jKennlinie.add(jKurve1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 30, -1, -1));

        buttonGroup9.add(jKurve2);
        jKurve2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jKurve2.setText(bundle.getString("LDG33.jKurve2.text")); // NOI18N
        jKurve2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jKurve2ActionPerformed(evt);
            }
        });
        jKennlinie.add(jKurve2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 50, -1, -1));

        buttonGroup9.add(jKurve3);
        jKurve3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jKurve3.setText(bundle.getString("LDG33.jKurve3.text")); // NOI18N
        jKurve3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jKurve3ActionPerformed(evt);
            }
        });
        jKennlinie.add(jKurve3, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 70, -1, -1));

        buttonGroup9.add(jKurve4);
        jKurve4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jKurve4.setText(bundle.getString("LDG33.jKurve4.text")); // NOI18N
        jKurve4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jKurve4ActionPerformed(evt);
            }
        });
        jKennlinie.add(jKurve4, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 90, -1, -1));

        jCustom1sichern.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCustom1sichern.setText(bundle.getString("LDG33.jCustom1sichern.text")); // NOI18N
        jCustom1sichern.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCustom1sichernActionPerformed(evt);
            }
        });
        jKennlinie.add(jCustom1sichern, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 130, 150, -1));

        jCustom1laden.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCustom1laden.setText(bundle.getString("LDG33.jCustom1laden.text")); // NOI18N
        jCustom1laden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCustom1ladenActionPerformed(evt);
            }
        });
        jKennlinie.add(jCustom1laden, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 170, 150, -1));

        jFS_Anzeige.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jFS_Anzeige.setText(bundle.getString("LDG33.jFS_Anzeige.text")); // NOI18N
        jKennlinie.add(jFS_Anzeige, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 240, -1, 20));

        jFS_AnzeigeWert.setEditable(false);
        jFS_AnzeigeWert.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS_AnzeigeWert.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jFS_AnzeigeWert.setText(bundle.getString("LDG33.jFS_AnzeigeWert.text")); // NOI18N
        jKennlinie.add(jFS_AnzeigeWert, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 240, 30, -1));

        jDecodereigenschaften.addTab(bundle.getString("LDG33.jKennlinie.TabConstraints.tabTitle"), jKennlinie); // NOI18N

        jAnalog.setToolTipText(bundle.getString("LDG33.jAnalog.toolTipText")); // NOI18N
        jAnalog.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAnalog.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jAnalogComponentShown(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText(bundle.getString("LDG33.jLabel2.text")); // NOI18N

        jF1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF1.setText(bundle.getString("LDG33.jF1.text")); // NOI18N
        jF1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF1ActionPerformed(evt);
            }
        });

        jF2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF2.setText(bundle.getString("LDG33.jF2.text")); // NOI18N
        jF2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF2ActionPerformed(evt);
            }
        });

        jF3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF3.setText(bundle.getString("LDG33.jF3.text")); // NOI18N
        jF3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF3ActionPerformed(evt);
            }
        });

        jF4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF4.setText(bundle.getString("LDG33.jF4.text")); // NOI18N
        jF4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF4ActionPerformed(evt);
            }
        });

        jF5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF5.setText(bundle.getString("LDG33.jF5.text")); // NOI18N
        jF5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF5ActionPerformed(evt);
            }
        });

        jF6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF6.setText(bundle.getString("LDG33.jF6.text")); // NOI18N
        jF6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF6ActionPerformed(evt);
            }
        });

        jF7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF7.setText(bundle.getString("LDG33.jF7.text")); // NOI18N
        jF7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF7ActionPerformed(evt);
            }
        });

        jF8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF8.setText(bundle.getString("LDG33.jF8.text")); // NOI18N
        jF8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF8ActionPerformed(evt);
            }
        });

        buttonGroup10.add(jAnaSpannung_G);
        jAnaSpannung_G.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAnaSpannung_G.setText(bundle.getString("LDG33.jAnaSpannung_G.text")); // NOI18N
        jAnaSpannung_G.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAnaSpannung_GActionPerformed(evt);
            }
        });

        buttonGroup10.add(jAnaSpannung_W);
        jAnaSpannung_W.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAnaSpannung_W.setSelected(true);
        jAnaSpannung_W.setText(bundle.getString("LDG33.jAnaSpannung_W.text")); // NOI18N
        jAnaSpannung_W.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAnaSpannung_WActionPerformed(evt);
            }
        });

        jLabel92.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel92.setText(bundle.getString("LDG33.jLabel92.text")); // NOI18N

        javax.swing.GroupLayout jAnalogLayout = new javax.swing.GroupLayout(jAnalog);
        jAnalog.setLayout(jAnalogLayout);
        jAnalogLayout.setHorizontalGroup(
            jAnalogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jAnalogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jAnalogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
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
                    .addGroup(jAnalogLayout.createSequentialGroup()
                        .addComponent(jLabel92, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(59, 59, 59)
                        .addGroup(jAnalogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jAnaSpannung_G)
                            .addComponent(jAnaSpannung_W))))
                .addContainerGap(203, Short.MAX_VALUE))
        );
        jAnalogLayout.setVerticalGroup(
            jAnalogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jAnalogLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                .addGap(40, 40, 40)
                .addGroup(jAnalogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jAnalogLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jAnaSpannung_G))
                    .addGroup(jAnalogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jAnaSpannung_W)
                        .addComponent(jLabel92)))
                .addContainerGap(125, Short.MAX_VALUE))
        );

        jDecodereigenschaften.addTab(bundle.getString("LDG33.jAnalog.TabConstraints.tabTitle"), jAnalog); // NOI18N

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
        jLabel36.setText(bundle.getString("LDG33.jLabel36.text")); // NOI18N

        javax.swing.GroupLayout jKommentarLayout = new javax.swing.GroupLayout(jKommentar);
        jKommentar.setLayout(jKommentarLayout);
        jKommentarLayout.setHorizontalGroup(
            jKommentarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jKommentarLayout.createSequentialGroup()
                .addGroup(jKommentarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jKommentarLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE))
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                .addContainerGap())
        );

        jDecodereigenschaften.addTab(bundle.getString("LDG33.jKommentar.TabConstraints.tabTitle"), jKommentar); // NOI18N

        jSave.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jSave.setText(bundle.getString("LDG33.jSave.text")); // NOI18N
        jSave.setToolTipText(bundle.getString("LDG33.jSave.toolTipText")); // NOI18N
        jSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSaveActionPerformed(evt);
            }
        });

        jOpen.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jOpen.setText(bundle.getString("LDG33.jOpen.text")); // NOI18N
        jOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jOpenActionPerformed(evt);
            }
        });

        jCV_LesenSchreiben.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCV_LesenSchreiben.setText(bundle.getString("LDG33.jCV_LesenSchreiben.text")); // NOI18N
        jCV_LesenSchreiben.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCV_LesenSchreibenActionPerformed(evt);
            }
        });

        jAbbrechen.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAbbrechen.setText(bundle.getString("LDG33.jAbbrechen.text")); // NOI18N
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
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jCV_Anzeige, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jCV_Inhalt, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jDirekteingabe)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel27)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jDecoderAdresse1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel28))))))
                    .addComponent(jDecodereigenschaften))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCV_LesenSchreiben, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jOpen, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jAbbrechen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                        .addGap(9, 9, 9)
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel28)
                            .addComponent(jDecoderAdresse1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jOpen, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSave, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jCV_LesenSchreiben, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(111, 111, 111)
                        .addComponent(jAbbrechen, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jDecodereigenschaften))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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
            switch (CVNavi.Decoder) {
                case c.LD_W33:
                    dt.jDecType.setText("Decoder: LD-W-33, Adresse: " + jDecoderAdresse.getText());
                    break;

                case c.LD_G33:
                    dt.jDecType.setText("Decoder: LD-G-33, Adresse: " + jDecoderAdresse.getText());
                    break;

                case c.LD_G34:
                    dt.jDecType.setText("Decoder: LD-G-34, Adresse: " + jDecoderAdresse.getText());
                    break;

                case c.FD_XL:
                    dt.jDecType.setText("Decoder: FD-XL, Adresse: " + jDecoderAdresse.getText());
                    break;

            }
        } else {
            switch (CVNavi.Decoder) {
                case c.LD_W33:
                    dt.jDecType.setText("decoder: LD-W-33, address: " + jDecoderAdresse.getText());
                    break;

                case c.LD_G33:
                    dt.jDecType.setText("decoder: LD-G-33, address: " + jDecoderAdresse.getText());
                    break;

                case c.LD_G34:
                    dt.jDecType.setText("decoder: LD-G-34, address: " + jDecoderAdresse.getText());
                    break;

                case c.FD_XL:
                    dt.jDecType.setText("decoder: FD-XL, address: " + jDecoderAdresse.getText());
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

    private void jAltKennlinieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAltKennlinieActionPerformed
        if(!jAltKennlinie.isSelected())
            CV[1][29] &= ~16;
        else
            CV[1][29] |= 16;
        jCV_Anzeige.setSelectedItem( "CV#"+29 );
    }//GEN-LAST:event_jAltKennlinieActionPerformed

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

        if((CV[1][29] & 32) == 32) {
            jLongAddr.setSelected(true);
            jKurzeAdr.setSelected(false);
            jlangeAdr.setSelected(true);
            int n = (CV[1][17] - 192)*256 + CV[1][18];
            jDecoderAdresse.setText( "" + n);
        } else {
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

        if((CV[1][33] & 8) == 8)
            jFL_4.setSelected(true);
        else
            jFL_4.setSelected(false);

        if((CV[1][33] & 0x10) == 0x10)
            jFL_5.setSelected(true);
        else
            jFL_5.setSelected(false);

        if((CV[1][33] & 0x20) == 0x20)
            jFL_6.setSelected(true);
        else
            jFL_6.setSelected(false);

        if((CV[1][33] & 0x40) == 0x40)
            jFL_7.setSelected(true);
        else
            jFL_7.setSelected(false);

        if((CV[1][33] & 0x80) == 0x80)
            jFL_8.setSelected(true);
        else
            jFL_8.setSelected(false);


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

        if((CV[1][34] & 8) == 8)
            jFR_4.setSelected(true);
        else
            jFR_4.setSelected(false);

        if((CV[1][34] & 16) == 16)
            jFR_5.setSelected(true);
        else
            jFR_5.setSelected(false);

        if((CV[1][34] & 32) == 32)
            jFR_6.setSelected(true);
        else
            jFR_6.setSelected(false);

        if((CV[1][34] & 0x40) == 0x40)
            jFR_7.setSelected(true);
        else
            jFR_7.setSelected(false);

        if((CV[1][34] & 0x80) == 0x80)
            jFR_8.setSelected(true);
        else
            jFR_8.setSelected(false);


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

        if((CV[1][35] & 8) == 8)
            jF1_4.setSelected(true);
        else
            jF1_4.setSelected(false);

        if((CV[1][35] & 16) == 16)
            jF1_5.setSelected(true);
        else
            jF1_5.setSelected(false);

        if((CV[1][35] & 32) == 32)
            jF1_6.setSelected(true);
        else
            jF1_6.setSelected(false);

        if((CV[1][35] & 0x40) == 0x40)
            jF1_7.setSelected(true);
        else
            jF1_7.setSelected(false);

        if((CV[1][35] & 0x80) == 0x80)
            jF1_8.setSelected(true);
        else
            jF1_8.setSelected(false);


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

        if((CV[1][36] & 8) == 8)
            jF2_4.setSelected(true);
        else
            jF2_4.setSelected(false);

        if((CV[1][36] & 16) == 16)
            jF2_5.setSelected(true);
        else
            jF2_5.setSelected(false);

        if((CV[1][36] & 32) == 32)
            jF2_6.setSelected(true);
        else
            jF2_6.setSelected(false);

        if((CV[1][36] & 0x40) == 0x40)
            jF2_7.setSelected(true);
        else
            jF2_7.setSelected(false);

        if((CV[1][36] & 0x80) == 0x80)
            jF2_8.setSelected(true);
        else
            jF2_8.setSelected(false);


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

        if((CV[1][37] & 8) == 8)
            jF3_4.setSelected(true);
        else
            jF3_4.setSelected(false);

        if((CV[1][37] & 16) == 16)
            jF3_5.setSelected(true);
        else
            jF3_5.setSelected(false);

        if((CV[1][37] & 32) == 32)
            jF3_6.setSelected(true);
        else
            jF3_6.setSelected(false);

        if((CV[1][37] & 0x40) == 0x40)
            jF3_7.setSelected(true);
        else
            jF3_7.setSelected(false);

        if((CV[1][37] & 0x80) == 0x80)
            jF3_8.setSelected(true);
        else
            jF3_8.setSelected(false);


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

        if((CV[1][38] & 8) == 8)
            jF4_4.setSelected(true);
        else
            jF4_4.setSelected(false);

        if((CV[1][38] & 16) == 16)
            jF4_5.setSelected(true);
        else
            jF4_5.setSelected(false);

        if((CV[1][38] & 32) == 32)
            jF4_6.setSelected(true);
        else
            jF4_6.setSelected(false);

        if((CV[1][38] & 0x40) == 0x40)
            jF4_7.setSelected(true);
        else
            jF4_7.setSelected(false);

        if((CV[1][38] & 0x80) == 0x80)
            jF4_8.setSelected(true);
        else
            jF4_8.setSelected(false);


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

        if((CV[1][39] & 8) == 8)
            jF5_4.setSelected(true);
        else
            jF5_4.setSelected(false);

        if((CV[1][39] & 16) == 16)
            jF5_5.setSelected(true);
        else
            jF5_5.setSelected(false);

        if((CV[1][39] & 32) == 32)
            jF5_6.setSelected(true);
        else
            jF5_6.setSelected(false);

        if((CV[1][39] & 0x40) == 0x40)
            jF5_7.setSelected(true);
        else
            jF5_7.setSelected(false);

        if((CV[1][39] & 0x80) == 0x80)
            jF5_8.setSelected(true);
        else
            jF5_8.setSelected(false);


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

        if((CV[1][40] & 8) == 8)
            jF6_4.setSelected(true);
        else
            jF6_4.setSelected(false);

        if((CV[1][40] & 16) == 16)
            jF6_5.setSelected(true);
        else
            jF6_5.setSelected(false);

        if((CV[1][40] & 32) == 32)
            jF6_6.setSelected(true);
        else
            jF6_6.setSelected(false);

        if((CV[1][40] & 0x40) == 0x40)
            jF6_7.setSelected(true);
        else
            jF6_7.setSelected(false);

        if((CV[1][40] & 0x80) == 0x80)
            jF6_8.setSelected(true);
        else
            jF6_8.setSelected(false);


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

        if((CV[1][41] & 8) == 8)
            jF7_4.setSelected(true);
        else
            jF7_4.setSelected(false);

        if((CV[1][41] & 16) == 16)
            jF7_5.setSelected(true);
        else
            jF7_5.setSelected(false);

        if((CV[1][41] & 32) == 32)
            jF7_6.setSelected(true);
        else
            jF7_6.setSelected(false);

        if((CV[1][41] & 0x40) == 0x40)
            jF7_7.setSelected(true);
        else
            jF7_7.setSelected(false);

        if((CV[1][41] & 0x80) == 0x80)
            jF7_8.setSelected(true);
        else
            jF7_8.setSelected(false);


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

        if((CV[1][42] & 8) == 8)
            jF8_4.setSelected(true);
        else
            jF8_4.setSelected(false);

        if((CV[1][42] & 16) == 16)
            jF8_5.setSelected(true);
        else
            jF8_5.setSelected(false);

        if((CV[1][42] & 32) == 32)
            jF8_6.setSelected(true);
        else
            jF8_6.setSelected(false);

        if((CV[1][42] & 0x40) == 0x40)
            jF8_7.setSelected(true);
        else
            jF8_7.setSelected(false);

        if((CV[1][42] & 0x80) == 0x80)
            jF8_8.setSelected(true);
        else
            jF8_8.setSelected(false);


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

        if((CV[1][43] & 8) == 8)
            jF9_4.setSelected(true);
        else
            jF9_4.setSelected(false);

        if((CV[1][43] & 16) == 16)
            jF9_5.setSelected(true);
        else
            jF9_5.setSelected(false);

        if((CV[1][43] & 32) == 32)
            jF9_6.setSelected(true);
        else
            jF9_6.setSelected(false);

        if((CV[1][43] & 0x40) == 0x40)
            jF9_7.setSelected(true);
        else
            jF9_7.setSelected(false);

        if((CV[1][43] & 0x80) == 0x80)
            jF9_8.setSelected(true);
        else
            jF9_8.setSelected(false);


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

        if((CV[1][44] & 8) == 8)
            jF10_4.setSelected(true);
        else
            jF10_4.setSelected(false);

        if((CV[1][44] & 16) == 16)
            jF10_5.setSelected(true);
        else
            jF10_5.setSelected(false);

        if((CV[1][44] & 32) == 32)
            jF10_6.setSelected(true);
        else
            jF10_6.setSelected(false);

        if((CV[1][44] & 0x40) == 0x40)
            jF10_7.setSelected(true);
        else
            jF10_7.setSelected(false);

        if((CV[1][44] & 0x80) == 0x80)
            jF10_8.setSelected(true);
        else
            jF10_8.setSelected(false);


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

        if((CV[1][45] & 8) == 8)
            jF11_4.setSelected(true);
        else
            jF11_4.setSelected(false);

        if((CV[1][45] & 16) == 16)
            jF11_5.setSelected(true);
        else
            jF11_5.setSelected(false);

        if((CV[1][45] & 32) == 32)
            jF11_6.setSelected(true);
        else
            jF11_6.setSelected(false);

        if((CV[1][45] & 0x40) == 0x40)
            jF11_7.setSelected(true);
        else
            jF11_7.setSelected(false);

        if((CV[1][45] & 0x80) == 0x80)
            jF11_8.setSelected(true);
        else
            jF11_8.setSelected(false);


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

        if((CV[1][46] & 8) == 8)
            jF12_4.setSelected(true);
        else
            jF12_4.setSelected(false);

        if((CV[1][46] & 16) == 16)
            jF12_5.setSelected(true);
        else
            jF12_5.setSelected(false);

        if((CV[1][46] & 32) == 32)
            jF12_6.setSelected(true);
        else
            jF12_6.setSelected(false);

        if((CV[1][46] & 0x40) == 0x40)
            jF12_7.setSelected(true);
        else
            jF12_7.setSelected(false);

        if((CV[1][46] & 0x80) == 0x80)
            jF12_8.setSelected(true);
        else
            jF12_8.setSelected(false);
}//GEN-LAST:event_jFunctionMappingComponentShown

    private void jAltDim1einActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAltDim1einActionPerformed
        CV[1][116] |= 1;
        jCV_Anzeige.setSelectedItem( "CV#"+116 );
        jCV_Inhalt.setText("" + CV[1][116]);
    }//GEN-LAST:event_jAltDim1einActionPerformed

    private void jAltDim2einActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAltDim2einActionPerformed
        CV[1][116] |= 2;
        jCV_Anzeige.setSelectedItem( "CV#"+116 );
        jCV_Inhalt.setText("" + CV[1][116]);
    }//GEN-LAST:event_jAltDim2einActionPerformed

    private void jAltDim1ausActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAltDim1ausActionPerformed
        CV[1][116] &= ~1;
        jCV_Anzeige.setSelectedItem( "CV#"+116 );
        jCV_Inhalt.setText("" + CV[1][116]);
    }//GEN-LAST:event_jAltDim1ausActionPerformed

    private void jAltDim2ausActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAltDim2ausActionPerformed
        CV[1][116] &= ~2;
        jCV_Anzeige.setSelectedItem( "CV#"+116 );
        jCV_Inhalt.setText("" + CV[1][116]);
    }//GEN-LAST:event_jAltDim2ausActionPerformed

    private void jDimmenComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jDimmenComponentShown
        jDimmen1.setText("" + (CV[1][62] & 0x0F));
        jDimmen2.setText("" + ((CV[1][62] & 0xF0)>>4));
        jDimmen3.setText("" + (CV[1][63] & 0x0F));
        jDimmen4.setText("" + ((CV[1][63] & 0xF0)>>4));
        jDimmen5.setText("" + (CV[1][64] & 0x0F));
        jDimmen6.setText("" + ((CV[1][64] & 0xF0)>>4));
        jAltDimmenFS1.setText("" + CV[1][113]);
        jAltDimmen1.setText("" + (CV[1][118] & 0x0F));
        jAltDimmen2.setText("" + ((CV[1][118] & 0xF0)>>4));
        jAltDimmen3.setText("" + (CV[1][119] & 0x0F));
        jAltDimmen4.setText("" + ((CV[1][119] & 0xF0)>>4));
        jAltDimmen5.setText("" + (CV[1][120] & 0x0F));
        jAltDimmen6.setText("" + ((CV[1][120] & 0xF0)>>4));
        if((CV[1][116] & 1) == 1)
        {
            jAltDim1ein.setSelected(true);
            jAltDim1aus.setSelected(false);
        }
        else
        {
            jAltDim1ein.setSelected(false);
            jAltDim1aus.setSelected(true);
        }
        if((CV[1][116] & 2) == 2)
        {
            jAltDim2ein.setSelected(true);
            jAltDim2aus.setSelected(false);
        }
        else
        {
            jAltDim2ein.setSelected(false);
            jAltDim2aus.setSelected(true);
        }
        if((CV[1][116] & 4) == 4)
        {
            jAltDim3ein.setSelected(true);
            jAltDim3aus.setSelected(false);
        }
        else
        {
            jAltDim3ein.setSelected(false);
            jAltDim3aus.setSelected(true);
        }
        if((CV[1][116] & 8) == 8)
        {
            jAltDim4ein.setSelected(true);
            jAltDim4aus.setSelected(false);
        }
        else
        {
            jAltDim4ein.setSelected(false);
            jAltDim4aus.setSelected(true);
        }
        if((CV[1][116] & 0x10) == 0x10)
        {
            jAltDim5ein.setSelected(true);
            jAltDim5aus.setSelected(false);
        }
        else
        {
            jAltDim5ein.setSelected(false);
            jAltDim5aus.setSelected(true);
        }
        if((CV[1][116] & 0x20) == 0x20)
        {
            jAltDim6ein.setSelected(true);
            jAltDim6aus.setSelected(false);
        }
        else
        {
            jAltDim6ein.setSelected(false);
            jAltDim6aus.setSelected(true);
        }
        jCV_Anzeige.setSelectedItem( "CV#"+62 );
    }//GEN-LAST:event_jDimmenComponentShown

    private void jVor1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jVor1ActionPerformed
        if(!jVor1.isSelected()) {
            CV[1][53] |= 1;
        } else {
            CV[1][53] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+53 );
}//GEN-LAST:event_jVor1ActionPerformed

    private void jRueck1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRueck1ActionPerformed
        if(!jRueck1.isSelected()) {
            CV[1][53] |= 2;
        } else {
            CV[1][53] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+53 );
}//GEN-LAST:event_jRueck1ActionPerformed

    private void jRueck2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRueck2ActionPerformed
        if(!jRueck2.isSelected()) {
            CV[1][54] |= 2;
        } else {
            CV[1][54] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+54 );
}//GEN-LAST:event_jRueck2ActionPerformed

    private void jVor2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jVor2ActionPerformed
        if(!jVor2.isSelected()) {
            CV[1][54] |= 1;
        } else {
            CV[1][54] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+54 );
}//GEN-LAST:event_jVor2ActionPerformed

    private void jInvertBlink1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jInvertBlink1ActionPerformed
        if(jInvertBlink1.isSelected())
            CV[1][53] |= 8;
        else
            CV[1][53] &= ~8;
        jCV_Anzeige.setSelectedItem( "CV#"+53 );
    }//GEN-LAST:event_jInvertBlink1ActionPerformed

    private void jInvertBlink2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jInvertBlink2ActionPerformed
        if(jInvertBlink2.isSelected())
            CV[1][54] |= 8;
        else
            CV[1][54] &= ~8;
        jCV_Anzeige.setSelectedItem( "CV#"+54 );
    }//GEN-LAST:event_jInvertBlink2ActionPerformed

    private void jEffekteComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jEffekteComponentShown
        jCV_Anzeige.setSelectedItem( "CV#"+53 );

        jTast1.setText( "" + ((CV[1][53] & 0xF0) >> 4));
        jTast2.setText( "" + ((CV[1][54] & 0xF0) >> 4));
        jTast3.setText( "" + ((CV[1][55] & 0xF0) >> 4));
        jTast4.setText( "" + ((CV[1][56] & 0xF0) >> 4));
        jTast5.setText( "" + ((CV[1][57] & 0xF0) >> 4));
        jTast6.setText( "" + ((CV[1][58] & 0xF0) >> 4));

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
        if((CV[1][56] & 8) == 8) {
            jInvertBlink4.setSelected(true);
        } else {
            jInvertBlink4.setSelected(false);
        }
        if((CV[1][57] & 8) == 8) {
            jInvertBlink5.setSelected(true);
        } else {
            jInvertBlink5.setSelected(false);
        }
        if((CV[1][58] & 8) == 8) {
            jInvertBlink6.setSelected(true);
        } else {
            jInvertBlink6.setSelected(false);
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
        if((CV[1][56] & 1) == 1) {
            jVor4.setSelected(false);
        } else {
            jVor4.setSelected(true);
        }
        if((CV[1][56] & 2) == 2) {
            jRueck4.setSelected(false);
        } else {
            jRueck4.setSelected(true);
        }
        if((CV[1][57] & 1) == 1) {
            jVor5.setSelected(false);
        } else {
            jVor5.setSelected(true);
        }
        if((CV[1][57] & 2) == 2) {
            jRueck5.setSelected(false);
        } else {
            jRueck5.setSelected(true);
        }
        if((CV[1][58] & 1) == 1) {
            jVor6.setSelected(false);
        } else {
            jVor6.setSelected(true);
        }
        if((CV[1][58] & 2) == 2) {
            jRueck6.setSelected(false);
        } else {
            jRueck6.setSelected(true);
        }

        if((CV[1][121] & 1) == 1) {
            jF0AusF1.setSelected(true);
        } else {
            jF0AusF1.setSelected(false);
        }
        if((CV[1][121] & 2) == 2) {
            jF0AusF2.setSelected(true);
        } else {
            jF0AusF2.setSelected(false);
        }
        if((CV[1][121] & 4) == 4) {
            jF0AusF3.setSelected(true);
        } else {
            jF0AusF3.setSelected(false);
        }
        if((CV[1][121] & 8) == 8) {
            jF0AusF4.setSelected(true);
        } else {
            jF0AusF4.setSelected(false);
        }
        if((CV[1][121] & 16) == 16) {
            jF0AusF5.setSelected(true);
        } else {
            jF0AusF5.setSelected(false);
        }
        if((CV[1][121] & 32) == 32) {
            jF0AusF6.setSelected(true);
        } else {
            jF0AusF6.setSelected(false);
        }
        if((CV[1][121] & 64) == 64) {
            jF0AusF7.setSelected(true);
        } else {
            jF0AusF7.setSelected(false);
        }
        if((CV[1][121] & 128) == 128) {
            jF0AusF8.setSelected(true);
        } else {
            jF0AusF8.setSelected(false);
        }

        if((CV[1][122] & 1) == 1) {
            jF0AusAUX1.setSelected(true);
        } else {
            jF0AusAUX1.setSelected(false);
        }
        if((CV[1][122] & 2) == 2) {
            jF0AusAUX2.setSelected(true);
        } else {
            jF0AusAUX2.setSelected(false);
        }
        if((CV[1][122] & 4) == 4) {
            jF0AusAUX3.setSelected(true);
        } else {
            jF0AusAUX3.setSelected(false);
        }
        if((CV[1][122] & 8) == 8) {
            jF0AusAUX4.setSelected(true);
        } else {
            jF0AusAUX4.setSelected(false);
        }
        if((CV[1][122] & 16) == 16) {
            jF0AusAUX5.setSelected(true);
        } else {
            jF0AusAUX5.setSelected(false);
        }
        if((CV[1][122] & 32) == 32) {
            jF0AusAUX6.setSelected(true);
        } else {
            jF0AusAUX6.setSelected(false);
        }

        if((CV[1][123] & 1) == 1) {
            jF0AusF1Aus.setSelected(true);
        } else {
            jF0AusF1Aus.setSelected(false);
        }
        if((CV[1][123] & 2) == 2) {
            jF0AusF2Aus.setSelected(true);
        } else {
            jF0AusF2Aus.setSelected(false);
        }
        if((CV[1][123] & 4) == 4) {
            jF0AusF3Aus.setSelected(true);
        } else {
            jF0AusF3Aus.setSelected(false);
        }
        if((CV[1][123] & 8) == 8) {
            jF0AusF4Aus.setSelected(true);
        } else {
            jF0AusF4Aus.setSelected(false);
        }
    }//GEN-LAST:event_jEffekteComponentShown

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

    private void jRL_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRL_1ActionPerformed
        if(jRL_1.isSelected()) {
            CV[1][115] |= 1;
        } else {
            CV[1][115] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+115 );
}//GEN-LAST:event_jRL_1ActionPerformed

    private void jRL_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRL_2ActionPerformed
        if(jRL_2.isSelected()) {
            CV[1][115] |= 2;
        } else {
            CV[1][115] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+115 );
}//GEN-LAST:event_jRL_2ActionPerformed

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

    private void jRangierenComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jRangierenComponentShown
        jCV_Anzeige.setSelectedItem( "CV#"+49 );
        
        if((CV[1][49] & 32) == 32)
            jAB_F3.setSelected(true);
        else
            jAB_F3.setSelected(false);

        if((CV[1][49] & 64) == 64)
            jAB_F4.setSelected(true);
        else
            jAB_F4.setSelected(false);
        
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

        if((CV[1][115] & 8) == 8)
            jRL_4.setSelected(true);
        else
            jRL_4.setSelected(false);
        
        if((CV[1][115] & 16) == 16)
            jRL_5.setSelected(true);
        else
            jRL_5.setSelected(false);

        if((CV[1][115] & 32) == 32)
            jRL_6.setSelected(true);
        else
            jRL_6.setSelected(false);
        
        jKickZeit1.setText("" + (CV[1][59] & 0x0F));
        jKickZeit2.setText("" + ((CV[1][59] & 0xF0)>>4));
        jKickZeit3.setText("" + (CV[1][60] & 0x0F));
        jKickZeit4.setText("" + ((CV[1][60] & 0xF0)>>4));
        jKickZeit5.setText("" + (CV[1][61] & 0x0F));
        jKickZeit6.setText("" + ((CV[1][61] & 0xF0)>>4));

        if((CV[1][47] & 1) == 1)
            jAR_1.setSelected(true);
        else
            jAR_1.setSelected(false);

        if((CV[1][47] & 2) == 2)
            jAR_2.setSelected(true);
        else
            jAR_2.setSelected(false);

        if((CV[1][47] & 4) == 4)
            jAR_3.setSelected(true);
        else
            jAR_3.setSelected(false);

        if((CV[1][47] & 8) == 8)
            jAR_4.setSelected(true);
        else
            jAR_4.setSelected(false);

        if((CV[1][47] & 16) == 16)
            jAR_5.setSelected(true);
        else
            jAR_5.setSelected(false);

        if((CV[1][47] & 32) == 32)
            jAR_6.setSelected(true);
        else
            jAR_6.setSelected(false);

        jAbrueckGeschw.setText("" + CV[1][48]);
    }//GEN-LAST:event_jRangierenComponentShown

    private void jMotorListeMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMotorListeMouseReleased
        setMotorTyp();
    }//GEN-LAST:event_jMotorListeMouseReleased

    private void jPosBremsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPosBremsActionPerformed
        if(!jPosBrems.isSelected())
            CV[1][27] &= ~16;
        else
            CV[1][27] |= 16;
        jCV_Anzeige.setSelectedItem( "CV#"+27 );
    }//GEN-LAST:event_jPosBremsActionPerformed

    private void jNegBremsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jNegBremsActionPerformed
        if(!jNegBrems.isSelected())
            CV[1][27] &= ~32;
        else
            CV[1][27] |= 32;
        jCV_Anzeige.setSelectedItem( "CV#"+27 );
    }//GEN-LAST:event_jNegBremsActionPerformed

    private void j480HzActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_j480HzActionPerformed
        CV[1][9] = 0;
        jCV_Anzeige.setSelectedItem( "CV#"+9 );
        jCV_Inhalt.setText("" + CV[1][9]);
}//GEN-LAST:event_j480HzActionPerformed

    private void j80HzActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_j80HzActionPerformed
        CV[1][9] = 1;
        jCV_Anzeige.setSelectedItem( "CV#"+9 );
        jCV_Inhalt.setText("" + CV[1][9]);
}//GEN-LAST:event_j80HzActionPerformed

    private void jFahreigenschaftenComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jFahreigenschaftenComponentShown
        jAnfahrKick.setText("" + CV[1][65]);
        jAnfahrGeschw.setText("" + CV[1][2]);
        jAnfahrVerz.setText("" + CV[1][3]);
        jBremsVerz.setText("" + CV[1][4]);
        jVMax.setText("" + CV[1][5]);
        jMotorArt.setText("" + CV[1][124]);
        switch(CVNavi.Decoder) {
            case c.LD_W33:
                jUeberlast.setVisible(false);
                j480Hz.setVisible(false);
                j80Hz.setVisible(false);
                jCV117_CV9_Text.setVisible(false);
                break;

            case c.LD_G33:
                break;

            case c.LD_G34:
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
        for (int i = 0; i < 28; i++) {
            CV[1][67 + i] = c1[i];
        }
        jCV_Anzeige.setSelectedItem( "CV#"+67 );
    }//GEN-LAST:event_jKurve4ActionPerformed

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

    private void jAnalogComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jAnalogComponentShown
        if((CV[1][12] & 1) == 1)
        {
            jAnaSpannung_W.setSelected(false);
            jAnaSpannung_G.setSelected(true);
        }
        else
        {
            jAnaSpannung_W.setSelected(true);
            jAnaSpannung_G.setSelected(false);
        }
        
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
        switch(CVNavi.Decoder) {
            case c.LD_W33:     //LD-W-33
                CVs = "LD-W-33\r\n";
                extension = "ld33";
                break;

            case c.LD_G33:     //LD-G-33
                CVs = "LD-G-33\r\n";
                extension = "ld33";
                break;

            case c.LD_G34:     //LD-G-34
                CVs = "LD-G-34\r\n";
                extension = "ld33";
                break;

            case c.FD_XL:     //FD-XL
                CVs = "FD-XL\r\n";
                extension = "fdxl";
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
        switch(CVNavi.Decoder) {
            case c.LD_W33:
            case c.LD_G33:
            case c.LD_G34:
                od = new SaveOpenDialog( this, true, true, CVs, this, "ld33");
                break;

            case c.FD_XL:
                od = new SaveOpenDialog( this, true, true, CVs, this, "fdxl");
                break;
        }
        updateTabs();
}//GEN-LAST:event_jOpenActionPerformed

    private void jCV_LesenSchreibenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCV_LesenSchreibenActionPerformed
        try {
            // Hier wird die Schnittstelle geöffnet und die CVs von der Zentrale gelesen
            ReadWriteCV cvwr = new ReadWriteCV(this, true, CVNavi, CV);
        } catch (IOException ex) {
            CVNavi.mbDeviceReadProblem( this );
        }
}//GEN-LAST:event_jCV_LesenSchreibenActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        CVNavi.frameInstanceDEVICE = null;
        CVNavi.setFocus();
    }//GEN-LAST:event_formWindowClosed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        updateTabs();
    }//GEN-LAST:event_formWindowActivated

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

    private void jF9_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF9_3ActionPerformed
        if(jF9_3.isSelected()) {
            CV[1][43] |= 4;
        } else {
            CV[1][43] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+43 );
    }//GEN-LAST:event_jF9_3ActionPerformed

    private void jF8_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF8_3ActionPerformed
        if(jF8_3.isSelected()) {
            CV[1][42] |= 4;
        } else {
            CV[1][42] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+42 );
    }//GEN-LAST:event_jF8_3ActionPerformed

    private void jF7_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF7_3ActionPerformed
        if(jF7_3.isSelected()) {
            CV[1][41] |= 4;
        } else {
            CV[1][41] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+41 );
    }//GEN-LAST:event_jF7_3ActionPerformed

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

    private void jF6_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF6_3ActionPerformed
        if(jF6_3.isSelected()) {
            CV[1][40] |= 4;
        } else {
            CV[1][40] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+40 );
    }//GEN-LAST:event_jF6_3ActionPerformed

    private void jF12_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF12_3ActionPerformed
        if(jF12_3.isSelected()) {
            CV[1][46] |= 4;
        } else {
            CV[1][46] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+46 );
    }//GEN-LAST:event_jF12_3ActionPerformed

    private void jF12_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF12_4ActionPerformed
        if(jF12_4.isSelected()) {
            CV[1][46] |= 8;
        } else {
            CV[1][46] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+46 );
    }//GEN-LAST:event_jF12_4ActionPerformed

    private void jFL_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFL_4ActionPerformed
        if(jFL_4.isSelected()) {
            CV[1][33] |= 8;
        } else {
            CV[1][33] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+33 );
    }//GEN-LAST:event_jFL_4ActionPerformed

    private void jFR_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFR_4ActionPerformed
        if(jFR_4.isSelected()) {
            CV[1][34] |= 8;
        } else {
            CV[1][34] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+34 );
    }//GEN-LAST:event_jFR_4ActionPerformed

    private void jF1_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF1_4ActionPerformed
        if(jF1_4.isSelected()) {
            CV[1][35] |= 8;
        } else {
            CV[1][35] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+35 );
    }//GEN-LAST:event_jF1_4ActionPerformed

    private void jF2_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF2_4ActionPerformed
        if(jF2_4.isSelected()) {
            CV[1][36] |= 8;
        } else {
            CV[1][36] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+36 );
    }//GEN-LAST:event_jF2_4ActionPerformed

    private void jF3_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF3_4ActionPerformed
        if(jF3_4.isSelected()) {
            CV[1][37] |= 8;
        } else {
            CV[1][37] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+37 );
    }//GEN-LAST:event_jF3_4ActionPerformed

    private void jF4_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF4_4ActionPerformed
        if(jF4_4.isSelected()) {
            CV[1][38] |= 8;
        } else {
            CV[1][38] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+38 );
    }//GEN-LAST:event_jF4_4ActionPerformed

    private void jF5_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF5_4ActionPerformed
        if(jF5_4.isSelected()) {
            CV[1][39] |= 8;
        } else {
            CV[1][39] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+39 );
    }//GEN-LAST:event_jF5_4ActionPerformed

    private void jF6_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF6_4ActionPerformed
        if(jF6_4.isSelected()) {
            CV[1][40] |= 8;
        } else {
            CV[1][40] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+40 );
    }//GEN-LAST:event_jF6_4ActionPerformed

    private void jF7_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF7_4ActionPerformed
        if(jF7_4.isSelected()) {
            CV[1][41] |= 8;
        } else {
            CV[1][41] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+41 );
    }//GEN-LAST:event_jF7_4ActionPerformed

    private void jF8_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF8_4ActionPerformed
        if(jF8_4.isSelected()) {
            CV[1][42] |= 8;
        } else {
            CV[1][42] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+42 );
    }//GEN-LAST:event_jF8_4ActionPerformed

    private void jF9_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF9_4ActionPerformed
        if(jF9_4.isSelected()) {
            CV[1][43] |= 8;
        } else {
            CV[1][43] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+43 );
    }//GEN-LAST:event_jF9_4ActionPerformed

    private void jF10_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF10_4ActionPerformed
        if(jF10_4.isSelected()) {
            CV[1][44] |= 8;
        } else {
            CV[1][44] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+44 );
    }//GEN-LAST:event_jF10_4ActionPerformed

    private void jF11_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF11_4ActionPerformed
        if(jF11_4.isSelected()) {
            CV[1][45] |= 8;
        } else {
            CV[1][45] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+45 );
    }//GEN-LAST:event_jF11_4ActionPerformed

    private void jFL_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFL_5ActionPerformed
        if(jFL_5.isSelected()) {
            CV[1][33] |= 0x10;
        } else {
            CV[1][33] &= ~0x10;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+33 );
    }//GEN-LAST:event_jFL_5ActionPerformed

    private void jFR_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFR_5ActionPerformed
        if(jFR_5.isSelected()) {
            CV[1][34] |= 0x10;
        } else {
            CV[1][34] &= ~0x10;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+34 );
    }//GEN-LAST:event_jFR_5ActionPerformed

    private void jF1_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF1_5ActionPerformed
        if(jF1_5.isSelected()) {
            CV[1][35] |= 0x10;
        } else {
            CV[1][35] &= ~0x10;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+35 );
    }//GEN-LAST:event_jF1_5ActionPerformed

    private void jF2_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF2_5ActionPerformed
        if(jF2_5.isSelected()) {
            CV[1][36] |= 0x10;
        } else {
            CV[1][36] &= ~0x10;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+36 );
    }//GEN-LAST:event_jF2_5ActionPerformed

    private void jF3_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF3_5ActionPerformed
        if(jF3_5.isSelected()) {
            CV[1][37] |= 0x10;
        } else {
            CV[1][37] &= ~0x10;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+37 );
    }//GEN-LAST:event_jF3_5ActionPerformed

    private void jF4_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF4_5ActionPerformed
        if(jF4_5.isSelected()) {
            CV[1][38] |= 0x10;
        } else {
            CV[1][38] &= ~0x10;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+38 );
    }//GEN-LAST:event_jF4_5ActionPerformed

    private void jF5_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF5_5ActionPerformed
        if(jF5_5.isSelected()) {
            CV[1][39] |= 0x10;
        } else {
            CV[1][39] &= ~0x10;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+39 );
    }//GEN-LAST:event_jF5_5ActionPerformed

    private void jF9_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF9_5ActionPerformed
        if(jF9_5.isSelected()) {
            CV[1][43] |= 0x10;
        } else {
            CV[1][43] &= ~0x10;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+43 );
    }//GEN-LAST:event_jF9_5ActionPerformed

    private void jF8_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF8_5ActionPerformed
        if(jF8_5.isSelected()) {
            CV[1][42] |= 0x10;
        } else {
            CV[1][42] &= ~0x10;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+42 );
    }//GEN-LAST:event_jF8_5ActionPerformed

    private void jF7_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF7_5ActionPerformed
        if(jF7_5.isSelected()) {
            CV[1][41] |= 0x10;
        } else {
            CV[1][41] &= ~0x10;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+41 );
    }//GEN-LAST:event_jF7_5ActionPerformed

    private void jF10_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF10_5ActionPerformed
        if(jF10_5.isSelected()) {
            CV[1][44] |= 0x10;
        } else {
            CV[1][44] &= ~0x10;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+44 );
    }//GEN-LAST:event_jF10_5ActionPerformed

    private void jF11_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF11_5ActionPerformed
        if(jF11_5.isSelected()) {
            CV[1][45] |= 0x10;
        } else {
            CV[1][45] &= ~0x10;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+45 );
    }//GEN-LAST:event_jF11_5ActionPerformed

    private void jF6_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF6_5ActionPerformed
        if(jF6_5.isSelected()) {
            CV[1][40] |= 0x10;
        } else {
            CV[1][40] &= ~0x20;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+40 );
    }//GEN-LAST:event_jF6_5ActionPerformed

    private void jF12_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF12_5ActionPerformed
        if(jF12_5.isSelected()) {
            CV[1][46] |= 0x10;
        } else {
            CV[1][46] &= ~0x10;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+46 );
    }//GEN-LAST:event_jF12_5ActionPerformed

    private void jF12_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF12_6ActionPerformed
        if(jF12_6.isSelected()) {
            CV[1][46] |= 0x20;
        } else {
            CV[1][46] &= ~0x20;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+46 );
    }//GEN-LAST:event_jF12_6ActionPerformed

    private void jFL_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFL_6ActionPerformed
        if(jFL_6.isSelected()) {
            CV[1][33] |= 0x20;
        } else {
            CV[1][33] &= ~0x20;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+33 );
    }//GEN-LAST:event_jFL_6ActionPerformed

    private void jFR_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFR_6ActionPerformed
        if(jFR_6.isSelected()) {
            CV[1][34] |= 0x20;
        } else {
            CV[1][34] &= ~0x20;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+34 );
    }//GEN-LAST:event_jFR_6ActionPerformed

    private void jF1_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF1_6ActionPerformed
        if(jF1_6.isSelected()) {
            CV[1][35] |= 0x20;
        } else {
            CV[1][35] &= ~0x20;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+35 );
    }//GEN-LAST:event_jF1_6ActionPerformed

    private void jF2_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF2_6ActionPerformed
        if(jF2_6.isSelected()) {
            CV[1][36] |= 0x20;
        } else {
            CV[1][36] &= ~0x20;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+36 );
    }//GEN-LAST:event_jF2_6ActionPerformed

    private void jF3_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF3_6ActionPerformed
        if(jF3_6.isSelected()) {
            CV[1][37] |= 0x20;
        } else {
            CV[1][37] &= ~0x20;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+37 );
    }//GEN-LAST:event_jF3_6ActionPerformed

    private void jF4_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF4_6ActionPerformed
        if(jF4_6.isSelected()) {
            CV[1][38] |= 0x20;
        } else {
            CV[1][38] &= ~0x20;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+38 );
    }//GEN-LAST:event_jF4_6ActionPerformed

    private void jF5_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF5_6ActionPerformed
        if(jF5_6.isSelected()) {
            CV[1][39] |= 0x20;
        } else {
            CV[1][39] &= ~0x20;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+39 );
    }//GEN-LAST:event_jF5_6ActionPerformed

    private void jF6_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF6_6ActionPerformed
        if(jF6_6.isSelected()) {
            CV[1][40] |= 0x20;
        } else {
            CV[1][40] &= ~0x20;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+40 );
    }//GEN-LAST:event_jF6_6ActionPerformed

    private void jF7_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF7_6ActionPerformed
        if(jF7_6.isSelected()) {
            CV[1][41] |= 0x20;
        } else {
            CV[1][41] &= ~0x20;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+41 );
    }//GEN-LAST:event_jF7_6ActionPerformed

    private void jF8_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF8_6ActionPerformed
        if(jF8_6.isSelected()) {
            CV[1][42] |= 0x20;
        } else {
            CV[1][42] &= ~0x20;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+42 );
    }//GEN-LAST:event_jF8_6ActionPerformed

    private void jF9_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF9_6ActionPerformed
        if(jF9_6.isSelected()) {
            CV[1][43] |= 0x20;
        } else {
            CV[1][43] &= ~0x20;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+43 );
    }//GEN-LAST:event_jF9_6ActionPerformed

    private void jF10_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF10_6ActionPerformed
        if(jF10_6.isSelected()) {
            CV[1][44] |= 0x20;
        } else {
            CV[1][44] &= ~0x20;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+44 );
    }//GEN-LAST:event_jF10_6ActionPerformed

    private void jF11_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF11_6ActionPerformed
        if(jF11_6.isSelected()) {
            CV[1][45] |= 0x20;
        } else {
            CV[1][45] &= ~0x20;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+45 );
    }//GEN-LAST:event_jF11_6ActionPerformed

    private void jAltDim3einActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAltDim3einActionPerformed
        CV[1][116] |= 4;
        jCV_Anzeige.setSelectedItem( "CV#"+116 );
        jCV_Inhalt.setText("" + CV[1][116]);
}//GEN-LAST:event_jAltDim3einActionPerformed

    private void jAltDim3ausActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAltDim3ausActionPerformed
        CV[1][116] &= ~4;
        jCV_Anzeige.setSelectedItem( "CV#"+116 );
        jCV_Inhalt.setText("" + CV[1][116]);
}//GEN-LAST:event_jAltDim3ausActionPerformed

    private void jAltDim4einActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAltDim4einActionPerformed
        CV[1][116] |= 8;
        jCV_Anzeige.setSelectedItem( "CV#"+116 );
        jCV_Inhalt.setText("" + CV[1][116]);
}//GEN-LAST:event_jAltDim4einActionPerformed

    private void jAltDim4ausActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAltDim4ausActionPerformed
        CV[1][116] &= ~8;
        jCV_Anzeige.setSelectedItem( "CV#"+116 );
        jCV_Inhalt.setText("" + CV[1][116]);
}//GEN-LAST:event_jAltDim4ausActionPerformed

    private void jAltDim5einActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAltDim5einActionPerformed
        CV[1][116] |= 0x10;
        jCV_Anzeige.setSelectedItem( "CV#"+116 );
        jCV_Inhalt.setText("" + CV[1][116]);
}//GEN-LAST:event_jAltDim5einActionPerformed

    private void jAltDim5ausActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAltDim5ausActionPerformed
        CV[1][116] &= ~0x10;
        jCV_Anzeige.setSelectedItem( "CV#"+116 );
        jCV_Inhalt.setText("" + CV[1][116]);
}//GEN-LAST:event_jAltDim5ausActionPerformed

    private void jAltDim6einActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAltDim6einActionPerformed
        CV[1][116] |= 0x20;
        jCV_Anzeige.setSelectedItem( "CV#"+116 );
        jCV_Inhalt.setText("" + CV[1][116]);
}//GEN-LAST:event_jAltDim6einActionPerformed

    private void jAltDim6ausActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAltDim6ausActionPerformed
        CV[1][116] &= ~0x20;
        jCV_Anzeige.setSelectedItem( "CV#"+116 );
        jCV_Inhalt.setText("" + CV[1][116]);
}//GEN-LAST:event_jAltDim6ausActionPerformed

    private void jInvertBlink3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jInvertBlink3ActionPerformed
        if(jInvertBlink3.isSelected())
            CV[1][55] |= 8;
        else
            CV[1][55] &= ~8;
        jCV_Anzeige.setSelectedItem( "CV#"+55 );
    }//GEN-LAST:event_jInvertBlink3ActionPerformed

    private void jInvertBlink4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jInvertBlink4ActionPerformed
        if(jInvertBlink4.isSelected())
            CV[1][56] |= 8;
        else
            CV[1][56] &= ~8;
        jCV_Anzeige.setSelectedItem( "CV#"+56 );
    }//GEN-LAST:event_jInvertBlink4ActionPerformed

    private void jInvertBlink5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jInvertBlink5ActionPerformed
        if(jInvertBlink5.isSelected())
            CV[1][57] |= 8;
        else
            CV[1][57] &= ~8;
        jCV_Anzeige.setSelectedItem( "CV#"+57 );
    }//GEN-LAST:event_jInvertBlink5ActionPerformed

    private void jInvertBlink6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jInvertBlink6ActionPerformed
        if(jInvertBlink6.isSelected())
            CV[1][58] |= 8;
        else
            CV[1][58] &= ~8;
        jCV_Anzeige.setSelectedItem( "CV#"+58 );
    }//GEN-LAST:event_jInvertBlink6ActionPerformed

    private void jVor3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jVor3ActionPerformed
        if(!jVor3.isSelected()) {
            CV[1][55] |= 1;
        } else {
            CV[1][55] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+55 );
    }//GEN-LAST:event_jVor3ActionPerformed

    private void jVor4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jVor4ActionPerformed
        if(!jVor4.isSelected()) {
            CV[1][56] |= 1;
        } else {
            CV[1][56] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+56 );
    }//GEN-LAST:event_jVor4ActionPerformed

    private void jVor5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jVor5ActionPerformed
        if(!jVor5.isSelected()) {
            CV[1][57] |= 1;
        } else {
            CV[1][57] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+57 );
    }//GEN-LAST:event_jVor5ActionPerformed

    private void jVor6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jVor6ActionPerformed
        if(!jVor6.isSelected()) {
            CV[1][58] |= 1;
        } else {
            CV[1][58] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+58 );
    }//GEN-LAST:event_jVor6ActionPerformed

    private void jRueck3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRueck3ActionPerformed
        if(!jRueck3.isSelected()) {
            CV[1][55] |= 2;
        } else {
            CV[1][55] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+55 );
    }//GEN-LAST:event_jRueck3ActionPerformed

    private void jRueck4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRueck4ActionPerformed
        if(!jRueck4.isSelected()) {
            CV[1][56] |= 2;
        } else {
            CV[1][56] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+56 );
    }//GEN-LAST:event_jRueck4ActionPerformed

    private void jRueck5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRueck5ActionPerformed
        if(!jRueck5.isSelected()) {
            CV[1][57] |= 2;
        } else {
            CV[1][57] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+57 );
    }//GEN-LAST:event_jRueck5ActionPerformed

    private void jRueck6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRueck6ActionPerformed
        if(!jRueck6.isSelected()) {
            CV[1][58] |= 2;
        } else {
            CV[1][58] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+58 );
    }//GEN-LAST:event_jRueck6ActionPerformed

    private void jRL_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRL_3ActionPerformed
        if(jRL_3.isSelected()) {
            CV[1][115] |= 4;
        } else {
            CV[1][115] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+115 );
    }//GEN-LAST:event_jRL_3ActionPerformed

    private void jRL_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRL_4ActionPerformed
        if(jRL_4.isSelected()) {
            CV[1][115] |= 8;
        } else {
            CV[1][115] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+115 );
    }//GEN-LAST:event_jRL_4ActionPerformed

    private void jRL_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRL_5ActionPerformed
        if(jRL_5.isSelected()) {
            CV[1][115] |= 0x10;
        } else {
            CV[1][115] &= ~0x10;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+115 );
    }//GEN-LAST:event_jRL_5ActionPerformed

    private void jRL_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRL_6ActionPerformed
        if(jRL_6.isSelected()) {
            CV[1][115] |= 0x20;
        } else {
            CV[1][115] &= ~0x20;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+115 );
    }//GEN-LAST:event_jRL_6ActionPerformed

    private void jAR_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAR_1ActionPerformed
        if(jAR_1.isSelected()) {
            CV[1][47] |= 1;
        } else {
            CV[1][47] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+47 );
    }//GEN-LAST:event_jAR_1ActionPerformed

    private void jAR_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAR_2ActionPerformed
        if(jAR_2.isSelected()) {
            CV[1][47] |= 2;
        } else {
            CV[1][47] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+47 );
    }//GEN-LAST:event_jAR_2ActionPerformed

    private void jAR_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAR_3ActionPerformed
        if(jAR_3.isSelected()) {
            CV[1][47] |= 4;
        } else {
            CV[1][47] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+47 );
    }//GEN-LAST:event_jAR_3ActionPerformed

    private void jAR_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAR_4ActionPerformed
        if(jAR_4.isSelected()) {
            CV[1][47] |= 8;
        } else {
            CV[1][47] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+47 );
    }//GEN-LAST:event_jAR_4ActionPerformed

    private void jAR_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAR_5ActionPerformed
        if(jAR_5.isSelected()) {
            CV[1][47] |= 0x10;
        } else {
            CV[1][47] &= ~0x10;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+47 );
    }//GEN-LAST:event_jAR_5ActionPerformed

    private void jAR_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAR_6ActionPerformed
        if(jAR_6.isSelected()) {
            CV[1][47] |= 0x20;
        } else {
            CV[1][47] &= ~0x20;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+47 );
    }//GEN-LAST:event_jAR_6ActionPerformed

    private void jF0AusF1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF0AusF1ActionPerformed
        if(jF0AusF1.isSelected()) {
            CV[1][121] |= 1;
        } else {
            CV[1][121] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+121 );
}//GEN-LAST:event_jF0AusF1ActionPerformed

    private void jF0AusF2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF0AusF2ActionPerformed
        if(jF0AusF2.isSelected()) {
            CV[1][121] |= 2;
        } else {
            CV[1][121] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+121 );
    }//GEN-LAST:event_jF0AusF2ActionPerformed

    private void jF0AusF3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF0AusF3ActionPerformed
        if(jF0AusF3.isSelected()) {
            CV[1][121] |= 4;
        } else {
            CV[1][121] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+121 );
    }//GEN-LAST:event_jF0AusF3ActionPerformed

    private void jF0AusF4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF0AusF4ActionPerformed
        if(jF0AusF4.isSelected()) {
            CV[1][121] |= 8;
        } else {
            CV[1][121] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+121 );
    }//GEN-LAST:event_jF0AusF4ActionPerformed

    private void jF0AusF5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF0AusF5ActionPerformed
        if(jF0AusF5.isSelected()) {
            CV[1][121] |= 0x10;
        } else {
            CV[1][121] &= ~0x10;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+121 );
    }//GEN-LAST:event_jF0AusF5ActionPerformed

    private void jF0AusF6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF0AusF6ActionPerformed
        if(jF0AusF6.isSelected()) {
            CV[1][121] |= 0x20;
        } else {
            CV[1][121] &= ~0x20;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+121 );
    }//GEN-LAST:event_jF0AusF6ActionPerformed

    private void jF0AusF7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF0AusF7ActionPerformed
        if(jF0AusF7.isSelected()) {
            CV[1][121] |= 0x40;
        } else {
            CV[1][121] &= ~0x40;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+121 );
    }//GEN-LAST:event_jF0AusF7ActionPerformed

    private void jF0AusF8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF0AusF8ActionPerformed
        if(jF0AusF8.isSelected()) {
            CV[1][121] |= 0x80;
        } else {
            CV[1][121] &= ~0x80;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+121 );
    }//GEN-LAST:event_jF0AusF8ActionPerformed

    private void jF0AusAUX1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF0AusAUX1ActionPerformed
        if(jF0AusAUX1.isSelected()) {
            CV[1][122] |= 1;
        } else {
            CV[1][122] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+122 );
}//GEN-LAST:event_jF0AusAUX1ActionPerformed

    private void jF0AusAUX2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF0AusAUX2ActionPerformed
        if(jF0AusAUX2.isSelected()) {
            CV[1][122] |= 2;
        } else {
            CV[1][122] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+122 );
    }//GEN-LAST:event_jF0AusAUX2ActionPerformed

    private void jF0AusAUX3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF0AusAUX3ActionPerformed
        if(jF0AusAUX3.isSelected()) {
            CV[1][122] |= 4;
        } else {
            CV[1][122] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+122 );
    }//GEN-LAST:event_jF0AusAUX3ActionPerformed

    private void jF0AusAUX4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF0AusAUX4ActionPerformed
        if(jF0AusAUX4.isSelected()) {
            CV[1][122] |= 8;
        } else {
            CV[1][122] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+122 );
    }//GEN-LAST:event_jF0AusAUX4ActionPerformed

    private void jF0AusAUX5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF0AusAUX5ActionPerformed
        if(jF0AusAUX5.isSelected()) {
            CV[1][122] |= 0x10;
        } else {
            CV[1][122] &= ~0x10;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+122 );
    }//GEN-LAST:event_jF0AusAUX5ActionPerformed

    private void jF0AusAUX6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF0AusAUX6ActionPerformed
        if(jF0AusAUX6.isSelected()) {
            CV[1][122] |= 0x20;
        } else {
            CV[1][122] &= ~0x20;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+122 );
    }//GEN-LAST:event_jF0AusAUX6ActionPerformed

    private void jF0AusF1AusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF0AusF1AusActionPerformed
        if(jF0AusF1Aus.isSelected()) {
            CV[1][123] |= 1;
        } else {
            CV[1][123] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+123 );
}//GEN-LAST:event_jF0AusF1AusActionPerformed

    private void jF0AusF2AusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF0AusF2AusActionPerformed
        if(jF0AusF2Aus.isSelected()) {
            CV[1][123] |= 2;
        } else {
            CV[1][123] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+123 );
}//GEN-LAST:event_jF0AusF2AusActionPerformed

    private void jF0AusF3AusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF0AusF3AusActionPerformed
        if(jF0AusF3Aus.isSelected()) {
            CV[1][123] |= 4;
        } else {
            CV[1][123] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+123 );
}//GEN-LAST:event_jF0AusF3AusActionPerformed

    private void jF0AusF4AusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF0AusF4AusActionPerformed
        if(jF0AusF4Aus.isSelected()) {
            CV[1][123] |= 8;
        } else {
            CV[1][123] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+123 );
}//GEN-LAST:event_jF0AusF4AusActionPerformed

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

    private void jAnaSpannung_WActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAnaSpannung_WActionPerformed
        CV[1][12] = 0;
        jAnaSpannung_W.setSelected(true);
        jAnaSpannung_G.setSelected(false);
        jCV_Anzeige.setSelectedItem( "CV#"+12 );
    }//GEN-LAST:event_jAnaSpannung_WActionPerformed

    private void jAnaSpannung_GActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAnaSpannung_GActionPerformed
        CV[1][12] = 1;
        jAnaSpannung_W.setSelected(false);
        jAnaSpannung_G.setSelected(true);
        jCV_Anzeige.setSelectedItem( "CV#"+12 );
    }//GEN-LAST:event_jAnaSpannung_GActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // Sprache in Buttons einstellen
/*        if(!CVNavi.bSpracheDE) {
            int tabIndex = 0;
            jDirekteingabe.setText("Direct on");
            jKurzeAdr.setText("short Addr. (CV#1)");
            jlangeAdr.setText("long Addr. (CV#17 + 18)");
            jLabel28.setText("consist address");
            jLabel38.setText("blink frequency");
            jOpen.setText("Open");
            jSave.setText("Save");
            jCV_LesenSchreiben.setText("Read CV's");
            //Reiter "Allgemein"
            jDecodereigenschaften.setTitleAt(tabIndex++, "general");
            jLabel41.setText("second MM address");
            jRichtung.setText("change direction");
            jFS.setText("28 / 128 speed steps");
            jAnalog1.setText("analog mode");
            jRailCom.setText("RailCom on");
            jLongAddr.setText("long address");
            jAltKennlinie.setText("alternative characteristic curve");
            //Reiter F-Zuordnung
            jDecodereigenschaften.setTitleAt(tabIndex++, "function keys");
            jLabel33.setText("Fl = front light (F0)");
            jLabel3.setText("Fr = rear light (F0)");
            //Reiter Dimmen
            jDecodereigenschaften.setTitleAt(tabIndex++, "dim");
            jLabel23.setText("Dim (max 15)");
            jLabel43.setText("Alternative dim (max 15)");
            jLabel46.setText("(All AUX)");
            jLabel45.setText("Alternative dim above internal speed step");
            jAltDim1ein.setText("on");
            jAltDim2ein.setText("on");
            jAltDim3ein.setText("on");
            jAltDim4ein.setText("on");
            jAltDim5ein.setText("on");
            jAltDim6ein.setText("on");
            jAltDim1aus.setText("off");
            jAltDim2aus.setText("off");
            jAltDim3aus.setText("off");
            jAltDim4aus.setText("off");
            jAltDim5aus.setText("off");
            jAltDim6aus.setText("off");
            //Reiter Effekte
            jDecodereigenschaften.setTitleAt(tabIndex++, "effect");
            jLabel26.setText("Blink (0=off)");
            jLabel25.setText("duty cycle");
            jLabel62.setText("inverting");
            jLabel83.setText("direction");
            jLabel30.setText("dependent");
            jLabel71.setText("forw.on");
            jLabel32.setText("back.on");
            jLabel84.setText("behavior on speed step 0");
            jLabel85.setText("if F0 off with...");
            jLabel86.setText("than following outputs on:");
            jLabel82.setText("and following functions off:");
            //Reiter Rangieren
            jDecodereigenschaften.setTitleAt(tabIndex++, "shunting");
            jLabel59.setText("Acc. / Dec. off with function:");
            jLabel31.setText("switch on shunting light with:");
            jLabel76.setText("activate shunting gear with:");
            jLabel72.setText("shunting light on AUX:");
            jLabel75.setText("AUX and function keys for decoupling must be defined with F-key definition.");
            jLabel73.setText("active decoupling time:");
            jLabel74.setText("drive off the cabs if AUX is active:");
            jLabel29.setText("velocitiy while driving off:");
            if( CVNavi.Decoder != c.FD_XL ) { // not FD-XL
                // beim FD-XL sind Fahreigenschaften und Kennlinie nicht vorhanden,
                // deshalb nur bei allen anderen aendern
                
                //Reiter Fahreigenschaften
                jDecodereigenschaften.setTitleAt(tabIndex++, "driving behavior");
                jLabel49.setText("(velocity) speed step 1:");
                jLabel50.setText("acceleration:");
                jLabel51.setText("deceleration:");
                jLabel52.setText("max speed:");
                jLabel53.setText("load control:");
                jLabel57.setText("type of motor:");
                jLabel60.setText("motor adjustment:");
                jLabel61.setText("kick start:");
                jLabel87.setText("motor frequency:");
                jCV117_CV9_Text.setText("overload:");
                jPosBrems.setText("deceleration with positive DC");
                jNegBrems.setText("deceleration with negative DC");
                //Reiter Kennlinie
                jDecodereigenschaften.setTitleAt(tabIndex++, "characteristic curve");
                jKurve1.setText("curve 1");
                jKurve2.setText("curve 2");
                jKurve3.setText("curve 3");
                jCustom1sichern.setText("save custom");
                jCustom1laden.setText("load custom");
            }
            //Reiter Analog
            jDecodereigenschaften.setTitleAt(tabIndex++, "analog mode");
            jLabel2.setText("active functions in analog mode");
            jAnaSpannung_W.setText("analog AC");
            jAnaSpannung_G.setText("analog DC");
            //Reiter Kommentar
            jDecodereigenschaften.setTitleAt(tabIndex++, "comment");
            jLabel36.setText("enter a comment");
        }*/
}//GEN-LAST:event_formWindowOpened

    private void jFL_7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFL_7ActionPerformed
        if(jFL_7.isSelected()) {
            CV[1][33] |= 0x40;
        } else {
            CV[1][33] &= ~0x40;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+33 );
    }//GEN-LAST:event_jFL_7ActionPerformed

    private void jFR_7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFR_7ActionPerformed
        if(jFR_7.isSelected()) {
            CV[1][34] |= 0x40;
        } else {
            CV[1][34] &= ~0x40;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+34 );
    }//GEN-LAST:event_jFR_7ActionPerformed

    private void jF1_7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF1_7ActionPerformed
        if(jF1_7.isSelected()) {
            CV[1][35] |= 0x40;
        } else {
            CV[1][35] &= ~0x40;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+35 );
    }//GEN-LAST:event_jF1_7ActionPerformed

    private void jF2_7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF2_7ActionPerformed
        if(jF2_7.isSelected()) {
            CV[1][36] |= 0x40;
        } else {
            CV[1][36] &= ~0x40;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+36 );
    }//GEN-LAST:event_jF2_7ActionPerformed

    private void jF3_7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF3_7ActionPerformed
        if(jF3_7.isSelected()) {
            CV[1][37] |= 0x40;
        } else {
            CV[1][37] &= ~0x40;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+37 );
    }//GEN-LAST:event_jF3_7ActionPerformed

    private void jF4_7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF4_7ActionPerformed
        if(jF4_7.isSelected()) {
            CV[1][38] |= 0x40;
        } else {
            CV[1][38] &= ~0x40;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+38 );
    }//GEN-LAST:event_jF4_7ActionPerformed

    private void jF5_7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF5_7ActionPerformed
        if(jF5_7.isSelected()) {
            CV[1][39] |= 0x40;
        } else {
            CV[1][39] &= ~0x40;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+39 );
    }//GEN-LAST:event_jF5_7ActionPerformed

    private void jF9_7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF9_7ActionPerformed
        if(jF9_7.isSelected()) {
            CV[1][43] |= 0x40;
        } else {
            CV[1][43] &= ~0x40;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+43 );
    }//GEN-LAST:event_jF9_7ActionPerformed

    private void jF8_7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF8_7ActionPerformed
        if(jF8_7.isSelected()) {
            CV[1][42] |= 0x40;
        } else {
            CV[1][42] &= ~0x40;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+42 );
    }//GEN-LAST:event_jF8_7ActionPerformed

    private void jF7_7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF7_7ActionPerformed
        if(jF7_7.isSelected()) {
            CV[1][41] |= 0x40;
        } else {
            CV[1][41] &= ~0x40;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+41 );
    }//GEN-LAST:event_jF7_7ActionPerformed

    private void jF10_7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF10_7ActionPerformed
        if(jF10_7.isSelected()) {
            CV[1][44] |= 0x40;
        } else {
            CV[1][44] &= ~0x40;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+44 );
    }//GEN-LAST:event_jF10_7ActionPerformed

    private void jF11_7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF11_7ActionPerformed
        if(jF11_7.isSelected()) {
            CV[1][45] |= 0x40;
        } else {
            CV[1][45] &= ~0x40;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+45 );
    }//GEN-LAST:event_jF11_7ActionPerformed

    private void jF6_7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF6_7ActionPerformed
        if(jF6_7.isSelected()) {
            CV[1][40] |= 0x40;
        } else {
            CV[1][40] &= ~0x40;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+40 );
    }//GEN-LAST:event_jF6_7ActionPerformed

    private void jF12_7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF12_7ActionPerformed
        if(jF12_7.isSelected()) {
            CV[1][46] |= 0x40;
        } else {
            CV[1][46] &= ~0x40;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+46 );
    }//GEN-LAST:event_jF12_7ActionPerformed

    private void jF12_8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF12_8ActionPerformed
        if(jF12_8.isSelected()) {
            CV[1][46] |= 0x80;
        } else {
            CV[1][46] &= ~0x80;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+46 );
    }//GEN-LAST:event_jF12_8ActionPerformed

    private void jFL_8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFL_8ActionPerformed
        if(jFL_8.isSelected()) {
            CV[1][33] |= 0x80;
        } else {
            CV[1][33] &= ~0x80;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+33 );
    }//GEN-LAST:event_jFL_8ActionPerformed

    private void jFR_8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFR_8ActionPerformed
        if(jFR_8.isSelected()) {
            CV[1][34] |= 0x80;
        } else {
            CV[1][34] &= ~0x80;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+34 );
    }//GEN-LAST:event_jFR_8ActionPerformed

    private void jF1_8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF1_8ActionPerformed
        if(jF1_8.isSelected()) {
            CV[1][35] |= 0x80;
        } else {
            CV[1][35] &= ~0x80;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+35 );
    }//GEN-LAST:event_jF1_8ActionPerformed

    private void jF2_8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF2_8ActionPerformed
        if(jF2_8.isSelected()) {
            CV[1][36] |= 0x80;
        } else {
            CV[1][36] &= ~0x80;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+36 );
    }//GEN-LAST:event_jF2_8ActionPerformed

    private void jF3_8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF3_8ActionPerformed
        if(jF3_8.isSelected()) {
            CV[1][37] |= 0x80;
        } else {
            CV[1][37] &= ~0x80;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+37 );
    }//GEN-LAST:event_jF3_8ActionPerformed

    private void jF4_8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF4_8ActionPerformed
        if(jF4_8.isSelected()) {
            CV[1][38] |= 0x80;
        } else {
            CV[1][38] &= ~0x80;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+38 );
    }//GEN-LAST:event_jF4_8ActionPerformed

    private void jF5_8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF5_8ActionPerformed
        if(jF5_8.isSelected()) {
            CV[1][39] |= 0x80;
        } else {
            CV[1][39] &= ~0x80;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+39 );
    }//GEN-LAST:event_jF5_8ActionPerformed

    private void jF6_8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF6_8ActionPerformed
        if(jF6_8.isSelected()) {
            CV[1][40] |= 0x80;
        } else {
            CV[1][40] &= ~0x80;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+40 );
    }//GEN-LAST:event_jF6_8ActionPerformed

    private void jF7_8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF7_8ActionPerformed
        if(jF7_8.isSelected()) {
            CV[1][41] |= 0x80;
        } else {
            CV[1][41] &= ~0x80;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+41 );
    }//GEN-LAST:event_jF7_8ActionPerformed

    private void jF8_8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF8_8ActionPerformed
        if(jF8_8.isSelected()) {
            CV[1][42] |= 0x80;
        } else {
            CV[1][42] &= ~0x80;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+42 );
    }//GEN-LAST:event_jF8_8ActionPerformed

    private void jF9_8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF9_8ActionPerformed
        if(jF9_8.isSelected()) {
            CV[1][43] |= 0x80;
        } else {
            CV[1][43] &= ~0x80;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+43 );
    }//GEN-LAST:event_jF9_8ActionPerformed

    private void jF10_8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF10_8ActionPerformed
        if(jF10_8.isSelected()) {
            CV[1][44] |= 0x80;
        } else {
            CV[1][44] &= ~0x80;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+44 );
    }//GEN-LAST:event_jF10_8ActionPerformed

    private void jF11_8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF11_8ActionPerformed
        if(jF11_8.isSelected()) {
            CV[1][45] |= 0x80;
        } else {
            CV[1][45] &= ~0x80;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+45 );
    }//GEN-LAST:event_jF11_8ActionPerformed

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
        if( CV_InhaltCheckInProgress ) {
            return;
        }
        CV_InhaltCheckInProgress = true;
        int adr;
        int currCV = getCVfromIndexString(jCV_Anzeige, "CV#");
        int cvValue = CVNavi.checkTextField( this, jCV_Inhalt, 0, 255, 22222, false);
        if( cvValue == 22222 ) {
            jCV_Inhalt.setText(""+CV[1][currCV]);
            CV_InhaltCheckInProgress = false;
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
                CV[1][currCV] = cvValue;
                jKurzeAdr.setSelected(true);
                jlangeAdr.setSelected(false);
                CV[1][29] &= ~32;
                jLongAddr.setSelected(false);
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
                switch(CVNavi.Decoder) {
                    case c.LD_G33:
                    case c.LD_G34:
                        cvValue = CVNavi.checkTextField( this, jCV_Inhalt, 0, 1, 0, true );
                        s = jCV_Inhalt.getText();
                        if( cvValue == 0 ) {
                            j480Hz.setSelected(true);
                            j80Hz.setSelected(false);
                        } else {
                            j480Hz.setSelected(false);
                            j80Hz.setSelected(true);
                        }
                        break;
                }
                break;

            case 12: //CV#12
                int oldValue = CV[1][12];
                cvValue = CVNavi.checkTextField( this, jCV_Inhalt, 0, 1, oldValue, true );
                CV[1][currCV] = cvValue;
                if((CV[1][12] & 1) == 1)
                {
                    jAnaSpannung_G.setSelected(true);
                    jAnaSpannung_W.setSelected(false);
                }
                else
                {
                    jAnaSpannung_G.setSelected(false);
                    jAnaSpannung_W.setSelected(true);
                }
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
                    // CVNavi.mbValueNaNcv( 192, 255, 17, false);
                    cvValue = 192;
                    jCV_Inhalt.setText("192");
                }
                CV[1][currCV] = cvValue;
                jKurzeAdr.setSelected(false);
                jlangeAdr.setSelected(true);
                CV[1][29] |= 32;
                jLongAddr.setSelected(true);
                adr = (CV[1][17] - 192)*256 + CV[1][18];
                jDecoderAdresse.setText(""+adr);
                break;

            case 18: //CV#18
                CV[1][currCV] = cvValue;
                jKurzeAdr.setSelected(false);
                jlangeAdr.setSelected(true);
                CV[1][29] |= 32;
                jLongAddr.setSelected(true);
                adr = (CV[1][17] - 192)*256 + CV[1][18];
                jDecoderAdresse.setText(""+adr);
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

                if((cvValue & 8) == 8 )
                    jFL_4.setSelected(true);
                else
                    jFL_4.setSelected(false);

                if((cvValue & 16) == 16 )
                    jFL_5.setSelected(true);
                else
                    jFL_5.setSelected(false);

                if((cvValue & 32) == 32 )
                    jFL_6.setSelected(true);
                else
                    jFL_6.setSelected(false);

                if((cvValue & 64) == 64 )
                    jFL_7.setSelected(true);
                else
                    jFL_7.setSelected(false);

                if((cvValue & 128) == 128 )
                    jFL_8.setSelected(true);
                else
                    jFL_8.setSelected(false);
                break;

            case 34: //CV#34
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

                if((cvValue & 8) == 8 )
                    jFR_4.setSelected(true);
                else
                    jFR_4.setSelected(false);

                if((cvValue & 16) == 16 )
                    jFR_5.setSelected(true);
                else
                    jFR_5.setSelected(false);

                if((cvValue & 32) == 32 )
                    jFR_6.setSelected(true);
                else
                    jFR_6.setSelected(false);

                if((cvValue & 64) == 64 )
                    jFR_7.setSelected(true);
                else
                    jFR_7.setSelected(false);

                if((cvValue & 128) == 128 )
                    jFR_8.setSelected(true);
                else
                    jFR_8.setSelected(false);
                break;

            case 35: //CV#35
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

                if((cvValue & 8) == 8 )
                    jF1_4.setSelected(true);
                else
                    jF1_4.setSelected(false);

                if((cvValue & 16) == 16 )
                    jF1_5.setSelected(true);
                else
                    jF1_5.setSelected(false);

                if((cvValue & 32) == 32 )
                    jF1_6.setSelected(true);
                else
                    jF1_6.setSelected(false);

                if((cvValue & 64) == 64 )
                    jF1_7.setSelected(true);
                else
                    jF1_7.setSelected(false);

                if((cvValue & 128) == 128 )
                    jF1_8.setSelected(true);
                else
                    jF1_8.setSelected(false);
                break;

            case 36: //CV#36
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

                if((cvValue & 8) == 8 )
                    jF2_4.setSelected(true);
                else
                    jF2_4.setSelected(false);

                if((cvValue & 16) == 16 )
                    jF2_5.setSelected(true);
                else
                    jF2_5.setSelected(false);

                if((cvValue & 32) == 32 )
                    jF2_6.setSelected(true);
                else
                    jF2_6.setSelected(false);

                if((cvValue & 64) == 64 )
                    jF2_7.setSelected(true);
                else
                    jF2_7.setSelected(false);

                if((cvValue & 128) == 128 )
                    jF2_8.setSelected(true);
                else
                    jF2_8.setSelected(false);
                break;

            case 37: //CV#37
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

                if((cvValue & 8) == 8 )
                    jF3_4.setSelected(true);
                else
                    jF3_4.setSelected(false);

                if((cvValue & 16) == 16 )
                    jF3_5.setSelected(true);
                else
                    jF3_5.setSelected(false);

                if((cvValue & 32) == 32 )
                    jF3_6.setSelected(true);
                else
                    jF3_6.setSelected(false);

                if((cvValue & 64) == 64 )
                    jF3_7.setSelected(true);
                else
                    jF3_7.setSelected(false);

                if((cvValue & 128) == 128 )
                    jF3_8.setSelected(true);
                else
                    jF3_8.setSelected(false);
                break;

            case 38: //CV#38
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

                if((cvValue & 8) == 8 )
                    jF4_4.setSelected(true);
                else
                    jF4_4.setSelected(false);

                if((cvValue & 16) == 16 )
                    jF4_5.setSelected(true);
                else
                    jF4_5.setSelected(false);

                if((cvValue & 32) == 32 )
                    jF4_6.setSelected(true);
                else
                    jF4_6.setSelected(false);

                if((cvValue & 64) == 64 )
                    jF4_7.setSelected(true);
                else
                    jF4_7.setSelected(false);

                if((cvValue & 128) == 128 )
                    jF4_8.setSelected(true);
                else
                    jF4_8.setSelected(false);
                break;

            case 39: //CV#39
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

                if((cvValue & 8) == 8 )
                    jF5_4.setSelected(true);
                else
                    jF5_4.setSelected(false);

                if((cvValue & 16) == 16 )
                    jF5_5.setSelected(true);
                else
                    jF5_5.setSelected(false);

                if((cvValue & 32) == 32 )
                    jF5_6.setSelected(true);
                else
                    jF5_6.setSelected(false);

                if((cvValue & 64) == 64 )
                    jF5_7.setSelected(true);
                else
                    jF5_7.setSelected(false);

                if((cvValue & 128) == 128 )
                    jF5_8.setSelected(true);
                else
                    jF5_8.setSelected(false);
                break;

            case 40: //CV#40
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

                if((cvValue & 8) == 8 )
                    jF6_4.setSelected(true);
                else
                    jF6_4.setSelected(false);

                if((cvValue & 16) == 16 )
                    jF6_5.setSelected(true);
                else
                    jF6_5.setSelected(false);

                if((cvValue & 32) == 32 )
                    jF6_6.setSelected(true);
                else
                    jF6_6.setSelected(false);

                if((cvValue & 64) == 64 )
                    jF6_7.setSelected(true);
                else
                    jF6_7.setSelected(false);

                if((cvValue & 128) == 128 )
                    jF6_8.setSelected(true);
                else
                    jF6_8.setSelected(false);
                break;

            case 41: //CV#41
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

                if((cvValue & 8) == 8 )
                    jF7_4.setSelected(true);
                else
                    jF7_4.setSelected(false);

                if((cvValue & 16) == 16 )
                    jF7_5.setSelected(true);
                else
                    jF7_5.setSelected(false);

                if((cvValue & 32) == 32 )
                    jF7_6.setSelected(true);
                else
                    jF7_6.setSelected(false);

                if((cvValue & 64) == 64 )
                    jF7_7.setSelected(true);
                else
                    jF7_7.setSelected(false);

                if((cvValue & 128) == 128 )
                    jF7_8.setSelected(true);
                else
                    jF7_8.setSelected(false);
                break;

            case 42: //CV#42
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

                if((cvValue & 8) == 8 )
                    jF8_4.setSelected(true);
                else
                    jF8_4.setSelected(false);

                if((cvValue & 16) == 16 )
                    jF8_5.setSelected(true);
                else
                    jF8_5.setSelected(false);

                if((cvValue & 32) == 32 )
                    jF8_6.setSelected(true);
                else
                    jF8_6.setSelected(false);

                if((cvValue & 64) == 64 )
                    jF8_7.setSelected(true);
                else
                    jF8_7.setSelected(false);

                if((cvValue & 128) == 128 )
                    jF8_8.setSelected(true);
                else
                    jF8_8.setSelected(false);
                break;

            case 43: //CV#43
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

                if((cvValue & 8) == 8 )
                    jF9_4.setSelected(true);
                else
                    jF9_4.setSelected(false);

                if((cvValue & 16) == 16 )
                    jF9_5.setSelected(true);
                else
                    jF9_5.setSelected(false);

                if((cvValue & 32) == 32 )
                    jF9_6.setSelected(true);
                else
                    jF9_6.setSelected(false);

                if((cvValue & 64) == 64 )
                    jF9_7.setSelected(true);
                else
                    jF9_7.setSelected(false);

                if((cvValue & 128) == 128 )
                    jF9_8.setSelected(true);
                else
                    jF9_8.setSelected(false);
                break;

            case 44: //CV#44
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

                if((cvValue & 8) == 8 )
                    jF10_4.setSelected(true);
                else
                    jF10_4.setSelected(false);

                if((cvValue & 16) == 16 )
                    jF10_5.setSelected(true);
                else
                    jF10_5.setSelected(false);

                if((cvValue & 32) == 32 )
                    jF10_6.setSelected(true);
                else
                    jF10_6.setSelected(false);

                if((cvValue & 64) == 64 )
                    jF10_7.setSelected(true);
                else
                    jF10_7.setSelected(false);

                if((cvValue & 128) == 128 )
                    jF10_8.setSelected(true);
                else
                    jF10_8.setSelected(false);
                break;

            case 45: //CV#45
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

                if((cvValue & 8) == 8 )
                    jF11_4.setSelected(true);
                else
                    jF11_4.setSelected(false);

                if((cvValue & 16) == 16 )
                    jF11_5.setSelected(true);
                else
                    jF11_5.setSelected(false);

                if((cvValue & 32) == 32 )
                    jF11_6.setSelected(true);
                else
                    jF11_6.setSelected(false);

                if((cvValue & 64) == 64 )
                    jF11_7.setSelected(true);
                else
                    jF11_7.setSelected(false);

                if((cvValue & 128) == 128 )
                    jF11_8.setSelected(true);
                else
                    jF11_8.setSelected(false);
                break;

            case 46: //CV#46
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

                if((cvValue & 8) == 8 )
                    jF12_4.setSelected(true);
                else
                    jF12_4.setSelected(false);

                if((cvValue & 16) == 16 )
                    jF12_5.setSelected(true);
                else
                    jF12_5.setSelected(false);

                if((cvValue & 32) == 32 )
                    jF12_6.setSelected(true);
                else
                    jF12_6.setSelected(false);

                if((cvValue & 64) == 64 )
                    jF12_7.setSelected(true);
                else
                    jF12_7.setSelected(false);

                if((cvValue & 128) == 128 )
                    jF12_8.setSelected(true);
                else
                    jF12_8.setSelected(false);
                break;

            case 47: //CV#47
                jDecodereigenschaften.setSelectedComponent(jRangieren);
                cvValue = CVNavi.checkTextField( this, jCV_Inhalt, 0, 63, 0, true );
                s = jCV_Inhalt.getText();
                CV[1][currCV] = cvValue;
                break;

            case 48: //CV#48
                jDecodereigenschaften.setSelectedComponent(jRangieren);
                jAbrueckGeschw.setText(""+cvValue);
                CV[1][currCV] = cvValue;
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
                CV[1][currCV] = cvValue;
                jTast1.setText( "" + ((CV[1][53] & 0xF0) >> 4));
                jTast2.setText( "" + ((CV[1][54] & 0xF0) >> 4));
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
                break;

            case 55: //CV55
            case 56: //CV56
                CV[1][currCV] = cvValue;
                jTast3.setText( "" + ((CV[1][55] & 0xF0) >> 4));
                jTast4.setText( "" + ((CV[1][56] & 0xF0) >> 4));
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

                if((CV[1][56] & 1) == 1) {
                    jVor4.setSelected(false);
                } else {
                    jVor4.setSelected(true);
                }
                if((CV[1][56] & 2) == 2) {
                    jRueck4.setSelected(false);
                } else {
                    jRueck4.setSelected(true);
                }
                if((CV[1][55] & 8) == 8) {
                    jInvertBlink3.setSelected(true);
                } else {
                    jInvertBlink3.setSelected(false);
                }
                if((CV[1][56] & 8) == 8) {
                    jInvertBlink4.setSelected(true);
                } else {
                    jInvertBlink4.setSelected(false);
                }
                break;

            case 57: //CV57
            case 58: //CV58
                CV[1][currCV] = cvValue;
                jTast5.setText( "" + ((CV[1][57] & 0xF0) >> 4));
                jTast6.setText( "" + ((CV[1][58] & 0xF0) >> 4));
                if((CV[1][57] & 1) == 1) {
                    jVor5.setSelected(false);
                } else {
                    jVor5.setSelected(true);
                }
                if((CV[1][57] & 2) == 2) {
                    jRueck5.setSelected(false);
                } else {
                    jRueck5.setSelected(true);
                }

                if((CV[1][58] & 1) == 1) {
                    jVor6.setSelected(false);
                } else {
                    jVor6.setSelected(true);
                }
                if((CV[1][58] & 2) == 2) {
                    jRueck6.setSelected(false);
                } else {
                    jRueck6.setSelected(true);
                }
                if((CV[1][57] & 8) == 8) {
                    jInvertBlink5.setSelected(true);
                } else {
                    jInvertBlink5.setSelected(false);
                }
                if((CV[1][58] & 8) == 8) {
                    jInvertBlink6.setSelected(true);
                } else {
                    jInvertBlink6.setSelected(false);
                }

                if((CV[1][58] & 4) == 4) {
                    jSchleiferumschaltung.setSelected(true);
                } else {
                    jSchleiferumschaltung.setSelected(false);
                }
                break;

            case 59: //CV59 (Kick-Zeit)
            case 60: //CV60 (Kick-Zeit)
            case 61: //CV61 (Kick-Zeit)
                CV[1][currCV] = cvValue;
                jKickZeit1.setText("" + (CV[1][59] & 0x0F));
                jKickZeit2.setText("" + ((CV[1][59] & 0xF0)>>4));
                jKickZeit3.setText("" + (CV[1][60] & 0x0F));
                jKickZeit4.setText("" + ((CV[1][60] & 0xF0)>>4));
                jKickZeit5.setText("" + (CV[1][61] & 0x0F));
                jKickZeit6.setText("" + ((CV[1][61] & 0xF0)>>4));
                break;

            case 62: //CV62 (Dimmen)
                cvValue = CVNavi.checkTextField( this, jCV_Inhalt, 1, 255, 255, true );
                s = jCV_Inhalt.getText();
                CV[1][currCV] = cvValue;
                jDimmen1.setText("" + (CV[1][62] & 0x0F));
                jDimmen2.setText("" + ((CV[1][62] & 0xF0)>>4));
                break;

            case 63: //CV63 (Dimmen)
                cvValue = CVNavi.checkTextField( this, jCV_Inhalt, 1, 255, 255, true );
                s = jCV_Inhalt.getText();
                CV[1][currCV] = cvValue;
                jDimmen1.setText("" + (CV[1][63] & 0x0F));
                jDimmen2.setText("" + ((CV[1][63] & 0xF0)>>4));
                break;

            case 64: //CV64 (Dimmen)
                cvValue = CVNavi.checkTextField( this, jCV_Inhalt, 1, 255, 255, true );
                s = jCV_Inhalt.getText();
                CV[1][currCV] = cvValue;
                jDimmen1.setText("" + (CV[1][64] & 0x0F));
                jDimmen2.setText("" + ((CV[1][64] & 0xF0)>>4));
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
                cvValue = CVNavi.checkTextField( this, jCV_Inhalt, 0, 63, 0, true );
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
                if((cvValue & 8) == 8) {
                    jAltDim4ein.setSelected(true);
                    jAltDim4aus.setSelected(false);
                } else {
                    jAltDim4ein.setSelected(false);
                    jAltDim4aus.setSelected(true);
                }
                if((cvValue & 16) == 16) {
                    jAltDim5ein.setSelected(true);
                    jAltDim5aus.setSelected(false);
                } else {
                    jAltDim5ein.setSelected(false);
                    jAltDim5aus.setSelected(true);
                }
                if((cvValue & 32) == 32) {
                    jAltDim6ein.setSelected(true);
                    jAltDim6aus.setSelected(false);
                } else {
                    jAltDim6ein.setSelected(false);
                    jAltDim6aus.setSelected(true);
                }
                break;

            case 117: //CV117 (Überlast)
                switch(CVNavi.Decoder) {
                    case c.LD_G33:
                        cvValue = CVNavi.checkTextField( this, jCV_Inhalt, 0, 255, 69, true );
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


            case 119: //CV#119 (AltDimmen)
                cvValue = CVNavi.checkTextField( this, jCV_Inhalt, 1, 255, 255, true );
                s = jCV_Inhalt.getText();
                CV[1][currCV] = cvValue;
                jAltDimmen3.setText("" + (CV[1][119] & 0x0F));
                jAltDimmen4.setText("" + ((CV[1][119] & 0xF0)>>4));
                break;

            case 120: //CV#120 (AltDimmen)
                cvValue = CVNavi.checkTextField( this, jCV_Inhalt, 1, 255, 255, true );
                s = jCV_Inhalt.getText();
                CV[1][currCV] = cvValue;
                jAltDimmen5.setText("" + (CV[1][120] & 0x0F));
                jAltDimmen6.setText("" + ((CV[1][120] & 0xF0)>>4));
                break;

            case 121: //CV#121
                CV[1][currCV] = cvValue;
                if((cvValue & 1) == 1) {
                    jF0AusF1.setSelected(true);
                } else {
                    jF0AusF1.setSelected(false);
                }
                if((cvValue & 2) == 2) {
                    jF0AusF2.setSelected(true);
                } else {
                    jF0AusF2.setSelected(false);
                }
                if((cvValue & 4) == 4) {
                    jF0AusF3.setSelected(true);
                } else {
                    jF0AusF3.setSelected(false);
                }
                if((cvValue & 8) == 8) {
                    jF0AusF4.setSelected(true);
                } else {
                    jF0AusF4.setSelected(false);
                }
                if((cvValue & 16) == 16) {
                    jF0AusF5.setSelected(true);
                } else {
                    jF0AusF5.setSelected(false);
                }
                if((cvValue & 32) == 32) {
                    jF0AusF6.setSelected(true);
                } else {
                    jF0AusF6.setSelected(false);
                }
                if((cvValue & 64) == 64) {
                    jF0AusF7.setSelected(true);
                } else {
                    jF0AusF7.setSelected(false);
                }
                if((cvValue & 128) == 128) {
                    jF0AusF8.setSelected(true);
                } else {
                    jF0AusF8.setSelected(false);
                }
                break;

            case 122: //CV#122
                cvValue = CVNavi.checkTextField( this, jCV_Inhalt, 0, 63, 0, true );
                s = jCV_Inhalt.getText();
                CV[1][currCV] = cvValue;
                if((cvValue & 1) == 1) {
                    jF0AusAUX1.setSelected(true);
                } else {
                    jF0AusAUX1.setSelected(false);
                }
                if((cvValue & 2) == 2) {
                    jF0AusAUX2.setSelected(true);
                } else {
                    jF0AusAUX2.setSelected(false);
                }
                if((cvValue & 4) == 4) {
                    jF0AusAUX3.setSelected(true);
                } else {
                    jF0AusAUX3.setSelected(false);
                }
                if((cvValue & 8) == 8) {
                    jF0AusAUX4.setSelected(true);
                } else {
                    jF0AusAUX4.setSelected(false);
                }
                if((cvValue & 16) == 16) {
                    jF0AusAUX5.setSelected(true);
                } else {
                    jF0AusAUX5.setSelected(false);
                }
                if((cvValue & 32) == 32) {
                    jF0AusAUX6.setSelected(true);
                } else {
                    jF0AusAUX6.setSelected(false);
                }
                break;

            case 123: //CV#123
                cvValue = CVNavi.checkTextField( this, jCV_Inhalt, 0, 15, 0, true );
                s = jCV_Inhalt.getText();
                CV[1][currCV] = cvValue;
                if((cvValue & 1) == 1) {
                    jF0AusF1Aus.setSelected(true);
                } else {
                    jF0AusF1Aus.setSelected(false);
                }
                if((cvValue & 2) == 2) {
                    jF0AusF2Aus.setSelected(true);
                } else {
                    jF0AusF2Aus.setSelected(false);
                }
                if((cvValue & 4) == 4) {
                    jF0AusF3Aus.setSelected(true);
                } else {
                    jF0AusF3Aus.setSelected(false);
                }
                if((cvValue & 8) == 8) {
                    jF0AusF4Aus.setSelected(true);
                } else {
                    jF0AusF4Aus.setSelected(false);
                }
                break;

            case 124: //CV#124 Optimierung der Lastregelung / MotorArt
                cvValue = CVNavi.checkTextField( this, jCV_Inhalt, 1, 15, 2, true );
                s = jCV_Inhalt.getText();
                CV[1][currCV] = cvValue;
                jMotorArt.setText(s);
                break;

            case 126: //CV#126 AUX invertiert
                CV[1][currCV] = cvValue;
                if((cvValue & 1) == 1 )
                    jINV_1.setSelected(true);
                else
                    jINV_1.setSelected(false);

                if((cvValue & 2) == 2 )
                    jINV_2.setSelected(true);
                else
                    jINV_2.setSelected(false);

                if((cvValue & 4) == 4 )
                    jINV_3.setSelected(true);
                else
                    jINV_3.setSelected(false);

                if((cvValue & 8) == 8 )
                    jINV_4.setSelected(true);
                else
                    jINV_4.setSelected(false);

                if((cvValue & 16) == 16 )
                    jINV_5.setSelected(true);
                else
                    jINV_5.setSelected(false);

                if((cvValue & 32) == 32 )
                    jINV_6.setSelected(true);
                else
                    jINV_6.setSelected(false);

                if((cvValue & 64) == 64 )
                    jINV_7.setSelected(true);
                else
                    jINV_7.setSelected(false);

                if((cvValue & 128) == 128 )
                    jINV_8.setSelected(true);
                else
                    jINV_8.setSelected(false);
                break;

            default:
                System.out.println("jCV_InhaltFocusLost CV="+currCV+" Wert="+cvValue+" IGNORIERT");
                CV_InhaltCheckInProgress = false;
                return;

        }
        CV[1][currCV] = cvValue;
        jCV_Inhalt.setText("" + cvValue);

        CV_InhaltCheckInProgress = false;
        return;
    }//GEN-LAST:event_jCV_InhaltFocusLost

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

    private void jDimmen3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmen3FocusLost
        int cvValue = CVNavi.checkTextField( this, jDimmen3, 0, 15, 15, true );
        CV[1][63] = (CV[1][63] & 0xF0) + cvValue;
        jCV_Anzeige.setSelectedItem( "CV#"+63 );
    }//GEN-LAST:event_jDimmen3FocusLost

    private void jDimmen4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmen4FocusLost
        int cvValue = CVNavi.checkTextField( this, jDimmen4, 0, 15, 15, true );
        CV[1][63] = (CV[1][63] & 0x0F) + cvValue*16;
        jCV_Anzeige.setSelectedItem( "CV#"+63 );
    }//GEN-LAST:event_jDimmen4FocusLost

    private void jDimmen5FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmen5FocusLost
        int cvValue = CVNavi.checkTextField( this, jDimmen5, 0, 15, 15, true );
        CV[1][64] = (CV[1][64] & 0xF0) + cvValue;
        jCV_Anzeige.setSelectedItem( "CV#"+64 );
    }//GEN-LAST:event_jDimmen5FocusLost

    private void jDimmen6FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmen6FocusLost
        int cvValue = CVNavi.checkTextField( this, jDimmen6, 0, 15, 15, true );
        CV[1][64] = (CV[1][64] & 0x0F) + cvValue*16;
        jCV_Anzeige.setSelectedItem( "CV#"+64 );
    }//GEN-LAST:event_jDimmen6FocusLost

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

    private void jAltDimmen3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jAltDimmen3FocusLost
        int cvValue = CVNavi.checkTextField( this, jAltDimmen3, 0, 15, 15, true );
        CV[1][119] = (CV[1][119] & 0xF0) + cvValue;
        jCV_Anzeige.setSelectedItem( "CV#"+119 );
    }//GEN-LAST:event_jAltDimmen3FocusLost

    private void jAltDimmen4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jAltDimmen4FocusLost
        int cvValue = CVNavi.checkTextField( this, jAltDimmen4, 0, 15, 15, true );
        CV[1][119] = (CV[1][119] & 0x0F) + cvValue*16;
        jCV_Anzeige.setSelectedItem( "CV#"+119 );
    }//GEN-LAST:event_jAltDimmen4FocusLost

    private void jAltDimmen5FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jAltDimmen5FocusLost
        int cvValue = CVNavi.checkTextField( this, jAltDimmen5, 0, 15, 15, true );
        CV[1][120] = (CV[1][120] & 0xF0) + cvValue;
        jCV_Anzeige.setSelectedItem( "CV#"+120 );
    }//GEN-LAST:event_jAltDimmen5FocusLost

    private void jAltDimmen6FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jAltDimmen6FocusLost
        int cvValue = CVNavi.checkTextField( this, jAltDimmen6, 0, 15, 15, true );
        CV[1][120] = (CV[1][120] & 0x0F) + cvValue*16;
        jCV_Anzeige.setSelectedItem( "CV#"+120 );
    }//GEN-LAST:event_jAltDimmen6FocusLost

    private void jAltDimmenFS1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jAltDimmenFS1FocusLost
        int cvValue = CVNavi.checkTextField( this, jAltDimmenFS1, 1, 126, 16, true );
        CV[1][113] = cvValue;
        jCV_Anzeige.setSelectedItem( "CV#"+113 );
    }//GEN-LAST:event_jAltDimmenFS1FocusLost

    private void jTast1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTast1FocusLost
        int cvValue = CVNavi.checkTextField( this, jTast1, 0, 15, 15, true );
        CV[1][53] = (CV[1][53] & 0x0F) + cvValue*16;
        jCV_Anzeige.setSelectedItem( "CV#"+53 );
    }//GEN-LAST:event_jTast1FocusLost

    private void jTast2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTast2FocusLost
        int cvValue = CVNavi.checkTextField( this, jTast2, 0, 15, 15, true );
        CV[1][54] = (CV[1][54] & 0x0F) + cvValue*16;
        jCV_Anzeige.setSelectedItem( "CV#"+54 );
    }//GEN-LAST:event_jTast2FocusLost

    private void jTast3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTast3FocusLost
        int cvValue = CVNavi.checkTextField( this, jTast3, 0, 15, 15, true );
        CV[1][55] = (CV[1][55] & 0x0F) + cvValue*16;
        jCV_Anzeige.setSelectedItem( "CV#"+55 );
    }//GEN-LAST:event_jTast3FocusLost

    private void jTast4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTast4FocusLost
        int cvValue = CVNavi.checkTextField( this, jTast4, 0, 15, 15, true );
        CV[1][56] = (CV[1][56] & 0x0F) + cvValue*16;
        jCV_Anzeige.setSelectedItem( "CV#"+56 );
    }//GEN-LAST:event_jTast4FocusLost

    private void jTast5FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTast5FocusLost
        int cvValue = CVNavi.checkTextField( this, jTast5, 0, 15, 15, true );
        CV[1][57] = (CV[1][57] & 0x0F) + cvValue*16;
        jCV_Anzeige.setSelectedItem( "CV#"+57 );
    }//GEN-LAST:event_jTast5FocusLost

    private void jTast6FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTast6FocusLost
        int cvValue = CVNavi.checkTextField( this, jTast6, 0, 15, 15, true );
        CV[1][58] = (CV[1][58] & 0x0F) + cvValue*16;
        jCV_Anzeige.setSelectedItem( "CV#"+58 );
    }//GEN-LAST:event_jTast6FocusLost

    private void jAnfahrGeschwFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jAnfahrGeschwFocusLost
        int defValue = 5; // LD-G-30, LD-G-31, LD-G-32, LD-G-33, LD-G-34
        switch( CVNavi.Decoder) {
            case c.LD_W32:
                defValue = 50 ;
                break;
            case c.LD_W33:
                defValue = 60 ;
                break;
        }
        int cvValue = CVNavi.checkTextField( this, jAnfahrGeschw, 0, 255, defValue, true );
        CV[1][2] = cvValue;
        jCV_Anzeige.setSelectedItem( "CV#"+2 );
    }//GEN-LAST:event_jAnfahrGeschwFocusLost

    private void jAnfahrVerzFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jAnfahrVerzFocusLost
        int defValue = 16; // LD-G-33, LD-G-34, LD-W-33
        switch( CVNavi.Decoder) {
            case c.LD_W33:
                defValue = 10 ;
                break;
        }
        int cvValue = CVNavi.checkTextField( this, jAnfahrVerz, 0, 255, defValue, true );
        CV[1][3] = cvValue;
        jCV_Anzeige.setSelectedItem( "CV#"+3 );
    }//GEN-LAST:event_jAnfahrVerzFocusLost

    private void jBremsVerzFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBremsVerzFocusLost
        int defValue = 8; // LD-G-33, LD-G-34
        switch( CVNavi.Decoder) {
            case c.LD_W33:
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
        int cvValue = CVNavi.checkTextField( this, jMotorArt, 0, 255, 2, true );
        CV[1][124] = cvValue;
        jCV_Anzeige.setSelectedItem( "CV#"+124 );
    }//GEN-LAST:event_jMotorArtFocusLost

    private void jAnfahrKickFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jAnfahrKickFocusLost
        int defValue = 0; // LD-G-32, LD-G-33, LD-G-34
        switch( CVNavi.Decoder) {
            case c.LD_W32:
                defValue = 55 ;
                break;
            case c.LD_W33:
                defValue = 65 ;
                break;
        }
        int cvValue = CVNavi.checkTextField( this, jAnfahrKick, 0, 255, defValue, true );
        CV[1][65] = cvValue;
        jCV_Anzeige.setSelectedItem( "CV#"+65 );
    }//GEN-LAST:event_jAnfahrKickFocusLost

    private void jUeberlastFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jUeberlastFocusLost
        int defValue = -1; // not all decoders 
        switch( CVNavi.Decoder) {
            case c.LD_G33:
                defValue = 69 ;
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
            case c.LD_G33:
            case c.LD_G34:
                defValue = 80 ;
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
        int defValue = 0;
        switch( CVNavi.Decoder) {
            case c.LD_G33:
            case c.LD_G34:
                defValue = 45 ;
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
        int defValue = 40;
        switch( CVNavi.Decoder) {
            case c.LD_G33:
            case c.LD_G34:
                defValue = 50 ;
                break;
            default:
                // not on LD-W decoders !
                return;
        }
        int cvValue = CVNavi.checkTextField( this, jKd, 0, 255, defValue, true );
        CV[1][52] = cvValue;
        jCV_Anzeige.setSelectedItem( "CV#"+52 );
    }//GEN-LAST:event_jKdFocusLost

    private void jAbbrechenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAbbrechenActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jAbbrechenActionPerformed

    private void jDecoderAdresse1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDecoderAdresse1FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+19 );
    }//GEN-LAST:event_jDecoderAdresse1FocusGained

    private void jDecoderAdresseFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDecoderAdresseFocusGained
        if (jKurzeAdr.isSelected()) {
            jCV_Anzeige.setSelectedItem( "CV#"+1 );
        } else {
            jCV_Anzeige.setSelectedItem( "CV#"+17 );
        }
    }//GEN-LAST:event_jDecoderAdresseFocusGained

    private void jCV_InhaltFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jCV_InhaltFocusGained
        // no action on gain (but a lot on loss)
    }//GEN-LAST:event_jCV_InhaltFocusGained

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

    private void jDimmen3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmen3FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+63 );
    }//GEN-LAST:event_jDimmen3FocusGained

    private void jDimmen4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmen4FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+63 );
    }//GEN-LAST:event_jDimmen4FocusGained

    private void jDimmen5FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmen5FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+64 );
    }//GEN-LAST:event_jDimmen5FocusGained

    private void jDimmen6FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmen6FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+64 );
    }//GEN-LAST:event_jDimmen6FocusGained

    private void jAltDimmen1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jAltDimmen1FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+118 );
    }//GEN-LAST:event_jAltDimmen1FocusGained

    private void jAltDimmen2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jAltDimmen2FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+118 );
    }//GEN-LAST:event_jAltDimmen2FocusGained

    private void jAltDimmen3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jAltDimmen3FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+119 );
    }//GEN-LAST:event_jAltDimmen3FocusGained

    private void jAltDimmen4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jAltDimmen4FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+119 );
    }//GEN-LAST:event_jAltDimmen4FocusGained

    private void jAltDimmen5FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jAltDimmen5FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+120 );
    }//GEN-LAST:event_jAltDimmen5FocusGained

    private void jAltDimmen6FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jAltDimmen6FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+120 );
    }//GEN-LAST:event_jAltDimmen6FocusGained

    private void jAltDimmenFS1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jAltDimmenFS1FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+113 );
    }//GEN-LAST:event_jAltDimmenFS1FocusGained

    private void jTast1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTast1FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+53 );
    }//GEN-LAST:event_jTast1FocusGained

    private void jTast2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTast2FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+54 );
    }//GEN-LAST:event_jTast2FocusGained

    private void jTast3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTast3FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+55 );
    }//GEN-LAST:event_jTast3FocusGained

    private void jTast4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTast4FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+56 );
    }//GEN-LAST:event_jTast4FocusGained

    private void jTast5FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTast5FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+57 );
    }//GEN-LAST:event_jTast5FocusGained

    private void jTast6FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTast6FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+58 );
    }//GEN-LAST:event_jTast6FocusGained

    private void jKickZeit1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jKickZeit1FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+59 );
    }//GEN-LAST:event_jKickZeit1FocusGained

    private void jKickZeit1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jKickZeit1FocusLost
        int cvValue = CVNavi.checkTextField( this, jKickZeit1, 0, 15, 0, true );        
        CV[1][59] = (CV[1][59] & 0xF0) + cvValue;
        jCV_Anzeige.setSelectedItem( "CV#"+59 );
    }//GEN-LAST:event_jKickZeit1FocusLost

    private void jKickZeit2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jKickZeit2FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+59 );
    }//GEN-LAST:event_jKickZeit2FocusGained

    private void jKickZeit2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jKickZeit2FocusLost
        int cvValue = CVNavi.checkTextField( this, jKickZeit2, 0, 15, 0, true );        
        CV[1][59] = (CV[1][59] & 0x0F) + cvValue*16;
        jCV_Anzeige.setSelectedItem( "CV#"+59 );
    }//GEN-LAST:event_jKickZeit2FocusLost

    private void jKickZeit3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jKickZeit3FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+60 );
    }//GEN-LAST:event_jKickZeit3FocusGained

    private void jKickZeit3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jKickZeit3FocusLost
        int cvValue = CVNavi.checkTextField( this, jKickZeit3, 0, 15, 0, true );        
        CV[1][60] = (CV[1][60] & 0xF0) + cvValue;
        jCV_Anzeige.setSelectedItem( "CV#"+60 );
    }//GEN-LAST:event_jKickZeit3FocusLost

    private void jKickZeit4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jKickZeit4FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+60 );
    }//GEN-LAST:event_jKickZeit4FocusGained

    private void jKickZeit4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jKickZeit4FocusLost
        int cvValue = CVNavi.checkTextField( this, jKickZeit4, 0, 15, 0, true );        
        CV[1][60] = (CV[1][60] & 0x0F) + cvValue*16;
        jCV_Anzeige.setSelectedItem( "CV#"+60 );
    }//GEN-LAST:event_jKickZeit4FocusLost

    private void jKickZeit5FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jKickZeit5FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+61 );
    }//GEN-LAST:event_jKickZeit5FocusGained

    private void jKickZeit5FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jKickZeit5FocusLost
        int cvValue = CVNavi.checkTextField( this, jKickZeit5, 0, 15, 0, true );        
        CV[1][61] = (CV[1][61] & 0xF0) + cvValue;
        jCV_Anzeige.setSelectedItem( "CV#"+61 );
    }//GEN-LAST:event_jKickZeit5FocusLost

    private void jKickZeit6FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jKickZeit6FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+61 );
    }//GEN-LAST:event_jKickZeit6FocusGained

    private void jKickZeit6FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jKickZeit6FocusLost
        int cvValue = CVNavi.checkTextField( this, jKickZeit6, 0, 15, 0, true );        
        CV[1][61] = (CV[1][61] & 0x0F) + cvValue*16;
        jCV_Anzeige.setSelectedItem( "CV#"+61 );
    }//GEN-LAST:event_jKickZeit6FocusLost

    private void jAbrueckGeschwFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jAbrueckGeschwFocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+48 );
    }//GEN-LAST:event_jAbrueckGeschwFocusGained

    private void jAbrueckGeschwFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jAbrueckGeschwFocusLost
        int cvValue = CVNavi.checkTextField( this, jAbrueckGeschw, 0, 255, 0, true );
        CV[1][48] = cvValue;
        jCV_Anzeige.setSelectedItem( "CV#"+48 );
    }//GEN-LAST:event_jAbrueckGeschwFocusLost

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

    private void jMotorListeValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jMotorListeValueChanged
        System.out.println("LDG33 jMotorListeValueChanged idx="+this.jMotorListe.getSelectedIndex());
        setMotorTyp();
    }//GEN-LAST:event_jMotorListeValueChanged

    private void jSchleiferumschaltungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSchleiferumschaltungActionPerformed
        if(jSchleiferumschaltung.isSelected())
            CV[1][58] |= 4;
        else
            CV[1][58] &= ~4;
        jCV_Anzeige.setSelectedItem( "CV#"+58 );
    }//GEN-LAST:event_jSchleiferumschaltungActionPerformed

    private void jINV_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jINV_1ActionPerformed
        if(jINV_1.isSelected()) {
            CV[1][126] |= 1;
        } else {
            CV[1][126] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+126 );
    }//GEN-LAST:event_jINV_1ActionPerformed

    private void jINV_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jINV_2ActionPerformed
        if(jINV_2.isSelected()) {
            CV[1][126] |= 2;
        } else {
            CV[1][126] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+126 );
    }//GEN-LAST:event_jINV_2ActionPerformed

    private void jINV_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jINV_3ActionPerformed
        if(jINV_3.isSelected()) {
            CV[1][126] |= 4;
        } else {
            CV[1][126] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+126 );
    }//GEN-LAST:event_jINV_3ActionPerformed

    private void jINV_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jINV_4ActionPerformed
        if(jINV_4.isSelected()) {
            CV[1][126] |= 8;
        } else {
            CV[1][126] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+126 );
    }//GEN-LAST:event_jINV_4ActionPerformed

    private void jINV_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jINV_5ActionPerformed
        if(jINV_5.isSelected()) {
            CV[1][126] |= 16;
        } else {
            CV[1][126] &= ~16;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+126 );
    }//GEN-LAST:event_jINV_5ActionPerformed

    private void jINV_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jINV_6ActionPerformed
        if(jINV_6.isSelected()) {
            CV[1][126] |= 32;
        } else {
            CV[1][126] &= ~32;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+126 );
    }//GEN-LAST:event_jINV_6ActionPerformed

    private void jINV_7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jINV_7ActionPerformed
        if(jINV_7.isSelected()) {
            CV[1][126] |= 64;
        } else {
            CV[1][126] &= ~64;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+126 );
    }//GEN-LAST:event_jINV_7ActionPerformed

    private void jINV_8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jINV_8ActionPerformed
        if(jINV_8.isSelected()) {
            CV[1][126] |= 128;
        } else {
            CV[1][126] &= ~128;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+126 );
    }//GEN-LAST:event_jINV_8ActionPerformed

    private void jCV_InhaltKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCV_InhaltKeyReleased
        if( evt.getKeyCode() == KeyEvent.VK_ENTER ) {
            transferFocus();
        }
    }//GEN-LAST:event_jCV_InhaltKeyReleased

    private void jDecoderAdresseKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDecoderAdresseKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jDecoderAdresseKeyReleased

    private void jDecoderAdresse1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDecoderAdresse1KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jDecoderAdresse1KeyReleased

    private void jBlinkFrequenzKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBlinkFrequenzKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jBlinkFrequenzKeyReleased

    private void jMM_Addr_2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jMM_Addr_2KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jMM_Addr_2KeyReleased

    private void jDimmen1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDimmen1KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jDimmen1KeyReleased

    private void jDimmen2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDimmen2KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jDimmen2KeyReleased

    private void jDimmen3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDimmen3KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jDimmen3KeyReleased

    private void jDimmen4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDimmen4KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jDimmen4KeyReleased

    private void jDimmen5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDimmen5KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jDimmen5KeyReleased

    private void jDimmen6KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDimmen6KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jDimmen6KeyReleased

    private void jAltDimmen1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jAltDimmen1KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jAltDimmen1KeyReleased

    private void jAltDimmen2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jAltDimmen2KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jAltDimmen2KeyReleased

    private void jAltDimmen3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jAltDimmen3KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jAltDimmen3KeyReleased

    private void jAltDimmen4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jAltDimmen4KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jAltDimmen4KeyReleased

    private void jAltDimmen5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jAltDimmen5KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jAltDimmen5KeyReleased

    private void jAltDimmen6KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jAltDimmen6KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jAltDimmen6KeyReleased

    private void jAltDimmenFS1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jAltDimmenFS1KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jAltDimmenFS1KeyReleased

    private void jTast1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTast1KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jTast1KeyReleased

    private void jTast2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTast2KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jTast2KeyReleased

    private void jTast3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTast3KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jTast3KeyReleased

    private void jTast4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTast4KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jTast4KeyReleased

    private void jTast5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTast5KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jTast5KeyReleased

    private void jTast6KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTast6KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jTast6KeyReleased

    private void jKickZeit1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jKickZeit1KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jKickZeit1KeyReleased

    private void jKickZeit2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jKickZeit2KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jKickZeit2KeyReleased

    private void jKickZeit3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jKickZeit3KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jKickZeit3KeyReleased

    private void jKickZeit4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jKickZeit4KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jKickZeit4KeyReleased

    private void jKickZeit5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jKickZeit5KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jKickZeit5KeyReleased

    private void jKickZeit6KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jKickZeit6KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jKickZeit6KeyReleased

    private void jAbrueckGeschwKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jAbrueckGeschwKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jAbrueckGeschwKeyReleased

    private void jAnfahrGeschwKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jAnfahrGeschwKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jAnfahrGeschwKeyReleased

    private void jAnfahrVerzKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jAnfahrVerzKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jAnfahrVerzKeyReleased

    private void jBremsVerzKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBremsVerzKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jBremsVerzKeyReleased

    private void jVMaxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jVMaxKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jVMaxKeyReleased

    private void jMotorArtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jMotorArtKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jMotorArtKeyReleased

    private void jAnfahrKickKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jAnfahrKickKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jAnfahrKickKeyReleased

    private void jUeberlastKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jUeberlastKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jUeberlastKeyReleased

    private void jKpKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jKpKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jKpKeyReleased

    private void jKiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jKiKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jKiKeyReleased

    private void jKdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jKdKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jKdKeyReleased

    private void setMotorTyp() {
        int iMotorTyp = jMotorListe.getSelectedIndex();
        switch(iMotorTyp) {
            case 0:     //HLA
                switch(CVNavi.Decoder) {
                    case c.LD_G33: //LD-G-33
                    case c.LD_G34: //LD-G-34
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
                switch(CVNavi.Decoder) {
                    case c.LD_G33: //LD-G-33
                    case c.LD_G34: //LD-G-34
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
                switch(CVNavi.Decoder) {
                    case 6: //LD-G-33
                    case 7: //LD-G-34
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
                switch(CVNavi.Decoder) {
                    case c.LD_G33: //LD-G-33
                    case c.LD_G34: //LD-G-34
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
                switch(CVNavi.Decoder) {
                    case c.LD_G33: //LD-G-33
                    case c.LD_G34: //LD-G-34
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
                switch(CVNavi.Decoder) {
                    case c.LD_G33: //LD-G-33
                    case c.LD_G34: //LD-G-34
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
                switch(CVNavi.Decoder) {
                    case c.LD_G33: //LD-G-33
                    case c.LD_G34: //LD-G-34
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
                switch(CVNavi.Decoder) {
                    case c.LD_G32: //LD-G-33
                    case c.LD_G34: //LD-G-34
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
                switch(CVNavi.Decoder) {
                    case c.LD_G33: //LD-G-33
                    case c.LD_G34: //LD-G-34
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
                switch(CVNavi.Decoder) {
                    case c.LD_G33: //LD-G-33
                    case c.LD_G34: //LD-G-34
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
        String[] keys = { "LD-G-33", "LD-W-33",  "LD-G-34", "FD-XL" };
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
    private javax.swing.ButtonGroup buttonGroup10;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.ButtonGroup buttonGroup6;
    private javax.swing.ButtonGroup buttonGroup7;
    private javax.swing.ButtonGroup buttonGroup8;
    private javax.swing.ButtonGroup buttonGroup9;
    private javax.swing.JRadioButton j480Hz;
    private javax.swing.JRadioButton j80Hz;
    private javax.swing.JCheckBox jAB_F3;
    private javax.swing.JCheckBox jAB_F4;
    private javax.swing.JCheckBox jAR_1;
    private javax.swing.JCheckBox jAR_2;
    private javax.swing.JCheckBox jAR_3;
    private javax.swing.JCheckBox jAR_4;
    private javax.swing.JCheckBox jAR_5;
    private javax.swing.JCheckBox jAR_6;
    private javax.swing.JButton jAbbrechen;
    private javax.swing.JTextField jAbrueckGeschw;
    private javax.swing.JRadioButton jAltDim1aus;
    private javax.swing.JRadioButton jAltDim1ein;
    private javax.swing.JRadioButton jAltDim2aus;
    private javax.swing.JRadioButton jAltDim2ein;
    private javax.swing.JRadioButton jAltDim3aus;
    private javax.swing.JRadioButton jAltDim3ein;
    private javax.swing.JRadioButton jAltDim4aus;
    private javax.swing.JRadioButton jAltDim4ein;
    private javax.swing.JRadioButton jAltDim5aus;
    private javax.swing.JRadioButton jAltDim5ein;
    private javax.swing.JRadioButton jAltDim6aus;
    private javax.swing.JRadioButton jAltDim6ein;
    private javax.swing.JTextField jAltDimmen1;
    private javax.swing.JTextField jAltDimmen2;
    private javax.swing.JTextField jAltDimmen3;
    private javax.swing.JTextField jAltDimmen4;
    private javax.swing.JTextField jAltDimmen5;
    private javax.swing.JTextField jAltDimmen6;
    private javax.swing.JTextField jAltDimmenFS1;
    private javax.swing.JCheckBox jAltKennlinie;
    private javax.swing.JRadioButton jAnaSpannung_G;
    private javax.swing.JRadioButton jAnaSpannung_W;
    private javax.swing.JPanel jAnalog;
    private javax.swing.JCheckBox jAnalog1;
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
    private javax.swing.JTextField jDimmen4;
    private javax.swing.JTextField jDimmen5;
    private javax.swing.JTextField jDimmen6;
    private javax.swing.JToggleButton jDirekteingabe;
    private javax.swing.JPanel jEffekte;
    private javax.swing.JCheckBox jF0AusAUX1;
    private javax.swing.JCheckBox jF0AusAUX2;
    private javax.swing.JCheckBox jF0AusAUX3;
    private javax.swing.JCheckBox jF0AusAUX4;
    private javax.swing.JCheckBox jF0AusAUX5;
    private javax.swing.JCheckBox jF0AusAUX6;
    private javax.swing.JCheckBox jF0AusF1;
    private javax.swing.JCheckBox jF0AusF1Aus;
    private javax.swing.JCheckBox jF0AusF2;
    private javax.swing.JCheckBox jF0AusF2Aus;
    private javax.swing.JCheckBox jF0AusF3;
    private javax.swing.JCheckBox jF0AusF3Aus;
    private javax.swing.JCheckBox jF0AusF4;
    private javax.swing.JCheckBox jF0AusF4Aus;
    private javax.swing.JCheckBox jF0AusF5;
    private javax.swing.JCheckBox jF0AusF6;
    private javax.swing.JCheckBox jF0AusF7;
    private javax.swing.JCheckBox jF0AusF8;
    private javax.swing.JCheckBox jF1;
    private javax.swing.JCheckBox jF10_1;
    private javax.swing.JCheckBox jF10_2;
    private javax.swing.JCheckBox jF10_3;
    private javax.swing.JCheckBox jF10_4;
    private javax.swing.JCheckBox jF10_5;
    private javax.swing.JCheckBox jF10_6;
    private javax.swing.JCheckBox jF10_7;
    private javax.swing.JCheckBox jF10_8;
    private javax.swing.JCheckBox jF11_1;
    private javax.swing.JCheckBox jF11_2;
    private javax.swing.JCheckBox jF11_3;
    private javax.swing.JCheckBox jF11_4;
    private javax.swing.JCheckBox jF11_5;
    private javax.swing.JCheckBox jF11_6;
    private javax.swing.JCheckBox jF11_7;
    private javax.swing.JCheckBox jF11_8;
    private javax.swing.JCheckBox jF12_1;
    private javax.swing.JCheckBox jF12_2;
    private javax.swing.JCheckBox jF12_3;
    private javax.swing.JCheckBox jF12_4;
    private javax.swing.JCheckBox jF12_5;
    private javax.swing.JCheckBox jF12_6;
    private javax.swing.JCheckBox jF12_7;
    private javax.swing.JCheckBox jF12_8;
    private javax.swing.JCheckBox jF1_1;
    private javax.swing.JCheckBox jF1_2;
    private javax.swing.JCheckBox jF1_3;
    private javax.swing.JCheckBox jF1_4;
    private javax.swing.JCheckBox jF1_5;
    private javax.swing.JCheckBox jF1_6;
    private javax.swing.JCheckBox jF1_7;
    private javax.swing.JCheckBox jF1_8;
    private javax.swing.JCheckBox jF2;
    private javax.swing.JCheckBox jF2_1;
    private javax.swing.JCheckBox jF2_2;
    private javax.swing.JCheckBox jF2_3;
    private javax.swing.JCheckBox jF2_4;
    private javax.swing.JCheckBox jF2_5;
    private javax.swing.JCheckBox jF2_6;
    private javax.swing.JCheckBox jF2_7;
    private javax.swing.JCheckBox jF2_8;
    private javax.swing.JCheckBox jF3;
    private javax.swing.JCheckBox jF3_1;
    private javax.swing.JCheckBox jF3_2;
    private javax.swing.JCheckBox jF3_3;
    private javax.swing.JCheckBox jF3_4;
    private javax.swing.JCheckBox jF3_5;
    private javax.swing.JCheckBox jF3_6;
    private javax.swing.JCheckBox jF3_7;
    private javax.swing.JCheckBox jF3_8;
    private javax.swing.JCheckBox jF4;
    private javax.swing.JCheckBox jF4_1;
    private javax.swing.JCheckBox jF4_2;
    private javax.swing.JCheckBox jF4_3;
    private javax.swing.JCheckBox jF4_4;
    private javax.swing.JCheckBox jF4_5;
    private javax.swing.JCheckBox jF4_6;
    private javax.swing.JCheckBox jF4_7;
    private javax.swing.JCheckBox jF4_8;
    private javax.swing.JCheckBox jF5;
    private javax.swing.JCheckBox jF5_1;
    private javax.swing.JCheckBox jF5_2;
    private javax.swing.JCheckBox jF5_3;
    private javax.swing.JCheckBox jF5_4;
    private javax.swing.JCheckBox jF5_5;
    private javax.swing.JCheckBox jF5_6;
    private javax.swing.JCheckBox jF5_7;
    private javax.swing.JCheckBox jF5_8;
    private javax.swing.JCheckBox jF6;
    private javax.swing.JCheckBox jF6_1;
    private javax.swing.JCheckBox jF6_2;
    private javax.swing.JCheckBox jF6_3;
    private javax.swing.JCheckBox jF6_4;
    private javax.swing.JCheckBox jF6_5;
    private javax.swing.JCheckBox jF6_6;
    private javax.swing.JCheckBox jF6_7;
    private javax.swing.JCheckBox jF6_8;
    private javax.swing.JCheckBox jF7;
    private javax.swing.JCheckBox jF7_1;
    private javax.swing.JCheckBox jF7_2;
    private javax.swing.JCheckBox jF7_3;
    private javax.swing.JCheckBox jF7_4;
    private javax.swing.JCheckBox jF7_5;
    private javax.swing.JCheckBox jF7_6;
    private javax.swing.JCheckBox jF7_7;
    private javax.swing.JCheckBox jF7_8;
    private javax.swing.JCheckBox jF8;
    private javax.swing.JCheckBox jF8_1;
    private javax.swing.JCheckBox jF8_2;
    private javax.swing.JCheckBox jF8_3;
    private javax.swing.JCheckBox jF8_4;
    private javax.swing.JCheckBox jF8_5;
    private javax.swing.JCheckBox jF8_6;
    private javax.swing.JCheckBox jF8_7;
    private javax.swing.JCheckBox jF8_8;
    private javax.swing.JCheckBox jF9_1;
    private javax.swing.JCheckBox jF9_2;
    private javax.swing.JCheckBox jF9_3;
    private javax.swing.JCheckBox jF9_4;
    private javax.swing.JCheckBox jF9_5;
    private javax.swing.JCheckBox jF9_6;
    private javax.swing.JCheckBox jF9_7;
    private javax.swing.JCheckBox jF9_8;
    private javax.swing.JCheckBox jFL_1;
    private javax.swing.JCheckBox jFL_2;
    private javax.swing.JCheckBox jFL_3;
    private javax.swing.JCheckBox jFL_4;
    private javax.swing.JCheckBox jFL_5;
    private javax.swing.JCheckBox jFL_6;
    private javax.swing.JCheckBox jFL_7;
    private javax.swing.JCheckBox jFL_8;
    private javax.swing.JCheckBox jFR_1;
    private javax.swing.JCheckBox jFR_2;
    private javax.swing.JCheckBox jFR_3;
    private javax.swing.JCheckBox jFR_4;
    private javax.swing.JCheckBox jFR_5;
    private javax.swing.JCheckBox jFR_6;
    private javax.swing.JCheckBox jFR_7;
    private javax.swing.JCheckBox jFR_8;
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
    private javax.swing.JCheckBox jINV_1;
    private javax.swing.JCheckBox jINV_2;
    private javax.swing.JCheckBox jINV_3;
    private javax.swing.JCheckBox jINV_4;
    private javax.swing.JCheckBox jINV_5;
    private javax.swing.JCheckBox jINV_6;
    private javax.swing.JCheckBox jINV_7;
    private javax.swing.JCheckBox jINV_8;
    private javax.swing.JCheckBox jInvertBlink1;
    private javax.swing.JCheckBox jInvertBlink2;
    private javax.swing.JCheckBox jInvertBlink3;
    private javax.swing.JCheckBox jInvertBlink4;
    private javax.swing.JCheckBox jInvertBlink5;
    private javax.swing.JCheckBox jInvertBlink6;
    private javax.swing.JTextField jKd;
    private javax.swing.JPanel jKennlinie;
    private javax.swing.JTextField jKi;
    private javax.swing.JTextField jKickZeit1;
    private javax.swing.JTextField jKickZeit2;
    private javax.swing.JTextField jKickZeit3;
    private javax.swing.JTextField jKickZeit4;
    private javax.swing.JTextField jKickZeit5;
    private javax.swing.JTextField jKickZeit6;
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
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
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
    private javax.swing.JCheckBox jRL_4;
    private javax.swing.JCheckBox jRL_5;
    private javax.swing.JCheckBox jRL_6;
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
    private javax.swing.JCheckBox jRueck4;
    private javax.swing.JCheckBox jRueck5;
    private javax.swing.JCheckBox jRueck6;
    private javax.swing.JButton jSave;
    private javax.swing.JCheckBox jSchleiferumschaltung;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTextField jTast1;
    private javax.swing.JTextField jTast2;
    private javax.swing.JTextField jTast3;
    private javax.swing.JTextField jTast4;
    private javax.swing.JTextField jTast5;
    private javax.swing.JTextField jTast6;
    private javax.swing.JTextField jUeberlast;
    private javax.swing.JTextField jVMax;
    private javax.swing.JLabel jVersion;
    private javax.swing.JCheckBox jVor1;
    private javax.swing.JCheckBox jVor2;
    private javax.swing.JCheckBox jVor3;
    private javax.swing.JCheckBox jVor4;
    private javax.swing.JCheckBox jVor5;
    private javax.swing.JCheckBox jVor6;
    private javax.swing.JRadioButton jlangeAdr;
    // End of variables declaration//GEN-END:variables

}
