/*
 * CVNavi.java (KlarText.java)
 *
 * Created on 20.01.2009, 17:34:06
 *
 * @author Kersten Tams Copyright 2009-2018
 * @author Lothar Roth  Copyright 2012-2018
 *
 */

package my.CVNavi; 

import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author ktams
 */
// Konstanten definieren, die auch in switch-Anweisungen funktionieren !
// Etwas umständlich, aber sonst müssen alle switch-Anweisungen,
// die KTUI.Decoder verwenden auf "if-then-else-if..." umgeschrieben werden :(
//
// Zuerst die Definition der Konstanten (nur hier stehen Zahlen für Menu und switch-Anweisungen !!!)
// Erster Block ist für das Auswahlmenü: 
//   Nur fortlaufende Nummern, beginnend mit 0 erlaubt
//   Die Nummer ist der Index in der Auswahlliste!
//   Das wird beim Programmstart als Erstes auf Plausibilität überprüft !
interface c {
    public static final int tLOK   =  0;
    public static final int LD_G30 =  1;
    public static final int LD_G31 =  2;
    public static final int LD_W32 =  3;
    public static final int LD_W32_2 =  4;
    public static final int LD_G32 =  5;
    public static final int LD_G32_2 =  6;
    public static final int LD_W33 =  7;
    public static final int LD_G33 =  8;
    public static final int LD_G34 =  9;
    public static final int LD_G31Plus =  10;
    public static final int LD_G33Plus =  11;
    public static final int LD_G34Plus =  12;
    public static final int LD_G36Plus =  13;
    public static final int LINE_1 =  14;
    public static final int tFD    =  15;
    public static final int FD_R   = 16;
    public static final int FD_R2  = 17;
    public static final int FD_R_ex = 18;
    public static final int FD_M   = 19;
    public static final int FD_XL  = 20;
    public static final int FD_LED = 21;
    public static final int LINE_2 = 22;
    public static final int tZUB   = 23;
    public static final int WD_34  = 24;
    public static final int WD_34_2  = 25;
    public static final int SD_34  = 26;
    public static final int SD_34_2  = 27;
    public static final int MultiDecoder = 28;
    public static final int LINE_3 = 29;
    public static final int tOTHER = 30;
    public static final int MC     = 31; // MasterControl/RedBox
    public static final int B_4    = 32;
    public static final int BiDi_B = 33;
    public static final int WIB_30 = 34;

    // special numbers (used in SaveOpenDialog)
    public static final int MC_WR  = 196; // special handling of MC config writes
    public static final int M3     = 197; // M3 uid/sid Liste
    public static final int KENN   = 198; // (Motor-)Kennlinie
    public static final int HEX    = 199; // Firmware
    public static final int TXT    = 200; // Firmware

    // MC-Konstanten:
    public static final int MAX_SYSWRITES = 11; // #zu schreibende Variablen für ein Restore der Basiseinstellungen (ohne Listen)
    public static final int MAX_LOCS = 250; // Lokliste [0-249]
    public static final int MAX_TRACTIONS = 150; // Tracktionsliste [0..149]
    public static final int MAX_M3_ENTRIES = 512; // M3UID-SID-Pairs [0..511]
    public static final int MAX_LOC_NAME_LENGTH = 11; // maximum length of loco name
    // Zubehoerdecoder
    public static final int MAX_MM1_ACCMOD = 255; // 255 Module[0..254] =^= addr[1-1020]
    public static final int MAX_DCC_ACCMOD = 510; // 510 Module[0..509] "^° addr[1-2040]
    // Zentralen
    public static final int cuOpenDCC = 0;
    public static final int cuIntellibox1 = 1;
    public static final int cuMasterControl = 2;
    // Status
    public static final int cuPowerUnknown = -1;
    public static final int cuPowerOff = 0;
    public static final int cuPowerOn = 1;
    // S88
    public static final int maxS88 = 52;
    // MessageBoxErrorCodes
    public static final int mbRDcancel = 1;
    public static final int mbWRcancel = 2;
    public static final int mbRDtimeout = 3;
    public static final int mbWRtimeout = 4;
    public static final int mbRDtooShort = 5;
    public static final int mbMCRDtimeout = 6;
    public static final int mbMCRDcommitErr = 7;
    public static final int mbMCWRtimeout = 8;
    public static final int mbRDverify = 9;
    //
    public static final long MIN_MC_XFUNCS_VERSION = 0x01040666; // 1.4.6f
    public static final long MIN_MC_XM3SID_VERSION = 0x01040762; // 1.4.7b
    public static final long MIN_MC_SO999_VERSION  = 0x01040863; // 1.4.8c
    // other
    public static final int MAX_CV = 1024;
    public static final int MAX_M3_SID = 16383;
    public static final String ERR_PROBLEM_LEFT = "<- Problem !";
    public static final String TamsFirmwareURL = "https://tams-online.de/download/firmware/";
}

// Jetzt die Records mit und zu den Konstanten
enum decoderList {
    // const menuIdx, selectable, german text, english (if different)
    tLOK    (c.tLOK,   false, "Lok-Decoder", "Loco-Decoder"),
    LD_G30  (c.LD_G30, true,  "   LD-G-30",""),
    LD_G31  (c.LD_G31, true,  "   LD-G-31",""),
    LD_W32  (c.LD_W32, true,  "   LD-W-32",""),
    LD_W32_2  (c.LD_W32_2, true,  "   LD-W-32.2",""),
    LD_G32  (c.LD_G32, true,  "   LD-G-32",""),
    LD_G32_2  (c.LD_G32_2, true,  "   LD-G-32.2",""),
    LD_W33  (c.LD_W33, true,  "   LD-W-33",""),
    LD_G33  (c.LD_G33, true,  "   LD-G-33",""),
    LD_G34  (c.LD_G34, true,  "   LD-G-34",""),
    LD_G31Plus  (c.LD_G31Plus, true,  "   LD-G-31Plus",""),
    LD_G33Plus  (c.LD_G33Plus, true,  "   LD-G-33Plus",""),
    LD_G34Plus  (c.LD_G34Plus, true,  "   LD-G-34Plus",""),
    LD_G36Plus  (c.LD_G36Plus, true,  "   LD-G-36Plus",""),
    line_1  (c.LINE_1, false, "____________",""),
    tFD     (c.tFD,    false, "Funktions-Dekoder","Function-Decoder"),
    FD_R    (c.FD_R,   true,  "   FD-R basic",""),
    FD_R2   (c.FD_R2,  true,  "   FD-R basic 2",""),
    FD_R_ex (c.FD_R_ex,true,  "   FD-R extended",""),
    FD_M    (c.FD_M,   true,  "   FD-M",""),
    FD_XL   (c.FD_XL,  true,  "   FD-XL",""),
    FD_LED  (c.FD_LED, true,  "   FD-LED",""),
    line_2  (c.LINE_2, false, "____________",""),
    tZUB    (c.tZUB,   false, "Zubehör-Decoder","Accessory-Decoder"),
    WD_34   (c.WD_34,  true,  "   WD-34",""),
    WD_34_2   (c.WD_34_2,  true,  "   WD-34.2",""),
    SD_34   (c.SD_34,  true,  "   SD-34",""),
    SD_34_2   (c.SD_34_2,  true,  "   SD-34.2",""),
    MultiDecoder   (c.MultiDecoder,  true,  "   MultiDecoder",""),
    line_3  (c.LINE_3, false, "____________",""),
    tOTHER  (c.tOTHER, false, "Sonstiges","Other"),
    MC      (c.MC,     true,  "   MasterControl/RedBox",""),
    B_4     (c.B_4,    true,  "   B-4",""),
    BiDi_B  (c.BiDi_B, true,  "   BiDi-Booster",""),
    WIB_30  (c.WIB_30, true,  "   WIB-30er","");

    private final int     idx;
    private final Boolean selectable;
    private final String  menuTextGer;
    private final String  menuTextEng;
    decoderList(int idx, Boolean sel, String textGer, String textEng) {
        this.idx = idx;
        this.selectable  = sel;
        this.menuTextGer = textGer;
        this.menuTextEng = textEng;
    }
    public int getIdx() {return idx;}
    public Boolean isSelectable() {return selectable;}
    public String getMenutextGer() {
        return menuTextGer;
    }
    public String getMenutextEng() {
        return menuTextEng.isEmpty() ? menuTextGer : menuTextEng;
    }
}

public class CVNavi extends javax.swing.JFrame {
    /** Creates new form CVNavi */
    public int     gsVersionMayor = 3;  // Version 3.0
    public int     gsVersionMinor = 0;  // Version 3.0
    public JFrame  frameInstanceDEVICE = null;
    public JFrame  frameInstanceOPTIONS = null;
    public JFrame  frameInstanceINFO = null;
    public int     Decoder = 0; // better name would be Device, because MC and B4 are also handled
    public int     currSelection = 0;
    public boolean bSpracheDE = true;         //true -> deutsch, false -> englisch
    public static int debugLevel = 0;
    public static boolean debugOffline = false;
    public static boolean forceTrackPowerOn = false;
    public static int debugDummyData = 0;
    private static int userTimer1 = -1;
    private static int userTimer2 = -1;
    private static int userTimer3 = -1;
    private static int userTimerRetries = -1;
    public static int userTimerFwUp = 150;
    public static int MCtimer1 = -1;
    public static int MCtimer2 = -1;
    public static int timer1 = -1;
    public static int timer2 = -1;
    public static int timer3 = -1;
    public static int timerRetries = -1;
    public static boolean updateAlwaysVisible = false;
    public static boolean skipCV17 = false;
    public static boolean skipCV18 = false;
    private static int trackStatus = c.cuPowerUnknown;
    private boolean bReadStatusBin = false;
    public static boolean rs232_or_rb_usb_2 = false;
    public static boolean rs232_mode_was_forced = false;

    // Globale Variablen, die nur einmal eingelesen werden sollten
    // gs = GlobalSetting
    // Die ersten 3 Variablen sind static, da sie  auch in main (static context) verwendet werden
    static String  gsConfigDirectory = System.getProperty("user.home") + "/.CV-Navi/";
    static String  gsConfigFilename  = System.getProperty("user.home") + "/.CV-Navi/" + "CV-Navi.xml";
    static String  gsConfigComment = "Tams CV Navi";

    public String  gsZentrale = "MasterControl";
    private int    lcZentrale = 2;
    private TwoWaySerialComm KTUICom = null;
    private TwoWaySerialComm ExternalCom = null;
    private Boolean bVerifyZentraleInProgress = false;
    private Timer  timer = null;
    public static Boolean bZentraleVerified = false;
    public String fwVersion = null;
    public static Boolean bUseXfuncs = false; // MC >= 1.4.6f ; OpenDCC >= 23.08 ; IB >= 2.000
    public static Boolean bUseXm3sid = false; // MC >= 1.4.7b
    public static Boolean bUseSo999  = false; // MC >= 1.4.8c
    public String  gsSchnittstelle = "noCom";
    public int     gsBaudRate = -1 ;
    public boolean gsRtsCts = true;
    public String  gsLastMcConfLoad = "NoData" ;
    public String  gsLastMcConfSave = "NoData" ;
    public int     gsDecoderIndex = -1 ;
    public String  gsLookAndFeel = "Nothing" ;
    public String  gsSaveOpenDirectory ;
    public String  gsSaveOpenFilename ;
    public String  gsSaveOpenKennDirectory ;
    public String  gsSaveOpenKennFilename ;
    public String  gsSaveOpenMcDirectory ;
    public String  gsSaveOpenMcFilename ;
    public String  gsSaveOpenM3Directory ;
    public String  gsSaveOpenM3Filename ;
    public String  gsOpenFirmwareDirectory ;
    public String  gsOpenFirmwareFilename ;
    // public String  gsReadBuffer;
    public Boolean lastSaveOpenDialogWasCancel;
    public Dimension SODlocalSize ;
    public Point   mainWindowLocation;
    public boolean bGotoUpdate;
    public enum ModeZentrale { Halted, PwrOn, PwrOff, DCCprogram, Reset, UNKNOWN };
    public enum MZ { UNKNOWN, STOP, GO };
    public ModeZentrale gModeZentrale = ModeZentrale.UNKNOWN ;
    public MZ gMZvorAktion = MZ.UNKNOWN ;
    private ResourceBundle bundle;
    private int numS88 = 0;

    public CVNavi() {
        this.lastSaveOpenDialogWasCancel = false;
        mainWindowLocation = new Point();
        ImageIcon i = new ImageIcon(getClass().getResource("/main.gif"));
        this.setIconImage(i.getImage());
        initComponents();
        jDecoderChooser.setSelectedIndex(c.MC);
        jLabelProgVersion.setText("Version " + gsVersionMayor + "." + gsVersionMinor);
        setLocationRelativeTo(null);

        fillMenuSelection();
        bundle = java.util.ResourceBundle.getBundle("my.CVNavi/Bundle");
        if( debugLevel >= 3 ) {
            for(decoderList co : decoderList.values()) {
                System.out.printf("decoderList[%2d] [%2d] (%s) %10s Text %-35s %s\n", co.ordinal(), co.getIdx(), co.isSelectable()?"X":" ", co, co.getMenutextGer(), co.getMenutextEng());
            }
        }
    }

    public void setZentrale( int newZentrale ) {
        switch (newZentrale) {
            case c.cuOpenDCC:
                lcZentrale = newZentrale;
                gsZentrale = "OpenDCC";
                break;
            case c.cuIntellibox1:
                lcZentrale = newZentrale;
                gsZentrale = "Intellibox";
                break;
            default:
                lcZentrale = c.cuMasterControl ;
                gsZentrale = "MasterControl";
        }
    }

    public void setZentrale( String newZentrale ) {
        switch (newZentrale) {
            case "OpenDCC":
                lcZentrale = c.cuOpenDCC;
                gsZentrale = newZentrale;
                break;
            case "Intellibox":
                lcZentrale = c.cuIntellibox1;
                gsZentrale = newZentrale;
                break;
            default:
                // all other cases
                lcZentrale = c.cuMasterControl ;
                gsZentrale = "MasterControl";
                break;
        }
    }

    public int getZentrale() {
        return lcZentrale;
    }

    public int getDecoderChooser() {
        return(jDecoderChooser.getSelectedIndex());
    }
    public void setDecoderChooser( int n ) {
        if( n >= 0 )
            jDecoderChooser.setSelectedIndex(n);
    }

    public boolean setModeZentrale( String mode ) {
        switch(mode) {
            // "Pwr off", "Halted!", "Pwr on", "DCC program" oder "RESET"
            case "Pwr on":
                gModeZentrale = ModeZentrale.PwrOn ;
                break;
            case "Halted!":
                gModeZentrale = ModeZentrale.Halted ;
                break;
            case "Pwr off":
                gModeZentrale = ModeZentrale.PwrOff ;
                break;
            case "DCC program":
                gModeZentrale = ModeZentrale.DCCprogram ;
                break;
            case "RESET":
                gModeZentrale = ModeZentrale.Reset ;
                break;
            default:
                System.out.println("setModeZentrale UNEXPECTED mode="+mode);
                return false;
        }
        return true;
    }

    public ModeZentrale getModeZentrale() {
        return gModeZentrale;
    }

    public MZ getMZ() {
        switch( gModeZentrale ) {
            case Halted:
            case PwrOn:
                return MZ.GO;
            case PwrOff:
            case DCCprogram:
            case Reset :
                return MZ.STOP;
            case UNKNOWN:
            default:
                return MZ.UNKNOWN;
        }
    }

    public String getModeZentraleAsStringLong() {
        switch( gModeZentrale ) {
            case Halted:
                return"Halted";
            case PwrOn:
                return"PwrOn";
            case PwrOff:
                return"PwrOff";
            case DCCprogram:
                return"DCCprogram";
            case Reset :
                return"Reset";
            case UNKNOWN:
            default:
                return "???";
        }
    }

    public String getModeZentraleAsStringShort() {
        switch( gModeZentrale ) {
            case Halted:
            case PwrOn:
                return"GO";
            case PwrOff:
            case DCCprogram:
            case Reset :
                return"STOP";
            case UNKNOWN:
            default:
                return "???";
        }
    }

    public String getMenutext( decoderList dl ) {
        if( dl == null ) {
            return "CV-Navi";
        } else {
            if( bSpracheDE ) {
                return dl.getMenutextGer();
            } else {
                return dl.getMenutextEng();
            }
        }
    }

    public int getNumS88() {
        return numS88;
    }

