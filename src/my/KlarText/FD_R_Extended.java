/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FD_R_Extended.java
 *
 * Created on 16.08.2012, 18:23:19
 */
package my.KlarText;

import static java.awt.Toolkit.getDefaultToolkit;
import java.awt.event.KeyEvent;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import static my.KlarText.KlarTextUI.debugLevel;

/**
 *
 * @author ktams
 */
public class FD_R_Extended extends javax.swing.JFrame {
    
    private String CVs;
    private int CV[][] = new int[2][197];
    public KlarTextUI KTUI;
    public String ReturnString = "Tams Elektronik";

    /** Creates new form FD_R_Extended */
    public FD_R_Extended(KlarTextUI ktuiThis) {
        if( ktuiThis == null ) {
            return;
        }
        KTUI = ktuiThis;
        if( KTUI.frameInstanceDEVICE != null ) {
            KTUI.frameInstanceDEVICE.toFront();
            KTUI.frameInstanceDEVICE.repaint();
            return;
        }

        initComponents();
        ImageIcon II = new ImageIcon(getClass().getResource("/FD-R-Extended.gif"));
        this.setIconImage(II.getImage());
        ImageIcon II2 = new ImageIcon(getClass().getResource("/FD-R_Ext_Bl1.gif"));
        this.setIconImage(II2.getImage());
        setTitle( KTUI.getMenutext( decoderList.FD_R_ex ).trim() );
        jBild1.setIcon(II);
        jBlinkBild.setIcon(II2);

        //---- CV-default-Werte -----
        // Vom Decoder verwendete CVs markieren und mit Default-Werten besetzen
        initCV( 0, 0 ); // reset jCV_Anzeige (clean all entries)

        // unsupported / unsaved CVs
        // 9, 10,12,14,15,16, 20,21,22,23,24,25,26, 52, 65,66, 95,96,97,98,99,
        // 100,101,102,103,104,105, 122,127,128,129, 130,138,139, 146,147, 161,164,165,166, 178,179
        /*
        initCV( ,  );
        */

        // Werte aus FD-R-Extended_DE.pdf (02.06.2012), abweichende Werte im Originalsource als Kommentar
        initCV( 1, 3 );
        initCV( 2, 5 );
        initCV( 3, 4 ); // 20
        initCV( 4, 2 ); // 10
        initCV( 5, 255 );
        initCV( 6, 100 );
        initCV( 7, 10 );
        initCV( 8, 62 );
        initCV( 11, 5 );
        // initCV( ,  ); // CV12 = 1
        initCV( 13, 0 );
        initCV( 17, 192 );
        initCV( 18, 255 );
        initCV( 19, 0 );
        initCV( 27, 48 );
        initCV( 28, 3 );
        initCV( 29, 14 );
        // initCV( ,   ); // 30, 255
        initCV( 31, 0 );
        initCV( 32, 255 );
        initCV( 33, 1 );
        initCV( 34, 2 );
        initCV( 35, 4 );
        initCV( 36, 8 );
        initCV( 37, 0 );
        initCV( 38, 0 );
        initCV( 39, 16 );
        initCV( 40, 32 );
        for( int cv = 41 ; cv <= 46 ; cv++ ) { // Seite 34 F0 - F12 Zuordnung Ausgänge zu F-Tasten
            initCV( cv, 0 );
        }
        initCV( 47, 4 );
        initCV( 48, 64 ); // 50
        initCV( 49, 72 ); // 73
        initCV( 50, 0 ); // 50
        initCV( 51, 0 ); // 30
        for( int cv = 53 ; cv <= 58 ; cv++ ) { // Seite 35 Effekte der Ausgänge
            initCV( cv, 0 );
        }
        for( int cv = 59 ; cv <= 64 ; cv++ ) { // Seite 39 Zuordnung zu den Schalteingängen
            initCV( cv, 0 );
        }
        for( int i = 0 ; i < 28 ; i++ ) { // Seite 30 Alternative Kennlinie ; laut Handbuch alle 0 !
            initCV( 67+i, i*6 );
        }
        for( int cv = 106 ; cv <= 111 ; cv++ ) { // Seite 35 Blinkfrequenz
            initCV( cv, 20 );
        }
        initCV( 112, 64 );
        initCV( 113, 10 );
        initCV( 114, 0 );
        initCV( 115, 255 );
        initCV( 116, 255 );
        initCV( 117, 255 );
        initCV( 118, 255 );
        initCV( 119, 0 );
        initCV( 120, 0 );
        initCV( 121, 0 );
        initCV( 123, 255 );
        initCV( 124, 255 );
        initCV( 125, 255 );
        initCV( 126, 255 );
        initCV( 131, 0 );
        for( int cv = 132 ; cv <= 137 ; cv++ ) { // Seite 36 Einschaltdauer der Blinklichter
            initCV( cv, 4 );
        }
        for( int cv = 140 ; cv <= 145 ; cv++ ) { // Seite 36 Doppelblinken, Länge der Pausen
            initCV( cv, 2 );
        }
        for( int cv = 148 ; cv <= 153; cv++ ) { // Seite 40 Zuodnung F-tasten zu integrierten Sounds
            initCV( cv, 0 );
        }
        initCV( 154, 16 ); // 8
        initCV( 155, 32 ); // 16
        initCV( 156, 64 ); // 32
        initCV( 157, 8 ); // 64
        for( int cv = 158 ; cv <= 159; cv++ ) { // Seite 40 Zuodnung F-tasten zu integrierten Sounds
            initCV( cv, 0 );
        }
        initCV( 160, 0 );
        initCV( 162, 0 ); // 32
        initCV( 163, 0 ); // 32
        initCV( 167, 2 ); // 0
        initCV( 168, 0 ); // 2
        initCV( 169, 112 );
        initCV( 170, 176 );
        initCV( 171, 16 );
        initCV( 172, 112 );
        initCV( 173, 0 );
        initCV( 174, 0 );
        initCV( 175, 0 );
        initCV( 176, 0 );
        // initCV( 177,  ); // 0

        for( int cv = 180 ; cv <= 195 ; cv++ ) { // Seite 34 F13-F28 Zuordnung Ausgänge zu F-Tasten
            initCV( cv, 0 );
        }

        //---------------------------

        setTitle( KTUI.getMenutext( decoderList.FD_R_ex ).trim() );
        setLocationRelativeTo(ktuiThis);
        setVisible(true);
        KTUI.frameInstanceDEVICE = this;
    }

    private Boolean initCV( int cv, int value ) {
        if( cv == 0 ) {
            jCV_Anzeige.removeAllItems();
            return true;
        }
        if( value == -1 ) {
            jCV_Anzeige.removeItem("CV#"+cv);
            return( KTUI.unsetCVvalue(CV, cv) );
        }
        jCV_Anzeige.addItem("CV#"+cv);
        return( KTUI.setCVvalue(CV, cv, value) );
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
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jDirekteingabe = new javax.swing.JToggleButton();
        jlangeAdr = new javax.swing.JRadioButton();
        jSave = new javax.swing.JButton();
        jKurzeAdr = new javax.swing.JRadioButton();
        jDecodereigenschaften = new javax.swing.JTabbedPane();
        jCV29 = new javax.swing.JPanel();
        jRichtung = new javax.swing.JCheckBox();
        jFS = new javax.swing.JCheckBox();
        jAnalog1 = new javax.swing.JCheckBox();
        jRailCom = new javax.swing.JCheckBox();
        jAnalog3 = new javax.swing.JCheckBox();
        jLongAddr = new javax.swing.JCheckBox();
        jLongAddr1 = new javax.swing.JCheckBox();
        jLongAddr2 = new javax.swing.JCheckBox();
        jLabel41 = new javax.swing.JLabel();
        jMM_Addr_2 = new javax.swing.JTextField();
        jBild = new javax.swing.JLabel();
        jManID = new javax.swing.JLabel();
        jVersion = new javax.swing.JLabel();
        jBild1 = new javax.swing.JLabel();
        jBroadCasst = new javax.swing.JCheckBox();
        jChannel2 = new javax.swing.JCheckBox();
        jLabel56 = new javax.swing.JLabel();
        jFunctionMapping = new javax.swing.JPanel();
        jFL_1 = new javax.swing.JCheckBox();
        jF14_6 = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        jF1_1 = new javax.swing.JCheckBox();
        jF2_1 = new javax.swing.JCheckBox();
        jF3_1 = new javax.swing.JCheckBox();
        jF4_1 = new javax.swing.JCheckBox();
        jF5_1 = new javax.swing.JCheckBox();
        jF16_3 = new javax.swing.JCheckBox();
        jF16_2 = new javax.swing.JCheckBox();
        jF16_1 = new javax.swing.JCheckBox();
        jF16_4 = new javax.swing.JCheckBox();
        jF16_5 = new javax.swing.JCheckBox();
        jF10_6 = new javax.swing.JCheckBox();
        jF16_6 = new javax.swing.JCheckBox();
        jFL_2 = new javax.swing.JCheckBox();
        jFR_2 = new javax.swing.JCheckBox();
        jF1_2 = new javax.swing.JCheckBox();
        jF2_2 = new javax.swing.JCheckBox();
        jF3_2 = new javax.swing.JCheckBox();
        jF4_2 = new javax.swing.JCheckBox();
        jF9_2 = new javax.swing.JCheckBox();
        jF11_1 = new javax.swing.JCheckBox();
        jF17_1 = new javax.swing.JCheckBox();
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
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jFL_3 = new javax.swing.JCheckBox();
        jFR_3 = new javax.swing.JCheckBox();
        jF1_3 = new javax.swing.JCheckBox();
        jF2_3 = new javax.swing.JCheckBox();
        jF3_3 = new javax.swing.JCheckBox();
        jF4_3 = new javax.swing.JCheckBox();
        jF9_3 = new javax.swing.JCheckBox();
        jF17_4 = new javax.swing.JCheckBox();
        jF17_3 = new javax.swing.JCheckBox();
        jF17_2 = new javax.swing.JCheckBox();
        jF17_5 = new javax.swing.JCheckBox();
        jF17_6 = new javax.swing.JCheckBox();
        jF11_2 = new javax.swing.JCheckBox();
        jF18_1 = new javax.swing.JCheckBox();
        jF19_6 = new javax.swing.JCheckBox();
        jFL_4 = new javax.swing.JCheckBox();
        jFR_4 = new javax.swing.JCheckBox();
        jF1_4 = new javax.swing.JCheckBox();
        jF2_4 = new javax.swing.JCheckBox();
        jF3_4 = new javax.swing.JCheckBox();
        jF4_4 = new javax.swing.JCheckBox();
        jF9_4 = new javax.swing.JCheckBox();
        jF11_3 = new javax.swing.JCheckBox();
        jF18_2 = new javax.swing.JCheckBox();
        jF18_3 = new javax.swing.JCheckBox();
        jF18_4 = new javax.swing.JCheckBox();
        jF18_5 = new javax.swing.JCheckBox();
        jF18_6 = new javax.swing.JCheckBox();
        jF20_6 = new javax.swing.JCheckBox();
        jFL_5 = new javax.swing.JCheckBox();
        jFR_5 = new javax.swing.JCheckBox();
        jF1_5 = new javax.swing.JCheckBox();
        jF2_5 = new javax.swing.JCheckBox();
        jF3_5 = new javax.swing.JCheckBox();
        jF4_5 = new javax.swing.JCheckBox();
        jF9_5 = new javax.swing.JCheckBox();
        jF11_4 = new javax.swing.JCheckBox();
        jF19_1 = new javax.swing.JCheckBox();
        jF19_2 = new javax.swing.JCheckBox();
        jF19_3 = new javax.swing.JCheckBox();
        jF19_4 = new javax.swing.JCheckBox();
        jF19_5 = new javax.swing.JCheckBox();
        jLabel58 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jF21_6 = new javax.swing.JCheckBox();
        jFL_6 = new javax.swing.JCheckBox();
        jFR_6 = new javax.swing.JCheckBox();
        jF1_6 = new javax.swing.JCheckBox();
        jF2_6 = new javax.swing.JCheckBox();
        jF3_6 = new javax.swing.JCheckBox();
        jF4_6 = new javax.swing.JCheckBox();
        jF9_6 = new javax.swing.JCheckBox();
        jF11_5 = new javax.swing.JCheckBox();
        jF20_1 = new javax.swing.JCheckBox();
        jF20_2 = new javax.swing.JCheckBox();
        jF20_3 = new javax.swing.JCheckBox();
        jF20_4 = new javax.swing.JCheckBox();
        jF20_5 = new javax.swing.JCheckBox();
        jF22_6 = new javax.swing.JCheckBox();
        jF14_1 = new javax.swing.JCheckBox();
        jFR_1 = new javax.swing.JCheckBox();
        jF5_2 = new javax.swing.JCheckBox();
        jF5_3 = new javax.swing.JCheckBox();
        jF5_4 = new javax.swing.JCheckBox();
        jF5_5 = new javax.swing.JCheckBox();
        jF10_1 = new javax.swing.JCheckBox();
        jF11_6 = new javax.swing.JCheckBox();
        jF21_1 = new javax.swing.JCheckBox();
        jF21_2 = new javax.swing.JCheckBox();
        jF21_3 = new javax.swing.JCheckBox();
        jF21_4 = new javax.swing.JCheckBox();
        jF21_5 = new javax.swing.JCheckBox();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jF22_5 = new javax.swing.JCheckBox();
        jF14_2 = new javax.swing.JCheckBox();
        jF5_6 = new javax.swing.JCheckBox();
        jF6_1 = new javax.swing.JCheckBox();
        jF6_2 = new javax.swing.JCheckBox();
        jF6_3 = new javax.swing.JCheckBox();
        jF6_4 = new javax.swing.JCheckBox();
        jF10_2 = new javax.swing.JCheckBox();
        jF12_1 = new javax.swing.JCheckBox();
        jF15_6 = new javax.swing.JCheckBox();
        jF22_1 = new javax.swing.JCheckBox();
        jF22_2 = new javax.swing.JCheckBox();
        jF22_3 = new javax.swing.JCheckBox();
        jF22_4 = new javax.swing.JCheckBox();
        jF23_4 = new javax.swing.JCheckBox();
        jF14_3 = new javax.swing.JCheckBox();
        jF6_5 = new javax.swing.JCheckBox();
        jF6_6 = new javax.swing.JCheckBox();
        jF7_1 = new javax.swing.JCheckBox();
        jF7_2 = new javax.swing.JCheckBox();
        jF7_3 = new javax.swing.JCheckBox();
        jF10_3 = new javax.swing.JCheckBox();
        jF12_2 = new javax.swing.JCheckBox();
        jF15_4 = new javax.swing.JCheckBox();
        jF15_5 = new javax.swing.JCheckBox();
        jF23_1 = new javax.swing.JCheckBox();
        jF23_2 = new javax.swing.JCheckBox();
        jF23_3 = new javax.swing.JCheckBox();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jF23_6 = new javax.swing.JCheckBox();
        jF14_4 = new javax.swing.JCheckBox();
        jF7_4 = new javax.swing.JCheckBox();
        jF7_5 = new javax.swing.JCheckBox();
        jF7_6 = new javax.swing.JCheckBox();
        jF8_1 = new javax.swing.JCheckBox();
        jF8_2 = new javax.swing.JCheckBox();
        jF10_4 = new javax.swing.JCheckBox();
        jF12_3 = new javax.swing.JCheckBox();
        jF12_6 = new javax.swing.JCheckBox();
        jF13_6 = new javax.swing.JCheckBox();
        jF15_1 = new javax.swing.JCheckBox();
        jF15_2 = new javax.swing.JCheckBox();
        jF15_3 = new javax.swing.JCheckBox();
        jF13_5 = new javax.swing.JCheckBox();
        jF14_5 = new javax.swing.JCheckBox();
        jF8_3 = new javax.swing.JCheckBox();
        jF8_4 = new javax.swing.JCheckBox();
        jF8_5 = new javax.swing.JCheckBox();
        jF8_6 = new javax.swing.JCheckBox();
        jF9_1 = new javax.swing.JCheckBox();
        jF10_5 = new javax.swing.JCheckBox();
        jF12_4 = new javax.swing.JCheckBox();
        jF12_5 = new javax.swing.JCheckBox();
        jF13_1 = new javax.swing.JCheckBox();
        jF13_2 = new javax.swing.JCheckBox();
        jF13_3 = new javax.swing.JCheckBox();
        jF13_4 = new javax.swing.JCheckBox();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        jF23_5 = new javax.swing.JCheckBox();
        jF24_4 = new javax.swing.JCheckBox();
        jF24_1 = new javax.swing.JCheckBox();
        jF24_2 = new javax.swing.JCheckBox();
        jF24_3 = new javax.swing.JCheckBox();
        jF24_6 = new javax.swing.JCheckBox();
        jF24_5 = new javax.swing.JCheckBox();
        jF25_5 = new javax.swing.JCheckBox();
        jF25_2 = new javax.swing.JCheckBox();
        jF25_1 = new javax.swing.JCheckBox();
        jF25_4 = new javax.swing.JCheckBox();
        jF25_6 = new javax.swing.JCheckBox();
        jF25_3 = new javax.swing.JCheckBox();
        jF26_6 = new javax.swing.JCheckBox();
        jF26_2 = new javax.swing.JCheckBox();
        jF26_5 = new javax.swing.JCheckBox();
        jF26_1 = new javax.swing.JCheckBox();
        jF26_3 = new javax.swing.JCheckBox();
        jF26_4 = new javax.swing.JCheckBox();
        jF27_1 = new javax.swing.JCheckBox();
        jF27_5 = new javax.swing.JCheckBox();
        jF27_4 = new javax.swing.JCheckBox();
        jF27_6 = new javax.swing.JCheckBox();
        jF27_3 = new javax.swing.JCheckBox();
        jF27_2 = new javax.swing.JCheckBox();
        jF28_3 = new javax.swing.JCheckBox();
        jF28_6 = new javax.swing.JCheckBox();
        jF28_5 = new javax.swing.JCheckBox();
        jF28_1 = new javax.swing.JCheckBox();
        jF28_2 = new javax.swing.JCheckBox();
        jF28_4 = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();
        jEffekte_1 = new javax.swing.JPanel();
        jVor1 = new javax.swing.JCheckBox();
        jRueck1 = new javax.swing.JCheckBox();
        jLabel32 = new javax.swing.JLabel();
        jRueck2 = new javax.swing.JCheckBox();
        jVor2 = new javax.swing.JCheckBox();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jVor3 = new javax.swing.JCheckBox();
        jRueck3 = new javax.swing.JCheckBox();
        jBlink6 = new javax.swing.JCheckBox();
        jLabel46 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel40 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jBlink_Pausezeit_3 = new javax.swing.JTextField();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jMARs2 = new javax.swing.JCheckBox();
        jMARs1 = new javax.swing.JCheckBox();
        jMARs3 = new javax.swing.JCheckBox();
        jVor4 = new javax.swing.JCheckBox();
        jVor5 = new javax.swing.JCheckBox();
        jVor6 = new javax.swing.JCheckBox();
        jMARs4 = new javax.swing.JCheckBox();
        jBlink1 = new javax.swing.JCheckBox();
        jBlink2 = new javax.swing.JCheckBox();
        jBlink3 = new javax.swing.JCheckBox();
        jRueck4 = new javax.swing.JCheckBox();
        jRueck5 = new javax.swing.JCheckBox();
        jRueck6 = new javax.swing.JCheckBox();
        jBlink4 = new javax.swing.JCheckBox();
        jBlink5 = new javax.swing.JCheckBox();
        jBlink_Pausezeit_4 = new javax.swing.JTextField();
        jBlinkfrequenzMars = new javax.swing.JTextField();
        jDoppelBlink1 = new javax.swing.JCheckBox();
        jDoppelBlink2 = new javax.swing.JCheckBox();
        jDoppelBlink3 = new javax.swing.JCheckBox();
        jDoppelBlink4 = new javax.swing.JCheckBox();
        jDoppelBlink5 = new javax.swing.JCheckBox();
        jDoppelBlink6 = new javax.swing.JCheckBox();
        jLabel75 = new javax.swing.JLabel();
        jBlink_Einschaltzeit_1 = new javax.swing.JTextField();
        jBlink_Einschaltzeit_2 = new javax.swing.JTextField();
        jBlink_Einschaltzeit_3 = new javax.swing.JTextField();
        jBlink_Einschaltzeit_4 = new javax.swing.JTextField();
        jLabel79 = new javax.swing.JLabel();
        jBlink_Einschaltzeit_5 = new javax.swing.JTextField();
        jBlink_Einschaltzeit_6 = new javax.swing.JTextField();
        jBlink_Pausezeit_1 = new javax.swing.JTextField();
        jBlink_Pausezeit_2 = new javax.swing.JTextField();
        jAuxInv6 = new javax.swing.JCheckBox();
        jAuxInv2 = new javax.swing.JCheckBox();
        jAuxInv1 = new javax.swing.JCheckBox();
        jAuxInv3 = new javax.swing.JCheckBox();
        jAuxInv4 = new javax.swing.JCheckBox();
        jAuxInv5 = new javax.swing.JCheckBox();
        jLabel26 = new javax.swing.JLabel();
        jBl_Inv1 = new javax.swing.JCheckBox();
        jBl_Inv2 = new javax.swing.JCheckBox();
        jBl_Inv3 = new javax.swing.JCheckBox();
        jBl_Inv4 = new javax.swing.JCheckBox();
        jBl_Inv5 = new javax.swing.JCheckBox();
        jBl_Inv6 = new javax.swing.JCheckBox();
        jLabel95 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel96 = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        jBlink_Pausezeit_5 = new javax.swing.JTextField();
        jBlink_Pausezeit_6 = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        jBlinkfrequenz3 = new javax.swing.JTextField();
        jBlinkfrequenz4 = new javax.swing.JTextField();
        jBlinkfrequenz1 = new javax.swing.JTextField();
        jBlinkfrequenz2 = new javax.swing.JTextField();
        jBlinkfrequenz5 = new javax.swing.JTextField();
        jBlinkfrequenz6 = new javax.swing.JTextField();
        jBlinkBild = new javax.swing.JLabel();
        jKick6 = new javax.swing.JCheckBox();
        jKick1 = new javax.swing.JCheckBox();
        jKick2 = new javax.swing.JCheckBox();
        jKick3 = new javax.swing.JCheckBox();
        jKick4 = new javax.swing.JCheckBox();
        jKick5 = new javax.swing.JCheckBox();
        jEffekte_2 = new javax.swing.JPanel();
        jDimmen1 = new javax.swing.JTextField();
        jDimmen2 = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jDimmen3 = new javax.swing.JTextField();
        jRangier6 = new javax.swing.JCheckBox();
        jKickrueck = new javax.swing.JTextField();
        jKickvor = new javax.swing.JTextField();
        jLabel68 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        jbDimmFS2 = new javax.swing.JCheckBox();
        jbDimmFS1 = new javax.swing.JCheckBox();
        jbDimmFS3 = new javax.swing.JCheckBox();
        jbDimmFS4 = new javax.swing.JCheckBox();
        jRangier1 = new javax.swing.JCheckBox();
        jRangier2 = new javax.swing.JCheckBox();
        jRangier3 = new javax.swing.JCheckBox();
        jRangier4 = new javax.swing.JCheckBox();
        jRangier5 = new javax.swing.JCheckBox();
        jDimmen4 = new javax.swing.JTextField();
        jDimm_FS = new javax.swing.JTextField();
        jRangierF3 = new javax.swing.JCheckBox();
        jRangierF4 = new javax.swing.JCheckBox();
        jTuerIn1 = new javax.swing.JCheckBox();
        jSchaffnerIn1 = new javax.swing.JCheckBox();
        jSignalIn1 = new javax.swing.JCheckBox();
        jGlockeIn1 = new javax.swing.JCheckBox();
        jLabel101 = new javax.swing.JLabel();
        jMindestSchlt1 = new javax.swing.JTextField();
        jMindestSchlt2 = new javax.swing.JTextField();
        jMindestSchlt3 = new javax.swing.JTextField();
        jLabel102 = new javax.swing.JLabel();
        jIn1Aux1 = new javax.swing.JCheckBox();
        jIn1Aux2 = new javax.swing.JCheckBox();
        jIn1Aux3 = new javax.swing.JCheckBox();
        jIn1Aux4 = new javax.swing.JCheckBox();
        jIn1Aux5 = new javax.swing.JCheckBox();
        jIn1Aux6 = new javax.swing.JCheckBox();
        jLabel104 = new javax.swing.JLabel();
        jLabel105 = new javax.swing.JLabel();
        jLabel106 = new javax.swing.JLabel();
        jLabel107 = new javax.swing.JLabel();
        jLabel108 = new javax.swing.JLabel();
        jLabel109 = new javax.swing.JLabel();
        jLabel110 = new javax.swing.JLabel();
        jLabel111 = new javax.swing.JLabel();
        jLabel112 = new javax.swing.JLabel();
        jDimmFS3 = new javax.swing.JTextField();
        jDimmFS4 = new javax.swing.JTextField();
        jDimmFS1 = new javax.swing.JTextField();
        jDimmFS2 = new javax.swing.JTextField();
        jLabel67 = new javax.swing.JLabel();
        jLabel103 = new javax.swing.JLabel();
        jLabel113 = new javax.swing.JLabel();
        jLabel114 = new javax.swing.JLabel();
        jLabel115 = new javax.swing.JLabel();
        jLabel116 = new javax.swing.JLabel();
        jLabel117 = new javax.swing.JLabel();
        jLabel99 = new javax.swing.JLabel();
        jLabel118 = new javax.swing.JLabel();
        jLabel119 = new javax.swing.JLabel();
        jLabel120 = new javax.swing.JLabel();
        jLabel121 = new javax.swing.JLabel();
        jLabel122 = new javax.swing.JLabel();
        jIn2Aux1 = new javax.swing.JCheckBox();
        jIn2Aux2 = new javax.swing.JCheckBox();
        jIn2Aux3 = new javax.swing.JCheckBox();
        jIn2Aux4 = new javax.swing.JCheckBox();
        jIn2Aux5 = new javax.swing.JCheckBox();
        jIn2Aux6 = new javax.swing.JCheckBox();
        jIn3Aux6 = new javax.swing.JCheckBox();
        jIn3Aux1 = new javax.swing.JCheckBox();
        jIn3Aux4 = new javax.swing.JCheckBox();
        jIn3Aux2 = new javax.swing.JCheckBox();
        jIn3Aux3 = new javax.swing.JCheckBox();
        jIn3Aux5 = new javax.swing.JCheckBox();
        jLabel100 = new javax.swing.JLabel();
        jLabel123 = new javax.swing.JLabel();
        jLabel124 = new javax.swing.JLabel();
        jLabel125 = new javax.swing.JLabel();
        jTuerIn2 = new javax.swing.JCheckBox();
        jSchaffnerIn2 = new javax.swing.JCheckBox();
        jSignalIn2 = new javax.swing.JCheckBox();
        jGlockeIn2 = new javax.swing.JCheckBox();
        jSignalIn3 = new javax.swing.JCheckBox();
        jGlockeIn3 = new javax.swing.JCheckBox();
        jSchaffnerIn3 = new javax.swing.JCheckBox();
        jTuerIn3 = new javax.swing.JCheckBox();
        jLabel126 = new javax.swing.JLabel();
        jLabel127 = new javax.swing.JLabel();
        jLabel128 = new javax.swing.JLabel();
        jLabel129 = new javax.swing.JLabel();
        jLabel130 = new javax.swing.JLabel();
        jLabel131 = new javax.swing.JLabel();
        jIn1Pos1 = new javax.swing.JCheckBox();
        jIn1Pos2 = new javax.swing.JCheckBox();
        jIn1Pos3 = new javax.swing.JCheckBox();
        jIn2Pos1 = new javax.swing.JCheckBox();
        jIn2Pos2 = new javax.swing.JCheckBox();
        jIn2Pos3 = new javax.swing.JCheckBox();
        jSound = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        jF1_TP = new javax.swing.JCheckBox();
        jF2_TP = new javax.swing.JCheckBox();
        jF3_TP = new javax.swing.JCheckBox();
        jF4_TP = new javax.swing.JCheckBox();
        jF5_TP = new javax.swing.JCheckBox();
        jF1_SP = new javax.swing.JCheckBox();
        jF2_SP = new javax.swing.JCheckBox();
        jF3_SP = new javax.swing.JCheckBox();
        jF4_SP = new javax.swing.JCheckBox();
        jF9_SP = new javax.swing.JCheckBox();
        jF11_TP = new javax.swing.JCheckBox();
        jLabel134 = new javax.swing.JLabel();
        jLabel135 = new javax.swing.JLabel();
        jLabel136 = new javax.swing.JLabel();
        jLabel137 = new javax.swing.JLabel();
        jLabel138 = new javax.swing.JLabel();
        jLabel139 = new javax.swing.JLabel();
        jLabel140 = new javax.swing.JLabel();
        jLabel141 = new javax.swing.JLabel();
        jLabel142 = new javax.swing.JLabel();
        jLabel143 = new javax.swing.JLabel();
        jF1_SH = new javax.swing.JCheckBox();
        jF2_SH = new javax.swing.JCheckBox();
        jF3_SH = new javax.swing.JCheckBox();
        jF4_SH = new javax.swing.JCheckBox();
        jF9_SH = new javax.swing.JCheckBox();
        jF11_SP = new javax.swing.JCheckBox();
        jF1_Gl = new javax.swing.JCheckBox();
        jF2_Gl = new javax.swing.JCheckBox();
        jF3_Gl = new javax.swing.JCheckBox();
        jF4_Gl = new javax.swing.JCheckBox();
        jF9_Gl = new javax.swing.JCheckBox();
        jF11_SH = new javax.swing.JCheckBox();
        jF11_Gl = new javax.swing.JCheckBox();
        jLabel144 = new javax.swing.JLabel();
        jLabel145 = new javax.swing.JLabel();
        jF5_SP = new javax.swing.JCheckBox();
        jF5_SH = new javax.swing.JCheckBox();
        jF5_Gl = new javax.swing.JCheckBox();
        jF10_TP = new javax.swing.JCheckBox();
        jLabel146 = new javax.swing.JLabel();
        jLabel147 = new javax.swing.JLabel();
        jF6_TP = new javax.swing.JCheckBox();
        jF6_SP = new javax.swing.JCheckBox();
        jF6_SH = new javax.swing.JCheckBox();
        jF6_Gl = new javax.swing.JCheckBox();
        jF10_SP = new javax.swing.JCheckBox();
        jF12_TP = new javax.swing.JCheckBox();
        jF7_TP = new javax.swing.JCheckBox();
        jF7_SP = new javax.swing.JCheckBox();
        jF7_SH = new javax.swing.JCheckBox();
        jF10_SH = new javax.swing.JCheckBox();
        jF12_SP = new javax.swing.JCheckBox();
        jF7_Gl = new javax.swing.JCheckBox();
        jF8_TP = new javax.swing.JCheckBox();
        jF8_SP = new javax.swing.JCheckBox();
        jF10_Gl = new javax.swing.JCheckBox();
        jF12_SH = new javax.swing.JCheckBox();
        jF8_SH = new javax.swing.JCheckBox();
        jF8_Gl = new javax.swing.JCheckBox();
        jF9_TP = new javax.swing.JCheckBox();
        jF12_Gl = new javax.swing.JCheckBox();
        jLabel148 = new javax.swing.JLabel();
        jLabel150 = new javax.swing.JLabel();
        jF13_TP = new javax.swing.JCheckBox();
        jF14_TP = new javax.swing.JCheckBox();
        jF15_TP = new javax.swing.JCheckBox();
        jF16_TP = new javax.swing.JCheckBox();
        jF17_TP = new javax.swing.JCheckBox();
        jF13_SP = new javax.swing.JCheckBox();
        jF14_SP = new javax.swing.JCheckBox();
        jF15_SP = new javax.swing.JCheckBox();
        jF16_SP = new javax.swing.JCheckBox();
        jF21_SP = new javax.swing.JCheckBox();
        jF23_TP = new javax.swing.JCheckBox();
        jLabel149 = new javax.swing.JLabel();
        jLabel151 = new javax.swing.JLabel();
        jLabel152 = new javax.swing.JLabel();
        jLabel153 = new javax.swing.JLabel();
        jLabel154 = new javax.swing.JLabel();
        jLabel155 = new javax.swing.JLabel();
        jLabel156 = new javax.swing.JLabel();
        jLabel157 = new javax.swing.JLabel();
        jLabel158 = new javax.swing.JLabel();
        jLabel159 = new javax.swing.JLabel();
        jF13_SH = new javax.swing.JCheckBox();
        jF14_SH = new javax.swing.JCheckBox();
        jF15_SH = new javax.swing.JCheckBox();
        jF16_SH = new javax.swing.JCheckBox();
        jF21_SH = new javax.swing.JCheckBox();
        jF23_SP = new javax.swing.JCheckBox();
        jF13_Gl = new javax.swing.JCheckBox();
        jF14_Gl = new javax.swing.JCheckBox();
        jF15_Gl = new javax.swing.JCheckBox();
        jF16_Gl = new javax.swing.JCheckBox();
        jF21_Gl = new javax.swing.JCheckBox();
        jF23_SH = new javax.swing.JCheckBox();
        jF23_Gl = new javax.swing.JCheckBox();
        jLabel160 = new javax.swing.JLabel();
        jLabel161 = new javax.swing.JLabel();
        jF17_SP = new javax.swing.JCheckBox();
        jF17_SH = new javax.swing.JCheckBox();
        jF17_Gl = new javax.swing.JCheckBox();
        jF22_TP = new javax.swing.JCheckBox();
        jLabel162 = new javax.swing.JLabel();
        jLabel163 = new javax.swing.JLabel();
        jF18_TP = new javax.swing.JCheckBox();
        jF18_SP = new javax.swing.JCheckBox();
        jF18_SH = new javax.swing.JCheckBox();
        jF18_Gl = new javax.swing.JCheckBox();
        jF22_SP = new javax.swing.JCheckBox();
        jF24_TP = new javax.swing.JCheckBox();
        jF19_TP = new javax.swing.JCheckBox();
        jF19_SP = new javax.swing.JCheckBox();
        jF19_SH = new javax.swing.JCheckBox();
        jF22_SH = new javax.swing.JCheckBox();
        jF24_SP = new javax.swing.JCheckBox();
        jF19_Gl = new javax.swing.JCheckBox();
        jF20_TP = new javax.swing.JCheckBox();
        jF20_SP = new javax.swing.JCheckBox();
        jF22_Gl = new javax.swing.JCheckBox();
        jF24_SH = new javax.swing.JCheckBox();
        jF20_SH = new javax.swing.JCheckBox();
        jF20_Gl = new javax.swing.JCheckBox();
        jF21_TP = new javax.swing.JCheckBox();
        jF24_Gl = new javax.swing.JCheckBox();
        jLabel164 = new javax.swing.JLabel();
        jLabel165 = new javax.swing.JLabel();
        jLabel132 = new javax.swing.JLabel();
        jLautSlider = new javax.swing.JSlider();
        jLaut = new javax.swing.JTextField();
        jLabel133 = new javax.swing.JLabel();
        jLabel166 = new javax.swing.JLabel();
        jServo = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jIn3IsServo = new javax.swing.JCheckBox();
        jServoF9 = new javax.swing.JCheckBox();
        jServoF10 = new javax.swing.JCheckBox();
        jServoF12 = new javax.swing.JCheckBox();
        jServoF5 = new javax.swing.JCheckBox();
        jServoF6 = new javax.swing.JCheckBox();
        jServoF7 = new javax.swing.JCheckBox();
        jServoF8 = new javax.swing.JCheckBox();
        jLabel167 = new javax.swing.JLabel();
        jGeschwindigkeit = new javax.swing.JTextField();
        jServoF11 = new javax.swing.JCheckBox();
        jLabel168 = new javax.swing.JLabel();
        jLabel169 = new javax.swing.JLabel();
        jLabel170 = new javax.swing.JLabel();
        jlinkerAnschlag = new javax.swing.JTextField();
        jrechterAnschlag = new javax.swing.JTextField();
        jLabel171 = new javax.swing.JLabel();
        jLabel172 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel173 = new javax.swing.JLabel();
        jFS0_F5 = new javax.swing.JCheckBox();
        jFS0_F6 = new javax.swing.JCheckBox();
        jFS0_F7 = new javax.swing.JCheckBox();
        jFS0_F8 = new javax.swing.JCheckBox();
        jFS0_F1 = new javax.swing.JCheckBox();
        jFS0_F2 = new javax.swing.JCheckBox();
        jFS0_F3 = new javax.swing.JCheckBox();
        jFS0_F4 = new javax.swing.JCheckBox();
        jLabel174 = new javax.swing.JLabel();
        jFS0_AUX1 = new javax.swing.JCheckBox();
        jFS0_AUX2 = new javax.swing.JCheckBox();
        jFS0_AUX3 = new javax.swing.JCheckBox();
        jFS0_AUX4 = new javax.swing.JCheckBox();
        jFS0_AUX5 = new javax.swing.JCheckBox();
        jFS0_AUX6 = new javax.swing.JCheckBox();
        jLabel175 = new javax.swing.JLabel();
        jFS0_AUXinv1 = new javax.swing.JCheckBox();
        jFS0_AUXinv2 = new javax.swing.JCheckBox();
        jFS0_AUXinv3 = new javax.swing.JCheckBox();
        jFS0_AUXinv4 = new javax.swing.JCheckBox();
        jFS0_AUXinv5 = new javax.swing.JCheckBox();
        jFS0_AUXinv6 = new javax.swing.JCheckBox();
        jSeparator4 = new javax.swing.JSeparator();
        jFS0_AUX7 = new javax.swing.JCheckBox();
        jFS0_AUX8 = new javax.swing.JCheckBox();
        jFS0_AUX9 = new javax.swing.JCheckBox();
        jFS0_AUX10 = new javax.swing.JCheckBox();
        jFS0_AUX11 = new javax.swing.JCheckBox();
        jFS0_AUX12 = new javax.swing.JCheckBox();
        jLabel176 = new javax.swing.JLabel();
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
        jLabel17 = new javax.swing.JLabel();
        jPacketTimeOut = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jComment = new javax.swing.JTextArea();
        jLabel36 = new javax.swing.JLabel();
        jCV_Anzeige = new javax.swing.JComboBox();
        jOpen = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        jDecoderAdresse1 = new javax.swing.JTextField();
        jDecoderAdresse = new javax.swing.JTextField();
        jCV_Inhalt = new javax.swing.JTextField();
        jDecoderlesenSchreiben = new javax.swing.JButton();
        jAbbrechen = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("FD-R extended"));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Adresse");

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel28.setText("(Mehrfach-Traktions-Adr.)");

        jDirekteingabe.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jDirekteingabe.setText("Direkt ein");
        jDirekteingabe.setToolTipText("Hier kann die direkte Eingabe von CVs eingeschaltet werden.");
        jDirekteingabe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDirekteingabeActionPerformed(evt);
            }
        });

        buttonGroup1.add(jlangeAdr);
        jlangeAdr.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jlangeAdr.setText("lang (CV#17 + 18)");
        jlangeAdr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jlangeAdrActionPerformed(evt);
            }
        });

        jSave.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jSave.setText("Speichern");
        jSave.setToolTipText("<html>Gespeicherte Dateien können mit einem normalen<br>\nEditor geöffnet werden. Die Werte dürfen geändert werden.<br>\nDie Reihenfolge und Leerzeilen dürfen NICHT verändert werden!");
        jSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSaveActionPerformed(evt);
            }
        });

        buttonGroup1.add(jKurzeAdr);
        jKurzeAdr.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jKurzeAdr.setText("kurz (CV#1)");
        jKurzeAdr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jKurzeAdrActionPerformed(evt);
            }
        });

        jDecodereigenschaften.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jDecodereigenschaften.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jCV29.setToolTipText("<html>Verwendete CVs:<br>\nCV#28, CV#29, CV#47");
        jCV29.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jCV29ComponentShown(evt);
            }
        });
        jCV29.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jRichtung.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRichtung.setText("Fahrtrichtung umdrehen");
        jRichtung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRichtungActionPerformed(evt);
            }
        });
        jCV29.add(jRichtung, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jFS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS.setSelected(true);
        jFS.setText("28 / 128 Fahrstufen");
        jFS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFSActionPerformed(evt);
            }
        });
        jCV29.add(jFS, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, -1));

        jAnalog1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAnalog1.setSelected(true);
        jAnalog1.setText("Analog-Modus");
        jAnalog1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAnalog1ActionPerformed(evt);
            }
        });
        jCV29.add(jAnalog1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        jRailCom.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRailCom.setSelected(true);
        jRailCom.setText("RailCom ein");
        jRailCom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRailComActionPerformed(evt);
            }
        });
        jCV29.add(jRailCom, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        jAnalog3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAnalog3.setText("---");
        jAnalog3.setEnabled(false);
        jCV29.add(jAnalog3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));

        jLongAddr.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLongAddr.setText("lange Adresse");
        jLongAddr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jLongAddrActionPerformed(evt);
            }
        });
        jCV29.add(jLongAddr, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        jLongAddr1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLongAddr1.setText("---");
        jLongAddr1.setEnabled(false);
        jCV29.add(jLongAddr1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, -1));

        jLongAddr2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLongAddr2.setText("---");
        jLongAddr2.setEnabled(false);
        jCV29.add(jLongAddr2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));

        jLabel41.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel41.setText("RailCom Verhalten");
        jCV29.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 30, -1, -1));

        jMM_Addr_2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jMM_Addr_2.setText("4");
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
        jCV29.add(jMM_Addr_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 150, 40, -1));
        jCV29.add(jBild, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jManID.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jManID.setText("NMRA Man.-ID: xxx");
        jCV29.add(jManID, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, -1, -1));

        jVersion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jVersion.setText("Decoder-Version: xx");
        jCV29.add(jVersion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, -1, -1));

        jBild1.setMaximumSize(new java.awt.Dimension(160, 100));
        jCV29.add(jBild1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 200, 190, 100));

        jBroadCasst.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBroadCasst.setSelected(true);
        jBroadCasst.setText("BroadCast Meldungen");
        jBroadCasst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBroadCasstActionPerformed(evt);
            }
        });
        jCV29.add(jBroadCasst, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 60, -1, -1));

        jChannel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jChannel2.setSelected(true);
        jChannel2.setText("Kanal 2 Meldungen");
        jChannel2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jChannel2ActionPerformed(evt);
            }
        });
        jCV29.add(jChannel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 80, -1, -1));

        jLabel56.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel56.setText("zweite MM-Adresse");
        jCV29.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 130, -1, -1));

        jDecodereigenschaften.addTab("Allgemein", jCV29);

        jFunctionMapping.setToolTipText("Verwendete CVs: CV#33 bis CV#46 und CV#180 bis CV#195");
        jFunctionMapping.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFunctionMapping.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jFunctionMappingComponentShown(evt);
            }
        });
        jFunctionMapping.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jFL_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFL_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFL_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jFL_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, -1, -1));

        jF14_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF14_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF14_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF14_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 40, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("AUX");
        jFunctionMapping.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 20, -1, -1));

        jF1_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF1_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF1_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF1_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, -1, -1));

        jF2_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF2_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF2_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF2_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, -1, -1));

        jF3_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF3_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF3_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF3_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, -1, -1));

        jF4_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF4_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF4_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF4_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, -1, -1));

        jF5_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF5_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF5_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF5_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, -1, -1));

        jF16_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF16_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF16_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF16_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 80, -1, -1));

        jF16_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF16_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF16_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF16_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 80, -1, -1));

        jF16_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF16_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF16_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF16_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 80, -1, -1));

        jF16_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF16_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF16_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF16_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 80, -1, -1));

        jF16_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF16_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF16_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF16_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 80, -1, -1));

        jF10_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF10_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF10_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF10_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 260, -1, -1));

        jF16_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF16_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF16_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF16_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 80, -1, -1));

        jFL_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFL_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFL_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jFL_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 40, -1, -1));

        jFR_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFR_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFR_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jFR_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, -1, -1));

        jF1_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF1_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF1_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF1_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, -1, -1));

        jF2_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF2_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF2_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF2_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, -1, -1));

        jF3_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF3_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF3_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF3_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, -1, -1));

        jF4_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF4_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF4_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF4_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, -1, -1));

        jF9_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF9_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF9_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF9_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 240, -1, -1));

        jF11_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF11_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF11_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF11_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 280, -1, -1));

        jF17_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF17_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF17_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF17_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 100, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel6.setText("FL");
        jFunctionMapping.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel7.setText("FR");
        jFunctionMapping.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel8.setText("F1");
        jFunctionMapping.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel9.setText("F2");
        jFunctionMapping.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel10.setText("F3");
        jFunctionMapping.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel11.setText("F4");
        jFunctionMapping.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, -1, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel12.setText("F5");
        jFunctionMapping.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, -1, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel13.setText("F6");
        jFunctionMapping.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, -1, -1));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel14.setText("F7");
        jFunctionMapping.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, -1, -1));

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel15.setText("F8");
        jFunctionMapping.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, -1, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel16.setText("F9");
        jFunctionMapping.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, -1, -1));

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel18.setText("F11");
        jFunctionMapping.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, -1, -1));

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel19.setText("F14");
        jFunctionMapping.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 40, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("FR = F0 (Licht) hinten");
        jFunctionMapping.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 0, -1, -1));

        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel33.setText("FL = F0 (Licht) vorne");
        jFunctionMapping.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 0, -1, -1));

        jFL_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFL_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFL_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jFL_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, -1, -1));

        jFR_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFR_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFR_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jFR_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 60, -1, -1));

        jF1_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF1_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF1_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF1_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, -1, -1));

        jF2_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF2_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF2_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF2_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 100, -1, -1));

        jF3_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF3_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF3_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF3_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 120, -1, -1));

        jF4_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF4_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF4_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF4_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 140, -1, -1));

        jF9_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF9_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF9_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF9_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 240, -1, -1));

        jF17_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF17_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF17_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF17_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 100, -1, -1));

        jF17_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF17_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF17_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF17_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 100, -1, -1));

        jF17_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF17_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF17_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF17_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 100, -1, -1));

        jF17_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF17_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF17_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF17_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 100, -1, -1));

        jF17_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF17_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF17_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF17_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 100, -1, -1));

        jF11_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF11_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF11_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF11_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 280, -1, -1));

        jF18_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF18_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF18_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF18_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 120, -1, -1));

        jF19_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF19_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF19_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF19_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 140, -1, -1));

        jFL_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFL_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFL_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jFL_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, -1, -1));

        jFR_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFR_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFR_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jFR_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, -1, -1));

        jF1_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF1_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF1_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF1_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, -1, -1));

        jF2_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF2_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF2_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF2_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, -1, -1));

        jF3_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF3_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF3_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF3_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, -1, -1));

        jF4_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF4_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF4_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF4_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 140, -1, -1));

        jF9_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF9_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF9_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF9_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 240, -1, -1));

        jF11_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF11_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF11_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF11_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 280, -1, -1));

        jF18_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF18_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF18_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF18_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 120, -1, -1));

        jF18_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF18_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF18_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF18_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 120, -1, -1));

        jF18_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF18_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF18_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF18_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 120, -1, -1));

        jF18_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF18_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF18_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF18_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 120, -1, -1));

        jF18_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF18_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF18_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF18_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 120, -1, -1));

        jF20_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF20_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF20_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF20_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 160, -1, -1));

        jFL_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFL_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFL_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jFL_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, -1, -1));

        jFR_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFR_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFR_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jFR_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, -1, -1));

        jF1_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF1_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF1_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF1_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, -1, -1));

        jF2_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF2_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF2_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF2_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, -1, -1));

        jF3_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF3_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF3_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF3_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 120, -1, -1));

        jF4_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF4_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF4_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF4_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, -1, -1));

        jF9_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF9_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF9_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF9_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 240, -1, -1));

        jF11_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF11_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF11_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF11_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 280, -1, -1));

        jF19_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF19_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF19_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF19_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 140, -1, -1));

        jF19_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF19_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF19_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF19_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 140, -1, -1));

        jF19_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF19_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF19_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF19_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 140, -1, -1));

        jF19_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF19_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF19_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF19_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 140, -1, -1));

        jF19_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF19_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF19_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF19_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 140, -1, -1));

        jLabel58.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel58.setText("2");
        jFunctionMapping.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, -1, -1));

        jLabel51.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel51.setText("2");
        jFunctionMapping.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 20, -1, -1));

        jLabel52.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel52.setText("3");
        jFunctionMapping.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 20, 10, -1));

        jLabel59.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel59.setText("1");
        jFunctionMapping.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, -1, -1));

        jLabel53.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel53.setText("1");
        jFunctionMapping.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 20, 10, -1));

        jF21_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF21_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF21_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF21_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 180, -1, -1));

        jFL_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFL_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFL_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jFL_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, -1, -1));

        jFR_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFR_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFR_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jFR_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, -1, -1));

        jF1_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF1_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF1_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF1_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, -1, -1));

        jF2_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF2_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF2_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF2_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 100, -1, -1));

        jF3_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF3_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF3_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF3_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 120, -1, -1));

        jF4_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF4_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF4_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF4_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 140, -1, -1));

        jF9_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF9_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF9_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF9_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 240, -1, -1));

        jF11_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF11_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF11_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF11_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 280, -1, -1));

        jF20_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF20_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF20_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF20_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 160, -1, -1));

        jF20_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF20_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF20_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF20_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 160, -1, -1));

        jF20_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF20_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF20_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF20_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 160, -1, -1));

        jF20_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF20_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF20_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF20_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 160, -1, -1));

        jF20_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF20_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF20_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF20_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 160, -1, -1));

        jF22_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF22_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF22_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF22_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 200, -1, -1));

        jF14_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF14_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF14_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF14_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 40, -1, -1));

        jFR_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFR_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFR_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jFR_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, -1, -1));

        jF5_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF5_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF5_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF5_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 160, -1, -1));

        jF5_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF5_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF5_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF5_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 160, -1, -1));

        jF5_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF5_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF5_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF5_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 160, -1, -1));

        jF5_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF5_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF5_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF5_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 160, -1, -1));

        jF10_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF10_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF10_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF10_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 260, -1, -1));

        jF11_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF11_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF11_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF11_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 280, -1, -1));

        jF21_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF21_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF21_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF21_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 180, -1, -1));

        jF21_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF21_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF21_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF21_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 180, -1, -1));

        jF21_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF21_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF21_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF21_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 180, -1, -1));

        jF21_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF21_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF21_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF21_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 180, -1, -1));

        jF21_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF21_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF21_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF21_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 180, -1, -1));

        jLabel60.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel60.setText("4");
        jFunctionMapping.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, -1, -1));

        jLabel61.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel61.setText("3");
        jFunctionMapping.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, -1, -1));

        jF22_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF22_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF22_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF22_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 200, -1, -1));

        jF14_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF14_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF14_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF14_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 40, -1, -1));

        jF5_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF5_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF5_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF5_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 160, -1, -1));

        jF6_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF6_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF6_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF6_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, -1, -1));

        jF6_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF6_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF6_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF6_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 180, -1, -1));

        jF6_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF6_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF6_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF6_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 180, -1, -1));

        jF6_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF6_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF6_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF6_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 180, -1, -1));

        jF10_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF10_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF10_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF10_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 260, -1, -1));

        jF12_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF12_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF12_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF12_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 300, -1, -1));

        jF15_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF15_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF15_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF15_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 60, -1, -1));

        jF22_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF22_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF22_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF22_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 200, -1, -1));

        jF22_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF22_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF22_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF22_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 200, -1, -1));

        jF22_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF22_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF22_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF22_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 200, -1, -1));

        jF22_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF22_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF22_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF22_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 200, -1, -1));

        jF23_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF23_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF23_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF23_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 220, -1, -1));

        jF14_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF14_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF14_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF14_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 40, -1, -1));

        jF6_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF6_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF6_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF6_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 180, -1, -1));

        jF6_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF6_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF6_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF6_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 180, -1, -1));

        jF7_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF7_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF7_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF7_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 200, -1, -1));

        jF7_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF7_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF7_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF7_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, -1, -1));

        jF7_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF7_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF7_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF7_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 200, -1, -1));

        jF10_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF10_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF10_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF10_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 260, -1, -1));

        jF12_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF12_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF12_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF12_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 300, -1, -1));

        jF15_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF15_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF15_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF15_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 60, -1, -1));

        jF15_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF15_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF15_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF15_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 60, -1, -1));

        jF23_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF23_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF23_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF23_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 220, -1, -1));

        jF23_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF23_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF23_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF23_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 220, -1, -1));

        jF23_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF23_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF23_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF23_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 220, -1, -1));

        jLabel62.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel62.setText("6");
        jFunctionMapping.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, -1, -1));

        jLabel63.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel63.setText("5");
        jFunctionMapping.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, -1, -1));

        jF23_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF23_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF23_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF23_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 220, -1, -1));

        jF14_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF14_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF14_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF14_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 40, -1, -1));

        jF7_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF7_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF7_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF7_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 200, -1, -1));

        jF7_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF7_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF7_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF7_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 200, -1, -1));

        jF7_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF7_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF7_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF7_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 200, -1, -1));

        jF8_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF8_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF8_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF8_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, -1, -1));

        jF8_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF8_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF8_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF8_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 220, -1, -1));

        jF10_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF10_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF10_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF10_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 260, -1, -1));

        jF12_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF12_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF12_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF12_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 300, -1, -1));

        jF12_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF12_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF12_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF12_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 300, -1, -1));

        jF13_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF13_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF13_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF13_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 320, -1, -1));

        jF15_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF15_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF15_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF15_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 60, -1, -1));

        jF15_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF15_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF15_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF15_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 60, -1, -1));

        jF15_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF15_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF15_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF15_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 60, -1, -1));

        jF13_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF13_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF13_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF13_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 320, -1, -1));

        jF14_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF14_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF14_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF14_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 40, -1, -1));

        jF8_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF8_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF8_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF8_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 220, -1, -1));

        jF8_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF8_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF8_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF8_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 220, -1, -1));

        jF8_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF8_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF8_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF8_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 220, -1, -1));

        jF8_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF8_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF8_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF8_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 220, -1, -1));

        jF9_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF9_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF9_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF9_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 240, -1, -1));

        jF10_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF10_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF10_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF10_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 260, -1, -1));

        jF12_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF12_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF12_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF12_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 300, -1, -1));

        jF12_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF12_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF12_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF12_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 300, -1, -1));

        jF13_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF13_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF13_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF13_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 320, -1, -1));

        jF13_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF13_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF13_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF13_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 320, -1, -1));

        jF13_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF13_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF13_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF13_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 320, -1, -1));

        jF13_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF13_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF13_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF13_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 320, -1, -1));

        jLabel64.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel64.setText("6");
        jFunctionMapping.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 20, -1, -1));

        jLabel65.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel65.setText("4");
        jFunctionMapping.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 20, -1, -1));

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel20.setText("F12");
        jFunctionMapping.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, -1, -1));

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel21.setText("F13");
        jFunctionMapping.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, -1, -1));

        jLabel83.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel83.setText("5");
        jFunctionMapping.add(jLabel83, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 20, -1, -1));

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel22.setText("F10");
        jFunctionMapping.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, -1, -1));

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel23.setText("F15");
        jFunctionMapping.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 60, -1, -1));

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel29.setText("F16");
        jFunctionMapping.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 80, -1, -1));

        jLabel37.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel37.setText("F17");
        jFunctionMapping.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 100, -1, -1));

        jLabel84.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel84.setText("F18");
        jFunctionMapping.add(jLabel84, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 120, -1, -1));

        jLabel85.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel85.setText("F19");
        jFunctionMapping.add(jLabel85, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 140, -1, -1));

        jLabel86.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel86.setText("F20");
        jFunctionMapping.add(jLabel86, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 160, -1, -1));

        jLabel87.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel87.setText("F21");
        jFunctionMapping.add(jLabel87, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 180, -1, -1));

        jLabel88.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel88.setText("F22");
        jFunctionMapping.add(jLabel88, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 200, -1, -1));

        jLabel89.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel89.setText("F23");
        jFunctionMapping.add(jLabel89, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 220, -1, -1));

        jLabel90.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel90.setText("F24");
        jFunctionMapping.add(jLabel90, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, -1, -1));

        jLabel91.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel91.setText("F25");
        jFunctionMapping.add(jLabel91, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 260, -1, -1));

        jLabel92.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel92.setText("F26");
        jFunctionMapping.add(jLabel92, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 280, -1, -1));

        jLabel93.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel93.setText("F27");
        jFunctionMapping.add(jLabel93, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 300, -1, -1));

        jLabel94.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel94.setText("F28");
        jFunctionMapping.add(jLabel94, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 320, -1, -1));

        jF23_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF23_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF23_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF23_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 220, -1, -1));

        jF24_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF24_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF24_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF24_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 240, -1, -1));

        jF24_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF24_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF24_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF24_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 240, -1, -1));

        jF24_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF24_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF24_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF24_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 240, -1, -1));

        jF24_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF24_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF24_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF24_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 240, -1, -1));

        jF24_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF24_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF24_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF24_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 240, -1, -1));

        jF24_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF24_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF24_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF24_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 240, -1, -1));

        jF25_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF25_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF25_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF25_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 260, -1, -1));

        jF25_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF25_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF25_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF25_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 260, -1, -1));

        jF25_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF25_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF25_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF25_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 260, -1, -1));

        jF25_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF25_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF25_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF25_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 260, -1, -1));

        jF25_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF25_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF25_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF25_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 260, -1, -1));

        jF25_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF25_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF25_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF25_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 260, -1, -1));

        jF26_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF26_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF26_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF26_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 280, -1, -1));

        jF26_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF26_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF26_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF26_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 280, -1, -1));

        jF26_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF26_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF26_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF26_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 280, -1, -1));

        jF26_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF26_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF26_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF26_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 280, -1, -1));

        jF26_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF26_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF26_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF26_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 280, -1, -1));

        jF26_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF26_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF26_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF26_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 280, -1, -1));

        jF27_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF27_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF27_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF27_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 300, -1, -1));

        jF27_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF27_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF27_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF27_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 300, -1, -1));

        jF27_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF27_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF27_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF27_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 300, -1, -1));

        jF27_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF27_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF27_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF27_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 300, -1, -1));

        jF27_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF27_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF27_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF27_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 300, -1, -1));

        jF27_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF27_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF27_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF27_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 300, -1, -1));

        jF28_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF28_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF28_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF28_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 320, -1, -1));

        jF28_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF28_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF28_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF28_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 320, -1, -1));

        jF28_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF28_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF28_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF28_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 320, -1, -1));

        jF28_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF28_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF28_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF28_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 320, -1, -1));

        jF28_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF28_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF28_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF28_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 320, -1, -1));

        jF28_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF28_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF28_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF28_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 320, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("AUX");
        jFunctionMapping.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jDecodereigenschaften.addTab("F-Zuordnung", jFunctionMapping);

        jEffekte_1.setToolTipText("<html>Verwendete CVs:<br>\nCV#53  bis CV#64, CV#106 bis CV#112, CV#132 bis CV#137, CV#140 bis CV#145");
        jEffekte_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jEffekte_1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jEffekte_1ComponentShown(evt);
            }
        });
        jEffekte_1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jVor1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jVor1.setSelected(true);
        jVor1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jVor1ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jVor1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, -1, -1));

        jRueck1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRueck1.setSelected(true);
        jRueck1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRueck1ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jRueck1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, -1, -1));

        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel32.setText("rück");
        jEffekte_1.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, -1, -1));

        jRueck2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRueck2.setSelected(true);
        jRueck2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRueck2ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jRueck2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, -1, -1));

        jVor2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jVor2.setSelected(true);
        jVor2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jVor2ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jVor2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, -1, -1));

        jLabel44.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel44.setText("3");
        jEffekte_1.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        jLabel45.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel45.setText("vor");
        jEffekte_1.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, -1, -1));

        jVor3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jVor3.setSelected(true);
        jVor3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jVor3ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jVor3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, -1, -1));

        jRueck3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRueck3.setSelected(true);
        jRueck3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRueck3ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jRueck3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, -1, -1));

        jBlink6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBlink6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBlink6ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jBlink6, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 130, -1, -1));

        jLabel46.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel46.setText("AUX");
        jEffekte_1.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel48.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel48.setText("1");
        jEffekte_1.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel30.setText("Kicken");
        jEffekte_1.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, -1, -1));
        jEffekte_1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 420, 10));

        jLabel40.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel40.setText("Blinkfrequenz");
        jEffekte_1.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 190, -1, -1));

        jLabel42.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel42.setText("Blinken(Zeit):");
        jEffekte_1.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, -1));

        jBlink_Pausezeit_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBlink_Pausezeit_3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jBlink_Pausezeit_3.setText("2");
        jBlink_Pausezeit_3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jBlink_Pausezeit_3FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jBlink_Pausezeit_3FocusLost(evt);
            }
        });
        jBlink_Pausezeit_3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jBlink_Pausezeit_3KeyReleased(evt);
            }
        });
        jEffekte_1.add(jBlink_Pausezeit_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, 30, 20));

        jLabel54.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel54.setText("Blinken");
        jEffekte_1.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 10, -1, -1));

        jLabel55.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel55.setText("doppelt");
        jEffekte_1.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 10, -1, -1));

        jLabel57.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel57.setText("MARs");
        jEffekte_1.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, -1, -1));

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel25.setText("Bl.invers");
        jEffekte_1.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 10, -1, -1));

        jLabel69.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel69.setText("4");
        jEffekte_1.add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        jLabel70.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel70.setText("5");
        jEffekte_1.add(jLabel70, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        jLabel71.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel71.setText("6");
        jEffekte_1.add(jLabel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, -1));

        jMARs2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMARs2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMARs2ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jMARs2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 50, -1, -1));

        jMARs1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMARs1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMARs1ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jMARs1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 30, -1, -1));

        jMARs3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMARs3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMARs3ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jMARs3, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 70, -1, -1));

        jVor4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jVor4.setSelected(true);
        jVor4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jVor4ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jVor4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, -1, -1));

        jVor5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jVor5.setSelected(true);
        jVor5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jVor5ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jVor5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, -1, -1));

        jVor6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jVor6.setSelected(true);
        jVor6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jVor6ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jVor6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, -1, -1));

        jMARs4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMARs4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMARs4ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jMARs4, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 90, -1, -1));

        jBlink1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBlink1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBlink1ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jBlink1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 30, -1, -1));

        jBlink2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBlink2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBlink2ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jBlink2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 50, -1, -1));

        jBlink3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBlink3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBlink3ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jBlink3, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 70, -1, -1));

        jRueck4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRueck4.setSelected(true);
        jRueck4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRueck4ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jRueck4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, -1, -1));

        jRueck5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRueck5.setSelected(true);
        jRueck5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRueck5ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jRueck5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 110, -1, -1));

        jRueck6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRueck6.setSelected(true);
        jRueck6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRueck6ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jRueck6, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 130, -1, -1));

        jBlink4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBlink4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBlink4ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jBlink4, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 90, -1, -1));

        jBlink5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBlink5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBlink5ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jBlink5, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 110, -1, -1));

        jBlink_Pausezeit_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBlink_Pausezeit_4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jBlink_Pausezeit_4.setText("2");
        jBlink_Pausezeit_4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jBlink_Pausezeit_4FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jBlink_Pausezeit_4FocusLost(evt);
            }
        });
        jBlink_Pausezeit_4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jBlink_Pausezeit_4KeyReleased(evt);
            }
        });
        jEffekte_1.add(jBlink_Pausezeit_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 270, 30, -1));

        jBlinkfrequenzMars.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBlinkfrequenzMars.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jBlinkfrequenzMars.setText("64");
        jBlinkfrequenzMars.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jBlinkfrequenzMarsFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jBlinkfrequenzMarsFocusLost(evt);
            }
        });
        jBlinkfrequenzMars.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jBlinkfrequenzMarsKeyReleased(evt);
            }
        });
        jEffekte_1.add(jBlinkfrequenzMars, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 340, 30, -1));

        jDoppelBlink1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jDoppelBlink1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDoppelBlink1ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jDoppelBlink1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 30, -1, -1));

        jDoppelBlink2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jDoppelBlink2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDoppelBlink2ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jDoppelBlink2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 50, -1, -1));

        jDoppelBlink3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jDoppelBlink3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDoppelBlink3ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jDoppelBlink3, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 70, -1, -1));

        jDoppelBlink4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jDoppelBlink4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDoppelBlink4ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jDoppelBlink4, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 90, -1, -1));

        jDoppelBlink5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jDoppelBlink5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDoppelBlink5ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jDoppelBlink5, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 110, -1, -1));

        jDoppelBlink6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jDoppelBlink6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDoppelBlink6ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jDoppelBlink6, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 130, -1, -1));

        jLabel75.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel75.setText("Pause");
        jEffekte_1.add(jLabel75, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 190, -1, -1));

        jBlink_Einschaltzeit_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBlink_Einschaltzeit_1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jBlink_Einschaltzeit_1.setText("4");
        jBlink_Einschaltzeit_1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jBlink_Einschaltzeit_1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jBlink_Einschaltzeit_1FocusLost(evt);
            }
        });
        jBlink_Einschaltzeit_1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jBlink_Einschaltzeit_1KeyReleased(evt);
            }
        });
        jEffekte_1.add(jBlink_Einschaltzeit_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 30, 20));

        jBlink_Einschaltzeit_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBlink_Einschaltzeit_2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jBlink_Einschaltzeit_2.setText("4");
        jBlink_Einschaltzeit_2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jBlink_Einschaltzeit_2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jBlink_Einschaltzeit_2FocusLost(evt);
            }
        });
        jBlink_Einschaltzeit_2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jBlink_Einschaltzeit_2KeyReleased(evt);
            }
        });
        jEffekte_1.add(jBlink_Einschaltzeit_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, 30, 20));

        jBlink_Einschaltzeit_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBlink_Einschaltzeit_3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jBlink_Einschaltzeit_3.setText("4");
        jBlink_Einschaltzeit_3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jBlink_Einschaltzeit_3FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jBlink_Einschaltzeit_3FocusLost(evt);
            }
        });
        jBlink_Einschaltzeit_3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jBlink_Einschaltzeit_3KeyReleased(evt);
            }
        });
        jEffekte_1.add(jBlink_Einschaltzeit_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, 30, 20));

        jBlink_Einschaltzeit_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBlink_Einschaltzeit_4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jBlink_Einschaltzeit_4.setText("4");
        jBlink_Einschaltzeit_4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jBlink_Einschaltzeit_4FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jBlink_Einschaltzeit_4FocusLost(evt);
            }
        });
        jBlink_Einschaltzeit_4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jBlink_Einschaltzeit_4KeyReleased(evt);
            }
        });
        jEffekte_1.add(jBlink_Einschaltzeit_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, 30, 20));

        jLabel79.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel79.setText("2");
        jEffekte_1.add(jLabel79, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        jBlink_Einschaltzeit_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBlink_Einschaltzeit_5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jBlink_Einschaltzeit_5.setText("4");
        jBlink_Einschaltzeit_5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jBlink_Einschaltzeit_5FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jBlink_Einschaltzeit_5FocusLost(evt);
            }
        });
        jBlink_Einschaltzeit_5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jBlink_Einschaltzeit_5KeyReleased(evt);
            }
        });
        jEffekte_1.add(jBlink_Einschaltzeit_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, 30, 20));

        jBlink_Einschaltzeit_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBlink_Einschaltzeit_6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jBlink_Einschaltzeit_6.setText("4");
        jBlink_Einschaltzeit_6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jBlink_Einschaltzeit_6FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jBlink_Einschaltzeit_6FocusLost(evt);
            }
        });
        jBlink_Einschaltzeit_6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jBlink_Einschaltzeit_6KeyReleased(evt);
            }
        });
        jEffekte_1.add(jBlink_Einschaltzeit_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, 30, 20));

        jBlink_Pausezeit_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBlink_Pausezeit_1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jBlink_Pausezeit_1.setText("2");
        jBlink_Pausezeit_1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jBlink_Pausezeit_1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jBlink_Pausezeit_1FocusLost(evt);
            }
        });
        jBlink_Pausezeit_1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jBlink_Pausezeit_1KeyReleased(evt);
            }
        });
        jEffekte_1.add(jBlink_Pausezeit_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 210, 30, 20));

        jBlink_Pausezeit_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBlink_Pausezeit_2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jBlink_Pausezeit_2.setText("2");
        jBlink_Pausezeit_2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jBlink_Pausezeit_2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jBlink_Pausezeit_2FocusLost(evt);
            }
        });
        jBlink_Pausezeit_2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jBlink_Pausezeit_2KeyReleased(evt);
            }
        });
        jEffekte_1.add(jBlink_Pausezeit_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 230, 30, 20));

        jAuxInv6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAuxInv6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAuxInv6ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jAuxInv6, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 130, -1, -1));

        jAuxInv2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAuxInv2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAuxInv2ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jAuxInv2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, -1, -1));

        jAuxInv1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAuxInv1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAuxInv1ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jAuxInv1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 30, -1, -1));

        jAuxInv3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAuxInv3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAuxInv3ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jAuxInv3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, -1, -1));

        jAuxInv4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAuxInv4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAuxInv4ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jAuxInv4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, -1, -1));

        jAuxInv5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAuxInv5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAuxInv5ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jAuxInv5, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, -1, -1));

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel26.setText("invers");
        jEffekte_1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, -1, -1));

        jBl_Inv1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBl_Inv1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBl_Inv1ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jBl_Inv1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 30, -1, -1));

        jBl_Inv2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBl_Inv2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBl_Inv2ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jBl_Inv2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 50, -1, -1));

        jBl_Inv3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBl_Inv3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBl_Inv3ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jBl_Inv3, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 70, -1, -1));

        jBl_Inv4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBl_Inv4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBl_Inv4ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jBl_Inv4, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 90, -1, -1));

        jBl_Inv5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBl_Inv5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBl_Inv5ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jBl_Inv5, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 110, -1, -1));

        jBl_Inv6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBl_Inv6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBl_Inv6ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jBl_Inv6, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 130, -1, -1));

        jLabel95.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel95.setText("ein");
        jEffekte_1.add(jLabel95, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, -1, -1));

        jLabel47.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel47.setText("3");
        jEffekte_1.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, -1, -1));

        jLabel50.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel50.setText("AUX");
        jEffekte_1.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, -1));

        jLabel66.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel66.setText("1");
        jEffekte_1.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, -1, -1));

        jLabel72.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel72.setText("4");
        jEffekte_1.add(jLabel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, -1, -1));

        jLabel73.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel73.setText("5");
        jEffekte_1.add(jLabel73, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, -1, -1));

        jLabel96.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel96.setText("6");
        jEffekte_1.add(jLabel96, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, -1, -1));

        jLabel97.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel97.setText("2");
        jEffekte_1.add(jLabel97, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, -1, -1));

        jBlink_Pausezeit_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBlink_Pausezeit_5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jBlink_Pausezeit_5.setText("2");
        jBlink_Pausezeit_5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jBlink_Pausezeit_5FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jBlink_Pausezeit_5FocusLost(evt);
            }
        });
        jBlink_Pausezeit_5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jBlink_Pausezeit_5KeyReleased(evt);
            }
        });
        jEffekte_1.add(jBlink_Pausezeit_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 290, 30, -1));

        jBlink_Pausezeit_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBlink_Pausezeit_6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jBlink_Pausezeit_6.setText("2");
        jBlink_Pausezeit_6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jBlink_Pausezeit_6FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jBlink_Pausezeit_6FocusLost(evt);
            }
        });
        jBlink_Pausezeit_6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jBlink_Pausezeit_6KeyReleased(evt);
            }
        });
        jEffekte_1.add(jBlink_Pausezeit_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 310, 30, -1));

        jLabel43.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel43.setText("Blinkfrequenz MARs");
        jEffekte_1.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, -1, -1));

        jBlinkfrequenz3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBlinkfrequenz3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jBlinkfrequenz3.setText("20");
        jBlinkfrequenz3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jBlinkfrequenz3FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jBlinkfrequenz3FocusLost(evt);
            }
        });
        jBlinkfrequenz3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jBlinkfrequenz3KeyReleased(evt);
            }
        });
        jEffekte_1.add(jBlinkfrequenz3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 250, 30, 20));

        jBlinkfrequenz4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBlinkfrequenz4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jBlinkfrequenz4.setText("20");
        jBlinkfrequenz4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jBlinkfrequenz4FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jBlinkfrequenz4FocusLost(evt);
            }
        });
        jBlinkfrequenz4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jBlinkfrequenz4KeyReleased(evt);
            }
        });
        jEffekte_1.add(jBlinkfrequenz4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 270, 30, -1));

        jBlinkfrequenz1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBlinkfrequenz1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jBlinkfrequenz1.setText("20");
        jBlinkfrequenz1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jBlinkfrequenz1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jBlinkfrequenz1FocusLost(evt);
            }
        });
        jBlinkfrequenz1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jBlinkfrequenz1KeyReleased(evt);
            }
        });
        jEffekte_1.add(jBlinkfrequenz1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 210, 30, 20));

        jBlinkfrequenz2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBlinkfrequenz2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jBlinkfrequenz2.setText("20");
        jBlinkfrequenz2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jBlinkfrequenz2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jBlinkfrequenz2FocusLost(evt);
            }
        });
        jBlinkfrequenz2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jBlinkfrequenz2KeyReleased(evt);
            }
        });
        jEffekte_1.add(jBlinkfrequenz2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 230, 30, 20));

        jBlinkfrequenz5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBlinkfrequenz5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jBlinkfrequenz5.setText("20");
        jBlinkfrequenz5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jBlinkfrequenz5FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jBlinkfrequenz5FocusLost(evt);
            }
        });
        jBlinkfrequenz5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jBlinkfrequenz5KeyReleased(evt);
            }
        });
        jEffekte_1.add(jBlinkfrequenz5, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 290, 30, -1));

        jBlinkfrequenz6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBlinkfrequenz6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jBlinkfrequenz6.setText("20");
        jBlinkfrequenz6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jBlinkfrequenz6FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jBlinkfrequenz6FocusLost(evt);
            }
        });
        jBlinkfrequenz6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jBlinkfrequenz6KeyReleased(evt);
            }
        });
        jEffekte_1.add(jBlinkfrequenz6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 310, 30, -1));
        jEffekte_1.add(jBlinkBild, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 200, 210, 150));

        jKick6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jKick6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jKick6ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jKick6, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 130, -1, -1));

        jKick1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jKick1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jKick1ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jKick1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, -1, -1));

        jKick2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jKick2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jKick2ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jKick2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 50, -1, -1));

        jKick3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jKick3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jKick3ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jKick3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 70, -1, -1));

        jKick4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jKick4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jKick4ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jKick4, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 90, -1, -1));

        jKick5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jKick5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jKick5ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jKick5, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 110, -1, -1));

        jDecodereigenschaften.addTab("Effekte 1", jEffekte_1);

        jEffekte_2.setToolTipText("<html>Verwendete CVs:<br>\nCV#59 bis CV#64, CV#113 bis CV#121, CV#123 bis CV#126, CV#131, CV#162, CV#163");
        jEffekte_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jEffekte_2.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jEffekte_2ComponentShown(evt);
            }
        });
        jEffekte_2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jDimmen1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jDimmen1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jDimmen1.setText("15");
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
        jEffekte_2.add(jDimmen1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 30, -1));

        jDimmen2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jDimmen2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jDimmen2.setText("15");
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
        jEffekte_2.add(jDimmen2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 30, -1));

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel31.setText("Dimmen");
        jEffekte_2.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, -1, -1));

        jLabel49.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel49.setText("3");
        jEffekte_2.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        jDimmen3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jDimmen3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jDimmen3.setText("15");
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
        jEffekte_2.add(jDimmen3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, 30, -1));

        jRangier6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRangier6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRangier6ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jRangier6, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 150, -1, -1));

        jKickrueck.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jKickrueck.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jKickrueck.setText("0");
        jKickrueck.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jKickrueckFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jKickrueckFocusLost(evt);
            }
        });
        jKickrueck.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jKickrueckKeyReleased(evt);
            }
        });
        jEffekte_2.add(jKickrueck, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 80, 30, 20));

        jKickvor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jKickvor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jKickvor.setText("0");
        jKickvor.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jKickvorFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jKickvorFocusLost(evt);
            }
        });
        jKickvor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jKickvorKeyReleased(evt);
            }
        });
        jEffekte_2.add(jKickvor, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 50, 30, -1));

        jLabel68.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel68.setText("AUX");
        jEffekte_2.add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        jLabel74.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel74.setText("1");
        jEffekte_2.add(jLabel74, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        jLabel39.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel39.setText("Kicken");
        jEffekte_2.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, -1, -1));
        jEffekte_2.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 420, 10));

        jLabel76.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel76.setText("FS-abhängig");
        jEffekte_2.add(jLabel76, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 30, -1, -1));

        jLabel77.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel77.setText("normal");
        jEffekte_2.add(jLabel77, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, -1, -1));

        jLabel78.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel78.setText("Rangierlicht");
        jEffekte_2.add(jLabel78, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 10, -1, -1));

        jLabel80.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel80.setText("Schalteingänge schalten:");
        jEffekte_2.add(jLabel80, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 190, -1, -1));

        jLabel81.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel81.setText("AUX");
        jEffekte_2.add(jLabel81, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, -1, -1));

        jLabel82.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel82.setText("Servo");
        jEffekte_2.add(jLabel82, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 300, -1, -1));

        jLabel98.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel98.setText("4");
        jEffekte_2.add(jLabel98, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        jbDimmFS2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jbDimmFS2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbDimmFS2ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jbDimmFS2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, -1, -1));

        jbDimmFS1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jbDimmFS1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbDimmFS1ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jbDimmFS1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, -1, -1));

        jbDimmFS3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jbDimmFS3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbDimmFS3ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jbDimmFS3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, -1, -1));

        jbDimmFS4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jbDimmFS4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbDimmFS4ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jbDimmFS4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 110, -1, -1));

        jRangier1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRangier1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRangier1ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jRangier1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 50, -1, -1));

        jRangier2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRangier2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRangier2ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jRangier2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 70, -1, -1));

        jRangier3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRangier3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRangier3ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jRangier3, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 90, -1, -1));

        jRangier4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRangier4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRangier4ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jRangier4, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 110, -1, -1));

        jRangier5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRangier5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRangier5ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jRangier5, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 130, -1, -1));

        jDimmen4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jDimmen4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jDimmen4.setText("15");
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
        jEffekte_2.add(jDimmen4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 30, -1));

        jDimm_FS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jDimm_FS.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jDimm_FS.setText("64");
        jDimm_FS.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jDimm_FSFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jDimm_FSFocusLost(evt);
            }
        });
        jDimm_FS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jDimm_FSKeyReleased(evt);
            }
        });
        jEffekte_2.add(jDimm_FS, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, 30, -1));

        jRangierF3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRangierF3.setText("f3");
        jRangierF3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRangierF3ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jRangierF3, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 50, -1, -1));

        jRangierF4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRangierF4.setText("f4");
        jRangierF4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRangierF4ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jRangierF4, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 70, -1, -1));

        jTuerIn1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTuerIn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTuerIn1ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jTuerIn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 230, -1, -1));

        jSchaffnerIn1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jSchaffnerIn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSchaffnerIn1ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jSchaffnerIn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 230, -1, -1));

        jSignalIn1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jSignalIn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSignalIn1ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jSignalIn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 230, -1, -1));

        jGlockeIn1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jGlockeIn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jGlockeIn1ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jGlockeIn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 230, -1, -1));

        jLabel101.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel101.setText("schalten mit");
        jEffekte_2.add(jLabel101, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 30, -1, -1));

        jMindestSchlt1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMindestSchlt1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jMindestSchlt1.setText("4");
        jMindestSchlt1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jMindestSchlt1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jMindestSchlt1FocusLost(evt);
            }
        });
        jMindestSchlt1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jMindestSchlt1KeyReleased(evt);
            }
        });
        jEffekte_2.add(jMindestSchlt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 300, 30, 20));

        jMindestSchlt2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMindestSchlt2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jMindestSchlt2.setText("4");
        jMindestSchlt2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jMindestSchlt2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jMindestSchlt2FocusLost(evt);
            }
        });
        jMindestSchlt2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jMindestSchlt2KeyReleased(evt);
            }
        });
        jEffekte_2.add(jMindestSchlt2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 320, 30, 20));

        jMindestSchlt3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMindestSchlt3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jMindestSchlt3.setText("4");
        jMindestSchlt3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jMindestSchlt3FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jMindestSchlt3FocusLost(evt);
            }
        });
        jMindestSchlt3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jMindestSchlt3KeyReleased(evt);
            }
        });
        jEffekte_2.add(jMindestSchlt3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 340, 30, 20));

        jLabel102.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel102.setText("2");
        jEffekte_2.add(jLabel102, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        jIn1Aux1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn1Aux1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn1Aux1ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn1Aux1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, -1, -1));

        jIn1Aux2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn1Aux2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn1Aux2ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn1Aux2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 230, -1, -1));

        jIn1Aux3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn1Aux3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn1Aux3ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn1Aux3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 230, -1, -1));

        jIn1Aux4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn1Aux4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn1Aux4ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn1Aux4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 230, -1, -1));

        jIn1Aux5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn1Aux5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn1Aux5ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn1Aux5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 230, -1, -1));

        jIn1Aux6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn1Aux6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn1Aux6ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn1Aux6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 230, -1, -1));

        jLabel104.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel104.setText("vor");
        jEffekte_2.add(jLabel104, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 50, -1, -1));

        jLabel105.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel105.setText("In3");
        jEffekte_2.add(jLabel105, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, -1, -1));

        jLabel106.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel106.setText("rück");
        jEffekte_2.add(jLabel106, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 80, -1, -1));

        jLabel107.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel107.setText("In1");
        jEffekte_2.add(jLabel107, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, -1, -1));

        jLabel108.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel108.setText("Mindestschaltdauer In1");
        jEffekte_2.add(jLabel108, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, -1, -1));

        jLabel109.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel109.setText("Mindestschaltdauer In2");
        jEffekte_2.add(jLabel109, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, -1, -1));

        jLabel110.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel110.setText("Mindestschaltdauer In3");
        jEffekte_2.add(jLabel110, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, -1, -1));

        jLabel111.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel111.setText("In2");
        jEffekte_2.add(jLabel111, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, -1, -1));

        jLabel112.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel112.setText("ab FS");
        jEffekte_2.add(jLabel112, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 140, -1, -1));

        jDimmFS3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jDimmFS3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jDimmFS3.setText("20");
        jDimmFS3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jDimmFS3FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jDimmFS3FocusLost(evt);
            }
        });
        jDimmFS3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jDimmFS3KeyReleased(evt);
            }
        });
        jEffekte_2.add(jDimmFS3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 90, 30, 20));

        jDimmFS4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jDimmFS4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jDimmFS4.setText("20");
        jDimmFS4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jDimmFS4FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jDimmFS4FocusLost(evt);
            }
        });
        jDimmFS4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jDimmFS4KeyReleased(evt);
            }
        });
        jEffekte_2.add(jDimmFS4, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, 30, -1));

        jDimmFS1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jDimmFS1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jDimmFS1.setText("20");
        jDimmFS1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jDimmFS1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jDimmFS1FocusLost(evt);
            }
        });
        jDimmFS1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jDimmFS1KeyReleased(evt);
            }
        });
        jEffekte_2.add(jDimmFS1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, 30, 20));

        jDimmFS2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jDimmFS2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jDimmFS2.setText("20");
        jDimmFS2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jDimmFS2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jDimmFS2FocusLost(evt);
            }
        });
        jDimmFS2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jDimmFS2KeyReleased(evt);
            }
        });
        jEffekte_2.add(jDimmFS2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, 30, 20));

        jLabel67.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel67.setText("3");
        jEffekte_2.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 90, -1, -1));

        jLabel103.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel103.setText("AUX");
        jEffekte_2.add(jLabel103, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 30, -1, -1));

        jLabel113.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel113.setText("1");
        jEffekte_2.add(jLabel113, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 50, -1, -1));

        jLabel114.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel114.setText("4");
        jEffekte_2.add(jLabel114, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 110, -1, -1));

        jLabel115.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel115.setText("5");
        jEffekte_2.add(jLabel115, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 130, -1, -1));

        jLabel116.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel116.setText("6");
        jEffekte_2.add(jLabel116, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 150, -1, -1));

        jLabel117.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel117.setText("2");
        jEffekte_2.add(jLabel117, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 70, -1, -1));

        jLabel99.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel99.setText("3");
        jEffekte_2.add(jLabel99, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 210, -1, -1));

        jLabel118.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel118.setText("1");
        jEffekte_2.add(jLabel118, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 210, -1, -1));

        jLabel119.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel119.setText("4");
        jEffekte_2.add(jLabel119, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 210, -1, -1));

        jLabel120.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel120.setText("5");
        jEffekte_2.add(jLabel120, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 210, -1, -1));

        jLabel121.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel121.setText("6");
        jEffekte_2.add(jLabel121, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 210, -1, -1));

        jLabel122.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel122.setText("2");
        jEffekte_2.add(jLabel122, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 210, -1, -1));

        jIn2Aux1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn2Aux1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn2Aux1ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn2Aux1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, -1, -1));

        jIn2Aux2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn2Aux2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn2Aux2ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn2Aux2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 250, -1, -1));

        jIn2Aux3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn2Aux3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn2Aux3ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn2Aux3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 250, -1, -1));

        jIn2Aux4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn2Aux4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn2Aux4ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn2Aux4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 250, -1, -1));

        jIn2Aux5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn2Aux5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn2Aux5ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn2Aux5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 250, -1, -1));

        jIn2Aux6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn2Aux6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn2Aux6ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn2Aux6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 250, -1, -1));

        jIn3Aux6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn3Aux6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn3Aux6ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn3Aux6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 270, -1, -1));

        jIn3Aux1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn3Aux1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn3Aux1ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn3Aux1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, -1, -1));

        jIn3Aux4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn3Aux4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn3Aux4ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn3Aux4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 270, -1, -1));

        jIn3Aux2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn3Aux2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn3Aux2ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn3Aux2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 270, -1, -1));

        jIn3Aux3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn3Aux3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn3Aux3ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn3Aux3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 270, -1, -1));

        jIn3Aux5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn3Aux5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn3Aux5ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn3Aux5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 270, -1, -1));

        jLabel100.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel100.setText("Tür");
        jEffekte_2.add(jLabel100, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 210, -1, -1));

        jLabel123.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel123.setText("Schaffner");
        jEffekte_2.add(jLabel123, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 210, -1, -1));

        jLabel124.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel124.setText("Signal ");
        jEffekte_2.add(jLabel124, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 210, -1, -1));

        jLabel125.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel125.setText("Glocke");
        jEffekte_2.add(jLabel125, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 210, -1, -1));

        jTuerIn2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTuerIn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTuerIn2ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jTuerIn2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 250, -1, -1));

        jSchaffnerIn2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jSchaffnerIn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSchaffnerIn2ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jSchaffnerIn2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 250, -1, -1));

        jSignalIn2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jSignalIn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSignalIn2ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jSignalIn2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 250, -1, -1));

        jGlockeIn2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jGlockeIn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jGlockeIn2ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jGlockeIn2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 250, -1, -1));

        jSignalIn3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jSignalIn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSignalIn3ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jSignalIn3, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 270, -1, -1));

        jGlockeIn3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jGlockeIn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jGlockeIn3ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jGlockeIn3, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 270, -1, -1));

        jSchaffnerIn3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jSchaffnerIn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSchaffnerIn3ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jSchaffnerIn3, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 270, -1, -1));

        jTuerIn3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTuerIn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTuerIn3ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jTuerIn3, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 270, -1, -1));

        jLabel126.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel126.setText("Sound");
        jEffekte_2.add(jLabel126, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 210, -1, -1));

        jLabel127.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel127.setText("In1");
        jEffekte_2.add(jLabel127, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 320, -1, 20));

        jLabel128.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel128.setText("In2");
        jEffekte_2.add(jLabel128, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 340, -1, 20));

        jLabel129.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel129.setText("In3");
        jEffekte_2.add(jLabel129, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 270, -1, -1));

        jLabel130.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel130.setText("In1");
        jEffekte_2.add(jLabel130, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 230, -1, -1));

        jLabel131.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel131.setText("In2");
        jEffekte_2.add(jLabel131, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 250, -1, -1));

        jIn1Pos1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn1Pos1.setText("Pos 1");
        jIn1Pos1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn1Pos1ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn1Pos1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 320, -1, 20));

        jIn1Pos2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn1Pos2.setText("Pos 2");
        jIn1Pos2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn1Pos2ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn1Pos2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 320, -1, 20));

        jIn1Pos3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn1Pos3.setText("toggle");
        jIn1Pos3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn1Pos3ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn1Pos3, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 320, -1, 20));

        jIn2Pos1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn2Pos1.setText("Pos 1");
        jIn2Pos1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn2Pos1ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn2Pos1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 340, -1, 20));

        jIn2Pos2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn2Pos2.setText("Pos 2");
        jIn2Pos2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn2Pos2ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn2Pos2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 340, -1, 20));

        jIn2Pos3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn2Pos3.setText("toggle");
        jIn2Pos3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn2Pos3ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn2Pos3, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 340, -1, 20));

        jDecodereigenschaften.addTab("Effekte 2", jEffekte_2);

        jSound.setToolTipText("Verwendete CV: CV#148 bis CV#160");
        jSound.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jSound.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jSoundComponentShown(evt);
            }
        });
        jSound.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel38.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel38.setText("max (=0)");
        jSound.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 320, -1, -1));

        jF1_TP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF1_TP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF1_TPActionPerformed(evt);
            }
        });
        jSound.add(jF1_TP, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, -1, -1));

        jF2_TP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF2_TP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF2_TPActionPerformed(evt);
            }
        });
        jSound.add(jF2_TP, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, -1, -1));

        jF3_TP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF3_TP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF3_TPActionPerformed(evt);
            }
        });
        jSound.add(jF3_TP, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, -1, -1));

        jF4_TP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF4_TP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF4_TPActionPerformed(evt);
            }
        });
        jSound.add(jF4_TP, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 60, -1, -1));

        jF5_TP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF5_TP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF5_TPActionPerformed(evt);
            }
        });
        jSound.add(jF5_TP, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, -1, -1));

        jF1_SP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF1_SP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF1_SPActionPerformed(evt);
            }
        });
        jSound.add(jF1_SP, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, -1, -1));

        jF2_SP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF2_SP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF2_SPActionPerformed(evt);
            }
        });
        jSound.add(jF2_SP, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, -1, -1));

        jF3_SP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF3_SP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF3_SPActionPerformed(evt);
            }
        });
        jSound.add(jF3_SP, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, -1, -1));

        jF4_SP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF4_SP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF4_SPActionPerformed(evt);
            }
        });
        jSound.add(jF4_SP, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 80, -1, -1));

        jF9_SP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF9_SP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF9_SPActionPerformed(evt);
            }
        });
        jSound.add(jF9_SP, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 80, -1, -1));

        jF11_TP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF11_TP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF11_TPActionPerformed(evt);
            }
        });
        jSound.add(jF11_TP, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 60, -1, -1));

        jLabel134.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel134.setText("F1");
        jSound.add(jLabel134, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, -1, -1));

        jLabel135.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel135.setText("F2");
        jSound.add(jLabel135, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, -1, -1));

        jLabel136.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel136.setText("F3");
        jSound.add(jLabel136, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, -1, -1));

        jLabel137.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel137.setText("F4");
        jSound.add(jLabel137, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 50, -1, -1));

        jLabel138.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel138.setText("F5");
        jSound.add(jLabel138, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, -1, -1));

        jLabel139.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel139.setText("F6");
        jSound.add(jLabel139, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 50, -1, -1));

        jLabel140.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel140.setText("F7");
        jSound.add(jLabel140, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 50, -1, -1));

        jLabel141.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel141.setText("F8");
        jSound.add(jLabel141, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 50, -1, -1));

        jLabel142.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel142.setText("F9");
        jSound.add(jLabel142, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 50, -1, -1));

        jLabel143.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel143.setText("F11");
        jSound.add(jLabel143, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 50, -1, -1));

        jF1_SH.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF1_SH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF1_SHActionPerformed(evt);
            }
        });
        jSound.add(jF1_SH, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, -1, -1));

        jF2_SH.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF2_SH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF2_SHActionPerformed(evt);
            }
        });
        jSound.add(jF2_SH, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, -1, -1));

        jF3_SH.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF3_SH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF3_SHActionPerformed(evt);
            }
        });
        jSound.add(jF3_SH, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 100, -1, -1));

        jF4_SH.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF4_SH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF4_SHActionPerformed(evt);
            }
        });
        jSound.add(jF4_SH, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, -1, -1));

        jF9_SH.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF9_SH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF9_SHActionPerformed(evt);
            }
        });
        jSound.add(jF9_SH, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 100, -1, -1));

        jF11_SP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF11_SP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF11_SPActionPerformed(evt);
            }
        });
        jSound.add(jF11_SP, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 80, -1, -1));

        jF1_Gl.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF1_Gl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF1_GlActionPerformed(evt);
            }
        });
        jSound.add(jF1_Gl, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, -1, -1));

        jF2_Gl.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF2_Gl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF2_GlActionPerformed(evt);
            }
        });
        jSound.add(jF2_Gl, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 120, -1, -1));

        jF3_Gl.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF3_Gl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF3_GlActionPerformed(evt);
            }
        });
        jSound.add(jF3_Gl, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 120, -1, -1));

        jF4_Gl.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF4_Gl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF4_GlActionPerformed(evt);
            }
        });
        jSound.add(jF4_Gl, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 120, -1, -1));

        jF9_Gl.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF9_Gl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF9_GlActionPerformed(evt);
            }
        });
        jSound.add(jF9_Gl, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 120, -1, -1));

        jF11_SH.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF11_SH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF11_SHActionPerformed(evt);
            }
        });
        jSound.add(jF11_SH, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 100, -1, -1));

        jF11_Gl.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF11_Gl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF11_GlActionPerformed(evt);
            }
        });
        jSound.add(jF11_Gl, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 120, -1, -1));

        jLabel144.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel144.setText("Schaffnerpfiff");
        jSound.add(jLabel144, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        jLabel145.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel145.setText("Türpiepen");
        jSound.add(jLabel145, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        jF5_SP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF5_SP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF5_SPActionPerformed(evt);
            }
        });
        jSound.add(jF5_SP, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 80, -1, -1));

        jF5_SH.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF5_SH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF5_SHActionPerformed(evt);
            }
        });
        jSound.add(jF5_SH, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 100, -1, -1));

        jF5_Gl.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF5_Gl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF5_GlActionPerformed(evt);
            }
        });
        jSound.add(jF5_Gl, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 120, -1, -1));

        jF10_TP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF10_TP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF10_TPActionPerformed(evt);
            }
        });
        jSound.add(jF10_TP, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 60, -1, -1));

        jLabel146.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel146.setText("Glocke");
        jSound.add(jLabel146, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        jLabel147.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel147.setText("Signalhorn");
        jSound.add(jLabel147, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));

        jF6_TP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF6_TP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF6_TPActionPerformed(evt);
            }
        });
        jSound.add(jF6_TP, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, -1, -1));

        jF6_SP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF6_SP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF6_SPActionPerformed(evt);
            }
        });
        jSound.add(jF6_SP, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 80, -1, -1));

        jF6_SH.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF6_SH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF6_SHActionPerformed(evt);
            }
        });
        jSound.add(jF6_SH, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 100, -1, -1));

        jF6_Gl.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF6_Gl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF6_GlActionPerformed(evt);
            }
        });
        jSound.add(jF6_Gl, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 120, -1, -1));

        jF10_SP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF10_SP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF10_SPActionPerformed(evt);
            }
        });
        jSound.add(jF10_SP, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 80, -1, -1));

        jF12_TP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF12_TP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF12_TPActionPerformed(evt);
            }
        });
        jSound.add(jF12_TP, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 60, -1, -1));

        jF7_TP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF7_TP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF7_TPActionPerformed(evt);
            }
        });
        jSound.add(jF7_TP, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 60, -1, -1));

        jF7_SP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF7_SP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF7_SPActionPerformed(evt);
            }
        });
        jSound.add(jF7_SP, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 80, -1, -1));

        jF7_SH.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF7_SH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF7_SHActionPerformed(evt);
            }
        });
        jSound.add(jF7_SH, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 100, -1, -1));

        jF10_SH.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF10_SH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF10_SHActionPerformed(evt);
            }
        });
        jSound.add(jF10_SH, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 100, -1, -1));

        jF12_SP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF12_SP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF12_SPActionPerformed(evt);
            }
        });
        jSound.add(jF12_SP, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 80, -1, -1));

        jF7_Gl.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF7_Gl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF7_GlActionPerformed(evt);
            }
        });
        jSound.add(jF7_Gl, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 120, -1, -1));

        jF8_TP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF8_TP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF8_TPActionPerformed(evt);
            }
        });
        jSound.add(jF8_TP, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 60, -1, -1));

        jF8_SP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF8_SP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF8_SPActionPerformed(evt);
            }
        });
        jSound.add(jF8_SP, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 80, -1, -1));

        jF10_Gl.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF10_Gl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF10_GlActionPerformed(evt);
            }
        });
        jSound.add(jF10_Gl, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 120, -1, -1));

        jF12_SH.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF12_SH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF12_SHActionPerformed(evt);
            }
        });
        jSound.add(jF12_SH, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 100, -1, -1));

        jF8_SH.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF8_SH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF8_SHActionPerformed(evt);
            }
        });
        jSound.add(jF8_SH, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 100, -1, -1));

        jF8_Gl.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF8_Gl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF8_GlActionPerformed(evt);
            }
        });
        jSound.add(jF8_Gl, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 120, -1, -1));

        jF9_TP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF9_TP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF9_TPActionPerformed(evt);
            }
        });
        jSound.add(jF9_TP, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 60, -1, -1));

        jF12_Gl.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF12_Gl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF12_GlActionPerformed(evt);
            }
        });
        jSound.add(jF12_Gl, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 120, -1, -1));

        jLabel148.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel148.setText("F12");
        jSound.add(jLabel148, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 50, -1, -1));

        jLabel150.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel150.setText("F10");
        jSound.add(jLabel150, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 50, -1, -1));

        jF13_TP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF13_TP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF13_TPActionPerformed(evt);
            }
        });
        jSound.add(jF13_TP, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 180, -1, -1));

        jF14_TP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF14_TP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF14_TPActionPerformed(evt);
            }
        });
        jSound.add(jF14_TP, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 180, -1, -1));

        jF15_TP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF15_TP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF15_TPActionPerformed(evt);
            }
        });
        jSound.add(jF15_TP, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 180, -1, -1));

        jF16_TP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF16_TP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF16_TPActionPerformed(evt);
            }
        });
        jSound.add(jF16_TP, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 180, -1, -1));

        jF17_TP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF17_TP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF17_TPActionPerformed(evt);
            }
        });
        jSound.add(jF17_TP, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 180, -1, -1));

        jF13_SP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF13_SP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF13_SPActionPerformed(evt);
            }
        });
        jSound.add(jF13_SP, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 200, -1, -1));

        jF14_SP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF14_SP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF14_SPActionPerformed(evt);
            }
        });
        jSound.add(jF14_SP, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 200, -1, -1));

        jF15_SP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF15_SP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF15_SPActionPerformed(evt);
            }
        });
        jSound.add(jF15_SP, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 200, -1, -1));

        jF16_SP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF16_SP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF16_SPActionPerformed(evt);
            }
        });
        jSound.add(jF16_SP, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 200, -1, -1));

        jF21_SP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF21_SP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF21_SPActionPerformed(evt);
            }
        });
        jSound.add(jF21_SP, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 200, -1, -1));

        jF23_TP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF23_TP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF23_TPActionPerformed(evt);
            }
        });
        jSound.add(jF23_TP, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 180, -1, -1));

        jLabel149.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel149.setText("F13");
        jSound.add(jLabel149, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 170, -1, -1));

        jLabel151.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel151.setText("F14");
        jSound.add(jLabel151, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 170, -1, -1));

        jLabel152.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel152.setText("F15");
        jSound.add(jLabel152, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 170, -1, -1));

        jLabel153.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel153.setText("F16");
        jSound.add(jLabel153, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 170, -1, -1));

        jLabel154.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel154.setText("F17");
        jSound.add(jLabel154, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 170, -1, -1));

        jLabel155.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel155.setText("F18");
        jSound.add(jLabel155, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 170, -1, -1));

        jLabel156.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel156.setText("F19");
        jSound.add(jLabel156, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 170, -1, -1));

        jLabel157.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel157.setText("F20");
        jSound.add(jLabel157, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 170, -1, -1));

        jLabel158.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel158.setText("F21");
        jSound.add(jLabel158, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 170, -1, -1));

        jLabel159.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel159.setText("F23");
        jSound.add(jLabel159, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 170, -1, -1));

        jF13_SH.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF13_SH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF13_SHActionPerformed(evt);
            }
        });
        jSound.add(jF13_SH, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 220, -1, -1));

        jF14_SH.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF14_SH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF14_SHActionPerformed(evt);
            }
        });
        jSound.add(jF14_SH, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 220, -1, -1));

        jF15_SH.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF15_SH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF15_SHActionPerformed(evt);
            }
        });
        jSound.add(jF15_SH, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 220, -1, -1));

        jF16_SH.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF16_SH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF16_SHActionPerformed(evt);
            }
        });
        jSound.add(jF16_SH, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 220, -1, -1));

        jF21_SH.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF21_SH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF21_SHActionPerformed(evt);
            }
        });
        jSound.add(jF21_SH, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 220, -1, -1));

        jF23_SP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF23_SP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF23_SPActionPerformed(evt);
            }
        });
        jSound.add(jF23_SP, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 200, -1, -1));

        jF13_Gl.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF13_Gl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF13_GlActionPerformed(evt);
            }
        });
        jSound.add(jF13_Gl, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 240, -1, -1));

        jF14_Gl.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF14_Gl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF14_GlActionPerformed(evt);
            }
        });
        jSound.add(jF14_Gl, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 240, -1, -1));

        jF15_Gl.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF15_Gl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF15_GlActionPerformed(evt);
            }
        });
        jSound.add(jF15_Gl, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 240, -1, -1));

        jF16_Gl.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF16_Gl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF16_GlActionPerformed(evt);
            }
        });
        jSound.add(jF16_Gl, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 240, -1, -1));

        jF21_Gl.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF21_Gl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF21_GlActionPerformed(evt);
            }
        });
        jSound.add(jF21_Gl, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 240, -1, -1));

        jF23_SH.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF23_SH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF23_SHActionPerformed(evt);
            }
        });
        jSound.add(jF23_SH, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 220, -1, -1));

        jF23_Gl.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF23_Gl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF23_GlActionPerformed(evt);
            }
        });
        jSound.add(jF23_Gl, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 240, -1, -1));

        jLabel160.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel160.setText("Schaffnerpfiff");
        jSound.add(jLabel160, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, -1));

        jLabel161.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel161.setText("Türpiepen");
        jSound.add(jLabel161, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, -1, -1));

        jF17_SP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF17_SP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF17_SPActionPerformed(evt);
            }
        });
        jSound.add(jF17_SP, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 200, -1, -1));

        jF17_SH.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF17_SH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF17_SHActionPerformed(evt);
            }
        });
        jSound.add(jF17_SH, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 220, -1, -1));

        jF17_Gl.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF17_Gl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF17_GlActionPerformed(evt);
            }
        });
        jSound.add(jF17_Gl, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 240, -1, -1));

        jF22_TP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF22_TP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF22_TPActionPerformed(evt);
            }
        });
        jSound.add(jF22_TP, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 180, -1, -1));

        jLabel162.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel162.setText("Glocke");
        jSound.add(jLabel162, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, -1, -1));

        jLabel163.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel163.setText("Signalhorn");
        jSound.add(jLabel163, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, -1, -1));

        jF18_TP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF18_TP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF18_TPActionPerformed(evt);
            }
        });
        jSound.add(jF18_TP, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 180, -1, -1));

        jF18_SP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF18_SP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF18_SPActionPerformed(evt);
            }
        });
        jSound.add(jF18_SP, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 200, -1, -1));

        jF18_SH.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF18_SH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF18_SHActionPerformed(evt);
            }
        });
        jSound.add(jF18_SH, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 220, -1, -1));

        jF18_Gl.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF18_Gl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF18_GlActionPerformed(evt);
            }
        });
        jSound.add(jF18_Gl, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 240, -1, -1));

        jF22_SP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF22_SP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF22_SPActionPerformed(evt);
            }
        });
        jSound.add(jF22_SP, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 200, -1, -1));

        jF24_TP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF24_TP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF24_TPActionPerformed(evt);
            }
        });
        jSound.add(jF24_TP, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 180, -1, -1));

        jF19_TP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF19_TP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF19_TPActionPerformed(evt);
            }
        });
        jSound.add(jF19_TP, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 180, -1, -1));

        jF19_SP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF19_SP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF19_SPActionPerformed(evt);
            }
        });
        jSound.add(jF19_SP, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 200, -1, -1));

        jF19_SH.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF19_SH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF19_SHActionPerformed(evt);
            }
        });
        jSound.add(jF19_SH, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 220, -1, -1));

        jF22_SH.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF22_SH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF22_SHActionPerformed(evt);
            }
        });
        jSound.add(jF22_SH, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 220, -1, -1));

        jF24_SP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF24_SP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF24_SPActionPerformed(evt);
            }
        });
        jSound.add(jF24_SP, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 200, -1, -1));

        jF19_Gl.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF19_Gl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF19_GlActionPerformed(evt);
            }
        });
        jSound.add(jF19_Gl, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, -1, -1));

        jF20_TP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF20_TP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF20_TPActionPerformed(evt);
            }
        });
        jSound.add(jF20_TP, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 180, -1, -1));

        jF20_SP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF20_SP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF20_SPActionPerformed(evt);
            }
        });
        jSound.add(jF20_SP, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 200, -1, -1));

        jF22_Gl.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF22_Gl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF22_GlActionPerformed(evt);
            }
        });
        jSound.add(jF22_Gl, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 240, -1, -1));

        jF24_SH.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF24_SH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF24_SHActionPerformed(evt);
            }
        });
        jSound.add(jF24_SH, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 220, -1, -1));

        jF20_SH.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF20_SH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF20_SHActionPerformed(evt);
            }
        });
        jSound.add(jF20_SH, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 220, -1, -1));

        jF20_Gl.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF20_Gl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF20_GlActionPerformed(evt);
            }
        });
        jSound.add(jF20_Gl, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 240, -1, -1));

        jF21_TP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF21_TP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF21_TPActionPerformed(evt);
            }
        });
        jSound.add(jF21_TP, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 180, -1, -1));

        jF24_Gl.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF24_Gl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF24_GlActionPerformed(evt);
            }
        });
        jSound.add(jF24_Gl, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 240, -1, -1));

        jLabel164.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel164.setText("F24");
        jSound.add(jLabel164, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 170, -1, -1));

        jLabel165.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel165.setText("F22");
        jSound.add(jLabel165, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 170, -1, -1));

        jLabel132.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel132.setText("Funktions-Zuordnung Sound");
        jSound.add(jLabel132, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, -1, -1));

        jLautSlider.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLautSlider.setMaximum(255);
        jLautSlider.setValue(244);
        jLautSlider.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLautSliderMouseReleased(evt);
            }
        });
        jSound.add(jLautSlider, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 300, 200, 20));

        jLaut.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLaut.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jLaut.setText("0");
        jLaut.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jLautFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jLautFocusLost(evt);
            }
        });
        jLaut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jLautKeyReleased(evt);
            }
        });
        jSound.add(jLaut, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 300, 40, 20));

        jLabel133.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel133.setText("Lautstärke:");
        jSound.add(jLabel133, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, -1, -1));

        jLabel166.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel166.setText("min (=255)");
        jSound.add(jLabel166, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 320, -1, -1));

        jDecodereigenschaften.addTab("Sound", jSound);

        jServo.setToolTipText("Verwendete CV: CV#13");
        jServo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jServo.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jServoComponentShown(evt);
            }
        });
        jServo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel24.setText("Geschwindigkeit:");
        jServo.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 100, -1));

        jIn3IsServo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn3IsServo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn3IsServoActionPerformed(evt);
            }
        });
        jServo.add(jIn3IsServo, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 30, -1, -1));

        jServoF9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jServoF9.setText("F9");
        jServoF9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jServoF9ActionPerformed(evt);
            }
        });
        jServo.add(jServoF9, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 70, -1, -1));

        jServoF10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jServoF10.setText("F10");
        jServoF10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jServoF10ActionPerformed(evt);
            }
        });
        jServo.add(jServoF10, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 70, -1, -1));

        jServoF12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jServoF12.setText("F12");
        jServoF12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jServoF12ActionPerformed(evt);
            }
        });
        jServo.add(jServoF12, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 70, -1, -1));

        jServoF5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jServoF5.setText("F5");
        jServoF5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jServoF5ActionPerformed(evt);
            }
        });
        jServo.add(jServoF5, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 50, -1, -1));

        jServoF6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jServoF6.setText("F6");
        jServoF6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jServoF6ActionPerformed(evt);
            }
        });
        jServo.add(jServoF6, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 50, -1, -1));

        jServoF7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jServoF7.setText("F7");
        jServoF7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jServoF7ActionPerformed(evt);
            }
        });
        jServo.add(jServoF7, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 50, -1, -1));

        jServoF8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jServoF8.setText("F8");
        jServoF8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jServoF8ActionPerformed(evt);
            }
        });
        jServo.add(jServoF8, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 50, -1, -1));

        jLabel167.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel167.setText("invertieren von AUX:");
        jServo.add(jLabel167, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, -1, 20));

        jGeschwindigkeit.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jGeschwindigkeit.setText("5");
        jGeschwindigkeit.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jGeschwindigkeitFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jGeschwindigkeitFocusLost(evt);
            }
        });
        jGeschwindigkeit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jGeschwindigkeitKeyReleased(evt);
            }
        });
        jServo.add(jGeschwindigkeit, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 130, 50, -1));

        jServoF11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jServoF11.setText("F11");
        jServoF11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jServoF11ActionPerformed(evt);
            }
        });
        jServo.add(jServoF11, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 70, -1, -1));

        jLabel168.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel168.setText("Servoposition schaltbar mit:");
        jServo.add(jLabel168, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        jLabel169.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel169.setText("Einstellungen für Fahrstufe 0:");
        jServo.add(jLabel169, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 290, -1));

        jLabel170.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel170.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel170.setText("rechter Anschlag:");
        jServo.add(jLabel170, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 100, -1, -1));

        jlinkerAnschlag.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jlinkerAnschlag.setText("5");
        jlinkerAnschlag.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jlinkerAnschlagFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jlinkerAnschlagFocusLost(evt);
            }
        });
        jlinkerAnschlag.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jlinkerAnschlagKeyReleased(evt);
            }
        });
        jServo.add(jlinkerAnschlag, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, 50, -1));

        jrechterAnschlag.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jrechterAnschlag.setText("5");
        jrechterAnschlag.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jrechterAnschlagFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jrechterAnschlagFocusLost(evt);
            }
        });
        jrechterAnschlag.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jrechterAnschlagKeyReleased(evt);
            }
        });
        jServo.add(jrechterAnschlag, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 100, 50, -1));

        jLabel171.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel171.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel171.setText("linker Anschlag:");
        jServo.add(jLabel171, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 100, -1));

        jLabel172.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel172.setText("Einstellungen Servo:");
        jServo.add(jLabel172, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 190, -1));
        jServo.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 310, 200, 10));

        jLabel173.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel173.setText("Eingang In3 = Servo-Ausgang:");
        jServo.add(jLabel173, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        jFS0_F5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS0_F5.setText("F5");
        jFS0_F5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFS0_F5ActionPerformed(evt);
            }
        });
        jServo.add(jFS0_F5, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 220, -1, -1));

        jFS0_F6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS0_F6.setText("F6");
        jFS0_F6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFS0_F6ActionPerformed(evt);
            }
        });
        jServo.add(jFS0_F6, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 220, -1, -1));

        jFS0_F7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS0_F7.setText("F7");
        jFS0_F7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFS0_F7ActionPerformed(evt);
            }
        });
        jServo.add(jFS0_F7, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 220, -1, -1));

        jFS0_F8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS0_F8.setText("F8");
        jFS0_F8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFS0_F8ActionPerformed(evt);
            }
        });
        jServo.add(jFS0_F8, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 220, -1, -1));

        jFS0_F1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS0_F1.setText("F1");
        jFS0_F1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFS0_F1ActionPerformed(evt);
            }
        });
        jServo.add(jFS0_F1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 200, -1, -1));

        jFS0_F2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS0_F2.setText("F2");
        jFS0_F2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFS0_F2ActionPerformed(evt);
            }
        });
        jServo.add(jFS0_F2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 200, -1, -1));

        jFS0_F3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS0_F3.setText("F3");
        jFS0_F3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFS0_F3ActionPerformed(evt);
            }
        });
        jServo.add(jFS0_F3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 200, -1, -1));

        jFS0_F4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS0_F4.setText("F4");
        jFS0_F4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFS0_F4ActionPerformed(evt);
            }
        });
        jServo.add(jFS0_F4, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 200, -1, -1));

        jLabel174.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel174.setText("Ausschalten von F0 mit:");
        jServo.add(jLabel174, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, -1, 20));

        jFS0_AUX1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS0_AUX1.setText("1");
        jFS0_AUX1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFS0_AUX1ActionPerformed(evt);
            }
        });
        jServo.add(jFS0_AUX1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 250, -1, -1));

        jFS0_AUX2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS0_AUX2.setText("2");
        jFS0_AUX2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFS0_AUX2ActionPerformed(evt);
            }
        });
        jServo.add(jFS0_AUX2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 250, -1, -1));

        jFS0_AUX3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS0_AUX3.setText("3");
        jFS0_AUX3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFS0_AUX3ActionPerformed(evt);
            }
        });
        jServo.add(jFS0_AUX3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 250, -1, -1));

        jFS0_AUX4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS0_AUX4.setText("4");
        jFS0_AUX4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFS0_AUX4ActionPerformed(evt);
            }
        });
        jServo.add(jFS0_AUX4, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 250, -1, -1));

        jFS0_AUX5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS0_AUX5.setText("5");
        jFS0_AUX5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFS0_AUX5ActionPerformed(evt);
            }
        });
        jServo.add(jFS0_AUX5, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 250, -1, -1));

        jFS0_AUX6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS0_AUX6.setText("6");
        jFS0_AUX6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFS0_AUX6ActionPerformed(evt);
            }
        });
        jServo.add(jFS0_AUX6, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 250, -1, -1));

        jLabel175.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel175.setText("dann einschalten von AUX:");
        jServo.add(jLabel175, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, -1, 20));

        jFS0_AUXinv1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS0_AUXinv1.setText("1");
        jFS0_AUXinv1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFS0_AUXinv1ActionPerformed(evt);
            }
        });
        jServo.add(jFS0_AUXinv1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 320, -1, -1));

        jFS0_AUXinv2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS0_AUXinv2.setText("2");
        jFS0_AUXinv2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFS0_AUXinv2ActionPerformed(evt);
            }
        });
        jServo.add(jFS0_AUXinv2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 320, -1, -1));

        jFS0_AUXinv3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS0_AUXinv3.setText("3");
        jFS0_AUXinv3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFS0_AUXinv3ActionPerformed(evt);
            }
        });
        jServo.add(jFS0_AUXinv3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 320, -1, -1));

        jFS0_AUXinv4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS0_AUXinv4.setText("4");
        jFS0_AUXinv4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFS0_AUXinv4ActionPerformed(evt);
            }
        });
        jServo.add(jFS0_AUXinv4, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 320, -1, -1));

        jFS0_AUXinv5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS0_AUXinv5.setText("5");
        jFS0_AUXinv5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFS0_AUXinv5ActionPerformed(evt);
            }
        });
        jServo.add(jFS0_AUXinv5, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 320, -1, -1));

        jFS0_AUXinv6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS0_AUXinv6.setText("6");
        jFS0_AUXinv6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFS0_AUXinv6ActionPerformed(evt);
            }
        });
        jServo.add(jFS0_AUXinv6, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 320, -1, -1));
        jServo.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 420, 10));

        jFS0_AUX7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS0_AUX7.setText("1");
        jFS0_AUX7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFS0_AUX7ActionPerformed(evt);
            }
        });
        jServo.add(jFS0_AUX7, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 270, -1, -1));

        jFS0_AUX8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS0_AUX8.setText("2");
        jFS0_AUX8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFS0_AUX8ActionPerformed(evt);
            }
        });
        jServo.add(jFS0_AUX8, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 270, -1, -1));

        jFS0_AUX9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS0_AUX9.setText("3");
        jFS0_AUX9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFS0_AUX9ActionPerformed(evt);
            }
        });
        jServo.add(jFS0_AUX9, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 270, -1, -1));

        jFS0_AUX10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS0_AUX10.setText("4");
        jFS0_AUX10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFS0_AUX10ActionPerformed(evt);
            }
        });
        jServo.add(jFS0_AUX10, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 270, -1, -1));

        jFS0_AUX11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS0_AUX11.setText("5");
        jFS0_AUX11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFS0_AUX11ActionPerformed(evt);
            }
        });
        jServo.add(jFS0_AUX11, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 270, -1, -1));

        jFS0_AUX12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS0_AUX12.setText("6");
        jFS0_AUX12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFS0_AUX12ActionPerformed(evt);
            }
        });
        jServo.add(jFS0_AUX12, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 270, -1, -1));

        jLabel176.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel176.setText("bzw ausschalten von AUX:");
        jServo.add(jLabel176, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, -1, 20));

        jDecodereigenschaften.addTab("Servo + FS0", jServo);

        jAnalog.setToolTipText("Verwendete CV: CV#13");
        jAnalog.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAnalog.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jAnalogComponentShown(evt);
            }
        });
        jAnalog.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Time Out für Analogerkennung");
        jAnalog.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, -1));

        jF1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF1.setText("F1");
        jF1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF1ActionPerformed(evt);
            }
        });
        jAnalog.add(jF1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, -1, -1));

        jF2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF2.setText("F2");
        jF2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF2ActionPerformed(evt);
            }
        });
        jAnalog.add(jF2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, -1, -1));

        jF3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF3.setText("F3");
        jF3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF3ActionPerformed(evt);
            }
        });
        jAnalog.add(jF3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, -1, -1));

        jF4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF4.setText("F4");
        jF4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF4ActionPerformed(evt);
            }
        });
        jAnalog.add(jF4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, -1, -1));

        jF5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF5.setText("F5");
        jF5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF5ActionPerformed(evt);
            }
        });
        jAnalog.add(jF5, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, -1, -1));

        jF6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF6.setText("F6");
        jF6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF6ActionPerformed(evt);
            }
        });
        jAnalog.add(jF6, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, -1, -1));

        jF7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF7.setText("F7");
        jF7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF7ActionPerformed(evt);
            }
        });
        jAnalog.add(jF7, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 120, -1, -1));

        jF8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF8.setText("F8");
        jF8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF8ActionPerformed(evt);
            }
        });
        jAnalog.add(jF8, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 150, -1, -1));

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel17.setText("Funktionen, die im Analogmodus aktiv sind");
        jAnalog.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        jPacketTimeOut.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPacketTimeOut.setText("5");
        jPacketTimeOut.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPacketTimeOutFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jPacketTimeOutFocusLost(evt);
            }
        });
        jPacketTimeOut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jPacketTimeOutKeyReleased(evt);
            }
        });
        jAnalog.add(jPacketTimeOut, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 200, 50, -1));

        jDecodereigenschaften.addTab("Analog", jAnalog);

        jPanel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jComment.setColumns(20);
        jComment.setRows(5);
        jScrollPane1.setViewportView(jComment);

        jLabel36.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel36.setText("Kommentar eingeben:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(155, 155, 155)
                        .addComponent(jLabel36)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel36)
                .addGap(11, 11, 11)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
                .addContainerGap())
        );

        jDecodereigenschaften.addTab("Kommentar", jPanel3);

        jCV_Anzeige.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCV_Anzeige.setBorder(javax.swing.BorderFactory.createTitledBorder("CV Direkteingabe"));
        jCV_Anzeige.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCV_AnzeigeActionPerformed(evt);
            }
        });

        jOpen.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jOpen.setText("Öffnen");
        jOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jOpenActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton1.setText("Test");
        jButton1.setToolTipText("<html>Das folgende Fenster darf nicht zusammen mit <br>\ndem Fenster zum Programmieren der CVs geöffnet sein!");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel27.setText("Consist");

        jDecoderAdresse1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jDecoderAdresse1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jDecoderAdresse1.setText("0");
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

        jDecoderAdresse.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jDecoderAdresse.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jDecoderAdresse.setText("3");
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

        jCV_Inhalt.setEditable(false);
        jCV_Inhalt.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCV_Inhalt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jCV_Inhalt.setText("62");
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

        jDecoderlesenSchreiben.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jDecoderlesenSchreiben.setText("Decoder lesen/schreiben");
        jDecoderlesenSchreiben.setToolTipText("<html>ACHTUNG!<br>\nZum Auslesen muss RailCom ausgeschaltet sein!<br>\nCV29 Bit3 = 0!");
        jDecoderlesenSchreiben.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDecoderlesenSchreibenActionPerformed(evt);
            }
        });

        jAbbrechen.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAbbrechen.setText("Schließen");
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
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jCV_Anzeige, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jCV_Inhalt, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jDecoderAdresse, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jKurzeAdr)
                                    .addComponent(jlangeAdr))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jDirekteingabe)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel27)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jDecoderAdresse1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel28))))))
                    .addComponent(jDecodereigenschaften, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSave, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jOpen, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDecoderlesenSchreiben, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                    .addComponent(jAbbrechen, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(jOpen, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSave, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(81, 81, 81)
                        .addComponent(jDecoderlesenSchreiben, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                        .addComponent(jDecodereigenschaften))))
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

    private void jDecoderAdresseFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDecoderAdresseFocusLost
        int j = KTUI.checkTextField( this, jDecoderAdresse, 1, 10239, 3, true );
        String s = jDecoderAdresse.getText();
        if (jKurzeAdr.isSelected()) {
            if( j < 1 ||  j > 255 )
            {
                KTUI.mbValueNaN( this, 1, 255, true);
                j = 1;
                jDecoderAdresse.setText("3");
            }
            else if (j > 127)
            {
                KTUI.mbAdr128MMonly( this );
            }
            CV[1][1] = j;
            CV[1][29] &= ~32;
            jCV_Anzeige.setSelectedItem( "CV#"+1 );
        } else { // lange Adresse
            if (j < 128 || j > 10239) {
                KTUI.mbValueNaN( this, 128, 10239, true);
                j = 128;
                jDecoderAdresse.setText("128");
            }
            CV[1][29] |= 32;
            CV[1][17] = j/256 + 192;
            CV[1][18] = j%256;
            jCV_Anzeige.setSelectedItem( "CV#"+17 );
        }
    }//GEN-LAST:event_jDecoderAdresseFocusLost

    private void jDecoderAdresseKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDecoderAdresseKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jDecoderAdresseKeyReleased

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

    private void jMM_Addr_2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jMM_Addr_2FocusLost
        CV[1][47] = KTUI.checkTextField( this, jMM_Addr_2, 1, 255, 4, true );
        jCV_Anzeige.setSelectedItem( "CV#"+47 );
    }//GEN-LAST:event_jMM_Addr_2FocusLost

    private void jMM_Addr_2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jMM_Addr_2KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jMM_Addr_2KeyReleased

    private void jBroadCasstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBroadCasstActionPerformed
        if(jBroadCasst.isSelected()) {
            CV[1][28] |= 1;
        } else {
            CV[1][28] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+28 );
    }//GEN-LAST:event_jBroadCasstActionPerformed

    private void jChannel2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jChannel2ActionPerformed
        if(jChannel2.isSelected()) {
            CV[1][28] |= 2;
        } else {
            CV[1][28] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+28 );
    }//GEN-LAST:event_jChannel2ActionPerformed

    private void jCV29ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jCV29ComponentShown
        jCV_Anzeige.setSelectedItem( "CV#"+29 );

        jBroadCasst.setSelected((CV[1][28] & 1) == 1);
        jChannel2.setSelected(  (CV[1][28] & 2) == 2);
        
        jRichtung.setSelected((CV[1][29] & 1) == 1);
        jFS.setSelected(      (CV[1][29] & 2) == 2);
        jAnalog1.setSelected( (CV[1][29] & 4) == 4);
        jRailCom.setSelected( (CV[1][29] & 8) == 8);
        
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
        jMM_Addr_2.setText("" + CV[1][47]);
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

    private void jF14_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF14_6ActionPerformed
        if(jF14_6.isSelected()) {
            CV[1][181] |= 0x20;
        } else {
            CV[1][181] &= ~0x20;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+181 );
    }//GEN-LAST:event_jF14_6ActionPerformed

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
            CV[1][37] |= 0x01;
        } else {
            CV[1][37] &= ~0x01;
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

    private void jF16_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF16_3ActionPerformed
        if(jF16_3.isSelected()) {
            CV[1][183] |= 4;
        } else {
            CV[1][183] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+183 );
    }//GEN-LAST:event_jF16_3ActionPerformed

    private void jF16_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF16_2ActionPerformed
        if(jF16_2.isSelected()) {
            CV[1][183] |= 2;
        } else {
            CV[1][183] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+183 );
    }//GEN-LAST:event_jF16_2ActionPerformed

    private void jF16_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF16_1ActionPerformed
        if(jF16_1.isSelected()) {
            CV[1][183] |= 1;
        } else {
            CV[1][183] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+183 );
    }//GEN-LAST:event_jF16_1ActionPerformed

    private void jF16_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF16_4ActionPerformed
        if(jF16_4.isSelected()) {
            CV[1][183] |= 8;
        } else {
            CV[1][183] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+183 );
    }//GEN-LAST:event_jF16_4ActionPerformed

    private void jF16_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF16_5ActionPerformed
        if(jF16_5.isSelected()) {
            CV[1][183] |= 0x10;
        } else {
            CV[1][183] &= ~0x10;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+183 );
    }//GEN-LAST:event_jF16_5ActionPerformed

    private void jF10_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF10_6ActionPerformed
        if(jF10_6.isSelected()) {
            CV[1][44] |= 0x20;
        } else {
            CV[1][44] &= ~0x20;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+44 );
    }//GEN-LAST:event_jF10_6ActionPerformed

    private void jF16_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF16_6ActionPerformed
        if(jF16_6.isSelected()) {
            CV[1][183] |= 0x20;
        } else {
            CV[1][183] &= ~0x20;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+183 );
    }//GEN-LAST:event_jF16_6ActionPerformed

    private void jFL_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFL_2ActionPerformed
        if(jFL_2.isSelected()) {
            CV[1][33] |= 2; //CV#57 mit umprogrammieren...
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

    private void jF9_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF9_2ActionPerformed
        if(jF9_2.isSelected()) {
            CV[1][43] |= 2;
        } else {
            CV[1][43] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+43 );
    }//GEN-LAST:event_jF9_2ActionPerformed

    private void jF11_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF11_1ActionPerformed
        if(jF11_1.isSelected()) {
            CV[1][45] |= 1;
        } else {
            CV[1][45] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+45 );
    }//GEN-LAST:event_jF11_1ActionPerformed

    private void jF17_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF17_1ActionPerformed
        if(jF17_1.isSelected()) {
            CV[1][184] |= 1;
        } else {
            CV[1][184] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+184 );
    }//GEN-LAST:event_jF17_1ActionPerformed

    private void jFL_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFL_3ActionPerformed
        if(jFL_3.isSelected()) {
            CV[1][33] |= 4; //CV#58 mit umprogrammieren...
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

    private void jF9_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF9_3ActionPerformed
        if(jF9_3.isSelected()) {
            CV[1][43] |= 4;
        } else {
            CV[1][43] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+43 );
    }//GEN-LAST:event_jF9_3ActionPerformed

    private void jF17_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF17_4ActionPerformed
        if(jF17_4.isSelected()) {
            CV[1][184] |= 0x08;
        } else {
            CV[1][184] &= ~0x08;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+184 );
    }//GEN-LAST:event_jF17_4ActionPerformed

    private void jF17_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF17_3ActionPerformed
        if(jF17_3.isSelected()) {
            CV[1][184] |= 0x04;
        } else {
            CV[1][184] &= ~0x04;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+184 );
    }//GEN-LAST:event_jF17_3ActionPerformed

    private void jF17_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF17_2ActionPerformed
        if(jF17_2.isSelected()) {
            CV[1][184] |= 2;
        } else {
            CV[1][184] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+184 );
    }//GEN-LAST:event_jF17_2ActionPerformed

    private void jF17_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF17_5ActionPerformed
        if(jF17_5.isSelected()) {
            CV[1][184] |= 0x10;
        } else {
            CV[1][184] &= ~0x10;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+184 );
    }//GEN-LAST:event_jF17_5ActionPerformed

    private void jF17_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF17_6ActionPerformed
        if(jF17_6.isSelected()) {
            CV[1][184] |= 0x20;
        } else {
            CV[1][184] &= ~0x20;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+184 );
    }//GEN-LAST:event_jF17_6ActionPerformed

    private void jF11_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF11_2ActionPerformed
        if(jF11_2.isSelected()) {
            CV[1][45] |= 2;
        } else {
            CV[1][45] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+45 );
    }//GEN-LAST:event_jF11_2ActionPerformed

    private void jF18_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF18_1ActionPerformed
        if(jF18_1.isSelected()) {
            CV[1][185] |= 1;
        } else {
            CV[1][185] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+185 );
    }//GEN-LAST:event_jF18_1ActionPerformed

    private void jF19_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF19_6ActionPerformed
        if(jF19_6.isSelected()) {
            CV[1][186] |= 0x20;
        } else {
            CV[1][186] &= ~0x20;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+186 );
    }//GEN-LAST:event_jF19_6ActionPerformed

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

    private void jF9_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF9_4ActionPerformed
        if(jF9_4.isSelected()) {
            CV[1][43] |= 8;
        } else {
            CV[1][43] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+43 );
    }//GEN-LAST:event_jF9_4ActionPerformed

    private void jF11_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF11_3ActionPerformed
        if(jF11_3.isSelected()) {
            CV[1][45] |= 4;
        } else {
            CV[1][45] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+45 );
    }//GEN-LAST:event_jF11_3ActionPerformed

    private void jF18_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF18_2ActionPerformed
        if(jF18_2.isSelected()) {
            CV[1][185] |= 2;
        } else {
            CV[1][185] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+185 );
    }//GEN-LAST:event_jF18_2ActionPerformed

    private void jF18_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF18_3ActionPerformed
        if(jF18_3.isSelected()) {
            CV[1][185] |= 4;
        } else {
            CV[1][185] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+185 );
    }//GEN-LAST:event_jF18_3ActionPerformed

    private void jF18_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF18_4ActionPerformed
        if(jF18_4.isSelected()) {
            CV[1][185] |= 8;
        } else {
            CV[1][185] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+185 );
    }//GEN-LAST:event_jF18_4ActionPerformed

    private void jF18_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF18_5ActionPerformed
        if(jF18_5.isSelected()) {
            CV[1][185] |= 0x10;
        } else {
            CV[1][185] &= ~0x10;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+185 );
    }//GEN-LAST:event_jF18_5ActionPerformed

    private void jF18_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF18_6ActionPerformed
        if(jF18_6.isSelected()) {
            CV[1][185] |= 0x20;
        } else {
            CV[1][185] &= ~0x20;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+185 );
    }//GEN-LAST:event_jF18_6ActionPerformed

    private void jF20_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF20_6ActionPerformed
        if(jF20_6.isSelected()) {
            CV[1][187] |= 0x20;
        } else {
            CV[1][187] &= ~0x20;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+187 );
    }//GEN-LAST:event_jF20_6ActionPerformed

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

    private void jF9_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF9_5ActionPerformed
        if(jF9_5.isSelected()) {
            CV[1][43] |= 0x10;
        } else {
            CV[1][43] &= ~0x10;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+43 );
    }//GEN-LAST:event_jF9_5ActionPerformed

    private void jF11_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF11_4ActionPerformed
        if(jF11_4.isSelected()) {
            CV[1][45] |= 8;
        } else {
            CV[1][45] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+45 );
    }//GEN-LAST:event_jF11_4ActionPerformed

    private void jF19_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF19_1ActionPerformed
        if(jF19_1.isSelected()) {
            CV[1][186] |= 1;
        } else {
            CV[1][186] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+186 );
    }//GEN-LAST:event_jF19_1ActionPerformed

    private void jF19_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF19_2ActionPerformed
        if(jF19_2.isSelected()) {
            CV[1][186] |= 2;
        } else {
            CV[1][186] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+186 );
    }//GEN-LAST:event_jF19_2ActionPerformed

    private void jF19_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF19_3ActionPerformed
        if(jF19_3.isSelected()) {
            CV[1][186] |= 4;
        } else {
            CV[1][186] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+186 );
    }//GEN-LAST:event_jF19_3ActionPerformed

    private void jF19_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF19_4ActionPerformed
        if(jF19_4.isSelected()) {
            CV[1][186] |= 8;
        } else {
            CV[1][186] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+186 );
    }//GEN-LAST:event_jF19_4ActionPerformed

    private void jF19_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF19_5ActionPerformed
        if(jF19_5.isSelected()) {
            CV[1][186] |= 0x10;
        } else {
            CV[1][186] &= ~0x10;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+186 );
    }//GEN-LAST:event_jF19_5ActionPerformed

    private void jF21_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF21_6ActionPerformed
        if(jF21_6.isSelected()) {
            CV[1][188] |= 0x20;
        } else {
            CV[1][188] &= ~0x20;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+188 );
    }//GEN-LAST:event_jF21_6ActionPerformed

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

    private void jF9_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF9_6ActionPerformed
        if(jF9_6.isSelected()) {
            CV[1][43] |= 0x20;
        } else {
            CV[1][43] &= ~0x20;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+43 );
    }//GEN-LAST:event_jF9_6ActionPerformed

    private void jF11_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF11_5ActionPerformed
        if(jF11_5.isSelected()) {
            CV[1][45] |= 0x10;
        } else {
            CV[1][45] &= ~0x10;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+45 );
    }//GEN-LAST:event_jF11_5ActionPerformed

    private void jF20_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF20_1ActionPerformed
        if(jF20_1.isSelected()) {
            CV[1][187] |= 1;
        } else {
            CV[1][187] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+187 );
    }//GEN-LAST:event_jF20_1ActionPerformed

    private void jF20_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF20_2ActionPerformed
        if(jF20_2.isSelected()) {
            CV[1][187] |= 2;
        } else {
            CV[1][187] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+187 );
    }//GEN-LAST:event_jF20_2ActionPerformed

    private void jF20_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF20_3ActionPerformed
        if(jF20_3.isSelected()) {
            CV[1][187] |= 4;
        } else {
            CV[1][187] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+187 );
    }//GEN-LAST:event_jF20_3ActionPerformed

    private void jF20_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF20_4ActionPerformed
        if(jF20_4.isSelected()) {
            CV[1][187] |= 8;
        } else {
            CV[1][187] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+187 );
    }//GEN-LAST:event_jF20_4ActionPerformed

    private void jF20_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF20_5ActionPerformed
        if(jF20_5.isSelected()) {
            CV[1][187] |= 0x10;
        } else {
            CV[1][187] &= ~0x10;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+187 );
    }//GEN-LAST:event_jF20_5ActionPerformed

    private void jF22_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF22_6ActionPerformed
        if(jF22_6.isSelected()) {
            CV[1][189] |= 0x20;
        } else {
            CV[1][189] &= ~0x20;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+189 );
    }//GEN-LAST:event_jF22_6ActionPerformed

    private void jF14_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF14_1ActionPerformed
        if(jF14_1.isSelected()) {
            CV[1][181] |= 1;
        } else {
            CV[1][181] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+181 );
    }//GEN-LAST:event_jF14_1ActionPerformed

    private void jFR_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFR_1ActionPerformed
        if(jFR_1.isSelected()) {
            CV[1][34] |= 1;
        } else {
            CV[1][34] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+34 );
    }//GEN-LAST:event_jFR_1ActionPerformed

    private void jF5_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF5_2ActionPerformed
        if(jF5_2.isSelected()) {
            CV[1][39] |= 2;
        } else {
            CV[1][39] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+39 );
    }//GEN-LAST:event_jF5_2ActionPerformed

    private void jF5_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF5_3ActionPerformed
        if(jF5_3.isSelected()) {
            CV[1][39] |= 4;
        } else {
            CV[1][39] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+39 );
    }//GEN-LAST:event_jF5_3ActionPerformed

    private void jF5_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF5_4ActionPerformed
        if(jF5_4.isSelected()) {
            CV[1][39] |= 8;
        } else {
            CV[1][39] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+39 );
    }//GEN-LAST:event_jF5_4ActionPerformed

    private void jF5_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF5_5ActionPerformed
        if(jF5_5.isSelected()) {
            CV[1][39] |= 0x10;
        } else {
            CV[1][39] &= ~0x10;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+39 );
    }//GEN-LAST:event_jF5_5ActionPerformed

    private void jF10_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF10_1ActionPerformed
        if(jF10_1.isSelected()) {
            CV[1][44] |= 1;
        } else {
            CV[1][44] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+44 );
    }//GEN-LAST:event_jF10_1ActionPerformed

    private void jF11_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF11_6ActionPerformed
        if(jF11_6.isSelected()) {
            CV[1][45] |= 0x20;
        } else {
            CV[1][45] &= ~0x20;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+45 );
    }//GEN-LAST:event_jF11_6ActionPerformed

    private void jF21_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF21_1ActionPerformed
        if(jF21_1.isSelected()) {
            CV[1][188] |= 1;
        } else {
            CV[1][188] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+188 );
    }//GEN-LAST:event_jF21_1ActionPerformed

    private void jF21_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF21_2ActionPerformed
        if(jF21_2.isSelected()) {
            CV[1][188] |= 2;
        } else {
            CV[1][188] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+188 );
    }//GEN-LAST:event_jF21_2ActionPerformed

    private void jF21_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF21_3ActionPerformed
        if(jF21_3.isSelected()) {
            CV[1][188] |= 4;
        } else {
            CV[1][188] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+188 );
    }//GEN-LAST:event_jF21_3ActionPerformed

    private void jF21_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF21_4ActionPerformed
        if(jF21_4.isSelected()) {
            CV[1][188] |= 8;
        } else {
            CV[1][188] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+188 );
    }//GEN-LAST:event_jF21_4ActionPerformed

    private void jF21_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF21_5ActionPerformed
        if(jF21_5.isSelected()) {
            CV[1][188] |= 0x10;
        } else {
            CV[1][188] &= ~0x10;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+188 );
    }//GEN-LAST:event_jF21_5ActionPerformed

    private void jF22_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF22_5ActionPerformed
        if(jF22_5.isSelected()) {
            CV[1][189] |= 0x10;
        } else {
            CV[1][189] &= ~0x10;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+189 );
    }//GEN-LAST:event_jF22_5ActionPerformed

    private void jF14_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF14_2ActionPerformed
        if(jF14_2.isSelected()) {
            CV[1][181] |= 2;
        } else {
            CV[1][181] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+181 );
    }//GEN-LAST:event_jF14_2ActionPerformed

    private void jF5_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF5_6ActionPerformed
        if(jF5_6.isSelected()) {
            CV[1][39] |= 0x20;
        } else {
            CV[1][39] &= ~0x20;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+39 );
    }//GEN-LAST:event_jF5_6ActionPerformed

    private void jF6_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF6_1ActionPerformed
        if(jF6_1.isSelected()) {
            CV[1][40] |= 1;
        } else {
            CV[1][40] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+40 );
    }//GEN-LAST:event_jF6_1ActionPerformed

    private void jF6_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF6_2ActionPerformed
        if(jF6_2.isSelected()) {
            CV[1][40] |= 2;
        } else {
            CV[1][40] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+40 );
    }//GEN-LAST:event_jF6_2ActionPerformed

    private void jF6_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF6_3ActionPerformed
        if(jF6_3.isSelected()) {
            CV[1][40] |= 4;
        } else {
            CV[1][40] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+40 );
    }//GEN-LAST:event_jF6_3ActionPerformed

    private void jF6_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF6_4ActionPerformed
        if(jF6_4.isSelected()) {
            CV[1][40] |= 8;
        } else {
            CV[1][40] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+40 );
    }//GEN-LAST:event_jF6_4ActionPerformed

    private void jF10_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF10_2ActionPerformed
        if(jF10_2.isSelected()) {
            CV[1][44] |= 2;
        } else {
            CV[1][44] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+44 );
    }//GEN-LAST:event_jF10_2ActionPerformed

    private void jF12_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF12_1ActionPerformed
        if(jF12_1.isSelected()) {
            CV[1][46] |= 1;
        } else {
            CV[1][46] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+46 );
    }//GEN-LAST:event_jF12_1ActionPerformed

    private void jF15_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF15_6ActionPerformed
        if(jF15_6.isSelected()) {
            CV[1][182] |= 0x20;
        } else {
            CV[1][182] &= ~0x20;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+182 );
    }//GEN-LAST:event_jF15_6ActionPerformed

    private void jF22_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF22_1ActionPerformed
        if(jF22_1.isSelected()) {
            CV[1][189] |= 1;
        } else {
            CV[1][189] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+189 );
    }//GEN-LAST:event_jF22_1ActionPerformed

    private void jF22_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF22_2ActionPerformed
        if(jF22_2.isSelected()) {
            CV[1][189] |= 2;
        } else {
            CV[1][189] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+189 );
    }//GEN-LAST:event_jF22_2ActionPerformed

    private void jF22_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF22_3ActionPerformed
        if(jF22_3.isSelected()) {
            CV[1][189] |= 4;
        } else {
            CV[1][189] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+189 );
    }//GEN-LAST:event_jF22_3ActionPerformed

    private void jF22_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF22_4ActionPerformed
        if(jF22_4.isSelected()) {
            CV[1][189] |= 8;
        } else {
            CV[1][189] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+189 );
    }//GEN-LAST:event_jF22_4ActionPerformed

    private void jF23_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF23_4ActionPerformed
        if(jF23_4.isSelected()) {
            CV[1][190] |= 8;
        } else {
            CV[1][190] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+190 );
    }//GEN-LAST:event_jF23_4ActionPerformed

    private void jF14_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF14_3ActionPerformed
        if(jF14_3.isSelected()) {
            CV[1][181] |= 4;
        } else {
            CV[1][181] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+181 );
    }//GEN-LAST:event_jF14_3ActionPerformed

    private void jF6_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF6_5ActionPerformed
        if(jF6_5.isSelected()) {
            CV[1][40] |= 0x10;
        } else {
            CV[1][40] &= ~0x10;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+40 );
    }//GEN-LAST:event_jF6_5ActionPerformed

    private void jF6_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF6_6ActionPerformed
        if(jF6_6.isSelected()) {
            CV[1][40] |= 0x20;
        } else {
            CV[1][40] &= ~0x20;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+40 );
    }//GEN-LAST:event_jF6_6ActionPerformed

    private void jF7_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF7_1ActionPerformed
        if(jF7_1.isSelected()) {
            CV[1][41] |= 1;
        } else {
            CV[1][41] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+41 );
    }//GEN-LAST:event_jF7_1ActionPerformed

    private void jF7_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF7_2ActionPerformed
        if(jF7_2.isSelected()) {
            CV[1][41] |= 2;
        } else {
            CV[1][41] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+41 );
    }//GEN-LAST:event_jF7_2ActionPerformed

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

    private void jF12_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF12_2ActionPerformed
        if(jF12_2.isSelected()) {
            CV[1][46] |= 2;
        } else {
            CV[1][46] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+46 );
    }//GEN-LAST:event_jF12_2ActionPerformed

    private void jF15_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF15_4ActionPerformed
        if(jF15_4.isSelected()) {
            CV[1][182] |= 8;
        } else {
            CV[1][182] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+182 );
    }//GEN-LAST:event_jF15_4ActionPerformed

    private void jF15_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF15_5ActionPerformed
        if(jF15_5.isSelected()) {
            CV[1][182] |= 0x10;
        } else {
            CV[1][182] &= ~0x10;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+182 );
    }//GEN-LAST:event_jF15_5ActionPerformed

    private void jF23_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF23_1ActionPerformed
        if(jF23_1.isSelected()) {
            CV[1][190] |= 1;
        } else {
            CV[1][190] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+190 );
    }//GEN-LAST:event_jF23_1ActionPerformed

    private void jF23_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF23_2ActionPerformed
        if(jF23_2.isSelected()) {
            CV[1][190] |= 2;
        } else {
            CV[1][190] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+190 );
    }//GEN-LAST:event_jF23_2ActionPerformed

    private void jF23_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF23_3ActionPerformed
        if(jF23_3.isSelected()) {
            CV[1][190] |= 4;
        } else {
            CV[1][190] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+190 );
    }//GEN-LAST:event_jF23_3ActionPerformed

    private void jF23_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF23_6ActionPerformed
        if(jF23_6.isSelected()) {
            CV[1][190] |= 0x20;
        } else {
            CV[1][190] &= ~0x20;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+190 );
    }//GEN-LAST:event_jF23_6ActionPerformed

    private void jF14_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF14_4ActionPerformed
        if(jF14_4.isSelected()) {
            CV[1][181] |= 8;
        } else {
            CV[1][181] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+181 );
    }//GEN-LAST:event_jF14_4ActionPerformed

    private void jF7_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF7_4ActionPerformed
        if(jF7_4.isSelected()) {
            CV[1][41] |= 8;
        } else {
            CV[1][41] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+41 );
    }//GEN-LAST:event_jF7_4ActionPerformed

    private void jF7_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF7_5ActionPerformed
        if(jF7_5.isSelected()) {
            CV[1][41] |= 0x10;
        } else {
            CV[1][41] &= ~0x10;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+41 );
    }//GEN-LAST:event_jF7_5ActionPerformed

    private void jF7_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF7_6ActionPerformed
        if(jF7_6.isSelected()) {
            CV[1][41] |= 0x20;
        } else {
            CV[1][41] &= ~0x20;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+41 );
    }//GEN-LAST:event_jF7_6ActionPerformed

    private void jF8_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF8_1ActionPerformed
        if(jF8_1.isSelected()) {
            CV[1][42] |= 1;
        } else {
            CV[1][42] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+42 );
    }//GEN-LAST:event_jF8_1ActionPerformed

    private void jF8_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF8_2ActionPerformed
        if(jF8_2.isSelected()) {
            CV[1][42] |= 2;
        } else {
            CV[1][42] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+42 );
    }//GEN-LAST:event_jF8_2ActionPerformed

    private void jF10_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF10_4ActionPerformed
        if(jF10_4.isSelected()) {
            CV[1][44] |= 8;
        } else {
            CV[1][44] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+44 );
    }//GEN-LAST:event_jF10_4ActionPerformed

    private void jF12_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF12_3ActionPerformed
        if(jF12_3.isSelected()) {
            CV[1][46] |= 4;
        } else {
            CV[1][46] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+46 );
    }//GEN-LAST:event_jF12_3ActionPerformed

    private void jF12_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF12_6ActionPerformed
        if(jF12_6.isSelected()) {
            CV[1][46] |= 0x20;
        } else {
            CV[1][46] &= ~0x20;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+46 );
    }//GEN-LAST:event_jF12_6ActionPerformed

    private void jF13_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF13_6ActionPerformed
        if(jF13_6.isSelected()) {
            CV[1][180] |= 0x20;
        } else {
            CV[1][180] &= ~0x20;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+180 );
    }//GEN-LAST:event_jF13_6ActionPerformed

    private void jF15_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF15_1ActionPerformed
        if(jF15_1.isSelected()) {
            CV[1][182] |= 1;
        } else {
            CV[1][182] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+182 );
    }//GEN-LAST:event_jF15_1ActionPerformed

    private void jF15_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF15_2ActionPerformed
        if(jF15_2.isSelected()) {
            CV[1][182] |= 2;
        } else {
            CV[1][182] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+182 );
    }//GEN-LAST:event_jF15_2ActionPerformed

    private void jF15_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF15_3ActionPerformed
        if(jF15_3.isSelected()) {
            CV[1][182] |= 4;
        } else {
            CV[1][182] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+182 );
    }//GEN-LAST:event_jF15_3ActionPerformed

    private void jF13_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF13_5ActionPerformed
        if(jF13_5.isSelected()) {
            CV[1][180] |= 0x10;
        } else {
            CV[1][180] &= ~0x10;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+180 );
    }//GEN-LAST:event_jF13_5ActionPerformed

    private void jF14_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF14_5ActionPerformed
        if(jF14_5.isSelected()) {
            CV[1][181] |= 0x10;
        } else {
            CV[1][181] &= ~0x10;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+181 );
    }//GEN-LAST:event_jF14_5ActionPerformed

    private void jF8_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF8_3ActionPerformed
        if(jF8_3.isSelected()) {
            CV[1][42] |= 4;
        } else {
            CV[1][42] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+42 );
    }//GEN-LAST:event_jF8_3ActionPerformed

    private void jF8_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF8_4ActionPerformed
        if(jF8_4.isSelected()) {
            CV[1][42] |= 8;
        } else {
            CV[1][42] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+42 );
    }//GEN-LAST:event_jF8_4ActionPerformed

    private void jF8_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF8_5ActionPerformed
        if(jF8_5.isSelected()) {
            CV[1][42] |= 0x10;
        } else {
            CV[1][42] &= ~0x10;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+42 );
    }//GEN-LAST:event_jF8_5ActionPerformed

    private void jF8_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF8_6ActionPerformed
        if(jF8_6.isSelected()) {
            CV[1][42] |= 0x20;
        } else {
            CV[1][42] &= ~0x20;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+42 );
    }//GEN-LAST:event_jF8_6ActionPerformed

    private void jF9_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF9_1ActionPerformed
        if(jF9_1.isSelected()) {
            CV[1][43] |= 1;
        } else {
            CV[1][43] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+43 );
    }//GEN-LAST:event_jF9_1ActionPerformed

    private void jF10_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF10_5ActionPerformed
        if(jF10_5.isSelected()) {
            CV[1][44] |= 0x10;
        } else {
            CV[1][44] &= ~0x10;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+44 );
    }//GEN-LAST:event_jF10_5ActionPerformed

    private void jF12_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF12_4ActionPerformed
        if(jF12_4.isSelected()) {
            CV[1][46] |= 8;
        } else {
            CV[1][46] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+46 );
    }//GEN-LAST:event_jF12_4ActionPerformed

    private void jF12_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF12_5ActionPerformed
        if(jF12_5.isSelected()) {
            CV[1][46] |= 0x10;
        } else {
            CV[1][46] &= ~0x10;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+46 );
    }//GEN-LAST:event_jF12_5ActionPerformed

    private void jF13_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF13_1ActionPerformed
        if(jF13_1.isSelected()) {
            CV[1][180] |= 1;
        } else {
            CV[1][180] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+180 );
    }//GEN-LAST:event_jF13_1ActionPerformed

    private void jF13_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF13_2ActionPerformed
        if(jF13_2.isSelected()) {
            CV[1][180] |= 2;
        } else {
            CV[1][180] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+180 );
    }//GEN-LAST:event_jF13_2ActionPerformed

    private void jF13_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF13_3ActionPerformed
        if(jF13_3.isSelected()) {
            CV[1][180] |= 4;
        } else {
            CV[1][180] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+180 );
    }//GEN-LAST:event_jF13_3ActionPerformed

    private void jF13_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF13_4ActionPerformed
        if(jF13_4.isSelected()) {
            CV[1][180] |= 8;
        } else {
            CV[1][180] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+180 );
    }//GEN-LAST:event_jF13_4ActionPerformed

    private void jFunctionMappingComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jFunctionMappingComponentShown
        jCV_Anzeige.setSelectedItem( "CV#"+33 );

        int cvValue;
        //------------- FL ------------------
        cvValue = CV[1][33];
        jFL_1.setSelected((cvValue &  1) ==  1);
        jFL_2.setSelected((cvValue &  2) ==  2);
        jFL_3.setSelected((cvValue &  4) ==  4);
        jFL_4.setSelected((cvValue &  8) ==  8);
        jFL_5.setSelected((cvValue & 16) == 16);
        jFL_6.setSelected((cvValue & 32) == 32);

        //------------- FR ------------------
        cvValue = CV[1][34];
        jFR_1.setSelected((cvValue &  1) ==  1);
        jFR_2.setSelected((cvValue &  2) ==  2);
        jFR_3.setSelected((cvValue &  4) ==  4);
        jFR_4.setSelected((cvValue &  8) ==  8);
        jFR_5.setSelected((cvValue & 16) == 16);
        jFR_6.setSelected((cvValue & 32) == 32);
        
        //------------- F1 ------------------
        cvValue = CV[1][35];
        jF1_1.setSelected((cvValue &  1) ==  1);
        jF1_2.setSelected((cvValue &  2) ==  2);
        jF1_3.setSelected((cvValue &  4) ==  4);
        jF1_4.setSelected((cvValue &  8) ==  8);
        jF1_5.setSelected((cvValue & 16) == 16);
        jF1_6.setSelected((cvValue & 32) == 32);

        //------------- F2 ------------------
        cvValue = CV[1][36];
        jF2_1.setSelected((cvValue &  1) ==  1);
        jF2_2.setSelected((cvValue &  2) ==  2);
        jF2_3.setSelected((cvValue &  4) ==  4);
        jF2_4.setSelected((cvValue &  8) ==  8);
        jF2_5.setSelected((cvValue & 16) == 16);
        jF2_6.setSelected((cvValue & 32) == 32);

        //------------- F3 ------------------
        cvValue = CV[1][37];
        jF3_1.setSelected((cvValue &  1) ==  1);
        jF3_2.setSelected((cvValue &  2) ==  2);
        jF3_3.setSelected((cvValue &  4) ==  4);
        jF3_4.setSelected((cvValue &  8) ==  8);
        jF3_5.setSelected((cvValue & 16) == 16);
        jF3_6.setSelected((cvValue & 32) == 32);

        //------------- F4 ------------------
        cvValue = CV[1][38];
        jF4_1.setSelected((cvValue &  1) ==  1);
        jF4_2.setSelected((cvValue &  2) ==  2);
        jF4_3.setSelected((cvValue &  4) ==  4);
        jF4_4.setSelected((cvValue &  8) ==  8);
        jF4_5.setSelected((cvValue & 16) == 16);
        jF4_6.setSelected((cvValue & 32) == 32);

        //------------- F5 ------------------
        cvValue = CV[1][39];
        jF5_1.setSelected((cvValue &  1) ==  1);
        jF5_2.setSelected((cvValue &  2) ==  2);
        jF5_3.setSelected((cvValue &  4) ==  4);
        jF5_4.setSelected((cvValue &  8) ==  8);
        jF5_5.setSelected((cvValue & 16) == 16);
        jF5_6.setSelected((cvValue & 32) == 32);

        //------------- F6 ------------------
        cvValue = CV[1][40];
        jF6_1.setSelected((cvValue &  1) ==  1);
        jF6_2.setSelected((cvValue &  2) ==  2);
        jF6_3.setSelected((cvValue &  4) ==  4);
        jF6_4.setSelected((cvValue &  8) ==  8);
        jF6_5.setSelected((cvValue & 16) == 16);
        jF6_6.setSelected((cvValue & 32) == 32);

        //------------- F7 ------------------
        cvValue = CV[1][41];
        jF7_1.setSelected((cvValue &  1) ==  1);
        jF7_2.setSelected((cvValue &  2) ==  2);
        jF7_3.setSelected((cvValue &  4) ==  4);
        jF7_4.setSelected((cvValue &  8) ==  8);
        jF7_5.setSelected((cvValue & 16) == 16);
        jF7_6.setSelected((cvValue & 32) == 32);

        //------------- F8 ------------------
        cvValue = CV[1][42];
        jF8_1.setSelected((cvValue &  1) ==  1);
        jF8_2.setSelected((cvValue &  2) ==  2);
        jF8_3.setSelected((cvValue &  4) ==  4);
        jF8_4.setSelected((cvValue &  8) ==  8);
        jF8_5.setSelected((cvValue & 16) == 16);
        jF8_6.setSelected((cvValue & 32) == 32);

        //------------- F9 ------------------
        cvValue = CV[1][43];
        jF9_1.setSelected((cvValue &  1) ==  1);
        jF9_2.setSelected((cvValue &  2) ==  2);
        jF9_3.setSelected((cvValue &  4) ==  4);
        jF9_4.setSelected((cvValue &  8) ==  8);
        jF9_5.setSelected((cvValue & 16) == 16);
        jF9_6.setSelected((cvValue & 32) == 32);

        //------------- F10 ------------------
        cvValue = CV[1][44];
        jF10_1.setSelected((cvValue &  1) ==  1);
        jF10_2.setSelected((cvValue &  2) ==  2);
        jF10_3.setSelected((cvValue &  4) ==  4);
        jF10_4.setSelected((cvValue &  8) ==  8);
        jF10_5.setSelected((cvValue & 16) == 16);
        jF10_6.setSelected((cvValue & 32) == 32);

        //------------- F11 ------------------
        cvValue = CV[1][45];
        jF11_1.setSelected((cvValue &  1) ==  1);
        jF11_2.setSelected((cvValue &  2) ==  2);
        jF11_3.setSelected((cvValue &  4) ==  4);
        jF11_4.setSelected((cvValue &  8) ==  8);
        jF11_5.setSelected((cvValue & 16) == 16);
        jF11_6.setSelected((cvValue & 32) == 32);

        //------------- F12 ------------------
        cvValue = CV[1][46];
        jF12_1.setSelected((cvValue &  1) ==  1);
        jF12_2.setSelected((cvValue &  2) ==  2);
        jF12_3.setSelected((cvValue &  4) ==  4);
        jF12_4.setSelected((cvValue &  8) ==  8);
        jF12_5.setSelected((cvValue & 16) == 16);
        jF12_6.setSelected((cvValue & 32) == 32);

        //------------- F13 ------------------
        cvValue = CV[1][180];
        jF13_1.setSelected((cvValue &  1) ==  1);
        jF13_2.setSelected((cvValue &  2) ==  2);
        jF13_3.setSelected((cvValue &  4) ==  4);
        jF13_4.setSelected((cvValue &  8) ==  8);
        jF13_5.setSelected((cvValue & 16) == 16);
        jF13_6.setSelected((cvValue & 32) == 32);

        //------------- F14 ------------------
        cvValue = CV[1][181];
        jF14_1.setSelected((cvValue &  1) ==  1);
        jF14_2.setSelected((cvValue &  2) ==  2);
        jF14_3.setSelected((cvValue &  4) ==  4);
        jF14_4.setSelected((cvValue &  8) ==  8);
        jF14_5.setSelected((cvValue & 16) == 16);
        jF14_6.setSelected((cvValue & 32) == 32);

        //------------- F15 ------------------
        cvValue = CV[1][182];
        jF15_1.setSelected((cvValue &  1) ==  1);
        jF15_2.setSelected((cvValue &  2) ==  2);
        jF15_3.setSelected((cvValue &  4) ==  4);
        jF15_4.setSelected((cvValue &  8) ==  8);
        jF15_5.setSelected((cvValue & 16) == 16);
        jF15_6.setSelected((cvValue & 32) == 32);

        //------------- F16 ------------------
        cvValue = CV[1][183];
        jF16_1.setSelected((cvValue &  1) ==  1);
        jF16_2.setSelected((cvValue &  2) ==  2);
        jF16_3.setSelected((cvValue &  4) ==  4);
        jF16_4.setSelected((cvValue &  8) ==  8);
        jF16_5.setSelected((cvValue & 16) == 16);
        jF16_6.setSelected((cvValue & 32) == 32);

        //------------- F17 ------------------
        cvValue = CV[1][184];
        jF17_1.setSelected((cvValue &  1) ==  1);
        jF17_2.setSelected((cvValue &  2) ==  2);
        jF17_3.setSelected((cvValue &  4) ==  4);
        jF17_4.setSelected((cvValue &  8) ==  8);
        jF17_5.setSelected((cvValue & 16) == 16);
        jF17_6.setSelected((cvValue & 32) == 32);

        //------------- F18 ------------------
        cvValue = CV[1][185];
        jF18_1.setSelected((cvValue &  1) ==  1);
        jF18_2.setSelected((cvValue &  2) ==  2);
        jF18_3.setSelected((cvValue &  4) ==  4);
        jF18_4.setSelected((cvValue &  8) ==  8);
        jF18_5.setSelected((cvValue & 16) == 16);
        jF18_6.setSelected((cvValue & 32) == 32);

        //------------- F19 ------------------
        cvValue = CV[1][186];
        jF19_1.setSelected((cvValue &  1) ==  1);
        jF19_2.setSelected((cvValue &  2) ==  2);
        jF19_3.setSelected((cvValue &  4) ==  4);
        jF19_4.setSelected((cvValue &  8) ==  8);
        jF19_5.setSelected((cvValue & 16) == 16);
        jF19_6.setSelected((cvValue & 32) == 32);

        //------------- F20 ------------------
        cvValue = CV[1][187];
        jF20_1.setSelected((cvValue &  1) ==  1);
        jF20_2.setSelected((cvValue &  2) ==  2);
        jF20_3.setSelected((cvValue &  4) ==  4);
        jF20_4.setSelected((cvValue &  8) ==  8);
        jF20_5.setSelected((cvValue & 16) == 16);
        jF20_6.setSelected((cvValue & 32) == 32);

        //------------- F21 ------------------
        cvValue = CV[1][188];
        jF21_1.setSelected((cvValue &  1) ==  1);
        jF21_2.setSelected((cvValue &  2) ==  2);
        jF21_3.setSelected((cvValue &  4) ==  4);
        jF21_4.setSelected((cvValue &  8) ==  8);
        jF21_5.setSelected((cvValue & 16) == 16);
        jF21_6.setSelected((cvValue & 32) == 32);

        //------------- F22 ------------------
        cvValue = CV[1][189];
        jF22_1.setSelected((cvValue &  1) ==  1);
        jF22_2.setSelected((cvValue &  2) ==  2);
        jF22_3.setSelected((cvValue &  4) ==  4);
        jF22_4.setSelected((cvValue &  8) ==  8);
        jF22_5.setSelected((cvValue & 16) == 16);
        jF22_6.setSelected((cvValue & 32) == 32);

        //------------- F23 ------------------
        cvValue = CV[1][190];
        jF23_1.setSelected((cvValue &  1) ==  1);
        jF23_2.setSelected((cvValue &  2) ==  2);
        jF23_3.setSelected((cvValue &  4) ==  4);
        jF23_4.setSelected((cvValue &  8) ==  8);
        jF23_5.setSelected((cvValue & 16) == 16);
        jF23_6.setSelected((cvValue & 32) == 32);

        //------------- F24 ------------------
        cvValue = CV[1][191];
        jF24_1.setSelected((cvValue &  1) ==  1);
        jF24_2.setSelected((cvValue &  2) ==  2);
        jF24_3.setSelected((cvValue &  4) ==  4);
        jF24_4.setSelected((cvValue &  8) ==  8);
        jF24_5.setSelected((cvValue & 16) == 16);
        jF24_6.setSelected((cvValue & 32) == 32);

        //------------- F25 ------------------
        cvValue = CV[1][192];
        jF25_1.setSelected((cvValue &  1) ==  1);
        jF25_2.setSelected((cvValue &  2) ==  2);
        jF25_3.setSelected((cvValue &  4) ==  4);
        jF25_4.setSelected((cvValue &  8) ==  8);
        jF25_5.setSelected((cvValue & 16) == 16);
        jF25_6.setSelected((cvValue & 32) == 32);

        //------------- F26 ------------------
        cvValue = CV[1][193];
        jF26_1.setSelected((cvValue &  1) ==  1);
        jF26_2.setSelected((cvValue &  2) ==  2);
        jF26_3.setSelected((cvValue &  4) ==  4);
        jF26_4.setSelected((cvValue &  8) ==  8);
        jF26_5.setSelected((cvValue & 16) == 16);
        jF26_6.setSelected((cvValue & 32) == 32);

        //------------- F27 ------------------
        cvValue = CV[1][194];
        jF27_1.setSelected((cvValue &  1) ==  1);
        jF27_2.setSelected((cvValue &  2) ==  2);
        jF27_3.setSelected((cvValue &  4) ==  4);
        jF27_4.setSelected((cvValue &  8) ==  8);
        jF27_5.setSelected((cvValue & 16) == 16);
        jF27_6.setSelected((cvValue & 32) == 32);

        //------------- F28 ------------------
        cvValue = CV[1][195];
        jF28_1.setSelected((cvValue &  1) ==  1);
        jF28_2.setSelected((cvValue &  2) ==  2);
        jF28_3.setSelected((cvValue &  4) ==  4);
        jF28_4.setSelected((cvValue &  8) ==  8);
        jF28_5.setSelected((cvValue & 16) == 16);
        jF28_6.setSelected((cvValue & 32) == 32);

    }//GEN-LAST:event_jFunctionMappingComponentShown

    private void jVor1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jVor1ActionPerformed
        if(!jVor1.isSelected()) {
            CV[1][53] |= 2;
        } else {
            CV[1][53] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+53 );
    }//GEN-LAST:event_jVor1ActionPerformed

    private void jRueck1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRueck1ActionPerformed
        if(!jRueck1.isSelected()) {
            CV[1][53] |= 1;
        } else {
            CV[1][53] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+53 );
    }//GEN-LAST:event_jRueck1ActionPerformed

    private void jRueck2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRueck2ActionPerformed
        if(!jRueck2.isSelected()) {
            CV[1][54] |= 1;
        } else {
            CV[1][54] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+54 );
    }//GEN-LAST:event_jRueck2ActionPerformed

    private void jVor2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jVor2ActionPerformed
        if(!jVor2.isSelected()) {
            CV[1][54] |= 2;
        } else {
            CV[1][54] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+54 );
    }//GEN-LAST:event_jVor2ActionPerformed

    private void jVor3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jVor3ActionPerformed
        if(!jVor3.isSelected()) {
            CV[1][55] |= 2;
        } else {
            CV[1][55] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+55 );
    }//GEN-LAST:event_jVor3ActionPerformed

    private void jRueck3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRueck3ActionPerformed
        if(!jRueck3.isSelected()) {
            CV[1][55] |= 1;
        } else {
            CV[1][55] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+55 );
    }//GEN-LAST:event_jRueck3ActionPerformed

    private void jBlink6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBlink6ActionPerformed
        if(jBlink6.isSelected()) {
            CV[1][58] |= 16;
        } else {
            CV[1][58] &= ~16;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+58 );
    }//GEN-LAST:event_jBlink6ActionPerformed

    private void jBlink_Pausezeit_3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlink_Pausezeit_3FocusLost
        int max = 255; // Handbuch
        max = CV[1][108] - ( CV[1][134] * 2 ); // Abhängigkeiten
        if( ! KTUI.checkNumRange(jBlink_Pausezeit_3.getText(), 0, max) ) {
            KTUI.mbBlinkPausezeit( this, CV[1][108], CV[1][134], jBlink_Pausezeit_3.getText() );
            jBlink_Pausezeit_3.grabFocus();
            return;
        }
        // Wert ist bereits geprüft. also "still" übernehmen (view -> false)
        CV[1][142] = KTUI.checkTextField( this, jBlink_Pausezeit_3, 0, max, 2, false);
        jCV_Anzeige.setSelectedItem( "CV#"+142 );
    }//GEN-LAST:event_jBlink_Pausezeit_3FocusLost

    private void jBlink_Pausezeit_3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBlink_Pausezeit_3KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jBlink_Pausezeit_3KeyReleased

    private void jMARs2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMARs2ActionPerformed
        if(jMARs2.isSelected()) {
            CV[1][54] |= 32;
        } else {
            CV[1][54] &= ~32;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+54 );
    }//GEN-LAST:event_jMARs2ActionPerformed

    private void jMARs1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMARs1ActionPerformed
        if(jMARs1.isSelected()) {
            CV[1][53] |= 32;
        } else {
            CV[1][53] &= ~32;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+53 );
    }//GEN-LAST:event_jMARs1ActionPerformed

    private void jMARs3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMARs3ActionPerformed
        if(jMARs3.isSelected()) {
            CV[1][55] |= 32;
        } else {
            CV[1][55] &= ~32;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+55 );
    }//GEN-LAST:event_jMARs3ActionPerformed

    private void jVor4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jVor4ActionPerformed
        if(!jVor4.isSelected()) {
            CV[1][56] |= 2;
        } else {
            CV[1][56] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+56 );
    }//GEN-LAST:event_jVor4ActionPerformed

    private void jVor5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jVor5ActionPerformed
        if(!jVor5.isSelected()) {
            CV[1][57] |= 2;
        } else {
            CV[1][57] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+57 );
    }//GEN-LAST:event_jVor5ActionPerformed

    private void jVor6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jVor6ActionPerformed
        if(!jVor6.isSelected()) {
            CV[1][58] |= 2;
        } else {
            CV[1][58] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+58 );
    }//GEN-LAST:event_jVor6ActionPerformed

    private void jMARs4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMARs4ActionPerformed
        if(jMARs4.isSelected()) {
            CV[1][56] |= 32;
        } else {
            CV[1][56] &= ~32;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+56 );
    }//GEN-LAST:event_jMARs4ActionPerformed

    private void jBlink1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBlink1ActionPerformed
        if(jBlink1.isSelected()) {
            CV[1][53] |= 16;
        } else {
            CV[1][53] &= ~16;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+53 );
    }//GEN-LAST:event_jBlink1ActionPerformed

    private void jBlink2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBlink2ActionPerformed
        if(jBlink2.isSelected()) {
            CV[1][54] |= 16;
        } else {
            CV[1][54] &= ~16;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+54 );
    }//GEN-LAST:event_jBlink2ActionPerformed

    private void jBlink3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBlink3ActionPerformed
        if(jBlink3.isSelected()) {
            CV[1][55] |= 16;
        } else {
            CV[1][55] &= ~16;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+55 );
    }//GEN-LAST:event_jBlink3ActionPerformed

    private void jRueck4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRueck4ActionPerformed
        if(!jRueck4.isSelected()) {
            CV[1][56] |= 1;
        } else {
            CV[1][56] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+56 );
    }//GEN-LAST:event_jRueck4ActionPerformed

    private void jRueck5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRueck5ActionPerformed
        if(!jRueck5.isSelected()) {
            CV[1][57] |= 1;
        } else {
            CV[1][57] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+57 );
    }//GEN-LAST:event_jRueck5ActionPerformed

    private void jRueck6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRueck6ActionPerformed
        if(!jRueck6.isSelected()) {
            CV[1][58] |= 1;
        } else {
            CV[1][58] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+58 );
    }//GEN-LAST:event_jRueck6ActionPerformed

    private void jBlink4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBlink4ActionPerformed
        if(jBlink4.isSelected()) {
            CV[1][56] |= 16;
        } else {
            CV[1][56] &= ~16;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+56 );
    }//GEN-LAST:event_jBlink4ActionPerformed

    private void jBlink5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBlink5ActionPerformed
        if(jBlink5.isSelected()) {
            CV[1][57] |= 16;
        } else {
            CV[1][57] &= ~16;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+57 );
    }//GEN-LAST:event_jBlink5ActionPerformed

    private void jBlink_Pausezeit_4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlink_Pausezeit_4FocusLost
        int max = 255; // Handbuch
        max = CV[1][109] - ( CV[1][135] * 2 ); // Abhängigkeiten
        if( ! KTUI.checkNumRange(jBlink_Pausezeit_4.getText(), 0, max) ) {
            KTUI.mbBlinkPausezeit( this, CV[1][109], CV[1][135], jBlink_Pausezeit_4.getText() );
            jBlink_Pausezeit_4.grabFocus();
            return;
        }
        // Wert ist bereits geprüft. also "still" übernehmen (view -> false)
        CV[1][143] = KTUI.checkTextField( this, jBlink_Pausezeit_4, 0, max, 2, false);
        jCV_Anzeige.setSelectedItem( "CV#"+143 );
    }//GEN-LAST:event_jBlink_Pausezeit_4FocusLost

    private void jBlink_Pausezeit_4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBlink_Pausezeit_4KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jBlink_Pausezeit_4KeyReleased

    private void jBlinkfrequenzMarsFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlinkfrequenzMarsFocusLost
        CV[1][112] = KTUI.checkTextField( this, jBlinkfrequenzMars, 0, 255, 64, true);
        jCV_Anzeige.setSelectedItem( "CV#"+112 );
    }//GEN-LAST:event_jBlinkfrequenzMarsFocusLost

    private void jBlinkfrequenzMarsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBlinkfrequenzMarsKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jBlinkfrequenzMarsKeyReleased

    private void jDoppelBlink1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDoppelBlink1ActionPerformed
        if(jDoppelBlink1.isSelected()) {
            CV[1][53] |= 64;
        } else {
            CV[1][53] &= ~64;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+53 );
    }//GEN-LAST:event_jDoppelBlink1ActionPerformed

    private void jDoppelBlink2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDoppelBlink2ActionPerformed
        if(jDoppelBlink2.isSelected()) {
            CV[1][54] |= 64;
        } else {
            CV[1][54] &= ~64;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+54 );
    }//GEN-LAST:event_jDoppelBlink2ActionPerformed

    private void jDoppelBlink3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDoppelBlink3ActionPerformed
        if(jDoppelBlink3.isSelected()) {
            CV[1][55] |= 64;
        } else {
            CV[1][55] &= ~64;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+55 );
    }//GEN-LAST:event_jDoppelBlink3ActionPerformed

    private void jDoppelBlink4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDoppelBlink4ActionPerformed
        if(jDoppelBlink4.isSelected()) {
            CV[1][56] |= 64;
        } else {
            CV[1][56] &= ~64;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+56 );
    }//GEN-LAST:event_jDoppelBlink4ActionPerformed

    private void jDoppelBlink5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDoppelBlink5ActionPerformed
        if(jDoppelBlink5.isSelected()) {
            CV[1][57] |= 64;
        } else {
            CV[1][57] &= ~64;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+57 );
    }//GEN-LAST:event_jDoppelBlink5ActionPerformed

    private void jDoppelBlink6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDoppelBlink6ActionPerformed
        if(jDoppelBlink6.isSelected()) {
            CV[1][58] |= 64;
        } else {
            CV[1][58] &= ~64;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+58 );
    }//GEN-LAST:event_jDoppelBlink6ActionPerformed

    private void jBlink_Einschaltzeit_1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlink_Einschaltzeit_1FocusLost
        int max = 255; // Handbuch
        max = ( CV[1][106] - CV[1][140] ) / 2 ; // Abhängigkeiten
        if( ! KTUI.checkNumRange(jBlink_Einschaltzeit_1.getText(), 0, max) ) {
            KTUI.mbBlinkEinschaltzeit( this, CV[1][106], CV[1][140], jBlink_Einschaltzeit_1.getText() );
            jBlink_Einschaltzeit_1.grabFocus();
            return;
        }
        // Wert ist bereits geprüft. also "still" übernehmen (view -> false)
        CV[1][132] = KTUI.checkTextField( this, jBlink_Einschaltzeit_1, 0, max, 4, false);
        jCV_Anzeige.setSelectedItem( "CV#"+132 );
    }//GEN-LAST:event_jBlink_Einschaltzeit_1FocusLost

    private void jBlink_Einschaltzeit_1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBlink_Einschaltzeit_1KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jBlink_Einschaltzeit_1KeyReleased

    private void jBlink_Einschaltzeit_2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlink_Einschaltzeit_2FocusLost
        int max = 255; // Handbuch
        max = ( CV[1][107] - CV[1][141] ) / 2 ; // Abhängigkeiten
        if( ! KTUI.checkNumRange(jBlink_Einschaltzeit_2.getText(), 0, max) ) {
            KTUI.mbBlinkEinschaltzeit( this, CV[1][107], CV[1][141], jBlink_Einschaltzeit_2.getText() );
            jBlink_Einschaltzeit_2.grabFocus();
            return;
        }
        // Wert ist bereits geprüft. also "still" übernehmen (view -> false)
        CV[1][133] = KTUI.checkTextField( this, jBlink_Einschaltzeit_2, 0, max, 4, false);
        jCV_Anzeige.setSelectedItem( "CV#"+133 );
    }//GEN-LAST:event_jBlink_Einschaltzeit_2FocusLost

    private void jBlink_Einschaltzeit_2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBlink_Einschaltzeit_2KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jBlink_Einschaltzeit_2KeyReleased

    private void jBlink_Einschaltzeit_3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlink_Einschaltzeit_3FocusLost
        int max = 255; // Handbuch
        max = ( CV[1][108] - CV[1][142] ) / 2 ; // Abhängigkeiten
        if( ! KTUI.checkNumRange(jBlink_Einschaltzeit_3.getText(), 0, max) ) {
            KTUI.mbBlinkEinschaltzeit( this, CV[1][108], CV[1][142], jBlink_Einschaltzeit_3.getText() );
            jBlink_Einschaltzeit_3.grabFocus();
            return;
        }
        // Wert ist bereits geprüft. also "still" übernehmen (view -> false)
        CV[1][134] = KTUI.checkTextField( this, jBlink_Einschaltzeit_3, 0, max, 4, false);
        jCV_Anzeige.setSelectedItem( "CV#"+134 );
    }//GEN-LAST:event_jBlink_Einschaltzeit_3FocusLost

    private void jBlink_Einschaltzeit_3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBlink_Einschaltzeit_3KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jBlink_Einschaltzeit_3KeyReleased

    private void jBlink_Einschaltzeit_4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlink_Einschaltzeit_4FocusLost
        int max = 255; // Handbuch
        max = ( CV[1][109] - CV[1][143] ) / 2 ; // Abhängigkeiten
        if( ! KTUI.checkNumRange(jBlink_Einschaltzeit_4.getText(), 0, max) ) {
            KTUI.mbBlinkEinschaltzeit( this, CV[1][109], CV[1][143], jBlink_Einschaltzeit_4.getText() );
            jBlink_Einschaltzeit_4.grabFocus();
            return;
        }
        // Wert ist bereits geprüft. also "still" übernehmen (view -> false)
        CV[1][135] = KTUI.checkTextField( this, jBlink_Einschaltzeit_4, 0, max, 4, false);
        jCV_Anzeige.setSelectedItem( "CV#"+135 );
    }//GEN-LAST:event_jBlink_Einschaltzeit_4FocusLost

    private void jBlink_Einschaltzeit_4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBlink_Einschaltzeit_4KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jBlink_Einschaltzeit_4KeyReleased

    private void jBlink_Einschaltzeit_5FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlink_Einschaltzeit_5FocusLost
        int max = 255; // Handbuch
        max = ( CV[1][110] - CV[1][144] ) / 2 ; // Abhängigkeiten
        if( ! KTUI.checkNumRange(jBlink_Einschaltzeit_5.getText(), 0, max) ) {
            KTUI.mbBlinkEinschaltzeit( this, CV[1][110], CV[1][144], jBlink_Einschaltzeit_5.getText() );
            jBlink_Einschaltzeit_5.grabFocus();
            return;
        }
        // Wert ist bereits geprüft. also "still" übernehmen (view -> false)
        CV[1][136] = KTUI.checkTextField( this, jBlink_Einschaltzeit_5, 0, max, 4, false);
        jCV_Anzeige.setSelectedItem( "CV#"+136 );
    }//GEN-LAST:event_jBlink_Einschaltzeit_5FocusLost

    private void jBlink_Einschaltzeit_5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBlink_Einschaltzeit_5KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jBlink_Einschaltzeit_5KeyReleased

    private void jBlink_Einschaltzeit_6FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlink_Einschaltzeit_6FocusLost
        int max = 255; // Handbuch
        max = ( CV[1][111] - CV[1][145] ) / 2 ; // Abhängigkeiten
        if( ! KTUI.checkNumRange(jBlink_Einschaltzeit_6.getText(), 0, max) ) {
            KTUI.mbBlinkEinschaltzeit( this, CV[1][111], CV[1][145], jBlink_Einschaltzeit_6.getText() );
            jBlink_Einschaltzeit_6.grabFocus();
            return;
        }
        // Wert ist bereits geprüft. also "still" übernehmen (view -> false)
        CV[1][137] = KTUI.checkTextField( this, jBlink_Einschaltzeit_6, 0, max, 4, false);
        jCV_Anzeige.setSelectedItem( "CV#"+137 );
    }//GEN-LAST:event_jBlink_Einschaltzeit_6FocusLost

    private void jBlink_Einschaltzeit_6KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBlink_Einschaltzeit_6KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jBlink_Einschaltzeit_6KeyReleased

    private void jBlink_Pausezeit_1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlink_Pausezeit_1FocusLost
        int max = 255; // Handbuch
        max = CV[1][106] - ( CV[1][132] * 2 ); // Abhängigkeiten
        if( ! KTUI.checkNumRange(jBlink_Pausezeit_1.getText(), 0, max) ) {
            KTUI.mbBlinkPausezeit( this, CV[1][106], CV[1][132], jBlink_Pausezeit_1.getText() );
            jBlink_Pausezeit_1.grabFocus();
            return;
        }
        // Wert ist bereits geprüft. also "still" übernehmen (view -> false)
        CV[1][140] = KTUI.checkTextField( this, jBlink_Pausezeit_1, 0, max, 2, false);
        jCV_Anzeige.setSelectedItem( "CV#"+140 );
    }//GEN-LAST:event_jBlink_Pausezeit_1FocusLost

    private void jBlink_Pausezeit_1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBlink_Pausezeit_1KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jBlink_Pausezeit_1KeyReleased

    private void jBlink_Pausezeit_2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlink_Pausezeit_2FocusLost
        int max = 255; // Handbuch
        max = CV[1][107] - ( CV[1][133] * 2 ); // Abhängigkeiten
        if( ! KTUI.checkNumRange(jBlink_Pausezeit_2.getText(), 0, max) ) {
            KTUI.mbBlinkPausezeit( this, CV[1][107], CV[1][133], jBlink_Pausezeit_2.getText() );
            jBlink_Pausezeit_2.grabFocus();
            return;
        }
        // Wert ist bereits geprüft. also "still" übernehmen (view -> false)
        CV[1][141] = KTUI.checkTextField( this, jBlink_Pausezeit_2, 0, max, 2, false);
        jCV_Anzeige.setSelectedItem( "CV#"+141 );
    }//GEN-LAST:event_jBlink_Pausezeit_2FocusLost

    private void jBlink_Pausezeit_2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBlink_Pausezeit_2KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jBlink_Pausezeit_2KeyReleased

    private void jEffekte_1ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jEffekte_1ComponentShown
        jCV_Anzeige.setSelectedItem( "CV#"+53 );

        //AUX...
        int cvValue;
        cvValue = CV[1][53];
        jRueck1.setSelected(   ! ((cvValue &   1) ==   1));
        jVor1.setSelected(     ! ((cvValue &   2) ==   2));
        jAuxInv1.setSelected(     (cvValue &   4) ==   4 );
        jBl_Inv1.setSelected(     (cvValue &   8) ==   8 );
        jBlink1.setSelected(      (cvValue &  16) ==  16 );
        jMARs1.setSelected(       (cvValue &  32) ==  32 );
        jDoppelBlink1.setSelected((cvValue &  64) ==  64 );
        jKick1.setSelected(       (cvValue & 128) == 128 );

        cvValue = CV[1][54];
        jRueck2.setSelected(   ! ((cvValue &   1) ==   1));
        jVor2.setSelected(     ! ((cvValue &   2) ==   2));
        jAuxInv2.setSelected(     (cvValue &   4) ==   4 );
        jBl_Inv2.setSelected(     (cvValue &   8) ==   8 );
        jBlink2.setSelected(      (cvValue &  16) ==  16 );
        jMARs2.setSelected(       (cvValue &  32) ==  32 );
        jDoppelBlink2.setSelected((cvValue &  64) ==  64 );
        jKick2.setSelected(       (cvValue & 128) == 128 );

        cvValue = CV[1][55];
        jRueck3.setSelected(   ! ((cvValue &   1) ==   1));
        jVor3.setSelected(     ! ((cvValue &   2) ==   2));
        jAuxInv3.setSelected(     (cvValue &   4) ==   4 );
        jBl_Inv3.setSelected(     (cvValue &   8) ==   8 );
        jBlink3.setSelected(      (cvValue &  16) ==  16 );
        jMARs3.setSelected(       (cvValue &  32) ==  32 );
        jDoppelBlink3.setSelected((cvValue &  64) ==  64 );
        jKick3.setSelected(       (cvValue & 128) == 128 );

        cvValue = CV[1][56];
        jRueck4.setSelected(   ! ((cvValue &   1) ==   1));
        jVor4.setSelected(     ! ((cvValue &   2) ==   2));
        jAuxInv4.setSelected(     (cvValue &   4) ==   4 );
        jBl_Inv4.setSelected(     (cvValue &   8) ==   8 );
        jBlink4.setSelected(      (cvValue &  16) ==  16 );
        jMARs4.setSelected(       (cvValue &  32) ==  32 );
        jDoppelBlink4.setSelected((cvValue &  64) ==  64 );
        jKick4.setSelected(       (cvValue & 128) == 128 );

        cvValue = CV[1][57];
        jRueck5.setSelected(   ! ((cvValue &   1) ==   1));
        jVor5.setSelected(     ! ((cvValue &   2) ==   2));
        jAuxInv5.setSelected(     (cvValue &   4) ==   4 );
        jBl_Inv5.setSelected(     (cvValue &   8) ==   8 );
        jBlink5.setSelected(      (cvValue &  16) ==  16 );
        jDoppelBlink5.setSelected((cvValue &  64) ==  64 );
        jKick5.setSelected(       (cvValue & 128) == 128 );

        cvValue = CV[1][58];
        jRueck6.setSelected(   ! ((cvValue &   1) ==   1));
        jVor6.setSelected(     ! ((cvValue &   2) ==   2));
        jAuxInv6.setSelected(     (cvValue &   4) ==   4 );
        jBl_Inv6.setSelected(     (cvValue &   8) ==   8 );
        jBlink6.setSelected(      (cvValue &  16) ==  16 );
        jDoppelBlink6.setSelected((cvValue &  64) ==  64 );
        jKick6.setSelected(       (cvValue & 128) == 128 );

        //----------------
       
        jBlink_Einschaltzeit_1.setText("" + CV[1][132]);
        jBlink_Einschaltzeit_2.setText("" + CV[1][133]);
        jBlink_Einschaltzeit_3.setText("" + CV[1][134]);
        jBlink_Einschaltzeit_4.setText("" + CV[1][135]);
        jBlink_Einschaltzeit_5.setText("" + CV[1][136]);
        jBlink_Einschaltzeit_6.setText("" + CV[1][137]);
        
        jBlink_Pausezeit_1.setText( "" + CV[1][140]);
        jBlink_Pausezeit_2.setText( "" + CV[1][141]);
        jBlink_Pausezeit_3.setText( "" + CV[1][142]);
        jBlink_Pausezeit_4.setText( "" + CV[1][143]);
        jBlink_Pausezeit_5.setText( "" + CV[1][144]);
        jBlink_Pausezeit_6.setText( "" + CV[1][145]);
        
        jBlinkfrequenz1.setText("" + CV[1][106]);
        jBlinkfrequenz2.setText("" + CV[1][107]);
        jBlinkfrequenz3.setText("" + CV[1][108]);
        jBlinkfrequenz4.setText("" + CV[1][109]);
        jBlinkfrequenz5.setText("" + CV[1][110]);
        jBlinkfrequenz6.setText("" + CV[1][111]);
         //----------------
       
        jBlinkfrequenzMars.setText("" + CV[1][112]);
    }//GEN-LAST:event_jEffekte_1ComponentShown

    private void jF1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF1ActionPerformed
        if(jF1.isSelected()) {
            CV[1][13] |= 1;
        } else {
            CV[1][13] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+13 );
    }//GEN-LAST:event_jF1ActionPerformed

    private void jF2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF2ActionPerformed
        if(jF2.isSelected()) {
            CV[1][13] |= 2;
        } else {
            CV[1][13] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+13 );
    }//GEN-LAST:event_jF2ActionPerformed

    private void jF3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF3ActionPerformed
        if(jF3.isSelected()) {
            CV[1][13] |= 4;
        } else {
            CV[1][13] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+13 );
    }//GEN-LAST:event_jF3ActionPerformed

    private void jF4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF4ActionPerformed
        if(jF4.isSelected()) {
            CV[1][13] |= 8;
        } else {
            CV[1][13] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+13 );
    }//GEN-LAST:event_jF4ActionPerformed

    private void jF5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF5ActionPerformed
        if(jF5.isSelected()) {
            CV[1][13] |= 0x10;
        } else {
            CV[1][13] &= ~0x10;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+13 );
    }//GEN-LAST:event_jF5ActionPerformed

    private void jF6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF6ActionPerformed
        if(jF6.isSelected()) {
            CV[1][13] |= 0x20;
        } else {
            CV[1][13] &= ~0x20;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+13 );
    }//GEN-LAST:event_jF6ActionPerformed

    private void jF7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF7ActionPerformed
        if(jF7.isSelected()) {
            CV[1][13] |= 0x40;
        } else {
            CV[1][13] &= ~0x40;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+13 );
    }//GEN-LAST:event_jF7ActionPerformed

    private void jF8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF8ActionPerformed
        if(jF8.isSelected()) {
            CV[1][13] |= 0x80;
        } else {
            CV[1][13] &= ~0x80;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+13 );
    }//GEN-LAST:event_jF8ActionPerformed

    private void jAnalogComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jAnalogComponentShown
        jCV_Anzeige.setSelectedItem( "CV#"+13 );

        int cvValue = CV[1][13];
        jF1.setSelected((cvValue &   1) ==   1);
        jF2.setSelected((cvValue &   2) ==   2);
        jF3.setSelected((cvValue &   4) ==   4);
        jF4.setSelected((cvValue &   8) ==   8);
        jF5.setSelected((cvValue &  16) ==  16);
        jF6.setSelected((cvValue &  32) ==  32);
        jF7.setSelected((cvValue &  64) ==  64);
        jF8.setSelected((cvValue & 128) == 128);
    }//GEN-LAST:event_jAnalogComponentShown

    private void jDecoderAdresse1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDecoderAdresse1FocusLost
        CV[1][19] = KTUI.checkTextField( this, jDecoderAdresse1, 0, 127, 0, true);
        jCV_Anzeige.setSelectedItem( "CV#"+19 );
    }//GEN-LAST:event_jDecoderAdresse1FocusLost

    private void jDecoderAdresse1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDecoderAdresse1KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jDecoderAdresse1KeyReleased

    private void jKurzeAdrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jKurzeAdrActionPerformed
        int j = CV[1][1];
        if( j < 1 || j > 255 )
        {
            KTUI.mbValueNaN( this, 1, 255, true);
            j = 1;
        }
        else if (j > 127)
        {
            KTUI.mbAdr128MMonly( this );
        }
        CV[1][1] = j;
        jLongAddr.setSelected(false);
        CV[1][29] &= ~32;
        jCV_Anzeige.setSelectedItem( "CV#"+1 );
        jDecoderAdresse.setText("" + j);
    }//GEN-LAST:event_jKurzeAdrActionPerformed

    private void jCV_InhaltFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jCV_InhaltFocusLost
        int adr;
        int oldValue;
        int currCV = getCVfromIndexString(jCV_Anzeige, "CV#");
        String oriEingabe = jCV_Inhalt.getText();
        // int cvValue = KTUI.checkTextField( this, jCV_Inhalt, 0, 255, 22222, false);
        int cvValue = KTUI.checkTextField( this, jCV_Inhalt, 0, 255, -1, false);
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
                cvValue = KTUI.checkTextField( this, jCV_Inhalt, 1, 255, 3, true );
                s = jCV_Inhalt.getText();
                if( cvValue > 127 ) {
                    KTUI.mbAdr128MMonly( this );
                }
                jDecoderAdresse.setText(s);
                jKurzeAdr.setSelected(true);
                jlangeAdr.setSelected(false);
                CV[1][29] &= ~32;
                jLongAddr.setSelected(false);
                break;
                
            case 2: //CV#2 AnfahrGeschw SUSI
            case 3: //CV#3 AnfahrVerz SUSI
            case 4: //CV#4 BremsVerz SUSI
            case 5: //CV#5 VMax SUSI
            case 6: //CV#6 VMitten SUSI
                if( debugLevel >= 1 ) {
                    System.out.println("SUSI-CV="+currCV+" Wert="+cvValue+" ohne weiteren Test übernommen");
                }
                break;

            case 7: //CV#7 Version
            case 8: //CV#8 Hersteller
                if( debugLevel >= 1 ) {
                    System.out.println("jCV_InhaltFocusLost CV="+currCV+" Wert="+cvValue+" IGNORIERT");
                }
                return;

            case 11: //CV11 PacketTimeout (Analogerkennung)
                cvValue = KTUI.checkTextField( this, jCV_Inhalt, 0, 255, 5, true );
                s = jCV_Inhalt.getText();
                jPacketTimeOut.setText(s);
                break;

            case 13: //CV#13 im Analogbetrieb aktive Funktionen
                jF1.setSelected((cvValue &   1) ==   1);
                jF2.setSelected((cvValue &   2) ==   2);
                jF3.setSelected((cvValue &   4) ==   4);
                jF4.setSelected((cvValue &   8) ==   8);
                jF5.setSelected((cvValue &  16) ==  16);
                jF6.setSelected((cvValue &  32) ==  32);
                jF7.setSelected((cvValue &  64) ==  64);
                jF8.setSelected((cvValue & 128) == 128);
                break;
                
            case 17: //CV#17 erweiterte Adresse (high)
                cvValue = KTUI.checkTextField( this, jCV_Inhalt, 192, 255, 192, true );
                if (cvValue < 192) {
                    KTUI.mbValueNaNcv( this, 192, 255, 17, true);
                    cvValue = 192;
                    jCV_Inhalt.setText("192");
                }
                CV[1][currCV] = cvValue;
                jKurzeAdr.setSelected(false);
                jlangeAdr.setSelected(true);
                adr = (CV[1][17] - 192)*256 + CV[1][18];
                jDecoderAdresse.setText(""+adr);
                break;

            case 18: //CV#18 erweiterte Adresse (low)
                jDecoderAdresse.setText(s);
                jKurzeAdr.setSelected(false);
                jlangeAdr.setSelected(true);
                break;

            case 19: //CV#19 Consistadresse
                if (cvValue > 127)
                {
                    KTUI.mbValueConsist( this, 0, 127 );
                    cvValue = 127;
                    s = "127";
                    jCV_Inhalt.setText(s);
                }
                jDecoderAdresse1.setText(s);
                break;

            case 27: //CV#27 Bremsverhalten bei Gleichspannung (0,16,32,48)
                switch( cvValue ) {
                    case 0:
                    case 16:
                    case 32:
                    case 48:
                        if( debugLevel >= 2 ) {
                            System.out.println("CV="+currCV+" Wert="+cvValue+" Bremsverhalten bei Gleichspannung (0,16,32,48) OK");
                        }
                        break;
                    default:
                        System.out.println("CV="+currCV+" Wert="+cvValue+" Bremsverhalten bei Gleichspannung (0,16,32,48)) trotz falschem Wert übernommen" );
                }
                break;

            case 28: //CV#28 RailCom
                jBroadCasst.setSelected((cvValue & 1) == 1);
                jChannel2.setSelected((cvValue & 2) == 2);
                break;

            case 29: //CV#29 Konfigurationsdaten 1
                jRichtung.setSelected((cvValue &  1) ==  1);
                jFS.setSelected(      (cvValue &  2) ==  2);
                jAnalog1.setSelected( (cvValue &  4) ==  4);
                jRailCom.setSelected( (cvValue &  8) ==  8);
                // TODO Was ist mit 16 alternative Kennlinie ?
                // Gibt es die wegen (SUSI-)Sound) oder nicht ?
                jLongAddr.setSelected((cvValue & 32) == 32);

                jKurzeAdr.setSelected((cvValue & 32) ==  0);
                jlangeAdr.setSelected((cvValue & 32) == 32);
                break;

            case 31: //CV#31 Index für höhere CV-Pages
                if( debugLevel >= 1 ) {
                    System.out.println("jCV_InhaltFocusLost CV="+currCV+" Wert="+cvValue+" IGNORIERT");
                }
                return;

            case 32: //CV#32  Index für höhere CV-Pages
                if( debugLevel >= 1 ) {
                    System.out.println("jCV_InhaltFocusLost CV="+currCV+" Wert="+cvValue+" IGNORIERT");
                }
                return;

            case 33: //CV#33
                jFL_1.setSelected((cvValue &  1) ==  1);
                jFL_2.setSelected((cvValue &  2) ==  2);
                jFL_3.setSelected((cvValue &  4) ==  4);
                jFL_4.setSelected((cvValue &  8) ==  8);
                jFL_5.setSelected((cvValue & 16) == 16);
                jFL_6.setSelected((cvValue & 32) == 32);
                break;

            case 34: //CV#34
                jFR_1.setSelected((cvValue &  1) ==  1);
                jFR_2.setSelected((cvValue &  2) ==  2);
                jFR_3.setSelected((cvValue &  4) ==  4);
                jFR_4.setSelected((cvValue &  8) ==  8);
                jFR_5.setSelected((cvValue & 16) == 16);
                jFR_6.setSelected((cvValue & 32) == 32);
                break;

            case 35: //CV#35
                jF1_1.setSelected((cvValue &  1) ==  1);
                jF1_1.setSelected((cvValue &  2) ==  2);
                jF1_1.setSelected((cvValue &  4) ==  4);
                jF1_1.setSelected((cvValue &  8) ==  8);
                jF1_1.setSelected((cvValue & 16) == 16);
                jF1_1.setSelected((cvValue & 32) == 32);
                break;

            case 36: //CV#36
                jF2_1.setSelected((cvValue &  1) ==  1);
                jF2_1.setSelected((cvValue &  2) ==  2);
                jF2_1.setSelected((cvValue &  4) ==  4);
                jF2_1.setSelected((cvValue &  8) ==  8);
                jF2_1.setSelected((cvValue & 16) == 16);
                jF2_1.setSelected((cvValue & 32) == 32);
                break;

            case 37: //CV#37
                jF3_1.setSelected((cvValue &  1) ==  1);
                jF3_1.setSelected((cvValue &  2) ==  2);
                jF3_1.setSelected((cvValue &  4) ==  4);
                jF3_1.setSelected((cvValue &  8) ==  8);
                jF3_1.setSelected((cvValue & 16) == 16);
                jF3_1.setSelected((cvValue & 32) == 32);
                break;

            case 38: //CV#38
                jF4_1.setSelected((cvValue &  1) ==  1);
                jF4_1.setSelected((cvValue &  2) ==  2);
                jF4_1.setSelected((cvValue &  4) ==  4);
                jF4_1.setSelected((cvValue &  8) ==  8);
                jF4_1.setSelected((cvValue & 16) == 16);
                jF4_1.setSelected((cvValue & 32) == 32);
                break;

            case 39: //CV#39
                jF5_1.setSelected((cvValue &  1) ==  1);
                jF5_1.setSelected((cvValue &  2) ==  2);
                jF5_1.setSelected((cvValue &  4) ==  4);
                jF5_1.setSelected((cvValue &  8) ==  8);
                jF5_1.setSelected((cvValue & 16) == 16);
                jF5_1.setSelected((cvValue & 32) == 32);
                break;

            case 40: //CV#40
                jF6_1.setSelected((cvValue &  1) ==  1);
                jF6_1.setSelected((cvValue &  2) ==  2);
                jF6_1.setSelected((cvValue &  4) ==  4);
                jF6_1.setSelected((cvValue &  8) ==  8);
                jF6_1.setSelected((cvValue & 16) == 16);
                jF6_1.setSelected((cvValue & 32) == 32);
                break;

            case 41: //CV#41
                jF7_1.setSelected((cvValue &  1) ==  1);
                jF7_1.setSelected((cvValue &  2) ==  2);
                jF7_1.setSelected((cvValue &  4) ==  4);
                jF7_1.setSelected((cvValue &  8) ==  8);
                jF7_1.setSelected((cvValue & 16) == 16);
                jF7_1.setSelected((cvValue & 32) == 32);
                break;

            case 42: //CV#42
                jF8_1.setSelected((cvValue &  1) ==  1);
                jF8_1.setSelected((cvValue &  2) ==  2);
                jF8_1.setSelected((cvValue &  4) ==  4);
                jF8_1.setSelected((cvValue &  8) ==  8);
                jF8_1.setSelected((cvValue & 16) == 16);
                jF8_1.setSelected((cvValue & 32) == 32);
                break;

            case 43: //CV#43
                jF9_1.setSelected((cvValue &  1) ==  1);
                jF9_1.setSelected((cvValue &  2) ==  2);
                jF9_1.setSelected((cvValue &  4) ==  4);
                jF9_1.setSelected((cvValue &  8) ==  8);
                jF9_1.setSelected((cvValue & 16) == 16);
                jF9_1.setSelected((cvValue & 32) == 32);
                break;

            case 44: //CV#44
                jF10_1.setSelected((cvValue &  1) ==  1);
                jF10_1.setSelected((cvValue &  2) ==  2);
                jF10_1.setSelected((cvValue &  4) ==  4);
                jF10_1.setSelected((cvValue &  8) ==  8);
                jF10_1.setSelected((cvValue & 16) == 16);
                jF10_1.setSelected((cvValue & 32) == 32);
                break;

            case 45: //CV#45
                jF11_1.setSelected((cvValue &  1) ==  1);
                jF11_1.setSelected((cvValue &  2) ==  2);
                jF11_1.setSelected((cvValue &  4) ==  4);
                jF11_1.setSelected((cvValue &  8) ==  8);
                jF11_1.setSelected((cvValue & 16) == 16);
                jF11_1.setSelected((cvValue & 32) == 32);
                break;

            case 46: //CV#46
                jF12_1.setSelected((cvValue &  1) ==  1);
                jF12_1.setSelected((cvValue &  2) ==  2);
                jF12_1.setSelected((cvValue &  4) ==  4);
                jF12_1.setSelected((cvValue &  8) ==  8);
                jF12_1.setSelected((cvValue & 16) == 16);
                jF12_1.setSelected((cvValue & 32) == 32);
                break;

            case 47: //CV47 2te MM-Adresse
                cvValue = KTUI.checkTextField( this, jCV_Inhalt, 1, 255, 4, true );
                s = jCV_Inhalt.getText();
                jMM_Addr_2.setText(s);
                break;

            case 48: //CV48 Abschalten bei Überlast (nur für Notfälle)
                cvValue = KTUI.checkTextField( this, jCV_Inhalt, 0, 255, 64, true );
                break;

            case 49: //CV49 Konfigurationsdaten 2
                // TODO eventuell in FD-R extended gar nicht genutzt !
                cvValue = KTUI.checkTextField( this, jCV_Inhalt, 2, 126, 72, true );
                break;

            case 50: //CV#50 Hilfsregister (CV-Nr) für Programmierung mit alten Zentralen
            case 51: //CV#51 Hilfsregister (CV-Wert) für Programmierung mit alten Zentralen
                if( debugLevel >= 1 ) {
                    System.out.println("jCV_InhaltFocusLost CV="+currCV+" Wert="+cvValue+" IGNORIERT");
                }
                return;

            case 53: //CV#53 Effekte AUX1
                jRueck1.setSelected(   ! ((cvValue &   1) ==   1));
                jVor1.setSelected(     ! ((cvValue &   2) ==   2));
                jAuxInv1.setSelected(     (cvValue &   4) ==   4 );
                jBl_Inv1.setSelected(     (cvValue &   8) ==   8 );
                jBlink1.setSelected(      (cvValue &  16) ==  16 );
                jMARs1.setSelected(       (cvValue &  32) ==  32 );
                jDoppelBlink1.setSelected((cvValue &  64) ==  64 );
                jKick1.setSelected(       (cvValue & 128) == 128 );
                break;

            case 54: //CV#54 Effekte AUX2
                jRueck2.setSelected(   ! ((cvValue &   1) ==   1));
                jVor2.setSelected(     ! ((cvValue &   2) ==   2));
                jAuxInv2.setSelected(     (cvValue &   4) ==   4 );
                jBl_Inv2.setSelected(     (cvValue &   8) ==   8 );
                jBlink2.setSelected(      (cvValue &  16) ==  16 );
                jMARs2.setSelected(       (cvValue &  32) ==  32 );
                jDoppelBlink2.setSelected((cvValue &  64) ==  64 );
                jKick2.setSelected(       (cvValue & 128) == 128 );
                break;

            case 55: //CV#55 Effekte AUX3
                jRueck3.setSelected(   ! ((cvValue &   1) ==   1));
                jVor3.setSelected(     ! ((cvValue &   2) ==   2));
                jAuxInv3.setSelected(     (cvValue &   4) ==   4 );
                jBl_Inv3.setSelected(     (cvValue &   8) ==   8 );
                jBlink3.setSelected(      (cvValue &  16) ==  16 );
                jMARs3.setSelected(       (cvValue &  32) ==  32 );
                jDoppelBlink3.setSelected((cvValue &  64) ==  64 );
                jKick3.setSelected(       (cvValue & 128) == 128 );
                break;

            case 56: //CV#56 Effekte AUX4
                jRueck4.setSelected(   ! ((cvValue &   1) ==   1));
                jVor4.setSelected(     ! ((cvValue &   2) ==   2));
                jAuxInv4.setSelected(     (cvValue &   4) ==   4 );
                jBl_Inv4.setSelected(     (cvValue &   8) ==   8 );
                jBlink4.setSelected(      (cvValue &  16) ==  16 );
                jMARs4.setSelected(       (cvValue &  32) ==  32 );
                jDoppelBlink4.setSelected((cvValue &  64) ==  64 );
                jKick4.setSelected(       (cvValue & 128) == 128 );
                break;

            case 57: //CV#57 Effekte AUX5
                jRueck5.setSelected(   ! ((cvValue &   1) ==   1));
                jVor5.setSelected(     ! ((cvValue &   2) ==   2));
                jAuxInv5.setSelected(     (cvValue &   4) ==   4 );
                jBl_Inv5.setSelected(     (cvValue &   8) ==   8 );
                jBlink5.setSelected(      (cvValue &  16) ==  16 );
                jDoppelBlink5.setSelected((cvValue &  64) ==  64 );
                jKick5.setSelected(       (cvValue & 128) == 128 );
                break;

            case 58: //CV#58 Effekte AUX6
                jRueck6.setSelected(   ! ((cvValue &   1) ==   1));
                jVor6.setSelected(     ! ((cvValue &   2) ==   2));
                jAuxInv6.setSelected(     (cvValue &   4) ==   4 );
                jBl_Inv6.setSelected(     (cvValue &   8) ==   8 );
                jBlink6.setSelected(      (cvValue &  16) ==  16 );
                jDoppelBlink6.setSelected((cvValue &  64) ==  64 );
                jKick6.setSelected(       (cvValue & 128) == 128 );
                break;

            case 59: //CV#59 Zuordnung F-Ausg. zu Schalteing. 1
                jIn1Aux1.setSelected((cvValue &  1) ==  1);
                jIn1Aux2.setSelected((cvValue &  2) ==  2);
                jIn1Aux3.setSelected((cvValue &  4) ==  4);
                jIn1Aux4.setSelected((cvValue &  8) ==  8);
                jIn1Aux5.setSelected((cvValue & 16) == 16);
                jIn1Aux6.setSelected((cvValue & 32) == 32);
                break;

            case 60: //CV#60 Zuordnung servoPos. zu Schalteing. 1
                jIn1Pos1.setSelected(     (cvValue &  3) ==  1);
                jIn1Pos2.setSelected(     (cvValue &  3) ==  2);
                jIn1Pos3.setSelected(     (cvValue &  3) ==  3);
                jTuerIn1.setSelected(     (cvValue &  8) ==  8);
                jSchaffnerIn1.setSelected((cvValue & 16) == 16);
                jSignalIn1.setSelected(   (cvValue & 32) == 32);
                jGlockeIn1.setSelected(   (cvValue & 64) == 64);
                break;

            case 61: //CV#61 Zuordnung F-Ausg. zu Schalteing. 2
                jIn2Aux1.setSelected((cvValue &  1) ==  1);
                jIn2Aux2.setSelected((cvValue &  2) ==  2);
                jIn2Aux3.setSelected((cvValue &  4) ==  4);
                jIn2Aux4.setSelected((cvValue &  8) ==  8);
                jIn2Aux5.setSelected((cvValue & 16) == 16);
                jIn2Aux6.setSelected((cvValue & 32) == 32);
                break;

            case 62: //CV#62 Zuordnung servoPos. zu Schalteing. 2
                jIn2Pos1.setSelected(     (cvValue &  3) ==  1);
                jIn2Pos2.setSelected(     (cvValue &  3) ==  2);
                jIn2Pos3.setSelected(     (cvValue &  3) ==  3);
                jTuerIn2.setSelected(     (cvValue &  8) ==  8);
                jSchaffnerIn2.setSelected((cvValue & 16) == 16);
                jSignalIn2.setSelected(   (cvValue & 32) == 32);
                jGlockeIn2.setSelected(   (cvValue & 64) == 64);
                break;

            case 63: //CV#63 Zuordnung F-Ausg. zu Schalteing. 3
                jIn3Aux1.setSelected((cvValue &  1) ==  1);
                jIn3Aux2.setSelected((cvValue &  2) ==  2);
                jIn3Aux3.setSelected((cvValue &  4) ==  4);
                jIn3Aux4.setSelected((cvValue &  8) ==  8);
                jIn3Aux5.setSelected((cvValue & 16) == 16);
                jIn3Aux6.setSelected((cvValue & 32) == 32);
                break;

            case 64: //CV#64 Zuordnung servoPos. zu Schalteing. 3
                // jIn3Pos1.setSelected(     (cvValue &  3) ==  1);
                // jIn3Pos2.setSelected(     (cvValue &  3) ==  2);
                // jIn3Pos3.setSelected(     (cvValue &  3) ==  3);
                jTuerIn3.setSelected(     (cvValue &  8) ==  8);
                jSchaffnerIn3.setSelected((cvValue & 16) == 16);
                jSignalIn3.setSelected(   (cvValue & 32) == 32);
                jGlockeIn3.setSelected(   (cvValue & 64) == 64);
                break;

            case 67: // Alternative Kennlinie Punkt 1
            case 68: // Alternative Kennlinie Punkt 2
            case 69: // Alternative Kennlinie Punkt 3
            case 70: // Alternative Kennlinie Punkt 4
            case 71: // Alternative Kennlinie Punkt 5
            case 72: // Alternative Kennlinie Punkt 6
            case 73: // Alternative Kennlinie Punkt 7
            case 74: // Alternative Kennlinie Punkt 8
            case 75: // Alternative Kennlinie Punkt 9
            case 76: // Alternative Kennlinie Punkt 10
            case 77: // Alternative Kennlinie Punkt 11
            case 78: // Alternative Kennlinie Punkt 12
            case 79: // Alternative Kennlinie Punkt 13
            case 80: // Alternative Kennlinie Punkt 14
            case 81: // Alternative Kennlinie Punkt 15
            case 82: // Alternative Kennlinie Punkt 16
            case 83: // Alternative Kennlinie Punkt 17
            case 84: // Alternative Kennlinie Punkt 18
            case 85: // Alternative Kennlinie Punkt 19
            case 86: // Alternative Kennlinie Punkt 20
            case 87: // Alternative Kennlinie Punkt 21
            case 88: // Alternative Kennlinie Punkt 22
            case 89: // Alternative Kennlinie Punkt 23
            case 90: // Alternative Kennlinie Punkt 24
            case 91: // Alternative Kennlinie Punkt 25
            case 92: // Alternative Kennlinie Punkt 26
            case 93: // Alternative Kennlinie Punkt 27
            case 94: // Alternative Kennlinie Punkt 28
                if( debugLevel >= 1 ) {
                    System.out.println("SUSI-CV="+currCV+" Wert="+cvValue+" ohne weiteren Test übernommen");
                }
                break;

            case 106: // CV#106 AUX1 Blinkfrequenz der Beleuchtung
                // nutze den vorhandenen Test ;) 
                // Achtung: Randbedingungen müssen stimmen
                // Test darf bei Falscheingabe keinen Focus (um)setzen, auch nicht mit jCV_Anzeige.setSelectedItem( "CV#"+ x );
                jCV_Inhalt.setText(oriEingabe);         // Originaleingabe restaurieren...
                jBlinkfrequenz1.setText(oriEingabe);    // ...und Originaleingabe an den Test übergeben...
                oldValue = CV[1][currCV];         // alten Wert sichern
                CV[1][currCV] = -1;               // ungültigen Wert setzen !
                jBlinkfrequenz1FocusLost( null ); // ...Test aufrufen...
                cvValue = CV[1][currCV];          // neuen Wert merken
                if( -1 == cvValue ) {             // Wert unverändert ungültig -> Falscheingabe !
                    // alte Werte für die CV restaurieren
                    CV[1][currCV] = oldValue;
                    jBlinkfrequenz1.setText(""+oldValue);
                    // Fokus auf Eingabefeld
                    jCV_Inhalt.grabFocus();
                    if( debugLevel >= 1 ) {
                        System.out.println("CV="+currCV+" Wert="+cvValue+" Blinkfrequenz der Beleuchtung NACH SPEZIELLEM Test NICHT übernommen oldI="+oldValue);
                    }
                    // -> zurück zur Eingabe !!
                    return;
                }
                // es wurde ein gültiger Wert eingegeben -> Wert steht bereits in cvValue und im CV Array !
                if( debugLevel >= 1 ) {
                    System.out.println("CV="+currCV+" Wert="+cvValue+" Blinkfrequenz der Beleuchtung NACH SPEZIELLEM Test übernommen");
                }
                return;

            case 107: // CV#107 AUX2 Blinkfrequenz der Beleuchtung
                jCV_Inhalt.setText(oriEingabe);
                jBlinkfrequenz2.setText(oriEingabe);
                oldValue = CV[1][currCV];
                CV[1][currCV] = -1;
                jBlinkfrequenz2FocusLost( null );
                cvValue = CV[1][currCV];
                if( -1 == cvValue ) {
                    CV[1][currCV] = oldValue;
                    jBlinkfrequenz2.setText(""+oldValue);
                    jCV_Inhalt.grabFocus();
                    if( debugLevel >= 1 ) {
                        System.out.println("CV="+currCV+" Wert="+cvValue+" Blinkfrequenz der Beleuchtung NACH SPEZIELLEM Test NICHT übernommen oldI="+oldValue);
                    }
                    return;
                }
                if( debugLevel >= 1 ) {
                    System.out.println("CV="+currCV+" Wert="+cvValue+" Blinkfrequenz der Beleuchtung NACH SPEZIELLEM Test übernommen");
                }
                return;

            case 108: // CV#108 AUX3 Blinkfrequenz der Beleuchtung
                jCV_Inhalt.setText(oriEingabe);
                jBlinkfrequenz3.setText(oriEingabe);
                oldValue = CV[1][currCV];
                CV[1][currCV] = -1;
                jBlinkfrequenz3FocusLost( null );
                cvValue = CV[1][currCV];
                if( -1 == cvValue ) {
                    CV[1][currCV] = oldValue;
                    jBlinkfrequenz3.setText(""+oldValue);
                    jCV_Inhalt.grabFocus();
                    if( debugLevel >= 1 ) {
                        System.out.println("CV="+currCV+" Wert="+cvValue+" Blinkfrequenz der Beleuchtung NACH SPEZIELLEM Test NICHT übernommen oldI="+oldValue);
                    }
                    return;
                }
                if( debugLevel >= 1 ) {
                    System.out.println("CV="+currCV+" Wert="+cvValue+" Blinkfrequenz der Beleuchtung NACH SPEZIELLEM Test übernommen");
                }
                return;

            case 109: // CV#109 AUX4 Blinkfrequenz der Beleuchtung
                jCV_Inhalt.setText(oriEingabe);
                jBlinkfrequenz4.setText(oriEingabe);
                oldValue = CV[1][currCV];
                CV[1][currCV] = -1;
                jBlinkfrequenz4FocusLost( null );
                cvValue = CV[1][currCV];
                if( -1 == cvValue ) {
                    CV[1][currCV] = oldValue;
                    jBlinkfrequenz4.setText(""+oldValue);
                    jCV_Inhalt.grabFocus();
                    if( debugLevel >= 1 ) {
                        System.out.println("CV="+currCV+" Wert="+cvValue+" Blinkfrequenz der Beleuchtung NACH SPEZIELLEM Test NICHT übernommen oldI="+oldValue);
                    }
                    return;
                }
                if( debugLevel >= 1 ) {
                    System.out.println("CV="+currCV+" Wert="+cvValue+" Blinkfrequenz der Beleuchtung NACH SPEZIELLEM Test übernommen");
                }
                return;

            case 110: // CV#110 AUX5 Blinkfrequenz der Beleuchtung
                jCV_Inhalt.setText(oriEingabe);
                jBlinkfrequenz5.setText(oriEingabe);
                oldValue = CV[1][currCV];
                CV[1][currCV] = -1;
                jBlinkfrequenz5FocusLost( null );
                cvValue = CV[1][currCV];
                if( -1 == cvValue ) {
                    CV[1][currCV] = oldValue;
                    jBlinkfrequenz5.setText(""+oldValue);
                    jCV_Inhalt.grabFocus();
                    if( debugLevel >= 1 ) {
                        System.out.println("CV="+currCV+" Wert="+cvValue+" Blinkfrequenz der Beleuchtung NACH SPEZIELLEM Test NICHT übernommen oldI="+oldValue);
                    }
                    return;
                }
                if( debugLevel >= 1 ) {
                    System.out.println("CV="+currCV+" Wert="+cvValue+" Blinkfrequenz der Beleuchtung NACH SPEZIELLEM Test übernommen");
                }
                return;

            case 111: // CV#111 AUX6 Blinkfrequenz der Beleuchtung
                jCV_Inhalt.setText(oriEingabe);
                jBlinkfrequenz6.setText(oriEingabe);
                oldValue = CV[1][currCV];
                CV[1][currCV] = -1;
                jBlinkfrequenz6FocusLost( null );
                cvValue = CV[1][currCV];
                if( -1 == cvValue ) {
                    CV[1][currCV] = oldValue;
                    jBlinkfrequenz6.setText(""+oldValue);
                    jCV_Inhalt.grabFocus();
                    if( debugLevel >= 1 ) {
                        System.out.println("CV="+currCV+" Wert="+cvValue+" Blinkfrequenz der Beleuchtung NACH SPEZIELLEM Test NICHT übernommen oldI="+oldValue);
                    }
                    return;
                }
                if( debugLevel >= 1 ) {
                    System.out.println("CV="+currCV+" Wert="+cvValue+" Blinkfrequenz der Beleuchtung NACH SPEZIELLEM Test übernommen");
                }
                return;

            case 112: // CV#112 Blinkfrequenz für MARSlight
                jBlinkfrequenzMars.setText(s);
                break;

            case 113: // CV#113 interne Fahrst. f. CV#114
                cvValue = KTUI.checkTextField( this, jCV_Inhalt, 1, 126, 10, true );
                s = jCV_Inhalt.getText();
                jDimm_FS.setText(s);
                break;

            case 114: // CV#114 fahrstufenabh. gedimmte. Ausg.
                cvValue = KTUI.checkTextField( this, jCV_Inhalt, 0, 63, 0, true );
                s = jCV_Inhalt.getText();
                jbDimmFS1.setSelected((cvValue & 1) == 1);
                jbDimmFS2.setSelected((cvValue & 2) == 2);
                jbDimmFS3.setSelected((cvValue & 4) == 4) ;
                jbDimmFS4.setSelected((cvValue & 8) == 8);
                break;

            case 115: // CV#115 Dimmen AUX1
                cvValue = KTUI.checkTextField( this, jCV_Inhalt, 1, 255, 255, true );
                s = jCV_Inhalt.getText();
                jDimmen1.setText(s);
                break;

            case 116: // CV#116 Dimmen AUX2
                cvValue = KTUI.checkTextField( this, jCV_Inhalt, 1, 255, 255, true );
                s = jCV_Inhalt.getText();
                jDimmen2.setText(s);
                break;

            case 117: // CV#117 Dimmen AUX3
                cvValue = KTUI.checkTextField( this, jCV_Inhalt, 1, 255, 255, true );
                s = jCV_Inhalt.getText();
                jDimmen3.setText(s);
                break;

            case 118: // CV#118 Dimmen AUX4
                cvValue = KTUI.checkTextField( this, jCV_Inhalt, 1, 255, 255, true );
                s = jCV_Inhalt.getText();
                jDimmen4.setText(s);
                break;

            case 119: // CV#119 Mindestschaltdauer IN1
                jMindestSchlt1.setText(s);
                break;

            case 120: // CV#120 Mindestschaltdauer IN2
                jMindestSchlt2.setText(s);
                break;

            case 121: // CV#121 Mindestschaltdauer IN3
                jMindestSchlt3.setText(s);
                break;

            case 123: // CV#123 Fahrstufenabh. Dimmen AUX1
                cvValue = KTUI.checkTextField( this, jCV_Inhalt, 1, 255, 255, true );
                s = jCV_Inhalt.getText();
                jDimmFS1.setText(s);
                break;

            case 124: // CV#124 Fahrstufenabh. Dimmen AUX2
                cvValue = KTUI.checkTextField( this, jCV_Inhalt, 1, 255, 255, true );
                s = jCV_Inhalt.getText();
                jDimmFS2.setText(s);
                break;

            case 125: // CV#125 Fahrstufenabh. Dimmen AUX3
                cvValue = KTUI.checkTextField( this, jCV_Inhalt, 1, 255, 255, true );
                s = jCV_Inhalt.getText();
                jDimmFS3.setText(s);
                break;

            case 126: // CV#126 Fahrstufenabh. Dimmen AUX4
                cvValue = KTUI.checkTextField( this, jCV_Inhalt, 1, 255, 255, true );
                s = jCV_Inhalt.getText();
                jDimmFS4.setText(s);
                break;

            case 131: // CV#131 Rangierlicht
                jRangier1.setSelected( (cvValue & 0x01) == 0x01);
                jRangier2.setSelected( (cvValue & 0x02) == 0x02);
                jRangier3.setSelected( (cvValue & 0x04) == 0x04);
                jRangier4.setSelected( (cvValue & 0x08) == 0x08);
                jRangier5.setSelected( (cvValue & 0x10) == 0x10);
                jRangier6.setSelected( (cvValue & 0x20) == 0x20);
                jRangierF3.setSelected((cvValue & 0x40) == 0x40);
                jRangierF4.setSelected((cvValue & 0x80) == 0x80);
                break;

            case 132: // CV#132 Einschaltdauer Blinklicht AUX1
                jCV_Inhalt.setText(oriEingabe);
                jBlink_Einschaltzeit_1.setText(oriEingabe);
                oldValue = CV[1][currCV];
                CV[1][currCV] = -1;
                jBlink_Einschaltzeit_1FocusLost( null );
                cvValue = CV[1][currCV];
                if( -1 == cvValue ) {
                    CV[1][currCV] = oldValue;
                    jBlink_Einschaltzeit_1.setText(""+oldValue);
                    jCV_Inhalt.grabFocus();
                    if( debugLevel >= 1 ) {
                        System.out.println("CV="+currCV+" Wert="+cvValue+" Einschaltdauer Blinklicht NACH SPEZIELLEM Test NICHT übernommen oldI="+oldValue);
                    }
                    return;
                }
                if( debugLevel >= 1 ) {
                    System.out.println("CV="+currCV+" Wert="+cvValue+" Einschaltdauer Blinklicht NACH SPEZIELLEM Test übernommen");
                }
                return;

            case 133: // CV#133 Einschaltdauer Blinklicht AUX2
                jCV_Inhalt.setText(oriEingabe);
                jBlink_Einschaltzeit_2.setText(oriEingabe);
                oldValue = CV[1][currCV];
                CV[1][currCV] = -1;
                jBlink_Einschaltzeit_2FocusLost( null );
                cvValue = CV[1][currCV];
                if( -1 == cvValue ) {
                    CV[1][currCV] = oldValue;
                    jBlink_Einschaltzeit_2.setText(""+oldValue);
                    jCV_Inhalt.grabFocus();
                    if( debugLevel >= 1 ) {
                        System.out.println("CV="+currCV+" Wert="+cvValue+" Einschaltdauer Blinklicht NACH SPEZIELLEM Test NICHT übernommen oldI="+oldValue);
                    }
                    return;
                }
                if( debugLevel >= 1 ) {
                    System.out.println("CV="+currCV+" Wert="+cvValue+" Einschaltdauer Blinklicht NACH SPEZIELLEM Test übernommen");
                }
                return;

            case 134: // CV#134 Einschaltdauer Blinklicht AUX3
                jCV_Inhalt.setText(oriEingabe);
                jBlink_Einschaltzeit_3.setText(oriEingabe);
                oldValue = CV[1][currCV];
                CV[1][currCV] = -1;
                jBlink_Einschaltzeit_3FocusLost( null );
                cvValue = CV[1][currCV];
                if( -1 == cvValue ) {
                    CV[1][currCV] = oldValue;
                    jBlink_Einschaltzeit_3.setText(""+oldValue);
                    jCV_Inhalt.grabFocus();
                    if( debugLevel >= 1 ) {
                        System.out.println("CV="+currCV+" Wert="+cvValue+" Einschaltdauer Blinklicht NACH SPEZIELLEM Test NICHT übernommen oldI="+oldValue);
                    }
                    return;
                }
                if( debugLevel >= 1 ) {
                    System.out.println("CV="+currCV+" Wert="+cvValue+" Einschaltdauer Blinklicht NACH SPEZIELLEM Test übernommen");
                }
                return;

            case 135: // CV#135 Einschaltdauer Blinklicht AUX4
                jCV_Inhalt.setText(oriEingabe);
                jBlink_Einschaltzeit_4.setText(oriEingabe);
                oldValue = CV[1][currCV];
                CV[1][currCV] = -1;
                jBlink_Einschaltzeit_4FocusLost( null );
                cvValue = CV[1][currCV];
                if( -1 == cvValue ) {
                    CV[1][currCV] = oldValue;
                    jBlink_Einschaltzeit_4.setText(""+oldValue);
                    jCV_Inhalt.grabFocus();
                    if( debugLevel >= 1 ) {
                        System.out.println("CV="+currCV+" Wert="+cvValue+" Einschaltdauer Blinklicht NACH SPEZIELLEM Test NICHT übernommen oldI="+oldValue);
                    }
                    return;
                }
                if( debugLevel >= 1 ) {
                    System.out.println("CV="+currCV+" Wert="+cvValue+" Einschaltdauer Blinklicht NACH SPEZIELLEM Test übernommen");
                }
                return;

            case 136: // CV#136 Einschaltdauer Blinklicht AUX5
                jCV_Inhalt.setText(oriEingabe);
                jBlink_Einschaltzeit_5.setText(oriEingabe);
                oldValue = CV[1][currCV];
                CV[1][currCV] = -1;
                jBlink_Einschaltzeit_5FocusLost( null );
                cvValue = CV[1][currCV];
                if( -1 == cvValue ) {
                    CV[1][currCV] = oldValue;
                    jBlink_Einschaltzeit_5.setText(""+oldValue);
                    jCV_Inhalt.grabFocus();
                    if( debugLevel >= 1 ) {
                        System.out.println("CV="+currCV+" Wert="+cvValue+" Einschaltdauer Blinklicht NACH SPEZIELLEM Test NICHT übernommen oldI="+oldValue);
                    }
                    return;
                }
                if( debugLevel >= 1 ) {
                    System.out.println("CV="+currCV+" Wert="+cvValue+" Einschaltdauer Blinklicht NACH SPEZIELLEM Test übernommen");
                }
                return;

            case 137: // CV#137 Einschaltdauer Blinklicht AUX6
                jCV_Inhalt.setText(oriEingabe);
                jBlink_Einschaltzeit_6.setText(oriEingabe);
                oldValue = CV[1][currCV];
                CV[1][currCV] = -1;
                jBlink_Einschaltzeit_6FocusLost( null );
                cvValue = CV[1][currCV];
                if( -1 == cvValue ) {
                    CV[1][currCV] = oldValue;
                    jBlink_Einschaltzeit_6.setText(""+oldValue);
                    jCV_Inhalt.grabFocus();
                    if( debugLevel >= 1 ) {
                        System.out.println("CV="+currCV+" Wert="+cvValue+" Einschaltdauer Blinklicht NACH SPEZIELLEM Test NICHT übernommen oldI="+oldValue);
                    }
                    return;
                }
                if( debugLevel >= 1 ) {
                    System.out.println("CV="+currCV+" Wert="+cvValue+" Einschaltdauer Blinklicht NACH SPEZIELLEM Test übernommen");
                }
                return;

            case 140: // CV#140 Doppelblibken Pausenlänge AUX1
                jCV_Inhalt.setText(oriEingabe);
                jBlink_Pausezeit_1.setText(oriEingabe);
                oldValue = CV[1][currCV];
                CV[1][currCV] = -1;
                jBlink_Pausezeit_1FocusLost( null );
                cvValue = CV[1][currCV];
                if( -1 == cvValue ) {
                    CV[1][currCV] = oldValue;
                    jBlink_Pausezeit_1.setText(""+oldValue);
                    jCV_Inhalt.grabFocus();
                    if( debugLevel >= 1 ) {
                        System.out.println("CV="+currCV+" Wert="+cvValue+" Doppelblibken Pausenlänge NACH SPEZIELLEM Test NICHT übernommen oldI="+oldValue);
                    }
                    return;
                }
                if( debugLevel >= 1 ) {
                    System.out.println("CV="+currCV+" Wert="+cvValue+" Doppelblibken Pausenlänge NACH SPEZIELLEM Test übernommen");
                }
                return;

            case 141: // CV#141 Doppelblibken Pausenlänge AUX2
                jCV_Inhalt.setText(oriEingabe);
                jBlink_Pausezeit_2.setText(oriEingabe);
                oldValue = CV[1][currCV];
                CV[1][currCV] = -1;
                jBlink_Pausezeit_2FocusLost( null );
                cvValue = CV[1][currCV];
                if( -1 == cvValue ) {
                    CV[1][currCV] = oldValue;
                    jBlink_Pausezeit_2.setText(""+oldValue);
                    jCV_Inhalt.grabFocus();
                    if( debugLevel >= 1 ) {
                        System.out.println("CV="+currCV+" Wert="+cvValue+" Doppelblibken Pausenlänge NACH SPEZIELLEM Test NICHT übernommen oldI="+oldValue);
                    }
                    return;
                }
                if( debugLevel >= 1 ) {
                    System.out.println("CV="+currCV+" Wert="+cvValue+" Doppelblibken Pausenlänge NACH SPEZIELLEM Test übernommen");
                }
                return;

            case 142: // CV#142 Doppelblibken Pausenlänge AUX3
                jCV_Inhalt.setText(oriEingabe);
                jBlink_Pausezeit_3.setText(oriEingabe);
                oldValue = CV[1][currCV];
                CV[1][currCV] = -1;
                jBlink_Pausezeit_3FocusLost( null );
                cvValue = CV[1][currCV];
                if( -1 == cvValue ) {
                    CV[1][currCV] = oldValue;
                    jBlink_Pausezeit_3.setText(""+oldValue);
                    jCV_Inhalt.grabFocus();
                    if( debugLevel >= 1 ) {
                        System.out.println("CV="+currCV+" Wert="+cvValue+" Doppelblibken Pausenlänge NACH SPEZIELLEM Test NICHT übernommen oldI="+oldValue);
                    }
                    return;
                }
                if( debugLevel >= 1 ) {
                    System.out.println("CV="+currCV+" Wert="+cvValue+" Doppelblibken Pausenlänge NACH SPEZIELLEM Test übernommen");
                }
                return;

            case 143: // CV#143 Doppelblibken Pausenlänge AUX4
                jCV_Inhalt.setText(oriEingabe);
                jBlink_Pausezeit_4.setText(oriEingabe);
                oldValue = CV[1][currCV];
                CV[1][currCV] = -1;
                jBlink_Pausezeit_4FocusLost( null );
                cvValue = CV[1][currCV];
                if( -1 == cvValue ) {
                    CV[1][currCV] = oldValue;
                    jBlink_Pausezeit_4.setText(""+oldValue);
                    jCV_Inhalt.grabFocus();
                    if( debugLevel >= 1 ) {
                        System.out.println("CV="+currCV+" Wert="+cvValue+" Doppelblibken Pausenlänge NACH SPEZIELLEM Test NICHT übernommen oldI="+oldValue);
                    }
                    return;
                }
                if( debugLevel >= 1 ) {
                    System.out.println("CV="+currCV+" Wert="+cvValue+" Doppelblibken Pausenlänge NACH SPEZIELLEM Test übernommen");
                }
                return;

            case 144: // CV#144 Doppelblibken Pausenlänge AUX5
                jCV_Inhalt.setText(oriEingabe);
                jBlink_Pausezeit_5.setText(oriEingabe);
                oldValue = CV[1][currCV];
                CV[1][currCV] = -1;
                jBlink_Pausezeit_5FocusLost( null );
                cvValue = CV[1][currCV];
                if( -1 == cvValue ) {
                    CV[1][currCV] = oldValue;
                    jBlink_Pausezeit_5.setText(""+oldValue);
                    jCV_Inhalt.grabFocus();
                    if( debugLevel >= 1 ) {
                        System.out.println("CV="+currCV+" Wert="+cvValue+" Doppelblibken Pausenlänge NACH SPEZIELLEM Test NICHT übernommen oldI="+oldValue);
                    }
                    return;
                }
                if( debugLevel >= 1 ) {
                    System.out.println("CV="+currCV+" Wert="+cvValue+" Doppelblibken Pausenlänge NACH SPEZIELLEM Test übernommen");
                }
                return;

            case 145: // CV#145 Doppelblibken Pausenlänge AUX6
                jCV_Inhalt.setText(oriEingabe);
                jBlink_Pausezeit_6.setText(oriEingabe);
                oldValue = CV[1][currCV];
                CV[1][currCV] = -1;
                jBlink_Pausezeit_6FocusLost( null );
                cvValue = CV[1][currCV];
                if( -1 == cvValue ) {
                    CV[1][currCV] = oldValue;
                    jBlink_Pausezeit_6.setText(""+oldValue);
                    jCV_Inhalt.grabFocus();
                    if( debugLevel >= 1 ) {
                        System.out.println("CV="+currCV+" Wert="+cvValue+" Doppelblibken Pausenlänge NACH SPEZIELLEM Test NICHT übernommen oldI="+oldValue);
                    }
                    return;
                }
                if( debugLevel >= 1 ) {
                    System.out.println("CV="+currCV+" Wert="+cvValue+" Doppelblibken Pausenlänge NACH SPEZIELLEM Test übernommen");
                }
                return;

            case 148: // Zuordnung Fkt-Tasten zu integrierten Sounds (Seite 40)
                if( isValidFktkeySoundMapping( cvValue ) ) {
                    //------------- F1 ------------------
                    jF1_TP.setSelected( cvValue ==   8);
                    jF1_SP.setSelected( cvValue ==  16);
                    jF1_SH.setSelected( cvValue ==  32);
                    jF1_Gl.setSelected( cvValue ==  64);
                    //------------- F13 ------------------
                    jF13_TP.setSelected(cvValue == 136);
                    jF13_SP.setSelected(cvValue == 144);
                    jF13_SH.setSelected(cvValue == 160);
                    jF13_Gl.setSelected(cvValue == 192);
                } else {
                    // not OK
                    KTUI.mbInvalidValue(this, currCV, cvValue, "8 16 32 64 136 144 160 192" );
                    if( debugLevel >= 1 ) {
                        System.out.println("jCV_InhaltFocusLost CV="+currCV+" Wert="+cvValue+" IGNORIERT");
                    }
                    return;
                }
                break;

            case 149: // Zuordnung Fkt-Tasten zu integrierten Sounds (Seite 40)
                if( isValidFktkeySoundMapping( cvValue ) ) {
                    //------------- F2 ------------------
                    jF2_TP.setSelected( cvValue ==   8);
                    jF2_SP.setSelected( cvValue ==  16);
                    jF2_SH.setSelected( cvValue ==  32);
                    jF2_Gl.setSelected( cvValue ==  64);
                    //------------- F14 ------------------
                    jF14_TP.setSelected(cvValue == 136);
                    jF14_SP.setSelected(cvValue == 144);
                    jF14_SH.setSelected(cvValue == 160);
                    jF14_Gl.setSelected(cvValue == 192);
                } else {
                    // not OK
                    KTUI.mbInvalidValue(this, currCV, cvValue, "8 16 32 64 136 144 160 192" );
                    if( debugLevel >= 1 ) {
                        System.out.println("jCV_InhaltFocusLost CV="+currCV+" Wert="+cvValue+" IGNORIERT");
                    }
                    return;
                }
                break;

            case 150: // Zuordnung Fkt-Tasten zu integrierten Sounds (Seite 40)
                if( isValidFktkeySoundMapping( cvValue ) ) {
                    //------------- F3 ------------------
                    jF3_TP.setSelected( cvValue ==   8);
                    jF3_SP.setSelected( cvValue ==  16);
                    jF3_SH.setSelected( cvValue ==  32);
                    jF3_Gl.setSelected( cvValue ==  64);
                    //------------- F15 ------------------
                    jF15_TP.setSelected(cvValue == 136);
                    jF15_SP.setSelected(cvValue == 144);
                    jF15_SH.setSelected(cvValue == 160);
                    jF15_Gl.setSelected(cvValue == 192);
                } else {
                    // not OK
                    KTUI.mbInvalidValue(this, currCV, cvValue, "8 16 32 64 136 144 160 192" );
                    if( debugLevel >= 1 ) {
                        System.out.println("jCV_InhaltFocusLost CV="+currCV+" Wert="+cvValue+" IGNORIERT");
                    }
                    return;
                }
                break;

            case 151: // Zuordnung Fkt-Tasten zu integrierten Sounds (Seite 40)
                if( isValidFktkeySoundMapping( cvValue ) ) {
                    //------------- F4 ------------------
                    jF4_TP.setSelected( cvValue ==   8);
                    jF4_SP.setSelected( cvValue ==  16);
                    jF4_SH.setSelected( cvValue ==  32);
                    jF4_Gl.setSelected( cvValue ==  64);
                    //------------- F16 ------------------
                    jF16_TP.setSelected(cvValue == 136);
                    jF16_SP.setSelected(cvValue == 144);
                    jF16_SH.setSelected(cvValue == 160);
                    jF16_Gl.setSelected(cvValue == 192);
                } else {
                    // not OK
                    KTUI.mbInvalidValue(this, currCV, cvValue, "8 16 32 64 136 144 160 192" );
                    if( debugLevel >= 1 ) {
                        System.out.println("jCV_InhaltFocusLost CV="+currCV+" Wert="+cvValue+" IGNORIERT");
                    }
                    return;
                }
                break;

            case 152: // Zuordnung Fkt-Tasten zu integrierten Sounds (Seite 40)
                if( isValidFktkeySoundMapping( cvValue ) ) {
                    //------------- F5 ------------------
                    jF5_TP.setSelected( cvValue ==   8);
                    jF5_SP.setSelected( cvValue ==  16);
                    jF5_SH.setSelected( cvValue ==  32);
                    jF5_Gl.setSelected( cvValue ==  64);
                    //------------- F17 ------------------
                    jF17_TP.setSelected(cvValue == 136);
                    jF17_SP.setSelected(cvValue == 144);
                    jF17_SH.setSelected(cvValue == 160);
                    jF17_Gl.setSelected(cvValue == 192);
                } else {
                    // not OK
                    KTUI.mbInvalidValue(this, currCV, cvValue, "8 16 32 64 136 144 160 192" );
                    if( debugLevel >= 1 ) {
                        System.out.println("jCV_InhaltFocusLost CV="+currCV+" Wert="+cvValue+" IGNORIERT");
                    }
                    return;
                }
                break;

            case 153: // Zuordnung Fkt-Tasten zu integrierten Sounds (Seite 40)
                if( isValidFktkeySoundMapping( cvValue ) ) {
                    //------------- F6 ------------------
                    jF6_TP.setSelected( cvValue ==   8);
                    jF6_SP.setSelected( cvValue ==  16);
                    jF6_SH.setSelected( cvValue ==  32);
                    jF6_Gl.setSelected( cvValue ==  64);
                    //------------- F18 ------------------
                    jF18_TP.setSelected(cvValue == 136);
                    jF18_SP.setSelected(cvValue == 144);
                    jF18_SH.setSelected(cvValue == 160);
                    jF18_Gl.setSelected(cvValue == 192);
                } else {
                    // not OK
                    KTUI.mbInvalidValue(this, currCV, cvValue, "8 16 32 64 136 144 160 192" );
                    if( debugLevel >= 1 ) {
                        System.out.println("jCV_InhaltFocusLost CV="+currCV+" Wert="+cvValue+" IGNORIERT");
                    }
                    return;
                }
                break;

            case 154: // Zuordnung Fkt-Tasten zu integrierten Sounds (Seite 40)
                if( isValidFktkeySoundMapping( cvValue ) ) {
                    //------------- F7 ------------------
                    jF7_TP.setSelected( cvValue ==   8);
                    jF7_SP.setSelected( cvValue ==  16);
                    jF7_SH.setSelected( cvValue ==  32);
                    jF7_Gl.setSelected( cvValue ==  64);
                    //------------- F19 ------------------
                    jF19_TP.setSelected(cvValue == 136);
                    jF19_SP.setSelected(cvValue == 144);
                    jF19_SH.setSelected(cvValue == 160);
                    jF19_Gl.setSelected(cvValue == 192);
                } else {
                    // not OK
                    KTUI.mbInvalidValue(this, currCV, cvValue, "8 16 32 64 136 144 160 192" );
                    if( debugLevel >= 1 ) {
                        System.out.println("jCV_InhaltFocusLost CV="+currCV+" Wert="+cvValue+" IGNORIERT");
                    }
                    return;
                }
                break;

            case 155: // Zuordnung Fkt-Tasten zu integrierten Sounds (Seite 40)
                if( isValidFktkeySoundMapping( cvValue ) ) {
                    //------------- F8 ------------------
                    jF8_TP.setSelected( cvValue ==   8);
                    jF8_SP.setSelected( cvValue ==  16);
                    jF8_SH.setSelected( cvValue ==  32);
                    jF8_Gl.setSelected( cvValue ==  64);
                    //------------- F20 ------------------
                    jF20_TP.setSelected(cvValue == 136);
                    jF20_SP.setSelected(cvValue == 144);
                    jF20_SH.setSelected(cvValue == 160);
                    jF20_Gl.setSelected(cvValue == 192);
                } else {
                    // not OK
                    KTUI.mbInvalidValue(this, currCV, cvValue, "8 16 32 64 136 144 160 192" );
                    if( debugLevel >= 1 ) {
                        System.out.println("jCV_InhaltFocusLost CV="+currCV+" Wert="+cvValue+" IGNORIERT");
                    }
                    return;
                }
                break;

            case 156: // Zuordnung Fkt-Tasten zu integrierten Sounds (Seite 40)
                if( isValidFktkeySoundMapping( cvValue ) ) {
                    //------------- F9 ------------------
                    jF9_TP.setSelected( cvValue ==   8);
                    jF9_SP.setSelected( cvValue ==  16);
                    jF9_SH.setSelected( cvValue ==  32);
                    jF9_Gl.setSelected( cvValue ==  64);
                    //------------- F21 ------------------
                    jF21_TP.setSelected(cvValue == 136);
                    jF21_SP.setSelected(cvValue == 144);
                    jF21_SH.setSelected(cvValue == 160);
                    jF21_Gl.setSelected(cvValue == 192);
                } else {
                    // not OK
                    KTUI.mbInvalidValue(this, currCV, cvValue, "8 16 32 64 136 144 160 192" );
                    if( debugLevel >= 1 ) {
                        System.out.println("jCV_InhaltFocusLost CV="+currCV+" Wert="+cvValue+" IGNORIERT");
                    }
                    return;
                }
                break;

            case 157: // Zuordnung Fkt-Tasten zu integrierten Sounds (Seite 40)
                if( isValidFktkeySoundMapping( cvValue ) ) {
                    //------------- F10 ------------------
                    jF10_TP.setSelected( cvValue ==   8);
                    jF10_SP.setSelected( cvValue ==  16);
                    jF10_SH.setSelected( cvValue ==  32);
                    jF10_Gl.setSelected( cvValue ==  64);
                    //------------- F22 ------------------
                    jF22_TP.setSelected(cvValue == 136);
                    jF22_SP.setSelected(cvValue == 144);
                    jF22_SH.setSelected(cvValue == 160);
                    jF22_Gl.setSelected(cvValue == 192);
                } else {
                    // not OK
                    KTUI.mbInvalidValue(this, currCV, cvValue, "8 16 32 64 136 144 160 192" );
                    if( debugLevel >= 1 ) {
                        System.out.println("jCV_InhaltFocusLost CV="+currCV+" Wert="+cvValue+" IGNORIERT");
                    }
                    return;
                }
                break;

            case 158: // Zuordnung Fkt-Tasten zu integrierten Sounds (Seite 40)
                if( isValidFktkeySoundMapping( cvValue ) ) {
                    //------------- F11 ------------------
                    jF11_TP.setSelected( cvValue ==   8);
                    jF11_SP.setSelected( cvValue ==  16);
                    jF11_SH.setSelected( cvValue ==  32);
                    jF11_Gl.setSelected( cvValue ==  64);
                    //------------- F23 ------------------
                    jF23_TP.setSelected(cvValue == 136);
                    jF23_SP.setSelected(cvValue == 144);
                    jF23_SH.setSelected(cvValue == 160);
                    jF23_Gl.setSelected(cvValue == 192);
                } else {
                    // not OK
                    KTUI.mbInvalidValue(this, currCV, cvValue, "8 16 32 64 136 144 160 192" );
                    if( debugLevel >= 1 ) {
                        System.out.println("jCV_InhaltFocusLost CV="+currCV+" Wert="+cvValue+" IGNORIERT");
                    }
                    return;
                }
                break;

            case 159: // Zuordnung Fkt-Tasten zu integrierten Sounds (Seite 40)
                if( isValidFktkeySoundMapping( cvValue ) ) {
                    //------------- F12 ------------------
                    jF12_TP.setSelected( cvValue ==   8);
                    jF12_SP.setSelected( cvValue ==  16);
                    jF12_SH.setSelected( cvValue ==  32);
                    jF12_Gl.setSelected( cvValue ==  64);
                    //------------- F24 ------------------
                    jF24_TP.setSelected(cvValue == 136);
                    jF24_SP.setSelected(cvValue == 144);
                    jF24_SH.setSelected(cvValue == 160);
                    jF24_Gl.setSelected(cvValue == 192);
                } else {
                    // not OK
                    KTUI.mbInvalidValue(this, currCV, cvValue, "8 16 32 64 136 144 160 192" );
                    if( debugLevel >= 1 ) {
                        System.out.println("jCV_InhaltFocusLost CV="+currCV+" Wert="+cvValue+" IGNORIERT");
                    }
                    return;
                }
                break;

            case 160: // Lautstärke
                jLaut.setText(s);
                jLautSlider.setValue(255 - cvValue);
                break;
 
            case 162: // Kickzeit vorwärts
                jKickvor.setText(s);
                break;
 
            case 163: // Kickzeit rückwärts
                jKickrueck.setText(s);
                break;
 
            case 167: // Zuordnung Servoausgang (IN3) zu Funktionstasten
                if( cvValue > 0) {
                    jIn3IsServo.setSelected(true);
                    CV[1][168] = 1;
                }
                //------------- Servo-Funktionen ------------------
                jServoF5.setSelected( (cvValue & 0x01) == 0x01);
                jServoF6.setSelected( (cvValue & 0x02) == 0x02);
                jServoF7.setSelected( (cvValue & 0x04) == 0x04);
                jServoF8.setSelected( (cvValue & 0x08) == 0x08);
                jServoF9.setSelected( (cvValue & 0x10) == 0x10);
                jServoF10.setSelected((cvValue & 0x20) == 0x20);
                jServoF11.setSelected((cvValue & 0x40) == 0x40);
                jServoF12.setSelected((cvValue & 0x80) == 0x80);
                break;
 
            case 168: // Festlegung der Funktionsweise von IN3 0=IN 1=Servo3
                if( cvValue == 0 ) {
                    CV[1][167] = 0;
                    jIn3IsServo.setSelected(false);
                    jServoF5.setSelected(false);
                    jServoF6.setSelected(false);
                    jServoF7.setSelected(false);
                    jServoF8.setSelected(false);
                    jServoF9.setSelected(false);
                    jServoF10.setSelected(false);
                    jServoF11.setSelected(false);
                    jServoF12.setSelected(false);
                } else {
                    jIn3IsServo.setSelected(true);
                }
                break;
 
            case 169: // Servo: linker Anschlag
                // Maximum ist Wert(rechterAnschlag) CV[1][170]
                // default MIN( 112, Wert(rechterAnschlag)
                int rechterAnschlag =  CV[1][170];
                int defValueLA = 112;
                if( rechterAnschlag < 112 ) {
                    defValueLA = rechterAnschlag;
                }
                cvValue = KTUI.checkTextField( this, jCV_Inhalt, 40, rechterAnschlag, defValueLA, true );
                s = jCV_Inhalt.getText();
                jlinkerAnschlag.setText(s);
                break;
 
            case 170: // Servo: rechter Anschlag
                // Minimum ist MIN( 40, Wert(linkerAnschlag) CV[1][170] )
                // default MAX( 176, Wert(linkerAnschlag)
                int linkerAnschlag = CV[1][169];
                int defValueRA = 176;
                if( linkerAnschlag > 176 ) {
                    defValueRA = linkerAnschlag;
                }
                cvValue = KTUI.checkTextField( this, jCV_Inhalt, linkerAnschlag, 250, defValueRA, true );
                s = jCV_Inhalt.getText();
                jrechterAnschlag.setText(s);
                break;
 
            case 171: // Servo: Geschwindigkeit
                jGeschwindigkeit.setText(s);
                break;
 
            case 172: // Servo: Ansteuerung über POM / Servoposition
                if( debugLevel >= 1 ) {
                    System.out.println("jCV_InhaltFocusLost CV="+currCV+" Wert="+cvValue+" IGNORIERT");
                }
                return;
 
            case 173: // FS0-Funktionen
                jFS0_F1.setSelected((cvValue & 0x01) == 0x01);
                jFS0_F2.setSelected((cvValue & 0x02) == 0x02);
                jFS0_F3.setSelected((cvValue & 0x04) == 0x04);
                jFS0_F4.setSelected((cvValue & 0x08) == 0x08);
                jFS0_F5.setSelected((cvValue & 0x10) == 0x10);
                jFS0_F6.setSelected((cvValue & 0x20) == 0x20);
                jFS0_F7.setSelected((cvValue & 0x40) == 0x40);
                jFS0_F8.setSelected((cvValue & 0x80) == 0x80);
                break;

            case 174: // Ausgänge EIN/AUS bei FS0-Funktionen
                cvValue = KTUI.checkTextField( this, jCV_Inhalt, 0, 63, 0, true );
                s = jCV_Inhalt.getText();
                jFS0_AUX1.setSelected((cvValue & 0x01) == 0x01);
                jFS0_AUX2.setSelected((cvValue & 0x02) == 0x02);
                jFS0_AUX3.setSelected((cvValue & 0x04) == 0x04);
                jFS0_AUX4.setSelected((cvValue & 0x08) == 0x08);
                jFS0_AUX5.setSelected((cvValue & 0x10) == 0x10);
                jFS0_AUX6.setSelected((cvValue & 0x20) == 0x20);
                break;
        
            case 175: // Ausgänge EIN/AUS bei FS0-Funktionen
                cvValue = KTUI.checkTextField( this, jCV_Inhalt, 0, 63, 0, true );
                s = jCV_Inhalt.getText();
                jFS0_AUX7.setSelected( (cvValue & 0x01) == 0x01);
                jFS0_AUX8.setSelected( (cvValue & 0x02) == 0x02);
                jFS0_AUX9.setSelected( (cvValue & 0x04) == 0x04);
                jFS0_AUX10.setSelected((cvValue & 0x08) == 0x08);
                jFS0_AUX11.setSelected((cvValue & 0x10) == 0x10);
                jFS0_AUX12.setSelected((cvValue & 0x20) == 0x20);
                break;
        
            case 176: // Invertieren der Ausgänge bei FS0
                cvValue = KTUI.checkTextField( this, jCV_Inhalt, 0, 63, 0, true );
                s = jCV_Inhalt.getText();
                jFS0_AUXinv1.setSelected((cvValue & 0x01) == 0x01);
                jFS0_AUXinv2.setSelected((cvValue & 0x02) == 0x02);
                jFS0_AUXinv3.setSelected((cvValue & 0x04) == 0x04);
                jFS0_AUXinv4.setSelected((cvValue & 0x08) == 0x08);
                jFS0_AUXinv5.setSelected((cvValue & 0x10) == 0x10);
                jFS0_AUXinv6.setSelected((cvValue & 0x20) == 0x20);
                break;

            case 180: // F13
                cvValue = KTUI.checkTextField( this, jCV_Inhalt, 0, 63, 0, true );
                s = jCV_Inhalt.getText();
                jF13_1.setSelected((cvValue & 0x01) == 0x01 );
                jF13_2.setSelected((cvValue & 0x02) == 0x02 );
                jF13_3.setSelected((cvValue & 0x04) == 0x04 );
                jF13_4.setSelected((cvValue & 0x08) == 0x08 );
                jF13_5.setSelected((cvValue & 0x10) == 0x10 );
                jF13_6.setSelected((cvValue & 0x20) == 0x20 );
                break;

            case 181: // F14
                cvValue = KTUI.checkTextField( this, jCV_Inhalt, 0, 63, 0, true );
                s = jCV_Inhalt.getText();
                jF14_1.setSelected((cvValue & 0x01) == 0x01 );
                jF14_2.setSelected((cvValue & 0x02) == 0x02 );
                jF14_3.setSelected((cvValue & 0x04) == 0x04 );
                jF14_4.setSelected((cvValue & 0x08) == 0x08 );
                jF14_5.setSelected((cvValue & 0x10) == 0x10 );
                jF14_6.setSelected((cvValue & 0x20) == 0x20 );
                break;

            case 182: // F15
                cvValue = KTUI.checkTextField( this, jCV_Inhalt, 0, 63, 0, true );
                s = jCV_Inhalt.getText();
                jF15_1.setSelected((cvValue & 0x01) == 0x01 );
                jF15_2.setSelected((cvValue & 0x02) == 0x02 );
                jF15_3.setSelected((cvValue & 0x04) == 0x04 );
                jF15_4.setSelected((cvValue & 0x08) == 0x08 );
                jF15_5.setSelected((cvValue & 0x10) == 0x10 );
                jF15_6.setSelected((cvValue & 0x20) == 0x20 );
                break;

            case 183: // F16
                cvValue = KTUI.checkTextField( this, jCV_Inhalt, 0, 63, 0, true );
                s = jCV_Inhalt.getText();
                jF16_1.setSelected((cvValue & 0x01) == 0x01 );
                jF16_2.setSelected((cvValue & 0x02) == 0x02 );
                jF16_3.setSelected((cvValue & 0x04) == 0x04 );
                jF16_4.setSelected((cvValue & 0x08) == 0x08 );
                jF16_5.setSelected((cvValue & 0x10) == 0x10 );
                jF16_6.setSelected((cvValue & 0x20) == 0x20 );
                break;

            case 184: // F17
                cvValue = KTUI.checkTextField( this, jCV_Inhalt, 0, 63, 0, true );
                s = jCV_Inhalt.getText();
                jF17_1.setSelected((cvValue & 0x01) == 0x01 );
                jF17_2.setSelected((cvValue & 0x02) == 0x02 );
                jF17_3.setSelected((cvValue & 0x04) == 0x04 );
                jF17_4.setSelected((cvValue & 0x08) == 0x08 );
                jF17_5.setSelected((cvValue & 0x10) == 0x10 );
                jF17_6.setSelected((cvValue & 0x20) == 0x20 );
                break;

            case 185: // F18
                cvValue = KTUI.checkTextField( this, jCV_Inhalt, 0, 63, 0, true );
                s = jCV_Inhalt.getText();
                jF18_1.setSelected((cvValue & 0x01) == 0x01 );
                jF18_2.setSelected((cvValue & 0x02) == 0x02 );
                jF18_3.setSelected((cvValue & 0x04) == 0x04 );
                jF18_4.setSelected((cvValue & 0x08) == 0x08 );
                jF18_5.setSelected((cvValue & 0x10) == 0x10 );
                jF18_6.setSelected((cvValue & 0x20) == 0x20 );
                break;

            case 186: // F19
                cvValue = KTUI.checkTextField( this, jCV_Inhalt, 0, 63, 0, true );
                s = jCV_Inhalt.getText();
                jF19_1.setSelected((cvValue & 0x01) == 0x01 );
                jF19_2.setSelected((cvValue & 0x02) == 0x02 );
                jF19_3.setSelected((cvValue & 0x04) == 0x04 );
                jF19_4.setSelected((cvValue & 0x08) == 0x08 );
                jF19_5.setSelected((cvValue & 0x10) == 0x10 );
                jF19_6.setSelected((cvValue & 0x20) == 0x20 );
                break;

            case 187: // F20
                cvValue = KTUI.checkTextField( this, jCV_Inhalt, 0, 63, 0, true );
                s = jCV_Inhalt.getText();
                jF20_1.setSelected((cvValue & 0x01) == 0x01 );
                jF20_2.setSelected((cvValue & 0x02) == 0x02 );
                jF20_3.setSelected((cvValue & 0x04) == 0x04 );
                jF20_4.setSelected((cvValue & 0x08) == 0x08 );
                jF20_5.setSelected((cvValue & 0x10) == 0x10 );
                jF20_6.setSelected((cvValue & 0x20) == 0x20 );
                break;

            case 188: // F21
                cvValue = KTUI.checkTextField( this, jCV_Inhalt, 0, 63, 0, true );
                s = jCV_Inhalt.getText();
                jF21_1.setSelected((cvValue & 0x01) == 0x01 );
                jF21_2.setSelected((cvValue & 0x02) == 0x02 );
                jF21_3.setSelected((cvValue & 0x04) == 0x04 );
                jF21_4.setSelected((cvValue & 0x08) == 0x08 );
                jF21_5.setSelected((cvValue & 0x10) == 0x10 );
                jF21_6.setSelected((cvValue & 0x20) == 0x20 );
                break;

            case 189: // F22
                cvValue = KTUI.checkTextField( this, jCV_Inhalt, 0, 63, 0, true );
                s = jCV_Inhalt.getText();
                jF22_1.setSelected((cvValue & 0x01) == 0x01 );
                jF22_2.setSelected((cvValue & 0x02) == 0x02 );
                jF22_3.setSelected((cvValue & 0x04) == 0x04 );
                jF22_4.setSelected((cvValue & 0x08) == 0x08 );
                jF22_5.setSelected((cvValue & 0x10) == 0x10 );
                jF22_6.setSelected((cvValue & 0x20) == 0x20 );
                break;

            case 190: // F23
                cvValue = KTUI.checkTextField( this, jCV_Inhalt, 0, 63, 0, true );
                s = jCV_Inhalt.getText();
                jF23_1.setSelected((cvValue & 0x01) == 0x01 );
                jF23_2.setSelected((cvValue & 0x02) == 0x02 );
                jF23_3.setSelected((cvValue & 0x04) == 0x04 );
                jF23_4.setSelected((cvValue & 0x08) == 0x08 );
                jF23_5.setSelected((cvValue & 0x10) == 0x10 );
                jF23_6.setSelected((cvValue & 0x20) == 0x20 );
                break;

            case 191: // F24
                cvValue = KTUI.checkTextField( this, jCV_Inhalt, 0, 63, 0, true );
                s = jCV_Inhalt.getText();
                jF24_1.setSelected((cvValue & 0x01) == 0x01 );
                jF24_2.setSelected((cvValue & 0x02) == 0x02 );
                jF24_3.setSelected((cvValue & 0x04) == 0x04 );
                jF24_4.setSelected((cvValue & 0x08) == 0x08 );
                jF24_5.setSelected((cvValue & 0x10) == 0x10 );
                jF24_6.setSelected((cvValue & 0x20) == 0x20 );
                break;

            case 192: // F25
                cvValue = KTUI.checkTextField( this, jCV_Inhalt, 0, 63, 0, true );
                s = jCV_Inhalt.getText();
                jF25_1.setSelected((cvValue & 0x01) == 0x01 );
                jF25_2.setSelected((cvValue & 0x02) == 0x02 );
                jF25_3.setSelected((cvValue & 0x04) == 0x04 );
                jF25_4.setSelected((cvValue & 0x08) == 0x08 );
                jF25_5.setSelected((cvValue & 0x10) == 0x10 );
                jF25_6.setSelected((cvValue & 0x20) == 0x20 );
                break;

            case 193: // F26
                cvValue = KTUI.checkTextField( this, jCV_Inhalt, 0, 63, 0, true );
                s = jCV_Inhalt.getText();
                jF26_1.setSelected((cvValue & 0x01) == 0x01 );
                jF26_2.setSelected((cvValue & 0x02) == 0x02 );
                jF26_3.setSelected((cvValue & 0x04) == 0x04 );
                jF26_4.setSelected((cvValue & 0x08) == 0x08 );
                jF26_5.setSelected((cvValue & 0x10) == 0x10 );
                jF26_6.setSelected((cvValue & 0x20) == 0x20 );
                break;

            case 194: // F27
                cvValue = KTUI.checkTextField( this, jCV_Inhalt, 0, 63, 0, true );
                s = jCV_Inhalt.getText();
                jF27_1.setSelected((cvValue & 0x01) == 0x01 );
                jF27_2.setSelected((cvValue & 0x02) == 0x02 );
                jF27_3.setSelected((cvValue & 0x04) == 0x04 );
                jF27_4.setSelected((cvValue & 0x08) == 0x08 );
                jF27_5.setSelected((cvValue & 0x10) == 0x10 );
                jF27_6.setSelected((cvValue & 0x20) == 0x20 );
                break;

            case 195: // F28
                cvValue = KTUI.checkTextField( this, jCV_Inhalt, 0, 63, 0, true );
                s = jCV_Inhalt.getText();
                jF28_1.setSelected((cvValue & 0x01) == 0x01 );
                jF28_2.setSelected((cvValue & 0x02) == 0x02 );
                jF28_3.setSelected((cvValue & 0x04) == 0x04 );
                jF28_4.setSelected((cvValue & 0x08) == 0x08 );
                jF28_5.setSelected((cvValue & 0x10) == 0x10 );
                jF28_6.setSelected((cvValue & 0x20) == 0x20 );
                break;

            default:
                if( debugLevel >= 1 ) {
                    System.out.println("jCV_InhaltFocusLost CV="+currCV+" Wert="+cvValue+" IGNORIERT");
                }
                return;
                
        }
        if( cvValue == -1 ) {
            if( debugLevel >= 1 ) {
                System.out.println("jCV_InhaltFocusLost CV="+currCV+" Wert=\""+oriEingabe+"\" IGNORIERT");
            }
            jCV_Inhalt.setText(oriEingabe);
            getDefaultToolkit().beep();
            jCV_Inhalt.grabFocus();
        } else {
            CV[1][currCV] = cvValue;
           jCV_Inhalt.setText("" + cvValue);
        }
    }//GEN-LAST:event_jCV_InhaltFocusLost

    private boolean isValidFktkeySoundMapping( int value ) {
        // Zuordnung Fkt-Tasten zu integrierten Sounds (Seite 40)
        // nur ein Sound  pro Fkt-Tastenpaar (F1/F13 .. F12/F24) ist zugelassen
        // gültige Werte, 8,16,32,64 oder 136, 144,160,192
        switch( value) {
            case   8:
            case  16:
            case  32:
            case  64:
            case 136:
            case 144:
            case 160:
            case 192:
                // OK
                return true;
        }
        return false;
    }

    private void jCV_InhaltKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCV_InhaltKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jCV_InhaltKeyReleased

    private void jCV_AnzeigeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCV_AnzeigeActionPerformed
        int currCV = getCVfromIndexString(jCV_Anzeige, "CV#");
        jCV_Inhalt.setText("" + CV[1][currCV]);
    }//GEN-LAST:event_jCV_AnzeigeActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        DecTest dt = new DecTest(this, true, KTUI);
        dt.jDecType.setText("Decoder: FD-R extended");
        if(jKurzeAdr.isSelected()) {
            dt.DecAddr = CV[1][1];
        } else {
            int n = (CV[1][17] - 192)*256 + CV[1][18];
            dt.DecAddr = n;
        }
        dt.setLocationRelativeTo(this);
        dt.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jOpenActionPerformed
        SaveOpenDialog od = new SaveOpenDialog( this, true, true, CVs, this, "fde");
        updateTabs();
    }//GEN-LAST:event_jOpenActionPerformed

    private void jlangeAdrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jlangeAdrActionPerformed
        int j = (CV[1][17] - 192)*256 + CV[1][18];
        if (j < 128 || j > 10239)
        {
            KTUI.mbValueNaN( this, 128, 10239, true);
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

    private void jSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSaveActionPerformed
        // Alle CVs werden in einer Datei gespeichert
        CVs = "FD-R extended\r\n";
        CVs += "Version 1.1\r\n";
        for(int i = 0; i < CV[0].length; i++) {
            if( CV[0][i] > 0 ) { // only write used CVs (CV[0][cv] != 0 ) to file
                CVs += "\r\nCV#" + CV[0][i] + " = " + CV[1][i];
            }
        }
        CVs += "\r\n\r\nKommentar:\r\n";
        CVs += jComment.getText();
        SaveOpenDialog od = new SaveOpenDialog( this, true, false, CVs, this, "fde");
    }//GEN-LAST:event_jSaveActionPerformed

    private void jDecoderlesenSchreibenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDecoderlesenSchreibenActionPerformed
        try {
            // Hier wird die Schnittstelle geöffnet und die CVs werden zur Zentrale gesendet
            ReadWriteCV cvwr = new ReadWriteCV(this, true, KTUI, CV);
        } catch (IOException ex) {
            KTUI.mbDeviceReadProblem( this );
        }
    }//GEN-LAST:event_jDecoderlesenSchreibenActionPerformed

    private void jDirekteingabeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDirekteingabeActionPerformed
        // hier wird die Direkteingabe (de)aktiviert
        String str = jDirekteingabe.getText();
        if(str == "Direkt ein") {
            jCV_Inhalt.setEditable(true);
            str = "Direkt aus";
            jDirekteingabe.setText(str);
        } else {
            jCV_Inhalt.setEditable(false);
            str = "Direkt ein";
            jDirekteingabe.setText(str);
        }
        jCV_Inhalt.validate();
        jDirekteingabe.validate();
    }//GEN-LAST:event_jDirekteingabeActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        KTUI.frameInstanceDEVICE = null;
        KTUI.setFocus();
    }//GEN-LAST:event_formWindowClosed

    private void jF23_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF23_5ActionPerformed
        if(jF23_4.isSelected()) {
            CV[1][190] |= 0x10;
        } else {
            CV[1][190] &= ~0x10;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+190 );
    }//GEN-LAST:event_jF23_5ActionPerformed

    private void jF24_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF24_4ActionPerformed
        if(jF24_4.isSelected()) {
            CV[1][191] |= 8;
        } else {
            CV[1][191] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+191 );
    }//GEN-LAST:event_jF24_4ActionPerformed

    private void jF24_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF24_1ActionPerformed
        if(jF24_1.isSelected()) {
            CV[1][191] |= 1;
        } else {
            CV[1][191] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+191 );
    }//GEN-LAST:event_jF24_1ActionPerformed

    private void jF24_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF24_2ActionPerformed
        if(jF24_2.isSelected()) {
            CV[1][191] |= 2;
        } else {
            CV[1][191] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+191 );
    }//GEN-LAST:event_jF24_2ActionPerformed

    private void jF24_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF24_3ActionPerformed
        if(jF24_3.isSelected()) {
            CV[1][191] |= 4;
        } else {
            CV[1][191] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+191 );
    }//GEN-LAST:event_jF24_3ActionPerformed

    private void jF24_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF24_6ActionPerformed
        if(jF24_6.isSelected()) {
            CV[1][191] |= 0x20;
        } else {
            CV[1][191] &= ~0x20;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+191 );
    }//GEN-LAST:event_jF24_6ActionPerformed

    private void jF24_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF24_5ActionPerformed
        if(jF24_5.isSelected()) {
            CV[1][191] |= 0x10;
        } else {
            CV[1][191] &= ~0x10;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+191 );
    }//GEN-LAST:event_jF24_5ActionPerformed

    private void jF25_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF25_5ActionPerformed
        if(jF25_5.isSelected()) {
            CV[1][192] |= 0x10;
        } else {
            CV[1][192] &= ~0x10;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+192 );
    }//GEN-LAST:event_jF25_5ActionPerformed

    private void jF25_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF25_2ActionPerformed
        if(jF25_2.isSelected()) {
            CV[1][192] |= 2;
        } else {
            CV[1][192] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+192 );
    }//GEN-LAST:event_jF25_2ActionPerformed

    private void jF25_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF25_1ActionPerformed
        if(jF25_1.isSelected()) {
            CV[1][192] |= 1;
        } else {
            CV[1][192] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+192 );
    }//GEN-LAST:event_jF25_1ActionPerformed

    private void jF25_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF25_4ActionPerformed
        if(jF25_4.isSelected()) {
            CV[1][192] |= 8;
        } else {
            CV[1][192] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+192 );
    }//GEN-LAST:event_jF25_4ActionPerformed

    private void jF25_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF25_6ActionPerformed
        if(jF25_6.isSelected()) {
            CV[1][192] |= 0x20;
        } else {
            CV[1][192] &= ~0x20;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+192 );
    }//GEN-LAST:event_jF25_6ActionPerformed

    private void jF25_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF25_3ActionPerformed
        if(jF25_3.isSelected()) {
            CV[1][192] |= 4;
        } else {
            CV[1][192] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+192 );
    }//GEN-LAST:event_jF25_3ActionPerformed

    private void jF26_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF26_6ActionPerformed
        if(jF26_6.isSelected()) {
            CV[1][193] |= 0x20;
        } else {
            CV[1][193] &= ~0x20;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+193 );
    }//GEN-LAST:event_jF26_6ActionPerformed

    private void jF26_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF26_2ActionPerformed
        if(jF26_2.isSelected()) {
            CV[1][193] |= 2;
        } else {
            CV[1][193] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+193 );
    }//GEN-LAST:event_jF26_2ActionPerformed

    private void jF26_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF26_5ActionPerformed
        if(jF26_5.isSelected()) {
            CV[1][193] |= 0x10;
        } else {
            CV[1][193] &= ~0x10;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+193 );
    }//GEN-LAST:event_jF26_5ActionPerformed

    private void jF26_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF26_1ActionPerformed
        if(jF26_1.isSelected()) {
            CV[1][193] |= 1;
        } else {
            CV[1][193] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+193 );
    }//GEN-LAST:event_jF26_1ActionPerformed

    private void jF26_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF26_3ActionPerformed
        if(jF26_3.isSelected()) {
            CV[1][193] |= 4;
        } else {
            CV[1][193] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+193 );
    }//GEN-LAST:event_jF26_3ActionPerformed

    private void jF26_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF26_4ActionPerformed
        if(jF26_4.isSelected()) {
            CV[1][193] |= 8;
        } else {
            CV[1][193] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+193 );
    }//GEN-LAST:event_jF26_4ActionPerformed

    private void jF27_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF27_1ActionPerformed
        if(jF27_1.isSelected()) {
            CV[1][194] |= 1;
        } else {
            CV[1][194] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+194 );
    }//GEN-LAST:event_jF27_1ActionPerformed

    private void jF27_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF27_5ActionPerformed
        if(jF27_5.isSelected()) {
            CV[1][194] |= 0x10;
        } else {
            CV[1][194] &= ~0x10;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+194 );
    }//GEN-LAST:event_jF27_5ActionPerformed

    private void jF27_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF27_4ActionPerformed
        if(jF27_4.isSelected()) {
            CV[1][194] |= 8;
        } else {
            CV[1][194] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+194 );
    }//GEN-LAST:event_jF27_4ActionPerformed

    private void jF27_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF27_6ActionPerformed
        if(jF27_6.isSelected()) {
            CV[1][194] |= 0x20;
        } else {
            CV[1][194] &= ~0x20;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+194 );
    }//GEN-LAST:event_jF27_6ActionPerformed

    private void jF27_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF27_3ActionPerformed
        if(jF27_3.isSelected()) {
            CV[1][194] |= 4;
        } else {
            CV[1][194] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+194 );
    }//GEN-LAST:event_jF27_3ActionPerformed

    private void jF27_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF27_2ActionPerformed
        if(jF27_2.isSelected()) {
            CV[1][194] |= 2;
        } else {
            CV[1][194] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+194 );
    }//GEN-LAST:event_jF27_2ActionPerformed

    private void jF28_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF28_3ActionPerformed
        if(jF28_3.isSelected()) {
            CV[1][195] |= 4;
        } else {
            CV[1][195] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+195 );
    }//GEN-LAST:event_jF28_3ActionPerformed

    private void jF28_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF28_6ActionPerformed
        if(jF28_6.isSelected()) {
            CV[1][195] |= 0x20;
        } else {
            CV[1][195] &= ~0x20;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+195 );
    }//GEN-LAST:event_jF28_6ActionPerformed

    private void jF28_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF28_5ActionPerformed
        if(jF28_5.isSelected()) {
            CV[1][195] |= 0x10;
        } else {
            CV[1][195] &= ~0x10;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+195 );
    }//GEN-LAST:event_jF28_5ActionPerformed

    private void jF28_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF28_1ActionPerformed
        if(jF28_1.isSelected()) {
            CV[1][195] |= 1;
        } else {
            CV[1][195] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+195 );
    }//GEN-LAST:event_jF28_1ActionPerformed

    private void jF28_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF28_2ActionPerformed
        if(jF28_2.isSelected()) {
            CV[1][195] |= 2;
        } else {
            CV[1][195] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+195 );
    }//GEN-LAST:event_jF28_2ActionPerformed

    private void jF28_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF28_4ActionPerformed
        if(jF28_4.isSelected()) {
            CV[1][195] |= 8;
        } else {
            CV[1][195] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+195 );
    }//GEN-LAST:event_jF28_4ActionPerformed

    private void jPacketTimeOutFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPacketTimeOutFocusLost
        CV[1][11] = KTUI.checkTextField( this, jPacketTimeOut, 0, 255, 5, true );
        jCV_Anzeige.setSelectedItem( "CV#"+11 );
    }//GEN-LAST:event_jPacketTimeOutFocusLost

    private void jPacketTimeOutKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPacketTimeOutKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jPacketTimeOutKeyReleased

    private void jAuxInv6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAuxInv6ActionPerformed
        if(jAuxInv6.isSelected()) {
            CV[1][58] |= 4;
        } else {
            CV[1][58] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+58 );
    }//GEN-LAST:event_jAuxInv6ActionPerformed

    private void jAuxInv2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAuxInv2ActionPerformed
        if(jAuxInv2.isSelected()) {
            CV[1][54] |= 4;
        } else {
            CV[1][54] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+54 );
    }//GEN-LAST:event_jAuxInv2ActionPerformed

    private void jAuxInv1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAuxInv1ActionPerformed
        if(jAuxInv1.isSelected()) {
            CV[1][53] |= 4;
        } else {
            CV[1][53] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+53 );
    }//GEN-LAST:event_jAuxInv1ActionPerformed

    private void jAuxInv3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAuxInv3ActionPerformed
        if(jAuxInv3.isSelected()) {
            CV[1][55] |= 4;
        } else {
            CV[1][55] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+55 );
    }//GEN-LAST:event_jAuxInv3ActionPerformed

    private void jAuxInv4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAuxInv4ActionPerformed
        if(jAuxInv4.isSelected()) {
            CV[1][56] |= 4;
        } else {
            CV[1][56] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+56 );
    }//GEN-LAST:event_jAuxInv4ActionPerformed

    private void jAuxInv5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAuxInv5ActionPerformed
        if(jAuxInv5.isSelected()) {
            CV[1][57] |= 4;
        } else {
            CV[1][57] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+57 );
    }//GEN-LAST:event_jAuxInv5ActionPerformed

    private void jBl_Inv1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBl_Inv1ActionPerformed
        if(jBl_Inv1.isSelected()) {
            CV[1][53] |= 8;
        } else {
            CV[1][53] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+53 );
    }//GEN-LAST:event_jBl_Inv1ActionPerformed

    private void jBl_Inv2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBl_Inv2ActionPerformed
        if(jBl_Inv2.isSelected()) {
            CV[1][54] |= 8;
        } else {
            CV[1][54] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+54 );
    }//GEN-LAST:event_jBl_Inv2ActionPerformed

    private void jBl_Inv3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBl_Inv3ActionPerformed
        if(jBl_Inv3.isSelected()) {
            CV[1][55] |= 8;
        } else {
            CV[1][55] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+55 );
    }//GEN-LAST:event_jBl_Inv3ActionPerformed

    private void jBl_Inv4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBl_Inv4ActionPerformed
        if(jBl_Inv4.isSelected()) {
            CV[1][56] |= 8;
        } else {
            CV[1][56] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+56 );
    }//GEN-LAST:event_jBl_Inv4ActionPerformed

    private void jBl_Inv5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBl_Inv5ActionPerformed
        if(jBl_Inv5.isSelected()) {
            CV[1][57] |= 8;
        } else {
            CV[1][57] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+57 );
    }//GEN-LAST:event_jBl_Inv5ActionPerformed

    private void jBl_Inv6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBl_Inv6ActionPerformed
        if(jBl_Inv6.isSelected()) {
            CV[1][58] |= 8;
        } else {
            CV[1][58] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+58 );
    }//GEN-LAST:event_jBl_Inv6ActionPerformed

    private void jBlink_Pausezeit_5FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlink_Pausezeit_5FocusLost
        int max = 255; // Handbuch
        max = CV[1][110] - ( CV[1][136] * 2 ); // Abhängigkeiten
        if( ! KTUI.checkNumRange(jBlink_Pausezeit_5.getText(), 0, max) ) {
            KTUI.mbBlinkPausezeit( this, CV[1][110], CV[1][136], jBlink_Pausezeit_5.getText() );
            jBlink_Pausezeit_5.grabFocus();
            return;
        }
        // Wert ist bereits geprüft. also "still" übernehmen (view -> false)
        CV[1][144] = KTUI.checkTextField( this, jBlink_Pausezeit_5, 0, max, 2, false);
        jCV_Anzeige.setSelectedItem( "CV#"+144 );
    }//GEN-LAST:event_jBlink_Pausezeit_5FocusLost

    private void jBlink_Pausezeit_5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBlink_Pausezeit_5KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jBlink_Pausezeit_5KeyReleased

    private void jBlink_Pausezeit_6FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlink_Pausezeit_6FocusLost
        int max = 255; // Handbuch
        max = CV[1][111] - ( CV[1][137] * 2 ); // Abhängigkeiten
        if( ! KTUI.checkNumRange(jBlink_Pausezeit_6.getText(), 0, max) ) {
            KTUI.mbBlinkPausezeit( this, CV[1][111], CV[1][137], jBlink_Pausezeit_6.getText() );
            jBlink_Pausezeit_6.grabFocus();
            return;
        }
        // Wert ist bereits geprüft. also "still" übernehmen (view -> false)
        CV[1][145] = KTUI.checkTextField( this, jBlink_Pausezeit_6, 0, max, 2, false);
        jCV_Anzeige.setSelectedItem( "CV#"+145 );
    }//GEN-LAST:event_jBlink_Pausezeit_6FocusLost

    private void jBlink_Pausezeit_6KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBlink_Pausezeit_6KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jBlink_Pausezeit_6KeyReleased

    private void jBlinkfrequenz3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlinkfrequenz3FocusLost
        int min = ( CV[1][134] *2 ) + CV[1][142] ; // Abhängigkeiten
        if( min < 10 ) {
            min = 10; // Handbuch
        }
        if( ! KTUI.checkNumRange(jBlinkfrequenz3.getText(), min, 255) ) {
            KTUI.mbBlinkFrequenz( this, CV[1][134], CV[1][142], jBlinkfrequenz3.getText() );
            jBlinkfrequenz3.grabFocus();
            return;
        }
        // Wert ist bereits geprüft. also "still" übernehmen (view -> false)
        CV[1][108] = KTUI.checkTextField( this, jBlinkfrequenz3, min, 255, 20, false);
        jCV_Anzeige.setSelectedItem( "CV#"+108 );
    }//GEN-LAST:event_jBlinkfrequenz3FocusLost

    private void jBlinkfrequenz3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBlinkfrequenz3KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jBlinkfrequenz3KeyReleased

    private void jBlinkfrequenz4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlinkfrequenz4FocusLost
        int min = ( CV[1][135] *2 ) + CV[1][143] ; // Abhängigkeiten
        if( min < 10 ) {
            min = 10; // Handbuch
        }
        if( ! KTUI.checkNumRange(jBlinkfrequenz4.getText(), min, 255) ) {
            KTUI.mbBlinkFrequenz( this, CV[1][135], CV[1][143], jBlinkfrequenz4.getText() );
            jBlinkfrequenz4.grabFocus();
            return;
        }
        // Wert ist bereits geprüft. also "still" übernehmen (view -> false)
        CV[1][109] = KTUI.checkTextField( this, jBlinkfrequenz4, min, 255, 20, false);
        jCV_Anzeige.setSelectedItem( "CV#"+109 );
    }//GEN-LAST:event_jBlinkfrequenz4FocusLost

    private void jBlinkfrequenz4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBlinkfrequenz4KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jBlinkfrequenz4KeyReleased

    private void jBlinkfrequenz1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlinkfrequenz1FocusLost
        int min = ( CV[1][132] *2 ) + CV[1][140] ; // Abhängigkeiten
        if( min < 10 ) {
            min = 10; // Handbuch
        }
        if( ! KTUI.checkNumRange(jBlinkfrequenz1.getText(), min, 255) ) {
            KTUI.mbBlinkFrequenz( this, CV[1][132], CV[1][140], jBlinkfrequenz1.getText() );
            jBlinkfrequenz1.grabFocus();
            return;
        }
        // Wert ist bereits geprüft. also "still" übernehmen (view -> false)
        CV[1][106] = KTUI.checkTextField( this, jBlinkfrequenz1, min, 255, 20, false);
        jCV_Anzeige.setSelectedItem( "CV#"+106 );
    }//GEN-LAST:event_jBlinkfrequenz1FocusLost

    private void jBlinkfrequenz1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBlinkfrequenz1KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jBlinkfrequenz1KeyReleased

    private void jBlinkfrequenz2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlinkfrequenz2FocusLost
        int min = ( CV[1][133] *2 ) + CV[1][141] ; // Abhängigkeiten
        if( min < 10 ) {
            min = 10; // Handbuch
        }
        if( ! KTUI.checkNumRange(jBlinkfrequenz2.getText(), min, 255) ) {
            KTUI.mbBlinkFrequenz( this, CV[1][133], CV[1][141], jBlinkfrequenz2.getText() );
            jBlinkfrequenz2.grabFocus();
            return;
        }
        // Wert ist bereits geprüft. also "still" übernehmen (view -> false)
        CV[1][107] = KTUI.checkTextField( this, jBlinkfrequenz2, min, 255, 20, false);
        jCV_Anzeige.setSelectedItem( "CV#"+107 );
    }//GEN-LAST:event_jBlinkfrequenz2FocusLost

    private void jBlinkfrequenz2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBlinkfrequenz2KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jBlinkfrequenz2KeyReleased

    private void jBlinkfrequenz5FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlinkfrequenz5FocusLost
        int min = ( CV[1][136] *2 ) + CV[1][144] ; // Abhängigkeiten
        if( min < 10 ) {
            min = 10; // Handbuch
        }
        if( ! KTUI.checkNumRange(jBlinkfrequenz5.getText(), min, 255) ) {
            KTUI.mbBlinkFrequenz( this, CV[1][136], CV[1][144], jBlinkfrequenz5.getText() );
            jBlinkfrequenz5.grabFocus();
            return;
        }
        // Wert ist bereits geprüft. also "still" übernehmen (view -> false)
        CV[1][110] = KTUI.checkTextField( this, jBlinkfrequenz5, min, 255, 20, false);
        jCV_Anzeige.setSelectedItem( "CV#"+110 );
    }//GEN-LAST:event_jBlinkfrequenz5FocusLost

    private void jBlinkfrequenz5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBlinkfrequenz5KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jBlinkfrequenz5KeyReleased

    private void jBlinkfrequenz6FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlinkfrequenz6FocusLost
        int min = ( CV[1][137] *2 ) + CV[1][145] ; // Abhängigkeiten
        if( min < 10 ) {
            min = 10; // Handbuch
        }
        if( ! KTUI.checkNumRange(jBlinkfrequenz6.getText(), min, 255) ) {
            KTUI.mbBlinkFrequenz( this, CV[1][137], CV[1][145], jBlinkfrequenz6.getText() );
            jBlinkfrequenz6.grabFocus();
            return;
        }
        // Wert ist bereits geprüft. also "still" übernehmen (view -> false)
        CV[1][111] = KTUI.checkTextField( this, jBlinkfrequenz6, min, 255, 20, false);
        jCV_Anzeige.setSelectedItem( "CV#"+111 );
    }//GEN-LAST:event_jBlinkfrequenz6FocusLost

    private void jBlinkfrequenz6KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBlinkfrequenz6KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jBlinkfrequenz6KeyReleased

    private void jDimmen1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmen1FocusLost
        CV[1][115] = KTUI.checkTextField( this, jDimmen1, 1, 255, 255, true);
        jCV_Anzeige.setSelectedItem( "CV#"+115 );
    }//GEN-LAST:event_jDimmen1FocusLost

    private void jDimmen1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDimmen1KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jDimmen1KeyReleased

    private void jDimmen2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmen2FocusLost
        CV[1][116] = KTUI.checkTextField( this, jDimmen2, 1, 255, 255, true);
        jCV_Anzeige.setSelectedItem( "CV#"+116 );
    }//GEN-LAST:event_jDimmen2FocusLost

    private void jDimmen2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDimmen2KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jDimmen2KeyReleased

    private void jDimmen3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmen3FocusLost
        CV[1][117] = KTUI.checkTextField( this, jDimmen3, 1, 255, 255, true);
        jCV_Anzeige.setSelectedItem( "CV#"+117 );
    }//GEN-LAST:event_jDimmen3FocusLost

    private void jDimmen3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDimmen3KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jDimmen3KeyReleased

    private void jRangier6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRangier6ActionPerformed
        if(jRangier6.isSelected()) {
            CV[1][131] |= 32;
        } else {
            CV[1][131] &= ~32;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+131 );
    }//GEN-LAST:event_jRangier6ActionPerformed

    private void jKickrueckFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jKickrueckFocusLost
        CV[1][163] = KTUI.checkTextField( this, jKickrueck, 0, 255, 0, true);
        jCV_Anzeige.setSelectedItem( "CV#"+163 );
    }//GEN-LAST:event_jKickrueckFocusLost

    private void jKickrueckKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jKickrueckKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jKickrueckKeyReleased

    private void jKickvorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jKickvorFocusLost
        CV[1][162] = KTUI.checkTextField( this, jKickvor, 0, 255, 0, true);
        jCV_Anzeige.setSelectedItem( "CV#"+162 );
    }//GEN-LAST:event_jKickvorFocusLost

    private void jKickvorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jKickvorKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jKickvorKeyReleased

    private void jbDimmFS2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbDimmFS2ActionPerformed
        if(jbDimmFS2.isSelected()) {
            CV[1][114] |= 2;
        } else {
            CV[1][114] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+114 );
    }//GEN-LAST:event_jbDimmFS2ActionPerformed

    private void jbDimmFS1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbDimmFS1ActionPerformed
        if(jbDimmFS1.isSelected()) {
            CV[1][114] |= 1;
        } else {
            CV[1][114] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+114 );
    }//GEN-LAST:event_jbDimmFS1ActionPerformed

    private void jbDimmFS3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbDimmFS3ActionPerformed
        if(jbDimmFS3.isSelected()) {
            CV[1][114] |= 4;
        } else {
            CV[1][114] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+114 );
    }//GEN-LAST:event_jbDimmFS3ActionPerformed

    private void jbDimmFS4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbDimmFS4ActionPerformed
        if(jbDimmFS4.isSelected()) {
            CV[1][114] |= 8;
        } else {
            CV[1][114] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+114 );
    }//GEN-LAST:event_jbDimmFS4ActionPerformed

    private void jRangier1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRangier1ActionPerformed
        if(jRangier1.isSelected()) {
            CV[1][131] |= 1;
        } else {
            CV[1][131] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+131 );
    }//GEN-LAST:event_jRangier1ActionPerformed

    private void jRangier2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRangier2ActionPerformed
        if(jRangier2.isSelected()) {
            CV[1][131] |= 2;
        } else {
            CV[1][131] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+131 );
    }//GEN-LAST:event_jRangier2ActionPerformed

    private void jRangier3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRangier3ActionPerformed
        if(jRangier3.isSelected()) {
            CV[1][131] |= 4;
        } else {
            CV[1][131] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+131 );
    }//GEN-LAST:event_jRangier3ActionPerformed

    private void jRangier4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRangier4ActionPerformed
        if(jRangier4.isSelected()) {
            CV[1][131] |= 8;
        } else {
            CV[1][131] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+131 );
    }//GEN-LAST:event_jRangier4ActionPerformed

    private void jRangier5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRangier5ActionPerformed
        if(jRangier5.isSelected()) {
            CV[1][131] |= 16;
        } else {
            CV[1][131] &= ~16;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+131 );
    }//GEN-LAST:event_jRangier5ActionPerformed

    private void jDimmen4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmen4FocusLost
        CV[1][118] = KTUI.checkTextField( this, jDimmen4, 1, 255, 255, true);
        jCV_Anzeige.setSelectedItem( "CV#"+118 );
    }//GEN-LAST:event_jDimmen4FocusLost

    private void jDimmen4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDimmen4KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jDimmen4KeyReleased

    private void jDimm_FSFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimm_FSFocusLost
        CV[1][113] = KTUI.checkTextField( this, jDimm_FS, 1, 126, 10, true);
        jCV_Anzeige.setSelectedItem( "CV#"+113 );
    }//GEN-LAST:event_jDimm_FSFocusLost

    private void jDimm_FSKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDimm_FSKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jDimm_FSKeyReleased

    private void jRangierF3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRangierF3ActionPerformed
        if(jRangierF3.isSelected()) {
            CV[1][131] |= 64;
        } else {
            CV[1][131] &= ~64;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+131 );
    }//GEN-LAST:event_jRangierF3ActionPerformed

    private void jRangierF4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRangierF4ActionPerformed
        if(jRangierF4.isSelected()) {
            CV[1][131] |= 128;
        } else {
            CV[1][131] &= ~128;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+131 );
    }//GEN-LAST:event_jRangierF4ActionPerformed

    private void jTuerIn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTuerIn1ActionPerformed
        if(jTuerIn1.isSelected()) {
            CV[1][60] |= 8;
        } else {
            CV[1][60] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+60 );
    }//GEN-LAST:event_jTuerIn1ActionPerformed

    private void jSchaffnerIn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSchaffnerIn1ActionPerformed
        if(jSchaffnerIn1.isSelected()) {
            CV[1][60] |= 16;
        } else {
            CV[1][60] &= ~16;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+60 );
    }//GEN-LAST:event_jSchaffnerIn1ActionPerformed

    private void jSignalIn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSignalIn1ActionPerformed
        if(jSignalIn1.isSelected()) {
            CV[1][60] |= 32;
        } else {
            CV[1][60] &= ~32;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+60 );
    }//GEN-LAST:event_jSignalIn1ActionPerformed

    private void jGlockeIn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jGlockeIn1ActionPerformed
        if(jGlockeIn1.isSelected()) {
            CV[1][60] |= 64;
        } else {
            CV[1][60] &= ~64;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+60 );
    }//GEN-LAST:event_jGlockeIn1ActionPerformed

    private void jMindestSchlt1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jMindestSchlt1FocusLost
        CV[1][119] = KTUI.checkTextField( this, jMindestSchlt1, 0, 255, 0, true);
        jCV_Anzeige.setSelectedItem( "CV#"+119 );
    }//GEN-LAST:event_jMindestSchlt1FocusLost

    private void jMindestSchlt1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jMindestSchlt1KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jMindestSchlt1KeyReleased

    private void jMindestSchlt2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jMindestSchlt2FocusLost
        CV[1][120] = KTUI.checkTextField( this, jMindestSchlt2, 0, 255, 0, true);
        jCV_Anzeige.setSelectedItem( "CV#"+120 );
    }//GEN-LAST:event_jMindestSchlt2FocusLost

    private void jMindestSchlt2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jMindestSchlt2KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jMindestSchlt2KeyReleased

    private void jMindestSchlt3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jMindestSchlt3FocusLost
        CV[1][121] = KTUI.checkTextField( this, jMindestSchlt3, 0, 255, 0, true);
        jCV_Anzeige.setSelectedItem( "CV#"+121 );
    }//GEN-LAST:event_jMindestSchlt3FocusLost

    private void jMindestSchlt3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jMindestSchlt3KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jMindestSchlt3KeyReleased

    private void jIn1Aux1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn1Aux1ActionPerformed
        if(jIn1Aux1.isSelected()) {
            CV[1][59] |= 1;
        } else {
            CV[1][59] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+59 );
    }//GEN-LAST:event_jIn1Aux1ActionPerformed

    private void jIn1Aux2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn1Aux2ActionPerformed
        if(jIn1Aux2.isSelected()) {
            CV[1][59] |= 2;
        } else {
            CV[1][59] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+59 );
    }//GEN-LAST:event_jIn1Aux2ActionPerformed

    private void jIn1Aux3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn1Aux3ActionPerformed
        if(jIn1Aux3.isSelected()) {
            CV[1][59] |= 4;
        } else {
            CV[1][59] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+59 );
    }//GEN-LAST:event_jIn1Aux3ActionPerformed

    private void jIn1Aux4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn1Aux4ActionPerformed
        if(jIn1Aux4.isSelected()) {
            CV[1][59] |= 8;
        } else {
            CV[1][59] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+59 );
    }//GEN-LAST:event_jIn1Aux4ActionPerformed

    private void jIn1Aux5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn1Aux5ActionPerformed
        if(jIn1Aux5.isSelected()) {
            CV[1][59] |= 16;
        } else {
            CV[1][59] &= ~16;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+59 );
    }//GEN-LAST:event_jIn1Aux5ActionPerformed

    private void jIn1Aux6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn1Aux6ActionPerformed
        if(jIn1Aux6.isSelected()) {
            CV[1][59] |= 32;
        } else {
            CV[1][59] &= ~32;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+59 );
    }//GEN-LAST:event_jIn1Aux6ActionPerformed

    private void jDimmFS3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmFS3FocusLost
        CV[1][125] = KTUI.checkTextField( this, jDimmFS3, 1, 255, 255, true);
        jCV_Anzeige.setSelectedItem( "CV#"+125 );
    }//GEN-LAST:event_jDimmFS3FocusLost

    private void jDimmFS3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDimmFS3KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jDimmFS3KeyReleased

    private void jDimmFS4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmFS4FocusLost
        CV[1][126] = KTUI.checkTextField( this, jDimmFS4, 1, 255, 255, true);
        jCV_Anzeige.setSelectedItem( "CV#"+126 );
    }//GEN-LAST:event_jDimmFS4FocusLost

    private void jDimmFS4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDimmFS4KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jDimmFS4KeyReleased

    private void jDimmFS1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmFS1FocusLost
        CV[1][123] = KTUI.checkTextField( this, jDimmFS1, 1, 255, 255, true);
        jCV_Anzeige.setSelectedItem( "CV#"+123 );
    }//GEN-LAST:event_jDimmFS1FocusLost

    private void jDimmFS1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDimmFS1KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jDimmFS1KeyReleased

    private void jDimmFS2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmFS2FocusLost
        CV[1][124] = KTUI.checkTextField( this, jDimmFS1, 1, 255, 255, true);
        jCV_Anzeige.setSelectedItem( "CV#"+124 );
    }//GEN-LAST:event_jDimmFS2FocusLost

    private void jDimmFS2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDimmFS2KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jDimmFS2KeyReleased

    private void jEffekte_2ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jEffekte_2ComponentShown
        jCV_Anzeige.setSelectedItem( "CV#"+115 );
        
        jDimmen1.setText("" + CV[1][115]);
        jDimmen2.setText("" + CV[1][116]);
        jDimmen3.setText("" + CV[1][117]);
        jDimmen4.setText("" + CV[1][118]);

        jbDimmFS1.setSelected((CV[1][114] & 1) == 1);
        jbDimmFS2.setSelected((CV[1][114] & 2) == 2);
        jbDimmFS3.setSelected((CV[1][114] & 4) == 4);
        jbDimmFS4.setSelected((CV[1][114] & 8) == 8);
        
        jDimmFS1.setText("" + CV[1][123]);
        jDimmFS2.setText("" + CV[1][124]);
        jDimmFS3.setText("" + CV[1][125]);
        jDimmFS4.setText("" + CV[1][126]);
        
        jDimm_FS.setText("" + CV[1][113]);
        
        jKickvor.setText("" + CV[1][162]);
        jKickrueck.setText("" + CV[1][163]);
        
        jMindestSchlt1.setText("" + CV[1][119]);
        jMindestSchlt2.setText("" + CV[1][120]);
        jMindestSchlt3.setText("" + CV[1][121]);
        

        jRangier1.setSelected( (CV[1][131] &   1) ==   1);
        jRangier2.setSelected( (CV[1][131] &   2) ==   2);
        jRangier3.setSelected( (CV[1][131] &   4) ==   4);
        jRangier4.setSelected( (CV[1][131] &   8) ==   8);
        jRangier5.setSelected( (CV[1][131] &  16) ==  16);
        jRangier6.setSelected( (CV[1][131] &  32) ==  32);
        jRangierF3.setSelected((CV[1][131] &  64) ==  64);
        jRangierF4.setSelected((CV[1][131] & 128) == 128);

        jIn1Aux1.setSelected((CV[1][59] &  1) ==  1);
        jIn1Aux2.setSelected((CV[1][59] &  2) ==  2);
        jIn1Aux3.setSelected((CV[1][59] &  4) ==  4);
        jIn1Aux4.setSelected((CV[1][59] &  8) ==  8);
        jIn1Aux5.setSelected((CV[1][59] & 16) == 16);
        jIn1Aux6.setSelected((CV[1][59] & 32) == 32);


        jIn2Aux1.setSelected((CV[1][61] &  1) ==  1);
        jIn2Aux2.setSelected((CV[1][61] &  2) ==  2);
        jIn2Aux3.setSelected((CV[1][61] &  4) ==  4);
        jIn2Aux4.setSelected((CV[1][61] &  8) ==  8);
        jIn2Aux5.setSelected((CV[1][61] & 16) == 16);
        jIn2Aux6.setSelected((CV[1][61] & 32) == 32);

        jIn3Aux1.setSelected((CV[1][63] &  1) ==  1);
        jIn3Aux2.setSelected((CV[1][63] &  2) ==  2);
        jIn3Aux3.setSelected((CV[1][63] &  4) ==  4);
        jIn3Aux4.setSelected((CV[1][63] &  8) ==  8);
        jIn3Aux5.setSelected((CV[1][63] & 16) == 16);
        jIn3Aux6.setSelected((CV[1][63] & 32) == 32);

        jTuerIn1.setSelected((CV[1][60] & 8) == 8);
        jTuerIn2.setSelected((CV[1][62] & 8) == 8);
        jTuerIn3.setSelected((CV[1][64] & 8) == 8);
        jSchaffnerIn1.setSelected((CV[1][60] & 16) == 16);
        jSchaffnerIn2.setSelected((CV[1][62] & 16) == 16);
        jSchaffnerIn3.setSelected((CV[1][64] & 16) == 16);
        jSignalIn1.setSelected((CV[1][60] & 32) == 32);
        jSignalIn2.setSelected((CV[1][62] & 32) == 32);
        jSignalIn3.setSelected((CV[1][64] & 32) == 32);
        jGlockeIn1.setSelected((CV[1][60] & 64) == 64);
        jGlockeIn2.setSelected((CV[1][62] & 64) == 64);
        jGlockeIn3.setSelected((CV[1][64] & 64) == 64);

        jIn1Pos1.setSelected((CV[1][60] & 3) == 1);
        jIn2Pos1.setSelected((CV[1][62] & 3) == 1);
        // jIn3Pos1.setSelected((CV[1][64] & 3) == 1);
        jIn1Pos2.setSelected((CV[1][60] & 3) == 2);
        jIn2Pos2.setSelected((CV[1][62] & 3) == 2);
        // jIn3Pos2.setSelected((CV[1][64] & 3) == 2);
        jIn1Pos3.setSelected((CV[1][60] & 3) == 3);
        jIn2Pos3.setSelected((CV[1][62] & 3) == 3);
        // jIn3Pos3.setSelected((CV[1][64] & 3) == 3);

    }//GEN-LAST:event_jEffekte_2ComponentShown

    private void jKick6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jKick6ActionPerformed
        if(jKick6.isSelected()) {
            CV[1][58] |= 128;
        } else {
            CV[1][58] &= ~128;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+58 );
    }//GEN-LAST:event_jKick6ActionPerformed

    private void jKick1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jKick1ActionPerformed
        if(jKick1.isSelected()) {
            CV[1][53] |= 128;
        } else {
            CV[1][53] &= ~128;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+53 );
    }//GEN-LAST:event_jKick1ActionPerformed

    private void jKick2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jKick2ActionPerformed
        if(jKick2.isSelected()) {
            CV[1][54] |= 128;
        } else {
            CV[1][54] &= ~128;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+54 );
    }//GEN-LAST:event_jKick2ActionPerformed

    private void jKick3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jKick3ActionPerformed
        if(jKick3.isSelected()) {
            CV[1][55] |= 128;
        } else {
            CV[1][55] &= ~128;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+55 );
    }//GEN-LAST:event_jKick3ActionPerformed

    private void jKick4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jKick4ActionPerformed
        if(jKick4.isSelected()) {
            CV[1][56] |= 128;
        } else {
            CV[1][56] &= ~128;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+56 );
    }//GEN-LAST:event_jKick4ActionPerformed

    private void jKick5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jKick5ActionPerformed
        if(jKick5.isSelected()) {
            CV[1][57] |= 128;
        } else {
            CV[1][57] &= ~128;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+57 );
    }//GEN-LAST:event_jKick5ActionPerformed

    private void jIn2Aux1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn2Aux1ActionPerformed
        if(jIn2Aux1.isSelected()) {
            CV[1][61] |= 1;
        } else {
            CV[1][61] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+61 );
    }//GEN-LAST:event_jIn2Aux1ActionPerformed

    private void jIn2Aux2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn2Aux2ActionPerformed
        if(jIn2Aux2.isSelected()) {
            CV[1][61] |= 2;
        } else {
            CV[1][61] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+61 );
    }//GEN-LAST:event_jIn2Aux2ActionPerformed

    private void jIn2Aux3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn2Aux3ActionPerformed
        if(jIn2Aux3.isSelected()) {
            CV[1][61] |= 4;
        } else {
            CV[1][61] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+61 );
    }//GEN-LAST:event_jIn2Aux3ActionPerformed

    private void jIn2Aux4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn2Aux4ActionPerformed
        if(jIn2Aux4.isSelected()) {
            CV[1][61] |= 8;
        } else {
            CV[1][61] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+61 );
    }//GEN-LAST:event_jIn2Aux4ActionPerformed

    private void jIn2Aux5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn2Aux5ActionPerformed
        if(jIn2Aux5.isSelected()) {
            CV[1][61] |= 16;
        } else {
            CV[1][61] &= ~16;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+61 );
    }//GEN-LAST:event_jIn2Aux5ActionPerformed

    private void jIn2Aux6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn2Aux6ActionPerformed
        if(jIn2Aux6.isSelected()) {
            CV[1][61] |= 32;
        } else {
            CV[1][61] &= ~32;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+61 );
    }//GEN-LAST:event_jIn2Aux6ActionPerformed

    private void jIn3Aux6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn3Aux6ActionPerformed
        if(jIn3Aux6.isSelected()) {
            CV[1][63] |= 32;
            CV[1][167] = 0;
            CV[1][168] = 0;
            jIn1Pos3.setSelected(false);
            jIn1Pos2.setSelected(false);
            jIn1Pos1.setSelected(false);
            jIn2Pos3.setSelected(false);
            jIn2Pos2.setSelected(false);
            jIn2Pos1.setSelected(false);
        } else {
            CV[1][63] &= ~32;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+63 );
    }//GEN-LAST:event_jIn3Aux6ActionPerformed

    private void jIn3Aux1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn3Aux1ActionPerformed
        if(jIn3Aux1.isSelected()) {
            CV[1][63] |= 1;
            CV[1][167] = 0;
            CV[1][168] = 0;
            jIn1Pos3.setSelected(false);
            jIn1Pos2.setSelected(false);
            jIn1Pos1.setSelected(false);
            jIn2Pos3.setSelected(false);
            jIn2Pos2.setSelected(false);
            jIn2Pos1.setSelected(false);
        } else {
            CV[1][63] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+63 );
    }//GEN-LAST:event_jIn3Aux1ActionPerformed

    private void jIn3Aux4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn3Aux4ActionPerformed
        if(jIn3Aux4.isSelected()) {
            CV[1][63] |= 8;
            CV[1][167] = 0;
            CV[1][168] = 0;
            jIn1Pos3.setSelected(false);
            jIn1Pos2.setSelected(false);
            jIn1Pos1.setSelected(false);
            jIn2Pos3.setSelected(false);
            jIn2Pos2.setSelected(false);
            jIn2Pos1.setSelected(false);
        } else {
            CV[1][63] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+63 );
    }//GEN-LAST:event_jIn3Aux4ActionPerformed

    private void jIn3Aux2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn3Aux2ActionPerformed
        if(jIn3Aux2.isSelected()) {
            CV[1][63] |= 2;
            CV[1][167] = 0;
            CV[1][168] = 0;
            jIn1Pos3.setSelected(false);
            jIn1Pos2.setSelected(false);
            jIn1Pos1.setSelected(false);
            jIn2Pos3.setSelected(false);
            jIn2Pos2.setSelected(false);
            jIn2Pos1.setSelected(false);
        } else {
            CV[1][63] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+63 );
    }//GEN-LAST:event_jIn3Aux2ActionPerformed

    private void jIn3Aux3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn3Aux3ActionPerformed
        if(jIn3Aux3.isSelected()) {
            CV[1][63] |= 4;
            CV[1][167] = 0;
            CV[1][168] = 0;
            jIn1Pos3.setSelected(false);
            jIn1Pos2.setSelected(false);
            jIn1Pos1.setSelected(false);
            jIn2Pos3.setSelected(false);
            jIn2Pos2.setSelected(false);
            jIn2Pos1.setSelected(false);
        } else {
            CV[1][63] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+63 );
    }//GEN-LAST:event_jIn3Aux3ActionPerformed

    private void jIn3Aux5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn3Aux5ActionPerformed
        if(jIn3Aux5.isSelected()) {
            CV[1][63] |= 16;
            CV[1][167] = 0;
            CV[1][168] = 0;
            jIn1Pos3.setSelected(false);
            jIn1Pos2.setSelected(false);
            jIn1Pos1.setSelected(false);
            jIn2Pos3.setSelected(false);
            jIn2Pos2.setSelected(false);
            jIn2Pos1.setSelected(false);
        } else {
            CV[1][63] &= ~16;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+63 );
    }//GEN-LAST:event_jIn3Aux5ActionPerformed

    private void jTuerIn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTuerIn2ActionPerformed
        if(jTuerIn2.isSelected()) {
            CV[1][62] |= 8;
        } else {
            CV[1][62] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+62 );
    }//GEN-LAST:event_jTuerIn2ActionPerformed

    private void jSchaffnerIn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSchaffnerIn2ActionPerformed
        if(jSchaffnerIn2.isSelected()) {
            CV[1][62] |= 16;
        } else {
            CV[1][62] &= ~16;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+62 );
    }//GEN-LAST:event_jSchaffnerIn2ActionPerformed

    private void jSignalIn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSignalIn2ActionPerformed
        if(jSignalIn2.isSelected()) {
            CV[1][62] |= 32;
        } else {
            CV[1][62] &= ~32;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+62 );
    }//GEN-LAST:event_jSignalIn2ActionPerformed

    private void jGlockeIn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jGlockeIn2ActionPerformed
        if(jGlockeIn2.isSelected()) {
            CV[1][62] |= 64;
        } else {
            CV[1][62] &= ~64;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+62 );
    }//GEN-LAST:event_jGlockeIn2ActionPerformed

    private void jSignalIn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSignalIn3ActionPerformed
        if(jSignalIn3.isSelected()) {
            CV[1][64] |= 32;
            CV[1][167] = 0;
            CV[1][168] = 0;
            jIn1Pos3.setSelected(false);
            jIn1Pos2.setSelected(false);
            jIn1Pos1.setSelected(false);
            jIn2Pos3.setSelected(false);
            jIn2Pos2.setSelected(false);
            jIn2Pos1.setSelected(false);
        } else {
            CV[1][64] &= ~32;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+64 );
    }//GEN-LAST:event_jSignalIn3ActionPerformed

    private void jGlockeIn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jGlockeIn3ActionPerformed
        if(jGlockeIn3.isSelected()) {
            CV[1][64] |= 64;
            CV[1][167] = 0;
            CV[1][168] = 0;
            jIn1Pos3.setSelected(false);
            jIn1Pos2.setSelected(false);
            jIn1Pos1.setSelected(false);
            jIn2Pos3.setSelected(false);
            jIn2Pos2.setSelected(false);
            jIn2Pos1.setSelected(false);
        } else {
            CV[1][64] &= ~64;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+64 );
    }//GEN-LAST:event_jGlockeIn3ActionPerformed

    private void jSchaffnerIn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSchaffnerIn3ActionPerformed
        if(jSchaffnerIn3.isSelected()) {
            CV[1][64] |= 16;
            CV[1][167] = 0;
            CV[1][168] = 0;
            jIn1Pos3.setSelected(false);
            jIn1Pos2.setSelected(false);
            jIn1Pos1.setSelected(false);
            jIn2Pos3.setSelected(false);
            jIn2Pos2.setSelected(false);
            jIn2Pos1.setSelected(false);
        } else {
            CV[1][64] &= ~16;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+64 );
    }//GEN-LAST:event_jSchaffnerIn3ActionPerformed

    private void jTuerIn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTuerIn3ActionPerformed
        if(jTuerIn3.isSelected()) {
            CV[1][64] |= 8;
            CV[1][167] = 0;
            CV[1][168] = 0;
            jIn1Pos3.setSelected(false);
            jIn1Pos2.setSelected(false);
            jIn1Pos1.setSelected(false);
            jIn2Pos3.setSelected(false);
            jIn2Pos2.setSelected(false);
            jIn2Pos1.setSelected(false);
        } else {
            CV[1][64] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+64 );
    }//GEN-LAST:event_jTuerIn3ActionPerformed

    private void jIn1Pos1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn1Pos1ActionPerformed
        if(jIn1Pos1.isSelected()) {
            CV[1][63] = 0;
            CV[1][64] = 0;
            jIn3Aux1.setSelected(false);
            jIn3Aux2.setSelected(false);
            jIn3Aux3.setSelected(false);
            jIn3Aux4.setSelected(false);
            jIn3Aux5.setSelected(false);
            jIn3Aux6.setSelected(false);
            jTuerIn3.setSelected(false);
            jSchaffnerIn3.setSelected(false);
            jSignalIn3.setSelected(false);
            jGlockeIn3.setSelected(false);
            CV[1][60] &= ~3;
            CV[1][60] |= 1;
            jIn1Pos2.setSelected(false);
            jIn1Pos3.setSelected(false);
        } else {
            CV[1][60] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+60 );
    }//GEN-LAST:event_jIn1Pos1ActionPerformed

    private void jIn1Pos2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn1Pos2ActionPerformed
        if(jIn1Pos2.isSelected()) {
            CV[1][63] = 0;
            CV[1][64] = 0;
            jIn3Aux1.setSelected(false);
            jIn3Aux2.setSelected(false);
            jIn3Aux3.setSelected(false);
            jIn3Aux4.setSelected(false);
            jIn3Aux5.setSelected(false);
            jIn3Aux6.setSelected(false);
            jTuerIn3.setSelected(false);
            jSchaffnerIn3.setSelected(false);
            jSignalIn3.setSelected(false);
            jGlockeIn3.setSelected(false);
            CV[1][60] &= ~3;
            CV[1][60] |= 2;
            jIn1Pos1.setSelected(false);
            jIn1Pos3.setSelected(false);
        } else {
            CV[1][60] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+60 );
    }//GEN-LAST:event_jIn1Pos2ActionPerformed

    private void jIn1Pos3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn1Pos3ActionPerformed
        if(jIn1Pos3.isSelected()) {
            CV[1][63] = 0;
            CV[1][64] = 0;
            jIn3Aux1.setSelected(false);
            jIn3Aux2.setSelected(false);
            jIn3Aux3.setSelected(false);
            jIn3Aux4.setSelected(false);
            jIn3Aux5.setSelected(false);
            jIn3Aux6.setSelected(false);
            jTuerIn3.setSelected(false);
            jSchaffnerIn3.setSelected(false);
            jSignalIn3.setSelected(false);
            jGlockeIn3.setSelected(false);
            CV[1][60] |= 3;
            jIn1Pos2.setSelected(false);
            jIn1Pos1.setSelected(false);
        } else {
            CV[1][60] &= ~3;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+60 );
    }//GEN-LAST:event_jIn1Pos3ActionPerformed

    private void jIn2Pos1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn2Pos1ActionPerformed
        if(jIn2Pos1.isSelected()) {
            CV[1][63] = 0;
            CV[1][64] = 0;
            jIn3Aux1.setSelected(false);
            jIn3Aux2.setSelected(false);
            jIn3Aux3.setSelected(false);
            jIn3Aux4.setSelected(false);
            jIn3Aux5.setSelected(false);
            jIn3Aux6.setSelected(false);
            jTuerIn3.setSelected(false);
            jSchaffnerIn3.setSelected(false);
            jSignalIn3.setSelected(false);
            jGlockeIn3.setSelected(false);
            CV[1][62] &= ~3;
            CV[1][62] |= 1;
            jIn2Pos2.setSelected(false);
            jIn2Pos3.setSelected(false);
        } else {
            CV[1][62] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+62 );
    }//GEN-LAST:event_jIn2Pos1ActionPerformed

    private void jIn2Pos2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn2Pos2ActionPerformed
        if(jIn2Pos2.isSelected()) {
            CV[1][63] = 0;
            CV[1][64] = 0;
            jIn3Aux1.setSelected(false);
            jIn3Aux2.setSelected(false);
            jIn3Aux3.setSelected(false);
            jIn3Aux4.setSelected(false);
            jIn3Aux5.setSelected(false);
            jIn3Aux6.setSelected(false);
            jTuerIn3.setSelected(false);
            jSchaffnerIn3.setSelected(false);
            jSignalIn3.setSelected(false);
            jGlockeIn3.setSelected(false);
            CV[1][62] &= ~3;
            CV[1][62] |= 2;
            jIn2Pos1.setSelected(false);
            jIn2Pos3.setSelected(false);
        } else {
            CV[1][62] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+62 );
    }//GEN-LAST:event_jIn2Pos2ActionPerformed

    private void jIn2Pos3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn2Pos3ActionPerformed
        if(jIn2Pos3.isSelected()) {
            CV[1][63] = 0;
            CV[1][64] = 0;
            jIn3Aux1.setSelected(false);
            jIn3Aux2.setSelected(false);
            jIn3Aux3.setSelected(false);
            jIn3Aux4.setSelected(false);
            jIn3Aux5.setSelected(false);
            jIn3Aux6.setSelected(false);
            jTuerIn3.setSelected(false);
            jSchaffnerIn3.setSelected(false);
            jSignalIn3.setSelected(false);
            jGlockeIn3.setSelected(false);
            CV[1][62] |= 3;
            jIn2Pos2.setSelected(false);
            jIn2Pos1.setSelected(false);
        } else {
            CV[1][62] &= ~3;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+62 );
    }//GEN-LAST:event_jIn2Pos3ActionPerformed

    private void jSoundComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jSoundComponentShown
        jCV_Anzeige.setSelectedItem( "CV#"+148 );

        int cvValue;

        cvValue = CV[1][148];
        if( ! isValidFktkeySoundMapping( cvValue ) ) {
            CV[1][148] = 0;
            cvValue = 0;
            jCV_Inhalt.setText(""+0 );
        }
        //------------- F1 ------------------
        jF1_TP.setSelected( cvValue ==   8);
        jF1_SP.setSelected( cvValue ==  16);
        jF1_SH.setSelected( cvValue ==  32);
        jF1_Gl.setSelected( cvValue ==  64);
        //------------- F13 ------------------
        jF13_TP.setSelected(cvValue == 136);
        jF13_SP.setSelected(cvValue == 144);
        jF13_SH.setSelected(cvValue == 160);
        jF13_Gl.setSelected(cvValue == 192);

        cvValue = CV[1][149];
        if( ! isValidFktkeySoundMapping( cvValue ) ) {
            CV[1][149] = 0;
            cvValue = 0;
        }
        //------------- F2 ------------------
        jF2_TP.setSelected( cvValue ==   8);
        jF2_SP.setSelected( cvValue ==  16);
        jF2_SH.setSelected( cvValue ==  32);
        jF2_Gl.setSelected( cvValue ==  64);
        //------------- F14 ------------------
        jF14_TP.setSelected(cvValue == 136);
        jF14_SP.setSelected(cvValue == 144);
        jF14_SH.setSelected(cvValue == 160);
        jF14_Gl.setSelected(cvValue == 192);

        cvValue = CV[1][150];
        if( ! isValidFktkeySoundMapping( cvValue ) ) {
            CV[1][150] = 0;
            cvValue = 0;
        }
        //------------- F3 ------------------
        jF3_TP.setSelected( cvValue ==   8);
        jF3_SP.setSelected( cvValue ==  16);
        jF3_SH.setSelected( cvValue ==  32);
        jF3_Gl.setSelected( cvValue ==  64);
        //------------- F15 ------------------
        jF15_TP.setSelected(cvValue == 136);
        jF15_SP.setSelected(cvValue == 144);
        jF15_SH.setSelected(cvValue == 160);
        jF15_Gl.setSelected(cvValue == 192);

        cvValue = CV[1][151];
        if( ! isValidFktkeySoundMapping( cvValue ) ) {
            CV[1][151] = 0;
            cvValue = 0;
        }
        //------------- F4 ------------------
        jF4_TP.setSelected( cvValue ==   8);
        jF4_SP.setSelected( cvValue ==  16);
        jF4_SH.setSelected( cvValue ==  32);
        jF4_Gl.setSelected( cvValue ==  64);
        //------------- F16 ------------------
        jF16_TP.setSelected(cvValue == 136);
        jF16_SP.setSelected(cvValue == 144);
        jF16_SH.setSelected(cvValue == 160);
        jF16_Gl.setSelected(cvValue == 192);

        cvValue = CV[1][152];
        if( ! isValidFktkeySoundMapping( cvValue ) ) {
            CV[1][152] = 0;
            cvValue = 0;
        }
        //------------- F5 ------------------
        jF5_TP.setSelected( cvValue ==   8);
        jF5_SP.setSelected( cvValue ==  16);
        jF5_SH.setSelected( cvValue ==  32);
        jF5_Gl.setSelected( cvValue ==  64);
        //------------- F17 ------------------
        jF17_TP.setSelected(cvValue == 136);
        jF17_SP.setSelected(cvValue == 144);
        jF17_SH.setSelected(cvValue == 160);
        jF17_Gl.setSelected(cvValue == 192);

        cvValue = CV[1][153];
        if( ! isValidFktkeySoundMapping( cvValue ) ) {
            CV[1][153] = 0;
            cvValue = 0;
        }
        //------------- F6 ------------------
        jF6_TP.setSelected( cvValue ==   8);
        jF6_SP.setSelected( cvValue ==  16);
        jF6_SH.setSelected( cvValue ==  32);
        jF6_Gl.setSelected( cvValue ==  64);
        //------------- F18 ------------------
        jF18_TP.setSelected(cvValue == 136);
        jF18_SP.setSelected(cvValue == 144);
        jF18_SH.setSelected(cvValue == 160);
        jF18_Gl.setSelected(cvValue == 192);

        cvValue = CV[1][154];
        if( ! isValidFktkeySoundMapping( cvValue ) ) {
            CV[1][154] = 16;
            cvValue = 16;
        }
        //------------- F7 ------------------
        jF7_TP.setSelected( cvValue ==   8);
        jF7_SP.setSelected( cvValue ==  16);
        jF7_SH.setSelected( cvValue ==  32);
        jF7_Gl.setSelected( cvValue ==  64);
        //------------- F19 ------------------
        jF19_TP.setSelected(cvValue == 136);
        jF19_SP.setSelected(cvValue == 144);
        jF19_SH.setSelected(cvValue == 160);
        jF19_Gl.setSelected(cvValue == 192);

        cvValue = CV[1][155];
        if( ! isValidFktkeySoundMapping( cvValue ) ) {
            CV[1][155] = 32;
            cvValue = 32;
        }
        //------------- F8 ------------------
        jF8_TP.setSelected( cvValue ==   8);
        jF8_SP.setSelected( cvValue ==  16);
        jF8_SH.setSelected( cvValue ==  32);
        jF8_Gl.setSelected( cvValue ==  64);
        //------------- F20 ------------------
        jF20_TP.setSelected(cvValue == 136);
        jF20_SP.setSelected(cvValue == 144);
        jF20_SH.setSelected(cvValue == 160);
        jF20_Gl.setSelected(cvValue == 192);

        cvValue = CV[1][156];
        if( ! isValidFktkeySoundMapping( cvValue ) ) {
            CV[1][156] = 64;
            cvValue = 64;
        }
        //------------- F9 ------------------
        jF9_TP.setSelected( cvValue ==   8);
        jF9_SP.setSelected( cvValue ==  16);
        jF9_SH.setSelected( cvValue ==  32);
        jF9_Gl.setSelected( cvValue ==  64);
        //------------- F21 ------------------
        jF21_TP.setSelected(cvValue == 136);
        jF21_SP.setSelected(cvValue == 144);
        jF21_SH.setSelected(cvValue == 160);
        jF21_Gl.setSelected(cvValue == 192);

        cvValue = CV[1][157];
        if( ! isValidFktkeySoundMapping( cvValue ) ) {
            CV[1][157] = 8;
            cvValue = 8;
        }
        //------------- F10 ------------------
        jF10_TP.setSelected( cvValue ==   8);
        jF10_SP.setSelected( cvValue ==  16);
        jF10_SH.setSelected( cvValue ==  32);
        jF10_Gl.setSelected( cvValue ==  64);
        //------------- F22 ------------------
        jF22_TP.setSelected(cvValue == 136);
        jF22_SP.setSelected(cvValue == 144);
        jF22_SH.setSelected(cvValue == 160);
        jF2_Gl.setSelected(cvValue == 192);

        cvValue = CV[1][158];
        if( ! isValidFktkeySoundMapping( cvValue ) ) {
            CV[1][158] = 0;
            cvValue = 0;
        }
        //------------- F11 ------------------
        jF11_TP.setSelected( cvValue ==   8);
        jF11_SP.setSelected( cvValue ==  16);
        jF11_SH.setSelected( cvValue ==  32);
        jF11_Gl.setSelected( cvValue ==  64);
        //------------- F23 ------------------
        jF23_TP.setSelected(cvValue == 136);
        jF23_SP.setSelected(cvValue == 144);
        jF23_SH.setSelected(cvValue == 160);
        jF23_Gl.setSelected(cvValue == 192);

        cvValue = CV[1][159];
        if( ! isValidFktkeySoundMapping( cvValue ) ) {
            CV[1][159] = 0;
            cvValue = 0;
        }
        //------------- F12 ------------------
        jF12_TP.setSelected( cvValue ==   8);
        jF12_SP.setSelected( cvValue ==  16);
        jF12_SH.setSelected( cvValue ==  32);
        jF12_Gl.setSelected( cvValue ==  64);
        //------------- F24 ------------------
        jF24_TP.setSelected(cvValue == 136);
        jF24_SP.setSelected(cvValue == 144);
        jF24_SH.setSelected(cvValue == 160);
        jF24_Gl.setSelected(cvValue == 192);


        jLaut.setText("" + CV[1][160]);
        jLautSlider.setValue(255 - CV[1][160]);
    }//GEN-LAST:event_jSoundComponentShown
    private void jF12_GlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF12_GlActionPerformed
        jF12_TP.setSelected(false);
        jF12_SH.setSelected(false);
        jF12_SP.setSelected(false);
        jF24_TP.setSelected(false);
        jF24_SP.setSelected(false);
        jF24_SH.setSelected(false);
        jF24_Gl.setSelected(false);
        if(jF1_Gl.isSelected()) {
            CV[1][159] = 64;
        } else {
            CV[1][159] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+159 );
    }//GEN-LAST:event_jF12_GlActionPerformed
    private void jF9_TPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF9_TPActionPerformed
        jF9_Gl.setSelected(false);
        jF9_SH.setSelected(false);
        jF9_SP.setSelected(false);
        jF21_TP.setSelected(false);
        jF21_SP.setSelected(false);
        jF21_SH.setSelected(false);
        jF21_Gl.setSelected(false);
        if(jF9_TP.isSelected()) {
            CV[1][156] = 8;
        } else {
            CV[1][156] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+156 );
    }//GEN-LAST:event_jF9_TPActionPerformed
        private void jF8_GlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF8_GlActionPerformed
        jF8_TP.setSelected(false);
        jF8_SH.setSelected(false);
        jF8_SP.setSelected(false);
        jF20_TP.setSelected(false);
        jF20_SP.setSelected(false);
        jF20_SH.setSelected(false);
        jF20_Gl.setSelected(false);
        if(jF1_Gl.isSelected()) {
            CV[1][155] = 64;
        } else {
            CV[1][155] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+155 );
    }//GEN-LAST:event_jF8_GlActionPerformed
    private void jF8_SHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF8_SHActionPerformed
        jF8_TP.setSelected(false);
        jF8_SP.setSelected(false);
        jF8_Gl.setSelected(false);
        jF20_TP.setSelected(false);
        jF20_SP.setSelected(false);
        jF20_SH.setSelected(false);
        jF20_Gl.setSelected(false);
        if(jF8_SH.isSelected()) {
            CV[1][155] = 32;
        } else {
            CV[1][155] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+155 );
    }//GEN-LAST:event_jF8_SHActionPerformed
    private void jF12_SHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF12_SHActionPerformed
        jF12_TP.setSelected(false);
        jF12_SP.setSelected(false);
        jF12_Gl.setSelected(false);
        jF24_TP.setSelected(false);
        jF24_SP.setSelected(false);
        jF24_SH.setSelected(false);
        jF24_Gl.setSelected(false);
        if(jF12_SH.isSelected()) {
            CV[1][159] = 32;
        } else {
            CV[1][159] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+159 );
    }//GEN-LAST:event_jF12_SHActionPerformed
    private void jF10_GlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF10_GlActionPerformed
        jF10_TP.setSelected(false);
        jF10_SH.setSelected(false);
        jF10_SP.setSelected(false);
        jF22_TP.setSelected(false);
        jF22_SP.setSelected(false);
        jF22_SH.setSelected(false);
        jF22_Gl.setSelected(false);
        if(jF1_Gl.isSelected()) {
            CV[1][157] = 64;
        } else {
            CV[1][157] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+157 );
    }//GEN-LAST:event_jF10_GlActionPerformed
    private void jF8_SPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF8_SPActionPerformed
        jF8_TP.setSelected(false);
        jF8_SH.setSelected(false);
        jF8_Gl.setSelected(false);
        jF20_TP.setSelected(false);
        jF20_SP.setSelected(false);
        jF20_SH.setSelected(false);
        jF20_Gl.setSelected(false);
        if(jF8_SP.isSelected()) {
            CV[1][155] = 16;
        } else {
            CV[1][155] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+155 );
    }//GEN-LAST:event_jF8_SPActionPerformed
    private void jF8_TPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF8_TPActionPerformed
        jF8_Gl.setSelected(false);
        jF8_SH.setSelected(false);
        jF8_SP.setSelected(false);
        jF20_TP.setSelected(false);
        jF20_SP.setSelected(false);
        jF20_SH.setSelected(false);
        jF20_Gl.setSelected(false);
        if(jF8_TP.isSelected()) {
            CV[1][155] = 8;
        } else {
            CV[1][155] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+155 );
    }//GEN-LAST:event_jF8_TPActionPerformed
    private void jF7_GlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF7_GlActionPerformed
        jF7_TP.setSelected(false);
        jF7_SH.setSelected(false);
        jF7_SP.setSelected(false);
        jF19_TP.setSelected(false);
        jF19_SP.setSelected(false);
        jF19_SH.setSelected(false);
        jF19_Gl.setSelected(false);
        if(jF1_Gl.isSelected()) {
            CV[1][154] = 64;
        } else {
            CV[1][154] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+154 );
    }//GEN-LAST:event_jF7_GlActionPerformed
    private void jF12_SPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF12_SPActionPerformed
        jF12_TP.setSelected(false);
        jF12_SH.setSelected(false);
        jF12_Gl.setSelected(false);
        jF24_TP.setSelected(false);
        jF24_SP.setSelected(false);
        jF24_SH.setSelected(false);
        jF24_Gl.setSelected(false);
        if(jF12_SP.isSelected()) {
            CV[1][159] = 16;
        } else {
            CV[1][159] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+159 );
    }//GEN-LAST:event_jF12_SPActionPerformed
    private void jF10_SHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF10_SHActionPerformed
        jF10_TP.setSelected(false);
        jF10_SP.setSelected(false);
        jF10_Gl.setSelected(false);
        jF22_TP.setSelected(false);
        jF22_SP.setSelected(false);
        jF22_SH.setSelected(false);
        jF22_Gl.setSelected(false);
        if(jF10_SH.isSelected()) {
            CV[1][157] = 32;
        } else {
            CV[1][157] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+157 );
    }//GEN-LAST:event_jF10_SHActionPerformed
    private void jF7_SHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF7_SHActionPerformed
        jF7_TP.setSelected(false);
        jF7_SP.setSelected(false);
        jF7_Gl.setSelected(false);
        jF19_TP.setSelected(false);
        jF19_SP.setSelected(false);
        jF19_SH.setSelected(false);
        jF19_Gl.setSelected(false);
        if(jF7_SH.isSelected()) {
            CV[1][154] = 32;
        } else {
            CV[1][154] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+154 );
    }//GEN-LAST:event_jF7_SHActionPerformed
    private void jF7_SPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF7_SPActionPerformed
        jF7_TP.setSelected(false);
        jF7_SH.setSelected(false);
        jF7_Gl.setSelected(false);
        jF19_TP.setSelected(false);
        jF19_SP.setSelected(false);
        jF19_SH.setSelected(false);
        jF19_Gl.setSelected(false);
        if(jF7_SP.isSelected()) {
            CV[1][154] = 16;
        } else {
            CV[1][154] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+154 );
    }//GEN-LAST:event_jF7_SPActionPerformed
    private void jF7_TPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF7_TPActionPerformed
        jF7_Gl.setSelected(false);
        jF7_SH.setSelected(false);
        jF7_SP.setSelected(false);
        jF19_TP.setSelected(false);
        jF19_SP.setSelected(false);
        jF19_SH.setSelected(false);
        jF19_Gl.setSelected(false);
        if(jF7_TP.isSelected()) {
            CV[1][154] = 8;
        } else {
            CV[1][154] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+154 );
    }//GEN-LAST:event_jF7_TPActionPerformed
    private void jF12_TPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF12_TPActionPerformed
        jF12_Gl.setSelected(false);
        jF12_SH.setSelected(false);
        jF12_SP.setSelected(false);
        jF24_TP.setSelected(false);
        jF24_SP.setSelected(false);
        jF24_SH.setSelected(false);
        jF24_Gl.setSelected(false);
        if(jF12_TP.isSelected()) {
            CV[1][159] = 8;
        } else {
            CV[1][159] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+159 );
    }//GEN-LAST:event_jF12_TPActionPerformed
    private void jF10_SPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF10_SPActionPerformed
        jF10_TP.setSelected(false);
        jF10_SH.setSelected(false);
        jF10_Gl.setSelected(false);
        jF22_TP.setSelected(false);
        jF22_SP.setSelected(false);
        jF22_SH.setSelected(false);
        jF22_Gl.setSelected(false);
        if(jF10_SP.isSelected()) {
            CV[1][157] = 16;
        } else {
            CV[1][157] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+157 );
    }//GEN-LAST:event_jF10_SPActionPerformed
    private void jF6_GlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF6_GlActionPerformed
        jF6_TP.setSelected(false);
        jF6_SH.setSelected(false);
        jF6_SP.setSelected(false);
        jF18_TP.setSelected(false);
        jF18_SP.setSelected(false);
        jF18_SH.setSelected(false);
        jF18_Gl.setSelected(false);
        if(jF1_Gl.isSelected()) {
            CV[1][153] = 64;
        } else {
            CV[1][153] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+153 );
    }//GEN-LAST:event_jF6_GlActionPerformed
    private void jF6_SHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF6_SHActionPerformed
        jF6_TP.setSelected(false);
        jF6_SP.setSelected(false);
        jF6_Gl.setSelected(false);
        jF18_TP.setSelected(false);
        jF18_SP.setSelected(false);
        jF18_SH.setSelected(false);
        jF18_Gl.setSelected(false);
        if(jF6_SH.isSelected()) {
            CV[1][153] = 32;
        } else {
            CV[1][153] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+153 );
    }//GEN-LAST:event_jF6_SHActionPerformed
    private void jF6_SPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF6_SPActionPerformed
        jF6_TP.setSelected(false);
        jF6_SH.setSelected(false);
        jF6_Gl.setSelected(false);
        jF18_TP.setSelected(false);
        jF18_SP.setSelected(false);
        jF18_SH.setSelected(false);
        jF18_Gl.setSelected(false);
        if(jF6_SP.isSelected()) {
            CV[1][153] = 16;
        } else {
            CV[1][153] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+153 );
    }//GEN-LAST:event_jF6_SPActionPerformed
    private void jF6_TPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF6_TPActionPerformed
        jF6_Gl.setSelected(false);
        jF6_SH.setSelected(false);
        jF6_SP.setSelected(false);
        jF18_TP.setSelected(false);
        jF18_SP.setSelected(false);
        jF18_SH.setSelected(false);
        jF18_Gl.setSelected(false);
        if(jF6_TP.isSelected()) {
            CV[1][153] = 8;
        } else {
            CV[1][153] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+153 );
    }//GEN-LAST:event_jF6_TPActionPerformed
    private void jF10_TPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF10_TPActionPerformed
        jF10_Gl.setSelected(false);
        jF10_SH.setSelected(false);
        jF10_SP.setSelected(false);
        jF22_TP.setSelected(false);
        jF22_SP.setSelected(false);
        jF22_SH.setSelected(false);
        jF22_Gl.setSelected(false);
        if(jF10_TP.isSelected()) {
            CV[1][157] = 8;
        } else {
            CV[1][157] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+157 );
    }//GEN-LAST:event_jF10_TPActionPerformed
    private void jF5_GlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF5_GlActionPerformed
        jF5_TP.setSelected(false);
        jF5_SH.setSelected(false);
        jF5_SP.setSelected(false);
        jF17_TP.setSelected(false);
        jF17_SP.setSelected(false);
        jF17_SH.setSelected(false);
        jF17_Gl.setSelected(false);
        if(jF1_Gl.isSelected()) {
            CV[1][152] = 64;
        } else {
            CV[1][152] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+152 );
    }//GEN-LAST:event_jF5_GlActionPerformed
    private void jF5_SHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF5_SHActionPerformed
        jF5_TP.setSelected(false);
        jF5_SP.setSelected(false);
        jF5_Gl.setSelected(false);
        jF17_TP.setSelected(false);
        jF17_SP.setSelected(false);
        jF17_SH.setSelected(false);
        jF17_Gl.setSelected(false);
        if(jF5_SH.isSelected()) {
            CV[1][152] = 32;
        } else {
            CV[1][152] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+152 );
    }//GEN-LAST:event_jF5_SHActionPerformed
    private void jF5_SPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF5_SPActionPerformed
        jF5_TP.setSelected(false);
        jF5_SH.setSelected(false);
        jF5_Gl.setSelected(false);
        jF17_TP.setSelected(false);
        jF17_SP.setSelected(false);
        jF17_SH.setSelected(false);
        jF17_Gl.setSelected(false);
        if(jF5_SP.isSelected()) {
            CV[1][152] = 16;
        } else {
            CV[1][152] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+152 );
    }//GEN-LAST:event_jF5_SPActionPerformed
    private void jF11_GlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF11_GlActionPerformed
        jF11_TP.setSelected(false);
        jF11_SH.setSelected(false);
        jF11_SP.setSelected(false);
        jF23_TP.setSelected(false);
        jF23_SP.setSelected(false);
        jF23_SH.setSelected(false);
        jF23_Gl.setSelected(false);
        if(jF1_Gl.isSelected()) {
            CV[1][158] = 64;
        } else {
            CV[1][158] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+158 );
    }//GEN-LAST:event_jF11_GlActionPerformed
    private void jF11_SHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF11_SHActionPerformed
        jF11_TP.setSelected(false);
        jF11_SP.setSelected(false);
        jF11_Gl.setSelected(false);
        jF23_TP.setSelected(false);
        jF23_SP.setSelected(false);
        jF23_SH.setSelected(false);
        jF23_Gl.setSelected(false);
        if(jF11_SH.isSelected()) {
            CV[1][158] = 32;
        } else {
            CV[1][158] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+158 );
    }//GEN-LAST:event_jF11_SHActionPerformed
    private void jF9_GlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF9_GlActionPerformed
        jF9_TP.setSelected(false);
        jF9_SH.setSelected(false);
        jF9_SP.setSelected(false);
        jF21_TP.setSelected(false);
        jF21_SP.setSelected(false);
        jF21_SH.setSelected(false);
        jF21_Gl.setSelected(false);
        if(jF1_Gl.isSelected()) {
            CV[1][156] = 64;
        } else {
            CV[1][156] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+159 );
    }//GEN-LAST:event_jF9_GlActionPerformed
    private void jF4_GlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF4_GlActionPerformed
        jF4_TP.setSelected(false);
        jF4_SH.setSelected(false);
        jF4_SP.setSelected(false);
        jF16_TP.setSelected(false);
        jF16_SP.setSelected(false);
        jF16_SH.setSelected(false);
        jF16_Gl.setSelected(false);
        if(jF1_Gl.isSelected()) {
            CV[1][151] = 64;
        } else {
            CV[1][151] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+159 );
    }//GEN-LAST:event_jF4_GlActionPerformed
    private void jF3_GlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF3_GlActionPerformed
        jF3_SH.setSelected(false);
        jF3_SP.setSelected(false);
        jF3_TP.setSelected(false);
        jF15_TP.setSelected(false);
        jF15_SP.setSelected(false);
        jF15_SH.setSelected(false);
        jF15_Gl.setSelected(false);
        if(jF1_Gl.isSelected()) {
            CV[1][150] = 64;
        } else {
            CV[1][150] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+150 );
    }//GEN-LAST:event_jF3_GlActionPerformed
    private void jF2_GlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF2_GlActionPerformed
        jF2_SH.setSelected(false);
        jF2_SP.setSelected(false);
        jF2_TP.setSelected(false);
        jF14_TP.setSelected(false);
        jF14_SP.setSelected(false);
        jF14_SH.setSelected(false);
        jF14_Gl.setSelected(false);
        if(jF1_Gl.isSelected()) {
            CV[1][149] = 64;
        } else {
            CV[1][149] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+149 );
    }//GEN-LAST:event_jF2_GlActionPerformed
    private void jF1_GlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF1_GlActionPerformed
        jF1_SH.setSelected(false);
        jF1_SP.setSelected(false);
        jF1_TP.setSelected(false);
        jF13_TP.setSelected(false);
        jF13_SP.setSelected(false);
        jF13_SH.setSelected(false);
        jF13_Gl.setSelected(false);
        if(jF1_Gl.isSelected()) {
            CV[1][148] = 64;
        } else {
            CV[1][148] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+148 );
    }//GEN-LAST:event_jF1_GlActionPerformed
    private void jF11_SPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF11_SPActionPerformed
        jF11_TP.setSelected(false);
        jF11_SH.setSelected(false);
        jF11_Gl.setSelected(false);
        jF23_TP.setSelected(false);
        jF23_SP.setSelected(false);
        jF23_SH.setSelected(false);
        jF23_Gl.setSelected(false);
        if(jF11_SP.isSelected()) {
            CV[1][158] = 16;
        } else {
            CV[1][158] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+158 );
    }//GEN-LAST:event_jF11_SPActionPerformed
    private void jF9_SHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF9_SHActionPerformed
        jF9_TP.setSelected(false);
        jF9_SP.setSelected(false);
        jF9_Gl.setSelected(false);
        jF21_TP.setSelected(false);
        jF21_SP.setSelected(false);
        jF21_SH.setSelected(false);
        jF21_Gl.setSelected(false);
        if(jF9_SH.isSelected()) {
            CV[1][156] = 32;
        } else {
            CV[1][156] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+156 );
    }//GEN-LAST:event_jF9_SHActionPerformed
    private void jF4_SHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF4_SHActionPerformed
        jF4_TP.setSelected(false);
        jF4_SP.setSelected(false);
        jF4_Gl.setSelected(false);
        jF16_TP.setSelected(false);
        jF16_SP.setSelected(false);
        jF16_SH.setSelected(false);
        jF16_Gl.setSelected(false);
        if(jF4_SH.isSelected()) {
            CV[1][151] = 32;
        } else {
            CV[1][151] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+151 );
    }//GEN-LAST:event_jF4_SHActionPerformed
    private void jF3_SHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF3_SHActionPerformed
        jF3_Gl.setSelected(false);
        jF3_SP.setSelected(false);
        jF3_TP.setSelected(false);
        jF15_TP.setSelected(false);
        jF15_SP.setSelected(false);
        jF15_SH.setSelected(false);
        jF15_Gl.setSelected(false);
        if(jF3_SH.isSelected()) {
            CV[1][150] = 32;
        } else {
            CV[1][150] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+150 );
    }//GEN-LAST:event_jF3_SHActionPerformed
    private void jF2_SHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF2_SHActionPerformed
        jF2_Gl.setSelected(false);
        jF2_SP.setSelected(false);
        jF2_TP.setSelected(false);
        jF14_TP.setSelected(false);
        jF14_SP.setSelected(false);
        jF14_SH.setSelected(false);
        jF14_Gl.setSelected(false);
        if(jF2_SH.isSelected()) {
            CV[1][149] = 32;
        } else {
            CV[1][149] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+149 );
    }//GEN-LAST:event_jF2_SHActionPerformed
    private void jF1_SHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF1_SHActionPerformed
        jF1_Gl.setSelected(false);
        jF1_SP.setSelected(false);
        jF1_TP.setSelected(false);
        jF13_TP.setSelected(false);
        jF13_SP.setSelected(false);
        jF13_SH.setSelected(false);
        jF13_Gl.setSelected(false);
        if(jF1_SH.isSelected()) {
            CV[1][148] = 32;
        } else {
            CV[1][148] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+148 );
    }//GEN-LAST:event_jF1_SHActionPerformed
    private void jF11_TPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF11_TPActionPerformed
        jF11_Gl.setSelected(false);
        jF11_SH.setSelected(false);
        jF11_SP.setSelected(false);
        jF23_TP.setSelected(false);
        jF23_SP.setSelected(false);
        jF23_SH.setSelected(false);
        jF23_Gl.setSelected(false);
        if(jF11_TP.isSelected()) {
            CV[1][158] = 8;
        } else {
            CV[1][158] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+158 );
    }//GEN-LAST:event_jF11_TPActionPerformed
    private void jF9_SPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF9_SPActionPerformed
        jF9_TP.setSelected(false);
        jF9_SH.setSelected(false);
        jF9_Gl.setSelected(false);
        jF21_TP.setSelected(false);
        jF21_SP.setSelected(false);
        jF21_SH.setSelected(false);
        jF21_Gl.setSelected(false);
        if(jF9_SP.isSelected()) {
            CV[1][156] = 16;
        } else {
            CV[1][156] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+156 );
    }//GEN-LAST:event_jF9_SPActionPerformed
    private void jF4_SPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF4_SPActionPerformed
        jF4_TP.setSelected(false);
        jF4_SH.setSelected(false);
        jF4_Gl.setSelected(false);
        jF16_TP.setSelected(false);
        jF16_SP.setSelected(false);
        jF16_SH.setSelected(false);
        jF16_Gl.setSelected(false);
        if(jF4_SP.isSelected()) {
            CV[1][151] = 16;
        } else {
            CV[1][151] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+151 );
    }//GEN-LAST:event_jF4_SPActionPerformed
    private void jF3_SPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF3_SPActionPerformed
        jF3_Gl.setSelected(false);
        jF3_SH.setSelected(false);
        jF3_TP.setSelected(false);
        jF15_TP.setSelected(false);
        jF15_SP.setSelected(false);
        jF15_SH.setSelected(false);
        jF15_Gl.setSelected(false);
        if(jF3_SP.isSelected()) {
            CV[1][150] = 16;
        } else {
            CV[1][150] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+151 );
    }//GEN-LAST:event_jF3_SPActionPerformed
    private void jF2_SPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF2_SPActionPerformed
        jF2_Gl.setSelected(false);
        jF2_SH.setSelected(false);
        jF2_TP.setSelected(false);
        jF14_TP.setSelected(false);
        jF14_SP.setSelected(false);
        jF14_SH.setSelected(false);
        jF14_Gl.setSelected(false);
        if(jF2_SP.isSelected()) {
            CV[1][149] = 16;
        } else {
            CV[1][149] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+149 );
    }//GEN-LAST:event_jF2_SPActionPerformed
    private void jF1_SPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF1_SPActionPerformed
        jF1_Gl.setSelected(false);
        jF1_SH.setSelected(false);
        jF1_TP.setSelected(false);
        jF13_TP.setSelected(false);
        jF13_SP.setSelected(false);
        jF13_SH.setSelected(false);
        jF13_Gl.setSelected(false);
        if(jF1_SP.isSelected()) {
            CV[1][148] = 16;
        } else {
            CV[1][148] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+148 );
    }//GEN-LAST:event_jF1_SPActionPerformed
    private void jF5_TPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF5_TPActionPerformed
        jF5_Gl.setSelected(false);
        jF5_SH.setSelected(false);
        jF5_SP.setSelected(false);
        jF17_TP.setSelected(false);
        jF17_SP.setSelected(false);
        jF17_SH.setSelected(false);
        jF17_Gl.setSelected(false);
        if(jF5_TP.isSelected()) {
            CV[1][152] = 8;
        } else {
            CV[1][152] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+152 );
    }//GEN-LAST:event_jF5_TPActionPerformed
    private void jF4_TPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF4_TPActionPerformed
        jF4_SP.setSelected(false);
        jF4_SH.setSelected(false);
        jF4_Gl.setSelected(false);
        jF16_TP.setSelected(false);
        jF16_SP.setSelected(false);
        jF16_SH.setSelected(false);
        jF16_Gl.setSelected(false);
        if(jF4_TP.isSelected()) {
            CV[1][151] = 8;
        } else {
            CV[1][151] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+151 );
    }//GEN-LAST:event_jF4_TPActionPerformed
    private void jF3_TPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF3_TPActionPerformed
        jF3_Gl.setSelected(false);
        jF3_SH.setSelected(false);
        jF3_SP.setSelected(false);
        jF15_TP.setSelected(false);
        jF15_SP.setSelected(false);
        jF15_SH.setSelected(false);
        jF15_Gl.setSelected(false);
        if(jF3_TP.isSelected()) {
            CV[1][150] = 8;
        } else {
            CV[1][150] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+150 );
    }//GEN-LAST:event_jF3_TPActionPerformed
    private void jF2_TPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF2_TPActionPerformed
        jF2_Gl.setSelected(false);
        jF2_SH.setSelected(false);
        jF2_SP.setSelected(false);
        jF14_TP.setSelected(false);
        jF14_SP.setSelected(false);
        jF14_SH.setSelected(false);
        jF14_Gl.setSelected(false);
        if(jF2_TP.isSelected()) {
            CV[1][149] = 8;
        } else {
            CV[1][149] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+149 );
    }//GEN-LAST:event_jF2_TPActionPerformed
    private void jF1_TPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF1_TPActionPerformed
        jF1_Gl.setSelected(false);
        jF1_SH.setSelected(false);
        jF1_SP.setSelected(false);
        jF13_TP.setSelected(false);
        jF13_SP.setSelected(false);
        jF13_SH.setSelected(false);
        jF13_Gl.setSelected(false);
        if(jF1_TP.isSelected()) {
            CV[1][148] = 8;
        } else {
            CV[1][148] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+148 );
    }//GEN-LAST:event_jF1_TPActionPerformed
    private void jF13_TPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF13_TPActionPerformed
        jF1_Gl.setSelected(false);
        jF1_SH.setSelected(false);
        jF1_SP.setSelected(false);
        jF1_TP.setSelected(false);
        jF13_SP.setSelected(false);
        jF13_SH.setSelected(false);
        jF13_Gl.setSelected(false);
        if(jF13_TP.isSelected()) {
            CV[1][148] = 136;
        } else {
            CV[1][148] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+148 );
    }//GEN-LAST:event_jF13_TPActionPerformed
     
    private void jF14_TPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF14_TPActionPerformed
        jF2_Gl.setSelected(false);
        jF2_SH.setSelected(false);
        jF2_SP.setSelected(false);
        jF2_TP.setSelected(false);
        jF14_SP.setSelected(false);
        jF14_SH.setSelected(false);
        jF14_Gl.setSelected(false);
        if(jF14_TP.isSelected()) {
            CV[1][149] = 136;
        } else {
            CV[1][149] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+149 );
    }//GEN-LAST:event_jF14_TPActionPerformed

    private void jF15_TPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF15_TPActionPerformed
        jF3_Gl.setSelected(false);
        jF3_SH.setSelected(false);
        jF3_SP.setSelected(false);
        jF3_TP.setSelected(false);
        jF15_SP.setSelected(false);
        jF15_SH.setSelected(false);
        jF15_Gl.setSelected(false);
        if(jF15_TP.isSelected()) {
            CV[1][150] = 136;
        } else {
            CV[1][150] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+150 );
    }//GEN-LAST:event_jF15_TPActionPerformed

    private void jF16_TPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF16_TPActionPerformed
        jF4_Gl.setSelected(false);
        jF4_SH.setSelected(false);
        jF4_SP.setSelected(false);
        jF4_TP.setSelected(false);
        jF16_SP.setSelected(false);
        jF16_SH.setSelected(false);
        jF16_Gl.setSelected(false);
        if(jF16_TP.isSelected()) {
            CV[1][151] = 136;
        } else {
            CV[1][151] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+151 );
    }//GEN-LAST:event_jF16_TPActionPerformed

    private void jF17_TPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF17_TPActionPerformed
        jF5_Gl.setSelected(false);
        jF5_SH.setSelected(false);
        jF5_SP.setSelected(false);
        jF5_TP.setSelected(false);
        jF17_SP.setSelected(false);
        jF17_SH.setSelected(false);
        jF17_Gl.setSelected(false);
        if(jF17_TP.isSelected()) {
            CV[1][152] = 136;
        } else {
            CV[1][152] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+152 );
    }//GEN-LAST:event_jF17_TPActionPerformed

    private void jF13_SPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF13_SPActionPerformed
        jF1_Gl.setSelected(false);
        jF1_SH.setSelected(false);
        jF1_SP.setSelected(false);
        jF1_TP.setSelected(false);
        jF13_TP.setSelected(false);
        jF13_SH.setSelected(false);
        jF13_Gl.setSelected(false);
        if(jF13_SP.isSelected()) {
            CV[1][148] = 144;
        } else {
            CV[1][148] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+148 );
    }//GEN-LAST:event_jF13_SPActionPerformed

    private void jF14_SPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF14_SPActionPerformed
        jF2_Gl.setSelected(false);
        jF2_SH.setSelected(false);
        jF2_SP.setSelected(false);
        jF2_TP.setSelected(false);
        jF14_TP.setSelected(false);
        jF14_SH.setSelected(false);
        jF14_Gl.setSelected(false);
        if(jF14_SP.isSelected()) {
            CV[1][149] = 144;
        } else {
            CV[1][149] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+149 );
    }//GEN-LAST:event_jF14_SPActionPerformed

    private void jF15_SPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF15_SPActionPerformed
        jF3_Gl.setSelected(false);
        jF3_SH.setSelected(false);
        jF3_SP.setSelected(false);
        jF3_TP.setSelected(false);
        jF15_TP.setSelected(false);
        jF15_SH.setSelected(false);
        jF15_Gl.setSelected(false);
        if(jF15_SP.isSelected()) {
            CV[1][150] = 144;
        } else {
            CV[1][150] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+150 );
    }//GEN-LAST:event_jF15_SPActionPerformed

    private void jF16_SPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF16_SPActionPerformed
        jF4_Gl.setSelected(false);
        jF4_SH.setSelected(false);
        jF4_SP.setSelected(false);
        jF4_TP.setSelected(false);
        jF16_TP.setSelected(false);
        jF16_SH.setSelected(false);
        jF16_Gl.setSelected(false);
        if(jF16_SP.isSelected()) {
            CV[1][151] = 144;
        } else {
            CV[1][151] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+151 );
    }//GEN-LAST:event_jF16_SPActionPerformed

    private void jF21_SPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF21_SPActionPerformed
        jF9_Gl.setSelected(false);
        jF9_SH.setSelected(false);
        jF9_SP.setSelected(false);
        jF9_TP.setSelected(false);
        jF21_TP.setSelected(false);
        jF21_SH.setSelected(false);
        jF21_Gl.setSelected(false);
        if(jF21_SP.isSelected()) {
            CV[1][156] = 144;
        } else {
            CV[1][156] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+156 );
    }//GEN-LAST:event_jF21_SPActionPerformed

    private void jF23_TPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF23_TPActionPerformed
        jF11_Gl.setSelected(false);
        jF11_SH.setSelected(false);
        jF11_SP.setSelected(false);
        jF11_TP.setSelected(false);
        jF23_SP.setSelected(false);
        jF23_SH.setSelected(false);
        jF23_Gl.setSelected(false);
        if(jF23_TP.isSelected()) {
            CV[1][158] = 136;
        } else {
            CV[1][158] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+158 );
    }//GEN-LAST:event_jF23_TPActionPerformed

    private void jF13_SHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF13_SHActionPerformed
        jF1_Gl.setSelected(false);
        jF1_SH.setSelected(false);
        jF1_SP.setSelected(false);
        jF1_TP.setSelected(false);
        jF13_TP.setSelected(false);
        jF13_SP.setSelected(false);
        jF13_Gl.setSelected(false);
        if(jF13_SH.isSelected()) {
            CV[1][148] = 160;
        } else {
            CV[1][148] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+148 );
    }//GEN-LAST:event_jF13_SHActionPerformed

    private void jF14_SHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF14_SHActionPerformed
        jF2_Gl.setSelected(false);
        jF2_SH.setSelected(false);
        jF2_SP.setSelected(false);
        jF2_TP.setSelected(false);
        jF14_TP.setSelected(false);
        jF14_SP.setSelected(false);
        jF14_Gl.setSelected(false);
        if(jF14_SH.isSelected()) {
            CV[1][149] = 160;
        } else {
            CV[1][149] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+149 );
    }//GEN-LAST:event_jF14_SHActionPerformed

    private void jF15_SHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF15_SHActionPerformed
        jF3_Gl.setSelected(false);
        jF3_SH.setSelected(false);
        jF3_SP.setSelected(false);
        jF3_TP.setSelected(false);
        jF15_TP.setSelected(false);
        jF15_SP.setSelected(false);
        jF15_Gl.setSelected(false);
        if(jF15_SH.isSelected()) {
            CV[1][150] = 160;
        } else {
            CV[1][150] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+150 );
    }//GEN-LAST:event_jF15_SHActionPerformed

    private void jF16_SHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF16_SHActionPerformed
        jF4_Gl.setSelected(false);
        jF4_SH.setSelected(false);
        jF4_SP.setSelected(false);
        jF4_TP.setSelected(false);
        jF16_TP.setSelected(false);
        jF16_SP.setSelected(false);
        jF16_Gl.setSelected(false);
        if(jF16_SH.isSelected()) {
            CV[1][151] = 160;
        } else {
            CV[1][151] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+151 );
    }//GEN-LAST:event_jF16_SHActionPerformed

    private void jF21_SHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF21_SHActionPerformed
        jF9_Gl.setSelected(false);
        jF9_SH.setSelected(false);
        jF9_SP.setSelected(false);
        jF9_TP.setSelected(false);
        jF21_TP.setSelected(false);
        jF21_SP.setSelected(false);
        jF21_Gl.setSelected(false);
        if(jF21_SH.isSelected()) {
            CV[1][156] = 160;
        } else {
            CV[1][156] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+156 );
    }//GEN-LAST:event_jF21_SHActionPerformed

    private void jF23_SPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF23_SPActionPerformed
        jF11_Gl.setSelected(false);
        jF11_SH.setSelected(false);
        jF11_SP.setSelected(false);
        jF11_TP.setSelected(false);
        jF23_TP.setSelected(false);
        jF23_SH.setSelected(false);
        jF23_Gl.setSelected(false);
        if(jF23_SP.isSelected()) {
            CV[1][158] = 144;
        } else {
            CV[1][158] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+158 );
    }//GEN-LAST:event_jF23_SPActionPerformed

    private void jF13_GlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF13_GlActionPerformed
        jF1_Gl.setSelected(false);
        jF1_SH.setSelected(false);
        jF1_SP.setSelected(false);
        jF1_TP.setSelected(false);
        jF13_TP.setSelected(false);
        jF13_SP.setSelected(false);
        jF13_SH.setSelected(false);
        if(jF13_Gl.isSelected()) {
            CV[1][148] = 192;
        } else {
            CV[1][148] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+148 );
    }//GEN-LAST:event_jF13_GlActionPerformed

    private void jF14_GlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF14_GlActionPerformed
        jF2_Gl.setSelected(false);
        jF2_SH.setSelected(false);
        jF2_SP.setSelected(false);
        jF2_TP.setSelected(false);
        jF14_TP.setSelected(false);
        jF14_SP.setSelected(false);
        jF14_SH.setSelected(false);
        if(jF14_Gl.isSelected()) {
            CV[1][149] = 192;
        } else {
            CV[1][149] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+149 );
    }//GEN-LAST:event_jF14_GlActionPerformed

    private void jF15_GlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF15_GlActionPerformed
        jF3_Gl.setSelected(false);
        jF3_SH.setSelected(false);
        jF3_SP.setSelected(false);
        jF3_TP.setSelected(false);
        jF15_TP.setSelected(false);
        jF15_SP.setSelected(false);
        jF15_SH.setSelected(false);
        if(jF15_Gl.isSelected()) {
            CV[1][150] = 192;
        } else {
            CV[1][150] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+150 );
    }//GEN-LAST:event_jF15_GlActionPerformed

    private void jF16_GlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF16_GlActionPerformed
        jF4_Gl.setSelected(false);
        jF4_SH.setSelected(false);
        jF4_SP.setSelected(false);
        jF4_TP.setSelected(false);
        jF16_TP.setSelected(false);
        jF16_SP.setSelected(false);
        jF16_SH.setSelected(false);
        if(jF16_Gl.isSelected()) {
            CV[1][151] = 192;
        } else {
            CV[1][151] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+151 );
    }//GEN-LAST:event_jF16_GlActionPerformed

    private void jF21_GlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF21_GlActionPerformed
        jF9_Gl.setSelected(false);
        jF9_SH.setSelected(false);
        jF9_SP.setSelected(false);
        jF9_TP.setSelected(false);
        jF21_TP.setSelected(false);
        jF21_SP.setSelected(false);
        jF21_SH.setSelected(false);
        if(jF21_Gl.isSelected()) {
            CV[1][156] = 192;
        } else {
            CV[1][156] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+156 );
    }//GEN-LAST:event_jF21_GlActionPerformed

    private void jF23_SHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF23_SHActionPerformed
        jF11_Gl.setSelected(false);
        jF11_SH.setSelected(false);
        jF11_SP.setSelected(false);
        jF11_TP.setSelected(false);
        jF23_TP.setSelected(false);
        jF23_SP.setSelected(false);
        jF23_Gl.setSelected(false);
        if(jF23_SH.isSelected()) {
            CV[1][158] = 160;
        } else {
            CV[1][158] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+158 );
    }//GEN-LAST:event_jF23_SHActionPerformed

    private void jF23_GlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF23_GlActionPerformed
        jF11_Gl.setSelected(false);
        jF11_SH.setSelected(false);
        jF11_SP.setSelected(false);
        jF11_TP.setSelected(false);
        jF23_TP.setSelected(false);
        jF23_SP.setSelected(false);
        jF23_SH.setSelected(false);
        if(jF23_Gl.isSelected()) {
            CV[1][158] = 192;
        } else {
            CV[1][158] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+158 );
    }//GEN-LAST:event_jF23_GlActionPerformed

    private void jF17_SPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF17_SPActionPerformed
        jF5_Gl.setSelected(false);
        jF5_SH.setSelected(false);
        jF5_SP.setSelected(false);
        jF5_TP.setSelected(false);
        jF17_TP.setSelected(false);
        jF17_SH.setSelected(false);
        jF17_Gl.setSelected(false);
        if(jF17_SP.isSelected()) {
            CV[1][152] = 144;
        } else {
            CV[1][152] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+152 );
    }//GEN-LAST:event_jF17_SPActionPerformed

    private void jF17_SHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF17_SHActionPerformed
        jF5_Gl.setSelected(false);
        jF5_SH.setSelected(false);
        jF5_SP.setSelected(false);
        jF5_TP.setSelected(false);
        jF17_TP.setSelected(false);
        jF17_SP.setSelected(false);
        jF17_Gl.setSelected(false);
        if(jF17_SH.isSelected()) {
            CV[1][152] = 160;
        } else {
            CV[1][152] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+152 );
    }//GEN-LAST:event_jF17_SHActionPerformed

    private void jF17_GlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF17_GlActionPerformed
        jF5_Gl.setSelected(false);
        jF5_SH.setSelected(false);
        jF5_SP.setSelected(false);
        jF5_TP.setSelected(false);
        jF17_TP.setSelected(false);
        jF17_SP.setSelected(false);
        jF17_SH.setSelected(false);
        if(jF17_Gl.isSelected()) {
            CV[1][152] = 192;
        } else {
            CV[1][152] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+152 );
    }//GEN-LAST:event_jF17_GlActionPerformed

    private void jF22_TPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF22_TPActionPerformed
        jF10_Gl.setSelected(false);
        jF10_SH.setSelected(false);
        jF10_SP.setSelected(false);
        jF10_TP.setSelected(false);
        jF22_SP.setSelected(false);
        jF22_SH.setSelected(false);
        jF22_Gl.setSelected(false);
        if(jF22_TP.isSelected()) {
            CV[1][157] = 136;
        } else {
            CV[1][157] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+157 );
    }//GEN-LAST:event_jF22_TPActionPerformed

    private void jF18_TPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF18_TPActionPerformed
        jF6_Gl.setSelected(false);
        jF6_SH.setSelected(false);
        jF6_SP.setSelected(false);
        jF6_TP.setSelected(false);
        jF18_SP.setSelected(false);
        jF18_SH.setSelected(false);
        jF18_Gl.setSelected(false);
        if(jF18_TP.isSelected()) {
            CV[1][153] = 136;
        } else {
            CV[1][153] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+153 );
    }//GEN-LAST:event_jF18_TPActionPerformed

    private void jF18_SPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF18_SPActionPerformed
        jF6_Gl.setSelected(false);
        jF6_SH.setSelected(false);
        jF6_SP.setSelected(false);
        jF6_TP.setSelected(false);
        jF18_TP.setSelected(false);
        jF18_SH.setSelected(false);
        jF18_Gl.setSelected(false);
        if(jF18_SP.isSelected()) {
            CV[1][153] = 144;
        } else {
            CV[1][153] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+153 );
    }//GEN-LAST:event_jF18_SPActionPerformed

    private void jF18_SHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF18_SHActionPerformed
        jF6_Gl.setSelected(false);
        jF6_SH.setSelected(false);
        jF6_SP.setSelected(false);
        jF6_TP.setSelected(false);
        jF18_TP.setSelected(false);
        jF18_SP.setSelected(false);
        jF18_Gl.setSelected(false);
        if(jF18_SH.isSelected()) {
            CV[1][153] = 160;
        } else {
            CV[1][153] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+153 );
    }//GEN-LAST:event_jF18_SHActionPerformed

    private void jF18_GlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF18_GlActionPerformed
        jF6_Gl.setSelected(false);
        jF6_SH.setSelected(false);
        jF6_SP.setSelected(false);
        jF6_TP.setSelected(false);
        jF18_TP.setSelected(false);
        jF18_SP.setSelected(false);
        jF18_SH.setSelected(false);
        if(jF18_Gl.isSelected()) {
            CV[1][153] = 192;
        } else {
            CV[1][153] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+153 );
    }//GEN-LAST:event_jF18_GlActionPerformed

    private void jF22_SPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF22_SPActionPerformed
        jF10_Gl.setSelected(false);
        jF10_SH.setSelected(false);
        jF10_SP.setSelected(false);
        jF10_TP.setSelected(false);
        jF22_TP.setSelected(false);
        jF22_SH.setSelected(false);
        jF22_Gl.setSelected(false);
        if(jF22_SP.isSelected()) {
            CV[1][157] = 144;
        } else {
            CV[1][157] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+157 );
    }//GEN-LAST:event_jF22_SPActionPerformed

    private void jF24_TPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF24_TPActionPerformed
        jF12_Gl.setSelected(false);
        jF12_SH.setSelected(false);
        jF12_SP.setSelected(false);
        jF12_TP.setSelected(false);
        jF24_SP.setSelected(false);
        jF24_SH.setSelected(false);
        jF24_Gl.setSelected(false);
        if(jF24_TP.isSelected()) {
            CV[1][159] = 136;
        } else {
            CV[1][159] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+159 );
    }//GEN-LAST:event_jF24_TPActionPerformed

    private void jF19_TPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF19_TPActionPerformed
        jF7_Gl.setSelected(false);
        jF7_SH.setSelected(false);
        jF7_SP.setSelected(false);
        jF7_TP.setSelected(false);
        jF19_SP.setSelected(false);
        jF19_SH.setSelected(false);
        jF19_Gl.setSelected(false);
        if(jF19_TP.isSelected()) {
            CV[1][154] = 136;
        } else {
            CV[1][154] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+154 );
    }//GEN-LAST:event_jF19_TPActionPerformed

    private void jF19_SPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF19_SPActionPerformed
        jF7_Gl.setSelected(false);
        jF7_SH.setSelected(false);
        jF7_SP.setSelected(false);
        jF7_TP.setSelected(false);
        jF19_TP.setSelected(false);
        jF19_SH.setSelected(false);
        jF19_Gl.setSelected(false);
        if(jF19_SP.isSelected()) {
            CV[1][154] = 144;
        } else {
            CV[1][154] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+154 );
    }//GEN-LAST:event_jF19_SPActionPerformed

    private void jF19_SHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF19_SHActionPerformed
        jF7_Gl.setSelected(false);
        jF7_SH.setSelected(false);
        jF7_SP.setSelected(false);
        jF7_TP.setSelected(false);
        jF19_TP.setSelected(false);
        jF19_SP.setSelected(false);
        jF19_Gl.setSelected(false);
        if(jF19_SH.isSelected()) {
            CV[1][154] = 160;
        } else {
            CV[1][154] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+154 );
    }//GEN-LAST:event_jF19_SHActionPerformed

    private void jF22_SHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF22_SHActionPerformed
        jF10_Gl.setSelected(false);
        jF10_SH.setSelected(false);
        jF10_SP.setSelected(false);
        jF10_TP.setSelected(false);
        jF22_TP.setSelected(false);
        jF22_SP.setSelected(false);
        jF22_Gl.setSelected(false);
        if(jF22_SH.isSelected()) {
            CV[1][157] = 160;
        } else {
            CV[1][157] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+157 );
    }//GEN-LAST:event_jF22_SHActionPerformed

    private void jF24_SPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF24_SPActionPerformed
        jF12_Gl.setSelected(false);
        jF12_SH.setSelected(false);
        jF12_SP.setSelected(false);
        jF12_TP.setSelected(false);
        jF24_TP.setSelected(false);
        jF24_SH.setSelected(false);
        jF24_Gl.setSelected(false);
        if(jF24_SP.isSelected()) {
            CV[1][159] = 144;
        } else {
            CV[1][159] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+159 );
    }//GEN-LAST:event_jF24_SPActionPerformed

    private void jF19_GlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF19_GlActionPerformed
        jF7_Gl.setSelected(false);
        jF7_SH.setSelected(false);
        jF7_SP.setSelected(false);
        jF7_TP.setSelected(false);
        jF19_TP.setSelected(false);
        jF19_SP.setSelected(false);
        jF19_SH.setSelected(false);
        if(jF19_Gl.isSelected()) {
            CV[1][154] = 192;
        } else {
            CV[1][154] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+154 );
    }//GEN-LAST:event_jF19_GlActionPerformed

    private void jF20_TPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF20_TPActionPerformed
        jF8_Gl.setSelected(false);
        jF8_SH.setSelected(false);
        jF8_SP.setSelected(false);
        jF8_TP.setSelected(false);
        jF20_SP.setSelected(false);
        jF20_SH.setSelected(false);
        jF20_Gl.setSelected(false);
        if(jF20_TP.isSelected()) {
            CV[1][155] = 136;
        } else {
            CV[1][155] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+155 );
    }//GEN-LAST:event_jF20_TPActionPerformed

    private void jF20_SPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF20_SPActionPerformed
        jF8_Gl.setSelected(false);
        jF8_SH.setSelected(false);
        jF8_SP.setSelected(false);
        jF8_TP.setSelected(false);
        jF20_TP.setSelected(false);
        jF20_SH.setSelected(false);
        jF20_Gl.setSelected(false);
        if(jF20_SP.isSelected()) {
            CV[1][155] = 144;
        } else {
            CV[1][155] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+155 );
    }//GEN-LAST:event_jF20_SPActionPerformed

    private void jF22_GlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF22_GlActionPerformed
        jF10_Gl.setSelected(false);
        jF10_SH.setSelected(false);
        jF10_SP.setSelected(false);
        jF10_TP.setSelected(false);
        jF22_TP.setSelected(false);
        jF22_SP.setSelected(false);
        jF22_SH.setSelected(false);
        if(jF22_Gl.isSelected()) {
            CV[1][157] = 192;
        } else {
            CV[1][157] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+157 );
    }//GEN-LAST:event_jF22_GlActionPerformed

    private void jF24_SHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF24_SHActionPerformed
        jF12_Gl.setSelected(false);
        jF12_SH.setSelected(false);
        jF12_SP.setSelected(false);
        jF12_TP.setSelected(false);
        jF24_TP.setSelected(false);
        jF24_SP.setSelected(false);
        jF24_Gl.setSelected(false);
        if(jF24_SH.isSelected()) {
            CV[1][159] = 160;
        } else {
            CV[1][159] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+158 );
    }//GEN-LAST:event_jF24_SHActionPerformed

    private void jF20_SHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF20_SHActionPerformed
        jF8_Gl.setSelected(false);
        jF8_SH.setSelected(false);
        jF8_SP.setSelected(false);
        jF8_TP.setSelected(false);
        jF20_TP.setSelected(false);
        jF20_SP.setSelected(false);
        jF20_Gl.setSelected(false);
        if(jF20_SH.isSelected()) {
            CV[1][155] = 160;
        } else {
            CV[1][155] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+155 );
    }//GEN-LAST:event_jF20_SHActionPerformed

    private void jF20_GlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF20_GlActionPerformed
        jF8_Gl.setSelected(false);
        jF8_SH.setSelected(false);
        jF8_SP.setSelected(false);
        jF8_TP.setSelected(false);
        jF20_TP.setSelected(false);
        jF20_SP.setSelected(false);
        jF20_SH.setSelected(false);
        if(jF20_Gl.isSelected()) {
            CV[1][155] = 192;
        } else {
            CV[1][155] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+155 );
    }//GEN-LAST:event_jF20_GlActionPerformed

    private void jF21_TPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF21_TPActionPerformed
        jF9_Gl.setSelected(false);
        jF9_SH.setSelected(false);
        jF9_SP.setSelected(false);
        jF9_TP.setSelected(false);
        jF21_SP.setSelected(false);
        jF21_SH.setSelected(false);
        jF21_Gl.setSelected(false);
        if(jF21_TP.isSelected()) {
            CV[1][156] = 136;
        } else {
            CV[1][156] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+156 );
    }//GEN-LAST:event_jF21_TPActionPerformed

    private void jF24_GlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF24_GlActionPerformed
        jF12_Gl.setSelected(false);
        jF12_SH.setSelected(false);
        jF12_SP.setSelected(false);
        jF12_TP.setSelected(false);
        jF24_TP.setSelected(false);
        jF24_SP.setSelected(false);
        jF24_SH.setSelected(false);
        if(jF24_Gl.isSelected()) {
            CV[1][159] = 192;
        } else {
            CV[1][159] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+159 );
    }//GEN-LAST:event_jF24_GlActionPerformed

    private void jLautSliderMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLautSliderMouseReleased
        CV[1][160] = 255 - jLautSlider.getValue();
        jLaut.setText("" + CV[1][160]);
        jCV_Anzeige.setSelectedItem( "CV#"+160 );
    }//GEN-LAST:event_jLautSliderMouseReleased

    private void jLautFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jLautFocusLost
        CV[1][160] = KTUI.checkTextField( this, jLaut, 0, 255, 0, true );
        jCV_Anzeige.setSelectedItem( "CV#"+160 );
        jLautSlider.setValue(255 - CV[1][160]);
    }//GEN-LAST:event_jLautFocusLost

    private void jLautKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLautKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jLautKeyReleased

    private void jIn3IsServoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn3IsServoActionPerformed
        if(jIn3IsServo.isSelected()) {
            CV[1][63] = 0;
            CV[1][64] = 0;
            CV[1][168] = 1;
        } else {
            CV[1][167] = 0;
            CV[1][168] = 0;
            jServoF5.setSelected(false);
            jServoF6.setSelected(false);
            jServoF7.setSelected(false);
            jServoF8.setSelected(false);
            jServoF9.setSelected(false);
            jServoF10.setSelected(false);
            jServoF11.setSelected(false);
            jServoF12.setSelected(false);
        }
        jCV_Anzeige.setSelectedItem( "CV#"+167 );
    }//GEN-LAST:event_jIn3IsServoActionPerformed

    private void jServoF9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jServoF9ActionPerformed
        if(jServoF9.isSelected()) {
            CV[1][63] = 0;
            CV[1][64] = 0;
            CV[1][167] |= 16;
            jIn3IsServo.setSelected(true);
        } else {
            CV[1][167] &= ~16;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+167 );
    }//GEN-LAST:event_jServoF9ActionPerformed

    private void jServoF10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jServoF10ActionPerformed
        if(jServoF10.isSelected()) {
            CV[1][63] = 0;
            CV[1][64] = 0;
            CV[1][167] |= 32;
            jIn3IsServo.setSelected(true);
        } else {
            CV[1][167] &= ~32;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+167 );
    }//GEN-LAST:event_jServoF10ActionPerformed

    private void jServoF12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jServoF12ActionPerformed
        if(jServoF12.isSelected()) {
            CV[1][63] = 0;
            CV[1][64] = 0;
            CV[1][167] |= 128;
            jIn3IsServo.setSelected(true);
        } else {
            CV[1][167] &= ~128;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+167 );
    }//GEN-LAST:event_jServoF12ActionPerformed

    private void jServoF5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jServoF5ActionPerformed
        if(jServoF5.isSelected()) {
            CV[1][63] = 0;
            CV[1][64] = 0;
            CV[1][167] |= 1;
            jIn3IsServo.setSelected(true);
        } else {
            CV[1][167] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+167 );
    }//GEN-LAST:event_jServoF5ActionPerformed

    private void jServoF6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jServoF6ActionPerformed
        if(jServoF6.isSelected()) {
            CV[1][63] = 0;
            CV[1][64] = 0;
            CV[1][167] |= 2;
            jIn3IsServo.setSelected(true);
        } else {
            CV[1][167] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+167 );
    }//GEN-LAST:event_jServoF6ActionPerformed

    private void jServoF7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jServoF7ActionPerformed
        if(jServoF7.isSelected()) {
            CV[1][63] = 0;
            CV[1][64] = 0;
            CV[1][167] |= 4;
            jIn3IsServo.setSelected(true);
        } else {
            CV[1][167] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+167 );
    }//GEN-LAST:event_jServoF7ActionPerformed

    private void jServoF8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jServoF8ActionPerformed
        if(jServoF8.isSelected()) {
            CV[1][63] = 0;
            CV[1][64] = 0;
            CV[1][167] |= 8;
            jIn3IsServo.setSelected(true);
        } else {
            CV[1][167] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+167 );
    }//GEN-LAST:event_jServoF8ActionPerformed

    private void jGeschwindigkeitFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jGeschwindigkeitFocusLost
        CV[1][171] = KTUI.checkTextField( this, jGeschwindigkeit, 0, 255, 16, true );
        jCV_Anzeige.setSelectedItem( "CV#"+171 );
    }//GEN-LAST:event_jGeschwindigkeitFocusLost

    private void jGeschwindigkeitKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jGeschwindigkeitKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jGeschwindigkeitKeyReleased

    private void jServoComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jServoComponentShown
        jCV_Anzeige.setSelectedItem( "CV#"+167 );

        if((CV[1][167] > 0) || (CV[1][168]) > 0)
            jIn3IsServo.setSelected(true);
        else
            jIn3IsServo.setSelected(false);
        //------------- Servo-Funktionen ------------------
        jServoF5.setSelected( (CV[1][167] & 0x01) == 0x01);
        jServoF6.setSelected( (CV[1][167] & 0x02) == 0x02);
        jServoF7.setSelected( (CV[1][167] & 0x04) == 0x04);
        jServoF8.setSelected( (CV[1][167] & 0x08) == 0x08);
        jServoF9.setSelected( (CV[1][167] & 0x10) == 0x10);
        jServoF10.setSelected((CV[1][167] & 0x20) == 0x20);
        jServoF11.setSelected((CV[1][167] & 0x40) == 0x40);
        jServoF12.setSelected((CV[1][167] & 0x80) == 0x80);

        jlinkerAnschlag.setText("" + CV[1][169]);
        jrechterAnschlag.setText("" + CV[1][170]);
        jGeschwindigkeit.setText("" + CV[1][171]);

        //------------- FS0-Funktionen ------------------
        jFS0_F1.setSelected((CV[1][173] & 0x01) == 0x01);
        jFS0_F2.setSelected((CV[1][173] & 0x02) == 0x02);
        jFS0_F3.setSelected((CV[1][173] & 0x04) == 0x04);
        jFS0_F4.setSelected((CV[1][173] & 0x08) == 0x08);
        jFS0_F5.setSelected((CV[1][173] & 0x10) == 0x10);
        jFS0_F6.setSelected((CV[1][173] & 0x20) == 0x20);
        jFS0_F7.setSelected((CV[1][173] & 0x40) == 0x40);
        jFS0_F8.setSelected((CV[1][173] & 0x80) == 0x80);

        jFS0_AUX1.setSelected( (CV[1][174] & 0x01) == 0x01);
        jFS0_AUX2.setSelected( (CV[1][174] & 0x02) == 0x02);
        jFS0_AUX3.setSelected( (CV[1][174] & 0x04) == 0x04);
        jFS0_AUX4.setSelected( (CV[1][174] & 0x08) == 0x08);
        jFS0_AUX5.setSelected( (CV[1][174] & 0x10) == 0x10);
        jFS0_AUX6.setSelected( (CV[1][174] & 0x20) == 0x20);

        jFS0_AUX7.setSelected( (CV[1][175] & 0x01) == 0x01);
        jFS0_AUX8.setSelected( (CV[1][175] & 0x02) == 0x02);
        jFS0_AUX9.setSelected( (CV[1][175] & 0x04) == 0x04);
        jFS0_AUX10.setSelected((CV[1][175] & 0x08) == 0x08);
        jFS0_AUX11.setSelected((CV[1][175] & 0x10) == 0x10);
        jFS0_AUX12.setSelected((CV[1][175] & 0x20) == 0x20);

        jFS0_AUXinv1.setSelected((CV[1][176] & 0x01) == 0x01);
        jFS0_AUXinv2.setSelected((CV[1][176] & 0x02) == 0x02);
        jFS0_AUXinv3.setSelected((CV[1][176] & 0x04) == 0x04);
        jFS0_AUXinv4.setSelected((CV[1][176] & 0x08) == 0x08);
        jFS0_AUXinv5.setSelected((CV[1][176] & 0x10) == 0x10);
        jFS0_AUXinv6.setSelected((CV[1][176] & 0x20) == 0x20);

    }//GEN-LAST:event_jServoComponentShown

    private void jServoF11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jServoF11ActionPerformed
        if(jServoF11.isSelected()) {
            CV[1][63] = 0;
            CV[1][64] = 0;
            CV[1][167] |= 64;
            jIn3IsServo.setSelected(true);
        } else {
            CV[1][167] &= ~64;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+167 );
    }//GEN-LAST:event_jServoF11ActionPerformed

    private void jlinkerAnschlagFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jlinkerAnschlagFocusLost
        // Maximum ist Wert(rechterAnschlag) CV[1][170]
        // default MIN( 112, Wert(rechterAnschlag)
        int rechterAnschlag =  CV[1][170];
        int defValue = 112;
        if( rechterAnschlag < 112 ) {
            defValue = rechterAnschlag;
        }
        CV[1][169] = KTUI.checkTextField( this, jlinkerAnschlag, 40, rechterAnschlag, defValue, true );
        jCV_Anzeige.setSelectedItem( "CV#"+169 );
    }//GEN-LAST:event_jlinkerAnschlagFocusLost

    private void jlinkerAnschlagKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jlinkerAnschlagKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jlinkerAnschlagKeyReleased

    private void jrechterAnschlagFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jrechterAnschlagFocusLost
        // Minimum ist MIN( 40, Wert(linkerAnschlag) CV[1][170] )
        // default MAX( 176, Wert(linkerAnschlag)
        int linkerAnschlag = CV[1][169];
        int defValue = 176;
        if( linkerAnschlag > 176 ) {
            defValue = linkerAnschlag;
        }
        CV[1][170] = KTUI.checkTextField( this, jrechterAnschlag, linkerAnschlag, 250, defValue, true );
        jCV_Anzeige.setSelectedItem( "CV#"+170 );
    }//GEN-LAST:event_jrechterAnschlagFocusLost

    private void jrechterAnschlagKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jrechterAnschlagKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jrechterAnschlagKeyReleased

    private void jFS0_F5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFS0_F5ActionPerformed
        if(jFS0_F5.isSelected()) {
            CV[1][173] |= 16;
        } else {
            CV[1][173] &= ~16;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+173 );
    }//GEN-LAST:event_jFS0_F5ActionPerformed

    private void jFS0_F6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFS0_F6ActionPerformed
        if(jFS0_F6.isSelected()) {
            CV[1][173] |= 32;
        } else {
            CV[1][173] &= ~32;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+173 );
    }//GEN-LAST:event_jFS0_F6ActionPerformed

    private void jFS0_F7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFS0_F7ActionPerformed
        if(jFS0_F7.isSelected()) {
            CV[1][173] |= 64;
        } else {
            CV[1][173] &= ~64;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+173 );
    }//GEN-LAST:event_jFS0_F7ActionPerformed

    private void jFS0_F8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFS0_F8ActionPerformed
        if(jFS0_F8.isSelected()) {
            CV[1][173] |= 128;
        } else {
            CV[1][173] &= ~128;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+173 );
    }//GEN-LAST:event_jFS0_F8ActionPerformed

    private void jFS0_F1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFS0_F1ActionPerformed
        if(jFS0_F1.isSelected()) {
            CV[1][173] |= 1;
        } else {
            CV[1][173] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+173 );
    }//GEN-LAST:event_jFS0_F1ActionPerformed

    private void jFS0_F2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFS0_F2ActionPerformed
        if(jFS0_F2.isSelected()) {
            CV[1][173] |= 2;
        } else {
            CV[1][173] &= 21;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+173 );
    }//GEN-LAST:event_jFS0_F2ActionPerformed

    private void jFS0_F3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFS0_F3ActionPerformed
        if(jFS0_F3.isSelected()) {
            CV[1][173] |= 4;
        } else {
            CV[1][173] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+173 );
    }//GEN-LAST:event_jFS0_F3ActionPerformed

    private void jFS0_F4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFS0_F4ActionPerformed
        if(jFS0_F4.isSelected()) {
            CV[1][173] |= 8;
        } else {
            CV[1][173] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+173 );
    }//GEN-LAST:event_jFS0_F4ActionPerformed

    private void jFS0_AUX1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFS0_AUX1ActionPerformed
        if(jFS0_AUX1.isSelected()) {
            CV[1][174] |= 1;
        } else {
            CV[1][174] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+174 );
    }//GEN-LAST:event_jFS0_AUX1ActionPerformed

    private void jFS0_AUX2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFS0_AUX2ActionPerformed
        if(jFS0_AUX2.isSelected()) {
            CV[1][174] |= 2;
        } else {
            CV[1][174] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+174 );
    }//GEN-LAST:event_jFS0_AUX2ActionPerformed

    private void jFS0_AUX3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFS0_AUX3ActionPerformed
        if(jFS0_AUX3.isSelected()) {
            CV[1][174] |= 4;
        } else {
            CV[1][174] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+174 );
    }//GEN-LAST:event_jFS0_AUX3ActionPerformed

    private void jFS0_AUX4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFS0_AUX4ActionPerformed
        if(jFS0_AUX4.isSelected()) {
            CV[1][174] |= 8;
        } else {
            CV[1][174] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+174 );
    }//GEN-LAST:event_jFS0_AUX4ActionPerformed

    private void jFS0_AUX5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFS0_AUX5ActionPerformed
        if(jFS0_AUX5.isSelected()) {
            CV[1][174] |= 16;
        } else {
            CV[1][174] &= ~16;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+174 );
    }//GEN-LAST:event_jFS0_AUX5ActionPerformed

    private void jFS0_AUX6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFS0_AUX6ActionPerformed
        if(jFS0_AUX6.isSelected()) {
            CV[1][174] |= 32;
        } else {
            CV[1][174] &= ~32;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+174 );
    }//GEN-LAST:event_jFS0_AUX6ActionPerformed

    private void jFS0_AUXinv1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFS0_AUXinv1ActionPerformed
        if(jFS0_AUXinv1.isSelected()) {
            CV[1][176] |= 1;
        } else {
            CV[1][176] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+176 );
    }//GEN-LAST:event_jFS0_AUXinv1ActionPerformed

    private void jFS0_AUXinv2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFS0_AUXinv2ActionPerformed
        if(jFS0_AUXinv2.isSelected()) {
            CV[1][176] |= 2;
        } else {
            CV[1][176] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+176 );
    }//GEN-LAST:event_jFS0_AUXinv2ActionPerformed

    private void jFS0_AUXinv3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFS0_AUXinv3ActionPerformed
        if(jFS0_AUXinv3.isSelected()) {
            CV[1][176] |= 4;
        } else {
            CV[1][176] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+176 );
    }//GEN-LAST:event_jFS0_AUXinv3ActionPerformed

    private void jFS0_AUXinv4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFS0_AUXinv4ActionPerformed
        if(jFS0_AUXinv4.isSelected()) {
            CV[1][176] |= 8;
        } else {
            CV[1][176] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+176 );
    }//GEN-LAST:event_jFS0_AUXinv4ActionPerformed

    private void jFS0_AUXinv5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFS0_AUXinv5ActionPerformed
        if(jFS0_AUXinv5.isSelected()) {
            CV[1][176] |= 16;
        } else {
            CV[1][176] &= ~16;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+176 );
    }//GEN-LAST:event_jFS0_AUXinv5ActionPerformed

    private void jFS0_AUXinv6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFS0_AUXinv6ActionPerformed
        if(jFS0_AUXinv6.isSelected()) {
            CV[1][176] |= 32;
        } else {
            CV[1][176] &= ~32;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+176 );
    }//GEN-LAST:event_jFS0_AUXinv6ActionPerformed

    private void jFS0_AUX7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFS0_AUX7ActionPerformed
        if(jFS0_AUX7.isSelected()) {
            CV[1][175] |= 1;
        } else {
            CV[1][175] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+175 );
    }//GEN-LAST:event_jFS0_AUX7ActionPerformed

    private void jFS0_AUX8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFS0_AUX8ActionPerformed
        if(jFS0_AUX8.isSelected()) {
            CV[1][175] |= 2;
        } else {
            CV[1][175] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+175 );
    }//GEN-LAST:event_jFS0_AUX8ActionPerformed

    private void jFS0_AUX9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFS0_AUX9ActionPerformed
        if(jFS0_AUX9.isSelected()) {
            CV[1][175] |= 4;
        } else {
            CV[1][175] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+175 );
    }//GEN-LAST:event_jFS0_AUX9ActionPerformed

    private void jFS0_AUX10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFS0_AUX10ActionPerformed
        if(jFS0_AUX10.isSelected()) {
            CV[1][175] |= 8;
        } else {
            CV[1][175] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+175 );
    }//GEN-LAST:event_jFS0_AUX10ActionPerformed

    private void jFS0_AUX11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFS0_AUX11ActionPerformed
        if(jFS0_AUX11.isSelected()) {
            CV[1][175] |= 16;
        } else {
            CV[1][175] &= ~16;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+175 );
    }//GEN-LAST:event_jFS0_AUX11ActionPerformed

    private void jFS0_AUX12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFS0_AUX12ActionPerformed
        if(jFS0_AUX12.isSelected()) {
            CV[1][175] |= 32;
        } else {
            CV[1][175] &= ~32;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+175 );
    }//GEN-LAST:event_jFS0_AUX12ActionPerformed

    private void jAbbrechenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAbbrechenActionPerformed
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

    private void jMM_Addr_2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jMM_Addr_2FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+47 );
    }//GEN-LAST:event_jMM_Addr_2FocusGained

    private void jBlink_Einschaltzeit_1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlink_Einschaltzeit_1FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+132 );
    }//GEN-LAST:event_jBlink_Einschaltzeit_1FocusGained

    private void jBlink_Einschaltzeit_2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlink_Einschaltzeit_2FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+133 );
    }//GEN-LAST:event_jBlink_Einschaltzeit_2FocusGained

    private void jBlink_Einschaltzeit_3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlink_Einschaltzeit_3FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+134 );
    }//GEN-LAST:event_jBlink_Einschaltzeit_3FocusGained

    private void jBlink_Einschaltzeit_4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlink_Einschaltzeit_4FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+135 );
    }//GEN-LAST:event_jBlink_Einschaltzeit_4FocusGained

    private void jBlink_Einschaltzeit_5FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlink_Einschaltzeit_5FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+136 );
    }//GEN-LAST:event_jBlink_Einschaltzeit_5FocusGained

    private void jBlink_Einschaltzeit_6FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlink_Einschaltzeit_6FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+137 );
    }//GEN-LAST:event_jBlink_Einschaltzeit_6FocusGained

    private void jBlink_Pausezeit_1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlink_Pausezeit_1FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+140 );
    }//GEN-LAST:event_jBlink_Pausezeit_1FocusGained

    private void jBlink_Pausezeit_2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlink_Pausezeit_2FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+141 );
    }//GEN-LAST:event_jBlink_Pausezeit_2FocusGained

    private void jBlink_Pausezeit_3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlink_Pausezeit_3FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+142 );
    }//GEN-LAST:event_jBlink_Pausezeit_3FocusGained

    private void jBlink_Pausezeit_4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlink_Pausezeit_4FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+143 );
    }//GEN-LAST:event_jBlink_Pausezeit_4FocusGained

    private void jBlink_Pausezeit_5FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlink_Pausezeit_5FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+144 );
    }//GEN-LAST:event_jBlink_Pausezeit_5FocusGained

    private void jBlink_Pausezeit_6FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlink_Pausezeit_6FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+145 );
    }//GEN-LAST:event_jBlink_Pausezeit_6FocusGained

    private void jBlinkfrequenz1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlinkfrequenz1FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+106 );
    }//GEN-LAST:event_jBlinkfrequenz1FocusGained

    private void jBlinkfrequenz2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlinkfrequenz2FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+107 );
    }//GEN-LAST:event_jBlinkfrequenz2FocusGained

    private void jBlinkfrequenz3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlinkfrequenz3FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+108 );
    }//GEN-LAST:event_jBlinkfrequenz3FocusGained

    private void jBlinkfrequenz4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlinkfrequenz4FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+109 );
    }//GEN-LAST:event_jBlinkfrequenz4FocusGained

    private void jBlinkfrequenz5FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlinkfrequenz5FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+110 );
    }//GEN-LAST:event_jBlinkfrequenz5FocusGained

    private void jBlinkfrequenz6FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlinkfrequenz6FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+111 );
    }//GEN-LAST:event_jBlinkfrequenz6FocusGained

    private void jBlinkfrequenzMarsFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlinkfrequenzMarsFocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+112 );
    }//GEN-LAST:event_jBlinkfrequenzMarsFocusGained

    private void jDimmen1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmen1FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+115 );
    }//GEN-LAST:event_jDimmen1FocusGained

    private void jDimmen2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmen2FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+116 );
    }//GEN-LAST:event_jDimmen2FocusGained

    private void jDimmen3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmen3FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+117 );
    }//GEN-LAST:event_jDimmen3FocusGained

    private void jDimmen4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmen4FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+118 );
    }//GEN-LAST:event_jDimmen4FocusGained

    private void jDimmFS1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmFS1FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+123 );
    }//GEN-LAST:event_jDimmFS1FocusGained

    private void jDimmFS2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmFS2FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+124 );
    }//GEN-LAST:event_jDimmFS2FocusGained

    private void jDimmFS3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmFS3FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+125 );
    }//GEN-LAST:event_jDimmFS3FocusGained

    private void jDimmFS4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmFS4FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+126 );
    }//GEN-LAST:event_jDimmFS4FocusGained

    private void jDimm_FSFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimm_FSFocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+113 );
    }//GEN-LAST:event_jDimm_FSFocusGained

    private void jKickvorFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jKickvorFocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+162 );
    }//GEN-LAST:event_jKickvorFocusGained

    private void jKickrueckFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jKickrueckFocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+163 );
    }//GEN-LAST:event_jKickrueckFocusGained

    private void jMindestSchlt1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jMindestSchlt1FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+119 );
    }//GEN-LAST:event_jMindestSchlt1FocusGained

    private void jMindestSchlt2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jMindestSchlt2FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+120 );
    }//GEN-LAST:event_jMindestSchlt2FocusGained

    private void jMindestSchlt3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jMindestSchlt3FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+121 );
    }//GEN-LAST:event_jMindestSchlt3FocusGained

    private void jLautFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jLautFocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+160 );
    }//GEN-LAST:event_jLautFocusGained

    private void jlinkerAnschlagFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jlinkerAnschlagFocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+169 );
    }//GEN-LAST:event_jlinkerAnschlagFocusGained

    private void jrechterAnschlagFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jrechterAnschlagFocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+170 );
    }//GEN-LAST:event_jrechterAnschlagFocusGained

    private void jGeschwindigkeitFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jGeschwindigkeitFocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+171 );
    }//GEN-LAST:event_jGeschwindigkeitFocusGained

    private void jPacketTimeOutFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPacketTimeOutFocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+11 );
    }//GEN-LAST:event_jPacketTimeOutFocusGained

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        updateTabs();
    }//GEN-LAST:event_formWindowActivated

    void filfilCVs() {
        Boolean b ;
        String[] keys = { "FD-R extended" };
        b = parseString2CVs.convertString2CV( ReturnString, keys, CV, jComment, KTUI );
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
    private javax.swing.JButton jAbbrechen;
    private javax.swing.JPanel jAnalog;
    private javax.swing.JCheckBox jAnalog1;
    private javax.swing.JCheckBox jAnalog3;
    private javax.swing.JCheckBox jAuxInv1;
    private javax.swing.JCheckBox jAuxInv2;
    private javax.swing.JCheckBox jAuxInv3;
    private javax.swing.JCheckBox jAuxInv4;
    private javax.swing.JCheckBox jAuxInv5;
    private javax.swing.JCheckBox jAuxInv6;
    private javax.swing.JLabel jBild;
    private javax.swing.JLabel jBild1;
    private javax.swing.JCheckBox jBl_Inv1;
    private javax.swing.JCheckBox jBl_Inv2;
    private javax.swing.JCheckBox jBl_Inv3;
    private javax.swing.JCheckBox jBl_Inv4;
    private javax.swing.JCheckBox jBl_Inv5;
    private javax.swing.JCheckBox jBl_Inv6;
    private javax.swing.JCheckBox jBlink1;
    private javax.swing.JCheckBox jBlink2;
    private javax.swing.JCheckBox jBlink3;
    private javax.swing.JCheckBox jBlink4;
    private javax.swing.JCheckBox jBlink5;
    private javax.swing.JCheckBox jBlink6;
    private javax.swing.JLabel jBlinkBild;
    private javax.swing.JTextField jBlink_Einschaltzeit_1;
    private javax.swing.JTextField jBlink_Einschaltzeit_2;
    private javax.swing.JTextField jBlink_Einschaltzeit_3;
    private javax.swing.JTextField jBlink_Einschaltzeit_4;
    private javax.swing.JTextField jBlink_Einschaltzeit_5;
    private javax.swing.JTextField jBlink_Einschaltzeit_6;
    private javax.swing.JTextField jBlink_Pausezeit_1;
    private javax.swing.JTextField jBlink_Pausezeit_2;
    private javax.swing.JTextField jBlink_Pausezeit_3;
    private javax.swing.JTextField jBlink_Pausezeit_4;
    private javax.swing.JTextField jBlink_Pausezeit_5;
    private javax.swing.JTextField jBlink_Pausezeit_6;
    private javax.swing.JTextField jBlinkfrequenz1;
    private javax.swing.JTextField jBlinkfrequenz2;
    private javax.swing.JTextField jBlinkfrequenz3;
    private javax.swing.JTextField jBlinkfrequenz4;
    private javax.swing.JTextField jBlinkfrequenz5;
    private javax.swing.JTextField jBlinkfrequenz6;
    private javax.swing.JTextField jBlinkfrequenzMars;
    private javax.swing.JCheckBox jBroadCasst;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jCV29;
    private javax.swing.JComboBox jCV_Anzeige;
    private javax.swing.JTextField jCV_Inhalt;
    private javax.swing.JCheckBox jChannel2;
    private javax.swing.JTextArea jComment;
    private javax.swing.JTextField jDecoderAdresse;
    private javax.swing.JTextField jDecoderAdresse1;
    private javax.swing.JTabbedPane jDecodereigenschaften;
    private javax.swing.JButton jDecoderlesenSchreiben;
    private javax.swing.JTextField jDimmFS1;
    private javax.swing.JTextField jDimmFS2;
    private javax.swing.JTextField jDimmFS3;
    private javax.swing.JTextField jDimmFS4;
    private javax.swing.JTextField jDimm_FS;
    private javax.swing.JTextField jDimmen1;
    private javax.swing.JTextField jDimmen2;
    private javax.swing.JTextField jDimmen3;
    private javax.swing.JTextField jDimmen4;
    private javax.swing.JToggleButton jDirekteingabe;
    private javax.swing.JCheckBox jDoppelBlink1;
    private javax.swing.JCheckBox jDoppelBlink2;
    private javax.swing.JCheckBox jDoppelBlink3;
    private javax.swing.JCheckBox jDoppelBlink4;
    private javax.swing.JCheckBox jDoppelBlink5;
    private javax.swing.JCheckBox jDoppelBlink6;
    private javax.swing.JPanel jEffekte_1;
    private javax.swing.JPanel jEffekte_2;
    private javax.swing.JCheckBox jF1;
    private javax.swing.JCheckBox jF10_1;
    private javax.swing.JCheckBox jF10_2;
    private javax.swing.JCheckBox jF10_3;
    private javax.swing.JCheckBox jF10_4;
    private javax.swing.JCheckBox jF10_5;
    private javax.swing.JCheckBox jF10_6;
    private javax.swing.JCheckBox jF10_Gl;
    private javax.swing.JCheckBox jF10_SH;
    private javax.swing.JCheckBox jF10_SP;
    private javax.swing.JCheckBox jF10_TP;
    private javax.swing.JCheckBox jF11_1;
    private javax.swing.JCheckBox jF11_2;
    private javax.swing.JCheckBox jF11_3;
    private javax.swing.JCheckBox jF11_4;
    private javax.swing.JCheckBox jF11_5;
    private javax.swing.JCheckBox jF11_6;
    private javax.swing.JCheckBox jF11_Gl;
    private javax.swing.JCheckBox jF11_SH;
    private javax.swing.JCheckBox jF11_SP;
    private javax.swing.JCheckBox jF11_TP;
    private javax.swing.JCheckBox jF12_1;
    private javax.swing.JCheckBox jF12_2;
    private javax.swing.JCheckBox jF12_3;
    private javax.swing.JCheckBox jF12_4;
    private javax.swing.JCheckBox jF12_5;
    private javax.swing.JCheckBox jF12_6;
    private javax.swing.JCheckBox jF12_Gl;
    private javax.swing.JCheckBox jF12_SH;
    private javax.swing.JCheckBox jF12_SP;
    private javax.swing.JCheckBox jF12_TP;
    private javax.swing.JCheckBox jF13_1;
    private javax.swing.JCheckBox jF13_2;
    private javax.swing.JCheckBox jF13_3;
    private javax.swing.JCheckBox jF13_4;
    private javax.swing.JCheckBox jF13_5;
    private javax.swing.JCheckBox jF13_6;
    private javax.swing.JCheckBox jF13_Gl;
    private javax.swing.JCheckBox jF13_SH;
    private javax.swing.JCheckBox jF13_SP;
    private javax.swing.JCheckBox jF13_TP;
    private javax.swing.JCheckBox jF14_1;
    private javax.swing.JCheckBox jF14_2;
    private javax.swing.JCheckBox jF14_3;
    private javax.swing.JCheckBox jF14_4;
    private javax.swing.JCheckBox jF14_5;
    private javax.swing.JCheckBox jF14_6;
    private javax.swing.JCheckBox jF14_Gl;
    private javax.swing.JCheckBox jF14_SH;
    private javax.swing.JCheckBox jF14_SP;
    private javax.swing.JCheckBox jF14_TP;
    private javax.swing.JCheckBox jF15_1;
    private javax.swing.JCheckBox jF15_2;
    private javax.swing.JCheckBox jF15_3;
    private javax.swing.JCheckBox jF15_4;
    private javax.swing.JCheckBox jF15_5;
    private javax.swing.JCheckBox jF15_6;
    private javax.swing.JCheckBox jF15_Gl;
    private javax.swing.JCheckBox jF15_SH;
    private javax.swing.JCheckBox jF15_SP;
    private javax.swing.JCheckBox jF15_TP;
    private javax.swing.JCheckBox jF16_1;
    private javax.swing.JCheckBox jF16_2;
    private javax.swing.JCheckBox jF16_3;
    private javax.swing.JCheckBox jF16_4;
    private javax.swing.JCheckBox jF16_5;
    private javax.swing.JCheckBox jF16_6;
    private javax.swing.JCheckBox jF16_Gl;
    private javax.swing.JCheckBox jF16_SH;
    private javax.swing.JCheckBox jF16_SP;
    private javax.swing.JCheckBox jF16_TP;
    private javax.swing.JCheckBox jF17_1;
    private javax.swing.JCheckBox jF17_2;
    private javax.swing.JCheckBox jF17_3;
    private javax.swing.JCheckBox jF17_4;
    private javax.swing.JCheckBox jF17_5;
    private javax.swing.JCheckBox jF17_6;
    private javax.swing.JCheckBox jF17_Gl;
    private javax.swing.JCheckBox jF17_SH;
    private javax.swing.JCheckBox jF17_SP;
    private javax.swing.JCheckBox jF17_TP;
    private javax.swing.JCheckBox jF18_1;
    private javax.swing.JCheckBox jF18_2;
    private javax.swing.JCheckBox jF18_3;
    private javax.swing.JCheckBox jF18_4;
    private javax.swing.JCheckBox jF18_5;
    private javax.swing.JCheckBox jF18_6;
    private javax.swing.JCheckBox jF18_Gl;
    private javax.swing.JCheckBox jF18_SH;
    private javax.swing.JCheckBox jF18_SP;
    private javax.swing.JCheckBox jF18_TP;
    private javax.swing.JCheckBox jF19_1;
    private javax.swing.JCheckBox jF19_2;
    private javax.swing.JCheckBox jF19_3;
    private javax.swing.JCheckBox jF19_4;
    private javax.swing.JCheckBox jF19_5;
    private javax.swing.JCheckBox jF19_6;
    private javax.swing.JCheckBox jF19_Gl;
    private javax.swing.JCheckBox jF19_SH;
    private javax.swing.JCheckBox jF19_SP;
    private javax.swing.JCheckBox jF19_TP;
    private javax.swing.JCheckBox jF1_1;
    private javax.swing.JCheckBox jF1_2;
    private javax.swing.JCheckBox jF1_3;
    private javax.swing.JCheckBox jF1_4;
    private javax.swing.JCheckBox jF1_5;
    private javax.swing.JCheckBox jF1_6;
    private javax.swing.JCheckBox jF1_Gl;
    private javax.swing.JCheckBox jF1_SH;
    private javax.swing.JCheckBox jF1_SP;
    private javax.swing.JCheckBox jF1_TP;
    private javax.swing.JCheckBox jF2;
    private javax.swing.JCheckBox jF20_1;
    private javax.swing.JCheckBox jF20_2;
    private javax.swing.JCheckBox jF20_3;
    private javax.swing.JCheckBox jF20_4;
    private javax.swing.JCheckBox jF20_5;
    private javax.swing.JCheckBox jF20_6;
    private javax.swing.JCheckBox jF20_Gl;
    private javax.swing.JCheckBox jF20_SH;
    private javax.swing.JCheckBox jF20_SP;
    private javax.swing.JCheckBox jF20_TP;
    private javax.swing.JCheckBox jF21_1;
    private javax.swing.JCheckBox jF21_2;
    private javax.swing.JCheckBox jF21_3;
    private javax.swing.JCheckBox jF21_4;
    private javax.swing.JCheckBox jF21_5;
    private javax.swing.JCheckBox jF21_6;
    private javax.swing.JCheckBox jF21_Gl;
    private javax.swing.JCheckBox jF21_SH;
    private javax.swing.JCheckBox jF21_SP;
    private javax.swing.JCheckBox jF21_TP;
    private javax.swing.JCheckBox jF22_1;
    private javax.swing.JCheckBox jF22_2;
    private javax.swing.JCheckBox jF22_3;
    private javax.swing.JCheckBox jF22_4;
    private javax.swing.JCheckBox jF22_5;
    private javax.swing.JCheckBox jF22_6;
    private javax.swing.JCheckBox jF22_Gl;
    private javax.swing.JCheckBox jF22_SH;
    private javax.swing.JCheckBox jF22_SP;
    private javax.swing.JCheckBox jF22_TP;
    private javax.swing.JCheckBox jF23_1;
    private javax.swing.JCheckBox jF23_2;
    private javax.swing.JCheckBox jF23_3;
    private javax.swing.JCheckBox jF23_4;
    private javax.swing.JCheckBox jF23_5;
    private javax.swing.JCheckBox jF23_6;
    private javax.swing.JCheckBox jF23_Gl;
    private javax.swing.JCheckBox jF23_SH;
    private javax.swing.JCheckBox jF23_SP;
    private javax.swing.JCheckBox jF23_TP;
    private javax.swing.JCheckBox jF24_1;
    private javax.swing.JCheckBox jF24_2;
    private javax.swing.JCheckBox jF24_3;
    private javax.swing.JCheckBox jF24_4;
    private javax.swing.JCheckBox jF24_5;
    private javax.swing.JCheckBox jF24_6;
    private javax.swing.JCheckBox jF24_Gl;
    private javax.swing.JCheckBox jF24_SH;
    private javax.swing.JCheckBox jF24_SP;
    private javax.swing.JCheckBox jF24_TP;
    private javax.swing.JCheckBox jF25_1;
    private javax.swing.JCheckBox jF25_2;
    private javax.swing.JCheckBox jF25_3;
    private javax.swing.JCheckBox jF25_4;
    private javax.swing.JCheckBox jF25_5;
    private javax.swing.JCheckBox jF25_6;
    private javax.swing.JCheckBox jF26_1;
    private javax.swing.JCheckBox jF26_2;
    private javax.swing.JCheckBox jF26_3;
    private javax.swing.JCheckBox jF26_4;
    private javax.swing.JCheckBox jF26_5;
    private javax.swing.JCheckBox jF26_6;
    private javax.swing.JCheckBox jF27_1;
    private javax.swing.JCheckBox jF27_2;
    private javax.swing.JCheckBox jF27_3;
    private javax.swing.JCheckBox jF27_4;
    private javax.swing.JCheckBox jF27_5;
    private javax.swing.JCheckBox jF27_6;
    private javax.swing.JCheckBox jF28_1;
    private javax.swing.JCheckBox jF28_2;
    private javax.swing.JCheckBox jF28_3;
    private javax.swing.JCheckBox jF28_4;
    private javax.swing.JCheckBox jF28_5;
    private javax.swing.JCheckBox jF28_6;
    private javax.swing.JCheckBox jF2_1;
    private javax.swing.JCheckBox jF2_2;
    private javax.swing.JCheckBox jF2_3;
    private javax.swing.JCheckBox jF2_4;
    private javax.swing.JCheckBox jF2_5;
    private javax.swing.JCheckBox jF2_6;
    private javax.swing.JCheckBox jF2_Gl;
    private javax.swing.JCheckBox jF2_SH;
    private javax.swing.JCheckBox jF2_SP;
    private javax.swing.JCheckBox jF2_TP;
    private javax.swing.JCheckBox jF3;
    private javax.swing.JCheckBox jF3_1;
    private javax.swing.JCheckBox jF3_2;
    private javax.swing.JCheckBox jF3_3;
    private javax.swing.JCheckBox jF3_4;
    private javax.swing.JCheckBox jF3_5;
    private javax.swing.JCheckBox jF3_6;
    private javax.swing.JCheckBox jF3_Gl;
    private javax.swing.JCheckBox jF3_SH;
    private javax.swing.JCheckBox jF3_SP;
    private javax.swing.JCheckBox jF3_TP;
    private javax.swing.JCheckBox jF4;
    private javax.swing.JCheckBox jF4_1;
    private javax.swing.JCheckBox jF4_2;
    private javax.swing.JCheckBox jF4_3;
    private javax.swing.JCheckBox jF4_4;
    private javax.swing.JCheckBox jF4_5;
    private javax.swing.JCheckBox jF4_6;
    private javax.swing.JCheckBox jF4_Gl;
    private javax.swing.JCheckBox jF4_SH;
    private javax.swing.JCheckBox jF4_SP;
    private javax.swing.JCheckBox jF4_TP;
    private javax.swing.JCheckBox jF5;
    private javax.swing.JCheckBox jF5_1;
    private javax.swing.JCheckBox jF5_2;
    private javax.swing.JCheckBox jF5_3;
    private javax.swing.JCheckBox jF5_4;
    private javax.swing.JCheckBox jF5_5;
    private javax.swing.JCheckBox jF5_6;
    private javax.swing.JCheckBox jF5_Gl;
    private javax.swing.JCheckBox jF5_SH;
    private javax.swing.JCheckBox jF5_SP;
    private javax.swing.JCheckBox jF5_TP;
    private javax.swing.JCheckBox jF6;
    private javax.swing.JCheckBox jF6_1;
    private javax.swing.JCheckBox jF6_2;
    private javax.swing.JCheckBox jF6_3;
    private javax.swing.JCheckBox jF6_4;
    private javax.swing.JCheckBox jF6_5;
    private javax.swing.JCheckBox jF6_6;
    private javax.swing.JCheckBox jF6_Gl;
    private javax.swing.JCheckBox jF6_SH;
    private javax.swing.JCheckBox jF6_SP;
    private javax.swing.JCheckBox jF6_TP;
    private javax.swing.JCheckBox jF7;
    private javax.swing.JCheckBox jF7_1;
    private javax.swing.JCheckBox jF7_2;
    private javax.swing.JCheckBox jF7_3;
    private javax.swing.JCheckBox jF7_4;
    private javax.swing.JCheckBox jF7_5;
    private javax.swing.JCheckBox jF7_6;
    private javax.swing.JCheckBox jF7_Gl;
    private javax.swing.JCheckBox jF7_SH;
    private javax.swing.JCheckBox jF7_SP;
    private javax.swing.JCheckBox jF7_TP;
    private javax.swing.JCheckBox jF8;
    private javax.swing.JCheckBox jF8_1;
    private javax.swing.JCheckBox jF8_2;
    private javax.swing.JCheckBox jF8_3;
    private javax.swing.JCheckBox jF8_4;
    private javax.swing.JCheckBox jF8_5;
    private javax.swing.JCheckBox jF8_6;
    private javax.swing.JCheckBox jF8_Gl;
    private javax.swing.JCheckBox jF8_SH;
    private javax.swing.JCheckBox jF8_SP;
    private javax.swing.JCheckBox jF8_TP;
    private javax.swing.JCheckBox jF9_1;
    private javax.swing.JCheckBox jF9_2;
    private javax.swing.JCheckBox jF9_3;
    private javax.swing.JCheckBox jF9_4;
    private javax.swing.JCheckBox jF9_5;
    private javax.swing.JCheckBox jF9_6;
    private javax.swing.JCheckBox jF9_Gl;
    private javax.swing.JCheckBox jF9_SH;
    private javax.swing.JCheckBox jF9_SP;
    private javax.swing.JCheckBox jF9_TP;
    private javax.swing.JCheckBox jFL_1;
    private javax.swing.JCheckBox jFL_2;
    private javax.swing.JCheckBox jFL_3;
    private javax.swing.JCheckBox jFL_4;
    private javax.swing.JCheckBox jFL_5;
    private javax.swing.JCheckBox jFL_6;
    private javax.swing.JCheckBox jFR_1;
    private javax.swing.JCheckBox jFR_2;
    private javax.swing.JCheckBox jFR_3;
    private javax.swing.JCheckBox jFR_4;
    private javax.swing.JCheckBox jFR_5;
    private javax.swing.JCheckBox jFR_6;
    private javax.swing.JCheckBox jFS;
    private javax.swing.JCheckBox jFS0_AUX1;
    private javax.swing.JCheckBox jFS0_AUX10;
    private javax.swing.JCheckBox jFS0_AUX11;
    private javax.swing.JCheckBox jFS0_AUX12;
    private javax.swing.JCheckBox jFS0_AUX2;
    private javax.swing.JCheckBox jFS0_AUX3;
    private javax.swing.JCheckBox jFS0_AUX4;
    private javax.swing.JCheckBox jFS0_AUX5;
    private javax.swing.JCheckBox jFS0_AUX6;
    private javax.swing.JCheckBox jFS0_AUX7;
    private javax.swing.JCheckBox jFS0_AUX8;
    private javax.swing.JCheckBox jFS0_AUX9;
    private javax.swing.JCheckBox jFS0_AUXinv1;
    private javax.swing.JCheckBox jFS0_AUXinv2;
    private javax.swing.JCheckBox jFS0_AUXinv3;
    private javax.swing.JCheckBox jFS0_AUXinv4;
    private javax.swing.JCheckBox jFS0_AUXinv5;
    private javax.swing.JCheckBox jFS0_AUXinv6;
    private javax.swing.JCheckBox jFS0_F1;
    private javax.swing.JCheckBox jFS0_F2;
    private javax.swing.JCheckBox jFS0_F3;
    private javax.swing.JCheckBox jFS0_F4;
    private javax.swing.JCheckBox jFS0_F5;
    private javax.swing.JCheckBox jFS0_F6;
    private javax.swing.JCheckBox jFS0_F7;
    private javax.swing.JCheckBox jFS0_F8;
    private javax.swing.JPanel jFunctionMapping;
    private javax.swing.JTextField jGeschwindigkeit;
    private javax.swing.JCheckBox jGlockeIn1;
    private javax.swing.JCheckBox jGlockeIn2;
    private javax.swing.JCheckBox jGlockeIn3;
    private javax.swing.JCheckBox jIn1Aux1;
    private javax.swing.JCheckBox jIn1Aux2;
    private javax.swing.JCheckBox jIn1Aux3;
    private javax.swing.JCheckBox jIn1Aux4;
    private javax.swing.JCheckBox jIn1Aux5;
    private javax.swing.JCheckBox jIn1Aux6;
    private javax.swing.JCheckBox jIn1Pos1;
    private javax.swing.JCheckBox jIn1Pos2;
    private javax.swing.JCheckBox jIn1Pos3;
    private javax.swing.JCheckBox jIn2Aux1;
    private javax.swing.JCheckBox jIn2Aux2;
    private javax.swing.JCheckBox jIn2Aux3;
    private javax.swing.JCheckBox jIn2Aux4;
    private javax.swing.JCheckBox jIn2Aux5;
    private javax.swing.JCheckBox jIn2Aux6;
    private javax.swing.JCheckBox jIn2Pos1;
    private javax.swing.JCheckBox jIn2Pos2;
    private javax.swing.JCheckBox jIn2Pos3;
    private javax.swing.JCheckBox jIn3Aux1;
    private javax.swing.JCheckBox jIn3Aux2;
    private javax.swing.JCheckBox jIn3Aux3;
    private javax.swing.JCheckBox jIn3Aux4;
    private javax.swing.JCheckBox jIn3Aux5;
    private javax.swing.JCheckBox jIn3Aux6;
    private javax.swing.JCheckBox jIn3IsServo;
    private javax.swing.JCheckBox jKick1;
    private javax.swing.JCheckBox jKick2;
    private javax.swing.JCheckBox jKick3;
    private javax.swing.JCheckBox jKick4;
    private javax.swing.JCheckBox jKick5;
    private javax.swing.JCheckBox jKick6;
    private javax.swing.JTextField jKickrueck;
    private javax.swing.JTextField jKickvor;
    private javax.swing.JRadioButton jKurzeAdr;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel116;
    private javax.swing.JLabel jLabel117;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel120;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JLabel jLabel122;
    private javax.swing.JLabel jLabel123;
    private javax.swing.JLabel jLabel124;
    private javax.swing.JLabel jLabel125;
    private javax.swing.JLabel jLabel126;
    private javax.swing.JLabel jLabel127;
    private javax.swing.JLabel jLabel128;
    private javax.swing.JLabel jLabel129;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel130;
    private javax.swing.JLabel jLabel131;
    private javax.swing.JLabel jLabel132;
    private javax.swing.JLabel jLabel133;
    private javax.swing.JLabel jLabel134;
    private javax.swing.JLabel jLabel135;
    private javax.swing.JLabel jLabel136;
    private javax.swing.JLabel jLabel137;
    private javax.swing.JLabel jLabel138;
    private javax.swing.JLabel jLabel139;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel140;
    private javax.swing.JLabel jLabel141;
    private javax.swing.JLabel jLabel142;
    private javax.swing.JLabel jLabel143;
    private javax.swing.JLabel jLabel144;
    private javax.swing.JLabel jLabel145;
    private javax.swing.JLabel jLabel146;
    private javax.swing.JLabel jLabel147;
    private javax.swing.JLabel jLabel148;
    private javax.swing.JLabel jLabel149;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel150;
    private javax.swing.JLabel jLabel151;
    private javax.swing.JLabel jLabel152;
    private javax.swing.JLabel jLabel153;
    private javax.swing.JLabel jLabel154;
    private javax.swing.JLabel jLabel155;
    private javax.swing.JLabel jLabel156;
    private javax.swing.JLabel jLabel157;
    private javax.swing.JLabel jLabel158;
    private javax.swing.JLabel jLabel159;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel160;
    private javax.swing.JLabel jLabel161;
    private javax.swing.JLabel jLabel162;
    private javax.swing.JLabel jLabel163;
    private javax.swing.JLabel jLabel164;
    private javax.swing.JLabel jLabel165;
    private javax.swing.JLabel jLabel166;
    private javax.swing.JLabel jLabel167;
    private javax.swing.JLabel jLabel168;
    private javax.swing.JLabel jLabel169;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel170;
    private javax.swing.JLabel jLabel171;
    private javax.swing.JLabel jLabel172;
    private javax.swing.JLabel jLabel173;
    private javax.swing.JLabel jLabel174;
    private javax.swing.JLabel jLabel175;
    private javax.swing.JLabel jLabel176;
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
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JTextField jLaut;
    private javax.swing.JSlider jLautSlider;
    private javax.swing.JCheckBox jLongAddr;
    private javax.swing.JCheckBox jLongAddr1;
    private javax.swing.JCheckBox jLongAddr2;
    private javax.swing.JCheckBox jMARs1;
    private javax.swing.JCheckBox jMARs2;
    private javax.swing.JCheckBox jMARs3;
    private javax.swing.JCheckBox jMARs4;
    private javax.swing.JTextField jMM_Addr_2;
    private javax.swing.JLabel jManID;
    private javax.swing.JTextField jMindestSchlt1;
    private javax.swing.JTextField jMindestSchlt2;
    private javax.swing.JTextField jMindestSchlt3;
    private javax.swing.JButton jOpen;
    private javax.swing.JTextField jPacketTimeOut;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JCheckBox jRailCom;
    private javax.swing.JCheckBox jRangier1;
    private javax.swing.JCheckBox jRangier2;
    private javax.swing.JCheckBox jRangier3;
    private javax.swing.JCheckBox jRangier4;
    private javax.swing.JCheckBox jRangier5;
    private javax.swing.JCheckBox jRangier6;
    private javax.swing.JCheckBox jRangierF3;
    private javax.swing.JCheckBox jRangierF4;
    private javax.swing.JCheckBox jRichtung;
    private javax.swing.JCheckBox jRueck1;
    private javax.swing.JCheckBox jRueck2;
    private javax.swing.JCheckBox jRueck3;
    private javax.swing.JCheckBox jRueck4;
    private javax.swing.JCheckBox jRueck5;
    private javax.swing.JCheckBox jRueck6;
    private javax.swing.JButton jSave;
    private javax.swing.JCheckBox jSchaffnerIn1;
    private javax.swing.JCheckBox jSchaffnerIn2;
    private javax.swing.JCheckBox jSchaffnerIn3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JPanel jServo;
    private javax.swing.JCheckBox jServoF10;
    private javax.swing.JCheckBox jServoF11;
    private javax.swing.JCheckBox jServoF12;
    private javax.swing.JCheckBox jServoF5;
    private javax.swing.JCheckBox jServoF6;
    private javax.swing.JCheckBox jServoF7;
    private javax.swing.JCheckBox jServoF8;
    private javax.swing.JCheckBox jServoF9;
    private javax.swing.JCheckBox jSignalIn1;
    private javax.swing.JCheckBox jSignalIn2;
    private javax.swing.JCheckBox jSignalIn3;
    private javax.swing.JPanel jSound;
    private javax.swing.JCheckBox jTuerIn1;
    private javax.swing.JCheckBox jTuerIn2;
    private javax.swing.JCheckBox jTuerIn3;
    private javax.swing.JLabel jVersion;
    private javax.swing.JCheckBox jVor1;
    private javax.swing.JCheckBox jVor2;
    private javax.swing.JCheckBox jVor3;
    private javax.swing.JCheckBox jVor4;
    private javax.swing.JCheckBox jVor5;
    private javax.swing.JCheckBox jVor6;
    private javax.swing.JCheckBox jbDimmFS1;
    private javax.swing.JCheckBox jbDimmFS2;
    private javax.swing.JCheckBox jbDimmFS3;
    private javax.swing.JCheckBox jbDimmFS4;
    private javax.swing.JRadioButton jlangeAdr;
    private javax.swing.JTextField jlinkerAnschlag;
    private javax.swing.JTextField jrechterAnschlag;
    // End of variables declaration//GEN-END:variables


}
