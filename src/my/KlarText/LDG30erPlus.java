/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * LDG30erPlus.java
 *
 * Created on 10.12.2012, 18:46:39
 */
package my.KlarText;
import static java.awt.Toolkit.getDefaultToolkit;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ResourceBundle;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;
import static my.KlarText.KlarTextUI.debugLevel;

/**
 *
 * @author ktams
 */
public class LDG30erPlus extends javax.swing.JFrame {

    private String CVs;
    private int CV[][] = new int[2][200];
    public KlarTextUI KTUI;
    public String ReturnString = "Tams Elektronik";
    private ResourceBundle bundle;

    /** Creates new form LDG30erPlus */
    public LDG30erPlus(KlarTextUI ktuiThis) {
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
        bundle = java.util.ResourceBundle.getBundle("my.KlarText/Bundle");
        ImageIcon II = null;
        TitledBorder b = (TitledBorder)jPanel1.getBorder();
        switch(KTUI.Decoder)
        {
            case c.LD_G31Plus:
                b.setTitle("LD-G-31plus");
                II =new ImageIcon(getClass().getResource("/LD-G-31plus.gif"));
                setTitle( KTUI.getMenutext( decoderList.LD_G31Plus ).trim() );
                break;
                
            case c.LD_G33Plus:
                b.setTitle("LD-G-33plus");
                II =new ImageIcon(getClass().getResource("/LD-G-33plus.gif"));
                setTitle( KTUI.getMenutext( decoderList.LD_G33Plus ).trim() );
                break;
                
            case c.LD_G34Plus:
                b.setTitle("LD-G-34plus");
                II =new ImageIcon(getClass().getResource("/LD-G-34plus.gif"));
                setTitle( KTUI.getMenutext( decoderList.LD_G34Plus ).trim() );
                break;
                
            case c.LD_G36Plus:
                b.setTitle("LD-G-36plus");
                II =new ImageIcon(getClass().getResource("/LD-G-36plus.gif"));
                setTitle( KTUI.getMenutext( decoderList.LD_G36Plus ).trim() );
                break;
                
        }
        this.setIconImage(II.getImage());
        ImageIcon II2 = new ImageIcon(getClass().getResource("/FD-R_Ext_Bl1.gif"));
        this.setIconImage(II2.getImage());
        jBild1.setIcon(II);
        jBlinkBild.setIcon(II2);

        //---- CV-default-Werte -----
        // Vom Decoder verwendete CVs markieren und mit Default-Werten besetzen
        initCV( 0, 0 ); // reset jCV_Anzeige (clean all entries)


        // unsupported / unsaved CVs 10,14,15,16,20,21,22,23,24,25,26,66,95,102
        // 33p/34p keine CV 103

        // andere 36p keine CV 148-159

        // Werte aus LD-30_plus_2012_12_DE.pdf (04.02.2014), abweichende Werte im Originalsource als Kommentar
        initCV( 1, 3 );
        initCV( 2, 6 ); // 5
        initCV( 3, 10 ); // 26
        initCV( 4, 5 ); // 10
        initCV( 5, 255 );
        initCV( 6, 100 );
        initCV( 7, 10 );
        initCV( 8, 62 );
        initCV( 9, 0 );
        initCV( 11, 5 );
        initCV( 12, 1 );
        initCV( 13, 0 );
        // initCV( 14,  );
        // initCV( 15,  );
        // initCV( 16,  );
        initCV( 17, 192 );
        initCV( 18, 255 );
        initCV( 19, 0 );
        // initCV( 20,  );
        // initCV( 21,  );
        // initCV( 22,  );
        // initCV( 23,  );
        // initCV( 24,  );
        // initCV( 25,  );
        // initCV( 26,  ); // Hilfsregister ; Seite 58
        initCV( 27, 0 ); // 48 0x30
        initCV( 28, 131 ); // 123 0x83
        initCV( 29, 14 );
        initCV( 30, 0 ); // Hilfsregister ; Seite 58 // TODO  deaktivieren ?
        initCV( 31, 0 );   // Index für höhere CV-PAges ; Seite 58 // TODO  deaktivieren ?
        initCV( 32, 255 ); // Index für höhere CV-PAges ; Seite 58 // TODO  deaktivieren ?
        initCV( 33, 1 );
        initCV( 34, 2 );
        if(KTUI.Decoder != c.LD_G36Plus)
        {
            // 33p/34p
            initCV( 33, 1 );
            initCV( 34, 2 );
            initCV( 35, 4 );
            initCV( 36, 8 );
            initCV( 37, 0 );
            initCV( 38, 0 );
            initCV( 39, 16 );
            initCV( 40, 32 );
            initCV( 41, 0 );
            initCV( 42, 0 );
            initCV( 43, 0 );
            initCV( 44, 64 );
            initCV( 45, 128 );
            initCV( 46, 0 );
        }
        else
        {
            // 36p
            initCV( 33, 1 );
            initCV( 34, 2 );
            initCV( 35, 0 );
            initCV( 36, 0 );
            initCV( 37, 0 );
            initCV( 38, 0 );
            initCV( 39, 4 );
            initCV( 40, 8 );
            initCV( 41, 0 );
            initCV( 42, 0 );
            initCV( 43, 16 );
            initCV( 44, 32 );
            initCV( 45, 0 );
            initCV( 46, 64 );
        }

        initCV( 47, 4 );
        initCV( 48, 45 );
        initCV( 49, 73 );
        initCV( 50, 16 );
        initCV( 51, 14 );
        initCV( 52, 8 );
        for( int cv = 53 ; cv <= 60 ; cv++ ) { // Seite 67 Effekte der Ausgänge
            initCV( cv, 0 );
        }
        // initCV( 61,  );
        // initCV( 62,  );
        // initCV( 63,  );
        // initCV( 64,  );
        // initCV( 65, 0 );
        // 67-94 // Seite 63 Alternative Kennlinie ; laut Handbuch 68-95 ohne Defaults
        initCV( 67, 2 );
        initCV( 68, 4 );
        initCV( 69, 6 );
        initCV( 70, 9 );
        initCV( 71, 12 );
        initCV( 72, 15 );
        initCV( 73, 19 );
        initCV( 74, 23 );
        initCV( 75, 28 );
        initCV( 76, 33 );
        initCV( 77, 39 );
        initCV( 78, 45 );
        initCV( 79, 54 );
        initCV( 80, 61 );
        initCV( 81, 69 );
        initCV( 82, 79 );
        initCV( 83, 89 );
        initCV( 84, 99 );
        initCV( 85, 110 );
        initCV( 86, 122 );
        initCV( 87, 134 );
        initCV( 88, 146 );
        initCV( 89, 160 );
        initCV( 90, 176 );
        initCV( 91, 194 );
        initCV( 92, 214 );
        initCV( 93, 250 );
        initCV( 94, 255 );
        initCV( 96, 10 );
        initCV( 97, 0 );
        initCV( 98, 0 );
        initCV( 99, 0 );
        initCV( 100, 0 );
        initCV( 101, 16 );
        // initCV( 102,  );
        if(KTUI.Decoder == c.LD_G36Plus)
        {
            initCV( 103, 0 ); // 255
        }
        for( int cv = 104 ; cv <= 111 ; cv++ ) { // Seite 67 Einstellung der Blinklichter
            initCV( cv, 20 );
        }
        initCV( 112, 64 );
        initCV( 113, 10 );
        initCV( 114, 0 );
        for( int cv = 115 ; cv <= 122 ; cv++ ) { // Seite 69 Dimmen der Ausgänge
            initCV( cv, 255 );
        }
        for( int cv = 123 ; cv <= 130 ; cv++ ) { // Seite 69 Fahrstufenabhängiges Dimmen der Ausgänge
            initCV( cv, 255 );
        }
        initCV( 131, 0 );
        for( int cv = 132 ; cv <= 139 ; cv++ ) { // Seite 68 Einschaltdauer der Blinklichter
            initCV( cv, 4 );
        }
        for( int cv = 140 ; cv <= 147 ; cv++ ) { // Seite 68 Pausendauer beim Doppelblinken
            initCV( cv, 2 );
        }
        if(KTUI.Decoder != c.LD_G36Plus)
        {
            for( int cv = 148 ; cv <= 153 ; cv++ ) { // Seite 72 Einstellungen für integrierte Sounds
                initCV( cv, 0 );
            }
            initCV( 154, 16 );
            initCV( 155, 32 );
            initCV( 156, 64 );
            for( int cv = 157 ; cv <= 159 ; cv++ ) { // Seite 72 Einstellungen für integrierte Sounds
                initCV( cv, 0 );
            }
        }
        initCV( 160, 0 );
        initCV( 161, 10 );
        initCV( 162, 32 );
        initCV( 163, 32 );
        initCV( 164, 1 ); // 0
        initCV( 165, 4 ); // 2
        initCV( 166, 1 ); // 2
        initCV( 167, 2 );
        initCV( 168, 0 );
        initCV( 169, 112 );
        initCV( 170, 176 );
        initCV( 171, 16 );
        initCV( 172, 112 );
        initCV( 173, 0 );
        initCV( 174, 0 );
        initCV( 175, 0 );
        initCV( 176, 0 );
        initCV( 177, 1 );
        initCV( 178, 1 );
        initCV( 179, 1 );
        initCV( 180, 64 ); // Seite 66 Zuordnung der Ausgänge zu den Funktionstasten F13 bis F28
        for( int cv = 181 ; cv <= 195 ; cv++ ) { // Seite 66 Zuordnung der Ausgänge zu den Funktionstasten F13 bis F28
            initCV( cv, 0 );
        }
        initCV( 196, 0 ); // Seite 75 Pendelautomatik
        initCV( 197, 0 ); // Seite 75 Pendelautomatik
        initCV( 198, 0 ); // Seite 75 Pendelautomatik
        initCV( 199, 100 ); // seite 59 Intensität des Umschaltimpulses

        //---------------------------
        if(KTUI.Decoder == c.LD_G31Plus)
        {
            jFL_5.setVisible(false);
            jFL_6.setVisible(false);
            jFL_7.setVisible(false);
            jFL_8.setVisible(false);

            jFR_5.setVisible(false);
            jFR_6.setVisible(false);
            jFR_7.setVisible(false);
            jFR_8.setVisible(false);

            jF1_5.setVisible(false);
            jF1_6.setVisible(false);
            jF1_7.setVisible(false);
            jF1_8.setVisible(false);

            jF2_5.setVisible(false);
            jF2_6.setVisible(false);
            jF2_7.setVisible(false);
            jF2_8.setVisible(false);

            jF3_5.setVisible(false);
            jF3_6.setVisible(false);
            jF3_7.setVisible(false);
            jF3_8.setVisible(false);

            jF4_5.setVisible(false);
            jF4_6.setVisible(false);
            jF4_7.setVisible(false);
            jF4_8.setVisible(false);

            jF5_5.setVisible(false);
            jF5_6.setVisible(false);
            jF5_7.setVisible(false);
            jF5_8.setVisible(false);

            jF6_5.setVisible(false);
            jF6_6.setVisible(false);
            jF6_7.setVisible(false);
            jF6_8.setVisible(false);

            jF7_5.setVisible(false);
            jF7_6.setVisible(false);
            jF7_7.setVisible(false);
            jF7_8.setVisible(false);

            jF8_5.setVisible(false);
            jF8_6.setVisible(false);
            jF8_7.setVisible(false);
            jF8_8.setVisible(false);

            jF9_5.setVisible(false);
            jF9_6.setVisible(false);
            jF9_7.setVisible(false);
            jF9_8.setVisible(false);

            jF10_5.setVisible(false);
            jF10_6.setVisible(false);
            jF10_7.setVisible(false);
            jF10_8.setVisible(false);

            jF11_5.setVisible(false);
            jF11_6.setVisible(false);
            jF11_7.setVisible(false);
            jF11_8.setVisible(false);

            jF12_5.setVisible(false);
            jF12_6.setVisible(false);
            jF12_7.setVisible(false);
            jF12_8.setVisible(false);

            jF13_3.setVisible(false);
            jF13_4.setVisible(false);
            jF13_5.setVisible(false);
            jF13_6.setVisible(false);
            jF13_7.setVisible(false);

            jF14_3.setVisible(false);
            jF14_4.setVisible(false);
            jF14_5.setVisible(false);
            jF14_6.setVisible(false);
            jF14_7.setVisible(false);

            jF15_3.setVisible(false);
            jF15_4.setVisible(false);
            jF15_5.setVisible(false);
            jF15_6.setVisible(false);
            jF15_7.setVisible(false);

            jF16_3.setVisible(false);
            jF16_4.setVisible(false);
            jF16_5.setVisible(false);
            jF16_6.setVisible(false);
            jF16_7.setVisible(false);

            jF17_3.setVisible(false);
            jF17_4.setVisible(false);
            jF17_5.setVisible(false);
            jF17_6.setVisible(false);
            jF17_7.setVisible(false);

            jF18_3.setVisible(false);
            jF18_4.setVisible(false);
            jF18_5.setVisible(false);
            jF18_6.setVisible(false);
            jF18_7.setVisible(false);

            jF19_3.setVisible(false);
            jF19_4.setVisible(false);
            jF19_5.setVisible(false);
            jF19_6.setVisible(false);
            jF19_7.setVisible(false);

            jF20_3.setVisible(false);
            jF20_4.setVisible(false);
            jF20_5.setVisible(false);
            jF20_6.setVisible(false);
            jF20_7.setVisible(false);

            jF21_3.setVisible(false);
            jF21_4.setVisible(false);
            jF21_5.setVisible(false);
            jF21_6.setVisible(false);
            jF21_7.setVisible(false);

            jF22_3.setVisible(false);
            jF22_4.setVisible(false);
            jF22_5.setVisible(false);
            jF22_6.setVisible(false);
            jF22_7.setVisible(false);

            jF23_3.setVisible(false);
            jF23_4.setVisible(false);
            jF23_5.setVisible(false);
            jF23_6.setVisible(false);
            jF23_7.setVisible(false);

            jF24_3.setVisible(false);
            jF24_4.setVisible(false);
            jF24_5.setVisible(false);
            jF24_6.setVisible(false);
            jF24_7.setVisible(false);

            jF25_3.setVisible(false);
            jF25_4.setVisible(false);
            jF25_5.setVisible(false);
            jF25_6.setVisible(false);
            jF25_7.setVisible(false);

            jF26_3.setVisible(false);
            jF26_4.setVisible(false);
            jF26_5.setVisible(false);
            jF26_6.setVisible(false);
            jF26_7.setVisible(false);

            jF27_3.setVisible(false);
            jF27_4.setVisible(false);
            jF27_5.setVisible(false);
            jF27_6.setVisible(false);
            jF27_7.setVisible(false);

            jF28_3.setVisible(false);
            jF28_4.setVisible(false);
            jF28_5.setVisible(false);
            jF28_6.setVisible(false);
            jF28_7.setVisible(false);
            
            jLabel61.setVisible(false);
            jLabel60.setVisible(false);
            jLabel63.setVisible(false);
            jLabel62.setVisible(false);
            
            jLabel52.setVisible(false);
            jLabel65.setVisible(false);
            jLabel83.setVisible(false);
            jLabel179.setVisible(false);
            jLabel64.setVisible(false);

//---------------------- Effekte --------------

            jVor3.setVisible(false);
            jVor4.setVisible(false);
            jVor5.setVisible(false);
            jVor6.setVisible(false);

            jRueck3.setVisible(false);
            jRueck4.setVisible(false);
            jRueck5.setVisible(false);
            jRueck6.setVisible(false);

            jAuxInv3.setVisible(false);
            jAuxInv4.setVisible(false);
            jAuxInv5.setVisible(false);
            jAuxInv6.setVisible(false);

            jMARs3.setVisible(false);
            jMARs4.setVisible(false);
            jMARs5.setVisible(false);
            jMARs6.setVisible(false);

            jBlink3.setVisible(false);
            jBlink4.setVisible(false);
            jBlink5.setVisible(false);
            jBlink6.setVisible(false);

            jDoppelBlink3.setVisible(false);
            jDoppelBlink4.setVisible(false);
            jDoppelBlink5.setVisible(false);
            jDoppelBlink6.setVisible(false);

            jBl_Inv3.setVisible(false);
            jBl_Inv4.setVisible(false);
            jBl_Inv5.setVisible(false);
            jBl_Inv6.setVisible(false);

            jBlink_Einschaltzeit_3.setVisible(false);
            jBlink_Einschaltzeit_4.setVisible(false);
            jBlink_Einschaltzeit_5.setVisible(false);
            jBlink_Einschaltzeit_6.setVisible(false);

            jBlink_Pausezeit_3.setVisible(false);
            jBlink_Pausezeit_4.setVisible(false);
            jBlink_Pausezeit_5.setVisible(false);
            jBlink_Pausezeit_6.setVisible(false);

            jBlinkfrequenz3.setVisible(false);
            jBlinkfrequenz4.setVisible(false);
            jBlinkfrequenz5.setVisible(false);
            jBlinkfrequenz6.setVisible(false);

            jLabel44.setVisible(false);
            jLabel69.setVisible(false);
            jLabel70.setVisible(false);
            jLabel71.setVisible(false);

            jLabel47.setVisible(false);
            jLabel72.setVisible(false);
            jLabel73.setVisible(false);
            jLabel96.setVisible(false);

            jLabel49.setVisible(false);
            jLabel98.setVisible(false);
            jLabel110.setVisible(false);
            jLabel127.setVisible(false);

            jLabel67.setVisible(false);
            jLabel114.setVisible(false);

            jLabel242.setVisible(false);
            jLabel244.setVisible(false);
            jLabel246.setVisible(false);
            jLabel247.setVisible(false);

            jLabel99.setVisible(false);
            jLabel119.setVisible(false);
            jLabel120.setVisible(false);
            jLabel121.setVisible(false);

            jFS0_AUX3.setVisible(false);
            jFS0_AUX4.setVisible(false);
            jFS0_AUX5.setVisible(false);
            jFS0_AUX6.setVisible(false);

            jFS0_AUX9.setVisible(false);
            jFS0_AUX10.setVisible(false);
            jFS0_AUX11.setVisible(false);
            jFS0_AUX12.setVisible(false);

            jFS0_AUXinv3.setVisible(false);
            jFS0_AUXinv4.setVisible(false);
            jFS0_AUXinv5.setVisible(false);
            jFS0_AUXinv6.setVisible(false);

            jDimmen3.setVisible(false);
            jDimmen4.setVisible(false);
            jDimmen5.setVisible(false);
            jDimmen6.setVisible(false);

            jbDimmFS3.setVisible(false);
            jbDimmFS4.setVisible(false);
            jbDimmFS5.setVisible(false);
            jbDimmFS6.setVisible(false);

            jDimmFS3.setVisible(false);
            jDimmFS4.setVisible(false);
            jDimmFS5.setVisible(false);
            jDimmFS6.setVisible(false);

            jRangier3.setVisible(false);
            jRangier4.setVisible(false);

            jIn1Aux3.setVisible(false);
            jIn1Aux4.setVisible(false);
            jIn1Aux5.setVisible(false);
            jIn1Aux6.setVisible(false);

            jIn2Aux3.setVisible(false);
            jIn2Aux4.setVisible(false);
            jIn2Aux5.setVisible(false);
            jIn2Aux6.setVisible(false);

            jKick3.setVisible(false);
            jKick4.setVisible(false);
            jKick5.setVisible(false);
            jKick6.setVisible(false);

            jKickFahr3.setVisible(false);
            jKickFahr4.setVisible(false);
            jKickFahr5.setVisible(false);
            jKickFahr6.setVisible(false);

            jLabelSwitchWith.setVisible(false);
            jServoF5.setVisible(false);
            jServoF6.setVisible(false);
            jServoF7.setVisible(false);
            jServoF8.setVisible(false);
            jServoF9.setVisible(false);
            jServoF10.setVisible(false);
            jServoF11.setVisible(false);
            jServoF12.setVisible(false);

            jLabelLeftPos.setVisible(false);
            jlinkerAnschlag.setVisible(false);
            jLabelRightPos.setVisible(false);
            jrechterAnschlag.setVisible(false);
            jLabelVelocity.setVisible(false);
            jGeschwindigkeit.setVisible(false);
            jLabelChangeLR.setVisible(false);
            jServoTausch.setVisible(false);
            jLabelServoPOM.setVisible(false);
            jServoPOM.setVisible(false);
        }
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
        jPanel1 = new javax.swing.JPanel();
        jCV_Inhalt = new javax.swing.JTextField();
        jSave = new javax.swing.JButton();
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
        jRCplus = new javax.swing.JCheckBox();
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
        jLabel177 = new javax.swing.JLabel();
        jLabel178 = new javax.swing.JLabel();
        jFL_7 = new javax.swing.JCheckBox();
        jFL_8 = new javax.swing.JCheckBox();
        jFR_7 = new javax.swing.JCheckBox();
        jFR_8 = new javax.swing.JCheckBox();
        jF1_7 = new javax.swing.JCheckBox();
        jF1_8 = new javax.swing.JCheckBox();
        jF2_7 = new javax.swing.JCheckBox();
        jF2_8 = new javax.swing.JCheckBox();
        jF3_7 = new javax.swing.JCheckBox();
        jF3_8 = new javax.swing.JCheckBox();
        jF4_7 = new javax.swing.JCheckBox();
        jF4_8 = new javax.swing.JCheckBox();
        jF5_7 = new javax.swing.JCheckBox();
        jF5_8 = new javax.swing.JCheckBox();
        jF6_7 = new javax.swing.JCheckBox();
        jF6_8 = new javax.swing.JCheckBox();
        jF7_7 = new javax.swing.JCheckBox();
        jF7_8 = new javax.swing.JCheckBox();
        jF8_7 = new javax.swing.JCheckBox();
        jF8_8 = new javax.swing.JCheckBox();
        jF9_7 = new javax.swing.JCheckBox();
        jF9_8 = new javax.swing.JCheckBox();
        jF10_7 = new javax.swing.JCheckBox();
        jF10_8 = new javax.swing.JCheckBox();
        jF11_7 = new javax.swing.JCheckBox();
        jF11_8 = new javax.swing.JCheckBox();
        jF12_7 = new javax.swing.JCheckBox();
        jF12_8 = new javax.swing.JCheckBox();
        jF13_7 = new javax.swing.JCheckBox();
        jF14_7 = new javax.swing.JCheckBox();
        jF15_7 = new javax.swing.JCheckBox();
        jF16_7 = new javax.swing.JCheckBox();
        jF17_7 = new javax.swing.JCheckBox();
        jF18_7 = new javax.swing.JCheckBox();
        jF19_7 = new javax.swing.JCheckBox();
        jF20_7 = new javax.swing.JCheckBox();
        jF21_7 = new javax.swing.JCheckBox();
        jF22_7 = new javax.swing.JCheckBox();
        jF23_7 = new javax.swing.JCheckBox();
        jF24_7 = new javax.swing.JCheckBox();
        jF25_7 = new javax.swing.JCheckBox();
        jF26_7 = new javax.swing.JCheckBox();
        jF27_7 = new javax.swing.JCheckBox();
        jF28_7 = new javax.swing.JCheckBox();
        jLabel179 = new javax.swing.JLabel();
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
        jLabel180 = new javax.swing.JLabel();
        jLabel181 = new javax.swing.JLabel();
        jBlink_Einschaltzeit_f = new javax.swing.JTextField();
        jBlink_Einschaltzeit_r = new javax.swing.JTextField();
        jBlink_Pausezeit_f = new javax.swing.JTextField();
        jBlink_Pausezeit_r = new javax.swing.JTextField();
        jBlinkfrequenzf = new javax.swing.JTextField();
        jBlinkfrequenzr = new javax.swing.JTextField();
        jLabel182 = new javax.swing.JLabel();
        jLabel183 = new javax.swing.JLabel();
        jVorf = new javax.swing.JCheckBox();
        jRueckf = new javax.swing.JCheckBox();
        jRueckr = new javax.swing.JCheckBox();
        jVorr = new javax.swing.JCheckBox();
        jAuxInvr = new javax.swing.JCheckBox();
        jAuxInvf = new javax.swing.JCheckBox();
        jMARs5 = new javax.swing.JCheckBox();
        jMARs6 = new javax.swing.JCheckBox();
        jMARsf = new javax.swing.JCheckBox();
        jMARsr = new javax.swing.JCheckBox();
        jBlinkf = new javax.swing.JCheckBox();
        jBlinkr = new javax.swing.JCheckBox();
        jDoppelBlinkf = new javax.swing.JCheckBox();
        jDoppelBlinkr = new javax.swing.JCheckBox();
        jBl_Invf = new javax.swing.JCheckBox();
        jBl_Invr = new javax.swing.JCheckBox();
        jEffekte_2 = new javax.swing.JPanel();
        jDimmen1 = new javax.swing.JTextField();
        jDimmen2 = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jDimmen3 = new javax.swing.JTextField();
        jRangier4 = new javax.swing.JCheckBox();
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
        jLabel98 = new javax.swing.JLabel();
        jbDimmFS2 = new javax.swing.JCheckBox();
        jbDimmFS1 = new javax.swing.JCheckBox();
        jbDimmFS3 = new javax.swing.JCheckBox();
        jbDimmFS4 = new javax.swing.JCheckBox();
        jRangierf = new javax.swing.JCheckBox();
        jRangierr = new javax.swing.JCheckBox();
        jRangier1 = new javax.swing.JCheckBox();
        jRangier2 = new javax.swing.JCheckBox();
        jRangier3 = new javax.swing.JCheckBox();
        jDimmen4 = new javax.swing.JTextField();
        jDimm_FS = new javax.swing.JTextField();
        jRangierF3 = new javax.swing.JCheckBox();
        jRangierF4 = new javax.swing.JCheckBox();
        jLabel101 = new javax.swing.JLabel();
        jMindestSchlt1 = new javax.swing.JTextField();
        jMindestSchlt2 = new javax.swing.JTextField();
        jLabel102 = new javax.swing.JLabel();
        jIn1Aux1 = new javax.swing.JCheckBox();
        jIn1Aux2 = new javax.swing.JCheckBox();
        jIn1Aux3 = new javax.swing.JCheckBox();
        jIn1Aux4 = new javax.swing.JCheckBox();
        jIn1Aux5 = new javax.swing.JCheckBox();
        jIn1Aux6 = new javax.swing.JCheckBox();
        jLabel106 = new javax.swing.JLabel();
        jLabel107 = new javax.swing.JLabel();
        jLabel108 = new javax.swing.JLabel();
        jLabel109 = new javax.swing.JLabel();
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
        jLabel126 = new javax.swing.JLabel();
        jLabel130 = new javax.swing.JLabel();
        jLabel131 = new javax.swing.JLabel();
        jLabel129 = new javax.swing.JLabel();
        jLabel184 = new javax.swing.JLabel();
        jIn1Auxf = new javax.swing.JCheckBox();
        jIn1Auxr = new javax.swing.JCheckBox();
        jIn2Auxf = new javax.swing.JCheckBox();
        jIn2Auxr = new javax.swing.JCheckBox();
        jIn1Func1 = new javax.swing.JCheckBox();
        jIn2Func1 = new javax.swing.JCheckBox();
        jIn1Func2 = new javax.swing.JCheckBox();
        jIn2Func2 = new javax.swing.JCheckBox();
        jLabel100 = new javax.swing.JLabel();
        jLabel123 = new javax.swing.JLabel();
        jLabel124 = new javax.swing.JLabel();
        jLabel125 = new javax.swing.JLabel();
        jLabel185 = new javax.swing.JLabel();
        jLabel186 = new javax.swing.JLabel();
        jLabel187 = new javax.swing.JLabel();
        jLabel188 = new javax.swing.JLabel();
        jIn1Func3 = new javax.swing.JCheckBox();
        jIn2Func3 = new javax.swing.JCheckBox();
        jIn1Func4 = new javax.swing.JCheckBox();
        jIn2Func4 = new javax.swing.JCheckBox();
        jIn1Func5 = new javax.swing.JCheckBox();
        jIn2Func5 = new javax.swing.JCheckBox();
        jIn1Func6 = new javax.swing.JCheckBox();
        jIn2Func6 = new javax.swing.JCheckBox();
        jIn1Func7 = new javax.swing.JCheckBox();
        jIn2Func7 = new javax.swing.JCheckBox();
        jIn1Func8 = new javax.swing.JCheckBox();
        jIn2Func8 = new javax.swing.JCheckBox();
        jLabel30 = new javax.swing.JLabel();
        jKick6 = new javax.swing.JCheckBox();
        jKick1 = new javax.swing.JCheckBox();
        jKick2 = new javax.swing.JCheckBox();
        jKick3 = new javax.swing.JCheckBox();
        jKick4 = new javax.swing.JCheckBox();
        jKick5 = new javax.swing.JCheckBox();
        jDimmenf = new javax.swing.JTextField();
        jDimmenr = new javax.swing.JTextField();
        jLabel82 = new javax.swing.JLabel();
        jbDimmFSr = new javax.swing.JCheckBox();
        jbDimmFSf = new javax.swing.JCheckBox();
        jLabel105 = new javax.swing.JLabel();
        jDimmFSf = new javax.swing.JTextField();
        jDimmFSr = new javax.swing.JTextField();
        jLabel110 = new javax.swing.JLabel();
        jDimmen5 = new javax.swing.JTextField();
        jLabel127 = new javax.swing.JLabel();
        jbDimmFS5 = new javax.swing.JCheckBox();
        jbDimmFS6 = new javax.swing.JCheckBox();
        jDimmen6 = new javax.swing.JTextField();
        jDimmFS5 = new javax.swing.JTextField();
        jDimmFS6 = new javax.swing.JTextField();
        jKickMotor = new javax.swing.JTextField();
        jLabel128 = new javax.swing.JLabel();
        jKickFahr1 = new javax.swing.JCheckBox();
        jLabel189 = new javax.swing.JLabel();
        jLabel190 = new javax.swing.JLabel();
        jLabel191 = new javax.swing.JLabel();
        jLabel192 = new javax.swing.JLabel();
        jKickFahr2 = new javax.swing.JCheckBox();
        jKickFahr3 = new javax.swing.JCheckBox();
        jKickFahr4 = new javax.swing.JCheckBox();
        jKickFahr5 = new javax.swing.JCheckBox();
        jKickFahr6 = new javax.swing.JCheckBox();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel104 = new javax.swing.JLabel();
        jLabel206 = new javax.swing.JLabel();
        jLabel240 = new javax.swing.JLabel();
        jLabel241 = new javax.swing.JLabel();
        jLabel242 = new javax.swing.JLabel();
        jLabel243 = new javax.swing.JLabel();
        jLabel244 = new javax.swing.JLabel();
        jLabel245 = new javax.swing.JLabel();
        jLabel246 = new javax.swing.JLabel();
        jLabel247 = new javax.swing.JLabel();
        jSound = new javax.swing.JPanel();
        jF1_AK1 = new javax.swing.JCheckBox();
        jF2_SP = new javax.swing.JCheckBox();
        jF3_SP = new javax.swing.JCheckBox();
        jF4_SP = new javax.swing.JCheckBox();
        jF9_SP = new javax.swing.JCheckBox();
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
        jF5_SP = new javax.swing.JCheckBox();
        jF5_SH = new javax.swing.JCheckBox();
        jF5_Gl = new javax.swing.JCheckBox();
        jLabel146 = new javax.swing.JLabel();
        jLabel147 = new javax.swing.JLabel();
        jF6_SP = new javax.swing.JCheckBox();
        jF6_SH = new javax.swing.JCheckBox();
        jF6_Gl = new javax.swing.JCheckBox();
        jF10_SP = new javax.swing.JCheckBox();
        jF7_SP = new javax.swing.JCheckBox();
        jF7_SH = new javax.swing.JCheckBox();
        jF10_SH = new javax.swing.JCheckBox();
        jF12_SP = new javax.swing.JCheckBox();
        jF7_Gl = new javax.swing.JCheckBox();
        jF8_SP = new javax.swing.JCheckBox();
        jF10_Gl = new javax.swing.JCheckBox();
        jF12_SH = new javax.swing.JCheckBox();
        jF8_SH = new javax.swing.JCheckBox();
        jF8_Gl = new javax.swing.JCheckBox();
        jF12_Gl = new javax.swing.JCheckBox();
        jLabel148 = new javax.swing.JLabel();
        jLabel150 = new javax.swing.JLabel();
        jLabel132 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel133 = new javax.swing.JLabel();
        jLabel145 = new javax.swing.JLabel();
        jLabel149 = new javax.swing.JLabel();
        jLabel151 = new javax.swing.JLabel();
        jLabel152 = new javax.swing.JLabel();
        jLabel161 = new javax.swing.JLabel();
        jLabel162 = new javax.swing.JLabel();
        jLabel163 = new javax.swing.JLabel();
        jLabel164 = new javax.swing.JLabel();
        jLabel165 = new javax.swing.JLabel();
        jLabel166 = new javax.swing.JLabel();
        jLabel193 = new javax.swing.JLabel();
        jLabel194 = new javax.swing.JLabel();
        jLabel195 = new javax.swing.JLabel();
        jLabel196 = new javax.swing.JLabel();
        jLabel197 = new javax.swing.JLabel();
        jLabel198 = new javax.swing.JLabel();
        jLabel199 = new javax.swing.JLabel();
        jLabel200 = new javax.swing.JLabel();
        jLabel201 = new javax.swing.JLabel();
        jF1_SP = new javax.swing.JCheckBox();
        jF1_AK2 = new javax.swing.JCheckBox();
        jF1_AK3 = new javax.swing.JCheckBox();
        jF1_AK4 = new javax.swing.JCheckBox();
        jF1_AK5 = new javax.swing.JCheckBox();
        jF1_AK6 = new javax.swing.JCheckBox();
        jF1_AK7 = new javax.swing.JCheckBox();
        jF1_AK8 = new javax.swing.JCheckBox();
        jF1_AK9 = new javax.swing.JCheckBox();
        jF1_AK10 = new javax.swing.JCheckBox();
        jF1_AK11 = new javax.swing.JCheckBox();
        jIN1_PendelF12 = new javax.swing.JCheckBox();
        jF2_AK1 = new javax.swing.JCheckBox();
        jF2_AK2 = new javax.swing.JCheckBox();
        jF2_AK3 = new javax.swing.JCheckBox();
        jF2_AK4 = new javax.swing.JCheckBox();
        jF2_AK5 = new javax.swing.JCheckBox();
        jF2_AK6 = new javax.swing.JCheckBox();
        jF2_AK7 = new javax.swing.JCheckBox();
        jF2_AK8 = new javax.swing.JCheckBox();
        jF2_AK9 = new javax.swing.JCheckBox();
        jF2_AK10 = new javax.swing.JCheckBox();
        jF2_AK11 = new javax.swing.JCheckBox();
        jF2_AK12 = new javax.swing.JCheckBox();
        jF1_AKf = new javax.swing.JCheckBox();
        jF1_AKr = new javax.swing.JCheckBox();
        jLabel202 = new javax.swing.JLabel();
        jLabel203 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jAnkuppelMotor = new javax.swing.JTextField();
        jF2_AKf = new javax.swing.JCheckBox();
        jF2_AKr = new javax.swing.JCheckBox();
        jLabel204 = new javax.swing.JLabel();
        jLabel205 = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        jLabel208 = new javax.swing.JLabel();
        jLabel209 = new javax.swing.JLabel();
        jLabel210 = new javax.swing.JLabel();
        jLabel211 = new javax.swing.JLabel();
        jIN1_PendelF5 = new javax.swing.JCheckBox();
        jIN1_PendelF6 = new javax.swing.JCheckBox();
        jIN1_PendelF7 = new javax.swing.JCheckBox();
        jIN1_PendelF8 = new javax.swing.JCheckBox();
        jIN1_PendelF9 = new javax.swing.JCheckBox();
        jIN1_PendelF10 = new javax.swing.JCheckBox();
        jIN1_PendelF11 = new javax.swing.JCheckBox();
        jF1_AK12 = new javax.swing.JCheckBox();
        jLabel212 = new javax.swing.JLabel();
        jLabel213 = new javax.swing.JLabel();
        jLabel214 = new javax.swing.JLabel();
        jLabel215 = new javax.swing.JLabel();
        jLabel216 = new javax.swing.JLabel();
        jLabel217 = new javax.swing.JLabel();
        jLabel218 = new javax.swing.JLabel();
        jLabel219 = new javax.swing.JLabel();
        jLabel220 = new javax.swing.JLabel();
        jLabel221 = new javax.swing.JLabel();
        jLabel222 = new javax.swing.JLabel();
        jLabel223 = new javax.swing.JLabel();
        jLabel224 = new javax.swing.JLabel();
        jLabel225 = new javax.swing.JLabel();
        jLabel226 = new javax.swing.JLabel();
        jLabel227 = new javax.swing.JLabel();
        jLabel228 = new javax.swing.JLabel();
        jIN1_Pendelf = new javax.swing.JCheckBox();
        jIN1_Pendelr = new javax.swing.JCheckBox();
        jLabel229 = new javax.swing.JLabel();
        jLabel230 = new javax.swing.JLabel();
        jLabel231 = new javax.swing.JLabel();
        jLabel232 = new javax.swing.JLabel();
        jIN2_Pendelf = new javax.swing.JCheckBox();
        jIN2_Pendelr = new javax.swing.JCheckBox();
        jLabel233 = new javax.swing.JLabel();
        jIN1_PendelRW = new javax.swing.JCheckBox();
        jIN2_PendelRW = new javax.swing.JCheckBox();
        jLabel234 = new javax.swing.JLabel();
        jLabel235 = new javax.swing.JLabel();
        jIN1_PendelHaltZeit = new javax.swing.JTextField();
        jIN2_PendelHaltZeit = new javax.swing.JTextField();
        jLabel236 = new javax.swing.JLabel();
        jLabel237 = new javax.swing.JLabel();
        jLabel248 = new javax.swing.JLabel();
        jLabel249 = new javax.swing.JLabel();
        jServo = new javax.swing.JPanel();
        jLabelVelocity = new javax.swing.JLabel();
        jServoPOM = new javax.swing.JCheckBox();
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
        jLabelSwitchWith = new javax.swing.JLabel();
        jLabel169 = new javax.swing.JLabel();
        jLabelRightPos = new javax.swing.JLabel();
        jlinkerAnschlag = new javax.swing.JTextField();
        jrechterAnschlag = new javax.swing.JTextField();
        jLabelLeftPos = new javax.swing.JLabel();
        jLabel172 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabelChangeLR = new javax.swing.JLabel();
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
        jServoTausch = new javax.swing.JCheckBox();
        jLabelServoPOM = new javax.swing.JLabel();
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
        jAnalogRW_W = new javax.swing.JRadioButton();
        jAnalogRW_G = new javax.swing.JRadioButton();
        jLabel238 = new javax.swing.JLabel();
        jUmschaltEmpf = new javax.swing.JTextField();
        jLabel239 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jComment = new javax.swing.JTextArea();
        jLabel36 = new javax.swing.JLabel();
        jCV_LesenSchreiben = new javax.swing.JButton();
        jDirekteingabe = new javax.swing.JToggleButton();
        jLabel1 = new javax.swing.JLabel();
        jOpen = new javax.swing.JButton();
        jKurzeAdr = new javax.swing.JRadioButton();
        jDecoderAdresse = new javax.swing.JTextField();
        jCV_Anzeige = new javax.swing.JComboBox();
        jlangeAdr = new javax.swing.JRadioButton();
        jAbbrechen = new javax.swing.JButton();
        jDecoderAdresse1 = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("my/KlarText/Bundle"); // NOI18N
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("LDG30erPlus.jPanel1.border.title"))); // NOI18N

        jCV_Inhalt.setEditable(false);
        jCV_Inhalt.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCV_Inhalt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jCV_Inhalt.setText(bundle.getString("LDG30erPlus.jCV_Inhalt.text")); // NOI18N
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

        jSave.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jSave.setText(bundle.getString("LDG30erPlus.jSave.text")); // NOI18N
        jSave.setToolTipText(bundle.getString("LDG30erPlus.jSave.toolTipText")); // NOI18N
        jSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSaveActionPerformed(evt);
            }
        });

        jDecodereigenschaften.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jDecodereigenschaften.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jCV29.setToolTipText(bundle.getString("LDG30erPlus.jCV29.toolTipText")); // NOI18N
        jCV29.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jCV29ComponentShown(evt);
            }
        });
        jCV29.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jRichtung.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRichtung.setText(bundle.getString("LDG30erPlus.jRichtung.text")); // NOI18N
        jRichtung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRichtungActionPerformed(evt);
            }
        });
        jCV29.add(jRichtung, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, -1));

        jFS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS.setSelected(true);
        jFS.setText(bundle.getString("LDG30erPlus.jFS.text")); // NOI18N
        jFS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFSActionPerformed(evt);
            }
        });
        jCV29.add(jFS, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        jAnalog1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAnalog1.setSelected(true);
        jAnalog1.setText(bundle.getString("LDG30erPlus.jAnalog1.text")); // NOI18N
        jAnalog1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAnalog1ActionPerformed(evt);
            }
        });
        jCV29.add(jAnalog1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        jRailCom.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRailCom.setSelected(true);
        jRailCom.setText(bundle.getString("LDG30erPlus.jRailCom.text")); // NOI18N
        jRailCom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRailComActionPerformed(evt);
            }
        });
        jCV29.add(jRailCom, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));

        jAnalog3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAnalog3.setText(bundle.getString("LDG30erPlus.jAnalog3.text")); // NOI18N
        jCV29.add(jAnalog3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        jLongAddr.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLongAddr.setText(bundle.getString("LDG30erPlus.jLongAddr.text")); // NOI18N
        jLongAddr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jLongAddrActionPerformed(evt);
            }
        });
        jCV29.add(jLongAddr, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, -1));

        jLongAddr1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLongAddr1.setText(bundle.getString("LDG30erPlus.jLongAddr1.text")); // NOI18N
        jLongAddr1.setEnabled(false);
        jCV29.add(jLongAddr1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));

        jLongAddr2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLongAddr2.setText(bundle.getString("LDG30erPlus.jLongAddr2.text")); // NOI18N
        jLongAddr2.setEnabled(false);
        jCV29.add(jLongAddr2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, -1, -1));

        jLabel41.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel41.setText(bundle.getString("LDG30erPlus.jLabel41.text")); // NOI18N
        jCV29.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 50, -1, -1));

        jMM_Addr_2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jMM_Addr_2.setText(bundle.getString("LDG30erPlus.jMM_Addr_2.text")); // NOI18N
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
        jCV29.add(jMM_Addr_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 170, 40, -1));
        jCV29.add(jBild, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jManID.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jManID.setText(bundle.getString("LDG30erPlus.jManID.text")); // NOI18N
        jCV29.add(jManID, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, -1, -1));

        jVersion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jVersion.setText(bundle.getString("LDG30erPlus.jVersion.text")); // NOI18N
        jCV29.add(jVersion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, -1, -1));

        jBild1.setMaximumSize(new java.awt.Dimension(160, 100));
        jCV29.add(jBild1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 250, 190, 100));

        jBroadCasst.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBroadCasst.setSelected(true);
        jBroadCasst.setText(bundle.getString("LDG30erPlus.jBroadCasst.text")); // NOI18N
        jBroadCasst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBroadCasstActionPerformed(evt);
            }
        });
        jCV29.add(jBroadCasst, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 70, -1, -1));

        jChannel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jChannel2.setSelected(true);
        jChannel2.setText(bundle.getString("LDG30erPlus.jChannel2.text")); // NOI18N
        jChannel2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jChannel2ActionPerformed(evt);
            }
        });
        jCV29.add(jChannel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 90, -1, -1));

        jLabel56.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel56.setText(bundle.getString("LDG30erPlus.jLabel56.text")); // NOI18N
        jCV29.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 150, -1, -1));

        jRCplus.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRCplus.setSelected(true);
        jRCplus.setText(bundle.getString("LDG30erPlus.jRCplus.text")); // NOI18N
        jRCplus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRCplusActionPerformed(evt);
            }
        });
        jCV29.add(jRCplus, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 110, -1, -1));

        jDecodereigenschaften.addTab(bundle.getString("LDG30erPlus.jCV29.TabConstraints.tabTitle"), jCV29); // NOI18N

        jFunctionMapping.setToolTipText(bundle.getString("LDG30erPlus.jFunctionMapping.toolTipText")); // NOI18N
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
        jFunctionMapping.add(jFL_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, -1, -1));

        jF14_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF14_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF14_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF14_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 80, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText(bundle.getString("LDG30erPlus.jLabel4.text")); // NOI18N
        jFunctionMapping.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 40, -1, -1));

        jF1_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF1_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF1_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF1_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, -1, -1));

        jF2_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF2_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF2_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF2_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, -1, -1));

        jF3_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF3_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF3_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF3_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, -1, -1));

        jF4_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF4_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF4_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF4_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, -1, -1));

        jF5_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF5_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF5_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF5_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, -1, -1));

        jF16_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF16_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF16_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF16_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 120, -1, -1));

        jF16_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF16_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF16_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF16_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 120, -1, -1));

        jF16_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF16_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF16_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF16_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 120, -1, -1));

        jF16_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF16_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF16_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF16_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 120, -1, -1));

        jF16_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF16_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF16_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF16_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 120, -1, -1));

        jF10_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF10_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF10_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF10_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 280, -1, -1));

        jF16_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF16_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF16_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF16_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 120, -1, -1));

        jFL_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFL_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFL_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jFL_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, -1, -1));

        jFR_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFR_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFR_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jFR_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, -1, -1));

        jF1_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF1_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF1_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF1_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, -1, -1));

        jF2_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF2_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF2_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF2_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, -1, -1));

        jF3_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF3_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF3_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF3_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, -1, -1));

        jF4_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF4_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF4_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF4_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 160, -1, -1));

        jF9_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF9_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF9_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF9_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 260, -1, -1));

        jF11_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF11_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF11_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF11_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 300, -1, -1));

        jF17_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF17_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF17_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF17_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 140, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel6.setText(bundle.getString("LDG30erPlus.jLabel6.text")); // NOI18N
        jFunctionMapping.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel7.setText(bundle.getString("LDG30erPlus.jLabel7.text")); // NOI18N
        jFunctionMapping.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel8.setText(bundle.getString("LDG30erPlus.jLabel8.text")); // NOI18N
        jFunctionMapping.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel9.setText(bundle.getString("LDG30erPlus.jLabel9.text")); // NOI18N
        jFunctionMapping.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel10.setText(bundle.getString("LDG30erPlus.jLabel10.text")); // NOI18N
        jFunctionMapping.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, -1, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel11.setText(bundle.getString("LDG30erPlus.jLabel11.text")); // NOI18N
        jFunctionMapping.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, -1, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel12.setText(bundle.getString("LDG30erPlus.jLabel12.text")); // NOI18N
        jFunctionMapping.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, -1, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel13.setText(bundle.getString("LDG30erPlus.jLabel13.text")); // NOI18N
        jFunctionMapping.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, -1, -1));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel14.setText(bundle.getString("LDG30erPlus.jLabel14.text")); // NOI18N
        jFunctionMapping.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, -1, -1));

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel15.setText(bundle.getString("LDG30erPlus.jLabel15.text")); // NOI18N
        jFunctionMapping.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, -1, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel16.setText(bundle.getString("LDG30erPlus.jLabel16.text")); // NOI18N
        jFunctionMapping.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, -1, -1));

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel18.setText(bundle.getString("LDG30erPlus.jLabel18.text")); // NOI18N
        jFunctionMapping.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, -1, -1));

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel19.setText(bundle.getString("LDG30erPlus.jLabel19.text")); // NOI18N
        jFunctionMapping.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 80, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText(bundle.getString("LDG30erPlus.jLabel3.text")); // NOI18N
        jFunctionMapping.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 10, -1, -1));

        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel33.setText(bundle.getString("LDG30erPlus.jLabel33.text")); // NOI18N
        jFunctionMapping.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, -1, -1));

        jFL_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFL_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFL_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jFL_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 60, -1, -1));

        jFR_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFR_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFR_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jFR_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, -1, -1));

        jF1_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF1_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF1_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF1_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 100, -1, -1));

        jF2_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF2_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF2_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF2_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 120, -1, -1));

        jF3_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF3_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF3_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF3_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 140, -1, -1));

        jF4_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF4_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF4_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF4_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 160, -1, -1));

        jF9_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF9_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF9_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF9_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 260, -1, -1));

        jF17_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF17_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF17_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF17_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 140, -1, -1));

        jF17_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF17_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF17_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF17_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 140, -1, -1));

        jF17_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF17_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF17_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF17_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 140, -1, -1));

        jF17_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF17_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF17_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF17_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 140, -1, -1));

        jF17_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF17_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF17_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF17_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 140, -1, -1));

        jF11_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF11_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF11_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF11_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 300, -1, -1));

        jF18_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF18_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF18_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF18_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 160, -1, -1));

        jF19_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF19_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF19_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF19_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 180, -1, -1));

        jFL_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFL_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFL_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jFL_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, -1, -1));

        jFR_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFR_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFR_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jFR_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, -1, -1));

        jF1_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF1_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF1_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF1_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, -1, -1));

        jF2_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF2_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF2_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF2_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, -1, -1));

        jF3_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF3_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF3_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF3_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 140, -1, -1));

        jF4_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF4_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF4_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF4_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 160, -1, -1));

        jF9_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF9_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF9_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF9_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 260, -1, -1));

        jF11_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF11_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF11_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF11_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 300, -1, -1));

        jF18_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF18_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF18_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF18_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 160, -1, -1));

        jF18_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF18_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF18_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF18_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 160, -1, -1));

        jF18_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF18_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF18_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF18_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 160, -1, -1));

        jF18_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF18_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF18_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF18_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 160, -1, -1));

        jF18_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF18_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF18_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF18_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 160, -1, -1));

        jF20_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF20_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF20_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF20_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 200, -1, -1));

        jFL_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFL_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFL_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jFL_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, -1, -1));

        jFR_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFR_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFR_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jFR_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, -1, -1));

        jF1_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF1_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF1_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF1_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, -1, -1));

        jF2_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF2_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF2_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF2_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 120, -1, -1));

        jF3_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF3_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF3_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF3_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, -1, -1));

        jF4_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF4_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF4_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF4_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 160, -1, -1));

        jF9_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF9_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF9_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF9_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 260, -1, -1));

        jF11_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF11_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF11_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF11_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 300, -1, -1));

        jF19_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF19_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF19_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF19_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 180, -1, -1));

        jF19_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF19_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF19_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF19_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 180, -1, -1));

        jF19_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF19_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF19_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF19_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 180, -1, -1));

        jF19_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF19_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF19_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF19_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 180, -1, -1));

        jF19_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF19_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF19_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF19_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 180, -1, -1));

        jLabel58.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel58.setText(bundle.getString("LDG30erPlus.jLabel58.text")); // NOI18N
        jFunctionMapping.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, -1, -1));

        jLabel51.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel51.setText(bundle.getString("LDG30erPlus.jLabel51.text")); // NOI18N
        jFunctionMapping.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 40, -1, -1));

        jLabel52.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel52.setText(bundle.getString("LDG30erPlus.jLabel52.text")); // NOI18N
        jFunctionMapping.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 40, 10, -1));

        jLabel59.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel59.setText(bundle.getString("LDG30erPlus.jLabel59.text")); // NOI18N
        jFunctionMapping.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, -1, -1));

        jLabel53.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel53.setText(bundle.getString("LDG30erPlus.jLabel53.text")); // NOI18N
        jFunctionMapping.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 40, 10, -1));

        jF21_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF21_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF21_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF21_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 220, -1, -1));

        jFL_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFL_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFL_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jFL_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, -1, -1));

        jFR_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFR_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFR_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jFR_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, -1, -1));

        jF1_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF1_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF1_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF1_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 100, -1, -1));

        jF2_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF2_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF2_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF2_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 120, -1, -1));

        jF3_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF3_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF3_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF3_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 140, -1, -1));

        jF4_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF4_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF4_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF4_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 160, -1, -1));

        jF9_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF9_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF9_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF9_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 260, -1, -1));

        jF11_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF11_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF11_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF11_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 300, -1, -1));

        jF20_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF20_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF20_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF20_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 200, -1, -1));

        jF20_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF20_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF20_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF20_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 200, -1, -1));

        jF20_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF20_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF20_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF20_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 200, -1, -1));

        jF20_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF20_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF20_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF20_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 200, -1, -1));

        jF20_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF20_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF20_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF20_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 200, -1, -1));

        jF22_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF22_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF22_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF22_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 240, -1, -1));

        jF14_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF14_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF14_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF14_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 80, -1, -1));

        jFR_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFR_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFR_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jFR_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, -1, -1));

        jF5_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF5_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF5_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF5_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 180, -1, -1));

        jF5_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF5_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF5_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF5_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 180, -1, -1));

        jF5_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF5_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF5_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF5_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 180, -1, -1));

        jF5_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF5_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF5_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF5_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 180, -1, -1));

        jF10_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF10_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF10_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF10_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 280, -1, -1));

        jF11_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF11_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF11_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF11_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 300, -1, -1));

        jF21_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF21_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF21_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF21_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 220, -1, -1));

        jF21_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF21_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF21_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF21_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 220, -1, -1));

        jF21_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF21_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF21_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF21_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 220, -1, -1));

        jF21_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF21_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF21_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF21_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 220, -1, -1));

        jF21_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF21_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF21_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF21_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 220, -1, -1));

        jLabel60.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel60.setText(bundle.getString("LDG30erPlus.jLabel60.text")); // NOI18N
        jFunctionMapping.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 40, -1, -1));

        jLabel61.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel61.setText(bundle.getString("LDG30erPlus.jLabel61.text")); // NOI18N
        jFunctionMapping.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 40, -1, -1));

        jF22_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF22_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF22_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF22_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 240, -1, -1));

        jF14_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF14_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF14_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF14_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 80, -1, -1));

        jF5_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF5_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF5_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF5_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 180, -1, -1));

        jF6_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF6_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF6_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF6_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 200, -1, -1));

        jF6_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF6_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF6_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF6_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, -1, -1));

        jF6_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF6_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF6_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF6_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 200, -1, -1));

        jF6_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF6_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF6_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF6_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 200, -1, -1));

        jF10_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF10_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF10_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF10_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 280, -1, -1));

        jF12_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF12_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF12_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF12_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 320, -1, -1));

        jF15_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF15_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF15_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF15_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 100, -1, -1));

        jF22_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF22_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF22_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF22_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 240, -1, -1));

        jF22_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF22_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF22_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF22_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 240, -1, -1));

        jF22_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF22_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF22_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF22_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 240, -1, -1));

        jF22_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF22_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF22_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF22_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 240, -1, -1));

        jF23_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF23_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF23_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF23_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 260, -1, -1));

        jF14_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF14_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF14_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF14_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 80, -1, -1));

        jF6_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF6_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF6_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF6_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 200, -1, -1));

        jF6_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF6_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF6_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF6_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 200, -1, -1));

        jF7_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF7_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF7_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF7_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, -1, -1));

        jF7_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF7_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF7_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF7_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 220, -1, -1));

        jF7_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF7_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF7_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF7_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 220, -1, -1));

        jF10_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF10_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF10_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF10_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 280, -1, -1));

        jF12_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF12_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF12_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF12_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 320, -1, -1));

        jF15_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF15_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF15_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF15_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 100, -1, -1));

        jF15_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF15_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF15_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF15_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 100, -1, -1));

        jF23_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF23_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF23_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF23_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 260, -1, -1));

        jF23_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF23_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF23_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF23_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 260, -1, -1));

        jF23_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF23_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF23_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF23_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 260, -1, -1));

        jLabel62.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel62.setText(bundle.getString("LDG30erPlus.jLabel62.text")); // NOI18N
        jFunctionMapping.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, -1, -1));

        jLabel63.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel63.setText(bundle.getString("LDG30erPlus.jLabel63.text")); // NOI18N
        jFunctionMapping.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 40, -1, -1));

        jF23_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF23_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF23_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF23_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 260, -1, -1));

        jF14_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF14_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF14_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF14_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 80, -1, -1));

        jF7_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF7_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF7_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF7_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 220, -1, -1));

        jF7_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF7_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF7_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF7_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 220, -1, -1));

        jF7_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF7_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF7_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF7_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 220, -1, -1));

        jF8_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF8_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF8_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF8_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 240, -1, -1));

        jF8_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF8_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF8_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF8_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 240, -1, -1));

        jF10_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF10_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF10_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF10_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 280, -1, -1));

        jF12_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF12_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF12_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF12_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 320, -1, -1));

        jF12_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF12_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF12_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF12_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 320, -1, -1));

        jF13_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF13_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF13_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF13_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 60, -1, -1));

        jF15_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF15_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF15_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF15_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 100, -1, -1));

        jF15_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF15_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF15_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF15_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 100, -1, -1));

        jF15_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF15_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF15_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF15_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 100, -1, -1));

        jF13_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF13_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF13_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF13_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 60, -1, -1));

        jF14_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF14_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF14_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF14_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 80, -1, -1));

        jF8_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF8_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF8_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF8_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 240, -1, -1));

        jF8_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF8_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF8_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF8_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 240, -1, -1));

        jF8_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF8_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF8_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF8_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 240, -1, -1));

        jF8_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF8_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF8_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF8_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 240, -1, -1));

        jF9_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF9_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF9_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF9_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 260, -1, -1));

        jF10_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF10_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF10_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF10_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 280, -1, -1));

        jF12_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF12_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF12_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF12_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 320, -1, -1));

        jF12_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF12_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF12_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF12_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 320, -1, -1));

        jF13_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF13_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF13_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF13_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 60, -1, -1));

        jF13_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF13_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF13_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF13_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 60, -1, -1));

        jF13_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF13_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF13_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF13_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 60, -1, -1));

        jF13_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF13_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF13_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF13_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 60, -1, -1));

        jLabel64.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel64.setText(bundle.getString("LDG30erPlus.jLabel64.text")); // NOI18N
        jFunctionMapping.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 40, -1, -1));

        jLabel65.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel65.setText(bundle.getString("LDG30erPlus.jLabel65.text")); // NOI18N
        jFunctionMapping.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 40, -1, -1));

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel20.setText(bundle.getString("LDG30erPlus.jLabel20.text")); // NOI18N
        jFunctionMapping.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, -1, -1));

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel21.setText(bundle.getString("LDG30erPlus.jLabel21.text")); // NOI18N
        jFunctionMapping.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 60, -1, -1));

        jLabel83.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel83.setText(bundle.getString("LDG30erPlus.jLabel83.text")); // NOI18N
        jFunctionMapping.add(jLabel83, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 40, -1, -1));

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel22.setText(bundle.getString("LDG30erPlus.jLabel22.text")); // NOI18N
        jFunctionMapping.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, -1, -1));

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel23.setText(bundle.getString("LDG30erPlus.jLabel23.text")); // NOI18N
        jFunctionMapping.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 100, -1, -1));

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel29.setText(bundle.getString("LDG30erPlus.jLabel29.text")); // NOI18N
        jFunctionMapping.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 120, -1, -1));

        jLabel37.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel37.setText(bundle.getString("LDG30erPlus.jLabel37.text")); // NOI18N
        jFunctionMapping.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 140, -1, -1));

        jLabel84.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel84.setText(bundle.getString("LDG30erPlus.jLabel84.text")); // NOI18N
        jFunctionMapping.add(jLabel84, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 160, -1, -1));

        jLabel85.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel85.setText(bundle.getString("LDG30erPlus.jLabel85.text")); // NOI18N
        jFunctionMapping.add(jLabel85, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 180, -1, -1));

        jLabel86.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel86.setText(bundle.getString("LDG30erPlus.jLabel86.text")); // NOI18N
        jFunctionMapping.add(jLabel86, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 200, -1, -1));

        jLabel87.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel87.setText(bundle.getString("LDG30erPlus.jLabel87.text")); // NOI18N
        jFunctionMapping.add(jLabel87, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 220, -1, -1));

        jLabel88.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel88.setText(bundle.getString("LDG30erPlus.jLabel88.text")); // NOI18N
        jFunctionMapping.add(jLabel88, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 240, -1, -1));

        jLabel89.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel89.setText(bundle.getString("LDG30erPlus.jLabel89.text")); // NOI18N
        jFunctionMapping.add(jLabel89, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 260, -1, -1));

        jLabel90.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel90.setText(bundle.getString("LDG30erPlus.jLabel90.text")); // NOI18N
        jFunctionMapping.add(jLabel90, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 280, -1, -1));

        jLabel91.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel91.setText(bundle.getString("LDG30erPlus.jLabel91.text")); // NOI18N
        jFunctionMapping.add(jLabel91, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 300, -1, -1));

        jLabel92.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel92.setText(bundle.getString("LDG30erPlus.jLabel92.text")); // NOI18N
        jFunctionMapping.add(jLabel92, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 320, -1, -1));

        jLabel93.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel93.setText(bundle.getString("LDG30erPlus.jLabel93.text")); // NOI18N
        jFunctionMapping.add(jLabel93, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 340, -1, -1));

        jLabel94.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel94.setText(bundle.getString("LDG30erPlus.jLabel94.text")); // NOI18N
        jFunctionMapping.add(jLabel94, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 360, -1, -1));

        jF23_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF23_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF23_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF23_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 260, -1, -1));

        jF24_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF24_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF24_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF24_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 280, -1, -1));

        jF24_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF24_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF24_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF24_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 280, -1, -1));

        jF24_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF24_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF24_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF24_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 280, -1, -1));

        jF24_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF24_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF24_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF24_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 280, -1, -1));

        jF24_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF24_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF24_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF24_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 280, -1, -1));

        jF24_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF24_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF24_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF24_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 280, -1, -1));

        jF25_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF25_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF25_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF25_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 300, -1, -1));

        jF25_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF25_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF25_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF25_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 300, -1, -1));

        jF25_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF25_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF25_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF25_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 300, -1, -1));

        jF25_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF25_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF25_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF25_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 300, -1, -1));

        jF25_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF25_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF25_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF25_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 300, -1, -1));

        jF25_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF25_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF25_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF25_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 300, -1, -1));

        jF26_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF26_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF26_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF26_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 320, -1, -1));

        jF26_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF26_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF26_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF26_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 320, -1, -1));

        jF26_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF26_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF26_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF26_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 320, -1, -1));

        jF26_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF26_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF26_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF26_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 320, -1, -1));

        jF26_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF26_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF26_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF26_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 320, -1, -1));

        jF26_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF26_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF26_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF26_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 320, -1, -1));

        jF27_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF27_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF27_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF27_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 340, -1, -1));

        jF27_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF27_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF27_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF27_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 340, -1, -1));

        jF27_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF27_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF27_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF27_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 340, -1, -1));

        jF27_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF27_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF27_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF27_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 340, -1, -1));

        jF27_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF27_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF27_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF27_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 340, -1, -1));

        jF27_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF27_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF27_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF27_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 340, -1, -1));

        jF28_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF28_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF28_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF28_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 360, -1, -1));

        jF28_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF28_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF28_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF28_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 360, -1, -1));

        jF28_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF28_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF28_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF28_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 360, -1, -1));

        jF28_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF28_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF28_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF28_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 360, -1, -1));

        jF28_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF28_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF28_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF28_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 360, -1, -1));

        jF28_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF28_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF28_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF28_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 360, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText(bundle.getString("LDG30erPlus.jLabel5.text")); // NOI18N
        jFunctionMapping.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, -1));

        jLabel177.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel177.setText(bundle.getString("LDG30erPlus.jLabel177.text")); // NOI18N
        jFunctionMapping.add(jLabel177, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, -1, -1));

        jLabel178.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel178.setText(bundle.getString("LDG30erPlus.jLabel178.text")); // NOI18N
        jFunctionMapping.add(jLabel178, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, -1, -1));

        jFL_7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFL_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFL_7ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jFL_7, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 60, -1, -1));

        jFL_8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFL_8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFL_8ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jFL_8, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, -1, -1));

        jFR_7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFR_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFR_7ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jFR_7, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 80, -1, -1));

        jFR_8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFR_8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFR_8ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jFR_8, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 80, -1, -1));

        jF1_7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF1_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF1_7ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF1_7, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, -1, -1));

        jF1_8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF1_8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF1_8ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF1_8, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 100, -1, -1));

        jF2_7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF2_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF2_7ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF2_7, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 120, -1, -1));

        jF2_8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF2_8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF2_8ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF2_8, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 120, -1, -1));

        jF3_7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF3_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF3_7ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF3_7, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 140, -1, -1));

        jF3_8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF3_8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF3_8ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF3_8, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 140, -1, -1));

        jF4_7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF4_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF4_7ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF4_7, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 160, -1, -1));

        jF4_8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF4_8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF4_8ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF4_8, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 160, -1, -1));

        jF5_7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF5_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF5_7ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF5_7, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 180, -1, -1));

        jF5_8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF5_8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF5_8ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF5_8, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 180, -1, -1));

        jF6_7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF6_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF6_7ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF6_7, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 200, -1, -1));

        jF6_8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF6_8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF6_8ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF6_8, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 200, -1, -1));

        jF7_7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF7_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF7_7ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF7_7, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 220, -1, -1));

        jF7_8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF7_8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF7_8ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF7_8, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 220, -1, -1));

        jF8_7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF8_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF8_7ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF8_7, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 240, -1, -1));

        jF8_8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF8_8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF8_8ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF8_8, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 240, -1, -1));

        jF9_7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF9_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF9_7ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF9_7, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 260, -1, -1));

        jF9_8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF9_8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF9_8ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF9_8, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 260, -1, -1));

        jF10_7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF10_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF10_7ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF10_7, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 280, -1, -1));

        jF10_8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF10_8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF10_8ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF10_8, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 280, -1, -1));

        jF11_7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF11_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF11_7ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF11_7, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 300, -1, -1));

        jF11_8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF11_8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF11_8ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF11_8, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 300, -1, -1));

        jF12_7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF12_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF12_7ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF12_7, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 320, -1, -1));

        jF12_8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF12_8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF12_8ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF12_8, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 320, -1, -1));

        jF13_7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF13_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF13_7ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF13_7, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 60, -1, -1));

        jF14_7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF14_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF14_7ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF14_7, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 80, -1, -1));

        jF15_7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF15_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF15_7ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF15_7, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 100, -1, -1));

        jF16_7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF16_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF16_7ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF16_7, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 120, -1, -1));

        jF17_7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF17_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF17_7ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF17_7, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 140, -1, -1));

        jF18_7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF18_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF18_7ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF18_7, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 160, -1, -1));

        jF19_7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF19_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF19_7ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF19_7, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 180, -1, -1));

        jF20_7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF20_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF20_7ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF20_7, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 200, -1, -1));

        jF21_7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF21_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF21_7ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF21_7, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 220, -1, -1));

        jF22_7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF22_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF22_7ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF22_7, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 240, -1, -1));

        jF23_7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF23_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF23_7ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF23_7, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 260, -1, -1));

        jF24_7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF24_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF24_7ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF24_7, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 280, -1, -1));

        jF25_7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF25_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF25_7ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF25_7, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 300, -1, -1));

        jF26_7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF26_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF26_7ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF26_7, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 320, -1, -1));

        jF27_7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF27_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF27_7ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF27_7, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 340, -1, -1));

        jF28_7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF28_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF28_7ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF28_7, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 360, -1, -1));

        jLabel179.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel179.setText(bundle.getString("LDG30erPlus.jLabel179.text")); // NOI18N
        jFunctionMapping.add(jLabel179, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 40, -1, -1));

        jDecodereigenschaften.addTab(bundle.getString("LDG30erPlus.jFunctionMapping.TabConstraints.tabTitle"), jFunctionMapping); // NOI18N

        jEffekte_1.setToolTipText(bundle.getString("LDG30erPlus.jEffekte_1.toolTipText")); // NOI18N
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
        jEffekte_1.add(jVor1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, -1, -1));

        jRueck1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRueck1.setSelected(true);
        jRueck1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRueck1ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jRueck1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, -1, -1));

        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel32.setText(bundle.getString("LDG30erPlus.jLabel32.text")); // NOI18N
        jEffekte_1.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, -1, -1));

        jRueck2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRueck2.setSelected(true);
        jRueck2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRueck2ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jRueck2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, -1, -1));

        jVor2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jVor2.setSelected(true);
        jVor2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jVor2ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jVor2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, -1, -1));

        jLabel44.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel44.setText(bundle.getString("LDG30erPlus.jLabel44.text")); // NOI18N
        jEffekte_1.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));

        jLabel45.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel45.setText(bundle.getString("LDG30erPlus.jLabel45.text")); // NOI18N
        jEffekte_1.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, -1, -1));

        jVor3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jVor3.setSelected(true);
        jVor3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jVor3ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jVor3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, -1, -1));

        jRueck3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRueck3.setSelected(true);
        jRueck3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRueck3ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jRueck3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 100, -1, -1));

        jBlink6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBlink6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBlink6ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jBlink6, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 160, -1, -1));

        jLabel46.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel46.setText(bundle.getString("LDG30erPlus.jLabel46.text")); // NOI18N
        jEffekte_1.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

        jLabel48.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel48.setText(bundle.getString("LDG30erPlus.jLabel48.text")); // NOI18N
        jEffekte_1.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));
        jEffekte_1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 420, 10));

        jLabel40.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel40.setText(bundle.getString("LDG30erPlus.jLabel40.text")); // NOI18N
        jEffekte_1.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 220, -1, -1));

        jLabel42.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel42.setText(bundle.getString("LDG30erPlus.jLabel42.text")); // NOI18N
        jEffekte_1.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, -1));

        jBlink_Pausezeit_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBlink_Pausezeit_3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jBlink_Pausezeit_3.setText(bundle.getString("LDG30erPlus.jBlink_Pausezeit_3.text")); // NOI18N
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
        jEffekte_1.add(jBlink_Pausezeit_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 320, 30, 20));

        jLabel54.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel54.setText(bundle.getString("LDG30erPlus.jLabel54.text")); // NOI18N
        jEffekte_1.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 0, -1, -1));

        jLabel55.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel55.setText(bundle.getString("LDG30erPlus.jLabel55.text")); // NOI18N
        jEffekte_1.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 0, -1, -1));

        jLabel57.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel57.setText(bundle.getString("LDG30erPlus.jLabel57.text")); // NOI18N
        jEffekte_1.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 0, -1, -1));

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel25.setText(bundle.getString("LDG30erPlus.jLabel25.text")); // NOI18N
        jEffekte_1.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 0, -1, -1));

        jLabel69.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel69.setText(bundle.getString("LDG30erPlus.jLabel69.text")); // NOI18N
        jEffekte_1.add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        jLabel70.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel70.setText(bundle.getString("LDG30erPlus.jLabel70.text")); // NOI18N
        jEffekte_1.add(jLabel70, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, -1));

        jLabel71.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel71.setText(bundle.getString("LDG30erPlus.jLabel71.text")); // NOI18N
        jEffekte_1.add(jLabel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));

        jMARs2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMARs2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMARs2ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jMARs2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 80, -1, -1));

        jMARs1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMARs1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMARs1ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jMARs1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 60, -1, -1));

        jMARs3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMARs3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMARs3ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jMARs3, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 100, -1, -1));

        jVor4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jVor4.setSelected(true);
        jVor4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jVor4ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jVor4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, -1, -1));

        jVor5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jVor5.setSelected(true);
        jVor5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jVor5ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jVor5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, -1, -1));

        jVor6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jVor6.setSelected(true);
        jVor6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jVor6ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jVor6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, -1, -1));

        jMARs4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMARs4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMARs4ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jMARs4, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 120, -1, -1));

        jBlink1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBlink1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBlink1ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jBlink1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 60, -1, -1));

        jBlink2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBlink2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBlink2ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jBlink2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 80, -1, -1));

        jBlink3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBlink3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBlink3ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jBlink3, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 100, -1, -1));

        jRueck4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRueck4.setSelected(true);
        jRueck4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRueck4ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jRueck4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 120, -1, -1));

        jRueck5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRueck5.setSelected(true);
        jRueck5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRueck5ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jRueck5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 140, -1, -1));

        jRueck6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRueck6.setSelected(true);
        jRueck6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRueck6ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jRueck6, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 160, -1, -1));

        jBlink4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBlink4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBlink4ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jBlink4, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 120, -1, -1));

        jBlink5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBlink5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBlink5ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jBlink5, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 140, -1, -1));

        jBlink_Pausezeit_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBlink_Pausezeit_4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jBlink_Pausezeit_4.setText(bundle.getString("LDG30erPlus.jBlink_Pausezeit_4.text")); // NOI18N
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
        jEffekte_1.add(jBlink_Pausezeit_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 340, 30, -1));

        jBlinkfrequenzMars.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBlinkfrequenzMars.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jBlinkfrequenzMars.setText(bundle.getString("LDG30erPlus.jBlinkfrequenzMars.text")); // NOI18N
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
        jEffekte_1.add(jBlinkfrequenzMars, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 410, 30, -1));

        jDoppelBlink1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jDoppelBlink1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDoppelBlink1ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jDoppelBlink1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 60, -1, -1));

        jDoppelBlink2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jDoppelBlink2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDoppelBlink2ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jDoppelBlink2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 80, -1, -1));

        jDoppelBlink3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jDoppelBlink3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDoppelBlink3ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jDoppelBlink3, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 100, -1, -1));

        jDoppelBlink4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jDoppelBlink4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDoppelBlink4ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jDoppelBlink4, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 120, -1, -1));

        jDoppelBlink5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jDoppelBlink5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDoppelBlink5ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jDoppelBlink5, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 140, -1, -1));

        jDoppelBlink6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jDoppelBlink6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDoppelBlink6ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jDoppelBlink6, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 160, -1, -1));

        jLabel75.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel75.setText(bundle.getString("LDG30erPlus.jLabel75.text")); // NOI18N
        jEffekte_1.add(jLabel75, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 220, -1, -1));

        jBlink_Einschaltzeit_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBlink_Einschaltzeit_1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jBlink_Einschaltzeit_1.setText(bundle.getString("LDG30erPlus.jBlink_Einschaltzeit_1.text")); // NOI18N
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
        jEffekte_1.add(jBlink_Einschaltzeit_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 280, 30, 20));

        jBlink_Einschaltzeit_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBlink_Einschaltzeit_2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jBlink_Einschaltzeit_2.setText(bundle.getString("LDG30erPlus.jBlink_Einschaltzeit_2.text")); // NOI18N
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
        jEffekte_1.add(jBlink_Einschaltzeit_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, 30, 20));

        jBlink_Einschaltzeit_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBlink_Einschaltzeit_3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jBlink_Einschaltzeit_3.setText(bundle.getString("LDG30erPlus.jBlink_Einschaltzeit_3.text")); // NOI18N
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
        jEffekte_1.add(jBlink_Einschaltzeit_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, 30, 20));

        jBlink_Einschaltzeit_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBlink_Einschaltzeit_4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jBlink_Einschaltzeit_4.setText(bundle.getString("LDG30erPlus.jBlink_Einschaltzeit_4.text")); // NOI18N
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
        jEffekte_1.add(jBlink_Einschaltzeit_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 340, 30, 20));

        jLabel79.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel79.setText(bundle.getString("LDG30erPlus.jLabel79.text")); // NOI18N
        jEffekte_1.add(jLabel79, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        jBlink_Einschaltzeit_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBlink_Einschaltzeit_5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jBlink_Einschaltzeit_5.setText(bundle.getString("LDG30erPlus.jBlink_Einschaltzeit_5.text")); // NOI18N
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
        jEffekte_1.add(jBlink_Einschaltzeit_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 30, 20));

        jBlink_Einschaltzeit_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBlink_Einschaltzeit_6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jBlink_Einschaltzeit_6.setText(bundle.getString("LDG30erPlus.jBlink_Einschaltzeit_6.text")); // NOI18N
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
        jEffekte_1.add(jBlink_Einschaltzeit_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 380, 30, 20));

        jBlink_Pausezeit_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBlink_Pausezeit_1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jBlink_Pausezeit_1.setText(bundle.getString("LDG30erPlus.jBlink_Pausezeit_1.text")); // NOI18N
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
        jEffekte_1.add(jBlink_Pausezeit_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 280, 30, 20));

        jBlink_Pausezeit_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBlink_Pausezeit_2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jBlink_Pausezeit_2.setText(bundle.getString("LDG30erPlus.jBlink_Pausezeit_2.text")); // NOI18N
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
        jEffekte_1.add(jBlink_Pausezeit_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 300, 30, 20));

        jAuxInv6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAuxInv6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAuxInv6ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jAuxInv6, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 160, -1, -1));

        jAuxInv2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAuxInv2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAuxInv2ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jAuxInv2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, -1, -1));

        jAuxInv1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAuxInv1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAuxInv1ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jAuxInv1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 60, -1, -1));

        jAuxInv3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAuxInv3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAuxInv3ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jAuxInv3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, -1, -1));

        jAuxInv4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAuxInv4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAuxInv4ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jAuxInv4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 120, -1, -1));

        jAuxInv5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAuxInv5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAuxInv5ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jAuxInv5, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 140, -1, -1));

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel26.setText(bundle.getString("LDG30erPlus.jLabel26.text")); // NOI18N
        jEffekte_1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 0, -1, -1));

        jBl_Inv1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBl_Inv1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBl_Inv1ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jBl_Inv1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 60, -1, -1));

        jBl_Inv2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBl_Inv2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBl_Inv2ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jBl_Inv2, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 80, -1, -1));

        jBl_Inv3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBl_Inv3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBl_Inv3ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jBl_Inv3, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 100, -1, -1));

        jBl_Inv4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBl_Inv4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBl_Inv4ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jBl_Inv4, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 120, -1, -1));

        jBl_Inv5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBl_Inv5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBl_Inv5ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jBl_Inv5, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 140, -1, -1));

        jBl_Inv6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBl_Inv6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBl_Inv6ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jBl_Inv6, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 160, -1, -1));

        jLabel95.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel95.setText(bundle.getString("LDG30erPlus.jLabel95.text")); // NOI18N
        jEffekte_1.add(jLabel95, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, -1, -1));

        jLabel47.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel47.setText(bundle.getString("LDG30erPlus.jLabel47.text")); // NOI18N
        jEffekte_1.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, -1, -1));

        jLabel50.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel50.setText(bundle.getString("LDG30erPlus.jLabel50.text")); // NOI18N
        jEffekte_1.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, -1, -1));

        jLabel66.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel66.setText(bundle.getString("LDG30erPlus.jLabel66.text")); // NOI18N
        jEffekte_1.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, -1, -1));

        jLabel72.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel72.setText(bundle.getString("LDG30erPlus.jLabel72.text")); // NOI18N
        jEffekte_1.add(jLabel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, -1, -1));

        jLabel73.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel73.setText(bundle.getString("LDG30erPlus.jLabel73.text")); // NOI18N
        jEffekte_1.add(jLabel73, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, -1, -1));

        jLabel96.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel96.setText(bundle.getString("LDG30erPlus.jLabel96.text")); // NOI18N
        jEffekte_1.add(jLabel96, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, -1, -1));

        jLabel97.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel97.setText(bundle.getString("LDG30erPlus.jLabel97.text")); // NOI18N
        jEffekte_1.add(jLabel97, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, -1, -1));

        jBlink_Pausezeit_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBlink_Pausezeit_5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jBlink_Pausezeit_5.setText(bundle.getString("LDG30erPlus.jBlink_Pausezeit_5.text")); // NOI18N
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
        jEffekte_1.add(jBlink_Pausezeit_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 360, 30, -1));

        jBlink_Pausezeit_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBlink_Pausezeit_6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jBlink_Pausezeit_6.setText(bundle.getString("LDG30erPlus.jBlink_Pausezeit_6.text")); // NOI18N
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
        jEffekte_1.add(jBlink_Pausezeit_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 380, 30, -1));

        jLabel43.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel43.setText(bundle.getString("LDG30erPlus.jLabel43.text")); // NOI18N
        jEffekte_1.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, -1, -1));

        jBlinkfrequenz3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBlinkfrequenz3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jBlinkfrequenz3.setText(bundle.getString("LDG30erPlus.jBlinkfrequenz3.text")); // NOI18N
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
        jEffekte_1.add(jBlinkfrequenz3, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 320, 30, 20));

        jBlinkfrequenz4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBlinkfrequenz4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jBlinkfrequenz4.setText(bundle.getString("LDG30erPlus.jBlinkfrequenz4.text")); // NOI18N
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
        jEffekte_1.add(jBlinkfrequenz4, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 340, 30, -1));

        jBlinkfrequenz1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBlinkfrequenz1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jBlinkfrequenz1.setText(bundle.getString("LDG30erPlus.jBlinkfrequenz1.text")); // NOI18N
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
        jEffekte_1.add(jBlinkfrequenz1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 280, 30, 20));

        jBlinkfrequenz2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBlinkfrequenz2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jBlinkfrequenz2.setText(bundle.getString("LDG30erPlus.jBlinkfrequenz2.text")); // NOI18N
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
        jEffekte_1.add(jBlinkfrequenz2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 300, 30, 20));

        jBlinkfrequenz5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBlinkfrequenz5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jBlinkfrequenz5.setText(bundle.getString("LDG30erPlus.jBlinkfrequenz5.text")); // NOI18N
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
        jEffekte_1.add(jBlinkfrequenz5, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 360, 30, -1));

        jBlinkfrequenz6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBlinkfrequenz6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jBlinkfrequenz6.setText(bundle.getString("LDG30erPlus.jBlinkfrequenz6.text")); // NOI18N
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
        jEffekte_1.add(jBlinkfrequenz6, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 380, 30, -1));
        jEffekte_1.add(jBlinkBild, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 260, 210, 150));

        jLabel180.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel180.setText(bundle.getString("LDG30erPlus.jLabel180.text")); // NOI18N
        jEffekte_1.add(jLabel180, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, -1, -1));

        jLabel181.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel181.setText(bundle.getString("LDG30erPlus.jLabel181.text")); // NOI18N
        jEffekte_1.add(jLabel181, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, -1, -1));

        jBlink_Einschaltzeit_f.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBlink_Einschaltzeit_f.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jBlink_Einschaltzeit_f.setText(bundle.getString("LDG30erPlus.jBlink_Einschaltzeit_f.text")); // NOI18N
        jBlink_Einschaltzeit_f.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jBlink_Einschaltzeit_fFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jBlink_Einschaltzeit_fFocusLost(evt);
            }
        });
        jBlink_Einschaltzeit_f.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jBlink_Einschaltzeit_fKeyReleased(evt);
            }
        });
        jEffekte_1.add(jBlink_Einschaltzeit_f, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 30, 20));

        jBlink_Einschaltzeit_r.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBlink_Einschaltzeit_r.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jBlink_Einschaltzeit_r.setText(bundle.getString("LDG30erPlus.jBlink_Einschaltzeit_r.text")); // NOI18N
        jBlink_Einschaltzeit_r.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jBlink_Einschaltzeit_rFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jBlink_Einschaltzeit_rFocusLost(evt);
            }
        });
        jBlink_Einschaltzeit_r.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jBlink_Einschaltzeit_rKeyReleased(evt);
            }
        });
        jEffekte_1.add(jBlink_Einschaltzeit_r, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, 30, 20));

        jBlink_Pausezeit_f.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBlink_Pausezeit_f.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jBlink_Pausezeit_f.setText(bundle.getString("LDG30erPlus.jBlink_Pausezeit_f.text")); // NOI18N
        jBlink_Pausezeit_f.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jBlink_Pausezeit_fFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jBlink_Pausezeit_fFocusLost(evt);
            }
        });
        jBlink_Pausezeit_f.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jBlink_Pausezeit_fKeyReleased(evt);
            }
        });
        jEffekte_1.add(jBlink_Pausezeit_f, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 240, 30, 20));

        jBlink_Pausezeit_r.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBlink_Pausezeit_r.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jBlink_Pausezeit_r.setText(bundle.getString("LDG30erPlus.jBlink_Pausezeit_r.text")); // NOI18N
        jBlink_Pausezeit_r.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jBlink_Pausezeit_rFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jBlink_Pausezeit_rFocusLost(evt);
            }
        });
        jBlink_Pausezeit_r.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jBlink_Pausezeit_rKeyReleased(evt);
            }
        });
        jEffekte_1.add(jBlink_Pausezeit_r, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 260, 30, 20));

        jBlinkfrequenzf.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBlinkfrequenzf.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jBlinkfrequenzf.setText(bundle.getString("LDG30erPlus.jBlinkfrequenzf.text")); // NOI18N
        jBlinkfrequenzf.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jBlinkfrequenzfFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jBlinkfrequenzfFocusLost(evt);
            }
        });
        jBlinkfrequenzf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jBlinkfrequenzfKeyReleased(evt);
            }
        });
        jEffekte_1.add(jBlinkfrequenzf, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 240, 30, 20));

        jBlinkfrequenzr.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBlinkfrequenzr.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jBlinkfrequenzr.setText(bundle.getString("LDG30erPlus.jBlinkfrequenzr.text")); // NOI18N
        jBlinkfrequenzr.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jBlinkfrequenzrFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jBlinkfrequenzrFocusLost(evt);
            }
        });
        jBlinkfrequenzr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jBlinkfrequenzrKeyReleased(evt);
            }
        });
        jEffekte_1.add(jBlinkfrequenzr, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 260, 30, 20));

        jLabel182.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel182.setText(bundle.getString("LDG30erPlus.jLabel182.text")); // NOI18N
        jEffekte_1.add(jLabel182, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jLabel183.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel183.setText(bundle.getString("LDG30erPlus.jLabel183.text")); // NOI18N
        jEffekte_1.add(jLabel183, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, -1));

        jVorf.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jVorf.setSelected(true);
        jVorf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jVorfActionPerformed(evt);
            }
        });
        jEffekte_1.add(jVorf, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, -1, -1));

        jRueckf.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRueckf.setSelected(true);
        jRueckf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRueckfActionPerformed(evt);
            }
        });
        jEffekte_1.add(jRueckf, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, -1, -1));

        jRueckr.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRueckr.setSelected(true);
        jRueckr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRueckrActionPerformed(evt);
            }
        });
        jEffekte_1.add(jRueckr, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, -1, -1));

        jVorr.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jVorr.setSelected(true);
        jVorr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jVorrActionPerformed(evt);
            }
        });
        jEffekte_1.add(jVorr, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, -1, -1));

        jAuxInvr.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAuxInvr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAuxInvrActionPerformed(evt);
            }
        });
        jEffekte_1.add(jAuxInvr, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, -1, -1));

        jAuxInvf.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAuxInvf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAuxInvfActionPerformed(evt);
            }
        });
        jEffekte_1.add(jAuxInvf, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, -1, -1));

        jMARs5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMARs5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMARs5ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jMARs5, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 140, -1, -1));

        jMARs6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMARs6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMARs6ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jMARs6, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 160, -1, -1));

        jMARsf.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMARsf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMARsfActionPerformed(evt);
            }
        });
        jEffekte_1.add(jMARsf, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 20, -1, -1));

        jMARsr.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMARsr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMARsrActionPerformed(evt);
            }
        });
        jEffekte_1.add(jMARsr, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 40, -1, -1));

        jBlinkf.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBlinkf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBlinkfActionPerformed(evt);
            }
        });
        jEffekte_1.add(jBlinkf, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 20, -1, -1));

        jBlinkr.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBlinkr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBlinkrActionPerformed(evt);
            }
        });
        jEffekte_1.add(jBlinkr, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 40, -1, -1));

        jDoppelBlinkf.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jDoppelBlinkf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDoppelBlinkfActionPerformed(evt);
            }
        });
        jEffekte_1.add(jDoppelBlinkf, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 20, -1, -1));

        jDoppelBlinkr.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jDoppelBlinkr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDoppelBlinkrActionPerformed(evt);
            }
        });
        jEffekte_1.add(jDoppelBlinkr, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 40, -1, -1));

        jBl_Invf.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBl_Invf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBl_InvfActionPerformed(evt);
            }
        });
        jEffekte_1.add(jBl_Invf, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 20, -1, -1));

        jBl_Invr.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBl_Invr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBl_InvrActionPerformed(evt);
            }
        });
        jEffekte_1.add(jBl_Invr, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 40, -1, -1));

        jDecodereigenschaften.addTab(bundle.getString("LDG30erPlus.jEffekte_1.TabConstraints.tabTitle"), jEffekte_1); // NOI18N

        jEffekte_2.setToolTipText(bundle.getString("LDG30erPlus.jEffekte_2.toolTipText")); // NOI18N
        jEffekte_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jEffekte_2.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jEffekte_2ComponentShown(evt);
            }
        });
        jEffekte_2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jDimmen1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jDimmen1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jDimmen1.setText(bundle.getString("LDG30erPlus.jDimmen1.text")); // NOI18N
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
        jEffekte_2.add(jDimmen1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, 30, -1));

        jDimmen2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jDimmen2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jDimmen2.setText(bundle.getString("LDG30erPlus.jDimmen2.text")); // NOI18N
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
        jEffekte_2.add(jDimmen2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 30, -1));

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel31.setText(bundle.getString("LDG30erPlus.jLabel31.text")); // NOI18N
        jEffekte_2.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, -1, -1));

        jLabel49.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel49.setText(bundle.getString("LDG30erPlus.jLabel49.text")); // NOI18N
        jEffekte_2.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, -1));

        jDimmen3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jDimmen3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jDimmen3.setText(bundle.getString("LDG30erPlus.jDimmen3.text")); // NOI18N
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
        jEffekte_2.add(jDimmen3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, 30, -1));

        jRangier4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRangier4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRangier4ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jRangier4, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 150, -1, -1));

        jKickrueck.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jKickrueck.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jKickrueck.setText(bundle.getString("LDG30erPlus.jKickrueck.text")); // NOI18N
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
        jEffekte_2.add(jKickrueck, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 190, 30, 20));

        jKickvor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jKickvor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jKickvor.setText(bundle.getString("LDG30erPlus.jKickvor.text")); // NOI18N
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
        jEffekte_2.add(jKickvor, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 170, 30, -1));

        jLabel68.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel68.setText(bundle.getString("LDG30erPlus.jLabel68.text")); // NOI18N
        jEffekte_2.add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        jLabel74.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel74.setText(bundle.getString("LDG30erPlus.jLabel74.text")); // NOI18N
        jEffekte_2.add(jLabel74, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        jLabel39.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel39.setText(bundle.getString("LDG30erPlus.jLabel39.text")); // NOI18N
        jEffekte_2.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 31, 110, -1));
        jEffekte_2.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 420, 10));

        jLabel76.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel76.setText(bundle.getString("LDG30erPlus.jLabel76.text")); // NOI18N
        jEffekte_2.add(jLabel76, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 30, -1, -1));

        jLabel77.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel77.setText(bundle.getString("LDG30erPlus.jLabel77.text")); // NOI18N
        jEffekte_2.add(jLabel77, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));

        jLabel78.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel78.setText(bundle.getString("LDG30erPlus.jLabel78.text")); // NOI18N
        jEffekte_2.add(jLabel78, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, -1, -1));

        jLabel80.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel80.setText(bundle.getString("LDG30erPlus.jLabel80.text")); // NOI18N
        jEffekte_2.add(jLabel80, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 270, -1, -1));

        jLabel81.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel81.setText(bundle.getString("LDG30erPlus.jLabel81.text")); // NOI18N
        jEffekte_2.add(jLabel81, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, -1, -1));

        jLabel98.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel98.setText(bundle.getString("LDG30erPlus.jLabel98.text")); // NOI18N
        jEffekte_2.add(jLabel98, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, -1));

        jbDimmFS2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jbDimmFS2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbDimmFS2ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jbDimmFS2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 110, -1, -1));

        jbDimmFS1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jbDimmFS1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbDimmFS1ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jbDimmFS1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, -1, -1));

        jbDimmFS3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jbDimmFS3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbDimmFS3ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jbDimmFS3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 130, -1, -1));

        jbDimmFS4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jbDimmFS4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbDimmFS4ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jbDimmFS4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 150, -1, -1));

        jRangierf.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRangierf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRangierfActionPerformed(evt);
            }
        });
        jEffekte_2.add(jRangierf, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 50, -1, -1));

        jRangierr.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRangierr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRangierrActionPerformed(evt);
            }
        });
        jEffekte_2.add(jRangierr, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 70, -1, -1));

        jRangier1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRangier1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRangier1ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jRangier1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 90, -1, -1));

        jRangier2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRangier2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRangier2ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jRangier2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 110, -1, -1));

        jRangier3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRangier3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRangier3ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jRangier3, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 130, -1, -1));

        jDimmen4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jDimmen4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jDimmen4.setText(bundle.getString("LDG30erPlus.jDimmen4.text")); // NOI18N
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
        jEffekte_2.add(jDimmen4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, 30, -1));

        jDimm_FS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jDimm_FS.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jDimm_FS.setText(bundle.getString("LDG30erPlus.jDimm_FS.text")); // NOI18N
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
        jEffekte_2.add(jDimm_FS, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 220, 30, -1));

        jRangierF3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRangierF3.setText(bundle.getString("LDG30erPlus.jRangierF3.text")); // NOI18N
        jRangierF3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRangierF3ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jRangierF3, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 50, -1, -1));

        jRangierF4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRangierF4.setText(bundle.getString("LDG30erPlus.jRangierF4.text")); // NOI18N
        jRangierF4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRangierF4ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jRangierF4, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 70, -1, -1));

        jLabel101.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel101.setText(bundle.getString("LDG30erPlus.jLabel101.text")); // NOI18N
        jEffekte_2.add(jLabel101, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 30, -1, -1));

        jMindestSchlt1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMindestSchlt1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jMindestSchlt1.setText(bundle.getString("LDG30erPlus.jMindestSchlt1.text")); // NOI18N
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
        jEffekte_2.add(jMindestSchlt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 310, 30, 20));

        jMindestSchlt2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMindestSchlt2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jMindestSchlt2.setText(bundle.getString("LDG30erPlus.jMindestSchlt2.text")); // NOI18N
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
        jEffekte_2.add(jMindestSchlt2, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 330, 30, 20));

        jLabel102.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel102.setText(bundle.getString("LDG30erPlus.jLabel102.text")); // NOI18N
        jEffekte_2.add(jLabel102, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        jIn1Aux1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn1Aux1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn1Aux1ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn1Aux1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 310, -1, -1));

        jIn1Aux2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn1Aux2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn1Aux2ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn1Aux2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 310, -1, -1));

        jIn1Aux3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn1Aux3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn1Aux3ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn1Aux3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 310, -1, -1));

        jIn1Aux4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn1Aux4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn1Aux4ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn1Aux4, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 310, -1, -1));

        jIn1Aux5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn1Aux5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn1Aux5ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn1Aux5, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 310, -1, -1));

        jIn1Aux6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn1Aux6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn1Aux6ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn1Aux6, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 310, -1, -1));

        jLabel106.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel106.setText(bundle.getString("LDG30erPlus.jLabel106.text")); // NOI18N
        jEffekte_2.add(jLabel106, new org.netbeans.lib.awtextra.AbsoluteConstraints(377, 190, -1, -1));

        jLabel107.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel107.setText(bundle.getString("LDG30erPlus.jLabel107.text")); // NOI18N
        jEffekte_2.add(jLabel107, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, -1, -1));

        jLabel108.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel108.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel108.setText(bundle.getString("LDG30erPlus.jLabel108.text")); // NOI18N
        jEffekte_2.add(jLabel108, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 311, 150, -1));

        jLabel109.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel109.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel109.setText(bundle.getString("LDG30erPlus.jLabel109.text")); // NOI18N
        jEffekte_2.add(jLabel109, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 310, 20, -1));

        jLabel111.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel111.setText(bundle.getString("LDG30erPlus.jLabel111.text")); // NOI18N
        jEffekte_2.add(jLabel111, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, -1, -1));

        jLabel112.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel112.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel112.setText(bundle.getString("LDG30erPlus.jLabel112.text")); // NOI18N
        jEffekte_2.add(jLabel112, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, -1, -1));

        jDimmFS3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jDimmFS3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jDimmFS3.setText(bundle.getString("LDG30erPlus.jDimmFS3.text")); // NOI18N
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
        jEffekte_2.add(jDimmFS3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, 30, 20));

        jDimmFS4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jDimmFS4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jDimmFS4.setText(bundle.getString("LDG30erPlus.jDimmFS4.text")); // NOI18N
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
        jEffekte_2.add(jDimmFS4, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 150, 30, -1));

        jDimmFS1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jDimmFS1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jDimmFS1.setText(bundle.getString("LDG30erPlus.jDimmFS1.text")); // NOI18N
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
        jEffekte_2.add(jDimmFS1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 90, 30, 20));

        jDimmFS2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jDimmFS2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jDimmFS2.setText(bundle.getString("LDG30erPlus.jDimmFS2.text")); // NOI18N
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
        jEffekte_2.add(jDimmFS2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, 30, 20));

        jLabel67.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel67.setText(bundle.getString("LDG30erPlus.jLabel67.text")); // NOI18N
        jEffekte_2.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 130, -1, -1));

        jLabel103.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel103.setText(bundle.getString("LDG30erPlus.jLabel103.text")); // NOI18N
        jEffekte_2.add(jLabel103, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 30, -1, -1));

        jLabel113.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel113.setText(bundle.getString("LDG30erPlus.jLabel113.text")); // NOI18N
        jEffekte_2.add(jLabel113, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 90, -1, -1));

        jLabel114.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel114.setText(bundle.getString("LDG30erPlus.jLabel114.text")); // NOI18N
        jEffekte_2.add(jLabel114, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 150, -1, -1));

        jLabel115.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel115.setText(bundle.getString("LDG30erPlus.jLabel115.text")); // NOI18N
        jEffekte_2.add(jLabel115, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 50, -1, -1));

        jLabel116.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel116.setText(bundle.getString("LDG30erPlus.jLabel116.text")); // NOI18N
        jEffekte_2.add(jLabel116, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 70, -1, -1));

        jLabel117.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel117.setText(bundle.getString("LDG30erPlus.jLabel117.text")); // NOI18N
        jEffekte_2.add(jLabel117, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 110, -1, -1));

        jLabel99.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel99.setText(bundle.getString("LDG30erPlus.jLabel99.text")); // NOI18N
        jEffekte_2.add(jLabel99, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 290, -1, -1));

        jLabel118.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel118.setText(bundle.getString("LDG30erPlus.jLabel118.text")); // NOI18N
        jEffekte_2.add(jLabel118, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 290, -1, -1));

        jLabel119.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel119.setText(bundle.getString("LDG30erPlus.jLabel119.text")); // NOI18N
        jEffekte_2.add(jLabel119, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 290, -1, -1));

        jLabel120.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel120.setText(bundle.getString("LDG30erPlus.jLabel120.text")); // NOI18N
        jEffekte_2.add(jLabel120, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 290, -1, -1));

        jLabel121.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel121.setText(bundle.getString("LDG30erPlus.jLabel121.text")); // NOI18N
        jEffekte_2.add(jLabel121, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 290, -1, -1));

        jLabel122.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel122.setText(bundle.getString("LDG30erPlus.jLabel122.text")); // NOI18N
        jEffekte_2.add(jLabel122, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 290, -1, -1));

        jIn2Aux1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn2Aux1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn2Aux1ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn2Aux1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 330, -1, -1));

        jIn2Aux2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn2Aux2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn2Aux2ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn2Aux2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 330, -1, -1));

        jIn2Aux3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn2Aux3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn2Aux3ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn2Aux3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 330, -1, -1));

        jIn2Aux4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn2Aux4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn2Aux4ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn2Aux4, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 330, -1, -1));

        jIn2Aux5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn2Aux5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn2Aux5ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn2Aux5, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 330, -1, -1));

        jIn2Aux6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn2Aux6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn2Aux6ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn2Aux6, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 330, -1, -1));

        jLabel126.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel126.setText(bundle.getString("LDG30erPlus.jLabel126.text")); // NOI18N
        jEffekte_2.add(jLabel126, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, -1, -1));

        jLabel130.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel130.setText(bundle.getString("LDG30erPlus.jLabel130.text")); // NOI18N
        jEffekte_2.add(jLabel130, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, -1, -1));

        jLabel131.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel131.setText(bundle.getString("LDG30erPlus.jLabel131.text")); // NOI18N
        jEffekte_2.add(jLabel131, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 400, -1, -1));

        jLabel129.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel129.setText(bundle.getString("LDG30erPlus.jLabel129.text")); // NOI18N
        jEffekte_2.add(jLabel129, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 290, -1, -1));

        jLabel184.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel184.setText(bundle.getString("LDG30erPlus.jLabel184.text")); // NOI18N
        jEffekte_2.add(jLabel184, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 290, -1, -1));

        jIn1Auxf.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn1Auxf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn1AuxfActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn1Auxf, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 310, -1, -1));

        jIn1Auxr.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn1Auxr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn1AuxrActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn1Auxr, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 310, -1, -1));

        jIn2Auxf.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn2Auxf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn2AuxfActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn2Auxf, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 330, -1, -1));

        jIn2Auxr.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn2Auxr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn2AuxrActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn2Auxr, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 330, -1, -1));

        jIn1Func1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn1Func1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn1Func1ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn1Func1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 380, -1, -1));

        jIn2Func1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn2Func1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn2Func1ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn2Func1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 400, -1, -1));

        jIn1Func2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn1Func2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn1Func2ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn1Func2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 380, -1, -1));

        jIn2Func2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn2Func2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn2Func2ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn2Func2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 400, -1, -1));

        jLabel100.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel100.setText(bundle.getString("LDG30erPlus.jLabel100.text")); // NOI18N
        jEffekte_2.add(jLabel100, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 360, -1, -1));

        jLabel123.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel123.setText(bundle.getString("LDG30erPlus.jLabel123.text")); // NOI18N
        jEffekte_2.add(jLabel123, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 360, -1, -1));

        jLabel124.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel124.setText(bundle.getString("LDG30erPlus.jLabel124.text")); // NOI18N
        jEffekte_2.add(jLabel124, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 360, -1, -1));

        jLabel125.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel125.setText(bundle.getString("LDG30erPlus.jLabel125.text")); // NOI18N
        jEffekte_2.add(jLabel125, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 360, -1, -1));

        jLabel185.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel185.setText(bundle.getString("LDG30erPlus.jLabel185.text")); // NOI18N
        jEffekte_2.add(jLabel185, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 360, -1, -1));

        jLabel186.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel186.setText(bundle.getString("LDG30erPlus.jLabel186.text")); // NOI18N
        jEffekte_2.add(jLabel186, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 360, -1, -1));

        jLabel187.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel187.setText(bundle.getString("LDG30erPlus.jLabel187.text")); // NOI18N
        jEffekte_2.add(jLabel187, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 360, -1, -1));

        jLabel188.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel188.setText(bundle.getString("LDG30erPlus.jLabel188.text")); // NOI18N
        jEffekte_2.add(jLabel188, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 360, -1, -1));

        jIn1Func3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn1Func3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn1Func3ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn1Func3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 380, -1, -1));

        jIn2Func3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn2Func3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn2Func3ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn2Func3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 400, -1, -1));

        jIn1Func4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn1Func4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn1Func4ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn1Func4, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 380, -1, -1));

        jIn2Func4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn2Func4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn2Func4ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn2Func4, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 400, -1, -1));

        jIn1Func5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn1Func5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn1Func5ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn1Func5, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 380, -1, -1));

        jIn2Func5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn2Func5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn2Func5ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn2Func5, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 400, -1, -1));

        jIn1Func6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn1Func6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn1Func6ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn1Func6, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 380, -1, -1));

        jIn2Func6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn2Func6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn2Func6ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn2Func6, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 400, -1, -1));

        jIn1Func7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn1Func7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn1Func7ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn1Func7, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 380, -1, -1));

        jIn2Func7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn2Func7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn2Func7ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn2Func7, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 400, -1, -1));

        jIn1Func8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn1Func8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn1Func8ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn1Func8, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 380, -1, -1));

        jIn2Func8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn2Func8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn2Func8ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn2Func8, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 400, -1, -1));

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel30.setText(bundle.getString("LDG30erPlus.jLabel30.text")); // NOI18N
        jEffekte_2.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 90, -1, -1));

        jKick6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jKick6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jKick6ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jKick6, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 190, -1, -1));

        jKick1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jKick1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jKick1ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jKick1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 90, -1, -1));

        jKick2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jKick2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jKick2ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jKick2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 110, -1, -1));

        jKick3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jKick3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jKick3ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jKick3, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 130, -1, -1));

        jKick4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jKick4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jKick4ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jKick4, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 150, -1, -1));

        jKick5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jKick5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jKick5ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jKick5, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 170, -1, -1));

        jDimmenf.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jDimmenf.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jDimmenf.setText(bundle.getString("LDG30erPlus.jDimmenf.text")); // NOI18N
        jDimmenf.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jDimmenfFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jDimmenfFocusLost(evt);
            }
        });
        jDimmenf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jDimmenfKeyReleased(evt);
            }
        });
        jEffekte_2.add(jDimmenf, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 30, -1));

        jDimmenr.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jDimmenr.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jDimmenr.setText(bundle.getString("LDG30erPlus.jDimmenr.text")); // NOI18N
        jDimmenr.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jDimmenrFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jDimmenrFocusLost(evt);
            }
        });
        jDimmenr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jDimmenrKeyReleased(evt);
            }
        });
        jEffekte_2.add(jDimmenr, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 30, -1));

        jLabel82.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel82.setText(bundle.getString("LDG30erPlus.jLabel82.text")); // NOI18N
        jEffekte_2.add(jLabel82, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        jbDimmFSr.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jbDimmFSr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbDimmFSrActionPerformed(evt);
            }
        });
        jEffekte_2.add(jbDimmFSr, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, -1, -1));

        jbDimmFSf.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jbDimmFSf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbDimmFSfActionPerformed(evt);
            }
        });
        jEffekte_2.add(jbDimmFSf, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, -1, -1));

        jLabel105.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel105.setText(bundle.getString("LDG30erPlus.jLabel105.text")); // NOI18N
        jEffekte_2.add(jLabel105, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        jDimmFSf.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jDimmFSf.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jDimmFSf.setText(bundle.getString("LDG30erPlus.jDimmFSf.text")); // NOI18N
        jDimmFSf.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jDimmFSfFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jDimmFSfFocusLost(evt);
            }
        });
        jDimmFSf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jDimmFSfKeyReleased(evt);
            }
        });
        jEffekte_2.add(jDimmFSf, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, 30, 20));

        jDimmFSr.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jDimmFSr.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jDimmFSr.setText(bundle.getString("LDG30erPlus.jDimmFSr.text")); // NOI18N
        jDimmFSr.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jDimmFSrFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jDimmFSrFocusLost(evt);
            }
        });
        jDimmFSr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jDimmFSrKeyReleased(evt);
            }
        });
        jEffekte_2.add(jDimmFSr, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, 30, 20));

        jLabel110.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel110.setText(bundle.getString("LDG30erPlus.jLabel110.text")); // NOI18N
        jEffekte_2.add(jLabel110, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, -1));

        jDimmen5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jDimmen5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jDimmen5.setText(bundle.getString("LDG30erPlus.jDimmen5.text")); // NOI18N
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
        jEffekte_2.add(jDimmen5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, 30, -1));

        jLabel127.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel127.setText(bundle.getString("LDG30erPlus.jLabel127.text")); // NOI18N
        jEffekte_2.add(jLabel127, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, -1, -1));

        jbDimmFS5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jbDimmFS5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbDimmFS5ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jbDimmFS5, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 170, -1, -1));

        jbDimmFS6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jbDimmFS6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbDimmFS6ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jbDimmFS6, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 190, -1, -1));

        jDimmen6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jDimmen6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jDimmen6.setText(bundle.getString("LDG30erPlus.jDimmen6.text")); // NOI18N
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
        jEffekte_2.add(jDimmen6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, 30, -1));

        jDimmFS5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jDimmFS5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jDimmFS5.setText(bundle.getString("LDG30erPlus.jDimmFS5.text")); // NOI18N
        jDimmFS5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jDimmFS5FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jDimmFS5FocusLost(evt);
            }
        });
        jDimmFS5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jDimmFS5KeyReleased(evt);
            }
        });
        jEffekte_2.add(jDimmFS5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 170, 30, 20));

        jDimmFS6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jDimmFS6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jDimmFS6.setText(bundle.getString("LDG30erPlus.jDimmFS6.text")); // NOI18N
        jDimmFS6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jDimmFS6FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jDimmFS6FocusLost(evt);
            }
        });
        jDimmFS6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jDimmFS6KeyReleased(evt);
            }
        });
        jEffekte_2.add(jDimmFS6, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 190, 30, -1));

        jKickMotor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jKickMotor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jKickMotor.setText(bundle.getString("LDG30erPlus.jKickMotor.text")); // NOI18N
        jKickMotor.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jKickMotorFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jKickMotorFocusLost(evt);
            }
        });
        jKickMotor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jKickMotorActionPerformed(evt);
            }
        });
        jKickMotor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jKickMotorKeyReleased(evt);
            }
        });
        jEffekte_2.add(jKickMotor, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 110, 30, -1));

        jLabel128.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel128.setText(bundle.getString("LDG30erPlus.jLabel128.text")); // NOI18N
        jEffekte_2.add(jLabel128, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 150, -1, -1));

        jKickFahr1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jKickFahr1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jKickFahr1ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jKickFahr1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 90, -1, -1));

        jLabel189.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel189.setText(bundle.getString("LDG30erPlus.jLabel189.text")); // NOI18N
        jEffekte_2.add(jLabel189, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 170, -1, -1));

        jLabel190.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel190.setText(bundle.getString("LDG30erPlus.jLabel190.text")); // NOI18N
        jEffekte_2.add(jLabel190, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 70, -1, -1));

        jLabel191.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel191.setText(bundle.getString("LDG30erPlus.jLabel191.text")); // NOI18N
        jEffekte_2.add(jLabel191, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 50, -1, 20));

        jLabel192.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel192.setText(bundle.getString("LDG30erPlus.jLabel192.text")); // NOI18N
        jEffekte_2.add(jLabel192, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 70, -1, -1));

        jKickFahr2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jKickFahr2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jKickFahr2ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jKickFahr2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 110, -1, -1));

        jKickFahr3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jKickFahr3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jKickFahr3ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jKickFahr3, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 130, -1, -1));

        jKickFahr4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jKickFahr4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jKickFahr4ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jKickFahr4, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 150, -1, -1));

        jKickFahr5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jKickFahr5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jKickFahr5ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jKickFahr5, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 170, -1, -1));

        jKickFahr6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jKickFahr6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jKickFahr6ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jKickFahr6, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 190, -1, -1));
        jEffekte_2.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 110, -1, -1));

        jSeparator6.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jEffekte_2.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 20, 10, 200));

        jSeparator7.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jEffekte_2.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, 10, 200));

        jLabel104.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel104.setText(bundle.getString("LDG30erPlus.jLabel104.text")); // NOI18N
        jEffekte_2.add(jLabel104, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, -1, -1));

        jLabel206.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel206.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel206.setText(bundle.getString("LDG30erPlus.jLabel206.text")); // NOI18N
        jEffekte_2.add(jLabel206, new org.netbeans.lib.awtextra.AbsoluteConstraints(237, 331, 150, -1));

        jLabel240.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel240.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel240.setText(bundle.getString("LDG30erPlus.jLabel240.text")); // NOI18N
        jEffekte_2.add(jLabel240, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 330, 20, -1));

        jLabel241.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel241.setText(bundle.getString("LDG30erPlus.jLabel241.text")); // NOI18N
        jEffekte_2.add(jLabel241, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 30, -1, -1));

        jLabel242.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel242.setText(bundle.getString("LDG30erPlus.jLabel242.text")); // NOI18N
        jEffekte_2.add(jLabel242, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 130, -1, -1));

        jLabel243.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel243.setText(bundle.getString("LDG30erPlus.jLabel243.text")); // NOI18N
        jEffekte_2.add(jLabel243, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 90, -1, -1));

        jLabel244.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel244.setText(bundle.getString("LDG30erPlus.jLabel244.text")); // NOI18N
        jEffekte_2.add(jLabel244, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 150, -1, -1));

        jLabel245.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel245.setText(bundle.getString("LDG30erPlus.jLabel245.text")); // NOI18N
        jEffekte_2.add(jLabel245, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 110, -1, -1));

        jLabel246.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel246.setText(bundle.getString("LDG30erPlus.jLabel246.text")); // NOI18N
        jEffekte_2.add(jLabel246, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 170, -1, -1));

        jLabel247.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel247.setText(bundle.getString("LDG30erPlus.jLabel247.text")); // NOI18N
        jEffekte_2.add(jLabel247, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 190, -1, -1));

        jDecodereigenschaften.addTab(bundle.getString("LDG30erPlus.jEffekte_2.TabConstraints.tabTitle"), jEffekte_2); // NOI18N

        jSound.setToolTipText(bundle.getString("LDG30erPlus.jSound.toolTipText")); // NOI18N
        jSound.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jSound.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jSoundComponentShown(evt);
            }
        });
        jSound.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jF1_AK1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF1_AK1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF1_AK1ActionPerformed(evt);
            }
        });
        jSound.add(jF1_AK1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 160, -1, -1));

        jF2_SP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF2_SP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF2_SPActionPerformed(evt);
            }
        });
        jSound.add(jF2_SP, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, -1, -1));

        jF3_SP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF3_SP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF3_SPActionPerformed(evt);
            }
        });
        jSound.add(jF3_SP, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 50, -1, -1));

        jF4_SP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF4_SP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF4_SPActionPerformed(evt);
            }
        });
        jSound.add(jF4_SP, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 50, -1, -1));

        jF9_SP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF9_SP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF9_SPActionPerformed(evt);
            }
        });
        jSound.add(jF9_SP, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 50, -1, -1));

        jLabel134.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel134.setText(bundle.getString("LDG30erPlus.jLabel134.text")); // NOI18N
        jSound.add(jLabel134, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, -1, -1));

        jLabel135.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel135.setText(bundle.getString("LDG30erPlus.jLabel135.text")); // NOI18N
        jSound.add(jLabel135, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 30, -1, -1));

        jLabel136.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel136.setText(bundle.getString("LDG30erPlus.jLabel136.text")); // NOI18N
        jSound.add(jLabel136, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 30, -1, -1));

        jLabel137.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel137.setText(bundle.getString("LDG30erPlus.jLabel137.text")); // NOI18N
        jSound.add(jLabel137, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 30, -1, -1));

        jLabel138.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel138.setText(bundle.getString("LDG30erPlus.jLabel138.text")); // NOI18N
        jSound.add(jLabel138, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 30, -1, -1));

        jLabel139.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel139.setText(bundle.getString("LDG30erPlus.jLabel139.text")); // NOI18N
        jSound.add(jLabel139, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 30, -1, -1));

        jLabel140.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel140.setText(bundle.getString("LDG30erPlus.jLabel140.text")); // NOI18N
        jSound.add(jLabel140, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 30, -1, -1));

        jLabel141.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel141.setText(bundle.getString("LDG30erPlus.jLabel141.text")); // NOI18N
        jSound.add(jLabel141, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 30, -1, -1));

        jLabel142.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel142.setText(bundle.getString("LDG30erPlus.jLabel142.text")); // NOI18N
        jSound.add(jLabel142, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 30, -1, -1));

        jLabel143.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel143.setText(bundle.getString("LDG30erPlus.jLabel143.text")); // NOI18N
        jSound.add(jLabel143, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 30, -1, -1));

        jF1_SH.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF1_SH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF1_SHActionPerformed(evt);
            }
        });
        jSound.add(jF1_SH, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, -1, -1));

        jF2_SH.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF2_SH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF2_SHActionPerformed(evt);
            }
        });
        jSound.add(jF2_SH, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, -1, -1));

        jF3_SH.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF3_SH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF3_SHActionPerformed(evt);
            }
        });
        jSound.add(jF3_SH, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, -1, -1));

        jF4_SH.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF4_SH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF4_SHActionPerformed(evt);
            }
        });
        jSound.add(jF4_SH, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 70, -1, -1));

        jF9_SH.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF9_SH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF9_SHActionPerformed(evt);
            }
        });
        jSound.add(jF9_SH, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 70, -1, -1));

        jF11_SP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF11_SP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF11_SPActionPerformed(evt);
            }
        });
        jSound.add(jF11_SP, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 50, -1, -1));

        jF1_Gl.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF1_Gl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF1_GlActionPerformed(evt);
            }
        });
        jSound.add(jF1_Gl, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, -1, -1));

        jF2_Gl.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF2_Gl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF2_GlActionPerformed(evt);
            }
        });
        jSound.add(jF2_Gl, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, -1, -1));

        jF3_Gl.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF3_Gl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF3_GlActionPerformed(evt);
            }
        });
        jSound.add(jF3_Gl, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, -1, -1));

        jF4_Gl.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF4_Gl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF4_GlActionPerformed(evt);
            }
        });
        jSound.add(jF4_Gl, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 90, -1, -1));

        jF9_Gl.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF9_Gl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF9_GlActionPerformed(evt);
            }
        });
        jSound.add(jF9_Gl, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 90, -1, -1));

        jF11_SH.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF11_SH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF11_SHActionPerformed(evt);
            }
        });
        jSound.add(jF11_SH, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 70, -1, -1));

        jF11_Gl.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF11_Gl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF11_GlActionPerformed(evt);
            }
        });
        jSound.add(jF11_Gl, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 90, -1, -1));

        jLabel144.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel144.setText(bundle.getString("LDG30erPlus.jLabel144.text")); // NOI18N
        jSound.add(jLabel144, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 320, -1, 20));

        jF5_SP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF5_SP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF5_SPActionPerformed(evt);
            }
        });
        jSound.add(jF5_SP, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 50, -1, -1));

        jF5_SH.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF5_SH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF5_SHActionPerformed(evt);
            }
        });
        jSound.add(jF5_SH, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 70, -1, -1));

        jF5_Gl.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF5_Gl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF5_GlActionPerformed(evt);
            }
        });
        jSound.add(jF5_Gl, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 90, -1, -1));

        jLabel146.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel146.setText(bundle.getString("LDG30erPlus.jLabel146.text")); // NOI18N
        jSound.add(jLabel146, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        jLabel147.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel147.setText(bundle.getString("LDG30erPlus.jLabel147.text")); // NOI18N
        jSound.add(jLabel147, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        jF6_SP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF6_SP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF6_SPActionPerformed(evt);
            }
        });
        jSound.add(jF6_SP, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 50, -1, -1));

        jF6_SH.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF6_SH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF6_SHActionPerformed(evt);
            }
        });
        jSound.add(jF6_SH, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 70, -1, -1));

        jF6_Gl.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF6_Gl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF6_GlActionPerformed(evt);
            }
        });
        jSound.add(jF6_Gl, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 90, -1, -1));

        jF10_SP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF10_SP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF10_SPActionPerformed(evt);
            }
        });
        jSound.add(jF10_SP, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 50, -1, -1));

        jF7_SP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF7_SP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF7_SPActionPerformed(evt);
            }
        });
        jSound.add(jF7_SP, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 50, -1, -1));

        jF7_SH.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF7_SH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF7_SHActionPerformed(evt);
            }
        });
        jSound.add(jF7_SH, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 70, -1, -1));

        jF10_SH.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF10_SH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF10_SHActionPerformed(evt);
            }
        });
        jSound.add(jF10_SH, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 70, -1, -1));

        jF12_SP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF12_SP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF12_SPActionPerformed(evt);
            }
        });
        jSound.add(jF12_SP, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 50, -1, -1));

        jF7_Gl.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF7_Gl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF7_GlActionPerformed(evt);
            }
        });
        jSound.add(jF7_Gl, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 90, -1, -1));

        jF8_SP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF8_SP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF8_SPActionPerformed(evt);
            }
        });
        jSound.add(jF8_SP, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 50, -1, -1));

        jF10_Gl.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF10_Gl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF10_GlActionPerformed(evt);
            }
        });
        jSound.add(jF10_Gl, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 90, -1, -1));

        jF12_SH.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF12_SH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF12_SHActionPerformed(evt);
            }
        });
        jSound.add(jF12_SH, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 70, -1, -1));

        jF8_SH.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF8_SH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF8_SHActionPerformed(evt);
            }
        });
        jSound.add(jF8_SH, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 70, -1, -1));

        jF8_Gl.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF8_Gl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF8_GlActionPerformed(evt);
            }
        });
        jSound.add(jF8_Gl, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 90, -1, -1));

        jF12_Gl.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF12_Gl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF12_GlActionPerformed(evt);
            }
        });
        jSound.add(jF12_Gl, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 90, -1, -1));

        jLabel148.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel148.setText(bundle.getString("LDG30erPlus.jLabel148.text")); // NOI18N
        jSound.add(jLabel148, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 30, -1, -1));

        jLabel150.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel150.setText(bundle.getString("LDG30erPlus.jLabel150.text")); // NOI18N
        jSound.add(jLabel150, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 30, -1, -1));

        jLabel132.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel132.setText(bundle.getString("LDG30erPlus.jLabel132.text")); // NOI18N
        jSound.add(jLabel132, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 280, -1, 20));
        jSound.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 122, 410, 10));

        jLabel133.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel133.setText(bundle.getString("LDG30erPlus.jLabel133.text")); // NOI18N
        jSound.add(jLabel133, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 6, 190, 20));

        jLabel145.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel145.setText(bundle.getString("LDG30erPlus.jLabel145.text")); // NOI18N
        jSound.add(jLabel145, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, -1, -1));

        jLabel149.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel149.setText(bundle.getString("LDG30erPlus.jLabel149.text")); // NOI18N
        jSound.add(jLabel149, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, -1, -1));

        jLabel151.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel151.setText(bundle.getString("LDG30erPlus.jLabel151.text")); // NOI18N
        jSound.add(jLabel151, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 150, -1, -1));

        jLabel152.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel152.setText(bundle.getString("LDG30erPlus.jLabel152.text")); // NOI18N
        jSound.add(jLabel152, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 150, -1, -1));

        jLabel161.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel161.setText(bundle.getString("LDG30erPlus.jLabel161.text")); // NOI18N
        jSound.add(jLabel161, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 210, -1, -1));

        jLabel162.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel162.setText(bundle.getString("LDG30erPlus.jLabel162.text")); // NOI18N
        jSound.add(jLabel162, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 210, -1, -1));

        jLabel163.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel163.setText(bundle.getString("LDG30erPlus.jLabel163.text")); // NOI18N
        jSound.add(jLabel163, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 210, -1, -1));

        jLabel164.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel164.setText(bundle.getString("LDG30erPlus.jLabel164.text")); // NOI18N
        jSound.add(jLabel164, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 210, -1, -1));

        jLabel165.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel165.setText(bundle.getString("LDG30erPlus.jLabel165.text")); // NOI18N
        jSound.add(jLabel165, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 210, -1, -1));

        jLabel166.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel166.setText(bundle.getString("LDG30erPlus.jLabel166.text")); // NOI18N
        jSound.add(jLabel166, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 210, -1, -1));

        jLabel193.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel193.setText(bundle.getString("LDG30erPlus.jLabel193.text")); // NOI18N
        jSound.add(jLabel193, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 210, -1, -1));

        jLabel194.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel194.setText(bundle.getString("LDG30erPlus.jLabel194.text")); // NOI18N
        jSound.add(jLabel194, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 210, -1, -1));

        jLabel195.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel195.setText(bundle.getString("LDG30erPlus.jLabel195.text")); // NOI18N
        jSound.add(jLabel195, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 210, -1, -1));

        jLabel196.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel196.setText(bundle.getString("LDG30erPlus.jLabel196.text")); // NOI18N
        jSound.add(jLabel196, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 210, -1, -1));

        jLabel197.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel197.setText(bundle.getString("LDG30erPlus.jLabel197.text")); // NOI18N
        jSound.add(jLabel197, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 210, -1, -1));

        jLabel198.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel198.setText(bundle.getString("LDG30erPlus.jLabel198.text")); // NOI18N
        jSound.add(jLabel198, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 210, -1, -1));

        jLabel199.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel199.setText(bundle.getString("LDG30erPlus.jLabel199.text")); // NOI18N
        jSound.add(jLabel199, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        jLabel200.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel200.setText(bundle.getString("LDG30erPlus.jLabel200.text")); // NOI18N
        jSound.add(jLabel200, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 311, 110, -1));

        jLabel201.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel201.setText(bundle.getString("LDG30erPlus.jLabel201.text")); // NOI18N
        jSound.add(jLabel201, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 390, -1, -1));

        jF1_SP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF1_SP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF1_SPActionPerformed(evt);
            }
        });
        jSound.add(jF1_SP, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, -1, -1));

        jF1_AK2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF1_AK2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF1_AK2ActionPerformed(evt);
            }
        });
        jSound.add(jF1_AK2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 160, -1, -1));

        jF1_AK3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF1_AK3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF1_AK3ActionPerformed(evt);
            }
        });
        jSound.add(jF1_AK3, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 160, -1, -1));

        jF1_AK4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF1_AK4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF1_AK4ActionPerformed(evt);
            }
        });
        jSound.add(jF1_AK4, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 160, -1, -1));

        jF1_AK5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF1_AK5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF1_AK5ActionPerformed(evt);
            }
        });
        jSound.add(jF1_AK5, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 160, -1, -1));

        jF1_AK6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF1_AK6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF1_AK6ActionPerformed(evt);
            }
        });
        jSound.add(jF1_AK6, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 160, -1, -1));

        jF1_AK7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF1_AK7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF1_AK7ActionPerformed(evt);
            }
        });
        jSound.add(jF1_AK7, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 160, -1, -1));

        jF1_AK8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF1_AK8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF1_AK8ActionPerformed(evt);
            }
        });
        jSound.add(jF1_AK8, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 160, -1, -1));

        jF1_AK9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF1_AK9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF1_AK9ActionPerformed(evt);
            }
        });
        jSound.add(jF1_AK9, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 160, -1, -1));

        jF1_AK10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF1_AK10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF1_AK10ActionPerformed(evt);
            }
        });
        jSound.add(jF1_AK10, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 160, -1, -1));

        jF1_AK11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF1_AK11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF1_AK11ActionPerformed(evt);
            }
        });
        jSound.add(jF1_AK11, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 160, -1, -1));

        jIN1_PendelF12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIN1_PendelF12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIN1_PendelF12ActionPerformed(evt);
            }
        });
        jSound.add(jIN1_PendelF12, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 380, -1, -1));

        jF2_AK1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF2_AK1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF2_AK1ActionPerformed(evt);
            }
        });
        jSound.add(jF2_AK1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 220, -1, -1));

        jF2_AK2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF2_AK2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF2_AK2ActionPerformed(evt);
            }
        });
        jSound.add(jF2_AK2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 220, -1, -1));

        jF2_AK3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF2_AK3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF2_AK3ActionPerformed(evt);
            }
        });
        jSound.add(jF2_AK3, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 220, -1, -1));

        jF2_AK4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF2_AK4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF2_AK4ActionPerformed(evt);
            }
        });
        jSound.add(jF2_AK4, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 220, -1, -1));

        jF2_AK5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF2_AK5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF2_AK5ActionPerformed(evt);
            }
        });
        jSound.add(jF2_AK5, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 220, -1, -1));

        jF2_AK6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF2_AK6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF2_AK6ActionPerformed(evt);
            }
        });
        jSound.add(jF2_AK6, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 220, -1, -1));

        jF2_AK7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF2_AK7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF2_AK7ActionPerformed(evt);
            }
        });
        jSound.add(jF2_AK7, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 220, -1, -1));

        jF2_AK8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF2_AK8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF2_AK8ActionPerformed(evt);
            }
        });
        jSound.add(jF2_AK8, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 220, -1, -1));

        jF2_AK9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF2_AK9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF2_AK9ActionPerformed(evt);
            }
        });
        jSound.add(jF2_AK9, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 220, -1, -1));

        jF2_AK10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF2_AK10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF2_AK10ActionPerformed(evt);
            }
        });
        jSound.add(jF2_AK10, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 220, -1, -1));

        jF2_AK11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF2_AK11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF2_AK11ActionPerformed(evt);
            }
        });
        jSound.add(jF2_AK11, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 220, -1, -1));

        jF2_AK12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF2_AK12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF2_AK12ActionPerformed(evt);
            }
        });
        jSound.add(jF2_AK12, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 220, -1, -1));

        jF1_AKf.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF1_AKf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF1_AKfActionPerformed(evt);
            }
        });
        jSound.add(jF1_AKf, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 180, -1, -1));

        jF1_AKr.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF1_AKr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF1_AKrActionPerformed(evt);
            }
        });
        jSound.add(jF1_AKr, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 180, -1, -1));

        jLabel202.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel202.setText(bundle.getString("LDG30erPlus.jLabel202.text")); // NOI18N
        jSound.add(jLabel202, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 180, -1, 20));

        jLabel203.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel203.setText(bundle.getString("LDG30erPlus.jLabel203.text")); // NOI18N
        jSound.add(jLabel203, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 180, -1, 20));

        jLabel38.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel38.setText(bundle.getString("LDG30erPlus.jLabel38.text")); // NOI18N
        jSound.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 250, -1, 20));

        jAnkuppelMotor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAnkuppelMotor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jAnkuppelMotor.setText(bundle.getString("LDG30erPlus.jAnkuppelMotor.text")); // NOI18N
        jAnkuppelMotor.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jAnkuppelMotorFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jAnkuppelMotorFocusLost(evt);
            }
        });
        jAnkuppelMotor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jAnkuppelMotorKeyReleased(evt);
            }
        });
        jSound.add(jAnkuppelMotor, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 250, 30, -1));

        jF2_AKf.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF2_AKf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF2_AKfActionPerformed(evt);
            }
        });
        jSound.add(jF2_AKf, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 240, -1, -1));

        jF2_AKr.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF2_AKr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF2_AKrActionPerformed(evt);
            }
        });
        jSound.add(jF2_AKr, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 240, -1, -1));

        jLabel204.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel204.setText(bundle.getString("LDG30erPlus.jLabel204.text")); // NOI18N
        jSound.add(jLabel204, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 350, -1, 20));

        jLabel205.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel205.setText(bundle.getString("LDG30erPlus.jLabel205.text")); // NOI18N
        jSound.add(jLabel205, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 330, -1, 20));
        jSound.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 410, 10));

        jLabel208.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel208.setText(bundle.getString("LDG30erPlus.jLabel208.text")); // NOI18N
        jSound.add(jLabel208, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 131, 80, 20));

        jLabel209.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel209.setText(bundle.getString("LDG30erPlus.jLabel209.text")); // NOI18N
        jSound.add(jLabel209, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, -1));

        jLabel210.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel210.setText(bundle.getString("LDG30erPlus.jLabel210.text")); // NOI18N
        jSound.add(jLabel210, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 175, -1, 20));

        jLabel211.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel211.setText(bundle.getString("LDG30erPlus.jLabel211.text")); // NOI18N
        jSound.add(jLabel211, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 235, -1, 20));

        jIN1_PendelF5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIN1_PendelF5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIN1_PendelF5ActionPerformed(evt);
            }
        });
        jSound.add(jIN1_PendelF5, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 350, -1, -1));

        jIN1_PendelF6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIN1_PendelF6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIN1_PendelF6ActionPerformed(evt);
            }
        });
        jSound.add(jIN1_PendelF6, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 350, -1, -1));

        jIN1_PendelF7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIN1_PendelF7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIN1_PendelF7ActionPerformed(evt);
            }
        });
        jSound.add(jIN1_PendelF7, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 350, -1, -1));

        jIN1_PendelF8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIN1_PendelF8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIN1_PendelF8ActionPerformed(evt);
            }
        });
        jSound.add(jIN1_PendelF8, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 350, -1, -1));

        jIN1_PendelF9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIN1_PendelF9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIN1_PendelF9ActionPerformed(evt);
            }
        });
        jSound.add(jIN1_PendelF9, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 380, -1, -1));

        jIN1_PendelF10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIN1_PendelF10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIN1_PendelF10ActionPerformed(evt);
            }
        });
        jSound.add(jIN1_PendelF10, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 380, -1, -1));

        jIN1_PendelF11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIN1_PendelF11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIN1_PendelF11ActionPerformed(evt);
            }
        });
        jSound.add(jIN1_PendelF11, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 380, -1, -1));

        jF1_AK12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF1_AK12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF1_AK12ActionPerformed(evt);
            }
        });
        jSound.add(jF1_AK12, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 160, -1, -1));

        jLabel212.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel212.setText(bundle.getString("LDG30erPlus.jLabel212.text")); // NOI18N
        jSound.add(jLabel212, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 150, -1, -1));

        jLabel213.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel213.setText(bundle.getString("LDG30erPlus.jLabel213.text")); // NOI18N
        jSound.add(jLabel213, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 150, -1, -1));

        jLabel214.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel214.setText(bundle.getString("LDG30erPlus.jLabel214.text")); // NOI18N
        jSound.add(jLabel214, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 150, -1, -1));

        jLabel215.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel215.setText(bundle.getString("LDG30erPlus.jLabel215.text")); // NOI18N
        jSound.add(jLabel215, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 150, -1, -1));

        jLabel216.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel216.setText(bundle.getString("LDG30erPlus.jLabel216.text")); // NOI18N
        jSound.add(jLabel216, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 150, -1, -1));

        jLabel217.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel217.setText(bundle.getString("LDG30erPlus.jLabel217.text")); // NOI18N
        jSound.add(jLabel217, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 150, -1, -1));

        jLabel218.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel218.setText(bundle.getString("LDG30erPlus.jLabel218.text")); // NOI18N
        jSound.add(jLabel218, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 150, -1, -1));

        jLabel219.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel219.setText(bundle.getString("LDG30erPlus.jLabel219.text")); // NOI18N
        jSound.add(jLabel219, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 150, -1, -1));

        jLabel220.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel220.setText(bundle.getString("LDG30erPlus.jLabel220.text")); // NOI18N
        jSound.add(jLabel220, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 340, -1, -1));

        jLabel221.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel221.setText(bundle.getString("LDG30erPlus.jLabel221.text")); // NOI18N
        jSound.add(jLabel221, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 340, -1, -1));

        jLabel222.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel222.setText(bundle.getString("LDG30erPlus.jLabel222.text")); // NOI18N
        jSound.add(jLabel222, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 340, -1, -1));

        jLabel223.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel223.setText(bundle.getString("LDG30erPlus.jLabel223.text")); // NOI18N
        jSound.add(jLabel223, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 340, -1, -1));

        jLabel224.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel224.setText(bundle.getString("LDG30erPlus.jLabel224.text")); // NOI18N
        jSound.add(jLabel224, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 370, -1, -1));

        jLabel225.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel225.setText(bundle.getString("LDG30erPlus.jLabel225.text")); // NOI18N
        jSound.add(jLabel225, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 370, -1, -1));

        jLabel226.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel226.setText(bundle.getString("LDG30erPlus.jLabel226.text")); // NOI18N
        jSound.add(jLabel226, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 370, -1, -1));

        jLabel227.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel227.setText(bundle.getString("LDG30erPlus.jLabel227.text")); // NOI18N
        jSound.add(jLabel227, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 370, -1, -1));

        jLabel228.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel228.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel228.setText(bundle.getString("LDG30erPlus.jLabel228.text")); // NOI18N
        jSound.add(jLabel228, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 340, 20, 20));

        jIN1_Pendelf.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIN1_Pendelf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIN1_PendelfActionPerformed(evt);
            }
        });
        jSound.add(jIN1_Pendelf, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 330, -1, -1));

        jIN1_Pendelr.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIN1_Pendelr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIN1_PendelrActionPerformed(evt);
            }
        });
        jSound.add(jIN1_Pendelr, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 350, -1, -1));

        jLabel229.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel229.setText(bundle.getString("LDG30erPlus.jLabel229.text")); // NOI18N
        jSound.add(jLabel229, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 240, -1, 20));

        jLabel230.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel230.setText(bundle.getString("LDG30erPlus.jLabel230.text")); // NOI18N
        jSound.add(jLabel230, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 240, -1, 20));

        jLabel231.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel231.setText(bundle.getString("LDG30erPlus.jLabel231.text")); // NOI18N
        jSound.add(jLabel231, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 400, -1, 20));

        jLabel232.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel232.setText(bundle.getString("LDG30erPlus.jLabel232.text")); // NOI18N
        jSound.add(jLabel232, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 380, -1, 20));

        jIN2_Pendelf.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIN2_Pendelf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIN2_PendelfActionPerformed(evt);
            }
        });
        jSound.add(jIN2_Pendelf, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 380, -1, -1));

        jIN2_Pendelr.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIN2_Pendelr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIN2_PendelrActionPerformed(evt);
            }
        });
        jSound.add(jIN2_Pendelr, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 400, -1, -1));

        jLabel233.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel233.setText(bundle.getString("LDG30erPlus.jLabel233.text")); // NOI18N
        jSound.add(jLabel233, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, -1, -1));

        jIN1_PendelRW.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIN1_PendelRW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIN1_PendelRWActionPerformed(evt);
            }
        });
        jSound.add(jIN1_PendelRW, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 340, -1, -1));

        jIN2_PendelRW.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIN2_PendelRW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIN2_PendelRWActionPerformed(evt);
            }
        });
        jSound.add(jIN2_PendelRW, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 390, -1, -1));

        jLabel234.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel234.setText(bundle.getString("LDG30erPlus.jLabel234.text")); // NOI18N
        jSound.add(jLabel234, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 311, 50, 20));

        jLabel235.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel235.setText(bundle.getString("LDG30erPlus.jLabel235.text")); // NOI18N
        jSound.add(jLabel235, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 300, -1, 30));

        jIN1_PendelHaltZeit.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIN1_PendelHaltZeit.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jIN1_PendelHaltZeit.setText(bundle.getString("LDG30erPlus.jIN1_PendelHaltZeit.text")); // NOI18N
        jIN1_PendelHaltZeit.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jIN1_PendelHaltZeitFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jIN1_PendelHaltZeitFocusLost(evt);
            }
        });
        jIN1_PendelHaltZeit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIN1_PendelHaltZeitActionPerformed(evt);
            }
        });
        jIN1_PendelHaltZeit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jIN1_PendelHaltZeitKeyReleased(evt);
            }
        });
        jSound.add(jIN1_PendelHaltZeit, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 340, 50, -1));

        jIN2_PendelHaltZeit.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIN2_PendelHaltZeit.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jIN2_PendelHaltZeit.setText(bundle.getString("LDG30erPlus.jIN2_PendelHaltZeit.text")); // NOI18N
        jIN2_PendelHaltZeit.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jIN2_PendelHaltZeitFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jIN2_PendelHaltZeitFocusLost(evt);
            }
        });
        jIN2_PendelHaltZeit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jIN2_PendelHaltZeitKeyReleased(evt);
            }
        });
        jSound.add(jIN2_PendelHaltZeit, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 390, 50, -1));

        jLabel236.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel236.setText(bundle.getString("LDG30erPlus.jLabel236.text")); // NOI18N
        jSound.add(jLabel236, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 306, 70, 20));

        jLabel237.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel237.setText(bundle.getString("LDG30erPlus.jLabel237.text")); // NOI18N
        jSound.add(jLabel237, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 320, -1, 20));

        jLabel248.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel248.setText(bundle.getString("LDG30erPlus.jLabel248.text")); // NOI18N
        jSound.add(jLabel248, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 340, -1, -1));

        jLabel249.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel249.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel249.setText(bundle.getString("LDG30erPlus.jLabel249.text")); // NOI18N
        jSound.add(jLabel249, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 390, 20, 20));

        jDecodereigenschaften.addTab(bundle.getString("LDG30erPlus.jSound.TabConstraints.tabTitle"), jSound); // NOI18N

        jServo.setToolTipText(bundle.getString("LDG30erPlus.jServo.toolTipText")); // NOI18N
        jServo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jServo.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jServoComponentShown(evt);
            }
        });
        jServo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelVelocity.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelVelocity.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelVelocity.setText(bundle.getString("LDG30erPlus.jLabelVelocity.text")); // NOI18N
        jServo.add(jLabelVelocity, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 110, 20));

        jServoPOM.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jServoPOM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jServoPOMActionPerformed(evt);
            }
        });
        jServo.add(jServoPOM, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 130, -1, -1));

        jServoF9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jServoF9.setText(bundle.getString("LDG30erPlus.jServoF9.text")); // NOI18N
        jServoF9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jServoF9ActionPerformed(evt);
            }
        });
        jServo.add(jServoF9, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 40, -1, -1));

        jServoF10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jServoF10.setText(bundle.getString("LDG30erPlus.jServoF10.text")); // NOI18N
        jServoF10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jServoF10ActionPerformed(evt);
            }
        });
        jServo.add(jServoF10, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 40, -1, -1));

        jServoF12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jServoF12.setText(bundle.getString("LDG30erPlus.jServoF12.text")); // NOI18N
        jServoF12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jServoF12ActionPerformed(evt);
            }
        });
        jServo.add(jServoF12, new org.netbeans.lib.awtextra.AbsoluteConstraints(398, 40, 50, -1));

        jServoF5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jServoF5.setText(bundle.getString("LDG30erPlus.jServoF5.text")); // NOI18N
        jServoF5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jServoF5ActionPerformed(evt);
            }
        });
        jServo.add(jServoF5, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, -1, -1));

        jServoF6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jServoF6.setText(bundle.getString("LDG30erPlus.jServoF6.text")); // NOI18N
        jServoF6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jServoF6ActionPerformed(evt);
            }
        });
        jServo.add(jServoF6, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 40, -1, -1));

        jServoF7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jServoF7.setText(bundle.getString("LDG30erPlus.jServoF7.text")); // NOI18N
        jServoF7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jServoF7ActionPerformed(evt);
            }
        });
        jServo.add(jServoF7, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 40, -1, -1));

        jServoF8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jServoF8.setText(bundle.getString("LDG30erPlus.jServoF8.text")); // NOI18N
        jServoF8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jServoF8ActionPerformed(evt);
            }
        });
        jServo.add(jServoF8, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 40, -1, -1));

        jLabel167.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel167.setText(bundle.getString("LDG30erPlus.jLabel167.text")); // NOI18N
        jServo.add(jLabel167, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 351, 140, 20));

        jGeschwindigkeit.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jGeschwindigkeit.setText(bundle.getString("LDG30erPlus.jGeschwindigkeit.text")); // NOI18N
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
        jServo.add(jGeschwindigkeit, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, 50, -1));

        jServoF11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jServoF11.setText(bundle.getString("LDG30erPlus.jServoF11.text")); // NOI18N
        jServoF11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jServoF11ActionPerformed(evt);
            }
        });
        jServo.add(jServoF11, new org.netbeans.lib.awtextra.AbsoluteConstraints(348, 40, 50, -1));

        jLabelSwitchWith.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelSwitchWith.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelSwitchWith.setText(bundle.getString("LDG30erPlus.jLabelSwitchWith.text")); // NOI18N
        jServo.add(jLabelSwitchWith, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 41, 90, 20));

        jLabel169.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel169.setText(bundle.getString("LDG30erPlus.jLabel169.text")); // NOI18N
        jServo.add(jLabel169, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 210, 230, -1));

        jLabelRightPos.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelRightPos.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelRightPos.setText(bundle.getString("LDG30erPlus.jLabelRightPos.text")); // NOI18N
        jServo.add(jLabelRightPos, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 71, 120, -1));

        jlinkerAnschlag.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jlinkerAnschlag.setText(bundle.getString("LDG30erPlus.jlinkerAnschlag.text")); // NOI18N
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
        jServo.add(jlinkerAnschlag, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, 50, -1));

        jrechterAnschlag.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jrechterAnschlag.setText(bundle.getString("LDG30erPlus.jrechterAnschlag.text")); // NOI18N
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
        jServo.add(jrechterAnschlag, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 70, 50, -1));

        jLabelLeftPos.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelLeftPos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelLeftPos.setText(bundle.getString("LDG30erPlus.jLabelLeftPos.text")); // NOI18N
        jServo.add(jLabelLeftPos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 100, -1));

        jLabel172.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel172.setText(bundle.getString("LDG30erPlus.jLabel172.text")); // NOI18N
        jServo.add(jLabel172, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 330, -1));
        jServo.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 340, 200, 10));

        jLabelChangeLR.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelChangeLR.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelChangeLR.setText(bundle.getString("LDG30erPlus.jLabelChangeLR.text")); // NOI18N
        jServo.add(jLabelChangeLR, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 170, 20));

        jFS0_F5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS0_F5.setText(bundle.getString("LDG30erPlus.jFS0_F5.text")); // NOI18N
        jFS0_F5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFS0_F5ActionPerformed(evt);
            }
        });
        jServo.add(jFS0_F5, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 250, -1, -1));

        jFS0_F6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS0_F6.setText(bundle.getString("LDG30erPlus.jFS0_F6.text")); // NOI18N
        jFS0_F6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFS0_F6ActionPerformed(evt);
            }
        });
        jServo.add(jFS0_F6, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 250, -1, -1));

        jFS0_F7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS0_F7.setText(bundle.getString("LDG30erPlus.jFS0_F7.text")); // NOI18N
        jFS0_F7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFS0_F7ActionPerformed(evt);
            }
        });
        jServo.add(jFS0_F7, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 250, -1, -1));

        jFS0_F8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS0_F8.setText(bundle.getString("LDG30erPlus.jFS0_F8.text")); // NOI18N
        jFS0_F8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFS0_F8ActionPerformed(evt);
            }
        });
        jServo.add(jFS0_F8, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 250, -1, -1));

        jFS0_F1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS0_F1.setText(bundle.getString("LDG30erPlus.jFS0_F1.text")); // NOI18N
        jFS0_F1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFS0_F1ActionPerformed(evt);
            }
        });
        jServo.add(jFS0_F1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 230, -1, -1));

        jFS0_F2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS0_F2.setText(bundle.getString("LDG30erPlus.jFS0_F2.text")); // NOI18N
        jFS0_F2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFS0_F2ActionPerformed(evt);
            }
        });
        jServo.add(jFS0_F2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 230, -1, -1));

        jFS0_F3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS0_F3.setText(bundle.getString("LDG30erPlus.jFS0_F3.text")); // NOI18N
        jFS0_F3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFS0_F3ActionPerformed(evt);
            }
        });
        jServo.add(jFS0_F3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 230, -1, -1));

        jFS0_F4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS0_F4.setText(bundle.getString("LDG30erPlus.jFS0_F4.text")); // NOI18N
        jFS0_F4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFS0_F4ActionPerformed(evt);
            }
        });
        jServo.add(jFS0_F4, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 230, -1, -1));

        jLabel174.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel174.setText(bundle.getString("LDG30erPlus.jLabel174.text")); // NOI18N
        jServo.add(jLabel174, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, -1, 20));

        jFS0_AUX1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS0_AUX1.setText(bundle.getString("LDG30erPlus.jFS0_AUX1.text")); // NOI18N
        jFS0_AUX1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFS0_AUX1ActionPerformed(evt);
            }
        });
        jServo.add(jFS0_AUX1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 280, -1, -1));

        jFS0_AUX2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS0_AUX2.setText(bundle.getString("LDG30erPlus.jFS0_AUX2.text")); // NOI18N
        jFS0_AUX2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFS0_AUX2ActionPerformed(evt);
            }
        });
        jServo.add(jFS0_AUX2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 280, -1, -1));

        jFS0_AUX3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS0_AUX3.setText(bundle.getString("LDG30erPlus.jFS0_AUX3.text")); // NOI18N
        jFS0_AUX3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFS0_AUX3ActionPerformed(evt);
            }
        });
        jServo.add(jFS0_AUX3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 280, -1, -1));

        jFS0_AUX4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS0_AUX4.setText(bundle.getString("LDG30erPlus.jFS0_AUX4.text")); // NOI18N
        jFS0_AUX4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFS0_AUX4ActionPerformed(evt);
            }
        });
        jServo.add(jFS0_AUX4, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 280, -1, -1));

        jFS0_AUX5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS0_AUX5.setText(bundle.getString("LDG30erPlus.jFS0_AUX5.text")); // NOI18N
        jFS0_AUX5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFS0_AUX5ActionPerformed(evt);
            }
        });
        jServo.add(jFS0_AUX5, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 280, -1, -1));

        jFS0_AUX6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS0_AUX6.setText(bundle.getString("LDG30erPlus.jFS0_AUX6.text")); // NOI18N
        jFS0_AUX6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFS0_AUX6ActionPerformed(evt);
            }
        });
        jServo.add(jFS0_AUX6, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 280, -1, -1));

        jLabel175.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel175.setText(bundle.getString("LDG30erPlus.jLabel175.text")); // NOI18N
        jServo.add(jLabel175, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, -1, 20));

        jFS0_AUXinv1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS0_AUXinv1.setText(bundle.getString("LDG30erPlus.jFS0_AUXinv1.text")); // NOI18N
        jFS0_AUXinv1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFS0_AUXinv1ActionPerformed(evt);
            }
        });
        jServo.add(jFS0_AUXinv1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 350, -1, -1));

        jFS0_AUXinv2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS0_AUXinv2.setText(bundle.getString("LDG30erPlus.jFS0_AUXinv2.text")); // NOI18N
        jFS0_AUXinv2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFS0_AUXinv2ActionPerformed(evt);
            }
        });
        jServo.add(jFS0_AUXinv2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 350, -1, -1));

        jFS0_AUXinv3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS0_AUXinv3.setText(bundle.getString("LDG30erPlus.jFS0_AUXinv3.text")); // NOI18N
        jFS0_AUXinv3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFS0_AUXinv3ActionPerformed(evt);
            }
        });
        jServo.add(jFS0_AUXinv3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 350, -1, -1));

        jFS0_AUXinv4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS0_AUXinv4.setText(bundle.getString("LDG30erPlus.jFS0_AUXinv4.text")); // NOI18N
        jFS0_AUXinv4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFS0_AUXinv4ActionPerformed(evt);
            }
        });
        jServo.add(jFS0_AUXinv4, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 350, -1, -1));

        jFS0_AUXinv5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS0_AUXinv5.setText(bundle.getString("LDG30erPlus.jFS0_AUXinv5.text")); // NOI18N
        jFS0_AUXinv5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFS0_AUXinv5ActionPerformed(evt);
            }
        });
        jServo.add(jFS0_AUXinv5, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 350, -1, -1));

        jFS0_AUXinv6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS0_AUXinv6.setText(bundle.getString("LDG30erPlus.jFS0_AUXinv6.text")); // NOI18N
        jFS0_AUXinv6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFS0_AUXinv6ActionPerformed(evt);
            }
        });
        jServo.add(jFS0_AUXinv6, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 350, -1, -1));
        jServo.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 420, 10));

        jFS0_AUX7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS0_AUX7.setText(bundle.getString("LDG30erPlus.jFS0_AUX7.text")); // NOI18N
        jFS0_AUX7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFS0_AUX7ActionPerformed(evt);
            }
        });
        jServo.add(jFS0_AUX7, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 300, -1, -1));

        jFS0_AUX8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS0_AUX8.setText(bundle.getString("LDG30erPlus.jFS0_AUX8.text")); // NOI18N
        jFS0_AUX8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFS0_AUX8ActionPerformed(evt);
            }
        });
        jServo.add(jFS0_AUX8, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 300, -1, -1));

        jFS0_AUX9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS0_AUX9.setText(bundle.getString("LDG30erPlus.jFS0_AUX9.text")); // NOI18N
        jFS0_AUX9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFS0_AUX9ActionPerformed(evt);
            }
        });
        jServo.add(jFS0_AUX9, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 300, -1, -1));

        jFS0_AUX10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS0_AUX10.setText(bundle.getString("LDG30erPlus.jFS0_AUX10.text")); // NOI18N
        jFS0_AUX10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFS0_AUX10ActionPerformed(evt);
            }
        });
        jServo.add(jFS0_AUX10, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 300, -1, -1));

        jFS0_AUX11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS0_AUX11.setText(bundle.getString("LDG30erPlus.jFS0_AUX11.text")); // NOI18N
        jFS0_AUX11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFS0_AUX11ActionPerformed(evt);
            }
        });
        jServo.add(jFS0_AUX11, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 300, -1, -1));

        jFS0_AUX12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS0_AUX12.setText(bundle.getString("LDG30erPlus.jFS0_AUX12.text")); // NOI18N
        jFS0_AUX12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFS0_AUX12ActionPerformed(evt);
            }
        });
        jServo.add(jFS0_AUX12, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 300, -1, -1));

        jLabel176.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel176.setText(bundle.getString("LDG30erPlus.jLabel176.text")); // NOI18N
        jServo.add(jLabel176, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, -1, 20));

        jServoTausch.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jServoTausch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jServoTauschActionPerformed(evt);
            }
        });
        jServo.add(jServoTausch, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 130, -1, -1));

        jLabelServoPOM.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelServoPOM.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelServoPOM.setText(bundle.getString("LDG30erPlus.jLabelServoPOM.text")); // NOI18N
        jServo.add(jLabelServoPOM, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 130, 160, 20));

        jDecodereigenschaften.addTab(bundle.getString("LDG30erPlus.jServo.TabConstraints.tabTitle"), jServo); // NOI18N

        jAnalog.setToolTipText(bundle.getString("LDG30erPlus.jAnalog.toolTipText")); // NOI18N
        jAnalog.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAnalog.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jAnalogComponentShown(evt);
            }
        });
        jAnalog.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText(bundle.getString("LDG30erPlus.jLabel2.text")); // NOI18N
        jAnalog.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 261, 150, -1));

        jF1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF1.setText(bundle.getString("LDG30erPlus.jF1.text")); // NOI18N
        jF1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF1ActionPerformed(evt);
            }
        });
        jAnalog.add(jF1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, -1, -1));

        jF2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF2.setText(bundle.getString("LDG30erPlus.jF2.text")); // NOI18N
        jF2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF2ActionPerformed(evt);
            }
        });
        jAnalog.add(jF2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, -1, -1));

        jF3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF3.setText(bundle.getString("LDG30erPlus.jF3.text")); // NOI18N
        jF3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF3ActionPerformed(evt);
            }
        });
        jAnalog.add(jF3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, -1, -1));

        jF4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF4.setText(bundle.getString("LDG30erPlus.jF4.text")); // NOI18N
        jF4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF4ActionPerformed(evt);
            }
        });
        jAnalog.add(jF4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, -1, -1));

        jF5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF5.setText(bundle.getString("LDG30erPlus.jF5.text")); // NOI18N
        jF5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF5ActionPerformed(evt);
            }
        });
        jAnalog.add(jF5, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, -1, -1));

        jF6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF6.setText(bundle.getString("LDG30erPlus.jF6.text")); // NOI18N
        jF6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF6ActionPerformed(evt);
            }
        });
        jAnalog.add(jF6, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, -1, -1));

        jF7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF7.setText(bundle.getString("LDG30erPlus.jF7.text")); // NOI18N
        jF7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF7ActionPerformed(evt);
            }
        });
        jAnalog.add(jF7, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 120, -1, -1));

        jF8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF8.setText(bundle.getString("LDG30erPlus.jF8.text")); // NOI18N
        jF8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF8ActionPerformed(evt);
            }
        });
        jAnalog.add(jF8, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 150, -1, -1));

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel17.setText(bundle.getString("LDG30erPlus.jLabel17.text")); // NOI18N
        jAnalog.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        jPacketTimeOut.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPacketTimeOut.setText(bundle.getString("LDG30erPlus.jPacketTimeOut.text")); // NOI18N
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

        buttonGroup2.add(jAnalogRW_W);
        jAnalogRW_W.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAnalogRW_W.setSelected(true);
        jAnalogRW_W.setText(bundle.getString("LDG30erPlus.jAnalogRW_W.text")); // NOI18N
        jAnalogRW_W.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAnalogRW_WActionPerformed(evt);
            }
        });
        jAnalog.add(jAnalogRW_W, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 251, 270, -1));

        buttonGroup2.add(jAnalogRW_G);
        jAnalogRW_G.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAnalogRW_G.setText(bundle.getString("LDG30erPlus.jAnalogRW_G.text")); // NOI18N
        jAnalogRW_G.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAnalogRW_GActionPerformed(evt);
            }
        });
        jAnalog.add(jAnalogRW_G, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 271, 280, -1));

        jLabel238.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel238.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel238.setText(bundle.getString("LDG30erPlus.jLabel238.text")); // NOI18N
        jAnalog.add(jLabel238, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 331, 200, -1));

        jUmschaltEmpf.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jUmschaltEmpf.setText(bundle.getString("LDG30erPlus.jUmschaltEmpf.text")); // NOI18N
        jUmschaltEmpf.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jUmschaltEmpfFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jUmschaltEmpfFocusLost(evt);
            }
        });
        jUmschaltEmpf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jUmschaltEmpfKeyReleased(evt);
            }
        });
        jAnalog.add(jUmschaltEmpf, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 330, 50, -1));

        jLabel239.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel239.setText(bundle.getString("LDG30erPlus.jLabel239.text")); // NOI18N
        jAnalog.add(jLabel239, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, -1));

        jDecodereigenschaften.addTab(bundle.getString("LDG30erPlus.jAnalog.TabConstraints.tabTitle"), jAnalog); // NOI18N

        jPanel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel3.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanel3ComponentShown(evt);
            }
        });

        jComment.setColumns(20);
        jComment.setRows(5);
        jScrollPane1.setViewportView(jComment);

        jLabel36.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel36.setText(bundle.getString("LDG30erPlus.jLabel36.text")); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE))
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE)
                .addContainerGap())
        );

        jDecodereigenschaften.addTab(bundle.getString("LDG30erPlus.jPanel3.TabConstraints.tabTitle"), jPanel3); // NOI18N

        jCV_LesenSchreiben.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCV_LesenSchreiben.setText(bundle.getString("LDG30erPlus.jCV_LesenSchreiben.text")); // NOI18N
        jCV_LesenSchreiben.setToolTipText(bundle.getString("LDG30erPlus.jCV_LesenSchreiben.toolTipText")); // NOI18N
        jCV_LesenSchreiben.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCV_LesenSchreibenActionPerformed(evt);
            }
        });

        jDirekteingabe.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jDirekteingabe.setText(bundle.getString("LDG30erPlus.jDirekteingabe.text")); // NOI18N
        jDirekteingabe.setToolTipText(bundle.getString("LDG30erPlus.jDirekteingabe.toolTipText")); // NOI18N
        jDirekteingabe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDirekteingabeActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText(bundle.getString("LDG30erPlus.jLabel1.text")); // NOI18N

        jOpen.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jOpen.setText(bundle.getString("LDG30erPlus.jOpen.text")); // NOI18N
        jOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jOpenActionPerformed(evt);
            }
        });

        buttonGroup1.add(jKurzeAdr);
        jKurzeAdr.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jKurzeAdr.setText(bundle.getString("LDG30erPlus.jKurzeAdr.text")); // NOI18N
        jKurzeAdr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jKurzeAdrActionPerformed(evt);
            }
        });

        jDecoderAdresse.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jDecoderAdresse.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jDecoderAdresse.setText(bundle.getString("LDG30erPlus.jDecoderAdresse.text")); // NOI18N
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

        jCV_Anzeige.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCV_Anzeige.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("LDG30erPlus.jCV_Anzeige.border.title"))); // NOI18N
        jCV_Anzeige.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCV_AnzeigeActionPerformed(evt);
            }
        });

        buttonGroup1.add(jlangeAdr);
        jlangeAdr.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jlangeAdr.setText(bundle.getString("LDG30erPlus.jlangeAdr.text")); // NOI18N
        jlangeAdr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jlangeAdrActionPerformed(evt);
            }
        });

        jAbbrechen.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAbbrechen.setText(bundle.getString("LDG30erPlus.jAbbrechen.text")); // NOI18N
        jAbbrechen.setToolTipText(bundle.getString("LDG30erPlus.jAbbrechen.toolTipText")); // NOI18N
        jAbbrechen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAbbrechenActionPerformed(evt);
            }
        });

        jDecoderAdresse1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jDecoderAdresse1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jDecoderAdresse1.setText(bundle.getString("LDG30erPlus.jDecoderAdresse1.text")); // NOI18N
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

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel27.setText(bundle.getString("LDG30erPlus.jLabel27.text")); // NOI18N

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel28.setText(bundle.getString("LDG30erPlus.jLabel28.text")); // NOI18N

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton1.setText(bundle.getString("LDG30erPlus.jButton1.text")); // NOI18N
        jButton1.setToolTipText(bundle.getString("LDG30erPlus.jButton1.toolTipText")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
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
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jDecoderAdresse, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jKurzeAdr)
                                    .addComponent(jlangeAdr)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jCV_Anzeige, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jCV_Inhalt, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jDirekteingabe)
                                .addGap(28, 28, 28)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel27)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jDecoderAdresse1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel28)))))
                        .addGap(6, 6, 6))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jDecodereigenschaften, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jOpen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jAbbrechen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jCV_LesenSchreiben, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(jOpen, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSave, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(142, 142, 142)
                        .addComponent(jCV_LesenSchreiben, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                        .addComponent(jDecodereigenschaften)))
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
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCV_LesenSchreibenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCV_LesenSchreibenActionPerformed
        try {
            // Hier wird die Schnittstelle geöffnet und die CVs werden zur Zentrale gesendet
            ReadWriteCV cvwr = new ReadWriteCV(this, true, KTUI, CV);
        } catch (IOException ex) {
            KTUI.mbDeviceReadProblem( this );
        }
    }//GEN-LAST:event_jCV_LesenSchreibenActionPerformed

    private void jCV_InhaltFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jCV_InhaltFocusLost
        int adr;
        int oldValue;
        int currCV = getCVfromIndexString(jCV_Anzeige, "CV#");
        String oriEingabe = jCV_Inhalt.getText();
        // int cvValue = KTUI.checkTextField( this, jCV_Inhalt, 0, 255, 22222, false);
        int cvValue = KTUI.checkTextField( this, jCV_Inhalt, 0, 255, -1, false);
        if( cvValue == -1 ) {
            System.out.println("jCV_InhaltFocusLost CV="+currCV+" Wert=\""+oriEingabe+"\" IGNORIERT");
            jCV_Inhalt.setText(oriEingabe);
            getDefaultToolkit().beep();
            jCV_Inhalt.grabFocus();
            return;
        }
        String s = jCV_Inhalt.getText();

        //int i = currCV -1 ;
        //int j = cvValue;
        //switch(i) {
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
                
            case 2: //CV#2 AnfahrGeschw
            case 3: //CV#3 AnfahrVerz
            case 4: //CV#4 BremsVerz
            case 5: //CV#5 VMax
            case 6: //CV#6 VMitten
                if( debugLevel >= 1 ) {
                    System.out.println("TODO CV="+currCV+" Wert="+cvValue+" ohne weiteren Test übernommen");
                }
                break;

            case 7: //CV#7 Version
            case 8: //CV#8 Hersteller
                System.out.println("jCV_InhaltFocusLost CV="+currCV+" Wert="+cvValue+" IGNORIERT");
                return;

            case 9: //CV#9 Motorfrequenz
                if( debugLevel >= 1 ) {
                    System.out.println("TODO CV="+currCV+" Wert="+cvValue+" ohne weiteren Test übernommen");
                }
                break;

            case 11:
                jPacketTimeOut.setText("" + cvValue);
                break;

            case 12:
                cvValue = KTUI.checkTextField( this, jCV_Inhalt, 0, 1, 1, true );
                jAnalogRW_G.setSelected(    (cvValue & 1) == 1);
                jAnalogRW_W.setSelected( ! ((cvValue & 1) == 1));
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

            case 27: // Bremsverhalten bei Gleichspannung ; Seite 62
                switch( cvValue) {
                    case   0:
                    case  32:
                    case  64:
                    case  96:
                        break;
                    default:
                        KTUI.mbInvalidValue(this, currCV, cvValue, "0 32 64 96" );
                        System.out.println("jCV_InhaltFocusLost CV="+currCV+" Wert="+cvValue+" IGNORIERT");
                        return;
                }
                break;

            case 28: //CV#28 RailCom
                jBroadCasst.setSelected((cvValue &   1) ==   1);
                jChannel2.setSelected(  (cvValue &   2) ==   2);
                jRCplus.setSelected(    (cvValue & 128) == 128);
                break;

            case 29: //CV#29 Konfigurationsdaten 1
                jRichtung.setSelected((cvValue &  1) ==  1);
                jFS.setSelected(      (cvValue &  2) ==  2);
                jAnalog1.setSelected( (cvValue &  4) ==  4);
                jRailCom.setSelected( (cvValue &  8) ==  8);
                // TODO Was ist mit 16 alternative Kennlinie ?
                // Gibt es die nicht ?
                jLongAddr.setSelected((cvValue & 32) == 32);

                jKurzeAdr.setSelected((cvValue & 32) ==  0);
                jlangeAdr.setSelected((cvValue & 32) == 32);
                break;


            case 30: // Hilfsregister ; Seite 58
            case 31: // Index für höhere CV-PAges ; Seite 58
            case 32: // Index für höhere CV-PAges ; Seite 58
                System.out.println("jCV_InhaltFocusLost CV="+currCV+" Wert="+cvValue+" IGNORIERT");
                break;

            case 33: //CV#33
                jFL_1.setSelected((cvValue &   1) ==   1);
                jFL_2.setSelected((cvValue &   2) ==   2);
                jFL_3.setSelected((cvValue &   4) ==   4);
                jFL_4.setSelected((cvValue &   8) ==   8);
                jFL_5.setSelected((cvValue &  16) ==  16);
                jFL_6.setSelected((cvValue &  32) ==  32);
                jFL_7.setSelected((cvValue &  64) ==  64);
                jFL_8.setSelected((cvValue & 128) == 128);
                break;

            case 34: //CV#34
                jFR_1.setSelected((cvValue &   1) ==   1);
                jFR_2.setSelected((cvValue &   2) ==   2);
                jFR_3.setSelected((cvValue &   4) ==   4);
                jFR_4.setSelected((cvValue &   8) ==   8);
                jFR_5.setSelected((cvValue &  16) ==  16);
                jFR_6.setSelected((cvValue &  32) ==  32);
                jFR_7.setSelected((cvValue &  64) ==  64);
                jFR_8.setSelected((cvValue & 128) == 128);
                break;

            case 35: //CV#35 F1
                jF1_1.setSelected( (cvValue &   1) ==   1);
                jF1_2.setSelected( (cvValue &   2) ==   2);
                jF1_3.setSelected( (cvValue &   4) ==   4);
                jF1_4.setSelected( (cvValue &   8) ==   8);
                jF1_5.setSelected( (cvValue &  16) ==  16);
                jF1_6.setSelected( (cvValue &  32) ==  32);
                jF1_7.setSelected( (cvValue &  64) ==  64);
                jF1_8.setSelected( (cvValue & 128) == 128);
                break;


            case 36: //CV#36 F2
                jF2_1.setSelected( (cvValue &   1) ==   1);
                jF2_2.setSelected( (cvValue &   2) ==   2);
                jF2_3.setSelected( (cvValue &   4) ==   4);
                jF2_4.setSelected( (cvValue &   8) ==   8);
                jF2_5.setSelected( (cvValue &  16) ==  16);
                jF2_6.setSelected( (cvValue &  32) ==  32);
                jF2_7.setSelected( (cvValue &  64) ==  64);
                jF2_8.setSelected( (cvValue & 128) == 128);
                break;

            case 37: //CV#37 F3
                jF3_1.setSelected( (cvValue &   1) ==   1);
                jF3_2.setSelected( (cvValue &   2) ==   2);
                jF3_3.setSelected( (cvValue &   4) ==   4);
                jF3_4.setSelected( (cvValue &   8) ==   8);
                jF3_5.setSelected( (cvValue &  16) ==  16);
                jF3_6.setSelected( (cvValue &  32) ==  32);
                jF3_7.setSelected( (cvValue &  64) ==  64);
                jF3_8.setSelected( (cvValue & 128) == 128);
                break;

            case 38: //CV#38 F4
                jF4_1.setSelected( (cvValue &   1) ==   1);
                jF4_2.setSelected( (cvValue &   2) ==   2);
                jF4_3.setSelected( (cvValue &   4) ==   4);
                jF4_4.setSelected( (cvValue &   8) ==   8);
                jF4_5.setSelected( (cvValue &  16) ==  16);
                jF4_6.setSelected( (cvValue &  32) ==  32);
                jF4_7.setSelected( (cvValue &  64) ==  64);
                jF4_8.setSelected( (cvValue & 128) == 128);
                break;

            case 39: //CV#39 F5
                jF5_1.setSelected( (cvValue &   1) ==   1);
                jF5_2.setSelected( (cvValue &   2) ==   2);
                jF5_3.setSelected( (cvValue &   4) ==   4);
                jF5_4.setSelected( (cvValue &   8) ==   8);
                jF5_5.setSelected( (cvValue &  16) ==  16);
                jF5_6.setSelected( (cvValue &  32) ==  32);
                jF5_7.setSelected( (cvValue &  64) ==  64);
                jF5_8.setSelected( (cvValue & 128) == 128);
                break;

            case 40: //CV#40 F6
                jF6_1.setSelected( (cvValue &   1) ==   1);
                jF6_2.setSelected( (cvValue &   2) ==   2);
                jF6_3.setSelected( (cvValue &   4) ==   4);
                jF6_4.setSelected( (cvValue &   8) ==   8);
                jF6_5.setSelected( (cvValue &  16) ==  16);
                jF6_6.setSelected( (cvValue &  32) ==  32);
                jF6_7.setSelected( (cvValue &  64) ==  64);
                jF6_8.setSelected( (cvValue & 128) == 128);
                break;

            case 41: //CV#41 F7
                jF7_1.setSelected( (cvValue &   1) ==   1);
                jF7_2.setSelected( (cvValue &   2) ==   2);
                jF7_3.setSelected( (cvValue &   4) ==   4);
                jF7_4.setSelected( (cvValue &   8) ==   8);
                jF7_5.setSelected( (cvValue &  16) ==  16);
                jF7_6.setSelected( (cvValue &  32) ==  32);
                jF7_7.setSelected( (cvValue &  64) ==  64);
                jF7_8.setSelected( (cvValue & 128) == 128);
                break;

            case 42: //CV#42 F8
                jF8_1.setSelected( (cvValue &   1) ==   1);
                jF8_2.setSelected( (cvValue &   2) ==   2);
                jF8_3.setSelected( (cvValue &   4) ==   4);
                jF8_4.setSelected( (cvValue &   8) ==   8);
                jF8_5.setSelected( (cvValue &  16) ==  16);
                jF8_6.setSelected( (cvValue &  32) ==  32);
                jF8_7.setSelected( (cvValue &  64) ==  64);
                jF8_8.setSelected( (cvValue & 128) == 128);
                break;

            case 43: //CV#43 F9
                jF9_1.setSelected( (cvValue &   1) ==   1);
                jF9_2.setSelected( (cvValue &   2) ==   2);
                jF9_3.setSelected( (cvValue &   4) ==   4);
                jF9_4.setSelected( (cvValue &   8) ==   8);
                jF9_5.setSelected( (cvValue &  16) ==  16);
                jF9_6.setSelected( (cvValue &  32) ==  32);
                jF9_7.setSelected( (cvValue &  64) ==  64);
                jF9_8.setSelected( (cvValue & 128) == 128);
                break;

            case 44: //CV#44 F10
                jF10_1.setSelected( (cvValue &   1) ==   1);
                jF10_2.setSelected( (cvValue &   2) ==   2);
                jF10_3.setSelected( (cvValue &   4) ==   4);
                jF10_4.setSelected( (cvValue &   8) ==   8);
                jF10_5.setSelected( (cvValue &  16) ==  16);
                jF10_6.setSelected( (cvValue &  32) ==  32);
                jF10_7.setSelected( (cvValue &  64) ==  64);
                jF10_8.setSelected( (cvValue & 128) == 128);
                break;

            case 45: //CV#45 F11
                jF11_1.setSelected( (cvValue &   1) ==   1);
                jF11_2.setSelected( (cvValue &   2) ==   2);
                jF11_3.setSelected( (cvValue &   4) ==   4);
                jF11_4.setSelected( (cvValue &   8) ==   8);
                jF11_5.setSelected( (cvValue &  16) ==  16);
                jF11_6.setSelected( (cvValue &  32) ==  32);
                jF11_7.setSelected( (cvValue &  64) ==  64);
                jF11_8.setSelected( (cvValue & 128) == 128);
                break;

            case 46: //CV#46 F12
                jF12_1.setSelected( (cvValue &   1) ==   1);
                jF12_2.setSelected( (cvValue &   2) ==   2);
                jF12_3.setSelected( (cvValue &   4) ==   4);
                jF12_4.setSelected( (cvValue &   8) ==   8);
                jF12_5.setSelected( (cvValue &  16) ==  16);
                jF12_6.setSelected( (cvValue &  32) ==  32);
                jF12_7.setSelected( (cvValue &  64) ==  64);
                jF12_8.setSelected( (cvValue & 128) == 128);
                break;

            case 47: //CV47 2te MM-Adresse
                cvValue = KTUI.checkTextField( this, jCV_Inhalt, 1, 255, 4, true );
                s = jCV_Inhalt.getText();
                jMM_Addr_2.setText(s);
                break;

            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
                System.out.println("TODO CV="+currCV+" Wert="+cvValue+" ohne weiteren Test übernommen");
                break;

            case 53: //CV#53
                jRueckf.setSelected(   ! ((cvValue &   1) ==   1));
                jVorf.setSelected(     ! ((cvValue &   2) ==   2));
                jAuxInvf.setSelected(     (cvValue &   4) ==   4 );
                jBl_Invf.setSelected(     (cvValue &   8) ==   8 );
                jBlinkf.setSelected(      (cvValue &  16) ==  16 );
                jMARsf.setSelected(       (cvValue &  32) ==  32 );
                jDoppelBlinkf.setSelected((cvValue &  64) ==  64 );
                break;

            case 54: //CV#54
                jRueckr.setSelected(   ! ((cvValue &   1) ==   1));
                jVorr.setSelected(     ! ((cvValue &   2) ==   2));
                jAuxInvr.setSelected(     (cvValue &   4) ==   4 );
                jBl_Invr.setSelected(     (cvValue &   8) ==   8 );
                jBlinkr.setSelected(      (cvValue &  16) ==  16 );
                jMARsr.setSelected(       (cvValue &  32) ==  32 );
                jDoppelBlinkr.setSelected((cvValue &  64) ==  64 );
                break;

            case 55: //CV#55
                jRueck1.setSelected(   ! ((cvValue &   1) ==   1));
                jVor1.setSelected(     ! ((cvValue &   2) ==   2));
                jAuxInv1.setSelected(     (cvValue &   4) ==   4 );
                jBl_Inv1.setSelected(     (cvValue &   8) ==   8 );
                jBlink1.setSelected(      (cvValue &  16) ==  16 );
                jMARs1.setSelected(       (cvValue &  32) ==  32 );
                jDoppelBlink1.setSelected((cvValue &  64) ==  64 );
                jKick1.setSelected(       (cvValue & 128) == 128 );
                break;

            case 56: //CV#56
                jRueck2.setSelected(   ! ((cvValue &   1) ==   1));
                jVor2.setSelected(     ! ((cvValue &   2) ==   2));
                jAuxInv2.setSelected(     (cvValue &   4) ==   4 );
                jBl_Inv2.setSelected(     (cvValue &   8) ==   8 );
                jBlink2.setSelected(      (cvValue &  16) ==  16 );
                jMARs2.setSelected(       (cvValue &  32) ==  32 );
                jDoppelBlink2.setSelected((cvValue &  64) ==  64 );
                jKick2.setSelected(       (cvValue & 128) == 128 );
                break;

            case 57: //CV#57
                jRueck3.setSelected(   ! ((cvValue &   1) ==   1));
                jVor3.setSelected(     ! ((cvValue &   2) ==   2));
                jAuxInv3.setSelected(     (cvValue &   4) ==   4 );
                jBl_Inv3.setSelected(     (cvValue &   8) ==   8 );
                jBlink3.setSelected(      (cvValue &  16) ==  16 );
                jMARs3.setSelected(       (cvValue &  32) ==  32 );
                jDoppelBlink3.setSelected((cvValue &  64) ==  64 );
                jKick3.setSelected(       (cvValue & 128) == 128 );
                break;

            case 58: //CV#58
                jRueck4.setSelected(   ! ((cvValue &   1) ==   1));
                jVor4.setSelected(     ! ((cvValue &   2) ==   2));
                jAuxInv4.setSelected(     (cvValue &   4) ==   4 );
                jBl_Inv4.setSelected(     (cvValue &   8) ==   8 );
                jBlink4.setSelected(      (cvValue &  16) ==  16 );
                jMARs4.setSelected(       (cvValue &  32) ==  32 );
                jDoppelBlink4.setSelected((cvValue &  64) ==  64 );
                jKick4.setSelected(       (cvValue & 128) == 128 );
                break;

            case 59: //CV#59
                jRueck5.setSelected(   ! ((cvValue &   1) ==   1));
                jVor5.setSelected(     ! ((cvValue &   2) ==   2));
                jAuxInv5.setSelected(     (cvValue &   4) ==   4 );
                jBl_Inv5.setSelected(     (cvValue &   8) ==   8 );
                jBlink5.setSelected(      (cvValue &  16) ==  16 );
                jDoppelBlink5.setSelected((cvValue &  64) ==  64 );
                jKick5.setSelected(       (cvValue & 128) == 128 );
                break;

            case 60: //CV#60
                jRueck6.setSelected(   ! ((cvValue &   1) ==   1));
                jVor6.setSelected(     ! ((cvValue &   2) ==   2));
                jAuxInv6.setSelected(     (cvValue &   4) ==   4 );
                jBl_Inv6.setSelected(     (cvValue &   8) ==   8 );
                jBlink6.setSelected(      (cvValue &  16) ==  16 );
                jDoppelBlink6.setSelected((cvValue &  64) ==  64 );
                jKick6.setSelected(       (cvValue & 128) == 128 );
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
                System.out.println("alternative Kennlinie CV="+currCV+" Wert="+cvValue+" ohne weiteren Test übernommen");
                break;

            case 96:
            case 97:
            case 98:
            case 99:
            case 100:
            case 101:
            case 103:
                System.out.println("TODO CV="+currCV+" Wert="+cvValue+" ohne weiteren Test übernommen");
                break;

            case 104:
            case 105:
            case 106:
            case 107:
            case 108:
            case 109:
            case 110:
            case 111:
                System.out.println("TODO CV="+currCV+" Wert="+cvValue+" ohne weiteren Test übernommen");
                break;

            case 112:
            case 113:
            case 114:
                System.out.println("TODO CV="+currCV+" Wert="+cvValue+" ohne weiteren Test übernommen");
                break;

            case 115:
            case 116:
            case 117:
            case 118:
            case 119:
            case 120:
            case 121:
            case 122:
                System.out.println("TODO CV="+currCV+" Wert="+cvValue+" ohne weiteren Test übernommen");
                break;

            case 123:
            case 124:
            case 125:
            case 126:
            case 127:
            case 128:
            case 129:
            case 130:
                System.out.println("TODO CV="+currCV+" Wert="+cvValue+" ohne weiteren Test übernommen");
                break;

            case 131:
                System.out.println("TODO CV="+currCV+" Wert="+cvValue+" ohne weiteren Test übernommen");
                break;

            case 132:
            case 133:
            case 134:
            case 135:
            case 136:
            case 137:
            case 138:
            case 139:
                System.out.println("TODO CV="+currCV+" Wert="+cvValue+" ohne weiteren Test übernommen");
                break;

            case 140:
            case 141:
            case 142:
            case 143:
            case 144:
            case 145:
            case 146:
            case 147:
                System.out.println("TODO CV="+currCV+" Wert="+cvValue+" ohne weiteren Test übernommen");
                break;

            case 148:
            case 149:
            case 150:
            case 151:
            case 152:
            case 153:
            case 154:
            case 155:
            case 156:
            case 157:
            case 158:
            case 159:
                System.out.println("TODO CV="+currCV+" Wert="+cvValue+" ohne weiteren Test übernommen");
                break;

            case 160:
            case 161:
            case 162:
            case 163:
            case 164:
            case 165:
            case 166:
            case 167:
            case 168:
            case 169:
            case 170:
            case 171:
            case 172:
            case 173:
            case 174:
            case 175:
            case 176:
            case 177:
            case 178:
            case 179:
                System.out.println("TODO CV="+currCV+" Wert="+cvValue+" ohne weiteren Test übernommen");
                break;

            case 180:
            case 181:
            case 182:
            case 183:
            case 184:
            case 185:
            case 186:
            case 187:
            case 188:
            case 189:
            case 190:
            case 191:
            case 192:
            case 193:
            case 194:
            case 195:
                System.out.println("TODO CV="+currCV+" Wert="+cvValue+" ohne weiteren Test übernommen");
                break;

            case 196:
            case 197:
            case 198:
            case 199:
                System.out.println("TODO CV="+currCV+" Wert="+cvValue+" ohne weiteren Test übernommen");
                break;

            default:
                System.out.println("jCV_InhaltFocusLost CV="+currCV+" Wert="+cvValue+" ohne weiteren Test übernommen DEBUGGING");
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

    private void jCV_InhaltKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCV_InhaltKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jCV_InhaltKeyReleased

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

    private void jOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jOpenActionPerformed
        jCV_Inhalt.setText("62");
        jCV_Anzeige.setSelectedItem( "CV#"+8 );
        SaveOpenDialog od;
        switch( KTUI.Decoder ) {
            case c.LD_G31Plus:
            case c.LD_G33Plus:
            case c.LD_G34Plus:
                od = new SaveOpenDialog( this, true, true, CVs, this, "30p");
                break;

            case c.LD_G36Plus:
                od = new SaveOpenDialog( this, true, true, CVs, this, "36p");
                break;
        }
        updateTabs();
    }//GEN-LAST:event_jOpenActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        DecTest dt = new DecTest(this, true, KTUI);
        switch(KTUI.Decoder)
        {
            case c.LD_G31Plus:
                dt.jDecType.setText("Decoder: LD-G-31plus");
                break;
            case c.LD_G33Plus:
                dt.jDecType.setText("Decoder: LD-G-33plus");
                break;
            case c.LD_G34Plus:
                dt.jDecType.setText("Decoder: LD-G-34plus");
                break;
            case c.LD_G36Plus:
                dt.jDecType.setText("Decoder: LD-G-36plus");
                break;
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

    private void jCV_AnzeigeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCV_AnzeigeActionPerformed
        int currCV = getCVfromIndexString(jCV_Anzeige, "CV#");
        jCV_Inhalt.setText("" + CV[1][currCV]);
    }//GEN-LAST:event_jCV_AnzeigeActionPerformed

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
        
        jBroadCasst.setSelected((CV[1][28] &   1) ==   1);
        jChannel2.setSelected(  (CV[1][28] &   2) ==   2);
        jRCplus.setSelected(    (CV[1][28] & 128) == 128);

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
        jCV_Anzeige.setSelectedItem( "CV#"+335 );
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

    private void jFunctionMappingComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jFunctionMappingComponentShown
        jCV_Anzeige.setSelectedItem( "CV#"+33 );

        int cvValue;
        //------------- FL ------------------
        cvValue = CV[1][33];
        jFL_1.setSelected((cvValue &   1) ==   1);
        jFL_2.setSelected((cvValue &   2) ==   2);
        jFL_3.setSelected((cvValue &   4) ==   4);
        jFL_4.setSelected((cvValue &   8) ==   8);
        jFL_5.setSelected((cvValue &  16) ==  16);
        jFL_6.setSelected((cvValue &  32) ==  32);
        jFL_7.setSelected((cvValue &  64) ==  64);
        jFL_8.setSelected((cvValue & 128) == 128);

        //------------- FR ------------------
        cvValue = CV[1][34];
        jFR_1.setSelected((cvValue &   1) ==   1);
        jFR_2.setSelected((cvValue &   2) ==   2);
        jFR_3.setSelected((cvValue &   4) ==   4);
        jFR_4.setSelected((cvValue &   8) ==   8);
        jFR_5.setSelected((cvValue &  16) ==  16);
        jFR_6.setSelected((cvValue &  32) ==  32);
        jFR_7.setSelected((cvValue &  64) ==  64);
        jFR_8.setSelected((cvValue & 128) == 128);

        //------------- F1 ------------------
        cvValue = CV[1][35];
        jF1_1.setSelected((cvValue &   1) ==   1);
        jF1_2.setSelected((cvValue &   2) ==   2);
        jF1_3.setSelected((cvValue &   4) ==   4);
        jF1_4.setSelected((cvValue &   8) ==   8);
        jF1_5.setSelected((cvValue &  16) ==  16);
        jF1_6.setSelected((cvValue &  32) ==  32);
        jF1_7.setSelected((cvValue &  64) ==  64);
        jF1_8.setSelected((cvValue & 128) == 128);

        //------------- F2 ------------------
        cvValue = CV[1][36];
        jF2_1.setSelected((cvValue &   1) ==   1);
        jF2_2.setSelected((cvValue &   2) ==   2);
        jF2_3.setSelected((cvValue &   4) ==   4);
        jF2_4.setSelected((cvValue &   8) ==   8);
        jF2_5.setSelected((cvValue &  16) ==  16);
        jF2_6.setSelected((cvValue &  32) ==  32);
        jF2_7.setSelected((cvValue &  64) ==  64);
        jF2_8.setSelected((cvValue & 128) == 128);

        //------------- F3 ------------------
        cvValue = CV[1][37];
        jF3_1.setSelected((cvValue &   1) ==   1);
        jF3_2.setSelected((cvValue &   2) ==   2);
        jF3_3.setSelected((cvValue &   4) ==   4);
        jF3_4.setSelected((cvValue &   8) ==   8);
        jF3_5.setSelected((cvValue &  16) ==  16);
        jF3_6.setSelected((cvValue &  32) ==  32);
        jF3_7.setSelected((cvValue &  64) ==  64);
        jF3_8.setSelected((cvValue & 128) == 128);

        //------------- F4 ------------------
        cvValue = CV[1][38];
        jF4_1.setSelected((cvValue &   1) ==   1);
        jF4_2.setSelected((cvValue &   2) ==   2);
        jF4_3.setSelected((cvValue &   4) ==   4);
        jF4_4.setSelected((cvValue &   8) ==   8);
        jF4_5.setSelected((cvValue &  16) ==  16);
        jF4_6.setSelected((cvValue &  32) ==  32);
        jF4_7.setSelected((cvValue &  64) ==  64);
        jF4_8.setSelected((cvValue & 128) == 128);

        //------------- F5 ------------------
        cvValue = CV[1][39];
        jF5_1.setSelected((cvValue &   1) ==   1);
        jF5_2.setSelected((cvValue &   2) ==   2);
        jF5_3.setSelected((cvValue &   4) ==   4);
        jF5_4.setSelected((cvValue &   8) ==   8);
        jF5_5.setSelected((cvValue &  16) ==  16);
        jF5_6.setSelected((cvValue &  32) ==  32);
        jF5_7.setSelected((cvValue &  64) ==  64);
        jF5_8.setSelected((cvValue & 128) == 128);

        //------------- F6 ------------------
        cvValue = CV[1][40];
        jF6_1.setSelected((cvValue &   1) ==   1);
        jF6_2.setSelected((cvValue &   2) ==   2);
        jF6_3.setSelected((cvValue &   4) ==   4);
        jF6_4.setSelected((cvValue &   8) ==   8);
        jF6_5.setSelected((cvValue &  16) ==  16);
        jF6_6.setSelected((cvValue &  32) ==  32);
        jF6_7.setSelected((cvValue &  64) ==  64);
        jF6_8.setSelected((cvValue & 128) == 128);

        //------------- F7 ------------------
        cvValue = CV[1][41];
        jF7_1.setSelected((cvValue &   1) ==   1);
        jF7_2.setSelected((cvValue &   2) ==   2);
        jF7_3.setSelected((cvValue &   4) ==   4);
        jF7_4.setSelected((cvValue &   8) ==   8);
        jF7_5.setSelected((cvValue &  16) ==  16);
        jF7_6.setSelected((cvValue &  32) ==  32);
        jF7_7.setSelected((cvValue &  64) ==  64);
        jF7_8.setSelected((cvValue & 128) == 128);

        //------------- F8 ------------------
        cvValue = CV[1][42];
        jF8_1.setSelected((cvValue &   1) ==   1);
        jF8_2.setSelected((cvValue &   2) ==   2);
        jF8_3.setSelected((cvValue &   4) ==   4);
        jF8_4.setSelected((cvValue &   8) ==   8);
        jF8_5.setSelected((cvValue &  16) ==  16);
        jF8_6.setSelected((cvValue &  32) ==  32);
        jF8_7.setSelected((cvValue &  64) ==  64);
        jF8_8.setSelected((cvValue & 128) == 128);

        //------------- F9 ------------------
        cvValue = CV[1][43];
        jF9_1.setSelected((cvValue &   1) ==   1);
        jF9_2.setSelected((cvValue &   2) ==   2);
        jF9_3.setSelected((cvValue &   4) ==   4);
        jF9_4.setSelected((cvValue &   8) ==   8);
        jF9_5.setSelected((cvValue &  16) ==  16);
        jF9_6.setSelected((cvValue &  32) ==  32);
        jF9_7.setSelected((cvValue &  64) ==  64);
        jF9_8.setSelected((cvValue & 128) == 128);

        //------------- F10 ------------------
        cvValue = CV[1][44];
        jF10_1.setSelected((cvValue &   1) ==   1);
        jF10_2.setSelected((cvValue &   2) ==   2);
        jF10_3.setSelected((cvValue &   4) ==   4);
        jF10_4.setSelected((cvValue &   8) ==   8);
        jF10_5.setSelected((cvValue &  16) ==  16);
        jF10_6.setSelected((cvValue &  32) ==  32);
        jF10_7.setSelected((cvValue &  64) ==  64);
        jF10_8.setSelected((cvValue & 128) == 128);

        //------------- F11 ------------------
        cvValue = CV[1][45];
        jF11_1.setSelected((cvValue &   1) ==   1);
        jF11_2.setSelected((cvValue &   2) ==   2);
        jF11_3.setSelected((cvValue &   4) ==   4);
        jF11_4.setSelected((cvValue &   8) ==   8);
        jF11_5.setSelected((cvValue &  16) ==  16);
        jF11_6.setSelected((cvValue &  32) ==  32);
        jF11_7.setSelected((cvValue &  64) ==  64);
        jF11_8.setSelected((cvValue & 128) == 128);

        //------------- F12 ------------------
        cvValue = CV[1][46];
        jF12_1.setSelected((cvValue &   1) ==   1);
        jF12_2.setSelected((cvValue &   2) ==   2);
        jF12_3.setSelected((cvValue &   4) ==   4);
        jF12_4.setSelected((cvValue &   8) ==   8);
        jF12_5.setSelected((cvValue &  16) ==  16);
        jF12_6.setSelected((cvValue &  32) ==  32);
        jF12_7.setSelected((cvValue &  64) ==  64);
        jF12_8.setSelected((cvValue & 128) == 128);


//hier verschieben!
        //------------- F13 ------------------
        cvValue = CV[1][180];
        jF13_1.setSelected((cvValue &  1) ==  1);
        jF13_2.setSelected((cvValue &  2) ==  2);
        jF13_3.setSelected((cvValue &  4) ==  4);
        jF13_4.setSelected((cvValue &  8) ==  8);
        jF13_5.setSelected((cvValue & 16) == 16);
        jF13_6.setSelected((cvValue & 32) == 32);
        jF13_7.setSelected((cvValue & 64) == 64);

        //------------- F14 ------------------
        cvValue = CV[1][181];
        jF14_1.setSelected((cvValue &  1) ==  1);
        jF14_2.setSelected((cvValue &  2) ==  2);
        jF14_3.setSelected((cvValue &  4) ==  4);
        jF14_4.setSelected((cvValue &  8) ==  8);
        jF14_5.setSelected((cvValue & 16) == 16);
        jF14_6.setSelected((cvValue & 32) == 32);
        jF14_7.setSelected((cvValue & 64) == 64);

        //------------- F15 ------------------
        cvValue = CV[1][182];
        jF15_1.setSelected((cvValue &  1) ==  1);
        jF15_2.setSelected((cvValue &  2) ==  2);
        jF15_3.setSelected((cvValue &  4) ==  4);
        jF15_4.setSelected((cvValue &  8) ==  8);
        jF15_5.setSelected((cvValue & 16) == 16);
        jF15_6.setSelected((cvValue & 32) == 32);
        jF15_7.setSelected((cvValue & 64) == 64);

        //------------- F16 ------------------
        cvValue = CV[1][183];
        jF16_1.setSelected((cvValue &  1) ==  1);
        jF16_2.setSelected((cvValue &  2) ==  2);
        jF16_3.setSelected((cvValue &  4) ==  4);
        jF16_4.setSelected((cvValue &  8) ==  8);
        jF16_5.setSelected((cvValue & 16) == 16);
        jF16_6.setSelected((cvValue & 32) == 32);
        jF16_7.setSelected((cvValue & 64) == 64);

        //------------- F17 ------------------
        cvValue = CV[1][184];
        jF17_1.setSelected((cvValue &  1) ==  1);
        jF17_2.setSelected((cvValue &  2) ==  2);
        jF17_3.setSelected((cvValue &  4) ==  4);
        jF17_4.setSelected((cvValue &  8) ==  8);
        jF17_5.setSelected((cvValue & 16) == 16);
        jF17_6.setSelected((cvValue & 32) == 32);
        jF17_7.setSelected((cvValue & 64) == 64);

        //------------- F18 ------------------
        cvValue = CV[1][185];
        jF18_1.setSelected((cvValue &  1) ==  1);
        jF18_2.setSelected((cvValue &  2) ==  2);
        jF18_3.setSelected((cvValue &  4) ==  4);
        jF18_4.setSelected((cvValue &  8) ==  8);
        jF18_5.setSelected((cvValue & 16) == 16);
        jF18_6.setSelected((cvValue & 32) == 32);
        jF18_7.setSelected((cvValue & 64) == 64);

        //------------- F19 ------------------
        cvValue = CV[1][186];
        jF19_1.setSelected((cvValue &  1) ==  1);
        jF19_2.setSelected((cvValue &  2) ==  2);
        jF19_3.setSelected((cvValue &  4) ==  4);
        jF19_4.setSelected((cvValue &  8) ==  8);
        jF19_5.setSelected((cvValue & 16) == 16);
        jF19_6.setSelected((cvValue & 32) == 32);
        jF19_7.setSelected((cvValue & 64) == 64);

        //------------- F20 ------------------
        cvValue = CV[1][187];
        jF20_1.setSelected((cvValue &  1) ==  1);
        jF20_2.setSelected((cvValue &  2) ==  2);
        jF20_3.setSelected((cvValue &  4) ==  4);
        jF20_4.setSelected((cvValue &  8) ==  8);
        jF20_5.setSelected((cvValue & 16) == 16);
        jF20_6.setSelected((cvValue & 32) == 32);
        jF20_7.setSelected((cvValue & 64) == 64);

        //------------- F21 ------------------
        cvValue = CV[1][188];
        jF21_1.setSelected((cvValue &  1) ==  1);
        jF21_2.setSelected((cvValue &  2) ==  2);
        jF21_3.setSelected((cvValue &  4) ==  4);
        jF21_4.setSelected((cvValue &  8) ==  8);
        jF21_5.setSelected((cvValue & 16) == 16);
        jF21_6.setSelected((cvValue & 32) == 32);
        jF21_7.setSelected((cvValue & 64) == 64);

        //------------- F22 ------------------
        cvValue = CV[1][189];
        jF22_1.setSelected((cvValue &  1) ==  1);
        jF22_2.setSelected((cvValue &  2) ==  2);
        jF22_3.setSelected((cvValue &  4) ==  4);
        jF22_4.setSelected((cvValue &  8) ==  8);
        jF22_5.setSelected((cvValue & 16) == 16);
        jF22_6.setSelected((cvValue & 32) == 32);
        jF22_7.setSelected((cvValue & 64) == 64);

        //------------- F23 ------------------
        cvValue = CV[1][190];
        jF23_1.setSelected((cvValue &  1) ==  1);
        jF23_2.setSelected((cvValue &  2) ==  2);
        jF23_3.setSelected((cvValue &  4) ==  4);
        jF23_4.setSelected((cvValue &  8) ==  8);
        jF23_5.setSelected((cvValue & 16) == 16);
        jF23_6.setSelected((cvValue & 32) == 32);
        jF23_7.setSelected((cvValue & 64) == 64);

        //------------- F24 ------------------
        cvValue = CV[1][191];
        jF24_1.setSelected((cvValue &  1) ==  1);
        jF24_2.setSelected((cvValue &  2) ==  2);
        jF24_3.setSelected((cvValue &  4) ==  4);
        jF24_4.setSelected((cvValue &  8) ==  8);
        jF24_5.setSelected((cvValue & 16) == 16);
        jF24_6.setSelected((cvValue & 32) == 32);
        jF24_7.setSelected((cvValue & 64) == 64);

        //------------- F25 ------------------
        cvValue = CV[1][192];
        jF25_1.setSelected((cvValue &  1) ==  1);
        jF25_2.setSelected((cvValue &  2) ==  2);
        jF25_3.setSelected((cvValue &  4) ==  4);
        jF25_4.setSelected((cvValue &  8) ==  8);
        jF25_5.setSelected((cvValue & 16) == 16);
        jF25_6.setSelected((cvValue & 32) == 32);
        jF25_7.setSelected((cvValue & 64) == 64);

        //------------- F26 ------------------
        cvValue = CV[1][193];
        jF26_1.setSelected((cvValue &  1) ==  1);
        jF26_2.setSelected((cvValue &  2) ==  2);
        jF26_3.setSelected((cvValue &  4) ==  4);
        jF26_4.setSelected((cvValue &  8) ==  8);
        jF26_5.setSelected((cvValue & 16) == 16);
        jF26_6.setSelected((cvValue & 32) == 32);
        jF26_7.setSelected((cvValue & 64) == 64);

        //------------- F27 ------------------
        cvValue = CV[1][194];
        jF27_1.setSelected((cvValue &  1) ==  1);
        jF27_2.setSelected((cvValue &  2) ==  2);
        jF27_3.setSelected((cvValue &  4) ==  4);
        jF27_4.setSelected((cvValue &  8) ==  8);
        jF27_5.setSelected((cvValue & 16) == 16);
        jF27_6.setSelected((cvValue & 32) == 32);
        jF27_7.setSelected((cvValue & 64) == 64);

        //------------- F28 ------------------
        cvValue = CV[1][195];
        jF28_1.setSelected((cvValue &  1) ==  1);
        jF28_2.setSelected((cvValue &  2) ==  2);
        jF28_3.setSelected((cvValue &  4) ==  4);
        jF28_4.setSelected((cvValue &  8) ==  8);
        jF28_5.setSelected((cvValue & 16) == 16);
        jF28_6.setSelected((cvValue & 32) == 32);
        jF28_7.setSelected((cvValue & 64) == 64);

    }//GEN-LAST:event_jFunctionMappingComponentShown

    private void jVor1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jVor1ActionPerformed
        if(!jVor1.isSelected()) {
            CV[1][55] |= 2;
        } else {
            CV[1][55] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+55 );
    }//GEN-LAST:event_jVor1ActionPerformed

    private void jRueck1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRueck1ActionPerformed
        if(!jRueck1.isSelected()) {
            CV[1][55] |= 1;
        } else {
            CV[1][55] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+55 );
    }//GEN-LAST:event_jRueck1ActionPerformed

    private void jRueck2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRueck2ActionPerformed
        if(!jRueck2.isSelected()) {
            CV[1][56] |= 1;
        } else {
            CV[1][56] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+56 );
    }//GEN-LAST:event_jRueck2ActionPerformed

    private void jVor2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jVor2ActionPerformed
        if(!jVor2.isSelected()) {
            CV[1][56] |= 2;
        } else {
            CV[1][56] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+56 );
    }//GEN-LAST:event_jVor2ActionPerformed

    private void jVor3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jVor3ActionPerformed
        if(!jVor3.isSelected()) {
            CV[1][57] |= 2;
        } else {
            CV[1][57] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+57 );
    }//GEN-LAST:event_jVor3ActionPerformed

    private void jRueck3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRueck3ActionPerformed
        if(!jRueck3.isSelected()) {
            CV[1][57] |= 1;
        } else {
            CV[1][57] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+57 );
    }//GEN-LAST:event_jRueck3ActionPerformed

    private void jBlink6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBlink6ActionPerformed
        if(jBlink6.isSelected()) {
            CV[1][60] |= 16;
        } else {
            CV[1][60] &= ~16;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+60 );
    }//GEN-LAST:event_jBlink6ActionPerformed

    private void jBlink_Pausezeit_3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlink_Pausezeit_3FocusLost
        int max = 255; // Handbuch
        max = CV[1][108] - ( CV[1][136] * 2 ); // Abhängigkeiten
        if( ! KTUI.checkNumRange(jBlink_Pausezeit_3.getText(), 0, max) ) {
            KTUI.mbBlinkPausezeit( this, CV[1][108], CV[1][136], jBlink_Pausezeit_3.getText() );
            jBlink_Pausezeit_3.grabFocus();
            return;
        }
        // Wert ist bereits geprüft. also "still" übernehmen (view -> false)
        CV[1][144] = KTUI.checkTextField( this, jBlink_Pausezeit_3, 0, max, 2, false);
        jCV_Anzeige.setSelectedItem( "CV#"+144 );
    }//GEN-LAST:event_jBlink_Pausezeit_3FocusLost

    private void jBlink_Pausezeit_3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBlink_Pausezeit_3KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jBlink_Pausezeit_3KeyReleased

    private void jMARs2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMARs2ActionPerformed
        if(jMARs2.isSelected()) {
            CV[1][56] |= 32;
        } else {
            CV[1][56] &= ~32;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+56 );
    }//GEN-LAST:event_jMARs2ActionPerformed

    private void jMARs1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMARs1ActionPerformed
        if(jMARs1.isSelected()) {
            CV[1][55] |= 32;
        } else {
            CV[1][55] &= ~32;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+55 );
    }//GEN-LAST:event_jMARs1ActionPerformed

    private void jMARs3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMARs3ActionPerformed
        if(jMARs3.isSelected()) {
            CV[1][57] |= 32;
        } else {
            CV[1][57] &= ~32;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+57 );
    }//GEN-LAST:event_jMARs3ActionPerformed

    private void jVor4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jVor4ActionPerformed
        if(!jVor4.isSelected()) {
            CV[1][58] |= 2;
        } else {
            CV[1][58] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+58 );
    }//GEN-LAST:event_jVor4ActionPerformed

    private void jVor5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jVor5ActionPerformed
        if(!jVor5.isSelected()) {
            CV[1][59] |= 2;
        } else {
            CV[1][59] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+59 );
    }//GEN-LAST:event_jVor5ActionPerformed

    private void jVor6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jVor6ActionPerformed
        if(!jVor6.isSelected()) {
            CV[1][60] |= 2;
        } else {
            CV[1][60] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+60 );
    }//GEN-LAST:event_jVor6ActionPerformed

    private void jMARs4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMARs4ActionPerformed
        if(jMARs4.isSelected()) {
            CV[1][58] |= 32;
        } else {
            CV[1][58] &= ~32;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+58 );
    }//GEN-LAST:event_jMARs4ActionPerformed

    private void jBlink1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBlink1ActionPerformed
        if(jBlink1.isSelected()) {
            CV[1][57] |= 16;
        } else {
            CV[1][57] &= ~16;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+57 );
    }//GEN-LAST:event_jBlink1ActionPerformed

    private void jBlink2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBlink2ActionPerformed
        if(jBlink2.isSelected()) {
            CV[1][56] |= 16;
        } else {
            CV[1][56] &= ~16;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+56 );
    }//GEN-LAST:event_jBlink2ActionPerformed

    private void jBlink3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBlink3ActionPerformed
        if(jBlink3.isSelected()) {
            CV[1][57] |= 16;
        } else {
            CV[1][57] &= ~16;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+58 );
    }//GEN-LAST:event_jBlink3ActionPerformed

    private void jRueck4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRueck4ActionPerformed
        if(!jRueck4.isSelected()) {
            CV[1][58] |= 1;
        } else {
            CV[1][58] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+58 );
    }//GEN-LAST:event_jRueck4ActionPerformed

    private void jRueck5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRueck5ActionPerformed
        if(!jRueck5.isSelected()) {
            CV[1][59] |= 1;
        } else {
            CV[1][59] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+59 );
    }//GEN-LAST:event_jRueck5ActionPerformed

    private void jRueck6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRueck6ActionPerformed
        if(!jRueck6.isSelected()) {
            CV[1][60] |= 1;
        } else {
            CV[1][60] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+60 );
    }//GEN-LAST:event_jRueck6ActionPerformed

    private void jBlink4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBlink4ActionPerformed
        if(jBlink4.isSelected()) {
            CV[1][58] |= 16;
        } else {
            CV[1][58] &= ~16;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+58 );
    }//GEN-LAST:event_jBlink4ActionPerformed

    private void jBlink5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBlink5ActionPerformed
        if(jBlink5.isSelected()) {
            CV[1][59] |= 16;
        } else {
            CV[1][59] &= ~16;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+59 );
    }//GEN-LAST:event_jBlink5ActionPerformed

    private void jBlink_Pausezeit_4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlink_Pausezeit_4FocusLost
        int max = 255; // Handbuch
        max = CV[1][109] - ( CV[1][137] * 2 ); // Abhängigkeiten
        if( ! KTUI.checkNumRange(jBlink_Pausezeit_4.getText(), 0, max) ) {
            KTUI.mbBlinkPausezeit( this, CV[1][109], CV[1][137], jBlink_Pausezeit_4.getText() );
            jBlink_Pausezeit_4.grabFocus();
            return;
        }
        // Wert ist bereits geprüft. also "still" übernehmen (view -> false)
        CV[1][145] = KTUI.checkTextField( this, jBlink_Pausezeit_4, 0, max, 2, false);
        jCV_Anzeige.setSelectedItem( "CV#"+145 );
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
            CV[1][55] |= 64;
        } else {
            CV[1][55] &= ~64;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+55 );
    }//GEN-LAST:event_jDoppelBlink1ActionPerformed

    private void jDoppelBlink2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDoppelBlink2ActionPerformed
        if(jDoppelBlink2.isSelected()) {
            CV[1][56] |= 64;
        } else {
            CV[1][56] &= ~64;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+56 );
    }//GEN-LAST:event_jDoppelBlink2ActionPerformed

    private void jDoppelBlink3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDoppelBlink3ActionPerformed
        if(jDoppelBlink3.isSelected()) {
            CV[1][57] |= 64;
        } else {
            CV[1][57] &= ~64;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+57 );
    }//GEN-LAST:event_jDoppelBlink3ActionPerformed

    private void jDoppelBlink4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDoppelBlink4ActionPerformed
        if(jDoppelBlink4.isSelected()) {
            CV[1][58] |= 64;
        } else {
            CV[1][58] &= ~64;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+58 );
    }//GEN-LAST:event_jDoppelBlink4ActionPerformed

    private void jDoppelBlink5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDoppelBlink5ActionPerformed
        if(jDoppelBlink5.isSelected()) {
            CV[1][59] |= 64;
        } else {
            CV[1][59] &= ~64;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+59 );
    }//GEN-LAST:event_jDoppelBlink5ActionPerformed

    private void jDoppelBlink6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDoppelBlink6ActionPerformed
        if(jDoppelBlink6.isSelected()) {
            CV[1][60] |= 64;
        } else {
            CV[1][60] &= ~64;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+60 );
    }//GEN-LAST:event_jDoppelBlink6ActionPerformed

    private void jBlink_Einschaltzeit_1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlink_Einschaltzeit_1FocusLost
        int max = 255; // Handbuch
        max = ( CV[1][106] - CV[1][142] ) / 2 ; // Abhängigkeiten
        if( ! KTUI.checkNumRange(jBlink_Einschaltzeit_1.getText(), 0, max) ) {
            KTUI.mbBlinkEinschaltzeit( this, CV[1][106], CV[1][142], jBlink_Einschaltzeit_1.getText() );
            jBlink_Einschaltzeit_1.grabFocus();
            return;
        }
        // Wert ist bereits geprüft. also "still" übernehmen (view -> false)
        CV[1][134] = KTUI.checkTextField( this, jBlink_Einschaltzeit_1, 0, max, 4, false);
        jCV_Anzeige.setSelectedItem( "CV#"+134 );
    }//GEN-LAST:event_jBlink_Einschaltzeit_1FocusLost

    private void jBlink_Einschaltzeit_1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBlink_Einschaltzeit_1KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jBlink_Einschaltzeit_1KeyReleased

    private void jBlink_Einschaltzeit_2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlink_Einschaltzeit_2FocusLost
        int max = 255; // Handbuch
        max = ( CV[1][107] - CV[1][143] ) / 2 ; // Abhängigkeiten
        if( ! KTUI.checkNumRange(jBlink_Einschaltzeit_2.getText(), 0, max) ) {
            KTUI.mbBlinkEinschaltzeit( this, CV[1][107], CV[1][143], jBlink_Einschaltzeit_2.getText() );
            jBlink_Einschaltzeit_2.grabFocus();
            return;
        }
        // Wert ist bereits geprüft. also "still" übernehmen (view -> false)
        CV[1][135] = KTUI.checkTextField( this, jBlink_Einschaltzeit_2, 0, max, 4, false);
        jCV_Anzeige.setSelectedItem( "CV#"+135 );
    }//GEN-LAST:event_jBlink_Einschaltzeit_2FocusLost

    private void jBlink_Einschaltzeit_2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBlink_Einschaltzeit_2KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jBlink_Einschaltzeit_2KeyReleased

    private void jBlink_Einschaltzeit_3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlink_Einschaltzeit_3FocusLost
        int max = 255; // Handbuch
        max = ( CV[1][108] - CV[1][144] ) / 2 ; // Abhängigkeiten
        if( ! KTUI.checkNumRange(jBlink_Einschaltzeit_3.getText(), 0, max) ) {
            KTUI.mbBlinkEinschaltzeit( this, CV[1][108], CV[1][144], jBlink_Einschaltzeit_3.getText() );
            jBlink_Einschaltzeit_3.grabFocus();
            return;
        }
        // Wert ist bereits geprüft. also "still" übernehmen (view -> false)
        CV[1][136] = KTUI.checkTextField( this, jBlink_Einschaltzeit_3, 0, max, 4, false);
        jCV_Anzeige.setSelectedItem( "CV#"+136 );
    }//GEN-LAST:event_jBlink_Einschaltzeit_3FocusLost

    private void jBlink_Einschaltzeit_3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBlink_Einschaltzeit_3KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jBlink_Einschaltzeit_3KeyReleased

    private void jBlink_Einschaltzeit_4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlink_Einschaltzeit_4FocusLost
        int max = 255; // Handbuch
        max = ( CV[1][109] - CV[1][144] ) / 2 ; // Abhängigkeiten
        if( ! KTUI.checkNumRange(jBlink_Einschaltzeit_4.getText(), 0, max) ) {
            KTUI.mbBlinkEinschaltzeit( this, CV[1][109], CV[1][144], jBlink_Einschaltzeit_4.getText() );
            jBlink_Einschaltzeit_4.grabFocus();
            return;
        }
        // Wert ist bereits geprüft. also "still" übernehmen (view -> false)
        CV[1][137] = KTUI.checkTextField( this, jBlink_Einschaltzeit_4, 0, max, 4, false);
        jCV_Anzeige.setSelectedItem( "CV#"+137 );
    }//GEN-LAST:event_jBlink_Einschaltzeit_4FocusLost

    private void jBlink_Einschaltzeit_4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBlink_Einschaltzeit_4KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jBlink_Einschaltzeit_4KeyReleased

    private void jBlink_Einschaltzeit_5FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlink_Einschaltzeit_5FocusLost
        int max = 255; // Handbuch
        max = ( CV[1][110] - CV[1][146] ) / 2 ; // Abhängigkeiten
        if( ! KTUI.checkNumRange(jBlink_Einschaltzeit_5.getText(), 0, max) ) {
            KTUI.mbBlinkEinschaltzeit( this, CV[1][110], CV[1][146], jBlink_Einschaltzeit_5.getText() );
            jBlink_Einschaltzeit_5.grabFocus();
            return;
        }
        // Wert ist bereits geprüft. also "still" übernehmen (view -> false)
        CV[1][138] = KTUI.checkTextField( this, jBlink_Einschaltzeit_5, 0, max, 4, false);
        jCV_Anzeige.setSelectedItem( "CV#"+138 );
    }//GEN-LAST:event_jBlink_Einschaltzeit_5FocusLost

    private void jBlink_Einschaltzeit_5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBlink_Einschaltzeit_5KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jBlink_Einschaltzeit_5KeyReleased

    private void jBlink_Einschaltzeit_6FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlink_Einschaltzeit_6FocusLost
        int max = 255; // Handbuch
        max = ( CV[1][111] - CV[1][147] ) / 2 ; // Abhängigkeiten
        if( ! KTUI.checkNumRange(jBlink_Einschaltzeit_6.getText(), 0, max) ) {
            KTUI.mbBlinkEinschaltzeit( this, CV[1][111], CV[1][147], jBlink_Einschaltzeit_6.getText() );
            jBlink_Einschaltzeit_6.grabFocus();
            return;
        }
        // Wert ist bereits geprüft. also "still" übernehmen (view -> false)
        CV[1][139] = KTUI.checkTextField( this, jBlink_Einschaltzeit_6, 0, max, 4, false);
        jCV_Anzeige.setSelectedItem( "CV#"+139 );
    }//GEN-LAST:event_jBlink_Einschaltzeit_6FocusLost

    private void jBlink_Einschaltzeit_6KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBlink_Einschaltzeit_6KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jBlink_Einschaltzeit_6KeyReleased

    private void jBlink_Pausezeit_1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlink_Pausezeit_1FocusLost
        int max = 255; // Handbuch
        max = CV[1][106] - ( CV[1][134] * 2 ); // Abhängigkeiten
        if( ! KTUI.checkNumRange(jBlink_Pausezeit_1.getText(), 0, max) ) {
            KTUI.mbBlinkPausezeit( this, CV[1][106], CV[1][134], jBlink_Pausezeit_1.getText() );
            jBlink_Pausezeit_1.grabFocus();
            return;
        }
        // Wert ist bereits geprüft. also "still" übernehmen (view -> false)
        CV[1][142] = KTUI.checkTextField( this, jBlink_Pausezeit_1, 0, max, 2, false);
        jCV_Anzeige.setSelectedItem( "CV#"+142 );
    }//GEN-LAST:event_jBlink_Pausezeit_1FocusLost

    private void jBlink_Pausezeit_1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBlink_Pausezeit_1KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jBlink_Pausezeit_1KeyReleased

    private void jBlink_Pausezeit_2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlink_Pausezeit_2FocusLost
        int max = 255; // Handbuch
        max = CV[1][107] - ( CV[1][135] * 2 ); // Abhängigkeiten
        if( ! KTUI.checkNumRange(jBlink_Pausezeit_2.getText(), 0, max) ) {
            KTUI.mbBlinkPausezeit( this, CV[1][107], CV[1][135], jBlink_Pausezeit_2.getText() );
            jBlink_Pausezeit_2.grabFocus();
            return;
        }
        // Wert ist bereits geprüft. also "still" übernehmen (view -> false)
        CV[1][143] = KTUI.checkTextField( this, jBlink_Pausezeit_2, 0, max, 2, false);
        jCV_Anzeige.setSelectedItem( "CV#"+143 );
    }//GEN-LAST:event_jBlink_Pausezeit_2FocusLost

    private void jBlink_Pausezeit_2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBlink_Pausezeit_2KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jBlink_Pausezeit_2KeyReleased

    private void jAuxInv6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAuxInv6ActionPerformed
        if(jAuxInv6.isSelected()) {
            CV[1][60] |= 4;
        } else {
            CV[1][60] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+60 );
    }//GEN-LAST:event_jAuxInv6ActionPerformed

    private void jAuxInv2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAuxInv2ActionPerformed
        if(jAuxInv2.isSelected()) {
            CV[1][56] |= 4;
        } else {
            CV[1][56] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+56 );
    }//GEN-LAST:event_jAuxInv2ActionPerformed

    private void jAuxInv1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAuxInv1ActionPerformed
        if(jAuxInv1.isSelected()) {
            CV[1][55] |= 4;
        } else {
            CV[1][55] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+55 );
    }//GEN-LAST:event_jAuxInv1ActionPerformed

    private void jAuxInv3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAuxInv3ActionPerformed
        if(jAuxInv3.isSelected()) {
            CV[1][57] |= 4;
        } else {
            CV[1][57] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+57 );
    }//GEN-LAST:event_jAuxInv3ActionPerformed

    private void jAuxInv4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAuxInv4ActionPerformed
        if(jAuxInv4.isSelected()) {
            CV[1][58] |= 4;
        } else {
            CV[1][58] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+58 );
    }//GEN-LAST:event_jAuxInv4ActionPerformed

    private void jAuxInv5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAuxInv5ActionPerformed
        if(jAuxInv5.isSelected()) {
            CV[1][59] |= 4;
        } else {
            CV[1][59] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+59 );
    }//GEN-LAST:event_jAuxInv5ActionPerformed

    private void jBl_Inv1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBl_Inv1ActionPerformed
        if(jBl_Inv1.isSelected()) {
            CV[1][55] |= 8;
        } else {
            CV[1][55] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+55 );
    }//GEN-LAST:event_jBl_Inv1ActionPerformed

    private void jBl_Inv2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBl_Inv2ActionPerformed
        if(jBl_Inv2.isSelected()) {
            CV[1][56] |= 8;
        } else {
            CV[1][56] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+56 );
    }//GEN-LAST:event_jBl_Inv2ActionPerformed

    private void jBl_Inv3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBl_Inv3ActionPerformed
        if(jBl_Inv3.isSelected()) {
            CV[1][57] |= 8;
        } else {
            CV[1][57] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+57 );
    }//GEN-LAST:event_jBl_Inv3ActionPerformed

    private void jBl_Inv4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBl_Inv4ActionPerformed
        if(jBl_Inv4.isSelected()) {
            CV[1][58] |= 8;
        } else {
            CV[1][58] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+58 );
    }//GEN-LAST:event_jBl_Inv4ActionPerformed

    private void jBl_Inv5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBl_Inv5ActionPerformed
        if(jBl_Inv5.isSelected()) {
            CV[1][59] |= 8;
        } else {
            CV[1][59] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+59 );
    }//GEN-LAST:event_jBl_Inv5ActionPerformed

    private void jBl_Inv6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBl_Inv6ActionPerformed
        if(jBl_Inv6.isSelected()) {
            CV[1][60] |= 8;
        } else {
            CV[1][60] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+60 );
    }//GEN-LAST:event_jBl_Inv6ActionPerformed

    private void jBlink_Pausezeit_5FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlink_Pausezeit_5FocusLost
        int max = 255; // Handbuch
        max = CV[1][110] - ( CV[1][138] * 2 ); // Abhängigkeiten
        if( ! KTUI.checkNumRange(jBlink_Pausezeit_5.getText(), 0, max) ) {
            KTUI.mbBlinkPausezeit( this, CV[1][110], CV[1][138], jBlink_Pausezeit_5.getText() );
            jBlink_Pausezeit_5.grabFocus();
            return;
        }
        // Wert ist bereits geprüft. also "still" übernehmen (view -> false)
        CV[1][146] = KTUI.checkTextField( this, jBlink_Pausezeit_5, 0, max, 2, false);
        jCV_Anzeige.setSelectedItem( "CV#"+146 );
    }//GEN-LAST:event_jBlink_Pausezeit_5FocusLost

    private void jBlink_Pausezeit_5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBlink_Pausezeit_5KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jBlink_Pausezeit_5KeyReleased

    private void jBlink_Pausezeit_6FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlink_Pausezeit_6FocusLost
        int max = 255; // Handbuch
        max = CV[1][111] - ( CV[1][139] * 2 ); // Abhängigkeiten
        if( ! KTUI.checkNumRange(jBlink_Pausezeit_6.getText(), 0, max) ) {
            KTUI.mbBlinkPausezeit( this, CV[1][111], CV[1][139], jBlink_Pausezeit_6.getText() );
            jBlink_Pausezeit_6.grabFocus();
            return;
        }
        // Wert ist bereits geprüft. also "still" übernehmen (view -> false)
        CV[1][147] = KTUI.checkTextField( this, jBlink_Pausezeit_6, 0, max, 2, false);
        jCV_Anzeige.setSelectedItem( "CV#"+147 );
    }//GEN-LAST:event_jBlink_Pausezeit_6FocusLost

    private void jBlink_Pausezeit_6KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBlink_Pausezeit_6KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jBlink_Pausezeit_6KeyReleased

    private void jBlinkfrequenz3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlinkfrequenz3FocusLost
        int min = ( CV[1][136] *2 ) + CV[1][144] ; // Abhängigkeiten
        if( min < 10 ) {
            min = 10; // Handbuch
        }
        if( ! KTUI.checkNumRange(jBlinkfrequenz3.getText(), min, 255) ) {
            KTUI.mbBlinkFrequenz( this, CV[1][136], CV[1][144], jBlinkfrequenz3.getText() );
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
        int min = ( CV[1][137] *2 ) + CV[1][145] ; // Abhängigkeiten
        if( min < 10 ) {
            min = 10; // Handbuch
        }
        if( ! KTUI.checkNumRange(jBlinkfrequenz4.getText(), min, 255) ) {
            KTUI.mbBlinkFrequenz( this, CV[1][137], CV[1][145], jBlinkfrequenz4.getText() );
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
        int min = ( CV[1][134] *2 ) + CV[1][142] ; // Abhängigkeiten
        if( min < 10 ) {
            min = 10; // Handbuch
        }
        if( ! KTUI.checkNumRange(jBlinkfrequenz1.getText(), min, 255) ) {
            KTUI.mbBlinkFrequenz( this, CV[1][134], CV[1][142], jBlinkfrequenz1.getText() );
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
        int min = ( CV[1][135] *2 ) + CV[1][143] ; // Abhängigkeiten
        if( min < 10 ) {
            min = 10; // Handbuch
        }
        if( ! KTUI.checkNumRange(jBlinkfrequenz2.getText(), min, 255) ) {
            KTUI.mbBlinkFrequenz( this, CV[1][135], CV[1][143], jBlinkfrequenz2.getText() );
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
        int min = ( CV[1][138] *2 ) + CV[1][146] ; // Abhängigkeiten
        if( min < 10 ) {
            min = 10; // Handbuch
        }
        if( ! KTUI.checkNumRange(jBlinkfrequenz5.getText(), min, 255) ) {
            KTUI.mbBlinkFrequenz( this, CV[1][138], CV[1][146], jBlinkfrequenz5.getText() );
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
        int min = ( CV[1][139] *2 ) + CV[1][147] ; // Abhängigkeiten
        if( min < 10 ) {
            min = 10; // Handbuch
        }
        if( ! KTUI.checkNumRange(jBlinkfrequenz6.getText(), min, 255) ) {
            KTUI.mbBlinkFrequenz( this, CV[1][139], CV[1][147], jBlinkfrequenz6.getText() );
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

    private void jKick6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jKick6ActionPerformed
        if(jKick6.isSelected()) {
            CV[1][60] |= 128;
        } else {
            CV[1][60] &= ~128;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+60 );
    }//GEN-LAST:event_jKick6ActionPerformed

    private void jKick1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jKick1ActionPerformed
        if(jKick1.isSelected()) {
            CV[1][55] |= 128;
        } else {
            CV[1][55] &= ~128;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+55 );
    }//GEN-LAST:event_jKick1ActionPerformed

    private void jKick2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jKick2ActionPerformed
        if(jKick2.isSelected()) {
            CV[1][56] |= 128;
        } else {
            CV[1][56] &= ~128;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+56 );
    }//GEN-LAST:event_jKick2ActionPerformed

    private void jKick3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jKick3ActionPerformed
        if(jKick3.isSelected()) {
            CV[1][57] |= 128;
        } else {
            CV[1][57] &= ~128;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+57 );
    }//GEN-LAST:event_jKick3ActionPerformed

    private void jKick4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jKick4ActionPerformed
        if(jKick4.isSelected()) {
            CV[1][58] |= 128;
        } else {
            CV[1][58] &= ~128;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+58 );
    }//GEN-LAST:event_jKick4ActionPerformed

    private void jKick5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jKick5ActionPerformed
        if(jKick5.isSelected()) {
            CV[1][59] |= 128;
        } else {
            CV[1][59] &= ~128;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+59 );
    }//GEN-LAST:event_jKick5ActionPerformed

    private void jEffekte_1ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jEffekte_1ComponentShown
        jCV_Anzeige.setSelectedItem( "CV#"+53 );

        //AUX...
        int cvValue;
        cvValue = CV[1][53];
        jRueckf.setSelected(   ! ((cvValue &   1) ==   1));
        jVorf.setSelected(     ! ((cvValue &   2) ==   2));
        jAuxInvf.setSelected(     (cvValue &   4) ==   4 );
        jBl_Invf.setSelected(     (cvValue &   8) ==   8 );
        jBlinkf.setSelected(      (cvValue &  16) ==  16 );
        jMARsf.setSelected(       (cvValue &  32) ==  32 );
        jDoppelBlinkf.setSelected((cvValue &  64) ==  64 );

        cvValue = CV[1][54];
        jRueckr.setSelected(   ! ((cvValue &   1) ==   1));
        jVorr.setSelected(     ! ((cvValue &   2) ==   2));
        jAuxInvr.setSelected(     (cvValue &   4) ==   4 );
        jBl_Invr.setSelected(     (cvValue &   8) ==   8 );
        jBlinkr.setSelected(      (cvValue &  16) ==  16 );
        jMARsr.setSelected(       (cvValue &  32) ==  32 );
        jDoppelBlinkr.setSelected((cvValue &  64) ==  64 );

        cvValue = CV[1][55];
        jRueck1.setSelected(   ! ((cvValue &   1) ==   1));
        jVor1.setSelected(     ! ((cvValue &   2) ==   2));
        jAuxInv1.setSelected(     (cvValue &   4) ==   4 );
        jBl_Inv1.setSelected(     (cvValue &   8) ==   8 );
        jBlink1.setSelected(      (cvValue &  16) ==  16 );
        jMARs1.setSelected(       (cvValue &  32) ==  32 );
        jDoppelBlink1.setSelected((cvValue &  64) ==  64 );
        jKick1.setSelected(       (cvValue & 128) == 128 );

        cvValue = CV[1][56];
        jRueck2.setSelected(   ! ((cvValue &   1) ==   1));
        jVor2.setSelected(     ! ((cvValue &   2) ==   2));
        jAuxInv2.setSelected(     (cvValue &   4) ==   4 );
        jBl_Inv2.setSelected(     (cvValue &   8) ==   8 );
        jBlink2.setSelected(      (cvValue &  16) ==  16 );
        jMARs2.setSelected(       (cvValue &  32) ==  32 );
        jDoppelBlink2.setSelected((cvValue &  64) ==  64 );
        jKick2.setSelected(       (cvValue & 128) == 128 );

        cvValue = CV[1][57];
        jRueck3.setSelected(   ! ((cvValue &   1) ==   1));
        jVor3.setSelected(     ! ((cvValue &   2) ==   2));
        jAuxInv3.setSelected(     (cvValue &   4) ==   4 );
        jBl_Inv3.setSelected(     (cvValue &   8) ==   8 );
        jBlink3.setSelected(      (cvValue &  16) ==  16 );
        jMARs3.setSelected(       (cvValue &  32) ==  32 );
        jDoppelBlink3.setSelected((cvValue &  64) ==  64 );
        jKick3.setSelected(       (cvValue & 128) == 128 );

        cvValue = CV[1][58];
        jRueck4.setSelected(   ! ((cvValue &   1) ==   1));
        jVor4.setSelected(     ! ((cvValue &   2) ==   2));
        jAuxInv4.setSelected(     (cvValue &   4) ==   4 );
        jBl_Inv4.setSelected(     (cvValue &   8) ==   8 );
        jBlink4.setSelected(      (cvValue &  16) ==  16 );
        jMARs4.setSelected(       (cvValue &  32) ==  32 );
        jDoppelBlink4.setSelected((cvValue &  64) ==  64 );
        jKick4.setSelected(       (cvValue & 128) == 128 );

        cvValue = CV[1][59];
        jRueck5.setSelected(   ! ((cvValue &   1) ==   1));
        jVor5.setSelected(     ! ((cvValue &   2) ==   2));
        jAuxInv5.setSelected(     (cvValue &   4) ==   4 );
        jBl_Inv5.setSelected(     (cvValue &   8) ==   8 );
        jBlink5.setSelected(      (cvValue &  16) ==  16 );
        jDoppelBlink5.setSelected((cvValue &  64) ==  64 );
        jKick5.setSelected(       (cvValue & 128) == 128 );

        cvValue = CV[1][60];
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

    private void jDimmen1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmen1FocusLost
        CV[1][117] = KTUI.checkTextField( this, jDimmen1, 1, 255, 255, true);
        jCV_Anzeige.setSelectedItem( "CV#"+117 );
    }//GEN-LAST:event_jDimmen1FocusLost

    private void jDimmen1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDimmen1KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jDimmen1KeyReleased

    private void jDimmen2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmen2FocusLost
        CV[1][118] = KTUI.checkTextField( this, jDimmen2, 1, 255, 255, true);
        jCV_Anzeige.setSelectedItem( "CV#"+118 );
    }//GEN-LAST:event_jDimmen2FocusLost

    private void jDimmen2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDimmen2KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jDimmen2KeyReleased

    private void jDimmen3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmen3FocusLost
        CV[1][119] = KTUI.checkTextField( this, jDimmen3, 1, 255, 255, true);
        jCV_Anzeige.setSelectedItem( "CV#"+119 );
    }//GEN-LAST:event_jDimmen3FocusLost

    private void jDimmen3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDimmen3KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jDimmen3KeyReleased

    private void jRangier4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRangier4ActionPerformed
        if(jRangier4.isSelected()) {
            CV[1][131] |= 32;
        } else {
            CV[1][131] &= ~32;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+131 );
    }//GEN-LAST:event_jRangier4ActionPerformed

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
            CV[1][114] |= 8;
        } else {
            CV[1][114] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+114 );
    }//GEN-LAST:event_jbDimmFS2ActionPerformed

    private void jbDimmFS1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbDimmFS1ActionPerformed
        if(jbDimmFS1.isSelected()) {
            CV[1][114] |= 4;
        } else {
            CV[1][114] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+114 );
    }//GEN-LAST:event_jbDimmFS1ActionPerformed

    private void jbDimmFS3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbDimmFS3ActionPerformed
        if(jbDimmFS3.isSelected()) {
            CV[1][114] |= 0x10;
        } else {
            CV[1][114] &= ~0x10;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+114 );
    }//GEN-LAST:event_jbDimmFS3ActionPerformed

    private void jbDimmFS4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbDimmFS4ActionPerformed
        if(jbDimmFS4.isSelected()) {
            CV[1][114] |= 0x20;
        } else {
            CV[1][114] &= ~0x20;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+114 );
    }//GEN-LAST:event_jbDimmFS4ActionPerformed

    private void jRangierfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRangierfActionPerformed
        if(jRangierf.isSelected()) {
            CV[1][131] |= 1;
        } else {
            CV[1][131] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+131 );
    }//GEN-LAST:event_jRangierfActionPerformed

    private void jRangierrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRangierrActionPerformed
        if(jRangierr.isSelected()) {
            CV[1][131] |= 2;
        } else {
            CV[1][131] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+131 );
    }//GEN-LAST:event_jRangierrActionPerformed

    private void jRangier1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRangier1ActionPerformed
        if(jRangier1.isSelected()) {
            CV[1][131] |= 4;
        } else {
            CV[1][131] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+131 );
    }//GEN-LAST:event_jRangier1ActionPerformed

    private void jRangier2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRangier2ActionPerformed
        if(jRangier2.isSelected()) {
            CV[1][131] |= 8;
        } else {
            CV[1][131] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+131 );
    }//GEN-LAST:event_jRangier2ActionPerformed

    private void jRangier3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRangier3ActionPerformed
        if(jRangier3.isSelected()) {
            CV[1][131] |= 16;
        } else {
            CV[1][131] &= ~16;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+131 );
    }//GEN-LAST:event_jRangier3ActionPerformed

    private void jDimmen4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmen4FocusLost
        CV[1][120] = KTUI.checkTextField( this, jDimmen4, 1, 255, 255, true);
        jCV_Anzeige.setSelectedItem( "CV#"+120 );
    }//GEN-LAST:event_jDimmen4FocusLost

    private void jDimmen4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDimmen4KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jDimmen4KeyReleased

    private void jDimm_FSFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimm_FSFocusLost
        CV[1][113] = KTUI.checkTextField( this, jDimm_FS, 1, 255, 10, true);
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

    private void jMindestSchlt1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jMindestSchlt1FocusLost
        CV[1][177] = KTUI.checkTextField( this, jMindestSchlt1, 0, 255, 0, true);
        jCV_Anzeige.setSelectedItem( "CV#"+177 );
    }//GEN-LAST:event_jMindestSchlt1FocusLost

    private void jMindestSchlt1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jMindestSchlt1KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jMindestSchlt1KeyReleased

    private void jMindestSchlt2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jMindestSchlt2FocusLost
        CV[1][178] = KTUI.checkTextField( this, jMindestSchlt2, 0, 255, 0, true);
        jCV_Anzeige.setSelectedItem( "CV#"+178 );
    }//GEN-LAST:event_jMindestSchlt2FocusLost

    private void jMindestSchlt2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jMindestSchlt2KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jMindestSchlt2KeyReleased

    private void jIn1Aux1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn1Aux1ActionPerformed
        if(jIn1Aux1.isSelected()) {
            CV[1][61] |= 4;
        } else {
            CV[1][61] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+61 );
    }//GEN-LAST:event_jIn1Aux1ActionPerformed

    private void jIn1Aux2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn1Aux2ActionPerformed
        if(jIn1Aux2.isSelected()) {
            CV[1][61] |= 8;
        } else {
            CV[1][61] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+61 );
    }//GEN-LAST:event_jIn1Aux2ActionPerformed

    private void jIn1Aux3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn1Aux3ActionPerformed
        if(jIn1Aux3.isSelected()) {
            CV[1][61] |= 16;
        } else {
            CV[1][61] &= ~16;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+61 );
    }//GEN-LAST:event_jIn1Aux3ActionPerformed

    private void jIn1Aux4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn1Aux4ActionPerformed
        if(jIn1Aux4.isSelected()) {
            CV[1][61] |= 32;
        } else {
            CV[1][61] &= ~32;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+61 );
    }//GEN-LAST:event_jIn1Aux4ActionPerformed

    private void jIn1Aux5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn1Aux5ActionPerformed
        if(jIn1Aux5.isSelected()) {
            CV[1][61] |= 64;
        } else {
            CV[1][61] &= ~64;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+61 );
    }//GEN-LAST:event_jIn1Aux5ActionPerformed

    private void jIn1Aux6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn1Aux6ActionPerformed
        if(jIn1Aux6.isSelected()) {
            CV[1][61] |= 128;
        } else {
            CV[1][61] &= ~128;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+61 );
    }//GEN-LAST:event_jIn1Aux6ActionPerformed

    private void jDimmFS3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmFS3FocusLost
        CV[1][127] = KTUI.checkTextField( this, jDimmFS3, 1, 255, 255, true);
        jCV_Anzeige.setSelectedItem( "CV#"+1257 );
    }//GEN-LAST:event_jDimmFS3FocusLost

    private void jDimmFS3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDimmFS3KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jDimmFS3KeyReleased

    private void jDimmFS4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmFS4FocusLost
        CV[1][128] = KTUI.checkTextField( this, jDimmFS4, 1, 255, 255, true);
        jCV_Anzeige.setSelectedItem( "CV#"+128 );
    }//GEN-LAST:event_jDimmFS4FocusLost

    private void jDimmFS4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDimmFS4KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jDimmFS4KeyReleased

    private void jDimmFS1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmFS1FocusLost
        CV[1][125] = KTUI.checkTextField( this, jDimmFS1, 1, 255, 255, true);
        jCV_Anzeige.setSelectedItem( "CV#"+125 );
    }//GEN-LAST:event_jDimmFS1FocusLost

    private void jDimmFS1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDimmFS1KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jDimmFS1KeyReleased

    private void jDimmFS2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmFS2FocusLost
        CV[1][126] = KTUI.checkTextField( this, jDimmFS1, 1, 255, 255, true);
        jCV_Anzeige.setSelectedItem( "CV#"+126 );
    }//GEN-LAST:event_jDimmFS2FocusLost

    private void jDimmFS2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDimmFS2KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jDimmFS2KeyReleased

    private void jIn2Aux1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn2Aux1ActionPerformed
        if(jIn2Aux1.isSelected()) {
            CV[1][63] |= 4;
        } else {
            CV[1][63] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+63 );
    }//GEN-LAST:event_jIn2Aux1ActionPerformed

    private void jIn2Aux2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn2Aux2ActionPerformed
        if(jIn2Aux2.isSelected()) {
            CV[1][63] |= 8;
        } else {
            CV[1][63] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+63 );
    }//GEN-LAST:event_jIn2Aux2ActionPerformed

    private void jIn2Aux3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn2Aux3ActionPerformed
        if(jIn2Aux3.isSelected()) {
            CV[1][63] |= 16;
        } else {
            CV[1][63] &= ~16;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+63 );
    }//GEN-LAST:event_jIn2Aux3ActionPerformed

    private void jIn2Aux4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn2Aux4ActionPerformed
        if(jIn2Aux4.isSelected()) {
            CV[1][63] |= 32;
        } else {
            CV[1][63] &= ~32;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+63 );
    }//GEN-LAST:event_jIn2Aux4ActionPerformed

    private void jIn2Aux5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn2Aux5ActionPerformed
        if(jIn2Aux5.isSelected()) {
            CV[1][63] |= 64;
        } else {
            CV[1][63] &= ~64;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+63 );
    }//GEN-LAST:event_jIn2Aux5ActionPerformed

    private void jIn2Aux6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn2Aux6ActionPerformed
        if(jIn2Aux6.isSelected()) {
            CV[1][63] |= 128;
        } else {
            CV[1][63] &= ~128;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+63 );
    }//GEN-LAST:event_jIn2Aux6ActionPerformed

    private void jEffekte_2ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jEffekte_2ComponentShown
        jCV_Anzeige.setSelectedItem( "CV#"+115 );

        jDimmenf.setText("" + CV[1][115]);
        jDimmenr.setText("" + CV[1][116]);
        jDimmen1.setText("" + CV[1][117]);
        jDimmen2.setText("" + CV[1][118]);
        jDimmen3.setText("" + CV[1][119]);
        jDimmen4.setText("" + CV[1][120]);
        jDimmen5.setText("" + CV[1][121]);
        jDimmen6.setText("" + CV[1][122]);

        jbDimmFSf.setSelected((CV[1][114] &   1) ==   1);
        jbDimmFSr.setSelected((CV[1][114] &   2) ==   2);
        jbDimmFS1.setSelected((CV[1][114] &   4) ==   4);
        jbDimmFS2.setSelected((CV[1][114] &   8) ==   8);
        jbDimmFS3.setSelected((CV[1][114] &  16) ==  16);
        jbDimmFS4.setSelected((CV[1][114] &  32) ==  32);
        jbDimmFS5.setSelected((CV[1][114] &  64) ==  64);
        jbDimmFS6.setSelected((CV[1][114] & 128) == 128);

        jDimmFSf.setText("" + CV[1][123]);
        jDimmFSr.setText("" + CV[1][124]);
        jDimmFS1.setText("" + CV[1][125]);
        jDimmFS2.setText("" + CV[1][126]);
        jDimmFS3.setText("" + CV[1][127]);
        jDimmFS4.setText("" + CV[1][128]);
        jDimmFS5.setText("" + CV[1][129]);
        jDimmFS6.setText("" + CV[1][130]);

        jDimm_FS.setText("" + CV[1][113]);

        jKickvor.setText("" + CV[1][162]);
        jKickrueck.setText("" + CV[1][163]);
        jKickMotor.setText("" + CV[1][161]);
        
        jMindestSchlt1.setText("" + CV[1][177]);
        jMindestSchlt2.setText("" + CV[1][178]);

        jRangierf.setSelected( (CV[1][131] &   1) ==   1);
        jRangierr.setSelected( (CV[1][131] &   2) ==   2);
        jRangier1.setSelected( (CV[1][131] &   4) ==   4);
        jRangier2.setSelected( (CV[1][131] &   8) ==   8);
        jRangier3.setSelected( (CV[1][131] &  16) ==  16);
        jRangier4.setSelected( (CV[1][131] &  32) ==  32);
        jRangierF3.setSelected((CV[1][131] &  64) ==  64);
        jRangierF4.setSelected((CV[1][131] & 128) == 128);


        jIn1Auxf.setSelected((CV[1][61] &   1) ==   1);
        jIn1Auxr.setSelected((CV[1][61] &   2) ==   2);
        jIn1Aux1.setSelected((CV[1][61] &   4) ==   4);
        jIn1Aux2.setSelected((CV[1][61] &   8) ==   8);
        jIn1Aux3.setSelected((CV[1][61] &  16) ==  16);
        jIn1Aux4.setSelected((CV[1][61] &  32) ==  32);
        jIn1Aux5.setSelected((CV[1][61] &  64) ==  64);
        jIn1Aux6.setSelected((CV[1][61] & 128) == 128);

        jIn2Auxf.setSelected((CV[1][63] &   1) ==   1);
        jIn2Auxr.setSelected((CV[1][63] &   2) ==   2);
        jIn2Aux1.setSelected((CV[1][63] &   4) ==   4);
        jIn2Aux2.setSelected((CV[1][63] &   8) ==   8);
        jIn2Aux3.setSelected((CV[1][63] &  16) ==  16);
        jIn2Aux4.setSelected((CV[1][63] &  32) ==  32);
        jIn2Aux5.setSelected((CV[1][63] &  64) ==  64);
        jIn2Aux6.setSelected((CV[1][63] & 128) == 128);

        
        jIn1Func1.setSelected((CV[1][62] &   1) ==   1);
        jIn1Func2.setSelected((CV[1][62] &   2) ==   2);
        jIn1Func3.setSelected((CV[1][62] &   4) ==   4);
        jIn1Func4.setSelected((CV[1][62] &   8) ==   8);
        jIn1Func5.setSelected((CV[1][62] &  16) ==  16);
        jIn1Func6.setSelected((CV[1][62] &  32) ==  32);
        jIn1Func7.setSelected((CV[1][62] &  64) ==  64);
        jIn1Func8.setSelected((CV[1][62] & 128) == 128);

        jIn2Func1.setSelected((CV[1][64] &   1) ==   1);
        jIn2Func2.setSelected((CV[1][64] &   2) ==   2);
        jIn2Func3.setSelected((CV[1][64] &   4) ==   4);
        jIn2Func4.setSelected((CV[1][64] &   8) ==   8);
        jIn2Func5.setSelected((CV[1][64] &  16) ==  16);
        jIn2Func6.setSelected((CV[1][64] &  32) ==  32);
        jIn2Func7.setSelected((CV[1][64] &  64) ==  64);
        jIn2Func8.setSelected((CV[1][64] & 128) == 128);

    }//GEN-LAST:event_jEffekte_2ComponentShown

    private void jF1_AK1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF1_AK1ActionPerformed
        if(jF1_AK1.isSelected()) {
            CV[1][97] |= 1;
        } else {
            CV[1][97] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+97 );
    }//GEN-LAST:event_jF1_AK1ActionPerformed

    private void jF2_SPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF2_SPActionPerformed
        jF2_Gl.setSelected(false);
        jF2_SH.setSelected(false);
        if(jF2_SP.isSelected()) {
            CV[1][149] = 16;
        } else {
            CV[1][149] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+149 );
    }//GEN-LAST:event_jF2_SPActionPerformed

    private void jF3_SPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF3_SPActionPerformed
        jF3_Gl.setSelected(false);
        jF3_SH.setSelected(false);
        if(jF3_SP.isSelected()) {
            CV[1][150] = 16;
        } else {
            CV[1][150] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+150 );
    }//GEN-LAST:event_jF3_SPActionPerformed

    private void jF4_SPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF4_SPActionPerformed
        jF4_SH.setSelected(false);
        jF4_Gl.setSelected(false);
        if(jF4_SP.isSelected()) {
            CV[1][151] = 16;
        } else {
            CV[1][151] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+151 );
    }//GEN-LAST:event_jF4_SPActionPerformed

    private void jF9_SPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF9_SPActionPerformed
        jF9_SH.setSelected(false);
        jF9_Gl.setSelected(false);
        if(jF9_SP.isSelected()) {
            CV[1][156] = 16;
        } else {
            CV[1][156] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+156 );
    }//GEN-LAST:event_jF9_SPActionPerformed

    private void jF1_SHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF1_SHActionPerformed
        jF1_Gl.setSelected(false);
        jF1_AK1.setSelected(false);
        if(jF1_SH.isSelected()) {
            CV[1][148] = 32;
        } else {
            CV[1][148] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+148 );
    }//GEN-LAST:event_jF1_SHActionPerformed

    private void jF2_SHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF2_SHActionPerformed
        jF2_Gl.setSelected(false);
        jF2_SP.setSelected(false);
        if(jF2_SH.isSelected()) {
            CV[1][149] = 32;
        } else {
            CV[1][149] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+149 );
    }//GEN-LAST:event_jF2_SHActionPerformed

    private void jF3_SHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF3_SHActionPerformed
        jF3_Gl.setSelected(false);
        jF3_SP.setSelected(false);
        if(jF3_SH.isSelected()) {
            CV[1][150] = 32;
        } else {
            CV[1][150] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+150 );
    }//GEN-LAST:event_jF3_SHActionPerformed

    private void jF4_SHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF4_SHActionPerformed
        jF4_SP.setSelected(false);
        jF4_Gl.setSelected(false);
        if(jF4_SH.isSelected()) {
            CV[1][151] = 32;
        } else {
            CV[1][151] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+151 );
    }//GEN-LAST:event_jF4_SHActionPerformed

    private void jF9_SHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF9_SHActionPerformed
        jF9_SP.setSelected(false);
        jF9_Gl.setSelected(false);
        if(jF9_SH.isSelected()) {
            CV[1][156] = 32;
        } else {
            CV[1][156] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+156 );
    }//GEN-LAST:event_jF9_SHActionPerformed

    private void jF11_SPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF11_SPActionPerformed
        jF11_SH.setSelected(false);
        jF11_Gl.setSelected(false);
        if(jF11_SP.isSelected()) {
            CV[1][158] = 16;
        } else {
            CV[1][158] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+158 );
    }//GEN-LAST:event_jF11_SPActionPerformed

    private void jF1_GlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF1_GlActionPerformed
        jF1_SH.setSelected(false);
        jF1_AK1.setSelected(false);
       if(jF1_Gl.isSelected()) {
            CV[1][148] = 64;
        } else {
            CV[1][148] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+148 );
    }//GEN-LAST:event_jF1_GlActionPerformed

    private void jF2_GlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF2_GlActionPerformed
        jF2_SH.setSelected(false);
        jF2_SP.setSelected(false);
        if(jF1_Gl.isSelected()) {
            CV[1][149] = 64;
        } else {
            CV[1][149] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+149 );
    }//GEN-LAST:event_jF2_GlActionPerformed

    private void jF3_GlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF3_GlActionPerformed
        jF3_SH.setSelected(false);
        jF3_SP.setSelected(false);
        if(jF1_Gl.isSelected()) {
            CV[1][150] = 64;
        } else {
            CV[1][150] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+150 );
    }//GEN-LAST:event_jF3_GlActionPerformed

    private void jF4_GlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF4_GlActionPerformed
        jF4_SH.setSelected(false);
        jF4_SP.setSelected(false);
        if(jF1_Gl.isSelected()) {
            CV[1][151] = 64;
        } else {
            CV[1][151] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+151 );
    }//GEN-LAST:event_jF4_GlActionPerformed

    private void jF9_GlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF9_GlActionPerformed
        jF9_SH.setSelected(false);
        jF9_SP.setSelected(false);
        if(jF1_Gl.isSelected()) {
            CV[1][156] = 64;
        } else {
            CV[1][156] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+156 );
    }//GEN-LAST:event_jF9_GlActionPerformed

    private void jF11_SHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF11_SHActionPerformed
        jF11_SP.setSelected(false);
        jF11_Gl.setSelected(false);
        if(jF11_SH.isSelected()) {
            CV[1][158] = 32;
        } else {
            CV[1][158] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+158 );
    }//GEN-LAST:event_jF11_SHActionPerformed

    private void jF11_GlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF11_GlActionPerformed
        jF11_SH.setSelected(false);
        jF11_SP.setSelected(false);
        if(jF1_Gl.isSelected()) {
            CV[1][158] = 64;
        } else {
            CV[1][158] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+158 );
    }//GEN-LAST:event_jF11_GlActionPerformed

    private void jF5_SPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF5_SPActionPerformed
        jF5_SH.setSelected(false);
        jF5_Gl.setSelected(false);
        if(jF5_SP.isSelected()) {
            CV[1][152] = 16;
        } else {
            CV[1][152] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+152 );
    }//GEN-LAST:event_jF5_SPActionPerformed

    private void jF5_SHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF5_SHActionPerformed
        jF5_SP.setSelected(false);
        jF5_Gl.setSelected(false);
        if(jF5_SH.isSelected()) {
            CV[1][152] = 32;
        } else {
            CV[1][152] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+152 );
    }//GEN-LAST:event_jF5_SHActionPerformed

    private void jF5_GlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF5_GlActionPerformed
        jF5_SH.setSelected(false);
        jF5_SP.setSelected(false);
        if(jF1_Gl.isSelected()) {
            CV[1][152] = 64;
        } else {
            CV[1][152] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+152 );
    }//GEN-LAST:event_jF5_GlActionPerformed

    private void jF6_SPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF6_SPActionPerformed
        jF6_SH.setSelected(false);
        jF6_Gl.setSelected(false);
        if(jF6_SP.isSelected()) {
            CV[1][153] = 16;
        } else {
            CV[1][153] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+153 );
    }//GEN-LAST:event_jF6_SPActionPerformed

    private void jF6_SHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF6_SHActionPerformed
        jF6_SP.setSelected(false);
        jF6_Gl.setSelected(false);
        if(jF6_SH.isSelected()) {
            CV[1][153] = 32;
        } else {
            CV[1][153] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+153 );
    }//GEN-LAST:event_jF6_SHActionPerformed

    private void jF6_GlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF6_GlActionPerformed
        jF6_SH.setSelected(false);
        jF6_SP.setSelected(false);
        if(jF6_SH.isSelected()) {
            CV[1][153] = 64;
        } else {
            CV[1][153] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+153 );
    }//GEN-LAST:event_jF6_GlActionPerformed
    private void jF10_SPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF10_SPActionPerformed
        jF10_SH.setSelected(false);
        jF10_Gl.setSelected(false);
        if(jF10_SP.isSelected()) {
            CV[1][157] = 16;
        } else {
            CV[1][157] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+157 );
    }//GEN-LAST:event_jF10_SPActionPerformed

    private void jF7_SPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF7_SPActionPerformed
        jF7_SH.setSelected(false);
        jF7_Gl.setSelected(false);
       if(jF7_SP.isSelected()) {
            CV[1][154] = 16;
        } else {
            CV[1][154] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+154 );
    }//GEN-LAST:event_jF7_SPActionPerformed

    private void jF7_SHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF7_SHActionPerformed
        jF7_SP.setSelected(false);
        jF7_Gl.setSelected(false);
        if(jF7_SH.isSelected()) {
            CV[1][154] = 32;
        } else {
            CV[1][154] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+154 );
    }//GEN-LAST:event_jF7_SHActionPerformed

    private void jF10_SHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF10_SHActionPerformed
        jF10_SP.setSelected(false);
        jF10_Gl.setSelected(false);
        if(jF10_SH.isSelected()) {
            CV[1][157] = 32;
        } else {
            CV[1][157] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+157 );
    }//GEN-LAST:event_jF10_SHActionPerformed

    private void jF12_SPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF12_SPActionPerformed
        jF12_SH.setSelected(false);
        jF12_Gl.setSelected(false);
        if(jF12_SP.isSelected()) {
            CV[1][159] = 16;
        } else {
            CV[1][159] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+159 );
    }//GEN-LAST:event_jF12_SPActionPerformed

    private void jF7_GlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF7_GlActionPerformed
        jF7_SH.setSelected(false);
        jF7_SP.setSelected(false);
        if(jF1_Gl.isSelected()) {
            CV[1][154] = 64;
        } else {
            CV[1][154] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+154 );
    }//GEN-LAST:event_jF7_GlActionPerformed

    private void jF8_SPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF8_SPActionPerformed
        jF8_SH.setSelected(false);
        jF8_Gl.setSelected(false);
        if(jF8_SP.isSelected()) {
            CV[1][155] = 16;
        } else {
            CV[1][155] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+155 );
    }//GEN-LAST:event_jF8_SPActionPerformed

    private void jF10_GlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF10_GlActionPerformed
        jF10_SH.setSelected(false);
        jF10_SP.setSelected(false);
        if(jF1_Gl.isSelected()) {
            CV[1][157] = 64;
        } else {
            CV[1][157] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+157 );
    }//GEN-LAST:event_jF10_GlActionPerformed

    private void jF12_SHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF12_SHActionPerformed
        jF12_SP.setSelected(false);
        jF12_Gl.setSelected(false);
        if(jF12_SH.isSelected()) {
            CV[1][159] = 32;
        } else {
            CV[1][159] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+159 );
    }//GEN-LAST:event_jF12_SHActionPerformed

    private void jF8_SHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF8_SHActionPerformed
        jF8_SP.setSelected(false);
        jF8_Gl.setSelected(false);
        if(jF8_SH.isSelected()) {
            CV[1][155] = 32;
        } else {
            CV[1][155] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+155 );
    }//GEN-LAST:event_jF8_SHActionPerformed

    private void jF8_GlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF8_GlActionPerformed
        jF8_SH.setSelected(false);
        jF8_SP.setSelected(false);
        if(jF1_Gl.isSelected()) {
            CV[1][155] = 64;
        } else {
            CV[1][155] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+155 );
    }//GEN-LAST:event_jF8_GlActionPerformed

    private void jF12_GlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF12_GlActionPerformed
        jF12_SH.setSelected(false);
        jF12_SP.setSelected(false);
        if(jF1_Gl.isSelected()) {
            CV[1][159] = 64;
        } else {
            CV[1][159] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+159 );
    }//GEN-LAST:event_jF12_GlActionPerformed

    private void jSoundComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jSoundComponentShown
        jCV_Anzeige.setSelectedItem( "CV#"+148 );

        //------------- F1 ------------------
        jF1_SP.setSelected((CV[1][148] & 0x10) == 0x10);
        jF1_SH.setSelected((CV[1][148] & 0x20) == 0x20);
        jF1_Gl.setSelected((CV[1][148] & 0x40) == 0x40);

        //------------- F2 ------------------
        jF2_SP.setSelected((CV[1][149] & 0x10) == 0x10);
        jF2_SH.setSelected((CV[1][149] & 0x20) == 0x20);
        jF2_Gl.setSelected((CV[1][149] & 0x40) == 0x40);

        //------------- F3 ------------------
        jF3_SP.setSelected((CV[1][150] & 0x10) == 0x10);
        jF3_SH.setSelected((CV[1][150] & 0x20) == 0x20);
        jF3_Gl.setSelected((CV[1][150] & 0x40) == 0x40);

        //------------- F4 ------------------
        jF4_SP.setSelected((CV[1][151] & 0x10) == 0x10);
        jF4_SH.setSelected((CV[1][151] & 0x20) == 0x20);
        jF4_Gl.setSelected((CV[1][151] & 0x40) == 0x40);

        //------------- F5 ------------------
        jF5_SP.setSelected((CV[1][152] & 0x10) == 0x10);
        jF5_SH.setSelected((CV[1][152] & 0x20) == 0x20);
        jF5_Gl.setSelected((CV[1][152] & 0x40) == 0x40);

        //------------- F6 ------------------
        jF6_SP.setSelected((CV[1][153] & 0x10) == 0x10);
        jF6_SH.setSelected((CV[1][153] & 0x20) == 0x20);
        jF6_Gl.setSelected((CV[1][153] & 0x40) == 0x40);

        //------------- F7 ------------------
        jF7_SP.setSelected((CV[1][154] & 0x10) == 0x10);
        jF7_SH.setSelected((CV[1][154] & 0x20) == 0x20);
        jF7_Gl.setSelected((CV[1][154] & 0x40) == 0x40);

        //------------- F8 ------------------
        jF8_SP.setSelected((CV[1][155] & 0x10) == 0x10);
        jF8_SH.setSelected((CV[1][155] & 0x20) == 0x20);
        jF8_Gl.setSelected((CV[1][155] & 0x40) == 0x40);

        //------------- F9 ------------------
        jF9_SP.setSelected((CV[1][156] & 0x10) == 0x10);
        jF9_SH.setSelected((CV[1][156] & 0x20) == 0x20);
        jF9_Gl.setSelected((CV[1][156] & 0x40) == 0x40);

        //------------- F10 ------------------
        jF10_SP.setSelected((CV[1][157] & 0x10) == 0x10);
        jF10_SH.setSelected((CV[1][157] & 0x20) == 0x20);
        jF10_Gl.setSelected((CV[1][157] & 0x40) == 0x40);

        //------------- F11 ------------------
        jF11_SP.setSelected((CV[1][158] & 0x10) == 0x10);
        jF11_SH.setSelected((CV[1][158] & 0x20) == 0x20);
        jF11_Gl.setSelected((CV[1][158] & 0x40) == 0x40);

        //------------- F12 ------------------
        jF12_SP.setSelected((CV[1][159] & 0x10) == 0x10);
        jF12_SH.setSelected((CV[1][159] & 0x20) == 0x20);
        jF12_Gl.setSelected((CV[1][159] & 0x40) == 0x40);

        // Ankuppeln
        //-----Eingang 1 -----------
        jF1_AKf.setSelected(  (CV[1][97] & 128) == 128);
        jF1_AKr.setSelected(!((CV[1][97] & 128) == 128));
        jF1_AK1.setSelected(  (CV[1][97] &   1) ==   1);
        jF1_AK2.setSelected(  (CV[1][97] &   2) ==   2);
        jF1_AK3.setSelected(  (CV[1][97] &   4) ==   4);
        jF1_AK4.setSelected(  (CV[1][97] &   8) ==   8);

        jF1_AK5.setSelected( (CV[1][98] &   1) ==   1);
        jF1_AK6.setSelected( (CV[1][98] &   2) ==   2);
        jF1_AK7.setSelected( (CV[1][98] &   4) ==   4);
        jF1_AK8.setSelected( (CV[1][98] &   8) ==   8);
        jF1_AK9.setSelected( (CV[1][98] &  16) ==  16);
        jF1_AK10.setSelected((CV[1][98] &  32) ==  32);
        jF1_AK11.setSelected((CV[1][98] &  64) ==  64);
        jF1_AK12.setSelected((CV[1][98] & 128) == 128);

        //-----Eingang 2 -----------
        jF2_AKf.setSelected(  (CV[1][99] & 128) == 128);
        jF2_AKr.setSelected(!((CV[1][99] & 128) == 128));
        jF2_AK1.setSelected(  (CV[1][99] &   1) ==   1);
        jF2_AK2.setSelected(  (CV[1][99] &   2) ==   2);
        jF2_AK3.setSelected(  (CV[1][99] &   4) ==   4);
        jF2_AK4.setSelected(  (CV[1][99] &   8) ==   8);

        jF2_AK5.setSelected( (CV[1][100] &   1) ==   1);
        jF2_AK6.setSelected( (CV[1][100] &   2) ==   2);
        jF2_AK7.setSelected( (CV[1][100] &   4) ==   4);
        jF2_AK8.setSelected( (CV[1][100] &   8) ==   8);
        jF2_AK9.setSelected( (CV[1][100] &  16) ==  16);
        jF2_AK10.setSelected((CV[1][100] &  32) ==  32);
        jF2_AK11.setSelected((CV[1][100] &  64) ==  64);
        jF2_AK12.setSelected((CV[1][100] & 128) == 128);
        
        
        //Pendeln
        jIN1_PendelF5.setSelected( (CV[1][198] &   1) ==   1);
        jIN1_PendelF6.setSelected( (CV[1][198] &   2) ==   2);
        jIN1_PendelF7.setSelected( (CV[1][198] &   4) ==   4);
        jIN1_PendelF8.setSelected( (CV[1][198] &   8) ==   8);
        jIN1_PendelF9.setSelected( (CV[1][198] &  16) ==  16);
        jIN1_PendelF10.setSelected((CV[1][198] &  32) ==  32);
        jIN1_PendelF11.setSelected((CV[1][198] &  64) ==  64);
        jIN1_PendelF12.setSelected((CV[1][198] & 128) == 128);
        
        jIN1_PendelRW.setSelected((CV[1][196] & 128) == 128);
        jIN2_PendelRW.setSelected((CV[1][197] & 128) == 128);

        jIN1_Pendelr.setSelected(  (CV[1][196] & 64) == 64);
        jIN1_Pendelf.setSelected(!((CV[1][196] & 64) == 64));

        jIN2_Pendelr.setSelected(  (CV[1][197] & 64) == 64);
        jIN2_Pendelf.setSelected(!((CV[1][197] & 64) == 64));

        if((CV[1][196] & 32) == 32)
        {
            jIN1_Pendelr.setSelected(true);
            jIN1_Pendelf.setSelected(true);
        }
        if((CV[1][197] & 32) == 32)
        {
            jIN2_Pendelr.setSelected(true);
            jIN2_Pendelf.setSelected(true);
        }
        
        int j = CV[1][196] & 0x1F;
        int i = 0;
        if((j & 1) == 1)
        {
            i += 5;
        }
        if((j & 2) == 2)
        {
            i += 10;
        }
        if((j & 4) == 4)
        {
            i += 20;
        }
        if((j & 8) == 8)
        {
            i += 40;
        }
        if((j & 16) == 16)
        {
            i *= 4;
        }        
        jIN1_PendelHaltZeit.setText("" + i);
        
        i = 0;
        j = CV[1][197] & 0x1F;
        if((j & 1) == 1)
        {
            i += 5;
        }
        if((j & 2) == 2)
        {
            i += 10;
        }
        if((j & 4) == 4)
        {
            i += 20;
        }
        if((j & 8) == 8)
        {
            i += 40;
        }
        if((j & 16) == 16)
        {
            i *= 4;
        }
        jIN2_PendelHaltZeit.setText("" + i);
    }//GEN-LAST:event_jSoundComponentShown

    private void jServoF9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jServoF9ActionPerformed
        if(jServoF9.isSelected()) {
            CV[1][63] = 0;
            CV[1][64] = 0;
            CV[1][167] |= 16;
            jServoPOM.setSelected(true);
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
            jServoPOM.setSelected(true);
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
            jServoPOM.setSelected(true);
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
            jServoPOM.setSelected(true);
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
            jServoPOM.setSelected(true);
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
            jServoPOM.setSelected(true);
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
            jServoPOM.setSelected(true);
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

    private void jServoF11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jServoF11ActionPerformed
        if(jServoF11.isSelected()) {
            CV[1][63] = 0;
            CV[1][64] = 0;
            CV[1][167] |= 64;
            jServoPOM.setSelected(true);
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

    private void jServoComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jServoComponentShown
        jCV_Anzeige.setSelectedItem( "CV#"+167 );

        if((CV[1][167] > 0) || (CV[1][168]) > 0)
            jServoPOM.setSelected(true);
        else
            jServoPOM.setSelected(false);

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

        jServoPOM.setSelected(CV[1][164] > 0);

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

    private void jPacketTimeOutFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPacketTimeOutFocusLost
        CV[1][11] = KTUI.checkTextField( this, jPacketTimeOut, 0, 255, 5, true );
        jCV_Anzeige.setSelectedItem( "CV#"+11 );
    }//GEN-LAST:event_jPacketTimeOutFocusLost

    private void jPacketTimeOutKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPacketTimeOutKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jPacketTimeOutKeyReleased

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

        jPacketTimeOut.setText("" + CV[1][11]);
        jUmschaltEmpf.setText("" + CV[1][199]);
        if((CV[1][12] & 1) == 1) {
            jAnalogRW_G.setSelected(true);
            jAnalogRW_W.setSelected(false);
        } else {
            jAnalogRW_W.setSelected(true);
            jAnalogRW_G.setSelected(false);
        }
    }//GEN-LAST:event_jAnalogComponentShown

    private void jPanel3ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel3ComponentShown

    }//GEN-LAST:event_jPanel3ComponentShown

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

    private void jSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSaveActionPerformed
        // Alle CVs werden in einer Datei gespeichert
        SaveOpenDialog od;
        switch( KTUI.Decoder ) {
            case c.LD_G31Plus:
            case c.LD_G33Plus:
            case c.LD_G34Plus:
                CVs = "LD-G-30plus\r\n";
                CVs += "Version 1.1\r\n";
                for(int i = 0; i < CV[0].length; i++) {
                    if( CV[0][i] > 0 ) { // only write used CVs (CV[0][cv] != 0 ) to file
                        CVs += "\r\nCV#" + CV[0][i] + " = " + CV[1][i];
                    }
                }
                CVs += "\r\n\r\nKommentar:\r\n";
                CVs += jComment.getText();
                od = new SaveOpenDialog( this, true, false, CVs, this, "30p");
                break;

            case c.LD_G36Plus:
                CVs = "LD-G-36plus\r\n";
                CVs += "Version 1.0\r\n";
                for(int i = 0; i < CV[0].length; i++) {
                    if( CV[0][i] > 0 ) { // only write used CVs (CV[0][cv] != 0 ) to file
                        CVs += "\r\nCV#" + CV[0][i] + " = " + CV[1][i];
                    }
                }
                CVs += "\r\n\r\nKommentar:\r\n";
                CVs += jComment.getText();
                od = new SaveOpenDialog( this, true, false, CVs, this, "36p");
                break;
        }
    }//GEN-LAST:event_jSaveActionPerformed

    private void jDecoderAdresse1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDecoderAdresse1FocusLost
        CV[1][19] = KTUI.checkTextField( this, jDecoderAdresse1, 0, 127, 0, true);
        jCV_Anzeige.setSelectedItem( "CV#"+19 );
    }//GEN-LAST:event_jDecoderAdresse1FocusLost

    private void jDecoderAdresse1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDecoderAdresse1KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jDecoderAdresse1KeyReleased

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
         KTUI.frameInstanceDEVICE = null;
         KTUI.setFocus();
    }//GEN-LAST:event_formWindowClosed

    private void jFL_7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFL_7ActionPerformed
        if(jFL_7.isSelected()) {
            CV[1][33] |= 0x40;
        } else {
            CV[1][33] &= ~0x40;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+33 );
    }//GEN-LAST:event_jFL_7ActionPerformed

    private void jFL_8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFL_8ActionPerformed
        if(jFL_8.isSelected()) {
            CV[1][33] |= 0x80;
        } else {
            CV[1][33] &= ~0x80;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+33 );

    }//GEN-LAST:event_jFL_8ActionPerformed

    private void jFR_7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFR_7ActionPerformed
        if(jFR_7.isSelected()) {
            CV[1][34] |= 0x40;
        } else {
            CV[1][34] &= ~0x40;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+34 );
    }//GEN-LAST:event_jFR_7ActionPerformed

    private void jFR_8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFR_8ActionPerformed
        if(jFR_8.isSelected()) {
            CV[1][34] |= 0x80;
        } else {
            CV[1][34] &= ~0x80;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+34 );
    }//GEN-LAST:event_jFR_8ActionPerformed

    private void jF1_7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF1_7ActionPerformed
        if(jF1_7.isSelected()) {
            CV[1][35] |= 0x40;
        } else {
            CV[1][35] &= ~0x40;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+35 );
    }//GEN-LAST:event_jF1_7ActionPerformed

    private void jF1_8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF1_8ActionPerformed
        if(jF1_8.isSelected()) {
            CV[1][35] |= 0x80;
        } else {
            CV[1][35] &= ~0x80;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+35 );
    }//GEN-LAST:event_jF1_8ActionPerformed

    private void jF2_7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF2_7ActionPerformed
        if(jF2_7.isSelected()) {
            CV[1][36] |= 0x40;
        } else {
            CV[1][36] &= ~0x40;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+36 );
    }//GEN-LAST:event_jF2_7ActionPerformed

    private void jF2_8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF2_8ActionPerformed
        if(jF2_8.isSelected()) {
            CV[1][36] |= 0x80;
        } else {
            CV[1][36] &= ~0x80;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+36 );
    }//GEN-LAST:event_jF2_8ActionPerformed

    private void jF3_7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF3_7ActionPerformed
        if(jF3_7.isSelected()) {
            CV[1][37] |= 0x40;
        } else {
            CV[1][37] &= ~0x40;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+37 );
    }//GEN-LAST:event_jF3_7ActionPerformed

    private void jF3_8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF3_8ActionPerformed
        if(jF3_8.isSelected()) {
            CV[1][37] |= 0x80;
        } else {
            CV[1][37] &= ~0x80;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+37 );
    }//GEN-LAST:event_jF3_8ActionPerformed

    private void jF4_7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF4_7ActionPerformed
        if(jF4_7.isSelected()) {
            CV[1][38] |= 0x40;
        } else {
            CV[1][38] &= ~0x40;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+38 );
    }//GEN-LAST:event_jF4_7ActionPerformed

    private void jF4_8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF4_8ActionPerformed
        if(jF4_8.isSelected()) {
            CV[1][38] |= 0x80;
        } else {
            CV[1][38] &= ~0x80;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+38 );
    }//GEN-LAST:event_jF4_8ActionPerformed

    private void jF5_7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF5_7ActionPerformed
        if(jF5_7.isSelected()) {
            CV[1][39] |= 0x40;
        } else {
            CV[1][39] &= ~0x40;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+39 );
    }//GEN-LAST:event_jF5_7ActionPerformed

    private void jF5_8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF5_8ActionPerformed
        if(jF5_8.isSelected()) {
            CV[1][39] |= 0x80;
        } else {
            CV[1][39] &= ~0x80;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+39 );
    }//GEN-LAST:event_jF5_8ActionPerformed

    private void jF6_7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF6_7ActionPerformed
        if(jF6_7.isSelected()) {
            CV[1][40] |= 0x40;
        } else {
            CV[1][40] &= ~0x40;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+40 );
    }//GEN-LAST:event_jF6_7ActionPerformed

    private void jF6_8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF6_8ActionPerformed
        if(jF6_8.isSelected()) {
            CV[1][40] |= 0x80;
        } else {
            CV[1][40] &= ~0x80;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+40 );
    }//GEN-LAST:event_jF6_8ActionPerformed

    private void jF7_7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF7_7ActionPerformed
        if(jF7_7.isSelected()) {
            CV[1][41] |= 0x40;
        } else {
            CV[1][41] &= ~0x40;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+41 );
    }//GEN-LAST:event_jF7_7ActionPerformed

    private void jF7_8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF7_8ActionPerformed
        if(jF7_8.isSelected()) {
            CV[1][41] |= 0x80;
        } else {
            CV[1][41] &= ~0x80;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+41 );
    }//GEN-LAST:event_jF7_8ActionPerformed

    private void jF8_7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF8_7ActionPerformed
        if(jF8_7.isSelected()) {
            CV[1][42] |= 0x40;
        } else {
            CV[1][42] &= ~0x40;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+42 );
    }//GEN-LAST:event_jF8_7ActionPerformed

    private void jF8_8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF8_8ActionPerformed
        if(jF8_8.isSelected()) {
            CV[1][42] |= 0x80;
        } else {
            CV[1][42] &= ~0x80;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+42 );
    }//GEN-LAST:event_jF8_8ActionPerformed

    private void jF9_7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF9_7ActionPerformed
        if(jF9_7.isSelected()) {
            CV[1][43] |= 0x40;
        } else {
            CV[1][43] &= ~0x40;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+43 );
    }//GEN-LAST:event_jF9_7ActionPerformed

    private void jF9_8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF9_8ActionPerformed
        if(jF9_8.isSelected()) {
            CV[1][43] |= 0x80;
        } else {
            CV[1][43] &= ~0x80;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+43 );
    }//GEN-LAST:event_jF9_8ActionPerformed

    private void jF10_7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF10_7ActionPerformed
        if(jF10_7.isSelected()) {
            CV[1][44] |= 0x40;
        } else {
            CV[1][44] &= ~0x40;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+44 );
    }//GEN-LAST:event_jF10_7ActionPerformed

    private void jF10_8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF10_8ActionPerformed
        if(jF10_8.isSelected()) {
            CV[1][44] |= 0x80;
        } else {
            CV[1][44] &= ~0x80;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+44 );
    }//GEN-LAST:event_jF10_8ActionPerformed

    private void jF11_7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF11_7ActionPerformed
        if(jF11_7.isSelected()) {
            CV[1][45] |= 0x40;
        } else {
            CV[1][45] &= ~0x40;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+45 );
    }//GEN-LAST:event_jF11_7ActionPerformed

    private void jF11_8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF11_8ActionPerformed
        if(jF11_8.isSelected()) {
            CV[1][45] |= 0x80;
        } else {
            CV[1][45] &= ~0x80;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+45 );
    }//GEN-LAST:event_jF11_8ActionPerformed

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

    private void jF13_7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF13_7ActionPerformed
        if(jF13_7.isSelected()) {
            CV[1][180] |= 0x40;
        } else {
            CV[1][180] &= ~0x40;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+180 );
    }//GEN-LAST:event_jF13_7ActionPerformed

    private void jF14_7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF14_7ActionPerformed
        if(jF14_7.isSelected()) {
            CV[1][181] |= 0x40;
        } else {
            CV[1][181] &= ~0x40;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+181 );
    }//GEN-LAST:event_jF14_7ActionPerformed

    private void jF15_7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF15_7ActionPerformed
        if(jF15_7.isSelected()) {
            CV[1][182] |= 0x40;
        } else {
            CV[1][182] &= ~0x40;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+182 );
    }//GEN-LAST:event_jF15_7ActionPerformed

    private void jF16_7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF16_7ActionPerformed
        if(jF16_7.isSelected()) {
            CV[1][183] |= 0x40;
        } else {
            CV[1][183] &= ~0x40;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+183 );
    }//GEN-LAST:event_jF16_7ActionPerformed

    private void jF17_7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF17_7ActionPerformed
        if(jF17_7.isSelected()) {
            CV[1][184] |= 0x40;
        } else {
            CV[1][184] &= ~0x40;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+184 );
    }//GEN-LAST:event_jF17_7ActionPerformed

    private void jF18_7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF18_7ActionPerformed
        if(jF18_7.isSelected()) {
            CV[1][185] |= 0x40;
        } else {
            CV[1][185] &= ~0x40;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+185 );
    }//GEN-LAST:event_jF18_7ActionPerformed

    private void jF19_7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF19_7ActionPerformed
        if(jF19_7.isSelected()) {
            CV[1][186] |= 0x40;
        } else {
            CV[1][186] &= ~0x40;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+186 );
    }//GEN-LAST:event_jF19_7ActionPerformed

    private void jF20_7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF20_7ActionPerformed
        if(jF20_7.isSelected()) {
            CV[1][187] |= 0x40;
        } else {
            CV[1][187] &= ~0x40;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+187 );
    }//GEN-LAST:event_jF20_7ActionPerformed

    private void jF21_7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF21_7ActionPerformed
        if(jF21_7.isSelected()) {
            CV[1][188] |= 0x40;
        } else {
            CV[1][188] &= ~0x40;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+188 );
    }//GEN-LAST:event_jF21_7ActionPerformed

    private void jF22_7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF22_7ActionPerformed
        if(jF22_7.isSelected()) {
            CV[1][189] |= 0x40;
        } else {
            CV[1][189] &= ~0x40;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+189 );
    }//GEN-LAST:event_jF22_7ActionPerformed

    private void jF23_7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF23_7ActionPerformed
        if(jF23_7.isSelected()) {
            CV[1][190] |= 0x40;
        } else {
            CV[1][190] &= ~0x40;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+190 );
    }//GEN-LAST:event_jF23_7ActionPerformed

    private void jF24_7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF24_7ActionPerformed
        if(jF24_7.isSelected()) {
            CV[1][191] |= 0x40;
        } else {
            CV[1][191] &= ~0x40;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+191 );
    }//GEN-LAST:event_jF24_7ActionPerformed

    private void jF25_7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF25_7ActionPerformed
        if(jF25_7.isSelected()) {
            CV[1][192] |= 0x40;
        } else {
            CV[1][192] &= ~0x40;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+192 );
    }//GEN-LAST:event_jF25_7ActionPerformed

    private void jF26_7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF26_7ActionPerformed
        if(jF26_7.isSelected()) {
            CV[1][193] |= 0x40;
        } else {
            CV[1][193] &= ~0x40;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+193 );
    }//GEN-LAST:event_jF26_7ActionPerformed

    private void jF27_7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF27_7ActionPerformed
        if(jF27_7.isSelected()) {
            CV[1][194] |= 0x40;
        } else {
            CV[1][194] &= ~0x40;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+194 );
    }//GEN-LAST:event_jF27_7ActionPerformed

    private void jF28_7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF28_7ActionPerformed
        if(jF28_7.isSelected()) {
            CV[1][195] |= 0x40;
        } else {
            CV[1][195] &= ~0x40;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+195 );
    }//GEN-LAST:event_jF28_7ActionPerformed

    private void jBlink_Einschaltzeit_fFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlink_Einschaltzeit_fFocusLost
        int max = 255; // Handbuch
        max = ( CV[1][104] - CV[1][140] ) / 2 ; // Abhängigkeiten
        if( ! KTUI.checkNumRange(jBlink_Einschaltzeit_f.getText(), 0, max) ) {
            KTUI.mbBlinkEinschaltzeit( this, CV[1][104], CV[1][140], jBlink_Einschaltzeit_f.getText() );
            jBlink_Einschaltzeit_f.grabFocus();
            return;
        }
        // Wert ist bereits geprüft. also "still" übernehmen (view -> false)
        CV[1][132] = KTUI.checkTextField( this, jBlink_Einschaltzeit_f, 0, max, 4, false);
        jCV_Anzeige.setSelectedItem( "CV#"+132 );
    }//GEN-LAST:event_jBlink_Einschaltzeit_fFocusLost

    private void jBlink_Einschaltzeit_fKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBlink_Einschaltzeit_fKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jBlink_Einschaltzeit_fKeyReleased

    private void jBlink_Einschaltzeit_rFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlink_Einschaltzeit_rFocusLost
        int max = 255; // Handbuch
        max = ( CV[1][105] - CV[1][141] ) / 2 ; // Abhängigkeiten
        if( ! KTUI.checkNumRange(jBlink_Einschaltzeit_r.getText(), 0, max) ) {
            KTUI.mbBlinkEinschaltzeit( this, CV[1][105], CV[1][141], jBlink_Einschaltzeit_r.getText() );
            jBlink_Einschaltzeit_r.grabFocus();
            return;
        }
        // Wert ist bereits geprüft. also "still" übernehmen (view -> false)
        CV[1][133] = KTUI.checkTextField( this, jBlink_Einschaltzeit_r, 0, max, 4, false);
        jCV_Anzeige.setSelectedItem( "CV#"+133 );
    }//GEN-LAST:event_jBlink_Einschaltzeit_rFocusLost

    private void jBlink_Einschaltzeit_rKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBlink_Einschaltzeit_rKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jBlink_Einschaltzeit_rKeyReleased

    private void jBlink_Pausezeit_fFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlink_Pausezeit_fFocusLost
        int max = 255; // Handbuch
        max = CV[1][104] - ( CV[1][132] * 2 ); // Abhängigkeiten
        if( ! KTUI.checkNumRange(jBlink_Pausezeit_f.getText(), 0, max) ) {
            KTUI.mbBlinkPausezeit( this, CV[1][104], CV[1][132], jBlink_Pausezeit_f.getText() );
            jBlink_Pausezeit_f.grabFocus();
            return;
        }
        // Wert ist bereits geprüft. also "still" übernehmen (view -> false)
        CV[1][140] = KTUI.checkTextField( this, jBlink_Pausezeit_f, 0, max, 2, false);
        jCV_Anzeige.setSelectedItem( "CV#"+140 );
    }//GEN-LAST:event_jBlink_Pausezeit_fFocusLost

    private void jBlink_Pausezeit_fKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBlink_Pausezeit_fKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jBlink_Pausezeit_fKeyReleased

    private void jBlink_Pausezeit_rFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlink_Pausezeit_rFocusLost
        int max = 255; // Handbuch
        max = CV[1][105] - ( CV[1][133] * 2 ); // Abhängigkeiten
        if( ! KTUI.checkNumRange(jBlink_Pausezeit_r.getText(), 0, max) ) {
            KTUI.mbBlinkPausezeit( this, CV[1][105], CV[1][133], jBlink_Pausezeit_r.getText() );
            jBlink_Pausezeit_r.grabFocus();
            return;
        }
        // Wert ist bereits geprüft. also "still" übernehmen (view -> false)
        CV[1][141] = KTUI.checkTextField( this, jBlink_Pausezeit_r, 0, max, 2, false);
        jCV_Anzeige.setSelectedItem( "CV#"+141 );
    }//GEN-LAST:event_jBlink_Pausezeit_rFocusLost

    private void jBlink_Pausezeit_rKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBlink_Pausezeit_rKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jBlink_Pausezeit_rKeyReleased

    private void jBlinkfrequenzfFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlinkfrequenzfFocusLost
        int min = ( CV[1][132] *2 ) + CV[1][140] ; // Abhängigkeiten
        if( min < 10 ) {
            min = 10; // Handbuch
        }
        if( ! KTUI.checkNumRange(jBlinkfrequenzf.getText(), min, 255) ) {
            KTUI.mbBlinkFrequenz( this, CV[1][132], CV[1][140], jBlinkfrequenzf.getText() );
            jBlinkfrequenzf.grabFocus();
            return;
        }
        // Wert ist bereits geprüft. also "still" übernehmen (view -> false)
        CV[1][104] = KTUI.checkTextField( this, jBlinkfrequenzf, min, 255, 20, false);
        jCV_Anzeige.setSelectedItem( "CV#"+104 );
    }//GEN-LAST:event_jBlinkfrequenzfFocusLost

    private void jBlinkfrequenzfKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBlinkfrequenzfKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jBlinkfrequenzfKeyReleased

    private void jBlinkfrequenzrFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlinkfrequenzrFocusLost
        int min = ( CV[1][133] *2 ) + CV[1][141] ; // Abhängigkeiten
        if( min < 10 ) {
            min = 10; // Handbuch
        }
        if( ! KTUI.checkNumRange(jBlinkfrequenzr.getText(), min, 255) ) {
            KTUI.mbBlinkFrequenz( this, CV[1][133], CV[1][141], jBlinkfrequenzr.getText() );
            jBlinkfrequenzr.grabFocus();
            return;
        }
        // Wert ist bereits geprüft. also "still" übernehmen (view -> false)
        CV[1][105] = KTUI.checkTextField( this, jBlinkfrequenzr, min, 255, 20, false);
        jCV_Anzeige.setSelectedItem( "CV#"+105 );
    }//GEN-LAST:event_jBlinkfrequenzrFocusLost

    private void jBlinkfrequenzrKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBlinkfrequenzrKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jBlinkfrequenzrKeyReleased

    private void jVorfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jVorfActionPerformed
        if(!jVor1.isSelected()) {
            CV[1][53] |= 2;
        } else {
            CV[1][53] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+53 );
    }//GEN-LAST:event_jVorfActionPerformed

    private void jRueckfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRueckfActionPerformed
        if(!jRueck1.isSelected()) {
            CV[1][55] |= 1;
        } else {
            CV[1][55] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+55 );
    }//GEN-LAST:event_jRueckfActionPerformed

    private void jRueckrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRueckrActionPerformed
        if(!jRueck2.isSelected()) {
            CV[1][54] |= 1;
        } else {
            CV[1][54] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+54 );
    }//GEN-LAST:event_jRueckrActionPerformed

    private void jVorrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jVorrActionPerformed
        if(!jVor2.isSelected()) {
            CV[1][54] |= 2;
        } else {
            CV[1][54] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+54 );
    }//GEN-LAST:event_jVorrActionPerformed

    private void jAuxInvrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAuxInvrActionPerformed
        if(jAuxInv2.isSelected()) {
            CV[1][54] |= 4;
        } else {
            CV[1][54] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+54 );
    }//GEN-LAST:event_jAuxInvrActionPerformed

    private void jAuxInvfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAuxInvfActionPerformed
        if(jAuxInv1.isSelected()) {
            CV[1][53] |= 4;
        } else {
            CV[1][53] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+53 );
    }//GEN-LAST:event_jAuxInvfActionPerformed

    private void jMARs5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMARs5ActionPerformed
        if(jMARs5.isSelected()) {
            CV[1][59] |= 32;
        } else {
            CV[1][59] &= ~32;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+59 );
    }//GEN-LAST:event_jMARs5ActionPerformed

    private void jMARs6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMARs6ActionPerformed
        if(jMARs6.isSelected()) {
            CV[1][60] |= 32;
        } else {
            CV[1][60] &= ~32;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+60 );
    }//GEN-LAST:event_jMARs6ActionPerformed

    private void jMARsfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMARsfActionPerformed
        if(jMARs1.isSelected()) {
            CV[1][53] |= 32;
        } else {
            CV[1][53] &= ~32;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+53 );
    }//GEN-LAST:event_jMARsfActionPerformed

    private void jMARsrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMARsrActionPerformed
        if(jMARs2.isSelected()) {
            CV[1][54] |= 32;
        } else {
            CV[1][54] &= ~32;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+54 );
    }//GEN-LAST:event_jMARsrActionPerformed

    private void jBlinkfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBlinkfActionPerformed
        if(jBlink1.isSelected()) {
            CV[1][53] |= 16;
        } else {
            CV[1][53] &= ~16;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+53 );
    }//GEN-LAST:event_jBlinkfActionPerformed

    private void jBlinkrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBlinkrActionPerformed
        if(jBlink2.isSelected()) {
            CV[1][54] |= 16;
        } else {
            CV[1][54] &= ~16;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+54 );
    }//GEN-LAST:event_jBlinkrActionPerformed

    private void jDoppelBlinkfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDoppelBlinkfActionPerformed
        if(jDoppelBlink1.isSelected()) {
            CV[1][53] |= 64;
        } else {
            CV[1][53] &= ~64;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+53 );
    }//GEN-LAST:event_jDoppelBlinkfActionPerformed

    private void jDoppelBlinkrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDoppelBlinkrActionPerformed
        if(jDoppelBlink2.isSelected()) {
            CV[1][54] |= 64;
        } else {
            CV[1][54] &= ~64;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+54 );
    }//GEN-LAST:event_jDoppelBlinkrActionPerformed

    private void jBl_InvfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBl_InvfActionPerformed
        if(jBl_Inv1.isSelected()) {
            CV[1][53] |= 8;
        } else {
            CV[1][53] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+53 );
    }//GEN-LAST:event_jBl_InvfActionPerformed

    private void jBl_InvrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBl_InvrActionPerformed
        if(jBl_Inv2.isSelected()) {
            CV[1][54] |= 8;
        } else {
            CV[1][54] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+54 );
    }//GEN-LAST:event_jBl_InvrActionPerformed

    private void jIn1AuxfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn1AuxfActionPerformed
        if(jIn1Auxf.isSelected()) {
            CV[1][61] |= 1;
        } else {
            CV[1][61] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+61 );
    }//GEN-LAST:event_jIn1AuxfActionPerformed

    private void jIn1AuxrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn1AuxrActionPerformed
        if(jIn1Auxr.isSelected()) {
            CV[1][61] |= 2;
        } else {
            CV[1][61] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+61 );
    }//GEN-LAST:event_jIn1AuxrActionPerformed

    private void jIn2AuxfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn2AuxfActionPerformed
        if(jIn2Auxf.isSelected()) {
            CV[1][63] |= 1;
        } else {
            CV[1][63] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+63 );
    }//GEN-LAST:event_jIn2AuxfActionPerformed

    private void jIn2AuxrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn2AuxrActionPerformed
        if(jIn2Auxr.isSelected()) {
            CV[1][63] |= 2;
        } else {
            CV[1][63] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+63 );
    }//GEN-LAST:event_jIn2AuxrActionPerformed

    private void jIn1Func1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn1Func1ActionPerformed
        if(jIn1Func1.isSelected()) {
            CV[1][62] |= 1;
        } else {
            CV[1][62] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+62 );
    }//GEN-LAST:event_jIn1Func1ActionPerformed

    private void jIn2Func1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn2Func1ActionPerformed
        if(jIn2Func1.isSelected()) {
            CV[1][64] |= 1;
        } else {
            CV[1][64] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+64 );
    }//GEN-LAST:event_jIn2Func1ActionPerformed

    private void jIn1Func2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn1Func2ActionPerformed
        if(jIn1Func2.isSelected()) {
            CV[1][62] |= 2;
        } else {
            CV[1][62] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+62 );
    }//GEN-LAST:event_jIn1Func2ActionPerformed

    private void jIn2Func2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn2Func2ActionPerformed
        if(jIn2Func2.isSelected()) {
            CV[1][64] |= 2;
        } else {
            CV[1][64] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+64 );
    }//GEN-LAST:event_jIn2Func2ActionPerformed

    private void jIn1Func3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn1Func3ActionPerformed
        if(jIn1Func3.isSelected()) {
            CV[1][62] |= 4;
        } else {
            CV[1][62] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+62 );
    }//GEN-LAST:event_jIn1Func3ActionPerformed

    private void jIn2Func3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn2Func3ActionPerformed
        if(jIn2Func3.isSelected()) {
            CV[1][64] |= 4;
        } else {
            CV[1][64] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+64 );
    }//GEN-LAST:event_jIn2Func3ActionPerformed

    private void jIn1Func4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn1Func4ActionPerformed
        if(jIn1Func4.isSelected()) {
            CV[1][62] |= 8;
        } else {
            CV[1][62] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+62 );
    }//GEN-LAST:event_jIn1Func4ActionPerformed

    private void jIn2Func4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn2Func4ActionPerformed
        if(jIn2Func4.isSelected()) {
            CV[1][64] |= 8;
        } else {
            CV[1][64] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+64 );
    }//GEN-LAST:event_jIn2Func4ActionPerformed

    private void jIn1Func5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn1Func5ActionPerformed
        if(jIn1Func5.isSelected()) {
            CV[1][62] |= 16;
        } else {
            CV[1][62] &= ~16;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+62 );
    }//GEN-LAST:event_jIn1Func5ActionPerformed

    private void jIn2Func5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn2Func5ActionPerformed
        if(jIn2Func5.isSelected()) {
            CV[1][64] |= 16;
        } else {
            CV[1][64] &= ~16;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+64 );
    }//GEN-LAST:event_jIn2Func5ActionPerformed

    private void jIn1Func6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn1Func6ActionPerformed
        if(jIn1Func6.isSelected()) {
            CV[1][62] |= 32;
        } else {
            CV[1][62] &= ~32;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+62 );
    }//GEN-LAST:event_jIn1Func6ActionPerformed

    private void jIn2Func6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn2Func6ActionPerformed
        if(jIn2Func6.isSelected()) {
            CV[1][64] |= 32;
        } else {
            CV[1][64] &= ~32;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+64 );
    }//GEN-LAST:event_jIn2Func6ActionPerformed

    private void jIn1Func7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn1Func7ActionPerformed
        if(jIn1Func7.isSelected()) {
            CV[1][62] |= 64;
        } else {
            CV[1][62] &= ~64;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+62 );
    }//GEN-LAST:event_jIn1Func7ActionPerformed

    private void jIn2Func7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn2Func7ActionPerformed
        if(jIn2Func7.isSelected()) {
            CV[1][64] |= 64;
        } else {
            CV[1][64] &= ~64;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+64 );
    }//GEN-LAST:event_jIn2Func7ActionPerformed

    private void jIn1Func8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn1Func8ActionPerformed
        if(jIn1Func8.isSelected()) {
            CV[1][62] |= 128;
        } else {
            CV[1][62] &= ~128;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+62 );
    }//GEN-LAST:event_jIn1Func8ActionPerformed

    private void jIn2Func8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn2Func8ActionPerformed
        if(jIn2Func8.isSelected()) {
            CV[1][64] |= 128;
        } else {
            CV[1][64] &= ~128;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+64 );
    }//GEN-LAST:event_jIn2Func8ActionPerformed

    private void jDimmenfFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmenfFocusLost
        CV[1][115] = KTUI.checkTextField( this, jDimmenf, 1, 255, 255, true);
        jCV_Anzeige.setSelectedItem( "CV#"+115 );
    }//GEN-LAST:event_jDimmenfFocusLost

    private void jDimmenfKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDimmenfKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jDimmenfKeyReleased

    private void jDimmenrFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmenrFocusLost
        CV[1][116] = KTUI.checkTextField( this, jDimmenr, 1, 255, 255, true);
        jCV_Anzeige.setSelectedItem( "CV#"+116 );
    }//GEN-LAST:event_jDimmenrFocusLost

    private void jDimmenrKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDimmenrKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jDimmenrKeyReleased

    private void jbDimmFSrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbDimmFSrActionPerformed
        if(jbDimmFS2.isSelected()) {
            CV[1][114] |= 2;
        } else {
            CV[1][114] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+114 );
    }//GEN-LAST:event_jbDimmFSrActionPerformed

    private void jbDimmFSfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbDimmFSfActionPerformed
        if(jbDimmFS1.isSelected()) {
            CV[1][114] |= 1;
        } else {
            CV[1][114] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+114 );
    }//GEN-LAST:event_jbDimmFSfActionPerformed

    private void jDimmFSfFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmFSfFocusLost
        CV[1][123] = KTUI.checkTextField( this, jDimmFSf, 1, 255, 255, true);
        jCV_Anzeige.setSelectedItem( "CV#"+123 );
    }//GEN-LAST:event_jDimmFSfFocusLost

    private void jDimmFSfKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDimmFSfKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jDimmFSfKeyReleased

    private void jDimmFSrFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmFSrFocusLost
        CV[1][124] = KTUI.checkTextField( this, jDimmFSr, 1, 255, 255, true);
        jCV_Anzeige.setSelectedItem( "CV#"+124 );
    }//GEN-LAST:event_jDimmFSrFocusLost

    private void jDimmFSrKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDimmFSrKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jDimmFSrKeyReleased

    private void jDimmen5FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmen5FocusLost
        CV[1][121] = KTUI.checkTextField( this, jDimmen5, 1, 255, 255, true);
        jCV_Anzeige.setSelectedItem( "CV#"+121 );
    }//GEN-LAST:event_jDimmen5FocusLost

    private void jDimmen5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDimmen5KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jDimmen5KeyReleased

    private void jbDimmFS5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbDimmFS5ActionPerformed
        if(jbDimmFS2.isSelected()) {
            CV[1][114] |= 0x40;
        } else {
            CV[1][114] &= ~0x40;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+114 );
    }//GEN-LAST:event_jbDimmFS5ActionPerformed

    private void jbDimmFS6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbDimmFS6ActionPerformed
        if(jbDimmFS2.isSelected()) {
            CV[1][114] |= 0x80;
        } else {
            CV[1][114] &= ~0x80;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+114 );
    }//GEN-LAST:event_jbDimmFS6ActionPerformed

    private void jDimmen6FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmen6FocusLost
        CV[1][122] = KTUI.checkTextField( this, jDimmen6, 1, 255, 255, true);
        jCV_Anzeige.setSelectedItem( "CV#"+122 );
    }//GEN-LAST:event_jDimmen6FocusLost

    private void jDimmen6KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDimmen6KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jDimmen6KeyReleased

    private void jDimmFS5FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmFS5FocusLost
        CV[1][129] = KTUI.checkTextField( this, jDimmFS5, 1, 255, 255, true);
        jCV_Anzeige.setSelectedItem( "CV#"+129 );
    }//GEN-LAST:event_jDimmFS5FocusLost

    private void jDimmFS5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDimmFS5KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jDimmFS5KeyReleased

    private void jDimmFS6FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmFS6FocusLost
        CV[1][130] = KTUI.checkTextField( this, jDimmFS6, 1, 255, 255, true);
        jCV_Anzeige.setSelectedItem( "CV#"+130 );
    }//GEN-LAST:event_jDimmFS6FocusLost

    private void jDimmFS6KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDimmFS6KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jDimmFS6KeyReleased

    private void jKickMotorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jKickMotorFocusLost
        CV[1][161] = KTUI.checkTextField( this, jKickMotor, 0, 255, 10, true);
        jCV_Anzeige.setSelectedItem( "CV#"+161 );
    }//GEN-LAST:event_jKickMotorFocusLost

    private void jKickMotorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jKickMotorKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jKickMotorKeyReleased

    private void jKickFahr1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jKickFahr1ActionPerformed
        if(jKickFahr1.isSelected()) {
            CV[1][160] |= 4;
        } else {
            CV[1][160] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+160 );
    }//GEN-LAST:event_jKickFahr1ActionPerformed

    private void jKickFahr2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jKickFahr2ActionPerformed
        if(jKickFahr2.isSelected()) {
            CV[1][160] |= 8;
        } else {
            CV[1][160] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+160 );
    }//GEN-LAST:event_jKickFahr2ActionPerformed

    private void jKickFahr3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jKickFahr3ActionPerformed
        if(jKickFahr3.isSelected()) {
            CV[1][160] |= 0x10;
        } else {
            CV[1][160] &= ~0x10;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+160 );
    }//GEN-LAST:event_jKickFahr3ActionPerformed

    private void jKickFahr4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jKickFahr4ActionPerformed
        if(jKickFahr4.isSelected()) {
            CV[1][160] |= 0x20;
        } else {
            CV[1][160] &= ~0x20;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+160 );
    }//GEN-LAST:event_jKickFahr4ActionPerformed

    private void jKickFahr5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jKickFahr5ActionPerformed
        if(jKickFahr5.isSelected()) {
            CV[1][160] |= 0x40;
        } else {
            CV[1][160] &= ~0x40;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+160 );
    }//GEN-LAST:event_jKickFahr5ActionPerformed

    private void jKickFahr6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jKickFahr6ActionPerformed
        if(jKickFahr6.isSelected()) {
            CV[1][160] |= 0x80;
        } else {
            CV[1][160] &= ~0x80;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+160 );
    }//GEN-LAST:event_jKickFahr6ActionPerformed

    private void jKickMotorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jKickMotorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jKickMotorActionPerformed

    private void jF1_SPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF1_SPActionPerformed
        jF1_Gl.setSelected(false);
        jF1_SH.setSelected(false);
        if(jF1_SP.isSelected()) {
            CV[1][148] = 16;
        } else {
            CV[1][148] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+148 );
    }//GEN-LAST:event_jF1_SPActionPerformed

    private void jF1_AK2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF1_AK2ActionPerformed
        if(jF1_AK2.isSelected()) {
            CV[1][97] |= 2;
        } else {
            CV[1][97] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+97 );
    }//GEN-LAST:event_jF1_AK2ActionPerformed

    private void jF1_AK3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF1_AK3ActionPerformed
        if(jF1_AK3.isSelected()) {
            CV[1][97] |= 4;
        } else {
            CV[1][97] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+97 );
    }//GEN-LAST:event_jF1_AK3ActionPerformed

    private void jF1_AK4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF1_AK4ActionPerformed
        if(jF1_AK4.isSelected()) {
            CV[1][97] |= 8;
        } else {
            CV[1][97] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+97 );
    }//GEN-LAST:event_jF1_AK4ActionPerformed

    private void jF1_AK5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF1_AK5ActionPerformed
        if(jF1_AK1.isSelected()) {
            CV[1][98] |= 1;
        } else {
            CV[1][98] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+98 );
    }//GEN-LAST:event_jF1_AK5ActionPerformed

    private void jF1_AK6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF1_AK6ActionPerformed
        if(jF1_AK6.isSelected()) {
            CV[1][98] |= 2;
        } else {
            CV[1][98] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+98 );

    }//GEN-LAST:event_jF1_AK6ActionPerformed

    private void jF1_AK7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF1_AK7ActionPerformed
        if(jF1_AK7.isSelected()) {
            CV[1][98] |= 4;
        } else {
            CV[1][98] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+98 );

    }//GEN-LAST:event_jF1_AK7ActionPerformed

    private void jF1_AK8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF1_AK8ActionPerformed
        if(jF1_AK8.isSelected()) {
            CV[1][98] |= 8;
        } else {
            CV[1][98] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+98 );

    }//GEN-LAST:event_jF1_AK8ActionPerformed

    private void jF1_AK9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF1_AK9ActionPerformed
        if(jF1_AK9.isSelected()) {
            CV[1][98] |= 16;
        } else {
            CV[1][98] &= ~16;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+98 );

    }//GEN-LAST:event_jF1_AK9ActionPerformed

    private void jF1_AK10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF1_AK10ActionPerformed
        if(jF1_AK10.isSelected()) {
            CV[1][98] |= 32;
        } else {
            CV[1][98] &= ~32;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+98 );

    }//GEN-LAST:event_jF1_AK10ActionPerformed

    private void jF1_AK11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF1_AK11ActionPerformed
        if(jF1_AK11.isSelected()) {
            CV[1][98] |= 64;
        } else {
            CV[1][98] &= ~64;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+98 );

    }//GEN-LAST:event_jF1_AK11ActionPerformed

    private void jIN1_PendelF12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIN1_PendelF12ActionPerformed
        if(jIN1_PendelF12.isSelected()) {
            CV[1][198] |= 128;
        } else {
            CV[1][198] &= ~128;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+198 );
    }//GEN-LAST:event_jIN1_PendelF12ActionPerformed

    private void jF2_AK1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF2_AK1ActionPerformed
        if(jF2_AK1.isSelected()) {
            CV[1][99] |= 1;
        } else {
            CV[1][99] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+99 );
    }//GEN-LAST:event_jF2_AK1ActionPerformed

    private void jF2_AK2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF2_AK2ActionPerformed
        if(jF2_AK2.isSelected()) {
            CV[1][99] |= 2;
        } else {
            CV[1][99] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+99 );
    }//GEN-LAST:event_jF2_AK2ActionPerformed

    private void jF2_AK3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF2_AK3ActionPerformed
        if(jF2_AK3.isSelected()) {
            CV[1][99] |= 4;
        } else {
            CV[1][99] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+99 );
    }//GEN-LAST:event_jF2_AK3ActionPerformed

    private void jF2_AK4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF2_AK4ActionPerformed
        if(jF2_AK4.isSelected()) {
            CV[1][99] |= 8;
        } else {
            CV[1][99] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+99 );
    }//GEN-LAST:event_jF2_AK4ActionPerformed

    private void jF2_AK5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF2_AK5ActionPerformed
        if(jF2_AK5.isSelected()) {
            CV[1][100] |= 1;
        } else {
            CV[1][100] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+100 );
    }//GEN-LAST:event_jF2_AK5ActionPerformed

    private void jF2_AK6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF2_AK6ActionPerformed
        if(jF2_AK6.isSelected()) {
            CV[1][100] |= 2;
        } else {
            CV[1][100] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+100 );
    }//GEN-LAST:event_jF2_AK6ActionPerformed

    private void jF2_AK7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF2_AK7ActionPerformed
        if(jF2_AK7.isSelected()) {
            CV[1][100] |= 4;
        } else {
            CV[1][100] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+100 );
    }//GEN-LAST:event_jF2_AK7ActionPerformed

    private void jF2_AK8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF2_AK8ActionPerformed
        if(jF2_AK8.isSelected()) {
            CV[1][100] |= 8;
        } else {
            CV[1][100] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+100 );
    }//GEN-LAST:event_jF2_AK8ActionPerformed

    private void jF2_AK9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF2_AK9ActionPerformed
        if(jF2_AK9.isSelected()) {
            CV[1][100] |= 16;
        } else {
            CV[1][100] &= ~16;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+100 );
    }//GEN-LAST:event_jF2_AK9ActionPerformed

    private void jF2_AK10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF2_AK10ActionPerformed
        if(jF2_AK10.isSelected()) {
            CV[1][100] |= 32;
        } else {
            CV[1][100] &= ~32;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+100 );
    }//GEN-LAST:event_jF2_AK10ActionPerformed

    private void jF2_AK11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF2_AK11ActionPerformed
        if(jF2_AK11.isSelected()) {
            CV[1][100] |= 64;
        } else {
            CV[1][100] &= ~64;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+100 );
    }//GEN-LAST:event_jF2_AK11ActionPerformed

    private void jF2_AK12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF2_AK12ActionPerformed
        if(jF2_AK12.isSelected()) {
            CV[1][100] |= 128;
        } else {
            CV[1][100] &= ~128;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+100 );
    }//GEN-LAST:event_jF2_AK12ActionPerformed

    private void jF1_AKfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF1_AKfActionPerformed
        if(jF1_AKf.isSelected()) {
            jF1_AKr.setSelected(false);
            CV[1][97] |= 128;
        } else {
            jF1_AKr.setSelected(true);
            CV[1][97] &= ~128;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+97 );
    }//GEN-LAST:event_jF1_AKfActionPerformed

    private void jF1_AKrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF1_AKrActionPerformed
        if(!jF1_AKr.isSelected()) {
            jF1_AKf.setSelected(true);
            CV[1][97] |= 128;
        } else {
            jF1_AKf.setSelected(false);
            CV[1][97] &= ~128;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+97 );
    }//GEN-LAST:event_jF1_AKrActionPerformed

    private void jAnkuppelMotorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jAnkuppelMotorFocusLost
        CV[1][101] = KTUI.checkTextField( this, jAnkuppelMotor, 1, 255, 16, true);
        jCV_Anzeige.setSelectedItem( "CV#"+101 );
    }//GEN-LAST:event_jAnkuppelMotorFocusLost

    private void jAnkuppelMotorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jAnkuppelMotorKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jAnkuppelMotorKeyReleased

    private void jF2_AKfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF2_AKfActionPerformed
        if(jF2_AKf.isSelected()) {
            jF2_AKr.setSelected(false);
            CV[1][99] |= 128;
        } else {
            jF2_AKr.setSelected(true);
            CV[1][99] &= ~128;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+99 );
    }//GEN-LAST:event_jF2_AKfActionPerformed

    private void jF2_AKrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF2_AKrActionPerformed
        if(!jF2_AKr.isSelected()) {
            jF2_AKf.setSelected(true);
            CV[1][99] |= 128;
        } else {
            jF2_AKf.setSelected(false);
            CV[1][99] &= ~128;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+99 );
    }//GEN-LAST:event_jF2_AKrActionPerformed

    private void jServoPOMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jServoPOMActionPerformed
        if(!jServoPOM.isSelected()) {
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
        jCV_Anzeige.setSelectedItem( "CV#"+168 );
    }//GEN-LAST:event_jServoPOMActionPerformed

    private void jServoTauschActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jServoTauschActionPerformed
        if(jServoTausch.isSelected())
        {
            CV[1][164] = 255;
        }
        else
        {
            CV[1][164] = 0;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+164 );
    }//GEN-LAST:event_jServoTauschActionPerformed

    private void jIN1_PendelF5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIN1_PendelF5ActionPerformed
        if(jIN1_PendelF5.isSelected()) {
            CV[1][198] |= 1;
        } else {
            CV[1][198] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+198 );
    }//GEN-LAST:event_jIN1_PendelF5ActionPerformed

    private void jIN1_PendelF6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIN1_PendelF6ActionPerformed
        if(jIN1_PendelF6.isSelected()) {
            CV[1][198] |= 2;
        } else {
            CV[1][198] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+198 );
    }//GEN-LAST:event_jIN1_PendelF6ActionPerformed

    private void jIN1_PendelF7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIN1_PendelF7ActionPerformed
        if(jIN1_PendelF7.isSelected()) {
            CV[1][198] |= 4;
        } else {
            CV[1][198] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+198 );
    }//GEN-LAST:event_jIN1_PendelF7ActionPerformed

    private void jIN1_PendelF8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIN1_PendelF8ActionPerformed
        if(jIN1_PendelF8.isSelected()) {
            CV[1][198] |= 8;
        } else {
            CV[1][198] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+198 );
    }//GEN-LAST:event_jIN1_PendelF8ActionPerformed

    private void jIN1_PendelF9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIN1_PendelF9ActionPerformed
        if(jIN1_PendelF9.isSelected()) {
            CV[1][198] |= 16;
        } else {
            CV[1][198] &= ~16;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+198 );
    }//GEN-LAST:event_jIN1_PendelF9ActionPerformed

    private void jIN1_PendelF10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIN1_PendelF10ActionPerformed
        if(jIN1_PendelF10.isSelected()) {
            CV[1][198] |= 32;
        } else {
            CV[1][198] &= ~32;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+198 );
    }//GEN-LAST:event_jIN1_PendelF10ActionPerformed

    private void jIN1_PendelF11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIN1_PendelF11ActionPerformed
        if(jIN1_PendelF11.isSelected()) {
            CV[1][198] |= 64;
        } else {
            CV[1][198] &= ~64;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+198 );
    }//GEN-LAST:event_jIN1_PendelF11ActionPerformed

    private void jF1_AK12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF1_AK12ActionPerformed
        if(jF1_AK12.isSelected()) {
            CV[1][98] |= 128;
        } else {
            CV[1][98] &= ~128;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+98 );
    }//GEN-LAST:event_jF1_AK12ActionPerformed

    private void jIN1_PendelfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIN1_PendelfActionPerformed
        if(jIN1_Pendelf.isSelected()) {
            CV[1][196] &= ~64;
            if(jIN1_Pendelr.isSelected())
            {
                CV[1][196] |= 32;
            }
            else
            {
                CV[1][196] &= ~32;
            }
        } else {
            CV[1][196] &= ~32;
            if(jIN1_Pendelr.isSelected())
            {
                CV[1][196] |= 64;
            }
            else
            {
                CV[1][196] &= ~64;
            }
        }
        jCV_Anzeige.setSelectedItem( "CV#"+196 );
    }//GEN-LAST:event_jIN1_PendelfActionPerformed

    private void jIN1_PendelrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIN1_PendelrActionPerformed
        if(jIN1_Pendelr.isSelected()) {
            CV[1][196] |= 64;
            if(jIN1_Pendelf.isSelected())
            {
                CV[1][196] |= 32;
            }
            else
            {
                CV[1][196] &= ~32;
            }
        } else {
            CV[1][196] &= ~96;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+196 );
    }//GEN-LAST:event_jIN1_PendelrActionPerformed

    private void jIN2_PendelfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIN2_PendelfActionPerformed
        if(jIN2_Pendelf.isSelected()) {
            CV[1][197] &= ~64;
            if(jIN2_Pendelr.isSelected())
            {
                CV[1][197] |= 32;
            }
            else
            {
                CV[1][197] &= ~32;
            }
        } else {
            CV[1][196] &= ~32;
            if(jIN2_Pendelr.isSelected())
            {
                CV[1][197] |= 64;
            }
            else
            {
                CV[1][197] &= ~64;
            }
        }
        jCV_Anzeige.setSelectedItem( "CV#"+197 );
    }//GEN-LAST:event_jIN2_PendelfActionPerformed

    private void jIN2_PendelrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIN2_PendelrActionPerformed
        if(jIN2_Pendelr.isSelected()) {
            CV[1][197] |= 64;
            if(jIN2_Pendelf.isSelected())
            {
                CV[1][197] |= 32;
            }
            else
            {
                CV[1][197] &= ~32;
            }
        } else {
            CV[1][197] &= ~96;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+197 );
    }//GEN-LAST:event_jIN2_PendelrActionPerformed

    private void jIN1_PendelRWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIN1_PendelRWActionPerformed
        if(jIN1_PendelRW.isSelected()) {
            CV[1][196] |= 128;
        } else {
            CV[1][196] &= ~128;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+196 );
    }//GEN-LAST:event_jIN1_PendelRWActionPerformed

    private void jIN2_PendelRWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIN2_PendelRWActionPerformed
        if(jIN2_PendelRW.isSelected()) {
            CV[1][197] |= 128;
        } else {
            CV[1][197] &= ~128;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+197 );
    }//GEN-LAST:event_jIN2_PendelRWActionPerformed

    private void jIN1_PendelHaltZeitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIN1_PendelHaltZeitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jIN1_PendelHaltZeitActionPerformed

    private void jIN1_PendelHaltZeitFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jIN1_PendelHaltZeitFocusLost
        int j = KTUI.checkTextField( this, jIN1_PendelHaltZeit, 0, 300, 0, true);
        jCV_Anzeige.setSelectedItem( "CV#"+196 );

        CV[1][196] &= 0xE0;
        if(j > 75)
        {
            CV[1][196] |= 0x10;
            j /= 4;
        }
        j -= j%5;
        int i = (j%10)/5;
        j -= j%10;
        if(j >= 40)
        {
            j -= 40;
            i += 8;
        }
        if(j >= 20)
        {
            j -= 20;
            i += 4;
        }
        if(j >= 10)
        {
            i += 2;
        }
        j = (i & 1)*5 + (i & 2)*10 + (i & 4)*20 + (i & 8)*40;
        if((i & 16)  == 16)
        {
            j *= 4;
        }
        jIN1_PendelHaltZeit.setText("" + j);
            
        CV[1][196] += i;
        jCV_Anzeige.setSelectedItem( "CV#"+196 );
    }//GEN-LAST:event_jIN1_PendelHaltZeitFocusLost

    private void jIN1_PendelHaltZeitKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jIN1_PendelHaltZeitKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jIN1_PendelHaltZeitKeyReleased

    private void jIN2_PendelHaltZeitFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jIN2_PendelHaltZeitFocusLost
        int j = KTUI.checkTextField( this, jIN2_PendelHaltZeit, 0, 300, 0, true);
        jCV_Anzeige.setSelectedItem( "CV#"+197 );

        CV[1][197] &= 0xE0;
        if(j > 75)
        {
            CV[1][197] |= 0x10;
            j /= 4;
        }
        j -= j%5;
        int i = (j%10)/5;
        j -= j%10;
        if(j >= 40)
        {
            j -= 40;
            i += 8;
        }
        if(j >= 20)
        {
            j -= 20;
            i += 4;
        }
        if(j >= 10)
        {
            i += 2;
        }
        j = (i & 1)*5 + (i & 2)*10 + (i & 4)*20 + (i & 8)*40;
        if((i & 16)  == 16)
        {
            j *= 4;
        }
        jIN2_PendelHaltZeit.setText("" + j);
            
        CV[1][197] += i;
        jCV_Anzeige.setSelectedItem( "CV#"+197 );
    }//GEN-LAST:event_jIN2_PendelHaltZeitFocusLost

    private void jIN2_PendelHaltZeitKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jIN2_PendelHaltZeitKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jIN2_PendelHaltZeitKeyReleased

    private void jAnalogRW_WActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAnalogRW_WActionPerformed
        CV[1][12] = 0;
        jCV_Anzeige.setSelectedItem( "CV#"+12 );
    }//GEN-LAST:event_jAnalogRW_WActionPerformed

    private void jAnalogRW_GActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAnalogRW_GActionPerformed
        CV[1][12] = 1;
        jCV_Anzeige.setSelectedItem( "CV#"+12 );
    }//GEN-LAST:event_jAnalogRW_GActionPerformed

    private void jUmschaltEmpfFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jUmschaltEmpfFocusLost
        CV[1][199] = KTUI.checkTextField( this, jUmschaltEmpf, 0, 255, 100, true);
        jCV_Anzeige.setSelectedItem( "CV#"+199 );
    }//GEN-LAST:event_jUmschaltEmpfFocusLost

    private void jUmschaltEmpfKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jUmschaltEmpfKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus(); 
        }
    }//GEN-LAST:event_jUmschaltEmpfKeyReleased

    private void jRCplusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRCplusActionPerformed
        if(jRCplus.isSelected()) {
            CV[1][28] |= 128;
        } else {
            CV[1][28] &= ~128;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+28 );
    }//GEN-LAST:event_jRCplusActionPerformed

    private void jAbbrechenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAbbrechenActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jAbbrechenActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        updateTabs();
    }//GEN-LAST:event_formWindowActivated

    private void jDecoderAdresseFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDecoderAdresseFocusGained
        if (jKurzeAdr.isSelected()) {
            jCV_Anzeige.setSelectedItem( "CV#"+1 );
        } else {
            jCV_Anzeige.setSelectedItem( "CV#"+17 );
        }
    }//GEN-LAST:event_jDecoderAdresseFocusGained

    private void jMM_Addr_2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jMM_Addr_2FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+47 );
    }//GEN-LAST:event_jMM_Addr_2FocusGained

    private void jDecoderAdresse1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDecoderAdresse1FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+19 );
    }//GEN-LAST:event_jDecoderAdresse1FocusGained

    private void jBlink_Einschaltzeit_fFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlink_Einschaltzeit_fFocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+132 );
    }//GEN-LAST:event_jBlink_Einschaltzeit_fFocusGained

    private void jBlink_Einschaltzeit_rFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlink_Einschaltzeit_rFocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+133 );
    }//GEN-LAST:event_jBlink_Einschaltzeit_rFocusGained

    private void jBlink_Einschaltzeit_1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlink_Einschaltzeit_1FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+134 );
    }//GEN-LAST:event_jBlink_Einschaltzeit_1FocusGained

    private void jBlink_Einschaltzeit_2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlink_Einschaltzeit_2FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+135 );
    }//GEN-LAST:event_jBlink_Einschaltzeit_2FocusGained

    private void jBlink_Einschaltzeit_3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlink_Einschaltzeit_3FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+136 );
    }//GEN-LAST:event_jBlink_Einschaltzeit_3FocusGained

    private void jBlink_Einschaltzeit_4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlink_Einschaltzeit_4FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+137 );
    }//GEN-LAST:event_jBlink_Einschaltzeit_4FocusGained

    private void jBlink_Einschaltzeit_5FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlink_Einschaltzeit_5FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+138 );
    }//GEN-LAST:event_jBlink_Einschaltzeit_5FocusGained

    private void jBlink_Einschaltzeit_6FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlink_Einschaltzeit_6FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+139 );
    }//GEN-LAST:event_jBlink_Einschaltzeit_6FocusGained

    private void jBlink_Pausezeit_fFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlink_Pausezeit_fFocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+140 );
    }//GEN-LAST:event_jBlink_Pausezeit_fFocusGained

    private void jBlink_Pausezeit_rFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlink_Pausezeit_rFocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+141 );
    }//GEN-LAST:event_jBlink_Pausezeit_rFocusGained

    private void jBlink_Pausezeit_1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlink_Pausezeit_1FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+142 );
    }//GEN-LAST:event_jBlink_Pausezeit_1FocusGained

    private void jBlink_Pausezeit_2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlink_Pausezeit_2FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+143 );
    }//GEN-LAST:event_jBlink_Pausezeit_2FocusGained

    private void jBlink_Pausezeit_3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlink_Pausezeit_3FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+144 );
    }//GEN-LAST:event_jBlink_Pausezeit_3FocusGained

    private void jBlink_Pausezeit_4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlink_Pausezeit_4FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+145 );
    }//GEN-LAST:event_jBlink_Pausezeit_4FocusGained

    private void jBlink_Pausezeit_5FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlink_Pausezeit_5FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+146 );
    }//GEN-LAST:event_jBlink_Pausezeit_5FocusGained

    private void jBlink_Pausezeit_6FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlink_Pausezeit_6FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+147 );
    }//GEN-LAST:event_jBlink_Pausezeit_6FocusGained

    private void jBlinkfrequenzfFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlinkfrequenzfFocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+104 );
    }//GEN-LAST:event_jBlinkfrequenzfFocusGained

    private void jBlinkfrequenzrFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlinkfrequenzrFocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+105 );
    }//GEN-LAST:event_jBlinkfrequenzrFocusGained

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

    private void jDimmenfFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmenfFocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+115 );
    }//GEN-LAST:event_jDimmenfFocusGained

    private void jDimmenrFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmenrFocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+116 );
    }//GEN-LAST:event_jDimmenrFocusGained

    private void jDimmen1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmen1FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+117 );
    }//GEN-LAST:event_jDimmen1FocusGained

    private void jDimmen2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmen2FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+118 );
    }//GEN-LAST:event_jDimmen2FocusGained

    private void jDimmen3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmen3FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+119 );
    }//GEN-LAST:event_jDimmen3FocusGained

    private void jDimmen4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmen4FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+120 );
    }//GEN-LAST:event_jDimmen4FocusGained

    private void jDimmen5FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmen5FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+121 );
    }//GEN-LAST:event_jDimmen5FocusGained

    private void jDimmen6FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmen6FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+122 );
    }//GEN-LAST:event_jDimmen6FocusGained

    private void jDimmFSfFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmFSfFocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+123 );
    }//GEN-LAST:event_jDimmFSfFocusGained

    private void jDimmFSrFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmFSrFocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+124 );
    }//GEN-LAST:event_jDimmFSrFocusGained

    private void jDimmFS1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmFS1FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+125 );
    }//GEN-LAST:event_jDimmFS1FocusGained

    private void jDimmFS2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmFS2FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+126 );
    }//GEN-LAST:event_jDimmFS2FocusGained

    private void jDimmFS3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmFS3FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+127 );
    }//GEN-LAST:event_jDimmFS3FocusGained

    private void jDimmFS4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmFS4FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+128 );
    }//GEN-LAST:event_jDimmFS4FocusGained

    private void jDimmFS5FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmFS5FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+129 );
    }//GEN-LAST:event_jDimmFS5FocusGained

    private void jDimmFS6FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmFS6FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+130 );
    }//GEN-LAST:event_jDimmFS6FocusGained

    private void jDimm_FSFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimm_FSFocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+113 );
    }//GEN-LAST:event_jDimm_FSFocusGained

    private void jKickMotorFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jKickMotorFocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+161 );
    }//GEN-LAST:event_jKickMotorFocusGained

    private void jKickvorFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jKickvorFocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+162 );
    }//GEN-LAST:event_jKickvorFocusGained

    private void jKickrueckFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jKickrueckFocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+163 );
    }//GEN-LAST:event_jKickrueckFocusGained

    private void jMindestSchlt1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jMindestSchlt1FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+177 );
    }//GEN-LAST:event_jMindestSchlt1FocusGained

    private void jMindestSchlt2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jMindestSchlt2FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+178 );
    }//GEN-LAST:event_jMindestSchlt2FocusGained

    private void jIN1_PendelHaltZeitFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jIN1_PendelHaltZeitFocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+196 );
    }//GEN-LAST:event_jIN1_PendelHaltZeitFocusGained

    private void jIN2_PendelHaltZeitFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jIN2_PendelHaltZeitFocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+197 );
    }//GEN-LAST:event_jIN2_PendelHaltZeitFocusGained

    private void jAnkuppelMotorFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jAnkuppelMotorFocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+101 );
    }//GEN-LAST:event_jAnkuppelMotorFocusGained

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

    private void jUmschaltEmpfFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jUmschaltEmpfFocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+199 );
    }//GEN-LAST:event_jUmschaltEmpfFocusGained

    void filfilCVs() {
        String[] keys = null;
        if( KTUI.Decoder != c.LD_G36Plus ) {
            keys = new String[] {"LD-G-30plus"};
        } else {
            keys = new String[] {"LD-G-36plus"};
        }
        Boolean b = parseString2CVs.convertString2CV( ReturnString, keys, CV, jComment, KTUI );
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
    private javax.swing.JButton jAbbrechen;
    private javax.swing.JPanel jAnalog;
    private javax.swing.JCheckBox jAnalog1;
    private javax.swing.JCheckBox jAnalog3;
    private javax.swing.JRadioButton jAnalogRW_G;
    private javax.swing.JRadioButton jAnalogRW_W;
    private javax.swing.JTextField jAnkuppelMotor;
    private javax.swing.JCheckBox jAuxInv1;
    private javax.swing.JCheckBox jAuxInv2;
    private javax.swing.JCheckBox jAuxInv3;
    private javax.swing.JCheckBox jAuxInv4;
    private javax.swing.JCheckBox jAuxInv5;
    private javax.swing.JCheckBox jAuxInv6;
    private javax.swing.JCheckBox jAuxInvf;
    private javax.swing.JCheckBox jAuxInvr;
    private javax.swing.JLabel jBild;
    private javax.swing.JLabel jBild1;
    private javax.swing.JCheckBox jBl_Inv1;
    private javax.swing.JCheckBox jBl_Inv2;
    private javax.swing.JCheckBox jBl_Inv3;
    private javax.swing.JCheckBox jBl_Inv4;
    private javax.swing.JCheckBox jBl_Inv5;
    private javax.swing.JCheckBox jBl_Inv6;
    private javax.swing.JCheckBox jBl_Invf;
    private javax.swing.JCheckBox jBl_Invr;
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
    private javax.swing.JTextField jBlink_Einschaltzeit_f;
    private javax.swing.JTextField jBlink_Einschaltzeit_r;
    private javax.swing.JTextField jBlink_Pausezeit_1;
    private javax.swing.JTextField jBlink_Pausezeit_2;
    private javax.swing.JTextField jBlink_Pausezeit_3;
    private javax.swing.JTextField jBlink_Pausezeit_4;
    private javax.swing.JTextField jBlink_Pausezeit_5;
    private javax.swing.JTextField jBlink_Pausezeit_6;
    private javax.swing.JTextField jBlink_Pausezeit_f;
    private javax.swing.JTextField jBlink_Pausezeit_r;
    private javax.swing.JCheckBox jBlinkf;
    private javax.swing.JTextField jBlinkfrequenz1;
    private javax.swing.JTextField jBlinkfrequenz2;
    private javax.swing.JTextField jBlinkfrequenz3;
    private javax.swing.JTextField jBlinkfrequenz4;
    private javax.swing.JTextField jBlinkfrequenz5;
    private javax.swing.JTextField jBlinkfrequenz6;
    private javax.swing.JTextField jBlinkfrequenzMars;
    private javax.swing.JTextField jBlinkfrequenzf;
    private javax.swing.JTextField jBlinkfrequenzr;
    private javax.swing.JCheckBox jBlinkr;
    private javax.swing.JCheckBox jBroadCasst;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jCV29;
    private javax.swing.JComboBox jCV_Anzeige;
    private javax.swing.JTextField jCV_Inhalt;
    private javax.swing.JButton jCV_LesenSchreiben;
    private javax.swing.JCheckBox jChannel2;
    private javax.swing.JTextArea jComment;
    private javax.swing.JTextField jDecoderAdresse;
    private javax.swing.JTextField jDecoderAdresse1;
    private javax.swing.JTabbedPane jDecodereigenschaften;
    private javax.swing.JTextField jDimmFS1;
    private javax.swing.JTextField jDimmFS2;
    private javax.swing.JTextField jDimmFS3;
    private javax.swing.JTextField jDimmFS4;
    private javax.swing.JTextField jDimmFS5;
    private javax.swing.JTextField jDimmFS6;
    private javax.swing.JTextField jDimmFSf;
    private javax.swing.JTextField jDimmFSr;
    private javax.swing.JTextField jDimm_FS;
    private javax.swing.JTextField jDimmen1;
    private javax.swing.JTextField jDimmen2;
    private javax.swing.JTextField jDimmen3;
    private javax.swing.JTextField jDimmen4;
    private javax.swing.JTextField jDimmen5;
    private javax.swing.JTextField jDimmen6;
    private javax.swing.JTextField jDimmenf;
    private javax.swing.JTextField jDimmenr;
    private javax.swing.JToggleButton jDirekteingabe;
    private javax.swing.JCheckBox jDoppelBlink1;
    private javax.swing.JCheckBox jDoppelBlink2;
    private javax.swing.JCheckBox jDoppelBlink3;
    private javax.swing.JCheckBox jDoppelBlink4;
    private javax.swing.JCheckBox jDoppelBlink5;
    private javax.swing.JCheckBox jDoppelBlink6;
    private javax.swing.JCheckBox jDoppelBlinkf;
    private javax.swing.JCheckBox jDoppelBlinkr;
    private javax.swing.JPanel jEffekte_1;
    private javax.swing.JPanel jEffekte_2;
    private javax.swing.JCheckBox jF1;
    private javax.swing.JCheckBox jF10_1;
    private javax.swing.JCheckBox jF10_2;
    private javax.swing.JCheckBox jF10_3;
    private javax.swing.JCheckBox jF10_4;
    private javax.swing.JCheckBox jF10_5;
    private javax.swing.JCheckBox jF10_6;
    private javax.swing.JCheckBox jF10_7;
    private javax.swing.JCheckBox jF10_8;
    private javax.swing.JCheckBox jF10_Gl;
    private javax.swing.JCheckBox jF10_SH;
    private javax.swing.JCheckBox jF10_SP;
    private javax.swing.JCheckBox jF11_1;
    private javax.swing.JCheckBox jF11_2;
    private javax.swing.JCheckBox jF11_3;
    private javax.swing.JCheckBox jF11_4;
    private javax.swing.JCheckBox jF11_5;
    private javax.swing.JCheckBox jF11_6;
    private javax.swing.JCheckBox jF11_7;
    private javax.swing.JCheckBox jF11_8;
    private javax.swing.JCheckBox jF11_Gl;
    private javax.swing.JCheckBox jF11_SH;
    private javax.swing.JCheckBox jF11_SP;
    private javax.swing.JCheckBox jF12_1;
    private javax.swing.JCheckBox jF12_2;
    private javax.swing.JCheckBox jF12_3;
    private javax.swing.JCheckBox jF12_4;
    private javax.swing.JCheckBox jF12_5;
    private javax.swing.JCheckBox jF12_6;
    private javax.swing.JCheckBox jF12_7;
    private javax.swing.JCheckBox jF12_8;
    private javax.swing.JCheckBox jF12_Gl;
    private javax.swing.JCheckBox jF12_SH;
    private javax.swing.JCheckBox jF12_SP;
    private javax.swing.JCheckBox jF13_1;
    private javax.swing.JCheckBox jF13_2;
    private javax.swing.JCheckBox jF13_3;
    private javax.swing.JCheckBox jF13_4;
    private javax.swing.JCheckBox jF13_5;
    private javax.swing.JCheckBox jF13_6;
    private javax.swing.JCheckBox jF13_7;
    private javax.swing.JCheckBox jF14_1;
    private javax.swing.JCheckBox jF14_2;
    private javax.swing.JCheckBox jF14_3;
    private javax.swing.JCheckBox jF14_4;
    private javax.swing.JCheckBox jF14_5;
    private javax.swing.JCheckBox jF14_6;
    private javax.swing.JCheckBox jF14_7;
    private javax.swing.JCheckBox jF15_1;
    private javax.swing.JCheckBox jF15_2;
    private javax.swing.JCheckBox jF15_3;
    private javax.swing.JCheckBox jF15_4;
    private javax.swing.JCheckBox jF15_5;
    private javax.swing.JCheckBox jF15_6;
    private javax.swing.JCheckBox jF15_7;
    private javax.swing.JCheckBox jF16_1;
    private javax.swing.JCheckBox jF16_2;
    private javax.swing.JCheckBox jF16_3;
    private javax.swing.JCheckBox jF16_4;
    private javax.swing.JCheckBox jF16_5;
    private javax.swing.JCheckBox jF16_6;
    private javax.swing.JCheckBox jF16_7;
    private javax.swing.JCheckBox jF17_1;
    private javax.swing.JCheckBox jF17_2;
    private javax.swing.JCheckBox jF17_3;
    private javax.swing.JCheckBox jF17_4;
    private javax.swing.JCheckBox jF17_5;
    private javax.swing.JCheckBox jF17_6;
    private javax.swing.JCheckBox jF17_7;
    private javax.swing.JCheckBox jF18_1;
    private javax.swing.JCheckBox jF18_2;
    private javax.swing.JCheckBox jF18_3;
    private javax.swing.JCheckBox jF18_4;
    private javax.swing.JCheckBox jF18_5;
    private javax.swing.JCheckBox jF18_6;
    private javax.swing.JCheckBox jF18_7;
    private javax.swing.JCheckBox jF19_1;
    private javax.swing.JCheckBox jF19_2;
    private javax.swing.JCheckBox jF19_3;
    private javax.swing.JCheckBox jF19_4;
    private javax.swing.JCheckBox jF19_5;
    private javax.swing.JCheckBox jF19_6;
    private javax.swing.JCheckBox jF19_7;
    private javax.swing.JCheckBox jF1_1;
    private javax.swing.JCheckBox jF1_2;
    private javax.swing.JCheckBox jF1_3;
    private javax.swing.JCheckBox jF1_4;
    private javax.swing.JCheckBox jF1_5;
    private javax.swing.JCheckBox jF1_6;
    private javax.swing.JCheckBox jF1_7;
    private javax.swing.JCheckBox jF1_8;
    private javax.swing.JCheckBox jF1_AK1;
    private javax.swing.JCheckBox jF1_AK10;
    private javax.swing.JCheckBox jF1_AK11;
    private javax.swing.JCheckBox jF1_AK12;
    private javax.swing.JCheckBox jF1_AK2;
    private javax.swing.JCheckBox jF1_AK3;
    private javax.swing.JCheckBox jF1_AK4;
    private javax.swing.JCheckBox jF1_AK5;
    private javax.swing.JCheckBox jF1_AK6;
    private javax.swing.JCheckBox jF1_AK7;
    private javax.swing.JCheckBox jF1_AK8;
    private javax.swing.JCheckBox jF1_AK9;
    private javax.swing.JCheckBox jF1_AKf;
    private javax.swing.JCheckBox jF1_AKr;
    private javax.swing.JCheckBox jF1_Gl;
    private javax.swing.JCheckBox jF1_SH;
    private javax.swing.JCheckBox jF1_SP;
    private javax.swing.JCheckBox jF2;
    private javax.swing.JCheckBox jF20_1;
    private javax.swing.JCheckBox jF20_2;
    private javax.swing.JCheckBox jF20_3;
    private javax.swing.JCheckBox jF20_4;
    private javax.swing.JCheckBox jF20_5;
    private javax.swing.JCheckBox jF20_6;
    private javax.swing.JCheckBox jF20_7;
    private javax.swing.JCheckBox jF21_1;
    private javax.swing.JCheckBox jF21_2;
    private javax.swing.JCheckBox jF21_3;
    private javax.swing.JCheckBox jF21_4;
    private javax.swing.JCheckBox jF21_5;
    private javax.swing.JCheckBox jF21_6;
    private javax.swing.JCheckBox jF21_7;
    private javax.swing.JCheckBox jF22_1;
    private javax.swing.JCheckBox jF22_2;
    private javax.swing.JCheckBox jF22_3;
    private javax.swing.JCheckBox jF22_4;
    private javax.swing.JCheckBox jF22_5;
    private javax.swing.JCheckBox jF22_6;
    private javax.swing.JCheckBox jF22_7;
    private javax.swing.JCheckBox jF23_1;
    private javax.swing.JCheckBox jF23_2;
    private javax.swing.JCheckBox jF23_3;
    private javax.swing.JCheckBox jF23_4;
    private javax.swing.JCheckBox jF23_5;
    private javax.swing.JCheckBox jF23_6;
    private javax.swing.JCheckBox jF23_7;
    private javax.swing.JCheckBox jF24_1;
    private javax.swing.JCheckBox jF24_2;
    private javax.swing.JCheckBox jF24_3;
    private javax.swing.JCheckBox jF24_4;
    private javax.swing.JCheckBox jF24_5;
    private javax.swing.JCheckBox jF24_6;
    private javax.swing.JCheckBox jF24_7;
    private javax.swing.JCheckBox jF25_1;
    private javax.swing.JCheckBox jF25_2;
    private javax.swing.JCheckBox jF25_3;
    private javax.swing.JCheckBox jF25_4;
    private javax.swing.JCheckBox jF25_5;
    private javax.swing.JCheckBox jF25_6;
    private javax.swing.JCheckBox jF25_7;
    private javax.swing.JCheckBox jF26_1;
    private javax.swing.JCheckBox jF26_2;
    private javax.swing.JCheckBox jF26_3;
    private javax.swing.JCheckBox jF26_4;
    private javax.swing.JCheckBox jF26_5;
    private javax.swing.JCheckBox jF26_6;
    private javax.swing.JCheckBox jF26_7;
    private javax.swing.JCheckBox jF27_1;
    private javax.swing.JCheckBox jF27_2;
    private javax.swing.JCheckBox jF27_3;
    private javax.swing.JCheckBox jF27_4;
    private javax.swing.JCheckBox jF27_5;
    private javax.swing.JCheckBox jF27_6;
    private javax.swing.JCheckBox jF27_7;
    private javax.swing.JCheckBox jF28_1;
    private javax.swing.JCheckBox jF28_2;
    private javax.swing.JCheckBox jF28_3;
    private javax.swing.JCheckBox jF28_4;
    private javax.swing.JCheckBox jF28_5;
    private javax.swing.JCheckBox jF28_6;
    private javax.swing.JCheckBox jF28_7;
    private javax.swing.JCheckBox jF2_1;
    private javax.swing.JCheckBox jF2_2;
    private javax.swing.JCheckBox jF2_3;
    private javax.swing.JCheckBox jF2_4;
    private javax.swing.JCheckBox jF2_5;
    private javax.swing.JCheckBox jF2_6;
    private javax.swing.JCheckBox jF2_7;
    private javax.swing.JCheckBox jF2_8;
    private javax.swing.JCheckBox jF2_AK1;
    private javax.swing.JCheckBox jF2_AK10;
    private javax.swing.JCheckBox jF2_AK11;
    private javax.swing.JCheckBox jF2_AK12;
    private javax.swing.JCheckBox jF2_AK2;
    private javax.swing.JCheckBox jF2_AK3;
    private javax.swing.JCheckBox jF2_AK4;
    private javax.swing.JCheckBox jF2_AK5;
    private javax.swing.JCheckBox jF2_AK6;
    private javax.swing.JCheckBox jF2_AK7;
    private javax.swing.JCheckBox jF2_AK8;
    private javax.swing.JCheckBox jF2_AK9;
    private javax.swing.JCheckBox jF2_AKf;
    private javax.swing.JCheckBox jF2_AKr;
    private javax.swing.JCheckBox jF2_Gl;
    private javax.swing.JCheckBox jF2_SH;
    private javax.swing.JCheckBox jF2_SP;
    private javax.swing.JCheckBox jF3;
    private javax.swing.JCheckBox jF3_1;
    private javax.swing.JCheckBox jF3_2;
    private javax.swing.JCheckBox jF3_3;
    private javax.swing.JCheckBox jF3_4;
    private javax.swing.JCheckBox jF3_5;
    private javax.swing.JCheckBox jF3_6;
    private javax.swing.JCheckBox jF3_7;
    private javax.swing.JCheckBox jF3_8;
    private javax.swing.JCheckBox jF3_Gl;
    private javax.swing.JCheckBox jF3_SH;
    private javax.swing.JCheckBox jF3_SP;
    private javax.swing.JCheckBox jF4;
    private javax.swing.JCheckBox jF4_1;
    private javax.swing.JCheckBox jF4_2;
    private javax.swing.JCheckBox jF4_3;
    private javax.swing.JCheckBox jF4_4;
    private javax.swing.JCheckBox jF4_5;
    private javax.swing.JCheckBox jF4_6;
    private javax.swing.JCheckBox jF4_7;
    private javax.swing.JCheckBox jF4_8;
    private javax.swing.JCheckBox jF4_Gl;
    private javax.swing.JCheckBox jF4_SH;
    private javax.swing.JCheckBox jF4_SP;
    private javax.swing.JCheckBox jF5;
    private javax.swing.JCheckBox jF5_1;
    private javax.swing.JCheckBox jF5_2;
    private javax.swing.JCheckBox jF5_3;
    private javax.swing.JCheckBox jF5_4;
    private javax.swing.JCheckBox jF5_5;
    private javax.swing.JCheckBox jF5_6;
    private javax.swing.JCheckBox jF5_7;
    private javax.swing.JCheckBox jF5_8;
    private javax.swing.JCheckBox jF5_Gl;
    private javax.swing.JCheckBox jF5_SH;
    private javax.swing.JCheckBox jF5_SP;
    private javax.swing.JCheckBox jF6;
    private javax.swing.JCheckBox jF6_1;
    private javax.swing.JCheckBox jF6_2;
    private javax.swing.JCheckBox jF6_3;
    private javax.swing.JCheckBox jF6_4;
    private javax.swing.JCheckBox jF6_5;
    private javax.swing.JCheckBox jF6_6;
    private javax.swing.JCheckBox jF6_7;
    private javax.swing.JCheckBox jF6_8;
    private javax.swing.JCheckBox jF6_Gl;
    private javax.swing.JCheckBox jF6_SH;
    private javax.swing.JCheckBox jF6_SP;
    private javax.swing.JCheckBox jF7;
    private javax.swing.JCheckBox jF7_1;
    private javax.swing.JCheckBox jF7_2;
    private javax.swing.JCheckBox jF7_3;
    private javax.swing.JCheckBox jF7_4;
    private javax.swing.JCheckBox jF7_5;
    private javax.swing.JCheckBox jF7_6;
    private javax.swing.JCheckBox jF7_7;
    private javax.swing.JCheckBox jF7_8;
    private javax.swing.JCheckBox jF7_Gl;
    private javax.swing.JCheckBox jF7_SH;
    private javax.swing.JCheckBox jF7_SP;
    private javax.swing.JCheckBox jF8;
    private javax.swing.JCheckBox jF8_1;
    private javax.swing.JCheckBox jF8_2;
    private javax.swing.JCheckBox jF8_3;
    private javax.swing.JCheckBox jF8_4;
    private javax.swing.JCheckBox jF8_5;
    private javax.swing.JCheckBox jF8_6;
    private javax.swing.JCheckBox jF8_7;
    private javax.swing.JCheckBox jF8_8;
    private javax.swing.JCheckBox jF8_Gl;
    private javax.swing.JCheckBox jF8_SH;
    private javax.swing.JCheckBox jF8_SP;
    private javax.swing.JCheckBox jF9_1;
    private javax.swing.JCheckBox jF9_2;
    private javax.swing.JCheckBox jF9_3;
    private javax.swing.JCheckBox jF9_4;
    private javax.swing.JCheckBox jF9_5;
    private javax.swing.JCheckBox jF9_6;
    private javax.swing.JCheckBox jF9_7;
    private javax.swing.JCheckBox jF9_8;
    private javax.swing.JCheckBox jF9_Gl;
    private javax.swing.JCheckBox jF9_SH;
    private javax.swing.JCheckBox jF9_SP;
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
    private javax.swing.JCheckBox jIN1_PendelF10;
    private javax.swing.JCheckBox jIN1_PendelF11;
    private javax.swing.JCheckBox jIN1_PendelF12;
    private javax.swing.JCheckBox jIN1_PendelF5;
    private javax.swing.JCheckBox jIN1_PendelF6;
    private javax.swing.JCheckBox jIN1_PendelF7;
    private javax.swing.JCheckBox jIN1_PendelF8;
    private javax.swing.JCheckBox jIN1_PendelF9;
    private javax.swing.JTextField jIN1_PendelHaltZeit;
    private javax.swing.JCheckBox jIN1_PendelRW;
    private javax.swing.JCheckBox jIN1_Pendelf;
    private javax.swing.JCheckBox jIN1_Pendelr;
    private javax.swing.JTextField jIN2_PendelHaltZeit;
    private javax.swing.JCheckBox jIN2_PendelRW;
    private javax.swing.JCheckBox jIN2_Pendelf;
    private javax.swing.JCheckBox jIN2_Pendelr;
    private javax.swing.JCheckBox jIn1Aux1;
    private javax.swing.JCheckBox jIn1Aux2;
    private javax.swing.JCheckBox jIn1Aux3;
    private javax.swing.JCheckBox jIn1Aux4;
    private javax.swing.JCheckBox jIn1Aux5;
    private javax.swing.JCheckBox jIn1Aux6;
    private javax.swing.JCheckBox jIn1Auxf;
    private javax.swing.JCheckBox jIn1Auxr;
    private javax.swing.JCheckBox jIn1Func1;
    private javax.swing.JCheckBox jIn1Func2;
    private javax.swing.JCheckBox jIn1Func3;
    private javax.swing.JCheckBox jIn1Func4;
    private javax.swing.JCheckBox jIn1Func5;
    private javax.swing.JCheckBox jIn1Func6;
    private javax.swing.JCheckBox jIn1Func7;
    private javax.swing.JCheckBox jIn1Func8;
    private javax.swing.JCheckBox jIn2Aux1;
    private javax.swing.JCheckBox jIn2Aux2;
    private javax.swing.JCheckBox jIn2Aux3;
    private javax.swing.JCheckBox jIn2Aux4;
    private javax.swing.JCheckBox jIn2Aux5;
    private javax.swing.JCheckBox jIn2Aux6;
    private javax.swing.JCheckBox jIn2Auxf;
    private javax.swing.JCheckBox jIn2Auxr;
    private javax.swing.JCheckBox jIn2Func1;
    private javax.swing.JCheckBox jIn2Func2;
    private javax.swing.JCheckBox jIn2Func3;
    private javax.swing.JCheckBox jIn2Func4;
    private javax.swing.JCheckBox jIn2Func5;
    private javax.swing.JCheckBox jIn2Func6;
    private javax.swing.JCheckBox jIn2Func7;
    private javax.swing.JCheckBox jIn2Func8;
    private javax.swing.JCheckBox jKick1;
    private javax.swing.JCheckBox jKick2;
    private javax.swing.JCheckBox jKick3;
    private javax.swing.JCheckBox jKick4;
    private javax.swing.JCheckBox jKick5;
    private javax.swing.JCheckBox jKick6;
    private javax.swing.JCheckBox jKickFahr1;
    private javax.swing.JCheckBox jKickFahr2;
    private javax.swing.JCheckBox jKickFahr3;
    private javax.swing.JCheckBox jKickFahr4;
    private javax.swing.JCheckBox jKickFahr5;
    private javax.swing.JCheckBox jKickFahr6;
    private javax.swing.JTextField jKickMotor;
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
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel161;
    private javax.swing.JLabel jLabel162;
    private javax.swing.JLabel jLabel163;
    private javax.swing.JLabel jLabel164;
    private javax.swing.JLabel jLabel165;
    private javax.swing.JLabel jLabel166;
    private javax.swing.JLabel jLabel167;
    private javax.swing.JLabel jLabel169;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel172;
    private javax.swing.JLabel jLabel174;
    private javax.swing.JLabel jLabel175;
    private javax.swing.JLabel jLabel176;
    private javax.swing.JLabel jLabel177;
    private javax.swing.JLabel jLabel178;
    private javax.swing.JLabel jLabel179;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel180;
    private javax.swing.JLabel jLabel181;
    private javax.swing.JLabel jLabel182;
    private javax.swing.JLabel jLabel183;
    private javax.swing.JLabel jLabel184;
    private javax.swing.JLabel jLabel185;
    private javax.swing.JLabel jLabel186;
    private javax.swing.JLabel jLabel187;
    private javax.swing.JLabel jLabel188;
    private javax.swing.JLabel jLabel189;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel190;
    private javax.swing.JLabel jLabel191;
    private javax.swing.JLabel jLabel192;
    private javax.swing.JLabel jLabel193;
    private javax.swing.JLabel jLabel194;
    private javax.swing.JLabel jLabel195;
    private javax.swing.JLabel jLabel196;
    private javax.swing.JLabel jLabel197;
    private javax.swing.JLabel jLabel198;
    private javax.swing.JLabel jLabel199;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel200;
    private javax.swing.JLabel jLabel201;
    private javax.swing.JLabel jLabel202;
    private javax.swing.JLabel jLabel203;
    private javax.swing.JLabel jLabel204;
    private javax.swing.JLabel jLabel205;
    private javax.swing.JLabel jLabel206;
    private javax.swing.JLabel jLabel208;
    private javax.swing.JLabel jLabel209;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel210;
    private javax.swing.JLabel jLabel211;
    private javax.swing.JLabel jLabel212;
    private javax.swing.JLabel jLabel213;
    private javax.swing.JLabel jLabel214;
    private javax.swing.JLabel jLabel215;
    private javax.swing.JLabel jLabel216;
    private javax.swing.JLabel jLabel217;
    private javax.swing.JLabel jLabel218;
    private javax.swing.JLabel jLabel219;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel220;
    private javax.swing.JLabel jLabel221;
    private javax.swing.JLabel jLabel222;
    private javax.swing.JLabel jLabel223;
    private javax.swing.JLabel jLabel224;
    private javax.swing.JLabel jLabel225;
    private javax.swing.JLabel jLabel226;
    private javax.swing.JLabel jLabel227;
    private javax.swing.JLabel jLabel228;
    private javax.swing.JLabel jLabel229;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel230;
    private javax.swing.JLabel jLabel231;
    private javax.swing.JLabel jLabel232;
    private javax.swing.JLabel jLabel233;
    private javax.swing.JLabel jLabel234;
    private javax.swing.JLabel jLabel235;
    private javax.swing.JLabel jLabel236;
    private javax.swing.JLabel jLabel237;
    private javax.swing.JLabel jLabel238;
    private javax.swing.JLabel jLabel239;
    private javax.swing.JLabel jLabel240;
    private javax.swing.JLabel jLabel241;
    private javax.swing.JLabel jLabel242;
    private javax.swing.JLabel jLabel243;
    private javax.swing.JLabel jLabel244;
    private javax.swing.JLabel jLabel245;
    private javax.swing.JLabel jLabel246;
    private javax.swing.JLabel jLabel247;
    private javax.swing.JLabel jLabel248;
    private javax.swing.JLabel jLabel249;
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
    private javax.swing.JLabel jLabelChangeLR;
    private javax.swing.JLabel jLabelLeftPos;
    private javax.swing.JLabel jLabelRightPos;
    private javax.swing.JLabel jLabelServoPOM;
    private javax.swing.JLabel jLabelSwitchWith;
    private javax.swing.JLabel jLabelVelocity;
    private javax.swing.JCheckBox jLongAddr;
    private javax.swing.JCheckBox jLongAddr1;
    private javax.swing.JCheckBox jLongAddr2;
    private javax.swing.JCheckBox jMARs1;
    private javax.swing.JCheckBox jMARs2;
    private javax.swing.JCheckBox jMARs3;
    private javax.swing.JCheckBox jMARs4;
    private javax.swing.JCheckBox jMARs5;
    private javax.swing.JCheckBox jMARs6;
    private javax.swing.JCheckBox jMARsf;
    private javax.swing.JCheckBox jMARsr;
    private javax.swing.JTextField jMM_Addr_2;
    private javax.swing.JLabel jManID;
    private javax.swing.JTextField jMindestSchlt1;
    private javax.swing.JTextField jMindestSchlt2;
    private javax.swing.JButton jOpen;
    private javax.swing.JTextField jPacketTimeOut;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JCheckBox jRCplus;
    private javax.swing.JCheckBox jRailCom;
    private javax.swing.JCheckBox jRangier1;
    private javax.swing.JCheckBox jRangier2;
    private javax.swing.JCheckBox jRangier3;
    private javax.swing.JCheckBox jRangier4;
    private javax.swing.JCheckBox jRangierF3;
    private javax.swing.JCheckBox jRangierF4;
    private javax.swing.JCheckBox jRangierf;
    private javax.swing.JCheckBox jRangierr;
    private javax.swing.JCheckBox jRichtung;
    private javax.swing.JCheckBox jRueck1;
    private javax.swing.JCheckBox jRueck2;
    private javax.swing.JCheckBox jRueck3;
    private javax.swing.JCheckBox jRueck4;
    private javax.swing.JCheckBox jRueck5;
    private javax.swing.JCheckBox jRueck6;
    private javax.swing.JCheckBox jRueckf;
    private javax.swing.JCheckBox jRueckr;
    private javax.swing.JButton jSave;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JPanel jServo;
    private javax.swing.JCheckBox jServoF10;
    private javax.swing.JCheckBox jServoF11;
    private javax.swing.JCheckBox jServoF12;
    private javax.swing.JCheckBox jServoF5;
    private javax.swing.JCheckBox jServoF6;
    private javax.swing.JCheckBox jServoF7;
    private javax.swing.JCheckBox jServoF8;
    private javax.swing.JCheckBox jServoF9;
    private javax.swing.JCheckBox jServoPOM;
    private javax.swing.JCheckBox jServoTausch;
    private javax.swing.JPanel jSound;
    private javax.swing.JTextField jUmschaltEmpf;
    private javax.swing.JLabel jVersion;
    private javax.swing.JCheckBox jVor1;
    private javax.swing.JCheckBox jVor2;
    private javax.swing.JCheckBox jVor3;
    private javax.swing.JCheckBox jVor4;
    private javax.swing.JCheckBox jVor5;
    private javax.swing.JCheckBox jVor6;
    private javax.swing.JCheckBox jVorf;
    private javax.swing.JCheckBox jVorr;
    private javax.swing.JCheckBox jbDimmFS1;
    private javax.swing.JCheckBox jbDimmFS2;
    private javax.swing.JCheckBox jbDimmFS3;
    private javax.swing.JCheckBox jbDimmFS4;
    private javax.swing.JCheckBox jbDimmFS5;
    private javax.swing.JCheckBox jbDimmFS6;
    private javax.swing.JCheckBox jbDimmFSf;
    private javax.swing.JCheckBox jbDimmFSr;
    private javax.swing.JRadioButton jlangeAdr;
    private javax.swing.JTextField jlinkerAnschlag;
    private javax.swing.JTextField jrechterAnschlag;
    // End of variables declaration//GEN-END:variables

}