    public void setNumS88( int num ) {
        if( checkNumRange( num, 0, c.maxS88 ) ) {
            numS88 = num;
        }
    }

    public void mbTest( Container cont, Boolean modal ) {
        System.out.println("mbTest" );
        MsgBox messageBox = new MsgBox( (Frame) this.getParent(), modal, cont);
        messageBox.jLabel1.setText("Test!");
        messageBox.jLabel2.setText("Decoder: "+Decoder+" SS: " + gsSchnittstelle);
        messageBox.jLabel3.setText("Test.");
        messageBox.setVisible(true);
    }

    public void mbGeneric( Container cont, String s1 ) {
        mbGeneric( cont, s1, null, null, 0, true );
    }
    public void mbGeneric( Container cont, String s1, Boolean modal ) {
        mbGeneric( cont, s1, null, null, 0, modal );
    }
    public void mbGeneric( Container cont, String s1, int seconds ) {
        mbGeneric( cont, s1, null, null, seconds, true );
    }
    public void mbGeneric( Container cont, String s1, int seconds, Boolean modal ) {
        mbGeneric( cont, s1, null, null, seconds, modal );
    }
    public void mbGeneric( Container cont, String s1, String s2 ) {
        mbGeneric( cont, s1, s2, null, true );
    }
    public void mbGeneric( Container cont, String s1, String s2, Boolean modal ) {
        mbGeneric( cont, s1, s2, null, modal );
    }
    public void mbGeneric( Container cont, String s1, String s2, int seconds ) {
        mbGeneric( cont, s1, s2, null, seconds, true );
    }
    public void mbGeneric( Container cont, String s1, String s2, int seconds, Boolean modal ) {
        mbGeneric( cont, s1, s2, null, seconds, modal );
    }
    public void mbGeneric( Container cont, String s1, String s2, String s3 ) {
        mbGeneric( cont, s1, s2, s3, 0, true );
    }
    public void mbGeneric( Container cont, String s1, String s2, String s3, Boolean modal ) {
        mbGeneric( cont, s1, s2, s3, 0, modal );
    }
    public void mbGeneric( Container cont, String s1, String s2, String s3, int seconds ) {
        mbGeneric( cont, s1, s2, s3, seconds, true );
    }
    public void mbGeneric( Container cont, String s1, String s2, String s3, int seconds, Boolean modal ) {
        // System.out.println("mbGeneric seconds="+seconds+" modal="+modal+" Container name="+c.getName()+" string=" +c.toString());
        MsgBox messageBox = new MsgBox( (Frame) this.getParent(), modal, cont);
        messageBox.jLabel1.setText(s1==null?"":s1);
        messageBox.jLabel2.setText(s2==null?"":s2);
        messageBox.jLabel3.setText(s3==null?"":s3);
        messageBox.setCountdown(seconds);
        messageBox.pack();
        messageBox.setLocationRelativeTo(cont);
        messageBox.setVisible(true);
    }

    public static String byteArrayToHex(byte[] a) {
       StringBuilder sb = new StringBuilder(a.length * 2);
       for(byte b: a)
          sb.append(String.format("%02x", b));
       return sb.toString();
    }

    public void mbVerifyXVer(Container cont, String verInfo, Boolean mismatch) {
        String dbgOptions ="";
        if( debugLevel > 0 ) {
            dbgOptions = "(Xfuncs="+bUseXfuncs+" Xm3sid="+bUseXm3sid+")";
        }
        if( mismatch ) {
            mbGeneric(cont, bundle.getString("Configrationmismatch"), bundle.getString("Configured")+gsZentrale, bundle.getString("Detected")+verInfo+" "+dbgOptions, 0, true);
        } else {
            if( debugLevel > 0 ) {
                mbGeneric(cont, "Information", verInfo, "OK "+dbgOptions, 5, false);
            }
        }
    }

    public void mbNotAvailable( Container cont, String s3) {
        MsgBox messageBox = new MsgBox( (Frame) this.getParent(), true, cont);
        messageBox.jLabel1.setText("Information");
        messageBox.jLabel2.setText(bundle.getString("notavailable"));
        messageBox.jLabel3.setText(s3==null?"":s3);
        messageBox.setVisible(true);
    }

    public void mbDeviceConnectProblem( Container cont ) {
        MsgBox messageBox = new MsgBox( (Frame) this.getParent(), true, cont);
        messageBox.jLabel1.setText(bundle.getString("Fehler"));
        messageBox.jLabel2.setText(bundle.getString("Problemswithport") + gsSchnittstelle);
        messageBox.jLabel3.setText(bundle.getString("Openfailed"));
        messageBox.setVisible(true);
    }
    public void mbDeviceConnectProblemOffline( Container cont ) {
        MsgBox messageBox = new MsgBox( (Frame) this.getParent(), true, cont);
        messageBox.jLabel1.setText(bundle.getString("Fehler"));
        messageBox.jLabel2.setText(bundle.getString("Problemswithport") + gsSchnittstelle);
        messageBox.jLabel3.setText(bundle.getString("Openfailed") + "-> Offline Modus");
        messageBox.setCountdown(5);
        messageBox.setVisible(true);
    }
    public void mbDeviceDisconnectProblem( Container cont ) {
        MsgBox messageBox = new MsgBox( (Frame) this.getParent(), true, cont);
        messageBox.jLabel1.setText(bundle.getString("Fehler"));
        messageBox.jLabel2.setText(bundle.getString("Problemswithport") + gsSchnittstelle);
        messageBox.jLabel3.setText(bundle.getString("Closefailed"));
        messageBox.setVisible(true);
    }
    public void mbDeviceReadProblem( Container cont ) {
        MsgBox messageBox = new MsgBox( (Frame) this.getParent(), true, cont);
        messageBox.jLabel1.setText(bundle.getString("Fehler"));
        messageBox.jLabel2.setText(bundle.getString("Problemswithport") + gsSchnittstelle);
        messageBox.jLabel3.setText(bundle.getString("Readfailed"));
        messageBox.setVisible(true);
    }
    public void mbDeviceWriteProblem( Container cont ) {
        MsgBox messageBox = new MsgBox( (Frame) this.getParent(), true, cont);
        messageBox.jLabel1.setText(bundle.getString("Fehler"));
        messageBox.jLabel2.setText(bundle.getString("Problemswithport") + gsSchnittstelle);
        messageBox.jLabel3.setText(bundle.getString("Writefailed"));
        messageBox.setVisible(true);
    }
    public void mbDeviceOwned( Container cont ) {
        MsgBox messageBox = new MsgBox( (Frame) this.getParent(), true, cont);
        messageBox.jLabel1.setText(bundle.getString("Fehler"));
        messageBox.jLabel2.setText("");
        messageBox.jLabel3.setText("Interface " + gsSchnittstelle + bundle.getString("busy"));
        messageBox.setVisible(true);
    }
    public void mbDeviceNotSupported( Container cont, int seconds  ) {
        MsgBox messageBox = new MsgBox( (Frame) this.getParent(), true, cont);
        messageBox.jLabel1.setText(bundle.getString("Hinweis"));
        messageBox.jLabel2.setText(bundle.getString("notsupported"));
        messageBox.jLabel3.setText("");
        messageBox.setCountdown(seconds);
        messageBox.setVisible(true);
    }

    public void mbUpdateWriteSuccess( Container cont, int block ) {
        MsgBox messageBox = new MsgBox( (Frame) this.getParent(), true, cont);
        messageBox.jLabel1.setText(bundle.getString("Hinweis"));
        messageBox.jLabel2.setText(bundle.getString("successful"));
        messageBox.jLabel3.setText("" + block + " " +bundle.getString("transferred"));
        messageBox.setCountdown(5);
        messageBox.setVisible(true);
    }
    public void mbUpdateWriteError( Container cont, char c ) {
        MsgBox messageBox = new MsgBox( (Frame) this.getParent(), true, cont );
        messageBox.jLabel1.setText(bundle.getString("ACHTUNG"));
        messageBox.jLabel2.setText(bundle.getString("Unexpected") + " " + c + " " + bundle.getString("empfangen"));
        messageBox.jLabel3.setText(bundle.getString("restartupdate"));
        messageBox.setVisible(true);
    }
    public void mbUpdateWriteError( Container cont, String s ) {
        MsgBox messageBox = new MsgBox( (Frame) this.getParent(), true, cont );
        messageBox.jLabel1.setText(bundle.getString("ACHTUNG"));
        messageBox.jLabel2.setText(bundle.getString("Unexpected") + " " + s + " " + bundle.getString("empfangen"));
        messageBox.jLabel3.setText(bundle.getString("restartupdate"));
        messageBox.setVisible(true);
    }
    public void mbUpdateReadAnswerError( Container cont ) {
        MsgBox messageBox = new MsgBox( (Frame) this.getParent(), true, cont);
        messageBox.jLabel1.setText(bundle.getString("ACHTUNG"));
        messageBox.jLabel2.setText(bundle.getString("Noanswer"));
        messageBox.jLabel3.setText(bundle.getString("Updatecancelled"));
        messageBox.setVisible(true);
    }

    public void mbConfigReadSuccess( Container cont ) {
        mbConfigReadSuccess( cont, 0);
    }
    public void mbConfigReadSuccess( Container cont, int seconds) {
        MsgBox messageBox = new MsgBox( (Frame) this.getParent(), true, cont);
        messageBox.jLabel1.setText("Information:");
        messageBox.jLabel2.setText(bundle.getString("successfullyread"));
        messageBox.jLabel3.setText("");
        messageBox.setCountdown(seconds);
        messageBox.setVisible(true);
    }

    public void mbConfigReadAbort( Container cont ) {
        mbConfigReadAbort( cont, 0);
    }
    public void mbConfigReadAbort(Container cont, int seconds) {
        MsgBox messageBox = new MsgBox( (Frame) this.getParent(), true, cont);
        messageBox.jLabel1.setText("Problem!");
        messageBox.jLabel2.setText(bundle.getString("readaborted"));
        messageBox.jLabel3.setText(bundle.getString("notprocessed"));
        messageBox.setCountdown(seconds);
        messageBox.setVisible(true);
    }

    public void mbConfigReadCancelled( Container cont ) {
        mbConfigReadCancelled( cont, 0);
    }
    public void mbConfigReadCancelled( Container cont, int seconds) {
        MsgBox messageBox = new MsgBox( (Frame) this.getParent(), true, cont);
        messageBox.jLabel1.setText("Information:");
        messageBox.jLabel2.setText(bundle.getString("readaborted"));
        messageBox.jLabel3.setText(bundle.getString("incomplete"));
        messageBox.setCountdown(seconds);
        messageBox.setVisible(true);
    }

    public void mbRWCancelled( Container cont ) {
        mbRWCancelled( cont, 0);
    }
    public void mbRWCancelled( Container cont, int seconds) {
        MsgBox messageBox = new MsgBox( (Frame) this.getParent(), true, cont);
        messageBox.jLabel1.setText("Information:");
        messageBox.jLabel2.setText(bundle.getString("readwritecancelled"));
        messageBox.jLabel3.setText(bundle.getString("incomplete"));
        messageBox.setCountdown(seconds);
        messageBox.setVisible(true);
    }

    public void mbConfigWriteCancelled( Container cont ) {
        mbConfigWriteCancelled( cont, 0);
    }
    public void mbConfigWriteCancelled( Container cont, int seconds) {
        MsgBox messageBox = new MsgBox( (Frame) this.getParent(), true, cont);
        messageBox.jLabel1.setText("Information:");
        messageBox.jLabel2.setText(bundle.getString("readwritecancelled"));
        messageBox.jLabel3.setText(bundle.getString("incomplete"));
        messageBox.setCountdown(seconds);
        messageBox.setVisible(true);
    }

    public void mbConfigWriteSuccess( Container cont ) {
        mbConfigWriteSuccess( cont, 0);
    }
    public void mbConfigWriteSuccess( Container cont, int seconds) {
        MsgBox messageBox = new MsgBox( (Frame) this.getParent(), true, cont);
        messageBox.jLabel1.setText("Information");
        messageBox.jLabel2.setText(bundle.getString("successfullywritten"));
        messageBox.jLabel3.setText("");
        messageBox.setCountdown(seconds);
        messageBox.setVisible(true);
    }
    public void mbConfigWriteSuccessMcWithProblems( Container cont, int errBits ) {
        if( errBits == 0 ) {
            mbConfigWriteSuccess( cont );
            return;
        }
        MsgBox messageBox = new MsgBox( (Frame) this.getParent(), true, cont);
        if( bSpracheDE) {
            messageBox.jLabel1.setText("Schreiben erfolgreich.");
            messageBox.jLabel2.setText("ACHTUNG! Einige Eingaben waren falsch und wurden korrigiert:");
            String sFehler = "(";
            if((errBits & 0x0007) != 0 )
                sFehler += "Adressfehler: ";
            if((errBits & 0x0001) == 0x0001 ){
                sFehler += "DCC";
                if((errBits & 0x0006) != 0) {
                    sFehler += ", ";
                }
            }
            if((errBits & 0x0002) == 0x0002 ) {
                sFehler += "MM";
                if((errBits & 0x0004) != 0) {
                    sFehler += ", ";
                }
            }
            if((errBits & 0x0004) == 0x0004 )
                sFehler += "m3";
            if(((errBits & 0x00F8) != 0)&& sFehler.length() > 1)
                sFehler += " ; ";
            if((errBits & 0x0008) == 0x0008 )
                sFehler += "Formatfehler";
            if(((errBits & 0x00F0) != 0)&& sFehler.length() > 1)
                sFehler += " ; ";
            if((errBits & 0x0010) == 0x0010 )
                sFehler += "Fahrstufenfehler";
            if(((errBits & 0x00E0) != 0)&& sFehler.length() > 1)
                sFehler += " ; ";
            if((errBits & 0x0020) == 0x0020 )
                sFehler += "Name zu lang";
            if(((errBits & 0x00C0) != 0)&& sFehler.length() > 1)
                sFehler += " ; ";
            if((errBits & 0x0040) == 0x0040 )
                sFehler += "Adressfehler Doppeltraktion";
            if(((errBits & 0x0080) != 0)&& sFehler.length() > 1)
                sFehler += " ; ";
            if((errBits & 0x0080) == 0x0080 )
                sFehler += "Formatfehler Acc-Decoder";
            sFehler += ")";
            messageBox.jLabel3.setText(sFehler);
          } else {
            messageBox.jLabel1.setText("Configuration data successfully sent.");
            messageBox.jLabel2.setText("ATTENTION! Some data was corrected while writing:");
            String sFehler = "(";
            if((errBits & 0x0007) != 0)
                sFehler += "loco address error: ";
            if((errBits & 0x0001) == 0x0001 ) {
                sFehler += "DCC";
                if((errBits & 0x0006) != 0) {
                    sFehler += ", ";
                }
            }
            if((errBits & 0x0002) == 0x0002 ) {
                sFehler += "MM";
                if((errBits & 0x0004) != 0) {
                    sFehler += ", ";
                }
            }
            if((errBits & 0x0004) == 0x0004 )
                sFehler += "m3";
            if(((errBits & 0x00F8) != 0)&& sFehler.length() > 1)
                sFehler += " ; ";
            if((errBits & 0x0008) == 0x0008 )
                sFehler += "format error";
            if(((errBits & 0x00F0) != 0)&& sFehler.length() > 1)
                sFehler += " ; ";
            if((errBits & 0x0010) == 0x0010 )
                sFehler += "speed step error";
            if(((errBits & 0x00E0) != 0)&& sFehler.length() > 1)
                sFehler += " ; ";
            if((errBits & 0x0020) == 0x0020 )
                sFehler += "name too long";
            if(((errBits & 0x00C0) != 0)&& sFehler.length() > 1)
                sFehler += " ; ";
            if((errBits & 0x0040) == 0x0040 )
                sFehler += "traction address error";
            if(((errBits & 0x0080) != 0)&& sFehler.length() > 1)
                sFehler += " ; ";
            if((errBits & 0x0080) == 0x0080 )
                sFehler += "accessory format error";
            sFehler += ")";
            messageBox.jLabel3.setText(sFehler);
        }
        messageBox.setVisible(true);
    }

