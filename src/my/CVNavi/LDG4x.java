/*
 * LDG42.java
 *
 * Created on 09.02.2025 
 *
 * @author Kersten Tams Copyright 2009-2018
 * @author Lothar Roth  Copyright 2012-2018
 * @author Peter Fritze Copyright 2012-2025
 *
 */
package my.CVNavi;

import java.awt.Color;
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
public class LDG4x extends javax.swing.JFrame {

    private String CVs;
    private int CV[][] = new int[2][512];
    public CVNavi CVNavi;
    public String ReturnString = "Tams Elektronik";
    private ResourceBundle bundle;
    private int CVFktOffset = 0;
    private int selectedRange = 0;

    /**
     * Creates new form LDG42
     */
    public LDG4x(CVNavi cvnaviThis) {
        if (cvnaviThis == null) {
            return;
        }
        CVNavi = cvnaviThis;
        if (CVNavi.frameInstanceDEVICE != null) {
            CVNavi.frameInstanceDEVICE.toFront();
            CVNavi.frameInstanceDEVICE.repaint();
            return;
        }

        initComponents();
        bundle = java.util.ResourceBundle.getBundle("my.CVNavi/Bundle");
        ImageIcon II = null;
        TitledBorder b = (TitledBorder) jPanel1.getBorder();
        switch (CVNavi.Decoder) {
            case c.LD_G41:
                b.setTitle("LD-G-41");
                II = new ImageIcon(getClass().getResource("/LD-G-41.gif"));
                setTitle(CVNavi.getMenutext(decoderList.LD_G41).trim());
                break;
            case c.LD_G42:
                b.setTitle("LD-G-42");
                II = new ImageIcon(getClass().getResource("/LD-G-42.gif"));
                setTitle(CVNavi.getMenutext(decoderList.LD_G42).trim());
                break;
            case c.LD_W42:
                b.setTitle("LD-W-42");
                II = new ImageIcon(getClass().getResource("/LD-W-42.gif"));
                setTitle(CVNavi.getMenutext(decoderList.LD_W42).trim());
                break;
            case c.LD_G42_2:
                b.setTitle("LD-G-42.2");
                II = new ImageIcon(getClass().getResource("/LD-G-42-2.gif"));
                setTitle(CVNavi.getMenutext(decoderList.LD_G42_2).trim());
                break;
            case c.LD_W42_2:
                b.setTitle("LD-W-42.2");
                II = new ImageIcon(getClass().getResource("/LD-G-42-2.gif"));
                setTitle(CVNavi.getMenutext(decoderList.LD_W42_2).trim());
                break;
            case c.LD_G43:
                b.setTitle("LD-G-43");
                II = new ImageIcon(getClass().getResource("/LD-G-43.gif"));
                setTitle(CVNavi.getMenutext(decoderList.LD_G43).trim());
                break;
            case c.LD_G44:
                b.setTitle("LD-G-44");
                II = new ImageIcon(getClass().getResource("/LD-G-44.gif"));
                setTitle(CVNavi.getMenutext(decoderList.LD_G44).trim());
                break;
        }
        this.setIconImage(II.getImage());
        ImageIcon II2 = new ImageIcon(getClass().getResource("/FD-R_Ext_Bl1.gif"));
        this.setIconImage(II2.getImage());
        jBild1.setIcon(II);

        //---- CV-default-Werte -----
        // Vom Decoder verwendete CVs markieren und mit Default-Werten besetzen
        initCV(0, 0); // reset jCV_Anzeige (clean all entries)

        initCV(1, 3);
        initCV(2, 4);
        initCV(3, 10);
        initCV(4, 5);
        initCV(5, 255);
        initCV(6, 100);
        switch (CVNavi.Decoder) {
            case c.LD_G42:
                initCV(4, 8);
                break;
            case c.LD_W42:
                initCV(3, 8);
                initCV(4, 64);
                initCV(6, 128);
                break;
            case c.LD_W42_2:
                initCV(2, 40);
                break;
            case c.LD_G43:
            case c.LD_G44:
                initCV(3, 16);
                break;
        }
        initCV(8, 62);
        initCV(10, 0);
        initCV(11, 16);
        if (CVNavi.Decoder == c.LD_G41) {
            initCV(12, 53);
        } else {
            initCV(12, 53);
        }
        initCV(13, 0);
        initCV(14, 1);
        initCV(15, 3);
        initCV(16, 3);
        initCV(17, 192);
        initCV(18, 232);
        initCV(19, 0);
        switch (CVNavi.Decoder) {
            case c.LD_G41:
            case c.LD_G43:
            case c.LD_G44:
                initCV(20, 4);
                break;
        }

        initCV(21, 0);
        initCV(22, 0);
        switch (CVNavi.Decoder) {
            case c.LD_G41:
            case c.LD_G42_2:
            case c.LD_W42_2:
            case c.LD_G43:
            case c.LD_G44:
                initCV(27, 48);
                initCV(28, 131);
                break;
            case c.LD_G42:
            case c.LD_W42:
                initCV(27, 3);
                initCV(28, 3);
                break;
        }

        initCV(27, 3);
        initCV(28, 3);
        initCV(29, 14);
        initCV(30, 0);
        initCV(31, 0);   // Index für höhere CV-PAges ; Seite 44 // TODO  deaktivieren ?
        initCV(32, 42); // Index für höhere CV-PAges ; Seite 44 // TODO  deaktivieren ?
        initCV(47, 64);
        initCV(48, 64);
        switch (CVNavi.Decoder) {
            case c.LD_G41:
            case c.LD_G43:
            case c.LD_G44:
                initCV(49, 64);
                initCV(50, 64);
                initCV(51, 64);
                initCV(52, 64);
                initCV(53, 64);
                initCV(54, 64);
                break;
            case c.LD_G42_2:
            case c.LD_W42_2:
                initCV(49, 64);
                initCV(50, 64);
                break;
        }
        switch (CVNavi.Decoder) {
            case c.LD_G41:
            case c.LD_G42_2:
            case c.LD_W42_2:
            case c.LD_G43:
            case c.LD_G44:
                initCV(55, 0);
                initCV(56, 0);
                break;
        }
        initCV(57, 0);
        initCV(58, 0);

        for (int cv = 59; cv <= 62; cv++) { // Seite 67 Effekte der Ausgänge
            if (CVNavi.Decoder == c.LD_G42_2 || CVNavi.Decoder == c.LD_W42_2) {
                initCV(cv, 255);
            } else {
                initCV(cv, 0);
            }
        }
        if (CVNavi.Decoder == c.LD_G42 || CVNavi.Decoder == c.LD_W42) {
            initCV(63, 2);
        } else {
            initCV(63, 16);
        }
        if (CVNavi.Decoder == c.LD_G42_2 || CVNavi.Decoder == c.LD_W42_2) {
            initCV(64, 255);
        } else {
            initCV(64, 50);
        }

        switch (CVNavi.Decoder) {
            case c.LD_G41:
            case c.LD_G42_2:
            case c.LD_G43:
            case c.LD_G44:
                initCV(65, 0);
                break;
            case c.LD_G42:
            case c.LD_W42:
                initCV(65, 1);
                break;
            case c.LD_W42_2:
                initCV(65, 80);
                break;
        }
        initCV(66, 255);
        initCV(67, 2);
        initCV(68, 4);
        initCV(69, 6);
        initCV(70, 9);
        initCV(71, 12);
        initCV(72, 15);
        initCV(73, 19);
        initCV(74, 23);
        initCV(75, 28);
        initCV(76, 33);
        initCV(77, 39);
        initCV(78, 45);
        initCV(79, 54);
        initCV(80, 61);
        initCV(81, 69);
        initCV(82, 79);
        initCV(83, 89);
        initCV(84, 99);
        initCV(85, 110);
        initCV(86, 122);
        initCV(87, 134);
        initCV(88, 146);
        initCV(89, 160);
        initCV(90, 176);
        initCV(91, 194);
        initCV(92, 214);
        initCV(93, 250);
        initCV(94, 255);
        initCV(95, 255);
        initCV(96, 2);
        initCV(97, 0);
        switch (CVNavi.Decoder) {
            case c.LD_G41:
            case c.LD_G43:
            case c.LD_G44:
                initCV(98, 0);
                break;
            case c.LD_G42:
            case c.LD_W42:
            case c.LD_G42_2:
            case c.LD_W42_2:
                initCV(98, 255);
                break;
        }

        initCV(99, 32);
        if (CVNavi.Decoder != c.LD_G42 && CVNavi.Decoder != c.LD_W42) {
            initCV(100, 10);
        }

        for (int cv = 101; cv <= 104; cv++) { // Seite 51 Einstellung der Blinkfrequenz
            initCV(cv, 20);
        }
        initCV(101, 20);
        initCV(102, 20);

        if (CVNavi.Decoder == c.LD_G42_2 || CVNavi.Decoder == c.LD_W42_2) {
            initCV(103, 255);
            initCV(104, 255);
        } else {
            initCV(103, 20);
            initCV(104, 20);
        }
        if (CVNavi.Decoder != c.LD_G42 && CVNavi.Decoder != c.LD_W42) {
            initCV(105, 0);
            initCV(106, 0);
            initCV(107, 0);
            initCV(108, 0);
            initCV(109, 0);
            initCV(110, 5);
            initCV(111, 0);
        }

        switch (CVNavi.Decoder) {
            case c.LD_G41:
                initCV(112, 20);
                initCV(113, 32);
                initCV(114, 5);
                initCV(115, 4);
                initCV(116, 1);
                initCV(117, 255);
                initCV(118, 13);
                initCV(119, 5);
                initCV(120, 10);
                initCV(121, 4);
                initCV(122, 10);
                initCV(123, 255);
                initCV(124, 255);
                initCV(126, 255);
                initCV(127, 255);
                break;
            case c.LD_G42:
                initCV(112, 3);
                initCV(113, 32);
                initCV(114, 5);
                initCV(115, 4);
                initCV(116, 1);
                break;
            case c.LD_W42:
                initCV(112, 0);
                initCV(113, 0);
                initCV(114, 0);
                initCV(115, 0);
                initCV(116, 0);
                break;
            case c.LD_G42_2:
            case c.LD_W42_2:
                initCV(112, 23);
                initCV(113, 50);
                initCV(114, 5);
                initCV(115, 8);
                initCV(116, 1);
                initCV(117, 255);
                initCV(118, 255);
                initCV(119, 255);
                initCV(120, 255);
                initCV(121, 0);
                initCV(122, 10);
                initCV(123, 255);
                initCV(124, 255);
                initCV(126, 255);
                initCV(127, 255);
                break;
            case c.LD_G43:
            case c.LD_G44:
                initCV(112, 5);
                initCV(113, 32);
                initCV(114, 5);
                initCV(115, 4);
                initCV(116, 1);
                initCV(117, 255);
                initCV(118, 13);
                initCV(119, 38);
                initCV(120, 10);
                initCV(121, 4);
                initCV(122, 10);
                initCV(123, 255);
                initCV(124, 255);
                initCV(126, 255);
                initCV(127, 255);
                break;
        }

        for (int cv = 257; cv < 488; cv += 4) {
            initCV(cv, 0);
            initCV(cv + 1, 0);
            initCV(cv + 2, 0);
            initCV(cv + 3, 255);
        }

        //---------------------------
        switch (CVNavi.Decoder) {
            case c.LD_G42:
            case c.LD_W42:
            case c.LD_W42_2:
            case c.LD_G42_2:
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

                jF13_5.setVisible(false);
                jF13_6.setVisible(false);
                jF13_7.setVisible(false);
                jF13_8.setVisible(false);

                jF14_5.setVisible(false);
                jF14_6.setVisible(false);
                jF14_7.setVisible(false);
                jF14_8.setVisible(false);

                jF15_5.setVisible(false);
                jF15_6.setVisible(false);
                jF15_7.setVisible(false);
                jF15_8.setVisible(false);

                jF16_5.setVisible(false);
                jF16_6.setVisible(false);
                jF16_7.setVisible(false);
                jF16_8.setVisible(false);

                jLabel61.setVisible(false);
                jLabel60.setVisible(false);
                jLabel63.setVisible(false);
                jLabel62.setVisible(false);
                break;
        }

        switch (CVNavi.Decoder) {
            case c.LD_G42:
            case c.LD_W42:
            case c.LD_G41:
                jF1_USV.setVisible(false);
                jF2_USV.setVisible(false);
                jF3_USV.setVisible(false);
                jF4_USV.setVisible(false);
                jF5_USV.setVisible(false);
                jF6_USV.setVisible(false);
                jF7_USV.setVisible(false);
                jF8_USV.setVisible(false);
                jF9_USV.setVisible(false);
                jF10_USV.setVisible(false);
                jF11_USV.setVisible(false);
                jF12_USV.setVisible(false);
                jF13_USV.setVisible(false);
                jF14_USV.setVisible(false);
                jF15_USV.setVisible(false);
                jF16_USV.setVisible(false);
                jLabUSV.setVisible(false);
                break;
        }
        switch (CVNavi.Decoder) {
            case c.LD_G42:
            case c.LD_W42:
                jF1_Stop.setVisible(false);
                jF2_Stop.setVisible(false);
                jF3_Stop.setVisible(false);
                jF4_Stop.setVisible(false);
                jF5_Stop.setVisible(false);
                jF6_Stop.setVisible(false);
                jF7_Stop.setVisible(false);
                jF8_Stop.setVisible(false);
                jF9_Stop.setVisible(false);
                jF10_Stop.setVisible(false);
                jF11_Stop.setVisible(false);
                jF12_Stop.setVisible(false);
                jF13_Stop.setVisible(false);
                jF14_Stop.setVisible(false);
                jF15_Stop.setVisible(false);
                jF16_Stop.setVisible(false);
                jLabStop.setVisible(false);
                break;
        }

        // --------------------Effekte 2 -------------------------------
        setLocationRelativeTo(cvnaviThis);
        setVisible(true);
        CVNavi.frameInstanceDEVICE = this;

    }

    private Boolean initCV(int cv, int value) {
        if (cv == 0) {
            jCV_Anzeige.removeAllItems();
            return true;
        }
        if (value == -1) {
            jCV_Anzeige.removeItem("CV#" + cv);
            return (CVNavi.unsetCVvalue(CV, cv));
        }
        jCV_Anzeige.addItem("CV#" + cv);
        return (CVNavi.setCVvalue(CV, cv, value));
    }

    private void CVFktBits(int CVakt, int Mask, boolean CVstatus) {
        int CVIndex;
        CVIndex = CVakt + CVFktOffset - 1;
        if (CVstatus) {
            CV[1][CVIndex] |= Mask;
        } else {
            CV[1][CVIndex] &= ~Mask;
        }
        jCV_Anzeige.setSelectedItem("CV#" + CVIndex);
    }

    private void CVFktBitsDir(int CVakt, int Mask, boolean CVstatus) {
        if (CVstatus) {
            CV[1][CVakt] |= Mask;
        } else {
            CV[1][CVakt] &= ~Mask;
        }
        jCV_Anzeige.setSelectedItem("CV#" + CVakt);
    }

