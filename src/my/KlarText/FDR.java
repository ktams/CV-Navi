/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FDR.java
 *
 * Created on 23.01.2009, 14:39:08
 */

package my.KlarText;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;

/**
 *
 * @author ktams
 */
public class FDR extends javax.swing.JFrame {

    /** Creates new form FDR */
    private String CVs;
    private int CV[][];
    public KlarTextUI KTUI;
    public String ReturnString;
    ResourceBundle bundle;
    private boolean bFD_R_basic2;
    private boolean bFunktionsweise; //false->Funktionsdecoder, true->RaislCom-Sender

    public FDR(KlarTextUI ktuiThis) {
        this.bFunktionsweise = false;
        if( ktuiThis == null ) {
            return;
        }
        this.bFD_R_basic2 = false;
        KTUI = ktuiThis;
        if( KTUI.frameInstanceDEVICE != null ) {
            KTUI.frameInstanceDEVICE.toFront();
            KTUI.frameInstanceDEVICE.repaint();
            return;
        }

        initComponents();
        CV = new int[2][115];
        bundle = java.util.ResourceBundle.getBundle("my.KlarText/Bundle");
        ReturnString = "Tams Elektronik";
        ImageIcon II = new ImageIcon(getClass().getResource("/FD-R.gif"));
        setIconImage(II.getImage());
        jBild.setIcon(II);
        setTitle( KTUI.getMenutext( decoderList.FD_R ).trim() );

        //---- CV-default-Werte -----
        // Vom Decoder verwendete CVs markieren und mit Default-Werten besetzen
        initCV( 0, 0 ); // reset jCV_Anzeige (clean all entries)

        // Vom Decoder verwendete CVs markieren und mit Default-Werten besetzen
        initCV( 1, 3 );
        initCV( 7, 10 );
        initCV( 8, 62 );
        initCV( 13, 0 );
        initCV( 17, 192 );
        initCV( 18, 255 );
        initCV( 19, 0 );
        initCV( 29, 14 );
        initCV( 33, 1 );
        initCV( 34, 2 );
        for( int i = 35; i<= 46; i++ ) { // [35..46]=0
            initCV( i, 0 );
        }
        initCV( 49, 64 );
        initCV( 50, 64 );
        initCV( 53, 0 );
        initCV( 54, 0 );
        initCV( 61, 255 );
        initCV( 62, 255 );
        initCV( 112, 48 );
        initCV( 114, 4 );

        //FD-R-basic2 Elemente ausblenden
        jLabel_AUX3.setVisible(false);
        jLabel_AUX.setVisible(false);
        jLabel_AUX_1.setVisible(false);
        jLabel_AUX_2.setVisible(false);
        jLabel_AUX_3.setVisible(false);
        jLabel_F13.setVisible(false);
        jLabel_F14.setVisible(false);
        jLabel_F15.setVisible(false);
        jLabel_F16.setVisible(false);
        jLabel_F17.setVisible(false);
        jLabel_F18.setVisible(false);
        jLabel_F19.setVisible(false);
        jLabel_F20.setVisible(false);
        jLabel_F21.setVisible(false);
        jLabel_F22.setVisible(false);
        jLabel_F23.setVisible(false);
        jLabel_F24.setVisible(false);
        jLabel_F25.setVisible(false);
        jLabel_F26.setVisible(false);
        jLabel_F27.setVisible(false);
        jLabel_F28.setVisible(false);
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
        jF13_3.setVisible(false);
        jF14_3.setVisible(false);
        jF15_3.setVisible(false);
        jF16_3.setVisible(false);
        jF17_3.setVisible(false);
        jF18_3.setVisible(false);
        jF19_3.setVisible(false);
        jF20_3.setVisible(false);
        jF21_3.setVisible(false);
        jF22_3.setVisible(false);
        jF23_3.setVisible(false);
        jF24_3.setVisible(false);
        jF25_3.setVisible(false);
        jF26_3.setVisible(false);
        jF27_3.setVisible(false);
        jF28_3.setVisible(false);

        jF13_2.setVisible(false);
        jF14_2.setVisible(false);
        jF15_2.setVisible(false);
        jF16_2.setVisible(false);
        jF17_2.setVisible(false);
        jF18_2.setVisible(false);
        jF19_2.setVisible(false);
        jF20_2.setVisible(false);
        jF21_2.setVisible(false);
        jF22_2.setVisible(false);
        jF23_2.setVisible(false);
        jF24_2.setVisible(false);
        jF25_2.setVisible(false);
        jF26_2.setVisible(false);
        jF27_2.setVisible(false);
        jF28_2.setVisible(false);

        jF13_1.setVisible(false);
        jF14_1.setVisible(false);
        jF15_1.setVisible(false);
        jF16_1.setVisible(false);
        jF17_1.setVisible(false);
        jF18_1.setVisible(false);
        jF19_1.setVisible(false);
        jF20_1.setVisible(false);
        jF21_1.setVisible(false);
        jF22_1.setVisible(false);
        jF23_1.setVisible(false);
        jF24_1.setVisible(false);
        jF25_1.setVisible(false);
        jF26_1.setVisible(false);
        jF27_1.setVisible(false);
        jF28_1.setVisible(false);
        setLocationRelativeTo(ktuiThis);
        setVisible(true);
        KTUI.frameInstanceDEVICE = this;
    }

    FDR(KlarTextUI aThis, int i) {
        this.bFunktionsweise = false;
        if( aThis == null ) {
            return;
        }
        this.bFD_R_basic2 = true;
        KTUI = aThis;
        if( KTUI.frameInstanceDEVICE != null ) {
            KTUI.frameInstanceDEVICE.toFront();
            KTUI.frameInstanceDEVICE.repaint();
            return;
        }

        initComponents();
        CV = new int[2][115];
        bundle = java.util.ResourceBundle.getBundle("my.KlarText/Bundle");
        ReturnString = "Tams Elektronik";
        ImageIcon II = new ImageIcon(getClass().getResource("/FD-R2.gif"));
        setIconImage(II.getImage());
        jBild.setIcon(II);
        setTitle( KTUI.getMenutext( decoderList.FD_R ).trim() );
        jLabelMM_Addr_2.setText(bundle.getString("FDR.jLabelMM_Addr_2.text2"));
        jLabel_AUX1.setText("2");
        jLabel_AUX2.setText("1");
        //---- CV-default-Werte -----
        // Vom Decoder verwendete CVs markieren und mit Default-Werten besetzen
        initCV( 0, 0 ); // reset jCV_Anzeige (clean all entries)

        // Vom Decoder verwendete CVs markieren und mit Default-Werten besetzen
        initCV( 1, 3 );
        initCV( 7, 10 );
        initCV( 8, 62 );
        initCV( 9, 0 );
        initCV( 17, 192 );
        initCV( 18, 255 );
        initCV( 19, 0 );
        initCV( 21, 0 );
        initCV( 28, 3 );
        initCV( 29, 14 );
        initCV( 33, 2 );
        initCV( 34, 4 );
        initCV( 35, 1 );
        for( i = 36; i<= 46; i++ ) { // [35..46]=0
            initCV( i, 0 );
        }
        initCV( 49, 64 );
        initCV( 50, 64 );
        initCV( 51, 64 );
        initCV( 53, 0 );
        initCV( 54, 0 );
        initCV( 55, 0 );
        initCV( 58, 0 );
        initCV( 59, 0 );
        initCV( 60, 0 );
        initCV( 61, 255 );
        initCV( 62, 255 );
        initCV( 63, 255 );
        
    //    initCV( 888, 0 );
        //die Sachen ausblenden, die es nicht gibt.....
        jMM_Addr_2.setVisible(false);
        setLocationRelativeTo(aThis);
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

    public void filfilCVs() {
        Boolean b ;
        String[] keys = { "FD-R basic" };
    b = parseString2CVs.convertString2CV( ReturnString, keys, CV, jComment, KTUI );
    }

    void updateTabs() {
        int idx = jDecodereigenschaften.getSelectedIndex();

        for( int i = 0 ; i < jDecodereigenschaften.getComponentCount() ; i ++ ) {
            jDecodereigenschaften.setSelectedIndex(i);
        }

        jDecodereigenschaften.setSelectedIndex(idx);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jSave = new javax.swing.JButton();
        jOpen = new javax.swing.JButton();
        jCV_Anzeige = new javax.swing.JComboBox();
        jCV_Inhalt = new javax.swing.JTextField();
        jDirekteingabe = new javax.swing.JToggleButton();
        jCV_LesenSchreiben = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jDecoderAdresse = new javax.swing.JTextField();
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
        jLabelBlinkFrequenz = new javax.swing.JLabel();
        jLabelBlinkFrequenz_t1 = new javax.swing.JLabel();
        jLabelBlinkFrequenz_t2 = new javax.swing.JLabel();
        jBlinkFrequenz = new javax.swing.JTextField();
        jLabelMM_Addr_2 = new javax.swing.JLabel();
        jMM_Addr_2 = new javax.swing.JTextField();
        jBild = new javax.swing.JLabel();
        jRC_Sender = new javax.swing.JRadioButton();
        jFD = new javax.swing.JRadioButton();
        jFunctionMapping = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
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
        jLabel_AUX1 = new javax.swing.JLabel();
        jFL_1 = new javax.swing.JCheckBox();
        jFR_1 = new javax.swing.JCheckBox();
        jF1_1 = new javax.swing.JCheckBox();
        jF2_1 = new javax.swing.JCheckBox();
        jF3_1 = new javax.swing.JCheckBox();
        jF4_1 = new javax.swing.JCheckBox();
        jF5_1 = new javax.swing.JCheckBox();
        jF6_1 = new javax.swing.JCheckBox();
        jF7_1 = new javax.swing.JCheckBox();
        jF8_1 = new javax.swing.JCheckBox();
        jF9_1 = new javax.swing.JCheckBox();
        jF10_1 = new javax.swing.JCheckBox();
        jF11_1 = new javax.swing.JCheckBox();
        jF12_1 = new javax.swing.JCheckBox();
        jLabel_AUX2 = new javax.swing.JLabel();
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
        jF10_2 = new javax.swing.JCheckBox();
        jF11_2 = new javax.swing.JCheckBox();
        jF12_2 = new javax.swing.JCheckBox();
        jLabel33 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jFL_3 = new javax.swing.JCheckBox();
        jFR_3 = new javax.swing.JCheckBox();
        jLabel_AUX3 = new javax.swing.JLabel();
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
        jF28_1 = new javax.swing.JCheckBox();
        jLabel_AUX = new javax.swing.JLabel();
        jLabel_AUX_1 = new javax.swing.JLabel();
        jLabel_AUX_2 = new javax.swing.JLabel();
        jLabel_AUX_3 = new javax.swing.JLabel();
        jLabel_F14 = new javax.swing.JLabel();
        jLabel_F13 = new javax.swing.JLabel();
        jLabel_F28 = new javax.swing.JLabel();
        jLabel_F15 = new javax.swing.JLabel();
        jLabel_F16 = new javax.swing.JLabel();
        jLabel_F17 = new javax.swing.JLabel();
        jLabel_F18 = new javax.swing.JLabel();
        jLabel_F19 = new javax.swing.JLabel();
        jLabel_F20 = new javax.swing.JLabel();
        jLabel_F21 = new javax.swing.JLabel();
        jLabel_F22 = new javax.swing.JLabel();
        jLabel_F23 = new javax.swing.JLabel();
        jLabel_F24 = new javax.swing.JLabel();
        jLabel_F25 = new javax.swing.JLabel();
        jLabel_F26 = new javax.swing.JLabel();
        jLabel_F27 = new javax.swing.JLabel();
        jF12_3 = new javax.swing.JCheckBox();
        jF13_1 = new javax.swing.JCheckBox();
        jF14_1 = new javax.swing.JCheckBox();
        jF15_1 = new javax.swing.JCheckBox();
        jF16_1 = new javax.swing.JCheckBox();
        jF17_1 = new javax.swing.JCheckBox();
        jF18_1 = new javax.swing.JCheckBox();
        jF19_1 = new javax.swing.JCheckBox();
        jF20_1 = new javax.swing.JCheckBox();
        jF21_1 = new javax.swing.JCheckBox();
        jF22_1 = new javax.swing.JCheckBox();
        jF23_1 = new javax.swing.JCheckBox();
        jF24_1 = new javax.swing.JCheckBox();
        jF25_1 = new javax.swing.JCheckBox();
        jF26_1 = new javax.swing.JCheckBox();
        jF27_1 = new javax.swing.JCheckBox();
        jF28_2 = new javax.swing.JCheckBox();
        jF13_2 = new javax.swing.JCheckBox();
        jF14_2 = new javax.swing.JCheckBox();
        jF15_2 = new javax.swing.JCheckBox();
        jF16_2 = new javax.swing.JCheckBox();
        jF17_2 = new javax.swing.JCheckBox();
        jF18_2 = new javax.swing.JCheckBox();
        jF19_2 = new javax.swing.JCheckBox();
        jF20_2 = new javax.swing.JCheckBox();
        jF21_2 = new javax.swing.JCheckBox();
        jF22_2 = new javax.swing.JCheckBox();
        jF23_2 = new javax.swing.JCheckBox();
        jF24_2 = new javax.swing.JCheckBox();
        jF25_2 = new javax.swing.JCheckBox();
        jF26_2 = new javax.swing.JCheckBox();
        jF27_2 = new javax.swing.JCheckBox();
        jF28_3 = new javax.swing.JCheckBox();
        jF13_3 = new javax.swing.JCheckBox();
        jF14_3 = new javax.swing.JCheckBox();
        jF15_3 = new javax.swing.JCheckBox();
        jF16_3 = new javax.swing.JCheckBox();
        jF17_3 = new javax.swing.JCheckBox();
        jF18_3 = new javax.swing.JCheckBox();
        jF19_3 = new javax.swing.JCheckBox();
        jF20_3 = new javax.swing.JCheckBox();
        jF21_3 = new javax.swing.JCheckBox();
        jF22_3 = new javax.swing.JCheckBox();
        jF23_3 = new javax.swing.JCheckBox();
        jF24_3 = new javax.swing.JCheckBox();
        jF25_3 = new javax.swing.JCheckBox();
        jF26_3 = new javax.swing.JCheckBox();
        jF27_3 = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel21 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jVor1 = new javax.swing.JCheckBox();
        jRueck1 = new javax.swing.JCheckBox();
        jDimmen1 = new javax.swing.JTextField();
        jTast1 = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jR_F4_1 = new javax.swing.JCheckBox();
        jR_F3_1 = new javax.swing.JCheckBox();
        jLabel22 = new javax.swing.JLabel();
        jDimmen2 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jTast2 = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jR_F4_2 = new javax.swing.JCheckBox();
        jR_F3_2 = new javax.swing.JCheckBox();
        jRück2 = new javax.swing.JCheckBox();
        jVor2 = new javax.swing.JCheckBox();
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
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jComment = new javax.swing.JTextArea();
        jLabel36 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jDecoderAdresse1 = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jKurzeAdr = new javax.swing.JRadioButton();
        jlangeAdr = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();
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

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("my/KlarText/Bundle"); // NOI18N
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, bundle.getString("FDR.jPanel1.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        jSave.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jSave.setText(bundle.getString("FDR.jSave.text")); // NOI18N
        jSave.setToolTipText(bundle.getString("FDR.jSave.toolTipText")); // NOI18N
        jSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSaveActionPerformed(evt);
            }
        });