    public void mbConfigWriteError( Container cont ) {
        mbConfigWriteError( cont, "", 0);
    }
    public void mbConfigWriteError( Container cont, String sError ) {
        mbConfigWriteError( cont, sError, 0);
    }
    public void mbConfigWriteError( Container cont, String sError, int seconds) {
        MsgBox messageBox = new MsgBox( (Frame) this.getParent(), true, cont);

        messageBox.jLabel1.setText(bundle.getString("Fehler"));
        messageBox.jLabel2.setText(bundle.getString("Errorwriting"));
        if( (sError != null) && (sError.length() > 0 ) ) {
            messageBox.jLabel3.setText(sError);
        } else {
            messageBox.jLabel3.setText("");
        }
        messageBox.setCountdown(seconds);
        messageBox.setVisible(true);
    }

    public void mbM3TooMany( Container cont ) {
        mbGeneric( cont, "Problem", bundle.getString("UIDSIDPaare"), bundle.getString("MaxAnz")+c.MAX_M3_ENTRIES, 0, true);
        return;
    }

    public void mbM3duplicateUid( Container cont ) {
        mbGeneric( cont, "Information", bundle.getString("M3.duplicateUID"), 0, true);
        return;
    }

    public void mbTableCheckOK( Container cont, boolean repair, int seconds) {
        MsgBox messageBox = new MsgBox( (Frame) this.getParent(), true, cont);
        messageBox.jLabel1.setText("Information");
        if( repair ) {
            messageBox.jLabel2.setText(bundle.getString("TabellenreparaturOK"));
        } else {
            messageBox.jLabel2.setText(bundle.getString("TablecheckedOK"));
        }
        messageBox.jLabel3.setText("");
        messageBox.setCountdown(seconds);
        messageBox.setVisible(true);
    }
    public void mbTableCheck( Container cont, int errBits ) {
        mbTableCheck( cont, errBits, false, null );
        return;
    }
    public void mbTableCheck( Container cont, int errBits, boolean repair ) {
        mbTableCheck( cont, errBits, repair, null );
        return;
    }
    public void mbTableCheck( Container cont, int errBits, boolean repair, String sList ) {
        MsgBox messageBox = new MsgBox( (Frame) this.getParent(), true, cont);
        if( bSpracheDE) {
            if( sList != null ) {
                messageBox.jLabel1.setText("Probleme in Zeile(n)"+sList+" festgestellt!");
            } else  {
                messageBox.jLabel1.setText("Probleme festgestellt!");
            }
            if( repair )
                messageBox.jLabel2.setText("ACHTUNG! Einige Eingaben waren falsch und wurden korrigiert:");
            else
                messageBox.jLabel2.setText("ACHTUNG! Einige Eingaben sind falsch:");
            String sFehler = "(";
            if((errBits & 0x0007) != 0 )
                sFehler += "Adressfehler: ";
            if((errBits & 0x0001) == 0x0001 ){
                sFehler += "DCC";
                if((errBits & 0x0006) != 0) {
                    sFehler += ", ";
                }
            }
            if((errBits & 0x0002) == 0x0002 ) {
                sFehler += "MM";
                if((errBits & 0x0004) != 0) {
                    sFehler += ", ";
                }
            }
            if((errBits & 0x0004) == 0x0004 )
                sFehler += "m3";
            if(((errBits & 0x00F8) != 0)&& sFehler.length() > 1)
                sFehler += " ; ";
            if((errBits & 0x0008) == 0x0008 )
                sFehler += "Formatfehler";
            if((errBits & 0x0010) != 0) {
                if(sFehler.length() > 1)
                    sFehler += " ; ";
                sFehler += "Fahrstufenfehler";
            }
            if((errBits & 0x0020) != 0) {
                if(sFehler.length() > 1)
                    sFehler += " ; ";
                sFehler += "Name zu lang";
            }
            if((errBits & 0x0040) != 0) {
                if(sFehler.length() > 1)
                  sFehler += " ; ";
                sFehler += "Adressfehler Doppeltraktion";
            }
            if((errBits & 0x0080) != 0) {
                if(sFehler.length() > 1)
                    sFehler += " ; ";
                sFehler += "Formatfehler Acc-Decoder";
            }
            sFehler += ")";
            messageBox.jLabel3.setText(sFehler);
          } else {
            if( sList != null ) {
                messageBox.jLabel1.setText("Problems in line(s)"+sList+" detected!");
            } else  {
                messageBox.jLabel1.setText("Problems detected!");
            }
            if( repair )
                messageBox.jLabel2.setText("ATTENTION! Some data was corrected:");
            else
                messageBox.jLabel2.setText("ATTENTION! Some data is wrong:");
            String sFehler = "(";
            if((errBits & 0x0007) != 0)
                sFehler += "loco address error: ";
            if((errBits & 0x0001) == 0x0001 ) {
                sFehler += "DCC";
                if((errBits & 0x0006) != 0) {
                    sFehler += ", ";
                }
            }
            if((errBits & 0x0002) == 0x0002 ) {
                sFehler += "MM";
                if((errBits & 0x0004) != 0) {
                    sFehler += ", ";
                }
            }
            if((errBits & 0x0004) == 0x0004 )
                sFehler += "m3";
            if(((errBits & 0x00F8) != 0)&& sFehler.length() > 1)
                sFehler += " ; ";
            if((errBits & 0x0008) == 0x0008 )
                sFehler += "format error";
            if(((errBits & 0x00F0) != 0)&& sFehler.length() > 1)
                sFehler += " ; ";
            if((errBits & 0x0010) == 0x0010 )
                sFehler += "speed step error";
            if(((errBits & 0x00E0) != 0)&& sFehler.length() > 1)
                sFehler += " ; ";
            if((errBits & 0x0020) == 0x0020 )
                sFehler += "name too long";
            if(((errBits & 0x00C0) != 0)&& sFehler.length() > 1)
                sFehler += " ; ";
            if((errBits & 0x0040) == 0x0040 )
                sFehler += "traction address error";
            if(((errBits & 0x0080) != 0)&& sFehler.length() > 1)
                sFehler += " ; ";
            if((errBits & 0x0080) == 0x0080 )
                sFehler += "accessory format error";
            sFehler += ")";
            messageBox.jLabel3.setText(sFehler);
        }
        ModalExclusionType MET = messageBox.getModalExclusionType();
        String t = "" + MET ;
        System.out.println("MET = ["+t+"]");
        messageBox.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
        messageBox.setVisible(true);
    }

    public void mbFileNotFound( Container cont ) {
        MsgBox messageBox = new MsgBox( (Frame) this.getParent(), true, cont);
        messageBox.jLabel1.setText(bundle.getString("Fehler"));
        messageBox.jLabel2.setText("");
        messageBox.jLabel3.setText(bundle.getString("Filenotfound"));
        messageBox.setVisible(true);
    }
    public void mbFileNotFound( Container cont, String name) {
        MsgBox messageBox = new MsgBox( (Frame) this.getParent(), true, cont);
            messageBox.jLabel1.setText(bundle.getString("Fehler"));
            messageBox.jLabel2.setText("");
            messageBox.jLabel3.setText(bundle.getString("Filenotfound") + ": "+name);
        messageBox.setVisible(true);
    }
    public void mbFileOpenError( Container cont ) {
        MsgBox messageBox = new MsgBox( (Frame) this.getParent(), true, cont);
        messageBox.jLabel1.setText(bundle.getString("Fehler"));
        messageBox.jLabel2.setText("");
        messageBox.jLabel3.setText(bundle.getString("Filenotopenend"));
        messageBox.setVisible(true);
    }
    public void mbFileOpenError( Container cont, String name) {
        MsgBox messageBox = new MsgBox( (Frame) this.getParent(), true, cont);
        messageBox.jLabel1.setText(bundle.getString("Fehler"));
        messageBox.jLabel2.setText("");
        messageBox.jLabel3.setText(bundle.getString("Filenotopenend") + ": "+name);
        messageBox.setVisible(true);
    }
    public void mbFileWriteError( Container cont ) {
        MsgBox messageBox = new MsgBox( (Frame) this.getParent(), true, cont);
        messageBox.jLabel1.setText(bundle.getString("Fehler"));
        messageBox.jLabel2.setText("");
        messageBox.jLabel3.setText(bundle.getString("Problemswriting"));
        messageBox.setVisible(true);
    }
    public void mbFileReadError( Container cont ) {
        MsgBox messageBox = new MsgBox( (Frame) this.getParent(), true, cont);
        messageBox.jLabel1.setText(bundle.getString("Fehler"));
        messageBox.jLabel2.setText("");
        messageBox.jLabel3.setText(bundle.getString("Problemsreading"));
        messageBox.setVisible(true);
    }
    public void mbFileHexFormatError( Container cont ) {
        MsgBox messageBox = new MsgBox( (Frame) this.getParent(), true, cont);
        messageBox.jLabel1.setText(bundle.getString("ACHTUNG"));
        messageBox.jLabel2.setText(bundle.getString("noHEX"));
        messageBox.jLabel3.setText(bundle.getString("chooseHEX"));
        messageBox.setVisible(true);
    }
    public void mbFileReadBeginError( Container cont, String begin) {
        MsgBox messageBox = new MsgBox( (Frame) this.getParent(), true, cont);
        messageBox.jLabel1.setText(bundle.getString("Fehler"));
        messageBox.jLabel2.setText("");
        messageBox.jLabel3.setText(bundle.getString("notstarting") + begin );
        messageBox.setVisible(true);
    }

    public void mbTimeout( Container cont ) {
        mbTimeout( cont, 0 );
    }
    public void mbTimeout( Container cont, int errCode ) {
        MsgBox messageBox = new MsgBox( (Frame) this.getParent(), true, cont);
        if(bSpracheDE)
        {
            messageBox.jLabel1.setText("Achtung!");
            switch( errCode ) {
                case c.mbRDtimeout:
                    messageBox.jLabel2.setText("Zentrale meldet Timeout beim Lesen!");
                    messageBox.jLabel3.setText("Auslesen abgebrochen.");
                    break;
                case c.mbWRtimeout:
                    messageBox.jLabel2.setText("Zentrale meldet Timeout beim Schreiben!");
                    messageBox.jLabel3.setText("Schreiben abgebrochen.");
                    break;
                case c.mbMCRDtimeout:
                    messageBox.jLabel2.setText("Timeout beim Lesen aus der Zentrale!");
                    messageBox.jLabel3.setText("Auslesen abgebrochen.");
                    break;
                case c.mbRDtooShort:
                    messageBox.jLabel2.setText("Antwort zu kurz beim Lesen aus der Zentrale!");
                    messageBox.jLabel3.setText("Auslesen abgebrochen.");
                    break;
                case c.mbMCRDcommitErr:
                    messageBox.jLabel2.setText("Timeout beim Warten auf eine Antwort der Zentrale!");
                    messageBox.jLabel3.setText("Bitte Verbindung überprüfen.");
                    break;
                case c.mbMCWRtimeout:
                    messageBox.jLabel2.setText("Timeout beim Schreiben in die Zentrale!");
                    messageBox.jLabel3.setText("Schreiben abgebrochen.");
                    break;
                case c.mbRDverify:
                    messageBox.jLabel2.setText("Timeout beim Verifizieren der Zentrale!");
                    messageBox.jLabel3.setText("");
                    break;
                default:
                    messageBox.jLabel2.setText("Timeout aufgetreten!");
                    messageBox.jLabel3.setText("");
            }
        }
        else
        {
            messageBox.jLabel1.setText("WARNING!");
            switch( errCode ) {
                case c.mbRDtimeout:
                    messageBox.jLabel2.setText("Control unit reports timeout on read!");
                    messageBox.jLabel3.setText("read cancelled");
                    break;
                case c.mbWRtimeout:
                    messageBox.jLabel2.setText("Control unit reports timeout on write!");
                    messageBox.jLabel3.setText("write cancelled");
                    break;
                case c.mbMCRDtimeout:
                    messageBox.jLabel2.setText("Timeout reading data from control unit!");
                    messageBox.jLabel3.setText("read cancelled");
                    break;
                case c.mbRDtooShort:
                    messageBox.jLabel2.setText("Answer too short while reading data from control unit!");
                    messageBox.jLabel3.setText("read cancelled");
                    break;
                case c.mbMCRDcommitErr:
                    messageBox.jLabel2.setText("Timeout waiting for an answer from control unit!");
                    messageBox.jLabel3.setText("Please check connection.");
                    break;
                case c.mbMCWRtimeout:
                    messageBox.jLabel2.setText("Timeout writing data to control unit!");
                    messageBox.jLabel3.setText("write cancelled");
                    break;
                case c.mbRDverify:
                    messageBox.jLabel2.setText("Timeout verifying control unit!");
                    messageBox.jLabel3.setText("");
                    break;
                default:
                    messageBox.jLabel2.setText("Timeout occured!");
                    messageBox.jLabel3.setText("");
            }
        }
        messageBox.setVisible(true);
    }
    public void mbTimeoutMcUpdStart( Container cont ) {
        MsgBox messageBox = new MsgBox( (Frame) this.getParent(), true, cont);
        if(bSpracheDE)
        {
            messageBox.jLabel1.setText("keine Verbindung zur MasterControl!");
            messageBox.jLabel2.setText("Bitte Verbindung überprüfen und");
            messageBox.jLabel3.setText("MC in Update-Modus setzen (Stop + GO beim Einschalten drücken)!");
        }
        else
        {
            messageBox.jLabel1.setText("No connection to MasterControl!");
            messageBox.jLabel2.setText("Please check connectivity and ");
            messageBox.jLabel3.setText("set MC in update mode: Press Stop + GO while switching power on!");
        }
        messageBox.setVisible(true);
    }

    public void mbEnablePower( Container cont ) {
        MsgBox messageBox = new MsgBox( (Frame) this.getParent(), true, cont);
        if(bSpracheDE)
        {
            messageBox.jLabel1.setText("Schienenspannung ist aus!");
            messageBox.jLabel2.setText("");
            messageBox.jLabel3.setText("Bitte Schienenspannung einschalten und erneut probieren");
        }
        else
        {
            messageBox.jLabel1.setText("Track Power is off!");
            messageBox.jLabel2.setText("");
            messageBox.jLabel3.setText("Please enable Power and try again");
        }
        messageBox.setVisible(true);
    }

    public void mbNoTams( Container cont ) {
        mbNoTams( cont, 0 );
    }

    public void mbNoTams( Container cont, int errCode ) {
        MsgBox messageBox = new MsgBox( (Frame) this.getParent(), true, cont);
        if(bSpracheDE)
        {
            messageBox.jLabel1.setText("Achtung!");
            messageBox.jLabel2.setText("Dies ist kein Tams Decoder!");
            switch( errCode ) {
                case c.mbRDcancel:
                    messageBox.jLabel3.setText("weiteres Auslesen abgebrochen.");
                    break;
                case c.mbWRcancel:
                    messageBox.jLabel3.setText("weiteres Schreiben abgebrochen.");
                    break;
                default:
                    messageBox.jLabel3.setText("");
            }
        }
        else
        {
            messageBox.jLabel1.setText("WARNING!");
            messageBox.jLabel2.setText("This is no Tams device!");
            switch( errCode ) {
                case c.mbRDcancel:
                    messageBox.jLabel3.setText("read cancelled");
                    break;
                case c.mbWRcancel:
                    messageBox.jLabel3.setText("write cancelled");
                    break;
                default:
                    messageBox.jLabel3.setText("");
            }
        }
        messageBox.setVisible(true);
    }
    public void mbNoTamsMC( Container cont ) {
        MsgBox messageBox = new MsgBox( (Frame) this.getParent(), true, cont);
        messageBox.jLabel1.setText(bundle.getString("Fehler"));
        messageBox.jLabel2.setText(bundle.getString("Wrongcu") + gsZentrale + "\" "+bundle.getString("configured"));
        messageBox.jLabel3.setText(bundle.getString("PleaseSelectMC"));
        messageBox.setVisible(true);
    }

    public void mbNoS88modules( Container cont ) {
        MsgBox messageBox = new MsgBox( (Frame) this.getParent(), true, cont);
        messageBox.jLabel1.setText(bundle.getString("Information"));
        messageBox.jLabel2.setText(bundle.getString("NoS88modules"));
        messageBox.jLabel3.setText(bundle.getString("SkipS88monitor"));
        messageBox.setVisible(true);
    }

    public void mbInvalidValue( Container cont, int cv, int value, String validValues ) {
        MsgBox messageBox = new MsgBox( (Frame) this.getParent(), true, cont);
        messageBox.jLabel1.setText(bundle.getString("ACHTUNG"));
        messageBox.jLabel2.setText("CV# "+cv+bundle.getString("invalidvalue")+value);
        if( validValues.length() > 0) {
            messageBox.jLabel3.setText(bundle.getString("Validvalues")+validValues);
        }
        messageBox.setVisible(true);
    }