    private void initFunktionen() {
        int cvValue;
        int cvNum;
        selectedRange = jSelektFunktionen.getSelectedIndex();
        CVFktOffset = 257 + (selectedRange * 64);
        jLabF11.setVisible(true);
        jLabF12.setVisible(true);
        jLabF13.setVisible(true);
        jLabF14.setVisible(true);
        jLabF15.setVisible(true);
        jLabF16.setVisible(true);

        jF11_1.setVisible(true);
        jF11_2.setVisible(true);
        jF11_3.setVisible(true);
        jF11_4.setVisible(true);
        jF11_RG.setVisible(true);
        jF11_ABV.setVisible(true);
        jF11_Fkt.setVisible(true);
        jF12_1.setVisible(true);
        jF12_2.setVisible(true);
        jF12_3.setVisible(true);
        jF12_4.setVisible(true);
        jF12_RG.setVisible(true);
        jF12_ABV.setVisible(true);
        jF12_Fkt.setVisible(true);
        jF13_1.setVisible(true);
        jF13_2.setVisible(true);
        jF13_3.setVisible(true);
        jF13_4.setVisible(true);
        jF13_RG.setVisible(true);
        jF13_ABV.setVisible(true);
        jF13_Fkt.setVisible(true);
        jF14_1.setVisible(true);
        jF14_2.setVisible(true);
        jF14_3.setVisible(true);
        jF14_4.setVisible(true);
        jF14_RG.setVisible(true);
        jF14_ABV.setVisible(true);
        jF14_Fkt.setVisible(true);
        jF15_1.setVisible(true);
        jF15_2.setVisible(true);
        jF15_3.setVisible(true);
        jF15_4.setVisible(true);
        jF15_RG.setVisible(true);
        jF15_ABV.setVisible(true);
        jF15_Fkt.setVisible(true);
        jF16_1.setVisible(true);
        jF16_2.setVisible(true);
        jF16_3.setVisible(true);
        jF16_4.setVisible(true);
        jF16_RG.setVisible(true);
        jF16_ABV.setVisible(true);
        jF16_Fkt.setVisible(true);
        switch (CVNavi.Decoder) {

            case c.LD_G41:
            case c.LD_G43:
            case c.LD_G44:
                jF11_5.setVisible(true);
                jF11_6.setVisible(true);
                jF11_7.setVisible(true);
                jF11_8.setVisible(true);
                jF12_5.setVisible(true);
                jF12_6.setVisible(true);
                jF12_7.setVisible(true);
                jF12_8.setVisible(true);
                jF13_5.setVisible(true);
                jF13_6.setVisible(true);
                jF13_7.setVisible(true);
                jF13_8.setVisible(true);
                jF14_5.setVisible(true);
                jF14_6.setVisible(true);
                jF14_7.setVisible(true);
                jF14_8.setVisible(true);
                jF15_5.setVisible(true);
                jF15_6.setVisible(true);
                jF15_7.setVisible(true);
                jF15_8.setVisible(true);
                jF16_5.setVisible(true);
                jF16_6.setVisible(true);
                jF16_7.setVisible(true);
                jF16_8.setVisible(true);
                break;
        }
        switch (CVNavi.Decoder) {

            case c.LD_G41:
            case c.LD_G42_2:
            case c.LD_W42_2:
            case c.LD_G43:
            case c.LD_G44:
                jF11_Stop.setVisible(true);
                jF12_Stop.setVisible(true);
                jF13_Stop.setVisible(true);
                jF14_Stop.setVisible(true);
                jF15_Stop.setVisible(true);
                jF16_Stop.setVisible(true);
                break;
        }
        switch (CVNavi.Decoder) {

            case c.LD_G42_2:
            case c.LD_W42_2:
            case c.LD_G43:
            case c.LD_G44:
                jF11_USV.setVisible(true);
                jF12_USV.setVisible(true);
                jF13_USV.setVisible(true);
                jF14_USV.setVisible(true);
                jF15_USV.setVisible(true);
                jF16_USV.setVisible(true);
                break;
        }
        switch (selectedRange) {
            case 0:
                jLabF1.setText("F0f");
                jLabF2.setText("F0r");
                jLabF3.setText("F1f");
                jLabF4.setText("F1r");
                jLabF5.setText("F2f");
                jLabF6.setText("F2r");
                jLabF7.setText("F3f");
                jLabF8.setText("F3r");
                jLabF9.setText("F4f");
                jLabF10.setText("F4r");
                jLabF11.setText("F5f");
                jLabF12.setText("F5r");
                jLabF13.setText("F6f");
                jLabF14.setText("F6r");
                jLabF15.setText("F7f");
                jLabF16.setText("F7r");
                break;
            case 1:
                jLabF1.setText("F8f");
                jLabF2.setText("F8r");
                jLabF3.setText("F9f");
                jLabF4.setText("F9r");
                jLabF5.setText("F10f");
                jLabF6.setText("F10r");
                jLabF7.setText("F11f");
                jLabF8.setText("F11r");
                jLabF9.setText("F12f");
                jLabF10.setText("F12r");
                jLabF11.setText("F13f");
                jLabF12.setText("F13r");
                jLabF13.setText("F14f");
                jLabF14.setText("F14r");
                jLabF15.setText("F15f");
                jLabF16.setText("F15r");
                break;
            case 2:
                jLabF1.setText("F16f");
                jLabF2.setText("F16r");
                jLabF3.setText("F17f");
                jLabF4.setText("F17r");
                jLabF5.setText("F18f");
                jLabF6.setText("F18r");
                jLabF7.setText("F19f");
                jLabF8.setText("F19r");
                jLabF9.setText("F20f");
                jLabF10.setText("F20r");
                jLabF11.setText("F21f");
                jLabF12.setText("F21r");
                jLabF13.setText("F22f");
                jLabF14.setText("F22r");
                jLabF15.setText("F23f");
                jLabF16.setText("F23r");
                break;
            case 3:
                jLabF1.setText("F24f");
                jLabF2.setText("F24r");
                jLabF3.setText("F25f");
                jLabF4.setText("F25r");
                jLabF5.setText("F26f");
                jLabF6.setText("F26r");
                jLabF7.setText("F27f");
                jLabF8.setText("F27r");
                jLabF9.setText("F28f");
                jLabF10.setText("F28r");
                jLabF11.setVisible(false);
                jLabF12.setVisible(false);
                jLabF13.setVisible(false);
                jLabF14.setVisible(false);
                jLabF15.setVisible(false);
                jLabF16.setVisible(false);
                jF11_1.setVisible(false);
                jF11_2.setVisible(false);
                jF11_3.setVisible(false);
                jF11_4.setVisible(false);
                jF11_5.setVisible(false);
                jF11_6.setVisible(false);
                jF11_7.setVisible(false);
                jF11_8.setVisible(false);
                jF11_USV.setVisible(false);
                jF11_Stop.setVisible(false);
                jF11_RG.setVisible(false);
                jF11_ABV.setVisible(false);
                jF11_Fkt.setVisible(false);
                jF12_1.setVisible(false);
                jF12_2.setVisible(false);
                jF12_3.setVisible(false);
                jF12_4.setVisible(false);
                jF12_5.setVisible(false);
                jF12_6.setVisible(false);
                jF12_7.setVisible(false);
                jF12_8.setVisible(false);
                jF12_USV.setVisible(false);
                jF12_Stop.setVisible(false);
                jF12_RG.setVisible(false);
                jF12_ABV.setVisible(false);
                jF12_Fkt.setVisible(false);
                jF13_1.setVisible(false);
                jF13_2.setVisible(false);
                jF13_3.setVisible(false);
                jF13_4.setVisible(false);
                jF13_5.setVisible(false);
                jF13_6.setVisible(false);
                jF13_7.setVisible(false);
                jF13_8.setVisible(false);
                jF13_USV.setVisible(false);
                jF13_Stop.setVisible(false);
                jF13_RG.setVisible(false);
                jF13_ABV.setVisible(false);
                jF13_Fkt.setVisible(false);
                jF14_1.setVisible(false);
                jF14_2.setVisible(false);
                jF14_3.setVisible(false);
                jF14_4.setVisible(false);
                jF14_5.setVisible(false);
                jF14_6.setVisible(false);
                jF14_7.setVisible(false);
                jF14_8.setVisible(false);
                jF14_USV.setVisible(false);
                jF14_Stop.setVisible(false);
                jF14_RG.setVisible(false);
                jF14_ABV.setVisible(false);
                jF14_Fkt.setVisible(false);
                jF15_1.setVisible(false);
                jF15_2.setVisible(false);
                jF15_3.setVisible(false);
                jF15_4.setVisible(false);
                jF15_5.setVisible(false);
                jF15_6.setVisible(false);
                jF15_7.setVisible(false);
                jF15_8.setVisible(false);
                jF15_USV.setVisible(false);
                jF15_Stop.setVisible(false);
                jF15_RG.setVisible(false);
                jF15_ABV.setVisible(false);
                jF15_Fkt.setVisible(false);
                jF16_1.setVisible(false);
                jF16_2.setVisible(false);
                jF16_3.setVisible(false);
                jF16_4.setVisible(false);
                jF16_5.setVisible(false);
                jF16_6.setVisible(false);
                jF16_7.setVisible(false);
                jF16_8.setVisible(false);
                jF16_USV.setVisible(false);
                jF16_Stop.setVisible(false);
                jF16_RG.setVisible(false);
                jF16_ABV.setVisible(false);
                jF16_Fkt.setVisible(false);
                break;

        }
        // Offset für 8 Funktionen mit je 4 CVs       

        cvNum = CVFktOffset;
        //------------- F1 ------------------
        cvValue = CV[1][cvNum];
        jF1_1.setSelected((cvValue & 1) == 1);
        jF1_2.setSelected((cvValue & 2) == 2);
        jF1_3.setSelected((cvValue & 4) == 4);
        jF1_4.setSelected((cvValue & 8) == 8);
        jF1_5.setSelected((cvValue & 16) == 16);
        jF1_6.setSelected((cvValue & 32) == 32);
        jF1_7.setSelected((cvValue & 64) == 64);
        jF1_8.setSelected((cvValue & 128) == 128);
        cvNum += 2;
        cvValue = CV[1][cvNum];
        jF1_USV.setSelected((cvValue & 1) == 1);
        jF1_Stop.setSelected((cvValue & 2) == 2);
        jF1_RG.setSelected((cvValue & 4) == 4);
        jF1_ABV.setSelected((cvValue & 8) == 8);
        cvNum += 1;
        jF1_Fkt.setText("" + CV[1][cvNum]);
        cvNum += 1;
        //------------- F2 ------------------
        cvValue = CV[1][cvNum];
        jF2_1.setSelected((cvValue & 1) == 1);
        jF2_2.setSelected((cvValue & 2) == 2);
        jF2_3.setSelected((cvValue & 4) == 4);
        jF2_4.setSelected((cvValue & 8) == 8);
        jF2_5.setSelected((cvValue & 16) == 16);
        jF2_6.setSelected((cvValue & 32) == 32);
        jF2_7.setSelected((cvValue & 64) == 64);
        jF2_8.setSelected((cvValue & 128) == 128);
        cvNum += 2;
        cvValue = CV[1][cvNum];
        jF2_USV.setSelected((cvValue & 1) == 1);
        jF2_Stop.setSelected((cvValue & 2) == 2);
        jF2_RG.setSelected((cvValue & 4) == 4);
        jF2_ABV.setSelected((cvValue & 8) == 8);
        cvNum += 1;
        jF2_Fkt.setText("" + CV[1][cvNum]);
        cvNum += 1;

        //------------- F3 ------------------
        cvValue = CV[1][cvNum];
        jF3_1.setSelected((cvValue & 1) == 1);
        jF3_2.setSelected((cvValue & 2) == 2);
        jF3_3.setSelected((cvValue & 4) == 4);
        jF3_4.setSelected((cvValue & 8) == 8);
        jF3_5.setSelected((cvValue & 16) == 16);
        jF3_6.setSelected((cvValue & 32) == 32);
        jF3_7.setSelected((cvValue & 64) == 64);
        jF3_8.setSelected((cvValue & 128) == 128);
        cvNum += 2;
        cvValue = CV[1][cvNum];
        jF3_USV.setSelected((cvValue & 1) == 1);
        jF3_Stop.setSelected((cvValue & 2) == 2);
        jF3_RG.setSelected((cvValue & 4) == 4);
        jF3_ABV.setSelected((cvValue & 8) == 8);
        cvNum += 1;
        jF3_Fkt.setText("" + CV[1][cvNum]);
        cvNum += 1;

        //------------- F4 ------------------
        cvValue = CV[1][cvNum];
        jF4_1.setSelected((cvValue & 1) == 1);
        jF4_2.setSelected((cvValue & 2) == 2);
        jF4_3.setSelected((cvValue & 4) == 4);
        jF4_4.setSelected((cvValue & 8) == 8);
        jF4_5.setSelected((cvValue & 16) == 16);
        jF4_6.setSelected((cvValue & 32) == 32);
        jF4_7.setSelected((cvValue & 64) == 64);
        jF4_8.setSelected((cvValue & 128) == 128);
        cvNum += 2;
        cvValue = CV[1][cvNum];
        jF4_USV.setSelected((cvValue & 1) == 1);
        jF4_Stop.setSelected((cvValue & 2) == 2);
        jF4_RG.setSelected((cvValue & 4) == 4);
        jF4_ABV.setSelected((cvValue & 8) == 8);
        cvNum += 1;
        jF4_Fkt.setText("" + CV[1][cvNum]);
        cvNum += 1;

        //------------- F5 ------------------
        cvValue = CV[1][cvNum];
        jF5_1.setSelected((cvValue & 1) == 1);
        jF5_2.setSelected((cvValue & 2) == 2);
        jF5_3.setSelected((cvValue & 4) == 4);
        jF5_4.setSelected((cvValue & 8) == 8);
        jF5_5.setSelected((cvValue & 16) == 16);
        jF5_6.setSelected((cvValue & 32) == 32);
        jF5_7.setSelected((cvValue & 64) == 64);
        jF5_8.setSelected((cvValue & 128) == 128);
        cvNum += 2;
        cvValue = CV[1][cvNum];
        jF5_USV.setSelected((cvValue & 1) == 1);
        jF5_Stop.setSelected((cvValue & 2) == 2);
        jF5_RG.setSelected((cvValue & 4) == 4);
        jF5_ABV.setSelected((cvValue & 8) == 8);
        cvNum += 1;
        jF5_Fkt.setText("" + CV[1][cvNum]);
        cvNum += 1;

        //------------- F6 ------------------
        cvValue = CV[1][cvNum];
        jF6_1.setSelected((cvValue & 1) == 1);
        jF6_2.setSelected((cvValue & 2) == 2);
        jF6_3.setSelected((cvValue & 4) == 4);
        jF6_4.setSelected((cvValue & 8) == 8);
        jF6_5.setSelected((cvValue & 16) == 16);
        jF6_6.setSelected((cvValue & 32) == 32);
        jF6_7.setSelected((cvValue & 64) == 64);
        jF6_8.setSelected((cvValue & 128) == 128);
        cvNum += 2;
        cvValue = CV[1][cvNum];
        jF6_USV.setSelected((cvValue & 1) == 1);
        jF6_Stop.setSelected((cvValue & 2) == 2);
        jF6_RG.setSelected((cvValue & 4) == 4);
        jF6_ABV.setSelected((cvValue & 8) == 8);
        cvNum += 1;
        jF6_Fkt.setText("" + CV[1][cvNum]);
        cvNum += 1;

        //------------- F7 ------------------
        cvValue = CV[1][cvNum];
        jF7_1.setSelected((cvValue & 1) == 1);
        jF7_2.setSelected((cvValue & 2) == 2);
        jF7_3.setSelected((cvValue & 4) == 4);
        jF7_4.setSelected((cvValue & 8) == 8);
        jF7_5.setSelected((cvValue & 16) == 16);
        jF7_6.setSelected((cvValue & 32) == 32);
        jF7_7.setSelected((cvValue & 64) == 64);
        jF7_8.setSelected((cvValue & 128) == 128);
        cvNum += 2;
        cvValue = CV[1][cvNum];
        jF7_USV.setSelected((cvValue & 1) == 1);
        jF7_Stop.setSelected((cvValue & 2) == 2);
        jF7_RG.setSelected((cvValue & 4) == 4);
        jF7_ABV.setSelected((cvValue & 8) == 8);
        cvNum += 1;
        jF7_Fkt.setText("" + CV[1][cvNum]);
        cvNum += 1;

        //------------- F8 ------------------
        cvValue = CV[1][cvNum];
        jF8_1.setSelected((cvValue & 1) == 1);
        jF8_2.setSelected((cvValue & 2) == 2);
        jF8_3.setSelected((cvValue & 4) == 4);
        jF8_4.setSelected((cvValue & 8) == 8);
        jF8_5.setSelected((cvValue & 16) == 16);
        jF8_6.setSelected((cvValue & 32) == 32);
        jF8_7.setSelected((cvValue & 64) == 64);
        jF8_8.setSelected((cvValue & 128) == 128);
        cvNum += 2;
        cvValue = CV[1][cvNum];
        jF8_USV.setSelected((cvValue & 1) == 1);
        jF8_Stop.setSelected((cvValue & 2) == 2);
        jF8_RG.setSelected((cvValue & 4) == 4);
        jF8_ABV.setSelected((cvValue & 8) == 8);
        cvNum += 1;
        jF8_Fkt.setText("" + CV[1][cvNum]);
        cvNum += 1;

        //------------- F9 ------------------
        cvValue = CV[1][cvNum];
        jF9_1.setSelected((cvValue & 1) == 1);
        jF9_2.setSelected((cvValue & 2) == 2);
        jF9_3.setSelected((cvValue & 4) == 4);
        jF9_4.setSelected((cvValue & 8) == 8);
        jF9_5.setSelected((cvValue & 16) == 16);
        jF9_6.setSelected((cvValue & 32) == 32);
        jF9_7.setSelected((cvValue & 64) == 64);
        jF9_8.setSelected((cvValue & 128) == 128);
        cvNum += 2;
        cvValue = CV[1][cvNum];
        jF9_USV.setSelected((cvValue & 1) == 1);
        jF9_Stop.setSelected((cvValue & 2) == 2);
        jF9_RG.setSelected((cvValue & 4) == 4);
        jF9_ABV.setSelected((cvValue & 8) == 8);
        cvNum += 1;
        jF9_Fkt.setText("" + CV[1][cvNum]);
        cvNum += 1;

        //------------- F10 ------------------
        cvValue = CV[1][cvNum];
        jF10_1.setSelected((cvValue & 1) == 1);
        jF10_2.setSelected((cvValue & 2) == 2);
        jF10_3.setSelected((cvValue & 4) == 4);
        jF10_4.setSelected((cvValue & 8) == 8);
        jF10_5.setSelected((cvValue & 16) == 16);
        jF10_6.setSelected((cvValue & 32) == 32);
        jF10_7.setSelected((cvValue & 64) == 64);
        jF10_8.setSelected((cvValue & 128) == 128);
        cvNum += 2;
        cvValue = CV[1][cvNum];
        jF10_USV.setSelected((cvValue & 1) == 1);
        jF10_Stop.setSelected((cvValue & 2) == 2);
        jF10_RG.setSelected((cvValue & 4) == 4);
        jF10_ABV.setSelected((cvValue & 8) == 8);
        cvNum += 1;
        jF10_Fkt.setText("" + CV[1][cvNum]);
        cvNum += 1;

        if (selectedRange < 3) {
            //------------- F11 ------------------
            cvValue = CV[1][cvNum];
            jF11_1.setSelected((cvValue & 1) == 1);
            jF11_2.setSelected((cvValue & 2) == 2);
            jF11_3.setSelected((cvValue & 4) == 4);
            jF11_4.setSelected((cvValue & 8) == 8);
            jF11_5.setSelected((cvValue & 16) == 16);
            jF11_6.setSelected((cvValue & 32) == 32);
            jF11_7.setSelected((cvValue & 64) == 64);
            jF11_8.setSelected((cvValue & 128) == 128);
            cvNum += 2;
            cvValue = CV[1][cvNum];
            jF11_USV.setSelected((cvValue & 1) == 1);
            jF11_Stop.setSelected((cvValue & 2) == 2);
            jF11_RG.setSelected((cvValue & 4) == 4);
            jF11_ABV.setSelected((cvValue & 8) == 8);
            cvNum += 1;
            jF11_Fkt.setText("" + CV[1][cvNum]);
            cvNum += 1;

            //------------- F12 ------------------
            cvValue = CV[1][cvNum];
            jF12_1.setSelected((cvValue & 1) == 1);
            jF12_2.setSelected((cvValue & 2) == 2);
            jF12_3.setSelected((cvValue & 4) == 4);
            jF12_4.setSelected((cvValue & 8) == 8);
            jF12_5.setSelected((cvValue & 16) == 16);
            jF12_6.setSelected((cvValue & 32) == 32);
            jF12_7.setSelected((cvValue & 64) == 64);
            jF12_8.setSelected((cvValue & 128) == 128);
            cvNum += 2;
            cvValue = CV[1][cvNum];
            jF12_USV.setSelected((cvValue & 1) == 1);
            jF12_Stop.setSelected((cvValue & 2) == 2);
            jF12_RG.setSelected((cvValue & 4) == 4);
            jF12_ABV.setSelected((cvValue & 8) == 8);
            cvNum += 1;
            jF12_Fkt.setText("" + CV[1][cvNum]);
            cvNum += 1;

            //------------- F13 ------------------
            cvValue = CV[1][cvNum];
            jF13_1.setSelected((cvValue & 1) == 1);
            jF13_2.setSelected((cvValue & 2) == 2);
            jF13_3.setSelected((cvValue & 4) == 4);
            jF13_4.setSelected((cvValue & 8) == 8);
            jF13_5.setSelected((cvValue & 16) == 16);
            jF13_6.setSelected((cvValue & 32) == 32);
            jF13_7.setSelected((cvValue & 64) == 64);
            jF13_8.setSelected((cvValue & 128) == 128);
            cvNum += 2;
            cvValue = CV[1][cvNum];
            jF13_USV.setSelected((cvValue & 1) == 1);
            jF13_Stop.setSelected((cvValue & 2) == 2);
            jF13_RG.setSelected((cvValue & 4) == 4);
            jF13_ABV.setSelected((cvValue & 8) == 8);
            cvNum += 1;
            jF13_Fkt.setText("" + CV[1][cvNum]);
            cvNum += 1;

            //------------- F14 ------------------
            cvValue = CV[1][cvNum];
            jF14_1.setSelected((cvValue & 1) == 1);
            jF14_2.setSelected((cvValue & 2) == 2);
            jF14_3.setSelected((cvValue & 4) == 4);
            jF14_4.setSelected((cvValue & 8) == 8);
            jF14_5.setSelected((cvValue & 16) == 16);
            jF14_6.setSelected((cvValue & 32) == 32);
            jF14_7.setSelected((cvValue & 64) == 64);
            jF14_8.setSelected((cvValue & 128) == 128);
            cvNum += 2;
            cvValue = CV[1][cvNum];
            jF14_USV.setSelected((cvValue & 1) == 1);
            jF14_Stop.setSelected((cvValue & 2) == 2);
            jF14_RG.setSelected((cvValue & 4) == 4);
            jF14_ABV.setSelected((cvValue & 8) == 8);
            cvNum += 1;
            jF14_Fkt.setText("" + CV[1][cvNum]);
            cvNum += 1;

            //------------- F15 ------------------
            cvValue = CV[1][cvNum];
            jF15_1.setSelected((cvValue & 1) == 1);
            jF15_2.setSelected((cvValue & 2) == 2);
            jF15_3.setSelected((cvValue & 4) == 4);
            jF15_4.setSelected((cvValue & 8) == 8);
            jF15_5.setSelected((cvValue & 16) == 16);
            jF15_6.setSelected((cvValue & 32) == 32);
            jF15_7.setSelected((cvValue & 64) == 64);
            cvNum += 2;
            cvValue = CV[1][cvNum];
            jF15_USV.setSelected((cvValue & 1) == 1);
            jF15_Stop.setSelected((cvValue & 2) == 2);
            jF15_RG.setSelected((cvValue & 4) == 4);
            jF15_ABV.setSelected((cvValue & 8) == 8);
            cvNum += 1;
            jF15_Fkt.setText("" + CV[1][cvNum]);
            cvNum += 1;

            //------------- F16 ------------------
            cvValue = CV[1][cvNum];
            jF16_1.setSelected((cvValue & 1) == 1);
            jF16_2.setSelected((cvValue & 2) == 2);
            jF16_3.setSelected((cvValue & 4) == 4);
            jF16_4.setSelected((cvValue & 8) == 8);
            jF16_5.setSelected((cvValue & 16) == 16);
            jF16_6.setSelected((cvValue & 32) == 32);
            jF16_7.setSelected((cvValue & 64) == 64);
            cvNum += 2;
            cvValue = CV[1][cvNum];
            jF16_USV.setSelected((cvValue & 1) == 1);
            jF16_Stop.setSelected((cvValue & 2) == 2);
            jF16_RG.setSelected((cvValue & 4) == 4);
            jF16_ABV.setSelected((cvValue & 8) == 8);
            cvNum += 1;
            jF16_Fkt.setText("" + CV[1][cvNum]);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
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
        jF1_1 = new javax.swing.JCheckBox();
        jF16_6 = new javax.swing.JCheckBox();
        jF3_1 = new javax.swing.JCheckBox();
        jF4_1 = new javax.swing.JCheckBox();
        jF5_1 = new javax.swing.JCheckBox();
        jF6_1 = new javax.swing.JCheckBox();
        jF7_1 = new javax.swing.JCheckBox();
        jF12_6 = new javax.swing.JCheckBox();
        jF1_2 = new javax.swing.JCheckBox();
        jF2_2 = new javax.swing.JCheckBox();
        jF3_2 = new javax.swing.JCheckBox();
        jF4_2 = new javax.swing.JCheckBox();
        jF5_2 = new javax.swing.JCheckBox();
        jF6_2 = new javax.swing.JCheckBox();
        jF11_2 = new javax.swing.JCheckBox();
        jF13_1 = new javax.swing.JCheckBox();
        jLabF1 = new javax.swing.JLabel();
        jLabF2 = new javax.swing.JLabel();
        jLabF3 = new javax.swing.JLabel();
        jLabF4 = new javax.swing.JLabel();
        jLabF5 = new javax.swing.JLabel();
        jLabF6 = new javax.swing.JLabel();
        jLabF7 = new javax.swing.JLabel();
        jLabF8 = new javax.swing.JLabel();
        jLabF9 = new javax.swing.JLabel();
        jLabF10 = new javax.swing.JLabel();
        jLabF11 = new javax.swing.JLabel();
        jLabF13 = new javax.swing.JLabel();
        jLabF16 = new javax.swing.JLabel();
        jF1_3 = new javax.swing.JCheckBox();
        jF2_3 = new javax.swing.JCheckBox();
        jF3_3 = new javax.swing.JCheckBox();
        jF4_3 = new javax.swing.JCheckBox();
        jF5_3 = new javax.swing.JCheckBox();
        jF6_3 = new javax.swing.JCheckBox();
        jF11_3 = new javax.swing.JCheckBox();
        jF13_2 = new javax.swing.JCheckBox();
        jF1_4 = new javax.swing.JCheckBox();
        jF2_4 = new javax.swing.JCheckBox();
        jF3_4 = new javax.swing.JCheckBox();
        jF4_4 = new javax.swing.JCheckBox();
        jF5_4 = new javax.swing.JCheckBox();
        jF6_4 = new javax.swing.JCheckBox();
        jF11_4 = new javax.swing.JCheckBox();
        jF13_3 = new javax.swing.JCheckBox();
        jF1_5 = new javax.swing.JCheckBox();
        jF2_5 = new javax.swing.JCheckBox();
        jF3_5 = new javax.swing.JCheckBox();
        jF4_5 = new javax.swing.JCheckBox();
        jF5_5 = new javax.swing.JCheckBox();
        jF6_5 = new javax.swing.JCheckBox();
        jF11_5 = new javax.swing.JCheckBox();
        jF13_4 = new javax.swing.JCheckBox();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jF1_6 = new javax.swing.JCheckBox();
        jF2_6 = new javax.swing.JCheckBox();
        jF3_6 = new javax.swing.JCheckBox();
        jF4_6 = new javax.swing.JCheckBox();
        jF5_6 = new javax.swing.JCheckBox();
        jF6_6 = new javax.swing.JCheckBox();
        jF11_6 = new javax.swing.JCheckBox();
        jF13_5 = new javax.swing.JCheckBox();
        jF16_1 = new javax.swing.JCheckBox();
        jF2_1 = new javax.swing.JCheckBox();
        jF7_2 = new javax.swing.JCheckBox();
        jF7_3 = new javax.swing.JCheckBox();
        jF7_4 = new javax.swing.JCheckBox();
        jF7_5 = new javax.swing.JCheckBox();
        jF12_1 = new javax.swing.JCheckBox();
        jF13_6 = new javax.swing.JCheckBox();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jF16_2 = new javax.swing.JCheckBox();
        jF7_6 = new javax.swing.JCheckBox();
        jF8_1 = new javax.swing.JCheckBox();
        jF8_2 = new javax.swing.JCheckBox();
        jF8_3 = new javax.swing.JCheckBox();
        jF8_4 = new javax.swing.JCheckBox();
        jF12_2 = new javax.swing.JCheckBox();
        jF14_1 = new javax.swing.JCheckBox();
        jF16_3 = new javax.swing.JCheckBox();
        jF8_5 = new javax.swing.JCheckBox();
        jF8_6 = new javax.swing.JCheckBox();
        jF9_1 = new javax.swing.JCheckBox();
        jF9_2 = new javax.swing.JCheckBox();
        jF9_3 = new javax.swing.JCheckBox();
        jF12_3 = new javax.swing.JCheckBox();
        jF14_2 = new javax.swing.JCheckBox();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jF16_4 = new javax.swing.JCheckBox();
        jF9_4 = new javax.swing.JCheckBox();
        jF9_5 = new javax.swing.JCheckBox();
        jF9_6 = new javax.swing.JCheckBox();
        jF10_1 = new javax.swing.JCheckBox();
        jF10_2 = new javax.swing.JCheckBox();
        jF12_4 = new javax.swing.JCheckBox();
        jF14_3 = new javax.swing.JCheckBox();
        jF14_6 = new javax.swing.JCheckBox();
        jF15_6 = new javax.swing.JCheckBox();
        jF15_5 = new javax.swing.JCheckBox();
        jF16_5 = new javax.swing.JCheckBox();
        jF10_3 = new javax.swing.JCheckBox();
        jF10_4 = new javax.swing.JCheckBox();
        jF10_5 = new javax.swing.JCheckBox();
        jF10_6 = new javax.swing.JCheckBox();
        jF11_1 = new javax.swing.JCheckBox();
        jF12_5 = new javax.swing.JCheckBox();
        jF14_4 = new javax.swing.JCheckBox();
        jF14_5 = new javax.swing.JCheckBox();
        jF15_1 = new javax.swing.JCheckBox();
        jF15_2 = new javax.swing.JCheckBox();
        jF15_3 = new javax.swing.JCheckBox();
        jF15_4 = new javax.swing.JCheckBox();
        jLabF14 = new javax.swing.JLabel();
        jLabF15 = new javax.swing.JLabel();
        jLabF12 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel177 = new javax.swing.JLabel();
        jLabel178 = new javax.swing.JLabel();
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
        jF13_8 = new javax.swing.JCheckBox();
        jF14_7 = new javax.swing.JCheckBox();
        jF14_8 = new javax.swing.JCheckBox();
        jF15_7 = new javax.swing.JCheckBox();
        jF16_7 = new javax.swing.JCheckBox();
        jF15_8 = new javax.swing.JCheckBox();
        jF16_8 = new javax.swing.JCheckBox();
        jF1_RG = new javax.swing.JCheckBox();
        jLabRGan = new javax.swing.JLabel();
        jLabABVaus = new javax.swing.JLabel();
        jF1_ABV = new javax.swing.JCheckBox();
        jLabFkt = new javax.swing.JLabel();
        jF1_Fkt = new javax.swing.JTextField();
        jF2_RG = new javax.swing.JCheckBox();
        jF3_RG = new javax.swing.JCheckBox();
        jF4_RG = new javax.swing.JCheckBox();
        jF5_RG = new javax.swing.JCheckBox();
        jF6_RG = new javax.swing.JCheckBox();
        jF7_RG = new javax.swing.JCheckBox();
        jF8_RG = new javax.swing.JCheckBox();
        jF9_RG = new javax.swing.JCheckBox();
        jF10_RG = new javax.swing.JCheckBox();
        jF11_RG = new javax.swing.JCheckBox();
        jF12_RG = new javax.swing.JCheckBox();
        jF13_RG = new javax.swing.JCheckBox();
        jF14_RG = new javax.swing.JCheckBox();
        jF15_RG = new javax.swing.JCheckBox();
        jF16_RG = new javax.swing.JCheckBox();
        jF2_ABV = new javax.swing.JCheckBox();
        jF3_ABV = new javax.swing.JCheckBox();
        jLabUSV = new javax.swing.JLabel();
        jLabStop = new javax.swing.JLabel();
        jF1_Stop = new javax.swing.JCheckBox();
        jF1_USV = new javax.swing.JCheckBox();
        jF4_ABV = new javax.swing.JCheckBox();
        jF5_ABV = new javax.swing.JCheckBox();
        jF6_ABV = new javax.swing.JCheckBox();
        jF7_ABV = new javax.swing.JCheckBox();
        jF8_ABV = new javax.swing.JCheckBox();
        jF9_ABV = new javax.swing.JCheckBox();
        jF10_ABV = new javax.swing.JCheckBox();
        jF11_ABV = new javax.swing.JCheckBox();
        jF12_ABV = new javax.swing.JCheckBox();
        jF13_ABV = new javax.swing.JCheckBox();
        jF14_ABV = new javax.swing.JCheckBox();
        jF15_ABV = new javax.swing.JCheckBox();
        jF16_ABV = new javax.swing.JCheckBox();
        jF2_USV = new javax.swing.JCheckBox();
        jF3_USV = new javax.swing.JCheckBox();
        jF4_USV = new javax.swing.JCheckBox();
        jF5_USV = new javax.swing.JCheckBox();
        jF6_USV = new javax.swing.JCheckBox();
        jF7_USV = new javax.swing.JCheckBox();
        jF8_USV = new javax.swing.JCheckBox();
        jF9_USV = new javax.swing.JCheckBox();
        jF10_USV = new javax.swing.JCheckBox();
        jF11_USV = new javax.swing.JCheckBox();
        jF12_USV = new javax.swing.JCheckBox();
        jF13_USV = new javax.swing.JCheckBox();
        jF14_USV = new javax.swing.JCheckBox();
        jF15_USV = new javax.swing.JCheckBox();
        jF16_USV = new javax.swing.JCheckBox();
        jF2_Stop = new javax.swing.JCheckBox();
        jF3_Stop = new javax.swing.JCheckBox();
        jF4_Stop = new javax.swing.JCheckBox();
        jF5_Stop = new javax.swing.JCheckBox();
        jF6_Stop = new javax.swing.JCheckBox();
        jF7_Stop = new javax.swing.JCheckBox();
        jF8_Stop = new javax.swing.JCheckBox();
        jF9_Stop = new javax.swing.JCheckBox();
        jF10_Stop = new javax.swing.JCheckBox();
        jF11_Stop = new javax.swing.JCheckBox();
        jF12_Stop = new javax.swing.JCheckBox();
        jF13_Stop = new javax.swing.JCheckBox();
        jF14_Stop = new javax.swing.JCheckBox();
        jF15_Stop = new javax.swing.JCheckBox();
        jF16_Stop = new javax.swing.JCheckBox();
        jF2_Fkt = new javax.swing.JTextField();
        jF3_Fkt = new javax.swing.JTextField();
        jF4_Fkt = new javax.swing.JTextField();
        jF5_Fkt = new javax.swing.JTextField();
        jF6_Fkt = new javax.swing.JTextField();
        jF7_Fkt = new javax.swing.JTextField();
        jF8_Fkt = new javax.swing.JTextField();
        jF9_Fkt = new javax.swing.JTextField();
        jF10_Fkt = new javax.swing.JTextField();
        jF11_Fkt = new javax.swing.JTextField();
        jF12_Fkt = new javax.swing.JTextField();
        jF13_Fkt = new javax.swing.JTextField();
        jF14_Fkt = new javax.swing.JTextField();
        jF15_Fkt = new javax.swing.JTextField();
        jF16_Fkt = new javax.swing.JTextField();
        jSelektFunktionen = new javax.swing.JComboBox<>();
        jLabFunktionen = new javax.swing.JLabel();
        jEffekte_1 = new javax.swing.JPanel();
        jAuxInv1 = new javax.swing.JCheckBox();
        jAuxBlink1 = new javax.swing.JCheckBox();
        jLabel32 = new javax.swing.JLabel();
        jAuxBlink2 = new javax.swing.JCheckBox();
        jAuxInv2 = new javax.swing.JCheckBox();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jAuxInv3 = new javax.swing.JCheckBox();
        jAuxBlink3 = new javax.swing.JCheckBox();
        jAuxFeuer6 = new javax.swing.JCheckBox();
        jLabel46 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel40 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jAuxDim2 = new javax.swing.JCheckBox();
        jAuxDim1 = new javax.swing.JCheckBox();
        jAuxDim3 = new javax.swing.JCheckBox();
        jAuxInv4 = new javax.swing.JCheckBox();
        jAuxInv5 = new javax.swing.JCheckBox();
        jAuxInv6 = new javax.swing.JCheckBox();
        jAuxDim4 = new javax.swing.JCheckBox();
        jAuxFeuer1 = new javax.swing.JCheckBox();
        jAuxFeuer2 = new javax.swing.JCheckBox();
        jAuxFeuer3 = new javax.swing.JCheckBox();
        jAuxBlink4 = new javax.swing.JCheckBox();
        jAuxBlink5 = new javax.swing.JCheckBox();
        jAuxBlink6 = new javax.swing.JCheckBox();
        jAuxFeuer4 = new javax.swing.JCheckBox();
        jAuxFeuer5 = new javax.swing.JCheckBox();
        jAuxSpSch1 = new javax.swing.JCheckBox();
        jAuxSpSch2 = new javax.swing.JCheckBox();
        jAuxSpSch3 = new javax.swing.JCheckBox();
        jAuxSpSch4 = new javax.swing.JCheckBox();
        jAuxSpSch5 = new javax.swing.JCheckBox();
        jAuxSpSch6 = new javax.swing.JCheckBox();
        jLabel79 = new javax.swing.JLabel();
        jAuxKick6 = new javax.swing.JCheckBox();
        jAuxKick2 = new javax.swing.JCheckBox();
        jAuxKick1 = new javax.swing.JCheckBox();
        jAuxKick3 = new javax.swing.JCheckBox();
        jAuxKick4 = new javax.swing.JCheckBox();
        jAuxKick5 = new javax.swing.JCheckBox();
        jLabel26 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        jBlinkfrequenz1 = new javax.swing.JTextField();
        jBlinkfrequenz2 = new javax.swing.JTextField();
        jLabel180 = new javax.swing.JLabel();
        jLabel181 = new javax.swing.JLabel();
        jBlinkfrequenzf = new javax.swing.JTextField();
        jBlinkfrequenzr = new javax.swing.JTextField();
        jLabel182 = new javax.swing.JLabel();
        jLabel183 = new javax.swing.JLabel();
        jAuxInvf = new javax.swing.JCheckBox();
        jAuxBlinkf = new javax.swing.JCheckBox();
        jAuxBlinkr = new javax.swing.JCheckBox();
        jAuxInvr = new javax.swing.JCheckBox();
        jAuxKickr = new javax.swing.JCheckBox();
        jAuxKickf = new javax.swing.JCheckBox();
        jAuxDim5 = new javax.swing.JCheckBox();
        jAuxDim6 = new javax.swing.JCheckBox();
        jAuxDimf = new javax.swing.JCheckBox();
        jAuxDimr = new javax.swing.JCheckBox();
        jAuxFeuerf = new javax.swing.JCheckBox();
        jAuxFeuerr = new javax.swing.JCheckBox();
        jAuxSpSchf = new javax.swing.JCheckBox();
        jAuxSpSchr = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        jVSchalt = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jKickZeit = new javax.swing.JTextField();
        jEffekte_2 = new javax.swing.JPanel();
        jDimmen1 = new javax.swing.JTextField();
        jDimmen2 = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jDimmen3 = new javax.swing.JTextField();
        jLabel68 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        jDimmen4 = new javax.swing.JTextField();
        jMindestSchlt1 = new javax.swing.JTextField();
        jMindestSchlt2 = new javax.swing.JTextField();
        jLabel102 = new javax.swing.JLabel();
        jIn1Func10 = new javax.swing.JCheckBox();
        jIn1Func11 = new javax.swing.JCheckBox();
        jIn1Func12 = new javax.swing.JCheckBox();
        jIn1Func13 = new javax.swing.JCheckBox();
        jIn1Func14 = new javax.swing.JCheckBox();
        jIn1Func15 = new javax.swing.JCheckBox();
        jLabel107 = new javax.swing.JLabel();
        jLabel108 = new javax.swing.JLabel();
        jLabel109 = new javax.swing.JLabel();
        jLabel111 = new javax.swing.JLabel();
        jLabel99 = new javax.swing.JLabel();
        jLabel118 = new javax.swing.JLabel();
        jLabel119 = new javax.swing.JLabel();
        jLabel120 = new javax.swing.JLabel();
        jLabel121 = new javax.swing.JLabel();
        jLabel122 = new javax.swing.JLabel();
        jIn2Func10 = new javax.swing.JCheckBox();
        jIn2Func11 = new javax.swing.JCheckBox();
        jIn2Func12 = new javax.swing.JCheckBox();
        jIn2Func13 = new javax.swing.JCheckBox();
        jIn2Func14 = new javax.swing.JCheckBox();
        jIn2Func15 = new javax.swing.JCheckBox();
        jLabel129 = new javax.swing.JLabel();
        jLabel184 = new javax.swing.JLabel();
        jIn1Func8 = new javax.swing.JCheckBox();
        jIn1Func9 = new javax.swing.JCheckBox();
        jIn2Func8 = new javax.swing.JCheckBox();
        jIn2Func9 = new javax.swing.JCheckBox();
        jIn1Func0 = new javax.swing.JCheckBox();
        jIn2Func0 = new javax.swing.JCheckBox();
        jIn1Func1 = new javax.swing.JCheckBox();
        jIn2Func1 = new javax.swing.JCheckBox();
        jLabel100 = new javax.swing.JLabel();
        jLabel123 = new javax.swing.JLabel();
        jLabel124 = new javax.swing.JLabel();
        jLabel125 = new javax.swing.JLabel();
        jLabel185 = new javax.swing.JLabel();
        jLabel186 = new javax.swing.JLabel();
        jLabel187 = new javax.swing.JLabel();
        jLabel188 = new javax.swing.JLabel();
        jIn1Func2 = new javax.swing.JCheckBox();
        jIn2Func2 = new javax.swing.JCheckBox();
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
        jDimmenf = new javax.swing.JTextField();
        jDimmenr = new javax.swing.JTextField();
        jLabel82 = new javax.swing.JLabel();
        jLabel105 = new javax.swing.JLabel();
        jLabel110 = new javax.swing.JLabel();
        jDimmen5 = new javax.swing.JTextField();
        jLabel127 = new javax.swing.JLabel();
        jDimmen6 = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel104 = new javax.swing.JLabel();
        jLabel206 = new javax.swing.JLabel();
        jLabel240 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jDimmPeriode = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jFahrStWalzer = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jUeberlastSchwelle = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jUeberlastDauer = new javax.swing.JTextField();
        jEffekte_3 = new javax.swing.JPanel();
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
        jLabel148 = new javax.swing.JLabel();
        jLabel150 = new javax.swing.JLabel();
        jLabel132 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel133 = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        jLabel208 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jConsF0 = new javax.swing.JCheckBox();
        jConsF1 = new javax.swing.JCheckBox();
        jConsF2 = new javax.swing.JCheckBox();
        jConsF3 = new javax.swing.JCheckBox();
        jConsF4 = new javax.swing.JCheckBox();
        jConsF5 = new javax.swing.JCheckBox();
        jConsF6 = new javax.swing.JCheckBox();
        jConsF7 = new javax.swing.JCheckBox();
        jConsF8 = new javax.swing.JCheckBox();
        jConsF9 = new javax.swing.JCheckBox();
        jConsF10 = new javax.swing.JCheckBox();
        jConsF11 = new javax.swing.JCheckBox();
        jConsF12 = new javax.swing.JCheckBox();
        jLabel13 = new javax.swing.JLabel();
        jConsAdr = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLastregEin = new javax.swing.JCheckBox();
        jLabel15 = new javax.swing.JLabel();
        jVerstaerk = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jKP = new javax.swing.JTextField();
        jKI = new javax.swing.JTextField();
        jKD = new javax.swing.JTextField();
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
        jF0 = new javax.swing.JCheckBox();
        jF9 = new javax.swing.JCheckBox();
        jF10 = new javax.swing.JCheckBox();
        jF11 = new javax.swing.JCheckBox();
        jF12 = new javax.swing.JCheckBox();
        jKommentar = new javax.swing.JPanel();
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

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("my/CVNavi/Bundle"); // NOI18N
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

        jDecodereigenschaften.addTab(bundle.getString("LDG4x.jCV29.TabConstraints.tabTitle"), jCV29); // NOI18N

        jFunctionMapping.setToolTipText(bundle.getString("LDG4x.jFunctionMapping.toolTipText")); // NOI18N
        jFunctionMapping.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFunctionMapping.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jFunctionMappingComponentShown(evt);
            }
        });
        jFunctionMapping.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jF1_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF1_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF1_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF1_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, -1, -1));

        jF16_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF16_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF16_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF16_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 360, -1, -1));

        jF3_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF3_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF3_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF3_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, -1, -1));

        jF4_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF4_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF4_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF4_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, -1, -1));

        jF5_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF5_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF5_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF5_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, -1, -1));

        jF6_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF6_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF6_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF6_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, -1, -1));

        jF7_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF7_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF7_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF7_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, -1, -1));

        jF12_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF12_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF12_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF12_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 280, -1, -1));

        jF1_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF1_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF1_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF1_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, -1, -1));

        jF2_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF2_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF2_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF2_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, -1, -1));

        jF3_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF3_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF3_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF3_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, -1, -1));

        jF4_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF4_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF4_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF4_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, -1, -1));

        jF5_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF5_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF5_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF5_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, -1, -1));

        jF6_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF6_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF6_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF6_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 160, -1, -1));

        jF11_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF11_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF11_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF11_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 260, -1, -1));

        jF13_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF13_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF13_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF13_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 300, -1, -1));

        jLabF1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabF1.setText(bundle.getString("LDG4x.jLabF1.text")); // NOI18N
        jFunctionMapping.add(jLabF1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        jLabF2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabF2.setText(bundle.getString("LDG4x.jLabF2.text")); // NOI18N
        jFunctionMapping.add(jLabF2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));

        jLabF3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabF3.setText(bundle.getString("LDG4x.jLabF3.text")); // NOI18N
        jFunctionMapping.add(jLabF3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, -1));

        jLabF4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabF4.setText(bundle.getString("LDG4x.jLabF4.text")); // NOI18N
        jFunctionMapping.add(jLabF4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, -1));

        jLabF5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabF5.setText(bundle.getString("LDG4x.jLabF5.text")); // NOI18N
        jFunctionMapping.add(jLabF5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, -1, -1));

        jLabF6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabF6.setText("F2r");
        jFunctionMapping.add(jLabF6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, -1, -1));

        jLabF7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabF7.setText(bundle.getString("LDG4x.jLabF7.text")); // NOI18N
        jFunctionMapping.add(jLabF7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, -1, -1));

        jLabF8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabF8.setText(bundle.getString("LDG4x.jLabF8.text")); // NOI18N
        jFunctionMapping.add(jLabF8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, -1, -1));

        jLabF9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabF9.setText("F4f");
        jFunctionMapping.add(jLabF9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, -1, -1));

        jLabF10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabF10.setText("F4r");
        jFunctionMapping.add(jLabF10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, -1, -1));

        jLabF11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabF11.setText("F5f");
        jFunctionMapping.add(jLabF11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, -1, -1));

        jLabF13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabF13.setText(bundle.getString("LDG4x.jLabF13.text")); // NOI18N
        jFunctionMapping.add(jLabF13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, -1, -1));

        jLabF16.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabF16.setText(bundle.getString("LDG4x.jLabF16.text")); // NOI18N
        jFunctionMapping.add(jLabF16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, -1, -1));

        jF1_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF1_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF1_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF1_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 60, -1, -1));

        jF2_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF2_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF2_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF2_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, -1, -1));

        jF3_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF3_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF3_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF3_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 100, -1, -1));

        jF4_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF4_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF4_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF4_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 120, -1, -1));

        jF5_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF5_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF5_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF5_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 140, -1, -1));

        jF6_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF6_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF6_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF6_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 160, -1, -1));

        jF11_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF11_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF11_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF11_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 260, -1, -1));

        jF13_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF13_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF13_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF13_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 300, -1, -1));

        jF1_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF1_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF1_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF1_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, -1, -1));

        jF2_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF2_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF2_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF2_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, -1, -1));

        jF3_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF3_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF3_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF3_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, -1, -1));

        jF4_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF4_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF4_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF4_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, -1, -1));

        jF5_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF5_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF5_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF5_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 140, -1, -1));

        jF6_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF6_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF6_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF6_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 160, -1, -1));

        jF11_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF11_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF11_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF11_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 260, -1, -1));

        jF13_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF13_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF13_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF13_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 300, -1, -1));

        jF1_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF1_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF1_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF1_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, -1, -1));

        jF2_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF2_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF2_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF2_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, -1, -1));

        jF3_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF3_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF3_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF3_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, -1, -1));

        jF4_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF4_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF4_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF4_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 120, -1, -1));

        jF5_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF5_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF5_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF5_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, -1, -1));

        jF6_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF6_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF6_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF6_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 160, -1, -1));

        jF11_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF11_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF11_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF11_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 260, -1, -1));

        jF13_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF13_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF13_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF13_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 300, -1, -1));

        jLabel58.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel58.setText(bundle.getString("LDG30erPlus.jLabel58.text")); // NOI18N
        jFunctionMapping.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, -1, -1));

        jLabel59.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel59.setText(bundle.getString("LDG30erPlus.jLabel59.text")); // NOI18N
        jFunctionMapping.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, -1, -1));

        jF1_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF1_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF1_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF1_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, -1, -1));

        jF2_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF2_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF2_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF2_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, -1, -1));

        jF3_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF3_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF3_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF3_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 100, -1, -1));

        jF4_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF4_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF4_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF4_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 120, -1, -1));

        jF5_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF5_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF5_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF5_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 140, -1, -1));

        jF6_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF6_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF6_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF6_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 160, -1, -1));

        jF11_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF11_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF11_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF11_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 260, -1, -1));

        jF13_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF13_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF13_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF13_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 300, -1, -1));

        jF16_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF16_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF16_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF16_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 360, -1, -1));

        jF2_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF2_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF2_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF2_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, -1, -1));

        jF7_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF7_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF7_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF7_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 180, -1, -1));

        jF7_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF7_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF7_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF7_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 180, -1, -1));

        jF7_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF7_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF7_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF7_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 180, -1, -1));

        jF7_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF7_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF7_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF7_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 180, -1, -1));

        jF12_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF12_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF12_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF12_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 280, -1, -1));

        jF13_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF13_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF13_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF13_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 300, -1, -1));

        jLabel60.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel60.setText(bundle.getString("LDG30erPlus.jLabel60.text")); // NOI18N
        jFunctionMapping.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 40, -1, -1));

        jLabel61.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel61.setText(bundle.getString("LDG30erPlus.jLabel61.text")); // NOI18N
        jFunctionMapping.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 40, -1, -1));

        jF16_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF16_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF16_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF16_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 360, -1, -1));

        jF7_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF7_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF7_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF7_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 180, -1, -1));

        jF8_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF8_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF8_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF8_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 200, -1, -1));

        jF8_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF8_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF8_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF8_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, -1, -1));

        jF8_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF8_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF8_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF8_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 200, -1, -1));

        jF8_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF8_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF8_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF8_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 200, -1, -1));

        jF12_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF12_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF12_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF12_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 280, -1, -1));

        jF14_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF14_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF14_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF14_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 320, -1, -1));

        jF16_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF16_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF16_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF16_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 360, -1, -1));

        jF8_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF8_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF8_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF8_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 200, -1, -1));

        jF8_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF8_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF8_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF8_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 200, -1, -1));

        jF9_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF9_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF9_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF9_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, -1, -1));

        jF9_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF9_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF9_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF9_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 220, -1, -1));

        jF9_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF9_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF9_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF9_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 220, -1, -1));

        jF12_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF12_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF12_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF12_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 280, -1, -1));

        jF14_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF14_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF14_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF14_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 320, -1, -1));

        jLabel62.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel62.setText(bundle.getString("LDG30erPlus.jLabel62.text")); // NOI18N
        jFunctionMapping.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, -1, -1));

        jLabel63.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel63.setText(bundle.getString("LDG30erPlus.jLabel63.text")); // NOI18N
        jFunctionMapping.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 40, -1, -1));

        jF16_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF16_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF16_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF16_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 360, -1, -1));

        jF9_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF9_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF9_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF9_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 220, -1, -1));

        jF9_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF9_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF9_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF9_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 220, -1, -1));

        jF9_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF9_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF9_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF9_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 220, -1, -1));

        jF10_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF10_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF10_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF10_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 240, -1, -1));

        jF10_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF10_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF10_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF10_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 240, -1, -1));

        jF12_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF12_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF12_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF12_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 280, -1, -1));

        jF14_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF14_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF14_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF14_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 320, -1, -1));

        jF14_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF14_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF14_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF14_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 320, -1, -1));

        jF15_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF15_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF15_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF15_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 340, -1, -1));

        jF15_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF15_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF15_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF15_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 340, -1, -1));

        jF16_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF16_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF16_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF16_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 360, -1, -1));

        jF10_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF10_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF10_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF10_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 240, -1, -1));

        jF10_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF10_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF10_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF10_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 240, -1, -1));

        jF10_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF10_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF10_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF10_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 240, -1, -1));

        jF10_6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF10_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF10_6ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF10_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 240, -1, -1));

        jF11_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF11_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF11_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF11_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 260, -1, -1));

        jF12_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF12_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF12_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF12_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 280, -1, -1));

        jF14_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF14_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF14_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF14_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 320, -1, -1));

        jF14_5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF14_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF14_5ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF14_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 320, -1, -1));

        jF15_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF15_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF15_1ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF15_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 340, -1, -1));

        jF15_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF15_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF15_2ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF15_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 340, -1, -1));

        jF15_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF15_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF15_3ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF15_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 340, -1, -1));

        jF15_4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF15_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF15_4ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF15_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 340, -1, -1));

        jLabF14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabF14.setText("F6r");
        jFunctionMapping.add(jLabF14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, -1, -1));

        jLabF15.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabF15.setText("F7f");
        jFunctionMapping.add(jLabF15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, -1, -1));

        jLabF12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabF12.setText(bundle.getString("LDG4x.jLabF12.text")); // NOI18N
        jFunctionMapping.add(jLabF12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText(bundle.getString("LDG30erPlus.jLabel5.text")); // NOI18N
        jFunctionMapping.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, -1));

        jLabel177.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel177.setText(bundle.getString("LDG30erPlus.jLabel177.text")); // NOI18N
        jFunctionMapping.add(jLabel177, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, -1, -1));

        jLabel178.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel178.setText(bundle.getString("LDG30erPlus.jLabel178.text")); // NOI18N
        jFunctionMapping.add(jLabel178, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, -1, -1));

        jF1_7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF1_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF1_7ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF1_7, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 60, -1, -1));

        jF1_8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF1_8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF1_8ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF1_8, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, -1, -1));

        jF2_7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF2_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF2_7ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF2_7, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 80, -1, -1));

        jF2_8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF2_8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF2_8ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF2_8, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 80, -1, -1));

        jF3_7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF3_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF3_7ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF3_7, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, -1, -1));

        jF3_8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF3_8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF3_8ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF3_8, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 100, -1, -1));

        jF4_7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF4_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF4_7ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF4_7, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 120, -1, -1));

        jF4_8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF4_8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF4_8ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF4_8, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 120, -1, -1));

        jF5_7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF5_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF5_7ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF5_7, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 140, -1, -1));

        jF5_8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF5_8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF5_8ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF5_8, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 140, -1, -1));

        jF6_7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF6_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF6_7ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF6_7, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 160, -1, -1));

        jF6_8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF6_8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF6_8ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF6_8, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 160, -1, -1));

        jF7_7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF7_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF7_7ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF7_7, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 180, -1, -1));

        jF7_8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF7_8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF7_8ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF7_8, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 180, -1, -1));

        jF8_7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF8_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF8_7ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF8_7, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 200, -1, -1));

        jF8_8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF8_8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF8_8ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF8_8, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 200, -1, -1));

        jF9_7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF9_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF9_7ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF9_7, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 220, -1, -1));

        jF9_8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF9_8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF9_8ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF9_8, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 220, -1, -1));

        jF10_7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF10_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF10_7ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF10_7, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 240, -1, -1));

        jF10_8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF10_8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF10_8ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF10_8, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 240, -1, -1));

        jF11_7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF11_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF11_7ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF11_7, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 260, -1, -1));

        jF11_8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF11_8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF11_8ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF11_8, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 260, -1, -1));

        jF12_7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF12_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF12_7ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF12_7, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 280, -1, -1));

        jF12_8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF12_8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF12_8ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF12_8, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 280, -1, -1));

        jF13_7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF13_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF13_7ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF13_7, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 300, -1, -1));

        jF13_8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF13_8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF13_8ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF13_8, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 300, -1, -1));

        jF14_7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF14_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF14_7ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF14_7, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 320, -1, -1));

        jF14_8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF14_8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF14_8ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF14_8, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 320, -1, -1));

        jF15_7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF15_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF15_7ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF15_7, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 340, -1, -1));

        jF16_7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF16_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF16_7ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF16_7, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 360, -1, -1));

        jF15_8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF15_8ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF15_8, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 340, -1, -1));

        jF16_8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF16_8ActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF16_8, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 360, -1, -1));

        jF1_RG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF1_RGActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF1_RG, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 60, -1, -1));

        jLabRGan.setFont(new java.awt.Font("sansserif", 0, 12)); // NOI18N
        jLabRGan.setText(bundle.getString("LDG4x.jLabRGan.text")); // NOI18N
        jFunctionMapping.add(jLabRGan, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 40, -1, -1));

        jLabABVaus.setFont(new java.awt.Font("sansserif", 0, 12)); // NOI18N
        jLabABVaus.setText(bundle.getString("LDG4x.jLabABVaus.text")); // NOI18N
        jFunctionMapping.add(jLabABVaus, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 40, -1, -1));

        jF1_ABV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF1_ABVActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF1_ABV, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 60, -1, -1));

        jLabFkt.setFont(new java.awt.Font("sansserif", 0, 12)); // NOI18N
        jLabFkt.setText(bundle.getString("LDG4x.jLabFkt.text")); // NOI18N
        jFunctionMapping.add(jLabFkt, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 40, -1, -1));

        jF1_Fkt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jF1_FktFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jF1_FktFocusLost(evt);
            }
        });
        jF1_Fkt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jF1_FktKeyReleased(evt);
            }
        });
        jFunctionMapping.add(jF1_Fkt, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 60, 40, 16));

        jF2_RG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF2_RGActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF2_RG, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 80, -1, -1));

        jF3_RG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF3_RGActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF3_RG, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 100, -1, -1));

        jF4_RG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF4_RGActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF4_RG, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 120, -1, -1));

        jF5_RG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF5_RGActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF5_RG, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 140, -1, -1));

        jF6_RG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF6_RGActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF6_RG, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 160, -1, -1));

        jF7_RG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF7_RGActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF7_RG, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 180, -1, -1));

        jF8_RG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF8_RGActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF8_RG, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 200, -1, -1));

        jF9_RG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF9_RGActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF9_RG, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 220, -1, -1));

        jF10_RG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF10_RGActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF10_RG, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 240, -1, -1));

        jF11_RG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF11_RGActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF11_RG, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 260, -1, -1));

        jF12_RG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF12_RGActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF12_RG, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 280, -1, -1));

        jF13_RG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF13_RGActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF13_RG, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 300, -1, -1));

        jF14_RG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF14_RGActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF14_RG, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 320, -1, -1));

        jF15_RG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF15_RGActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF15_RG, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 340, -1, -1));

        jF16_RG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF16_RGActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF16_RG, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 360, -1, -1));

        jF2_ABV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF2_ABVActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF2_ABV, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 80, -1, -1));

        jF3_ABV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF3_ABVActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF3_ABV, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 100, -1, -1));

        jLabUSV.setFont(new java.awt.Font("sansserif", 0, 12)); // NOI18N
        jLabUSV.setText(bundle.getString("LDG4x.jLabUSV.text")); // NOI18N
        jFunctionMapping.add(jLabUSV, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 40, -1, -1));

        jLabStop.setFont(new java.awt.Font("sansserif", 0, 12)); // NOI18N
        jLabStop.setText(bundle.getString("LDG4x.jLabStop.text")); // NOI18N
        jFunctionMapping.add(jLabStop, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, -1, -1));

        jF1_Stop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF1_StopActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF1_Stop, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 60, -1, -1));

        jF1_USV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF1_USVActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF1_USV, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 60, -1, -1));

        jF4_ABV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF4_ABVActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF4_ABV, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 120, -1, -1));

        jF5_ABV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF5_ABVActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF5_ABV, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 140, -1, -1));

        jF6_ABV.setText("\n");
        jF6_ABV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF6_ABVActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF6_ABV, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 160, -1, -1));

        jF7_ABV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF7_ABVActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF7_ABV, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 180, -1, -1));

        jF8_ABV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF8_ABVActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF8_ABV, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 200, -1, -1));

        jF9_ABV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF9_ABVActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF9_ABV, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 220, -1, -1));

        jF10_ABV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF10_ABVActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF10_ABV, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 240, -1, -1));

        jF11_ABV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF11_ABVActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF11_ABV, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 260, -1, -1));

        jF12_ABV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF12_ABVActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF12_ABV, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 280, -1, -1));

        jF13_ABV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF13_ABVActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF13_ABV, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 300, -1, -1));

        jF14_ABV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF14_ABVActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF14_ABV, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 320, -1, -1));

        jF15_ABV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF15_ABVActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF15_ABV, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 340, -1, -1));

        jF16_ABV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF16_ABVActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF16_ABV, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 360, -1, -1));

        jF2_USV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF2_USVActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF2_USV, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 80, -1, -1));

        jF3_USV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF3_USVActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF3_USV, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 100, -1, -1));

        jF4_USV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF4_USVActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF4_USV, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 120, -1, -1));

        jF5_USV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF5_USVActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF5_USV, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 140, -1, -1));

        jF6_USV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF6_USVActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF6_USV, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 160, -1, -1));

        jF7_USV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF7_USVActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF7_USV, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 180, -1, -1));

        jF8_USV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF8_USVActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF8_USV, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 200, -1, -1));

        jF9_USV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF9_USVActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF9_USV, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 220, -1, -1));

        jF10_USV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF10_USVActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF10_USV, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 240, -1, -1));

        jF11_USV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF11_USVActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF11_USV, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 260, -1, -1));

        jF12_USV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF12_USVActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF12_USV, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 280, -1, -1));

        jF13_USV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF13_USVActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF13_USV, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 300, -1, -1));

        jF14_USV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF14_USVActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF14_USV, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 320, -1, -1));

        jF15_USV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF15_USVActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF15_USV, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 340, -1, -1));

        jF16_USV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF16_USVActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF16_USV, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 360, -1, -1));

        jF2_Stop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF2_StopActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF2_Stop, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 80, -1, -1));

        jF3_Stop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF3_StopActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF3_Stop, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 100, -1, -1));

        jF4_Stop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF4_StopActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF4_Stop, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 120, -1, -1));

        jF5_Stop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF5_StopActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF5_Stop, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 140, -1, -1));

        jF6_Stop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF6_StopActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF6_Stop, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 160, -1, -1));

        jF7_Stop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF7_StopActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF7_Stop, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 180, -1, -1));

        jF8_Stop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF8_StopActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF8_Stop, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 200, -1, -1));

        jF9_Stop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF9_StopActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF9_Stop, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 220, -1, -1));

        jF10_Stop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF10_StopActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF10_Stop, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 240, -1, -1));

        jF11_Stop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF11_StopActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF11_Stop, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 260, -1, -1));

        jF12_Stop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF12_StopActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF12_Stop, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 280, -1, -1));

        jF13_Stop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF13_StopActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF13_Stop, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 300, -1, -1));

        jF14_Stop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF14_StopActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF14_Stop, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 320, -1, -1));

        jF15_Stop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF15_StopActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF15_Stop, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 340, -1, -1));

        jF16_Stop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF16_StopActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jF16_Stop, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 360, -1, -1));

        jF2_Fkt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jF2_FktFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jF2_FktFocusLost(evt);
            }
        });
        jF2_Fkt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jF2_FktKeyReleased(evt);
            }
        });
        jFunctionMapping.add(jF2_Fkt, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 80, 40, 16));

        jF3_Fkt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jF3_FktFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jF3_FktFocusLost(evt);
            }
        });
        jF3_Fkt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jF3_FktKeyReleased(evt);
            }
        });
        jFunctionMapping.add(jF3_Fkt, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 100, 40, 16));

        jF4_Fkt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jF4_FktFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jF4_FktFocusLost(evt);
            }
        });
        jF4_Fkt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jF4_FktKeyReleased(evt);
            }
        });
        jFunctionMapping.add(jF4_Fkt, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 120, 40, 16));

        jF5_Fkt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jF5_FktFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jF5_FktFocusLost(evt);
            }
        });
        jF5_Fkt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jF5_FktKeyReleased(evt);
            }
        });
        jFunctionMapping.add(jF5_Fkt, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 140, 40, 16));

        jF6_Fkt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jF6_FktFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jF6_FktFocusLost(evt);
            }
        });
        jF6_Fkt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jF6_FktKeyReleased(evt);
            }
        });
        jFunctionMapping.add(jF6_Fkt, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 160, 40, 16));

        jF7_Fkt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jF7_FktFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jF7_FktFocusLost(evt);
            }
        });
        jF7_Fkt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jF7_FktKeyReleased(evt);
            }
        });
        jFunctionMapping.add(jF7_Fkt, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 180, 40, 16));

        jF8_Fkt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jF8_FktFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jF8_FktFocusLost(evt);
            }
        });
        jF8_Fkt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jF8_FktKeyReleased(evt);
            }
        });
        jFunctionMapping.add(jF8_Fkt, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 200, 40, 16));

        jF9_Fkt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jF9_FktFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jF9_FktFocusLost(evt);
            }
        });
        jF9_Fkt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jF9_FktKeyReleased(evt);
            }
        });
        jFunctionMapping.add(jF9_Fkt, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 220, 40, 16));

        jF10_Fkt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jF10_FktFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jF10_FktFocusLost(evt);
            }
        });
        jF10_Fkt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jF10_FktKeyReleased(evt);
            }
        });
        jFunctionMapping.add(jF10_Fkt, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 240, 40, 16));

        jF11_Fkt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jF11_FktFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jF11_FktFocusLost(evt);
            }
        });
        jF11_Fkt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jF11_FktKeyReleased(evt);
            }
        });
        jFunctionMapping.add(jF11_Fkt, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 260, 40, 16));

        jF12_Fkt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jF12_FktFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jF12_FktFocusLost(evt);
            }
        });
        jF12_Fkt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jF12_FktKeyReleased(evt);
            }
        });
        jFunctionMapping.add(jF12_Fkt, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 280, 40, 16));

        jF13_Fkt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jF13_FktFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jF13_FktFocusLost(evt);
            }
        });
        jF13_Fkt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jF13_FktKeyReleased(evt);
            }
        });
        jFunctionMapping.add(jF13_Fkt, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 300, 40, 16));

        jF14_Fkt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jF14_FktFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jF14_FktFocusLost(evt);
            }
        });
        jF14_Fkt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jF14_FktKeyReleased(evt);
            }
        });
        jFunctionMapping.add(jF14_Fkt, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 320, 40, 16));

        jF15_Fkt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jF15_FktFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jF15_FktFocusLost(evt);
            }
        });
        jF15_Fkt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jF15_FktKeyReleased(evt);
            }
        });
        jFunctionMapping.add(jF15_Fkt, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 340, 40, 16));

        jF16_Fkt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jF16_FktFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jF16_FktFocusLost(evt);
            }
        });
        jF16_Fkt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jF16_FktKeyReleased(evt);
            }
        });
        jFunctionMapping.add(jF16_Fkt, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 360, 40, 16));

        jSelektFunktionen.setFont(new java.awt.Font("sansserif", 0, 12)); // NOI18N
        jSelektFunktionen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "F0 - F7", "F8 - F15", "F16 - F23", "F24 - F28" }));
        jSelektFunktionen.setToolTipText(bundle.getString("Funktionsbereich.ToolTip.text")); // NOI18N
        jSelektFunktionen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSelektFunktionenActionPerformed(evt);
            }
        });
        jFunctionMapping.add(jSelektFunktionen, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, -1, -1));

        jLabFunktionen.setFont(new java.awt.Font("sansserif", 0, 12)); // NOI18N
        jLabFunktionen.setText(bundle.getString("LDG4x.jLabFunktionen.text")); // NOI18N
        jFunctionMapping.add(jLabFunktionen, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, -1, 20));

        jDecodereigenschaften.addTab(bundle.getString("LDG4x.Funktionszuordnung1.text"), jFunctionMapping); // NOI18N

        jEffekte_1.setToolTipText(bundle.getString("LDG4x.jEffekte_1.toolTipText")); // NOI18N
        jEffekte_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jEffekte_1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jEffekte_1ComponentShown(evt);
            }
        });
        jEffekte_1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jAuxInv1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAuxInv1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAuxInv1ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jAuxInv1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 20, -1, -1));

        jAuxBlink1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAuxBlink1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAuxBlink1ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jAuxBlink1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 40, -1, -1));

        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel32.setText(bundle.getString("LDG4x.jLabel32.text")); // NOI18N
        jEffekte_1.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        jAuxBlink2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAuxBlink2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAuxBlink2ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jAuxBlink2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, -1, -1));

        jAuxInv2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAuxInv2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAuxInv2ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jAuxInv2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 20, -1, -1));

        jLabel44.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel44.setText(bundle.getString("LDG30erPlus.jLabel44.text")); // NOI18N
        jEffekte_1.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 0, -1, -1));

        jLabel45.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel45.setText(bundle.getString("LDG4x.jLabel45.text")); // NOI18N
        jEffekte_1.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        jAuxInv3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAuxInv3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAuxInv3ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jAuxInv3, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 20, -1, -1));

        jAuxBlink3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAuxBlink3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAuxBlink3ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jAuxBlink3, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 40, -1, -1));

        jAuxFeuer6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAuxFeuer6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAuxFeuer6ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jAuxFeuer6, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 100, -1, -1));

        jLabel46.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel46.setText(bundle.getString("LDG30erPlus.jLabel46.text")); // NOI18N
        jEffekte_1.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 0, -1, -1));

        jLabel48.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel48.setText(bundle.getString("LDG30erPlus.jLabel48.text")); // NOI18N
        jEffekte_1.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 0, -1, -1));
        jEffekte_1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 420, 10));

        jLabel40.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel40.setText(bundle.getString("LDG30erPlus.jLabel40.text")); // NOI18N
        jEffekte_1.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, -1, -1));

        jLabel42.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel42.setText(bundle.getString("LDG30erPlus.jLabel42.text")); // NOI18N
        jEffekte_1.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, -1));

        jLabel54.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel54.setText(bundle.getString("LDG4x.jLabel54.text")); // NOI18N
        jEffekte_1.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, -1));

        jLabel55.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel55.setText(bundle.getString("LDG4x.jLabel55.text")); // NOI18N
        jEffekte_1.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, -1));

        jLabel57.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel57.setText(bundle.getString("LDG4x.jLabel57.text")); // NOI18N
        jEffekte_1.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));

        jLabel69.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel69.setText(bundle.getString("LDG30erPlus.jLabel69.text")); // NOI18N
        jEffekte_1.add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 0, -1, -1));

        jLabel70.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel70.setText(bundle.getString("LDG30erPlus.jLabel70.text")); // NOI18N
        jEffekte_1.add(jLabel70, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 0, -1, -1));

        jLabel71.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel71.setText(bundle.getString("LDG30erPlus.jLabel71.text")); // NOI18N
        jEffekte_1.add(jLabel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 0, -1, -1));

        jAuxDim2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAuxDim2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAuxDim2ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jAuxDim2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 80, -1, -1));

        jAuxDim1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAuxDim1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAuxDim1ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jAuxDim1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 80, -1, -1));

        jAuxDim3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAuxDim3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAuxDim3ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jAuxDim3, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 80, -1, -1));

        jAuxInv4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAuxInv4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAuxInv4ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jAuxInv4, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 20, -1, -1));

        jAuxInv5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAuxInv5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAuxInv5ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jAuxInv5, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 20, -1, -1));

        jAuxInv6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAuxInv6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAuxInv6ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jAuxInv6, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 20, -1, -1));

        jAuxDim4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAuxDim4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAuxDim4ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jAuxDim4, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 80, -1, -1));

        jAuxFeuer1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAuxFeuer1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAuxFeuer1ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jAuxFeuer1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 100, -1, -1));

        jAuxFeuer2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAuxFeuer2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAuxFeuer2ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jAuxFeuer2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 100, -1, -1));

        jAuxFeuer3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAuxFeuer3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAuxFeuer3ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jAuxFeuer3, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 100, -1, -1));

        jAuxBlink4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAuxBlink4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAuxBlink4ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jAuxBlink4, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 40, -1, -1));

        jAuxBlink5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAuxBlink5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAuxBlink5ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jAuxBlink5, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 40, -1, -1));

        jAuxBlink6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAuxBlink6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAuxBlink6ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jAuxBlink6, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 40, -1, -1));

        jAuxFeuer4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAuxFeuer4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAuxFeuer4ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jAuxFeuer4, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 100, -1, -1));

        jAuxFeuer5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAuxFeuer5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAuxFeuer5ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jAuxFeuer5, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 100, -1, -1));

        jAuxSpSch1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAuxSpSch1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAuxSpSch1ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jAuxSpSch1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 120, -1, -1));

        jAuxSpSch2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAuxSpSch2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAuxSpSch2ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jAuxSpSch2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 120, -1, -1));

        jAuxSpSch3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAuxSpSch3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAuxSpSch3ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jAuxSpSch3, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 120, -1, -1));

        jAuxSpSch4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAuxSpSch4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAuxSpSch4ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jAuxSpSch4, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 120, -1, -1));

        jAuxSpSch5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAuxSpSch5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAuxSpSch5ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jAuxSpSch5, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 120, -1, -1));

        jAuxSpSch6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAuxSpSch6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAuxSpSch6ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jAuxSpSch6, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 120, -1, -1));

        jLabel79.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel79.setText(bundle.getString("LDG30erPlus.jLabel79.text")); // NOI18N
        jEffekte_1.add(jLabel79, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 0, -1, -1));

        jAuxKick6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAuxKick6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAuxKick6ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jAuxKick6, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 60, -1, -1));

        jAuxKick2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAuxKick2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAuxKick2ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jAuxKick2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 60, -1, -1));

        jAuxKick1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAuxKick1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAuxKick1ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jAuxKick1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 60, -1, -1));

        jAuxKick3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAuxKick3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAuxKick3ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jAuxKick3, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 60, -1, -1));

        jAuxKick4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAuxKick4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAuxKick4ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jAuxKick4, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 60, -1, -1));

        jAuxKick5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAuxKick5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAuxKick5ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jAuxKick5, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 60, -1, -1));

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel26.setText(bundle.getString("LDG4x.jLabel26.text")); // NOI18N
        jEffekte_1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        jLabel50.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel50.setText(bundle.getString("LDG30erPlus.jLabel50.text")); // NOI18N
        jEffekte_1.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, -1, -1));

        jLabel66.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel66.setText(bundle.getString("LDG4x.jLabel66a.text")); // NOI18N
        jEffekte_1.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 60, -1));

        jLabel97.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel97.setText(bundle.getString("LDG4x.jLabel97a.text")); // NOI18N
        jEffekte_1.add(jLabel97, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 60, -1));

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
        jEffekte_1.add(jBlinkfrequenz1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 280, 30, 20));

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
        jEffekte_1.add(jBlinkfrequenz2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 300, 30, 20));

        jLabel180.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel180.setText(bundle.getString("LDG4x.jLabel180a.text")); // NOI18N
        jEffekte_1.add(jLabel180, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 60, -1));

        jLabel181.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel181.setText(bundle.getString("LDG4x.jLabel181a.text")); // NOI18N
        jEffekte_1.add(jLabel181, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 60, -1));

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
        jEffekte_1.add(jBlinkfrequenzf, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 240, 30, 20));

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
        jEffekte_1.add(jBlinkfrequenzr, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 260, 30, 20));

        jLabel182.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel182.setText(bundle.getString("LDG30erPlus.jLabel182.text")); // NOI18N
        jEffekte_1.add(jLabel182, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, -1, -1));

        jLabel183.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel183.setText(bundle.getString("LDG30erPlus.jLabel183.text")); // NOI18N
        jEffekte_1.add(jLabel183, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 0, -1, -1));

        jAuxInvf.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAuxInvf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAuxInvfActionPerformed(evt);
            }
        });
        jEffekte_1.add(jAuxInvf, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, -1, -1));

        jAuxBlinkf.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAuxBlinkf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAuxBlinkfActionPerformed(evt);
            }
        });
        jEffekte_1.add(jAuxBlinkf, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 40, -1, -1));

        jAuxBlinkr.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAuxBlinkr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAuxBlinkrActionPerformed(evt);
            }
        });
        jEffekte_1.add(jAuxBlinkr, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 40, -1, -1));

        jAuxInvr.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAuxInvr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAuxInvrActionPerformed(evt);
            }
        });
        jEffekte_1.add(jAuxInvr, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 20, -1, -1));

        jAuxKickr.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAuxKickr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAuxKickrActionPerformed(evt);
            }
        });
        jEffekte_1.add(jAuxKickr, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 60, -1, -1));

        jAuxKickf.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAuxKickf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAuxKickfActionPerformed(evt);
            }
        });
        jEffekte_1.add(jAuxKickf, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, -1, -1));

        jAuxDim5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAuxDim5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAuxDim5ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jAuxDim5, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 80, -1, -1));

        jAuxDim6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAuxDim6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAuxDim6ActionPerformed(evt);
            }
        });
        jEffekte_1.add(jAuxDim6, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 80, -1, -1));

        jAuxDimf.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAuxDimf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAuxDimfActionPerformed(evt);
            }
        });
        jEffekte_1.add(jAuxDimf, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 80, -1, -1));

        jAuxDimr.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAuxDimr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAuxDimrActionPerformed(evt);
            }
        });
        jEffekte_1.add(jAuxDimr, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 80, -1, -1));

        jAuxFeuerf.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAuxFeuerf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAuxFeuerfActionPerformed(evt);
            }
        });
        jEffekte_1.add(jAuxFeuerf, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 100, -1, -1));

        jAuxFeuerr.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAuxFeuerr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAuxFeuerrActionPerformed(evt);
            }
        });
        jEffekte_1.add(jAuxFeuerr, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 100, -1, -1));

        jAuxSpSchf.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAuxSpSchf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAuxSpSchfActionPerformed(evt);
            }
        });
        jEffekte_1.add(jAuxSpSchf, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 120, -1, -1));

        jAuxSpSchr.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jAuxSpSchr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAuxSpSchrActionPerformed(evt);
            }
        });
        jEffekte_1.add(jAuxSpSchr, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 120, -1, -1));

        jLabel6.setFont(new java.awt.Font("sansserif", 0, 12)); // NOI18N
        jLabel6.setText(bundle.getString("LDG4x.jLabel6.text")); // NOI18N
        jEffekte_1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, -1, -1));

        jVSchalt.setFont(new java.awt.Font("sansserif", 0, 12)); // NOI18N
        jVSchalt.setText("0");
        jEffekte_1.add(jVSchalt, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 160, 30, -1));

        jLabel8.setFont(new java.awt.Font("sansserif", 0, 12)); // NOI18N
        jLabel8.setText(bundle.getString("LDG4x.jLabel8.text")); // NOI18N
        jEffekte_1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 160, 130, -1));

        jKickZeit.setFont(new java.awt.Font("sansserif", 0, 12)); // NOI18N
        jKickZeit.setText("0");
        jKickZeit.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jKickZeitFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jKickZeitFocusLost(evt);
            }
        });
        jKickZeit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jKickZeitKeyReleased(evt);
            }
        });
        jEffekte_1.add(jKickZeit, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 160, 40, -1));

        jDecodereigenschaften.addTab(bundle.getString("LDG4x.jEffekte_1.TabConstraints.tabTitle"), jEffekte_1); // NOI18N

        jEffekte_2.setToolTipText(bundle.getString("LDG4x.jEffekte_2.toolTipText")); // NOI18N
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
        jEffekte_2.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jLabel49.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel49.setText(bundle.getString("3.text")); // NOI18N
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

        jLabel68.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel68.setText(bundle.getString("LDG30erPlus.jLabel68.text")); // NOI18N
        jEffekte_2.add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        jLabel74.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel74.setText(bundle.getString("1.text")); // NOI18N
        jEffekte_2.add(jLabel74, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        jLabel39.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel39.setText(bundle.getString("LDG30erPlus.jLabel39.text")); // NOI18N
        jEffekte_2.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 31, 110, -1));
        jEffekte_2.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 420, 10));

        jLabel77.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel77.setText(bundle.getString("LDG30erPlus.jLabel77.text")); // NOI18N
        jEffekte_2.add(jLabel77, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));

        jLabel78.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel78.setText(bundle.getString("LDG4x.jLabel78.text")); // NOI18N
        jEffekte_2.add(jLabel78, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, -1, -1));

        jLabel80.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel80.setText(bundle.getString("LDG4x.jLabel80.text")); // NOI18N
        jEffekte_2.add(jLabel80, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 250, -1, -1));

        jLabel98.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel98.setText(bundle.getString("4.text")); // NOI18N
        jEffekte_2.add(jLabel98, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, -1));

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
        jEffekte_2.add(jMindestSchlt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 410, 30, 20));

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
        jEffekte_2.add(jMindestSchlt2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 410, 30, 20));

        jLabel102.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel102.setText(bundle.getString("2.text")); // NOI18N
        jEffekte_2.add(jLabel102, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        jIn1Func10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn1Func10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn1Func10ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn1Func10, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 360, -1, -1));

        jIn1Func11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn1Func11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn1Func11ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn1Func11, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 360, -1, -1));

        jIn1Func12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn1Func12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn1Func12ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn1Func12, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 360, -1, -1));

        jIn1Func13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn1Func13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn1Func13ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn1Func13, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 360, -1, -1));

        jIn1Func14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn1Func14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn1Func14ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn1Func14, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 360, -1, -1));

        jIn1Func15.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn1Func15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn1Func15ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn1Func15, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 360, -1, -1));

        jLabel107.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel107.setText(bundle.getString("LDG30erPlus.jLabel107.text")); // NOI18N
        jEffekte_2.add(jLabel107, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, -1, -1));

        jLabel108.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel108.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel108.setText(bundle.getString("LDG30erPlus.jLabel108.text")); // NOI18N
        jEffekte_2.add(jLabel108, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 410, 140, 20));

        jLabel109.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel109.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel109.setText(bundle.getString("LDG4x.jLabel109.text")); // NOI18N
        jEffekte_2.add(jLabel109, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 410, 50, 20));

        jLabel111.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel111.setText(bundle.getString("LDG30erPlus.jLabel111.text")); // NOI18N
        jEffekte_2.add(jLabel111, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, -1, -1));

        jLabel99.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel99.setText(bundle.getString("F12.text")); // NOI18N
        jEffekte_2.add(jLabel99, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 340, -1, -1));

        jLabel118.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel118.setText(bundle.getString("F10.text")); // NOI18N
        jEffekte_2.add(jLabel118, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 340, -1, -1));

        jLabel119.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel119.setText(bundle.getString("F13.text")); // NOI18N
        jEffekte_2.add(jLabel119, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 340, -1, -1));

        jLabel120.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel120.setText(bundle.getString("F14.text")); // NOI18N
        jEffekte_2.add(jLabel120, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 340, -1, -1));

        jLabel121.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel121.setText(bundle.getString("F15.text")); // NOI18N
        jEffekte_2.add(jLabel121, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 340, -1, -1));

        jLabel122.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel122.setText(bundle.getString("F11.text")); // NOI18N
        jEffekte_2.add(jLabel122, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 340, -1, -1));

        jIn2Func10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn2Func10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn2Func10ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn2Func10, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 380, -1, -1));

        jIn2Func11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn2Func11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn2Func11ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn2Func11, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 380, -1, -1));

        jIn2Func12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn2Func12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn2Func12ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn2Func12, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 380, -1, -1));

        jIn2Func13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn2Func13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn2Func13ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn2Func13, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 380, -1, -1));

        jIn2Func14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn2Func14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn2Func14ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn2Func14, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 380, -1, -1));

        jIn2Func15.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn2Func15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn2Func15ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn2Func15, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 380, -1, -1));

        jLabel129.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel129.setText(bundle.getString("F8.text")); // NOI18N
        jEffekte_2.add(jLabel129, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 340, -1, -1));

        jLabel184.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel184.setText(bundle.getString("F9.text")); // NOI18N
        jEffekte_2.add(jLabel184, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 340, -1, -1));

        jIn1Func8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn1Func8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn1Func8ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn1Func8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 360, -1, -1));

        jIn1Func9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn1Func9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn1Func9ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn1Func9, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 360, -1, -1));

        jIn2Func8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn2Func8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn2Func8ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn2Func8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 380, -1, -1));

        jIn2Func9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn2Func9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn2Func9ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn2Func9, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 380, -1, -1));

        jIn1Func0.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn1Func0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn1Func0ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn1Func0, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 290, -1, -1));

        jIn2Func0.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn2Func0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn2Func0ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn2Func0, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 310, -1, -1));

        jIn1Func1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn1Func1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn1Func1ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn1Func1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 290, -1, -1));

        jIn2Func1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn2Func1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn2Func1ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn2Func1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 310, -1, -1));

        jLabel100.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel100.setText(bundle.getString("F4.text")); // NOI18N
        jEffekte_2.add(jLabel100, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 270, -1, -1));

        jLabel123.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel123.setText(bundle.getString("F2.text")); // NOI18N
        jEffekte_2.add(jLabel123, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 270, -1, -1));

        jLabel124.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel124.setText(bundle.getString("F5.text")); // NOI18N
        jEffekte_2.add(jLabel124, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 270, -1, -1));

        jLabel125.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel125.setText(bundle.getString("F6.text")); // NOI18N
        jEffekte_2.add(jLabel125, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 270, -1, -1));

        jLabel185.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel185.setText(bundle.getString("F7.text")); // NOI18N
        jEffekte_2.add(jLabel185, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 270, -1, -1));

        jLabel186.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel186.setText(bundle.getString("F3.text")); // NOI18N
        jEffekte_2.add(jLabel186, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 270, -1, -1));

        jLabel187.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel187.setText(bundle.getString("F0.text")); // NOI18N
        jEffekte_2.add(jLabel187, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, -1, -1));

        jLabel188.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel188.setText(bundle.getString("F1.text")); // NOI18N
        jEffekte_2.add(jLabel188, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 270, -1, -1));

        jIn1Func2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn1Func2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn1Func2ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn1Func2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 290, -1, -1));

        jIn2Func2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn2Func2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn2Func2ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn2Func2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 310, -1, -1));

        jIn1Func3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn1Func3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn1Func3ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn1Func3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 290, -1, -1));

        jIn2Func3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn2Func3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn2Func3ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn2Func3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 310, -1, -1));

        jIn1Func4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn1Func4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn1Func4ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn1Func4, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 290, -1, -1));

        jIn2Func4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn2Func4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn2Func4ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn2Func4, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 310, -1, -1));

        jIn1Func5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn1Func5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn1Func5ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn1Func5, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 290, -1, -1));

        jIn2Func5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn2Func5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn2Func5ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn2Func5, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 310, -1, -1));

        jIn1Func6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn1Func6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn1Func6ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn1Func6, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 290, -1, -1));

        jIn2Func6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn2Func6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn2Func6ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn2Func6, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 310, -1, -1));

        jIn1Func7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn1Func7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn1Func7ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn1Func7, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 290, -1, -1));

        jIn2Func7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIn2Func7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIn2Func7ActionPerformed(evt);
            }
        });
        jEffekte_2.add(jIn2Func7, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 310, -1, -1));

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
        jLabel82.setText(bundle.getString("f.text")); // NOI18N
        jEffekte_2.add(jLabel82, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        jLabel105.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel105.setText(bundle.getString("r.text")); // NOI18N
        jEffekte_2.add(jLabel105, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        jLabel110.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel110.setText(bundle.getString("5.text")); // NOI18N
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
        jLabel127.setText(bundle.getString("6.text")); // NOI18N
        jEffekte_2.add(jLabel127, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, -1, -1));

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
        jEffekte_2.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 110, -1, -1));

        jSeparator6.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jEffekte_2.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 20, 10, 200));

        jSeparator7.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jEffekte_2.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, 30, 200));

        jLabel104.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel104.setText(bundle.getString("LDG30erPlus.jLabel104.text")); // NOI18N
        jEffekte_2.add(jLabel104, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, -1, -1));

        jLabel206.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel206.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel206.setText(bundle.getString("LDG30erPlus.jLabel206.text")); // NOI18N
        jEffekte_2.add(jLabel206, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 410, 140, 20));

        jLabel240.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel240.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel240.setText(bundle.getString("LDG4x.jLabel109.text")); // NOI18N
        jEffekte_2.add(jLabel240, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 410, 50, 20));

        jLabel3.setFont(new java.awt.Font("sansserif", 0, 12)); // NOI18N
        jLabel3.setText(bundle.getString("LDG4x.jLabel3.text")); // NOI18N
        jEffekte_2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, -1, -1));

        jLabel4.setFont(new java.awt.Font("sansserif", 0, 12)); // NOI18N
        jLabel4.setText(bundle.getString("LDG4x.jLabel4.text")); // NOI18N
        jEffekte_2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, -1, -1));

        jLabel7.setFont(new java.awt.Font("sansserif", 0, 12)); // NOI18N
        jLabel7.setText(bundle.getString("LDG4x.jLabel7.text")); // NOI18N
        jEffekte_2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, -1, -1));

        jDimmPeriode.setFont(new java.awt.Font("sansserif", 0, 12)); // NOI18N
        jDimmPeriode.setText("0");
        jDimmPeriode.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jDimmPeriodeFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jDimmPeriodeFocusLost(evt);
            }
        });
        jDimmPeriode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jDimmPeriodeKeyReleased(evt);
            }
        });
        jEffekte_2.add(jDimmPeriode, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 220, 40, 20));

        jLabel9.setFont(new java.awt.Font("sansserif", 0, 12)); // NOI18N
        jLabel9.setText(bundle.getString("LDG4x.jLabel9.text")); // NOI18N
        jEffekte_2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 50, -1, 20));

        jFahrStWalzer.setFont(new java.awt.Font("sansserif", 0, 12)); // NOI18N
        jFahrStWalzer.setText(bundle.getString("LDG4x.jFahrStWalzer.text")); // NOI18N
        jFahrStWalzer.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jFahrStWalzerFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jFahrStWalzerFocusLost(evt);
            }
        });
        jFahrStWalzer.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jFahrStWalzerKeyReleased(evt);
            }
        });
        jEffekte_2.add(jFahrStWalzer, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 50, 40, -1));

        jLabel10.setFont(new java.awt.Font("sansserif", 0, 12)); // NOI18N
        jLabel10.setText(bundle.getString("LDG4x.jLabel10.text")); // NOI18N
        jEffekte_2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 50, -1, 20));

        jUeberlastSchwelle.setFont(new java.awt.Font("sansserif", 0, 12)); // NOI18N
        jUeberlastSchwelle.setText("50");
        jUeberlastSchwelle.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jUeberlastSchwelleFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jUeberlastSchwelleFocusLost(evt);
            }
        });
        jUeberlastSchwelle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jUeberlastSchwelleKeyReleased(evt);
            }
        });
        jEffekte_2.add(jUeberlastSchwelle, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 50, 40, 20));

        jLabel11.setFont(new java.awt.Font("sansserif", 0, 12)); // NOI18N
        jLabel11.setText(bundle.getString("LDG4x.jLabel11.text")); // NOI18N
        jEffekte_2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 70, -1, 20));

        jUeberlastDauer.setFont(new java.awt.Font("sansserif", 0, 12)); // NOI18N
        jUeberlastDauer.setText("2");
        jUeberlastDauer.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jUeberlastDauerFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jUeberlastDauerFocusLost(evt);
            }
        });
        jUeberlastDauer.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jUeberlastDauerKeyReleased(evt);
            }
        });
        jEffekte_2.add(jUeberlastDauer, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 70, 40, 20));

        jDecodereigenschaften.addTab(bundle.getString("LDG4x.jEffekte_2.TabConstraints.tabTitle"), jEffekte_2); // NOI18N

        jEffekte_3.setToolTipText(bundle.getString("LDG4x.jEffekte_3toolTipText")); // NOI18N
        jEffekte_3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jEffekte_3.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jEffekte_3ComponentShown(evt);
            }
        });
        jEffekte_3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel134.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel134.setText(bundle.getString("LDG30erPlus.jLabel134.text")); // NOI18N
        jEffekte_3.add(jLabel134, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, -1, -1));

        jLabel135.setFont(new java.awt.Font("Noto Sans", 0, 10)); // NOI18N
        jLabel135.setText(bundle.getString("LDG30erPlus.jLabel135.text")); // NOI18N
        jEffekte_3.add(jLabel135, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 30, -1, -1));

        jLabel136.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel136.setText(bundle.getString("LDG30erPlus.jLabel136.text")); // NOI18N
        jEffekte_3.add(jLabel136, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 30, -1, -1));

        jLabel137.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel137.setText(bundle.getString("LDG30erPlus.jLabel137.text")); // NOI18N
        jEffekte_3.add(jLabel137, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 30, -1, -1));

        jLabel138.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel138.setText(bundle.getString("LDG30erPlus.jLabel138.text")); // NOI18N
        jEffekte_3.add(jLabel138, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 30, -1, -1));

        jLabel139.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel139.setText(bundle.getString("LDG30erPlus.jLabel139.text")); // NOI18N
        jEffekte_3.add(jLabel139, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 30, -1, -1));

        jLabel140.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel140.setText(bundle.getString("LDG30erPlus.jLabel140.text")); // NOI18N
        jEffekte_3.add(jLabel140, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 30, -1, -1));

        jLabel141.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel141.setText(bundle.getString("LDG30erPlus.jLabel141.text")); // NOI18N
        jEffekte_3.add(jLabel141, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 30, -1, -1));

        jLabel142.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel142.setText(bundle.getString("LDG30erPlus.jLabel142.text")); // NOI18N
        jEffekte_3.add(jLabel142, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 30, -1, -1));

        jLabel143.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel143.setText(bundle.getString("LDG30erPlus.jLabel143.text")); // NOI18N
        jEffekte_3.add(jLabel143, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 30, -1, -1));

        jLabel148.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel148.setText(bundle.getString("LDG30erPlus.jLabel148.text")); // NOI18N
        jEffekte_3.add(jLabel148, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 30, -1, -1));

        jLabel150.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel150.setText(bundle.getString("LDG30erPlus.jLabel150.text")); // NOI18N
        jEffekte_3.add(jLabel150, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 30, -1, -1));

        jLabel132.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel132.setText(bundle.getString("LDG4x.jLabel132.text")); // NOI18N
        jEffekte_3.add(jLabel132, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 280, -1, 20));
        jEffekte_3.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 122, 410, 10));

        jLabel133.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel133.setText(bundle.getString("LDG4x.jLabel133.text")); // NOI18N
        jEffekte_3.add(jLabel133, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 6, 210, 20));
        jEffekte_3.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 410, 10));

        jLabel208.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel208.setText(bundle.getString("LDG4x.jLabel208.text")); // NOI18N
        jEffekte_3.add(jLabel208, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 131, 80, 20));

        jLabel12.setFont(new java.awt.Font("sansserif", 0, 10)); // NOI18N
        jLabel12.setText(bundle.getString("LDG4x.jF0.text")); // NOI18N
        jEffekte_3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, -1, -1));

        jConsF0.setFont(new java.awt.Font("sansserif", 0, 12)); // NOI18N
        jConsF0.setText(bundle.getString("LDG4x.jConsF0.text")); // NOI18N
        jConsF0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jConsF0ActionPerformed(evt);
            }
        });
        jEffekte_3.add(jConsF0, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, -1, -1));

        jConsF1.setFont(new java.awt.Font("sansserif", 0, 12)); // NOI18N
        jConsF1.setText(bundle.getString("LDG4x.jConsF1.text")); // NOI18N
        jConsF1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jConsF1ActionPerformed(evt);
            }
        });
        jEffekte_3.add(jConsF1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, -1, -1));

        jConsF2.setFont(new java.awt.Font("sansserif", 0, 12)); // NOI18N
        jConsF2.setText(bundle.getString("LDG4x.jConsF2.text")); // NOI18N
        jConsF2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jConsF2ActionPerformed(evt);
            }
        });
        jEffekte_3.add(jConsF2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, -1, -1));

        jConsF3.setFont(new java.awt.Font("sansserif", 0, 12)); // NOI18N
        jConsF3.setText(bundle.getString("LDG4x.jConsF3.text")); // NOI18N
        jConsF3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jConsF3ActionPerformed(evt);
            }
        });
        jEffekte_3.add(jConsF3, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 50, -1, -1));

        jConsF4.setFont(new java.awt.Font("sansserif", 0, 12)); // NOI18N
        jConsF4.setText(bundle.getString("LDG4x.jConsF4.text")); // NOI18N
        jConsF4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jConsF4ActionPerformed(evt);
            }
        });
        jEffekte_3.add(jConsF4, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 50, -1, -1));

        jConsF5.setFont(new java.awt.Font("sansserif", 0, 12)); // NOI18N
        jConsF5.setText(bundle.getString("LDG4x.jConsF5.text")); // NOI18N
        jConsF5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jConsF5ActionPerformed(evt);
            }
        });
        jEffekte_3.add(jConsF5, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 50, -1, -1));

        jConsF6.setFont(new java.awt.Font("sansserif", 0, 12)); // NOI18N
        jConsF6.setText(bundle.getString("LDG4x.jConsF6.text")); // NOI18N
        jConsF6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jConsF6ActionPerformed(evt);
            }
        });
        jEffekte_3.add(jConsF6, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 50, -1, -1));

        jConsF7.setFont(new java.awt.Font("sansserif", 0, 12)); // NOI18N
        jConsF7.setText(bundle.getString("LDG4x.jConsF7.text")); // NOI18N
        jConsF7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jConsF7ActionPerformed(evt);
            }
        });
        jEffekte_3.add(jConsF7, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 50, -1, -1));

        jConsF8.setFont(new java.awt.Font("sansserif", 0, 12)); // NOI18N
        jConsF8.setText(bundle.getString("LDG4x.jConsF8.text")); // NOI18N
        jConsF8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jConsF8ActionPerformed(evt);
            }
        });
        jEffekte_3.add(jConsF8, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 50, -1, -1));

        jConsF9.setFont(new java.awt.Font("sansserif", 0, 12)); // NOI18N
        jConsF9.setText(bundle.getString("LDG4x.jConsF9.text")); // NOI18N
        jConsF9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jConsF9ActionPerformed(evt);
            }
        });
        jEffekte_3.add(jConsF9, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 50, -1, -1));

        jConsF10.setFont(new java.awt.Font("sansserif", 0, 12)); // NOI18N
        jConsF10.setText(bundle.getString("LDG4x.jConsF10.text")); // NOI18N
        jConsF10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jConsF10ActionPerformed(evt);
            }
        });
        jEffekte_3.add(jConsF10, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 50, -1, -1));

        jConsF11.setFont(new java.awt.Font("sansserif", 0, 12)); // NOI18N
        jConsF11.setText(bundle.getString("LDG4x.jConsF11.text")); // NOI18N
        jConsF11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jConsF11ActionPerformed(evt);
            }
        });
        jEffekte_3.add(jConsF11, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 50, -1, -1));

        jConsF12.setFont(new java.awt.Font("sansserif", 0, 12)); // NOI18N
        jConsF12.setText(bundle.getString("LDG4x.jConsF12.text")); // NOI18N
        jConsF12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jConsF12ActionPerformed(evt);
            }
        });
        jEffekte_3.add(jConsF12, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 50, -1, -1));

        jLabel13.setFont(new java.awt.Font("sansserif", 0, 12)); // NOI18N
        jLabel13.setText(bundle.getString("LDG4x.jLabel13.text")); // NOI18N
        jEffekte_3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, -1, 20));

        jConsAdr.setFont(new java.awt.Font("sansserif", 0, 12)); // NOI18N
        jConsAdr.setText("0");
        jConsAdr.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jConsAdrFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jConsAdrFocusLost(evt);
            }
        });
        jConsAdr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jConsAdrKeyReleased(evt);
            }
        });
        jEffekte_3.add(jConsAdr, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, 30, 20));

        jLabel14.setFont(new java.awt.Font("sansserif", 0, 12)); // NOI18N
        jLabel14.setText(bundle.getString("LDG4x.jLabel14.text")); // NOI18N
        jEffekte_3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 110, -1));

        jLastregEin.setFont(new java.awt.Font("sansserif", 0, 12)); // NOI18N
        jLastregEin.setText(bundle.getString("LDG4x.jLastregEin.text")); // NOI18N
        jLastregEin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jLastregEinActionPerformed(evt);
            }
        });
        jEffekte_3.add(jLastregEin, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 160, -1, -1));

        jLabel15.setFont(new java.awt.Font("sansserif", 0, 12)); // NOI18N
        jLabel15.setText(bundle.getString("LDG4x.jLabel15.text")); // NOI18N
        jEffekte_3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 110, 20));

        jVerstaerk.setFont(new java.awt.Font("sansserif", 0, 12)); // NOI18N
        jVerstaerk.setText("5");
        jVerstaerk.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jVerstaerkFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jVerstaerkFocusLost(evt);
            }
        });
        jVerstaerk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jVerstaerkKeyReleased(evt);
            }
        });
        jEffekte_3.add(jVerstaerk, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 180, 30, 20));

        jLabel16.setFont(new java.awt.Font("sansserif", 0, 12)); // NOI18N
        jLabel16.setText(bundle.getString("LDG4x.jLabel16.text")); // NOI18N
        jEffekte_3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, -1, -1));

        jLabel18.setFont(new java.awt.Font("sansserif", 0, 12)); // NOI18N
        jLabel18.setText(bundle.getString("LDG4x.jLabel18.text")); // NOI18N
        jEffekte_3.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, -1, -1));

        jLabel19.setFont(new java.awt.Font("sansserif", 0, 12)); // NOI18N
        jLabel19.setText(bundle.getString("LDG4x.jLabel19.text")); // NOI18N
        jEffekte_3.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, -1, -1));

        jKP.setFont(new java.awt.Font("sansserif", 0, 12)); // NOI18N
        jKP.setText("32");
        jKP.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jKPFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jKPFocusLost(evt);
            }
        });
        jKP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jKPKeyReleased(evt);
            }
        });
        jEffekte_3.add(jKP, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 200, 30, 20));

        jKI.setFont(new java.awt.Font("sansserif", 0, 12)); // NOI18N
        jKI.setText("5");
        jKI.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jKIFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jKIFocusLost(evt);
            }
        });
        jKI.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jKIKeyReleased(evt);
            }
        });
        jEffekte_3.add(jKI, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 220, 30, 20));

        jKD.setFont(new java.awt.Font("sansserif", 0, 12)); // NOI18N
        jKD.setText("4");
        jKD.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jKDFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jKDFocusLost(evt);
            }
        });
        jKD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jKDKeyReleased(evt);
            }
        });
        jEffekte_3.add(jKD, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 240, 30, 20));

        jDecodereigenschaften.addTab(bundle.getString("LDG4x.jSound.TabConstraints.tabTitle"), jEffekte_3); // NOI18N

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
        jAnalog.add(jF1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, -1, -1));

        jF2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF2.setText(bundle.getString("LDG30erPlus.jF2.text")); // NOI18N
        jF2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF2ActionPerformed(evt);
            }
        });
        jAnalog.add(jF2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, -1, -1));

        jF3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF3.setText(bundle.getString("LDG30erPlus.jF3.text")); // NOI18N
        jF3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF3ActionPerformed(evt);
            }
        });
        jAnalog.add(jF3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, -1, -1));

        jF4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF4.setText(bundle.getString("LDG30erPlus.jF4.text")); // NOI18N
        jF4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF4ActionPerformed(evt);
            }
        });
        jAnalog.add(jF4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, -1, -1));

        jF5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF5.setText(bundle.getString("LDG30erPlus.jF5.text")); // NOI18N
        jF5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF5ActionPerformed(evt);
            }
        });
        jAnalog.add(jF5, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, -1, -1));

        jF6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF6.setText(bundle.getString("LDG30erPlus.jF6.text")); // NOI18N
        jF6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF6ActionPerformed(evt);
            }
        });
        jAnalog.add(jF6, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 120, -1, -1));

        jF7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF7.setText(bundle.getString("LDG30erPlus.jF7.text")); // NOI18N
        jF7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF7ActionPerformed(evt);
            }
        });
        jAnalog.add(jF7, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 150, -1, -1));

        jF8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF8.setText(bundle.getString("LDG30erPlus.jF8.text")); // NOI18N
        jF8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF8ActionPerformed(evt);
            }
        });
        jAnalog.add(jF8, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, -1, -1));

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

        jF0.setFont(new java.awt.Font("sansserif", 0, 12)); // NOI18N
        jF0.setText(bundle.getString("LDG4x.jF0.text")); // NOI18N
        jF0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF0ActionPerformed(evt);
            }
        });
        jAnalog.add(jF0, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, -1, -1));

        jF9.setFont(new java.awt.Font("sansserif", 0, 12)); // NOI18N
        jF9.setText(bundle.getString("LDG4x.jF9.text")); // NOI18N
        jF9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF9ActionPerformed(evt);
            }
        });
        jAnalog.add(jF9, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, -1, -1));

        jF10.setFont(new java.awt.Font("sansserif", 0, 12)); // NOI18N
        jF10.setText(bundle.getString("LDG4x.jF10.text")); // NOI18N
        jF10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF10ActionPerformed(evt);
            }
        });
        jAnalog.add(jF10, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 120, -1, -1));

        jF11.setFont(new java.awt.Font("sansserif", 0, 12)); // NOI18N
        jF11.setText(bundle.getString("LDG4x.jF11.text")); // NOI18N
        jF11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF11ActionPerformed(evt);
            }
        });
        jAnalog.add(jF11, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 150, -1, -1));

        jF12.setFont(new java.awt.Font("sansserif", 0, 12)); // NOI18N
        jF12.setText(bundle.getString("LDG4x.jF12.text")); // NOI18N
        jF12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF12ActionPerformed(evt);
            }
        });
        jAnalog.add(jF12, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 60, -1, -1));

        jDecodereigenschaften.addTab(bundle.getString("LDG4x.jAnalog.TabConstraints.tabTitle"), jAnalog); // NOI18N

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
        jLabel36.setText(bundle.getString("LDG30erPlus.jLabel36.text")); // NOI18N

        javax.swing.GroupLayout jKommentarLayout = new javax.swing.GroupLayout(jKommentar);
        jKommentar.setLayout(jKommentarLayout);
        jKommentarLayout.setHorizontalGroup(
            jKommentarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jKommentarLayout.createSequentialGroup()
                .addGroup(jKommentarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jKommentarLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE))
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
                .addContainerGap())
        );

        jDecodereigenschaften.addTab(bundle.getString("LDG4x.jPanel3.TabConstraints.tabTitle"), jKommentar); // NOI18N

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
                                        .addComponent(jLabel28))))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jDecodereigenschaften, javax.swing.GroupLayout.PREFERRED_SIZE, 476, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jOpen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jCV_LesenSchreiben, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jAbbrechen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jDecodereigenschaften, javax.swing.GroupLayout.PREFERRED_SIZE, 519, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0))
        );

        jSave.getAccessibleContext().setAccessibleName(bundle.getString("LDG4x.Speichern.Text")); // NOI18N
        jCV_LesenSchreiben.getAccessibleContext().setAccessibleName(bundle.getString("LDG4x.Lesen_Schreiben.text")); // NOI18N
        jOpen.getAccessibleContext().setAccessibleName(bundle.getString("LDG4x.Oeffnen.text")); // NOI18N
        jAbbrechen.getAccessibleContext().setAccessibleName(bundle.getString("LDG4x.Schliessen.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        jPanel1.getAccessibleContext().setAccessibleName(bundle.getString("LDG4x.jPanel1.AccessibleContext.accessibleName")); // NOI18N

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCV_LesenSchreibenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCV_LesenSchreibenActionPerformed
        try {
            // Hier wird die Schnittstelle geöffnet und die CVs werden zur Zentrale gesendet
            ReadWriteCV cvwr = new ReadWriteCV(this, true, CVNavi, CV);
        } catch (IOException ex) {
            CVNavi.mbDeviceReadProblem(this);
        }
    }//GEN-LAST:event_jCV_LesenSchreibenActionPerformed

    private void jCV_InhaltFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jCV_InhaltFocusLost
        int adr;
        int oldValue;
        int currCV = getCVfromIndexString(jCV_Anzeige, "CV#");
        String oriEingabe = jCV_Inhalt.getText();
        // int cvValue = CVNavi.checkTextField( this, jCV_Inhalt, 0, 255, 22222, false);
        int cvValue = CVNavi.checkTextField(this, jCV_Inhalt, 0, 511, -1, false);
        if (cvValue == -1) {
            System.out.println("jCV_InhaltFocusLost CV=" + currCV + " Wert=\"" + oriEingabe + "\" IGNORIERT");
            jCV_Inhalt.setText(oriEingabe);
            getDefaultToolkit().beep();
            jCV_Inhalt.grabFocus();
            return;
        }
        String s = jCV_Inhalt.getText();

        //int i = currCV -1 ;
        //int j = cvValue;
        //switch(i) {
        switch (currCV) {
            case 1: //CV#1
                cvValue = CVNavi.checkTextField(this, jCV_Inhalt, 1, 255, 3, true);
                s = jCV_Inhalt.getText();
                if (cvValue > 127) {
                    CVNavi.mbAdr128MMonly(this);
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
                if (debugLevel >= 1) {
                    System.out.println("TODO CV=" + currCV + " Wert=" + cvValue + " ohne weiteren Test übernommen");
                }
                break;

            case 7: //CV#7 Version
            case 8: //CV#8 Hersteller
                System.out.println("jCV_InhaltFocusLost CV=" + currCV + " Wert=" + cvValue + " IGNORIERT");
                return;

            case 9: //CV#9 Motorfrequenz
                if (debugLevel >= 1) {
                    System.out.println("TODO CV=" + currCV + " Wert=" + cvValue + " ohne weiteren Test übernommen");
                }
                break;

            case 11:
                jPacketTimeOut.setText("" + cvValue);
                break;

            case 12:
                cvValue = CVNavi.checkTextField(this, jCV_Inhalt, 0, 1, 1, true);
                jAnalogRW_G.setSelected((cvValue & 1) == 1);
                jAnalogRW_W.setSelected(!((cvValue & 1) == 1));
                break;

            case 13: //CV#13 im Analogbetrieb aktive Funktionen
                jF1.setSelected((cvValue & 1) == 1);
                jF2.setSelected((cvValue & 2) == 2);
                jF3.setSelected((cvValue & 4) == 4);
                jF4.setSelected((cvValue & 8) == 8);
                jF5.setSelected((cvValue & 16) == 16);
                jF6.setSelected((cvValue & 32) == 32);
                jF7.setSelected((cvValue & 64) == 64);
                jF8.setSelected((cvValue & 128) == 128);
                break;

            case 17: //CV#17 erweiterte Adresse (high)
                cvValue = CVNavi.checkTextField(this, jCV_Inhalt, 192, 255, 192, true);
                if (cvValue < 192) {
                    CVNavi.mbValueNaNcv(this, 192, 255, 17, true);
                    cvValue = 192;
                    jCV_Inhalt.setText("192");
                }
                CV[1][currCV] = cvValue;
                jKurzeAdr.setSelected(false);
                jlangeAdr.setSelected(true);
                adr = (CV[1][17] - 192) * 256 + CV[1][18];
                jDecoderAdresse.setText("" + adr);
                break;

            case 18: //CV#18 erweiterte Adresse (low)
                jDecoderAdresse.setText(s);
                jKurzeAdr.setSelected(false);
                jlangeAdr.setSelected(true);
                break;

            case 19: //CV#19 Consistadresse
                if (cvValue > 127) {
                    CVNavi.mbValueConsist(this, 0, 127);
                    cvValue = 127;
                    s = "127";
                    jCV_Inhalt.setText(s);
                }
                jDecoderAdresse1.setText(s);
                break;

            case 27: // Bremsverhalten bei Gleichspannung ; Seite 62
                switch (cvValue) {
                    case 0:
                    case 32:
                    case 64:
                    case 96:
                        break;
                    default:
                        CVNavi.mbInvalidValue(this, currCV, cvValue, "0 32 64 96");
                        System.out.println("jCV_InhaltFocusLost CV=" + currCV + " Wert=" + cvValue + " IGNORIERT");
                        return;
                }
                break;

            case 28: //CV#28 RailCom
                jBroadCasst.setSelected((cvValue & 1) == 1);
                jChannel2.setSelected((cvValue & 2) == 2);
                jRCplus.setSelected((cvValue & 128) == 128);
                break;

            case 29: //CV#29 Konfigurationsdaten 1
                jRichtung.setSelected((cvValue & 1) == 1);
                jFS.setSelected((cvValue & 2) == 2);
                jAnalog1.setSelected((cvValue & 4) == 4);
                jRailCom.setSelected((cvValue & 8) == 8);
                // TODO Was ist mit 16 alternative Kennlinie ?
                // Gibt es die nicht ?
                jLongAddr.setSelected((cvValue & 32) == 32);

                jKurzeAdr.setSelected((cvValue & 32) == 0);
                jlangeAdr.setSelected((cvValue & 32) == 32);
                break;

            case 30: // Hilfsregister ; Seite 58
            case 31: // Index für höhere CV-PAges ; Seite 58
            case 32: // Index für höhere CV-PAges ; Seite 58
                System.out.println("jCV_InhaltFocusLost CV=" + currCV + " Wert=" + cvValue + " IGNORIERT");
                break;

            case 33: //CV#33
                jF1_1.setSelected((cvValue & 1) == 1);
                jF1_2.setSelected((cvValue & 2) == 2);
                jF1_3.setSelected((cvValue & 4) == 4);
                jF1_4.setSelected((cvValue & 8) == 8);
                jF1_5.setSelected((cvValue & 16) == 16);
                jF1_6.setSelected((cvValue & 32) == 32);
                jF1_7.setSelected((cvValue & 64) == 64);
                jF1_8.setSelected((cvValue & 128) == 128);
                break;

            case 34: //CV#34
                jF2_1.setSelected((cvValue & 1) == 1);
                jF2_2.setSelected((cvValue & 2) == 2);
                jF2_3.setSelected((cvValue & 4) == 4);
                jF2_4.setSelected((cvValue & 8) == 8);
                jF2_5.setSelected((cvValue & 16) == 16);
                jF2_6.setSelected((cvValue & 32) == 32);
                jF2_7.setSelected((cvValue & 64) == 64);
                jF2_8.setSelected((cvValue & 128) == 128);
                break;

            case 35: //CV#35 F1
                jF3_1.setSelected((cvValue & 1) == 1);
                jF3_2.setSelected((cvValue & 2) == 2);
                jF3_3.setSelected((cvValue & 4) == 4);
                jF3_4.setSelected((cvValue & 8) == 8);
                jF3_5.setSelected((cvValue & 16) == 16);
                jF3_6.setSelected((cvValue & 32) == 32);
                jF3_7.setSelected((cvValue & 64) == 64);
                jF3_8.setSelected((cvValue & 128) == 128);
                break;

            case 36: //CV#36 F2
                jF4_1.setSelected((cvValue & 1) == 1);
                jF4_2.setSelected((cvValue & 2) == 2);
                jF4_3.setSelected((cvValue & 4) == 4);
                jF4_4.setSelected((cvValue & 8) == 8);
                jF4_5.setSelected((cvValue & 16) == 16);
                jF4_6.setSelected((cvValue & 32) == 32);
                jF4_7.setSelected((cvValue & 64) == 64);
                jF4_8.setSelected((cvValue & 128) == 128);
                break;

            case 37: //CV#37 F3
                jF5_1.setSelected((cvValue & 1) == 1);
                jF5_2.setSelected((cvValue & 2) == 2);
                jF5_3.setSelected((cvValue & 4) == 4);
                jF5_4.setSelected((cvValue & 8) == 8);
                jF5_5.setSelected((cvValue & 16) == 16);
                jF5_6.setSelected((cvValue & 32) == 32);
                jF5_7.setSelected((cvValue & 64) == 64);
                jF5_8.setSelected((cvValue & 128) == 128);
                break;

            case 38: //CV#38 F4
                jF6_1.setSelected((cvValue & 1) == 1);
                jF6_2.setSelected((cvValue & 2) == 2);
                jF6_3.setSelected((cvValue & 4) == 4);
                jF6_4.setSelected((cvValue & 8) == 8);
                jF6_5.setSelected((cvValue & 16) == 16);
                jF6_6.setSelected((cvValue & 32) == 32);
                jF6_7.setSelected((cvValue & 64) == 64);
                jF6_8.setSelected((cvValue & 128) == 128);
                break;

            case 39: //CV#39 F5
                jF7_1.setSelected((cvValue & 1) == 1);
                jF7_2.setSelected((cvValue & 2) == 2);
                jF7_3.setSelected((cvValue & 4) == 4);
                jF7_4.setSelected((cvValue & 8) == 8);
                jF7_5.setSelected((cvValue & 16) == 16);
                jF7_6.setSelected((cvValue & 32) == 32);
                jF7_7.setSelected((cvValue & 64) == 64);
                jF7_8.setSelected((cvValue & 128) == 128);
                break;

            case 40: //CV#40 F6
                jF8_1.setSelected((cvValue & 1) == 1);
                jF8_2.setSelected((cvValue & 2) == 2);
                jF8_3.setSelected((cvValue & 4) == 4);
                jF8_4.setSelected((cvValue & 8) == 8);
                jF8_5.setSelected((cvValue & 16) == 16);
                jF8_6.setSelected((cvValue & 32) == 32);
                jF8_7.setSelected((cvValue & 64) == 64);
                jF8_8.setSelected((cvValue & 128) == 128);
                break;

            case 41: //CV#41 F7
                jF9_1.setSelected((cvValue & 1) == 1);
                jF9_2.setSelected((cvValue & 2) == 2);
                jF9_3.setSelected((cvValue & 4) == 4);
                jF9_4.setSelected((cvValue & 8) == 8);
                jF9_5.setSelected((cvValue & 16) == 16);
                jF9_6.setSelected((cvValue & 32) == 32);
                jF9_7.setSelected((cvValue & 64) == 64);
                jF9_8.setSelected((cvValue & 128) == 128);
                break;

            case 42: //CV#42 F8
                jF10_1.setSelected((cvValue & 1) == 1);
                jF10_2.setSelected((cvValue & 2) == 2);
                jF10_3.setSelected((cvValue & 4) == 4);
                jF10_4.setSelected((cvValue & 8) == 8);
                jF10_5.setSelected((cvValue & 16) == 16);
                jF10_6.setSelected((cvValue & 32) == 32);
                jF10_7.setSelected((cvValue & 64) == 64);
                jF10_8.setSelected((cvValue & 128) == 128);
                break;

            case 43: //CV#43 F9
                jF11_1.setSelected((cvValue & 1) == 1);
                jF11_2.setSelected((cvValue & 2) == 2);
                jF11_3.setSelected((cvValue & 4) == 4);
                jF11_4.setSelected((cvValue & 8) == 8);
                jF11_5.setSelected((cvValue & 16) == 16);
                jF11_6.setSelected((cvValue & 32) == 32);
                jF11_7.setSelected((cvValue & 64) == 64);
                jF11_8.setSelected((cvValue & 128) == 128);
                break;

            case 44: //CV#44 F10
                jF12_1.setSelected((cvValue & 1) == 1);
                jF12_2.setSelected((cvValue & 2) == 2);
                jF12_3.setSelected((cvValue & 4) == 4);
                jF12_4.setSelected((cvValue & 8) == 8);
                jF12_5.setSelected((cvValue & 16) == 16);
                jF12_6.setSelected((cvValue & 32) == 32);
                jF12_7.setSelected((cvValue & 64) == 64);
                jF12_8.setSelected((cvValue & 128) == 128);
                break;

            case 45: //CV#45 F11
                jF13_1.setSelected((cvValue & 1) == 1);
                jF13_2.setSelected((cvValue & 2) == 2);
                jF13_3.setSelected((cvValue & 4) == 4);
                jF13_4.setSelected((cvValue & 8) == 8);
                jF13_5.setSelected((cvValue & 16) == 16);
                jF13_6.setSelected((cvValue & 32) == 32);
                jF13_7.setSelected((cvValue & 64) == 64);
                jF13_8.setSelected((cvValue & 128) == 128);
                break;

            case 46: //CV#46 F12
                jF14_1.setSelected((cvValue & 1) == 1);
                jF14_2.setSelected((cvValue & 2) == 2);
                jF14_3.setSelected((cvValue & 4) == 4);
                jF14_4.setSelected((cvValue & 8) == 8);
                jF14_5.setSelected((cvValue & 16) == 16);
                jF14_6.setSelected((cvValue & 32) == 32);
                jF14_7.setSelected((cvValue & 64) == 64);
                jF14_8.setSelected((cvValue & 128) == 128);
                break;

            case 47: //CV47 2te MM-Adresse
                cvValue = CVNavi.checkTextField(this, jCV_Inhalt, 1, 255, 4, true);
                s = jCV_Inhalt.getText();
                jMM_Addr_2.setText(s);
                break;

            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
                System.out.println("TODO CV=" + currCV + " Wert=" + cvValue + " ohne weiteren Test übernommen");
                break;

            case 53: //CV#53
                jAuxBlinkf.setSelected(!((cvValue & 1) == 1));
                jAuxInvf.setSelected(!((cvValue & 2) == 2));
                jAuxKickf.setSelected((cvValue & 4) == 4);
                jAuxFeuerf.setSelected((cvValue & 16) == 16);
                jAuxDimf.setSelected((cvValue & 32) == 32);
                jAuxSpSchf.setSelected((cvValue & 64) == 64);
                break;

            case 54: //CV#54
                jAuxBlinkr.setSelected(!((cvValue & 1) == 1));
                jAuxInvr.setSelected(!((cvValue & 2) == 2));
                jAuxKickr.setSelected((cvValue & 4) == 4);
                jAuxFeuerr.setSelected((cvValue & 16) == 16);
                jAuxDimr.setSelected((cvValue & 32) == 32);
                jAuxSpSchr.setSelected((cvValue & 64) == 64);
                break;

            case 55: //CV#55
                jAuxBlink1.setSelected(!((cvValue & 1) == 1));
                jAuxInv1.setSelected(!((cvValue & 2) == 2));
                jAuxKick1.setSelected((cvValue & 4) == 4);
                jAuxFeuer1.setSelected((cvValue & 16) == 16);
                jAuxDim1.setSelected((cvValue & 32) == 32);
                jAuxSpSch1.setSelected((cvValue & 64) == 64);
                break;

            case 56: //CV#56
                jAuxBlink2.setSelected(!((cvValue & 1) == 1));
                jAuxInv2.setSelected(!((cvValue & 2) == 2));
                jAuxKick2.setSelected((cvValue & 4) == 4);
                jAuxFeuer2.setSelected((cvValue & 16) == 16);
                jAuxDim2.setSelected((cvValue & 32) == 32);
                jAuxSpSch2.setSelected((cvValue & 64) == 64);
                break;

            case 57: //CV#57
                jAuxBlink3.setSelected(!((cvValue & 1) == 1));
                jAuxInv3.setSelected(!((cvValue & 2) == 2));
                jAuxKick3.setSelected((cvValue & 4) == 4);
                jAuxFeuer3.setSelected((cvValue & 16) == 16);
                jAuxDim3.setSelected((cvValue & 32) == 32);
                jAuxSpSch3.setSelected((cvValue & 64) == 64);
                break;

            case 58: //CV#58
                jAuxBlink4.setSelected(!((cvValue & 1) == 1));
                jAuxInv4.setSelected(!((cvValue & 2) == 2));
                jAuxKick4.setSelected((cvValue & 4) == 4);
                jAuxFeuer4.setSelected((cvValue & 16) == 16);
                jAuxDim4.setSelected((cvValue & 32) == 32);
                jAuxSpSch4.setSelected((cvValue & 64) == 64);
                break;

            case 59: //CV#59
                jAuxBlink5.setSelected(!((cvValue & 1) == 1));
                jAuxInv5.setSelected(!((cvValue & 2) == 2));
                jAuxKick5.setSelected((cvValue & 4) == 4);
                jAuxFeuer5.setSelected((cvValue & 16) == 16);
                jAuxSpSch5.setSelected((cvValue & 64) == 64);
                break;

            case 60: //CV#60
                jAuxBlink6.setSelected(!((cvValue & 1) == 1));
                jAuxInv6.setSelected(!((cvValue & 2) == 2));
                jAuxKick6.setSelected((cvValue & 4) == 4);
                jAuxFeuer6.setSelected((cvValue & 16) == 16);
                jAuxSpSch6.setSelected((cvValue & 64) == 64);
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
                System.out.println("alternative Kennlinie CV=" + currCV + " Wert=" + cvValue + " ohne weiteren Test übernommen");
                break;

            case 96:
            case 97:
            case 98:
            case 99:
            case 100:
            case 101:
            case 103:
                System.out.println("TODO CV=" + currCV + " Wert=" + cvValue + " ohne weiteren Test übernommen");
                break;

            case 104:
            case 105:
            case 106:
            case 107:
            case 108:
            case 109:
            case 110:
            case 111:
                System.out.println("TODO CV=" + currCV + " Wert=" + cvValue + " ohne weiteren Test übernommen");
                break;

            case 112:
            case 113:
            case 114:
                System.out.println("TODO CV=" + currCV + " Wert=" + cvValue + " ohne weiteren Test übernommen");
                break;

            case 115:
            case 116:
            case 117:
            case 118:
            case 119:
            case 120:
            case 121:
            case 122:
                System.out.println("TODO CV=" + currCV + " Wert=" + cvValue + " ohne weiteren Test übernommen");
                break;

            case 123:
            case 124:
            case 125:
            case 126:
            case 127:
            case 128:
            case 129:
            case 130:
                System.out.println("TODO CV=" + currCV + " Wert=" + cvValue + " ohne weiteren Test übernommen");
                break;

            case 131:
                System.out.println("TODO CV=" + currCV + " Wert=" + cvValue + " ohne weiteren Test übernommen");
                break;

            case 132:
            case 133:
            case 134:
            case 135:
            case 136:
            case 137:
            case 138:
            case 139:
                System.out.println("TODO CV=" + currCV + " Wert=" + cvValue + " ohne weiteren Test übernommen");
                break;

            case 140:
            case 141:
            case 142:
            case 143:
            case 144:
            case 145:
            case 146:
            case 147:
                System.out.println("TODO CV=" + currCV + " Wert=" + cvValue + " ohne weiteren Test übernommen");
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
                System.out.println("TODO CV=" + currCV + " Wert=" + cvValue + " ohne weiteren Test übernommen");
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
                System.out.println("TODO CV=" + currCV + " Wert=" + cvValue + " ohne weiteren Test übernommen");
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
                System.out.println("TODO CV=" + currCV + " Wert=" + cvValue + " ohne weiteren Test übernommen");
                break;

            case 196:
            case 197:
            case 198:
            case 199:
                System.out.println("TODO CV=" + currCV + " Wert=" + cvValue + " ohne weiteren Test übernommen");
                break;

            default:
                System.out.println("jCV_InhaltFocusLost CV=" + currCV + " Wert=" + cvValue + " ohne weiteren Test übernommen DEBUGGING");
        }
        if (cvValue == -1) {
            System.out.println("jCV_InhaltFocusLost CV=" + currCV + " Wert=\"" + oriEingabe + "\" IGNORIERT");
            jCV_Inhalt.setText(oriEingabe);
            getDefaultToolkit().beep();
            jCV_Inhalt.grabFocus();
        } else {
            CV[1][currCV] = cvValue;
            jCV_Inhalt.setText("" + cvValue);
        }
    }//GEN-LAST:event_jCV_InhaltFocusLost

    private void jCV_InhaltKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCV_InhaltKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus();
        }
    }//GEN-LAST:event_jCV_InhaltKeyReleased

    private void jKurzeAdrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jKurzeAdrActionPerformed
        int j = CV[1][1];
        if (j < 1 || j > 255) {
            CVNavi.mbValueNaN(this, 1, 255, true);
            j = 1;
        } else if (j > 127) {
            CVNavi.mbAdr128MMonly(this);
        }
        CV[1][1] = j;
        jLongAddr.setSelected(false);
        CV[1][29] &= ~32;
        jCV_Anzeige.setSelectedItem("CV#" + 1);
        jDecoderAdresse.setText("" + j);
    }//GEN-LAST:event_jKurzeAdrActionPerformed

    private void jOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jOpenActionPerformed
        jCV_Inhalt.setText("62");
        jCV_Anzeige.setSelectedItem("CV#" + 8);
        SaveOpenDialog od;
        switch (CVNavi.Decoder) {
            case c.LD_G31Plus:
            case c.LD_G33Plus:
            case c.LD_G34Plus:
                od = new SaveOpenDialog(this, true, true, CVs, this, "30p");
                break;

            case c.LD_G36Plus:
                od = new SaveOpenDialog(this, true, true, CVs, this, "36p");
                break;

            case c.LD_G41:
            case c.LD_G42:
            case c.LD_W42:
            case c.LD_G42_2:
            case c.LD_W42_2:
            case c.LD_G43:
            case c.LD_G44:
                od = new SaveOpenDialog(this, true, true, CVs, this, "ld4x");
                break;
        }
        updateTabs();
    }//GEN-LAST:event_jOpenActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        DecTest dt = new DecTest(this, true, CVNavi);
        switch (CVNavi.Decoder) {
            case c.LD_G41:
                dt.jDecType.setText("Decoder: LD-G-41");
                break;
            case c.LD_G42:
                dt.jDecType.setText("Decoder: LD-G-42");
                break;
            case c.LD_W42:
                dt.jDecType.setText("Decoder: LD-W-42");
                break;
            case c.LD_G42_2:
                dt.jDecType.setText("Decoder: LD-G-42.2");
                break;
            case c.LD_W42_2:
                dt.jDecType.setText("Decoder: LD-W_42.2");
                break;
            case c.LD_G43:
                dt.jDecType.setText("Decoder: LD-G-43");
                break;
            case c.LD_G44:
                dt.jDecType.setText("Decoder: LD-G-44");
                break;
        }
        if (jKurzeAdr.isSelected()) {
            dt.DecAddr = CV[1][1];
        } else {
            int n = (CV[1][17] - 192) * 256 + CV[1][18];
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
        int j = CVNavi.checkTextField(this, jDecoderAdresse, 1, 10239, 3, true);
        String s = jDecoderAdresse.getText();
        if (jKurzeAdr.isSelected()) {
            if (j < 1 || j > 255) {
                CVNavi.mbValueNaN(this, 1, 255, true);
                j = 1;
                jDecoderAdresse.setText("3");
            } else if (j > 127) {
                CVNavi.mbAdr128MMonly(this);
            }
            CV[1][1] = j;
            CV[1][29] &= ~32;
            jCV_Anzeige.setSelectedItem("CV#" + 1);
        } else { // lange Adresse
            if (j < 128 || j > 10239) {
                CVNavi.mbValueNaN(this, 128, 10239, true);
                j = 128;
                jDecoderAdresse.setText("128");
            }
            CV[1][29] |= 32;
            CV[1][17] = j / 256 + 192;
            CV[1][18] = j % 256;
            jCV_Anzeige.setSelectedItem("CV#" + 17);
        }
    }//GEN-LAST:event_jDecoderAdresseFocusLost

    private void jDecoderAdresseKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDecoderAdresseKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus();
        }
    }//GEN-LAST:event_jDecoderAdresseKeyReleased

    private void jRichtungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRichtungActionPerformed
        if (jRichtung.isSelected()) {
            CV[1][29] |= 1;
        } else {
            CV[1][29] &= ~1;
        }
        jCV_Anzeige.setSelectedItem("CV#" + 29);
    }//GEN-LAST:event_jRichtungActionPerformed

    private void jFSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFSActionPerformed
        if (jFS.isSelected()) {
            CV[1][29] |= 2;
        } else {
            CV[1][29] &= ~2;
        }
        jCV_Anzeige.setSelectedItem("CV#" + 29);
    }//GEN-LAST:event_jFSActionPerformed

    private void jAnalog1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAnalog1ActionPerformed
        if (jAnalog1.isSelected()) {
            CV[1][29] |= 4;
        } else {
            CV[1][29] &= ~4;
        }
        jCV_Anzeige.setSelectedItem("CV#" + 29);
    }//GEN-LAST:event_jAnalog1ActionPerformed

    private void jRailComActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRailComActionPerformed
        if (jRailCom.isSelected()) {
            CV[1][29] |= 8;
        } else {
            CV[1][29] &= ~8;
        }
        jCV_Anzeige.setSelectedItem("CV#" + 29);
    }//GEN-LAST:event_jRailComActionPerformed

    private void jLongAddrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jLongAddrActionPerformed
        if (jLongAddr.isSelected()) {
            CV[1][29] |= 32;
            jKurzeAdr.setSelected(false);
            jlangeAdr.setSelected(true);
            int n = (CV[1][17] - 192) * 256 + CV[1][18];
            jDecoderAdresse.setText("" + n);
        } else {
            CV[1][29] &= ~32;
            jKurzeAdr.setSelected(true);
            jlangeAdr.setSelected(false);
            jDecoderAdresse.setText("" + CV[1][1]);
        }
        jCV_Anzeige.setSelectedItem("CV#" + 29);
    }//GEN-LAST:event_jLongAddrActionPerformed

    private void jMM_Addr_2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jMM_Addr_2FocusLost
        CV[1][47] = CVNavi.checkTextField(this, jMM_Addr_2, 1, 255, 4, true);
        jCV_Anzeige.setSelectedItem("CV#" + 47);
    }//GEN-LAST:event_jMM_Addr_2FocusLost

    private void jMM_Addr_2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jMM_Addr_2KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus();
        }
    }//GEN-LAST:event_jMM_Addr_2KeyReleased

    private void jBroadCasstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBroadCasstActionPerformed
        if (jBroadCasst.isSelected()) {
            CV[1][28] |= 1;
        } else {
            CV[1][28] &= ~1;
        }
        jCV_Anzeige.setSelectedItem("CV#" + 28);
    }//GEN-LAST:event_jBroadCasstActionPerformed

    private void jChannel2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jChannel2ActionPerformed
        if (jChannel2.isSelected()) {
            CV[1][28] |= 2;
        } else {
            CV[1][28] &= ~2;
        }
        jCV_Anzeige.setSelectedItem("CV#" + 28);
    }//GEN-LAST:event_jChannel2ActionPerformed

    private void jCV29ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jCV29ComponentShown
        jCV_Anzeige.setSelectedItem("CV#" + 29);

        jBroadCasst.setSelected((CV[1][28] & 1) == 1);
        jChannel2.setSelected((CV[1][28] & 2) == 2);
        jRCplus.setSelected((CV[1][28] & 128) == 128);

        jRichtung.setSelected((CV[1][29] & 1) == 1);
        jFS.setSelected((CV[1][29] & 2) == 2);
        jAnalog1.setSelected((CV[1][29] & 4) == 4);
        jRailCom.setSelected((CV[1][29] & 8) == 8);

        if ((CV[1][29] & 32) == 32) {
            jLongAddr.setSelected(true);
            jKurzeAdr.setSelected(false);
            jlangeAdr.setSelected(true);
            int n = (CV[1][17] - 192) * 256 + CV[1][18];
            jDecoderAdresse.setText("" + n);
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

    private void jF1_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF1_1ActionPerformed
        CVFktBits(1, 1, jF1_1.isSelected());
    }//GEN-LAST:event_jF1_1ActionPerformed

    private void jF16_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF16_6ActionPerformed
        CVFktBits(61, 32, jF16_6.isSelected());
    }//GEN-LAST:event_jF16_6ActionPerformed

    private void jF3_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF3_1ActionPerformed
        CVFktBits(9, 1, jF3_1.isSelected());
    }//GEN-LAST:event_jF3_1ActionPerformed

    private void jF4_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF4_1ActionPerformed
        CVFktBits(13, 1, jF4_1.isSelected());
    }//GEN-LAST:event_jF4_1ActionPerformed

    private void jF5_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF5_1ActionPerformed
        CVFktBits(17, 1, jF5_1.isSelected());
    }//GEN-LAST:event_jF5_1ActionPerformed

    private void jF6_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF6_1ActionPerformed
        CVFktBits(21, 1, jF6_1.isSelected());
    }//GEN-LAST:event_jF6_1ActionPerformed

    private void jF7_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF7_1ActionPerformed
        CVFktBits(25, 1, jF7_1.isSelected());
    }//GEN-LAST:event_jF7_1ActionPerformed

    private void jF12_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF12_6ActionPerformed
        CVFktBits(45, 32, jF12_6.isSelected());
    }//GEN-LAST:event_jF12_6ActionPerformed

    private void jF1_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF1_2ActionPerformed
        CVFktBits(1, 2, jF1_2.isSelected());
    }//GEN-LAST:event_jF1_2ActionPerformed

    private void jF2_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF2_2ActionPerformed
        CVFktBits(5, 2, jF2_2.isSelected());
    }//GEN-LAST:event_jF2_2ActionPerformed

    private void jF3_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF3_2ActionPerformed
        CVFktBits(9, 2, jF3_2.isSelected());
    }//GEN-LAST:event_jF3_2ActionPerformed

    private void jF4_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF4_2ActionPerformed
        CVFktBits(13, 2, jF4_2.isSelected());
    }//GEN-LAST:event_jF4_2ActionPerformed

    private void jF5_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF5_2ActionPerformed
        CVFktBits(17, 2, jF5_2.isSelected());
    }//GEN-LAST:event_jF5_2ActionPerformed

    private void jF6_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF6_2ActionPerformed
        CVFktBits(21, 2, jF6_2.isSelected());
    }//GEN-LAST:event_jF6_2ActionPerformed

    private void jF11_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF11_2ActionPerformed
        CVFktBits(41, 2, jF11_2.isSelected());
    }//GEN-LAST:event_jF11_2ActionPerformed

    private void jF13_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF13_1ActionPerformed
        CVFktBits(49, 1, jF13_1.isSelected());
    }//GEN-LAST:event_jF13_1ActionPerformed

    private void jF1_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF1_3ActionPerformed
        CVFktBits(1, 4, jF1_3.isSelected());
    }//GEN-LAST:event_jF1_3ActionPerformed

    private void jF2_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF2_3ActionPerformed
        CVFktBits(5, 4, jF2_3.isSelected());
    }//GEN-LAST:event_jF2_3ActionPerformed

    private void jF3_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF3_3ActionPerformed
        CVFktBits(9, 4, jF3_3.isSelected());
    }//GEN-LAST:event_jF3_3ActionPerformed

    private void jF4_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF4_3ActionPerformed
        CVFktBits(13, 4, jF4_3.isSelected());
    }//GEN-LAST:event_jF4_3ActionPerformed

    private void jF5_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF5_3ActionPerformed
        CVFktBits(17, 4, jF5_3.isSelected());
    }//GEN-LAST:event_jF5_3ActionPerformed

    private void jF6_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF6_3ActionPerformed
        CVFktBits(21, 4, jF6_3.isSelected());
    }//GEN-LAST:event_jF6_3ActionPerformed

    private void jF11_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF11_3ActionPerformed
        CVFktBits(41, 4, jF11_3.isSelected());
    }//GEN-LAST:event_jF11_3ActionPerformed

    private void jF13_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF13_2ActionPerformed
        CVFktBits(49, 2, jF13_2.isSelected());
    }//GEN-LAST:event_jF13_2ActionPerformed

    private void jF1_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF1_4ActionPerformed
        CVFktBits(1, 8, jF1_4.isSelected());
    }//GEN-LAST:event_jF1_4ActionPerformed

    private void jF2_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF2_4ActionPerformed
        CVFktBits(5, 8, jF2_4.isSelected());
    }//GEN-LAST:event_jF2_4ActionPerformed

    private void jF3_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF3_4ActionPerformed
        CVFktBits(9, 8, jF3_4.isSelected());
    }//GEN-LAST:event_jF3_4ActionPerformed

    private void jF4_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF4_4ActionPerformed
        CVFktBits(13, 8, jF4_4.isSelected());
    }//GEN-LAST:event_jF4_4ActionPerformed

    private void jF5_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF5_4ActionPerformed
        CVFktBits(17, 8, jF5_4.isSelected());
    }//GEN-LAST:event_jF5_4ActionPerformed

    private void jF6_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF6_4ActionPerformed
        CVFktBits(21, 8, jF6_4.isSelected());
    }//GEN-LAST:event_jF6_4ActionPerformed

    private void jF11_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF11_4ActionPerformed
        CVFktBits(41, 8, jF11_4.isSelected());
    }//GEN-LAST:event_jF11_4ActionPerformed

    private void jF13_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF13_3ActionPerformed
        CVFktBits(49, 4, jF13_3.isSelected());
    }//GEN-LAST:event_jF13_3ActionPerformed

    private void jF1_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF1_5ActionPerformed
        CVFktBits(1, 16, jF1_5.isSelected());
    }//GEN-LAST:event_jF1_5ActionPerformed

    private void jF2_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF2_5ActionPerformed
        CVFktBits(5, 16, jF2_5.isSelected());
    }//GEN-LAST:event_jF2_5ActionPerformed

    private void jF3_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF3_5ActionPerformed
        CVFktBits(9, 16, jF3_5.isSelected());
    }//GEN-LAST:event_jF3_5ActionPerformed

    private void jF4_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF4_5ActionPerformed
        CVFktBits(13, 16, jF4_5.isSelected());
    }//GEN-LAST:event_jF4_5ActionPerformed

    private void jF5_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF5_5ActionPerformed
        CVFktBits(17, 16, jF5_5.isSelected());
    }//GEN-LAST:event_jF5_5ActionPerformed

    private void jF6_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF6_5ActionPerformed
        CVFktBits(21, 16, jF6_5.isSelected());
    }//GEN-LAST:event_jF6_5ActionPerformed

    private void jF11_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF11_5ActionPerformed
        CVFktBits(41, 16, jF11_5.isSelected());
    }//GEN-LAST:event_jF11_5ActionPerformed

    private void jF13_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF13_4ActionPerformed
        CVFktBits(49, 8, jF13_4.isSelected());
    }//GEN-LAST:event_jF13_4ActionPerformed

    private void jF1_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF1_6ActionPerformed
        CVFktBits(1, 32, jF1_6.isSelected());
    }//GEN-LAST:event_jF1_6ActionPerformed

    private void jF2_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF2_6ActionPerformed
        CVFktBits(5, 32, jF2_6.isSelected());
    }//GEN-LAST:event_jF2_6ActionPerformed

    private void jF3_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF3_6ActionPerformed
        CVFktBits(9, 32, jF3_6.isSelected());
    }//GEN-LAST:event_jF3_6ActionPerformed

    private void jF4_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF4_6ActionPerformed
        CVFktBits(13, 32, jF4_6.isSelected());
    }//GEN-LAST:event_jF4_6ActionPerformed

    private void jF5_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF5_6ActionPerformed
        CVFktBits(17, 32, jF5_6.isSelected());
    }//GEN-LAST:event_jF5_6ActionPerformed

    private void jF6_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF6_6ActionPerformed
        CVFktBits(21, 32, jF6_6.isSelected());
    }//GEN-LAST:event_jF6_6ActionPerformed

    private void jF11_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF11_6ActionPerformed
        CVFktBits(41, 32, jF11_6.isSelected());
    }//GEN-LAST:event_jF11_6ActionPerformed

    private void jF13_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF13_5ActionPerformed
        CVFktBits(49, 32, jF13_5.isSelected());
    }//GEN-LAST:event_jF13_5ActionPerformed

    private void jF16_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF16_1ActionPerformed
        CVFktBits(61, 1, jF16_1.isSelected());
    }//GEN-LAST:event_jF16_1ActionPerformed

    private void jF2_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF2_1ActionPerformed
        CVFktBits(5, 1, jF2_1.isSelected());
    }//GEN-LAST:event_jF2_1ActionPerformed

    private void jF7_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF7_2ActionPerformed
        CVFktBits(25, 2, jF7_2.isSelected());
    }//GEN-LAST:event_jF7_2ActionPerformed

    private void jF7_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF7_3ActionPerformed
        CVFktBits(25, 4, jF7_3.isSelected());
    }//GEN-LAST:event_jF7_3ActionPerformed

    private void jF7_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF7_4ActionPerformed
        CVFktBits(25, 8, jF7_4.isSelected());
    }//GEN-LAST:event_jF7_4ActionPerformed

    private void jF7_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF7_5ActionPerformed
        CVFktBits(25, 16, jF7_5.isSelected());
    }//GEN-LAST:event_jF7_5ActionPerformed

    private void jF12_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF12_1ActionPerformed
        CVFktBits(45, 1, jF12_1.isSelected());
    }//GEN-LAST:event_jF12_1ActionPerformed

    private void jF13_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF13_6ActionPerformed
        CVFktBits(49, 32, jF13_6.isSelected());
    }//GEN-LAST:event_jF13_6ActionPerformed

    private void jF16_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF16_2ActionPerformed
        CVFktBits(61, 2, jF16_2.isSelected());
    }//GEN-LAST:event_jF16_2ActionPerformed

    private void jF7_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF7_6ActionPerformed
        CVFktBits(25, 32, jF7_6.isSelected());
    }//GEN-LAST:event_jF7_6ActionPerformed

    private void jF8_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF8_1ActionPerformed
        CVFktBits(29, 1, jF8_1.isSelected());
    }//GEN-LAST:event_jF8_1ActionPerformed

    private void jF8_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF8_2ActionPerformed
        CVFktBits(29, 2, jF8_2.isSelected());
    }//GEN-LAST:event_jF8_2ActionPerformed

    private void jF8_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF8_3ActionPerformed
        CVFktBits(29, 4, jF8_3.isSelected());
    }//GEN-LAST:event_jF8_3ActionPerformed

    private void jF8_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF8_4ActionPerformed
        CVFktBits(29, 8, jF8_4.isSelected());
    }//GEN-LAST:event_jF8_4ActionPerformed

    private void jF12_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF12_2ActionPerformed
        CVFktBits(45, 2, jF12_2.isSelected());
    }//GEN-LAST:event_jF12_2ActionPerformed

    private void jF14_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF14_1ActionPerformed
        CVFktBits(53, 1, jF14_1.isSelected());
    }//GEN-LAST:event_jF14_1ActionPerformed

    private void jF16_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF16_3ActionPerformed
        CVFktBits(61, 4, jF16_3.isSelected());
    }//GEN-LAST:event_jF16_3ActionPerformed

    private void jF8_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF8_5ActionPerformed
        CVFktBits(29, 16, jF8_5.isSelected());
    }//GEN-LAST:event_jF8_5ActionPerformed

    private void jF8_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF8_6ActionPerformed
        CVFktBits(29, 32, jF8_6.isSelected());
    }//GEN-LAST:event_jF8_6ActionPerformed

    private void jF9_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF9_1ActionPerformed
        CVFktBits(33, 1, jF9_1.isSelected());
    }//GEN-LAST:event_jF9_1ActionPerformed

    private void jF9_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF9_2ActionPerformed
        CVFktBits(33, 2, jF9_2.isSelected());
    }//GEN-LAST:event_jF9_2ActionPerformed

    private void jF9_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF9_3ActionPerformed
        CVFktBits(33, 4, jF9_3.isSelected());
    }//GEN-LAST:event_jF9_3ActionPerformed

    private void jF12_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF12_3ActionPerformed
        CVFktBits(45, 4, jF12_3.isSelected());
    }//GEN-LAST:event_jF12_3ActionPerformed

    private void jF14_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF14_2ActionPerformed
        CVFktBits(53, 2, jF14_2.isSelected());
    }//GEN-LAST:event_jF14_2ActionPerformed

    private void jF16_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF16_4ActionPerformed
        CVFktBits(61, 8, jF16_4.isSelected());
    }//GEN-LAST:event_jF16_4ActionPerformed

    private void jF9_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF9_4ActionPerformed
        CVFktBits(33, 8, jF9_4.isSelected());
    }//GEN-LAST:event_jF9_4ActionPerformed

    private void jF9_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF9_5ActionPerformed
        CVFktBits(33, 16, jF9_5.isSelected());
    }//GEN-LAST:event_jF9_5ActionPerformed

    private void jF9_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF9_6ActionPerformed
        CVFktBits(33, 32, jF9_6.isSelected());
    }//GEN-LAST:event_jF9_6ActionPerformed

    private void jF10_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF10_1ActionPerformed
        CVFktBits(37, 1, jF10_1.isSelected());
    }//GEN-LAST:event_jF10_1ActionPerformed

    private void jF10_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF10_2ActionPerformed
        CVFktBits(37, 2, jF10_2.isSelected());
    }//GEN-LAST:event_jF10_2ActionPerformed

    private void jF12_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF12_4ActionPerformed
        CVFktBits(45, 8, jF12_4.isSelected());
    }//GEN-LAST:event_jF12_4ActionPerformed

    private void jF14_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF14_3ActionPerformed
        CVFktBits(53, 4, jF14_3.isSelected());
    }//GEN-LAST:event_jF14_3ActionPerformed

    private void jF14_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF14_6ActionPerformed
        CVFktBits(53, 32, jF14_6.isSelected());
    }//GEN-LAST:event_jF14_6ActionPerformed

    private void jF15_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF15_6ActionPerformed
        CVFktBits(57, 32, jF15_6.isSelected());
        jCV_Anzeige.setSelectedItem("CV#" + 180);
    }//GEN-LAST:event_jF15_6ActionPerformed

    private void jF15_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF15_5ActionPerformed
        CVFktBits(57, 16, jF15_5.isSelected());
    }//GEN-LAST:event_jF15_5ActionPerformed

    private void jF16_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF16_5ActionPerformed
        CVFktBits(61, 16, jF16_5.isSelected());
    }//GEN-LAST:event_jF16_5ActionPerformed

    private void jF10_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF10_3ActionPerformed
        CVFktBits(37, 4, jF10_3.isSelected());
    }//GEN-LAST:event_jF10_3ActionPerformed

    private void jF10_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF10_4ActionPerformed
        CVFktBits(37, 8, jF10_4.isSelected());
    }//GEN-LAST:event_jF10_4ActionPerformed

    private void jF10_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF10_5ActionPerformed
        CVFktBits(37, 16, jF10_5.isSelected());
    }//GEN-LAST:event_jF10_5ActionPerformed

    private void jF10_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF10_6ActionPerformed
        CVFktBits(37, 32, jF10_6.isSelected());
    }//GEN-LAST:event_jF10_6ActionPerformed

    private void jF11_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF11_1ActionPerformed
        CVFktBits(41, 1, jF11_1.isSelected());
    }//GEN-LAST:event_jF11_1ActionPerformed

    private void jF12_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF12_5ActionPerformed
        CVFktBits(45, 16, jF12_5.isSelected());
    }//GEN-LAST:event_jF12_5ActionPerformed

    private void jF14_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF14_4ActionPerformed
        CVFktBits(53, 8, jF14_4.isSelected());
    }//GEN-LAST:event_jF14_4ActionPerformed

    private void jF14_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF14_5ActionPerformed
        CVFktBits(53, 16, jF14_5.isSelected());
    }//GEN-LAST:event_jF14_5ActionPerformed

    private void jF15_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF15_1ActionPerformed
        CVFktBits(57, 1, jF15_1.isSelected());
    }//GEN-LAST:event_jF15_1ActionPerformed

    private void jF15_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF15_2ActionPerformed
        CVFktBits(57, 2, jF15_2.isSelected());
    }//GEN-LAST:event_jF15_2ActionPerformed

    private void jF15_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF15_3ActionPerformed
        CVFktBits(57, 4, jF15_3.isSelected());
    }//GEN-LAST:event_jF15_3ActionPerformed

    private void jF15_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF15_4ActionPerformed
        CVFktBits(57, 8, jF15_4.isSelected());
    }//GEN-LAST:event_jF15_4ActionPerformed

    private void jFunctionMappingComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jFunctionMappingComponentShown
        jCV_Anzeige.setSelectedItem("CV#" + 33);
        initFunktionen();

    }//GEN-LAST:event_jFunctionMappingComponentShown

    private void jAuxInv1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAuxInv1ActionPerformed
        if (!jAuxInv1.isSelected()) {
            CV[1][55] |= 2;
        } else {
            CV[1][55] &= ~2;
        }
        jCV_Anzeige.setSelectedItem("CV#" + 55);
    }//GEN-LAST:event_jAuxInv1ActionPerformed

    private void jAuxBlink1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAuxBlink1ActionPerformed
        if (!jAuxBlink1.isSelected()) {
            CV[1][55] |= 1;
        } else {
            CV[1][55] &= ~1;
        }
        jCV_Anzeige.setSelectedItem("CV#" + 55);
    }//GEN-LAST:event_jAuxBlink1ActionPerformed

    private void jAuxBlink2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAuxBlink2ActionPerformed
        if (!jAuxBlink2.isSelected()) {
            CV[1][56] |= 1;
        } else {
            CV[1][56] &= ~1;
        }
        jCV_Anzeige.setSelectedItem("CV#" + 56);
    }//GEN-LAST:event_jAuxBlink2ActionPerformed

    private void jAuxInv2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAuxInv2ActionPerformed
        if (!jAuxInv2.isSelected()) {
            CV[1][56] |= 2;
        } else {
            CV[1][56] &= ~2;
        }
        jCV_Anzeige.setSelectedItem("CV#" + 56);
    }//GEN-LAST:event_jAuxInv2ActionPerformed

    private void jAuxInv3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAuxInv3ActionPerformed
        if (!jAuxInv3.isSelected()) {
            CV[1][57] |= 2;
        } else {
            CV[1][57] &= ~2;
        }
        jCV_Anzeige.setSelectedItem("CV#" + 57);
    }//GEN-LAST:event_jAuxInv3ActionPerformed

    private void jAuxBlink3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAuxBlink3ActionPerformed
        if (!jAuxBlink3.isSelected()) {
            CV[1][57] |= 1;
        } else {
            CV[1][57] &= ~1;
        }
        jCV_Anzeige.setSelectedItem("CV#" + 57);
    }//GEN-LAST:event_jAuxBlink3ActionPerformed

    private void jAuxFeuer6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAuxFeuer6ActionPerformed
        if (jAuxFeuer6.isSelected()) {
            CV[1][60] |= 16;
        } else {
            CV[1][60] &= ~16;
        }
        jCV_Anzeige.setSelectedItem("CV#" + 60);
    }//GEN-LAST:event_jAuxFeuer6ActionPerformed

    private void jAuxDim2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAuxDim2ActionPerformed
        if (jAuxDim2.isSelected()) {
            CV[1][56] |= 32;
        } else {
            CV[1][56] &= ~32;
        }
        jCV_Anzeige.setSelectedItem("CV#" + 56);
    }//GEN-LAST:event_jAuxDim2ActionPerformed

    private void jAuxDim1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAuxDim1ActionPerformed
        if (jAuxDim1.isSelected()) {
            CV[1][55] |= 32;
        } else {
            CV[1][55] &= ~32;
        }
        jCV_Anzeige.setSelectedItem("CV#" + 55);
    }//GEN-LAST:event_jAuxDim1ActionPerformed

    private void jAuxDim3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAuxDim3ActionPerformed
        if (jAuxDim3.isSelected()) {
            CV[1][57] |= 32;
        } else {
            CV[1][57] &= ~32;
        }
        jCV_Anzeige.setSelectedItem("CV#" + 57);
    }//GEN-LAST:event_jAuxDim3ActionPerformed

    private void jAuxInv4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAuxInv4ActionPerformed
        if (!jAuxInv4.isSelected()) {
            CV[1][58] |= 2;
        } else {
            CV[1][58] &= ~2;
        }
        jCV_Anzeige.setSelectedItem("CV#" + 58);
    }//GEN-LAST:event_jAuxInv4ActionPerformed

    private void jAuxInv5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAuxInv5ActionPerformed
        if (!jAuxInv5.isSelected()) {
            CV[1][59] |= 2;
        } else {
            CV[1][59] &= ~2;
        }
        jCV_Anzeige.setSelectedItem("CV#" + 59);
    }//GEN-LAST:event_jAuxInv5ActionPerformed

    private void jAuxInv6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAuxInv6ActionPerformed
        if (!jAuxInv6.isSelected()) {
            CV[1][60] |= 2;
        } else {
            CV[1][60] &= ~2;
        }
        jCV_Anzeige.setSelectedItem("CV#" + 60);
    }//GEN-LAST:event_jAuxInv6ActionPerformed

    private void jAuxDim4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAuxDim4ActionPerformed
        if (jAuxDim4.isSelected()) {
            CV[1][58] |= 32;
        } else {
            CV[1][58] &= ~32;
        }
        jCV_Anzeige.setSelectedItem("CV#" + 58);
    }//GEN-LAST:event_jAuxDim4ActionPerformed

    private void jAuxFeuer1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAuxFeuer1ActionPerformed
        if (jAuxFeuer1.isSelected()) {
            CV[1][57] |= 16;
        } else {
            CV[1][57] &= ~16;
        }
        jCV_Anzeige.setSelectedItem("CV#" + 57);
    }//GEN-LAST:event_jAuxFeuer1ActionPerformed

    private void jAuxFeuer2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAuxFeuer2ActionPerformed
        if (jAuxFeuer2.isSelected()) {
            CV[1][56] |= 16;
        } else {
            CV[1][56] &= ~16;
        }
        jCV_Anzeige.setSelectedItem("CV#" + 56);
    }//GEN-LAST:event_jAuxFeuer2ActionPerformed

    private void jAuxFeuer3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAuxFeuer3ActionPerformed
        if (jAuxFeuer3.isSelected()) {
            CV[1][57] |= 16;
        } else {
            CV[1][57] &= ~16;
        }
        jCV_Anzeige.setSelectedItem("CV#" + 58);
    }//GEN-LAST:event_jAuxFeuer3ActionPerformed

    private void jAuxBlink4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAuxBlink4ActionPerformed
        if (!jAuxBlink4.isSelected()) {
            CV[1][58] |= 1;
        } else {
            CV[1][58] &= ~1;
        }
        jCV_Anzeige.setSelectedItem("CV#" + 58);
    }//GEN-LAST:event_jAuxBlink4ActionPerformed

    private void jAuxBlink5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAuxBlink5ActionPerformed
        if (!jAuxBlink5.isSelected()) {
            CV[1][59] |= 1;
        } else {
            CV[1][59] &= ~1;
        }
        jCV_Anzeige.setSelectedItem("CV#" + 59);
    }//GEN-LAST:event_jAuxBlink5ActionPerformed

    private void jAuxBlink6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAuxBlink6ActionPerformed
        if (!jAuxBlink6.isSelected()) {
            CV[1][60] |= 1;
        } else {
            CV[1][60] &= ~1;
        }
        jCV_Anzeige.setSelectedItem("CV#" + 60);
    }//GEN-LAST:event_jAuxBlink6ActionPerformed

    private void jAuxFeuer4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAuxFeuer4ActionPerformed
        if (jAuxFeuer4.isSelected()) {
            CV[1][58] |= 16;
        } else {
            CV[1][58] &= ~16;
        }
        jCV_Anzeige.setSelectedItem("CV#" + 58);
    }//GEN-LAST:event_jAuxFeuer4ActionPerformed

    private void jAuxFeuer5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAuxFeuer5ActionPerformed
        if (jAuxFeuer5.isSelected()) {
            CV[1][59] |= 16;
        } else {
            CV[1][59] &= ~16;
        }
        jCV_Anzeige.setSelectedItem("CV#" + 59);
    }//GEN-LAST:event_jAuxFeuer5ActionPerformed

    private void jAuxSpSch1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAuxSpSch1ActionPerformed
        if (jAuxSpSch1.isSelected()) {
            CV[1][55] |= 64;
        } else {
            CV[1][55] &= ~64;
        }
        jCV_Anzeige.setSelectedItem("CV#" + 55);
    }//GEN-LAST:event_jAuxSpSch1ActionPerformed

    private void jAuxSpSch2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAuxSpSch2ActionPerformed
        if (jAuxSpSch2.isSelected()) {
            CV[1][56] |= 64;
        } else {
            CV[1][56] &= ~64;
        }
        jCV_Anzeige.setSelectedItem("CV#" + 56);
    }//GEN-LAST:event_jAuxSpSch2ActionPerformed

    private void jAuxSpSch3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAuxSpSch3ActionPerformed
        if (jAuxSpSch3.isSelected()) {
            CV[1][57] |= 64;
        } else {
            CV[1][57] &= ~64;
        }
        jCV_Anzeige.setSelectedItem("CV#" + 57);
    }//GEN-LAST:event_jAuxSpSch3ActionPerformed

    private void jAuxSpSch4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAuxSpSch4ActionPerformed
        if (jAuxSpSch4.isSelected()) {
            CV[1][58] |= 64;
        } else {
            CV[1][58] &= ~64;
        }
        jCV_Anzeige.setSelectedItem("CV#" + 58);
    }//GEN-LAST:event_jAuxSpSch4ActionPerformed

    private void jAuxSpSch5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAuxSpSch5ActionPerformed
        if (jAuxSpSch5.isSelected()) {
            CV[1][59] |= 64;
        } else {
            CV[1][59] &= ~64;
        }
        jCV_Anzeige.setSelectedItem("CV#" + 59);
    }//GEN-LAST:event_jAuxSpSch5ActionPerformed

    private void jAuxSpSch6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAuxSpSch6ActionPerformed
        if (jAuxSpSch6.isSelected()) {
            CV[1][60] |= 64;
        } else {
            CV[1][60] &= ~64;
        }
        jCV_Anzeige.setSelectedItem("CV#" + 60);
    }//GEN-LAST:event_jAuxSpSch6ActionPerformed

    private void jAuxKick6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAuxKick6ActionPerformed
        if (jAuxKick6.isSelected()) {
            CV[1][60] |= 4;
        } else {
            CV[1][60] &= ~4;
        }
        jCV_Anzeige.setSelectedItem("CV#" + 60);
    }//GEN-LAST:event_jAuxKick6ActionPerformed

    private void jAuxKick2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAuxKick2ActionPerformed
        if (jAuxKick2.isSelected()) {
            CV[1][56] |= 4;
        } else {
            CV[1][56] &= ~4;
        }
        jCV_Anzeige.setSelectedItem("CV#" + 56);
    }//GEN-LAST:event_jAuxKick2ActionPerformed

    private void jAuxKick1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAuxKick1ActionPerformed
        if (jAuxKick1.isSelected()) {
            CV[1][55] |= 4;
        } else {
            CV[1][55] &= ~4;
        }
        jCV_Anzeige.setSelectedItem("CV#" + 55);
    }//GEN-LAST:event_jAuxKick1ActionPerformed

    private void jAuxKick3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAuxKick3ActionPerformed
        if (jAuxKick3.isSelected()) {
            CV[1][57] |= 4;
        } else {
            CV[1][57] &= ~4;
        }
        jCV_Anzeige.setSelectedItem("CV#" + 57);
    }//GEN-LAST:event_jAuxKick3ActionPerformed

    private void jAuxKick4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAuxKick4ActionPerformed
        if (jAuxKick4.isSelected()) {
            CV[1][58] |= 4;
        } else {
            CV[1][58] &= ~4;
        }
        jCV_Anzeige.setSelectedItem("CV#" + 58);
    }//GEN-LAST:event_jAuxKick4ActionPerformed

    private void jAuxKick5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAuxKick5ActionPerformed
        if (jAuxKick5.isSelected()) {
            CV[1][59] |= 4;
        } else {
            CV[1][59] &= ~4;
        }
        jCV_Anzeige.setSelectedItem("CV#" + 59);
    }//GEN-LAST:event_jAuxKick5ActionPerformed

    private void jBlinkfrequenz1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlinkfrequenz1FocusLost
        int min = (CV[1][134] * 2) + CV[1][142]; // Abhängigkeiten
        if (min < 10) {
            min = 10; // Handbuch
        }
        if (!CVNavi.checkNumRange(jBlinkfrequenz1.getText(), min, 255)) {
            CVNavi.mbBlinkFrequenz(this, CV[1][134], CV[1][142], jBlinkfrequenz1.getText());
            jBlinkfrequenz1.grabFocus();
            return;
        }
        // Wert ist bereits geprüft. also "still" übernehmen (view -> false)
        CV[1][106] = CVNavi.checkTextField(this, jBlinkfrequenz1, min, 255, 20, false);
        jCV_Anzeige.setSelectedItem("CV#" + 106);
    }//GEN-LAST:event_jBlinkfrequenz1FocusLost

    private void jBlinkfrequenz1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBlinkfrequenz1KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus();
        }
    }//GEN-LAST:event_jBlinkfrequenz1KeyReleased

    private void jBlinkfrequenz2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlinkfrequenz2FocusLost
        int min = (CV[1][135] * 2) + CV[1][143]; // Abhängigkeiten
        if (min < 10) {
            min = 10; // Handbuch
        }
        if (!CVNavi.checkNumRange(jBlinkfrequenz2.getText(), min, 255)) {
            CVNavi.mbBlinkFrequenz(this, CV[1][135], CV[1][143], jBlinkfrequenz2.getText());
            jBlinkfrequenz2.grabFocus();
            return;
        }
        // Wert ist bereits geprüft. also "still" übernehmen (view -> false)
        CV[1][107] = CVNavi.checkTextField(this, jBlinkfrequenz2, min, 255, 20, false);
        jCV_Anzeige.setSelectedItem("CV#" + 107);
    }//GEN-LAST:event_jBlinkfrequenz2FocusLost

    private void jBlinkfrequenz2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBlinkfrequenz2KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus();
        }
    }//GEN-LAST:event_jBlinkfrequenz2KeyReleased

    private void jEffekte_1ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jEffekte_1ComponentShown
        //---------------------- Effekte 1 --------------
        switch (CVNavi.Decoder) {
            case c.LD_G42:
            case c.LD_W42:
            case c.LD_G42_2:
            case c.LD_W42_2:
                jLabel44.setVisible(false);
                jLabel70.setVisible(false);
                jLabel71.setVisible(false);
                jLabel69.setVisible(false);

                jAuxInv3.setVisible(false);
                jAuxInv4.setVisible(false);
                jAuxInv5.setVisible(false);
                jAuxInv6.setVisible(false);

                jAuxBlink3.setVisible(false);
                jAuxBlink4.setVisible(false);
                jAuxBlink5.setVisible(false);
                jAuxBlink6.setVisible(false);

                jAuxKick3.setVisible(false);
                jAuxKick4.setVisible(false);
                jAuxKick5.setVisible(false);
                jAuxKick6.setVisible(false);

                jAuxDim3.setVisible(false);
                jAuxDim4.setVisible(false);
                jAuxDim5.setVisible(false);
                jAuxDim6.setVisible(false);

                jAuxFeuer3.setVisible(false);
                jAuxFeuer4.setVisible(false);
                jAuxFeuer5.setVisible(false);
                jAuxFeuer6.setVisible(false);

                jAuxSpSch3.setVisible(false);
                jAuxSpSch4.setVisible(false);
                jAuxSpSch5.setVisible(false);
                jAuxSpSch6.setVisible(false);

                break;

        }
        if (CVNavi.Decoder == c.LD_G42 || CVNavi.Decoder == c.LD_W42) {
            jLabel180.setText(bundle.getString("LDG4x.jLabel180a.text"));
            jLabel181.setText(bundle.getString("LDG4x.jLabel181a.text"));
            jLabel66.setText(bundle.getString("LDG4x.jLabel66a.text"));
            jLabel97.setText(bundle.getString("LDG4x.jLabel97a.text"));
        } else {
            jLabel180.setText(bundle.getString("LDG4x.jLabel180b.text"));
            jLabel181.setText(bundle.getString("LDG4x.jLabel181b.text"));
            jLabel66.setText(bundle.getString("LDG4x.jLabel66b.text"));
            jLabel97.setText(bundle.getString("LDG4x.jLabel97b.text"));
        }

        jCV_Anzeige.setSelectedItem("CV#" + 53);

        //AUX...
        int cvValue;
        cvValue = CV[1][53];
        jAuxBlinkf.setSelected(!((cvValue & 1) == 1));
        jAuxInvf.setSelected(!((cvValue & 2) == 2));
        jAuxKickf.setSelected((cvValue & 4) == 4);
        jAuxFeuerf.setSelected((cvValue & 16) == 16);
        jAuxDimf.setSelected((cvValue & 32) == 32);
        jAuxSpSchf.setSelected((cvValue & 64) == 64);

        cvValue = CV[1][54];
        jAuxBlinkr.setSelected(!((cvValue & 1) == 1));
        jAuxInvr.setSelected(!((cvValue & 2) == 2));
        jAuxKickr.setSelected((cvValue & 4) == 4);
        jAuxFeuerr.setSelected((cvValue & 16) == 16);
        jAuxDimr.setSelected((cvValue & 32) == 32);
        jAuxSpSchr.setSelected((cvValue & 64) == 64);

        cvValue = CV[1][55];
        jAuxBlink1.setSelected(!((cvValue & 1) == 1));
        jAuxInv1.setSelected(!((cvValue & 2) == 2));
        jAuxKick1.setSelected((cvValue & 4) == 4);
        jAuxFeuer1.setSelected((cvValue & 16) == 16);
        jAuxDim1.setSelected((cvValue & 32) == 32);
        jAuxSpSch1.setSelected((cvValue & 64) == 64);

        cvValue = CV[1][56];
        jAuxBlink2.setSelected(!((cvValue & 1) == 1));
        jAuxInv2.setSelected(!((cvValue & 2) == 2));
        jAuxKick2.setSelected((cvValue & 4) == 4);
        jAuxFeuer2.setSelected((cvValue & 16) == 16);
        jAuxDim2.setSelected((cvValue & 32) == 32);
        jAuxSpSch2.setSelected((cvValue & 64) == 64);

        cvValue = CV[1][57];
        jAuxBlink3.setSelected(!((cvValue & 1) == 1));
        jAuxInv3.setSelected(!((cvValue & 2) == 2));
        jAuxKick3.setSelected((cvValue & 4) == 4);
        jAuxFeuer3.setSelected((cvValue & 16) == 16);
        jAuxDim3.setSelected((cvValue & 32) == 32);
        jAuxSpSch3.setSelected((cvValue & 64) == 64);

        cvValue = CV[1][58];
        jAuxBlink4.setSelected(!((cvValue & 1) == 1));
        jAuxInv4.setSelected(!((cvValue & 2) == 2));
        jAuxKick4.setSelected((cvValue & 4) == 4);
        jAuxFeuer4.setSelected((cvValue & 16) == 16);
        jAuxDim4.setSelected((cvValue & 32) == 32);
        jAuxSpSch4.setSelected((cvValue & 64) == 64);

        cvValue = CV[1][59];
        jAuxBlink5.setSelected(!((cvValue & 1) == 1));
        jAuxInv5.setSelected(!((cvValue & 2) == 2));
        jAuxKick5.setSelected((cvValue & 4) == 4);
        jAuxFeuer5.setSelected((cvValue & 16) == 16);
        jAuxSpSch5.setSelected((cvValue & 64) == 64);

        cvValue = CV[1][60];
        jAuxBlink6.setSelected(!((cvValue & 1) == 1));
        jAuxInv6.setSelected(!((cvValue & 2) == 2));
        jAuxKick6.setSelected((cvValue & 4) == 4);
        jAuxFeuer6.setSelected((cvValue & 16) == 16);
        jAuxSpSch6.setSelected((cvValue & 64) == 64);

        //----------------
        jKickZeit.setText("" + CV[1][99]);
        jBlinkfrequenz1.setText("" + CV[1][106]);
        jBlinkfrequenz2.setText("" + CV[1][107]);
        //----------------


    }//GEN-LAST:event_jEffekte_1ComponentShown

    private void jEffekte_3ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jEffekte_3ComponentShown
        jCV_Anzeige.setSelectedItem("CV#" + 22);
        jConsF0.setSelected((CV[1][22] & 1) == 1);
        jConsF1.setSelected((CV[1][21] & 1) == 1);
        jConsF2.setSelected((CV[1][21] & 2) == 1);
        jConsF3.setSelected((CV[1][21] & 4) == 1);
        jConsF4.setSelected((CV[1][21] & 8) == 1);
        jConsF5.setSelected((CV[1][21] & 16) == 1);
        jConsF6.setSelected((CV[1][22] & 32) == 1);
        jConsF7.setSelected((CV[1][21] & 64) == 1);
        jConsF8.setSelected((CV[1][21] & 128) == 1);
        jConsF9.setSelected((CV[1][22] & 2) == 1);
        jConsF10.setSelected((CV[1][22] & 4) == 1);
        jConsF11.setSelected((CV[1][22] & 8) == 1);
        jConsF12.setSelected((CV[1][22] & 16) == 1);
        jConsAdr.setText("" + CV[1][19]);
        
        jLastregEin.setSelected((CV[1][116] & 1) == 1);
        if(CVNavi.Decoder != c.LD_W42 && CVNavi.Decoder != c.LD_W42_2){
            jLastregEin.setSelected((CV[1][116] & 1) == 1);
            jVerstaerk.setText("" + CV[1][112]);
            jKP.setText("" + CV[1][113]);
            jKI.setText("" + CV[1][114]);
            jKD.setText("" + CV[1][115]);
        }
        else
        {
            jLastregEin.setVisible(false);
            jKP.setVisible(false);
            jKI.setVisible(false);
            jKD.setVisible(false);
            jLabel14.setVisible(false);
            jLabel16.setVisible(false);
            jLabel18.setVisible(false);
            jLabel19.setVisible(false);
        }
 
    }//GEN-LAST:event_jEffekte_3ComponentShown

    private void jF1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF1ActionPerformed
        if (jF1.isSelected()) {
            CV[1][13] |= 1;
        } else {
            CV[1][13] &= ~1;
        }
        jCV_Anzeige.setSelectedItem("CV#" + 13);
    }//GEN-LAST:event_jF1ActionPerformed

    private void jF2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF2ActionPerformed
        if (jF2.isSelected()) {
            CV[1][13] |= 2;
        } else {
            CV[1][13] &= ~2;
        }
        jCV_Anzeige.setSelectedItem("CV#" + 13);
    }//GEN-LAST:event_jF2ActionPerformed

    private void jF3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF3ActionPerformed
        if (jF3.isSelected()) {
            CV[1][13] |= 4;
        } else {
            CV[1][13] &= ~4;
        }
        jCV_Anzeige.setSelectedItem("CV#" + 13);
    }//GEN-LAST:event_jF3ActionPerformed

    private void jF4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF4ActionPerformed
        if (jF4.isSelected()) {
            CV[1][13] |= 8;
        } else {
            CV[1][13] &= ~8;
        }
        jCV_Anzeige.setSelectedItem("CV#" + 13);
    }//GEN-LAST:event_jF4ActionPerformed

    private void jF5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF5ActionPerformed
        if (jF5.isSelected()) {
            CV[1][13] |= 0x10;
        } else {
            CV[1][13] &= ~0x10;
        }
        jCV_Anzeige.setSelectedItem("CV#" + 13);
    }//GEN-LAST:event_jF5ActionPerformed

    private void jF6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF6ActionPerformed
        if (jF6.isSelected()) {
            CV[1][13] |= 0x20;
        } else {
            CV[1][13] &= ~0x20;
        }
        jCV_Anzeige.setSelectedItem("CV#" + 13);
    }//GEN-LAST:event_jF6ActionPerformed

    private void jF7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF7ActionPerformed
        if (jF7.isSelected()) {
            CV[1][13] |= 0x40;
        } else {
            CV[1][13] &= ~0x40;
        }
        jCV_Anzeige.setSelectedItem("CV#" + 13);
    }//GEN-LAST:event_jF7ActionPerformed

    private void jF8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF8ActionPerformed
        if (jF8.isSelected()) {
            CV[1][13] |= 0x80;
        } else {
            CV[1][13] &= ~0x80;
        }
        jCV_Anzeige.setSelectedItem("CV#" + 13);
    }//GEN-LAST:event_jF8ActionPerformed

    private void jPacketTimeOutFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPacketTimeOutFocusLost
        CV[1][11] = CVNavi.checkTextField(this, jPacketTimeOut, 0, 255, 5, true);
        jCV_Anzeige.setSelectedItem("CV#" + 11);
    }//GEN-LAST:event_jPacketTimeOutFocusLost

    private void jPacketTimeOutKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPacketTimeOutKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus();
        }
    }//GEN-LAST:event_jPacketTimeOutKeyReleased

    private void jAnalogComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jAnalogComponentShown
        jCV_Anzeige.setSelectedItem("CV#" + 13);

        int cvValue = CV[1][13];
        jF1.setSelected((cvValue & 1) == 1);
        jF2.setSelected((cvValue & 2) == 2);
        jF3.setSelected((cvValue & 4) == 4);
        jF4.setSelected((cvValue & 8) == 8);
        jF5.setSelected((cvValue & 16) == 16);
        jF6.setSelected((cvValue & 32) == 32);
        jF7.setSelected((cvValue & 64) == 64);
        jF8.setSelected((cvValue & 128) == 128);
        cvValue = CV[1][14];
        jF0.setSelected((cvValue & 1) == 1);
        jF9.setSelected((cvValue & 2) == 2);
        jF10.setSelected((cvValue & 4) == 4);
        jF11.setSelected((cvValue & 8) == 8);
        jF12.setSelected((cvValue & 16) == 16);

        jPacketTimeOut.setText("" + CV[1][11]);
        if ((CV[1][12] & 1) == 1) {
            jAnalogRW_G.setSelected(true);
            jAnalogRW_W.setSelected(false);
        } else {
            jAnalogRW_W.setSelected(true);
            jAnalogRW_G.setSelected(false);
        }
        if (CVNavi.Decoder == c.LD_G43 || CVNavi.Decoder == c.LD_G44) {
            jUmschaltEmpf.setVisible(true);
            jLabel238.setVisible(true);
            jUmschaltEmpf.setText("" + CV[1][64]);
        } else {
            jUmschaltEmpf.setVisible(false);
            jLabel238.setVisible(false);

        }
    }//GEN-LAST:event_jAnalogComponentShown

    private void jKommentarComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jKommentarComponentShown

    }//GEN-LAST:event_jKommentarComponentShown

    private void jlangeAdrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jlangeAdrActionPerformed
        int j = (CV[1][17] - 192) * 256 + CV[1][18];
        if (j < 128 || j > 10239) {
            CVNavi.mbValueNaN(this, 128, 10239, true);
            j = 128;
        }
        CV[1][29] |= 32;
        CV[1][17] = j / 256 + 192;
        CV[1][18] = j % 256;
        jLongAddr.setSelected(true);
        CV[1][29] |= 32;
        jCV_Anzeige.setSelectedItem("CV#" + 17);
        jDecoderAdresse.setText("" + j);
    }//GEN-LAST:event_jlangeAdrActionPerformed

    private void jDirekteingabeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDirekteingabeActionPerformed
        // hier wird die Direkteingabe (de)aktiviert
        String str = jDirekteingabe.getText();
        if (str.equals(bundle.getString("FD_LED.jDirekteingabe.text"))) {
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
        switch (CVNavi.Decoder) {

            case c.LD_G41:
                CVs = "LD-G-41\r\n";
                CVs += "Version 1.0\r\n";
                for (int i = 0; i < CV[0].length; i++) {
                    if (CV[0][i] > 0) { // only write used CVs (CV[0][cv] != 0 ) to file
                        CVs += "\r\nCV#" + CV[0][i] + " = " + CV[1][i];
                    }
                }
                CVs += "\r\n\r\nKommentar:\r\n";
                CVs += jComment.getText();
                od = new SaveOpenDialog(this, true, false, CVs, this, "ld4x");
                break;

            case c.LD_G42:
                CVs = "LD-G-42\r\n";
                CVs += "Version 1.0\r\n";
                for (int i = 0; i < CV[0].length; i++) {
                    if (CV[0][i] > 0) { // only write used CVs (CV[0][cv] != 0 ) to file
                        CVs += "\r\nCV#" + CV[0][i] + " = " + CV[1][i];
                    }
                }
                CVs += "\r\n\r\nKommentar:\r\n";
                CVs += jComment.getText();
                od = new SaveOpenDialog(this, true, false, CVs, this, "ld4x");
                break;

            case c.LD_W42:
                CVs = "LD-W-42\r\n";
                CVs += "Version 1.0\r\n";
                for (int i = 0; i < CV[0].length; i++) {
                    if (CV[0][i] > 0) { // only write used CVs (CV[0][cv] != 0 ) to file
                        CVs += "\r\nCV#" + CV[0][i] + " = " + CV[1][i];
                    }
                }
                CVs += "\r\n\r\nKommentar:\r\n";
                CVs += jComment.getText();
                od = new SaveOpenDialog(this, true, false, CVs, this, "ld4x");
                break;

            case c.LD_G42_2:
                CVs = "LD-G-42.2\r\n";
                CVs += "Version 1.0\r\n";
                for (int i = 0; i < CV[0].length; i++) {
                    if (CV[0][i] > 0) { // only write used CVs (CV[0][cv] != 0 ) to file
                        CVs += "\r\nCV#" + CV[0][i] + " = " + CV[1][i];
                    }
                }
                CVs += "\r\n\r\nKommentar:\r\n";
                CVs += jComment.getText();
                od = new SaveOpenDialog(this, true, false, CVs, this, "ld4x");
                break;

            case c.LD_W42_2:
                CVs = "LD-W-42.2\r\n";
                CVs += "Version 1.0\r\n";
                for (int i = 0; i < CV[0].length; i++) {
                    if (CV[0][i] > 0) { // only write used CVs (CV[0][cv] != 0 ) to file
                        CVs += "\r\nCV#" + CV[0][i] + " = " + CV[1][i];
                    }
                }
                CVs += "\r\n\r\nKommentar:\r\n";
                CVs += jComment.getText();
                od = new SaveOpenDialog(this, true, false, CVs, this, "ld4x");
                break;

            case c.LD_G43:
                CVs = "LD-G-43\r\n";
                CVs += "Version 1.0\r\n";
                for (int i = 0; i < CV[0].length; i++) {
                    if (CV[0][i] > 0) { // only write used CVs (CV[0][cv] != 0 ) to file
                        CVs += "\r\nCV#" + CV[0][i] + " = " + CV[1][i];
                    }
                }
                CVs += "\r\n\r\nKommentar:\r\n";
                CVs += jComment.getText();
                od = new SaveOpenDialog(this, true, false, CVs, this, "ld4x");
                break;

            case c.LD_G44:
                CVs = "LD-G-44\r\n";
                CVs += "Version 1.0\r\n";
                for (int i = 0; i < CV[0].length; i++) {
                    if (CV[0][i] > 0) { // only write used CVs (CV[0][cv] != 0 ) to file
                        CVs += "\r\nCV#" + CV[0][i] + " = " + CV[1][i];
                    }
                }
                CVs += "\r\n\r\nKommentar:\r\n";
                CVs += jComment.getText();
                od = new SaveOpenDialog(this, true, false, CVs, this, "ld4x");
                break;
        }
    }//GEN-LAST:event_jSaveActionPerformed

    private void jDecoderAdresse1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDecoderAdresse1FocusLost
        CV[1][19] = CVNavi.checkTextField(this, jDecoderAdresse1, 0, 127, 0, true);
        jCV_Anzeige.setSelectedItem("CV#" + 19);
    }//GEN-LAST:event_jDecoderAdresse1FocusLost

    private void jDecoderAdresse1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDecoderAdresse1KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus();
        }
    }//GEN-LAST:event_jDecoderAdresse1KeyReleased

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        CVNavi.frameInstanceDEVICE = null;
        CVNavi.setFocus();
    }//GEN-LAST:event_formWindowClosed

    private void jF1_7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF1_7ActionPerformed
        CVFktBits(1, 64, jF1_7.isSelected());

    }//GEN-LAST:event_jF1_7ActionPerformed

    private void jF1_8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF1_8ActionPerformed
        CVFktBits(1, 128, jF1_8.isSelected());

    }//GEN-LAST:event_jF1_8ActionPerformed

    private void jF2_7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF2_7ActionPerformed
        CVFktBits(5, 64, jF2_7.isSelected());
    }//GEN-LAST:event_jF2_7ActionPerformed

    private void jF2_8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF2_8ActionPerformed
        CVFktBits(5, 128, jF2_8.isSelected());
    }//GEN-LAST:event_jF2_8ActionPerformed

    private void jF3_7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF3_7ActionPerformed
        CVFktBits(9, 64, jF3_7.isSelected());
    }//GEN-LAST:event_jF3_7ActionPerformed

    private void jF3_8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF3_8ActionPerformed
        CVFktBits(9, 128, jF3_8.isSelected());
    }//GEN-LAST:event_jF3_8ActionPerformed

    private void jF4_7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF4_7ActionPerformed
        CVFktBits(13, 64, jF4_7.isSelected());
    }//GEN-LAST:event_jF4_7ActionPerformed

    private void jF4_8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF4_8ActionPerformed
        CVFktBits(13, 128, jF4_8.isSelected());
    }//GEN-LAST:event_jF4_8ActionPerformed

    private void jF5_7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF5_7ActionPerformed
        CVFktBits(17, 64, jF5_7.isSelected());
    }//GEN-LAST:event_jF5_7ActionPerformed

    private void jF5_8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF5_8ActionPerformed
        CVFktBits(17, 128, jF5_8.isSelected());
    }//GEN-LAST:event_jF5_8ActionPerformed

    private void jF6_7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF6_7ActionPerformed
        CVFktBits(21, 64, jF6_7.isSelected());
    }//GEN-LAST:event_jF6_7ActionPerformed

    private void jF6_8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF6_8ActionPerformed
        CVFktBits(21, 128, jF6_8.isSelected());
    }//GEN-LAST:event_jF6_8ActionPerformed

    private void jF7_7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF7_7ActionPerformed
        CVFktBits(25, 64, jF7_7.isSelected());
    }//GEN-LAST:event_jF7_7ActionPerformed

    private void jF7_8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF7_8ActionPerformed
        CVFktBits(25, 128, jF7_8.isSelected());
    }//GEN-LAST:event_jF7_8ActionPerformed

    private void jF8_7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF8_7ActionPerformed
        CVFktBits(29, 64, jF8_7.isSelected());
    }//GEN-LAST:event_jF8_7ActionPerformed

    private void jF8_8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF8_8ActionPerformed
        CVFktBits(29, 128, jF8_8.isSelected());
    }//GEN-LAST:event_jF8_8ActionPerformed

    private void jF9_7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF9_7ActionPerformed
        CVFktBits(33, 64, jF9_7.isSelected());
    }//GEN-LAST:event_jF9_7ActionPerformed

    private void jF9_8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF9_8ActionPerformed
        CVFktBits(33, 128, jF9_8.isSelected());
    }//GEN-LAST:event_jF9_8ActionPerformed

    private void jF10_7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF10_7ActionPerformed
        CVFktBits(37, 64, jF10_7.isSelected());
    }//GEN-LAST:event_jF10_7ActionPerformed

    private void jF10_8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF10_8ActionPerformed
        CVFktBits(37, 128, jF10_8.isSelected());
    }//GEN-LAST:event_jF10_8ActionPerformed

    private void jF11_7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF11_7ActionPerformed
        CVFktBits(41, 64, jF11_7.isSelected());
    }//GEN-LAST:event_jF11_7ActionPerformed

    private void jF11_8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF11_8ActionPerformed
        CVFktBits(41, 128, jF11_8.isSelected());
    }//GEN-LAST:event_jF11_8ActionPerformed

    private void jF12_7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF12_7ActionPerformed
        CVFktBits(45, 64, jF12_7.isSelected());
    }//GEN-LAST:event_jF12_7ActionPerformed

    private void jF12_8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF12_8ActionPerformed
        CVFktBits(45, 128, jF12_8.isSelected());
    }//GEN-LAST:event_jF12_8ActionPerformed

    private void jF13_7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF13_7ActionPerformed
        CVFktBits(49, 64, jF13_7.isSelected());
    }//GEN-LAST:event_jF13_7ActionPerformed

    private void jF13_8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF13_8ActionPerformed
        CVFktBits(49, 128, jF13_8.isSelected());
    }//GEN-LAST:event_jF13_8ActionPerformed

    private void jF14_7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF14_7ActionPerformed
        CVFktBits(53, 64, jF14_7.isSelected());
    }//GEN-LAST:event_jF14_7ActionPerformed

    private void jF14_8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF14_8ActionPerformed
        CVFktBits(53, 128, jF14_8.isSelected());
    }//GEN-LAST:event_jF14_8ActionPerformed

    private void jF16_7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF16_7ActionPerformed
        CVFktBits(61, 64, jF16_7.isSelected());
    }//GEN-LAST:event_jF16_7ActionPerformed

    private void jBlinkfrequenzfFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlinkfrequenzfFocusLost
        int min = (CV[1][132] * 2) + CV[1][140]; // Abhängigkeiten
        if (min < 10) {
            min = 10; // Handbuch
        }
        if (!CVNavi.checkNumRange(jBlinkfrequenzf.getText(), min, 255)) {
            CVNavi.mbBlinkFrequenz(this, CV[1][132], CV[1][140], jBlinkfrequenzf.getText());
            jBlinkfrequenzf.grabFocus();
            return;
        }
        // Wert ist bereits geprüft. also "still" übernehmen (view -> false)
        CV[1][104] = CVNavi.checkTextField(this, jBlinkfrequenzf, min, 255, 20, false);
        jCV_Anzeige.setSelectedItem("CV#" + 104);
    }//GEN-LAST:event_jBlinkfrequenzfFocusLost

    private void jBlinkfrequenzfKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBlinkfrequenzfKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus();
        }
    }//GEN-LAST:event_jBlinkfrequenzfKeyReleased

    private void jBlinkfrequenzrFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlinkfrequenzrFocusLost
        int min = (CV[1][133] * 2) + CV[1][141]; // Abhängigkeiten
        if (min < 10) {
            min = 10; // Handbuch
        }
        if (!CVNavi.checkNumRange(jBlinkfrequenzr.getText(), min, 255)) {
            CVNavi.mbBlinkFrequenz(this, CV[1][133], CV[1][141], jBlinkfrequenzr.getText());
            jBlinkfrequenzr.grabFocus();
            return;
        }
        // Wert ist bereits geprüft. also "still" übernehmen (view -> false)
        CV[1][105] = CVNavi.checkTextField(this, jBlinkfrequenzr, min, 255, 20, false);
        jCV_Anzeige.setSelectedItem("CV#" + 105);
    }//GEN-LAST:event_jBlinkfrequenzrFocusLost

    private void jBlinkfrequenzrKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBlinkfrequenzrKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus();
        }
    }//GEN-LAST:event_jBlinkfrequenzrKeyReleased

    private void jAuxInvfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAuxInvfActionPerformed
        if (!jAuxInv1.isSelected()) {
            CV[1][53] |= 2;
        } else {
            CV[1][53] &= ~2;
        }
        jCV_Anzeige.setSelectedItem("CV#" + 53);
    }//GEN-LAST:event_jAuxInvfActionPerformed

    private void jAuxBlinkfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAuxBlinkfActionPerformed
        if (!jAuxBlink1.isSelected()) {
            CV[1][55] |= 1;
        } else {
            CV[1][55] &= ~1;
        }
        jCV_Anzeige.setSelectedItem("CV#" + 55);
    }//GEN-LAST:event_jAuxBlinkfActionPerformed

    private void jAuxBlinkrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAuxBlinkrActionPerformed
        if (!jAuxBlink2.isSelected()) {
            CV[1][54] |= 1;
        } else {
            CV[1][54] &= ~1;
        }
        jCV_Anzeige.setSelectedItem("CV#" + 54);
    }//GEN-LAST:event_jAuxBlinkrActionPerformed

    private void jAuxInvrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAuxInvrActionPerformed
        if (!jAuxInv2.isSelected()) {
            CV[1][54] |= 2;
        } else {
            CV[1][54] &= ~2;
        }
        jCV_Anzeige.setSelectedItem("CV#" + 54);
    }//GEN-LAST:event_jAuxInvrActionPerformed

    private void jAuxKickrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAuxKickrActionPerformed
        if (jAuxKick2.isSelected()) {
            CV[1][54] |= 4;
        } else {
            CV[1][54] &= ~4;
        }
        jCV_Anzeige.setSelectedItem("CV#" + 54);
    }//GEN-LAST:event_jAuxKickrActionPerformed

    private void jAuxKickfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAuxKickfActionPerformed
        if (jAuxKick1.isSelected()) {
            CV[1][53] |= 4;
        } else {
            CV[1][53] &= ~4;
        }
        jCV_Anzeige.setSelectedItem("CV#" + 53);
    }//GEN-LAST:event_jAuxKickfActionPerformed

    private void jAuxDim5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAuxDim5ActionPerformed
        if (jAuxDim5.isSelected()) {
            CV[1][59] |= 32;
        } else {
            CV[1][59] &= ~32;
        }
        jCV_Anzeige.setSelectedItem("CV#" + 59);
    }//GEN-LAST:event_jAuxDim5ActionPerformed

    private void jAuxDim6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAuxDim6ActionPerformed
        if (jAuxDim6.isSelected()) {
            CV[1][60] |= 32;
        } else {
            CV[1][60] &= ~32;
        }
        jCV_Anzeige.setSelectedItem("CV#" + 60);
    }//GEN-LAST:event_jAuxDim6ActionPerformed

    private void jAuxDimfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAuxDimfActionPerformed
        if (jAuxDim1.isSelected()) {
            CV[1][53] |= 32;
        } else {
            CV[1][53] &= ~32;
        }
        jCV_Anzeige.setSelectedItem("CV#" + 53);
    }//GEN-LAST:event_jAuxDimfActionPerformed

    private void jAuxDimrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAuxDimrActionPerformed
        if (jAuxDim2.isSelected()) {
            CV[1][54] |= 32;
        } else {
            CV[1][54] &= ~32;
        }
        jCV_Anzeige.setSelectedItem("CV#" + 54);
    }//GEN-LAST:event_jAuxDimrActionPerformed

    private void jAuxFeuerfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAuxFeuerfActionPerformed
        if (jAuxFeuer1.isSelected()) {
            CV[1][53] |= 16;
        } else {
            CV[1][53] &= ~16;
        }
        jCV_Anzeige.setSelectedItem("CV#" + 53);
    }//GEN-LAST:event_jAuxFeuerfActionPerformed

    private void jAuxFeuerrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAuxFeuerrActionPerformed
        if (jAuxFeuer2.isSelected()) {
            CV[1][54] |= 16;
        } else {
            CV[1][54] &= ~16;
        }
        jCV_Anzeige.setSelectedItem("CV#" + 54);
    }//GEN-LAST:event_jAuxFeuerrActionPerformed

    private void jAuxSpSchfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAuxSpSchfActionPerformed
        if (jAuxSpSch1.isSelected()) {
            CV[1][53] |= 64;
        } else {
            CV[1][53] &= ~64;
        }
        jCV_Anzeige.setSelectedItem("CV#" + 53);
    }//GEN-LAST:event_jAuxSpSchfActionPerformed

    private void jAuxSpSchrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAuxSpSchrActionPerformed
        if (jAuxSpSch2.isSelected()) {
            CV[1][54] |= 64;
        } else {
            CV[1][54] &= ~64;
        }
        jCV_Anzeige.setSelectedItem("CV#" + 54);
    }//GEN-LAST:event_jAuxSpSchrActionPerformed

    private void jAnalogRW_WActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAnalogRW_WActionPerformed
        CV[1][12] = 0;
        jCV_Anzeige.setSelectedItem("CV#" + 12);
    }//GEN-LAST:event_jAnalogRW_WActionPerformed

    private void jAnalogRW_GActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAnalogRW_GActionPerformed
        CV[1][12] = 1;
        jCV_Anzeige.setSelectedItem("CV#" + 12);
    }//GEN-LAST:event_jAnalogRW_GActionPerformed

    private void jUmschaltEmpfFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jUmschaltEmpfFocusLost
        CV[1][64] = CVNavi.checkTextField(this, jUmschaltEmpf, 0, 255, 100, true);
        jCV_Anzeige.setSelectedItem("CV#" + 64);
    }//GEN-LAST:event_jUmschaltEmpfFocusLost

    private void jUmschaltEmpfKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jUmschaltEmpfKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus();
        }
    }//GEN-LAST:event_jUmschaltEmpfKeyReleased

    private void jRCplusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRCplusActionPerformed
        if (jRCplus.isSelected()) {
            CV[1][28] |= 128;
        } else {
            CV[1][28] &= ~128;
        }
        jCV_Anzeige.setSelectedItem("CV#" + 28);
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
            jCV_Anzeige.setSelectedItem("CV#" + 1);
        } else {
            jCV_Anzeige.setSelectedItem("CV#" + 17);
        }
    }//GEN-LAST:event_jDecoderAdresseFocusGained

    private void jMM_Addr_2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jMM_Addr_2FocusGained
        jCV_Anzeige.setSelectedItem("CV#" + 47);
    }//GEN-LAST:event_jMM_Addr_2FocusGained

    private void jDecoderAdresse1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDecoderAdresse1FocusGained
        jCV_Anzeige.setSelectedItem("CV#" + 19);
    }//GEN-LAST:event_jDecoderAdresse1FocusGained

    private void jBlinkfrequenzfFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlinkfrequenzfFocusGained
        jCV_Anzeige.setSelectedItem("CV#" + 104);
    }//GEN-LAST:event_jBlinkfrequenzfFocusGained

    private void jBlinkfrequenzrFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlinkfrequenzrFocusGained
        jCV_Anzeige.setSelectedItem("CV#" + 105);
    }//GEN-LAST:event_jBlinkfrequenzrFocusGained

    private void jBlinkfrequenz1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlinkfrequenz1FocusGained
        jCV_Anzeige.setSelectedItem("CV#" + 106);
    }//GEN-LAST:event_jBlinkfrequenz1FocusGained

    private void jBlinkfrequenz2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBlinkfrequenz2FocusGained
        jCV_Anzeige.setSelectedItem("CV#" + 107);
    }//GEN-LAST:event_jBlinkfrequenz2FocusGained

    private void jPacketTimeOutFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPacketTimeOutFocusGained
        jCV_Anzeige.setSelectedItem("CV#" + 11);
    }//GEN-LAST:event_jPacketTimeOutFocusGained

    private void jUmschaltEmpfFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jUmschaltEmpfFocusGained
        jCV_Anzeige.setSelectedItem("CV#" + 64);
    }//GEN-LAST:event_jUmschaltEmpfFocusGained

    private void jF15_7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF15_7ActionPerformed
        CVFktBits(57, 64, jF15_7.isSelected());
    }//GEN-LAST:event_jF15_7ActionPerformed

    private void jF14_RGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF14_RGActionPerformed
        CVFktBits(55, 4, jF14_RG.isSelected());
    }//GEN-LAST:event_jF14_RGActionPerformed

    private void jF12_RGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF12_RGActionPerformed
        CVFktBits(47, 4, jF12_RG.isSelected());
    }//GEN-LAST:event_jF12_RGActionPerformed

    private void jSelektFunktionenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSelektFunktionenActionPerformed
        initFunktionen();
    }//GEN-LAST:event_jSelektFunktionenActionPerformed

    private void jF15_8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF15_8ActionPerformed
        CVFktBits(57, 128, jF15_8.isSelected());

    }//GEN-LAST:event_jF15_8ActionPerformed

    private void jF16_8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF16_8ActionPerformed
        CVFktBits(61, 128, jF16_8.isSelected());

    }//GEN-LAST:event_jF16_8ActionPerformed

    private void jF1_USVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF1_USVActionPerformed
        CVFktBits(3, 1, jF1_USV.isSelected());
    }//GEN-LAST:event_jF1_USVActionPerformed

    private void jF2_USVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF2_USVActionPerformed
        CVFktBits(7, 1, jF2_USV.isSelected());
    }//GEN-LAST:event_jF2_USVActionPerformed

    private void jF3_USVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF3_USVActionPerformed
        CVFktBits(11, 1, jF3_USV.isSelected());
    }//GEN-LAST:event_jF3_USVActionPerformed

    private void jF4_USVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF4_USVActionPerformed
        CVFktBits(15, 1, jF4_USV.isSelected());
    }//GEN-LAST:event_jF4_USVActionPerformed

    private void jF5_USVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF5_USVActionPerformed
        CVFktBits(19, 1, jF5_USV.isSelected());
    }//GEN-LAST:event_jF5_USVActionPerformed

    private void jF6_USVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF6_USVActionPerformed
        CVFktBits(23, 1, jF6_USV.isSelected());
    }//GEN-LAST:event_jF6_USVActionPerformed

    private void jF7_USVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF7_USVActionPerformed
        CVFktBits(27, 1, jF7_USV.isSelected());
    }//GEN-LAST:event_jF7_USVActionPerformed

    private void jF8_USVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF8_USVActionPerformed
        CVFktBits(31, 1, jF8_USV.isSelected());
    }//GEN-LAST:event_jF8_USVActionPerformed

    private void jF9_USVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF9_USVActionPerformed
        CVFktBits(35, 1, jF9_USV.isSelected());
    }//GEN-LAST:event_jF9_USVActionPerformed

    private void jF10_USVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF10_USVActionPerformed
        CVFktBits(39, 1, jF10_USV.isSelected());
    }//GEN-LAST:event_jF10_USVActionPerformed

    private void jF11_USVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF11_USVActionPerformed
        CVFktBits(43, 1, jF11_USV.isSelected());
    }//GEN-LAST:event_jF11_USVActionPerformed

    private void jF12_USVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF12_USVActionPerformed
        CVFktBits(47, 1, jF12_USV.isSelected());
    }//GEN-LAST:event_jF12_USVActionPerformed

    private void jF13_USVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF13_USVActionPerformed
        CVFktBits(51, 1, jF13_USV.isSelected());
    }//GEN-LAST:event_jF13_USVActionPerformed

    private void jF14_USVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF14_USVActionPerformed
        CVFktBits(55, 1, jF13_USV.isSelected());
    }//GEN-LAST:event_jF14_USVActionPerformed

    private void jF15_USVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF15_USVActionPerformed
        CVFktBits(59, 1, jF15_USV.isSelected());
    }//GEN-LAST:event_jF15_USVActionPerformed

    private void jF16_USVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF16_USVActionPerformed
        CVFktBits(63, 1, jF16_USV.isSelected());
    }//GEN-LAST:event_jF16_USVActionPerformed

    private void jF1_StopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF1_StopActionPerformed
        CVFktBits(3, 2, jF1_Stop.isSelected());
    }//GEN-LAST:event_jF1_StopActionPerformed

    private void jF2_StopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF2_StopActionPerformed
        CVFktBits(7, 2, jF2_Stop.isSelected());
    }//GEN-LAST:event_jF2_StopActionPerformed

    private void jF3_StopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF3_StopActionPerformed
        CVFktBits(11, 2, jF3_Stop.isSelected());
    }//GEN-LAST:event_jF3_StopActionPerformed

    private void jF4_StopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF4_StopActionPerformed
        CVFktBits(15, 2, jF4_Stop.isSelected());
    }//GEN-LAST:event_jF4_StopActionPerformed

    private void jF5_StopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF5_StopActionPerformed
        CVFktBits(19, 2, jF5_Stop.isSelected());
    }//GEN-LAST:event_jF5_StopActionPerformed

    private void jF6_StopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF6_StopActionPerformed
        CVFktBits(23, 2, jF6_Stop.isSelected());
    }//GEN-LAST:event_jF6_StopActionPerformed

    private void jF7_StopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF7_StopActionPerformed
        CVFktBits(27, 2, jF7_Stop.isSelected());
    }//GEN-LAST:event_jF7_StopActionPerformed

    private void jF8_StopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF8_StopActionPerformed
        CVFktBits(31, 2, jF8_Stop.isSelected());
    }//GEN-LAST:event_jF8_StopActionPerformed

    private void jF9_StopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF9_StopActionPerformed
        CVFktBits(35, 2, jF9_Stop.isSelected());
    }//GEN-LAST:event_jF9_StopActionPerformed

    private void jF10_StopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF10_StopActionPerformed
        CVFktBits(39, 2, jF10_Stop.isSelected());
    }//GEN-LAST:event_jF10_StopActionPerformed

    private void jF11_StopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF11_StopActionPerformed
        CVFktBits(43, 2, jF11_Stop.isSelected());
    }//GEN-LAST:event_jF11_StopActionPerformed

    private void jF12_StopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF12_StopActionPerformed
        CVFktBits(47, 2, jF12_Stop.isSelected());
    }//GEN-LAST:event_jF12_StopActionPerformed

    private void jF13_StopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF13_StopActionPerformed
        CVFktBits(51, 2, jF13_Stop.isSelected());
    }//GEN-LAST:event_jF13_StopActionPerformed

    private void jF14_StopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF14_StopActionPerformed
        CVFktBits(55, 2, jF14_Stop.isSelected());
    }//GEN-LAST:event_jF14_StopActionPerformed

    private void jF15_StopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF15_StopActionPerformed
        CVFktBits(59, 2, jF15_Stop.isSelected());
    }//GEN-LAST:event_jF15_StopActionPerformed

    private void jF16_StopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF16_StopActionPerformed
        CVFktBits(63, 2, jF16_Stop.isSelected());
    }//GEN-LAST:event_jF16_StopActionPerformed

    private void jF1_RGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF1_RGActionPerformed
        CVFktBits(3, 4, jF1_RG.isSelected());
    }//GEN-LAST:event_jF1_RGActionPerformed

    private void jF2_RGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF2_RGActionPerformed
        CVFktBits(7, 4, jF2_RG.isSelected());
    }//GEN-LAST:event_jF2_RGActionPerformed

    private void jF3_RGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF3_RGActionPerformed
        CVFktBits(11, 4, jF3_RG.isSelected());
    }//GEN-LAST:event_jF3_RGActionPerformed

    private void jF4_RGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF4_RGActionPerformed
        CVFktBits(15, 4, jF4_RG.isSelected());
    }//GEN-LAST:event_jF4_RGActionPerformed

    private void jF5_RGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF5_RGActionPerformed
        CVFktBits(19, 4, jF5_RG.isSelected());
    }//GEN-LAST:event_jF5_RGActionPerformed

    private void jF6_RGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF6_RGActionPerformed
        CVFktBits(23, 4, jF6_RG.isSelected());
    }//GEN-LAST:event_jF6_RGActionPerformed

    private void jF7_RGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF7_RGActionPerformed
        CVFktBits(27, 4, jF7_RG.isSelected());
    }//GEN-LAST:event_jF7_RGActionPerformed

    private void jF8_RGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF8_RGActionPerformed
        CVFktBits(31, 4, jF8_RG.isSelected());
    }//GEN-LAST:event_jF8_RGActionPerformed

    private void jF9_RGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF9_RGActionPerformed
        CVFktBits(35, 4, jF9_RG.isSelected());
    }//GEN-LAST:event_jF9_RGActionPerformed

    private void jF10_RGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF10_RGActionPerformed
        CVFktBits(39, 4, jF10_RG.isSelected());
    }//GEN-LAST:event_jF10_RGActionPerformed

    private void jF11_RGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF11_RGActionPerformed
        CVFktBits(43, 4, jF11_RG.isSelected());
    }//GEN-LAST:event_jF11_RGActionPerformed

    private void jF13_RGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF13_RGActionPerformed
        CVFktBits(51, 4, jF13_RG.isSelected());
    }//GEN-LAST:event_jF13_RGActionPerformed

    private void jF15_RGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF15_RGActionPerformed
        CVFktBits(59, 4, jF15_RG.isSelected());
    }//GEN-LAST:event_jF15_RGActionPerformed

    private void jF16_RGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF16_RGActionPerformed
        CVFktBits(63, 4, jF16_RG.isSelected());
    }//GEN-LAST:event_jF16_RGActionPerformed

    private void jF1_ABVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF1_ABVActionPerformed
        CVFktBits(3, 8, jF1_ABV.isSelected());
    }//GEN-LAST:event_jF1_ABVActionPerformed

    private void jF2_ABVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF2_ABVActionPerformed
        CVFktBits(7, 8, jF2_ABV.isSelected());
    }//GEN-LAST:event_jF2_ABVActionPerformed

    private void jF3_ABVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF3_ABVActionPerformed
        CVFktBits(11, 8, jF3_ABV.isSelected());
    }//GEN-LAST:event_jF3_ABVActionPerformed

    private void jF4_ABVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF4_ABVActionPerformed
        CVFktBits(15, 8, jF4_ABV.isSelected());
    }//GEN-LAST:event_jF4_ABVActionPerformed

    private void jF5_ABVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF5_ABVActionPerformed
        CVFktBits(19, 8, jF5_ABV.isSelected());
    }//GEN-LAST:event_jF5_ABVActionPerformed

    private void jF6_ABVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF6_ABVActionPerformed
        CVFktBits(23, 8, jF7_ABV.isSelected());
    }//GEN-LAST:event_jF6_ABVActionPerformed

    private void jF7_ABVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF7_ABVActionPerformed
        CVFktBits(27, 8, jF7_ABV.isSelected());
    }//GEN-LAST:event_jF7_ABVActionPerformed

    private void jF8_ABVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF8_ABVActionPerformed
        CVFktBits(31, 8, jF8_ABV.isSelected());
    }//GEN-LAST:event_jF8_ABVActionPerformed

    private void jF9_ABVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF9_ABVActionPerformed
        CVFktBits(35, 8, jF9_ABV.isSelected());
    }//GEN-LAST:event_jF9_ABVActionPerformed

    private void jF10_ABVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF10_ABVActionPerformed
        CVFktBits(39, 8, jF10_ABV.isSelected());
    }//GEN-LAST:event_jF10_ABVActionPerformed

    private void jF11_ABVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF11_ABVActionPerformed
        CVFktBits(43, 8, jF11_ABV.isSelected());
    }//GEN-LAST:event_jF11_ABVActionPerformed

    private void jF12_ABVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF12_ABVActionPerformed
        CVFktBits(47, 8, jF12_ABV.isSelected());
    }//GEN-LAST:event_jF12_ABVActionPerformed

    private void jF13_ABVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF13_ABVActionPerformed
        CVFktBits(51, 8, jF13_ABV.isSelected());
    }//GEN-LAST:event_jF13_ABVActionPerformed

    private void jF14_ABVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF14_ABVActionPerformed
        CVFktBits(55, 8, jF14_ABV.isSelected());
    }//GEN-LAST:event_jF14_ABVActionPerformed

    private void jF15_ABVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF15_ABVActionPerformed
        CVFktBits(59, 8, jF15_ABV.isSelected());
    }//GEN-LAST:event_jF15_ABVActionPerformed

    private void jF16_ABVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF16_ABVActionPerformed
        CVFktBits(63, 8, jF16_ABV.isSelected());
    }//GEN-LAST:event_jF16_ABVActionPerformed

    private void jF1_FktFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jF1_FktFocusGained
        int CVakt = 3 + CVFktOffset;
        jCV_Anzeige.setSelectedItem("CV#" + CVakt);
    }//GEN-LAST:event_jF1_FktFocusGained

    private void jF1_FktFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jF1_FktFocusLost
        int CVakt = 3 + CVFktOffset;
        int value = Integer.parseInt(jF1_Fkt.getText());
        if (value != 255) {
            CV[1][CVakt] = CVNavi.checkTextField(this, jF1_Fkt, 0, 28, 255, true);
        } else {
            CV[1][CVakt] = 255;
        }
    }//GEN-LAST:event_jF1_FktFocusLost

    private void jF1_FktKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jF1_FktKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus();
        }
    }//GEN-LAST:event_jF1_FktKeyReleased

    private void jF2_FktFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jF2_FktFocusGained
        int CVakt = 7 + CVFktOffset;
        jCV_Anzeige.setSelectedItem("CV#" + CVakt);
    }//GEN-LAST:event_jF2_FktFocusGained

    private void jF2_FktFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jF2_FktFocusLost
        int CVakt = 7 + CVFktOffset;
        int value = Integer.parseInt(jF1_Fkt.getText());
        if (value != 255) {
            CV[1][CVakt] = CVNavi.checkTextField(this, jF1_Fkt, 0, 28, 255, true);
        } else {
            CV[1][CVakt] = 255;
        }
    }//GEN-LAST:event_jF2_FktFocusLost

    private void jF2_FktKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jF2_FktKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus();
        }
    }//GEN-LAST:event_jF2_FktKeyReleased

    private void jF3_FktFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jF3_FktFocusGained
        int CVakt = 11 + CVFktOffset;
        jCV_Anzeige.setSelectedItem("CV#" + CVakt);
    }//GEN-LAST:event_jF3_FktFocusGained

    private void jF3_FktFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jF3_FktFocusLost
        int CVakt = 11 + CVFktOffset;
        int value = Integer.parseInt(jF3_Fkt.getText());
        if (value != 255) {
            CV[1][CVakt] = CVNavi.checkTextField(this, jF3_Fkt, 0, 28, 255, true);
        } else {
            CV[1][CVakt] = 255;
        }

    }//GEN-LAST:event_jF3_FktFocusLost

    private void jF3_FktKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jF3_FktKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus();
        }
    }//GEN-LAST:event_jF3_FktKeyReleased

    private void jF4_FktFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jF4_FktFocusGained
        int CVakt = 15 + CVFktOffset;
        jCV_Anzeige.setSelectedItem("CV#" + CVakt);
    }//GEN-LAST:event_jF4_FktFocusGained

    private void jF4_FktFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jF4_FktFocusLost
        int CVakt = 15 + CVFktOffset;
        int value = Integer.parseInt(jF4_Fkt.getText());
        if (value != 255) {
            CV[1][CVakt] = CVNavi.checkTextField(this, jF4_Fkt, 0, 28, 255, true);
        } else {
            CV[1][CVakt] = 255;
        }

    }//GEN-LAST:event_jF4_FktFocusLost

    private void jF4_FktKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jF4_FktKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus();
        }
    }//GEN-LAST:event_jF4_FktKeyReleased

    private void jF5_FktFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jF5_FktFocusGained
        int CVakt = 19 + CVFktOffset;
        jCV_Anzeige.setSelectedItem("CV#" + CVakt);
    }//GEN-LAST:event_jF5_FktFocusGained

    private void jF5_FktFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jF5_FktFocusLost
        int CVakt = 19 + CVFktOffset;
        int value = Integer.parseInt(jF5_Fkt.getText());
        if (value != 255) {
            CV[1][CVakt] = CVNavi.checkTextField(this, jF5_Fkt, 0, 28, 255, true);
        } else {
            CV[1][CVakt] = 255;
        }

    }//GEN-LAST:event_jF5_FktFocusLost

    private void jF5_FktKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jF5_FktKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus();
        }
    }//GEN-LAST:event_jF5_FktKeyReleased

    private void jF6_FktFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jF6_FktFocusGained
        int CVakt = 23 + CVFktOffset;
        jCV_Anzeige.setSelectedItem("CV#" + CVakt);
    }//GEN-LAST:event_jF6_FktFocusGained

    private void jF6_FktFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jF6_FktFocusLost
        int CVakt = 23 + CVFktOffset;
        int value = Integer.parseInt(jF6_Fkt.getText());
        if (value != 255) {
            CV[1][CVakt] = CVNavi.checkTextField(this, jF6_Fkt, 0, 28, 255, true);
        } else {
            CV[1][CVakt] = 255;
        }

    }//GEN-LAST:event_jF6_FktFocusLost

    private void jF6_FktKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jF6_FktKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus();
        }
    }//GEN-LAST:event_jF6_FktKeyReleased

    private void jF7_FktFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jF7_FktFocusGained
        int CVakt = 27 + CVFktOffset;
        jCV_Anzeige.setSelectedItem("CV#" + CVakt);
    }//GEN-LAST:event_jF7_FktFocusGained

    private void jF7_FktFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jF7_FktFocusLost
        int CVakt = 27 + CVFktOffset;
        int value = Integer.parseInt(jF7_Fkt.getText());
        if (value != 255) {
            CV[1][CVakt] = CVNavi.checkTextField(this, jF7_Fkt, 0, 28, 255, true);
        } else {
            CV[1][CVakt] = 255;
        }

    }//GEN-LAST:event_jF7_FktFocusLost

    private void jF7_FktKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jF7_FktKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus();
        }
    }//GEN-LAST:event_jF7_FktKeyReleased

    private void jF8_FktFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jF8_FktFocusGained
        int CVakt = 31 + CVFktOffset;
        jCV_Anzeige.setSelectedItem("CV#" + CVakt);
    }//GEN-LAST:event_jF8_FktFocusGained

    private void jF8_FktFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jF8_FktFocusLost
        int CVakt = 31 + CVFktOffset;
        int value = Integer.parseInt(jF8_Fkt.getText());
        if (value != 255) {
            CV[1][CVakt] = CVNavi.checkTextField(this, jF8_Fkt, 0, 28, 255, true);
        } else {
            CV[1][CVakt] = 255;
        }

    }//GEN-LAST:event_jF8_FktFocusLost

    private void jF8_FktKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jF8_FktKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus();
        }
    }//GEN-LAST:event_jF8_FktKeyReleased

    private void jF9_FktFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jF9_FktFocusGained
        int CVakt = 35 + CVFktOffset;
        jCV_Anzeige.setSelectedItem("CV#" + CVakt);
    }//GEN-LAST:event_jF9_FktFocusGained

    private void jF9_FktFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jF9_FktFocusLost
        int CVakt = 35 + CVFktOffset;
        int value = Integer.parseInt(jF9_Fkt.getText());
        if (value != 255) {
            CV[1][CVakt] = CVNavi.checkTextField(this, jF9_Fkt, 0, 28, 255, true);
        } else {
            CV[1][CVakt] = 255;
        }

    }//GEN-LAST:event_jF9_FktFocusLost

    private void jF9_FktKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jF9_FktKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus();
        }
    }//GEN-LAST:event_jF9_FktKeyReleased

    private void jF10_FktFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jF10_FktFocusGained
        int CVakt = 39 + CVFktOffset;
        jCV_Anzeige.setSelectedItem("CV#" + CVakt);
    }//GEN-LAST:event_jF10_FktFocusGained

    private void jF10_FktFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jF10_FktFocusLost
        int CVakt = 39 + CVFktOffset;
        int value = Integer.parseInt(jF10_Fkt.getText());
        if (value != 255) {
            CV[1][CVakt] = CVNavi.checkTextField(this, jF10_Fkt, 0, 28, 255, true);
        } else {
            CV[1][CVakt] = 255;
        }

    }//GEN-LAST:event_jF10_FktFocusLost

    private void jF10_FktKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jF10_FktKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus();
        }
    }//GEN-LAST:event_jF10_FktKeyReleased

    private void jF11_FktFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jF11_FktFocusGained
        int CVakt = 43 + CVFktOffset;
        jCV_Anzeige.setSelectedItem("CV#" + CVakt);
    }//GEN-LAST:event_jF11_FktFocusGained

    private void jF11_FktFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jF11_FktFocusLost
        int CVakt = 43 + CVFktOffset;
        int value = Integer.parseInt(jF11_Fkt.getText());
        if (value != 255) {
            CV[1][CVakt] = CVNavi.checkTextField(this, jF11_Fkt, 0, 28, 255, true);
        } else {
            CV[1][CVakt] = 255;
        }

    }//GEN-LAST:event_jF11_FktFocusLost

    private void jF11_FktKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jF11_FktKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus();
        }
    }//GEN-LAST:event_jF11_FktKeyReleased

    private void jF12_FktFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jF12_FktFocusGained
        int CVakt = 47 + CVFktOffset;
        jCV_Anzeige.setSelectedItem("CV#" + CVakt);
    }//GEN-LAST:event_jF12_FktFocusGained

    private void jF12_FktFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jF12_FktFocusLost
        int CVakt = 47 + CVFktOffset;
        int value = Integer.parseInt(jF12_Fkt.getText());
        if (value != 255) {
            CV[1][CVakt] = CVNavi.checkTextField(this, jF12_Fkt, 0, 28, 255, true);
        } else {
            CV[1][CVakt] = 255;
        }

    }//GEN-LAST:event_jF12_FktFocusLost

    private void jF12_FktKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jF12_FktKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus();
        }
    }//GEN-LAST:event_jF12_FktKeyReleased

    private void jF13_FktFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jF13_FktFocusGained
        int CVakt = 51 + CVFktOffset;
        jCV_Anzeige.setSelectedItem("CV#" + CVakt);
    }//GEN-LAST:event_jF13_FktFocusGained

    private void jF13_FktFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jF13_FktFocusLost
        int CVakt = 51 + CVFktOffset;
        int value = Integer.parseInt(jF13_Fkt.getText());
        if (value != 255) {
            CV[1][CVakt] = CVNavi.checkTextField(this, jF13_Fkt, 0, 28, 255, true);
        } else {
            CV[1][CVakt] = 255;
        }

    }//GEN-LAST:event_jF13_FktFocusLost

    private void jF13_FktKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jF13_FktKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus();
        }
    }//GEN-LAST:event_jF13_FktKeyReleased

    private void jF14_FktFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jF14_FktFocusGained
        int CVakt = 55 + CVFktOffset;
        jCV_Anzeige.setSelectedItem("CV#" + CVakt);
    }//GEN-LAST:event_jF14_FktFocusGained

    private void jF14_FktFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jF14_FktFocusLost
        int CVakt = 55 + CVFktOffset;
        int value = Integer.parseInt(jF14_Fkt.getText());
        if (value != 255) {
            CV[1][CVakt] = CVNavi.checkTextField(this, jF14_Fkt, 0, 28, 255, true);
        } else {
            CV[1][CVakt] = 255;
        }

    }//GEN-LAST:event_jF14_FktFocusLost

    private void jF14_FktKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jF14_FktKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus();
        }
    }//GEN-LAST:event_jF14_FktKeyReleased

    private void jF15_FktFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jF15_FktFocusGained
        int CVakt = 59 + CVFktOffset;
        jCV_Anzeige.setSelectedItem("CV#" + CVakt);
    }//GEN-LAST:event_jF15_FktFocusGained

    private void jF15_FktFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jF15_FktFocusLost
        int CVakt = 59 + CVFktOffset;
        int value = Integer.parseInt(jF15_Fkt.getText());
        if (value != 255) {
            CV[1][CVakt] = CVNavi.checkTextField(this, jF15_Fkt, 0, 28, 255, true);
        } else {
            CV[1][CVakt] = 255;
        }

    }//GEN-LAST:event_jF15_FktFocusLost

    private void jF15_FktKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jF15_FktKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus();
        }
    }//GEN-LAST:event_jF15_FktKeyReleased

    private void jF16_FktFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jF16_FktFocusGained
        int CVakt = 63 + CVFktOffset;
        jCV_Anzeige.setSelectedItem("CV#" + CVakt);
    }//GEN-LAST:event_jF16_FktFocusGained

    private void jF16_FktFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jF16_FktFocusLost
        int CVakt = 63 + CVFktOffset;
        int value = Integer.parseInt(jF16_Fkt.getText());
        if (value != 255) {
            CV[1][CVakt] = CVNavi.checkTextField(this, jF16_Fkt, 0, 28, 255, true);
        } else {
            CV[1][CVakt] = 255;
        }

    }//GEN-LAST:event_jF16_FktFocusLost

    private void jF16_FktKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jF16_FktKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus();
        }
    }//GEN-LAST:event_jF16_FktKeyReleased

    private void jF0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF0ActionPerformed
        if (jF0.isSelected()) {
            CV[1][14] |= 1;
        } else {
            CV[1][14] &= ~1;
        }
        jCV_Anzeige.setSelectedItem("CV#" + 14);

    }//GEN-LAST:event_jF0ActionPerformed

    private void jF9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF9ActionPerformed
        if (jF9.isSelected()) {
            CV[1][14] |= 2;
        } else {
            CV[1][14] &= ~2;
        }
        jCV_Anzeige.setSelectedItem("CV#" + 14);

    }//GEN-LAST:event_jF9ActionPerformed

    private void jF10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF10ActionPerformed
        if (jF10.isSelected()) {
            CV[1][14] |= 4;
        } else {
            CV[1][14] &= ~4;
        }
        jCV_Anzeige.setSelectedItem("CV#" + 14);

    }//GEN-LAST:event_jF10ActionPerformed

    private void jF11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF11ActionPerformed
        if (jF11.isSelected()) {
            CV[1][14] |= 8;
        } else {
            CV[1][14] &= ~8;
        }
        jCV_Anzeige.setSelectedItem("CV#" + 14);

    }//GEN-LAST:event_jF11ActionPerformed

    private void jF12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF12ActionPerformed
        if (jF12.isSelected()) {
            CV[1][14] |= 16;
        } else {
            CV[1][14] &= ~16;
        }
        jCV_Anzeige.setSelectedItem("CV#" + 14);

    }//GEN-LAST:event_jF12ActionPerformed

    private void jEffekte_2ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jEffekte_2ComponentShown
