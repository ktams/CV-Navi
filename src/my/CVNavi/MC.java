/*
 * MC.java
 *
 * Created on 07.03.2009, 17:53:15
 *
 * @author Kersten Tams Copyright 2009-2020
 * @author Lothar Roth  Copyright 2012-2020
 *
 */

package my.CVNavi;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.Timer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import my.CVNavi.CVNavi.MZ;
import static my.CVNavi.CVNavi.byteArrayToHex;
import static my.CVNavi.CVNavi.debugDummyData;
import static my.CVNavi.CVNavi.debugLevel;
import static my.CVNavi.CVNavi.rs232_or_rb_usb_2;


/**
 *
 */
public class MC extends javax.swing.JFrame {
    public CVNavi CVNavi = null;
    public String ReturnString = "Tams Elektronik";
    private TwoWaySerialComm Com = null;
    private int gsBaudRateSaved = 0;
    private boolean bReadStatus;
    private boolean bReadCfg;
    private boolean bReadRC;
    private boolean bReadSo999 = false;
    private boolean bWriteSo999 = false;
    private boolean bReadS88num = false;
    private boolean bReadS88value = false;
    private boolean bWriteCfg;
    private boolean bWriteM3sid;
    private boolean bWriteM3sidList;
    private boolean bReadPTdirekt;
    private boolean bReadPTList;
    private boolean bUpdate;
    private int TimeOut;
    private int locListWriteErrors = 0;
    private int BlockNr = 0;
    private int MaxBlocks = 0;
    private int UpdateData[][] = new int[2][0x10000];
    private int count = 0;
    private int nextWriteJob = 0;
    private int readWriteProgress = 0;
    private Timer timer = null;
    private boolean bFalscheEingabe = false;
    private int FehlerArt = 0;
    private int rcValue = -1;
    private int so999Value = -1;
    private boolean bWriteList;
    private boolean bProg_m3;
    private boolean bProg_MM;
    private boolean bMustAskStatus;
    private int DisplayCursor;
    private int DisplayState;
    private String OldDisplayString;
    private boolean bAskLokState;
    private int AskedLokAdr;
    private String AktLokState;
    private String AktLokFormat;
    private String LokName;
    private int Fahrstufen;
    private int[] Funktionen;
    private int AktLokAdr;
    private Color DefBackground;
    private boolean bReadDevices;
    public boolean bconnectedDevicesIsActiv = false;
    public boolean bFunktionsIconsIsActiv = false;
    private enum Parser { INIT, INFO, LOCO, TRAKTIONS, FUNCMAPS, ACCFMT, SYSTEM, END };
    private int sysIdx = 0;
    private int locIdx = 0;
    private int traIdx = 0;
    private int magIdx = 0;
    private int locosConfigured = 0;
    private int locoTableSelRow = -1;
    private int locoTableSelCol = -1;
    private int WriteLokInDB = 0;
    private int LokNummerFuerDB = 0;
    private Boolean checkM3uidValidActive = false;
    private M3_Liste M3L = null;
    public S88monitor S88mon = null;

    public String M3liste[][] = null;
    public String FuncIcons[] = null;
    public int M3used = 0;
    public Boolean M3changed = false;

    public int modulNr = 1;
    public int moduleValue = 0;

    private boolean bWaitAnswerInProgress = false;
    private byte[] bArray = new byte[0xFFFF];
    private int bytesRead = 0;
    private int retries;
    private byte[] bArrayTmp = new byte[0xFFFF];
    private int tmpBytesRead = 0;
    private String lastCmd = "";
    private boolean validMcData = false;
    private ResourceBundle bundle;

    public JDialog frameInstanceHelpLC = null;
    public JDialog frameInstanceHelpHC = null;
    public JDialog frameInstanceHelpPC = null;
    public JDialog frameInstanceHelpSNC = null;
    public JDialog frameInstanceHelpXNC = null;
    public JDialog frameInstanceHelpWIW = null;
    public JDialog frameInstanceHelpMC = null;
    
    private ConnectedDevices connectedDevices = null;



    private String Hersteller(int data) {
        String file_name = "/manID.csv";
        Class c = getClass();
        InputStream is = c.getResourceAsStream (file_name);
        String s = null;
        String[] strArr1 = null;
        int bytesRead = 0;
        if( is != null)
        {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF8"));
                char[] ac = null;
                s = br.readLine() + "\n";
                String s1 = "1";
                while(s1 != null)
                {
                    s1 = br.readLine();
                    s +=  s1 + "\n";
                }
                br.close();
                strArr1 = s.substring(0, s.length()).split("\n"); //
            } catch (UnsupportedEncodingException ex) {
                System.out.println("Error reading File ManID.csv");
            } catch (IOException ex) {
                System.out.println("Error reading File ManID.csv");
            }
        }
        String ManIDstr = "" + data;
        if(s.length() != 0)
        {
            int i;
            for(i = 0; i < strArr1.length; i++)
            {
                if(strArr1[i].contains("" + data + ","))
                {
                    ManIDstr = strArr1[i];
                    strArr1 = ManIDstr.substring(0, ManIDstr.length()).split(",");
                    ManIDstr = strArr1[0] + " [" + strArr1[1] + "]";
                    break;
                }
            }
        }
        return ManIDstr;
    }


    /** Creates new form MC */
    public MC(CVNavi cvnaviThis) {
        this.LokName = "";
        this.Fahrstufen = 28;
        if( cvnaviThis == null ) {
            return;
        }
        CVNavi = cvnaviThis;
        if( CVNavi.frameInstanceDEVICE != null ) {
            CVNavi.frameInstanceDEVICE.toFront();
            CVNavi.frameInstanceDEVICE.repaint();
            return;
        }
        retries = CVNavi.timerRetries;

        initComponents();

        if( CVNavi.getZentrale() == c.cuMasterControl2 ) {
            if( debugLevel >= 0 ) {
                System.out.println( "LRLRLR MC started with cuMasterControl2 " );
            }
            // jKonfSchreiben.setEnabled(false);

            // remove update tab
            jTabbedPane1.removeTabAt( 5 );
            // remove acessory tab
            jTabbedPane1.removeTabAt( 3 );
        }

        if( debugLevel >= 2 ) {
            System.out.println("jTableLoco A1 MAX_LOCS="+c.MAX_LOCS+" rows="+this.jTableLoco.getRowCount());
        }
        /* extend jTableLoco if necessary */
        /* TODO: make list dynamic like jTableM3 */
        if( this.jTableLoco.getRowCount() < c.MAX_LOCS ) {
            if( debugLevel >= 1 ) {
                System.out.println("jTableLoco B1 MAX_LOCS="+c.MAX_LOCS+" rows="+this.jTableLoco.getRowCount());
            }
            TableModel tm = jTableLoco.getModel();
            DefaultTableModel model=(DefaultTableModel) tm;
            Object[] os = { "", "", "",""};
            for( int i = this.jTableLoco.getRowCount(); i < c.MAX_LOCS ; i++ ) {
                model.addRow(os);
            }
            if( debugLevel >= 1 ) {
                System.out.println("jTableLoco B2 MAX_LOCS="+c.MAX_LOCS+" rows="+this.jTableLoco.getRowCount());
            }
        }
        if( debugLevel >= 2 ) {
            System.out.println("jTableLoco A2 MAX_LOCS="+c.MAX_LOCS+" rows="+this.jTableLoco.getRowCount());
        }

        AktLokState="";
        Funktionen = new int[33];
        for(int i = 0; i < 33; i++)
            Funktionen[i] = 0;
        ImageIcon II = null;
        ImageIcon II2 = null;
        if( CVNavi.getZentrale() == c.cuMasterControl2 ) {
            II2 = new ImageIcon(getClass().getResource("/MC2_Foto.jpg"));
            jLabelBaudrate.setVisible(false);
            jBaud.setVisible(false);
            jLabel14.setVisible(false);
        } else {
            II = new ImageIcon(getClass().getResource("/MasterControl.gif"));
            II2 = new ImageIcon(getClass().getResource("/RedBox.gif")); // replace with final version
        }
        setTitle( CVNavi.getMenutext( decoderList.MC ).trim() );
        bundle = java.util.ResourceBundle.getBundle("my.CVNavi/Bundle");
        if( II != null ) {
            setIconImage(II.getImage());
            jBild.setIcon(II);
        }
        if( II2 != null ) {
            setIconImage(II2.getImage());
            jBild2.setIcon(II2);
        }

        jDatenQuelle.setText(CVNavi.gsSchnittstelle);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        // nur Spalte 0
        // jTableAccessory.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        // alle Spalten -> ganze Tabelle
        jTableLoco.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        jTableLoco.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
        jTableLoco.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
        jTableTraction.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        jTableTraction.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
        jTableAccessory.setDefaultRenderer(String.class, centerRenderer);

        M3liste = new String[3][c.MAX_M3_ENTRIES];

        Comparator<String> compAdrTra = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2)
            {
                String s1 = ""+o1;
                String s2 = ""+o2;
                if( ( s1.length() == 0 ) && ( s2.length() == 0 ) ) {
                    return 0;
                }
                boolean ascending = jTableTraction.getRowSorter().getSortKeys().get(0).getSortOrder() == SortOrder.ASCENDING;
                if( debugLevel > 2 ) {
                    System.out.println("jTableTraction rows="+jTableTraction.getRowCount()+" s1="+s1+" s2="+s2+" ascending="+ascending);
                }
                if( s1.length() == 0 ) {
                    if( ascending == true ) {
                        return 1;
                    } else {
                        return -1;
                    }
                }
                if( s2.length() == 0 ) {
                    if( ascending == true ) {
                        return -1;
                    } else {
                        return 1;
                    }
                }
                if( s1.endsWith("!") ) {
                    s1 = s1.replaceAll("!", "");
                }
                if( s2.endsWith("!") ) {
                    s2 = s2.replaceAll("!", "");
                }
                int n1 = CVNavi.checkAndGetStrNumRangeDef( null, s1, 1, c.MAX_M3_SID, 0, false);
                int n2 = CVNavi.checkAndGetStrNumRangeDef( null, s2, 1, c.MAX_M3_SID, 0, false);
                return n1 - n2 ;
            }
        };

        // Sortierung in der Loco-Tabelle anpassen...
        TableRowSorter<DefaultTableModel> rowSorter = (TableRowSorter<DefaultTableModel>)jTableLoco.getRowSorter();
        // Spalte 3 (Name/Beschreibung) bitte nicht sortieren...
        rowSorter.setSortable(3, false);
        // ...die anderen Spalten nach speziellen Regeln...
        rowSorter.setComparator(0, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2)
            {
                String s1 = ""+o1;
                String s2 = ""+o2;
                if( ( s1.length() == 0 ) && ( s2.length() == 0 ) ) {
                    return 0;
                }
                boolean ascending = jTableLoco.getRowSorter().getSortKeys().get(0).getSortOrder() == SortOrder.ASCENDING;
                if( debugLevel > 2 ) {
                    System.out.println("jTableLoco rows="+jTableLoco.getRowCount()+" s1="+s1+" s2="+s2+" ascending="+ascending);
                }
                if( s1.length() == 0 ) {
                    if( ascending == true ) {
                        return 1;
                    } else {
                        return -1;
                    }
                }
                if( s2.length() == 0 ) {
                    if( ascending == true ) {
                        return -1;
                    } else {
                        return 1;
                    }
                }
                int n1 = CVNavi.checkAndGetStrNumRangeDef( null, o1, 1, c.MAX_M3_SID, 0, false);
                int n2 = CVNavi.checkAndGetStrNumRangeDef( null, o2, 1, c.MAX_M3_SID, 0, false);
                return n1 - n2 ;
            }
        });
        rowSorter.setComparator(1, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2)
            {
                String s1 = ""+o1;
                String s2 = ""+o2;
                if( ( s1.length() == 0 ) && ( s2.length() == 0 ) ) {
                    return 0;
                }
                boolean ascending = jTableLoco.getRowSorter().getSortKeys().get(0).getSortOrder() == SortOrder.ASCENDING;
                if( s1.length() == 0 ) {
                    if( ascending == true ) {
                        return 1;
                    } else {
                        return -1;
                    }
                }
                if( s2.length() == 0 ) {
                    if( ascending == true ) {
                        return -1;
                    } else {
                        return 1;
                    }
                }
                return o1.compareTo(o2);
            }
        });
        rowSorter.setComparator(2, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2)
            {
                String s1 = ""+o1;
                String s2 = ""+o2;
                if( ( s1.length() == 0 ) && ( s2.length() == 0 ) ) {
                    return 0;
                }
                boolean ascending = jTableLoco.getRowSorter().getSortKeys().get(0).getSortOrder() == SortOrder.ASCENDING;
                if( s1.length() == 0 ) {
                    if( ascending == true ) {
                        return 1;
                    } else {
                        return -1;
                    }
                }
                if( s2.length() == 0 ) {
                    if( ascending == true ) {
                        return -1;
                    } else {
                        return 1;
                    }
                }
                // Vergleiche Fahrstufen numerisch ( hier 27 ohne [aAbB] )
                int n1 = CVNavi.checkAndGetStrNumRangeDef( null, s1.replaceAll("[aAbB]", ""), 0, Integer.MAX_VALUE, 0, false);
                int n2 = CVNavi.checkAndGetStrNumRangeDef( null, s2.replaceAll("[aAbB]", ""), 0, Integer.MAX_VALUE, 0, false);
                return n1 - n2;
            }
        });

        // Sortierung in der Traktions-Tabelle anpassen...
        rowSorter = (TableRowSorter<DefaultTableModel>)jTableTraction.getRowSorter();
        // Spalten 1+3 (Name/Beschreibung) bitte nicht sortieren...
        rowSorter.setSortable(1, false);
        rowSorter.setSortable(3, false);
        // ...die anderen Spalten nach speziellen Regeln...
        rowSorter.setComparator(0, compAdrTra);
        rowSorter.setComparator(2, compAdrTra);

        // initialize M3 list if file is available
        readM3();

        // set to top to avoid scrolling to bottom
        this.jTextPane1.setCaretPosition(0);

        setLocationRelativeTo(cvnaviThis);
        setVisible(true);
        CVNavi.frameInstanceDEVICE = this;
        // special Debug-Section
        if( debugDummyData > 0 ) {
            String s;
            switch( debugDummyData ) {
                case 11:
                    s = "0x" + "000000aa";
                    break;
                case 12:
                    s = "0x" + "000000Aa";
                    break;
                case 13:
                    s = "0x" + "000000Aa";
                    break;
                default:
                    s = "";
            }
            jm3GotoList.setEnabled(true);
            jmfxUID.setText(s); // debug entry without forcing lowercase
        }

    }

    private int crc16(int s, int count,int[] data) {
        int i, j = 0;

        while (--count >= 0) {
            s = s ^ data[j++];
            for (i = 0; i < 8; i++) {
                if ((s & 1) > 0) {
                    s = (s >> 1) ^ 0xA001;
                }
                else {
                    s = s >> 1;
                }
            }
        }
        return s;
    }

    private int hexval(byte c, byte c0) {
        int val = 0;

        if (c >= '0' && c <= '9') {
            val = (c - '0');
        }
        switch(c)
        {
            case 'a':
            case 'A':
                val =10;
                break;

            case 'b':
            case 'B':
                val = 11;
                break;

            case 'c':
            case 'C':
                val = 12;
                break;

            case 'd':
            case 'D':
                val = 13;
                break;

            case 'e':
            case 'E':
                val = 14;
                break;

            case 'f':
            case 'F':
                val = 15;
                break;
        }
        if (c0 == '0') {
            return (val*16);
        }

        val *= 16;

        if (c0 >= '0' && c0 <= '9') {
            val += (c0 - '0');
        }
        switch(c0)
        {
            case 'a':
            case 'A':
                val += 10;
                break;

            case 'b':
            case 'B':
                val += 11;
                break;

            case 'c':
            case 'C':
                val += 12;
                break;

            case 'd':
            case 'D':
                val += 13;
                break;

            case 'e':
            case 'E':
                val += 14;
                break;

            case 'f':
            case 'F':
                val += 15;
                break;
        }
        return val;
    }

    private String sModAdr( int mod ) {
        if( mod <= 0 ) {
            return "";
        }
        String s = ""+mod+" ("+(((mod-1)*4)+1)+"-"+(((mod-1)*4)+4)+")";
        return s;
    }

    private boolean parseInputArray( String quelle, String str1 ) {
        boolean ret = true;
        boolean notifyTooManyLocos = false;
        boolean notifyTooManyTractions = false;

        if( str1 != null && str1.length() > 0 ) {
            int locTabIdx = 0;
            int traTabIdx = 0;
            String str = "";
            rcValue = -1;
            updateRailComCheckboxes( 0 );
            so999Value = -1;
            updateSO999checkboxes( 0 );
            initLocoTable();
            initTractionTable();
            initAccessoryTable();
            String[] strArr = str1.split("[\r\n]+"); // file \r\n MC \r
            Parser mode = Parser.INIT ;
            jDatenQuelle.setText(quelle);
            jDatenQuelle.setForeground(Color.BLACK);

            jMcRwProgress.setMaximum(strArr.length);
            jMcRwProgress.setValue(0);
            jMcRwInfo.setText("parse config in progress");

            for(int j = 0 ; j < strArr.length ; j++) {
                jMcRwProgress.setValue(j+1);
                String[] strArr1 = strArr[j].split(" ");
                try {
                    // special handling of "]" needed, because prompt from MC has no correct string termination !
                    if( strArr[j].startsWith("]")) {
                        if( debugLevel > 0 ) {
                            System.out.println("inside parseInputArray SKIP BRACKET j["+j+"] strArr1[0] length="+strArr1[0].length()+"");
                        }
                        mode = Parser.END;
                        continue;
                    }

                    // check for section change -> keep section in variable "mode"
                    switch( strArr1[0] ) {
                        case "[INFO]":
                            mode = Parser.INFO;
                            continue;
                        case "[LOCO]":
                            mode = Parser.LOCO;
                            continue;
                        case "[TRAKTIONS]":
                            mode = Parser.TRAKTIONS;
                            continue;
                        case "[FUNCMAPS]":
                            mode = Parser.FUNCMAPS;
                            continue;
                        case "[ACCFMT]":
                            mode = Parser.ACCFMT;
                            continue;
                        case "[SYSTEM]":
                            mode = Parser.SYSTEM;
                            continue;
                        case "*END*":
                            mode = Parser.END;
                            if( debugLevel > 0 ) {
                                System.out.println("inside parseInputArray search section found *END* SKIP j["+j+"] strArr[j]=["+strArr[j]+"]" );
                            }
                            continue;
                        default:
                            // a "normal" entry
                            if( debugLevel > 2 ) {
                                System.out.println("inside parseInputArray search Section -> normal entry j=["+j+"] strArr[0]=["+strArr1[0]+"]" );
                            }
                    }

                    switch( mode ) {
                        case INFO:
                            switch( strArr1[0] ) {
                                case "VERSION":
                                    jVersion.setText(strArr1[1]);
                                    break;
                                case "HARDWARE":
                                    jHardWare.setText(strArr1[1]);
                                    break;
                                case "MCU":
                                    jMCU.setText(strArr1[1]);
                                    break;
                                case "SERIAL":
                                    jSerNr.setText(strArr1[1]);
                                    break;
                                default:
                                    System.out.println("inside parseInputArray [INFO] -> UNKNOWN ENTRY j=["+j+"] strArr[j]=["+strArr[j]+"]");
                            }
                            break;
                        case LOCO:
                            // re-split with "," and " "
                            strArr1 = strArr[j].split("[, ]+");
                            if( locTabIdx >= c.MAX_LOCS) {
                                System.out.println("inside parseInputArray [LOCO] -> UNKNOWN ENTRY strArr["+j+"]=["+strArr[j]+"]");
                                System.out.println("inside parseInputArray [LOCO] locTabIdx=["+locTabIdx+"] out of bound -> skip/continue");
                                jMcRwInfo.setText("parse config: too many locos");
                                if( notifyTooManyLocos == false ) {
                                    notifyTooManyLocos = true;
                                    CVNavi.mbRWCancelled(this);
                                }
                                ret = false;
                                continue;
                            }
                            if (strArr1.length >= 3) {
                                // 1st check if address is already known, if not x == locTabIdx
                                int thisAdrIdx = 0;
                                for( ; thisAdrIdx < locTabIdx ; thisAdrIdx++) {
                                    String s = ""+jTableLoco.getValueAt(thisAdrIdx, 0);
                                    if(s.trim().equals(strArr1[0].trim()) ) {
                                        // address already present -> overwrite
                                        break;
                                    }
                                }
                                jTableLoco.setValueAt(strArr1[0], thisAdrIdx, 0);
                                jTableLoco.setValueAt(strArr1[1], thisAdrIdx, 2);
                                jTableLoco.setValueAt(strArr1[2], thisAdrIdx, 1);
                                if (strArr1.length >= 4) {
                                    String name = "";
                                    for( int i = 3 ; i < strArr1.length ; i++) {
                                        name += strArr1[i];
                                        if( (i+1) < strArr1.length ) {
                                            name += ",";
                                        }
                                    }
                                    jTableLoco.setValueAt(name, thisAdrIdx, 3);
                                }
                                // LRLRLR TODO New Parser for name. It may contain commas or blanks
                                /*
                                    [LOCO]
                                    1, 28, DCC, A,9.,#/B
                                    2, 14, DCC, 12345678901
                                    3, 126, DCC, äöü
                                */
                                // name starts at location of 3rd comma
                                if (strArr1.length >= 4) {
                                    String s = strArr[j];
                                    int idxComma = s.indexOf(",", 0);
                                    idxComma = s.indexOf(",", idxComma+1);
                                    idxComma = s.indexOf(",", idxComma+1);
                                    String name = s.substring(idxComma+1);
                                    jTableLoco.setValueAt(name.trim(), thisAdrIdx, 3);
                                    /*
                                    // re-split with "," to get real name (may be with more than 1 consecutive space inside !)
                                    String[] strArrName = strArr[j].split(",");
                                    if( strArrName.length == 4 ) {
                                        jTableLoco.setValueAt(strArrName[3].trim(), thisAdrIdx, 3);
                                    }
                                    */
                                }
                                if( thisAdrIdx == locTabIdx ) {
                                    // neuer Eintrag erstellt -> Zaehler erhöhen
                                    locTabIdx++;
                                }
                            }
                            break;
                        case TRAKTIONS:
                            // re-split with "," and " "
                            strArr1 = strArr[j].split("[, ]+");
                            if( traTabIdx >= c.MAX_TRACTIONS) {
                                System.out.println("inside parseInputArray [TRAKTIONS] -> UNKNOWN ENTRY strArr["+j+"]=["+strArr[j]+"]");
                                System.out.println("inside parseInputArray [TRAKTIONS] traction["+traTabIdx+"] out of bound -> skip/continue" );
                                jMcRwInfo.setText("parse config: too many tractions");
                                if( notifyTooManyTractions == false ) {
                                    notifyTooManyTractions = true;
                                    CVNavi.mbRWCancelled(this);
                                }
                                ret = false;
                                continue;
                            }
                            if( strArr1.length >= 2 ) {
                                jTableTraction.setValueAt(strArr1[0], traTabIdx, 0);
                                // ggf. Namen der Loks in Spalte 1 eintragen
                                String traLocAddr = strArr1[0];
                                // ggf. "!" am Ende löschen
                                if(traLocAddr.substring(traLocAddr.length()-1).matches("!")) {
                                    traLocAddr = traLocAddr.substring(0, traLocAddr.length()-1);
                                }
                                // suche nach passender Adresse in der Loktabelle
                                for(int i = 0; i < locTabIdx; i++) {
                                    if(traLocAddr.matches(""+jTableLoco.getValueAt(i, 0)))
                                        jTableTraction.setValueAt(jTableLoco.getValueAt(i, 3), traTabIdx, 1);
                                }
                                jTableTraction.setValueAt(strArr1[1], traTabIdx, 2);
                                // ggf. Namen der Loks in Spalte 3 eintragen
                                traLocAddr = strArr1[1];
                                // ggf. "!" am Ende löschen
                                if(traLocAddr.substring(traLocAddr.length()-1).matches("!")) {
                                    traLocAddr = traLocAddr.substring(0, traLocAddr.length()-1);
                                }
                                // suche nach passender Adresse in der Loktabelle
                                for(int i = 0; i < locTabIdx; i++) {
                                    if(traLocAddr.matches(""+jTableLoco.getValueAt(i, 0)))
                                        jTableTraction.setValueAt(jTableLoco.getValueAt(i, 3), traTabIdx, 3);
                                }
                                // neuer Eintrag erstellt -> Zaehler erhöhen
                                traTabIdx++;
                            }
                            break;
                        case FUNCMAPS:
                            str += strArr[j];
                            str += ";";
                            break;
                        case ACCFMT:
                            FuncIcons = str.split(";");
                            // re-split with "," and " "
                            strArr1 = strArr[j].split("[, ]+");
                            if (strArr1.length >= 2) {
                                int modNr = Integer.parseInt(strArr1[0]) + 1;
                                if( (modNr < 1) || (modNr > c.MAX_MM1_ACCMOD) ) {
                                    System.out.println("inside parseInputArray [ACCFMT] modNr["+modNr+"] out of bound -> skip/continue line["+strArr[j]+"]" );
                                    continue;
                                }
                                // jTableAccessory.setValueAt("" + modNr, modNr-1, 0); ZZZ
                                jTableAccessory.setValueAt(sModAdr(modNr), modNr-1, 0);
                                jTableAccessory.setValueAt(strArr1[1], modNr-1, 1);
                            }
                            break;
                        case SYSTEM:
                            switch( strArr1[0] ) {
                                case "LONGPAUSE":
                                    jLangePause.setSelected(strArr1[1].matches("yes"));
                                    break;
                                case "NEGATIVESHORT":
                                    jDCC_Booster.setSelected(strArr1[1].matches("yes"));
                                    break;
                                case "DEFAULTDCC":
                                    jDCC_Loks.setSelected(strArr1[1].matches("yes"));
                                    break;
                                case "SHORTTIME":
                                    int KSE;
                                    try {
                                        KSE = Integer.parseInt(strArr1[1]);
                                    } catch (NumberFormatException numberFormatException) {
                                        KSE = 20;
                                    }
                                    KSE *= 5;
                                    jKurzEmpf.setText(""+KSE);
                                    break;
                                case "s88MODULES":
                                    js88.setText(strArr1[1]);
                                    updateS88field(Integer.parseInt(js88.getText()));
                                    break;
                                case "MAGMINTIME":
                                    jMinMag.setText(strArr1[1]);
                                    break;
                                case "MAGMAXTIME":
                                    jMaxMag.setText(strArr1[1]);
                                    break;
                                case "BAUDRATE":
                                    jBaud.setSelectedItem(strArr1[1]);
                                    break;
                                case "RAILCOM":
                                    rcValue = Integer.parseInt(strArr1[1]);
                                    updateRailComCheckboxes( rcValue );
                                    break;
                                case "SO999":
                                    so999Value = Integer.parseInt(strArr1[1]);
                                    updateSO999checkboxes( so999Value );
                                    break;
                                default:
                                    System.out.println("inside parseInputArray [SYSTEM] -> UNKNOWN ENTRY j=["+j+"] strArr[0]=["+strArr1[0]+"]");
                            }
                            break;
                        case END:
                            if( strArr[0].startsWith("]") ) {
                                break;
                            } else {
                                switch( strArr1[0] ) {
                                    case "*END*":
                                        break;
                                    default:
                                        System.out.println("inside parseInputArray [END] -> UNKNOWN ENTRY j=["+j+"] strArr[0]=["+strArr1[0]+"]" );
                                }
                            }
                            break;
                        default:
                            System.out.println("inside parseInputArray [default] -> UNHANDLED MODE len["+strArr.length+"]");
                    }

                } catch (NumberFormatException ex) {
                    System.out.println("inside parseInputArray [SYSTEM] SRCstr["+strArr[j]+"] -> DST.len["+strArr1.length+"] Eception ex["+ex+"]");
                }

            }
            if( debugLevel > 0 ) {
                System.out.println("parseInputArray progress at end is "+jMcRwProgress.getValue() );
            }
            Font f = jDatenQuelle.getFont();
            Font f2 = new Font( f.getFontName(), Font.BOLD, f.getSize() );
            jDatenQuelle.setFont(f2);
        }

        // sort table by first column
        TableRowSorter<DefaultTableModel> sorter = (TableRowSorter<DefaultTableModel>)jTableLoco.getRowSorter();
        List<RowSorter.SortKey> sortKeys = new ArrayList<>();
        if( ( sorter != null ) && ( sortKeys != null ) ) {
            int columnIndexToSort = 0; // 0 == 1st column (loco address)
            sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));
            sorter.setSortKeys(sortKeys);
            sorter.sort();
        }
        String infoText = "parse config finished ";
        if( ret == false ) {
            infoText += "with problems";
            if( notifyTooManyLocos ) infoText += " [locos]";
            if( notifyTooManyTractions ) infoText += " [tractions]";
        } else {
            infoText += "OK";
        }
        jMcRwInfo.setText(infoText);
        return ret;
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
        jCBformat = new javax.swing.JComboBox<>();
        jCBspeed = new javax.swing.JComboBox<>();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jSystem = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jVersion = new javax.swing.JTextField();
        jHardWare = new javax.swing.JTextField();
        jMCU = new javax.swing.JTextField();
        jSerNr = new javax.swing.JTextField();
        jLangePause = new javax.swing.JCheckBox();
        jDCC_Booster = new javax.swing.JCheckBox();
        jDCC_Loks = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        jKurzEmpf = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        js88 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jMinMag = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jMaxMag = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabelBaudrate = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jKonfLesen = new javax.swing.JButton();
        jKonfSchreiben = new javax.swing.JButton();
        jKonfLaden = new javax.swing.JButton();
        jKonfSichern = new javax.swing.JButton();
        jBild = new javax.swing.JLabel();
        jMcRwProgress = new javax.swing.JProgressBar();
        jCancel = new javax.swing.JButton();
        jMcRwInfo = new javax.swing.JLabel();
        jRailcomSupport = new javax.swing.JLabel();
        jRailcomTailbits = new javax.swing.JCheckBox();
        jRailcomIdNotify = new javax.swing.JCheckBox();
        jRailcomAccessory = new javax.swing.JCheckBox();
        jWRsys = new javax.swing.JCheckBox();
        jWRloc = new javax.swing.JCheckBox();
        jWRtra = new javax.swing.JCheckBox();
        jWRmag = new javax.swing.JCheckBox();
        jDatenQuelle = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jClose = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jSysS88monitor = new javax.swing.JButton();
        jBoosterOpts = new javax.swing.JLabel();
        jBoostOptNoAccDrive = new javax.swing.JCheckBox();
        jBoostOptNoAccBreak = new javax.swing.JCheckBox();
        jBild2 = new javax.swing.JLabel();
        jMRST = new javax.swing.JButton();
        jBaud = new javax.swing.JComboBox<>();
        jconDevs = new javax.swing.JButton();
        jLoks = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableLoco = new javax.swing.JTable();
        jLocDelAll = new javax.swing.JButton();
        jLocCheck = new javax.swing.JButton();
        jLocRepair = new javax.swing.JButton();
        jLocM3sidWrite = new javax.swing.JButton();
        jTextM3UID = new javax.swing.JTextField();
        jLabelM3UID = new javax.swing.JLabel();
        jLabelM3SID = new javax.swing.JLabel();
        jTextM3SID = new javax.swing.JTextField();
        jMcM3Progress = new javax.swing.JProgressBar();
        jMcM3Info = new javax.swing.JTextField();
        jLocM3cfgLoad = new javax.swing.JButton();
        jLocM3cfgEdit = new javax.swing.JButton();
        jLocM3cfgSave = new javax.swing.JButton();
        jLocDel = new javax.swing.JButton();
        jLoc2System = new javax.swing.JButton();
        jLocM3sidWriteList = new javax.swing.JButton();
        jM3count = new javax.swing.JLabel();
        jFuncIcon = new javax.swing.JButton();
        jTraktionen = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableTraction = new javax.swing.JTable();
        jTraDelAll = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jTraCheck = new javax.swing.JButton();
        jTraRepair = new javax.swing.JButton();
        jTraDel = new javax.swing.JButton();
        jTra2System = new javax.swing.JButton();
        jMagnetartikel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableAccessory = new javax.swing.JTable();
        jMMM = new javax.swing.JButton();
        jMDCC = new javax.swing.JButton();
        jMagCheck = new javax.swing.JButton();
        jMagRepair = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jMag2System = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jCVListe = new javax.swing.JList();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jHauptGleis = new javax.swing.JRadioButton();
        jProgGleis = new javax.swing.JRadioButton();
        jListeLesen = new javax.swing.JButton();
        jListeSchreiben = new javax.swing.JButton();
        jListeBearbeiten = new javax.swing.JButton();
        jLabel28 = new javax.swing.JLabel();
        jHerstellerInfo = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jCV_Direkt = new javax.swing.JTextField();
        jWertDirekt = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jDirektLesen = new javax.swing.JButton();
        jDirektSchreiben = new javax.swing.JButton();
        jLabel31 = new javax.swing.JLabel();
        jDecAdr = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jm3Addr = new javax.swing.JTextField();
        jmfxUID = new javax.swing.JTextField();
        jm3Schreiben = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jm3GotoList = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jMMRegister = new javax.swing.JTextField();
        jMMWert = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jStartMMProg = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jGeschwindigkeit = new javax.swing.JSlider();
        jPanel7 = new javax.swing.JPanel();
        j1 = new javax.swing.JButton();
        j2 = new javax.swing.JButton();
        j3 = new javax.swing.JButton();
        j4 = new javax.swing.JButton();
        j5 = new javax.swing.JButton();
        j6 = new javax.swing.JButton();
        j7 = new javax.swing.JButton();
        j8 = new javax.swing.JButton();
        j9 = new javax.swing.JButton();
        jStern = new javax.swing.JButton();
        j0 = new javax.swing.JButton();
        jRaute = new javax.swing.JButton();
        jf0 = new javax.swing.JButton();
        jf1 = new javax.swing.JButton();
        jf2 = new javax.swing.JButton();
        jf3 = new javax.swing.JButton();
        jf4 = new javax.swing.JButton();
        jGO = new javax.swing.JButton();
        jSTOP = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jf5 = new javax.swing.JButton();
        jf9 = new javax.swing.JButton();
        jf6 = new javax.swing.JButton();
        jf8 = new javax.swing.JButton();
        jf7 = new javax.swing.JButton();
        jf10 = new javax.swing.JButton();
        jf11 = new javax.swing.JButton();
        jf12 = new javax.swing.JButton();
        jf18 = new javax.swing.JButton();
        jf13 = new javax.swing.JButton();
        jf17 = new javax.swing.JButton();
        jf14 = new javax.swing.JButton();
        jf19 = new javax.swing.JButton();
        jf15 = new javax.swing.JButton();
        jf16 = new javax.swing.JButton();
        jf20 = new javax.swing.JButton();
        jf22 = new javax.swing.JButton();
        jf21 = new javax.swing.JButton();
        jf23 = new javax.swing.JButton();
        jf24 = new javax.swing.JButton();
        jf26 = new javax.swing.JButton();
        jf25 = new javax.swing.JButton();
        jf27 = new javax.swing.JButton();
        jf28 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jDisplay = new javax.swing.JTextArea();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jRueck = new javax.swing.JButton();
        jVor = new javax.swing.JButton();
        jMC_ESC = new javax.swing.JLabel();
        jMC_MENU = new javax.swing.JLabel();
        jMC_OK = new javax.swing.JLabel();
        jUpdate = new javax.swing.JPanel();
        jUpdDatei = new javax.swing.JTextField();
        jUpdDateiAuswahl = new javax.swing.JButton();
        jMcUpdProgress = new javax.swing.JProgressBar();
        jUpdStartUpdate = new javax.swing.JButton();
        jMcUpdInfo = new javax.swing.JLabel();
        jUpdLastErrorLabel = new javax.swing.JLabel();
        jUpdLastError = new javax.swing.JLabel();
        jUpdCancel = new javax.swing.JButton();
        jUpdClose = new javax.swing.JButton();
        jUpd2System = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        helpLC = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        helpHC = new javax.swing.JButton();
        helpPC = new javax.swing.JButton();
        helpSNC = new javax.swing.JButton();
        helpXNC = new javax.swing.JButton();
        helpMC = new javax.swing.JButton();
        helpWasIstWas = new javax.swing.JButton();
        helpMCasLC = new javax.swing.JButton();
        Firmware = new javax.swing.JButton();
        jEasyNetUpdate = new javax.swing.JButton();
        jUSB1 = new javax.swing.JRadioButton();
        jUSB2 = new javax.swing.JRadioButton();
        jLabel18 = new javax.swing.JLabel();

        jCBformat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DCC", "MM1", "MM2", "m3" }));
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("my/CVNavi/Bundle"); // NOI18N
        jCBformat.setToolTipText(bundle.getString("MC.jCBformat.toolTipText")); // NOI18N

        jCBspeed.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "14", "27a", "27b", "28", "126" }));
        jCBspeed.setToolTipText(bundle.getString("MC.jCBspeed.toolTipText")); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTabbedPane1.setMaximumSize(new java.awt.Dimension(1115, 32767));
        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        jSystem.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jSystem.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jSystemComponentShown(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, bundle.getString("MC.jPanel1.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText(bundle.getString("MC.jLabel2.text")); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText(bundle.getString("MC.jLabel3.text")); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText(bundle.getString("MC.jLabel4.text")); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText(bundle.getString("MC.jLabel5.text")); // NOI18N

        jVersion.setEditable(false);
        jVersion.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jVersion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jVersion.setText(bundle.getString("MC.jVersion.text")); // NOI18N

        jHardWare.setEditable(false);
        jHardWare.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jHardWare.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jHardWare.setText(bundle.getString("MC.jHardWare.text")); // NOI18N

        jMCU.setEditable(false);
        jMCU.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMCU.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jMCU.setText(bundle.getString("MC.jMCU.text")); // NOI18N

        jSerNr.setEditable(false);
        jSerNr.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jSerNr.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jSerNr.setText(bundle.getString("MC.jSerNr.text")); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSerNr)
                    .addComponent(jMCU, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jHardWare, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jVersion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jVersion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jHardWare, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jMCU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jSerNr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLangePause.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLangePause.setText(bundle.getString("MC.jLangePause.text")); // NOI18N

        jDCC_Booster.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jDCC_Booster.setText(bundle.getString("MC.jDCC_Booster.text")); // NOI18N

        jDCC_Loks.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jDCC_Loks.setText(bundle.getString("MC.jDCC_Loks.text")); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText(bundle.getString("MC.jLabel6.text")); // NOI18N

        jKurzEmpf.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jKurzEmpf.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jKurzEmpf.setText(bundle.getString("MC.jKurzEmpf.text")); // NOI18N
        jKurzEmpf.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jKurzEmpfFocusLost(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText(bundle.getString("MC.jLabel7.text")); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText(bundle.getString("MC.jLabel8.text")); // NOI18N
        jLabel8.setToolTipText(bundle.getString("MC.jLabel17.toolTipText")); // NOI18N

        js88.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        js88.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        js88.setText(bundle.getString("MC.js88.text")); // NOI18N
        js88.setToolTipText(bundle.getString("MC.jLabel17.toolTipText")); // NOI18N
        js88.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                js88FocusLost(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText(bundle.getString("MC.jLabel9.text")); // NOI18N

        jMinMag.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMinMag.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jMinMag.setText(bundle.getString("MC.jMinMag.text")); // NOI18N
        jMinMag.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jMinMagFocusLost(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText(bundle.getString("MC.jLabel10.text")); // NOI18N

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText(bundle.getString("MC.jLabel11.text")); // NOI18N

        jMaxMag.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMaxMag.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jMaxMag.setText(bundle.getString("MC.jMaxMag.text")); // NOI18N
        jMaxMag.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jMaxMagFocusLost(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setText(bundle.getString("MC.jLabel12.text")); // NOI18N

        jLabelBaudrate.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelBaudrate.setText(bundle.getString("MC.jLabelBaudrate.text")); // NOI18N
        jLabelBaudrate.setToolTipText(bundle.getString("MC.jLabelBaudrate.toolTipText")); // NOI18N

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel14.setText(bundle.getString("MC.jLabel14.text")); // NOI18N
        jLabel14.setToolTipText(bundle.getString("MC.jLabelBaudrate.toolTipText")); // NOI18N

        jKonfLesen.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jKonfLesen.setText(bundle.getString("MC.jKonfLesen.text")); // NOI18N
        jKonfLesen.setMaximumSize(new java.awt.Dimension(220, 25));
        jKonfLesen.setMinimumSize(new java.awt.Dimension(220, 25));
        jKonfLesen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jKonfLesenActionPerformed(evt);
            }
        });

        jKonfSchreiben.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jKonfSchreiben.setText(bundle.getString("MC.jKonfSchreiben.text")); // NOI18N
        jKonfSchreiben.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jKonfSchreibenActionPerformed(evt);
            }
        });

        jKonfLaden.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jKonfLaden.setText(bundle.getString("MC.jKonfLaden.text")); // NOI18N
        jKonfLaden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jKonfLadenActionPerformed(evt);
            }
        });

        jKonfSichern.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jKonfSichern.setText(bundle.getString("MC.jKonfSichern.text")); // NOI18N
        jKonfSichern.setMaximumSize(new java.awt.Dimension(220, 25));
        jKonfSichern.setMinimumSize(new java.awt.Dimension(220, 25));
        jKonfSichern.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jKonfSichernActionPerformed(evt);
            }
        });

        jMcRwProgress.setStringPainted(true);

        jCancel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCancel.setText(bundle.getString("MC.jCancel.text")); // NOI18N
        jCancel.setMaximumSize(new java.awt.Dimension(220, 25));
        jCancel.setMinimumSize(new java.awt.Dimension(220, 25));
        jCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCancelActionPerformed(evt);
            }
        });

        jMcRwInfo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMcRwInfo.setText(bundle.getString("MC.jMcRwInfo.text")); // NOI18N
        jMcRwInfo.setToolTipText(bundle.getString("MC.jMcRwInfo.toolTipText")); // NOI18N

        jRailcomSupport.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRailcomSupport.setText(bundle.getString("MC.jRailcomSupport.text")); // NOI18N

        jRailcomTailbits.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRailcomTailbits.setText(bundle.getString("MC.jRailcomTailbits.text")); // NOI18N
        jRailcomTailbits.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRailcomTailbitsActionPerformed(evt);
            }
        });

        jRailcomIdNotify.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRailcomIdNotify.setText(bundle.getString("MC.jRailcomIdNotify.text")); // NOI18N
        jRailcomIdNotify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRailcomIdNotifyActionPerformed(evt);
            }
        });

        jRailcomAccessory.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRailcomAccessory.setText(bundle.getString("MC.jRailcomAccessory.text")); // NOI18N
        jRailcomAccessory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRailcomAccessoryActionPerformed(evt);
            }
        });

        jWRsys.setSelected(true);
        jWRsys.setText(bundle.getString("MC.jWRsys.text")); // NOI18N
        jWRsys.setToolTipText(bundle.getString("MC.jWRsys.toolTipText")); // NOI18N

        jWRloc.setSelected(true);
        jWRloc.setText(bundle.getString("MC.jWRloc.text")); // NOI18N
        jWRloc.setToolTipText(bundle.getString("MC.jWRloc.toolTipText")); // NOI18N

        jWRtra.setSelected(true);
        jWRtra.setText(bundle.getString("MC.jWRtra.text")); // NOI18N
        jWRtra.setToolTipText(bundle.getString("MC.jWRtra.toolTipText")); // NOI18N

        jWRmag.setSelected(true);
        jWRmag.setText(bundle.getString("MC.jWRmag.text")); // NOI18N
        jWRmag.setToolTipText(bundle.getString("MC.jWRmag.toolTipText")); // NOI18N

        jDatenQuelle.setEditable(false);
        jDatenQuelle.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jDatenQuelle.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jDatenQuelle.setText(bundle.getString("MC.jDatenQuelle.text")); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText(bundle.getString("MC.jLabel1.text")); // NOI18N

        jClose.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jClose.setText(bundle.getString("MC.jClose.text")); // NOI18N
        jClose.setMaximumSize(new java.awt.Dimension(220, 25));
        jClose.setMinimumSize(new java.awt.Dimension(220, 25));
        jClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCloseActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel17.setText(bundle.getString("MC.jLabel17.text")); // NOI18N
        jLabel17.setToolTipText(bundle.getString("MC.jLabel17.toolTipText")); // NOI18N

        jSysS88monitor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jSysS88monitor.setText(bundle.getString("MC.jSysS88monitor.text")); // NOI18N
        jSysS88monitor.setToolTipText(bundle.getString("MC.jSysS88monitor.toolTipText")); // NOI18N
        jSysS88monitor.setEnabled(false);
        jSysS88monitor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSysS88monitorActionPerformed(evt);
            }
        });

        jBoosterOpts.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jBoosterOpts.setText(bundle.getString("MC.jBoosterOpts.text")); // NOI18N

        jBoostOptNoAccDrive.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jBoostOptNoAccDrive.setText(bundle.getString("MC.jBoostOptNoAccDrive.text")); // NOI18N
        jBoostOptNoAccDrive.setEnabled(false);
        jBoostOptNoAccDrive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBoostOptNoAccDriveActionPerformed(evt);
            }
        });

        jBoostOptNoAccBreak.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jBoostOptNoAccBreak.setText(bundle.getString("MC.jBoostOptNoAccBreak.text")); // NOI18N
        jBoostOptNoAccBreak.setEnabled(false);
        jBoostOptNoAccBreak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBoostOptNoAccBreakActionPerformed(evt);
            }
        });

        jMRST.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jMRST.setForeground(java.awt.Color.red);
        jMRST.setText(bundle.getString("MC.jMRST.text")); // NOI18N
        jMRST.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jMRST.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMRSTActionPerformed(evt);
            }
        });

        jBaud.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "2400", "4800", "9600", "14400", "19200", "38400", "57600" }));
        jBaud.setToolTipText(bundle.getString("MC.jLabelBaudrate.toolTipText")); // NOI18N

        jconDevs.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jconDevs.setText(bundle.getString("MC.jconDevs.text")); // NOI18N
        jconDevs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jconDevsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jSystemLayout = new javax.swing.GroupLayout(jSystem);
        jSystem.setLayout(jSystemLayout);
        jSystemLayout.setHorizontalGroup(
            jSystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jSystemLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jSystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jSystemLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDatenQuelle))
                    .addComponent(jMcRwProgress, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jMcRwInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jSystemLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jClose, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jSystemLayout.createSequentialGroup()
                        .addGroup(jSystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRailcomTailbits, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jRailcomAccessory, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jRailcomIdNotify, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jSystemLayout.createSequentialGroup()
                                .addGroup(jSystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jRailcomSupport)
                                    .addGroup(jSystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jDCC_Booster)
                                        .addComponent(jLangePause)
                                        .addComponent(jDCC_Loks, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jSystemLayout.createSequentialGroup()
                                .addGroup(jSystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jBoosterOpts)
                                    .addComponent(jBoostOptNoAccDrive)
                                    .addComponent(jBoostOptNoAccBreak))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)))
                        .addGroup(jSystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jSystemLayout.createSequentialGroup()
                                .addComponent(jBild, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBild2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(jSystemLayout.createSequentialGroup()
                                .addGroup(jSystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelBaudrate)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel6))
                                .addGap(15, 15, 15)
                                .addGroup(jSystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jSystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(js88, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jMinMag, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jMaxMag, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jKurzEmpf, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jBaud, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jSystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                                .addComponent(jSysS88monitor, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(74, 74, 74)))
                        .addGroup(jSystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jMRST)
                            .addGroup(jSystemLayout.createSequentialGroup()
                                .addComponent(jWRsys)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jWRloc)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jWRtra)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jWRmag))
                            .addComponent(jCancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jKonfLesen, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jKonfSchreiben, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jKonfSichern, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jKonfLaden, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jconDevs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );

        jSystemLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jWRloc, jWRmag, jWRsys, jWRtra});

        jSystemLayout.setVerticalGroup(
            jSystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jSystemLayout.createSequentialGroup()
                .addGroup(jSystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jSystemLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jSystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jDatenQuelle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jSystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jSystemLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jSystemLayout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jMRST, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jSystemLayout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(jSystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jKurzEmpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jSystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(js88, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel17)
                            .addComponent(jSysS88monitor))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jSystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jMinMag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jSystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jMaxMag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jSystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelBaudrate)
                            .addGroup(jSystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jBaud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel14)))))
                .addGap(16, 16, 16)
                .addComponent(jconDevs)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                .addGroup(jSystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jSystemLayout.createSequentialGroup()
                        .addComponent(jKonfLesen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jSystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jSystemLayout.createSequentialGroup()
                                .addComponent(jKonfSchreiben)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jSystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jSystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jWRloc)
                                        .addComponent(jWRtra)
                                        .addComponent(jWRmag))
                                    .addComponent(jWRsys))
                                .addGap(56, 56, 56)
                                .addComponent(jKonfLaden)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jKonfSichern, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jSystemLayout.createSequentialGroup()
                                .addComponent(jBild2, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addComponent(jCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jSystemLayout.createSequentialGroup()
                        .addGroup(jSystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jBild, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jSystemLayout.createSequentialGroup()
                                .addComponent(jLangePause)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jDCC_Booster)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jDCC_Loks)
                                .addGap(23, 23, 23)
                                .addComponent(jRailcomSupport)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRailcomTailbits)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRailcomIdNotify)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRailcomAccessory)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jBoosterOpts)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBoostOptNoAccDrive)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBoostOptNoAccBreak)))
                        .addGap(4, 4, 4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jMcRwProgress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jMcRwInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jClose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jMcRwInfo.getAccessibleContext().setAccessibleName(bundle.getString("MC.jMcRwInfo.AccessibleContext.accessibleName")); // NOI18N

        jTabbedPane1.addTab(bundle.getString("MC.jSystem.TabConstraints.tabTitle"), jSystem); // NOI18N

        jLoks.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLoks.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jLoksComponentShown(evt);
            }
        });

        jTableLoco.setAutoCreateRowSorter(true);
        jTableLoco.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTableLoco.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Adresse", "Format", "Speedsteps", "Name"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTableLoco.setToolTipText(bundle.getString("MC.jTableLoco.toolTipText")); // NOI18N
        jTableLoco.setDragEnabled(true);
        jTableLoco.setIntercellSpacing(new java.awt.Dimension(10, 10));
        jTableLoco.setRowHeight(26);
        jTableLoco.getTableHeader().setReorderingAllowed(false);
        jTableLoco.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTableLocoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTableLocoFocusLost(evt);
            }
        });
        jTableLoco.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableLocoMouseClicked(evt);
            }
        });
        jTableLoco.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                jTableLocoCaretPositionChanged(evt);
            }
        });
        jTableLoco.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTableLocoPropertyChange(evt);
            }
        });
        jTableLoco.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTableLocoKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTableLoco);
        if (jTableLoco.getColumnModel().getColumnCount() > 0) {
            jTableLoco.getColumnModel().getColumn(0).setResizable(false);
            jTableLoco.getColumnModel().getColumn(0).setPreferredWidth(50);
            jTableLoco.getColumnModel().getColumn(0).setHeaderValue(bundle.getString("MC.jTableLoco.columnModel.title0")); // NOI18N
            jTableLoco.getColumnModel().getColumn(1).setResizable(false);
            jTableLoco.getColumnModel().getColumn(1).setPreferredWidth(50);
            jTableLoco.getColumnModel().getColumn(1).setHeaderValue(bundle.getString("MC.jTableLoco.columnModel.title2")); // NOI18N
            jTableLoco.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(jCBformat));
            jTableLoco.getColumnModel().getColumn(2).setResizable(false);
            jTableLoco.getColumnModel().getColumn(2).setPreferredWidth(50);
            jTableLoco.getColumnModel().getColumn(2).setHeaderValue(bundle.getString("MC.jTableLoco.columnModel.title1")); // NOI18N
            jTableLoco.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(jCBspeed));
            jTableLoco.getColumnModel().getColumn(3).setResizable(false);
            jTableLoco.getColumnModel().getColumn(3).setHeaderValue(bundle.getString("MC.jTableLoco.columnModel.title3")); // NOI18N
        }

        jLocDelAll.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLocDelAll.setText(bundle.getString("MC.jLocDelAll.text")); // NOI18N
        jLocDelAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jLocDelAllActionPerformed(evt);
            }
        });

        jLocCheck.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLocCheck.setText(bundle.getString("MC.jLocCheck.text")); // NOI18N
        jLocCheck.setToolTipText(bundle.getString("MC.jLocCheck.toolTipText")); // NOI18N
        jLocCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jLocCheckActionPerformed(evt);
            }
        });

        jLocRepair.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLocRepair.setText(bundle.getString("MC.jLocRepair.text")); // NOI18N
        jLocRepair.setToolTipText(bundle.getString("MC.jLocRepair.toolTipText")); // NOI18N
        jLocRepair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jLocRepairActionPerformed(evt);
            }
        });

        jLocM3sidWrite.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLocM3sidWrite.setText(bundle.getString("MC.jLocM3sidWrite.text")); // NOI18N
        jLocM3sidWrite.setToolTipText(bundle.getString("MC.jLocM3sidWrite.toolTipText")); // NOI18N
        jLocM3sidWrite.setEnabled(false);
        jLocM3sidWrite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jLocM3sidWriteActionPerformed(evt);
            }
        });

        jTextM3UID.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextM3UID.setText(bundle.getString("MC.jTextM3UID.text")); // NOI18N
        jTextM3UID.setToolTipText(bundle.getString("MC.jTextM3UID.toolTipText")); // NOI18N
        jTextM3UID.setEnabled(false);
        jTextM3UID.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextM3UIDFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextM3UIDFocusLost(evt);
            }
        });
        jTextM3UID.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                jTextM3UIDInputMethodTextChanged(evt);
            }
        });
        jTextM3UID.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTextM3UIDPropertyChange(evt);
            }
        });

        jLabelM3UID.setText(bundle.getString("MC.jLabelM3UID.text")); // NOI18N
        jLabelM3UID.setToolTipText(bundle.getString("MC.jLabelM3UID.toolTipText")); // NOI18N
        jLabelM3UID.setEnabled(false);

        jLabelM3SID.setText(bundle.getString("MC.jLabelM3SID.text")); // NOI18N
        jLabelM3SID.setToolTipText(bundle.getString("MC.jLabelM3SID.toolTipText")); // NOI18N
        jLabelM3SID.setEnabled(false);

        jTextM3SID.setEditable(false);
        jTextM3SID.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextM3SID.setText(bundle.getString("MC.jTextM3SID.text")); // NOI18N
        jTextM3SID.setToolTipText(bundle.getString("MC.jTextM3SID.toolTipText")); // NOI18N
        jTextM3SID.setEnabled(false);
        jTextM3SID.setFocusable(false);

        jMcM3Progress.setStringPainted(true);

        jMcM3Info.setEditable(false);

        jLocM3cfgLoad.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLocM3cfgLoad.setText(bundle.getString("MC.jLocM3cfgLoad.text")); // NOI18N
        jLocM3cfgLoad.setToolTipText(bundle.getString("MC.jLocM3cfgLoad.toolTipText")); // NOI18N
        jLocM3cfgLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jLocM3cfgLoadActionPerformed(evt);
            }
        });

        jLocM3cfgEdit.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLocM3cfgEdit.setText(bundle.getString("MC.jLocM3cfgEdit.text")); // NOI18N
        jLocM3cfgEdit.setToolTipText(bundle.getString("MC.jLocM3cfgEdit.toolTipText")); // NOI18N
        jLocM3cfgEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jLocM3cfgEditActionPerformed(evt);
            }
        });

        jLocM3cfgSave.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLocM3cfgSave.setText(bundle.getString("MC.jLocM3cfgSave.text")); // NOI18N
        jLocM3cfgSave.setToolTipText(bundle.getString("MC.jLocM3cfgSave.toolTipText")); // NOI18N
        jLocM3cfgSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jLocM3cfgSaveActionPerformed(evt);
            }
        });

        jLocDel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLocDel.setText(bundle.getString("MC.jLocDel.text")); // NOI18N
        jLocDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jLocDelActionPerformed(evt);
            }
        });

        jLoc2System.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLoc2System.setText(bundle.getString("MC.jLoc2System.text")); // NOI18N
        jLoc2System.setMaximumSize(new java.awt.Dimension(220, 25));
        jLoc2System.setMinimumSize(new java.awt.Dimension(220, 25));
        jLoc2System.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jLoc2SystemActionPerformed(evt);
            }
        });

        jLocM3sidWriteList.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLocM3sidWriteList.setText(bundle.getString("MC.jLocM3sidWriteList.text")); // NOI18N
        jLocM3sidWriteList.setToolTipText(bundle.getString("MC.jLocM3sidWriteList.toolTipText")); // NOI18N
        jLocM3sidWriteList.setEnabled(false);
        jLocM3sidWriteList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jLocM3sidWriteListActionPerformed(evt);
            }
        });

        jM3count.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jM3count.setText("(0)"); // NOI18N
        jM3count.setToolTipText(bundle.getString("MC.jM3count.toolTipText")); // NOI18N
        jM3count.setRequestFocusEnabled(false);
        jM3count.setVerifyInputWhenFocusTarget(false);

        jFuncIcon.setText(bundle.getString("MC.jFuncIcon.text")); // NOI18N
        jFuncIcon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFuncIconActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jLoksLayout = new javax.swing.GroupLayout(jLoks);
        jLoks.setLayout(jLoksLayout);
        jLoksLayout.setHorizontalGroup(
            jLoksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLoksLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 674, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLoksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLocDelAll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLocCheck, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLocRepair, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLocM3sidWrite, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLoksLayout.createSequentialGroup()
                        .addGap(0, 192, Short.MAX_VALUE)
                        .addComponent(jLabelM3UID)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextM3UID, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLoksLayout.createSequentialGroup()
                        .addComponent(jLabelM3SID, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextM3SID))
                    .addComponent(jMcM3Progress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jMcM3Info)
                    .addComponent(jLocM3cfgLoad, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLocDel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLoc2System, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLocM3sidWriteList, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLocM3cfgEdit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLocM3cfgSave, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jM3count, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jFuncIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jLoksLayout.setVerticalGroup(
            jLoksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLoksLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLoksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jLoksLayout.createSequentialGroup()
                        .addComponent(jLocDel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLocDelAll)
                        .addGap(18, 18, 18)
                        .addComponent(jLocCheck)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLocRepair)
                        .addGap(18, 18, 18)
                        .addGroup(jLoksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextM3SID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelM3SID))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jLoksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelM3UID)
                            .addComponent(jTextM3UID, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLocM3sidWrite, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jMcM3Progress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jMcM3Info, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLocM3sidWriteList)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLocM3cfgLoad)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLocM3cfgEdit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLocM3cfgSave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jM3count)
                        .addGap(18, 18, 18)
                        .addComponent(jFuncIcon)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                        .addComponent(jLoc2System, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 608, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLocM3sidWrite.getAccessibleContext().setAccessibleDescription(bundle.getString("MC.jLocM3sidWrite.AccessibleContext.accessibleDescription")); // NOI18N

        jTabbedPane1.addTab(bundle.getString("MC.jLoks.TabConstraints.tabTitle"), jLoks); // NOI18N

        jTraktionen.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTraktionen.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jTraktionenComponentShown(evt);
            }
        });

        jTableTraction.setAutoCreateRowSorter(true);
        jTableTraction.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTableTraction.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Adresse 1", "Name 1", "Adresse 2", "Name 2"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableTraction.setToolTipText(bundle.getString("MC.jTableTraction.toolTipText")); // NOI18N
        jTableTraction.setDragEnabled(true);
        jTableTraction.setIntercellSpacing(new java.awt.Dimension(10, 10));
        jTableTraction.setRowHeight(26);
        jTableTraction.getTableHeader().setReorderingAllowed(false);
        jTableTraction.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTableTractionKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(jTableTraction);
        if (jTableTraction.getColumnModel().getColumnCount() > 0) {
            jTableTraction.getColumnModel().getColumn(1).setResizable(false);
            jTableTraction.getColumnModel().getColumn(3).setResizable(false);
        }

        jTraDelAll.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTraDelAll.setText(bundle.getString("MC.jTraDelAll.text")); // NOI18N
        jTraDelAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTraDelAllActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel15.setText(bundle.getString("MC.jLabel15.text")); // NOI18N

        jTraCheck.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTraCheck.setText(bundle.getString("MC.jTraCheck.text")); // NOI18N
        jTraCheck.setToolTipText(bundle.getString("MC.jTraCheck.toolTipText")); // NOI18N
        jTraCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTraCheckActionPerformed(evt);
            }
        });

        jTraRepair.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTraRepair.setText(bundle.getString("MC.jTraRepair.text")); // NOI18N
        jTraRepair.setToolTipText(bundle.getString("MC.jTraRepair.toolTipText")); // NOI18N
        jTraRepair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTraRepairActionPerformed(evt);
            }
        });

        jTraDel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTraDel.setText(bundle.getString("MC.jTraDel.text")); // NOI18N
        jTraDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTraDelActionPerformed(evt);
            }
        });

        jTra2System.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTra2System.setText(bundle.getString("MC.jTra2System.text")); // NOI18N
        jTra2System.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTra2SystemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jTraktionenLayout = new javax.swing.GroupLayout(jTraktionen);
        jTraktionen.setLayout(jTraktionenLayout);
        jTraktionenLayout.setHorizontalGroup(
            jTraktionenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jTraktionenLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jTraktionenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 942, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jTraktionenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTraDelAll, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addComponent(jTraDel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTraCheck, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTraRepair, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTra2System, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jTraktionenLayout.setVerticalGroup(
            jTraktionenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jTraktionenLayout.createSequentialGroup()
                .addGroup(jTraktionenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jTraktionenLayout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jTraDel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTraDelAll)
                        .addGap(18, 18, 18)
                        .addComponent(jTraCheck)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTraRepair)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTra2System))
                    .addGroup(jTraktionenLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(jLabel15)
                .addGap(14, 14, 14))
        );

        jTabbedPane1.addTab(bundle.getString("MC.jTraktionen.TabConstraints.tabTitle"), jTraktionen); // NOI18N

        jMagnetartikel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMagnetartikel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jMagnetartikelComponentShown(evt);
            }
        });

        jTableAccessory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Gruppe (Adressen)", "Format"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableAccessory.setIntercellSpacing(new java.awt.Dimension(10, 10));
        jTableAccessory.setRowHeight(26);
        jTableAccessory.getTableHeader().setReorderingAllowed(false);
        jTableAccessory.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTableAccessoryKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(jTableAccessory);
        if (jTableAccessory.getColumnModel().getColumnCount() > 0) {
            jTableAccessory.getColumnModel().getColumn(0).setResizable(false);
        }

        jMMM.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMMM.setText(bundle.getString("MC.jMMM.text")); // NOI18N
        jMMM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMMMActionPerformed(evt);
            }
        });

        jMDCC.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMDCC.setText(bundle.getString("MC.jMDCC.text")); // NOI18N
        jMDCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMDCCActionPerformed(evt);
            }
        });

        jMagCheck.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMagCheck.setText(bundle.getString("MC.jMagCheck.text")); // NOI18N
        jMagCheck.setToolTipText(bundle.getString("MC.jMagCheck.toolTipText")); // NOI18N
        jMagCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMagCheckActionPerformed(evt);
            }
        });

        jMagRepair.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMagRepair.setText(bundle.getString("MC.jMagRepair.text")); // NOI18N
        jMagRepair.setToolTipText(bundle.getString("MC.jMagRepair.toolTipText")); // NOI18N
        jMagRepair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMagRepairActionPerformed(evt);
            }
        });

        jLabel16.setText(bundle.getString("MC.jLabel16.text")); // NOI18N

        jMag2System.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMag2System.setText(bundle.getString("MC.jMag2System.text")); // NOI18N
        jMag2System.setMaximumSize(new java.awt.Dimension(220, 25));
        jMag2System.setMinimumSize(new java.awt.Dimension(220, 25));
        jMag2System.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMag2SystemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jMagnetartikelLayout = new javax.swing.GroupLayout(jMagnetartikel);
        jMagnetartikel.setLayout(jMagnetartikelLayout);
        jMagnetartikelLayout.setHorizontalGroup(
            jMagnetartikelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jMagnetartikelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jMagnetartikelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 861, Short.MAX_VALUE)
                    .addComponent(jLabel16))
                .addGap(29, 29, 29)
                .addGroup(jMagnetartikelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jMag2System, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jMMM, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jMDCC, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jMagCheck, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jMagRepair, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE))
                .addContainerGap())
        );
        jMagnetartikelLayout.setVerticalGroup(
            jMagnetartikelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jMagnetartikelLayout.createSequentialGroup()
                .addGroup(jMagnetartikelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jMagnetartikelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE))
                    .addGroup(jMagnetartikelLayout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addComponent(jMMM)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jMDCC)
                        .addGap(52, 52, 52)
                        .addComponent(jMagCheck)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jMagRepair)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jMag2System, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel16)
                .addGap(20, 20, 20))
        );

        jTabbedPane1.addTab(bundle.getString("MC.jMagnetartikel.TabConstraints.tabTitle"), jMagnetartikel); // NOI18N

        jPanel2.setMaximumSize(new java.awt.Dimension(1110, 32767));
        jPanel2.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanel2ComponentShown(evt);
            }
        });
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("MC.jPanel3.border.title"))); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jCVListe.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { " 0002", " 0003", " 0004", " 0005", " 0029" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane5.setViewportView(jCVListe);

        jPanel3.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 45, 224, 275));

        jLabel25.setText(bundle.getString("MC.jLabel25.text")); // NOI18N
        jPanel3.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 24, -1, -1));

        jLabel26.setText(bundle.getString("MC.jLabel26.text")); // NOI18N
        jPanel3.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 24, -1, -1));

        buttonGroup1.add(jHauptGleis);
        jHauptGleis.setText(bundle.getString("MC.jHauptGleis.text")); // NOI18N
        jHauptGleis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jHauptGleisActionPerformed(evt);
            }
        });
        jPanel3.add(jHauptGleis, new org.netbeans.lib.awtextra.AbsoluteConstraints(247, 27, -1, -1));

        buttonGroup1.add(jProgGleis);
        jProgGleis.setText(bundle.getString("MC.jProgGleis.text")); // NOI18N
        jProgGleis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jProgGleisActionPerformed(evt);
            }
        });
        jPanel3.add(jProgGleis, new org.netbeans.lib.awtextra.AbsoluteConstraints(247, 52, -1, -1));

        jListeLesen.setText(bundle.getString("MC.jListeLesen.text")); // NOI18N
        jListeLesen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jListeLesenActionPerformed(evt);
            }
        });
        jPanel3.add(jListeLesen, new org.netbeans.lib.awtextra.AbsoluteConstraints(241, 221, 160, -1));

        jListeSchreiben.setText(bundle.getString("MC.jListeSchreiben.text")); // NOI18N
        jListeSchreiben.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jListeSchreibenActionPerformed(evt);
            }
        });
        jPanel3.add(jListeSchreiben, new org.netbeans.lib.awtextra.AbsoluteConstraints(241, 258, 160, -1));

        jListeBearbeiten.setText(bundle.getString("MC.jListeBearbeiten.text")); // NOI18N
        jListeBearbeiten.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jListeBearbeitenActionPerformed(evt);
            }
        });
        jPanel3.add(jListeBearbeiten, new org.netbeans.lib.awtextra.AbsoluteConstraints(241, 184, 160, -1));

        jLabel28.setText(bundle.getString("MC.jLabel28.text")); // NOI18N
        jPanel3.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 300, -1, -1));

        jHerstellerInfo.setEditable(false);
        jHerstellerInfo.setText(bundle.getString("MC.jHerstellerInfo.text")); // NOI18N
        jHerstellerInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jHerstellerInfoActionPerformed(evt);
            }
        });
        jPanel3.add(jHerstellerInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 300, 180, -1));

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("MC.jPanel8.border.title"))); // NOI18N

        jLabel29.setText(bundle.getString("MC.jLabel29.text")); // NOI18N

        jCV_Direkt.setText(bundle.getString("MC.jCV_Direkt.text")); // NOI18N

        jWertDirekt.setText(bundle.getString("MC.jWertDirekt.text")); // NOI18N

        jLabel30.setText(bundle.getString("MC.jLabel30.text")); // NOI18N

        jDirektLesen.setText(bundle.getString("MC.jDirektLesen.text")); // NOI18N
        jDirektLesen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDirektLesenActionPerformed(evt);
            }
        });

        jDirektSchreiben.setText(bundle.getString("MC.jDirektSchreiben.text")); // NOI18N
        jDirektSchreiben.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDirektSchreibenActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel30)
                    .addComponent(jLabel29))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jCV_Direkt)
                    .addComponent(jWertDirekt, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jDirektLesen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDirektSchreiben, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(jCV_Direkt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDirektLesen))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(jWertDirekt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDirektSchreiben))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(241, 77, 254, 95));

        jLabel31.setText(bundle.getString("MC.jLabel31.text")); // NOI18N
        jPanel3.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(357, 31, -1, -1));

        jDecAdr.setText(bundle.getString("MC.jDecAdr.text")); // NOI18N
        jPanel3.add(jDecAdr, new org.netbeans.lib.awtextra.AbsoluteConstraints(403, 29, 55, -1));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 12, 510, 340));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("MC.jPanel4.border.title"))); // NOI18N
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel19.setText(bundle.getString("MC.jLabel19.text")); // NOI18N
        jPanel4.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(204, 80, -1, -1));

        jLabel20.setText(bundle.getString("MC.jLabel20.text")); // NOI18N
        jLabel20.setToolTipText(bundle.getString("MC.jLabel20.toolTipText")); // NOI18N
        jPanel4.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 80, -1, -1));

        jm3Addr.setText(bundle.getString("MC.jm3Addr.text")); // NOI18N
        jm3Addr.setToolTipText(bundle.getString("MC.jm3Addr.toolTipText")); // NOI18N
        jPanel4.add(jm3Addr, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 80, 96, -1));

        jmfxUID.setEditable(false);
        jmfxUID.setText(bundle.getString("MC.jmfxUID.text")); // NOI18N
        jPanel4.add(jmfxUID, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 80, 90, -1));

        jm3Schreiben.setText(bundle.getString("MC.jm3Schreiben.text")); // NOI18N
        jm3Schreiben.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jm3SchreibenActionPerformed(evt);
            }
        });
        jPanel4.add(jm3Schreiben, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 80, 110, -1));

        jLabel27.setText(bundle.getString("MC.jLabel27.text")); // NOI18N
        jPanel4.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 28, -1, -1));

        jLabel32.setText(bundle.getString("MC.jLabel32.text")); // NOI18N
        jPanel4.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        jm3GotoList.setText(bundle.getString("MC.jm3GotoList.text")); // NOI18N
        jm3GotoList.setToolTipText(bundle.getString("MC.jm3GotoList.toolTipText")); // NOI18N
        jm3GotoList.setEnabled(false);
        jm3GotoList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jm3GotoListActionPerformed(evt);
            }
        });
        jPanel4.add(jm3GotoList, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, -1, -1));

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 361, 510, 150));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("MC.jPanel5.border.title"))); // NOI18N
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel21.setText(bundle.getString("MC.jLabel21.text")); // NOI18N
        jPanel5.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        jMMRegister.setText(bundle.getString("MC.jMMRegister.text")); // NOI18N
        jPanel5.add(jMMRegister, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, 90, -1));

        jMMWert.setText(bundle.getString("MC.jMMWert.text")); // NOI18N
        jMMWert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMMWertActionPerformed(evt);
            }
        });
        jPanel5.add(jMMWert, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 80, 70, -1));

        jLabel22.setText(bundle.getString("MC.jLabel22.text")); // NOI18N
        jPanel5.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 80, -1, -1));

        jStartMMProg.setText(bundle.getString("MC.jStartMMProg.text")); // NOI18N
        jStartMMProg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jStartMMProgActionPerformed(evt);
            }
        });
        jPanel5.add(jStartMMProg, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 80, 111, -1));

        jLabel23.setText(bundle.getString("MC.jLabel23.text")); // NOI18N
        jPanel5.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 28, -1, -1));

        jLabel24.setText(bundle.getString("MC.jLabel24.text")); // NOI18N
        jPanel5.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(91, 49, -1, -1));

        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 540, 510, 110));
        jPanel5.getAccessibleContext().setAccessibleName(bundle.getString("MC.jPanel5.AccessibleContext.accessibleName")); // NOI18N

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("MC.jPanel6.border.title"))); // NOI18N
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jGeschwindigkeit.setMaximum(126);
        jGeschwindigkeit.setToolTipText(bundle.getString("MC.jGeschwindigkeit.toolTipText")); // NOI18N
        jGeschwindigkeit.setValue(0);
        jGeschwindigkeit.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jGeschwindigkeitStateChanged(evt);
            }
        });
        jGeschwindigkeit.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                jGeschwindigkeitCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        jPanel6.add(jGeschwindigkeit, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 510, 180, -1));

        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        j1.setText(bundle.getString("MC.j1.text")); // NOI18N
        j1.setPreferredSize(new java.awt.Dimension(44, 44));
        j1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                j1ActionPerformed(evt);
            }
        });

        j2.setText(bundle.getString("MC.j2.text")); // NOI18N
        j2.setPreferredSize(new java.awt.Dimension(44, 44));
        j2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                j2ActionPerformed(evt);
            }
        });

        j3.setText(bundle.getString("MC.j3.text")); // NOI18N
        j3.setPreferredSize(new java.awt.Dimension(44, 44));
        j3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                j3ActionPerformed(evt);
            }
        });

        j4.setText(bundle.getString("MC.j4.text")); // NOI18N
        j4.setPreferredSize(new java.awt.Dimension(44, 44));
        j4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                j4ActionPerformed(evt);
            }
        });

        j5.setText(bundle.getString("MC.j5.text")); // NOI18N
        j5.setPreferredSize(new java.awt.Dimension(44, 44));
        j5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                j5ActionPerformed(evt);
            }
        });

        j6.setText(bundle.getString("MC.j6.text")); // NOI18N
        j6.setPreferredSize(new java.awt.Dimension(44, 44));
        j6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                j6ActionPerformed(evt);
            }
        });

        j7.setText(bundle.getString("MC.j7.text")); // NOI18N
        j7.setPreferredSize(new java.awt.Dimension(44, 44));
        j7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                j7ActionPerformed(evt);
            }
        });

        j8.setText(bundle.getString("MC.j8.text")); // NOI18N
        j8.setPreferredSize(new java.awt.Dimension(44, 44));
        j8.setRequestFocusEnabled(false);
        j8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                j8ActionPerformed(evt);
            }
        });

        j9.setText(bundle.getString("MC.j9.text")); // NOI18N
        j9.setPreferredSize(new java.awt.Dimension(44, 44));
        j9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                j9ActionPerformed(evt);
            }
        });

        jStern.setText(bundle.getString("MC.jStern.text")); // NOI18N
        jStern.setPreferredSize(new java.awt.Dimension(44, 44));
        jStern.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSternActionPerformed(evt);
            }
        });

        j0.setText(bundle.getString("MC.j0.text")); // NOI18N
        j0.setPreferredSize(new java.awt.Dimension(44, 44));
        j0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                j0ActionPerformed(evt);
            }
        });

        jRaute.setText(bundle.getString("MC.jRaute.text")); // NOI18N
        jRaute.setPreferredSize(new java.awt.Dimension(44, 44));
        jRaute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRauteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(j1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(j2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(j3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(j4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(j5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(j6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(j7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jStern, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(j0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jRaute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(j8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(j9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(j1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(j2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(j3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(j4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(j5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(j6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(j7, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(j8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(j9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jStern, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(j0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRaute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 208, 190, 250));

        jf0.setBackground(new java.awt.Color(255, 255, 0));
        jf0.setText(bundle.getString("MC.jf0.text")); // NOI18N
        jf0.setPreferredSize(new java.awt.Dimension(47, 45));
        jf0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jf0ActionPerformed(evt);
            }
        });
        jPanel6.add(jf0, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 260, -1, -1));

        jf1.setBackground(new java.awt.Color(51, 153, 255));
        jf1.setText(bundle.getString("MC.jf1.text")); // NOI18N
        jf1.setPreferredSize(new java.awt.Dimension(47, 45));
        jf1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jf1ActionPerformed(evt);
            }
        });
        jPanel6.add(jf1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 260, -1, -1));

        jf2.setBackground(new java.awt.Color(51, 153, 255));
        jf2.setText(bundle.getString("MC.jf2.text")); // NOI18N
        jf2.setPreferredSize(new java.awt.Dimension(47, 45));
        jf2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jf2ActionPerformed(evt);
            }
        });
        jPanel6.add(jf2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 260, -1, -1));

        jf3.setBackground(new java.awt.Color(51, 153, 255));
        jf3.setText(bundle.getString("MC.jf3.text")); // NOI18N
        jf3.setPreferredSize(new java.awt.Dimension(47, 45));
        jf3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jf3ActionPerformed(evt);
            }
        });
        jPanel6.add(jf3, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 330, -1, -1));

        jf4.setBackground(new java.awt.Color(51, 153, 255));
        jf4.setText(bundle.getString("MC.jf4.text")); // NOI18N
        jf4.setPreferredSize(new java.awt.Dimension(47, 45));
        jf4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jf4ActionPerformed(evt);
            }
        });
        jPanel6.add(jf4, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 330, -1, -1));

        jGO.setBackground(new java.awt.Color(0, 255, 0));
        jGO.setText(bundle.getString("MC.jGO.text")); // NOI18N
        jGO.setPreferredSize(new java.awt.Dimension(52, 45));
        jGO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jGOActionPerformed(evt);
            }
        });
        jPanel6.add(jGO, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 400, 70, -1));

        jSTOP.setBackground(new java.awt.Color(255, 0, 0));
        jSTOP.setText(bundle.getString("MC.jSTOP.text")); // NOI18N
        jSTOP.setPreferredSize(new java.awt.Dimension(66, 45));
        jSTOP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSTOPActionPerformed(evt);
            }
        });
        jPanel6.add(jSTOP, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 400, 70, -1));

        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jf5.setText(bundle.getString("MC.jf5.text")); // NOI18N
        jf5.setPreferredSize(new java.awt.Dimension(55, 39));
        jf5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jf5ActionPerformed(evt);
            }
        });

        jf9.setText(bundle.getString("MC.jf9.text")); // NOI18N
        jf9.setPreferredSize(new java.awt.Dimension(55, 39));
        jf9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jf9ActionPerformed(evt);
            }
        });

        jf6.setText(bundle.getString("MC.jf6.text")); // NOI18N
        jf6.setPreferredSize(new java.awt.Dimension(55, 39));
        jf6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jf6ActionPerformed(evt);
            }
        });

        jf8.setText(bundle.getString("MC.jf8.text")); // NOI18N
        jf8.setPreferredSize(new java.awt.Dimension(55, 39));
        jf8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jf8ActionPerformed(evt);
            }
        });

        jf7.setText(bundle.getString("MC.jf7.text")); // NOI18N
        jf7.setPreferredSize(new java.awt.Dimension(55, 39));
        jf7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jf7ActionPerformed(evt);
            }
        });

        jf10.setText(bundle.getString("MC.jf10.text")); // NOI18N
        jf10.setPreferredSize(new java.awt.Dimension(55, 39));
        jf10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jf10ActionPerformed(evt);
            }
        });

        jf11.setText(bundle.getString("MC.jf11.text")); // NOI18N
        jf11.setPreferredSize(new java.awt.Dimension(55, 39));
        jf11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jf11ActionPerformed(evt);
            }
        });

        jf12.setText(bundle.getString("MC.jf12.text")); // NOI18N
        jf12.setPreferredSize(new java.awt.Dimension(55, 39));
        jf12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jf12ActionPerformed(evt);
            }
        });

        jf18.setText(bundle.getString("MC.jf18.text")); // NOI18N
        jf18.setPreferredSize(new java.awt.Dimension(55, 39));
        jf18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jf18ActionPerformed(evt);
            }
        });

        jf13.setText(bundle.getString("MC.jf13.text")); // NOI18N
        jf13.setPreferredSize(new java.awt.Dimension(55, 39));
        jf13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jf13ActionPerformed(evt);
            }
        });

        jf17.setText(bundle.getString("MC.jf17.text")); // NOI18N
        jf17.setPreferredSize(new java.awt.Dimension(55, 39));
        jf17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jf17ActionPerformed(evt);
            }
        });

        jf14.setText(bundle.getString("MC.jf14.text")); // NOI18N
        jf14.setPreferredSize(new java.awt.Dimension(55, 39));
        jf14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jf14ActionPerformed(evt);
            }
        });

        jf19.setText(bundle.getString("MC.jf19.text")); // NOI18N
        jf19.setPreferredSize(new java.awt.Dimension(55, 39));
        jf19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jf19ActionPerformed(evt);
            }
        });

        jf15.setText(bundle.getString("MC.jf15.text")); // NOI18N
        jf15.setPreferredSize(new java.awt.Dimension(55, 39));
        jf15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jf15ActionPerformed(evt);
            }
        });

        jf16.setText(bundle.getString("MC.jf16.text")); // NOI18N
        jf16.setMaximumSize(new java.awt.Dimension(47, 55));
        jf16.setPreferredSize(new java.awt.Dimension(55, 39));
        jf16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jf16ActionPerformed(evt);
            }
        });

        jf20.setText(bundle.getString("MC.jf20.text")); // NOI18N
        jf20.setPreferredSize(new java.awt.Dimension(55, 39));
        jf20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jf20ActionPerformed(evt);
            }
        });

        jf22.setText(bundle.getString("MC.jf22.text")); // NOI18N
        jf22.setPreferredSize(new java.awt.Dimension(55, 39));
        jf22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jf22ActionPerformed(evt);
            }
        });

        jf21.setText(bundle.getString("MC.jf21.text")); // NOI18N
        jf21.setPreferredSize(new java.awt.Dimension(55, 39));
        jf21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jf21ActionPerformed(evt);
            }
        });

        jf23.setText(bundle.getString("MC.jf23.text")); // NOI18N
        jf23.setPreferredSize(new java.awt.Dimension(55, 39));
        jf23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jf23ActionPerformed(evt);
            }
        });

        jf24.setText(bundle.getString("MC.jf24.text")); // NOI18N
        jf24.setPreferredSize(new java.awt.Dimension(55, 39));
        jf24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jf24ActionPerformed(evt);
            }
        });

        jf26.setText(bundle.getString("MC.jf26.text")); // NOI18N
        jf26.setPreferredSize(new java.awt.Dimension(55, 39));
        jf26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jf26ActionPerformed(evt);
            }
        });

        jf25.setText(bundle.getString("MC.jf25.text")); // NOI18N
        jf25.setPreferredSize(new java.awt.Dimension(55, 39));
        jf25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jf25ActionPerformed(evt);
            }
        });

        jf27.setText(bundle.getString("MC.jf27.text")); // NOI18N
        jf27.setPreferredSize(new java.awt.Dimension(55, 39));
        jf27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jf27ActionPerformed(evt);
            }
        });

        jf28.setText(bundle.getString("MC.jf28.text")); // NOI18N
        jf28.setPreferredSize(new java.awt.Dimension(55, 39));
        jf28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jf28ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jf19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jf20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jf15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jf16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jf9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jf11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jf12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jf10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jf7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jf5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jf6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jf8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jf13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jf14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jf17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jf18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jf21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jf22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jf23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jf24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jf25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jf26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jf27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jf28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jf5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jf6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jf8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jf7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jf10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jf9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jf12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jf11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jf14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jf13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jf15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jf16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jf17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jf18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jf19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jf20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jf21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jf22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jf23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jf24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jf25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jf26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jf27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jf28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(57, 57, 57))
        );

        jPanel6.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, -1, 620));

        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 4));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jDisplay.setEditable(false);
        jDisplay.setFont(new java.awt.Font("Liberation Mono", 1, 32)); // NOI18N
        jDisplay.setTabSize(1);
        jDisplay.setText(bundle.getString("MC.jDisplay.text")); // NOI18N
        jDisplay.setAutoscrolls(false);
        jDisplay.setFocusable(false);
        jDisplay.setMaximumSize(new java.awt.Dimension(304, 66));
        jDisplay.setRequestFocusEnabled(false);
        jScrollPane6.setViewportView(jDisplay);

        jPanel10.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 320, 90));

        jPanel6.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 340, 110));

        jLabel33.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 12)); // NOI18N
        jLabel33.setText(bundle.getString("MC.jLabel33.text")); // NOI18N
        jPanel6.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 230, -1, -1));

        jLabel34.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 18)); // NOI18N
        jLabel34.setText(bundle.getString("MC.jLabel34.text")); // NOI18N
        jPanel6.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 230, -1, -1));

        jRueck.setText(bundle.getString("MC.jRueck.text")); // NOI18N
        jRueck.setToolTipText(bundle.getString("MC.jRueck.toolTipText")); // NOI18N
        jRueck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRueckActionPerformed(evt);
            }
        });
        jPanel6.add(jRueck, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 540, -1, -1));

        jVor.setText(bundle.getString("MC.jVor.text")); // NOI18N
        jVor.setToolTipText(bundle.getString("MC.jVor.toolTipText")); // NOI18N
        jVor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jVorActionPerformed(evt);
            }
        });
        jPanel6.add(jVor, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 540, -1, -1));

        jMC_ESC.setText(bundle.getString("MC.jMC_ESC.text")); // NOI18N
        jPanel6.add(jMC_ESC, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 480, -1, -1));

        jMC_MENU.setText(bundle.getString("MC.jMC_MENU.text")); // NOI18N
        jPanel6.add(jMC_MENU, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 480, -1, -1));

        jMC_OK.setText(bundle.getString("MC.jMC_OK.text")); // NOI18N
        jPanel6.add(jMC_OK, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 480, -1, -1));

        jPanel2.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 10, 560, 640));

        jTabbedPane1.addTab(bundle.getString("MC.jPanel2.TabConstraints.tabTitle"), jPanel2); // NOI18N

        jUpdate.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jUpdate.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jUpdateComponentShown(evt);
            }
        });

        jUpdDatei.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jUpdDatei.setText(bundle.getString("MC.jUpdDatei.text")); // NOI18N

        jUpdDateiAuswahl.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jUpdDateiAuswahl.setText(bundle.getString("MC.jUpdDateiAuswahl.text")); // NOI18N
        jUpdDateiAuswahl.setToolTipText(bundle.getString("MC.jUpdDateiAuswahl.toolTipText")); // NOI18N
        jUpdDateiAuswahl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jUpdDateiAuswahlActionPerformed(evt);
            }
        });

        jMcUpdProgress.setStringPainted(true);

        jUpdStartUpdate.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jUpdStartUpdate.setText(bundle.getString("MC.jUpdStartUpdate.text")); // NOI18N
        jUpdStartUpdate.setToolTipText(bundle.getString("MC.jUpdStartUpdate.toolTipText")); // NOI18N
        jUpdStartUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jUpdStartUpdateActionPerformed(evt);
            }
        });

        jMcUpdInfo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMcUpdInfo.setText(bundle.getString("MC.jMcUpdInfo.text")); // NOI18N

        jUpdLastErrorLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jUpdLastErrorLabel.setText(bundle.getString("MC.jUpdLastErrorLabel.text")); // NOI18N

        jUpdLastError.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jUpdLastError.setText(bundle.getString("MC.jUpdLastError.text")); // NOI18N

        jUpdCancel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jUpdCancel.setText(bundle.getString("MC.jUpdCancel.text")); // NOI18N
        jUpdCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jUpdCancelActionPerformed(evt);
            }
        });

        jUpdClose.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jUpdClose.setText(bundle.getString("MC.jUpdClose.text")); // NOI18N
        jUpdClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jUpdCloseActionPerformed(evt);
            }
        });

        jUpd2System.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jUpd2System.setText(bundle.getString("MC.jUpd2System.text")); // NOI18N
        jUpd2System.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jUpd2SystemActionPerformed(evt);
            }
        });

        jTextPane1.setEditable(false);
        jTextPane1.setBackground(new java.awt.Color(238, 238, 238));
        jTextPane1.setBorder(null);
        jTextPane1.setContentType("text/html"); // NOI18N
        jTextPane1.setText(bundle.getString("MC.jTextPane1.text")); // NOI18N
        jTextPane1.setFocusable(false);
        jScrollPane4.setViewportView(jTextPane1);

        helpLC.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        helpLC.setText(bundle.getString("MC.helpLC.text")); // NOI18N
        helpLC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpLCActionPerformed(evt);
            }
        });

        jTextField1.setBackground(new java.awt.Color(238, 238, 238));
        jTextField1.setText(bundle.getString("MC.jTextField1.text")); // NOI18N

        helpHC.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        helpHC.setText(bundle.getString("MC.helpHC.text")); // NOI18N
        helpHC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpHCActionPerformed(evt);
            }
        });

        helpPC.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        helpPC.setText(bundle.getString("MC.helpPC.text")); // NOI18N
        helpPC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpPCActionPerformed(evt);
            }
        });

        helpSNC.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        helpSNC.setText(bundle.getString("MC.helpSNC.text")); // NOI18N
        helpSNC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpSNCActionPerformed(evt);
            }
        });

        helpXNC.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        helpXNC.setText(bundle.getString("MC.helpXNC.text")); // NOI18N
        helpXNC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpXNCActionPerformed(evt);
            }
        });

        helpMC.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        helpMC.setText(bundle.getString("MC.helpMC.text")); // NOI18N
        helpMC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpMCActionPerformed(evt);
            }
        });

        helpWasIstWas.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        helpWasIstWas.setText(bundle.getString("MC.helpWasIstWas.text")); // NOI18N
        helpWasIstWas.setToolTipText(bundle.getString("MC.helpWasIstWas.toolTipText")); // NOI18N
        helpWasIstWas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpWasIstWasActionPerformed(evt);
            }
        });

        helpMCasLC.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        helpMCasLC.setText(bundle.getString("MC.helpMCasLC.text")); // NOI18N
        helpMCasLC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpMCasLCActionPerformed(evt);
            }
        });

        Firmware.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        Firmware.setText(bundle.getString("MC.Firmware.text")); // NOI18N
        Firmware.setToolTipText(bundle.getString("MC.Firmware.toolTipText")); // NOI18N
        Firmware.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FirmwareActionPerformed(evt);
            }
        });

        jEasyNetUpdate.setText(bundle.getString("MC.jEasyNetUpdate.text")); // NOI18N
        jEasyNetUpdate.setToolTipText(bundle.getString("MC.jEasyNetUpdate.toolTipText")); // NOI18N
        jEasyNetUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jEasyNetUpdateActionPerformed(evt);
            }
        });

        buttonGroup1.add(jUSB1);
        jUSB1.setSelected(true);
        jUSB1.setText(bundle.getString("MC.jUSB1.text")); // NOI18N
        jUSB1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jUSB1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jUSB2);
        jUSB2.setText(bundle.getString("MC.jUSB2.text")); // NOI18N
        jUSB2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jUSB2ActionPerformed(evt);
            }
        });

        jLabel18.setText(bundle.getString("MC.jLabel18.text")); // NOI18N
        jLabel18.setToolTipText(bundle.getString("MC.jLabel18.toolTipText")); // NOI18N

        javax.swing.GroupLayout jUpdateLayout = new javax.swing.GroupLayout(jUpdate);
        jUpdate.setLayout(jUpdateLayout);
        jUpdateLayout.setHorizontalGroup(
            jUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jUpdateLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jMcUpdProgress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jUpdateLayout.createSequentialGroup()
                        .addGroup(jUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jUpdateLayout.createSequentialGroup()
                                .addGroup(jUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jUpdateLayout.createSequentialGroup()
                                        .addGroup(jUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jMcUpdInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jUpdateLayout.createSequentialGroup()
                                                .addComponent(jLabel18)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jUSB1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jUSB2)))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(jUpdDatei))
                                .addGap(4, 4, 4))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jUpdateLayout.createSequentialGroup()
                                .addGroup(jUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jUpdateLayout.createSequentialGroup()
                                        .addComponent(jUpdLastErrorLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jUpdLastError, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 152, Short.MAX_VALUE)
                                        .addComponent(jEasyNetUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jUpdateLayout.createSequentialGroup()
                                        .addGroup(jUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(helpHC, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(helpLC, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(helpPC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(helpXNC, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jUpdateLayout.createSequentialGroup()
                                                .addComponent(helpMC, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(helpMCasLC, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jUpdateLayout.createSequentialGroup()
                                                .addComponent(helpSNC, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(Firmware))))
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addGap(15, 15, 15)))
                        .addGroup(jUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jUpd2System, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                            .addComponent(jUpdClose, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jUpdDateiAuswahl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jUpdCancel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jUpdStartUpdate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(helpWasIstWas))))
                .addContainerGap())
        );
        jUpdateLayout.setVerticalGroup(
            jUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jUpdateLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jUpdDatei, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jUpdDateiAuswahl))
                .addGap(6, 6, 6)
                .addGroup(jUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jUSB1)
                    .addComponent(jUSB2)
                    .addComponent(helpWasIstWas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jMcUpdInfo)
                .addGap(18, 18, 18)
                .addComponent(jMcUpdProgress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jUpdateLayout.createSequentialGroup()
                        .addGroup(jUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jUpdStartUpdate)
                            .addComponent(jEasyNetUpdate))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jUpdCancel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jUpd2System)
                            .addComponent(helpXNC)
                            .addComponent(helpHC))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jUpdClose)
                            .addComponent(helpPC)
                            .addComponent(helpMC)
                            .addComponent(helpMCasLC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(jUpdateLayout.createSequentialGroup()
                        .addGroup(jUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jUpdLastError)
                            .addComponent(jUpdLastErrorLabel))
                        .addGap(11, 11, 11)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jUpdateLayout.createSequentialGroup()
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(helpLC)
                                    .addComponent(helpSNC)))
                            .addComponent(Firmware, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(88, 88, 88))))
        );

        jTabbedPane1.addTab(bundle.getString("MC.jUpdate.TabConstraints.tabTitle"), jUpdate); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // falls Schnittstelle offen und verbunden -> schliessen
        if( this.M3changed ) {
            int answer = CVNavi.yesNoM3listSave();
            System.out.println("formWindowClosed: answer="+answer );
            if( answer == 0 ) {
                jLocM3cfgSaveActionPerformed(null);
            }
        }

        if( debugLevel > 0 ) {
            System.out.println("formWindowClosed");
        }
        CVNavi.frameInstanceDEVICE = null;
        stopIOAction();
        Com = CVNavi.safelyCloseCom( this, Com );
        CVNavi.setNumS88(0);
        CVNavi.setFocus();
    }//GEN-LAST:event_formWindowClosed

    private void initLocoTable(){

        for (int i = 0; i < c.MAX_LOCS; i++) {
            for (int j = 0; j < jTableLoco.getColumnCount(); j++) {
                jTableLoco.setValueAt("", i, j);
            }
        }

        jTableLoco.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
             public void valueChanged(ListSelectionEvent e) {
                locoTableSelRow = jTableLoco.getSelectedRow();
                locoTableSelCol = jTableLoco.getSelectedColumn();
                if( debugLevel > 0 ) {
                    System.out.println("LOCO ListSelectionListener valueChanged row="+locoTableSelRow+" col="+locoTableSelCol);
                }
                if( locoTableSelRow >= 0 ) {
                    checkM3sid(locoTableSelRow);
                }
            }
        });
    }
    private void checkM3sid( int row ) {
        String sFormat = ""+jTableLoco.getValueAt(row, 1);
        Boolean showM3 = "M3".equalsIgnoreCase(sFormat);
        jLabelM3SID.setEnabled(showM3);
        jTextM3SID.setEnabled(showM3);
        jLabelM3UID.setEnabled(showM3);
        jTextM3UID.setEnabled(showM3);
        jLocM3sidWrite.setEnabled(showM3);
        if( showM3 ) {
            jTextM3SID.setText(""+jTableLoco.getValueAt(row, 0));
            jTextM3UID.setText(""+getM3UID( jTableLoco.getValueAt(row, 0)));
            jMcM3Info.setText("");
            jMcM3Progress.setValue(0);
        }
    }
    private String getM3UID( Object loco ) {
        String sLoco = ""+loco;
        if( debugLevel > 0 ) {
            System.out.println("getM3UID sLoco="+sLoco );
        }
        for( int i = 0 ; i < M3used ; i++ ) {
            String sM3uid = ""+M3liste[0][i];
            String sM3sid = ""+M3liste[1][i];
            if( debugLevel > 0 ) {
                System.out.println("getM3UID sM3uid="+sM3uid+" sM3sid="+sM3sid );
            }
            if( sM3sid.equals(sLoco) ) {
                if( sM3uid != null ) {
                    return sM3uid;
                }
            }
        }
        return "";
    }
    private void initTractionTable(){
        for (int i = 0; i < jTableTraction.getRowCount(); i++) {
            for (int j = 0; j < jTableTraction.getColumnCount(); j++) {
                jTableTraction.setValueAt("", i, j);
            }
        }
    }
    private void initAccessoryTable(){
        for (int i = 0; i < jTableAccessory.getRowCount(); i++) {
            for (int j = 0; j < jTableAccessory.getColumnCount(); j++) {
                jTableAccessory.setValueAt("", i, j);
            }
        }
    }
    private void setAccessoryTableWithProto(String str){
        for (int i = 0; i < jTableAccessory.getRowCount(); i++) {
            jTableAccessory.setValueAt(str, i, 1);
        }
    }


    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

        // Ist die TamsMC auch eingestellt ?
        if ( ( CVNavi.getZentrale() != c.cuMasterControl1 ) && (CVNavi.getZentrale() != c.cuMasterControl2 ) ) {
            CVNavi.mbNoTamsMC( this );
            this.dispose();
            return;
        }

        gsBaudRateSaved = CVNavi.gsBaudRate;
        jLabel18.setText(jLabel18.getText().replace("COMx", CVNavi.gsSchnittstelle));
        setDeviceSelection();
        setFocusUpdStart();
        setFocusDateiAuswahl();
        Com = CVNavi.safelyOpenCom( this, Com, false );
        if( debugLevel > 0 ) {
            System.out.println("call Com.connect OUT: Com "+((Com == null)?"==":"!=")+" NULL" );
            System.out.println("call Com.connect OUT: isopen["+((Com == null)?"Com is undefined":Com.isconnected())+"]" );
        }
        if( (Com == null) || (! Com.isconnected()) ) {
            // continue -> prepare offline use with default values
            jKonfLesen.setEnabled(false);
            jKonfSchreiben.setEnabled(false);
            jWRsys.setEnabled(false);
            jWRloc.setEnabled(false);
            jWRtra.setEnabled(false);
            jWRmag.setEnabled(false);
            if( CVNavi.updateAlwaysVisible == false ) {
                jTabbedPane1.remove(this.jUpdate);
            }
            //jDatenQuelle.setText("invalid");
            jDatenQuelle.setForeground(Color.red);
            jBaud.setSelectedItem("0");
            Com = null;
            CVNavi.mbDeviceConnectProblemOffline( this );
        } else {
            Com = CVNavi.safelyCloseCom( this, Com );
        }
        jCancel.setEnabled(false);
        jUpdCancel.setEnabled(false);
        initLocoTable();
        initTractionTable();
        initAccessoryTable();
        jKonfLesen.setSelected(true);
        jKonfLesen.grabFocus();

        // store pointer to instance in a final variable -> useable inside ActionListener
        final MC outerThis = this;

        final ActionListener actionListener = new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                if( bWaitAnswerInProgress ) {
                    if( debugLevel > 2 ) {
                        System.out.println("bWaitAnswerInProgress");
                    }
                    tmpBytesRead = Com.read(bArrayTmp); // was gibts Neues ?
                    if( tmpBytesRead < 0 ) {
                        if(bReadS88num) {
                            System.out.println(" MC tmpBytesRead == "+tmpBytesRead+" ZZZ bReadS88num" );
                        } else {
                            System.out.println(" MC tmpBytesRead == "+tmpBytesRead+" ZZZ" );
                        }
                    }
                    if( tmpBytesRead > 0 ) {
                        //wenn ja, an bisher empfangene Daten anhängen
                        System.arraycopy(bArrayTmp, 0, bArray, bytesRead, tmpBytesRead);
                        if( CVNavi.debugLevel > 2 ) {
                            System.out.println("2875 MERGE PRE bytesRead="+bytesRead+" tmpBytesRead="+tmpBytesRead+" res="+(bytesRead+tmpBytesRead) );
                        }
                        bytesRead += tmpBytesRead;
                        bArray[bytesRead] = 0;
                        if( CVNavi.debugLevel > 3 ) {
                            System.out.println("2880 current: bytesRead="+bytesRead );
                            CVNavi.dumpbArray(bArray);
                        }
                    }

                    if( tmpBytesRead == 0 ) {
                        System.out.print("->"+retries+" ");  // no newline
                        retries--;
                        if(bProg_MM)
                        {
                            switch(retries)
                            {
                                case 3:
                                    System.out.println(" -> MM-Prog ende" );
                                    jStartMMProg.setEnabled(true);
                                    jMMRegister.setEnabled(true);
                                    jMMWert.setEnabled(true);
                                    jMcRwProgress.setString(null);
                                    Com.write((byte)0x61); // STOP
                                    stopIOAction();
                                    return;

                                case 6:
                                    int MM_Wert;
                                    MM_Wert = Integer.parseInt(jMMWert.getText());
                                    Com.write((byte)0);
                                    Com.write((byte)MM_Wert);
                                    break;

                                case 7:
                                    MM_Wert = Integer.parseInt(jMMWert.getText());
                                    Com.write((byte)15);
                                    Com.write((byte)MM_Wert);
                                    break;

                                case 8:
                                    try {
                                        MM_Wert = Integer.parseInt(jMMWert.getText());
                                    } catch (NumberFormatException numberFormatException) {
                                        stopIOAction();
                                        jStartMMProg.setEnabled(true);
                                        jMMRegister.setEnabled(true);
                                        jMMWert.setEnabled(true);
                                        jMcRwProgress.setString(null);
                                         CVNavi.mbGeneric(null, "Eingabe falsch: Wert");
                                        return;
                                    }
                                    if(MM_Wert > 255 || MM_Wert == 0)
                                    {
                                        MM_Wert = 255;
                                        jMMWert.setText("255");
                                    }
                                    Com.write((byte)0);
                                    Com.write((byte)MM_Wert);
                                    break;

                                case 9:
                                    int MM_Adr;
                                    MM_Adr = Integer.parseInt(jMMRegister.getText());
                                    Com.write((byte)0);
                                    Com.write((byte)MM_Adr);
                                    break;

                                case 10:
                                    MM_Adr = 0;
                                    MM_Adr = Integer.parseInt(jMMRegister.getText());
                                    Com.write((byte)15);
                                    Com.write((byte)MM_Adr);
                                    break;

                                case 11:
                                    try {
                                        MM_Adr = Integer.parseInt(jMMRegister.getText());
                                    } catch (NumberFormatException numberFormatException) {
                                        stopIOAction();
                                        jStartMMProg.setEnabled(true);
                                        jMMRegister.setEnabled(true);
                                        jMMWert.setEnabled(true);
                                        jMcRwProgress.setString(null);
                                         CVNavi.mbGeneric(null, "Eingabe falsch: Register");
                                        return;
                                    }
                                    if(MM_Adr > 255 || MM_Adr == 0)
                                    {
                                        MM_Adr = 255;
                                        jMMRegister.setText("255");
                                    }
                                    Com.write((byte)0);
                                    Com.write((byte)MM_Adr);
                                    break;

                                case 13:
//                                    Com.write("XTPM 80\r");
                                   break;
                            }
                        }
                        if( retries == 0 ) {
                            jMcRwProgress.setString(null);
                            System.out.println(" -> retries ende" );
                            stopIOAction();
                            return;
                        }
                        if( CVNavi.iComType == c.TCP_IP ) {
                            int duration = 100 ;
                            if( retries > 10 ) {
                                duration = 1000;
                            } else {
                        //        duration = retries * 1000 ;
                            }
                            if( duration < 100) {
                                duration = 100;
                            }
                            CVNavi.sleep( duration );
                        }
                        jMcRwProgress.setString(bundle.getString("ReadWriteCV.Warte")+retries);
                        return;
                    }

                    // ist ein vollständiger Datensatz angekommen ?
                    bArray[bytesRead] = 0;

                    if(bReadDevices)
                    {
                        if( tmpBytesRead == -1 ) {
                            System.out.println(" MC tmpBytesRead == -1 bReadDevices A1" );
                            stopIOAction();
                            bWaitAnswerInProgress = false;
                            bReadDevices = false;
                            jMcRwInfo.setText("read: MC read MC/RB devices failed.");
                            CVNavi.mbDeviceReadProblem(CVNavi);
                            System.out.println(" MC tmpBytesRead == -1 bReadDevices A2" );
                            return;
                        }
                        if(connectedDevices != null)
                        {
                            String s = new String(bArray);
                            if(s.contains("END"))
                            {
                                bReadDevices = false;
                                stopIOAction();
                                s = s.substring(0, bytesRead);
                                connectedDevices.strDevs = s.split("\r");
                                connectedDevices.SetDevs();
                            }
                            // firmware pre 2.1.0 :
                            // ERROR: unknown command
                            // ]
                            if  (   (  s.contains("ERROR: unknown command")
                                    || s.contains("ERROR: not implemented")
                                    )
                                &&
                                    s.contains("]")
                                )
                            {
                                bReadDevices = false;
                                stopIOAction();
                                s = s.substring(0, bytesRead);
                                connectedDevices.strDevs = s.split("\r");
                                connectedDevices.SetDevs();
                            }
                        }
                        else
                        {
                            bWaitAnswerInProgress = false;
                            bReadDevices = false;
                            stopIOAction();
                        }
                    }
                    if(bAskLokState)
                    {
                        if( ! CVNavi.checkReadComplete(bArray) ) {
                            // incomplete -> wait for more
                            return;
                        }
                        bWaitAnswerInProgress = false;
                        String s = new String(bArray);
                        if( debugLevel >= 4 ) {
                            System.out.println("bAskLokState: s=\n"+s);
                        }

                        String str;
                        if(s.contains("L "))
                        {
                            if( debugLevel >= 2 ) {
                                System.out.println("bAskLokState: s contains L");
                            }
                            s = s.substring(2);
                            AktLokState = s.substring(0, s.indexOf("]")-1);
                            if( debugLevel >= 2 ) {
                                System.out.println("bAskLokState: s contains L: AktLokState=\""+AktLokState+"\"");
                            }
                            String text = jDisplay.getText();
                            s = s.substring(s.indexOf(" ")+1);
                            str = s.substring(0, s.indexOf(" "));
                            int V = 0;
                            try {
                                V = Integer.parseInt(str);
                            } catch (NumberFormatException numberFormatException) {
                            }
                            jGeschwindigkeit.setValue(V);
                            switch(Fahrstufen)
                            {
                                case 14:
                                    V = (V + 8)/9;
                                    break;

                                case 27:
                                    V = (V*3 + 11)/14;
                                    break;

                                case 28:
                                    V = (V*2 + 7)/9;
                                    break;
                            }
                            str = "" + V +"  ";
                            str = text.substring(0, 12) + str.substring(0, 3);
                            s = s.substring(s.indexOf(" ")+1);
                            if(s.contains("f"))
                            {
                                text = str + "^" + text.substring(16,28);
                            }
                            else
                            {
                                text = str + "v" + text.substring(16,28);
                            }
                            if(s.substring(0, 1).contains("1"))
                                text += "*";
                            else
                                text += "-";
                            if(s.substring(4, 5).contains("1"))
                            {
                                text += "*";
                                Funktionen[0] = 1;
                            }
                            else
                            {
                                text += "-";
                                Funktionen[0] = 0;
                            }
                            if(s.substring(6, 7).contains("1"))
                            {
                                text += "*";
                                Funktionen[1] = 1;
                            }
                            else
                            {
                                text += "-";
                                Funktionen[1] = 0;
                            }
                            if(s.substring(8, 9).contains("1"))
                            {
                                text += "*";
                                Funktionen[2] = 1;
                            }
                            else
                            {
                                text += "-";
                                Funktionen[2] = 0;
                            }
                            if(s.substring(10, 11).contains("1"))
                            {
                                text += "*";
                                Funktionen[3] = 1;
                            }
                            else
                            {
                                text += "-";
                                Funktionen[3] = 0;
                            }
                            if( debugLevel >= 3 ) {
                                System.out.println("bAskLokState: Display.setText:"+text);
                            }
                            jDisplay.setText(text);
                            if(bMustAskStatus)
                            {
                                s = "XLC " + AskedLokAdr + "\r";
                                if( debugLevel >= 2 ) {
                                    System.out.println("bAskLokState: bMustAskStatus "+s);
                                }
                                CVNavi.flushReadBuffer( Com );
                                resetbArray();
                                bMustAskStatus = false;
                                Com.write(s);
                                timer.setInitialDelay(CVNavi.timer1);
                                timer.setDelay(CVNavi.timer2);
                                timer.setRepeats(true);
                                bWaitAnswerInProgress = true;
                                retries = CVNavi.timerRetries;
                                timer.start();
                            }
                            else
                            {
                                bAskLokState = false;
                            }

                        }
                        else if(s.contains("unused"))
                        {
                            s = "XL " + AskedLokAdr + " 0 0 f 0 0 0 0\r";
                            if( debugLevel >= 2 ) {
                                System.out.println("bAskLokState: contains \"unused\" bMustAskStatus -> send: "+s );
                            }
                            CVNavi.flushReadBuffer( Com );
                            resetbArray();
                            bMustAskStatus = true;
                            Com.write(s);
                            timer.setInitialDelay(CVNavi.timer1);
                            timer.setDelay(CVNavi.timer2);
                            timer.setRepeats(true);
                            bWaitAnswerInProgress = true;
                            retries = CVNavi.timerRetries;
                            timer.start();
                        }
                        else
                        {
                            if(s.contains("DCC"))
                            {
                                str = "DCC  ";
                                AktLokFormat = "DCC ";
                                jf5.setEnabled(true);
                                jf6.setEnabled(true);
                                jf7.setEnabled(true);
                                jf8.setEnabled(true);
                                jf9.setEnabled(true);
                                jf10.setEnabled(true);
                                jf11.setEnabled(true);
                                jf12.setEnabled(true);
                                jf13.setEnabled(true);
                                jf14.setEnabled(true);
                                jf15.setEnabled(true);
                                jf16.setEnabled(true);
                                jf17.setEnabled(true);
                                jf18.setEnabled(true);
                                jf19.setEnabled(true);
                                jf20.setEnabled(true);
                                jf21.setEnabled(true);
                                jf22.setEnabled(true);
                                jf23.setEnabled(true);
                                jf24.setEnabled(true);
                                jf25.setEnabled(true);
                                jf26.setEnabled(true);
                                jf27.setEnabled(true);
                                jf28.setEnabled(true);
                                Fahrstufen = 126;
                            }
                            else if(s.contains("m3"))
                            {
                                str = "m3   ";
                                AktLokFormat = "m3  ";
                                jf5.setEnabled(true);
                                jf6.setEnabled(true);
                                jf7.setEnabled(true);
                                jf8.setEnabled(true);
                                jf9.setEnabled(true);
                                jf10.setEnabled(true);
                                jf11.setEnabled(true);
                                jf12.setEnabled(true);
                                jf13.setEnabled(true);
                                jf14.setEnabled(true);
                                jf15.setEnabled(true);
                                jf16.setEnabled(false);
                                jf17.setEnabled(false);
                                jf18.setEnabled(false);
                                jf19.setEnabled(false);
                                jf20.setEnabled(false);
                                jf21.setEnabled(false);
                                jf22.setEnabled(false);
                                jf23.setEnabled(false);
                                jf24.setEnabled(false);
                                jf25.setEnabled(false);
                                jf26.setEnabled(false);
                                jf27.setEnabled(false);
                                jf28.setEnabled(false);
                                Fahrstufen = 126;
                            }
                            else if(s.contains("Old"))
                            {
                                str = "MM1  ";
                                AktLokFormat = "MM1 ";
                                jf5.setEnabled(false);
                                jf6.setEnabled(false);
                                jf7.setEnabled(false);
                                jf8.setEnabled(false);
                                jf9.setEnabled(false);
                                jf10.setEnabled(false);
                                jf11.setEnabled(false);
                                jf12.setEnabled(false);
                                jf13.setEnabled(false);
                                jf14.setEnabled(false);
                                jf15.setEnabled(false);
                                jf16.setEnabled(false);
                                jf17.setEnabled(false);
                                jf18.setEnabled(false);
                                jf19.setEnabled(false);
                                jf20.setEnabled(false);
                                jf21.setEnabled(false);
                                jf22.setEnabled(false);
                                jf23.setEnabled(false);
                                jf24.setEnabled(false);
                                jf25.setEnabled(false);
                                jf26.setEnabled(false);
                                jf27.setEnabled(false);
                                jf28.setEnabled(false);
                                Fahrstufen = 14;
                            }
                            else if(s.contains("New"))
                            {
                                str = "MM2  ";
                                AktLokFormat = "MM2 ";
                                jf5.setEnabled(false);
                                jf6.setEnabled(false);
                                jf7.setEnabled(false);
                                jf8.setEnabled(false);
                                jf9.setEnabled(false);
                                jf10.setEnabled(false);
                                jf11.setEnabled(false);
                                jf12.setEnabled(false);
                                jf13.setEnabled(false);
                                jf14.setEnabled(false);
                                jf15.setEnabled(false);
                                jf16.setEnabled(false);
                                jf17.setEnabled(false);
                                jf18.setEnabled(false);
                                jf19.setEnabled(false);
                                jf20.setEnabled(false);
                                jf21.setEnabled(false);
                                jf22.setEnabled(false);
                                jf23.setEnabled(false);
                                jf24.setEnabled(false);
                                jf25.setEnabled(false);
                                jf26.setEnabled(false);
                                jf27.setEnabled(false);
                                jf28.setEnabled(false);
                                Fahrstufen = 14;
                            }
                            else
                            {
                                jf5.setEnabled(true);
                                jf6.setEnabled(true);
                                jf7.setEnabled(true);
                                jf8.setEnabled(true);
                                jf9.setEnabled(true);
                                jf10.setEnabled(true);
                                jf11.setEnabled(true);
                                jf12.setEnabled(true);
                                jf13.setEnabled(true);
                                jf14.setEnabled(true);
                                jf15.setEnabled(true);
                                jf16.setEnabled(true);
                                jf17.setEnabled(true);
                                jf18.setEnabled(true);
                                jf19.setEnabled(true);
                                jf20.setEnabled(true);
                                jf21.setEnabled(true);
                                jf22.setEnabled(true);
                                jf23.setEnabled(true);
                                jf24.setEnabled(true);
                                jf25.setEnabled(true);
                                jf26.setEnabled(true);
                                jf27.setEnabled(true);
                                jf28.setEnabled(true);
                                bAskLokState = false;               
                                return;
                            }
                            AktLokAdr = AskedLokAdr;
                            String txt = jDisplay.getText();
                            jDisplay.setText(txt.substring(0, 6) + "          \n                ");
                            LokName = " ";
                            for (int i = 0; i < jTableLoco.getRowCount(); i++)
                            {
                                Object oAdr = jTableLoco.getValueAt(i, 0);
                                String Adr = (String)oAdr;
                                try {
                                    if (AktLokAdr == Integer.parseInt(Adr)) {
                                        Adr = (String) jTableLoco.getValueAt(i, 2);
                                        if(Adr.charAt(0) == '2')
                                        {
                                            Adr = Adr.substring(0, 2);  //27a oder 27b kann parseInt nicht. Muss sich um Zahlen handeln...
                                        }
                                        Fahrstufen = Integer.parseInt(Adr);
                                        LokName = (String) jTableLoco.getValueAt(i, 3);
                                    }
                                } catch (NumberFormatException numberFormatException) {
                                    break;
                                }
                            }
                            String text = jDisplay.getText();
                            if(LokName.length() > 0)
                            {
                                s = LokName + "          ";
                                s = s.substring(0, 11); // c.MAX_LOC_NAME_LENGTH
                                jDisplay.setText(text.substring(0, 7) + str + text.substring(12, 17) + s + text.substring(29));
                            }
                            else
                            {
                                jDisplay.setText(text.substring(0, 7) + str + text.substring(12, 17) + "           " + text.substring(29));
                            }
                            s = "XL " + AskedLokAdr + "\r";
                            if( debugLevel >= 2 ) {
                                System.out.println("bAskLokState: near end -> send: "+s );
                                System.out.println("bAskLokState: near end END" );
                            }
                            CVNavi.flushReadBuffer( Com );
                            resetbArray();
                            Com.write(s);
                            timer.setInitialDelay(CVNavi.timer1);
                            timer.setDelay(CVNavi.timer2);
                            timer.setRepeats(true);
                            bWaitAnswerInProgress = true;
                            retries = CVNavi.timerRetries;
                            timer.start();
                        }
                    }
                    if(bReadPTdirekt)
                    {
                        if( ! CVNavi.checkReadComplete(bArray) ) {
                            // incomplete -> wait for more
                            return;
                        }
                        bWaitAnswerInProgress = false;
                        String s = new String(bArray);
                        System.out.println("LRLRLR Strings=\""+s+"\"" );
                        int cvWert = 0;
                        if( s.startsWith( "Error" ) ) {
                            cvWert = -1;
                        }
                        else
                        {
                            int indexOf = s.indexOf(" ");
                            if( indexOf >= 0 ) {
                                s = s.substring(0, indexOf);
                            }
                            try {
                                cvWert = Integer.parseInt(s);
                            } catch (NumberFormatException numberFormatException) {
                                cvWert = 255;
                            }
                        }
                        jWertDirekt.setText("" + cvWert);
                        bReadPTdirekt = false;
                        stopIOAction();
                        int CV = Integer.parseInt(jCV_Direkt.getText());
                        if(CV == 8)
                        jHerstellerInfo.setText(Hersteller(cvWert));
                    }
                    if(bReadPTList)
                    {
                        if( ! CVNavi.checkReadComplete(bArray) ) {
                            // incomplete -> wait for more
                            return;
                        }
                        bWaitAnswerInProgress = false;
                        String s = new String(bArray);
                        int indexOf = s.indexOf(" ");
                        if( indexOf >= 0 ) {
                            s = s.substring(0, indexOf);
                        }
                        int cvWert = 0;
                        try {
                            cvWert = Integer.parseInt(s);
                        } catch (NumberFormatException numberFormatException) {
                            cvWert = 255;
                        }
                        int Index = jCVListe.getSelectedIndex();
                        if(Index >= 0)
                        {
                            s = (String)jCVListe.getSelectedValue();
                            s = s.substring(0, 5) + "       " + cvWert;
                            ListModel model = jCVListe.getModel();
                            DefaultListModel dlm = new DefaultListModel();
                            for(int i = 0; i < model.getSize(); i++)
                            {
                                if(i == Index)
                                {
                                    dlm.add(i, s);
                                }
                                else
                                {
                                    Object elementAt = model.getElementAt(i);
                                    dlm.add(i, elementAt);
                                }
                            }
                            jCVListe.setModel(dlm);
                            int j = dlm.getSize();
                            if(j > Index)
                            {
                                CVNavi.flushReadBuffer( Com );
                                resetbArray();
                                jCVListe.setSelectedIndex(Index + 1);
                                s = (String)jCVListe.getSelectedValue();
                                s = "XPTRD" + s + "\r";
                                Com.write(s);
                                timer.setInitialDelay(CVNavi.timer1);
                                timer.setDelay(CVNavi.timer2);
                                timer.setRepeats(true);
                                bWaitAnswerInProgress = true;
                                retries = CVNavi.timerRetries * 2;
                                timer.start();
                            }
                            else
                            {
                                bReadPTList = false;
                                stopIOAction();
                            }
                        }
                        else
                        {
                            bReadPTList = false;
                            stopIOAction();
                        }
                    }
                    if(bWriteList)
                    {
                        if( ! CVNavi.checkReadComplete(bArray) ) {
                            // incomplete -> wait for more
                            return;
                        }
                        bWaitAnswerInProgress = false;
                        String s = new String(bArray);
                        if(s.contains("Ok"))
                        {
                            int Index = jCVListe.getSelectedIndex();
                            if(Index >= 0)
                            {
                                s = (String)jCVListe.getSelectedValue();
                                ListModel model = jCVListe.getModel();
                                int j = model.getSize();
                                if(j != Index + 1)
                                {
                                    CVNavi.flushReadBuffer( Com );
                                    resetbArray();
                                    jCVListe.setSelectedIndex(Index + 1);
                                    s = (String)jCVListe.getSelectedValue();
                                    if(jHauptGleis.isSelected())
                                    {
                                        int DecAdr;
                                        try {
                                            DecAdr = Integer.parseInt(jDecAdr.getText());
                                        } catch (NumberFormatException numberFormatException) {
                                            DecAdr = 3;
                                        }
                                        s = "XPD" + DecAdr + s + "\r";
                                    }
                                    else
                                    {
                                        s = "XPTWD" + s + "\r";
                                    }
                                    Com.write(s);
                                    timer.setInitialDelay(CVNavi.timer1);
                                    timer.setDelay(CVNavi.timer2);
                                    timer.setRepeats(true);
                                    bWaitAnswerInProgress = true;
                                    retries = CVNavi.timerRetries * 2;
                                    timer.start();
                                }
                                else
                                {
                                    bWriteList = false;
                                    stopIOAction();
                                }
                            }
                        }
                        else
                        {
                            bWriteList = false;
                            stopIOAction();
                        }
                    }
                    if(bProg_m3)
                    {
                        if( ! CVNavi.checkReadComplete(bArray) ) {
                            // incomplete -> wait for more
                            return;
                        }
                        bWaitAnswerInProgress = false;
                        System.out.println();
                        String s = new String(bArray);
                        if( s.startsWith("ERROR") ) {
                            s = "ERROR" ;
                        }
                        else
                        {
                            s = "0x" + s.substring(0, 8).toLowerCase();
                            jm3GotoList.setEnabled(true);
                        }
                        jmfxUID.setText(s);
                        bProg_m3 = false;
                        jm3Schreiben.setEnabled(true);
                        stopIOAction();
                    }
                    if(bReadStatus){
                        System.out.println("2903 PRE  checkCfgReadComplete: bytesRead="+bytesRead );
                        if( ! CVNavi.checkReadComplete(bArray) ) {
                            // incomplete -> wait for more
                            System.out.println("2775 POST checkCfgReadComplete: INCOMPLETE");
                            return;
                        }
                        timer.stop();
                        System.out.println("2910 POST checkCfgReadComplete: COMPLETE");
                        bWaitAnswerInProgress = false;
                        // Datensatz vollständig -> Teste/Analysiere erwarteten Wert
                    }
                    if(bReadCfg) {
                        jMcRwInfo.setText("read: MC config read in progress ("+bytesRead+")");
                        if( ! CVNavi.checkReadComplete(bArray) ) {
                            // incomplete -> wait for more
                            return;
                        }
                        timer.stop();
                        bWaitAnswerInProgress = false;
                        jMcRwProgress.setValue(++readWriteProgress);
                        // Datensatz vollständig -> Teste/Analysiere erwarteten Wert
                    }
                    if(bReadRC) {
                        jMcRwInfo.setText("read: MC RailCom option read in progress ("+bytesRead+")");

                        if( ! CVNavi.checkReadComplete(bArray) ) {
                            // incomplete -> wait for more
                            return;
                        }
                        timer.stop();
                        System.out.println("2809 POST checkRCReadComplete: COMPLETE bytesRead="+bytesRead);
                        bWaitAnswerInProgress = false;
                        jMcRwProgress.setValue(++readWriteProgress);
                        // Datensatz vollständig -> Teste/Analysiere erwarteten Wert
                    }
                    if(bReadSo999) {
                        jMcRwInfo.setText("read: MC SO 999 option read in progress ("+bytesRead+")");

                        if( bytesRead == 0 ) {
                            return;
                        }
                        // check 1st byte for error code
                        byte[] mYbArray = new byte[1];
                        System.arraycopy(bArray, 0, mYbArray, 0, 1);
                        Boolean ok = CVNavi.checkMCAnswerByte( outerThis, mYbArray, (debugLevel > 0));
                        if( ( ! ok) && ( debugLevel == 0 ) ) {
                            CVNavi.checkMCAnswerByte( outerThis, mYbArray, true);
                        }
                        if( debugLevel > 0 ) {
                            System.out.println("mYbArray("+bytesRead+")=0x"+CVNavi.byteArrayToHex(mYbArray)+ " OK="+ok);
                            System.out.println("readProgress="+readWriteProgress);
                        }
                        if( ok && ( bytesRead == 1 ) ) {
                            // "OK" arrived but data byte missing
                            return;
                        }
                        if( ok && (bytesRead == 2 )) {
                            so999Value = bArray[1] & 0xFF ;
                            System.out.println("bReadSo999 : new value = "+ so999Value );
                            updateSO999checkboxes( so999Value );
                        }

                        timer.stop();
                        System.out.println("3030 POST checkSO999ReadComplete: COMPLETE bytesRead="+bytesRead);
                        bWaitAnswerInProgress = false;
                        jMcRwProgress.setValue(++readWriteProgress);
                        // Datensatz vollständig analysiert
                    }

                    if(bWriteCfg) {
                        if( CVNavi.debugLevel > 1 ) {
                            jMcRwInfo.setText("read: MC config write in progress ("+bytesRead+")");
                        }
                        if( bWriteSo999 ) {
                            // expect exaclty one byte as answer from MC
                            System.out.println("bWriteCfg &&  bWriteSo999 : #bytes="+ bytesRead);
                            if( bytesRead == 0 ) {
                                return;
                            }
                            byte[] mYbArray = new byte[bytesRead];
                            System.arraycopy(bArray, 0, mYbArray, 0, bytesRead);
                            Boolean ok = CVNavi.checkMCAnswerByte( outerThis, mYbArray, (debugLevel > 0));
                            if( ( ! ok ) && ( debugLevel == 0 ) ) {
                                CVNavi.checkMCAnswerByte( outerThis, mYbArray, true);
                            }
                            if( debugLevel > 0 ) {
                                System.out.println("mYbArray("+bytesRead+")=0x"+CVNavi.byteArrayToHex(mYbArray)+ " OK="+ok);
                                System.out.println("readProgress="+readWriteProgress);
                            }
                            bWriteSo999 = false;
                        }
                        else if( ! CVNavi.checkReadComplete(bArray) ) {
                            // incomplete -> wait for more
                            return;
                        }
                        timer.stop();
                        bWaitAnswerInProgress = false;
                        jMcRwProgress.setValue(++readWriteProgress);
                        // Datensatz vollständig -> Teste/Analysiere Antwort
                    }
                    if(bReadS88num) {
                        if( tmpBytesRead == -1 ) {
                            System.out.println(" MC tmpBytesRead == -1 bReadS88num A1" );
                            stopIOAction();
                            bWaitAnswerInProgress = false;
                            bReadS88num = false;
                            jMcRwInfo.setText("read: MC read number of s88 devices failed.");
                            CVNavi.mbDeviceReadProblem(CVNavi);
                            System.out.println(" MC tmpBytesRead == -1 bReadS88num A2" );
                        } else {
                            if( ! CVNavi.checkReadComplete(bArray) ) {
                                // incomplete -> wait for more
                                return;
                            }
                            System.out.println(" MC tmpBytesRead == -1 bReadS88num B1" );
                            stopIOAction();
                            bWaitAnswerInProgress = false;
                            bReadS88num = false;
                            checkS88num(bArray, bytesRead);
                            System.out.println(" MC tmpBytesRead == -1 bReadS88num B2" );
                        }
                    }
                    if(bReadS88value) {
                        if( ! CVNavi.checkReadComplete(bArray) ) {
                            // incomplete -> wait for more
                            return;
                        }
                        pauseS88read();
                        if( debugLevel > 2 ) {
                            System.out.println("bReadS88value: bytesRead="+bytesRead);
                        }
                        bWaitAnswerInProgress = false;
                        checkS88read(bArray, bytesRead);
                        startS88read();
                    }
                }

                // Tests und Analysen
                if( bWriteM3sid ) {
                    // Xm3Sid expects exaclty one byte as answer from MC
                    int nBytes;
                    nBytes = Com.read(bArray);
                    timer.stop();
                    jMcM3Info.setText("Xm3Sid: write in progress");
                    jMcM3Progress.setValue(++readWriteProgress);
                    byte[] mYbArray = new byte[nBytes];
                    System.arraycopy(bArray, 0, mYbArray, 0, nBytes);
                    Boolean ok = CVNavi.checkMCAnswerByte( outerThis, mYbArray, (debugLevel > 0));
                    if( debugLevel > 0 ) {
                        System.out.println("mYbArray("+nBytes+")=0x"+CVNavi.byteArrayToHex(mYbArray)+ " OK="+ok);
                        System.out.println("readProgress="+readWriteProgress);
                    }

                    if(nBytes < 1)
                    {
                        CVNavi.mbTimeout( outerThis, c.mbMCRDcommitErr );
                        jMcM3Info.setText("Xm3Sid: write finished with error");
                    } else {
                        jMcM3Info.setText("Xm3Sid: write finished");
                    }
                    bWriteM3sid = false;
                    stopIOAction();
                    return;
                }

                if( bWriteM3sidList ) {
                    // Xm3Sid expects exaclty one byte as answer from MC
                    int nBytes;
                    nBytes = Com.read(bArray);
                    timer.stop();
                    jMcM3Info.setText("Xm3Sid: write list in progress");
                    jMcM3Progress.setValue(++readWriteProgress);
                    byte[] mYbArray = new byte[nBytes];
                    System.arraycopy(bArray, 0, mYbArray, 0, nBytes);
                    Boolean ok = CVNavi.checkMCAnswerByte( outerThis, mYbArray, (debugLevel > 0));
                    if( debugLevel > 0 ) {
                        System.out.println("mYbArray("+nBytes+")=0x"+CVNavi.byteArrayToHex(mYbArray)+ " OK="+ok);
                        System.out.println("readProgress="+readWriteProgress+" M3used="+M3used);
                    }

                    if(nBytes < 1)
                    {
                        CVNavi.mbTimeout( outerThis, c.mbMCRDcommitErr );
                        jMcM3Info.setText("Xm3Sid: write list commit missing");
                        bWriteM3sidList = false;
                        stopIOAction();
                        return;
                    } else {
                        jMcM3Info.setText("Xm3Sid: write committed");
                    }
                    if( ( readWriteProgress / 2 ) == M3used ) {
                        // commit for last dataset
                        if( debugLevel > 0 ) {
                            System.out.println("last dataset commited readProgress="+readWriteProgress);
                        }
                        bWriteM3sidList = false;
                        stopIOAction();
                        jMcM3Info.setText("Xm3Sid: write list finished");
                        return;
                    }
                    // write next
                    int listIdx = readWriteProgress / 2 ;
                    long lM3UID = Long.decode( M3liste[0][listIdx] );
                    int iAdr = Integer.decode( M3liste[1][listIdx] );
                    if( debugLevel > 0 ) {
                        System.out.println("lM3UID="+String.format("%8s", Long.toHexString( lM3UID )).replace(' ', '0') + " iAdr="+iAdr);
                    }

                    byte[] wArray = new byte[8];
                    wArray[0] = (byte) 0x78; // x
                    wArray[1] = (byte) 0x87; // Xm3Sid (requires 1.4.7b)
                    wArray[2] = (byte) (   iAdr           & 0xFF );
                    wArray[3] = (byte) ( ( iAdr >> 8 )    & 0xFF );
                    wArray[4] = (byte) (   lM3UID         & 0xFF );
                    wArray[5] = (byte) ( ( lM3UID >>  8 ) & 0xFF );
                    wArray[6] = (byte) ( ( lM3UID >> 16 ) & 0xFF );
                    wArray[7] = (byte) ( ( lM3UID >> 24 ) & 0xFF );
                    Com.write(wArray);

                    if( debugLevel > 0 ) {
                        System.out.println("written to MC wArray=0x"+CVNavi.byteArrayToHex(wArray));
                    }
                    jMcM3Info.setText("Xm3Sid: write list in progress");
                    jMcM3Progress.setValue(++readWriteProgress);
                    timer.start();
                }

                if(bReadStatus) {
                    // Status ist vollständig angekommen
                    bReadStatus = false;
                    // es sind "bytesRead" Bytes an Daten in "bArray" verfügbar
                    String str = new String(bArray);
                    String[] strArr = str.split("\r");

                    stopIOAction();
                    resetbArray();

                    MZ pre = CVNavi.getMZ();
                    if( debugLevel > 0 ) {
                        System.out.println("bReadStatus="+strArr[0]+ " MZ pre="+pre.toString() );
                    }

                    CVNavi.setModeZentrale(strArr[0]);
                    return;
                }

                if(bReadCfg) {
                    // Konfig ist vollständig angekommen
                    timer.stop();
                    bReadCfg = false;

                    String str = new String(bArray);
                    if( debugLevel > 0 ) {
                        System.out.println("Parser 1 nBytes="+bytesRead+" str.length="+str.length() );
                    }
                    int tmpMcRwMax = jMcRwProgress.getMaximum();
                    validMcData = parseInputArray( CVNavi.gsSchnittstelle, str );
                    if( validMcData ) {
                        reCheckVersionInfo();
                    }
                    jMcRwProgress.setMaximum(tmpMcRwMax);

                    jDatenQuelle.setText(CVNavi.gsSchnittstelle);
                    jMcRwInfo.setText("read: MC config read finished");
                    jMcRwProgress.setValue(++readWriteProgress);

                    String s = "xRC\r";
                    Com.write(s);
                    resetbArray();
                    retries = CVNavi.timerRetries;
                    jMcRwProgress.setString(null);
                    bWaitAnswerInProgress = true;
                    timer.setInitialDelay(CVNavi.MCtimer1);
                    timer.setDelay(CVNavi.MCtimer2);
                    timer.start();
                    jMcRwInfo.setText("read: MC RailCom option read started");
                    jMcRwProgress.setValue(++readWriteProgress);
                    bReadRC = true;
                    return;
                }

                if(bReadRC) {
                    // es sind "bytesRead" Bytes an Daten in "bArray" verfügbar
                    String str = new String(bArray);
                    String[] strArr = str.split("\r");
                    bReadRC = false;
                    if (strArr[0].startsWith("RC ")) {
                        try {
                            rcValue = Integer.parseInt(strArr[0].substring(3));
                            updateRailComCheckboxes(rcValue);
                            if( debugLevel > 0 ) {
                                System.out.println("readRC strArr[0].length["+strArr[0].length()+"] rcValue["+rcValue+"]" );
                            }
                        } catch ( Exception ex ) {
                            System.out.println("readRC EXCEPTION="+ex.toString() );                            
                            System.out.println("readRC bArray=" );
                            CVNavi.dumpbArray(bArray);
                        }
                        if( debugLevel >= 4 ) {
                            System.out.println("readRC bArray=" );
                            CVNavi.dumpbArray(bArray);
                        }
                        jMcRwInfo.setText("read: MC read finished");
                    } else {
                        System.out.println("readRC ERROR strArr[0].length["+strArr[0].length()+"] strArr[0]=\""+strArr[0]+"\"" );
                        jMcRwInfo.setText("read: MC read finished with ERROR");
                    }

                    jMcRwInfo.setText("read: MC read finished");
                    jMcRwProgress.setValue(++readWriteProgress);
                    if( debugLevel > 0 ) {
                        System.out.println("readProgress="+readWriteProgress);
                    }

                    if( CVNavi.bUseSo999 ) { // (at least 1.4.8c was detected)
                        lastCmd = "XSoGet 999 " ;
                        System.out.println("read: Booster option s["+lastCmd+"]" );
                        byte[] wArray = new byte[4];
                        wArray[0] = (byte) 0x78; // x
                        wArray[1] = (byte) 0xa4; // XSoGet (requires 1.4.8c)
                        wArray[2] = (byte) (   999        & 0xFF );  // SO low byte
                        wArray[3] = (byte) ( ( 999 >> 8 ) & 0xFF );  // SO high byte
                        Com.write(wArray);
                        bReadSo999 = true;
                        resetbArray();
                        retries = CVNavi.timerRetries;
                        jMcRwProgress.setString(null);
                        bWaitAnswerInProgress = true;
                        timer.setInitialDelay(CVNavi.MCtimer1);
                        timer.setDelay(CVNavi.MCtimer2);
                        timer.start();
                        jMcRwInfo.setText("read: MC SO 999 option read started");
                        jMcRwProgress.setValue(++readWriteProgress);
                    }
                    else {
                        stopIOAction();
                        resetbArray();
                    }
                    return;
                }

                if(bReadSo999) {
                    jMcRwInfo.setText("read: MC SO 999 read finished");
                    bReadSo999 = false;
                    stopIOAction();
                    resetbArray();
                }

                if(bWriteCfg) {
                    // es sind "bytesRead" Bytes an Daten in "bArray" verfügbar, endend mit "\r]"
                    // Ausnahme: erster Aufruf. Dann gibt es noch keine Daten znd es mußdie erste Anfrage generiert werden !
                    if( debugLevel > 0 ) {
                        System.out.println("bWriteCfg bWaitAnswerInProgresss["+bWaitAnswerInProgress+"] bytesRead=["+bytesRead+"] nextWriteJob["+nextWriteJob+"] locIdx["+locIdx+"] traIdx["+traIdx+"] magIdx["+magIdx+"]" );
                    }
                    if( bytesRead > 0 ) {
                        String str = new String(bArray);
                        String[] strArr = str.split("\r");
                        if( debugLevel > 0 ) {
                            System.out.println("bWriteCfg bWaitAnswerInProgresss["+bWaitAnswerInProgress+"] nextWriteJob["+nextWriteJob+"] locIdx["+locIdx+"] traIdx["+traIdx+"] magIdx["+magIdx+"]" );
                            System.out.println("bWriteCfg bytesRead=["+bytesRead+"] strArr.length="+strArr.length );
                            if( debugLevel >= 3 ) {
                                for( int i = 0 ; i < strArr.length ; i++ ) {
                                    System.out.println("bWriteCfg strArr["+i+"]=\""+strArr[i]+"\"" );
                                }
                            }
                        }
                        if( strArr[0].toUpperCase().startsWith("ERROR: UNKNOWN COMMAND") && locListWriteErrors < CVNavi.maxLocListWriteErrors) {
                            locListWriteErrors++;
                            locIdx--;
                            System.out.println(" write loco list problem ("+locListWriteErrors+"/"+CVNavi.maxLocListWriteErrors+") locIdx decreased by 1  locIdx="+locIdx+" resend command \""+lastCmd+"\"");
                            if( locListWriteErrors < 10 ) {
                                CVNavi.sleep(250);
                                CVNavi.sleep(750);
                            } else {
                                CVNavi.sleep(1000);
                            }
                        } else if( strArr[0].toUpperCase().startsWith("ERROR: ")) {
                            System.out.println("ERROR detected : "+strArr[0] );
                            if( debugLevel > 0 ) {
                                System.out.println("bWriteCfg : DBGtmp nextWriteJob="+nextWriteJob+" locIdx="+locIdx+" lastCmd=### "+lastCmd+" ###" );
                            }
                            CVNavi.mbConfigWriteError(CVNavi, strArr[0] );
                        }
                    }

                    switch( nextWriteJob ) {
                        case 0: // system basics
                            nextWriteJob++;
                            sysIdx++;
                            if( ! jWRsys.isSelected()) {
                                jMcRwInfo.setText("write: skip basic config");
                                System.out.println("write: skip basic config");
                            } else {
                                jMcRwInfo.setText("write: basic config");
                                lastCmd = "xcfgsys ";
                                if(jDCC_Booster.isSelected())
                                    lastCmd += "DCC, ";
                                else
                                    lastCmd += "MM, ";
                                int KSE;
                                try {
                                    KSE = Integer.parseInt(jKurzEmpf.getText());
                                    KSE /= 5;
                                } catch (NumberFormatException numberFormatException) {
                                    KSE = 20;
                                }
                                lastCmd += KSE;
                                lastCmd += ", ";
                                if(jDCC_Loks.isSelected())
                                    lastCmd += "DCC, ";
                                else
                                    lastCmd += "MM, ";
                                if(jLangePause.isSelected())
                                    lastCmd += "Y, ";
                                else
                                    lastCmd += "N, ";
                                lastCmd += js88.getText();
                                lastCmd += ", ";
                                lastCmd += jBaud.getSelectedItem().toString();
                                System.out.println("write: basic config cmd["+lastCmd+"]");
                                lastCmd += "\r";
                                Com.write(lastCmd);
                                resetbArray();
                                retries = CVNavi.timerRetries;
                                jMcRwProgress.setString(null);
                                bWaitAnswerInProgress = true;
                            }
                            timer.start();
                            break;
                        case 1: // accessory timer
                            nextWriteJob++;
                            sysIdx++;
                            if( ! jWRsys.isSelected() ) {
                                jMcRwInfo.setText("write: skip accessory timer option");
                                System.out.println("write: skip accessory timer option");
                            } else {
                                jMcRwInfo.setText("write: accessory timer options\n");
                                int minMag = Integer.parseInt(jMinMag.getText())/50;
                                int maxMag = Integer.parseInt(jMaxMag.getText())/50;
                                lastCmd = "XMT " + minMag + ", " + maxMag + "\r";
                                if( debugLevel > 0 ) {
                                    System.out.println("write: accessory timer options min["+jMinMag.getText()+"] max["+jMaxMag.getText()+"] -> cmd["+lastCmd+"]" );
                                }
                                System.out.println("write: accessory timer options s["+lastCmd+"]" );
                                Com.write(lastCmd);
                                resetbArray();
                                retries = CVNavi.timerRetries;
                                jMcRwProgress.setString(null);
                                bWaitAnswerInProgress = true;
                            }
                            timer.start();
                            break;
                        case 2: // railcom option
                            nextWriteJob++;
                            sysIdx++;
                            if( jWRsys.isSelected() ) {
                                jMcRwInfo.setText("write: RailCom option");
                                rcValue = getRailComValueFromCheckboxes();
                                if( debugLevel > 0 ) {
                                    System.out.println("write: RailCom option ["+rcValue+"]");
                                }
                                lastCmd = "XRC " + rcValue + "\r";
                                System.out.println("write: RailCom option s["+lastCmd+"]" );
                                Com.write(lastCmd);
                                resetbArray();
                                retries = CVNavi.timerRetries;
                                jMcRwProgress.setString(null);
                                bWaitAnswerInProgress = true;
                            } else {
                                jMcRwInfo.setText("write: skip RailCom option");
                                System.out.println("write: skip RailCom option");
                            }
                            timer.start();
                            break;
                        case 3: // booster option
                            nextWriteJob++;
                            sysIdx++;
                            if( jWRsys.isSelected() && CVNavi.bUseSo999 ) { // (at least 1.4.8c was detected)
                                jMcRwInfo.setText("write: Booster option");
                                updateSo999Value();
                                if( debugLevel > 0 ) {
                                    System.out.println("write: Booster option ["+so999Value+"]");
                                }
                                lastCmd = "XSoSet 999 " + so999Value ;
                                System.out.println("write: Booster option s["+lastCmd+"]" );
                                byte[] wArray = new byte[5];
                                wArray[0] = (byte) 0x78; // x
                                wArray[1] = (byte) 0xa3; // XSoSet (requires 1.4.8c)
                                wArray[2] = (byte) (   999        & 0xFF );  // SO low byte
                                wArray[3] = (byte) ( ( 999 >> 8 ) & 0xFF );  // SO high byte
                                wArray[4] = (byte) (   so999Value & 0xFF );  // SO value (1 byte)
                                Com.write(wArray);
                                bWriteSo999 = true;
                                resetbArray();
                                retries = CVNavi.timerRetries;
                                jMcRwProgress.setString(null);
                                bWaitAnswerInProgress = true;
                            } else {
                                jMcRwInfo.setText("write: skip Booster option");
                                System.out.println("write: skip Booster option");
                            }
                            timer.start();
                            break;
                        case 4: // clear loco list
                            nextWriteJob++;
                            sysIdx++;
                            if( ! jWRloc.isSelected() ) {
                                jMcRwInfo.setText("write: skip clear loco list");
                                System.out.println("write: skip clear loco list");
                            } else {
                                jMcRwInfo.setText("write: clear loco list");
                                lastCmd = "XLOCCLEAR\r";
                                System.out.println("write: clear loco list s["+lastCmd+"]" );
                                Com.write(lastCmd);
                                resetbArray();
                                // 1 second per loco + 50 reserve
                                retries = Math.max(CVNavi.timerRetries, c.MAX_LOCS+50);
                                jMcRwProgress.setString(null);
                                bWaitAnswerInProgress = true;
                            }
                            timer.start();
                            break;
                        case 5: // locos
                            if( ! jWRloc.isSelected() ) {
                                nextWriteJob++;
                                jMcRwInfo.setText("write: skip locos");
                                System.out.println("write: skip locos");
                                locIdx = c.MAX_LOCS;
                                break;
                            }
                            while( bWaitAnswerInProgress == false)
                            {
                                // assumption jTableLoco was checked successfully (or automatically repaired)
                                // -> all jTableLoco entries are OK and we can skip most validation checks here

                                // send only valid loco addresses
                                if(locIdx < c.MAX_LOCS) 
                                {
                                    WriteLokInDB = 0;
                                    Object oAdr = jTableLoco.getValueAt(locIdx, 0);
                                    String sAdr = "" + oAdr;
                                    if( sAdr.trim().length() > 0) {
                                        String sFormat = "" + jTableLoco.getValueAt( locIdx, 1);
                                        String sFS     = "" + jTableLoco.getValueAt( locIdx, 2);
                                        String sName = null;

                                        Object oName = jTableLoco.getValueAt( locIdx, 3);
                                        if( oName != null ) {
                                            sName = "" + oName;
                                            if ( sName.length() > CVNavi.userLocoNameMax ) {
                                                System.out.println(" MC nextWriteJob : ### sName.length("+sName+")="+sName.length()+" ### shorten to length "+CVNavi.userLocoNameMax+" . " );
                                                sName = sName.substring(0, CVNavi.userLocoNameMax);
                                            }
                                        }

                                        if( (sName != null) && (sName.length() >  0) )
                                            lastCmd = "XLOCADD " + sAdr + ", " + sFS + ", " + sFormat + ", \"" + sName + "\"\r";
                                        else
                                            lastCmd = "XLOCADD " + sAdr + ", " + sFS + ", " + sFormat + "\r";
                                        if( debugLevel > 2 ) {
                                            System.out.println("write: loco["+locIdx+"] ( "+locIdx+" / "+locosConfigured+" ) ["+lastCmd+"]" );
                                        } else {
                                            System.out.println(" write: loco["+locIdx+"] s["+lastCmd+"]" );
                                        }
                                        Com.write(lastCmd);
                                        resetbArray();
                                        retries = CVNavi.timerRetries;
                                        jMcRwProgress.setString(null);
                                        bWaitAnswerInProgress = true;
                                        jMcRwInfo.setText("write: loco ( "+(locIdx+1)+" / "+locosConfigured+" )");
                                        if( CVNavi.iComType == c.TCP_IP ) {
                                            if( locListWriteErrors < 10 ) {
                                                CVNavi.sleep(250);
                                            } else {
                                                CVNavi.sleep(1000);
                                            }
                                        }
                                    }
                                    locIdx++;
                                }

                                if( locIdx >= c.MAX_LOCS ) {
                                    if(WriteLokInDB < FuncIcons.length)
                                    {
                                        if(FuncIcons[WriteLokInDB].length() > 1)
                                        {
                                            lastCmd = "XFM " + FuncIcons[WriteLokInDB] + "\r";
                                            Com.write(lastCmd);
                                            resetbArray();
                                            retries = CVNavi.timerRetries;
                                            jMcRwProgress.setString(null);
                                            bWaitAnswerInProgress = true;
                                            jMcRwInfo.setText("write: FM for locos");
                                            WriteLokInDB++;
                                        }
                                        else
                                        {
                                            WriteLokInDB = 0;
                                            // end of loco list
                                            nextWriteJob++;
                                            break;
                                        }
                                    }
                                    else
                                    {
                                        WriteLokInDB = 0;
                                        // end of loco list
                                        nextWriteJob++;
                                        break;
                                    }
                                }
                            }
                            timer.start();
                            break;
                        case 6: // clear traction list
                            nextWriteJob++;
                            sysIdx++;
                            if( ! jWRtra.isSelected() ) {
                                jMcRwInfo.setText("write: skip clear traction list");
                                System.out.println("write: skip clear traction list");
                            } else {
                                jMcRwInfo.setText("write: clear traction list");
                                lastCmd = "XTRKCLEAR\r";
                                System.out.println("write: traction s["+lastCmd+"]" );
                                Com.write(lastCmd);
                                resetbArray();
                                // 1 second per traction + 50 reserve
                                retries = Math.max(CVNavi.timerRetries, c.MAX_TRACTIONS+50);
                                jMcRwProgress.setString(null);
                                bWaitAnswerInProgress = true;
                            }
                            timer.start();
                            break;
                        case 7: // tractions
                            if( ! jWRtra.isSelected() ) {
                                nextWriteJob++;
                                jMcRwInfo.setText("write: skip tractions");
                                System.out.println("write: skip tractions");
                                traIdx = c.MAX_TRACTIONS;
                                break;
                            }
                            while( (bWaitAnswerInProgress == false) && (traIdx < c.MAX_TRACTIONS) )
                            {
                                // assumption jTableTraction was checked successfully
                                // -> all jTableTraction entries are OK and we can skip validation checks here

                                String sAdr1 = (""+jTableTraction.getValueAt(traIdx, 0)).trim();
                                String sAdr2 = (""+jTableTraction.getValueAt(traIdx, 2)).trim();
                                if( (sAdr1.length() > 0) && (sAdr2.length() > 0) ) {
                                    lastCmd = "XTRKADD " + sAdr1 + ", " + sAdr2 + "\r";
                                    System.out.println("write: traction s["+lastCmd+"]" );
                                    Com.write(lastCmd);
                                    resetbArray();
                                    retries = CVNavi.timerRetries;
                                    jMcRwProgress.setString(null);
                                    bWaitAnswerInProgress = true;
                                    jMcRwInfo.setText("write: traction ("+traIdx+"/"+c.MAX_TRACTIONS+")");
                                    if( debugLevel >= 2 ) {
                                        System.out.println("write: traction ("+traIdx+"/"+c.MAX_TRACTIONS+")");
                                    }
                                }
                                traIdx++;
                            }
                            if( traIdx >= c.MAX_TRACTIONS ) {
                                // end of traction list
                                nextWriteJob++;
                            }
                            timer.start();
                            break;
                        case 8: // accessories
                            if( ! jWRmag.isSelected() ) {
                                nextWriteJob++;
                                jMcRwInfo.setText("write: skip accessory list");
                                System.out.println("write: skip accessory list");
                                traIdx = c.MAX_MM1_ACCMOD;
                                break;
                            }                            
                           while( (bWaitAnswerInProgress == false) && (magIdx < c.MAX_MM1_ACCMOD) )
                            {
                                // assumption jTableAccessory was checked successfully
                                // -> all jTableAccessory entries are OK and we can skip some validation checks here

                                String sIdx = (""+jTableAccessory.getValueAt(magIdx, 0)).trim();
                                String sFmt = (""+jTableAccessory.getValueAt(magIdx, 1)).trim().toUpperCase();
                                if( (sIdx.length() > 0) && (sFmt.length() > 0 ) ) {
                                    String[] sIdxArr = sIdx.split(" ");
                                    int n = CVNavi.checkAndGetStrNumRangeDef( null, sIdxArr[0], 1, c.MAX_MM1_ACCMOD, 0, false);

                                    // module# is 1 less than displayed in table
                                    lastCmd = "XCFGACC " + (n-1) + ", " + sFmt + "\r";
                                    if( CVNavi.debugLevel > 0 ) {
                                        System.out.println("magIdx="+magIdx+" sIdx="+sIdx+" sIdxArr[0]="+sIdxArr[0]+" n="+n);
                                        System.out.println("write: accessories s["+lastCmd+"]" );
                                    }
                                    System.out.print("write: accessories s["+lastCmd+"]" );
                                    Com.write(lastCmd);
                                    resetbArray();
                                    retries = CVNavi.timerRetries;
                                    jMcRwProgress.setString(null);
                                    bWaitAnswerInProgress = true;
                                    jMcRwInfo.setText("write: accessories ("+magIdx+"/"+c.MAX_MM1_ACCMOD+")");
                                    if( debugLevel >= 3 ) {
                                        System.out.println("write: accessories ("+magIdx+"/"+c.MAX_MM1_ACCMOD+") "+lastCmd);
                                    }
                                    CVNavi.sleep( 100 );
                                }
                                magIdx++;
                            }
                            if( magIdx >= c.MAX_MM1_ACCMOD ) {
                                // end of traction list
                                nextWriteJob++;
                            }
                            timer.start();
                            break;
                        case 9:
                            nextWriteJob++;
                            sysIdx++;
                            timer.start();
                            break;
                        default:
                            String out = "write: finished";
                            if( ( debugLevel > 0 ) || (locListWriteErrors > 0 ) ) {
                                out += " with "+locListWriteErrors+" of max "+CVNavi.maxLocListWriteErrors+" loco list write errors";
                            }
                            jMcRwInfo.setText(out);
                            System.out.println(out);
                            System.out.println("write: sysIdx["+sysIdx+"] locIdx["+locIdx+"] traIdx["+traIdx+"] magIdx["+magIdx+"]" );
                            jMcRwProgress.setValue(c.MAX_SYSWRITES+c.MAX_LOCS+c.MAX_TRACTIONS+c.MAX_MM1_ACCMOD);
                            bWriteCfg = false;
                            stopIOAction();
                            return;
                    }
                    // calculate sM3UID for count
                    if( debugLevel >= 2 ) {
                        System.out.println("write: sysIdx["+sysIdx+"] locIdx["+locIdx+"] traIdx["+traIdx+"] magIdx["+magIdx+"]" );
                    }
                    // jMcRwProgress.setValue(count);
                    jMcRwProgress.setValue(sysIdx+locIdx+traIdx+magIdx);
                    return;
                }

                if(bUpdate) {
/*
 * Es gibt zwei Blockgrößen, die die MC anfordert.
 * folgende Zeichen können von der MC kommen:
 * *    -   letzter Block wurde erfolgreich übertragen (256 Bytes)
 * +    -   letzter Block wurde erfolgreich übertragen (128 Bytes)
 * #    -   Block wiederholen (256 Bytes)
 * ?    -   Block wiederholen (128 Bytes)
 * A    -   Adress-Error
 * C    -   CRC-Error
 * T    -   Time out Error
 * L    -   Length-Error
 * ac muss in 128 oder 256 Byte-Blöcke binär aufgeteilt werden dies passiert zur Laufzeit
 * Dazu wird ac zuerst komplett in binär gewandelt und in UpdateData gespeichert
*/
                    int BlockNrTemp;
                    int blockSize = 256;
                    bArray[0] = 0;
                    int n = Com.read(bArray);
                    if( n == 0 ) {
                        if( debugLevel >= 2 ) {
                            System.out.println("update: read 0 bytes and TimeOut="+TimeOut );
                        }
                        TimeOut++;
                        jMcUpdProgress.setValue(TimeOut * 5);

                        if( TimeOut > 20 ) {
                            bUpdate = false;
                            stopIOAction();
                            Com = CVNavi.safelyCloseCom( outerThis, Com );
                            CVNavi.gsBaudRate = gsBaudRateSaved;
                            jUpdLastError.setText(bundle.getString("MC.Protokollfehler"));
                            System.out.println("update: read 0 bytes" );
                            CVNavi.mbUpdateReadAnswerError( outerThis );
                        }
                        return;
                    }
                    if(bArray[n-1] != 0)
                    {
                        switch(bArray[n-1])
                        {
                            case '*':       //letzter Block wurde erfolgreich übertragen (256 Bytes)
                                BlockNr++;

                            case '#':        //Block wiederholen (256 Bytes)
                                blockSize = 256;
                                int data1[] = new int[262];
                                if (UpdateData[1][0] != 0) {
                                    MaxBlocks = (count / 128) + 513;
                                }
                                else
                                {
                                    MaxBlocks = (count / 128) + 1;
                                }
                                jMcUpdProgress.setMaximum(MaxBlocks);
                                //CRC berechnen
                                for(int g = 0; g < 256; g++)
                                {
                                    long nr = BlockNr * 256 + g;
                                    if (nr < 0xFFFB) {
                                        data1[g + 4] = UpdateData[0][BlockNr * 256 + g];
                                    }
                                    else
                                    {
                                        int index = (int)(nr - 0xFFFB);
                                        data1[g + 4] = UpdateData[1][index];
                                    }
                                }
                                if(BlockNr > MaxBlocks/2)
                                {
                                    data1[1] = 0x00FF;
                                    data1[0] = 0x00FF ;
                                    data1[2] = 0x00FF ;
                                    data1[3] = 0x00FF ;
                                    BlockNr = MaxBlocks/2 + 1;
                                }
                                else
                                {
                                    data1[1] = BlockNr % 256;
                                    data1[2] = BlockNr / 256;
                                }
                                int crc = crc16(0xFFFF, 260, data1);
                                data1[261] = crc / 256;
                                data1[260] = crc % 256;
                                Com.write(data1);
                               break;

                            case '+':       //letzter Block wurde erfolgreich übertragen (128 Bytes)
                                BlockNr++;
                            case '?':       //Block wiederholen (128 Bytes)
                                blockSize = 128;
                                int data[] = new int[132];
                                MaxBlocks = (count / 64) + 1;
                                jMcUpdProgress.setMaximum(MaxBlocks);
                                //CRC berechnen
                                for(int g = 0; g < 128; g++)
                                {
                                    data[g + 2] = UpdateData[0][BlockNr * 128 + g];
                                }
                                if(BlockNr > MaxBlocks/2 + 1)
                                {
                                    BlockNr = 480;
                                    data[1] = BlockNr / 2;
                                    data[0] = (BlockNr * 128) % 256;
                                    BlockNr = MaxBlocks/2 + 1;
                                }
                                else
                                {
                                    data[1] = BlockNr / 2;
                                    data[0] = (BlockNr * 128) % 256;
                                }
                                crc = crc16(0xFFFF, 130, data);
                                data[131] = crc / 256;
                                data[130] = crc % 256;
                                Com.write(data);
                                break;

                            case 'F':       //Fertich
                                CVNavi.mbUpdateWriteSuccess( outerThis, BlockNr*4);
                                Com = CVNavi.safelyCloseCom( outerThis, Com );
                                CVNavi.gsBaudRate = gsBaudRateSaved;
                                bUpdate = false;
                                timer.stop();
                                count = 0;
                                BlockNr = 0;
                                jMcUpdInfo.setText(bundle.getString("MC.Updateerfolg"));
                                stopIOAction();
                                return;

                            case 'A':       //Adress-Error
                                BlockNrTemp = BlockNr * 4;
                                if (BlockNr != 0) {
                                    jUpdLastError.setText(bundle.getString("MC.Adressfehler_1") + BlockNrTemp + bundle.getString("MC.Adressfehler_2"));
                                }
                                BlockNr = 0;
                              break;

                            case 'C':       //CRC-Error
                                BlockNrTemp = BlockNr * 4;
                                jUpdLastError.setText(bundle.getString("MC.CRC_Fehler_1") + BlockNrTemp + bundle.getString("MC.CRC_Fehler_2"));
                                break;

                            case 'T':       //Time out Error
                                break;

                            case 'L':       //Length Error
                                BlockNrTemp = BlockNr * 4;
                                jUpdLastError.setText(bundle.getString("MC.Blockfehler_1") + BlockNrTemp + bundle.getString("MC.Adressfehler_2"));
                                BlockNr = 0;
                                break;

                            default:
                                if(n > 1)
                                {
                                    if(bArray[n-2] == 'F')
                                    {
                                        CVNavi.mbUpdateWriteSuccess( outerThis, BlockNr*4);
                                        Com = CVNavi.safelyCloseCom( outerThis, Com );
                                        CVNavi.gsBaudRate = gsBaudRateSaved;
                                        bUpdate = false;
                                        timer.stop();
                                        count = 0;
                                        BlockNr = 0;
                                        jMcUpdInfo.setText(bundle.getString("MC.Updateerfolg"));
                                        stopIOAction();
                                        return;
                                    }
                                }
                                BlockNrTemp = BlockNr * 4;
                                jUpdLastError.setText(bundle.getString("MC.UnbekannterFehler_1") + BlockNrTemp + bundle.getString("MC.UnbekannterFehler_2"));
                                System.out.println("mbUpdateWriteError: numBytes from MC="+n+" Content:");
                                CVNavi.dumpbArrayBIN( bArray, n );
                                if( debugLevel == 0 ) {
                                    stopIOAction();
                                    Com = CVNavi.safelyCloseCom( outerThis, Com );
                                    CVNavi.mbUpdateWriteError( outerThis, (char)bArray[0]);
                                    count = 0;
                                    BlockNr = 0;
                                }
                                else {
                                    String strOut;
                                    if( debugLevel == 1 ) {
                                        strOut = CVNavi.dumpbArrayHexAsString( bArray );
                                        CVNavi.mbUpdateWriteError( outerThis, strOut );
                                        stopIOAction();
                                        Com = CVNavi.safelyCloseCom( outerThis, Com );
                                        count = 0;
                                        BlockNr = 0;
                                    }
                                    else {
                                        // may be dangerous
                                        // do not reset the count and BlockNr, just ignore
                                    }
                                }
                                break;
                        }
                        BlockNrTemp = BlockNr * 4;
                        jMcUpdProgress.setValue(BlockNrTemp/2);
                        jMcUpdInfo.setText(bundle.getString("MC.Transferblock") + BlockNrTemp + " / " + (MaxBlocks/2+1)*4 + " a "+blockSize+" Bytes" );
                    }
                    else
                    {
                        if (BlockNr == 0) {
                            TimeOut++;
                            jMcUpdProgress.setValue(TimeOut * 5);
                            if (TimeOut > 20) {
                                Com = CVNavi.safelyCloseCom( outerThis, Com );
                                CVNavi.gsBaudRate = gsBaudRateSaved;
                                bUpdate = false;
                                TimeOut = 0;
                                jMcUpdProgress.setValue(0);
                                stopIOAction();
                                jUpdLastError.setText(bundle.getString("MC.keineVerbindung"));
                                CVNavi.mbTimeoutMcUpdStart( outerThis );
                                bArray[0] = 0;
                            }
                        }
                    }
                }
            }
        };
        timer = new javax.swing.Timer(5000, actionListener);
        timer.setRepeats(true);
        actionListener.actionPerformed(null);

        if( CVNavi.bGotoUpdate ) {
            // jump immediately to the update page
            CVNavi.bGotoUpdate = false;
            jTabbedPane1.setSelectedIndex(jTabbedPane1.getTabCount()-1);
            jUpdDateiAuswahl.grabFocus();
        } else {
            // is there a valid number of s88 modules ?
            if( CVNavi.getNumS88() == 0 ) {
                // no -> start reading number from command station
                readS88num();
            }
        }
        if( debugLevel >= 1 ) {
            System.out.println("MC formWindowOpened: AktLokAdr="+AktLokAdr);
        }
    }//GEN-LAST:event_formWindowOpened

    private void resetbArray() {
        // Reset bArray with '0'
        for( int i = 0 ; i < bArray.length ; i++ ) {
            bArray[i] = 0;
        }
        // also reset counter for valid bytes read into this array
        bytesRead = 0;
    }

    private void updateRailComCheckboxes( int bits ) {
        if( bits == -1 )
            return;
        jRailcomTailbits.setSelected((bits & 0x01) == 0x01);
        jRailcomIdNotify.setSelected((bits & 0x02) == 0x02);
        jRailcomAccessory.setSelected((bits & 0x04) == 0x04);
    }

    private int getRailComValueFromCheckboxes() {
        int bits = 0x00;
        if( jRailcomTailbits.isSelected() )
            bits += 0x01;
        if( jRailcomIdNotify.isSelected() )
            bits += 0x02;
        if( jRailcomAccessory.isSelected() )
            bits += 0x04;
        return bits;
    }

    private void updateRailComValue() {
        rcValue = getRailComValueFromCheckboxes();
    }

    private void updateSO999checkboxes( int bits ) {
        if( bits == -1 )
            return;
        jBoostOptNoAccDrive.setSelected((bits & 0x01) == 0x01);
        jBoostOptNoAccBreak.setSelected((bits & 0x02) == 0x02);
    }

    private int getSO999ValueFromCheckboxes() {
        int val = 0;
        val += jBoostOptNoAccDrive.isSelected()?1:0 ;
        val += jBoostOptNoAccBreak.isSelected()?2:0 ;
        return val;
    }

    private void updateSo999Value() {
        so999Value = getSO999ValueFromCheckboxes();
    }

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        stopIOAction();
        CVNavi.frameInstanceDEVICE = null;
    }//GEN-LAST:event_formWindowClosing

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        int selIdx = jTabbedPane1.getSelectedIndex();
        if( debugLevel >= 2 ) {
            System.out.println("jTabbedPane1StateChanged selIdx="+selIdx);
        }
        if( debugLevel >= 1 ) {
            System.out.println("jTabbedPane1StateChanged AktLokAdr="+AktLokAdr+" AktLokState="+AktLokState+" AktLokFormat="+AktLokFormat);
        }
        if( selIdx == 4 ) { // Prog-/lok-Control
            String text = jDisplay.getText();
            if(LokName.length() > 0)
            {
                String lokName = LokName + "          ";
                lokName = lokName.substring(0, 11); // c.MAX_LOC_NAME_LENGTH
                String lokFormat = "     ";
                if( AktLokFormat.length() > 0 ) {
                    lokFormat = AktLokFormat + "     ";
                    lokFormat = lokFormat.substring(0, 5);
                }
                String newText = text.substring(0, 7) + lokFormat + text.substring(12, 17) + lokName + text.substring(28);
                jDisplay.setText(text.substring(0, 7) + lokFormat + text.substring(12, 17) + lokName + text.substring(28));
            }
            jFuncIcon.setEnabled(false);
        }
    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void jUpdateComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jUpdateComponentShown
        Cursor c = new Cursor(Cursor.DEFAULT_CURSOR );
        setCursor(c);
    }//GEN-LAST:event_jUpdateComponentShown

    private void jUpdCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jUpdCloseActionPerformed
        this.dispose();
    }//GEN-LAST:event_jUpdCloseActionPerformed

    private void jUpdCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jUpdCancelActionPerformed
        stopIOAction();
        Com = CVNavi.safelyCloseCom( this, Com );
        jMcUpdInfo.setText(bundle.getString("MC.Updatecancelled"));
    }//GEN-LAST:event_jUpdCancelActionPerformed

    private void jUpdStartUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jUpdStartUpdateActionPerformed

        jMcUpdInfo.setText(bundle.getString("MC.waitingforconnection"));
        jUpdLastError.setText(bundle.getString("MC.noerror"));

        RandomAccessFile inputStream = null;
        File f = new File(jUpdDatei.getText());
        long l = f.length();
        int i = 0;
        UpdateData[1][0] = 0;
        count = 0;
        TimeOut = 0;

        byte ac[] = new byte[0xFFFF];
        try {
            inputStream = new RandomAccessFile(f, "r");
        } catch (FileNotFoundException ex) {
            CVNavi.mbFileOpenError( this, jUpdDatei.getText());
            stopIOAction();
            return;
        }

        try {
            int n = 0;
            /*
            * Die Daten werden mit der eingestellten Datenrate übertragen
            * Es gibt zwei Blockgrößen, die die MC anfordert.
            * folgende Zeichen können von der MC kommen:
            * *    -   letzter Block wurde erfolgreich übertragen (256 Bytes)
            * +    -   letzter Block wurde erfolgreich übertragen (128 Bytes)
            * #    -   Block wiederholen (256 Bytes)
            * ?    -   Block wiederholen (128 Bytes)
            * A    -   Adress-Error
            * C    -   CRC-Error
            * T    -   Time out Error
            * L    -   Length-Error
            * ac muss in 128 oder 256 Byte-Blöcke binär aufgeteilt werden dies passiert zur Laufzeit
            * Dazu wird ac zuerst komplett in binär gewandelt und in UpdateData gespeichert
            */
            //binäre Daten erzeugen:
            inputStream.seek(0);
            long lSeek = 0;
            n = inputStream.read(ac);

            if(ac[0] != ':')
            {
                CVNavi.mbFileHexFormatError( null );
                return;
            }
            int Anzahl = 0;
            for(int m = 0; m < n;)
            {
                while(ac[m] != ':') {
                    m++;
                }
                if(ac[m+8] == '1')
                    break;         //Ende-Record
                m++;
                if(ac[m+7] != '0')
                    continue;
                Anzahl = hexval(ac[m], ac[m+1]);
                m += 6;
                for(int k = 0; k < Anzahl; k++)
                {
                    m += 2;
                    UpdateData[0][count++] = hexval(ac[m], ac[m+1]);
                }
                if (n == 0xFFFF) {
                    if (m > 0xFFC0) {
                        lSeek = m - 1;
                        break;
                    }
                }
            }
            if( debugLevel > 0 ) {
                System.out.println("strtDwnl A count["+count+"] lSeek="+lSeek);
            }
            if(n == 0xFFFF)
            {
                try {
                    inputStream.seek(lSeek);
                    n = inputStream.read(ac);
                } catch (IOException iOException) {
                    CVNavi.mbFileReadError( this );
                    stopIOAction();
                    return;
                }
                i = 0;
                Anzahl = 0;
                for(int m = 0; m < n;)
                {
                    while(ac[m] != ':')
                        m++;
                    if(m > 0xFFF6)
                        break;
                    if(ac[m+8] == '1')
                        break;         //Ende-Record
                    m++;
                    if(ac[m+7] != '0')
                        continue;
                    Anzahl = hexval(ac[m], ac[m+1]);
                    m += 6;
                    for(int k = 0; k < Anzahl; k++)
                    {
                        m += 2;
                        UpdateData[i][count] = hexval(ac[m], ac[m+1]);
                        if(count < 0xFFFA) {
                            count++;
                        }
                        else {
                            if( debugLevel > 0 ) {
                                System.out.println("strtDwnl A count["+count+"] reset to 0 i["+i+"]");
                            }
                            count = 0;
                            i++;
                        }
                    }
                    if (n == 0xFFFF) {
                        if (m > 0xFFC0) {
                            lSeek += m - 1;
                            break;
                        }
                    }
                }
                if( debugLevel > 0 ) {
                    System.out.println("strtDwnl B count["+count+"] lSeek="+lSeek);
                }
                if(n == 0xFFFF)
                {
                    try {
                        inputStream.seek(lSeek);
                        n = inputStream.read(ac);
                    } catch (IOException iOException) {
                        CVNavi.mbFileReadError( this );
                        stopIOAction();
                        return;
                    }
                    Anzahl = 0;

                    for(int m = 0; m < n;)
                    {
                        while(ac[m] != ':') {
                            m++;
                        }
                        if(m > 0xFFF6)
                            break;
                        if(ac[m+8] == '1')
                            break;         //Ende-Record
                        m++;
                        if(ac[m+7] != '0')
                            continue;
                        Anzahl = hexval(ac[m], ac[m+1]);
                        m += 6;
                        for(int k = 0; k < Anzahl; k++)
                        {
                            m += 2;
                            UpdateData[i][count] = hexval(ac[m], ac[m+1]);
                            if(count < 0xFFFA) {
                                count++;
                            }
                            else {
                                if( debugLevel > 0 ) {
                                    System.out.println("strtDwnl B count["+count+"] reset to 0 i["+i+"]");
                                }
                                count = 0;
                                i++;
                            }
                        }
                        if (n == 0xFFFF) {
                            if (m > 0xFFC0) {
                                lSeek += m - 1;
                                break;
                            }
                        }
                    }
                    if( debugLevel > 0 ) {
                        System.out.println("strtDwnl C count["+count+"] lSeek="+lSeek);
                    }
                    if(n == 0xFFFF)
                    {
                        try {
                            inputStream.seek(lSeek);
                            n = inputStream.read(ac);
                        } catch (IOException iOException) {
                            CVNavi.mbFileReadError( this );
                            stopIOAction();
                            return;
                        }
                        Anzahl = 0;

                        for(int m = 0; m < n;)
                        {
                            while(ac[m] != ':')
                                m++;
                            if(m > 0xFFF6)
                                break;
                            if(ac[m+8] == '1')
                                break;         //Ende-Record
                            m++;
                            if(ac[m+7] != '0')
                                continue;
                            Anzahl = hexval(ac[m], ac[m+1]);
                            m += 6;
                            for(int k = 0; k < Anzahl; k++)
                            {
                                m += 2;
                                UpdateData[i][count] = hexval(ac[m], ac[m+1]);
                                if(count < 0xFFFA) {
                                    count++;
                                }
                                else {
                                    if( debugLevel > 0 ) {
                                        System.out.println("strtDwnl C count["+count+"] reset to 0 i["+i+"]");
                                    }
                                    count = 0;
                                    i++;
                                }
                            }
                            if (n == 0xFFFF) {
                                if (m > 0xFFC0) {
                                    lSeek += m - 1;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            if( debugLevel > 0 ) {
                System.out.println("strtDwnl D count["+count+"] i["+i+"] lSeek="+lSeek+" n="+n);
            }

            BlockNr = 0;
            inputStream.close();

            // force 38400 baud for firmware updates for RS232 on MC and USB-2 (!) on RedBox
            if( CVNavi.rs232_or_rb_usb_2 ) {
                int oldBaudRate = CVNavi.gsBaudRate;
                if( CVNavi.gsBaudRate != 38400 ) {
                    System.out.println("forcing baud rate for updates to 38400");
                    Com = CVNavi.safelyCloseCom( this, Com);
                    CVNavi.gsBaudRate = 38400;
                    Com = CVNavi.safelyOpenCom( this, Com );
                    if( Com == null ){
                        // Umstellung auf 38400 schlägt fehl, also wieder zurück auf alte BaudRate
                        CVNavi.gsBaudRate = oldBaudRate;
                        Com = CVNavi.safelyOpenCom( this, Com );
                        if( Com == null ){
                            // geht nicht -> Abbruch
                            stopIOAction();
                            jUpdLastError.setText(bundle.getString("MC.Schnitttstellenfehler"));
                            return;
                        }
                    }
                }
                CVNavi.gsBaudRate = oldBaudRate;
            }
            CVNavi.flushReadBuffer(Com);
            bUpdate = true;
            timer.setInitialDelay(CVNavi.userTimerFwUp);
            timer.setDelay(CVNavi.userTimerFwUp);
            timer.setRepeats(true);
            jMcUpdProgress.setMaximum(100);
            startIOAction();

            jMcUpdInfo.setText(bundle.getString("MC.waitingforconnection"));


        } catch (IOException ex) {
            CVNavi.mbFileReadError( this );
            stopIOAction();
            return;
        }
    }//GEN-LAST:event_jUpdStartUpdateActionPerformed

    private void jUpdDateiAuswahlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jUpdDateiAuswahlActionPerformed
        System.out.println("jUpdDateiAuswahlActionPerformed: enter");
        SaveOpenDialog od = new SaveOpenDialog( this, true, true, null, this, "hex", c.HEX);
        System.out.println("jUpdDateiAuswahlActionPerformed: set jSerNr to ???");
        jSerNr.setText("???");
        System.out.println("jUpdDateiAuswahlActionPerformed: set device selection");
        setDeviceSelection();
        System.out.println("jUpdDateiAuswahlActionPerformed: leave");
        return;
    }//GEN-LAST:event_jUpdDateiAuswahlActionPerformed


    public void setDeviceSelection() {
        if( CVNavi.rs232_or_rb_usb_2 ) {
            System.out.println("setDeviceSelection: USB2");
            jUSB1.setSelected(false);
            jUSB2.setSelected(true);
        } else {
            jUSB1.setSelected(true);
            jUSB2.setSelected(false);
            System.out.println("setDeviceSelection: USB1");
        }
    }

    public void setUpdDatei( String fileName ) {
        jUpdDatei.setText( fileName );
    }

    private void jMagnetartikelComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jMagnetartikelComponentShown
        Cursor c = new Cursor(Cursor.DEFAULT_CURSOR );
        setCursor(c);
    }//GEN-LAST:event_jMagnetartikelComponentShown

    private void jMagRepairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMagRepairActionPerformed
        if( jTableAccessory.isEditing() ) {
            jTableAccessory.getCellEditor().stopCellEditing();
        }
        FehlerArt = 0;
        checkTableAccessory( true, true );
    }//GEN-LAST:event_jMagRepairActionPerformed

    private void jMagCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMagCheckActionPerformed
        if( jTableAccessory.isEditing() ) {
            jTableAccessory.getCellEditor().stopCellEditing();
        }
        FehlerArt = 0;
        checkTableAccessory( false, true );
    }//GEN-LAST:event_jMagCheckActionPerformed

    private void jMDCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMDCCActionPerformed
        setAccessoryTableWithProto("DCC");
    }//GEN-LAST:event_jMDCCActionPerformed

    private void jMMMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMMMActionPerformed
        setAccessoryTableWithProto("MM");
    }//GEN-LAST:event_jMMMActionPerformed

    private void jTableAccessoryKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableAccessoryKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTableAccessoryKeyReleased

    private void jTraktionenComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jTraktionenComponentShown
        Cursor c = new Cursor(Cursor.DEFAULT_CURSOR );
        setCursor(c);
    }//GEN-LAST:event_jTraktionenComponentShown

    private void jTraRepairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTraRepairActionPerformed
        if( jTableTraction.isEditing() ) {
            jTableTraction.getCellEditor().stopCellEditing();
        }
        FehlerArt = 0;
        checkTableTraction( true, true );
    }//GEN-LAST:event_jTraRepairActionPerformed

    private void jTraCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTraCheckActionPerformed
        if( jTableTraction.isEditing() ) {
            jTableTraction.getCellEditor().stopCellEditing();
        }
        FehlerArt = 0;
        checkTableTraction( false, true );
    }//GEN-LAST:event_jTraCheckActionPerformed

    private void jTraDelAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTraDelAllActionPerformed
        if( jTableTraction.isEditing() ) {
            jTableTraction.getCellEditor().stopCellEditing();
        }
        initTractionTable();
        jTableTraction.getSelectionModel().clearSelection();
    }//GEN-LAST:event_jTraDelAllActionPerformed

    private void jTableTractionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableTractionKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_DELETE)
        {
            delMultipleTractionLines();
        }
    }//GEN-LAST:event_jTableTractionKeyReleased

    private void jLoksComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jLoksComponentShown
        Cursor c = new Cursor(Cursor.DEFAULT_CURSOR );
        setCursor(c);
    }//GEN-LAST:event_jLoksComponentShown

    private void jLocM3sidWriteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jLocM3sidWriteActionPerformed
        String sAdr = ""+jTableLoco.getValueAt( this.locoTableSelRow, 0);
        int iAdr = Integer.parseInt(sAdr);

        if( ! checkM3uidValid() ) {
            return;
        }
        if( ! CVNavi.bUseXm3sid ) {
            String sFW = CVNavi.fwVersion;
            if( (sFW == null) || ( sFW.length() == 0) ) {
                sFW = bundle.getString("MC.unbekannt");
            }
            if( CVNavi.bSpracheDE) {
                CVNavi.mbGeneric( this, "HINWEIS", "Tams MC mit Firmware ab Version \"1.4.7b\" notwendig", "Diese Zentrale hat Firmware \""+sFW+"\"", 5, true );
            } else {
                CVNavi.mbGeneric( this, "NOTE", "Tams MC with firmware version \"1.4.7b\" or newer necessary", "This MC has firmware \""+sFW+"\"", 5, true );
            }
            return;
        }

        String sM3UID = jTextM3UID.getText();
        // int iM3UID = Integer.decode(sM3UID);
        long lM3UID = Long.decode(sM3UID);
        if( debugLevel > 0 ) {
            System.out.println("lM3UID="+String.format("%8s", Long.toHexString( lM3UID )).replace(' ', '0'));
        }

        Com = CVNavi.safelyOpenCom( this, Com );
        if( Com == null ){
            return;
        }


        byte[] wArray = new byte[8];
        wArray[0] = (byte) 0x78; // x
        wArray[1] = (byte) 0x87; // Xm3Sid (requires 1.4.7b)
        wArray[2] = (byte) (   iAdr           & 0xFF );
        wArray[3] = (byte) ( ( iAdr >> 8 )    & 0xFF );
        wArray[4] = (byte) (   lM3UID         & 0xFF );
        wArray[5] = (byte) ( ( lM3UID >>  8 ) & 0xFF );
        wArray[6] = (byte) ( ( lM3UID >> 16 ) & 0xFF );
        wArray[7] = (byte) ( ( lM3UID >> 24 ) & 0xFF );

        CVNavi.flushReadBuffer(Com);
        Com.write((byte)0x60); // GO
        CVNavi.flushReadBuffer(Com);
        if( debugLevel > 0 ) {
            // TODO evtl this durch this.getContentPane() ersetzen
            CVNavi.mbGeneric( this, "MC", "Xm3Sid adr(sid)="+iAdr+" MAC(uid)="+sM3UID, "writing to MC wArray=0x"+CVNavi.byteArrayToHex(wArray), 10, false );
        }
        Com.write(wArray);
        if( debugLevel > 0 ) {
            System.out.println("written to MC wArray=0x"+CVNavi.byteArrayToHex(wArray));
        }
        bWriteM3sid = true;
        readWriteProgress = 0;
        jMcM3Progress.setMaximum(2);

        timer.setInitialDelay(CVNavi.MCtimer1);
        timer.setDelay(CVNavi.MCtimer2);
        startIOAction();
        jMcM3Info.setText("Xm3Sid: write in progress");
        jMcM3Progress.setValue(++readWriteProgress);
    }//GEN-LAST:event_jLocM3sidWriteActionPerformed

    private void jLocRepairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jLocRepairActionPerformed
        if( jTableLoco.isEditing() ) {
            jTableLoco.getCellEditor().stopCellEditing();
        }
        FehlerArt = 0;
        checkTableLoco( true, true );
    }//GEN-LAST:event_jLocRepairActionPerformed

    private void jLocCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jLocCheckActionPerformed
        if( jTableLoco.isEditing() ) {
            jTableLoco.getCellEditor().stopCellEditing();
        }
        FehlerArt = 0;
        checkTableLoco( false, true );
    }//GEN-LAST:event_jLocCheckActionPerformed

    private void jLocDelAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jLocDelAllActionPerformed
        if( jTableLoco.isEditing() ) {
            jTableLoco.getCellEditor().stopCellEditing();
        }
        initLocoTable();
        jTableLoco.getSelectionModel().clearSelection();
    }//GEN-LAST:event_jLocDelAllActionPerformed

    private void jTableLocoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableLocoKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_DELETE)
        {
            delMultipleLocoLines();
        }
    }//GEN-LAST:event_jTableLocoKeyReleased

    private void jTableLocoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTableLocoPropertyChange
        if( debugLevel > 0 ) {
            System.out.println("jTableLocoPropertyChange col="+jTableLoco.getEditingColumn()+" row="+jTableLoco.getEditingRow());
        }
        int edRow = jTableLoco.getEditingRow();
        int edCol = jTableLoco.getEditingColumn();
        jFuncIcon.setEnabled(true);
        String str;
        if( edRow >= 0 && edCol >= 0 ) {
            switch( edCol ) {
                case 1:
                    str = (""+jTableLoco.getValueAt(edRow, edCol)).toUpperCase().replaceAll("\\s","");
                    jTableLoco.setValueAt(str, edRow, edCol);
                    checkM3sid(edRow);
                    break;
                case 2:
                    str = (""+jTableLoco.getValueAt(edRow, edCol)).toLowerCase().replaceAll("\\s","");
                    jTableLoco.setValueAt(str, edRow, edCol);
                    break;
            }
        }
    }//GEN-LAST:event_jTableLocoPropertyChange

    private void jTableLocoCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_jTableLocoCaretPositionChanged
        if( debugLevel > 0 ) {
            System.out.println("jTableLocoCaretPositionChanged col="+jTableLoco.getEditingColumn()+" row="+jTableLoco.getEditingRow());
        }
    }//GEN-LAST:event_jTableLocoCaretPositionChanged

    private void jTableLocoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTableLocoFocusLost
        if( debugLevel > 0 ) {
            System.out.println("jTableLocoFocusLost col="+jTableLoco.getEditingColumn()+" row="+jTableLoco.getEditingRow());
        }
    }//GEN-LAST:event_jTableLocoFocusLost

    private void jTableLocoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTableLocoFocusGained
        if( debugLevel > 0 ) {
            System.out.println("jTableLocoFocusGained col="+jTableLoco.getEditingColumn()+" row="+jTableLoco.getEditingRow());
        }
    }//GEN-LAST:event_jTableLocoFocusGained

    private void jSystemComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jSystemComponentShown
        Cursor c = new Cursor(Cursor.DEFAULT_CURSOR );
        setCursor(c);
    }//GEN-LAST:event_jSystemComponentShown

    private void jCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCloseActionPerformed
        if( debugLevel > 0 ) {
            System.out.println("jCloseActionPerformed");
        }
        this.dispose();
    }//GEN-LAST:event_jCloseActionPerformed

    private void jRailcomAccessoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRailcomAccessoryActionPerformed
        if( jRailcomAccessory.isSelected() ) {
            jRailcomTailbits.setSelected(true);
            jRailcomIdNotify.setSelected(true);
        }
        updateRailComValue();
    }//GEN-LAST:event_jRailcomAccessoryActionPerformed

    private void jRailcomIdNotifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRailcomIdNotifyActionPerformed
        if( jRailcomIdNotify.isSelected() ) {
            jRailcomTailbits.setSelected(true);
        } else {
            jRailcomAccessory.setSelected(false);
        }
        updateRailComValue();
    }//GEN-LAST:event_jRailcomIdNotifyActionPerformed

    private void jRailcomTailbitsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRailcomTailbitsActionPerformed
        if( ! jRailcomTailbits.isSelected() ) {
            jRailcomIdNotify.setSelected(false);
            jRailcomAccessory.setSelected(false);
        }
        updateRailComValue();
    }//GEN-LAST:event_jRailcomTailbitsActionPerformed

    private void jCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCancelActionPerformed
        if( debugLevel > 0 ) {
            System.out.println("jCancelActionPerformed");
        }
        stopIOAction();
    }//GEN-LAST:event_jCancelActionPerformed

    private void jKonfSichernActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jKonfSichernActionPerformed
        this.jMcRwInfo.setText("write to file: preparing data");
        this.jMcRwProgress.setMaximum(100);
        this.jMcRwProgress.setValue(0);
        String str = "[INFO]\r\nVERSION " + jVersion.getText() + "\r\nHARDWARE " + jHardWare.getText() + "\r\nMCU " + jMCU.getText() + "\r\nSERIAL " + jSerNr.getText() + "\r\n[LOCO]\r\n";
        if( debugLevel > 0 ) {
            System.out.println("jKonfSichernActionPerformed Version["+jVersion.getText()+"]" );
        }
        this.jMcRwProgress.setValue(10);
        for(int i = 0; i < c.MAX_LOCS; i++)
        {
            String s1 = (""+jTableLoco.getValueAt(i, 0)).trim();
            String s2 = (""+jTableLoco.getValueAt(i, 2)).trim();
            String s3 = (""+jTableLoco.getValueAt(i, 1)).trim();
            String s4 = (""+jTableLoco.getValueAt(i, 3)).trim();
            if((s1.length() > 0) && (s2.length() > 0) && (s3.length() > 0 )) {
                if( debugLevel > 0 ) {
                    System.out.println("jKonfSichernActionPerformed i["+i+"] s1["+s1+"] s2["+s2+"] s3["+s3+"]");
                }
                str += s1 + ", " + s2 + ", " + s3;
                if(s4.length() > 0) {
                    str += ", " + s4;
                }
                str += "\r\n";
            }
        }
        this.jMcRwProgress.setValue(30);
        str += "[FUNCMAPS]\r\n";
        for(int i = 0; i < FuncIcons.length; i++)
        {
            str += FuncIcons[i] + "\r\n";
        }
        str += "[TRAKTIONS]\r\n";
        if( debugLevel > 0 ) {
            System.out.println("jKonfSichernActionPerformed TRAKTIONS");
        }
        for(int i = 0; i < c.MAX_TRACTIONS; i++)
        {
            String s1 = (""+jTableTraction.getValueAt(i, 0)).trim();
            String s2 = (""+jTableTraction.getValueAt(i, 2)).trim();
            if( (s1.length() > 0) && (s2.length() > 0) ) {
                str += s1 + ", " + s2;
                str += "\r\n";
            }
            else {
                break;
            }
        }
        this.jMcRwProgress.setValue(60);
        if( debugLevel > 0 ) {
            System.out.println("jKonfSichernActionPerformed ACCFMT");
        }
        str += "[ACCFMT]\r\n";
        for(int i = 0; i < c.MAX_MM1_ACCMOD; i++)
        {
            String s1 = (""+jTableAccessory.getValueAt(i, 0)).trim();
            String s2 = (""+jTableAccessory.getValueAt(i, 1)).trim();
            if( (s1.length() > 0) && (s2.length() > 0) ) {
                String[] s1Arr = s1.split(" ");
                int j = -1;
                try {
                    j = Integer.parseInt(s1Arr[0]) - 1;
                } catch (Exception ex) {
                    System.out.println("jKonfSichernActionPerformed: EXCEPTION s1="+s1+" s1Arr[0]="+s1Arr[0]);
                }
                if( j >= 0 ) {
                    str += ""+j+", "+s2+"\r\n";
                }
            }
            else {
                break;
            }
        }
        this.jMcRwProgress.setValue(80);
        str += "[SYSTEM]\r\nLONGPAUSE ";
        if(jLangePause.isSelected()) {
            str += "yes\r\n";
        }
        else {
            str += "no\r\n";
        }
        str += "NEGATIVESHORT ";
        if(jDCC_Booster.isSelected()) {
            str += "yes\r\n";
        }
        else {
            str += "no\r\n";
        }
        str += "DEFAULTDCC ";
        if(jDCC_Loks.isSelected()) {
            str += "yes\r\n";
        }
        else {
            str += "no\r\n";
        }
        int KSE;
        try {
            KSE = Integer.parseInt(jKurzEmpf.getText());
            KSE /= 5;
        } catch (NumberFormatException numberFormatException) {
            KSE = 20;
        }
        str += "SHORTTIME " + KSE + "\r\n";
        str += "s88MODULES " + js88.getText() + "\r\n";
        str += "MAGMINTIME " + jMinMag.getText() + "\r\n";
        str += "MAGMAXTIME " + jMaxMag.getText() + "\r\n";
        str += "BAUDRATE " + jBaud.getSelectedItem().toString() + "\r\n";
        if( rcValue != -1 ) {
            str += "RAILCOM " + rcValue + "\r\n";
        }
        if( so999Value != -1 ) {
            str += "SO999 " + so999Value + "\r\n";
        }
        str += "*END*\r\n";

        this.jMcRwProgress.setValue(90);
        setMcRwInfo("write to file: select filename");

        SaveOpenDialog od = new SaveOpenDialog( this, true, false, str, this, "mc", c.MC);
    }//GEN-LAST:event_jKonfSichernActionPerformed

    public void setMcRwProgress( int i ) {
        this.jMcRwProgress.setValue( i );
    }

    public void setMcRwInfo( String s ) {
        this.jMcRwInfo.setText( s );
    }

    public void callParser( String fileName ) {
        if( ( ReturnString != null ) && ( ReturnString.length() > 0 ) ) {
            validMcData = parseInputArray( (fileName != null)?fileName:"unknown", ReturnString );
            if( validMcData ) {
                reCheckVersionInfo();
            }
            ReturnString = "";
        }
    }

    private int getLocosConfigured() {
        int ret = 0;
        for (int i = 0; i < c.MAX_LOCS; i++) {
            Object oAdr = jTableLoco.getValueAt(i, 0);
            String sAdr = "" + oAdr;
            if( sAdr.trim().length() > 0) {
                ret++;
                if( debugLevel > 2 ) {
                    System.out.println(" getLocosConfigured=\""+sAdr+"\" ret="+ret);
                }
            }
        }
        System.out.println("getLocosConfigured="+ret);
        return ret;
    }

    private void jKonfLadenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jKonfLadenActionPerformed
        setMcRwInfo("read from file: select filename");
        setMcRwProgress( 0 );
        SaveOpenDialog od = new SaveOpenDialog( this, true, true, null, this, "m3 conf txt", c.MC);
    }//GEN-LAST:event_jKonfLadenActionPerformed

    private void jKonfSchreibenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jKonfSchreibenActionPerformed
        Com = CVNavi.safelyOpenCom( this, Com );
        if( Com == null ) {
            return;
        }
        jMcRwInfo.setText("write: prepare");

        bFalscheEingabe = false;
        FehlerArt = 0;

        if( jWRsys.isSelected()) {
            // check all critical fields for reasonable values OR init with defaults
            CVNavi.checkTextFieldUnit( this, jKurzEmpf, 0, 1000, 100, 5, false);
            CVNavi.checkTextField( this, js88, 0, 52, 52, false);
            CVNavi.checkTextFieldUnit( this, jMinMag, 0, 1000, 100, 50, false);
            CVNavi.checkTextFieldUnit( this, jMaxMag, 100, 25500, 300, 100, false);
        }

        if( jWRloc.isSelected() ) {
            jMcRwInfo.setText("write: prepare loco table");
            // check loco table without "repair"-Option in silent mode (no show)
            Boolean chkLocOK = checkTableLoco( false, false );
            if( debugLevel > 0 ) {
                System.out.println("KonfSchreiben chkLoc=["+chkLocOK+"]" );
            }
            if( chkLocOK == false ) {
                // CVNavi.mbTableCheck( FehlerArt, false );
                if( debugLevel > 0 ) {
                    System.out.println("KonfSchreiben chkLoc=["+chkLocOK+"] RETURN");
                }
                jMcRwInfo.setText("write: prepare -> loco list with errors -> cancel");
                jTabbedPane1.setSelectedIndex(1);
                return;
            }
            // check loco table with "repair"-Option in silent mode (no show) using defaults
            checkTableLoco( true, false );
            locosConfigured = getLocosConfigured();
       }

        if( jWRtra.isSelected() ) {
            jMcRwInfo.setText("write: prepare traction table");
            // check traction table without "repair"-Option in silent mode (no show)
            Boolean chkTraOK = checkTableTraction( false, false );
            if( debugLevel > 0 ) {
                System.out.println("KonfSchreiben chkTra=["+chkTraOK+"]" );
            }
            if( chkTraOK == false ) {
                // CVNavi.mbTableCheck( FehlerArt, false );
                if( debugLevel > 0 ) {
                    System.out.println("KonfSchreiben chkTra=["+chkTraOK+"] RETURN" );
                }
                jMcRwInfo.setText("write: prepare -> traction list with errors -> cancel");
                jTabbedPane1.setSelectedIndex(2);
                return;
            }
            // check traction table with "repair"-Option in silent mode (no show) using defaults
            checkTableTraction( true, false );
        }

        if( jWRmag.isSelected() ) {
            jMcRwInfo.setText("write: prepare accessory table");
            // check accesory table without "repair"-Option in silent mode (no show)
            Boolean chkAccOK = checkTableAccessory( false, false );
            if( debugLevel > 0 ) {
                System.out.println("KonfSchreiben chkAcc=["+chkAccOK+"]" );
            }
            if( chkAccOK == false ) {
                // CVNavi.mbTableCheck( FehlerArt, false );
                if( debugLevel > 0 ) {
                    System.out.println("KonfSchreiben chkAcc=["+chkAccOK+"] RETURN" );
                }
                jMcRwInfo.setText("write: prepare -> accessory list with errors -> cancel");
                jTabbedPane1.setSelectedIndex(3);
                return;
            }
            // check accessory table with "repair"-Option in silent mode (no show) using defaults
            checkTableAccessory( true, false );
        }

        jMcRwInfo.setText("write: prepare");
        if ( validMcData ) {
            // TODO current data was not read from MC or file !
            // Are U sure ? Do you want to write ? YesNo 
            // if( NO )
            //   return;
        }
        bFalscheEingabe = false;
        FehlerArt = 0;
        int nR = CVNavi.flushReadBuffer(Com);
        if( debugLevel > 0 ) {
            System.out.println("KonfSchreiben clean buffer pre 0x61 write read "+nR+" bytes" );
        }

        resetbArray();
        retries = CVNavi.timerRetries;
        jMcRwProgress.setString(null);
        bWaitAnswerInProgress = false; // STOP as 0x61 byte does not return a prompt "]" -> start writing in loop !
        locListWriteErrors = 0;
        nextWriteJob = 0;
        bWriteCfg = true;
        count = 0; // used for FW-Upadate
        jMcRwProgress.setMaximum(c.MAX_SYSWRITES+c.MAX_LOCS+c.MAX_TRACTIONS+c.MAX_MM1_ACCMOD);
        jMcRwProgress.setValue(0);
        timer.setInitialDelay(CVNavi.MCtimer1);
        timer.setDelay(CVNavi.MCtimer2);
        startIOAction();
    }//GEN-LAST:event_jKonfSchreibenActionPerformed

    private void jKonfLesenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jKonfLesenActionPerformed
        Com = CVNavi.safelyOpenCom( this, Com );
        if( Com == null ){
            return;
        }

        CVNavi.flushReadBuffer(Com);
        String s = "xcfgdump\r";
        Com.write(s);
        resetbArray();
        retries = CVNavi.timerRetries;
        jMcRwProgress.setString(null);
        bWaitAnswerInProgress = true;
        bReadCfg = true;
        readWriteProgress = 0;
        jMcRwProgress.setMaximum(6);
        jMcRwProgress.setValue(0);
        timer.setInitialDelay(CVNavi.MCtimer1);
        timer.setDelay(CVNavi.MCtimer2);
        startIOAction();
        jMcRwInfo.setText("read: MC config read in progress");
        jMcRwProgress.setValue(++readWriteProgress);
        if( CVNavi.iComType == c.TCP_IP ) {
            int duration = 100 ;
            if( retries > 8 ) {
                duration = 1000;
            }
            if( duration < 100) {
                duration = 100;
            }
            CVNavi.sleep( duration );
        }
    }//GEN-LAST:event_jKonfLesenActionPerformed

    private void jMaxMagFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jMaxMagFocusLost
        CVNavi.checkTextFieldUnit( this, jMaxMag, 100, 25500, 300, 100, true);
    }//GEN-LAST:event_jMaxMagFocusLost

    private void jMinMagFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jMinMagFocusLost
        CVNavi.checkTextFieldUnit( this, jMinMag, 0, 1000, 100, 50, true);
    }//GEN-LAST:event_jMinMagFocusLost

    private void js88FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_js88FocusLost
        CVNavi.checkTextField( this, js88, 0, 52, 52, true);
        updateS88field(Integer.parseInt(js88.getText()));
    }//GEN-LAST:event_js88FocusLost

    private void jKurzEmpfFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jKurzEmpfFocusLost
        CVNavi.checkTextFieldUnit( this, jKurzEmpf, 0, 1000, 100, 5, true);
    }//GEN-LAST:event_jKurzEmpfFocusLost

    private void jTextM3UIDFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextM3UIDFocusGained
        // TODO add your handling code here:
        System.out.println("jTextM3UIDFocusGained");
    }//GEN-LAST:event_jTextM3UIDFocusGained

    private void jTextM3UIDFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextM3UIDFocusLost
        System.out.println("jTextM3UIDFocusLost");
        if( CVNavi.frameInstanceDEVICE == null ) {
            return;
        }
        if( ! checkM3uidValidActive ) {
            checkM3uidValid();
        }
    }//GEN-LAST:event_jTextM3UIDFocusLost

    private void jTextM3UIDPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTextM3UIDPropertyChange
        // TODO add your handling code here:
        System.out.println("jTextM3UIDPropertyChange");
    }//GEN-LAST:event_jTextM3UIDPropertyChange

    private void jTextM3UIDInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_jTextM3UIDInputMethodTextChanged
        // TODO add your handling code here:
        System.out.println("jTextM3UIDInputMethodTextChanged");
    }//GEN-LAST:event_jTextM3UIDInputMethodTextChanged

    private void jLocM3cfgLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jLocM3cfgLoadActionPerformed
        SaveOpenDialog od = new SaveOpenDialog( this, true, true, null, this, "m3", c.M3);
        if( CVNavi.lastSaveOpenDialogWasCancel ) {
            return;
        }

        M3changed = false;
        updateM3uid();
        updateM3count();
    }//GEN-LAST:event_jLocM3cfgLoadActionPerformed

    private void jLocM3cfgEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jLocM3cfgEditActionPerformed
        M3L = new M3_Liste( this, true );
    }//GEN-LAST:event_jLocM3cfgEditActionPerformed

    private void jLocM3cfgSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jLocM3cfgSaveActionPerformed
        String str = "[M3UID]\r\n";

        System.out.println("M3 [<MAC>][<SID>][<Beschreibung>]");
        for( int i = 0 ; i < M3used ; i++ ) {
            str += M3liste[0][i]+", "+M3liste[1][i]+", "+M3liste[2][i]+"\r\n";
            System.out.println("M3 ["+M3liste[0][i]+"]["+M3liste[1][i]+"]["+M3liste[2][i]+"]");
        }

        System.out.println("jLocM3cfgSaveActionPerformed: SaveOpenDialog CALL");
        SaveOpenDialog od = new SaveOpenDialog( this, true, false, str, this, "m3", c.M3);
        System.out.println("jLocM3cfgSaveActionPerformed: SaveOpenDialog RETURN : CVNavi.lastSaveOpenDialogwasCancel="+CVNavi.lastSaveOpenDialogWasCancel);
        if( CVNavi.lastSaveOpenDialogWasCancel ) {
            return;
        }
        M3changed = false;
        updateM3uid();
        updateM3count();
    }//GEN-LAST:event_jLocM3cfgSaveActionPerformed

    private void jLocDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jLocDelActionPerformed
        delMultipleLocoLines();
    }//GEN-LAST:event_jLocDelActionPerformed

    private void jTraDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTraDelActionPerformed
        delMultipleTractionLines();
    }//GEN-LAST:event_jTraDelActionPerformed

    private void jLoc2SystemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jLoc2SystemActionPerformed
        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_jLoc2SystemActionPerformed

    private void jTra2SystemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTra2SystemActionPerformed
        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_jTra2SystemActionPerformed

    private void jMag2SystemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMag2SystemActionPerformed
        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_jMag2SystemActionPerformed

    private void jUpd2SystemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jUpd2SystemActionPerformed
        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_jUpd2SystemActionPerformed

    private void jLocM3sidWriteListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jLocM3sidWriteListActionPerformed
        // for all in M3list do jLocM3sidWriteActionPerformed()
        System.out.println("jLocM3sidWriteListActionPerformed");

        if( M3used == 0 ) {
            return;
        }

        if( ! CVNavi.bUseXm3sid ) {
            String sFW = CVNavi.fwVersion;
            if( (sFW == null) || ( sFW.length() == 0) ) {
                if( CVNavi.bSpracheDE) {
                    sFW = "unbekannt"; 
                } else {
                    sFW = "unknown"; 
                }
            }
            if( CVNavi.bSpracheDE) {
                CVNavi.mbGeneric( this, "HINWEIS", "Tams MC mit Firmware ab Version \"1.4.7b\" notwendig", "Diese Zentrale hat Firmware \""+sFW+"\"", 5, true );
            } else {
                CVNavi.mbGeneric( this, "NOTE", "Tams MC with firmware version \"1.4.7b\" or newer necessary", "This MC has firmware \""+sFW+"\"", 5, true );
            }
            return;
        }


        if( debugLevel > 0 ) {
            for( int i = 0 ; i < M3used ; i++ ){
                System.out.println("jLocM3sidWriteListActionPerformed: Liste["+i+"] : uid="+ M3liste[0][i] +" sid="+ M3liste[1][i] );
            }
        }

        long lM3UID = Long.decode( M3liste[0][0] );
        int iAdr = Integer.decode( M3liste[1][0] );
        if( debugLevel > 0 ) {
            System.out.println("lM3UID="+String.format("%8s", Long.toHexString( lM3UID )).replace(' ', '0') + " iAdr="+iAdr);
        }

        Com = CVNavi.safelyOpenCom( this, Com );
        if( Com == null ){
            return;
        }

        byte[] wArray = new byte[8];
        wArray[0] = (byte) 0x78; // x
        wArray[1] = (byte) 0x87; // Xm3Sid (requires 1.4.7b)
        wArray[2] = (byte) (   iAdr           & 0xFF );
        wArray[3] = (byte) ( ( iAdr >> 8 )    & 0xFF );
        wArray[4] = (byte) (   lM3UID         & 0xFF );
        wArray[5] = (byte) ( ( lM3UID >>  8 ) & 0xFF );
        wArray[6] = (byte) ( ( lM3UID >> 16 ) & 0xFF );
        wArray[7] = (byte) ( ( lM3UID >> 24 ) & 0xFF );

        CVNavi.flushReadBuffer(Com);
        Com.write((byte)0x60); // GO
        CVNavi.flushReadBuffer(Com);
        if( debugLevel > 0 ) {
            // TODO evtl this durch this.getContentPane() ersetzen
            CVNavi.mbGeneric( this, "MC", "Xm3Sid adr(sid)="+iAdr+" MAC(uid)="+lM3UID, "writing to MC wArray=0x"+CVNavi.byteArrayToHex(wArray), 10, false );
        }
        Com.write(wArray);
        if( debugLevel > 0 ) {
            System.out.println("written to MC wArray=0x"+CVNavi.byteArrayToHex(wArray));
        }
        bWriteM3sidList = true;
        readWriteProgress = 0;
        jMcM3Progress.setMaximum(2*M3used);

        timer.setInitialDelay(CVNavi.MCtimer1);
        timer.setDelay(CVNavi.MCtimer2);
        startIOAction();
        jMcM3Info.setText("Xm3Sid: write list in progress");
        jMcM3Progress.setValue(++readWriteProgress);
    }//GEN-LAST:event_jLocM3sidWriteListActionPerformed

    private void helpLCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpLCActionPerformed
        HelpInfoLC hLC = new HelpInfoLC( this, false );
        return;
    }//GEN-LAST:event_helpLCActionPerformed

    private void helpHCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpHCActionPerformed
        HelpInfoHC hHC = new HelpInfoHC( this, false );
        return;
    }//GEN-LAST:event_helpHCActionPerformed

    private void helpPCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpPCActionPerformed
        HelpInfoPC hPC = new HelpInfoPC( this, false );
        return;
    }//GEN-LAST:event_helpPCActionPerformed

    private void helpSNCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpSNCActionPerformed
        HelpInfoSNC hSNC = new HelpInfoSNC( this, false );
        return;
    }//GEN-LAST:event_helpSNCActionPerformed

    private void helpXNCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpXNCActionPerformed
        HelpInfoXNC hXNC = new HelpInfoXNC( this, false );
        return;
    }//GEN-LAST:event_helpXNCActionPerformed

    private void helpMCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpMCActionPerformed
        HelpInfoMC hMC = new HelpInfoMC( this, false );
        return;
    }//GEN-LAST:event_helpMCActionPerformed

    private void helpWasIstWasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpWasIstWasActionPerformed
        new HelpInfoWasIstWas( this, false );
        return;
    }//GEN-LAST:event_helpWasIstWasActionPerformed

    private void helpMCasLCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpMCasLCActionPerformed
        // Knopf noch nicht benutuzt -> für Debugging nutzen ;-)
        System.out.println("CVNavi.rs232_or_rb_usb_2="+CVNavi.rs232_or_rb_usb_2+"  CVNavi.rs232_mode_was_forced="+CVNavi.rs232_mode_was_forced);
    }//GEN-LAST:event_helpMCasLCActionPerformed

    private void FirmwareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FirmwareActionPerformed
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                // desktop.browse(new URL(c.TamsFirmwareURL).toURI());
                desktop.browse(new URI(c.TamsFirmwareURL));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return;
    }//GEN-LAST:event_FirmwareActionPerformed

    private void jSysS88monitorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSysS88monitorActionPerformed
        if( debugLevel > 0 ) {
            System.out.println("jSysS88monitorActionPerformed pre NEW");
        }
        if( CVNavi.getNumS88() <= 0 ) {
            CVNavi.mbNoS88modules( this );
            return;            
        }
        S88mon = new S88monitor( this, false );
        if( debugLevel > 0 ) {
            System.out.println("jSysS88monitorActionPerformed post NEW S88mon valid="+(S88mon != null));
        }
    }//GEN-LAST:event_jSysS88monitorActionPerformed

    private void jEasyNetUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jEasyNetUpdateActionPerformed

        if( CVNavi.debugOffline == false ) {
            Com = CVNavi.safelyOpenCom( this, Com );
            if( Com == null ){
                return;
            }

            CVNavi.flushReadBuffer(Com);
            String s = "xSWUPDATE\r";
            Com.write(s);
        }
        resetbArray();
        retries = CVNavi.timerRetries;
        jMcRwProgress.setString(null);
        jMcRwInfo.setText("MC/RedBox set to SWUPDATE mode");
        jEasyNetUpdate.setText(bundle.getString("MC.jEasyNetUpdate.EasyNetActivated"));
        jEasyNetUpdate.setForeground(Color.red);
        // keine Antwort von MC/RB
    }//GEN-LAST:event_jEasyNetUpdateActionPerformed

    private void jBoostOptNoAccDriveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBoostOptNoAccDriveActionPerformed
        updateSo999Value();
    }//GEN-LAST:event_jBoostOptNoAccDriveActionPerformed

    private void jBoostOptNoAccBreakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBoostOptNoAccBreakActionPerformed
        updateSo999Value();
    }//GEN-LAST:event_jBoostOptNoAccBreakActionPerformed

    private void jMRSTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMRSTActionPerformed
        // MRST MasterReset / Zurücksetzen auf Werkseinstellung
        // Sicherheitsabfrage
        int YNresult = CVNavi.yesNoResetFactoryDefault();
        System.out.println("yesNoResetFactoryDefault() : YNresult="+YNresult);
        // 0 = JA , 1 = NEIN
        if( YNresult != 0 ) {
            System.out.println("yesNoResetFactoryDefault() : YNresult="+YNresult+" NO -> SKIP");
            return;
        }
        if( CVNavi.debugOffline == false ) {
            Com = CVNavi.safelyOpenCom( this, Com );
            if( Com == null ){
                return;
            }

            CVNavi.flushReadBuffer(Com);
            String s = "xMRST\r";
            Com.write(s);
        }
        resetbArray();
        retries = CVNavi.timerRetries;
        jMcRwProgress.setString(null);
        jMcRwInfo.setText("MC/RedBox reset to factory default");
        // keine Antwort von MC/RB
    }//GEN-LAST:event_jMRSTActionPerformed

    private void jUSB1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jUSB1ActionPerformed
        rs232_or_rb_usb_2 = false;
        jUSB2.setSelected(false);
        CVNavi.rs232_mode_was_forced = false;
        setDeviceSelection();
    }//GEN-LAST:event_jUSB1ActionPerformed

    private void jUSB2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jUSB2ActionPerformed
        rs232_or_rb_usb_2 = true;
        jUSB1.setSelected(false);
        CVNavi.rs232_mode_was_forced = false;
        setDeviceSelection();
    }//GEN-LAST:event_jUSB2ActionPerformed

    private void jf10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jf10ActionPerformed
        if(AktLokAdr == 0)
            return;
        if(Funktionen[9] == 0)
        {
            Funktionen[9] = 1;
            jf10.setBackground(Color.yellow);
        }
        else
        {
            Funktionen[9] = 0;
            jf10.setBackground(DefBackground);
        }
        String s = "XFX " + AktLokAdr;
        for(int i = 8; i < 16; i++)
        {
            if(Funktionen[i] == 0)
                s += " 0";
            else
                s += " 1";
        }
        if(Com == null)
        {
            Com = CVNavi.safelyOpenCom(this, Com);
        }
        s += "\r";
        if( debugLevel >= 2 ) {
            System.out.println("jf10ActionPerformed: -> send: "+s );
        }
        CVNavi.flushReadBuffer( Com );
        Com.write(s);
    }//GEN-LAST:event_jf10ActionPerformed

    private void jf12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jf12ActionPerformed
        if(AktLokAdr == 0)
            return;
        if(Funktionen[11] == 0)
        {
            Funktionen[11] = 1;
            jf12.setBackground(Color.yellow);
        }
        else
        {
            Funktionen[11] = 0;
            jf12.setBackground(DefBackground);
        }
        String s = "XFX " + AktLokAdr;
        for(int i = 8; i < 16; i++)
        {
            if(Funktionen[i] == 0)
                s += " 0";
            else
                s += " 1";
        }
        if(Com == null)
        {
            Com = CVNavi.safelyOpenCom(this, Com);
        }
        s += "\r";
        if( debugLevel >= 2 ) {
            System.out.println("jf10ActionPerformed: -> send: "+s );
        }
        CVNavi.flushReadBuffer( Com );
        Com.write(s);
    }//GEN-LAST:event_jf12ActionPerformed

    private void jPanel2ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel2ComponentShown
        Com = CVNavi.safelyOpenCom(this, Com);
        if(Com == null) {
            // LRLRLR: Todo Message-Box and return
            return;
        }
        Com.write((byte)0x60); // GO

        jDisplay.setAutoscrolls(false);
      if( AktLokState.length() == 0 ) {
        jDisplay.setText("----          0 \n           -----");
        DisplayCursor = 0;
        DisplayState = -1;
        jHauptGleis.setSelected(true);
        if( CVNavi.getZentrale() == c.cuMasterControl2 )
            jDirektLesen.setEnabled(true);
        else
            jDirektLesen.setEnabled(false);
        jListeLesen.setEnabled(false);
        DefBackground = jf5.getBackground();
        jf5.setEnabled(false);
        jf6.setEnabled(false);
        jf7.setEnabled(false);
        jf8.setEnabled(false);
        jf9.setEnabled(false);
        jf10.setEnabled(false);
        jf11.setEnabled(false);
        jf12.setEnabled(false);
        jf13.setEnabled(false);
        jf14.setEnabled(false);
        jf15.setEnabled(false);
        jf16.setEnabled(false);
        jf17.setEnabled(false);
        jf18.setEnabled(false);
        jf19.setEnabled(false);
        jf20.setEnabled(false);
        jf21.setEnabled(false);
        jf22.setEnabled(false);
        jf23.setEnabled(false);
        jf24.setEnabled(false);
        jf25.setEnabled(false);
        jf26.setEnabled(false);
        jf27.setEnabled(false);
        jf28.setEnabled(false);
      }
        if(jSerNr.getText().contains("-"))
            this.jKonfLesenActionPerformed(null);
    }//GEN-LAST:event_jPanel2ComponentShown

    private void jDirektSchreibenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDirektSchreibenActionPerformed
        Com = CVNavi.safelyOpenCom(this, Com);
        if(Com == null)
        {
            // LRLRLR: Todo Message-Box and return
            return;
        }
        try {
            int CV = Integer.parseInt(jCV_Direkt.getText());
            int Wert = Integer.parseInt(jWertDirekt.getText());
            String s;
            if(jHauptGleis.isSelected())
            {
                int DecAdr = Integer.parseInt(jDecAdr.getText());
                s = "XPD " + DecAdr + " " + CV + " " + Wert + "\r";
                System.out.println("497 jCVSchreibenActionPerformed POM : XPD " + DecAdr + " " + CV + " " + Wert );
                retries = 1;
            }
            else
            {
                s = "XPTWD " + " " + CV + " " + Wert + "\r";
                System.out.println("505 jCVSchreibenActionPerformed not POM : XPTWD " + CV + " " + Wert );
                retries = CVNavi.timerRetries;
            }
            CVNavi.flushReadBuffer( Com );
            Com.write(s);
        } catch (NumberFormatException numberFormatException) {
            CVNavi.mbGeneric( this, "Eingabefehler");
        }
    }//GEN-LAST:event_jDirektSchreibenActionPerformed

    private void jProgGleisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jProgGleisActionPerformed
        jDirektLesen.setEnabled(true);
        jListeLesen.setEnabled(true);
    }//GEN-LAST:event_jProgGleisActionPerformed

    private void jHauptGleisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jHauptGleisActionPerformed
        Com = CVNavi.safelyOpenCom(this, Com);
        if(Com == null)
        {
            // LRLRLR: Todo Message-Box and return
            return;
        }
        Com.write((byte)0x60); // GO
        // LRLRLR jDirektLesen.setEnabled(false);
        if( CVNavi.getZentrale() == c.cuMasterControl2 )
            jDirektLesen.setEnabled(true);
        else
            jDirektLesen.setEnabled(false);
        jListeLesen.setEnabled(false);
    }//GEN-LAST:event_jHauptGleisActionPerformed

    private void jDirektLesenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDirektLesenActionPerformed
        Com = CVNavi.safelyOpenCom(this, Com);
        if(Com == null)
        {
            // LRLRLR: Todo Message-Box and return
            return;
        }
        bReadPTdirekt = true;
        bAskLokState = false;
        CVNavi.flushReadBuffer( Com );
        resetbArray();
        int cvAnfrage = CVNavi.checkTextField( this, jCV_Direkt, 1, 1024, 8, false);

        String s = "";
        if(jHauptGleis.isSelected())
        {
            int DecAdr;
            try {
                DecAdr = Integer.parseInt(jDecAdr.getText());
            } catch (NumberFormatException numberFormatException) {
                DecAdr = 3;
            }
            s = "RCR " + DecAdr + " " + cvAnfrage + "\r";
            System.out.println("7348 jDirektLesenActionPerformed POM : " + s );
        }
        else
        {
            s = "XPTRD " + cvAnfrage + "\r";
            System.out.println("7352 jDirektLesenActionPerformed PT : " + s );
        }

        Com.write(s);
        timer.setInitialDelay(CVNavi.timer1);
        timer.setDelay(CVNavi.timer2);
        timer.setRepeats(true);
        bWaitAnswerInProgress = true;
        retries = CVNavi.timerRetries * 2; // LRLRLR warum doppelte Anzahl ???
        startIOAction();
    }//GEN-LAST:event_jDirektLesenActionPerformed

    private void jListeLesenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jListeLesenActionPerformed
        Com = CVNavi.safelyOpenCom(this, Com);
        if(Com == null)
        {
            // LRLRLR: Todo Message-Box and return
            return;
        }
        bReadPTList = true;
        CVNavi.flushReadBuffer( Com );
        resetbArray();
        jCVListe.clearSelection();
        jCVListe.setSelectedIndex(0);
        String s = (String)jCVListe.getSelectedValue();
        s = "XPTRD " + s + "\r";
        Com.write(s);
        timer.setInitialDelay(CVNavi.timer1);
        timer.setDelay(CVNavi.timer2);
        timer.setRepeats(true);
        bWaitAnswerInProgress = true;
        retries = CVNavi.timerRetries * 2; // LRLRLR warum doppelte Anzahl ???
        startIOAction();
    }//GEN-LAST:event_jListeLesenActionPerformed

    public ListModel getList()
    {
        return jCVListe.getModel();
    }

    public void setList(String s)
    {
        DefaultListModel dlm = new DefaultListModel();
        String[] split = s.split("\n");
        for(int i = 0; i < split.length; i++)
        {
            if(split[i].contains("\t"))
            {
                split[i] = split[i].replace("\t", "       ");
            }
            //ToDo   Auf Gültigkeit prüfen
            dlm.add(i, split[i]);
        }
        jCVListe.setModel(dlm);
    }

    private void jListeBearbeitenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jListeBearbeitenActionPerformed
        /* Aufruf der Listenbearbeitung öffnet modales Fenster -> aufrufendes Fenster ist eh nicht bedienbar
        ** sonst ggf. Auslagerung in startIOAction() / stopOPAction mit optionalem Parameter...
        jDirektLesen.setEnabled(false);
        jListeBearbeiten.setEnabled(false);
        jListeLesen.setEnabled(false);
        jListeSchreiben.setEnabled(false);
        jDirektSchreiben.setEnabled(false);
        */
        new MC_RB_CV_List(this, true).setVisible(true);
        /*
        if(!jHauptGleis.isSelected()) jDirektLesen.setEnabled(true);
        jListeBearbeiten.setEnabled(true);
        if(!jHauptGleis.isSelected()) jListeLesen.setEnabled(true);
        jListeSchreiben.setEnabled(true);
        jDirektSchreiben.setEnabled(true);
        */
    }//GEN-LAST:event_jListeBearbeitenActionPerformed

    private void jListeSchreibenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jListeSchreibenActionPerformed
        Com = CVNavi.safelyOpenCom(this, Com);
        if(Com == null)
        {
            // LRLRLR: Todo Message-Box and return
            return;
        }
        bWriteList = true;
        CVNavi.flushReadBuffer( Com );
        resetbArray();
        jCVListe.clearSelection();
        jCVListe.setSelectedIndex(0);
        String s = (String)jCVListe.getSelectedValue();
        if(jHauptGleis.isSelected())
        {
            int DecAdr;
            try {
                DecAdr = Integer.parseInt(jDecAdr.getText());
            } catch (NumberFormatException numberFormatException) {
                DecAdr = 3;
            }
            s = "XPD " + DecAdr +" "+ s + "\r";
            System.out.println("7456 jCVSchreibenActionPerformed POM : " + s );
        }
        else
        {
            s = "XPTWD " + s + "\r";
            System.out.println("7461 jCVSchreibenActionPerformed PT : " + s );
        }
        Com.write(s);
        timer.setInitialDelay(CVNavi.timer1);
        timer.setDelay(CVNavi.timer2);
        timer.setRepeats(true);
        bWaitAnswerInProgress = true;
        retries = CVNavi.timerRetries * 2; // LRLRLR warum doppelte Anzahl ???
        startIOAction();
    }//GEN-LAST:event_jListeSchreibenActionPerformed

    private void jm3SchreibenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jm3SchreibenActionPerformed
        Boolean sidOK = CVNavi.checkNumRange( jm3Addr.getText(), 1, c.MAX_M3_SID );
        if( ! sidOK ) {
            CVNavi.mbValueNaN( this, 1, c.MAX_M3_SID, true );
            jm3Addr.grabFocus();
            return;
        }
        Com = CVNavi.safelyOpenCom(this, Com);
        if(Com == null)
        {
            // LRLRLR: Todo Message-Box and return
            return;
        }
        bProg_m3 = true;
        jm3GotoList.setEnabled(false);
        CVNavi.flushReadBuffer( Com );
        resetbArray();

        String s = "xMFX " + jm3Addr.getText() + "\r";
        Com.write(s);
        timer.setInitialDelay(CVNavi.timer1);
        timer.setDelay(CVNavi.timer2);
        timer.setRepeats(true);
        bWaitAnswerInProgress = true;
        retries = 0; //CVNavi.timerRetries * 4;
        startIOAction();
    }//GEN-LAST:event_jm3SchreibenActionPerformed

    private void jMMWertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMMWertActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMMWertActionPerformed

    private void jHerstellerInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jHerstellerInfoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jHerstellerInfoActionPerformed

    private void jStartMMProgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jStartMMProgActionPerformed
        Com = CVNavi.safelyOpenCom(this, Com);
        if(Com == null)
        {
            // LRLRLR: Todo Message-Box and return
            return;
        }
        bProg_MM = true;
        Com.write((byte)0x61); // STOP
        CVNavi.flushReadBuffer( Com );
        resetbArray();

        CVNavi.mbGeneric(this, "Hinweis", "Für die Lokadressen " + jMMRegister.getText() + " und " + jMMWert.getText() + " muss das MM Protokoll eingestellt werde.", "Bitte stellen Sie an Ihrer MM-Lok nun den Programmiermodus ein.");
        timer.setInitialDelay(CVNavi.timer1);
        timer.setDelay(CVNavi.timer2);
        timer.setRepeats(true);
        bWaitAnswerInProgress = true;
        retries = 14; // LRLRLR Warum genau 14 ? KTKTKT Zeit für den Einstieg geben...
        startIOAction();
        Com.write("XTPM 80\r");
    }//GEN-LAST:event_jStartMMProgActionPerformed

    private void j1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_j1ActionPerformed
        if(DisplayState != 0)
        {
            DisplayState = 0;
            OldDisplayString = jDisplay.getText();
            jDisplay.setText("ADR: 1      " + jDisplay.getText().substring(12, 17) + "*=Abbruch       ");
            DisplayCursor = 5;
        }
        String text = jDisplay.getText();
        byte[] ba = text.getBytes();
        if(DisplayCursor < 10)
        {
            ba[DisplayCursor++] = '1';
        }

        text = new String(ba);
        jDisplay.setText(text);
    }//GEN-LAST:event_j1ActionPerformed

    private void jSternActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSternActionPerformed
        if(DisplayState == 0)
        {
            jDisplay.setText(OldDisplayString);
            DisplayState = -1;
        }
    }//GEN-LAST:event_jSternActionPerformed

    private void j2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_j2ActionPerformed
        if(DisplayState != 0)
        {
            DisplayState = 0;
            OldDisplayString = jDisplay.getText();
            jDisplay.setText("ADR: 2      " + jDisplay.getText().substring(12, 17) + "*=Abbruch       ");
            DisplayCursor = 5;
        }
        String text = jDisplay.getText();
        byte[] ba = text.getBytes();
        if(DisplayCursor < 10)
        {
            ba[DisplayCursor++] = '2';
        }

        text = new String(ba);
        jDisplay.setText(text);
    }//GEN-LAST:event_j2ActionPerformed

    private void j3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_j3ActionPerformed
        if(DisplayState != 0)
        {
            DisplayState = 0;
            OldDisplayString = jDisplay.getText();
            jDisplay.setText("ADR: 3      " + jDisplay.getText().substring(12, 17) + "*=Abbruch       ");
            DisplayCursor = 5;
        }
        String text = jDisplay.getText();
        byte[] ba = text.getBytes();
        if(DisplayCursor < 10)
        {
            ba[DisplayCursor++] = '3';
        }

        text = new String(ba);
        jDisplay.setText(text);
    }//GEN-LAST:event_j3ActionPerformed

    private void j4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_j4ActionPerformed
        if(DisplayState != 0)
        {
            DisplayState = 0;
            OldDisplayString = jDisplay.getText();
            jDisplay.setText("ADR: 4      " + jDisplay.getText().substring(12, 17) + "*=Abbruch       ");
            DisplayCursor = 5;
        }
        String text = jDisplay.getText();
        byte[] ba = text.getBytes();
        if(DisplayCursor < 10)
        {
            ba[DisplayCursor++] = '4';
        }

        text = new String(ba);
        jDisplay.setText(text);
    }//GEN-LAST:event_j4ActionPerformed

    private void j5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_j5ActionPerformed
        if(DisplayState != 0)
        {
            DisplayState = 0;
            OldDisplayString = jDisplay.getText();
            jDisplay.setText("ADR: 5      " + jDisplay.getText().substring(12, 17) + "*=Abbruch       ");
            DisplayCursor = 5;
        }
        String text = jDisplay.getText();
        byte[] ba = text.getBytes();
        if(DisplayCursor < 10)
        {
            ba[DisplayCursor++] = '5';
        }

        text = new String(ba);
        jDisplay.setText(text);
    }//GEN-LAST:event_j5ActionPerformed

    private void j6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_j6ActionPerformed
        if(DisplayState != 0)
        {
            DisplayState = 0;
            OldDisplayString = jDisplay.getText();
            jDisplay.setText("ADR: 6      " + jDisplay.getText().substring(12, 17) + "*=Abbruch       ");
            DisplayCursor = 5;
        }
        String text = jDisplay.getText();
        byte[] ba = text.getBytes();
        if(DisplayCursor < 10)
        {
            ba[DisplayCursor++] = '6';
        }

        text = new String(ba);
        jDisplay.setText(text);
    }//GEN-LAST:event_j6ActionPerformed

    private void j7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_j7ActionPerformed
        if(DisplayState != 0)
        {
            DisplayState = 0;
            OldDisplayString = jDisplay.getText();
            jDisplay.setText("ADR: 7      " + jDisplay.getText().substring(12, 17) + "*=Abbruch       ");
            DisplayCursor = 5;
        }
        String text = jDisplay.getText();
        byte[] ba = text.getBytes();
        if(DisplayCursor < 10)
        {
            ba[DisplayCursor++] = '7';
        }

        text = new String(ba);
        jDisplay.setText(text);
    }//GEN-LAST:event_j7ActionPerformed

    private void j8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_j8ActionPerformed
        if(DisplayState != 0)
        {
            DisplayState = 0;
            OldDisplayString = jDisplay.getText();
            jDisplay.setText("ADR: 8      " + jDisplay.getText().substring(12, 17) + "*=Abbruch       ");
            DisplayCursor = 5;
        }
        String text = jDisplay.getText();
        byte[] ba = text.getBytes();
        if(DisplayCursor < 10)
        {
            ba[DisplayCursor++] = '8';
        }

        text = new String(ba);
        jDisplay.setText(text);
    }//GEN-LAST:event_j8ActionPerformed

    private void j9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_j9ActionPerformed
        if(DisplayState != 0)
        {
            DisplayState = 0;
            OldDisplayString = jDisplay.getText();
            jDisplay.setText("ADR: 9      " + jDisplay.getText().substring(12, 17) + "*=Abbruch       ");
            DisplayCursor = 5;
        }
        String text = jDisplay.getText();
        byte[] ba = text.getBytes();
        if(DisplayCursor < 10)
        {
            ba[DisplayCursor++] = '9';
        }

        text = new String(ba);
        jDisplay.setText(text);
    }//GEN-LAST:event_j9ActionPerformed

    private void j0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_j0ActionPerformed
        if(DisplayState != 0)
        {
            return;
        }
        String text = jDisplay.getText();
        byte[] ba = text.getBytes();
        if(DisplayCursor < 10)
        {
            ba[DisplayCursor++] = '0';
        }

        text = new String(ba);
        jDisplay.setText(text);
    }//GEN-LAST:event_j0ActionPerformed

    private void jRauteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRauteActionPerformed
        if(DisplayState == 0)
        {
            String text = jDisplay.getText();
            int parseInt;
            String substring = text.substring(5, DisplayCursor);
            try {
                parseInt = Integer.parseInt(substring);
            } catch (NumberFormatException numberFormatException) {
                parseInt = 16384;
            }
            if(parseInt > 16384)
                return;
            if(substring.length() < 5)
            {
                substring += "     ";
                substring = substring.substring(0, 5);
            }
            text = substring + "           \nplease wait     " ;
            jDisplay.setText(text);
            DisplayState = -1;

            bAskLokState = true;
            if(Com == null)
            {
                Com = CVNavi.safelyOpenCom(this, Com);
            }
            CVNavi.flushReadBuffer( Com );
            resetbArray();

            System.out.println("LokAnfrage Adr.: "+parseInt);
            String s = "XLC " + parseInt + "\r";
            AskedLokAdr = parseInt;
            AktLokAdr = 0;
            if( debugLevel >= 2 ) {
                System.out.println("jRauteActionPerformed: -> send: "+s );
                System.out.println("jRauteActionPerformed: END" );
            }
            Com.write(s);
            timer.setInitialDelay(CVNavi.timer1);
            timer.setDelay(CVNavi.timer2);
            timer.setRepeats(true);
            bWaitAnswerInProgress = true;
            retries = CVNavi.timerRetries * 2;
            timer.start();
        }

    }//GEN-LAST:event_jRauteActionPerformed

    private void jGeschwindigkeitCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_jGeschwindigkeitCaretPositionChanged

    }//GEN-LAST:event_jGeschwindigkeitCaretPositionChanged

    private void jGeschwindigkeitStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jGeschwindigkeitStateChanged
        //AktLokState = z.B. 3 20 0 r 1 0 0 1
        int v = jGeschwindigkeit.getValue();
        String s = AktLokState.substring(AktLokState.indexOf(" ")+1);
        s = s.substring(0, s.indexOf(" "));
        int Vrb = 0;
        try {
            Vrb = Integer.parseInt(s);
        } catch (NumberFormatException numberFormatException) {
            Vrb = 0;
        }
        if(Vrb != v)
        {
            //neue Geschwindigkeit senden
            s = AktLokState.substring(0, AktLokState.indexOf(" "));
            s = "XL " + s + " " + v + "\r"; 
            if(Com == null)
            {
                Com = CVNavi.safelyOpenCom(this, Com);
            }
            if( debugLevel >= 2 ) {
                System.out.println("jGeschwindigkeitStateChanged: -> send: "+s );
                System.out.println("jGeschwindigkeitStateChanged: END" );
            }
            Com.write(s);
            s = jDisplay.getText();
            s = s.substring(0, 12);
            int V = v;
            switch(Fahrstufen)
            {
                case 14:
                    V = (V + 8)/9;
                    break;

                case 27:
                    V = (V*3 + 11)/14;
                    break;

                case 28:
                    V = (V*2 + 7)/9;
                    break;
            }
            s += V + "  ";
            s = s.substring(0, 15);
            s += jDisplay.getText().substring(15);
            jDisplay.setText(s);
            s = "" + v + "  ";
            s = s.substring(0, 3);
            int i = AktLokState.indexOf(" ")+1;
            String str = AktLokState.substring(AktLokState.indexOf(" ")+1);
            str = str.substring(str.indexOf(" "));
            AktLokState = AktLokState.substring(0, i) + v + str;
            CVNavi.sleep( 100 );
        }
    }//GEN-LAST:event_jGeschwindigkeitStateChanged

    private void jGOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jGOActionPerformed
        if(Com == null)
        {
            Com = CVNavi.safelyOpenCom(this, Com);
        }
        Com.write((byte)0x60); // GO
        String s = jDisplay.getText().substring(0, 7) + AktLokFormat + jDisplay.getText().substring(11);
        jDisplay.setText(s);
    }//GEN-LAST:event_jGOActionPerformed

    private void jSTOPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSTOPActionPerformed
        if(Com == null)
        {
            Com = CVNavi.safelyOpenCom(this, Com);
        }
        Com.write((byte)0x61); // STOP
        String s = jDisplay.getText().substring(0, 7) + "STOP" + jDisplay.getText().substring(11);
        jDisplay.setText(s);
    }//GEN-LAST:event_jSTOPActionPerformed

    private void jf0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jf0ActionPerformed
        //AktLokState = z.B. 3 20 0 r 1 0 0 1
        if( AktLokState.length() == 0 ) {
            // state not initialized
            return;
        }
        String[] ary = AktLokState.split(" ");
        if( ary.length < 3 ) {
            // not enough elements
            return;
        }
        String s = ary[2];
        if(s.contains("0"))
        {
            s = jDisplay.getText();
            s = s.substring(0, 28);
            s += "*";
            s += jDisplay.getText().substring(29);
            jDisplay.setText(s);
            ary[2] = "1";
        }
        else
        {
            s = jDisplay.getText();
            s = s.substring(0, 28);
            s += "-";
            s += jDisplay.getText().substring(29);
            jDisplay.setText(s);
            ary[2] = "0";
        }
        if(Com == null)
        {
            Com = CVNavi.safelyOpenCom(this, Com);
        }
        AktLokState = Arrays.toString(ary);
        AktLokState = Arrays.toString(ary)
                .replace(",", "")  //remove the commas
                .replace("[", "")  //remove the right bracket
                .replace("]", "")  //remove the left bracket
                .trim();           //remove trailing spaces from partially initialized arrays;
        AktLokState = String.join(" ", ary) ;
        AktLokState = "";
        for (String item : ary) {
            AktLokState += item+" ";
        }
        AktLokState = AktLokState.trim();
        s = "XL " + AktLokState + "\r";
        if( debugLevel >= 2 ) {
            System.out.println("jf0ActionPerformed: -> send: "+s );
            System.out.println("jf0ActionPerformed: END" );
        }
        CVNavi.flushReadBuffer( Com );
        Com.write(s);
    }//GEN-LAST:event_jf0ActionPerformed

    private void jf1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jf1ActionPerformed
        if(DisplayState == 0)
        {
            String text = jDisplay.getText();
            int parseInt;
            String substring = text.substring(5, DisplayCursor);
            try {
                parseInt = Integer.parseInt(substring);
            } catch (NumberFormatException numberFormatException) {
                parseInt = 16384;
            }
            if(parseInt > 16384)
                return;
            jDisplay.setText(OldDisplayString);
            DisplayState = -1;
            if(Com == null)
            {
                Com = CVNavi.safelyOpenCom(this, Com);
            }
            CVNavi.flushReadBuffer( Com );
            resetbArray();

            System.out.print("Weiche: "+parseInt + "gerade");
            String s = "XT " + parseInt + " g 1\r";
            if( debugLevel >= 2 ) {
                System.out.println("jf1ActionPerformed: DisplayState==0 -> send: "+s );
                System.out.println("jf1ActionPerformed: DisplayState==0 END" );
            }
            Com.write(s);
            return;
        }
        if( AktLokState.length() == 0 ) {
            // state not initialized
            return;
        }
        //AktLokState = z.B. 3 20 0 r 1 0 0 1
        String s = AktLokState.substring(AktLokState.indexOf(" ")+1);
        s = s.substring(s.indexOf(" ")+1);
        s = s.substring(s.indexOf(" ")+1);
        s = s.substring(s.indexOf(" ")+1);
        s = s.substring(0, s.indexOf(" "));
        int Index;
        String str;
        if(s.contains("0"))
        {
            s = jDisplay.getText();
            s = s.substring(0, 29);
            s += "*";
            s += jDisplay.getText().substring(30);
            jDisplay.setText(s);
            Index = AktLokState.indexOf(" ")+1;
            s = AktLokState.substring(0,Index);
            str = AktLokState.substring(Index);
            s += str.substring(0, str.indexOf(" ")+1);
            str = str.substring(str.indexOf(" ")+1);
            s += str.substring(0, 4) + "1 ";
            AktLokState = s + str.substring(6, str.length());
            Funktionen[0] = 1;
        }
        else
        {
            s = jDisplay.getText();
            s = s.substring(0, 29);
            s += "-";
            s += jDisplay.getText().substring(30);
            jDisplay.setText(s);
            Index = AktLokState.indexOf(" ")+1;
            s = AktLokState.substring(0,Index);
            str = AktLokState.substring(Index);
            s += str.substring(0, str.indexOf(" ")+1);
            str = str.substring(str.indexOf(" ")+1);
            s += str.substring(0, 4) + "0 ";
            AktLokState = s + str.substring(6, str.length());
            Funktionen[0] = 0;
        }
        if(Com == null)
        {
            Com = CVNavi.safelyOpenCom(this, Com);
        }
        s = "XL " + AktLokState + "\r";
        if( debugLevel >= 2 ) {
            System.out.println("jf1ActionPerformed: -> send: "+s );
        }
        CVNavi.flushReadBuffer( Com );
        if( debugLevel >= 2 ) {
            System.out.println("jf1ActionPerformed: -> send: "+s );
        }
        Com.write(s);
    }//GEN-LAST:event_jf1ActionPerformed

    private void jf2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jf2ActionPerformed
        if(DisplayState == 0)
        {
            String text = jDisplay.getText();
            int parseInt;
            String substring = text.substring(5, DisplayCursor);
            try {
                parseInt = Integer.parseInt(substring);
            } catch (NumberFormatException numberFormatException) {
                parseInt = 16384;
            }
            if(parseInt > 16384)
                return;
            jDisplay.setText(OldDisplayString);
            DisplayState = -1;
            if(Com == null)
            {
                Com = CVNavi.safelyOpenCom(this, Com);
            }
            System.out.print("Weiche: "+parseInt + "Abzweig");
            String s = "XT " + parseInt + " r 1\r";
            if( debugLevel >= 2 ) {
                System.out.println("jf2ActionPerformed: DisplayState==0 -> send: "+s );
                System.out.println("jf2ActionPerformed: DisplayState==0 END" );
            }
            CVNavi.flushReadBuffer( Com );
            resetbArray();
            Com.write(s);
            return;
        }
        if( AktLokState.length() == 0 ) {
            // state not initialized
            return;
        }
        //AktLokState = z.B. 3 20 0 r 1 0 0 1
        String s = AktLokState.substring(AktLokState.indexOf(" ")+1);
        s = s.substring(s.indexOf(" ")+1);
        s = s.substring(s.indexOf(" ")+1);
        s = s.substring(s.indexOf(" ")+1);
        s = s.substring(s.indexOf(" ")+1);
        s = s.substring(0, s.indexOf(" "));
        int Index;
        String str;
        if(s.contains("0"))
        {
            s = jDisplay.getText();
            s = s.substring(0, 30);
            s += "*";
            s += jDisplay.getText().substring(31);
            jDisplay.setText(s);
            Index = AktLokState.indexOf(" ")+1;
            s = AktLokState.substring(0,Index);
            str = AktLokState.substring(Index);
            s += str.substring(0, str.indexOf(" ")+1);
            str = str.substring(str.indexOf(" ")+1);
            s += str.substring(0, 6) + "1 ";
            AktLokState = s + str.substring(8, str.length());
            Funktionen[1] = 1;
       }
        else
        {
            s = jDisplay.getText();
            s = s.substring(0, 30);
            s += "-";
            s += jDisplay.getText().substring(31);
            jDisplay.setText(s);
            Index = AktLokState.indexOf(" ")+1;
            s = AktLokState.substring(0,Index);
            str = AktLokState.substring(Index);
            s += str.substring(0, str.indexOf(" ")+1);
            str = str.substring(str.indexOf(" ")+1);
            s += str.substring(0, 6) + "0 ";
            AktLokState = s + str.substring(8, str.length());
            Funktionen[1] = 0;
        }
        if(Com == null)
        {
            Com = CVNavi.safelyOpenCom(this, Com);
        }
        s = "XL " + AktLokState + "\r";
        if( debugLevel >= 2 ) {
            System.out.println("jf2ActionPerformed: -> send: "+s );
        }
        CVNavi.flushReadBuffer( Com );
        Com.write(s);
    }//GEN-LAST:event_jf2ActionPerformed

    private void jf3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jf3ActionPerformed
        if( AktLokState.length() == 0 ) {
            // state not initialized
            return;
        }
        //AktLokState = z.B. 3 20 0 r 1 0 0 1
        String s = AktLokState.substring(AktLokState.indexOf(" ")+1);
        s = s.substring(s.indexOf(" ")+1);
        s = s.substring(s.indexOf(" ")+1);
        s = s.substring(s.indexOf(" ")+1);
        s = s.substring(s.indexOf(" ")+1);
        s = s.substring(s.indexOf(" ")+1);
        s = s.substring(0, s.indexOf(" "));
        int Index;
        String str;
        if(s.contains("0"))
        {
            s = jDisplay.getText();
            s = s.substring(0, 31);
            s += "*";
            s += jDisplay.getText().substring(32);
            jDisplay.setText(s);
            Index = AktLokState.indexOf(" ")+1;
            s = AktLokState.substring(0,Index);
            str = AktLokState.substring(Index);
            s += str.substring(0, str.indexOf(" ")+1);
            str = str.substring(str.indexOf(" ")+1);
            s += str.substring(0, 8) + "1 ";
            AktLokState = s + str.substring(10, str.length());
            Funktionen[2] = 1;
        }
        else
        {
            s = jDisplay.getText();
            s = s.substring(0, 31);
            s += "-";
            s += jDisplay.getText().substring(32);
            jDisplay.setText(s);
            Index = AktLokState.indexOf(" ")+1;
            s = AktLokState.substring(0,Index);
            str = AktLokState.substring(Index);
            s += str.substring(0, str.indexOf(" ")+1);
            str = str.substring(str.indexOf(" ")+1);
            s += str.substring(0, 8) + "0 ";
            AktLokState = s + str.substring(10, str.length());
            Funktionen[2] = 0;
        }
        if(Com == null)
        {
            Com = CVNavi.safelyOpenCom(this, Com);
        }
        s = "XL " + AktLokState + "\r";
        if( debugLevel >= 2 ) {
            System.out.println("jf3ActionPerformed: -> send: "+s );
        }
        CVNavi.flushReadBuffer( Com );
        Com.write(s);
    }//GEN-LAST:event_jf3ActionPerformed

    private void jf4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jf4ActionPerformed
        if( AktLokState.length() == 0 ) {
            // state not initialized
            return;
        }
        //AktLokState = z.B. 3 20 0 r 1 0 0 1
        String s = AktLokState.substring(AktLokState.indexOf(" ")+1);
        s = s.substring(s.lastIndexOf(" ")+1);
        int Index;
        String str;
        if(s.contains("0"))
        {
            s = jDisplay.getText();
            s = s.substring(0, 32);
            s += "*";
            s += jDisplay.getText().substring(33);
            jDisplay.setText(s);
            Index = AktLokState.indexOf(" ")+1;
            s = AktLokState.substring(0,Index);
            str = AktLokState.substring(Index);
            s += str.substring(0, str.indexOf(" ")+1);
            str = str.substring(str.indexOf(" ")+1);
            AktLokState = s + str.substring(0, 10) + "1";
            Funktionen[3] = 1;
        }
        else
        {
            s = jDisplay.getText();
            s = s.substring(0, 32);
            s += "-";
            s += jDisplay.getText().substring(33);
            jDisplay.setText(s);
            Index = AktLokState.indexOf(" ")+1;
            s = AktLokState.substring(0,Index);
            str = AktLokState.substring(Index);
            s += str.substring(0, str.indexOf(" ")+1);
            str = str.substring(str.indexOf(" ")+1);
            AktLokState = s + str.substring(0, 10) + "0";
            Funktionen[3] = 0;
        }
        if(Com == null)
        {
            Com = CVNavi.safelyOpenCom(this, Com);
        }
        s = "XL " + AktLokState + "\r";
        if( debugLevel >= 2 ) {
            System.out.println("jf4ActionPerformed: -> send: "+s );
        }
        CVNavi.flushReadBuffer( Com );
        Com.write(s);
    }//GEN-LAST:event_jf4ActionPerformed

    private void jf5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jf5ActionPerformed
        if(AktLokAdr == 0)
            return;
        if(Funktionen[4] == 0)
        {
            Funktionen[4] = 1;
            jf5.setBackground(Color.yellow);
        }
        else
        {
            Funktionen[4] = 0;
            jf5.setBackground(DefBackground);
        }
        String s = "XF " + AktLokAdr;
        for(int i = 0; i < 8; i++)
        {
            if(Funktionen[i] == 0)
                s += " 0";
            else
                s += " 1";
        }
        if(Com == null)
        {
            Com = CVNavi.safelyOpenCom(this, Com);
        }
        s += "\r";
        if( debugLevel >= 2 ) {
            System.out.println("jf5ActionPerformed: -> send: "+s );
        }
        CVNavi.flushReadBuffer( Com );
        Com.write(s);
    }//GEN-LAST:event_jf5ActionPerformed

    private void jf6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jf6ActionPerformed
        if(AktLokAdr == 0)
            return;
        if(Funktionen[5] == 0)
        {
            Funktionen[5] = 1;
            jf6.setBackground(Color.yellow);
        }
        else
        {
            Funktionen[5] = 0;
            jf6.setBackground(DefBackground);
        }
        String s = "XF " + AktLokAdr;
        for(int i = 0; i < 8; i++)
        {
            if(Funktionen[i] == 0)
                s += " 0";
            else
                s += " 1";
        }
        if(Com == null)
        {
            Com = CVNavi.safelyOpenCom(this, Com);
        }
        s += "\r";
        if( debugLevel >= 2 ) {
            System.out.println("jf6ActionPerformed: -> send: "+s );
        }
        CVNavi.flushReadBuffer( Com );
        Com.write(s);
    }//GEN-LAST:event_jf6ActionPerformed

    private void jf7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jf7ActionPerformed
        if(AktLokAdr == 0)
            return;
        if(Funktionen[6] == 0)
        {
            Funktionen[6] = 1;
            jf7.setBackground(Color.yellow);
        }
        else
        {
            Funktionen[6] = 0;
            jf7.setBackground(DefBackground);
        }
        String s = "XF " + AktLokAdr;
        for(int i = 0; i < 8; i++)
        {
            if(Funktionen[i] == 0)
                s += " 0";
            else
                s += " 1";
        }
        if(Com == null)
        {
            Com = CVNavi.safelyOpenCom(this, Com);
        }
        s += "\r";
        if( debugLevel >= 2 ) {
            System.out.println("jf7ActionPerformed: -> send: "+s );
        }
        CVNavi.flushReadBuffer( Com );
        Com.write(s);
    }//GEN-LAST:event_jf7ActionPerformed

    private void jf8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jf8ActionPerformed
        if(AktLokAdr == 0)
            return;
        if(Funktionen[7] == 0)
        {
            Funktionen[7] = 1;
            jf8.setBackground(Color.yellow);
        }
        else
        {
            Funktionen[7] = 0;
            jf8.setBackground(DefBackground);
        }
        String s = "XF " + AktLokAdr;
        for(int i = 0; i < 8; i++)
        {
            if(Funktionen[i] == 0)
                s += " 0";
            else
                s += " 1";
        }
        if(Com == null)
        {
            Com = CVNavi.safelyOpenCom(this, Com);
        }
        s += "\r";
        if( debugLevel >= 2 ) {
            System.out.println("jf8ActionPerformed: -> send: "+s );
        }
        CVNavi.flushReadBuffer( Com );
        Com.write(s);
    }//GEN-LAST:event_jf8ActionPerformed

    private void jf9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jf9ActionPerformed
        if(AktLokAdr == 0)
            return;
        if(Funktionen[8] == 0)
        {
            Funktionen[8] = 1;
            jf9.setBackground(Color.yellow);
        }
        else
        {
            Funktionen[8] = 0;
            jf9.setBackground(DefBackground);
        }
        String s = "XFX " + AktLokAdr;
        for(int i = 8; i < 16; i++)
        {
            if(Funktionen[i] == 0)
                s += " 0";
            else
                s += " 1";
        }
        if(Com == null)
        {
            Com = CVNavi.safelyOpenCom(this, Com);
        }
        s += "\r";
        if( debugLevel >= 2 ) {
            System.out.println("jf9ActionPerformed: -> send: "+s );
        }
        CVNavi.flushReadBuffer( Com );
        Com.write(s);
    }//GEN-LAST:event_jf9ActionPerformed

    private void jf11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jf11ActionPerformed
        if(AktLokAdr == 0)
            return;
        if(Funktionen[10] == 0)
        {
            Funktionen[10] = 1;
            jf11.setBackground(Color.yellow);
        }
        else
        {
            Funktionen[10] = 0;
            jf11.setBackground(DefBackground);
        }
        String s = "XFX " + AktLokAdr;
        for(int i = 8; i < 16; i++)
        {
            if(Funktionen[i] == 0)
                s += " 0";
            else
                s += " 1";
        }
        if(Com == null)
        {
            Com = CVNavi.safelyOpenCom(this, Com);
        }
        s += "\r";
        if( debugLevel >= 2 ) {
            System.out.println("jf11ActionPerformed: -> send: "+s );
        }
        CVNavi.flushReadBuffer( Com );
        Com.write(s);
    }//GEN-LAST:event_jf11ActionPerformed

    private void jf13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jf13ActionPerformed
        if(AktLokAdr == 0)
            return;
        if(Funktionen[12] == 0)
        {
            Funktionen[12] = 1;
            jf13.setBackground(Color.yellow);
        }
        else
        {
            Funktionen[12] = 0;
            jf13.setBackground(DefBackground);
        }
        String s = "XFX " + AktLokAdr;
        for(int i = 8; i < 16; i++)
        {
            if(Funktionen[i] == 0)
                s += " 0";
            else
                s += " 1";
        }
        if(Com == null)
        {
            Com = CVNavi.safelyOpenCom(this, Com);
        }
        s += "\r";
        if( debugLevel >= 2 ) {
            System.out.println("jf13ActionPerformed: -> send: "+s );
        }
        CVNavi.flushReadBuffer( Com );
        Com.write(s);
    }//GEN-LAST:event_jf13ActionPerformed

    private void jf14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jf14ActionPerformed
        if(AktLokAdr == 0)
            return;
        if(Funktionen[13] == 0)
        {
            Funktionen[13] = 1;
            jf14.setBackground(Color.yellow);
        }
        else
        {
            Funktionen[13] = 0;
            jf14.setBackground(DefBackground);
        }
        String s = "XFX " + AktLokAdr;
        for(int i = 8; i < 16; i++)
        {
            if(Funktionen[i] == 0)
                s += " 0";
            else
                s += " 1";
        }
        if(Com == null)
        {
            Com = CVNavi.safelyOpenCom(this, Com);
        }
        s += "\r";
        if( debugLevel >= 2 ) {
            System.out.println("jf14ActionPerformed: -> send: "+s );
        }
        CVNavi.flushReadBuffer( Com );
        Com.write(s);
    }//GEN-LAST:event_jf14ActionPerformed

    private void jf15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jf15ActionPerformed
        if(AktLokAdr == 0)
            return;
        if(Funktionen[14] == 0)
        {
            Funktionen[14] = 1;
            jf15.setBackground(Color.yellow);
        } else {
            Funktionen[14] = 0;
            jf15.setBackground(DefBackground);
        }

        byte bBefehl[] = new byte[5] ;
        bBefehl[0] = (byte) 'X';
        bBefehl[1] = (byte) 0x89; // XFuncX
        bBefehl[2] = (byte) (AktLokAdr & 0x00FF);
        bBefehl[3] = (byte) ((AktLokAdr & 0xFF00) >> 8 );
        bBefehl[4] = (byte) 0x00;
        for(int i = 8; i < 16; i++)
        {
            if(Funktionen[i] == 1)
                bBefehl[4] |= (byte) 0x01 << (i-8) ;
        }
        if(Com == null)
        {
            Com = CVNavi.safelyOpenCom(this, Com);
        }
        if( debugLevel >= 2 ) {
            System.out.println("jf15ActionPerformed: -> send: "+bBefehl.toString() );
        }
        CVNavi.flushReadBuffer( Com );
        // Com.write(s);
        Com.write(bBefehl);

    }//GEN-LAST:event_jf15ActionPerformed

    private void jf16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jf16ActionPerformed
        if(AktLokAdr == 0)
            return;
        if(Funktionen[15] == 0)
        {
            Funktionen[15] = 1;
            jf16.setBackground(Color.yellow);
        }
        else
        {
            Funktionen[15] = 0;
            jf16.setBackground(DefBackground);
        }
        byte bBefehl[] = new byte[5] ;
        bBefehl[0] = (byte) 'X';
        bBefehl[1] = (byte) 0x89; // XFuncX
        bBefehl[2] = (byte) (AktLokAdr & 0x00FF);
        bBefehl[3] = (byte) ((AktLokAdr & 0xFF00) >> 8 );
        bBefehl[4] = (byte) 0x00;
        for(int i = 8; i < 16; i++)
        {
            if(Funktionen[i] == 1)
                bBefehl[4] |= (byte) 0x01 << (i-8) ;
        }
        if( debugLevel >= 2 ) {
            System.out.println(" jf16ActionPerformed: -> send: \""+byteArrayToHex(bBefehl)+"\" len="+bBefehl.length );
        }
        if(Com == null)
        {
            Com = CVNavi.safelyOpenCom(this, Com);
        }
        CVNavi.flushReadBuffer( Com );
        Com.write(bBefehl);
    }//GEN-LAST:event_jf16ActionPerformed

    private void jRueckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRueckActionPerformed
        //AktLokState = z.B. 3 20 0 r 1 0 0 1
        String s = AktLokState.substring(AktLokState.indexOf(" ")+1);
        s = s.substring(s.indexOf(" ")+1);
        AktLokState = AktLokState.substring(0, AktLokState.indexOf(" ")+1) + "0 " + s;
        jGeschwindigkeit.setValue(0);
        if(AktLokState.contains("f"))
        {
            s = jDisplay.getText();
            s = s.substring(0, 12);
            s += "0  v";
            s += jDisplay.getText().substring(16);
            jDisplay.setText(s);
            AktLokState = AktLokState.replace('f', 'r');
        }
        if(Com == null)
        {
            Com = CVNavi.safelyOpenCom(this, Com);
        }
        s = "XL " + AktLokState + "\r";
        if( debugLevel >= 2 ) {
            System.out.println("jRueckActionPerformed: -> send: "+s );
        }
        CVNavi.flushReadBuffer( Com );
        Com.write(s);
    }//GEN-LAST:event_jRueckActionPerformed

    private void jVorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jVorActionPerformed
        //AktLokState = z.B. 3 20 0 r 1 0 0 1
        String s = AktLokState.substring(AktLokState.indexOf(" ")+1);
        s = s.substring(s.indexOf(" ")+1);
        AktLokState = AktLokState.substring(0, AktLokState.indexOf(" ")+1) + "0 " + s;
        jGeschwindigkeit.setValue(0);
        if(AktLokState.contains("r"))
        {
            s = jDisplay.getText();
            s = s.substring(0, 12);
            s += "0  ^";
            s += jDisplay.getText().substring(16);
            jDisplay.setText(s);
            AktLokState = AktLokState.replace('r', 'f');
        }
        if(Com == null)
        {
            Com = CVNavi.safelyOpenCom(this, Com);
        }
        s = "XL " + AktLokState + "\r";
        if( debugLevel >= 2 ) {
            System.out.println("jVorActionPerformed: -> send: "+s );
        }
        CVNavi.flushReadBuffer( Com );
        Com.write(s);
    }//GEN-LAST:event_jVorActionPerformed

    private void jTableLocoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableLocoMouseClicked
        jFuncIcon.setEnabled(true);
    }//GEN-LAST:event_jTableLocoMouseClicked

    private void jconDevsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jconDevsActionPerformed
        Com = CVNavi.safelyOpenCom( this, Com );
        if( Com == null ){
            return;
        }
        if(bconnectedDevicesIsActiv)
        {
            return;
        }
        connectedDevices = new ConnectedDevices(this);
        CVNavi.flushReadBuffer(Com);
        String s = "XCVER\r";
        Com.write(s);
        resetbArray();
        retries = CVNavi.timerRetries;
        jMcRwProgress.setString(null);
        bWaitAnswerInProgress = true;
        bReadDevices = true;
        readWriteProgress = 0;
        jMcRwProgress.setMaximum(64);
        jMcRwProgress.setValue(0);
        timer.setInitialDelay(CVNavi.MCtimer1);
        timer.setDelay(CVNavi.MCtimer2);
        startIOAction();
        jMcRwInfo.setText("read: MC config read in progress");
    }//GEN-LAST:event_jconDevsActionPerformed

    private void jm3GotoListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jm3GotoListActionPerformed
        System.out.println("jm3GotoListActionPerformed: CALL");
        M3L = new M3_Liste( this, true );
        System.out.println("jm3GotoListActionPerformed: RETURN");
    }//GEN-LAST:event_jm3GotoListActionPerformed

    private void jf17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jf17ActionPerformed
        if(AktLokAdr == 0)
            return;
        if(Funktionen[16] == 0)
        {
            Funktionen[16] = 1;
            jf17.setBackground(Color.yellow);
        }
        else
        {
            Funktionen[16] = 0;
            jf17.setBackground(DefBackground);
        }
        byte bBefehl[] = new byte[6] ;
        bBefehl[0] = (byte) 'X';
        bBefehl[1] = (byte) 0x8A; // XFunc34
        bBefehl[2] = (byte) (AktLokAdr & 0x00FF);
        bBefehl[3] = (byte) ((AktLokAdr & 0xFF00) >> 8 );
        bBefehl[4] = (byte) 0x00;
        bBefehl[5] = (byte) 0x00;
        for(int i = 16; i < 24; i++)
        {
            if(Funktionen[i] == 1)
                bBefehl[4] |= (byte) 0x01 << (i-16) ;
        }
        for(int i = 24; i < 31; i++)
        {
            if(Funktionen[i] == 1)
                bBefehl[5] |= (byte) 0x01 << (i-24) ;
        }
        if( debugLevel >= 2 ) {
            System.out.println(" jf17ActionPerformed: -> send: \""+byteArrayToHex(bBefehl)+"\" len="+bBefehl.length );
        }
        if(Com == null)
        {
            Com = CVNavi.safelyOpenCom(this, Com);
        }
        CVNavi.flushReadBuffer( Com );
        Com.write(bBefehl);
    }//GEN-LAST:event_jf17ActionPerformed

    private void jf18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jf18ActionPerformed
        if(AktLokAdr == 0)
            return;
        if(Funktionen[17] == 0)
        {
            Funktionen[17] = 1;
            jf18.setBackground(Color.yellow);
        }
        else
        {
            Funktionen[17] = 0;
            jf18.setBackground(DefBackground);
        }
        byte bBefehl[] = new byte[6] ;
        bBefehl[0] = (byte) 'X';
        bBefehl[1] = (byte) 0x8A; // XFunc34
        bBefehl[2] = (byte) (AktLokAdr & 0x00FF);
        bBefehl[3] = (byte) ((AktLokAdr & 0xFF00) >> 8 );
        bBefehl[4] = (byte) 0x00;
        bBefehl[5] = (byte) 0x00;
        for(int i = 16; i < 24; i++)
        {
            if(Funktionen[i] == 1)
                bBefehl[4] |= (byte) 0x01 << (i-16) ;
        }
        for(int i = 24; i < 31; i++)
        {
            if(Funktionen[i] == 1)
                bBefehl[5] |= (byte) 0x01 << (i-24) ;
        }
        if( debugLevel >= 2 ) {
            System.out.println(" jf18ActionPerformed: -> send: \""+byteArrayToHex(bBefehl)+"\" len="+bBefehl.length );
        }
        if(Com == null)
        {
            Com = CVNavi.safelyOpenCom(this, Com);
        }
        CVNavi.flushReadBuffer( Com );
        Com.write(bBefehl);
    }//GEN-LAST:event_jf18ActionPerformed

    private void jf19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jf19ActionPerformed
        if(AktLokAdr == 0)
            return;
        if(Funktionen[18] == 0)
        {
            Funktionen[18] = 1;
            jf19.setBackground(Color.yellow);
        }
        else
        {
            Funktionen[18] = 0;
            jf19.setBackground(DefBackground);
        }
        byte bBefehl[] = new byte[6] ;
        bBefehl[0] = (byte) 'X';
        bBefehl[1] = (byte) 0x8A; // XFunc34
        bBefehl[2] = (byte) (AktLokAdr & 0x00FF);
        bBefehl[3] = (byte) ((AktLokAdr & 0xFF00) >> 8 );
        bBefehl[4] = (byte) 0x00;
        bBefehl[5] = (byte) 0x00;
        for(int i = 16; i < 24; i++)
        {
            if(Funktionen[i] == 1)
                bBefehl[4] |= (byte) 0x01 << (i-16) ;
        }
        for(int i = 24; i < 31; i++)
        {
            if(Funktionen[i] == 1)
                bBefehl[5] |= (byte) 0x01 << (i-24) ;
        }
        if( debugLevel >= 2 ) {
            System.out.println(" jf19ActionPerformed: -> send: \""+byteArrayToHex(bBefehl)+"\" len="+bBefehl.length );
        }
        if(Com == null)
        {
            Com = CVNavi.safelyOpenCom(this, Com);
        }
        CVNavi.flushReadBuffer( Com );
        Com.write(bBefehl);
    }//GEN-LAST:event_jf19ActionPerformed

    private void jf20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jf20ActionPerformed
        if(AktLokAdr == 0)
            return;
        if(Funktionen[19] == 0)
        {
            Funktionen[19] = 1;
            jf20.setBackground(Color.yellow);
        }
        else
        {
            Funktionen[19] = 0;
            jf20.setBackground(DefBackground);
        }
        byte bBefehl[] = new byte[6] ;
        bBefehl[0] = (byte) 'X';
        bBefehl[1] = (byte) 0x8A; // XFunc34
        bBefehl[2] = (byte) (AktLokAdr & 0x00FF);
        bBefehl[3] = (byte) ((AktLokAdr & 0xFF00) >> 8 );
        bBefehl[4] = (byte) 0x00;
        bBefehl[5] = (byte) 0x00;
        for(int i = 16; i < 24; i++)
        {
            if(Funktionen[i] == 1)
                bBefehl[4] |= (byte) 0x01 << (i-16) ;
        }
        for(int i = 24; i < 31; i++)
        {
            if(Funktionen[i] == 1)
                bBefehl[5] |= (byte) 0x01 << (i-24) ;
        }
        if( debugLevel >= 2 ) {
            System.out.println(" jf20ActionPerformed: -> send: \""+byteArrayToHex(bBefehl)+"\" len="+bBefehl.length );
        }
        if(Com == null)
        {
            Com = CVNavi.safelyOpenCom(this, Com);
        }
        CVNavi.flushReadBuffer( Com );
        Com.write(bBefehl);
    }//GEN-LAST:event_jf20ActionPerformed

    private void jf21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jf21ActionPerformed
        if(AktLokAdr == 0)
            return;
        if(Funktionen[20] == 0)
        {
            Funktionen[20] = 1;
            jf21.setBackground(Color.yellow);
        }
        else
        {
            Funktionen[20] = 0;
            jf21.setBackground(DefBackground);
        }
        byte bBefehl[] = new byte[6] ;
        bBefehl[0] = (byte) 'X';
        bBefehl[1] = (byte) 0x8A; // XFunc34
        bBefehl[2] = (byte) (AktLokAdr & 0x00FF);
        bBefehl[3] = (byte) ((AktLokAdr & 0xFF00) >> 8 );
        bBefehl[4] = (byte) 0x00;
        bBefehl[5] = (byte) 0x00;
        for(int i = 16; i < 24; i++)
        {
            if(Funktionen[i] == 1)
                bBefehl[4] |= (byte) 0x01 << (i-16) ;
        }
        for(int i = 24; i < 31; i++)
        {
            if(Funktionen[i] == 1)
                bBefehl[5] |= (byte) 0x01 << (i-24) ;
        }
        if( debugLevel >= 2 ) {
            System.out.println(" jf21ActionPerformed: -> send: \""+byteArrayToHex(bBefehl)+"\" len="+bBefehl.length );
        }
        if(Com == null)
        {
            Com = CVNavi.safelyOpenCom(this, Com);
        }
        CVNavi.flushReadBuffer( Com );
        Com.write(bBefehl);
    }//GEN-LAST:event_jf21ActionPerformed

    private void jf22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jf22ActionPerformed
        if(AktLokAdr == 0)
            return;
        if(Funktionen[21] == 0)
        {
            Funktionen[21] = 1;
            jf22.setBackground(Color.yellow);
        }
        else
        {
            Funktionen[21] = 0;
            jf22.setBackground(DefBackground);
        }
        byte bBefehl[] = new byte[6] ;
        bBefehl[0] = (byte) 'X';
        bBefehl[1] = (byte) 0x8A; // XFunc34
        bBefehl[2] = (byte) (AktLokAdr & 0x00FF);
        bBefehl[3] = (byte) ((AktLokAdr & 0xFF00) >> 8 );
        bBefehl[4] = (byte) 0x00;
        bBefehl[5] = (byte) 0x00;
        for(int i = 16; i < 24; i++)
        {
            if(Funktionen[i] == 1)
                bBefehl[4] |= (byte) 0x01 << (i-16) ;
        }
        for(int i = 24; i < 31; i++)
        {
            if(Funktionen[i] == 1)
                bBefehl[5] |= (byte) 0x01 << (i-24) ;
        }
        if( debugLevel >= 2 ) {
            System.out.println(" jf22ActionPerformed: -> send: \""+byteArrayToHex(bBefehl)+"\" len="+bBefehl.length );
        }
        if(Com == null)
        {
            Com = CVNavi.safelyOpenCom(this, Com);
        }
        CVNavi.flushReadBuffer( Com );
        Com.write(bBefehl);
    }//GEN-LAST:event_jf22ActionPerformed

    private void jf23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jf23ActionPerformed
        if(AktLokAdr == 0)
            return;
        if(Funktionen[22] == 0)
        {
            Funktionen[22] = 1;
            jf23.setBackground(Color.yellow);
        }
        else
        {
            Funktionen[22] = 0;
            jf23.setBackground(DefBackground);
        }
        byte bBefehl[] = new byte[6] ;
        bBefehl[0] = (byte) 'X';
        bBefehl[1] = (byte) 0x8A; // XFunc34
        bBefehl[2] = (byte) (AktLokAdr & 0x00FF);
        bBefehl[3] = (byte) ((AktLokAdr & 0xFF00) >> 8 );
        bBefehl[4] = (byte) 0x00;
        bBefehl[5] = (byte) 0x00;
        for(int i = 16; i < 24; i++)
        {
            if(Funktionen[i] == 1)
                bBefehl[4] |= (byte) 0x01 << (i-16) ;
        }
        for(int i = 24; i < 31; i++)
        {
            if(Funktionen[i] == 1)
                bBefehl[5] |= (byte) 0x01 << (i-24) ;
        }
        if( debugLevel >= 2 ) {
            System.out.println(" jf23ActionPerformed: -> send: \""+byteArrayToHex(bBefehl)+"\" len="+bBefehl.length );
        }
        if(Com == null)
        {
            Com = CVNavi.safelyOpenCom(this, Com);
        }
        CVNavi.flushReadBuffer( Com );
        Com.write(bBefehl);
    }//GEN-LAST:event_jf23ActionPerformed

    private void jf24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jf24ActionPerformed
        if(AktLokAdr == 0)
            return;
        if(Funktionen[23] == 0)
        {
            Funktionen[23] = 1;
            jf24.setBackground(Color.yellow);
        }
        else
        {
            Funktionen[23] = 0;
            jf24.setBackground(DefBackground);
        }
        byte bBefehl[] = new byte[6] ;
        bBefehl[0] = (byte) 'X';
        bBefehl[1] = (byte) 0x8A; // XFunc34
        bBefehl[2] = (byte) (AktLokAdr & 0x00FF);
        bBefehl[3] = (byte) ((AktLokAdr & 0xFF00) >> 8 );
        bBefehl[4] = (byte) 0x00;
        bBefehl[5] = (byte) 0x00;
        for(int i = 16; i < 24; i++)
        {
            if(Funktionen[i] == 1)
                bBefehl[4] |= (byte) 0x01 << (i-16) ;
        }
        for(int i = 24; i < 31; i++)
        {
            if(Funktionen[i] == 1)
                bBefehl[5] |= (byte) 0x01 << (i-24) ;
        }
        if( debugLevel >= 2 ) {
            System.out.println(" jf24ActionPerformed: -> send: \""+byteArrayToHex(bBefehl)+"\" len="+bBefehl.length );
        }
        if(Com == null)
        {
            Com = CVNavi.safelyOpenCom(this, Com);
        }
        CVNavi.flushReadBuffer( Com );
        Com.write(bBefehl);
    }//GEN-LAST:event_jf24ActionPerformed

    private void jf25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jf25ActionPerformed
        if(AktLokAdr == 0)
            return;
        if(Funktionen[24] == 0)
        {
            Funktionen[24] = 1;
            jf25.setBackground(Color.yellow);
        }
        else
        {
            Funktionen[24] = 0;
            jf25.setBackground(DefBackground);
        }
        byte bBefehl[] = new byte[6] ;
        bBefehl[0] = (byte) 'X';
        bBefehl[1] = (byte) 0x8A; // XFunc34
        bBefehl[2] = (byte) (AktLokAdr & 0x00FF);
        bBefehl[3] = (byte) ((AktLokAdr & 0xFF00) >> 8 );
        bBefehl[4] = (byte) 0x00;
        bBefehl[5] = (byte) 0x00;
        for(int i = 16; i < 24; i++)
        {
            if(Funktionen[i] == 1)
                bBefehl[4] |= (byte) 0x01 << (i-16) ;
        }
        for(int i = 24; i < 31; i++)
        {
            if(Funktionen[i] == 1)
                bBefehl[5] |= (byte) 0x01 << (i-24) ;
        }
        if( debugLevel >= 2 ) {
            System.out.println(" jf25ActionPerformed: -> send: \""+byteArrayToHex(bBefehl)+"\" len="+bBefehl.length );
        }
        if(Com == null)
        {
            Com = CVNavi.safelyOpenCom(this, Com);
        }
        CVNavi.flushReadBuffer( Com );
        Com.write(bBefehl);
    }//GEN-LAST:event_jf25ActionPerformed

    private void jf26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jf26ActionPerformed
        if(AktLokAdr == 0)
            return;
        if(Funktionen[25] == 0)
        {
            Funktionen[25] = 1;
            jf26.setBackground(Color.yellow);
        }
        else
        {
            Funktionen[25] = 0;
            jf26.setBackground(DefBackground);
        }
        byte bBefehl[] = new byte[6] ;
        bBefehl[0] = (byte) 'X';
        bBefehl[1] = (byte) 0x8A; // XFunc34
        bBefehl[2] = (byte) (AktLokAdr & 0x00FF);
        bBefehl[3] = (byte) ((AktLokAdr & 0xFF00) >> 8 );
        bBefehl[4] = (byte) 0x00;
        bBefehl[5] = (byte) 0x00;
        for(int i = 16; i < 24; i++)
        {
            if(Funktionen[i] == 1)
                bBefehl[4] |= (byte) 0x01 << (i-16) ;
        }
        for(int i = 24; i < 31; i++)
        {
            if(Funktionen[i] == 1)
                bBefehl[5] |= (byte) 0x01 << (i-24) ;
        }
        if( debugLevel >= 2 ) {
            System.out.println(" jf26ActionPerformed: -> send: \""+byteArrayToHex(bBefehl)+"\" len="+bBefehl.length );
        }
        if(Com == null)
        {
            Com = CVNavi.safelyOpenCom(this, Com);
        }
        CVNavi.flushReadBuffer( Com );
        Com.write(bBefehl);
    }//GEN-LAST:event_jf26ActionPerformed

    private void jf27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jf27ActionPerformed
        if(AktLokAdr == 0)
            return;
        if(Funktionen[26] == 0)
        {
            Funktionen[26] = 1;
            jf27.setBackground(Color.yellow);
        }
        else
        {
            Funktionen[26] = 0;
            jf27.setBackground(DefBackground);
        }
        byte bBefehl[] = new byte[6] ;
        bBefehl[0] = (byte) 'X';
        bBefehl[1] = (byte) 0x8A; // XFunc34
        bBefehl[2] = (byte) (AktLokAdr & 0x00FF);
        bBefehl[3] = (byte) ((AktLokAdr & 0xFF00) >> 8 );
        bBefehl[4] = (byte) 0x00;
        bBefehl[5] = (byte) 0x00;
        for(int i = 16; i < 24; i++)
        {
            if(Funktionen[i] == 1)
                bBefehl[4] |= (byte) 0x01 << (i-16) ;
        }
        for(int i = 24; i < 31; i++)
        {
            if(Funktionen[i] == 1)
                bBefehl[5] |= (byte) 0x01 << (i-24) ;
        }
        if( debugLevel >= 2 ) {
            System.out.println(" jf27ActionPerformed: -> send: \""+byteArrayToHex(bBefehl)+"\" len="+bBefehl.length );
        }
        if(Com == null)
        {
            Com = CVNavi.safelyOpenCom(this, Com);
        }
        CVNavi.flushReadBuffer( Com );
        Com.write(bBefehl);
    }//GEN-LAST:event_jf27ActionPerformed

    private void jf28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jf28ActionPerformed
        if(AktLokAdr == 0)
            return;
        if(Funktionen[27] == 0)
        {
            Funktionen[27] = 1;
            jf28.setBackground(Color.yellow);
        }
        else
        {
            Funktionen[27] = 0;
            jf28.setBackground(DefBackground);
        }
        byte bBefehl[] = new byte[6] ;
        bBefehl[0] = (byte) 'X';
        bBefehl[1] = (byte) 0x8A; // XFunc34
        bBefehl[2] = (byte) (AktLokAdr & 0x00FF);
        bBefehl[3] = (byte) ((AktLokAdr & 0xFF00) >> 8 );
        bBefehl[4] = (byte) 0x00;
        bBefehl[5] = (byte) 0x00;
        for(int i = 16; i < 24; i++)
        {
            if(Funktionen[i] == 1)
                bBefehl[4] |= (byte) 0x01 << (i-16) ;
        }
        for(int i = 24; i < 31; i++)
        {
            if(Funktionen[i] == 1)
                bBefehl[5] |= (byte) 0x01 << (i-24) ;
        }
        if( debugLevel >= 2 ) {
            System.out.println(" jf28ActionPerformed: -> send: \""+byteArrayToHex(bBefehl)+"\" len="+bBefehl.length );
        }
        if(Com == null)
        {
            Com = CVNavi.safelyOpenCom(this, Com);
        }
        CVNavi.flushReadBuffer( Com );
        Com.write(bBefehl);
    }//GEN-LAST:event_jf28ActionPerformed

    private void jFuncIconActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFuncIconActionPerformed
        if(bFunktionsIconsIsActiv)
        return;
        int edRow = jTableLoco.getSelectedRow();
        if( edRow >= 0)
        {
            String valueAt = (String)jTableLoco.getValueAt(edRow, 0);
            if(valueAt.length() > 0)
            {
                try {
                    int parseInt = Integer.parseInt(valueAt);
                    FunktionsIcons FI = new FunktionsIcons(this);
                    FI.DecAdress = parseInt;
                    FI.DecName = (String)jTableLoco.getValueAt(edRow, 3);
                    FI.DecIcons = FuncIcons;
                } catch (NumberFormatException numberFormatException) {
                }
            }
        }
    }//GEN-LAST:event_jFuncIconActionPerformed

    public Boolean m3dataAvaliable() {
        return jm3GotoList.isEnabled();
    }

    public String m3dataGetSid() {
        return jm3Addr.getText();
    }

    public String m3dataGetUid() {
        return jmfxUID.getText();
    }

    private Boolean checkM3uidValid() {
        if( checkM3uidValidActive )
            return false;
        checkM3uidValidActive = true;
        String sM3UID = ""+jTextM3UID.getText().trim();
        if( debugLevel > 0 ) {
            System.out.println("checkM3uidValid sM3UID="+sM3UID);
        }
        if( sM3UID.length() == 0 ) {
            jTextM3UID.setText(sM3UID);
            // jTextM3UID.requestFocus();
            checkM3uidValidActive = false;
            CVNavi.mbGeneric( this, "Eingabefehler", "M3 UID ungültig", sM3UID, 9, true );
            return false;
        }

        // int iM3UID = 0;
        long lM3UID = 0;

        // if not present add a hex prefix
        if( ! sM3UID.toLowerCase().startsWith("0x") ) {
            sM3UID = "0x"+sM3UID;
        }

        try {
            // iM3UID = Integer.decode(sM3UID);
            lM3UID = Long.decode(sM3UID);
        } catch (NumberFormatException ex) {
            System.out.println("checkM3uidValid NumberFormatException (LONG)" );
            jTextM3UID.setText(sM3UID);
            CVNavi.mbGeneric( this, "Eingabefehler", "M3 UID ungültig", sM3UID, 9, true );
            jTextM3UID.requestFocus();
            checkM3uidValidActive = false;
            return false;
        }
        if( debugLevel > 0 ) {
            System.out.println("checkM3uidValid lM3UID="+lM3UID );
            System.out.println("checkM3uidValid l3UID=0x"+String.format("%8s", Long.toHexString( lM3UID )).replace(' ', '0'));
        }

        sM3UID = String.format("0x%8s", Long.toHexString( lM3UID )).replace(' ', '0');
        if( sM3UID.length() > 10 ) {
            if( debugLevel > 0 ) {
                System.out.println("checkM3uidValid NumberFormatException (INTEGER)" );
            }
            jTextM3UID.setText(sM3UID);
            CVNavi.mbGeneric( this, "Eingabefehler", "M3 UID ungültig (zu groß)", sM3UID, 9, true );
            jTextM3UID.requestFocus();
            checkM3uidValidActive = false;
            return false;
        }

        jTextM3UID.setText(sM3UID);

        checkM3uidValidActive = false;
        return true;
    }

    public String checkM3uidValid( String sIn ) {
        if( (sIn == null)  || (sIn.length() == 0) )
            return null;
        String sM3UID = sIn.trim().toLowerCase();
        if( debugLevel > 2 ) {
            System.out.println("checkM3uidValid sM3UID="+sM3UID);
        }

        // int iM3UID = 0;
        long lM3UID = 0;

        // if not present add a hex prefix
        if( ! sM3UID.toLowerCase().startsWith("0x") ) {
            sM3UID = "0x"+sM3UID;
        }

        try {
            // iM3UID = Integer.decode(sM3UID);
            lM3UID = Long.decode(sM3UID);
        } catch (NumberFormatException ex) {
            System.out.println("checkM3uidValid NumberFormatException (LONG) sM3UID="+sM3UID );
            return null;
        }
        if( debugLevel > 2 ) {
            System.out.println("checkM3uidValid l3UID=0x"+String.format("%8s", Long.toHexString( lM3UID )).replace(' ', '0'));
        }

        sM3UID = String.format("0x%8s", Long.toHexString( lM3UID )).replace(' ', '0').toLowerCase();
        if( sM3UID.length() > 10 ) {
            System.out.println("checkM3uidValid too long after conversion" );
            return null;
        }

        return sM3UID;
    }

    private void startIOAction() {
        Com = CVNavi.safelyOpenCom( this, Com );
        if( Com == null ) {
            stopIOAction();
            return;
        }
        // Reset some variables
        sysIdx = 0;
        locIdx = 0;
        traIdx = 0;
        magIdx = 0;
        // set buttons for IO in progress
        jCancel.setEnabled(true);
        jUpdCancel.setEnabled(true);
        jKonfLesen.setEnabled(false);
        jKonfSchreiben.setEnabled(false);
        jWRsys.setEnabled(false);
        jWRloc.setEnabled(false);
        jWRtra.setEnabled(false);
        jWRmag.setEnabled(false);
        jKonfLaden.setEnabled(false);
        jKonfSichern.setEnabled(false);
        jconDevs.setEnabled(false);
        // loco tab (while writing m3sid/uid
        jLocDel.setEnabled(false);
        jLocDelAll.setEnabled(false);
        jLocCheck.setEnabled(false);
        jLocRepair.setEnabled(false);
        jLocM3sidWriteList.setEnabled(false);
        jLocM3cfgLoad.setEnabled(false);
        jLocM3cfgEdit.setEnabled(false);
        jLocM3cfgSave.setEnabled(false);
        jLoc2System.setEnabled(false);

        // disable some editable fields
        jLangePause.setEnabled(false);
        jDCC_Booster.setEnabled(false);
        jDCC_Loks.setEnabled(false);
        jRailcomTailbits.setEnabled(false);
        jRailcomIdNotify.setEnabled(false);
        jRailcomAccessory.setEnabled(false);
        jBoostOptNoAccDrive.setEnabled(false);
        jBoostOptNoAccBreak.setEnabled(false);
        jKurzEmpf.setEnabled(false);
        js88.setEnabled(false);
        jMinMag.setEnabled(false);
        jMaxMag.setEnabled(false);
        jTableLoco.setEnabled(false);
        jTableTraction.setEnabled(false);
        jTableAccessory.setEnabled(false);

        jUpdDatei.setEnabled(false);
        jUpdDateiAuswahl.setEnabled(false);
        jUpdStartUpdate.setEnabled(false);

        jEasyNetUpdate.setEnabled(false);
        jMRST.setEnabled(false);

        if( CVNavi.getZentrale() == c.cuMasterControl2 )
            jDirektLesen.setEnabled(true);
        else
            jDirektLesen.setEnabled(false);
        jListeLesen.setEnabled(false);
        jListeBearbeiten.setEnabled(false);
        jListeSchreiben.setEnabled(false);
        jDirektSchreiben.setEnabled(false);

        jm3Addr.setEnabled(false);
        jm3Schreiben.setEnabled(false);

        jMMRegister.setEnabled(false);
        jMMWert.setEnabled(false);
        jStartMMProg.setEnabled(false);

        // init progress bars
        jMcRwProgress.setValue(0);

        // set cursor to WAIT
        Cursor c = new Cursor(Cursor.WAIT_CURSOR);
        this.setCursor(c);
        //start timer
        timer.start();
    }
    private void stopIOAction() {
        // stop timer
        timer.stop();

        if( bReadCfg ) {
            bReadCfg = false;
            jMcRwInfo.setText("read config cancelled");
            CVNavi.mbConfigReadCancelled( this, 5 );
        }
        if( bReadRC ) {
            bReadRC = false;
            jMcRwInfo.setText("read RC cancelled");
            CVNavi.mbConfigReadCancelled( this, 5 );
        }
        if( bWriteCfg ) {
            bWriteCfg = false;
            jMcRwInfo.setText("write config cancelled");
            CVNavi.mbConfigWriteCancelled( this, 5 );
        }
        if( bWriteM3sid ) {
            bWriteM3sid = false;
            jMcRwInfo.setText("write M3SID cancelled");
            jMcM3Info.setText("write M3SID cancelled");
        }
        if( bWriteM3sidList ) {
            bWriteM3sidList = false;
            jMcRwInfo.setText("write M3SID list cancelled");
            jMcM3Info.setText("write M3SID list cancelled");
        }
        if( bUpdate ) {
            bUpdate = false;
            CVNavi.gsBaudRate = gsBaudRateSaved;
            if( CVNavi.bSpracheDE )
                jMcUpdInfo.setText("Aktualisierung abgebrochen");
            else
                jMcUpdInfo.setText("update cancelled");
        }

        if( bAskLokState ) {
            bAskLokState = false;
        }
        if( bReadPTdirekt ) {
            bReadPTdirekt = false;
        }
        if( bReadPTList ) {
            bReadPTList = false;
        }
        if( bWriteList ) {
            bWriteList = false;
        }
        if( bProg_m3 ) {
            bProg_m3 = false;
        }
        if( bProg_MM ) {
            bProg_MM = false;
        }
        if( bReadS88num ) {
            bReadS88num = false;
        }
        if( bReadS88value ) {
            bReadS88value = false;
        }
        if( bReadSo999 ) {
            bReadSo999 = false;
        }
        if( bReadDevices ) {
            bReadDevices = false;
        }

        // set buttons to normal operation
        jKonfLesen.setEnabled(true);
        jKonfSchreiben.setEnabled(true);
        jWRsys.setEnabled(true);
        jWRloc.setEnabled(true);
        jWRtra.setEnabled(true);
        jWRmag.setEnabled(true);
        jKonfLaden.setEnabled(true);
        jKonfSichern.setEnabled(true);
        jCancel.setEnabled(false);
        jUpdCancel.setEnabled(false);
        jconDevs.setEnabled(true);

        // (re-)enable some editable fields
        jLangePause.setEnabled(true);
        jDCC_Booster.setEnabled(true);
        jDCC_Loks.setEnabled(true);
        jRailcomTailbits.setEnabled(true);
        jRailcomIdNotify.setEnabled(true);
        jRailcomAccessory.setEnabled(true);
        if( CVNavi.bUseSo999 ) {
            jBoostOptNoAccDrive.setEnabled(true);
            jBoostOptNoAccBreak.setEnabled(true);
        }
        jKurzEmpf.setEnabled(true);
        js88.setEnabled(true);
        jMinMag.setEnabled(true);
        jMaxMag.setEnabled(true);
        jTableLoco.setEnabled(true);
        jTableTraction.setEnabled(true);
        jTableAccessory.setEnabled(true);
        // loco tab (while writing m3sid/uid
        jLocDel.setEnabled(true);
        jLocDelAll.setEnabled(true);
        jLocCheck.setEnabled(true);
        jLocRepair.setEnabled(true);
        if( M3used > 0 ) { jLocM3sidWriteList.setEnabled(true); }
        jLocM3cfgLoad.setEnabled(true);
        jLocM3cfgEdit.setEnabled(true);
        jLocM3cfgSave.setEnabled(true);
        jLoc2System.setEnabled(true);

        jUpdDatei.setEnabled(true);
        jUpdDateiAuswahl.setEnabled(true);
        jUpdStartUpdate.setEnabled(true);

        jEasyNetUpdate.setEnabled(true);
        jMRST.setEnabled(true);

        if( ! jHauptGleis.isSelected() ) {
            jDirektLesen.setEnabled(true);
            jListeLesen.setEnabled(true);
        }
        jListeBearbeiten.setEnabled(true);
        jListeSchreiben.setEnabled(true);
        jDirektSchreiben.setEnabled(true);

        jm3Addr.setEnabled(true);
        jm3Schreiben.setEnabled(true);

        jMMRegister.setEnabled(true);
        jMMWert.setEnabled(true);
        jStartMMProg.setEnabled(true);

        // for mc2 disable some options
        if( CVNavi.getZentrale() == c.cuMasterControl2 ) {
            jKonfSchreiben.setEnabled(false);
            jMRST.setEnabled(false);
        }

        // set cursor
        Cursor c = new Cursor(Cursor.DEFAULT_CURSOR );
        setCursor(c);

        // close interface
        /* TODO auch hier ??? */
        Com = CVNavi.safelyCloseCom( this, Com);
    }

    private void updateS88field( int num ) {
        CVNavi.setNumS88(num);
        js88.setText(""+num);
        jSysS88monitor.setEnabled(num > 0);
    }

    public void readS88num() {
        if( debugLevel > 0 ) {
            System.out.println("readS88num()" );
        }
        Com = CVNavi.safelyOpenCom( this, Com );
        if( Com == null ){
            System.out.println("readS88num() Com == null" );
            return;
        }

        CVNavi.flushReadBuffer(Com);
        String s = "XSE\r";
        Com.write(s);
        timer.setInitialDelay(CVNavi.MCtimer1);
        timer.setDelay(CVNavi.MCtimer2);
        retries = CVNavi.timerRetries;
        bWaitAnswerInProgress = true;
        bReadS88num = true;
        startIOAction();
    }

    public void initS88read() {
        if( debugLevel > 0 ) {
            System.out.println("initS88read()" );
        }
        Com = CVNavi.safelyOpenCom( this, Com );
        if( Com == null ){
            System.out.println("initS88read() Com == null" );
            return;
        }

        CVNavi.flushReadBuffer(Com);
        String s = "XSR 0\r";
        Com.write(s);
        timer.setInitialDelay(CVNavi.MCtimer1);
        timer.setDelay(CVNavi.MCtimer2);
        startIOAction();

        jCancel.setEnabled(false);
        jUpdCancel.setEnabled(false);
        jKonfLesen.setEnabled(false);
        jSysS88monitor.setEnabled(false);
    }

    public void startS88read() {
        if( Com == null ){
            System.out.println("startS88read() ERROR Com == null" );
            return;
        }
        if( debugLevel > 1 ) {
            System.out.println("startS88read() moduleNr "+this.modulNr );
        }
        if( this.modulNr <= 0 ) {
            System.out.println("startS88read() moduleNr="+this.modulNr+" <= 0 -> skip reading" );
            return;
        }

        String s = "XSS "+this.modulNr+"\r";
        Com.write(s);
        resetbArray();
        retries = CVNavi.timerRetries;
        bWaitAnswerInProgress = true;
        bReadS88value = true;
        timer.start();
    }

    public void stopS88read() {
        if( debugLevel > 0 ) {
            System.out.println("stopS88read()" );
        }
        stopIOAction();
        jSysS88monitor.setEnabled(true);
        bReadS88value = false;
    }

    public void pauseS88read() {
        if( debugLevel > 1 ) {
            System.out.println("pauseS88read()" );
        }
        timer.stop();
        bReadS88value = false;
    }

    private boolean checkS88num( byte[] bArray, int num ) {
        if( bArray == null ) {
            return false;
        }
        // convert byteArray into a String
        String str_bArr = new String(bArray);
        // Split string into parts
        String[] strParts = str_bArr.split(" = | |\r|\\000");
        if( debugLevel > 1 ) {
            for( int i = 0 ; i < strParts.length ; i++ ) {
                System.out.println("checkS88num strParts["+i+"]=\""+strParts[i]+"\""+((strParts[i].startsWith("]"))?" (ENDE)":"" ));
            }
        }
        if( strParts.length == 3) {
            if( strParts[0].startsWith("SE") && strParts[2].startsWith("]") ) {
                // answer is complete !
                // answer is number of 8 sensor modules !
                System.out.println("checkS88num strParts[1]=\""+strParts[1]+"\"");
                int num8 = Integer.parseInt(strParts[1]);
                int num16 = (num8+1)/2 ;
                System.out.println("checkS88num #modules(16)="+num16);
                updateS88field(num16);
                return true;
            }
        }
        return false;
    }

    private boolean checkS88read( byte[] bArray, int num ) {
        if( bArray == null ) {
            return false;
        }
        // convert byteArray into a String
        String str_bArr = new String(bArray);
        // Split string into parts
        String[] strParts = str_bArr.split(" = | |\r|\\000");
        if( debugLevel > 1 ) {
            for( int i = 0 ; i < strParts.length ; i++ ) {
                System.out.println("checkS88read strParts["+i+"]=\""+strParts[i]+"\""+((strParts[i].startsWith("]"))?" (ENDE)":"" ));
            }
        }
        if( strParts.length == 9) {
            if( strParts[8].startsWith("]") ) {
                // answer is complete !
                if( debugLevel > 2 ) {
                    System.out.println("checkS88read strParts.length="+strParts.length+" strParts["+(strParts.length-1)+"]=["+strParts[strParts.length-1]+"] COMPLETE");
                }
                int readMod = Integer.parseInt(strParts[1].substring(1));
                if( readMod != this.modulNr ) {
                    System.out.println("checkS88read: wanted #"+modulNr+" read #"+readMod+" -> ignore");
                    return true;
                }
                int newValue = 0;
                for( int j = 0 ; j <= 7 ; j++ ) {
                    if( strParts[6].charAt(7-j) == '1' ) {
                        newValue |= (1 << j) ;
                    }
                    if( strParts[7].charAt(7-j) == '1' ) {
                        newValue |= (1 << (j+8)) ;
                    }
                }
                // first check for changes by user...
                int shownValue = S88mon.getShownValue();
                if( shownValue != moduleValue ) {
                    // reset to correct value
                    System.out.println("checkS88read moduleNr "+modulNr+" :"+
                            " shownValue="+shownValue+" != moduleValue="+moduleValue );
                    if( S88mon != null ) {
                        if( debugLevel > 0 ) {
                            System.out.println("checkS88read redrawValues()");
                        }
                        S88mon.redrawValues();
                    } else {
                        System.out.println("checkS88read S88mon == null, no redraw");
                    }
                }
                int oldValue = moduleValue;
                moduleValue |= newValue;
                if( moduleValue != oldValue ) {
                    if( debugLevel > 1 ) {
                        System.out.println("checkS88read moduleNr "+modulNr+" :"+
                                " len6="+ strParts[6].length()+" "+strParts[6]+
                                " len7="+ strParts[7].length()+" "+strParts[7] );
                    }
                    if( debugLevel > 0 ) {
                        System.out.printf("checkS88read value changed: old %s new %s moduleValue %s\n",
                                String.format("%16s", Integer.toBinaryString(oldValue)).replace(' ', '0'),
                                String.format("%16s", Integer.toBinaryString(newValue)).replace(' ', '0'),
                                String.format("%16s", Integer.toBinaryString(moduleValue)).replace(' ', '0'));
                    }
                    if( S88mon != null ) {
                        if( debugLevel > 0 ) {
                            System.out.println("checkS88read redrawValues()");
                        }
                        S88mon.redrawValues();
                    } else {
                        System.out.println("checkS88read S88mon == null, no redraw");
                    }
                }
                return true;
            }
        }
        return false;
    }

    public String getLocoName( int locAddr, Boolean forceM3 ) {
        return getLocoName( Integer.toString( locAddr ), forceM3 );
    }

    public String getLocoName( String locAddr, Boolean forceM3 ) {
        // get name for loco with given address
        // with forceM3 set protocol to M3 and speed steps to 126
        System.out.println( "getLocoName( "+locAddr+" , " + forceM3 + ")" );

        for( int localLocIdx = 0 ; localLocIdx < c.MAX_LOCS; localLocIdx++) {
            String sAdr = "";
            String sFormat = "";
            String sName = "";

            Object oAdr = jTableLoco.getValueAt(localLocIdx, 0);
            if( oAdr != null ) {
                sAdr += oAdr;
                if( sAdr.trim().length() > 0 ) {
                    // valid addr
                    if( sAdr.equals( locAddr )) {
                        // matching addr
                        System.out.println( "getLocoName: matching addr at localLocIdx="+localLocIdx );
                        Object oFormat = jTableLoco.getValueAt( localLocIdx, 1);
                        if( oFormat != null ) {
                            sFormat += oFormat;
                            sFormat = sFormat.trim().toUpperCase();
                            Object oName = jTableLoco.getValueAt( localLocIdx, 3);
                            if(oName != null) {
                                sName += oName;
                            }
                            System.out.println( "getLocoName: sFormat="+sFormat+" sName="+sName );
                            if( forceM3 && ! sFormat.equals("M3") ) {
                                // adjust format and speedsteps
                                jTableLoco.setValueAt("M3", localLocIdx, 1);
                                jTableLoco.setValueAt("126", localLocIdx, 2);
                                CVNavi.mbGeneric(this, "Hinweis", "Protokoll von Lok "+sAdr+" ("+sName+") wurde auf M3 mit 126 Fahrstufen angepasst", 9, true);
                            }
                            System.out.println( "getLocoName: return( "+sName.trim()+" )");
                            return sName.trim();
                        }
                    }
                }
            }
        }

        // no loco with same addr found
        System.out.println( "getLocoName: return( empty )") ;
        return "";
    }

private boolean checkTableLoco( boolean repair, boolean show ) {
        boolean retVal = true;
        String errorIdxList = "";
        bFalscheEingabe = false;

        String sAdr = "";
        String sFormat = "";
        String sFS = "";
        String sName = "";

        for( int localLocIdx = 0 ; localLocIdx < c.MAX_LOCS; localLocIdx++) {
            sAdr = "";
            sFormat = "";
            sFS = "";
            sName = "";
            if( debugLevel >= 2 ) {
                System.out.println("check: loco ("+(localLocIdx+1)+"/"+c.MAX_LOCS+")" );
            }
            // check parameters
            Object oAdr = jTableLoco.getValueAt(localLocIdx, 0);
            if( oAdr != null )
                sAdr += oAdr;
            if( sAdr.trim().length() > 0 ) {
                Object oFormat = jTableLoco.getValueAt( localLocIdx, 1);
                Object oFS = jTableLoco.getValueAt( localLocIdx, 2);
                Object oName = jTableLoco.getValueAt( localLocIdx, 3);
                if( oFormat != null )
                    sFormat += oFormat;
                if( oFS != null )
                    sFS += oFS;
                if(oName != null)
                    sName += oName;

                // 1st check protocol format
                sFormat = sFormat.trim().toUpperCase();
                jTableLoco.setValueAt( sFormat, localLocIdx, 1);
                switch( sFormat ) {
                    case "DCC":
                        if( ! CVNavi.checkNumRange( sAdr, 1, 10239 ) ) {
                            if( repair ) {
                                oAdr = 3;
                                jTableLoco.setValueAt( ""+oAdr, localLocIdx, 0);
                            }
                            retVal = false;
                            bFalscheEingabe = true;
                            FehlerArt |= 0x0001;
                            errorIdxList += " " + (localLocIdx+1);
                        }
                        break;
                    case "MM1":
                    case "MM2":
                        if( ! CVNavi.checkNumRange( sAdr, 1, 255 ) ) {
                            if( repair ) {
                                oAdr = 3;
                                jTableLoco.setValueAt( ""+oAdr, localLocIdx, 0);
                            }
                            retVal = false;
                            bFalscheEingabe = true;
                            FehlerArt |= 0x0002;
                            errorIdxList += " " + (localLocIdx+1);
                        }
                        break;
                    case "M3":
                        if( ! CVNavi.checkNumRange( sAdr, 1, c.MAX_M3_SID ) ) {
                            if( repair ) {
                                oAdr = 3;
                                jTableLoco.setValueAt( ""+oAdr, localLocIdx, 0);
                            }
                            retVal = false;
                            bFalscheEingabe = true;
                            FehlerArt |= 0x0004;
                            errorIdxList += " " + (localLocIdx+1);
                        }
                        break;
                    default: // others
                        if( repair ) {

                            String[] str = {"MM1", "MM2", "DCC", "m3"};
                            int showOptionDialog = JOptionPane.showOptionDialog(this, ""+bundle.getString("MC.zeile")+" "+(localLocIdx+1)+":\n\""+sAdr+"\" \""+sFormat+"\" \""+sFS+"\" \""+sName+ "\"\n"+bundle.getString("MC.chooseFormat"), bundle.getString("MC.chooseFormat"), JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, str, "DCC");
                            switch(showOptionDialog)
                            {
                                case 0:
                                    sFormat = "MM1";
                                    break;

                                case 1:
                                    sFormat = "MM2";
                                    break;

                                case 2:
                                    sFormat = "DCC";
                                    break;

                                default:
                                    sFormat = "m3";
                                    break;

                            }

                            jTableLoco.setValueAt(sFormat, localLocIdx, 1);
                            jTableLoco.setValueAt(sFS, localLocIdx, 2);
                        }
                        retVal = false;
                        bFalscheEingabe = true;
                        FehlerArt |= 0x0008;
                        errorIdxList += " " + (localLocIdx+1);

                        if( ! CVNavi.checkNumRange( sAdr, 1, 10239 ) ) {
                            if( repair ) {
                                oAdr = 3;
                                jTableLoco.setValueAt( ""+oAdr, localLocIdx, 0);
                            }
                            retVal = false;
                            bFalscheEingabe = true;
                            FehlerArt |= 0x0001;
                            errorIdxList += " " + (localLocIdx+1);
                        }
                        break;
                }

                // 2nd check speed steps according to format
                switch( sFormat.toUpperCase() ) {
                    case "DCC":
                        switch( sFS.toLowerCase() ) {
                            case "14":
                            case "28":
                            case "126":
                                break;
                            default:
                                if( repair ) {
                                    String[] str = {"14", "28", "126 (128)"};
                                    int showOptionDialog = JOptionPane.showOptionDialog(this, ""+bundle.getString("MC.zeile")+" "+(localLocIdx+1)+":\n\""+sAdr+"\" \""+sFormat+"\" \""+sFS+"\" \""+sName+ "\"\n"+bundle.getString("MC.chooseSpeedsteps"), bundle.getString("MC.chooseSpeedsteps"), JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, str, "DCC");
                                    switch(showOptionDialog)
                                    {
                                        case 0:
                                            sFS = "14";
                                            break;
                                        case 1:
                                            sFS = "28";
                                            break;
                                        case 2:
                                            sFS = "126";
                                            break;
                                        default:
                                            // no valid choice
                                            sFS = "14";
                                            retVal = false;
                                            bFalscheEingabe = true;
                                            FehlerArt |= 0x0010;
                                            errorIdxList += " " + (localLocIdx+1);
                                            break;
                                    }
                                    jTableLoco.setValueAt( sFS, localLocIdx, 2);
                                }
                        }
                        break;
                    case "MM1":
                    case "MM2":
                        switch( sFS.toLowerCase() ) {
                            case "14":
                            case "27a":
                            case "27b":
                                break;
                            default:
                                if( repair ) {
                                     String[] str = {"14", "27a", "27b"};
                                    int showOptionDialog = JOptionPane.showOptionDialog(this, ""+bundle.getString("MC.zeile")+" "+(localLocIdx+1)+":\n\""+sAdr+"\" \""+sFormat+"\" \""+sFS+"\" \""+sName+ "\"\n"+bundle.getString("MC.chooseSpeedsteps"), bundle.getString("MC.chooseSpeedsteps"), JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, str, "DCC");
                                    switch(showOptionDialog)
                                    {
                                        case 0:
                                            sFS = "14";
                                            break;
                                        case 1:
                                            sFS = "27a";
                                            break;
                                        case 2:
                                            sFS = "27b";
                                            break;
                                        default:
                                            // no valid choice
                                            sFS = "14";
                                            retVal = false;
                                            bFalscheEingabe = true;
                                            FehlerArt |= 0x0010;
                                            errorIdxList += " " + (localLocIdx+1);
                                            break;
                                    }
                                   jTableLoco.setValueAt( sFS, localLocIdx, 2);
                                }
                        }
                        break;
                    case "M3":
                        switch( sFS.toLowerCase() ) {
                            case "126":
                                break;
                            default:
                                if( repair ) {
                                    // no choice
                                    sFS = "126";
                                    jTableLoco.setValueAt( sFS, localLocIdx, 2);
                                }
                                retVal = false;
                                bFalscheEingabe = true;
                                FehlerArt |= 0x0010;
                                errorIdxList += " " + (localLocIdx+1);
                        }
                        break;
                }

                // 3rd check name
                if(sName.length() > CVNavi.userLocoNameMax)
                {
                    sName = sName.substring(0, CVNavi.userLocoNameMax);
                    if( repair ) {
                        jTableLoco.setValueAt(sName, localLocIdx, 3);
                    }
                    retVal = false;
                    bFalscheEingabe = true;
                    FehlerArt |= 0x0020;
                    errorIdxList += " " + (localLocIdx+1);
                }
            }
        }

        // 4th check duplicate address
        for( int localLocIdxA = 0 ; localLocIdxA < c.MAX_LOCS; localLocIdxA++) {
            for( int localLocIdxB = (localLocIdxA+1) ; localLocIdxB < c.MAX_LOCS; localLocIdxB++) {
                if( localLocIdxA != localLocIdxB ) {
                    String sAdrA = "";
                    String sAdrB = "";
                    Object oAdrA = jTableLoco.getValueAt(localLocIdxA, 0);
                    Object oAdrB = jTableLoco.getValueAt(localLocIdxB, 0);
                    if( oAdrA != null )
                      sAdrA += oAdrA;
                    if( oAdrB != null )
                      sAdrB += oAdrB;
                    if( ( sAdrA.trim().length() > 0 ) && ( sAdrB.trim().length() > 0 ) ) {
                        if( sAdrA.trim().equals(sAdrB.trim())) {
                            FehlerArt |= 0x0100;
                            String errList = " " + (localLocIdxA+1) + " " + (localLocIdxB+1);
                            System.out.println("jTableLoco loco addresses ["+oAdrA+"] equal in lines ["+localLocIdxA+"] ["+localLocIdxB+"]");
                            CVNavi.mbTableCheck( this, FehlerArt, false, errList );
                            retVal = false;
                        }
                    }
                }
            }
        }



        if( errorIdxList.trim().length() > 0 ) {
            String[] szArr = errorIdxList.trim().split(" ");
            System.out.println("jTableLoco errorIdxList=["+errorIdxList+"]");
            System.out.println("jTableLoco "+szArr.length+" fehlerhafte Zeilen: " );

            if( szArr.length > 0 ) {
                jTableLoco.clearSelection();
                for( int i = 0 ; i < szArr.length ; i++ ) {
                    System.out.println("jTableLoco szArr["+i+"]=\""+ szArr[i]+"\"" );
                    int selection = Integer.parseInt(szArr[i])-1;
                    jTableLoco.addRowSelectionInterval(selection, selection);
                }
            }
        }

        if( retVal ) {
            if( show ) {
                CVNavi.mbTableCheckOK( this, repair, 3 );
            }
        } else {
            if( ( FehlerArt & 0x0100 ) != 0x0100 ) {
                CVNavi.mbTableCheck( this, FehlerArt, repair, errorIdxList );
            }
        }
        if( debugLevel >= 2 ) {
            System.out.println("checkTableLoco returns["+(retVal?"true":"false")+"]");
        }
        return retVal;
    }

    private boolean checkTableTraction( boolean repair, boolean show ) {
        boolean retVal = true;
        String errorIdxList = "";
        bFalscheEingabe = false;

        for( int localTraIdx = 0 ; localTraIdx < c.MAX_TRACTIONS; localTraIdx++) {
            if( debugLevel >= 2 ) {
                System.out.println("check: traction ("+(localTraIdx+1)+"/"+c.MAX_TRACTIONS+")" );
            }
            // check parameters
            String sAdr1 = ""+jTableTraction.getValueAt(localTraIdx, 0);
            String sNam1 = ""+jTableTraction.getValueAt(localTraIdx, 1);
            String sAdr2 = ""+jTableTraction.getValueAt(localTraIdx, 2);
            String sNam2 = ""+jTableTraction.getValueAt(localTraIdx, 3);

            // cleanup any problem strings (from previous tests)
            if( sNam1.equals(c.ERR_PROBLEM_LEFT) ) {
                jTableTraction.setValueAt("", localTraIdx, 1);
            }
            if( sNam2.equals(c.ERR_PROBLEM_LEFT) ) {
                jTableTraction.setValueAt("", localTraIdx, 3);
            }

            if( repair ) {
                String sTmp;
                sTmp = sAdr1.replaceAll("\\s","");
                if( ! sAdr1.matches(sTmp)) { // difference in address strings
                    // write back to table
                    jTableTraction.setValueAt(sTmp, localTraIdx, 0);
                    // reread for further usage...
                    sAdr1 = ""+jTableTraction.getValueAt(localTraIdx, 0);
                }
                sTmp = sAdr2.replaceAll("\\s","");
                if( ! sAdr2.matches(sTmp)) { // difference in address strings
                    // write back to table
                    jTableTraction.setValueAt(sTmp, localTraIdx, 2);
                    // reread for further usage...
                    sAdr2 = ""+jTableTraction.getValueAt(localTraIdx, 2);
                }
            }

            if( sAdr1.length() > 0 ) {
                if( sAdr1.substring(sAdr1.length()-1, sAdr1.length()).matches("!") ) {
                    sAdr1 = sAdr1.substring(0, sAdr1.length()-1);
                }
                if( ! CVNavi.checkNumRange( sAdr1, 1, c.MAX_M3_SID ) ) {
                    jTableTraction.setValueAt(c.ERR_PROBLEM_LEFT, localTraIdx, 1);
                    retVal = false;
                    bFalscheEingabe = true;
                    FehlerArt |= 0x0040;
                    errorIdxList += " " + (localTraIdx+1);
                } else {
                    jTableTraction.setValueAt("", localTraIdx, 1);
                    for(int l = 0; l < c.MAX_LOCS; l++) {
                        if(sAdr1.matches(""+jTableLoco.getValueAt(l, 0)))
                            jTableTraction.setValueAt(jTableLoco.getValueAt(l, 3), localTraIdx, 1);
                    }
                }
            }

            if( sAdr2.length() > 0) {
                if( sAdr2.substring(sAdr2.length()-1, sAdr2.length()).matches("!") ) {
                    sAdr2 = sAdr2.substring(0, sAdr2.length()-1);
                }
                if( ! CVNavi.checkNumRange( sAdr2, 1, c.MAX_M3_SID ) ) {
                    jTableTraction.setValueAt(c.ERR_PROBLEM_LEFT, localTraIdx, 3);
                    retVal = false;
                    bFalscheEingabe = true;
                    FehlerArt |= 0x0040;
                    errorIdxList += " " + (localTraIdx+1);
                } else {
                    jTableTraction.setValueAt("", localTraIdx, 3);
                    for(int l = 0; l < c.MAX_LOCS; l++) {
                        if(sAdr2.matches(""+jTableLoco.getValueAt(l, 0)))
                            jTableTraction.setValueAt(jTableLoco.getValueAt(l, 3), localTraIdx, 3);
                    }
                }
            }
            if( sAdr1.length() == 0 && sAdr2.length() > 0 ) {
                jTableTraction.setValueAt(c.ERR_PROBLEM_LEFT, localTraIdx, 1);
                    retVal = false;
                    bFalscheEingabe = true;
                    FehlerArt |= 0x0040;
                    errorIdxList += " " + (localTraIdx+1);
            }
            if( sAdr1.length() > 0 && sAdr2.length() == 0 ) {
                jTableTraction.setValueAt(c.ERR_PROBLEM_LEFT, localTraIdx, 3);
                    retVal = false;
                    bFalscheEingabe = true;
                    FehlerArt |= 0x0040;
                    errorIdxList += " " + (localTraIdx+1);
            }
        }

        if( errorIdxList.trim().length() > 0 ) {
            String[] szArr = errorIdxList.trim().split(" ");
            System.out.println("checkTableTraction errorIdxList=["+errorIdxList+"]");
            System.out.println("checkTableTraction "+szArr.length+" fehlerhafte Zeilen: " );

            if( szArr.length > 0 ) {
                jTableTraction.clearSelection();
                for( int i = 0 ; i < szArr.length ; i++ ) {
                    System.out.println("checkTableTraction szArr["+i+"]=\""+ szArr[i]+"\"" );
                    int selection = Integer.parseInt(szArr[i])-1;
                    jTableTraction.addRowSelectionInterval(selection, selection);
                }
            }
        }

        if( bFalscheEingabe ) {
            CVNavi.mbTableCheck( this, FehlerArt, false, errorIdxList );
        } else {
            if( show ) {
                CVNavi.mbTableCheckOK( this, repair, 3 );
            }
        }
        if( debugLevel >= 2 ) {
            System.out.println("checkTableTraction returns["+retVal+"]");
        }

        return retVal;
    }

    private boolean checkTableAccessory( boolean repair, boolean show ) {
        boolean retVal = true;
        String errorIdxList = "";
        bFalscheEingabe = false;

        for( int localMagIdx = 0 ; localMagIdx < c.MAX_MM1_ACCMOD; localMagIdx++) {
            if( debugLevel >= 2 ) {
                System.out.println("check: accessory ("+(localMagIdx+1)+"/"+c.MAX_MM1_ACCMOD+")" );
            }
            // check parameters
            String sIdx = (""+jTableAccessory.getValueAt(localMagIdx, 0)).trim();
            String sFmt = (""+jTableAccessory.getValueAt(localMagIdx, 1)).trim();
            if( (sIdx.length() > 0) && (sFmt.length() > 0) ) {
                int n = -1;
                String[] sArr = sIdx.split(" ");
                if( CVNavi.checkNumRange(sArr[0], 1, c.MAX_MM1_ACCMOD)) {
                    n = CVNavi.checkAndGetStrNumRangeDef( null, sArr[0], 1, c.MAX_MM1_ACCMOD, 0, false);
                }
                else {
                    if( repair ) {
                        // jTableAccessory.setValueAt("", localMagIdx, 0);
                        jTableAccessory.setValueAt( sModAdr(0), localMagIdx, 0);
                    }
                    retVal = false;
                    bFalscheEingabe = true;
                    FehlerArt |= 0x0080;
                    errorIdxList += " " + (localMagIdx+1);
                }
                // internal module# is 0 based -> 1 less than tableIdx
                n--;

                sFmt = sFmt.toUpperCase();
                switch( sFmt ) {
                    case "DCC":
                    case "MM":
                        break;
                    default:
                        if( sFmt.startsWith("D") )
                            sFmt = "DCC";
                        else
                            sFmt = "MM";
                        if( repair ) {
                            jTableAccessory.setValueAt(sFmt, localMagIdx, 1);
                        }
                        retVal = false;
                        bFalscheEingabe = true;
                        FehlerArt |= 0x0080;
                        errorIdxList += " " + (localMagIdx+1);
                }
            }
        }

        if( errorIdxList.trim().length() > 0 ) {
            String[] szArr = errorIdxList.trim().split(" ");
            System.out.println("jTableAccessory errorIdxList=["+errorIdxList+"]");
            System.out.println("jTableAccessory "+szArr.length+" fehlerhafte Zeilen: " );

            if( szArr.length > 0 ) {
                jTableAccessory.clearSelection();
                for( int i = 0 ; i < szArr.length ; i++ ) {
                    System.out.println("jTableAccessory szArr["+i+"]=\""+ szArr[i]+"\"" );
                    int selection = Integer.parseInt(szArr[i])-1;
                    jTableAccessory.addRowSelectionInterval(selection, selection);
                }
            }
        }

        if( retVal ) {
            if( show ) {
                CVNavi.mbTableCheckOK( this, repair, 3 );
            }
        } else {
            CVNavi.mbTableCheck( this, FehlerArt, repair, errorIdxList );
        }
        if( debugLevel >= 2 ) {
            System.out.println("checkTableAccessory returns["+retVal+"]" );
        }
        return retVal;
    }

    private Boolean readM3() {
        if( debugLevel > 0 ) {
            System.out.println("Read M3 data from DIR: "+CVNavi.gsSaveOpenM3Directory+" NAME: \""+CVNavi.gsSaveOpenM3Filename+"\"" );
        }
        if( (CVNavi.gsSaveOpenM3Filename.length() <= 1) && (CVNavi.gsSaveOpenM3Filename.contentEquals(" ")) ) {
            System.out.println("Read M3 data from DIR: "+CVNavi.gsSaveOpenM3Directory+" NAME is empty :\""+CVNavi.gsSaveOpenM3Filename+"\"" );
            return false;
        }
        String filenameM3 = CVNavi.gsSaveOpenM3Directory+"/"+CVNavi.gsSaveOpenM3Filename;
        File f = new File( filenameM3 );
        long filelen = f.length();

        if( debugLevel > 0 ) {
            System.out.println("Read M3 data from file: "+filenameM3+" ("+filelen+" bytes)" );
        }
        //Datei lesen
        String s = "";
        char ac[] = new char[(int)filelen+1];
        if(f.isFile())
        {
            FileReader inputStream = null;
            try
            {
                try {
                    inputStream = new FileReader(f);
                    try {

                        int n = inputStream.read(ac, 0, (int) filelen);
                        if(n == -1)
                        {
                            CVNavi.mbFileReadError(this);
                            return false;
                        }
                        String str1 = new String(ac);
                        ReturnString = str1.substring(0, n);
                        System.out.println("INFO: Read M3 data from file succeeded: "+filenameM3+" ("+filelen+" bytes)" );
                    } catch (IOException ex) {
                        Logger.getLogger(SaveOpenDialog.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(SaveOpenDialog.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            finally
            {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException ex) {
                        Logger.getLogger(SaveOpenDialog.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        else
        {
            // CVNavi.mbFileNotFound( this, filenameM3 );
            System.out.println("INFO: Read M3 data from file failed FileNotFound: "+filenameM3 );
            return false;
        }
        M3changed = false;
        updateM3uid();
        return true;
    }

    public void updateM3uid() {
        System.out.println("updateM3uid: Gelesen "+ReturnString.length()+" Bytes");
        if( (ReturnString == null) || (ReturnString.length() == 0) ) {
            // nichts gelesen oder es wurde geschrieben !
            return;
        }

        String[] strArr = ReturnString.split("\r\n");
        int n = strArr.length;
        System.out.println("updateM3uid: Gelesen "+ReturnString.length()+" Bytes, "+n+" Zeilen");
        Boolean parseData = false;
        for( int i = 0 ; i < n ; i++ ) {
            String[] zeilenArr = strArr[i].split(",");
            if( zeilenArr.length == 0 ) {
                // empty line
                continue;
            }
            if( zeilenArr[0].equalsIgnoreCase("[M3UID]")) {
                // M3UID-Tabelle startet
                // setze altes Array auf 0
                M3used = 0;
                updateM3count();
                parseData = true;
                continue;
            }
            if( parseData == false ) {
                // no valid section header -> skip line
                continue;
            }
            if( zeilenArr[0].trim().startsWith("[")) {
                // nächste Sektion -> Parsing abbrechen
                System.out.println("updateM3uid: Ende nach "+M3used+" Datensätzen");
                if( debugLevel > 0 ) {
                    CVNavi.mbConfigReadSuccess( this, 3 );
                }
                ReturnString = "";
                jLocM3sidWriteList.setEnabled(true);

                locoTableSelRow = jTableLoco.getSelectedRow();
                if( locoTableSelRow >= 0 ) {
                    checkM3sid(locoTableSelRow);
                }

                return;
            }
            if( zeilenArr.length < 2 ) {
                // less than 2 item -> skip line
                continue;
            }
            // check for data
            String sM3UID = checkM3uidValid(zeilenArr[0]);

            if( (sM3UID != null) && CVNavi.checkNumRange(zeilenArr[1].trim(), 1, c.MAX_M3_SID) ) {

                M3liste[0][M3used] = sM3UID;
                M3liste[1][M3used] = ""+Integer.parseInt(zeilenArr[1].trim());
                if( strArr.length > 2 ) {
                    // alles nach dem 2. Komma (nur einmal trim um Alles !)
                    int jIdx = strArr[i].indexOf(",");
                    jIdx = strArr[i].indexOf(",", jIdx+1);
                    String rest = strArr[i].substring(jIdx+1);
                    M3liste[2][M3used] = rest.trim();
                } else {
                    M3liste[2][M3used] = "";
                }
                M3used++;
            }
            updateM3count();

            if( M3used == c.MAX_M3_ENTRIES) {
                CVNavi.mbM3TooMany(this);
                CVNavi.mbConfigReadAbort( this, 5 );
                ReturnString = "";
                return;
            }
        }
        System.out.println("updateM3uid: Ende nach "+M3used+" Datensätzen");
        updateM3count();
        if( debugLevel > 0 ) {
            CVNavi.mbConfigReadSuccess( this, 3 );
        }
        jLocM3cfgSave.setEnabled(true);
        ReturnString = "";
        jLocM3sidWriteList.setEnabled(true);

        locoTableSelRow = jTableLoco.getSelectedRow();
        if( locoTableSelRow >= 0 ) {
            checkM3sid(locoTableSelRow);
        }

    }

    public void updateM3count() {
        jM3count.setForeground(Color.black); ;
        if( M3changed ) {
            jM3count.setForeground(Color.red);
        }
        jM3count.setText("( "+M3used+" )");
        return;
    }

    void delMultipleLocoLines() {
        int selRow = -1;
        try {
            selRow = jTableLoco.getSelectedRow();
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("delMultipleLocoLines IndexOutOfBoundsException");
        }
        if( selRow == -1 ) {
            // no valid selection -> leave
            System.out.println("delMultipleLocoLines invalid selection (no row seletced) selRow="+selRow );
            return;
        }

        int[] selMulti = jTableLoco.getSelectedRows();
        int selMultiLength = selMulti.length;
        jTableLoco.getSelectionModel().clearSelection();
        System.out.println("Loco_Liste selMultiLength="+selMultiLength);
        for( int i = (selMultiLength-1) ; i >= 0 ; i-- ) {
            int row = selMulti[i];
            int viewRow = jTableLoco.convertRowIndexToModel(row);
            System.out.println("Loco_Liste DELETE selMulti["+i+"] row="+row+" viewRow="+viewRow);
            delOneLocoLine( row );

            // set a selected row only if we had a single selection
            if( selMultiLength == 1 ) {
                // set selection to previous selection
                System.out.println("Loco_Liste set selection to row="+row);
                jTableLoco.setRowSelectionInterval(row, row);
            }
        }
        System.out.println("delMultipleLocoLines END");
    }

    void delOneLocoLine( int selRow ) {
        if( selRow == -1 ) {
            return;
        }
        // unset current cell selection to allow moving cell contents
        jTableLoco.getSelectionModel().clearSelection();
        if( jTableLoco.isEditing() ) {
            jTableLoco.getCellEditor().stopCellEditing();
        }
        // delete selRow by moving all following rows 1 to front...
        for (int k = selRow; k < (c.MAX_LOCS - 1); k++)
        {
            // TODO Umstellung auf loop über 0 .. < getAnzahlSpalten
            Object o = jTableLoco.getValueAt(k+1, 0);
            jTableLoco.setValueAt(o, k, 0);
            o = jTableLoco.getValueAt(k+1, 2);
            jTableLoco.setValueAt(o, k, 2);
            o = jTableLoco.getValueAt(k+1, 1);
            jTableLoco.setValueAt(o, k, 1);
            o = jTableLoco.getValueAt(k+1, 3);
            jTableLoco.setValueAt(o, k, 3);
        }
        // ... and set last to defaults
        jTableLoco.setValueAt("", (c.MAX_LOCS - 1), 0);
        jTableLoco.setValueAt("", (c.MAX_LOCS - 1), 1);
        jTableLoco.setValueAt("", (c.MAX_LOCS - 1), 2);
        jTableLoco.setValueAt("", (c.MAX_LOCS - 1), 3);
    }

    void delMultipleTractionLines() {
        System.out.println("delMultipleTractionLines START");
        int selRow = -1;
        try {
            selRow = jTableTraction.getSelectedRow();
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("delMultipleTractionLines IndexOutOfBoundsException");
        }
        if( selRow == -1 ) {
            // no valid selection -> leave
            System.out.println("delMultipleTractionLines invalid selection (no row seletced) selRow="+selRow );
            return;
        }

        int[] selMulti = jTableTraction.getSelectedRows();
        int selMultiLength = selMulti.length;
        jTableTraction.getSelectionModel().clearSelection();
        System.out.println("Traction_Liste selMultiLength="+selMultiLength);
        for( int i = (selMultiLength-1) ; i >= 0 ; i-- ) {
            int row = selMulti[i];
            int viewRow = jTableTraction.convertRowIndexToModel(row);
            System.out.println("Traction_Liste DELETE selMulti["+i+"] row="+row+" viewRow="+viewRow);
            delOneTractionLine( row );

            // set a selected row only if we had a single selection
            if( selMultiLength == 1 ) {
                // set selection to previous selection
                System.out.println("Traction_Liste set selection to row="+row);
                jTableTraction.setRowSelectionInterval(row, row);
            }
        }
        System.out.println("delMultipleTractionLines END");
    }

    void delOneTractionLine( int selRow ) {
        if( selRow == -1 ) {
            return;
        }
        // unset current cell selection to allow moving cell contents
        jTableTraction.getSelectionModel().clearSelection();
        if( jTableTraction.isEditing() ) {
            jTableTraction.getCellEditor().stopCellEditing();
        }
        // delete selRow by moving all following rows 1 to front...
        for (int k = selRow; k < (c.MAX_TRACTIONS - 1); k++)
        {
            // TODO Umstellung auf loop über 0 .. < getAnzahlSpalten
            Object o = jTableTraction.getValueAt(k+1, 0);
            jTableTraction.setValueAt(o, k, 0);
            o = jTableTraction.getValueAt(k+1, 1);
            jTableTraction.setValueAt(o, k, 1);
            o = jTableTraction.getValueAt(k+1, 2);
            jTableTraction.setValueAt(o, k, 2);
            o = jTableTraction.getValueAt(k+1, 3);
            jTableTraction.setValueAt(o, k, 3);
        }
        // ... and set last to defaults
        jTableTraction.setValueAt("", (c.MAX_TRACTIONS - 1), 0);
        jTableTraction.setValueAt("", (c.MAX_TRACTIONS - 1), 1);
        jTableTraction.setValueAt("", (c.MAX_TRACTIONS - 1), 2);
        jTableTraction.setValueAt("", (c.MAX_TRACTIONS - 1), 3);
    }

    void setFocusUpdStart() {
        if( CVNavi.bSpracheDE ) {
            jMcUpdInfo.setText("Aktualisierung kann gestartet werden");
        } else {
            jMcUpdInfo.setText("you may start the update");
        }
        jUpdStartUpdate.grabFocus();
    }

    void setFocusDateiAuswahl() {
        if( CVNavi.bSpracheDE ) {
            jMcUpdInfo.setText("Bitte Datei auswählen");
        } else {
            jMcUpdInfo.setText("please select file");
        }
        jUpdDateiAuswahl.grabFocus();
    }

    void reCheckVersionInfo() {
        String jVer = jVersion.getText();
        byte[] bVersion = jVer.replace(".", "").getBytes();
        if( debugLevel > 0 ) {
            System.out.println("jVer="+jVer+" bVersion.length="+bVersion.length );
        }
        if( bVersion.length < 3 ) {
            System.out.println("Version too short! jVer="+jVer );
            return;
        }

        long lSwVersion = 0;
        lSwVersion += (long)(((bVersion[0] & 0xFF)-'0') << 24 );
        lSwVersion += (long)(((bVersion[1] & 0xFF)-'0') << 16 );
        lSwVersion += (long)(((bVersion[2] & 0xFF)-'0') <<  8 );
        if( bVersion.length > 3 ) {
            lSwVersion += (long) (bVersion[3] & 0xFF);
        }

        CVNavi.bUseXfuncs = ( lSwVersion >= c.MIN_MC_XFUNCS_VERSION );
        CVNavi.bUseXm3sid = ( lSwVersion >= c.MIN_MC_XM3SID_VERSION );
        CVNavi.bUseSo999  = ( lSwVersion >= c.MIN_MC_SO999_VERSION  );
        jBoostOptNoAccDrive.setEnabled(CVNavi.bUseSo999);
        jBoostOptNoAccBreak.setEnabled(CVNavi.bUseSo999);

        CVNavi.fwVersion = jVer;
        String sSwVersion = "MasterControl Version "+CVNavi.fwVersion;
        System.out.println("--- "+sSwVersion+" --- bUseXfuncs="+CVNavi.bUseXfuncs+" bUseXm3sid="+CVNavi.bUseXm3sid);
        if( lSwVersion > 0 ) {
            System.out.println("lSwVersion="+lSwVersion+" in HEX="+String.format("0x%16s", Long.toHexString(lSwVersion)).replace(' ', '0') );
            System.out.println("bUseXfuncs="+CVNavi.bUseXfuncs+" bUseXm3sid="+CVNavi.bUseXm3sid+" bUseSo999="+CVNavi.bUseSo999);
        }
        CVNavi.fillMenuSelection();
    }

    void updateTabs() {
        int idx = jTabbedPane1.getSelectedIndex();

        for( int i = 0 ; i < jTabbedPane1.getComponentCount() ; i ++ ) {
            jTabbedPane1.setSelectedIndex(i);
        }
        setDeviceSelection();

        jTabbedPane1.setSelectedIndex(idx);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Firmware;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.JButton helpHC;
    private javax.swing.JButton helpLC;
    private javax.swing.JButton helpMC;
    private javax.swing.JButton helpMCasLC;
    private javax.swing.JButton helpPC;
    private javax.swing.JButton helpSNC;
    private javax.swing.JButton helpWasIstWas;
    private javax.swing.JButton helpXNC;
    private javax.swing.JButton j0;
    private javax.swing.JButton j1;
    private javax.swing.JButton j2;
    private javax.swing.JButton j3;
    private javax.swing.JButton j4;
    private javax.swing.JButton j5;
    private javax.swing.JButton j6;
    private javax.swing.JButton j7;
    private javax.swing.JButton j8;
    private javax.swing.JButton j9;
    private javax.swing.JComboBox<String> jBaud;
    private javax.swing.JLabel jBild;
    private javax.swing.JLabel jBild2;
    private javax.swing.JCheckBox jBoostOptNoAccBreak;
    private javax.swing.JCheckBox jBoostOptNoAccDrive;
    private javax.swing.JLabel jBoosterOpts;
    private javax.swing.JComboBox<String> jCBformat;
    private javax.swing.JComboBox<String> jCBspeed;
    private javax.swing.JList jCVListe;
    private javax.swing.JTextField jCV_Direkt;
    private javax.swing.JButton jCancel;
    private javax.swing.JButton jClose;
    private javax.swing.JCheckBox jDCC_Booster;
    private javax.swing.JCheckBox jDCC_Loks;
    private javax.swing.JTextField jDatenQuelle;
    private javax.swing.JTextField jDecAdr;
    private javax.swing.JButton jDirektLesen;
    private javax.swing.JButton jDirektSchreiben;
    private javax.swing.JTextArea jDisplay;
    private javax.swing.JButton jEasyNetUpdate;
    private javax.swing.JButton jFuncIcon;
    private javax.swing.JButton jGO;
    private javax.swing.JSlider jGeschwindigkeit;
    private javax.swing.JTextField jHardWare;
    private javax.swing.JRadioButton jHauptGleis;
    private javax.swing.JTextField jHerstellerInfo;
    private javax.swing.JButton jKonfLaden;
    private javax.swing.JButton jKonfLesen;
    private javax.swing.JButton jKonfSchreiben;
    private javax.swing.JButton jKonfSichern;
    private javax.swing.JTextField jKurzEmpf;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelBaudrate;
    private javax.swing.JLabel jLabelM3SID;
    private javax.swing.JLabel jLabelM3UID;
    private javax.swing.JCheckBox jLangePause;
    private javax.swing.JButton jListeBearbeiten;
    private javax.swing.JButton jListeLesen;
    private javax.swing.JButton jListeSchreiben;
    private javax.swing.JButton jLoc2System;
    private javax.swing.JButton jLocCheck;
    private javax.swing.JButton jLocDel;
    private javax.swing.JButton jLocDelAll;
    private javax.swing.JButton jLocM3cfgEdit;
    private javax.swing.JButton jLocM3cfgLoad;
    private javax.swing.JButton jLocM3cfgSave;
    private javax.swing.JButton jLocM3sidWrite;
    private javax.swing.JButton jLocM3sidWriteList;
    private javax.swing.JButton jLocRepair;
    private javax.swing.JPanel jLoks;
    private javax.swing.JLabel jM3count;
    private javax.swing.JTextField jMCU;
    private javax.swing.JLabel jMC_ESC;
    private javax.swing.JLabel jMC_MENU;
    private javax.swing.JLabel jMC_OK;
    private javax.swing.JButton jMDCC;
    private javax.swing.JButton jMMM;
    private javax.swing.JTextField jMMRegister;
    private javax.swing.JTextField jMMWert;
    private javax.swing.JButton jMRST;
    private javax.swing.JButton jMag2System;
    private javax.swing.JButton jMagCheck;
    private javax.swing.JButton jMagRepair;
    private javax.swing.JPanel jMagnetartikel;
    private javax.swing.JTextField jMaxMag;
    private javax.swing.JTextField jMcM3Info;
    private javax.swing.JProgressBar jMcM3Progress;
    private javax.swing.JLabel jMcRwInfo;
    private javax.swing.JProgressBar jMcRwProgress;
    private javax.swing.JLabel jMcUpdInfo;
    private javax.swing.JProgressBar jMcUpdProgress;
    private javax.swing.JTextField jMinMag;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jProgGleis;
    private javax.swing.JCheckBox jRailcomAccessory;
    private javax.swing.JCheckBox jRailcomIdNotify;
    private javax.swing.JLabel jRailcomSupport;
    private javax.swing.JCheckBox jRailcomTailbits;
    private javax.swing.JButton jRaute;
    private javax.swing.JButton jRueck;
    private javax.swing.JButton jSTOP;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTextField jSerNr;
    private javax.swing.JButton jStartMMProg;
    private javax.swing.JButton jStern;
    private javax.swing.JButton jSysS88monitor;
    private javax.swing.JPanel jSystem;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableAccessory;
    private javax.swing.JTable jTableLoco;
    private javax.swing.JTable jTableTraction;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextM3SID;
    private javax.swing.JTextField jTextM3UID;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JButton jTra2System;
    private javax.swing.JButton jTraCheck;
    private javax.swing.JButton jTraDel;
    private javax.swing.JButton jTraDelAll;
    private javax.swing.JButton jTraRepair;
    private javax.swing.JPanel jTraktionen;
    private javax.swing.JRadioButton jUSB1;
    private javax.swing.JRadioButton jUSB2;
    private javax.swing.JButton jUpd2System;
    private javax.swing.JButton jUpdCancel;
    private javax.swing.JButton jUpdClose;
    private javax.swing.JTextField jUpdDatei;
    private javax.swing.JButton jUpdDateiAuswahl;
    private javax.swing.JLabel jUpdLastError;
    private javax.swing.JLabel jUpdLastErrorLabel;
    private javax.swing.JButton jUpdStartUpdate;
    private javax.swing.JPanel jUpdate;
    private javax.swing.JTextField jVersion;
    private javax.swing.JButton jVor;
    private javax.swing.JCheckBox jWRloc;
    private javax.swing.JCheckBox jWRmag;
    private javax.swing.JCheckBox jWRsys;
    private javax.swing.JCheckBox jWRtra;
    private javax.swing.JTextField jWertDirekt;
    private javax.swing.JButton jconDevs;
    private javax.swing.JButton jf0;
    private javax.swing.JButton jf1;
    private javax.swing.JButton jf10;
    private javax.swing.JButton jf11;
    private javax.swing.JButton jf12;
    private javax.swing.JButton jf13;
    private javax.swing.JButton jf14;
    private javax.swing.JButton jf15;
    private javax.swing.JButton jf16;
    private javax.swing.JButton jf17;
    private javax.swing.JButton jf18;
    private javax.swing.JButton jf19;
    private javax.swing.JButton jf2;
    private javax.swing.JButton jf20;
    private javax.swing.JButton jf21;
    private javax.swing.JButton jf22;
    private javax.swing.JButton jf23;
    private javax.swing.JButton jf24;
    private javax.swing.JButton jf25;
    private javax.swing.JButton jf26;
    private javax.swing.JButton jf27;
    private javax.swing.JButton jf28;
    private javax.swing.JButton jf3;
    private javax.swing.JButton jf4;
    private javax.swing.JButton jf5;
    private javax.swing.JButton jf6;
    private javax.swing.JButton jf7;
    private javax.swing.JButton jf8;
    private javax.swing.JButton jf9;
    private javax.swing.JTextField jm3Addr;
    private javax.swing.JButton jm3GotoList;
    private javax.swing.JButton jm3Schreiben;
    private javax.swing.JTextField jmfxUID;
    private javax.swing.JTextField js88;
    // End of variables declaration//GEN-END:variables

}