    public void mbValueTooSmall( Container cont ) {
        mbValueTooSmall( cont, 0, 0, false);
    }
    public void mbValueTooSmall( Container cont, int low, int high, boolean showRange ) {
        MsgBox messageBox = new MsgBox( (Frame) this.getParent(), true, cont);
        messageBox.jLabel1.setText(bundle.getString("ACHTUNG"));
        messageBox.jLabel2.setText(bundle.getString("Valuetoosmall"));
        if(bSpracheDE)
        {
            if( showRange ) {
                messageBox.jLabel3.setText("Bitte einen Wert zwischen "+Integer.toString(low)+" und "+Integer.toString(high)+" eingeben.");
            } else {
                messageBox.jLabel3.setText("Bitte einen größeren Wert eingeben.");
            }
        }
        else
        {
            if( showRange ) {
                messageBox.jLabel3.setText("Please enter a value between "+Integer.toString(low)+" and "+Integer.toString(high)+" .");
            } else {
                messageBox.jLabel3.setText("Please enter a higher value.");
            }
        }
        messageBox.setVisible(true);
    }

    public void mbValueTooBig( Container cont ) {
        mbValueTooBig( cont, 0, 0, false);
    }
    public void mbValueTooBig( Container cont, int low, int high, boolean showRange ) {
        MsgBox messageBox = new MsgBox( (Frame) this.getParent(), true, cont);
        messageBox.jLabel1.setText(bundle.getString("ACHTUNG"));
        messageBox.jLabel2.setText(bundle.getString("Valuetoobig"));
        if(bSpracheDE)
        {
            if( showRange ) {
                messageBox.jLabel3.setText("Bitte einen Wert zwischen "+Integer.toString(low)+" und "+Integer.toString(high)+" eingeben.");
            } else {
                messageBox.jLabel3.setText("Bitte einen kleineren Wert eingeben.");
            }
        }
        else
        {
            if( showRange ) {
                messageBox.jLabel3.setText("Please enter a value between "+Integer.toString(low)+" and "+Integer.toString(high)+" .");
            } else {
                messageBox.jLabel3.setText("Please enter a lower value.");
            }
        }
        messageBox.setVisible(true);
    }

    public void mbValueNaN( Container cont ) {
        mbValueNaN( cont, 0, 0, false);
    }
    public void mbValueNaN( Container cont, int low, int high, boolean showRange ) {
        MsgBox messageBox = new MsgBox( (Frame) this.getParent(), true, cont);
        messageBox.jLabel1.setText(bundle.getString("ACHTUNG"));
        messageBox.jLabel2.setText(bundle.getString("Valueinvalid"));
        if(bSpracheDE)
        {
            if( showRange ) {
                messageBox.jLabel3.setText("Bitte einen Wert zwischen "+Integer.toString(low)+" und "+Integer.toString(high)+" eingeben.");
            } else {
                messageBox.jLabel3.setText("Bitte einen korrekten Wert eingeben.");
            }
        }
        else
        {
            if( showRange ) {
                messageBox.jLabel3.setText("Please use a value between "+Integer.toString(low)+" and "+Integer.toString(high)+" .");
            } else {
                messageBox.jLabel3.setText("Please use a valid value.");
            }
        }
        messageBox.setVisible(true);
    }

    public void mbValueNaNcv( Container cont, int low, int high, int cv, boolean showRange ) {
        MsgBox messageBox = new MsgBox( (Frame) this.getParent(), true, cont);
        messageBox.jLabel1.setText(bundle.getString("ACHTUNG"));
        messageBox.jLabel2.setText(bundle.getString("Valueinvalid"));
        if(bSpracheDE)
        {
            if( showRange ) {
                messageBox.jLabel3.setText("CV#"+cv+" muß einen Wert zwischen "+Integer.toString(low)+" und "+Integer.toString(high)+" haben.");
            } else {
                messageBox.jLabel3.setText("Bitte einen korrekten Wert für CV#"+cv+" eingeben.");
            }
        }
        else
        {
            if( showRange ) {
                messageBox.jLabel3.setText("CV#"+cv+" must have a value between "+Integer.toString(low)+" and "+Integer.toString(high)+" .");
            } else {
                messageBox.jLabel3.setText("Please use a valid value for CV#"+cv);
            }
        }
        messageBox.setVisible(true);
    }

    public void mbValueConsist( Container cont, int low, int high ) {
        MsgBox messageBox = new MsgBox( (Frame) this.getParent(), true, cont);
        messageBox.jLabel1.setText(bundle.getString("ACHTUNG"));
        messageBox.jLabel2.setText(bundle.getString("Valueinvalid"));
        if(bSpracheDE)
        {
            messageBox.jLabel3.setText("Gültige Werte für Consist-Adresse zwischen "+Integer.toString(low)+" und "+Integer.toString(high)+" .");
        }
        else
        {
            messageBox.jLabel3.setText("Valid consist address is between "+Integer.toString(low)+" and "+Integer.toString(high)+" .");
        }
        messageBox.setVisible(true);
    }

    public void mbAdr128MMonly( Container cont ) {
        MsgBox messageBox = new MsgBox( (Frame) this.getParent(), true, cont);
        messageBox.jLabel1.setText(bundle.getString("ACHTUNG"));
        if(bSpracheDE)
        {
            messageBox.jLabel2.setText("Nur für MM.");
            messageBox.jLabel3.setText("Bei kurzen Adressen zwischen 128 und 255 reagiert der Decoder nur noch auf MM-Befehle!");
        }
        else
        {
            messageBox.jLabel2.setText("Only for MM.");
            messageBox.jLabel3.setText("Short addresses between 128 and 255 are for MM protocol only!");
        }
        messageBox.setVisible(true);
    }

    public void mbBlinkFrequenz( Container cont, int dauer, int pause, String eingabe ) {
        MsgBox messageBox = new MsgBox( (Frame) this.getParent(), true, cont);
        messageBox.jLabel1.setText(bundle.getString("ACHTUNG"));
        if(bSpracheDE)
        {
            // "Wert \""+jBlink_Pausezeit_1.getText()+"\" ungültig.", "Bitte einen Wert zwischen 0 und Blinkfrequenz minus 2xein eingeben."
            messageBox.jLabel2.setText("Eingabe \""+eingabe+"\" für Frequenz ist ungültig.");
            messageBox.jLabel3.setText("Bitte einen Wert zwischen "+((dauer*2)+pause)+" und 255 eingeben. ( Minimum = ( Blinkdauer("+dauer+") * 2 ) plus Pause("+pause+") )");
        }
        else
        {
            messageBox.jLabel2.setText("Input \""+eingabe+"\" for frequency is invalid.");
            messageBox.jLabel3.setText("Please use a value between "+((dauer*2)+pause)+" and 255. ( min = ( onTime("+dauer+") * 2 ) plus pause("+pause+") ) ");
        }
        messageBox.pack(); // resize to fit contents before making visible ;)
        messageBox.setVisible(true);
    }

    public void mbBlinkEinschaltzeit( Container cont, int freq, int pause, String eingabe ) {
        MsgBox messageBox = new MsgBox( (Frame) this.getParent(), true, cont);
        messageBox.jLabel1.setText(bundle.getString("ACHTUNG"));
        if(bSpracheDE)
        {
            // "Wert \""+jBlink_Pausezeit_1.getText()+"\" ungültig.", "Bitte einen Wert zwischen 0 und Blinkfrequenz minus 2xein eingeben."
            messageBox.jLabel2.setText("Eingabe \""+eingabe+"\" für Einschaltzeit ist ungültig.");
            messageBox.jLabel3.setText("Bitte einen Wert zwischen 0 und "+((freq-pause)/2)+" eingeben. ( = ( Blinkfrequenz("+freq+") minus Pause("+pause+") geteilt durch 2 ) )");
        }
        else
        {
            messageBox.jLabel2.setText("Input \""+eingabe+"\" for onTime is invalid.");
            messageBox.jLabel3.setText("Please use a value between 0 and "+((freq-pause)/2)+" ( = ( flash freqeuncy("+freq+") minus pause("+pause+") ) / 2 )");
        }
        messageBox.pack(); // resize to fit contents before making visible ;)
        messageBox.setVisible(true);
    }

    public void mbBlinkPausezeit( Container cont, int freq, int dauer, String eingabe ) {
        MsgBox messageBox = new MsgBox( (Frame) this.getParent(), true, cont);
        messageBox.jLabel1.setText(bundle.getString("ACHTUNG"));
        if(bSpracheDE)
        {
            // "Wert \""+jBlink_Pausezeit_1.getText()+"\" ungültig.", "Bitte einen Wert zwischen 0 und Blinkfrequenz minus 2xein eingeben."
            messageBox.jLabel2.setText("Eingabe \""+eingabe+"\" für Pause ist ungültig.");
            messageBox.jLabel3.setText("Bitte einen Wert zwischen 0 und "+(freq-(2*dauer))+" eingeben. ( = Blinkfrequenz("+freq+") minus 2 * Einschaltdauer("+dauer+") )");
        }
        else
        {
            messageBox.jLabel2.setText("Input \""+eingabe+"\" for pause is invalid.");
            messageBox.jLabel3.setText("Please use a value between 0 and "+(freq-(2*dauer))+" ( = flash freqeuncy("+freq+") minus 2 * OnTime("+dauer+") )");
        }
        messageBox.setVisible(true);
    }

    public int yesNoResetFactoryDefault() {
        int dialogResult = -1;
        if(bSpracheDE)
        {
            Object[] options = { "Ja", "Nein" };
            dialogResult = JOptionPane.showOptionDialog(
                    this,
                    "Zurücksetzen aller Werte auf Auslieferungszustand.\n\nSind Sie sicher?",
                    "Warnung",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[1] );
        } else {
            Object[] options = { "Yes", "No" };
            dialogResult = JOptionPane.showOptionDialog(
                    this,
                    "Reset all values to factory defaults.\n\nAre you sure?",
                    "Warning",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[1] );
        }
        return dialogResult;
    }

    public int yesNoCalibrateBiDiBooster() {
        int dialogResult = -1;
        if(bSpracheDE)
        {
            Object[] options = { "Ja", "Nein" };
            dialogResult = JOptionPane.showOptionDialog(
                    this,
                    "Am Boosterausgang sollte kein Verbraucher angeschlossen sein.\n\nJetzt kalibrieren?",
                    "Warnung",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[1] );
        } else {
            Object[] options = { "Yes", "No" };
            dialogResult = JOptionPane.showOptionDialog(
                    this,
                    "No load should be connected to the booster output.\n\nCalibrate now?",
                    "Warning",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[1] );
        }
        return dialogResult;
    }