//--------------Effekte 2 ----------------- 
        switch (CVNavi.Decoder) {
            case c.LD_G41:
                jLabel80.setVisible(false);
                jLabel49.setVisible(false);
                jLabel98.setVisible(false);
                jLabel110.setVisible(false);
                jLabel127.setVisible(false);
                jLabel187.setVisible(false);
                jLabel188.setVisible(false);
                jLabel123.setVisible(false);
                jLabel185.setVisible(false);
                jLabel186.setVisible(false);
                jLabel187.setVisible(false);
                jLabel100.setVisible(false);
                jLabel124.setVisible(false);
                jLabel125.setVisible(false);

                jLabel3.setVisible(false);
                jLabel107.setVisible(false);
                jLabel108.setVisible(false);
                jLabel109.setVisible(false);
                jMindestSchlt1.setVisible(false);

                jLabel129.setVisible(false);
                jLabel184.setVisible(false);
                jLabel118.setVisible(false);
                jLabel122.setVisible(false);
                jLabel99.setVisible(false);
                jLabel119.setVisible(false);
                jLabel120.setVisible(false);
                jLabel121.setVisible(false);

                jLabel111.setVisible(false);
                jLabel4.setVisible(false);

                jLabel206.setVisible(false);
                jLabel240.setVisible(false);
                jMindestSchlt2.setVisible(false);

                jIn1Func0.setVisible(false);
                jIn1Func1.setVisible(false);
                jIn1Func2.setVisible(false);
                jIn1Func3.setVisible(false);
                jIn1Func4.setVisible(false);
                jIn1Func5.setVisible(false);
                jIn1Func6.setVisible(false);
                jIn1Func7.setVisible(false);
                jIn1Func8.setVisible(false);
                jIn1Func9.setVisible(false);
                jIn1Func10.setVisible(false);
                jIn1Func11.setVisible(false);
                jIn1Func12.setVisible(false);
                jIn1Func13.setVisible(false);
                jIn1Func14.setVisible(false);
                jIn1Func15.setVisible(false);
                jIn2Func0.setVisible(false);
                jIn2Func1.setVisible(false);
                jIn2Func2.setVisible(false);
                jIn2Func3.setVisible(false);
                jIn2Func4.setVisible(false);
                jIn2Func5.setVisible(false);
                jIn2Func6.setVisible(false);
                jIn2Func7.setVisible(false);
                jIn2Func8.setVisible(false);
                jIn2Func9.setVisible(false);
                jIn2Func10.setVisible(false);
                jIn2Func11.setVisible(false);
                jIn2Func12.setVisible(false);
                jIn2Func13.setVisible(false);
                jIn2Func14.setVisible(false);
                jIn2Func15.setVisible(false);

                jDimmen3.setVisible(false);
                jDimmen4.setVisible(false);
                jDimmen5.setVisible(false);
                jDimmen6.setVisible(false);
                jLabel11.setVisible(false);
                jUeberlastDauer.setVisible(false);

                break;

            case c.LD_G42_2:
            case c.LD_W42_2:

                jIn2Func0.setVisible(false);
                jIn2Func1.setVisible(false);
                jIn2Func2.setVisible(false);
                jIn2Func3.setVisible(false);
                jIn2Func4.setVisible(false);
                jIn2Func5.setVisible(false);
                jIn2Func6.setVisible(false);
                jIn2Func7.setVisible(false);
                jIn2Func8.setVisible(false);
                jIn2Func9.setVisible(false);
                jIn2Func10.setVisible(false);
                jIn2Func11.setVisible(false);
                jIn2Func12.setVisible(false);
                jIn2Func13.setVisible(false);
                jIn2Func14.setVisible(false);
                jIn2Func15.setVisible(false);

                jLabel111.setVisible(false);
                jLabel4.setVisible(false);

                jLabel206.setVisible(false);
                jLabel240.setVisible(false);
                jMindestSchlt2.setVisible(false);

                jLabel49.setVisible(false);
                jLabel98.setVisible(false);
                jLabel110.setVisible(false);
                jLabel127.setVisible(false);
                jDimmen3.setVisible(false);
                jDimmen4.setVisible(false);
                jDimmen5.setVisible(false);
                jDimmen6.setVisible(false);
                jLabel11.setVisible(false);
                jUeberlastDauer.setVisible(false);

                break;

            case c.LD_G42:
            case c.LD_W42:
                jLabel7.setVisible(false);
                jDimmPeriode.setVisible(false);
                jLabel129.setVisible(false);
                jLabel184.setVisible(false);
                jLabel118.setVisible(false);
                jLabel122.setVisible(false);
                jLabel99.setVisible(false);
                jLabel119.setVisible(false);
                jLabel120.setVisible(false);
                jLabel121.setVisible(false);
                jLabel3.setVisible(false);
                jIn1Func8.setVisible(false);
                jIn1Func9.setVisible(false);
                jIn1Func10.setVisible(false);
                jIn1Func11.setVisible(false);
                jIn1Func12.setVisible(false);
                jIn1Func13.setVisible(false);
                jIn1Func14.setVisible(false);
                jIn1Func15.setVisible(false);
                jIn2Func0.setVisible(false);
                jIn2Func1.setVisible(false);
                jIn2Func2.setVisible(false);
                jIn2Func3.setVisible(false);
                jIn2Func4.setVisible(false);
                jIn2Func5.setVisible(false);
                jIn2Func6.setVisible(false);
                jIn2Func7.setVisible(false);
                jIn2Func8.setVisible(false);
                jIn2Func9.setVisible(false);
                jIn2Func10.setVisible(false);
                jIn2Func11.setVisible(false);
                jIn2Func12.setVisible(false);
                jIn2Func13.setVisible(false);
                jIn2Func14.setVisible(false);
                jIn2Func15.setVisible(false);

                jLabel111.setVisible(false);
                jLabel4.setVisible(false);

                jLabel206.setVisible(false);
                jLabel240.setVisible(false);
                jMindestSchlt2.setVisible(false);

                jLabel74.setVisible(false);
                jLabel49.setVisible(false);
                jLabel102.setVisible(false);
                jLabel98.setVisible(false);
                jLabel110.setVisible(false);
                jLabel127.setVisible(false);
                jDimmen1.setVisible(false);
                jDimmen2.setVisible(false);
                jDimmen3.setVisible(false);
                jDimmen4.setVisible(false);
                jDimmen5.setVisible(false);
                jDimmen6.setVisible(false);

                jLabel9.setVisible(false);
                jFahrStWalzer.setVisible(false);
                break;
            case c.LD_G43:
            case c.LD_G44:
                jLabel11.setVisible(false);
                jUeberlastDauer.setVisible(false);
                break;

        }

        jCV_Anzeige.setSelectedItem("CV#" + 47);

        jDimmenf.setText("" + CV[1][47]);
        jDimmenr.setText("" + CV[1][48]);
        jDimmen1.setText("" + CV[1][49]);
        jDimmen2.setText("" + CV[1][50]);
        jMindestSchlt1.setText("" + CV[1][97]);

        if (CVNavi.Decoder != c.LD_G42_2 && CVNavi.Decoder != c.LD_W42_2) {
        }
 
        switch (CVNavi.Decoder) {
            case c.LD_G41:
                jUeberlastSchwelle.setText("" + CV[1][109]);
                jDimmPeriode.setText("" + CV[1][100]);
 
                break;

            case c.LD_G42:
            case c.LD_W42:
                jIn1Func0.setSelected((CV[1][62] & 1) == 1);
                jIn1Func1.setSelected((CV[1][62] & 2) == 2);
                jIn1Func2.setSelected((CV[1][62] & 4) == 4);
                jIn1Func3.setSelected((CV[1][62] & 8) == 8);
                jIn1Func4.setSelected((CV[1][62] & 16) == 16);
                jIn1Func5.setSelected((CV[1][62] & 32) == 32);
                jIn1Func6.setSelected((CV[1][62] & 64) == 64);
                jIn1Func7.setSelected((CV[1][62] & 128) == 128);

                jUeberlastSchwelle.setText("" + CV[1][64]);
                jUeberlastDauer.setText("" + CV[1][63]);
                jDimmPeriode.setText("" + CV[1][100]);
 
                break;

            case c.LD_G42_2:
            case c.LD_W42_2:
                jIn1Func0.setSelected((CV[1][105] & 1) == 1);
                jIn1Func1.setSelected((CV[1][105] & 2) == 2);
                jIn1Func2.setSelected((CV[1][105] & 4) == 4);
                jIn1Func3.setSelected((CV[1][105] & 8) == 8);
                jIn1Func4.setSelected((CV[1][105] & 16) == 16);
                jIn1Func5.setSelected((CV[1][105] & 32) == 32);
                jIn1Func6.setSelected((CV[1][105] & 64) == 64);
                jIn1Func7.setSelected((CV[1][105] & 128) == 128);

                jIn1Func8.setSelected((CV[1][106] & 1) == 1);
                jIn1Func9.setSelected((CV[1][106] & 2) == 2);
                jIn1Func10.setSelected((CV[1][106] & 4) == 4);
                jIn1Func11.setSelected((CV[1][106] & 8) == 8);
                jIn1Func12.setSelected((CV[1][106] & 16) == 16);
                jIn1Func13.setSelected((CV[1][106] & 32) == 32);
                jIn1Func14.setSelected((CV[1][106] & 64) == 64);
                jIn1Func15.setSelected((CV[1][106] & 128) == 128);

                jUeberlastSchwelle.setText("" + CV[1][109]);
                jFahrStWalzer.setText("" + CV[1][110]);
                break;

            case c.LD_G43:
            case c.LD_G44:
                jIn1Func0.setSelected((CV[1][105] & 1) == 1);
                jIn1Func1.setSelected((CV[1][105] & 2) == 2);
                jIn1Func2.setSelected((CV[1][105] & 4) == 4);
                jIn1Func3.setSelected((CV[1][105] & 8) == 8);
                jIn1Func4.setSelected((CV[1][105] & 16) == 16);
                jIn1Func5.setSelected((CV[1][105] & 32) == 32);
                jIn1Func6.setSelected((CV[1][105] & 64) == 64);
                jIn1Func7.setSelected((CV[1][105] & 128) == 128);

                jIn1Func8.setSelected((CV[1][106] & 1) == 1);
                jIn1Func9.setSelected((CV[1][106] & 2) == 2);
                jIn1Func10.setSelected((CV[1][106] & 4) == 4);
                jIn1Func11.setSelected((CV[1][106] & 8) == 8);
                jIn1Func12.setSelected((CV[1][106] & 16) == 16);
                jIn1Func13.setSelected((CV[1][106] & 32) == 32);
                jIn1Func14.setSelected((CV[1][106] & 64) == 64);
                jIn1Func15.setSelected((CV[1][106] & 128) == 128);

                jIn2Func0.setSelected((CV[1][107] & 1) == 1);
                jIn2Func1.setSelected((CV[1][107] & 2) == 2);
                jIn2Func2.setSelected((CV[1][107] & 4) == 4);
                jIn2Func3.setSelected((CV[1][107] & 8) == 8);
                jIn2Func4.setSelected((CV[1][107] & 16) == 16);
                jIn2Func5.setSelected((CV[1][107] & 32) == 32);
                jIn2Func6.setSelected((CV[1][107] & 64) == 64);
                jIn2Func7.setSelected((CV[1][107] & 128) == 128);

                jIn2Func8.setSelected((CV[1][108] & 1) == 1);
                jIn2Func9.setSelected((CV[1][108] & 2) == 2);
                jIn2Func10.setSelected((CV[1][108] & 4) == 4);
                jIn2Func11.setSelected((CV[1][108] & 8) == 8);
                jIn2Func12.setSelected((CV[1][108] & 16) == 16);
                jIn2Func13.setSelected((CV[1][108] & 32) == 32);
                jIn2Func14.setSelected((CV[1][108] & 64) == 64);
                jIn2Func15.setSelected((CV[1][108] & 128) == 128);
           
                jDimmen3.setText("" + CV[1][51]);
                jDimmen4.setText("" + CV[1][52]);
                jDimmen5.setText("" + CV[1][53]);
                jDimmen6.setText("" + CV[1][54]);
                jMindestSchlt2.setText("" + CV[1][98]);

                jDimmPeriode.setText("" + CV[1][100]);
                jUeberlastSchwelle.setText("" + CV[1][109]);
                jFahrStWalzer.setText("" + CV[1][110]);

                break;
        }
    }//GEN-LAST:event_jEffekte_2ComponentShown

    private void jDimmen6KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDimmen6KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus();
        }
    }//GEN-LAST:event_jDimmen6KeyReleased

    private void jDimmen6FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmen6FocusLost
        CV[1][54] = CVNavi.checkTextField(this, jDimmen6, 1, 255, 255, true);
        jCV_Anzeige.setSelectedItem("CV#" + 54);
    }//GEN-LAST:event_jDimmen6FocusLost

    private void jDimmen6FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmen6FocusGained
        jCV_Anzeige.setSelectedItem("CV#" + 54);
    }//GEN-LAST:event_jDimmen6FocusGained

    private void jDimmen5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDimmen5KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus();
        }
    }//GEN-LAST:event_jDimmen5KeyReleased

    private void jDimmen5FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmen5FocusLost
        CV[1][53] = CVNavi.checkTextField(this, jDimmen5, 1, 255, 255, true);
        jCV_Anzeige.setSelectedItem("CV#" + 53);
    }//GEN-LAST:event_jDimmen5FocusLost

    private void jDimmen5FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmen5FocusGained
        jCV_Anzeige.setSelectedItem("CV#" + 53);
    }//GEN-LAST:event_jDimmen5FocusGained

    private void jDimmenrKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDimmenrKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus();
        }
    }//GEN-LAST:event_jDimmenrKeyReleased

    private void jDimmenrFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmenrFocusLost
        CV[1][48] = CVNavi.checkTextField(this, jDimmenr, 1, 255, 255, true);
        jCV_Anzeige.setSelectedItem("CV#" + 48);
    }//GEN-LAST:event_jDimmenrFocusLost

    private void jDimmenrFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmenrFocusGained
        jCV_Anzeige.setSelectedItem("CV#" + 48);
    }//GEN-LAST:event_jDimmenrFocusGained

    private void jDimmenfKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDimmenfKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus();
        }
    }//GEN-LAST:event_jDimmenfKeyReleased

    private void jDimmenfFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmenfFocusLost
        CV[1][47] = CVNavi.checkTextField(this, jDimmenf, 1, 255, 255, true);
        jCV_Anzeige.setSelectedItem("CV#" + 47);
    }//GEN-LAST:event_jDimmenfFocusLost

    private void jDimmenfFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmenfFocusGained
        jCV_Anzeige.setSelectedItem("CV#" + 47);
    }//GEN-LAST:event_jDimmenfFocusGained

    private void jIn2Func9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn2Func9ActionPerformed
        CVFktBitsDir(108, 2, jIn2Func9.isSelected());
    }//GEN-LAST:event_jIn2Func9ActionPerformed

    private void jIn2Func8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn2Func8ActionPerformed
        CVFktBitsDir(108, 1, jIn2Func8.isSelected());
    }//GEN-LAST:event_jIn2Func8ActionPerformed

    private void jIn1Func9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn1Func9ActionPerformed
        CVFktBitsDir(106, 2, jIn1Func9.isSelected());
    }//GEN-LAST:event_jIn1Func9ActionPerformed

    private void jIn1Func8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn1Func8ActionPerformed
        CVFktBitsDir(106, 1, jIn1Func8.isSelected());
    }//GEN-LAST:event_jIn1Func8ActionPerformed

    private void jIn2Func15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn2Func15ActionPerformed
        CVFktBitsDir(108, 1128, jIn2Func15.isSelected());
    }//GEN-LAST:event_jIn2Func15ActionPerformed

    private void jIn2Func14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn2Func14ActionPerformed
        CVFktBitsDir(108, 64, jIn2Func14.isSelected());
    }//GEN-LAST:event_jIn2Func14ActionPerformed

    private void jIn2Func13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn2Func13ActionPerformed
        CVFktBitsDir(108, 32, jIn2Func13.isSelected());
    }//GEN-LAST:event_jIn2Func13ActionPerformed

    private void jIn2Func12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn2Func12ActionPerformed
        CVFktBitsDir(108, 16, jIn2Func12.isSelected());
    }//GEN-LAST:event_jIn2Func12ActionPerformed

    private void jIn2Func11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn2Func11ActionPerformed
        CVFktBitsDir(108, 8, jIn2Func11.isSelected());
    }//GEN-LAST:event_jIn2Func11ActionPerformed

    private void jIn2Func10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn2Func10ActionPerformed
        CVFktBitsDir(108, 4, jIn2Func10.isSelected());
    }//GEN-LAST:event_jIn2Func10ActionPerformed

    private void jIn1Func15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn1Func15ActionPerformed
        CVFktBitsDir(106, 128, jIn1Func15.isSelected());
    }//GEN-LAST:event_jIn1Func15ActionPerformed

    private void jIn1Func14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn1Func14ActionPerformed
        CVFktBitsDir(106, 64, jIn1Func14.isSelected());
    }//GEN-LAST:event_jIn1Func14ActionPerformed

    private void jIn1Func13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn1Func13ActionPerformed
        CVFktBitsDir(106, 32, jIn1Func13.isSelected());
    }//GEN-LAST:event_jIn1Func13ActionPerformed

    private void jIn1Func12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn1Func12ActionPerformed
        CVFktBitsDir(106, 16, jIn1Func12.isSelected());
    }//GEN-LAST:event_jIn1Func12ActionPerformed

    private void jIn1Func11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn1Func11ActionPerformed
        CVFktBitsDir(106, 8, jIn1Func11.isSelected());
    }//GEN-LAST:event_jIn1Func11ActionPerformed

    private void jIn1Func10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn1Func10ActionPerformed
        CVFktBitsDir(106, 4, jIn1Func10.isSelected());
    }//GEN-LAST:event_jIn1Func10ActionPerformed

    private void jMindestSchlt2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jMindestSchlt2KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus();
        }
    }//GEN-LAST:event_jMindestSchlt2KeyReleased

    private void jMindestSchlt2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jMindestSchlt2FocusLost
        CV[1][98] = CVNavi.checkTextField(this, jMindestSchlt2, 0, 255, 0, true);
        jCV_Anzeige.setSelectedItem("CV#" + 98);
    }//GEN-LAST:event_jMindestSchlt2FocusLost

    private void jMindestSchlt2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jMindestSchlt2FocusGained
        jCV_Anzeige.setSelectedItem("CV#" + 178);
    }//GEN-LAST:event_jMindestSchlt2FocusGained

    private void jMindestSchlt1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jMindestSchlt1KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus();
        }
    }//GEN-LAST:event_jMindestSchlt1KeyReleased

    private void jMindestSchlt1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jMindestSchlt1FocusLost
        CV[1][97] = CVNavi.checkTextField(this, jMindestSchlt1, 0, 255, 0, true);
        jCV_Anzeige.setSelectedItem("CV#" + 97);
    }//GEN-LAST:event_jMindestSchlt1FocusLost

    private void jMindestSchlt1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jMindestSchlt1FocusGained
        jCV_Anzeige.setSelectedItem("CV#" + 97);
    }//GEN-LAST:event_jMindestSchlt1FocusGained

    private void jDimmen4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDimmen4KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus();
        }
    }//GEN-LAST:event_jDimmen4KeyReleased

    private void jDimmen4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmen4FocusLost
        CV[1][52] = CVNavi.checkTextField(this, jDimmen4, 1, 255, 255, true);
        jCV_Anzeige.setSelectedItem("CV#" + 52);
    }//GEN-LAST:event_jDimmen4FocusLost

    private void jDimmen4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmen4FocusGained
        jCV_Anzeige.setSelectedItem("CV#" + 52);
    }//GEN-LAST:event_jDimmen4FocusGained

    private void jDimmen3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDimmen3KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus();
        }
    }//GEN-LAST:event_jDimmen3KeyReleased

    private void jDimmen3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmen3FocusLost
        CV[1][51] = CVNavi.checkTextField(this, jDimmen3, 1, 255, 255, true);
        jCV_Anzeige.setSelectedItem("CV#" + 51);
    }//GEN-LAST:event_jDimmen3FocusLost

    private void jDimmen3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmen3FocusGained
        jCV_Anzeige.setSelectedItem("CV#" + 51);
    }//GEN-LAST:event_jDimmen3FocusGained

    private void jDimmen2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDimmen2KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus();
        }
    }//GEN-LAST:event_jDimmen2KeyReleased

    private void jDimmen2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmen2FocusLost
        CV[1][50] = CVNavi.checkTextField(this, jDimmen2, 1, 255, 255, true);
        jCV_Anzeige.setSelectedItem("CV#" + 50);
    }//GEN-LAST:event_jDimmen2FocusLost

    private void jDimmen2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmen2FocusGained
        jCV_Anzeige.setSelectedItem("CV#" + 50);
    }//GEN-LAST:event_jDimmen2FocusGained

    private void jDimmen1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDimmen1KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus();
        }
    }//GEN-LAST:event_jDimmen1KeyReleased

    private void jDimmen1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmen1FocusLost
        CV[1][49] = CVNavi.checkTextField(this, jDimmen1, 1, 255, 255, true);
        jCV_Anzeige.setSelectedItem("CV#" + 49);
    }//GEN-LAST:event_jDimmen1FocusLost

    private void jDimmen1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmen1FocusGained
        jCV_Anzeige.setSelectedItem("CV#" + 49);
    }//GEN-LAST:event_jDimmen1FocusGained

    private void jIn1Func0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn1Func0ActionPerformed
        if (CVNavi.Decoder == c.LD_G42 || CVNavi.Decoder == c.LD_W42) {
            CVFktBitsDir(62, 1, jIn1Func0.isSelected());
        } else {
            CVFktBitsDir(105, 1, jIn1Func0.isSelected());
        }
    }//GEN-LAST:event_jIn1Func0ActionPerformed

    private void jIn1Func1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn1Func1ActionPerformed
        if (CVNavi.Decoder == c.LD_G42 || CVNavi.Decoder == c.LD_W42) {
            CVFktBitsDir(62, 2, jIn1Func1.isSelected());
        } else {
            CVFktBitsDir(105, 2, jIn1Func1.isSelected());
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jIn1Func1ActionPerformed

    private void jIn1Func2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn1Func2ActionPerformed
        if (CVNavi.Decoder == c.LD_G42 || CVNavi.Decoder == c.LD_W42) {
            CVFktBitsDir(62, 4, jIn1Func2.isSelected());
        } else {
            CVFktBitsDir(105, 4, jIn1Func2.isSelected());
        }
    }//GEN-LAST:event_jIn1Func2ActionPerformed

    private void jIn1Func3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn1Func3ActionPerformed
        if (CVNavi.Decoder == c.LD_G42 || CVNavi.Decoder == c.LD_W42) {
            CVFktBitsDir(62, 8, jIn1Func3.isSelected());
        } else {
            CVFktBitsDir(105, 8, jIn1Func3.isSelected());
        }
    }//GEN-LAST:event_jIn1Func3ActionPerformed

    private void jIn1Func4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn1Func4ActionPerformed
        if (CVNavi.Decoder == c.LD_G42 || CVNavi.Decoder == c.LD_W42) {
            CVFktBitsDir(62, 16, jIn1Func4.isSelected());
        } else {
            CVFktBitsDir(105, 16, jIn1Func4.isSelected());
        }
    }//GEN-LAST:event_jIn1Func4ActionPerformed

    private void jIn1Func5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn1Func5ActionPerformed
        if (CVNavi.Decoder == c.LD_G42 || CVNavi.Decoder == c.LD_W42) {
            CVFktBitsDir(62, 32, jIn1Func5.isSelected());
        } else {
            CVFktBitsDir(105, 32, jIn1Func5.isSelected());
        }
    }//GEN-LAST:event_jIn1Func5ActionPerformed

    private void jIn1Func6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn1Func6ActionPerformed
        if (CVNavi.Decoder == c.LD_G42 || CVNavi.Decoder == c.LD_W42) {
            CVFktBitsDir(62, 64, jIn1Func6.isSelected());
        } else {
            CVFktBitsDir(105, 64, jIn1Func6.isSelected());
        }
    }//GEN-LAST:event_jIn1Func6ActionPerformed

    private void jIn1Func7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn1Func7ActionPerformed
        if (CVNavi.Decoder == c.LD_G42 || CVNavi.Decoder == c.LD_W42) {
            CVFktBitsDir(62, 128, jIn1Func7.isSelected());
        } else {
            CVFktBitsDir(105, 128, jIn1Func7.isSelected());
        }
    }//GEN-LAST:event_jIn1Func7ActionPerformed

    private void jIn2Func0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn2Func0ActionPerformed
        CVFktBitsDir(107, 1, jIn2Func0.isSelected());
    }//GEN-LAST:event_jIn2Func0ActionPerformed

    private void jIn2Func1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn2Func1ActionPerformed
        CVFktBitsDir(107, 2, jIn2Func1.isSelected());
    }//GEN-LAST:event_jIn2Func1ActionPerformed

    private void jIn2Func2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn2Func2ActionPerformed
        CVFktBitsDir(107, 4, jIn2Func2.isSelected());
    }//GEN-LAST:event_jIn2Func2ActionPerformed

    private void jIn2Func3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn2Func3ActionPerformed
        CVFktBitsDir(107, 8, jIn2Func3.isSelected());
    }//GEN-LAST:event_jIn2Func3ActionPerformed

    private void jIn2Func4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn2Func4ActionPerformed
        CVFktBitsDir(107, 16, jIn2Func4.isSelected());
    }//GEN-LAST:event_jIn2Func4ActionPerformed

    private void jIn2Func5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn2Func5ActionPerformed
        CVFktBitsDir(107, 32, jIn2Func5.isSelected());
    }//GEN-LAST:event_jIn2Func5ActionPerformed

    private void jIn2Func6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn2Func6ActionPerformed
        CVFktBitsDir(107, 64, jIn2Func6.isSelected());
    }//GEN-LAST:event_jIn2Func6ActionPerformed

    private void jIn2Func7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIn2Func7ActionPerformed
        CVFktBitsDir(107, 128, jIn2Func7.isSelected());
    }//GEN-LAST:event_jIn2Func7ActionPerformed

    private void jDimmPeriodeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmPeriodeFocusGained
        jCV_Anzeige.setSelectedItem("CV#" + 100);
    }//GEN-LAST:event_jDimmPeriodeFocusGained

    private void jDimmPeriodeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDimmPeriodeFocusLost
        CV[1][100] = CVNavi.checkTextField(this, jDimmPeriode, 0, 255, 0, true);
        jCV_Anzeige.setSelectedItem("CV#" + 100);
    }//GEN-LAST:event_jDimmPeriodeFocusLost

    private void jDimmPeriodeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDimmPeriodeKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus();
        }
    }//GEN-LAST:event_jDimmPeriodeKeyReleased

    private void jKickZeitFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jKickZeitFocusLost
        CV[1][99] = CVNavi.checkTextField(this, jKickZeit, 0, 255, 0, true);
        jCV_Anzeige.setSelectedItem("CV#" + 99);
    }//GEN-LAST:event_jKickZeitFocusLost

    private void jKickZeitKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jKickZeitKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus();
        }
    }//GEN-LAST:event_jKickZeitKeyReleased

    private void jKickZeitFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jKickZeitFocusGained
        jCV_Anzeige.setSelectedItem("CV#" + 99);
    }//GEN-LAST:event_jKickZeitFocusGained

    private void jFahrStWalzerFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFahrStWalzerFocusGained
        jCV_Anzeige.setSelectedItem("CV#" + 110);
    }//GEN-LAST:event_jFahrStWalzerFocusGained

    private void jFahrStWalzerKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFahrStWalzerKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus();
        }
    }//GEN-LAST:event_jFahrStWalzerKeyReleased

    private void jFahrStWalzerFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFahrStWalzerFocusLost
        CV[1][110] = CVNavi.checkTextField(this, jFahrStWalzer, 0, 255, 0, true);
        jCV_Anzeige.setSelectedItem("CV#" + 110);
    }//GEN-LAST:event_jFahrStWalzerFocusLost

    private void jUeberlastSchwelleFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jUeberlastSchwelleFocusGained
        if (CVNavi.Decoder == c.LD_G42 || CVNavi.Decoder == c.LD_W42) {
            jCV_Anzeige.setSelectedItem("CV#" + 64);
        } else {
            jCV_Anzeige.setSelectedItem("CV#" + 109);
        }
    }//GEN-LAST:event_jUeberlastSchwelleFocusGained

    private void jUeberlastSchwelleFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jUeberlastSchwelleFocusLost
        if (CVNavi.Decoder == c.LD_G42 || CVNavi.Decoder == c.LD_W42) {
            CV[1][64] = CVNavi.checkTextField(this, jUeberlastSchwelle, 0, 255, 0, true);
            jCV_Anzeige.setSelectedItem("CV#" + 64);
        } else {
            CV[1][109] = CVNavi.checkTextField(this, jUeberlastSchwelle, 0, 255, 0, true);
            jCV_Anzeige.setSelectedItem("CV#" + 109);
        }
    }//GEN-LAST:event_jUeberlastSchwelleFocusLost

    private void jUeberlastSchwelleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jUeberlastSchwelleKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus();
        }
    }//GEN-LAST:event_jUeberlastSchwelleKeyReleased

    private void jUeberlastDauerFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jUeberlastDauerFocusGained
        jCV_Anzeige.setSelectedItem("CV#" + 63);

    }//GEN-LAST:event_jUeberlastDauerFocusGained

    private void jUeberlastDauerFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jUeberlastDauerFocusLost
        CV[1][63] = CVNavi.checkTextField(this, jUeberlastDauer, 0, 32, 0, true);
        jCV_Anzeige.setSelectedItem("CV#" + 63);
    }//GEN-LAST:event_jUeberlastDauerFocusLost

    private void jUeberlastDauerKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jUeberlastDauerKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus();
        }
    }//GEN-LAST:event_jUeberlastDauerKeyReleased

    private void jConsF0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jConsF0ActionPerformed
        CVFktBitsDir(22, 1, jConsF0.isSelected());
    }//GEN-LAST:event_jConsF0ActionPerformed

    private void jConsF1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jConsF1ActionPerformed
        CVFktBitsDir(21, 1, jConsF1.isSelected());
    }//GEN-LAST:event_jConsF1ActionPerformed

    private void jConsF2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jConsF2ActionPerformed
        CVFktBitsDir(21, 2, jConsF2.isSelected());
    }//GEN-LAST:event_jConsF2ActionPerformed

    private void jConsF4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jConsF4ActionPerformed
        CVFktBitsDir(21, 8, jConsF4.isSelected());
    }//GEN-LAST:event_jConsF4ActionPerformed

    private void jConsF3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jConsF3ActionPerformed
        CVFktBitsDir(21, 4, jConsF3.isSelected());
    }//GEN-LAST:event_jConsF3ActionPerformed

    private void jConsF5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jConsF5ActionPerformed
        CVFktBitsDir(21, 16, jConsF5.isSelected());
    }//GEN-LAST:event_jConsF5ActionPerformed

    private void jConsF6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jConsF6ActionPerformed
        CVFktBitsDir(21, 32, jConsF6.isSelected());
    }//GEN-LAST:event_jConsF6ActionPerformed

    private void jConsF7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jConsF7ActionPerformed
        CVFktBitsDir(21, 64, jConsF7.isSelected());
    }//GEN-LAST:event_jConsF7ActionPerformed

    private void jConsF8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jConsF8ActionPerformed
        CVFktBitsDir(21, 128, jConsF8.isSelected());
    }//GEN-LAST:event_jConsF8ActionPerformed

    private void jConsF9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jConsF9ActionPerformed
        CVFktBitsDir(22, 2, jConsF9.isSelected());
    }//GEN-LAST:event_jConsF9ActionPerformed

    private void jConsF10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jConsF10ActionPerformed
        CVFktBitsDir(22, 4, jConsF10.isSelected());
    }//GEN-LAST:event_jConsF10ActionPerformed

    private void jConsF11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jConsF11ActionPerformed
        CVFktBitsDir(22, 8, jConsF11.isSelected());
    }//GEN-LAST:event_jConsF11ActionPerformed

    private void jConsF12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jConsF12ActionPerformed
        CVFktBitsDir(22, 16, jConsF12.isSelected());
    }//GEN-LAST:event_jConsF12ActionPerformed

    private void jConsAdrFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jConsAdrFocusGained
        jCV_Anzeige.setSelectedItem("CV#" + 19);
    }//GEN-LAST:event_jConsAdrFocusGained

    private void jConsAdrFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jConsAdrFocusLost
        CV[1][19] = CVNavi.checkTextField(this, jConsAdr, 0, 255, 0, true);
        jCV_Anzeige.setSelectedItem("CV#" + 19);
    }//GEN-LAST:event_jConsAdrFocusLost

    private void jConsAdrKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jConsAdrKeyReleased
       if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus();
        }
    }//GEN-LAST:event_jConsAdrKeyReleased

    private void jLastregEinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jLastregEinActionPerformed
        CVFktBitsDir(116, 1, jLastregEin.isSelected());
    }//GEN-LAST:event_jLastregEinActionPerformed

    private void jVerstaerkFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jVerstaerkFocusGained
        jCV_Anzeige.setSelectedItem("CV#" + 112);
    }//GEN-LAST:event_jVerstaerkFocusGained

    private void jVerstaerkFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jVerstaerkFocusLost
       CV[1][112] = CVNavi.checkTextField(this, jVerstaerk, 1, 15, 1, true);
        jCV_Anzeige.setSelectedItem("CV#" + 112);
    }//GEN-LAST:event_jVerstaerkFocusLost

    private void jVerstaerkKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jVerstaerkKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus();
        }
    }//GEN-LAST:event_jVerstaerkKeyReleased

    private void jKPFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jKPFocusGained
        jCV_Anzeige.setSelectedItem("CV#" + 113);
    }//GEN-LAST:event_jKPFocusGained

    private void jKPFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jKPFocusLost
       CV[1][113] = CVNavi.checkTextField(this, jKP, 0, 255, 0, true);
        jCV_Anzeige.setSelectedItem("CV#" + 113);
    }//GEN-LAST:event_jKPFocusLost

    private void jKPKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jKPKeyReleased
       if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus();
        }
    }//GEN-LAST:event_jKPKeyReleased

    private void jKIFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jKIFocusGained
        jCV_Anzeige.setSelectedItem("CV#" + 114);
    }//GEN-LAST:event_jKIFocusGained

    private void jKIFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jKIFocusLost
       CV[1][114] = CVNavi.checkTextField(this, jKI, 0, 255, 0, true);
        jCV_Anzeige.setSelectedItem("CV#" + 114);
    }//GEN-LAST:event_jKIFocusLost

    private void jKIKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jKIKeyReleased
       if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus();
        }
    }//GEN-LAST:event_jKIKeyReleased

    private void jKDFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jKDFocusGained
        jCV_Anzeige.setSelectedItem("CV#" + 115);
    }//GEN-LAST:event_jKDFocusGained

    private void jKDFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jKDFocusLost
       CV[1][115] = CVNavi.checkTextField(this, jKD, 0, 255, 0, true);
        jCV_Anzeige.setSelectedItem("CV#" + 115);
    }//GEN-LAST:event_jKDFocusLost

    private void jKDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jKDKeyReleased
       if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus();
        }
    }//GEN-LAST:event_jKDKeyReleased

        void filfilCVs
        
            () {
        String[] keys = null;
            switch (CVNavi.Decoder) {
                case c.LD_G41:
                    keys = new String[]{"LD-G-41"};
                    break;

                case c.LD_G42:
                    keys = new String[]{"LD-G-42"};
                    break;

                case c.LD_W42:
                    keys = new String[]{"LD-W-42"};
                    break;

                case c.LD_G42_2:
                    keys = new String[]{"LD-G-42.2"};
                    break;

                case c.LD_W42_2:
                    keys = new String[]{"LD-W-42.2"};
                    break;

                case c.LD_G43:
                    keys = new String[]{"LD-G-43"};
                    break;

                case c.LD_G44:
                    keys = new String[]{"LD-G-44"};
                    break;
            }
            Boolean b = parseString2CVs.convertString2CV(ReturnString, keys, CV, jComment, CVNavi);
        }

        void updateTabs
        
            () {
        int idx = jDecodereigenschaften.getSelectedIndex();

            for (int i = 0; i < jDecodereigenschaften.getComponentCount(); i++) {
                jDecodereigenschaften.setSelectedIndex(i);
            }

            jDecodereigenschaften.setSelectedIndex(idx);
        }

    

    private int getCVfromIndexString(JComboBox jCB, String prefix) {
        int CV = 0;
        Object oSel = jCB.getSelectedItem();
        String sSel = "" + oSel;
        if (sSel.startsWith(prefix)) {
            String sT = sSel.substring(prefix.length());
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
    private javax.swing.JCheckBox jAuxBlink1;
    private javax.swing.JCheckBox jAuxBlink2;
    private javax.swing.JCheckBox jAuxBlink3;
    private javax.swing.JCheckBox jAuxBlink4;
    private javax.swing.JCheckBox jAuxBlink5;
    private javax.swing.JCheckBox jAuxBlink6;
    private javax.swing.JCheckBox jAuxBlinkf;
    private javax.swing.JCheckBox jAuxBlinkr;
    private javax.swing.JCheckBox jAuxDim1;
    private javax.swing.JCheckBox jAuxDim2;
    private javax.swing.JCheckBox jAuxDim3;
    private javax.swing.JCheckBox jAuxDim4;
    private javax.swing.JCheckBox jAuxDim5;
    private javax.swing.JCheckBox jAuxDim6;
    private javax.swing.JCheckBox jAuxDimf;
    private javax.swing.JCheckBox jAuxDimr;
    private javax.swing.JCheckBox jAuxFeuer1;
    private javax.swing.JCheckBox jAuxFeuer2;
    private javax.swing.JCheckBox jAuxFeuer3;
    private javax.swing.JCheckBox jAuxFeuer4;
    private javax.swing.JCheckBox jAuxFeuer5;
    private javax.swing.JCheckBox jAuxFeuer6;
    private javax.swing.JCheckBox jAuxFeuerf;
    private javax.swing.JCheckBox jAuxFeuerr;
    private javax.swing.JCheckBox jAuxInv1;
    private javax.swing.JCheckBox jAuxInv2;
    private javax.swing.JCheckBox jAuxInv3;
    private javax.swing.JCheckBox jAuxInv4;
    private javax.swing.JCheckBox jAuxInv5;
    private javax.swing.JCheckBox jAuxInv6;
    private javax.swing.JCheckBox jAuxInvf;
    private javax.swing.JCheckBox jAuxInvr;
    private javax.swing.JCheckBox jAuxKick1;
    private javax.swing.JCheckBox jAuxKick2;
    private javax.swing.JCheckBox jAuxKick3;
    private javax.swing.JCheckBox jAuxKick4;
    private javax.swing.JCheckBox jAuxKick5;
    private javax.swing.JCheckBox jAuxKick6;
    private javax.swing.JCheckBox jAuxKickf;
    private javax.swing.JCheckBox jAuxKickr;
    private javax.swing.JCheckBox jAuxSpSch1;
    private javax.swing.JCheckBox jAuxSpSch2;
    private javax.swing.JCheckBox jAuxSpSch3;
    private javax.swing.JCheckBox jAuxSpSch4;
    private javax.swing.JCheckBox jAuxSpSch5;
    private javax.swing.JCheckBox jAuxSpSch6;
    private javax.swing.JCheckBox jAuxSpSchf;
    private javax.swing.JCheckBox jAuxSpSchr;
    private javax.swing.JLabel jBild;
    private javax.swing.JLabel jBild1;
    private javax.swing.JTextField jBlinkfrequenz1;
    private javax.swing.JTextField jBlinkfrequenz2;
    private javax.swing.JTextField jBlinkfrequenzf;
    private javax.swing.JTextField jBlinkfrequenzr;
    private javax.swing.JCheckBox jBroadCasst;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jCV29;
    private javax.swing.JComboBox jCV_Anzeige;
    private javax.swing.JTextField jCV_Inhalt;
    private javax.swing.JButton jCV_LesenSchreiben;
    private javax.swing.JCheckBox jChannel2;
    private javax.swing.JTextArea jComment;
    private javax.swing.JTextField jConsAdr;
    private javax.swing.JCheckBox jConsF0;
    private javax.swing.JCheckBox jConsF1;
    private javax.swing.JCheckBox jConsF10;
    private javax.swing.JCheckBox jConsF11;
    private javax.swing.JCheckBox jConsF12;
    private javax.swing.JCheckBox jConsF2;
    private javax.swing.JCheckBox jConsF3;
    private javax.swing.JCheckBox jConsF4;
    private javax.swing.JCheckBox jConsF5;
    private javax.swing.JCheckBox jConsF6;
    private javax.swing.JCheckBox jConsF7;
    private javax.swing.JCheckBox jConsF8;
    private javax.swing.JCheckBox jConsF9;
    private javax.swing.JTextField jDecoderAdresse;
    private javax.swing.JTextField jDecoderAdresse1;
    private javax.swing.JTabbedPane jDecodereigenschaften;
    private javax.swing.JTextField jDimmPeriode;
    private javax.swing.JTextField jDimmen1;
    private javax.swing.JTextField jDimmen2;
    private javax.swing.JTextField jDimmen3;
    private javax.swing.JTextField jDimmen4;
    private javax.swing.JTextField jDimmen5;
    private javax.swing.JTextField jDimmen6;
    private javax.swing.JTextField jDimmenf;
    private javax.swing.JTextField jDimmenr;
    private javax.swing.JToggleButton jDirekteingabe;
    private javax.swing.JPanel jEffekte_1;
    private javax.swing.JPanel jEffekte_2;
    private javax.swing.JPanel jEffekte_3;
    private javax.swing.JCheckBox jF0;
    private javax.swing.JCheckBox jF1;
    private javax.swing.JCheckBox jF10;
    private javax.swing.JCheckBox jF10_1;
    private javax.swing.JCheckBox jF10_2;
    private javax.swing.JCheckBox jF10_3;
    private javax.swing.JCheckBox jF10_4;
    private javax.swing.JCheckBox jF10_5;
    private javax.swing.JCheckBox jF10_6;
    private javax.swing.JCheckBox jF10_7;
    private javax.swing.JCheckBox jF10_8;
    private javax.swing.JCheckBox jF10_ABV;
    private javax.swing.JTextField jF10_Fkt;
    private javax.swing.JCheckBox jF10_RG;
    private javax.swing.JCheckBox jF10_Stop;
    private javax.swing.JCheckBox jF10_USV;
    private javax.swing.JCheckBox jF11;
    private javax.swing.JCheckBox jF11_1;
    private javax.swing.JCheckBox jF11_2;
    private javax.swing.JCheckBox jF11_3;
    private javax.swing.JCheckBox jF11_4;
    private javax.swing.JCheckBox jF11_5;
    private javax.swing.JCheckBox jF11_6;
    private javax.swing.JCheckBox jF11_7;
    private javax.swing.JCheckBox jF11_8;
    private javax.swing.JCheckBox jF11_ABV;
    private javax.swing.JTextField jF11_Fkt;
    private javax.swing.JCheckBox jF11_RG;
    private javax.swing.JCheckBox jF11_Stop;
    private javax.swing.JCheckBox jF11_USV;
    private javax.swing.JCheckBox jF12;
    private javax.swing.JCheckBox jF12_1;
    private javax.swing.JCheckBox jF12_2;
    private javax.swing.JCheckBox jF12_3;
    private javax.swing.JCheckBox jF12_4;
    private javax.swing.JCheckBox jF12_5;
    private javax.swing.JCheckBox jF12_6;
    private javax.swing.JCheckBox jF12_7;
    private javax.swing.JCheckBox jF12_8;
    private javax.swing.JCheckBox jF12_ABV;
    private javax.swing.JTextField jF12_Fkt;
    private javax.swing.JCheckBox jF12_RG;
    private javax.swing.JCheckBox jF12_Stop;
    private javax.swing.JCheckBox jF12_USV;
    private javax.swing.JCheckBox jF13_1;
    private javax.swing.JCheckBox jF13_2;
    private javax.swing.JCheckBox jF13_3;
    private javax.swing.JCheckBox jF13_4;
    private javax.swing.JCheckBox jF13_5;
    private javax.swing.JCheckBox jF13_6;
    private javax.swing.JCheckBox jF13_7;
    private javax.swing.JCheckBox jF13_8;
    private javax.swing.JCheckBox jF13_ABV;
    private javax.swing.JTextField jF13_Fkt;
    private javax.swing.JCheckBox jF13_RG;
    private javax.swing.JCheckBox jF13_Stop;
    private javax.swing.JCheckBox jF13_USV;
    private javax.swing.JCheckBox jF14_1;
    private javax.swing.JCheckBox jF14_2;
    private javax.swing.JCheckBox jF14_3;
    private javax.swing.JCheckBox jF14_4;
    private javax.swing.JCheckBox jF14_5;
    private javax.swing.JCheckBox jF14_6;
    private javax.swing.JCheckBox jF14_7;
    private javax.swing.JCheckBox jF14_8;
    private javax.swing.JCheckBox jF14_ABV;
    private javax.swing.JTextField jF14_Fkt;
    private javax.swing.JCheckBox jF14_RG;
    private javax.swing.JCheckBox jF14_Stop;
    private javax.swing.JCheckBox jF14_USV;
    private javax.swing.JCheckBox jF15_1;
    private javax.swing.JCheckBox jF15_2;
    private javax.swing.JCheckBox jF15_3;
    private javax.swing.JCheckBox jF15_4;
    private javax.swing.JCheckBox jF15_5;
    private javax.swing.JCheckBox jF15_6;
    private javax.swing.JCheckBox jF15_7;
    private javax.swing.JCheckBox jF15_8;
    private javax.swing.JCheckBox jF15_ABV;
    private javax.swing.JTextField jF15_Fkt;
    private javax.swing.JCheckBox jF15_RG;
    private javax.swing.JCheckBox jF15_Stop;
    private javax.swing.JCheckBox jF15_USV;
    private javax.swing.JCheckBox jF16_1;
    private javax.swing.JCheckBox jF16_2;
    private javax.swing.JCheckBox jF16_3;
    private javax.swing.JCheckBox jF16_4;
    private javax.swing.JCheckBox jF16_5;
    private javax.swing.JCheckBox jF16_6;
    private javax.swing.JCheckBox jF16_7;
    private javax.swing.JCheckBox jF16_8;
    private javax.swing.JCheckBox jF16_ABV;
    private javax.swing.JTextField jF16_Fkt;
    private javax.swing.JCheckBox jF16_RG;
    private javax.swing.JCheckBox jF16_Stop;
    private javax.swing.JCheckBox jF16_USV;
    private javax.swing.JCheckBox jF1_1;
    private javax.swing.JCheckBox jF1_2;
    private javax.swing.JCheckBox jF1_3;
    private javax.swing.JCheckBox jF1_4;
    private javax.swing.JCheckBox jF1_5;
    private javax.swing.JCheckBox jF1_6;
    private javax.swing.JCheckBox jF1_7;
    private javax.swing.JCheckBox jF1_8;
    private javax.swing.JCheckBox jF1_ABV;
    private javax.swing.JTextField jF1_Fkt;
    private javax.swing.JCheckBox jF1_RG;
    private javax.swing.JCheckBox jF1_Stop;
    private javax.swing.JCheckBox jF1_USV;
    private javax.swing.JCheckBox jF2;
    private javax.swing.JCheckBox jF2_1;
    private javax.swing.JCheckBox jF2_2;
    private javax.swing.JCheckBox jF2_3;
    private javax.swing.JCheckBox jF2_4;
    private javax.swing.JCheckBox jF2_5;
    private javax.swing.JCheckBox jF2_6;
    private javax.swing.JCheckBox jF2_7;
    private javax.swing.JCheckBox jF2_8;
    private javax.swing.JCheckBox jF2_ABV;
    private javax.swing.JTextField jF2_Fkt;
    private javax.swing.JCheckBox jF2_RG;
    private javax.swing.JCheckBox jF2_Stop;
    private javax.swing.JCheckBox jF2_USV;
    private javax.swing.JCheckBox jF3;
    private javax.swing.JCheckBox jF3_1;
    private javax.swing.JCheckBox jF3_2;
    private javax.swing.JCheckBox jF3_3;
    private javax.swing.JCheckBox jF3_4;
    private javax.swing.JCheckBox jF3_5;
    private javax.swing.JCheckBox jF3_6;
    private javax.swing.JCheckBox jF3_7;
    private javax.swing.JCheckBox jF3_8;
    private javax.swing.JCheckBox jF3_ABV;
    private javax.swing.JTextField jF3_Fkt;
    private javax.swing.JCheckBox jF3_RG;
    private javax.swing.JCheckBox jF3_Stop;
    private javax.swing.JCheckBox jF3_USV;
    private javax.swing.JCheckBox jF4;
    private javax.swing.JCheckBox jF4_1;
    private javax.swing.JCheckBox jF4_2;
    private javax.swing.JCheckBox jF4_3;
    private javax.swing.JCheckBox jF4_4;
    private javax.swing.JCheckBox jF4_5;
    private javax.swing.JCheckBox jF4_6;
    private javax.swing.JCheckBox jF4_7;
    private javax.swing.JCheckBox jF4_8;
    private javax.swing.JCheckBox jF4_ABV;
    private javax.swing.JTextField jF4_Fkt;
    private javax.swing.JCheckBox jF4_RG;
    private javax.swing.JCheckBox jF4_Stop;
    private javax.swing.JCheckBox jF4_USV;
    private javax.swing.JCheckBox jF5;
    private javax.swing.JCheckBox jF5_1;
    private javax.swing.JCheckBox jF5_2;
    private javax.swing.JCheckBox jF5_3;
    private javax.swing.JCheckBox jF5_4;
    private javax.swing.JCheckBox jF5_5;
    private javax.swing.JCheckBox jF5_6;
    private javax.swing.JCheckBox jF5_7;
    private javax.swing.JCheckBox jF5_8;
    private javax.swing.JCheckBox jF5_ABV;
    private javax.swing.JTextField jF5_Fkt;
    private javax.swing.JCheckBox jF5_RG;
    private javax.swing.JCheckBox jF5_Stop;
    private javax.swing.JCheckBox jF5_USV;
    private javax.swing.JCheckBox jF6;
    private javax.swing.JCheckBox jF6_1;
    private javax.swing.JCheckBox jF6_2;
    private javax.swing.JCheckBox jF6_3;
    private javax.swing.JCheckBox jF6_4;
    private javax.swing.JCheckBox jF6_5;
    private javax.swing.JCheckBox jF6_6;
    private javax.swing.JCheckBox jF6_7;
    private javax.swing.JCheckBox jF6_8;
    private javax.swing.JCheckBox jF6_ABV;
    private javax.swing.JTextField jF6_Fkt;
    private javax.swing.JCheckBox jF6_RG;
    private javax.swing.JCheckBox jF6_Stop;
    private javax.swing.JCheckBox jF6_USV;
    private javax.swing.JCheckBox jF7;
    private javax.swing.JCheckBox jF7_1;
    private javax.swing.JCheckBox jF7_2;
    private javax.swing.JCheckBox jF7_3;
    private javax.swing.JCheckBox jF7_4;
    private javax.swing.JCheckBox jF7_5;
    private javax.swing.JCheckBox jF7_6;
    private javax.swing.JCheckBox jF7_7;
    private javax.swing.JCheckBox jF7_8;
    private javax.swing.JCheckBox jF7_ABV;
    private javax.swing.JTextField jF7_Fkt;
    private javax.swing.JCheckBox jF7_RG;
    private javax.swing.JCheckBox jF7_Stop;
    private javax.swing.JCheckBox jF7_USV;
    private javax.swing.JCheckBox jF8;
    private javax.swing.JCheckBox jF8_1;
    private javax.swing.JCheckBox jF8_2;
    private javax.swing.JCheckBox jF8_3;
    private javax.swing.JCheckBox jF8_4;
    private javax.swing.JCheckBox jF8_5;
    private javax.swing.JCheckBox jF8_6;
    private javax.swing.JCheckBox jF8_7;
    private javax.swing.JCheckBox jF8_8;
    private javax.swing.JCheckBox jF8_ABV;
    private javax.swing.JTextField jF8_Fkt;
    private javax.swing.JCheckBox jF8_RG;
    private javax.swing.JCheckBox jF8_Stop;
    private javax.swing.JCheckBox jF8_USV;
    private javax.swing.JCheckBox jF9;
    private javax.swing.JCheckBox jF9_1;
    private javax.swing.JCheckBox jF9_2;
    private javax.swing.JCheckBox jF9_3;
    private javax.swing.JCheckBox jF9_4;
    private javax.swing.JCheckBox jF9_5;
    private javax.swing.JCheckBox jF9_6;
    private javax.swing.JCheckBox jF9_7;
    private javax.swing.JCheckBox jF9_8;
    private javax.swing.JCheckBox jF9_ABV;
    private javax.swing.JTextField jF9_Fkt;
    private javax.swing.JCheckBox jF9_RG;
    private javax.swing.JCheckBox jF9_Stop;
    private javax.swing.JCheckBox jF9_USV;
    private javax.swing.JCheckBox jFS;
    private javax.swing.JTextField jFahrStWalzer;
    private javax.swing.JPanel jFunctionMapping;
    private javax.swing.JCheckBox jIn1Func0;
    private javax.swing.JCheckBox jIn1Func1;
    private javax.swing.JCheckBox jIn1Func10;
    private javax.swing.JCheckBox jIn1Func11;
    private javax.swing.JCheckBox jIn1Func12;
    private javax.swing.JCheckBox jIn1Func13;
    private javax.swing.JCheckBox jIn1Func14;
    private javax.swing.JCheckBox jIn1Func15;
    private javax.swing.JCheckBox jIn1Func2;
    private javax.swing.JCheckBox jIn1Func3;
    private javax.swing.JCheckBox jIn1Func4;
    private javax.swing.JCheckBox jIn1Func5;
    private javax.swing.JCheckBox jIn1Func6;
    private javax.swing.JCheckBox jIn1Func7;
    private javax.swing.JCheckBox jIn1Func8;
    private javax.swing.JCheckBox jIn1Func9;
    private javax.swing.JCheckBox jIn2Func0;
    private javax.swing.JCheckBox jIn2Func1;
    private javax.swing.JCheckBox jIn2Func10;
    private javax.swing.JCheckBox jIn2Func11;
    private javax.swing.JCheckBox jIn2Func12;
    private javax.swing.JCheckBox jIn2Func13;
    private javax.swing.JCheckBox jIn2Func14;
    private javax.swing.JCheckBox jIn2Func15;
    private javax.swing.JCheckBox jIn2Func2;
    private javax.swing.JCheckBox jIn2Func3;
    private javax.swing.JCheckBox jIn2Func4;
    private javax.swing.JCheckBox jIn2Func5;
    private javax.swing.JCheckBox jIn2Func6;
    private javax.swing.JCheckBox jIn2Func7;
    private javax.swing.JCheckBox jIn2Func8;
    private javax.swing.JCheckBox jIn2Func9;
    private javax.swing.JTextField jKD;
    private javax.swing.JTextField jKI;
    private javax.swing.JTextField jKP;
    private javax.swing.JTextField jKickZeit;
    private javax.swing.JPanel jKommentar;
    private javax.swing.JRadioButton jKurzeAdr;
    private javax.swing.JLabel jLabABVaus;
    private javax.swing.JLabel jLabF1;
    private javax.swing.JLabel jLabF10;
    private javax.swing.JLabel jLabF11;
    private javax.swing.JLabel jLabF12;
    private javax.swing.JLabel jLabF13;
    private javax.swing.JLabel jLabF14;
    private javax.swing.JLabel jLabF15;
    private javax.swing.JLabel jLabF16;
    private javax.swing.JLabel jLabF2;
    private javax.swing.JLabel jLabF3;
    private javax.swing.JLabel jLabF4;
    private javax.swing.JLabel jLabF5;
    private javax.swing.JLabel jLabF6;
    private javax.swing.JLabel jLabF7;
    private javax.swing.JLabel jLabF8;
    private javax.swing.JLabel jLabF9;
    private javax.swing.JLabel jLabFkt;
    private javax.swing.JLabel jLabFunktionen;
    private javax.swing.JLabel jLabRGan;
    private javax.swing.JLabel jLabStop;
    private javax.swing.JLabel jLabUSV;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel120;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JLabel jLabel122;
    private javax.swing.JLabel jLabel123;
    private javax.swing.JLabel jLabel124;
    private javax.swing.JLabel jLabel125;
    private javax.swing.JLabel jLabel127;
    private javax.swing.JLabel jLabel129;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JLabel jLabel148;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel150;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel177;
    private javax.swing.JLabel jLabel178;
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
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel206;
    private javax.swing.JLabel jLabel208;
    private javax.swing.JLabel jLabel238;
    private javax.swing.JLabel jLabel239;
    private javax.swing.JLabel jLabel240;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
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
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JCheckBox jLastregEin;
    private javax.swing.JCheckBox jLongAddr;
    private javax.swing.JCheckBox jLongAddr1;
    private javax.swing.JCheckBox jLongAddr2;
    private javax.swing.JTextField jMM_Addr_2;
    private javax.swing.JLabel jManID;
    private javax.swing.JTextField jMindestSchlt1;
    private javax.swing.JTextField jMindestSchlt2;
    private javax.swing.JButton jOpen;
    private javax.swing.JTextField jPacketTimeOut;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JCheckBox jRCplus;
    private javax.swing.JCheckBox jRailCom;
    private javax.swing.JCheckBox jRichtung;
    private javax.swing.JButton jSave;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> jSelektFunktionen;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTextField jUeberlastDauer;
    private javax.swing.JTextField jUeberlastSchwelle;
    private javax.swing.JTextField jUmschaltEmpf;
    private javax.swing.JTextField jVSchalt;
    private javax.swing.JLabel jVersion;
    private javax.swing.JTextField jVerstaerk;
    private javax.swing.JRadioButton jlangeAdr;
    // End of variables declaration//GEN-END:variables

}