        jOpen.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jOpen.setText(bundle.getString("FDR.jOpen.text")); // NOI18N
        jOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jOpenActionPerformed(evt);
            }
        });

        jCV_Anzeige.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCV_Anzeige.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("FDR.jCV_Anzeige.border.title"))); // NOI18N
        jCV_Anzeige.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCV_AnzeigeActionPerformed(evt);
            }
        });

        jCV_Inhalt.setEditable(false);
        jCV_Inhalt.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCV_Inhalt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jCV_Inhalt.setText(bundle.getString("FDR.jCV_Inhalt.text")); // NOI18N
        jCV_Inhalt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jCV_InhaltFocusLost(evt);
            }
        });

        jDirekteingabe.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jDirekteingabe.setText(bundle.getString("FDR.jDirekteingabe.text")); // NOI18N
        jDirekteingabe.setToolTipText(bundle.getString("FDR.jDirekteingabe.toolTipText")); // NOI18N
        jDirekteingabe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDirekteingabeActionPerformed(evt);
            }
        });

        jCV_LesenSchreiben.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCV_LesenSchreiben.setText(bundle.getString("FDR.jCV_LesenSchreiben.text")); // NOI18N
        jCV_LesenSchreiben.setToolTipText(bundle.getString("FDR.jCV_LesenSchreiben.toolTipText")); // NOI18N
        jCV_LesenSchreiben.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCV_LesenSchreibenActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText(bundle.getString("FDR.jLabel1.text")); // NOI18N

        jDecoderAdresse.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jDecoderAdresse.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jDecoderAdresse.setText(bundle.getString("FDR.jDecoderAdresse.text")); // NOI18N
        jDecoderAdresse.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jDecoderAdresseFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jDecoderAdresseFocusLost(evt);
            }
        });
        jDecoderAdresse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDecoderAdresseActionPerformed(evt);
            }
        });
        jDecoderAdresse.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jDecoderAdresseKeyReleased(evt);
            }
        });

        jDecodereigenschaften.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jDecodereigenschaften.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jCV29.setToolTipText(bundle.getString("FDR.jCV29.toolTipText")); // NOI18N
        jCV29.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jCV29ComponentShown(evt);
            }
        });

        jRichtung.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRichtung.setText(bundle.getString("FDR.jRichtung.text")); // NOI18N
        jRichtung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRichtungActionPerformed(evt);
            }
        });

        jFS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFS.setSelected(true);
        jFS.setText(bundle.getString("FDR.jFS.text")); // NOI18N
        jFS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFSActionPerformed(evt);
            }
        });

        jAnalog1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAnalog1.setSelected(true);
        jAnalog1.setText(bundle.getString("FDR.jAnalog1.text")); // NOI18N
        jAnalog1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAnalog1ActionPerformed(evt);
            }
        });

        jRailCom.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRailCom.setSelected(true);
        jRailCom.setText(bundle.getString("FDR.jRailCom.text")); // NOI18N
        jRailCom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRailComActionPerformed(evt);
            }
        });

        jAnalog3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAnalog3.setText(bundle.getString("FDR.jAnalog3.text")); // NOI18N
        jAnalog3.setEnabled(false);

        jLongAddr.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLongAddr.setText(bundle.getString("FDR.jLongAddr.text")); // NOI18N
        jLongAddr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jLongAddrActionPerformed(evt);
            }
        });

        jLongAddr1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLongAddr1.setText(bundle.getString("FDR.jLongAddr1.text")); // NOI18N
        jLongAddr1.setEnabled(false);

        jLongAddr2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLongAddr2.setText(bundle.getString("FDR.jLongAddr2.text")); // NOI18N
        jLongAddr2.setEnabled(false);

        jLabelBlinkFrequenz.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelBlinkFrequenz.setText(bundle.getString("FDR.jLabelBlinkFrequenz.text")); // NOI18N

        jLabelBlinkFrequenz_t1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelBlinkFrequenz_t1.setText(bundle.getString("FDR.jLabelBlinkFrequenz_t1.text")); // NOI18N

        jLabelBlinkFrequenz_t2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelBlinkFrequenz_t2.setText(bundle.getString("FDR.jLabelBlinkFrequenz_t2.text")); // NOI18N

        jBlinkFrequenz.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jBlinkFrequenz.setText(bundle.getString("FDR.jBlinkFrequenz.text")); // NOI18N
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

        jLabelMM_Addr_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelMM_Addr_2.setText(bundle.getString("FDR.jLabelMM_Addr_2.text")); // NOI18N

        jMM_Addr_2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jMM_Addr_2.setText(bundle.getString("FDR.jMM_Addr_2.text")); // NOI18N
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

        buttonGroup2.add(jRC_Sender);
        jRC_Sender.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jRC_Sender.setText(bundle.getString("FDR.jRC_Sender.text")); // NOI18N
        jRC_Sender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRC_SenderActionPerformed(evt);
            }
        });

        buttonGroup2.add(jFD);
        jFD.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jFD.setSelected(true);
        jFD.setText(bundle.getString("FDR.jFD.text")); // NOI18N
        jFD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jCV29Layout = new javax.swing.GroupLayout(jCV29);
        jCV29.setLayout(jCV29Layout);
        jCV29Layout.setHorizontalGroup(
            jCV29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jCV29Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jCV29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jCV29Layout.createSequentialGroup()
                        .addGroup(jCV29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRailCom)
                            .addComponent(jRichtung)
                            .addComponent(jFS)
                            .addComponent(jAnalog1)
                            .addGroup(jCV29Layout.createSequentialGroup()
                                .addGroup(jCV29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLongAddr)
                                    .addComponent(jLongAddr1)
                                    .addComponent(jLongAddr2))
                                .addGap(90, 90, 90)
                                .addComponent(jBild, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jCV29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jCV29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabelBlinkFrequenz, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabelBlinkFrequenz_t2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabelBlinkFrequenz_t1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jBlinkFrequenz, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelMM_Addr_2)
                            .addComponent(jMM_Addr_2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jRC_Sender)
                            .addComponent(jFD))
                        .addGap(48, 48, 48))
                    .addGroup(jCV29Layout.createSequentialGroup()
                        .addComponent(jAnalog3)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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
                        .addComponent(jAnalog3))
                    .addGroup(jCV29Layout.createSequentialGroup()
                        .addComponent(jLabelBlinkFrequenz)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelBlinkFrequenz_t1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelBlinkFrequenz_t2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBlinkFrequenz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jCV29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jCV29Layout.createSequentialGroup()
                        .addGap(0, 148, Short.MAX_VALUE)
                        .addComponent(jBild, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jCV29Layout.createSequentialGroup()
                        .addGroup(jCV29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jCV29Layout.createSequentialGroup()
                                .addComponent(jLongAddr)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLongAddr1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLongAddr2))
                            .addGroup(jCV29Layout.createSequentialGroup()
                                .addComponent(jLabelMM_Addr_2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jMM_Addr_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRC_Sender)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jFD)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jDecodereigenschaften.addTab(bundle.getString("FDR.jCV29.TabConstraints.tabTitle"), jCV29); // NOI18N

        jFunctionMapping.setToolTipText(bundle.getString("FDR.jFunctionMapping.toolTipText")); // NOI18N
        jFunctionMapping.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFunctionMapping.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jFunctionMappingComponentShown(evt);
            }
        });
        jFunctionMapping.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText(bundle.getString("FDR.jLabel4.text")); // NOI18N
        jFunctionMapping.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel6.setText(bundle.getString("FDR.jLabel6.text")); // NOI18N
        jFunctionMapping.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel7.setText(bundle.getString("FDR.jLabel7.text")); // NOI18N
        jFunctionMapping.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel8.setText(bundle.getString("FDR.jLabel8.text")); // NOI18N
        jFunctionMapping.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel9.setText(bundle.getString("FDR.jLabel9.text")); // NOI18N
        jFunctionMapping.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, -1, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel10.setText(bundle.getString("FDR.jLabel10.text")); // NOI18N
        jFunctionMapping.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, -1, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel11.setText(bundle.getString("FDR.jLabel11.text")); // NOI18N
        jFunctionMapping.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 80, 20, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel12.setText(bundle.getString("FDR.jLabel12.text")); // NOI18N
        jFunctionMapping.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 80, -1, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel13.setText(bundle.getString("FDR.jLabel13.text")); // NOI18N
        jFunctionMapping.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 80, -1, -1));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel14.setText(bundle.getString("FDR.jLabel14.text")); // NOI18N
        jFunctionMapping.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 80, -1, -1));

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel15.setText(bundle.getString("FDR.jLabel15.text")); // NOI18N
        jFunctionMapping.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 80, -1, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel16.setText(bundle.getString("FDR.jLabel16.text")); // NOI18N
        jFunctionMapping.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 80, -1, -1));

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel17.setText(bundle.getString("FDR.jLabel17.text")); // NOI18N
        jFunctionMapping.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 80, -1, -1));

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel18.setText(bundle.getString("FDR.jLabel18.text")); // NOI18N
        jFunctionMapping.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 80, -1, -1));

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel19.setText(bundle.getString("FDR.jLabel19.text")); // NOI18N
        jFunctionMapping.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 80, -1, -1));

        jLabel_AUX1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel_AUX1.setText(bundle.getString("FDR.jLabel_AUX1.text")); // NOI18N
        jFunctionMapping.add(jLabel_AUX1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 10, -1));

        jFL_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFL_1.setSelected(true);
        jFL_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFL_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jFL_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, -1, -1));

        jFR_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFR_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFR_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jFR_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, -1, -1));

        jF1_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF1_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF1_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF1_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 100, -1, -1));

        jF2_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF2_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF2_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF2_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, -1, -1));

        jF3_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF3_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF3_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF3_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 100, -1, -1));

        jF4_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF4_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF4_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF4_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 100, -1, -1));

        jF5_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF5_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF5_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF5_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 100, -1, -1));

        jF6_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF6_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF6_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF6_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 100, -1, -1));

        jF7_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF7_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF7_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF7_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 100, -1, -1));

        jF8_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF8_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF8_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF8_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 100, -1, -1));

        jF9_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF9_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF9_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF9_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 100, -1, -1));

        jF10_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF10_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF10_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF10_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 100, -1, -1));

        jF11_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF11_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF11_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF11_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 100, -1, -1));

        jF12_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF12_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF12_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF12_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 100, -1, -1));

        jLabel_AUX2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel_AUX2.setText(bundle.getString("FDR.jLabel_AUX2.text")); // NOI18N
        jFunctionMapping.add(jLabel_AUX2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, -1));

        jFL_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFL_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFL_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jFL_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, -1, -1));

        jFR_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFR_2.setSelected(true);
        jFR_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFR_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jFR_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, -1, -1));

        jF1_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF1_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF1_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF1_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, -1, -1));

        jF2_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF2_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF2_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF2_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 130, -1, -1));

        jF3_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF3_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF3_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF3_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 130, -1, -1));

        jF4_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF4_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF4_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF4_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 130, -1, -1));

        jF5_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF5_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF5_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF5_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 130, -1, -1));

        jF6_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF6_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF6_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF6_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 130, -1, -1));

        jF7_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF7_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF7_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF7_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 130, -1, -1));

        jF8_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF8_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF8_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF8_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 130, -1, -1));

        jF9_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF9_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF9_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF9_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 130, -1, -1));

        jF10_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF10_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF10_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF10_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 130, -1, -1));

        jF11_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF11_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF11_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF11_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 130, -1, -1));

        jF12_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF12_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF12_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF12_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 130, -1, -1));

        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel33.setText(bundle.getString("FDR.jLabel33.text")); // NOI18N
        jFunctionMapping.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText(bundle.getString("FDR.jLabel3.text")); // NOI18N
        jFunctionMapping.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 50, -1, -1));

        jFL_3.setText(bundle.getString("FDR.jFL_3.text")); // NOI18N
        jFL_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFL_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jFL_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, -1, -1));

        jFR_3.setText(bundle.getString("FDR.jFR_3.text")); // NOI18N
        jFR_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFR_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jFR_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, -1, -1));

        jLabel_AUX3.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel_AUX3.setText(bundle.getString("FDR.jLabel_AUX3.text")); // NOI18N
        jFunctionMapping.add(jLabel_AUX3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));

        jF1_3.setText(bundle.getString("FDR.jF1_3.text")); // NOI18N
        jF1_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF1_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF1_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 160, -1, -1));

        jF2_3.setText(bundle.getString("FDR.jF2_3.text")); // NOI18N
        jF2_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF2_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF2_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 160, -1, -1));

        jF3_3.setText(bundle.getString("FDR.jF3_3.text")); // NOI18N
        jF3_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF3_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF3_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 160, -1, -1));

        jF4_3.setText(bundle.getString("FDR.jF4_3.text")); // NOI18N
        jF4_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF4_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF4_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 160, -1, -1));

        jF5_3.setText(bundle.getString("FDR.jF5_3.text")); // NOI18N
        jF5_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF5_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF5_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 160, -1, -1));

        jF6_3.setText(bundle.getString("FDR.jF6_3.text")); // NOI18N
        jF6_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF6_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF6_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 160, -1, -1));

        jF7_3.setText(bundle.getString("FDR.jF7_3.text")); // NOI18N
        jF7_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF7_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF7_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 160, -1, -1));

        jF8_3.setText(bundle.getString("FDR.jF8_3.text")); // NOI18N
        jF8_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF8_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF8_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 160, -1, -1));

        jF9_3.setText(bundle.getString("FDR.jF9_3.text")); // NOI18N
        jF9_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF9_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF9_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 160, -1, -1));

        jF10_3.setText(bundle.getString("FDR.jF10_3.text")); // NOI18N
        jF10_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF10_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF10_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 160, -1, -1));

        jF11_3.setText(bundle.getString("FDR.jF11_3.text")); // NOI18N
        jF11_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF11_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF11_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 160, -1, -1));

        jF28_1.setText(bundle.getString("FDR.jF28_1.text")); // NOI18N
        jFunctionMapping.add(jF28_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 230, -1, -1));

        jLabel_AUX.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel_AUX.setText(bundle.getString("FDR.jLabel_AUX.text")); // NOI18N
        jFunctionMapping.add(jLabel_AUX, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, -1, -1));

        jLabel_AUX_1.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel_AUX_1.setText(bundle.getString("FDR.jLabel_AUX_1.text")); // NOI18N
        jFunctionMapping.add(jLabel_AUX_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, -1, -1));

        jLabel_AUX_2.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel_AUX_2.setText(bundle.getString("FDR.jLabel_AUX_2.text")); // NOI18N
        jFunctionMapping.add(jLabel_AUX_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, -1, -1));

        jLabel_AUX_3.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel_AUX_3.setText(bundle.getString("FDR.jLabel_AUX_3.text")); // NOI18N
        jFunctionMapping.add(jLabel_AUX_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, -1, -1));

        jLabel_F14.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        jLabel_F14.setText(bundle.getString("FDR.jLabel_F14.text")); // NOI18N
        jFunctionMapping.add(jLabel_F14, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 210, -1, -1));

        jLabel_F13.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        jLabel_F13.setText(bundle.getString("FDR.jLabel_F13.text")); // NOI18N
        jFunctionMapping.add(jLabel_F13, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, -1, -1));

        jLabel_F28.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        jLabel_F28.setText(bundle.getString("FDR.jLabel_F28.text")); // NOI18N
        jFunctionMapping.add(jLabel_F28, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 210, -1, -1));

        jLabel_F15.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        jLabel_F15.setText(bundle.getString("FDR.jLabel_F15.text")); // NOI18N
        jFunctionMapping.add(jLabel_F15, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 210, -1, -1));

        jLabel_F16.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        jLabel_F16.setText(bundle.getString("FDR.jLabel_F16.text")); // NOI18N
        jFunctionMapping.add(jLabel_F16, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 210, -1, -1));

        jLabel_F17.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        jLabel_F17.setText(bundle.getString("FDR.jLabel_F17.text")); // NOI18N
        jFunctionMapping.add(jLabel_F17, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 210, -1, -1));

        jLabel_F18.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        jLabel_F18.setText(bundle.getString("FDR.jLabel_F18.text")); // NOI18N
        jFunctionMapping.add(jLabel_F18, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 210, -1, -1));

        jLabel_F19.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        jLabel_F19.setText(bundle.getString("FDR.jLabel_F19.text")); // NOI18N
        jFunctionMapping.add(jLabel_F19, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 210, -1, -1));

        jLabel_F20.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        jLabel_F20.setText(bundle.getString("FDR.jLabel_F20.text")); // NOI18N
        jFunctionMapping.add(jLabel_F20, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 210, -1, -1));

        jLabel_F21.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        jLabel_F21.setText(bundle.getString("FDR.jLabel_F21.text")); // NOI18N
        jFunctionMapping.add(jLabel_F21, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 210, -1, -1));

        jLabel_F22.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        jLabel_F22.setText(bundle.getString("FDR.jLabel_F22.text")); // NOI18N
        jFunctionMapping.add(jLabel_F22, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 210, -1, -1));

        jLabel_F23.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        jLabel_F23.setText(bundle.getString("FDR.jLabel_F23.text")); // NOI18N
        jFunctionMapping.add(jLabel_F23, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 210, -1, -1));

        jLabel_F24.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        jLabel_F24.setText(bundle.getString("FDR.jLabel_F24.text")); // NOI18N
        jFunctionMapping.add(jLabel_F24, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 210, -1, -1));

        jLabel_F25.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        jLabel_F25.setText(bundle.getString("FDR.jLabel_F25.text")); // NOI18N
        jFunctionMapping.add(jLabel_F25, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 210, -1, -1));

        jLabel_F26.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        jLabel_F26.setText(bundle.getString("FDR.jLabel_F26.text")); // NOI18N
        jFunctionMapping.add(jLabel_F26, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 210, -1, -1));

        jLabel_F27.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        jLabel_F27.setText(bundle.getString("FDR.jLabel_F27.text")); // NOI18N
        jFunctionMapping.add(jLabel_F27, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 210, -1, -1));

        jF12_3.setText(bundle.getString("FDR.jF12_3.text")); // NOI18N
        jF12_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF12_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF12_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 160, -1, -1));

        jF13_1.setText(bundle.getString("FDR.jF13_1.text")); // NOI18N
        jFunctionMapping.add(jF13_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, -1, -1));

        jF14_1.setText(bundle.getString("FDR.jF14_1.text")); // NOI18N
        jFunctionMapping.add(jF14_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 230, -1, -1));

        jF15_1.setText(bundle.getString("FDR.jF15_1.text")); // NOI18N
        jFunctionMapping.add(jF15_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 230, -1, -1));

        jF16_1.setText(bundle.getString("FDR.jF16_1.text")); // NOI18N
        jFunctionMapping.add(jF16_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 230, -1, -1));

        jF17_1.setText(bundle.getString("FDR.jF17_1.text")); // NOI18N
        jFunctionMapping.add(jF17_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 230, -1, -1));

        jF18_1.setText(bundle.getString("FDR.jF18_1.text")); // NOI18N
        jFunctionMapping.add(jF18_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 230, -1, -1));

        jF19_1.setText(bundle.getString("FDR.jF19_1.text")); // NOI18N
        jFunctionMapping.add(jF19_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 230, -1, -1));

        jF20_1.setText(bundle.getString("FDR.jF20_1.text")); // NOI18N
        jFunctionMapping.add(jF20_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 230, -1, -1));

        jF21_1.setText(bundle.getString("FDR.jF21_1.text")); // NOI18N
        jFunctionMapping.add(jF21_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 230, -1, -1));

        jF22_1.setText(bundle.getString("FDR.jF22_1.text")); // NOI18N
        jFunctionMapping.add(jF22_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 230, -1, -1));

        jF23_1.setText(bundle.getString("FDR.jF23_1.text")); // NOI18N
        jFunctionMapping.add(jF23_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 230, -1, -1));

        jF24_1.setText(bundle.getString("FDR.jF24_1.text")); // NOI18N
        jFunctionMapping.add(jF24_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 230, -1, -1));

        jF25_1.setText(bundle.getString("FDR.jF25_1.text")); // NOI18N
        jFunctionMapping.add(jF25_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 230, -1, -1));

        jF26_1.setText(bundle.getString("FDR.jF26_1.text")); // NOI18N
        jFunctionMapping.add(jF26_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 230, -1, -1));

        jF27_1.setText(bundle.getString("FDR.jF27_1.text")); // NOI18N
        jFunctionMapping.add(jF27_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 230, -1, -1));

        jF28_2.setText(bundle.getString("FDR.jF28_2.text")); // NOI18N
        jFunctionMapping.add(jF28_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 250, -1, -1));

        jF13_2.setText(bundle.getString("FDR.jF13_2.text")); // NOI18N
        jFunctionMapping.add(jF13_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, -1, -1));

        jF14_2.setText(bundle.getString("FDR.jF14_2.text")); // NOI18N
        jFunctionMapping.add(jF14_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 250, -1, -1));

        jF15_2.setText(bundle.getString("FDR.jF15_2.text")); // NOI18N
        jFunctionMapping.add(jF15_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 250, -1, -1));

        jF16_2.setText(bundle.getString("FDR.jF16_2.text")); // NOI18N
        jFunctionMapping.add(jF16_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 250, -1, -1));

        jF17_2.setText(bundle.getString("FDR.jF17_2.text")); // NOI18N
        jFunctionMapping.add(jF17_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 250, -1, -1));

        jF18_2.setText(bundle.getString("FDR.jF18_2.text")); // NOI18N
        jFunctionMapping.add(jF18_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 250, -1, -1));

        jF19_2.setText(bundle.getString("FDR.jF19_2.text")); // NOI18N
        jFunctionMapping.add(jF19_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 250, -1, -1));

        jF20_2.setText(bundle.getString("FDR.jF20_2.text")); // NOI18N
        jFunctionMapping.add(jF20_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 250, -1, -1));

        jF21_2.setText(bundle.getString("FDR.jF21_2.text")); // NOI18N
        jFunctionMapping.add(jF21_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 250, -1, -1));

        jF22_2.setText(bundle.getString("FDR.jF22_2.text")); // NOI18N
        jFunctionMapping.add(jF22_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 250, -1, -1));

        jF23_2.setText(bundle.getString("FDR.jF23_2.text")); // NOI18N
        jFunctionMapping.add(jF23_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 250, -1, -1));

        jF24_2.setText(bundle.getString("FDR.jF24_2.text")); // NOI18N
        jFunctionMapping.add(jF24_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 250, -1, -1));

        jF25_2.setText(bundle.getString("FDR.jF25_2.text")); // NOI18N
        jFunctionMapping.add(jF25_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 250, -1, -1));

        jF26_2.setText(bundle.getString("FDR.jF26_2.text")); // NOI18N
        jFunctionMapping.add(jF26_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 250, -1, -1));

        jF27_2.setText(bundle.getString("FDR.jF27_2.text")); // NOI18N
        jFunctionMapping.add(jF27_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 250, -1, -1));

        jF28_3.setText(bundle.getString("FDR.jF28_3.text")); // NOI18N
        jFunctionMapping.add(jF28_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 270, -1, -1));

        jF13_3.setText(bundle.getString("FDR.jF13_3.text")); // NOI18N
        jFunctionMapping.add(jF13_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, -1, -1));

        jF14_3.setText(bundle.getString("FDR.jF14_3.text")); // NOI18N
        jFunctionMapping.add(jF14_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 270, -1, -1));

        jF15_3.setText(bundle.getString("FDR.jF15_3.text")); // NOI18N
        jFunctionMapping.add(jF15_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 270, -1, -1));

        jF16_3.setText(bundle.getString("FDR.jF16_3.text")); // NOI18N
        jFunctionMapping.add(jF16_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 270, -1, -1));

        jF17_3.setText(bundle.getString("FDR.jF17_3.text")); // NOI18N
        jFunctionMapping.add(jF17_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 270, -1, -1));

        jF18_3.setText(bundle.getString("FDR.jF18_3.text")); // NOI18N
        jFunctionMapping.add(jF18_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 270, -1, -1));

        jF19_3.setText(bundle.getString("FDR.jF19_3.text")); // NOI18N
        jFunctionMapping.add(jF19_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 270, -1, -1));

        jF20_3.setText(bundle.getString("FDR.jF20_3.text")); // NOI18N
        jFunctionMapping.add(jF20_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 270, -1, -1));

        jF21_3.setText(bundle.getString("FDR.jF21_3.text")); // NOI18N
        jFunctionMapping.add(jF21_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 270, -1, -1));

        jF22_3.setText(bundle.getString("FDR.jF22_3.text")); // NOI18N
        jFunctionMapping.add(jF22_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 270, -1, -1));

        jF23_3.setText(bundle.getString("FDR.jF23_3.text")); // NOI18N
        jFunctionMapping.add(jF23_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 270, -1, -1));

        jF24_3.setText(bundle.getString("FDR.jF24_3.text")); // NOI18N
        jFunctionMapping.add(jF24_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 270, -1, -1));

        jF25_3.setText(bundle.getString("FDR.jF25_3.text")); // NOI18N
        jFunctionMapping.add(jF25_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 270, -1, -1));

        jF26_3.setText(bundle.getString("FDR.jF26_3.text")); // NOI18N
        jFunctionMapping.add(jF26_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 270, -1, -1));

        jF27_3.setText(bundle.getString("FDR.jF27_3.text")); // NOI18N
        jFunctionMapping.add(jF27_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 270, -1, -1));

        jDecodereigenschaften.addTab(bundle.getString("FDR.jFunctionMapping.TabConstraints.tabTitle"), jFunctionMapping); // NOI18N

        jPanel2.setToolTipText(bundle.getString("FDR.jPanel2.toolTipText")); // NOI18N
        jPanel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel2.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanel2ComponentShown(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel21.setText(bundle.getString("FDR.jLabel21.text")); // NOI18N
        jLabel21.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel23.setText(bundle.getString("FDR.jLabel23.text")); // NOI18N

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel25.setText(bundle.getString("FDR.jLabel25.text")); // NOI18N

        jVor1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jVor1.setSelected(true);
        jVor1.setText(bundle.getString("FDR.jVor1.text")); // NOI18N
        jVor1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jVor1FocusGained(evt);
            }
        });
        jVor1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jVor1ActionPerformed(evt);
            }
        });

        jRueck1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRueck1.setSelected(true);
        jRueck1.setText(bundle.getString("FDR.jRueck1.text")); // NOI18N
        jRueck1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jRueck1FocusGained(evt);
            }
        });
        jRueck1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRueck1ActionPerformed(evt);
            }
        });

        jDimmen1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jDimmen1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jDimmen1.setText(bundle.getString("FDR.jDimmen1.text")); // NOI18N
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

        jTast1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTast1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTast1.setText(bundle.getString("FDR.jTast1.text")); // NOI18N
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

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel29.setText(bundle.getString("FDR.jLabel29.text")); // NOI18N

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel30.setText(bundle.getString("FDR.jLabel30.text")); // NOI18N

        jR_F4_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jR_F4_1.setText(bundle.getString("FDR.jR_F4_1.text")); // NOI18N
        jR_F4_1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jR_F4_1FocusGained(evt);
            }
        });
        jR_F4_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jR_F4_1ActionPerformed(evt);
            }
        });

        jR_F3_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jR_F3_1.setText(bundle.getString("FDR.jR_F3_1.text")); // NOI18N
        jR_F3_1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jR_F3_1FocusGained(evt);
            }
        });
        jR_F3_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jR_F3_1ActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel22.setText(bundle.getString("FDR.jLabel22.text")); // NOI18N
        jLabel22.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jDimmen2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jDimmen2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jDimmen2.setText(bundle.getString("FDR.jDimmen2.text")); // NOI18N
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

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel24.setText(bundle.getString("FDR.jLabel24.text")); // NOI18N

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel26.setText(bundle.getString("FDR.jLabel26.text")); // NOI18N

        jTast2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTast2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTast2.setText(bundle.getString("FDR.jTast2.text")); // NOI18N
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

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel31.setText(bundle.getString("FDR.jLabel31.text")); // NOI18N

        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel32.setText(bundle.getString("FDR.jLabel32.text")); // NOI18N

        jR_F4_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jR_F4_2.setText(bundle.getString("FDR.jR_F4_2.text")); // NOI18N
        jR_F4_2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jR_F4_2FocusGained(evt);
            }
        });
        jR_F4_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jR_F4_2ActionPerformed(evt);
            }
        });

        jR_F3_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jR_F3_2.setText(bundle.getString("FDR.jR_F3_2.text")); // NOI18N
        jR_F3_2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jR_F3_2FocusGained(evt);
            }
        });
        jR_F3_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jR_F3_2ActionPerformed(evt);
            }
        });

        jRück2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRück2.setSelected(true);
        jRück2.setText(bundle.getString("FDR.jRück2.text")); // NOI18N
        jRück2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jRück2FocusGained(evt);
            }
        });
        jRück2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRück2ActionPerformed(evt);
            }
        });

        jVor2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jVor2.setSelected(true);
        jVor2.setText(bundle.getString("FDR.jVor2.text")); // NOI18N
        jVor2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jVor2FocusGained(evt);
            }
        });
        jVor2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jVor2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addGap(377, 377, 377))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel32)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(12, 12, 12)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jRück2)
                                        .addComponent(jVor2))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel22)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jDimmen2)
                                            .addComponent(jLabel24))
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel26))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(68, 68, 68)
                                                .addComponent(jTast2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(12, 12, 12)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jR_F3_2)
                                                    .addComponent(jR_F4_2)))
                                            .addComponent(jLabel31)))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jDimmen1)
                                            .addComponent(jLabel23))
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(69, 69, 69)
                                                .addComponent(jTast1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel25)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel29)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(10, 10, 10)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jR_F3_1)
                                                    .addComponent(jR_F4_1))))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel30)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(12, 12, 12)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jRueck1)
                                                    .addComponent(jVor1))))))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jLabel25)
                    .addComponent(jLabel29)
                    .addComponent(jLabel30))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jDimmen1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTast1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jR_F3_1)
                            .addComponent(jVor1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jR_F4_1)
                            .addComponent(jRueck1))))
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24)
                            .addComponent(jLabel26))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jDimmen2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTast2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel32)
                            .addComponent(jLabel31))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jR_F3_2)
                            .addComponent(jVor2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jR_F4_2)
                            .addComponent(jRück2))))
                .addContainerGap(185, Short.MAX_VALUE))
        );

        jDecodereigenschaften.addTab(bundle.getString("FDR.jPanel2.TabConstraints.tabTitle"), jPanel2); // NOI18N

        jAnalog.setToolTipText(bundle.getString("FDR.jAnalog.toolTipText")); // NOI18N
        jAnalog.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAnalog.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jAnalogComponentShown(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText(bundle.getString("FDR.jLabel2.text")); // NOI18N

        jF1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF1.setText(bundle.getString("FDR.jF1.text")); // NOI18N
        jF1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jF1StateChanged(evt);
            }
        });

        jF2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF2.setText(bundle.getString("FDR.jF2.text")); // NOI18N
        jF2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jF2StateChanged(evt);
            }
        });

        jF3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF3.setText(bundle.getString("FDR.jF3.text")); // NOI18N
        jF3.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jF3StateChanged(evt);
            }
        });

        jF4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF4.setText(bundle.getString("FDR.jF4.text")); // NOI18N
        jF4.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jF4StateChanged(evt);
            }
        });

        jF5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF5.setText(bundle.getString("FDR.jF5.text")); // NOI18N
        jF5.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jF5StateChanged(evt);
            }
        });

        jF6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF6.setText(bundle.getString("FDR.jF6.text")); // NOI18N
        jF6.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jF6StateChanged(evt);
            }
        });

        jF7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF7.setText(bundle.getString("FDR.jF7.text")); // NOI18N
        jF7.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jF7StateChanged(evt);
            }
        });

        jF8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF8.setText(bundle.getString("FDR.jF8.text")); // NOI18N
        jF8.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jF8StateChanged(evt);
            }
        });

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
                            .addComponent(jF5))))
                .addContainerGap(309, Short.MAX_VALUE))
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
                .addContainerGap(250, Short.MAX_VALUE))
        );

        jDecodereigenschaften.addTab(bundle.getString("FDR.jAnalog.TabConstraints.tabTitle"), jAnalog); // NOI18N

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
        jLabel36.setText(bundle.getString("FDR.jLabel36.text")); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE))
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)
                .addContainerGap())
        );

        jDecodereigenschaften.addTab(bundle.getString("FDR.jPanel3.TabConstraints.tabTitle"), jPanel3); // NOI18N

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel27.setText(bundle.getString("FDR.jLabel27.text")); // NOI18N

        jDecoderAdresse1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jDecoderAdresse1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jDecoderAdresse1.setText(bundle.getString("FDR.jDecoderAdresse1.text")); // NOI18N
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
        jLabel28.setText(bundle.getString("FDR.jLabel28.text")); // NOI18N

        buttonGroup1.add(jKurzeAdr);
        jKurzeAdr.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jKurzeAdr.setSelected(true);
        jKurzeAdr.setText(bundle.getString("FDR.jKurzeAdr.text")); // NOI18N
        jKurzeAdr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jKurzeAdrActionPerformed(evt);
            }
        });

        buttonGroup1.add(jlangeAdr);
        jlangeAdr.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jlangeAdr.setText(bundle.getString("FDR.jlangeAdr.text")); // NOI18N
        jlangeAdr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jlangeAdrActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton1.setText(bundle.getString("FDR.jButton1.text")); // NOI18N
        jButton1.setToolTipText(bundle.getString("FDR.jButton1.toolTipText")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jAbbrechen.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAbbrechen.setText(bundle.getString("FDR.jAbbrechen.text")); // NOI18N
        jAbbrechen.setToolTipText(bundle.getString("FDR.jAbbrechen.toolTipText")); // NOI18N
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
                                    .addComponent(jlangeAdr)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jCV_Anzeige, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jCV_Inhalt, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel27)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jDecoderAdresse1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel28))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(jDirekteingabe)
                                .addGap(28, 28, 28)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jDecodereigenschaften, javax.swing.GroupLayout.PREFERRED_SIZE, 522, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jOpen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jCV_LesenSchreiben, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                    .addComponent(jAbbrechen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(124, 124, 124)
                        .addComponent(jOpen, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSave, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64)
                        .addComponent(jCV_LesenSchreiben, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jAbbrechen, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCV_Anzeige, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCV_Inhalt, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDirekteingabe, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSaveActionPerformed
        // Alle CVs werden in einer Datei gespeichert
        CVs = "FD-R basic\r\n";
        CVs += "Version 1.1\r\n";
        for(int i = 0; i < CV[0].length; i++) {
            if( CV[0][i] > 0 ) { // only write used CVs (CV[0][cv] != 0 ) to file
                CVs += "\r\nCV#" + CV[0][i] + " = " + CV[1][i];
            }
        }
        CVs += "\r\n\r\nKommentar:\r\n";
        CVs += jComment.getText();
        SaveOpenDialog od = new SaveOpenDialog( this, true, false, CVs, this, "fdr");
}//GEN-LAST:event_jSaveActionPerformed

    private void jOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jOpenActionPerformed
        // gespeicherte CVs werden gelesen
        jCV_Inhalt.setText("62");
        jCV_Anzeige.setSelectedItem( "CV#"+8);
        SaveOpenDialog od = new SaveOpenDialog( this, true, true, CVs, this, "fdr");
}//GEN-LAST:event_jOpenActionPerformed

    private void jDirekteingabeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDirekteingabeActionPerformed
        // hier wird die Direkteingabe (de)aktiviert
        String str = jDirekteingabe.getText();
        if(str.equals(bundle.getString("FD_LED.jDirekteingabe.text")))
        {
            jCV_Inhalt.setEditable(true);
            str = bundle.getBundle("my.KlarText/Bundle").getString("FD_LED.jDirekteingabe_aus.text");
            jDirekteingabe.setText(str);
        }
        else
        {
            jCV_Inhalt.setEditable(false);
            str = bundle.getBundle("my.KlarText/Bundle").getString("FD_LED.jDirekteingabe.text");
            jDirekteingabe.setText(str);
        }
        jCV_Inhalt.validate();
        jDirekteingabe.validate();
}//GEN-LAST:event_jDirekteingabeActionPerformed

    private void jCV_LesenSchreibenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCV_LesenSchreibenActionPerformed
        try {
            // Hier wird die Schnittstelle geöffnet und die CVs werden zur Zentrale gesendet
            ReadWriteCV cvwr = new ReadWriteCV(this, true, KTUI, CV);
        } catch (IOException ex) {
            KTUI.mbDeviceReadProblem( this );
        }
    }//GEN-LAST:event_jCV_LesenSchreibenActionPerformed

    private void jCV_AnzeigeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCV_AnzeigeActionPerformed
        int currCV = getCVfromIndexString(jCV_Anzeige, "CV#");
        jCV_Inhalt.setText("" + CV[1][currCV]);
    }//GEN-LAST:event_jCV_AnzeigeActionPerformed

    private void jF1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jF1StateChanged
        if(jF1.isSelected())
        {
            CV[1][13] |= 1;
        }
        else
        {
            CV[1][13] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+13 );
        jCV_Inhalt.setText("" + CV[1][13]);
    }//GEN-LAST:event_jF1StateChanged

    private void jF2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jF2StateChanged
         if(jF2.isSelected())
        {
            CV[1][13] |= 2;
        }
        else
        {
            CV[1][13] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+13 );
        jCV_Inhalt.setText("" + CV[1][13]);
    }//GEN-LAST:event_jF2StateChanged

    private void jF3StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jF3StateChanged
        if(jF3.isSelected())
        {
            CV[1][13] |= 4;
        }
        else
        {
            CV[1][13] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+13 );
        jCV_Inhalt.setText("" + CV[1][13]);
    }//GEN-LAST:event_jF3StateChanged

    private void jF4StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jF4StateChanged
        if(jF4.isSelected())
        {
            CV[1][13] |= 8;
        }
        else
        {
            CV[1][13] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+13 );
        jCV_Inhalt.setText("" + CV[1][13]);
    }//GEN-LAST:event_jF4StateChanged

    private void jF5StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jF5StateChanged
        if(jF5.isSelected())
        {
            CV[1][13] |= 16;
        }
        else
        {
            CV[1][13] &= ~16;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+13 );
        jCV_Inhalt.setText("" + CV[1][13]);
    }//GEN-LAST:event_jF5StateChanged

    private void jF6StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jF6StateChanged
        if(jF6.isSelected())
        {
            CV[1][13] |= 32;
        }
        else
        {
            CV[1][13] &= ~32;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+13 );
        jCV_Inhalt.setText("" + CV[1][13]);
    }//GEN-LAST:event_jF6StateChanged

    private void jF7StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jF7StateChanged
        if(jF7.isSelected())
        {
            CV[1][13] |= 64;
        }
        else
        {
            CV[1][13] &= ~64;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+13 );
        jCV_Inhalt.setText("" + CV[1][13]);
    }//GEN-LAST:event_jF7StateChanged

    private void jF8StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jF8StateChanged
        if(jF8.isSelected())
        {
            CV[1][13] |= 128;
        }
        else
        {
            CV[1][13] &= ~128;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+13 );
        jCV_Inhalt.setText("" + CV[1][13]);
    }//GEN-LAST:event_jF8StateChanged

    private void jCV29ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jCV29ComponentShown
        jCV_Anzeige.setSelectedItem( "CV#"+29);
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
    }//GEN-LAST:event_jCV29ComponentShown

    private void jRichtungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRichtungActionPerformed
        if(jRichtung.isSelected())
        {
            CV[1][29] |= 1;
        }
        else
        {
            CV[1][29] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+29 );
    }//GEN-LAST:event_jRichtungActionPerformed

    private void jFSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFSActionPerformed
        if(jFS.isSelected())
        {
            CV[1][29] |= 2;
        }
        else
        {
            CV[1][29] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+29 );
    }//GEN-LAST:event_jFSActionPerformed

    private void jAnalog1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAnalog1ActionPerformed
        if(jAnalog1.isSelected())
        {
            CV[1][29] |= 4;
        }
        else
        {
            CV[1][29] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+29 );
    }//GEN-LAST:event_jAnalog1ActionPerformed

    private void jRailComActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRailComActionPerformed
        if(jRailCom.isSelected())
        {
            CV[1][29] |= 8;
        }
        else
        {
            CV[1][29] &= ~8;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+29 );
    }//GEN-LAST:event_jRailComActionPerformed

    private void jLongAddrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jLongAddrActionPerformed
        if(jLongAddr.isSelected())
        {
            CV[1][29] |= 32;
            jKurzeAdr.setSelected(false);
            jlangeAdr.setSelected(true);
            int n = (CV[1][17] - 192)*256 + CV[1][18];
            jDecoderAdresse.setText( "" + n);
        }
        else
        {
            CV[1][29] &= ~32;
            jKurzeAdr.setSelected(true);
            jlangeAdr.setSelected(false);
            jDecoderAdresse.setText( "" + CV[1][1]);
        }
        jCV_Anzeige.setSelectedItem( "CV#"+29 );
    }//GEN-LAST:event_jLongAddrActionPerformed

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
    }//GEN-LAST:event_jFunctionMappingComponentShown

    private void jFL_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFL_1ActionPerformed
        if(jFL_1.isSelected())
        {
            CV[1][33] |= 1;
        }
        else
        {
            CV[1][33] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+33 );
    }//GEN-LAST:event_jFL_1ActionPerformed

    private void jFL_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFL_2ActionPerformed
        if(jFL_2.isSelected())
        {
            CV[1][33] |= 2;
        }
        else
        {
            CV[1][33] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+33 );
    }//GEN-LAST:event_jFL_2ActionPerformed

    private void jFR_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFR_1ActionPerformed
        if(jFR_1.isSelected())
        {
            CV[1][34] |= 1;
        }
        else
        {
            CV[1][34] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+34 );
    }//GEN-LAST:event_jFR_1ActionPerformed

    private void jFR_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFR_2ActionPerformed
        if(jFR_2.isSelected())
        {
            CV[1][34] |= 2;
        }
        else
        {
            CV[1][34] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+34 );
    }//GEN-LAST:event_jFR_2ActionPerformed

    private void jF1_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF1_1ActionPerformed
        if(jF1_1.isSelected())
        {
            CV[1][35] |= 1;
        }
        else
        {
            CV[1][35] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+35 );
    }//GEN-LAST:event_jF1_1ActionPerformed

    private void jF1_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF1_2ActionPerformed
        if(jF1_2.isSelected())
        {
            CV[1][35] |= 2;
        }
        else
        {
            CV[1][35] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+35 );
    }//GEN-LAST:event_jF1_2ActionPerformed

    private void jF2_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF2_1ActionPerformed
        if(jF2_1.isSelected())
        {
            CV[1][36] |= 1;
        }
        else
        {
            CV[1][36] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+36 );
    }//GEN-LAST:event_jF2_1ActionPerformed

    private void jF2_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF2_2ActionPerformed
        if(jF2_2.isSelected())
        {
            CV[1][36] |= 2;
        }
        else
        {
            CV[1][36] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+36 );
    }//GEN-LAST:event_jF2_2ActionPerformed

    private void jF3_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF3_1ActionPerformed
        if(jF3_1.isSelected())
        {
            CV[1][37] |= 1;
        }
        else
        {
            CV[1][37] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+37 );
    }//GEN-LAST:event_jF3_1ActionPerformed

    private void jF3_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF3_2ActionPerformed
        if(jF3_2.isSelected())
        {
            CV[1][37] |= 2;
        }
        else
        {
            CV[1][37] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+37 );
    }//GEN-LAST:event_jF3_2ActionPerformed

    private void jF4_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF4_1ActionPerformed
        if(jF4_1.isSelected())
        {
            CV[1][37] |= 1;
        }
        else
        {
            CV[1][37] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+37 );
    }//GEN-LAST:event_jF4_1ActionPerformed

    private void jF4_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF4_2ActionPerformed
        if(jF4_2.isSelected())
        {
            CV[1][38] |= 2;
        }
        else
        {
            CV[1][38] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+38 );
    }//GEN-LAST:event_jF4_2ActionPerformed

    private void jF5_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF5_1ActionPerformed
        if(jF5_1.isSelected())
        {
            CV[1][39] |= 1;
        }
        else
        {
            CV[1][39] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+39 );
    }//GEN-LAST:event_jF5_1ActionPerformed

    private void jF5_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF5_2ActionPerformed
        if(jF5_2.isSelected())
        {
            CV[1][39] |= 2;
        }
        else
        {
            CV[1][39] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+39 );
    }//GEN-LAST:event_jF5_2ActionPerformed

    private void jF6_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF6_1ActionPerformed
        if(jF6_1.isSelected())
        {
            CV[1][40] |= 1;
        }
        else
        {
            CV[1][40] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+40 );
    }//GEN-LAST:event_jF6_1ActionPerformed

    private void jF6_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF6_2ActionPerformed
        if(jF6_2.isSelected())
        {
            CV[1][40] |= 2;
        }
        else
        {
            CV[1][40] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+40 );
    }//GEN-LAST:event_jF6_2ActionPerformed

    private void jF7_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF7_1ActionPerformed
        if(jF7_1.isSelected())
        {
            CV[1][41] |= 1;
        }
        else
        {
            CV[1][41] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+41 );
    }//GEN-LAST:event_jF7_1ActionPerformed

    private void jF7_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF7_2ActionPerformed
        if(jF7_2.isSelected())
        {
            CV[1][41] |= 2;
        }
        else
        {
            CV[1][41] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+41 );
    }//GEN-LAST:event_jF7_2ActionPerformed

    private void jF8_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF8_1ActionPerformed
        if(jF8_1.isSelected())
        {
            CV[1][42] |= 1;
        }
        else
        {
            CV[1][42] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+42 );
    }//GEN-LAST:event_jF8_1ActionPerformed

    private void jF8_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF8_2ActionPerformed
        if(jF8_2.isSelected())
        {
            CV[1][42] |= 2;
        }
        else
        {
            CV[1][42] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+42 );
    }//GEN-LAST:event_jF8_2ActionPerformed

    private void jF9_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF9_1ActionPerformed
        if(jF9_1.isSelected())
        {
            CV[1][43] |= 1;
        }
        else
        {
            CV[1][43] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+43 );
    }//GEN-LAST:event_jF9_1ActionPerformed

    private void jF9_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF9_2ActionPerformed
        if(jF9_2.isSelected())
        {
            CV[1][43] |= 2;
        }
        else
        {
            CV[1][43] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+43 );
    }//GEN-LAST:event_jF9_2ActionPerformed

    private void jF10_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF10_1ActionPerformed
        if(jF10_1.isSelected())
        {
            CV[1][44] |= 1;
        }
        else
        {
            CV[1][44] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+44 );
    }//GEN-LAST:event_jF10_1ActionPerformed

    private void jF10_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF10_2ActionPerformed
        if(jF10_2.isSelected())
        {
            CV[1][44] |= 2;
        }
        else
        {
            CV[1][44] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+44 );
    }//GEN-LAST:event_jF10_2ActionPerformed

    private void jF11_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF11_1ActionPerformed
        if(jF11_1.isSelected())
        {
            CV[1][45] |= 1;
        }
        else
        {
            CV[1][45] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+45 );
    }//GEN-LAST:event_jF11_1ActionPerformed

    private void jF11_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF11_2ActionPerformed
        if(jF11_2.isSelected())
        {
            CV[1][45] |= 2;
        }
        else
        {
            CV[1][45] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+45 );
    }//GEN-LAST:event_jF11_2ActionPerformed

    private void jF12_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF12_1ActionPerformed
        if(jF12_1.isSelected())
        {
            CV[1][46] |= 1;
        }
        else
        {
            CV[1][46] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+46 );
    }//GEN-LAST:event_jF12_1ActionPerformed

    private void jF12_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF12_2ActionPerformed
        if(jF12_2.isSelected())
        {
            CV[1][46] |= 2;
        }
        else
        {
            CV[1][46] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+46 );
    }//GEN-LAST:event_jF12_2ActionPerformed

    private void jPanel2ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel2ComponentShown
        jCV_Anzeige.setSelectedItem( "CV#"+49 );
        jDimmen1.setText( "" + CV[1][49]);
        jDimmen2.setText( "" + CV[1][50]);
        jTast1.setText( "" + CV[1][61]);
        jTast2.setText( "" + CV[1][62]);
        if((CV[1][53] & 16) == 16)
        {
            jR_F3_1.setSelected(true);
        }
        else
        {
            jR_F3_1.setSelected(false);
        }
        if((CV[1][53] & 32) == 32)
        {
            jR_F4_1.setSelected(true);
        }
        else
        {
            jR_F4_1.setSelected(false);
        }

        if((CV[1][54] & 16) == 16)
        {
            jR_F3_2.setSelected(true);
        }
        else
        {
            jR_F3_2.setSelected(false);
        }
        if((CV[1][54] & 32) == 32)
        {
            jR_F4_2.setSelected(true);
        }
        else
        {
            jR_F4_2.setSelected(false);
        }

        if((CV[1][53] & 1) == 1)
        {
            jVor1.setSelected(false);
        }
        else
        {
            jVor1.setSelected(true);
        }
        if((CV[1][53] & 2) == 2)
        {
            jRueck1.setSelected(false);
        }
        else
        {
            jRueck1.setSelected(true);
        }

        if((CV[1][54] & 1) == 1)
        {
            jVor2.setSelected(false);
        }
        else
        {
            jVor2.setSelected(true);
        }
        if((CV[1][54] & 2) == 2)
        {
            jRück2.setSelected(false);
        }
        else
        {
            jRück2.setSelected(true);
        }
    }//GEN-LAST:event_jPanel2ComponentShown

    private void jR_F3_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jR_F3_1ActionPerformed
        if(jR_F3_1.isSelected())
        {
            CV[1][53] |= 16;
        }
        else
        {
            CV[1][53] &= ~16;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+53 );
    }//GEN-LAST:event_jR_F3_1ActionPerformed

    private void jR_F4_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jR_F4_1ActionPerformed
        if(jR_F4_1.isSelected())
        {
            CV[1][53] |= 32;
        }
        else
        {
            CV[1][53] &= ~32;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+53 );
    }//GEN-LAST:event_jR_F4_1ActionPerformed

    private void jR_F3_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jR_F3_2ActionPerformed
        if(jR_F3_2.isSelected())
        {
            CV[1][54] |= 16;
        }
        else
        {
            CV[1][54] &= ~16;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+54 );
    }//GEN-LAST:event_jR_F3_2ActionPerformed

    private void jR_F4_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jR_F4_2ActionPerformed
        if(jR_F4_2.isSelected())
        {
            CV[1][54] |= 32;
        }
        else
        {
            CV[1][54] &= ~32;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+54 );
    }//GEN-LAST:event_jR_F4_2ActionPerformed

    private void jVor1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jVor1ActionPerformed
        if(!jVor1.isSelected())
        {
            CV[1][53] |= 1;
        }
        else
        {
            CV[1][53] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+53 );
    }//GEN-LAST:event_jVor1ActionPerformed

    private void jRueck1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRueck1ActionPerformed
        if(!jRueck1.isSelected())
        {
            CV[1][53] |= 2;
        }
        else
        {
            CV[1][53] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+53 );
    }//GEN-LAST:event_jRueck1ActionPerformed

    private void jVor2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jVor2ActionPerformed
        if(!jVor2.isSelected())
        {
            CV[1][54] |= 1;
        }
        else
        {
            CV[1][54] &= ~1;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+54 );
    }//GEN-LAST:event_jVor2ActionPerformed

    private void jRück2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRück2ActionPerformed
        if(!jRück2.isSelected())
        {
            CV[1][54] |= 2;
        }
        else
        {
            CV[1][54] &= ~2;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+54 );
    }//GEN-LAST:event_jRück2ActionPerformed

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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        DecTest dt = new DecTest(this, true, KTUI);
        dt.jDecType.setText("Decoder: FD-R basic");
        if(jKurzeAdr.isSelected()) {
            dt.DecAddr = CV[1][1];
        } else {
            int n = (CV[1][17] - 192)*256 + CV[1][18];
            dt.DecAddr = n;
        }
        dt.setLocationRelativeTo(this);
        dt.setVisible(true);

    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        KTUI.frameInstanceDEVICE = null;
        KTUI.setFocus();
    }//GEN-LAST:event_formWindowClosed

    private void jDecoderAdresseFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDecoderAdresseFocusLost
        int j = KTUI.checkTextField( this, jDecoderAdresse, 1, 10239, 3, true );
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

    private void jDecoderAdresse1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDecoderAdresse1FocusLost
        CV[1][19] = KTUI.checkTextField( this, jDecoderAdresse1, 0, 127, 0, true);
        jCV_Anzeige.setSelectedItem( "CV#"+19 );
    }//GEN-LAST:event_jDecoderAdresse1FocusLost

    private void jCV_InhaltFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jCV_InhaltFocusLost
        int adr;
        int currCV = getCVfromIndexString(jCV_Anzeige, "CV#");
        int cvValue = KTUI.checkTextField( this, jCV_Inhalt, 0, 255, 22222, false);
        String s = jCV_Inhalt.getText();

        if (cvValue < 0 || cvValue > 255)
        {
            KTUI.mbValueNaN( this, 0, 255, true);
            jCV_Inhalt.setText("255");
            CV[1][currCV] = 255;
            return;
        }
        switch(currCV)
        {
            case 1: //CV#1
                cvValue = KTUI.checkTextField( this, jCV_Inhalt, 1, 255, 3, true );
                s = jCV_Inhalt.getText();
                if( cvValue > 127 ) {
                    KTUI.mbAdr128MMonly( this );
                }
                jDecoderAdresse.setText(s);
                jKurzeAdr.setSelected(true);
                jlangeAdr.setSelected(false);
                CV[1][currCV] = cvValue;
                break;

            case 13: //CV#13
                CV[1][13] = cvValue;
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

            case 17: //CV#17 [192..255]
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

            case 18: //CV#18 [0..255]
                CV[1][currCV] = cvValue;
                jKurzeAdr.setSelected(false);
                jlangeAdr.setSelected(true);
                adr = (CV[1][17] - 192)*256 + CV[1][18];
                jDecoderAdresse.setText(""+adr);
                break;

             case 19: //CV#19
                if (cvValue > 127)
                {
                    KTUI.mbValueConsist( this, 0, 127 );
                    cvValue = 127;
                    s = "127";
                    jCV_Inhalt.setText(s);
                }
                jDecoderAdresse1.setText(s);
                break;

             case 29: //CV#29
             case 114: //CV#114
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

                if((CV[1][29] & 32) == 32)
                    jLongAddr.setSelected(true);
                else
                    jLongAddr.setSelected(false);

                jMM_Addr_2.setText("" + CV[1][114]);
                break;

            case 49: //CV#49 [1..64]  22
            case 50: //CV#50 [1..64]  23
            case 53: //CV#53 [0..63]  24
            case 54: //CV#54 [0..63]  25
                if( currCV < 53 && ( cvValue < 1 || cvValue > 64 ) )
                {
                    KTUI.mbValueNaNcv( this, 1, 64, currCV, true);
                    cvValue = 64;
                    jCV_Inhalt.setText("64");
                }
                if( currCV >= 53 && ( cvValue < 0 || cvValue > 63 ) )
                {
                    KTUI.mbValueNaNcv( this, 0, 63, currCV, true);
                    cvValue = 0;
                    jCV_Inhalt.setText("0");
                }
                CV[1][currCV] = cvValue;

                jDimmen1.setText( "" + CV[1][49]);
                jDimmen2.setText( "" + CV[1][50]);
                jTast1.setText( "" + CV[1][61]);
                jTast2.setText( "" + CV[1][62]);
                if((CV[1][53] & 16) == 16)
                {
                    jR_F3_1.setSelected(true);
                }
                else
                {
                    jR_F3_1.setSelected(false);
                }
                if((CV[1][53] & 32) == 32)
                {
                    jR_F4_1.setSelected(true);
                }
                else
                {
                    jR_F4_1.setSelected(false);
                }

                if((CV[1][54] & 16) == 16)
                {
                    jR_F3_2.setSelected(true);
                }
                else
                {
                    jR_F3_2.setSelected(false);
                }
                if((CV[1][54] & 32) == 32)
                {
                    jR_F4_2.setSelected(true);
                }
                else
                {
                    jR_F4_2.setSelected(false);
                }

                if((CV[1][53] & 1) == 1)
                {
                    jVor1.setSelected(false);
                }
                else
                {
                    jVor1.setSelected(true);
                }
                if((CV[1][53] & 2) == 2)
                {
                    jRueck1.setSelected(false);
                }
                else
                {
                    jRueck1.setSelected(true);
                }

                if((CV[1][54] & 1) == 1)
                {
                    jVor2.setSelected(false);
                }
                else
                {
                    jVor2.setSelected(true);
                }
                if((CV[1][54] & 2) == 2)
                {
                    jRück2.setSelected(false);
                }
                else
                {
                    jRück2.setSelected(true);
                }
                break;

            case 112: //CV#112
                if (cvValue < 10)
                {
                    KTUI.mbValueNaNcv( this, 10, 255, 112, true);
                    cvValue = 10;
                    jCV_Inhalt.setText("10");
                }
                CV[1][112] = cvValue;
                jBlinkFrequenz.setText("" + cvValue);
                break;

            default:
                KTUI.mbGeneric( this, "Problem", "Unknown CV "+cvValue+" from selection", "s = "+s);
        }
        CV[1][currCV] = cvValue;
        if(currCV >= 33 && currCV <= 46)
        {
            if((CV[1][33] & 1) == 1)
                jFL_1.setSelected(true);
            else
                jFL_1.setSelected(false);

            if((CV[1][33] & 2) == 2)
                jFL_2.setSelected(true);
            else
                jFL_2.setSelected(false);

            if((CV[1][34] & 1) == 1)
                jFR_1.setSelected(true);
            else
                jFR_1.setSelected(false);

            if((CV[1][34] & 2) == 2)
                jFR_2.setSelected(true);
            else
                jFR_2.setSelected(false);

            if((CV[1][35] & 1) == 1)
                jF1_1.setSelected(true);
            else
                jF1_1.setSelected(false);

            if((CV[1][35] & 2) == 2)
                jF1_2.setSelected(true);
            else
                jF1_2.setSelected(false);

            if((CV[1][36] & 1) == 1)
                jF2_1.setSelected(true);
            else
                jF2_1.setSelected(false);

            if((CV[1][36] & 2) == 2)
                jF2_2.setSelected(true);
            else
                jF2_2.setSelected(false);

            if((CV[1][37] & 1) == 1)
                jF3_1.setSelected(true);
            else
                jF3_1.setSelected(false);

            if((CV[1][37] & 2) == 2)
                jF3_2.setSelected(true);
            else
                jF3_2.setSelected(false);

            if((CV[1][38] & 1) == 1)
                jF4_1.setSelected(true);
            else
                jF4_1.setSelected(false);

            if((CV[1][38] & 2) == 2)
                jF4_2.setSelected(true);
            else
                jF4_2.setSelected(false);

            if((CV[1][39] & 1) == 1)
                jF5_1.setSelected(true);
            else
                jF5_1.setSelected(false);

            if((CV[1][39] & 2) == 2)
                jF5_2.setSelected(true);
            else
                jF5_2.setSelected(false);

            if((CV[1][40] & 1) == 1)
                jF6_1.setSelected(true);
            else
                jF6_1.setSelected(false);

            if((CV[1][40] & 2) == 2)
                jF6_2.setSelected(true);
            else
                jF6_2.setSelected(false);

            if((CV[1][41] & 1) == 1)
                jF7_1.setSelected(true);
            else
                jF7_1.setSelected(false);

            if((CV[1][41] & 2) == 2)
                jF7_2.setSelected(true);
            else
                jF7_2.setSelected(false);

            if((CV[1][42] & 1) == 1)
                jF8_1.setSelected(true);
            else
                jF8_1.setSelected(false);

            if((CV[1][42] & 2) == 2)
                jF8_2.setSelected(true);
            else
                jF8_2.setSelected(false);

            if((CV[1][43] & 1) == 1)
                jF9_1.setSelected(true);
            else
                jF9_1.setSelected(false);

            if((CV[1][43] & 2) == 2)
                jF9_2.setSelected(true);
            else
                jF9_2.setSelected(false);

            if((CV[1][44] & 1) == 1)
                jF10_1.setSelected(true);
            else
                jF10_1.setSelected(false);

            if((CV[1][44] & 2) == 2)
                jF10_2.setSelected(true);
            else
                jF10_2.setSelected(false);

            if((CV[1][45] & 1) == 1)
                jF11_1.setSelected(true);
            else
                jF11_1.setSelected(false);

            if((CV[1][45] & 2) == 2)
                jF11_2.setSelected(true);
            else
                jF11_2.setSelected(false);

            if((CV[1][46] & 1) == 1)
                jF12_1.setSelected(true);
            else
                jF12_1.setSelected(false);

            if((CV[1][46] & 2) == 2)
                jF12_2.setSelected(true);
            else
                jF12_2.setSelected(false);

        }
        jCV_Inhalt.setText("" + cvValue);
    }//GEN-LAST:event_jCV_InhaltFocusLost

    private void jBlinkFrequenzFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlinkFrequenzFocusLost
        CV[1][112] = KTUI.checkTextField( this, jBlinkFrequenz, 10, 255, 48, true );
        jCV_Anzeige.setSelectedItem( "CV#"+112 );
    }//GEN-LAST:event_jBlinkFrequenzFocusLost

    private void jMM_Addr_2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jMM_Addr_2FocusLost
        CV[1][114] = KTUI.checkTextField( this, jMM_Addr_2, 1, 255, 4, true );
        jCV_Anzeige.setSelectedItem( "CV#"+114 );
    }//GEN-LAST:event_jMM_Addr_2FocusLost

    private void jDecoderAdresseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDecoderAdresseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jDecoderAdresseActionPerformed

    private void jAbbrechenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAbbrechenActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jAbbrechenActionPerformed

    private void jDimmen1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmen1FocusLost
        CV[1][49] = KTUI.checkTextField( this, jDimmen1, 1, 64, 64, true );
    }//GEN-LAST:event_jDimmen1FocusLost

    private void jDimmen2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmen2FocusLost
        CV[1][50] = KTUI.checkTextField( this, jDimmen2, 1, 64, 64, true );
    }//GEN-LAST:event_jDimmen2FocusLost

    private void jDimmen2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmen2FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+50 );
    }//GEN-LAST:event_jDimmen2FocusGained

    private void jDimmen1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmen1FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+49 );
    }//GEN-LAST:event_jDimmen1FocusGained

    private void jTast1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTast1FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+61 );
    }//GEN-LAST:event_jTast1FocusGained

    private void jTast1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTast1FocusLost
        CV[1][61] = KTUI.checkTextField( this, jTast1, 0, 255, 255, true );
    }//GEN-LAST:event_jTast1FocusLost

    private void jTast2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTast2FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+62 );
    }//GEN-LAST:event_jTast2FocusGained

    private void jTast2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTast2FocusLost
        CV[1][62] = KTUI.checkTextField( this, jTast2, 0, 255, 255, true );
    }//GEN-LAST:event_jTast2FocusLost

    private void jMM_Addr_2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jMM_Addr_2FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+114 );
    }//GEN-LAST:event_jMM_Addr_2FocusGained

    private void jDecoderAdresse1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDecoderAdresse1FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+19 );
    }//GEN-LAST:event_jDecoderAdresse1FocusGained

    private void jBlinkFrequenzFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlinkFrequenzFocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+112 );
    }//GEN-LAST:event_jBlinkFrequenzFocusGained

    private void jDecoderAdresseFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDecoderAdresseFocusGained
        if (jKurzeAdr.isSelected()) {
            jCV_Anzeige.setSelectedItem( "CV#"+1 );
        } else {
            jCV_Anzeige.setSelectedItem( "CV#"+17 );
        }
    }//GEN-LAST:event_jDecoderAdresseFocusGained

    private void jR_F3_1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jR_F3_1FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+53 );
    }//GEN-LAST:event_jR_F3_1FocusGained

    private void jR_F4_1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jR_F4_1FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+53 );
    }//GEN-LAST:event_jR_F4_1FocusGained

    private void jVor1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jVor1FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+53 );
    }//GEN-LAST:event_jVor1FocusGained

    private void jRueck1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jRueck1FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+53 );
    }//GEN-LAST:event_jRueck1FocusGained

    private void jR_F3_2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jR_F3_2FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+54 );
    }//GEN-LAST:event_jR_F3_2FocusGained

    private void jR_F4_2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jR_F4_2FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+54 );
    }//GEN-LAST:event_jR_F4_2FocusGained

    private void jVor2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jVor2FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+54 );
    }//GEN-LAST:event_jVor2FocusGained

    private void jRück2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jRück2FocusGained
        jCV_Anzeige.setSelectedItem( "CV#"+54 );
    }//GEN-LAST:event_jRück2FocusGained

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        updateTabs();
    }//GEN-LAST:event_formWindowActivated

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

    private void jTast1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTast1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTast1KeyReleased

    private void jTast2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTast2KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTast2KeyReleased

    private void jRC_SenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRC_SenderActionPerformed
        bFunktionsweise = true;
    }//GEN-LAST:event_jRC_SenderActionPerformed

    private void jFDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFDActionPerformed
        bFunktionsweise = false;
    }//GEN-LAST:event_jFDActionPerformed

    private void jFL_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFL_3ActionPerformed
         if(jFL_3.isSelected())
        {
            CV[1][33] |= 4;
        }
        else
        {
            CV[1][33] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+33 );
    }//GEN-LAST:event_jFL_3ActionPerformed

    private void jFR_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFR_3ActionPerformed
        if(jFR_3.isSelected())
        {
            CV[1][34] |= 4;
        }
        else
        {
            CV[1][34] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+34 );
    }//GEN-LAST:event_jFR_3ActionPerformed

    private void jF1_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF1_3ActionPerformed
        if(jF1_3.isSelected())
        {
            CV[1][35] |= 4;
        }
        else
        {
            CV[1][35] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+35 );
    }//GEN-LAST:event_jF1_3ActionPerformed

    private void jF2_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF2_3ActionPerformed
        if(jF2_3.isSelected())
        {
            CV[1][36] |= 4;
        }
        else
        {
            CV[1][36] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+36 );
    }//GEN-LAST:event_jF2_3ActionPerformed

    private void jF3_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF3_3ActionPerformed
        if(jF3_3.isSelected())
        {
            CV[1][37] |= 4;
        }
        else
        {
            CV[1][37] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+37 );
    }//GEN-LAST:event_jF3_3ActionPerformed

    private void jF4_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF4_3ActionPerformed
        if(jF4_3.isSelected())
        {
            CV[1][38] |= 4;
        }
        else
        {
            CV[1][38] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+38 );
    }//GEN-LAST:event_jF4_3ActionPerformed

    private void jF5_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF5_3ActionPerformed
        if(jF5_3.isSelected())
        {
            CV[1][39] |= 4;
        }
        else
        {
            CV[1][39] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+39 );
    }//GEN-LAST:event_jF5_3ActionPerformed

    private void jF6_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF6_3ActionPerformed
        if(jF6_3.isSelected())
        {
            CV[1][40] |= 4;
        }
        else
        {
            CV[1][40] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+40 );
    }//GEN-LAST:event_jF6_3ActionPerformed

    private void jF7_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF7_3ActionPerformed
        if(jF7_3.isSelected())
        {
            CV[1][41] |= 4;
        }
        else
        {
            CV[1][41] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+41 );
    }//GEN-LAST:event_jF7_3ActionPerformed

    private void jF8_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF8_3ActionPerformed
        if(jF8_3.isSelected())
        {
            CV[1][42] |= 4;
        }
        else
        {
            CV[1][42] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+42 );
    }//GEN-LAST:event_jF8_3ActionPerformed

    private void jF9_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF9_3ActionPerformed
        if(jF9_3.isSelected())
        {
            CV[1][43] |= 4;
        }
        else
        {
            CV[1][43] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+43 );
    }//GEN-LAST:event_jF9_3ActionPerformed

    private void jF10_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF10_3ActionPerformed
        if(jF10_3.isSelected())
        {
            CV[1][44] |= 4;
        }
        else
        {
            CV[1][44] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+44 );
    }//GEN-LAST:event_jF10_3ActionPerformed

    private void jF11_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF11_3ActionPerformed
        if(jF11_3.isSelected())
        {
            CV[1][45] |= 4;
        }
        else
        {
            CV[1][45] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+45 );
    }//GEN-LAST:event_jF11_3ActionPerformed

    private void jF12_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF12_3ActionPerformed
        if(jF12_3.isSelected())
        {
            CV[1][46] |= 4;
        }
        else
        {
            CV[1][46] &= ~4;
        }
        jCV_Anzeige.setSelectedItem( "CV#"+46 );
    }//GEN-LAST:event_jF12_3ActionPerformed

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
    private javax.swing.JLabel jBild;
    private javax.swing.JTextField jBlinkFrequenz;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jCV29;
    private javax.swing.JComboBox jCV_Anzeige;
    private javax.swing.JTextField jCV_Inhalt;
    private javax.swing.JButton jCV_LesenSchreiben;
    private javax.swing.JTextArea jComment;
    private javax.swing.JTextField jDecoderAdresse;
    private javax.swing.JTextField jDecoderAdresse1;
    private javax.swing.JTabbedPane jDecodereigenschaften;
    private javax.swing.JTextField jDimmen1;
    private javax.swing.JTextField jDimmen2;
    private javax.swing.JToggleButton jDirekteingabe;
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
    private javax.swing.JCheckBox jF13_1;
    private javax.swing.JCheckBox jF13_2;
    private javax.swing.JCheckBox jF13_3;
    private javax.swing.JCheckBox jF14_1;
    private javax.swing.JCheckBox jF14_2;
    private javax.swing.JCheckBox jF14_3;
    private javax.swing.JCheckBox jF15_1;
    private javax.swing.JCheckBox jF15_2;
    private javax.swing.JCheckBox jF15_3;
    private javax.swing.JCheckBox jF16_1;
    private javax.swing.JCheckBox jF16_2;
    private javax.swing.JCheckBox jF16_3;
    private javax.swing.JCheckBox jF17_1;
    private javax.swing.JCheckBox jF17_2;
    private javax.swing.JCheckBox jF17_3;
    private javax.swing.JCheckBox jF18_1;
    private javax.swing.JCheckBox jF18_2;
    private javax.swing.JCheckBox jF18_3;
    private javax.swing.JCheckBox jF19_1;
    private javax.swing.JCheckBox jF19_2;
    private javax.swing.JCheckBox jF19_3;
    private javax.swing.JCheckBox jF1_1;
    private javax.swing.JCheckBox jF1_2;
    private javax.swing.JCheckBox jF1_3;
    private javax.swing.JCheckBox jF2;
    private javax.swing.JCheckBox jF20_1;
    private javax.swing.JCheckBox jF20_2;
    private javax.swing.JCheckBox jF20_3;
    private javax.swing.JCheckBox jF21_1;
    private javax.swing.JCheckBox jF21_2;
    private javax.swing.JCheckBox jF21_3;
    private javax.swing.JCheckBox jF22_1;
    private javax.swing.JCheckBox jF22_2;
    private javax.swing.JCheckBox jF22_3;
    private javax.swing.JCheckBox jF23_1;
    private javax.swing.JCheckBox jF23_2;
    private javax.swing.JCheckBox jF23_3;
    private javax.swing.JCheckBox jF24_1;
    private javax.swing.JCheckBox jF24_2;
    private javax.swing.JCheckBox jF24_3;
    private javax.swing.JCheckBox jF25_1;
    private javax.swing.JCheckBox jF25_2;
    private javax.swing.JCheckBox jF25_3;
    private javax.swing.JCheckBox jF26_1;
    private javax.swing.JCheckBox jF26_2;
    private javax.swing.JCheckBox jF26_3;
    private javax.swing.JCheckBox jF27_1;
    private javax.swing.JCheckBox jF27_2;
    private javax.swing.JCheckBox jF27_3;
    private javax.swing.JCheckBox jF28_1;
    private javax.swing.JCheckBox jF28_2;
    private javax.swing.JCheckBox jF28_3;
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
    private javax.swing.JRadioButton jFD;
    private javax.swing.JCheckBox jFL_1;
    private javax.swing.JCheckBox jFL_2;
    private javax.swing.JCheckBox jFL_3;
    private javax.swing.JCheckBox jFR_1;
    private javax.swing.JCheckBox jFR_2;
    private javax.swing.JCheckBox jFR_3;
    private javax.swing.JCheckBox jFS;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JPanel jFunctionMapping;
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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelBlinkFrequenz;
    private javax.swing.JLabel jLabelBlinkFrequenz_t1;
    private javax.swing.JLabel jLabelBlinkFrequenz_t2;
    private javax.swing.JLabel jLabelMM_Addr_2;
    private javax.swing.JLabel jLabel_AUX;
    private javax.swing.JLabel jLabel_AUX1;
    private javax.swing.JLabel jLabel_AUX2;
    private javax.swing.JLabel jLabel_AUX3;
    private javax.swing.JLabel jLabel_AUX_1;
    private javax.swing.JLabel jLabel_AUX_2;
    private javax.swing.JLabel jLabel_AUX_3;
    private javax.swing.JLabel jLabel_F13;
    private javax.swing.JLabel jLabel_F14;
    private javax.swing.JLabel jLabel_F15;
    private javax.swing.JLabel jLabel_F16;
    private javax.swing.JLabel jLabel_F17;
    private javax.swing.JLabel jLabel_F18;
    private javax.swing.JLabel jLabel_F19;
    private javax.swing.JLabel jLabel_F20;
    private javax.swing.JLabel jLabel_F21;
    private javax.swing.JLabel jLabel_F22;
    private javax.swing.JLabel jLabel_F23;
    private javax.swing.JLabel jLabel_F24;
    private javax.swing.JLabel jLabel_F25;
    private javax.swing.JLabel jLabel_F26;
    private javax.swing.JLabel jLabel_F27;
    private javax.swing.JLabel jLabel_F28;
    private javax.swing.JCheckBox jLongAddr;
    private javax.swing.JCheckBox jLongAddr1;
    private javax.swing.JCheckBox jLongAddr2;
    private javax.swing.JTextField jMM_Addr_2;
    private javax.swing.JButton jOpen;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton jRC_Sender;
    private javax.swing.JCheckBox jR_F3_1;
    private javax.swing.JCheckBox jR_F3_2;
    private javax.swing.JCheckBox jR_F4_1;
    private javax.swing.JCheckBox jR_F4_2;
    private javax.swing.JCheckBox jRailCom;
    private javax.swing.JCheckBox jRichtung;
    private javax.swing.JCheckBox jRueck1;
    private javax.swing.JCheckBox jRück2;
    private javax.swing.JButton jSave;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTast1;
    private javax.swing.JTextField jTast2;
    private javax.swing.JCheckBox jVor1;
    private javax.swing.JCheckBox jVor2;
    private javax.swing.JRadioButton jlangeAdr;
    // End of variables declaration//GEN-END:variables

}