    public int yesNoM3listSave() {
        int dialogResult = -1;
        if(bSpracheDE)
        {
            Object[] options = { "Ja", "Nein" };
            dialogResult = JOptionPane.showOptionDialog(
                    this,
                    "M3 UID/SID-Liste wurde geändert.\n\nSoll die Liste gesichert werden?",
                    "Warnung",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0] );
        } else {
            Object[] options = { "Yes", "No" };
            dialogResult = JOptionPane.showOptionDialog(
                    this,
                    "M3 UID/SID list changed.\n\nDo you want to save?",
                    "Warning",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0] );
        }
        return dialogResult;
    }

    // Checks
    public int checkTextFieldUnit( Container cont, JTextField textField, int minValue, int maxValue, int defValue, int unit, boolean show ) {
        int i = checkTextField( cont, textField, minValue, maxValue, defValue, show );
        int j = (i/unit)*unit;
        if( i != j ) {
            textField.setText(Integer.toString(j));
        }
        return j;
    }

    public int checkTextField( Container cont, JTextField textField, int minValue, int maxValue, int defValue, boolean show ) {
        int value;
        try {
            value = Integer.parseInt(textField.getText());
            if(value < minValue)
            {
                if( show ) {
                    mbValueTooSmall( cont, minValue, maxValue, true );
                    textField.grabFocus();
                }
                value = minValue;
                textField.setText("" + value);
            }
            if(value > maxValue)
            {
                if( show ) {
                    mbValueTooBig( cont, minValue, maxValue, true );
                    textField.grabFocus();
                }
                value = maxValue;
                textField.setText("" + value);
            }
        } catch (NumberFormatException numberFormatException) {
            if( show ) {
                mbValueNaN( cont, minValue, maxValue, true );
                textField.grabFocus();
            }
            value = defValue;
            textField.setText("" + value);
        }
        return value;
    }

    public boolean checkNumRange( String sValue, int minValue, int maxValue ) {
        try {
            int value = Integer.parseInt(sValue);
            return( checkNumRange( value, minValue, maxValue ) );
        } catch (NumberFormatException numberFormatException) {
                return false;
        }
    }
    public boolean checkNumRange( int value, int minValue, int maxValue ) {
        if(value < minValue)
            return false;
        if(value > maxValue)
            return false;
        return true;
    }

    public int checkAndGetStrNumRangeDef( Container cont, String sValue, int minValue, int maxValue, int defValue, boolean show ) {
        int value;
        try {
            value = Integer.parseInt(sValue);
            if(value < minValue)
            {
                if( show )
                    mbValueTooSmall( cont, minValue, maxValue, true );
                value = minValue;
            }
            if(value > maxValue)
            {
                if( show )
                    mbValueTooBig( cont, minValue, maxValue, true );
                value = maxValue;
            }
        } catch (NumberFormatException numberFormatException) {
            if( show )
                mbValueNaN( cont, minValue, maxValue, true );
            value = defValue;
        }
        return value;
    }

    public Boolean setCVvalue( int[][] CV, int cv, int value ) {
        if( CV == null ) {
            return false;
        }
        int CVlen = CV[0].length ;
        if( checkNumRange( cv, 1, CVlen) && checkNumRange( value, 0, 255) ) {
            CV[0][cv] = cv;
            CV[1][cv] = value ;
            return true;
        }
        return false;
    }

    public Boolean unsetCVvalue( int[][] CV, int cv ) {
        if( CV == null ) {
            return false;
        }
        int CVlen = CV[0].length ;
        if( checkNumRange( cv, 1, CVlen) ) {
            CV[0][cv] = 0;
            CV[1][cv] = 0 ;
            return true;
        }
        return false;
    }

    /**
     *
     * @param bArray answer from command station
     * @return CV value <b>OR</b> <br> -1 if bArray is null pointer or an invalid answer <br> -2 answer is incomplete
     */
    public int checkPTRDAnswer( byte[] bArray ) {
        if( bArray == null ) {
            return -1;
        }
        // convert byteArray into a String
        String str_bArr = new String(bArray);
        // Split string into parts
        String[] strParts = str_bArr.split(" = |\r|\\000");
        if( debugLevel >= 3 ) {
            for( int i = 0 ; i < strParts.length ; i++ ) {
                System.out.println("checkPTRDAnswer 1622 strParts["+i+"]("+strParts[i].length()+")=\""+strParts[i]+"\""+((strParts[i].equals("]"))?" (ENDE)":"" ));
            }
        }
        int cvWert = -1;
        if( strParts.length > 0) {
            if( ! strParts[(strParts.length-1)].equals("]") ) {
                // answer is not complete !
                if( debugLevel >= 3 ) {
                    System.out.println("checkPTRDAnswer 1630 strParts.length="+strParts.length+" strParts["+(strParts.length-1)+"]=["+strParts[strParts.length-1]+"] INCOMPLETE");
                }
                return -2;
            }
            // answer is complete
            if( debugLevel >= 4 ) {
                if( strParts[(strParts.length-1)].equals("]") ) {
                    System.out.println("checkPTRDAnswer 1637 strParts[(strParts.length-1)].equals(\"[\") Yes");
                } else {
                    System.out.println("checkPTRDAnswer 1639 strParts[(strParts.length-1)].equals(\"[\") NO");
                }
            }

            if( strParts[0].equalsIgnoreCase("No ack") ) {
                System.out.println("checkPTRDAnswer 1644 strParts[0]=["+strParts[0]+"]" );
                return -1;
            }
            // convert first part to integer
            String s = "";
            try {
                s = ""+strParts[0];
                cvWert = Integer.parseInt(s);
            } catch (NumberFormatException ex) {
                System.out.println("checkPTRDAnswer 1516 Eception ex["+ex+"]");
                for( int i = 0 ; i < strParts.length ; i++ ) {
                    System.out.println("checkPTRDAnswer 1655 strParts["+i+"]=\""+strParts[i]+"\""+((strParts[i].startsWith("]"))?" (ENDE)":"" ));
                }
            }
        }
        return cvWert;
    }

    /**
     *
     * @param bArray answer from command station
     * @return true answer is complete (last answer line starts with "]")
     */
    public boolean checkAnswerComplete( byte[] bArray ) {
        if( bArray == null ) {
            return false;
        }
        // convert byteArray into a String
        String str_bArr = new String(bArray);
        // Split string into parts
        String[] strParts = str_bArr.split(" = |\r|\\000");
        if( debugLevel >= 3 ) {
            for( int i = 0 ; i < strParts.length ; i++ ) {
                System.out.println("checkAnswerComplete 1668 strParts["+i+"]=\""+strParts[i]+"\""+((strParts[i].startsWith("]"))?" (ENDE)":"" ));
            }
        }
        if( strParts.length > 0) {
            if( strParts[(strParts.length-1)].startsWith("]") ) {
                // answer is complete !
                if( debugLevel >= 3 ) {
                    System.out.println("checkAnswerComplete 1505 strParts.length="+strParts.length+" strParts["+(strParts.length-1)+"]=["+strParts[strParts.length-1]+"] COMPLETE");
                }
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param bArray answer from command station
     * @return true answer is complete (last answer line starts with "]")
     */
    public boolean checkReadComplete( byte[] bArray ) {
        if( bArray == null ) {
            return false;
        }
        // convert byteArray into a String
        String str_bArr = new String(bArray);
        int idxTerm = str_bArr.indexOf("\r]"); // line starting with "]" is end of data
        if( idxTerm >= 0 ) {
            return true;
        }
        return false;
    }

    /**
     *
     * @param bArray array of bytes (answer from command station)
     */
    public void dumpbArray( byte[] bArray ) {
        if( bArray == null ) {
            return;
        }
        // convert byteArray into a String
        String str_bArr = new String(bArray);
        // Split string into parts
        //String[] strParts = str_bArr.split(" = |\r|\\000");
        String[] strParts = str_bArr.split("\r|\\000");
        dumpbArray( strParts );
    }

    /**
     *
     * @param bArray array of bytes (answer from command station)
     */
    public void dumpbArrayHex( byte[] bArray ) {
        if( bArray == null ) {
            return;
        }
        System.out.print("HEX: ");
        for (byte theByte : bArray)
        {
            System.out.printf( " %02x", theByte );
            // System.out.print(Integer.toHexString(theByte)+" ");
        }
        System.out.println("");
    }
    /**
     *
     * @param bArray array of bytes (answer from command station)
     * @return String with array in Hex
     */
    public String dumpbArrayHexAsString( byte[] bArray ) {
        String sRet;
        String sTmp;
        String sTmp2;
        if( bArray == null ) {
            sRet = "null";
            return sRet;
        }
        sTmp = ("HEX: ");
        for (byte theByte : bArray)
        {
            sTmp2 = String.format(" %02x", theByte);
            sTmp.concat(sTmp2);
        }
        sRet = sTmp;
        return sRet;
    }

    public void dumpbArrayBIN( byte[] bArray, int num ) {
        if( bArray == null ) {
            return;
        }
        for( int n = 0 ; n < num ; n++ ) {
            byte theByte = bArray[n];
            System.out.printf( "HEX: 0x%02x BIN: %s\n",
                    theByte, String.format("%8s", Integer.toBinaryString(theByte & 0xFF)).replace(' ', '0') );
        }
    }

    /**
     *
     * @param strArray array of strings
     */
    public void dumpbArray( String[] strArray ) {
        if( strArray == null ) {
            return;
        }
        for( int i = 0 ; i < strArray.length ; i++ ) {
            System.out.println("dumpbArray 1209 line["+i+"]("+strArray[i].length()+")=\""+strArray[i]+"\""+ (strArray[i].startsWith("]")?" (ENDE)":"") );
        }
    }

    /**
     *
     * @param bArr array of bytes (answer from command station)
     * @param show if true show message box with raw error code
     * @return true if answer starts with 0x00
     * 
     * Liste der Error-Codes:
          0x00  OK      - kein Fehler, Befehl korrekt ausgeführt
          0x02  XBADPRM - Parameterfehler (Parameterbyte außerhalb der zulässigen Werte)
          0x06  XPWOFF  - Befehl nicht ausgeführt, da MC im Modus STOP
          0x07  XNOTSPC - Weichen-Queue ist voll, es können momentan keine weiterern
                          Weichenbefehle angenommen werden.
          0x08  XNOLSPC - Kein Platz im Kommandobuffer, Kommando später wiederholen
          0x0A  XNODATA - Lok ist nicht im Refresh-Buffer, daher kein Status verfügbar
          0x0B  XNOSLOT - Kein PLatz in der Refresh-Queue (Slot)
          0x0D  XLKBUSY - Lok wird bereits von einer anderen Unit kontrolliert
          0x0E  XBADTNP - Die Weichenadresse ist für das eingestellte Protokoll ungültig
          0x41  XLKHALT - Lok-Kommando akzeptiert, aber nicht ausgeführt, da Steuerung
                          im HALT-Modus ist. Die Geschwindigkeit wird auf 0 gesetzt.
          0x42  XLKPOFF - Lok-Kommando akzeptiert, aber nicht ausgeführt, da Steuerung
                          im STOP-Modus ist. Die Geschwindigkeit wird auf 0 gesetzt.
     */
    public boolean checkMCAnswerByte( Container cont, byte[] bArr, Boolean show ) {
        if( bArr == null ) {
            if( show ) mbGeneric( cont, "checkMCAnswerByte", "invalid byteArray");
            return false;
        }
        int len = bArr.length;
        if( len == 0 ) {
            if( show ) mbGeneric( cont, "checkMCAnswerByte", "byteArray is empty");
            return false;
        }
        if( len > 1 ) {
            if( show ) mbGeneric( cont, "checkMCAnswerByte", "byteArray is too long (more than one byte)");
            // was ist das denn ?
        }
        String errCode;
        errCode = bundle.getString("Errorcode");
        if( debugLevel > 0 ) {
            System.out.println("checkMCAnswerByte: len="+len+" bArr[0]="+String.format("%02X ", bArr[0]));
        }
        switch( bArr[0] ) {
            case 0x00 : // OK
                return true;
            case 0x02 :
                if( show ) mbGeneric( cont, "checkMCAnswerByte", errCode+" XBADPRM");
                break;
            case 0x06 :
                if( show ) mbGeneric( cont, "checkMCAnswerByte", errCode+" XPWOFF");
                break;
            case 0x07 :
                if( show ) mbGeneric( cont, "checkMCAnswerByte", errCode+" XNOTSPC");
                break;
            case 0x08 :
                if( show ) mbGeneric( cont, "checkMCAnswerByte", errCode+" XNOLSPC");
                break;
            case 0x0A :
                if( show ) mbGeneric( cont, "checkMCAnswerByte", errCode+" XNODATA");
                break;
            case 0x0B :
                if( show ) mbGeneric( cont, "checkMCAnswerByte", errCode+" XNOSLOT");
                break;
            case 0x0D :
                if( show ) mbGeneric( cont, "checkMCAnswerByte", errCode+" XLKBUSY");
                break;
            case 0x0E :
                if( show ) mbGeneric( cont, "checkMCAnswerByte", errCode+" XBADTNP");
                break;
            case 0x41 :
                if( show ) mbGeneric( cont, "checkMCAnswerByte", errCode+" XLKHALT");
                break;
            case 0x42 :
                if( show ) mbGeneric( cont, "checkMCAnswerByte", errCode+" XLKPOFF");
                //        "Lok-Kommando akzeptiert, aber nicht ausgeführt, da Steuerung im STOP-Modus ist.\nDie Geschwindigkeit wird auf 0 gesetzt." );
                break;
            default:
                if( show ) mbGeneric( cont, "checkMCAnswerByte", errCode, String.format(" %02X (unknown)", bArr[0]));
        }
        return false;
    }
    /**
     *
     * @param bArray array of bytes (answer from command station)
     * @return true if answer starts with "Ok"
     */
    public boolean checkAnswerOK( byte[] bArray ) {
        if( bArray == null ) {
            return false;
        }
        // convert byteArray into a String
        String str_bArr = new String(bArray);
        // Split string into parts
        String[] strParts = str_bArr.split(" = |\r|\\000");
        if( debugLevel >= 2 ) {
            for( int i = 0 ; i < strParts.length ; i++ ) {
                System.out.println("checkPTRDAnswer 1857 strParts["+i+"]("+strParts[i].length()+")=\""+strParts[i]+"\""+((strParts[i].startsWith("]"))?" (ENDE)":"" ));
            }
        }
        if( strParts.length > 0) {
            if( strParts[0].equalsIgnoreCase("Ok") ) {
                if( debugLevel >= 2 ) {
                    System.out.println("checkPTWDAnswer 1232 strParts[0]=["+strParts[0]+"]" );
                }
                return true;
            }
        }
        return false;
    }
    /**
     *
     * @param bArray array of bytes (answer from command station)
     * @return true if answer starts with "No ack"
     */
    public boolean checkAnswerNoAck( byte[] bArray ) {
        if( bArray == null ) {
            return false;
        }
        // convert byteArray into a String
        String str_bArr = new String(bArray);
        // Split string into parts
        String[] strParts = str_bArr.split(" = |\r|\\000");
        if( debugLevel >= 3 ) {
            dumpbArray( strParts );
        }
        if( strParts.length > 0) {
            if( strParts[0].equalsIgnoreCase("No ack") ) {
                if( debugLevel >= 2 ) {
                    System.out.println("checkPTWDAnswer 1258 strParts[0]=["+strParts[0]+"]");
                }
                return true;
            }
        }
        return false;
    }

    public TwoWaySerialComm safelyOpenCom( Container cont, TwoWaySerialComm Com ) {
        return( safelyOpenCom( cont, Com, true));
    }
    public TwoWaySerialComm safelyOpenCom( Container cont, TwoWaySerialComm Com, Boolean showMbox  ) {
        if( debugLevel > 0 ) {
            System.out.println("safelyOpenCom: Com "+ Com==null?"=":"!" +"= null");
        }
        if( Com != null ) {
            // valid Com -> check status connected
            try {
                if( Com.isconnected() ) {
                    // already connected -> all OK
                    return Com;
                }
            } catch (Exception | Error ex) {
                // problem COM is invalid
            }
            // problem COM is not connected
            Com = safelyCloseCom( cont, Com, showMbox );
        }

        if( Com == null ) {
            try {
                Com = new TwoWaySerialComm();
                Com.connect(this);
                if( ! Com.isconnected() ) {
                    if( showMbox ) {
                        mbDeviceConnectProblem( cont );
                    }
                    Com = safelyCloseCom( cont, Com, showMbox );
                    return Com;
                }
            } catch (Exception | Error ex) {
                if( showMbox ) {
                    mbDeviceConnectProblem( cont );
                }
                Com = null;
            }
        }
        return Com;
    }

    public TwoWaySerialComm safelyCloseCom( Container cont, TwoWaySerialComm Com ) {
        return( safelyCloseCom( cont, Com, true));
    }
    public TwoWaySerialComm safelyCloseCom( Container cont, TwoWaySerialComm Com, Boolean showMbox ) {
        if( debugLevel > 0 ) {
            System.out.println("safelyCloseCom: Com "+ Com==null?"=":"!" +"= null" );
            System.out.println("cont name="+cont.getName() +" class="+cont.getClass() );
        }
        if( Com != null ) {
            try {
                if( Com.isconnected() ) {
                    Com.close();
                }
            } catch (Exception | Error ex) {
                if( showMbox ) {
                    mbDeviceDisconnectProblem( cont );
                }
            }
            Com = null;
        }
        return Com;
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
        CVNaviMainPanel = new javax.swing.JPanel();
        jButtonEnd = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jDecoderChooser = new javax.swing.JList();
        jLabelProgName = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButtonStart = new javax.swing.JButton();
        jButtonOptions = new javax.swing.JButton();
        jLabelProgVersion = new javax.swing.JLabel();
        jButtonInfo = new javax.swing.JButton();
        jZentraleTitle = new javax.swing.JTextField();
        jZentrale = new javax.swing.JTextField();
        jSchnittstelleTitle = new javax.swing.JTextField();
        jSchnittstelle = new javax.swing.JTextField();
        jBaudrateTitle = new javax.swing.JTextField();
        jBaudRate = new javax.swing.JTextField();
        jLabelBuild = new javax.swing.JLabel();
        jLabelOS = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("my/CVNavi/Bundle"); // NOI18N
        setTitle(bundle.getString("CVNavi.title")); // NOI18N
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        CVNaviMainPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, bundle.getString("CVNavi.CVNaviMainPanel.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N
        CVNaviMainPanel.setName("CV-Navi"); // NOI18N

        jButtonEnd.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButtonEnd.setText(bundle.getString("CVNavi.jButtonEnd.text")); // NOI18N
        jButtonEnd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEndActionPerformed(evt);
            }
        });

        jDecoderChooser.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jDecoderChooser.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Lok-Decoder", ".   LD-G-30", ".   LD-G-31", ".   LD-W-32", ".   LD-G-32", ".   LD-W-33", ".   LD-G-33", ".   LD-G-34", ".   LD-G-31Plus", ".   LD-G-33Plus", ".   LD-G-34Plus", ".   LD-G-36Plus", "____________", "Funktions-Decoder", ".   FD-R basic", ".   FD-R extended", ".   FD-M", ".   FD-XL", "____________", "Zubehör-Decoder", ".   WD-34", ".   SD-34", "____________", "Sonstiges", ".   MasterControl", ".   B-4", ".   WIB-30er" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jDecoderChooser.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jDecoderChooser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jDecoderChooserMouseReleased(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jDecoderChooserMouseClicked(evt);
            }
        });
        jDecoderChooser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jDecoderChooserKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jDecoderChooser);

        jLabelProgName.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabelProgName.setText(bundle.getString("CVNavi.jLabelProgName.text")); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 0));
        jLabel2.setText(bundle.getString("CVNavi.jLabel2.text")); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel3.setText(bundle.getString("CVNavi.jLabel3.text")); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel4.setText(bundle.getString("CVNavi.jLabel4.text")); // NOI18N
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel4.setIconTextGap(0);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 0, 0));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText(bundle.getString("CVNavi.jLabel5.text")); // NOI18N
        jLabel5.setIconTextGap(0);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText(bundle.getString("CVNavi.jLabel6.text")); // NOI18N
        jLabel6.setIconTextGap(0);

        jButtonStart.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButtonStart.setText(bundle.getString("CVNavi.jButtonStart.text")); // NOI18N
        jButtonStart.setToolTipText(bundle.getString("CVNavi.jButtonStart.toolTipText")); // NOI18N
        jButtonStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonStartActionPerformed(evt);
            }
        });

        jButtonOptions.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButtonOptions.setText(bundle.getString("CVNavi.jButtonOptions.text")); // NOI18N
        jButtonOptions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOptionsActionPerformed(evt);
            }
        });

        jLabelProgVersion.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabelProgVersion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelProgVersion.setText(bundle.getString("CVNavi.jLabelProgVersion.text")); // NOI18N
        jLabelProgVersion.setIconTextGap(0);

        jButtonInfo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButtonInfo.setText(bundle.getString("CVNavi.jButtonInfo.text")); // NOI18N
        jButtonInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInfoActionPerformed(evt);
            }
        });

        jZentraleTitle.setEditable(false);
        jZentraleTitle.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jZentraleTitle.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jZentraleTitle.setText(bundle.getString("CVNavi.jZentraleTitle.text")); // NOI18N
        jZentraleTitle.setToolTipText(bundle.getString("CVNavi.jZentraleTitle.toolTipText")); // NOI18N
        jZentraleTitle.setBorder(null);

        jZentrale.setEditable(false);
        jZentrale.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jZentrale.setText(bundle.getString("CVNavi.jZentrale.text")); // NOI18N

        jSchnittstelleTitle.setEditable(false);
        jSchnittstelleTitle.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jSchnittstelleTitle.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jSchnittstelleTitle.setText(bundle.getString("CVNavi.jSchnittstelleTitle.text")); // NOI18N
        jSchnittstelleTitle.setToolTipText(bundle.getString("CVNavi.jSchnittstelleTitle.toolTipText")); // NOI18N
        jSchnittstelleTitle.setBorder(null);

        jSchnittstelle.setEditable(false);
        jSchnittstelle.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jSchnittstelle.setText(bundle.getString("CVNavi.jSchnittstelle.text")); // NOI18N

        jBaudrateTitle.setEditable(false);
        jBaudrateTitle.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jBaudrateTitle.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jBaudrateTitle.setText(bundle.getString("CVNavi.jBaudrateTitle.text")); // NOI18N
        jBaudrateTitle.setToolTipText(bundle.getString("CVNavi.jBaudrateTitle.toolTipText")); // NOI18N
        jBaudrateTitle.setBorder(null);

        jBaudRate.setEditable(false);
        jBaudRate.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jBaudRate.setText(bundle.getString("CVNavi.jBaudRate.text")); // NOI18N
        jBaudRate.setToolTipText(bundle.getString("CVNavi.jBaudRate.toolTipText")); // NOI18N

        jLabelBuild.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelBuild.setText("build");
        jLabelBuild.setIconTextGap(0);

        jLabelOS.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelOS.setText("os info");
        jLabelOS.setIconTextGap(0);

        javax.swing.GroupLayout CVNaviMainPanelLayout = new javax.swing.GroupLayout(CVNaviMainPanel);
        CVNaviMainPanel.setLayout(CVNaviMainPanelLayout);
        CVNaviMainPanelLayout.setHorizontalGroup(
            CVNaviMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CVNaviMainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CVNaviMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CVNaviMainPanelLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel5)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonEnd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(CVNaviMainPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(CVNaviMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(CVNaviMainPanelLayout.createSequentialGroup()
                                .addComponent(jLabelProgName)
                                .addGap(0, 0, 0)
                                .addComponent(jLabel2)
                                .addGap(0, 0, 0)
                                .addComponent(jLabel3))
                            .addComponent(jButtonStart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonOptions, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jBaudrateTitle, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jBaudRate, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSchnittstelle, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSchnittstelleTitle)
                            .addComponent(jZentrale, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jZentraleTitle, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(CVNaviMainPanelLayout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addGroup(CVNaviMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabelBuild, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabelProgVersion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabelOS, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))))
        );
        CVNaviMainPanelLayout.setVerticalGroup(
            CVNaviMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CVNaviMainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CVNaviMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(CVNaviMainPanelLayout.createSequentialGroup()
                        .addGroup(CVNaviMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelProgName)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelProgVersion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelOS, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelBuild, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 180, Short.MAX_VALUE)
                        .addComponent(jButtonStart, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(jButtonOptions)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jZentraleTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jZentrale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSchnittstelleTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSchnittstelle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(jBaudrateTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBaudRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addGap(18, 18, 18)
                .addGroup(CVNaviMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jButtonInfo)
                    .addComponent(jButtonEnd))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(CVNaviMainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(CVNaviMainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        CVNaviMainPanel.getAccessibleContext().setAccessibleName(bundle.getString("CVNavi.CVNaviMainPanel.AccessibleContext.accessibleName")); // NOI18N

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonEndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEndActionPerformed
        stopIOAction();

        mainWindowLocation = getLocationOnScreen();
        System.out.println("jButtonEndActionPerformed mainWindowLocation="+mainWindowLocation);


        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream(gsConfigFilename));
        } catch (FileNotFoundException ex) {
            // Die XML-Config-Datei wird bei Bedarf beim Start des Programms in main() angelegt
            // deshalb hier keine weitere Behandlung
            Logger.getLogger(CVNavi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
          Logger.getLogger(CVNavi.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            if ( -1 != jDecoderChooser.getSelectedIndex() ) {
                gsDecoderIndex = jDecoderChooser.getSelectedIndex();
            }
            prop.setProperty("DecoderIndex", Integer.toString(gsDecoderIndex));
            if( bSpracheDE ) {
                prop.setProperty("Language", "german") ;
            } else {
                prop.setProperty("Language", "english") ;
            }
            prop.setProperty("SaveOpenDirectory", gsSaveOpenDirectory);
            prop.setProperty("SaveOpenFilename",  gsSaveOpenFilename);
            prop.setProperty("SaveOpenKennDirectory", gsSaveOpenKennDirectory);
            prop.setProperty("SaveOpenKennFilename",  gsSaveOpenKennFilename);
            prop.setProperty("SaveOpenMcDirectory", gsSaveOpenMcDirectory);
            prop.setProperty("SaveOpenMcFilename",  gsSaveOpenMcFilename);
            prop.setProperty("SaveOpenM3Directory", gsSaveOpenM3Directory);
            prop.setProperty("SaveOpenM3Filename",  gsSaveOpenM3Filename);
            prop.setProperty("OpenFirmwareDirectory", gsOpenFirmwareDirectory);
            prop.setProperty("OpenFirmwareFilename",  gsOpenFirmwareFilename);
            prop.setProperty("WindowLocationX",  ""+mainWindowLocation.x);
            prop.setProperty("WindowLocationY",  ""+mainWindowLocation.y);
            if( ! rs232_mode_was_forced ) {
                // only store if it was not forced on command line
                if( rs232_or_rb_usb_2 ) {
                    prop.setProperty("DeviceForFwUpdate",  "usb2");
                } else {
                    prop.setProperty("DeviceForFwUpdate",  "usb1");
                }
            }
            prop.storeToXML(new FileOutputStream(gsConfigFilename), gsConfigComment);
        } catch (IOException ex) {
            Logger.getLogger(CVNavi.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.exit(0); 
    }//GEN-LAST:event_jButtonEndActionPerformed

    public void fillMenuSelection() {
        int prevIdx = this.getDecoderChooser();
        if( debugLevel > 0 ) {
            System.out.println("fillMenuSelection prevIdx="+prevIdx);
        }

        int coSize = decoderList.values().length;
        final String[] myStrings = new String[coSize];
        for(decoderList co : decoderList.values()) {
            // System.out.printf("decoderList[%2d] [%2d] (%s) Const %10s Text %-35s %s\n",co.ordinal(), co.getIdx(), co.isSelectable()?"X":" ", co, co.getMenutextGer(), co.getMenutextEng());
            myStrings[co.getIdx()] = bSpracheDE?co.getMenutextGer():co.getMenutextEng();
        }


        if( bSpracheDE ) {
            jButtonOptions.setText("Optionen");
            jButtonEnd.setText("Beenden");
            jZentraleTitle.setText("Zentrale:");
            jSchnittstelleTitle.setText("Schnittstelle:");
            jBaudrateTitle.setText("Baudrate (seriell):");
        }
        else {
            jButtonOptions.setText("Options");
            jButtonEnd.setText("End");
            jZentraleTitle.setText("control unit:");
            jSchnittstelleTitle.setText("interface:");
            jBaudrateTitle.setText("baud rate (serial):");
        }

        if( ( fwVersion != null ) && ( fwVersion.length() > 0 ) ) {
            jZentrale.setText(gsZentrale+" ( "+fwVersion+ " )");
        } else {
            jZentrale.setText(gsZentrale);
        }
        jSchnittstelle.setText(gsSchnittstelle);
        jBaudRate.setText(Integer.toString(gsBaudRate));

        // TimerSettings 1. defaults
        MCtimer1 = 1;
        MCtimer2 = 1;
        timer1 = 2000;
        timer2 = 500;
        timer3 = -1;
        // timer3 is optional and may stay < 0
        switch( lcZentrale ) {
            case c.cuIntellibox1:
                timerRetries = 20;
                break;
            case c.cuMasterControl:
                timerRetries = 9;
                break;
            case c.cuOpenDCC:
                timerRetries = 9;
                break;
            default:
                timerRetries = 20;
        }
        // if overridden by command-line, use user's values
        if( userTimer1 > 0 ){
            MCtimer1 = userTimer1;
            timer1 = userTimer1;
        }
        if( userTimer2 > 0 ){
            MCtimer2 = userTimer2;
            timer2 = userTimer2;
        }
        if( userTimer3 > 0 )
            timer3 = userTimer3;
        if( userTimerRetries >= 0 )
            timerRetries = userTimerRetries;
        if( debugLevel > 0 ) {
            System.out.println("timerSettings MCtimer1="+MCtimer1+" MCtimer2="+MCtimer2+" timer1="+timer1+" timer2="+timer2+" timer3="+timer3+" timerRetries="+timerRetries);
        }

        jDecoderChooser.setModel(new javax.swing.AbstractListModel() {
            public int getSize() { return myStrings.length; }
            public Object getElementAt(int i) { return myStrings[i]; }
        });
        if( debugLevel > 0 ) {
            System.out.println("fillMenuSelection prevIdx("+prevIdx+")");
        }
        this.setDecoderChooser(prevIdx);
    }

    private void jButtonStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonStartActionPerformed
        currSelection = jDecoderChooser.getSelectedIndex();
        if( debugLevel > 0 ) {
            System.out.println("jButtonStartActionPerformed currSelection="+currSelection );
        }

        if( this.frameInstanceDEVICE != null ) {
            this.frameInstanceDEVICE.toFront();
            this.frameInstanceDEVICE.repaint();

            if( debugLevel > 0 ) {
                System.out.println("jButtonStartActionPerformed: there is already an instance running for Decoder="+Decoder );
            }
            return;
        }
        Decoder = currSelection;
        switch( Decoder )
        {
            case c.LD_G30: // LD-G-30
            case c.LD_W32: // LD-W-32
            case c.LD_G32: // LD-G-32
            case c.LD_W32_2: // LD-W-32.2
            case c.LD_G32_2: // LD-G-32.2
                LDG30 lDG30 = new LDG30(this);
                break;

            case c.LD_G31: // LD-G-31
                LDG31 lDG31 = new LDG31(this);
                break;

            case c.LD_W33: // LD-W-33
            case c.LD_G33: // LD-G-33
            case c.LD_G34: // LD-G-34
            case c.FD_XL:  // FD-XL
                LDG33 lDG33 = new LDG33(this);
                break;

            case c.LD_G31Plus: // LD-G-31Plus
            case c.LD_G33Plus: // LD-G-33Plus
            case c.LD_G34Plus: // LD-G-33Plus
            case c.LD_G36Plus: // LD-G-33Plus
                LDG30erPlus lDG33Plus = new LDG30erPlus(this);
                break;

            case c.FD_R: // FD-R
            case c.FD_R2: // FD-R-basic2
                FDR fDR = new FDR(this);
                break;

            case c.FD_R_ex: // FD-R extended
                FD_R_Extended fDRex = new FD_R_Extended(this);
                break;

            case c.FD_M: // FD-M
                FD_M fD_M = new FD_M(this);
                break;

            case c.FD_LED: //FD-LED
                FD_LED fD_LED = new FD_LED(this);
                break;
/*
            case c.SD_22: // SD-22
                break;
*/
            case c.WD_34: // WD-34
            case c.SD_34: // SD-34
            case c.WD_34_2: // WD-34
            case c.SD_34_2: // SD-34
                WD34 wD34 = new WD34(this);
                break;

            case c.MultiDecoder: // MasterControl/RedBox
                MultiDec MD = new MultiDec(this);
                break;

            case c.MC: // MasterControl/RedBox
                MC mC = new MC(this);
                break;

            case c.B_4: // B-4
                B_4 b_4 = new B_4(this);
                break;

            case c.BiDi_B: // BiDi-Booster
                BiDi_B BiDi_B = new BiDi_B(this);
                break;

            case c.WIB_30: // WIB-30er
                WIB_30 wib_30 = new WIB_30(this);
                break;

            default:
                mbDeviceNotSupported( this, 5 );
        }
    }//GEN-LAST:event_jButtonStartActionPerformed

    private void jButtonOptionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOptionsActionPerformed
        Options options = new Options(this);
    }//GEN-LAST:event_jButtonOptionsActionPerformed

    enum KTUImenuEvt {
        VK_UP, VK_DOWN
    }

    private void KTUImenuEvalKey(KTUImenuEvt key) {
        Boolean checkAgain = true;
        decoderList[] DLval = decoderList.values();
        while( checkAgain ) {
            checkAgain = false;
            int n = jDecoderChooser.getSelectedIndex();

            if( DLval[n].isSelectable() ) {
                // entry is selectable -> OK
                return;
            }
            if( key == KTUImenuEvt.VK_DOWN) {
                if( n < ( DLval.length - 1 )) {
                    jDecoderChooser.setSelectedIndex(n+1);
                    checkAgain = true;
                }
                if( n == ( DLval.length - 1 )) {
                    // UUps, das Ende der Liste ist nicht selectable !
                    // wir muessen aufwaerts bis wieder ein selectable kommt
                    while( --n > 0 ) {
                        if( DLval[n].isSelectable() ) {
                            jDecoderChooser.setSelectedIndex(n);
                            return;
                        }
                    }
                }
            }
            if( key == KTUImenuEvt.VK_UP) {
                if( n > 0 ) {
                    jDecoderChooser.setSelectedIndex(n-1);
                    checkAgain = true;
                }
                if( n == 0 ) {
                    // 1st entry is not selectable
                    // change to first selectable choice
                    while( ++n < ( DLval.length - 1 ) ) {
                        if( DLval[n].isSelectable() ) {
                            jDecoderChooser.setSelectedIndex(n);
                            return;
                        }
                    }
                }
            }
        }
    }

    private void jDecoderChooserMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDecoderChooserMouseReleased
        // mouse event is treated like VK_DOWN
        KTUImenuEvalKey(KTUImenuEvt.VK_DOWN);
    }//GEN-LAST:event_jDecoderChooserMouseReleased

    private void jDecoderChooserKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDecoderChooserKeyReleased
        if(evt.getKeyCode()== KeyEvent.VK_DOWN) {
            KTUImenuEvalKey(KTUImenuEvt.VK_DOWN);
        }
        if(evt.getKeyCode()== KeyEvent.VK_UP) {
            KTUImenuEvalKey(KTUImenuEvt.VK_UP);
        }
    }//GEN-LAST:event_jDecoderChooserKeyReleased

    private void jDecoderChooserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDecoderChooserMouseClicked
        if(evt.getClickCount() == 2)
        {
            ActionEvent evnt = null;
            this.jButtonStartActionPerformed(evnt);
        }
    }//GEN-LAST:event_jDecoderChooserMouseClicked

    private void jButtonInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInfoActionPerformed
        jInfo jI = new jInfo( this );
}//GEN-LAST:event_jButtonInfoActionPerformed

    public void setFocus() {
        jButtonStart.grabFocus();
        this.toFront();
        this.repaint();
    }

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

        Properties prop = new Properties();
        try {
            prop.loadFromXML(new FileInputStream(gsConfigFilename));
        } catch (FileNotFoundException ex) {
            // Logger.getLogger(CVNavi.class.getName()).log(Level.INFO, null, ex);
            try {
                    File f = new File(gsConfigDirectory);
                    f.mkdir();
                    prop.storeToXML(new FileOutputStream(gsConfigFilename), gsConfigComment);
            } catch (IOException ex1) {
                Logger.getLogger(CVNavi.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (IOException ex) {
            Logger.getLogger(CVNavi.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Werte auslesen bzw. mit Defaults initialisieren
        setZentrale(prop.getProperty("Zentrale", "MasterControl"));
        gsSchnittstelle = prop.getProperty("Schnittstelle", "COM1");
        gsBaudRate = Integer.parseInt( prop.getProperty("BaudRate","9600"));
        gsDecoderIndex = Integer.parseInt( prop.getProperty("DecoderIndex", Integer.toString(c.MC)));
        if(bundle.getString("Sprache").equals("Deutsch"))
        {
            bSpracheDE = true;
        }
        else
        {
            bSpracheDE = false;
        }
        gsLookAndFeel = prop.getProperty("LookAndFeel","Metal");
        gsSaveOpenDirectory = prop.getProperty("SaveOpenDirectory",System.getProperty("user.home"));
        gsSaveOpenFilename  = prop.getProperty("SaveOpenFilename"," ");
        gsSaveOpenKennDirectory = prop.getProperty("SaveOpenKennDirectory",System.getProperty("user.home"));
        gsSaveOpenKennFilename  = prop.getProperty("SaveOpenKennFilename"," ");
        gsSaveOpenMcDirectory = prop.getProperty("SaveOpenMcDirectory",System.getProperty("user.home"));
        gsSaveOpenMcFilename  = prop.getProperty("SaveOpenMcFilename"," ");
        gsSaveOpenM3Directory = prop.getProperty("SaveOpenM3Directory",System.getProperty("user.home"));
        gsSaveOpenM3Filename  = prop.getProperty("SaveOpenM3Filename"," ");
        gsOpenFirmwareDirectory = prop.getProperty("OpenFirmwareDirectory",System.getProperty("user.home"));
        gsOpenFirmwareFilename  = prop.getProperty("OpenFirmwareFilename"," ");
        if( ! rs232_mode_was_forced ) {
            // nur nach Änderung durch Benutzer zurückschreiben (Umstellung per Aufrufparameter ignorieren)
            rs232_or_rb_usb_2 = prop.getProperty("DeviceForFwUpdate","usb1").contentEquals("usb2");
        }

        int x = Integer.parseInt( prop.getProperty("WindowLocationX", "-1" ) );
        int y = Integer.parseInt( prop.getProperty("WindowLocationY", "-1" ) );
        mainWindowLocation.setLocation(x, y);
        if( (mainWindowLocation.x >= 0 ) && (mainWindowLocation.y >= 0 ) ) {
            setLocation(mainWindowLocation);
        }

        fillMenuSelection();
        jDecoderChooser.setSelectedIndex(gsDecoderIndex);
        jButtonStart.setSelected(true);
        jButtonStart.grabFocus();

        SODlocalSize = new Dimension();

        String osName = System.getProperty("os.name");
        String osArch = System.getProperty("os.arch");
        String dataModel = System.getProperty("sun.arch.data.model");
        String javaversion = System.getProperty("java.version");
        String osInfo = "("+osName+"["+osArch+"] , java "+javaversion+"["+dataModel+"bit])";
        jLabelOS.setText(osInfo);

        String gsBuild ="beta 20180531b"; // use keyword "beta" or "release"
        System.out.println("Build: "+gsBuild);
        if( debugLevel > 0 || gsBuild.contains("beta") ) {
            jLabelBuild.setText(gsBuild);
        }
        else {
            jLabelBuild.setText("");
        }

        // store pointer to instance in a final variable -> useable inside ActionListener
        final CVNavi outerThis = this;

        final ActionListener actionListener = new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if( debugLevel > 0 ) {
                    System.out.println( "actionPerformed" );
                }
                byte[] bArray = new byte[0xFFFF];

                if( bReadStatusBin ) {
                    if( debugLevel > 0 ) {
                        System.out.println( "actionPerformed bReadStatusBin " );
                    }
                    bReadStatusBin = false;
                    stopIOAction();
                    int tmpBytesRead = ExternalCom.read(bArray); // Gbts was Neues ?
                    ExternalCom = null;
                    if( debugLevel > 0 ) {
                        System.out.println( "actionPerformed bReadStatusBin tmpBytesRead="+tmpBytesRead );
                    }
                    if( tmpBytesRead > 0 ) {
                        dumpbArrayBIN(bArray, tmpBytesRead);
                        if( (bArray[0]&0x08)==0x08) {
                            trackStatus = c.cuPowerOn;
                        } else {
                            trackStatus = c.cuPowerOff;
                        }
                    } else {
                        trackStatus = -1 ;
                    }
                    distributeTrackStatus();
                }

                if( bVerifyZentraleInProgress ) {
                    bVerifyZentraleInProgress = false;
                    stopIOAction();

                    int nBytes = KTUICom.read(bArray);
                    KTUICom = safelyCloseCom( outerThis, KTUICom );

                    if( debugDummyData == 1 ) {
                        // DUMMY - Werte zum DEBUGGEN : Tams MC
                        bArray[0] = 0x04;
                        bArray[1] = 0x01;
                        bArray[2] = 0x04;
                        bArray[3] = 0x07;
                        bArray[4] = 'b';
                        bArray[5] = 0x04;
                        bArray[6] = 0x00;
                        bArray[7] = 0x13;
                        bArray[8] = 0x07;
                        bArray[9] = 0x56;
                        bArray[10] = 0x00;
                        nBytes = 11;
                    }

                    if( debugDummyData == 2 ) {
                        // DUMMY - Werte zum DEBUGGEN : OpenDCC neu
                        bArray[0] = 0x02;
                        bArray[1] = 0x17;
                        bArray[2] = 0x08;
                        bArray[3] = 0x01;
                        bArray[4] = 0x2a;
                        bArray[5] = 0x00;
                        nBytes = 6;
                    }

                    if( debugDummyData == 3 ) {
                        // DUMMY - Werte zum DEBUGGEN : IB
                        /*
                        For example, the IB replies with:
                        02h, <SPU version low>, <SPU version high>,
                        02h, <KPU version low>, <KPU version high>,
                        01h, <PPU version>, 
                        01h, <LIPU version>,
                        01h, <DNG version>,
                        05h, <IB serial number: 5 bytes (digits 98, 76, 54, 32, 10)>,
                        00h
                        A single byte version # is to be interpreted as: H.L
                        For example: 10h -> version 1.0
                        A two byte version # (low/high) is to be interpreted as: H.HLL
                        For example: 23h, 10h -> version 1.023
                        (the version numbers and the serial number are sent in BCD
                        notation - Binary Coded Decimal).
                        The serial number is to be interpreted as: '9876543210' - i.e.
                        digit '9' is the most significant digit, etc...
                        SPU = System Processing Unit (the IB 'heart')
                        KPU = Keypad Processing Unit (user interface)
                        PPU = Peripheral Processing Unit (digital signal generator)
                        LIPU = Lokmaus/I2C Processing Unit
                        DNG = Dispositivo di Nostra Gestione (sorry this is italian language!)
                        */
                        bArray[0] = 0x02;
                        bArray[1] = (byte) 0x92;
                        bArray[2] = 0x20;
                        bArray[3] = 0x01;
                        bArray[4] = 0x02;
                        bArray[5] = 0x01;
                        bArray[6] = 0x03;
                        bArray[7] = 0x01;
                        bArray[8] = 0x04;
                        bArray[9] = 0x01;
                        bArray[10] = 0x05;
                        bArray[11] = 0x01;
                        bArray[12] = 0x06;
                        bArray[13] = 0x00;
                        nBytes = 14;
                    }
                    if( debugDummyData == 4 ) {
                        // DUMMY - Werte zum DEBUGGEN : IB
                        bArray[0] = 0x02;
                        bArray[1] = 0x23;
                        bArray[2] = 0x10;
                        bArray[3] = 0x01;
                        bArray[4] = 0x02;
                        bArray[5] = 0x01;
                        bArray[6] = 0x03;
                        bArray[7] = 0x01;
                        bArray[8] = 0x04;
                        bArray[9] = 0x01;
                        bArray[10] = 0x05;
                        bArray[11] = 0x01;
                        bArray[12] = 0x06;
                        bArray[13] = 0x00;
                        nBytes = 14;
                    }

                    if(nBytes < 1)
                    {
                        mbTimeout( outerThis, c.mbRDverify );
                    } else {
                        if( debugLevel > 0 ) {
                            System.out.println("Xver: finished bArraySize="+nBytes);
                        }
                        byte[] mYbArray = new byte[nBytes];
                        System.arraycopy(bArray, 0, mYbArray, 0, nBytes);
                        Boolean ok = verifyXVer( mYbArray );
                        bZentraleVerified = true;
                        fillMenuSelection();
                        if( ok && bGotoUpdate ) {
                            if( debugLevel > 0 ) {
                                System.out.println("bVerifyZentraleInProgress jDecoderChooser.setSelectedIndex="+c.MC );
                            }
                            jDecoderChooser.setSelectedIndex(c.MC);
                            jButtonStartActionPerformed( null );
                        }
                    }
                    return;
                }
            }
        };
        timer = new javax.swing.Timer(5000, actionListener);
        timer.setRepeats(false);
        actionListener.actionPerformed(null);

        verifyZentrale(false);
    }//GEN-LAST:event_formWindowOpened

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // leave through OS closing the app -> simulate exit-button event
        jButtonEndActionPerformed( null );
    }//GEN-LAST:event_formWindowClosing

    private Boolean verifyXVer( byte[] bIn ) {
        int lenIn = bIn.length;
        int numAnswers = 0;
        int idx = 0;
        String sSwVersion = null;
        long lSwVersion = 0;
        char b = ' ';
        char bNum = ' ';

        /*
         * Version Info:
         * IB      6 answers first is 2 Bytes (BCD)
         * OpenDCC 1 answer           1 Bytes (BYTE) (until V0.14, only version)
         * OpenDCC 1 answer           2 Bytes (BYTE) (since V0.15, version+serno combined)
         * OpenDCC 2 answers first is 2 Bytes (BYTE) (since V0.23.8release)
         * Tams    2 answers first is 3 Bytes (BCD) or 4 Bytes (3*BCD + ASCII)
         * MoPi    2 answer           3 Bytes (BYTE) (stores version: major, minor, build)
         */
        /* First in byte tells how much bytes are in next answer. */
        if( debugLevel > 0 ) {
            System.out.println("bIn("+lenIn+")=0x"+byteArrayToHex(bIn));
        }

        if( lenIn <= 0 ) {
            if( debugLevel > 0 ) {
                System.out.println("verifyXVer bIn is empty lenIn="+lenIn);
            }
            return false;
        }
        int lenAntwort = (int) bIn[idx];
        idx++;
        if( debugLevel > 0 ) {
            System.out.println("Antwort["+numAnswers+"] ist "+lenAntwort+" Bytes lang");
        }
        if( lenAntwort > lenIn ) {
            System.out.println("May be a TamsMC waiting for a software update...");
            // meist zwischen 8 und 12 Bytes
            if( lenIn >= 5 ) {
                // 0x4C235423542354235423 T#L#T#T#T# // 10 MasterControl
                // 0x4C235423542354235423 L#T#T#T#T# T#L#T#T#T#T# // MC als HC  T#T#T#T#T#T#
                // 0x54234323542354235423 C#T#T#T#T# T#C#T#T#T# // 10 HandControl / PhoneControl 
                // 0x543F4C3F543F543F543F L?T?T?T?T? // 10 XN-Control / mControl T?C?T?T? C?T?T?T? T?L?T?T?T?T? T?L?T?T?T?T?
                // 0x23 = 35 = '#' Block wiederholen (256 Bytes)
                // 0x3F = 63 = '?' Block wiederholen (128 Bytes)
                // 0x43 = 67 = 'C' CRC Error
                // 0x4C = 76 = 'L' Length Error
                // 0x54 = 84 = 'T' Time out Error
                String s = new String( bIn );
                String cs = s.substring(1, 5);
                if( debugLevel > 0 ) {
                    System.out.println("bIN as String="+s+" len="+s.length()+" cs="+cs );
                }
                // die ersten 4 Zeichen vergleichen
                switch( cs ) {
                    case "L#T#" :
                    case "T#L#" :
                    case "C#T#" :
                    case "T#C#" :
                    case "T#T#" :
                    case "#L#T" :
                    case "#T#L" :
                    case "#C#T" :
                    case "#T#C" :
                    case "#T#T" :
                        System.out.println("Yes a TamsMC with a 256 byte block device (MasterControl, HandControl, PhoneControl, ... ) waiting for a software update");
                        break;
                    case "L?T?" :
                    case "T?L?" :
                    case "C?T?" :
                    case "T?C?" :
                    case "T?T?" :
                    case "?L?T" :
                    case "?T?L" :
                    case "?C?T" :
                    case "?T?C" :
                    case "?T?T" :
                        System.out.println("Yes a TamsMC with a 128 byte block device (XNControl, mControl, ... ) waiting for a software update");
                        break;
                    default:
                        System.out.println("A TamsMC with an unknown device waiting for a software update");
                        // TODO Dialog ausgliedern und DE/EN 
                        mbGeneric(this, "A TamsMC with an unknown device waiting for a software update", "Please report devicename and the following line to Tams", s, 0, true);
                }
                bGotoUpdate = (getZentrale() == c.cuMasterControl);
                mbVerifyXVer( this, sSwVersion, getZentrale() != c.cuMasterControl );
                if( debugLevel > 0 ) {
                    System.out.println("bVerifyXVer bGotoUpdate="+bGotoUpdate );
                }
                return bGotoUpdate;
            }
            return false;
        }
        if( lenAntwort < 0 ) {
            System.out.println("Antwort["+numAnswers+"] : Länge ("+lenAntwort+") ist negativ -> Abbruch bVerifyXVer");
            return false;
        }
        numAnswers++;

        byte[] bVersion = new byte[lenAntwort];
        int lenVersion = lenAntwort;
        System.arraycopy(bIn, 1, bVersion, 0, lenAntwort);
        idx += lenAntwort;

        while( idx < lenIn ) {
            lenAntwort = (int) bIn[idx];
            if( debugLevel > 0 ) {
                System.out.println("2880 Antwort["+numAnswers+"] ist "+lenAntwort+" Bytes lang");
            }
            idx++;
            if( idx + lenAntwort <= lenIn ) {
                if( lenAntwort > 0 ) {
                    numAnswers++;
                } else {
                    if( debugLevel > 0 ) {
                        System.out.println("2888 ENDE Xver : idx["+idx+"] + lenAntwort["+lenAntwort+"] <= lenIn["+lenIn+"]");
                    }
                }
            } else {
                // Ooops
                if( debugLevel > 0 ) {
                    System.out.println("2894 Oooops : idx["+idx+"] + lenAntwort["+lenAntwort+"] <= lenIn["+lenIn+"]");
                }
            }
            idx += lenAntwort;
        }
        if( debugLevel > 0 ) {
            System.out.println("numAnswers="+numAnswers);
        }
        switch( lenVersion ) {
            case 1:
                fwVersion = "0."+(bVersion[0] & 0xFF)+ ".0";
                sSwVersion = "OpenDCC Version "+fwVersion;
                System.out.println("--- "+sSwVersion+" ---");
                mbGeneric( this, "Information", "Detected "+sSwVersion, "(Xfuncs="+bUseXfuncs+")", 5, false );
                break;
            case 2:
                switch( numAnswers ) {
                    case 1: // OpenDCC V0.15 until V0.23.8beta, (2 bytes version+serno)
                        fwVersion = "0."+(bVersion[0] & 0xFF)+".0";
                        sSwVersion = "OpenDCC Version "+fwVersion;
                        System.out.println("--- "+sSwVersion+" ---");
                        mbVerifyXVer( this, sSwVersion, getZentrale() != c.cuOpenDCC );
                        break;
                    case 2: // OpenDCC since V0.23.8 (2 bytes version + 1 byte serno)
                        lSwVersion = (long) ((bVersion[0] & 0xFF) << 8 ) + (long) (bVersion[1] & 0xFF);
                        if( lSwVersion >= 0x1708 ) {
                            bUseXfuncs = true;
                        }
                        fwVersion = "0."+ (bVersion[0] & 0xFF) +"."+ (bVersion[1] & 0xFF);
                        sSwVersion = "OpenDCC Version "+fwVersion;
                        System.out.println("--- "+sSwVersion+" ---");
                        mbVerifyXVer( this, sSwVersion, getZentrale() != c.cuOpenDCC );
                        break;
                    default: // IB should have 6 answers 
                        lSwVersion = (long) ((bVersion[1] & 0xFF) << 8 ) + (long) (bVersion[0] & 0xFF);
                        if( lSwVersion >= 0x2000 ) {
                            bUseXfuncs = true;
                        }
                        fwVersion = String.format("%01x.%01x%02x", (bVersion[1]&0xF0)>>4, bVersion[1]&0x0F, bVersion[0]&0xFF);
                        sSwVersion = "Intellibox Version "+fwVersion;
                        System.out.println("--- "+sSwVersion+" ---");
                        mbVerifyXVer( this, sSwVersion, getZentrale() != c.cuIntellibox1 );
                        break;
                }
                break;
            case 5: // Tams beta prerelease #
                bNum = (char) ( bVersion[4] & 0xFF );
            case 4: // Tams beta release
                b = (char) ( bVersion[3] & 0xFF );
                lSwVersion = (long) (bVersion[3] & 0xFF);
            case 3: // Tams official release
                lSwVersion += (long)((bVersion[0] & 0xFF) << 24 );
                lSwVersion += (long)((bVersion[1] & 0xFF) << 16 );
                lSwVersion += (long)((bVersion[2] & 0xFF) << 8 );

                bUseXfuncs = ( lSwVersion >= c.MIN_MC_XFUNCS_VERSION );
                bUseXm3sid = ( lSwVersion >= c.MIN_MC_XM3SID_VERSION );
                bUseSo999  = ( lSwVersion >= c.MIN_MC_SO999_VERSION  );
                fwVersion = ""
                        + (int) (bVersion[0] & 0xFF) +"."
                        + (int) (bVersion[1] & 0xFF) +"."
                        + (int) (bVersion[2] & 0xFF)
                        + b
                        + bNum;
                sSwVersion = "MasterControl Version "+fwVersion;
                System.out.println("--- "+sSwVersion+" ---");
                mbVerifyXVer( this, sSwVersion, getZentrale() != c.cuMasterControl );
                break;
            default:
                System.out.println("Unbekannte Zentrale ausgelesen: Daten["+lenIn+" Bytes] = 0x"+byteArrayToHex(bIn));
                return false;
        }

        if( lSwVersion > 0 ) {
            if( debugLevel > 0 ) {
                System.out.println("lSwVersion="+lSwVersion+" in HEX="+String.format("0x%16s", Long.toHexString(lSwVersion)).replace(' ', '0') );
                System.out.println("bUseXfuncs="+bUseXfuncs+" bUseXm3sid="+bUseXm3sid);
            }
        }

        return true;
    }

    /**
     *
     * @param force force verify of control unit (use after changed parameters)
     */
    public void verifyZentrale(Boolean force) {
        if( force )
            bZentraleVerified = false;
        if( bZentraleVerified )
            return;
        if( bVerifyZentraleInProgress )
            return;
        bUseXfuncs = false;
        bUseXm3sid = false;

        KTUICom = safelyOpenCom( this, KTUICom );
        if( (KTUICom != null) && KTUICom.isconnected() ) {
            flushReadBuffer( KTUICom );

            byte[] wArray = new byte[2];
            wArray[0] = (byte) 0x78; // x
            wArray[1] = (byte) 0xA0; // XVer

            KTUICom.write(wArray);
            bVerifyZentraleInProgress = true;

            timer.setInitialDelay(2000);
            timer.setDelay(2000);
            if( userTimer1 > 2000 ) timer.setInitialDelay(userTimer1);
            if( userTimer2 > 2000 ) timer.setDelay(userTimer2);
            if( debugLevel > 0 ) {
                System.out.println("verifyZentrale: initialdelay="+timer.getInitialDelay()+" delay="+timer.getDelay());
            }

            startIOAction();
        }
    }

    private void startIOAction() {
        //
        jButtonStart.setEnabled(false);
        jButtonOptions.setEnabled(false);
        jDecoderChooser.setEnabled(false);

        // set cursor to WAIT
        Cursor c = new Cursor(Cursor.WAIT_CURSOR);
        this.setCursor(c);
        //start timer
        timer.start();
    }
    private void stopIOAction() {
        // stop timer
        timer.stop();

        bVerifyZentraleInProgress = false;

        // set cursor
        Cursor c = new Cursor(Cursor.DEFAULT_CURSOR );
        setCursor(c);

        //
        jButtonStart.setEnabled(true);
        jButtonOptions.setEnabled(true);
        jDecoderChooser.setEnabled(true);
    }


    public int flushReadBuffer( TwoWaySerialComm Com ) {
        int numBytes = 0;
        if( (Com != null) && Com.isconnected() ) {
            // flushReadBuffer
            byte[] bArray = new byte[0xFFFF];
            numBytes = Com.read(bArray);
            if( debugLevel > 0 ) {
                System.out.println("2982 flushReadBuffer : "+numBytes+" Bytes" );
                dumpbArray(bArray);
            }
        }
        return numBytes;
    }

    public void trackPowerOn( TwoWaySerialComm Com ) {
        Com.write( (byte) 0x60 );
        checkTrackStatus( Com );
    }
    public void trackPowerOff( TwoWaySerialComm Com ) {
        Com.write( (byte) 0x61 );
        checkTrackStatus( Com );
    }

    public void checkTrackStatus( TwoWaySerialComm Com ) {
        if( Com == null ) {
            return;
        }
        ExternalCom = Com ;
        flushReadBuffer( ExternalCom );

        /* Abfrage mit 1 Byte Antwort ! */
        byte[] wArray = new byte[2];
        wArray[0] = (byte) 0x78; // x
        wArray[1] = (byte) 0xA2; // XStatus
        ExternalCom.write(wArray);
        bReadStatusBin = true;

        timer.setInitialDelay(500);
        timer.setDelay(500);
        if( userTimer1 > 2000 ) timer.setInitialDelay(userTimer1);
        if( userTimer2 > 2000 ) timer.setDelay(userTimer2);

        startIOAction();
    }

    public int getTrackStatus() {
        if( forceTrackPowerOn ) {
            return c.cuPowerOn;
        }
        return trackStatus;
    }

    private void distributeTrackStatus() {
        if( frameInstanceDEVICE != null ) {
            String devName = frameInstanceDEVICE.getClass().getSimpleName() ;
            switch( devName ) {
                case "B_4":
                    B_4 b4 = (B_4) frameInstanceDEVICE;
                    System.out.println( "calling setStatus( "+trackStatus+" ) for "+devName );
                    b4.setStatus( getTrackStatus() );
                    break;
                case "BiDi_B":
                    BiDi_B bidib = (BiDi_B) frameInstanceDEVICE;
                    System.out.println( "calling setStatus( "+trackStatus+" ) for "+devName );
                    bidib.setStatus( getTrackStatus() );
                    break;
                default:
                    System.out.println( "setStatus( "+trackStatus+" ) for UNKNOWN/UNSUPPORTED DEVICE "+devName );
            }
        }
    }

    public void setSwitch( TwoWaySerialComm Com, int address, boolean straight, boolean coilOn ) {
        byte[] wArray = new byte[4];
        wArray[0] = (byte) 0x78; // x
        wArray[1] = (byte) 0x90; // XTrnt
        wArray[2] = (byte) (address & 0xFF); // AdrLow
        wArray[3] = (byte) ( ( (address >> 8) & 0x07) | (straight?0x80:0x00) | (coilOn?0x40:0x00) ); // AdrHigh (3bits) + Bit8 = turnout, Bit7= coilOn
        Com.write( wArray );
        if( debugLevel > 0 ) {
            System.out.println("Weiche "+address+" gerade="+straight+ " aktiv="+coilOn );
        }
        dumpbArrayBIN(wArray, 4);
    }

    private static Boolean verifySelectionList() {
        // test decoder menu for correct idx in first column
        // if not some strange things might happen !
        int decoderListSize  = decoderList.values().length;
        int decoderListCount = 0;
        int errorsDetected   = 0;
        for(decoderList co : decoderList.values()) {
            if( co.getIdx() != decoderListCount ) {
                System.out.printf("ERROR in decoderListCount %2d decoderList[%2d] [%2d] (%s) Const %10s Text %-35s %s\n",
                        decoderListCount, co.ordinal(), co.getIdx(), co.isSelectable()?"X":" ", co, co.getMenutextGer(), co.getMenutextEng());
                errorsDetected++;
            }
            decoderListCount++;
        }
        if( decoderListCount != decoderListSize ) {
            System.out.println("ERROR in decoderListCount != decoderListSize "+decoderListCount+" != "+decoderListSize);
            errorsDetected++;
        }
        if( errorsDetected != 0 ) {
            System.out.println(""+errorsDetected+" ERRORS in decoderList detected" );
            return false;
        }else {
            System.out.println("decoderList verified successfully");
        }
        return true;
    }

    private static void helpCommandLine() {
        System.out.println("CV-Navi [-d [<num>]] [-c] [-c Xfuncs] [-c Xm3sid ] [-b <num>] [-o]");
        System.out.println("\t-d [<num>] \tdebuglevel (without num: increment by 1)" );
        System.out.println("\t-c         \tforce configured control unit (no check)");
        System.out.println("\t-c Xfuncs  \tforce enable XFuncs");
        System.out.println("\t-c Xm3sid  \tforce enable Xm3sid");
        System.out.println("\t-b <num>   \tuse preconfigured dummy datasets for debugging");
        System.out.println("\t-o         \tforce offline mode");
        System.out.println("\t-p         \tforce track power reported as on");
        System.out.println("\t-t1 <num>  \tread/write CVs: initial timeout [ms] (default: 2000)");
        System.out.println("\t-t2 <num>  \tread/write CVs: delay timeout [ms] (default: 500)");
        System.out.println("\t-t3 <num>  \tread/write CVs: extra pause [ms] between CV requests (default: 0)");
        System.out.println("\t-tr <num>  \tread/write CVs: number of retries with delay timeout (default: 9)");
        System.out.println("\t-tfu <num> \ttimer interval during firmware updates (default: 250)");
        System.out.println("\t-u         \tupdate window always visible");
        System.out.println("\t-usb1      \tuse USB-1 in RedBox/MasterControl -> no BaudRate changes on firmware updates (default: on)");
        System.out.println("\t-usb2      \tuse USB-2 in RedBox -> BaudRate change to 38400 on firmware-updates (default: off)");
        System.out.println("\t-rs232     \tuse RS232 in RedBox/MasterControl -> BaudRate change to 38400 on firmware-updates (default: off)");
        System.out.println("\t-no17      \tdo not read CV17");
        System.out.println("\t-no18      \tdo not read CV18");
    }
    /**
    * @param args the command line arguments
    */
    public static void main(final String args[]) {

        int argc = args.length;
        String dataModel = System.getProperty("sun.arch.data.model");
        String osName = System.getProperty("os.name");
        String osArch = System.getProperty("os.arch");
        String javaversion = System.getProperty("java.version");
        System.out.println(""+osName+"["+osArch+"] , java "+javaversion+"["+dataModel+"bit]");

        System.out.println("argc="+ argc );
        int n = 0;
        try {
            for ( n = 0 ; n < argc ; n++ ) {
                switch( args[n].toLowerCase() ) {
                    case "-d":
                        if( n == (argc-1) || args[n+1].startsWith("-") ){
                            debugLevel++;
                            System.out.println("debugLevel set to "+debugLevel);
                            break;
                        }
                        n++;
                        debugLevel = Integer.parseInt(args[n]);
                        System.out.println("debugLevel set to "+debugLevel);
                        break;
                    case "-c":
                        if( n == (argc-1) || args[n+1].startsWith("-") ){
                            bZentraleVerified = true;
                            System.out.println("bZentraleVerified set to "+bZentraleVerified);
                            break;
                        }
                        n++;
                        if( args[n].equalsIgnoreCase("Xfuncs")) {
                            bZentraleVerified = true;
                            bUseXfuncs = true;
                            System.out.println("bUseXfuncs set to "+bUseXfuncs);
                        }
                        if( args[n].equalsIgnoreCase("Xm3sid")) {
                            bZentraleVerified = true;
                            bUseXm3sid = true;
                            System.out.println("bUseXm3sid set to "+bUseXm3sid);
                        }
                        break;
                    case "-b":
                        if( n == (argc-1) || args[n+1].startsWith("-") ) {
                            // "-b" ohne Parameter -> Zahl fehlt => ignorieren
                            break;
                        }
                        n++;
                        debugDummyData = Integer.parseInt(args[n]);
                        System.out.println("debugDummyData set to "+debugDummyData);
                        break;
                    case "-o":
                        debugOffline = true;
                        System.out.println("debugOffline set to true");
                        break;
                    case "-p":
                        forceTrackPowerOn = true;
                        System.out.println("forceTrackPowerOn set to true");
                        break;
                    case "-t1":
                        if( n == (argc-1) || args[n+1].startsWith("-") ) {
                            // "-t1" ohne Parameter -> Zahl fehlt => ignorieren
                            break;
                        }
                        n++;
                        userTimer1 = Integer.parseInt(args[n]);
                        System.out.println("userTimer1 set to "+userTimer1);
                        break;
                    case "-t2":
                        if( n == (argc-1) || args[n+1].startsWith("-") ) {
                            // ohne Parameter -> Zahl fehlt => ignorieren
                            break;
                        }
                        n++;
                        userTimer2 = Integer.parseInt(args[n]);
                        System.out.println("userTimer2 set to "+userTimer2);
                        break;
                    case "-t3":
                        if( n == (argc-1) || args[n+1].startsWith("-") ) {
                            // ohne Parameter -> Zahl fehlt => ignorieren
                            break;
                        }
                        n++;
                        timer3 = Integer.parseInt(args[n]);
                        System.out.println("userTimer3 set to "+userTimer3);
                        break;
                    case "-tr":
                        if( n == (argc-1) || args[n+1].startsWith("-") ) {
                            // ohne Parameter -> Zahl fehlt => ignorieren
                            break;
                        }
                        n++;
                        userTimerRetries = Integer.parseInt(args[n]);
                        System.out.println("userTimerRetries set to "+userTimerRetries);
                        break;
                    case "-tfu":
                        if( n == (argc-1) || args[n+1].startsWith("-") ) {
                            // ohne Parameter -> Zahl fehlt => ignorieren
                            break;
                        }
                        n++;
                        userTimerFwUp = Integer.parseInt(args[n]);
                        System.out.println("userTimerFwUp set to "+userTimerFwUp);
                        break;
                    case "-u":
                        updateAlwaysVisible = true;
                        System.out.println("updateVisible set to true");
                        break;
                    case "-usb1":
                        rs232_or_rb_usb_2 = false;
                        System.out.println("force using usb-1 mode");
                        rs232_mode_was_forced = true;
                        break;
                    case "-usb2":
                    case "-rs232":
                        rs232_or_rb_usb_2 = true;
                        System.out.println("force using usb-2/rs232 mode");
                        rs232_mode_was_forced = true;
                        break;
                    case "-no17":
                        skipCV17 = true;
                        System.out.println("skipCV17 set to "+skipCV17);
                        break;
                    case "-no18":
                        skipCV18 = true;
                        System.out.println("skipCV18 set to "+skipCV18);
                        break;
                    case "-h":
                        helpCommandLine();
                        return;
                    default:
                        System.out.println("args["+n+"]=["+args[n]+"] UNHANDLED");
                }
            }
        } catch (NumberFormatException ex) {
            System.out.println("inside parse args: numberFormatException ex["+ex+"] n="+n);
            helpCommandLine();
            return;
        }
        if( debugLevel > 0 )
            System.out.println("debugLevel="+debugLevel);

        if( verifySelectionList() == false )
            return;

        String s = "javax.swing.plaf.metal.MetalLookAndFeel";

        Properties prop = new Properties();
        try {
            prop.loadFromXML(new FileInputStream(gsConfigFilename));
        } catch (FileNotFoundException ex) {
            // Logger.getLogger(CVNavi.class.getName()).log(Level.INFO, null, ex);
            try {
                    File f = new File(gsConfigDirectory);
                    f.mkdir();
                    prop.storeToXML(new FileOutputStream(gsConfigFilename), gsConfigComment);
            } catch (IOException ex1) {
                // MsgBox messageBox = new MsgBox( (Frame) CVNavi.getFrames()[0] , true);
                MsgBox messageBox = new MsgBox( (Frame) null , true, null );

                messageBox.jLabel1.setText("FEHLER");
                messageBox.jLabel2.setText("Kann Konfigurationsdatei nicht anlegen");
                messageBox.jLabel3.setText(gsConfigFilename);
                messageBox.setVisible(true);

                Logger.getLogger(CVNavi.class.getName()).log(Level.SEVERE, null, ex1);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex2) {
                    Logger.getLogger(CVNavi.class.getName()).log(Level.SEVERE, null, ex2);
                }
                // System.exit(1);
                // System.exit(1);
            }
        } catch (IOException ex) {
          Logger.getLogger(CVNavi.class.getName()).log(Level.SEVERE, null, ex);
        }

        switch (prop.getProperty("LookAndFeel","Metal")) {
            case "Motif":
                s = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
                break;
            case "Nimbus":
                s = "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";
                break;
            case "Windows":
                s = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
                break;
        }

        try {
            UIManager.setLookAndFeel(s);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(CVNavi.class.getName()).log(Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CVNavi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel CVNaviMainPanel;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextField jBaudRate;
    private javax.swing.JTextField jBaudrateTitle;
    private javax.swing.JButton jButtonEnd;
    private javax.swing.JButton jButtonInfo;
    private javax.swing.JButton jButtonOptions;
    private javax.swing.JButton jButtonStart;
    private javax.swing.JList jDecoderChooser;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabelBuild;
    private javax.swing.JLabel jLabelOS;
    private javax.swing.JLabel jLabelProgName;
    private javax.swing.JLabel jLabelProgVersion;
    private javax.swing.JTextField jSchnittstelle;
    private javax.swing.JTextField jSchnittstelleTitle;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jZentrale;
    private javax.swing.JTextField jZentraleTitle;
    // End of variables declaration//GEN-END:variables

}
