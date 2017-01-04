/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package my.KlarText;

import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.InputMap;
import javax.swing.JFileChooser;
import javax.swing.KeyStroke;
import static my.KlarText.KlarTextUI.debugLevel;

/**
 *
 * @author Lothar
 */
public class SaveOpenDialog extends javax.swing.JDialog {

    private Frame parentFrame;
    private boolean bModal;
    private boolean bLesen;
    public boolean bCancel;
    public String filename;
    public FDR myFDR;
    public FD_M myFDM;
    public FD_LED myFDLED;
    public LDG30 myLD30;
    public LDG31 myLD31;
    public LDG33 myLD33;
    public WIB_30 myWIB;
    public FD_R_Extended myFDRex;
    public LDG30erPlus myLDG30erPlus;
    public MC myMC;
    private String str;
    private String filter;
    private int Decoder;
    private KlarTextUI KTUI;


    private void commonDialogSettings() {
        KeyStroke enter = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
        InputMap map = jFileChooser1.getInputMap(JFileChooser.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        map.put(enter, "approveSelection");

        switch( Decoder ) {
            case c.KENN:
                jFileChooser1.setSelectedFile(new File(KTUI.gsSaveOpenKennDirectory + "/" + KTUI.gsSaveOpenKennFilename));
                break;
            case c.M3:
                jFileChooser1.setSelectedFile(new File(KTUI.gsSaveOpenM3Directory+"/"+KTUI.gsSaveOpenM3Filename));
                break;
            case c.MC:
                jFileChooser1.setSelectedFile(new File(KTUI.gsSaveOpenMcDirectory+"/"+KTUI.gsSaveOpenMcFilename));
                break;
            case c.HEX:
                jFileChooser1.setSelectedFile(new File(KTUI.gsOpenFirmwareDirectory+"/"+KTUI.gsOpenFirmwareFilename));
                break;
            default: // all decoders
                jFileChooser1.setSelectedFile(new File(KTUI.gsSaveOpenDirectory+"/"+KTUI.gsSaveOpenFilename));
        }

        if (!bLesen) {
            jFileChooser1.setDialogType(javax.swing.JFileChooser.SAVE_DIALOG);
        }
        DecoderFilter ff = new DecoderFilter();
        ff.Decoder = Decoder;
        if( (Decoder == c.MC) && (! bLesen) ) {
            // special handling for writing MC-Config
            ff.Decoder = c.MC_WR;
        }
        jFileChooser1.addChoosableFileFilter(ff);
        jFileChooser1.setFileFilter(ff);

        if( KTUI.SODlocalSize.width > 0 ) {
            setPreferredSize(KTUI.SODlocalSize);
        }
        pack();
        setLocationRelativeTo(parentFrame);
    }

    /** Creates new form SaveOpenDialog
     * @param parent parent/calling frame
     * @param modal  modality
     * @param bRead  true = read , false = write
     * @param wStr    a string containing data to be saved to the user selected file
     * @param decoderThis  the calling instance
     * @param filter file extension to use
     */
    public SaveOpenDialog( Frame parent, boolean modal, boolean bRead, String wStr, FD_R_Extended decoderThis, String filter) {
        super(parent, modal);
        this.parentFrame = parent;
        this.bModal = modal;
        initComponents();

        this.bLesen = bRead;
        this.str = wStr;
        this.myFDRex = decoderThis;
        this.filter = filter;

        this.KTUI = decoderThis.KTUI ;
        this.Decoder = decoderThis.KTUI.Decoder;

        commonDialogSettings();

        setVisible(true);
    }

    public SaveOpenDialog( Frame parent, boolean modal, boolean bRead, String wStr, LDG30erPlus decoderThis, String filter) {
        super(parent, modal);
        this.parentFrame = parent;
        this.bModal = modal;
        initComponents();

        this.bLesen = bRead;
        this.str = wStr;
        this.myLDG30erPlus = decoderThis;
        this.filter = filter;
        this.KTUI = decoderThis.KTUI ;
        this.Decoder = decoderThis.KTUI.Decoder;

        commonDialogSettings();

        setVisible(true);
    }

    public SaveOpenDialog( Frame parent, boolean modal, boolean bRead, String wStr, FDR decoderThis, String filter) {
        super(parent, modal);
        this.parentFrame = parent;
        this.bModal = modal;
        initComponents();

        this.bLesen = bRead;
        this.str = wStr;
        this.myFDR = decoderThis;
        this.filter = filter;
        this.KTUI = decoderThis.KTUI ;
        this.Decoder = decoderThis.KTUI.Decoder;

        commonDialogSettings();

        setVisible(true);
    }

    public SaveOpenDialog( Frame parent, boolean modal, boolean bRead, String wStr, WIB_30 decoderThis, String filter) {
        super(parent, modal);
        this.parentFrame = parent;
        this.bModal = modal;
        initComponents();

        this.bLesen = bRead;
        this.str = wStr;
        this.myWIB = decoderThis;
        this.filter = filter;
        this.KTUI = decoderThis.KTUI ;
        this.Decoder = decoderThis.KTUI.Decoder;

        commonDialogSettings();

        setVisible(true);
    }

    public SaveOpenDialog( Frame parent, boolean modal, boolean bRead, String wStr, FD_M decoderThis, String filter) {
        super(parent, modal);
        this.parentFrame = parent;
        this.bModal = modal;
        initComponents();

        this.bLesen = bRead;
        this.str = wStr;
        this.myFDM = decoderThis;
        this.filter = filter;
        this.KTUI = decoderThis.KTUI ;
        this.Decoder = decoderThis.KTUI.Decoder;

        commonDialogSettings();

        setVisible(true);
    }

    public SaveOpenDialog( Frame parent, boolean modal, boolean bRead, String wStr, FD_LED decoderThis, String filter) {
        super(parent, modal);
        this.parentFrame = parent;
        this.bModal = modal;
        initComponents();

        this.bLesen = bRead;
        this.str = wStr;
        this.myFDLED = decoderThis;
        this.filter = filter;
        this.KTUI = decoderThis.KTUI ;
        this.Decoder = decoderThis.KTUI.Decoder;

        commonDialogSettings();

        setVisible(true);
    }

    public SaveOpenDialog( Frame parent, boolean modal, boolean bRead, String wStr, LDG30 decoderThis, String filter) {
        super(parent, modal);
        this.parentFrame = parent;
        this.bModal = modal;
        initComponents();

        this.bLesen = bRead;
        this.str = wStr;
        this.myLD30 = decoderThis;
        this.filter = filter;
        this.KTUI = decoderThis.KTUI ;
        this.Decoder = decoderThis.KTUI.Decoder;

        commonDialogSettings();

        setVisible(true);
    }

    public SaveOpenDialog( Frame parent, boolean modal, boolean bRead, String wStr, LDG33 decoderThis, String filter) {
        super(parent, modal);
        this.parentFrame = parent;
        this.bModal = modal;
        initComponents();

        this.bLesen = bRead;
        this.str = wStr;
        this.myLD33 = decoderThis;
        this.filter = filter;
        this.KTUI = decoderThis.KTUI ;
        this.Decoder = decoderThis.KTUI.Decoder;

        commonDialogSettings();

        setVisible(true);
    }

    public SaveOpenDialog( Frame parent, boolean modal, boolean bRead, String wStr, LDG31 decoderThis, String filter) {
        super(parent, modal);
        this.parentFrame = parent;
        this.bModal = modal;
        initComponents();

        this.bLesen = bRead;
        this.str = wStr;
        this.myLD31 = decoderThis;
        this.filter = filter;
        this.KTUI = decoderThis.KTUI ;
        this.Decoder = decoderThis.KTUI.Decoder;

        commonDialogSettings();

        setVisible(true);
    }

    public SaveOpenDialog( Frame parent, boolean modal, boolean bRead, String wStr, MC decoderThis, String filter, int mcSubType) {
        super(parent, modal);
        this.parentFrame = parent;
        this.bModal = modal;
        initComponents();

        this.bLesen = bRead;
        this.str = wStr;
        this.myMC = decoderThis;
        this.filter = filter;
        this.KTUI = decoderThis.KTUI ;
        this.Decoder = mcSubType; // different here NOT decoderThis.KTUI.Decoder !

        commonDialogSettings();

        setVisible(true);
    }

    // Kennlinie LDG30
    public SaveOpenDialog( Frame parent, boolean modal, boolean bRead, String wStr, LDG30 decoderThis ) {
        super(parent, modal);
        this.parentFrame = parent;
        this.bModal = modal;
        initComponents();

        this.bLesen = bRead;
        this.str = wStr;
        this.myLD30 = decoderThis;
        this.filter = "kenn";
        this.KTUI = decoderThis.KTUI;
        this.Decoder = c.KENN; // Kennlinie

        commonDialogSettings();

        setVisible(true);
    }

    // Kennlinie LDG31
    public SaveOpenDialog( Frame parent, boolean modal, boolean bRead, String wStr, LDG31 decoderThis ) {
        super(parent, modal);
        this.parentFrame = parent;
        this.bModal = modal;
        initComponents();

        this.bLesen = bRead;
        this.str = wStr;
        this.myLD31 = decoderThis;
        this.filter = "kenn";
        this.KTUI = decoderThis.KTUI;
        this.Decoder = c.KENN; // Kennlinie

        commonDialogSettings();

        setVisible(true);
    }

    // Kennlinie LDG33
    public SaveOpenDialog( Frame parent, boolean modal, boolean bRead, String wStr, LDG33 decoderThis ) {
        super(parent, modal);
        this.parentFrame = parent;
        this.bModal = modal;
        initComponents();

        this.bLesen = bRead;
        this.str = wStr;
        this.myLD33 = decoderThis;
        this.filter = "kenn";
        this.KTUI = decoderThis.KTUI;
        this.Decoder = c.KENN; // Kennlinie

        commonDialogSettings();

        setVisible(true);
    }



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLastFiles = new javax.swing.JComboBox<String>();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jFileChooser1 = new javax.swing.JFileChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jLastFiles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jLastFilesActionPerformed(evt);
            }
        });

        jTextField1.setEditable(false);
        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("my/KlarText/Bundle"); // NOI18N
        jTextField1.setText(bundle.getString("SaveOpenDialog.jTextField1.text")); // NOI18N

        jLabel1.setText(bundle.getString("SaveOpenDialog.jLabel1.text")); // NOI18N

        jFileChooser1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFileChooser1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFileChooser1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLastFiles, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(34, 34, 34))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 605, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jFileChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(21, 21, 21))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(jFileChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLastFiles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLastFilesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jLastFilesActionPerformed
        int i = jLastFiles.getSelectedIndex();
        String s = jLastFiles.getItemAt(i);
        File f = new File(s);
        jFileChooser1.setSelectedFile(f);
    }//GEN-LAST:event_jLastFilesActionPerformed

    private void jFileChooser1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFileChooser1ActionPerformed
        String str_temp = evt.getActionCommand();
        if( debugLevel >= 1 ) {
            System.out.println("jFileChooser1ActionPerformed["+str_temp+"]" );
        }
        if( !str_temp.matches("CancelSelection") )
        {
            if( debugLevel >= 1 ) {
                System.out.println("bCancel = false;" );
            }
            bCancel = false;

            File f = jFileChooser1.getSelectedFile();
            str_temp = null;
            if(bLesen)
            {
                if( debugLevel >= 1 ) {
                    System.out.println("Datei lesen");
                }
                //Datei lesen
                String s = "";
                char ac[] = new char[5000];
                if(f.isFile())
                {
                    FileReader inputStream = null;
                    str_temp = f.getPath();
                    try
                    {
                        try {
                            inputStream = new FileReader(f);
                            try {

                                int n = inputStream.read(ac, 0, 5000);
                                if(n == -1)
                                {
                                    MsgBox messageBox = new MsgBox( (Frame) this.getParent(), true, this);
                                    if(KTUI.bSpracheDE)
                                    {
                                        messageBox.jLabel1.setText("Fehler!");
                                        messageBox.jLabel2.setText("");
                                        messageBox.jLabel3.setText("Datei enthält keine Daten");
                                    }
                                    else
                                    {
                                        messageBox.jLabel1.setText("Error!");
                                        messageBox.jLabel2.setText("");
                                        messageBox.jLabel3.setText("no data in File.");
                                    }
                                    messageBox.setVisible(true);
                                    return;
                                }
                                String str1 = new String(ac);
                                switch (Decoder)
                                {
                                    case c.LD_G30:
                                    case c.LD_W32:
                                    case c.LD_G32:
                                    case c.LD_W32_2:
                                    case c.LD_G32_2:
                                    myLD30.ReturnString = str1.substring(0, n);
                                    break;

                                    case c.LD_G31:
                                    myLD31.ReturnString = str1.substring(0, n);
                                    break;

                                    case c.LD_W33:
                                    case c.LD_G33:
                                    case c.LD_G34:
                                    case c.FD_XL:
                                    myLD33.ReturnString = str1.substring(0, n);
                                    break;

                                    case c.FD_R:
                                    case c.FD_R2:
                                    myFDR.ReturnString = str1.substring(0, n);
                                    break;

                                    case c.LD_G31Plus:
                                    case c.LD_G33Plus:
                                    case c.LD_G34Plus:
                                    case c.LD_G36Plus:
                                    myLDG30erPlus.ReturnString = str1.substring(0, n);
                                    break;

                                    case c.FD_M:
                                    myFDM.ReturnString = str1.substring(0, n);
                                    break;

                                    case c.FD_R_ex:
                                    myFDRex.ReturnString = str1.substring(0, n);
                                    break;

                                    case c.FD_LED:
                                    myFDLED.ReturnString = str1.substring(0, n);
                                    break;

                                    case c.WIB_30:
                                    myWIB.ReturnString = str1.substring(0, n);
                                    break;

                                    case c.KENN:
                                    if( myLD30 != null ) {
                                        myLD30.ReturnString = str1.substring(0, n);
                                    }
                                    if( myLD31 != null ) {
                                        myLD31.ReturnString = str1.substring(0, n);
                                    }
                                    if( myLD33 != null ) {
                                        myLD33.ReturnString = str1.substring(0, n);
                                    }
                                    break;

                                    case c.M3:
                                    myMC.ReturnString = str1.substring(0, n);
                                    break;

                                    case c.MC:
                                    // TODO SICHERUNG von LastMCLoadDirectory und LastMCLoadFile ???
                                    myMC.ReturnString = str1.substring(0, n);
                                    break;

                                    case c.HEX:
                                    // TODO SICHERUNG von LastFirmwareLoadDirectory und LastFirmwareLoadFile ???
                                    myMC.setUpdDatei( f.getPath() );
                                    myMC.ReturnString = str1.substring(0, n);
                                    break;

                                    case c.TXT:
                                    // TODO SICHERUNG von LastFirmwareLoadDirectory und LastFirmwareLoadFile ???
                                    myMC.setUpdDatei( f.getPath() );
                                    myMC.ReturnString = str1.substring(0, n);
                                    break;
                                }
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

                    FileWriter outputStream;
                    switch(Decoder)
                    {
                        case c.LD_G30:
                        s = KlarTextUI.gsConfigDirectory + "LastFiles30.lfs";
                        break;

                        case c.LD_G31:
                        s = KlarTextUI.gsConfigDirectory + "LastFiles31.lfs";
                        break;

                        case c.LD_W32:
                        case c.LD_G32:
                        case c.LD_W32_2:
                        case c.LD_G32_2:
                        s = KlarTextUI.gsConfigDirectory + "LastFiles32.lfs";
                        break;

                        case c.FD_R:
                        s = KlarTextUI.gsConfigDirectory + "LastFilesFDR.lfs";
                        break;

                        case c.FD_R2:
                        s = KlarTextUI.gsConfigDirectory + "LastFilesFDR2.lfs";
                        break;

                        case c.LD_W33:
                        case c.LD_G33:
                        s = KlarTextUI.gsConfigDirectory + "LastFiles33.lfs";
                        break;

                        case c.LD_G34:
                        s = KlarTextUI.gsConfigDirectory + "LastFiles34.lfs";
                        break;

                        case c.LD_G31Plus:
                        s = KlarTextUI.gsConfigDirectory + "LastFiles31plus.lfs";
                        break;

                        case c.LD_G33Plus:
                        s = KlarTextUI.gsConfigDirectory + "LastFiles33plus.lfs";
                        break;

                        case c.LD_G34Plus:
                        s = KlarTextUI.gsConfigDirectory + "LastFiles34plus.lfs";
                        break;

                        case c.LD_G36Plus:
                        s = KlarTextUI.gsConfigDirectory + "LastFiles36plus.lfs";
                        break;

                        case c.FD_M:
                        s = KlarTextUI.gsConfigDirectory + "LastFilesFDM.lfs";
                        break;

                        case c.FD_R_ex:
                        s = KlarTextUI.gsConfigDirectory + "LastFilesFDE.lfs";
                        break;

                        case c.FD_XL:
                        s = KlarTextUI.gsConfigDirectory + "LastFilesFDXL.lfs";
                        break;

                        case c.FD_LED:
                        s = KlarTextUI.gsConfigDirectory + "LastFilesFDLED.lfs";
                        break;

                        case c.WIB_30:
                        s = KlarTextUI.gsConfigDirectory + "LastFilesWIB.lfs";
                        break;

                        case c.KENN:
                        s = KlarTextUI.gsConfigDirectory + "LastFilesKenn.lfs";
                        break;

                        case c.M3:
                        s = KlarTextUI.gsConfigDirectory + "LastFilesM3uid.lfs";
                        break;

                        case c.MC:
                        s = KlarTextUI.gsConfigDirectory + "LastFilesMC.lfs";
                        break;

                        case c.HEX:
                        s = KlarTextUI.gsConfigDirectory + "LastFilesFirmware.lfs";
                        break;

                        case c.TXT:
                        s = KlarTextUI.gsConfigDirectory + "LastFilesTXT.lfs";
                        break;
                    }
                    outputStream = null;
                    inputStream = null;
                    try {
                        inputStream = new FileReader(s);
                        char[] cbuf = new char[10000];
                        try {
                            int i = inputStream.read(cbuf);
                            if (i > 0) {
                                String str1 = new String(cbuf);
                                String[] strArr = str1.substring(0, i).split("\r\n");
                                int n = strArr.length;
                                inputStream.close();
                                if(n > 10)
                                {
                                    i = n % 10;
                                }
                                else
                                {
                                    i = 0;
                                }
                                outputStream = new FileWriter(s);
                                for (; i < n; i++) {
                                    boolean b;
                                    try {
                                        b = str_temp.matches(strArr[i]);
                                    } catch (Exception e) {
                                        b = false;
                                    }
                                    if (b) {
                                        continue;
                                    }
                                    outputStream.write(strArr[i]);
                                    outputStream.write("\r\n");
                                }
                                outputStream.write(str_temp);
                                outputStream.write("\r\n");
                                outputStream.close();
                            } else {
                                outputStream = new FileWriter(s);
                                outputStream.write(str_temp);
                                outputStream.write("\r\n");
                                outputStream.close();
                            }
                        } catch (IOException ex) {
                            Logger.getLogger(SaveOpenDialog.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    } catch (FileNotFoundException ex) {
                        switch(Decoder)
                        {
                            case c.LD_G30:
                            s = KlarTextUI.gsConfigDirectory + "LastFiles30.lfs";
                            break;

                            case c.LD_G31:
                            s = KlarTextUI.gsConfigDirectory + "LastFiles31.lfs";
                            break;

                            case c.LD_W32:
                            case c.LD_G32:
                            case c.LD_W32_2:
                            case c.LD_G32_2:
                            s = KlarTextUI.gsConfigDirectory + "LastFiles32.lfs";
                            break;

                            case c.FD_R:
                            s = KlarTextUI.gsConfigDirectory + "LastFilesFDR.lfs";
                            break;

                            case c.FD_R2:
                            s = KlarTextUI.gsConfigDirectory + "LastFilesFDR2.lfs";
                            break;

                            case c.LD_W33:
                            case c.LD_G33:
                            s = KlarTextUI.gsConfigDirectory + "LastFiles33.lfs";
                            break;

                            case c.LD_G34:
                            s = KlarTextUI.gsConfigDirectory + "LastFiles34.lfs";
                            break;

                            case c.LD_G31Plus:
                            s = KlarTextUI.gsConfigDirectory + "LastFiles31plus.lfs";
                            break;

                            case c.LD_G33Plus:
                            s = KlarTextUI.gsConfigDirectory + "LastFiles33plus.lfs";
                            break;

                            case c.LD_G34Plus:
                            s = KlarTextUI.gsConfigDirectory + "LastFiles34plus.lfs";
                            break;

                            case c.LD_G36Plus:
                            s = KlarTextUI.gsConfigDirectory + "LastFiles36plus.lfs";
                            break;

                            case c.FD_M:
                            s = KlarTextUI.gsConfigDirectory + "LastFilesFDM.lfs";
                            break;

                            case c.FD_R_ex:
                            s = KlarTextUI.gsConfigDirectory + "LastFilesFDE.lfs";
                            break;

                            case c.FD_XL:
                            s = KlarTextUI.gsConfigDirectory + "LastFilesFDXL.lfs";
                            break;

                            case c.FD_LED:
                            s = KlarTextUI.gsConfigDirectory + "LastFilesFDLED.lfs";
                            break;

                            case c.WIB_30:
                            s = KlarTextUI.gsConfigDirectory + "LastFilesWIB.lfs";
                            break;

                            case c.KENN:
                            s = KlarTextUI.gsConfigDirectory + "LastFilesKenn.lfs";
                            break;

                            case c.M3:
                            s = KlarTextUI.gsConfigDirectory + "LastFilesM3uid.lfs";
                            break;

                            case c.MC:
                            s = KlarTextUI.gsConfigDirectory + "LastFilesMC.lfs";
                            break;

                            case c.HEX:
                            s = KlarTextUI.gsConfigDirectory + "LastFilesFirmware.lfs";
                            break;

                            case c.TXT:
                            s = KlarTextUI.gsConfigDirectory + "LastFilesTXT.lfs";
                            break;
                        }
                        try {
                            outputStream = new FileWriter(s);
                            outputStream.write(str_temp);
                            outputStream.write("\r\n");
                            outputStream.close();
                        } catch (IOException ex1) {
                            Logger.getLogger(SaveOpenDialog.class.getName()).log(Level.SEVERE, null, ex1);
                        }
                    }
                }
                else
                {
                    if (KTUI.bSpracheDE) {
                        str_temp = "Datei \"" + f.getPath() + "\" nicht gefunden.";
                    } else {
                        str_temp = "File not found: \"" + f.getPath() + "\".";
                    }
                    MsgBox messageBox = new MsgBox( (Frame) this.getParent(), true, this );
                    if (KTUI.bSpracheDE) {
                        messageBox.jLabel1.setText("Fehler!");
                    } else {
                        messageBox.jLabel1.setText("Error!");
                    }
                    messageBox.jLabel2.setText("");
                    messageBox.jLabel3.setText(str_temp);
                    messageBox.setVisible(true);
                    return;
                }
            }
            else
            {
                if( debugLevel >= 1 ) {
                    System.out.println("Datei schreiben");
                }
                //Datei schreiben
                String s = f.getPath();
                int length = s.length();
                if(length > 4)
                {
                    if(!s.substring(length - filter.substring(0).length(), length).matches(filter.substring(0)))
                    {
                        s = s + "." + filter.substring(0);
                    }
                }
                else
                {
                    s = s + "." + filter.substring(0);
                }
                FileWriter outputStream = null;
                try
                {
                    try {
                        outputStream = new FileWriter(s);

                        outputStream.write(str);
                    } catch (IOException ex) {
                        Logger.getLogger(SaveOpenDialog.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                finally
                {
                    if (outputStream != null)
                    {
                        try {
                            outputStream.close();
                        } catch (IOException ex) {
                            Logger.getLogger(SaveOpenDialog.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
            /*
            try {
                KTUI.gsLastMcConfSave = f.getPath();
                prop.setProperty("LastConfigSaveFile", f.getName()) ;
                prop.setProperty("LastConfigSaveDirectory", f.getParent()) ;
                prop.storeToXML(new FileOutputStream(KlarTextUI.gsConfigFilename), KlarTextUI.gsConfigComment);
            } catch (IOException ex) {
                Logger.getLogger(MC.class.getName()).log(Level.SEVERE, null, ex);
            }
            */
            // Save this selection
            switch( Decoder ) {
                case c.KENN:
                KTUI.gsSaveOpenKennDirectory = f.getParent();
                KTUI.gsSaveOpenKennFilename  = f.getName();
                break;

                case c.M3:
                KTUI.gsSaveOpenM3Directory = f.getParent();
                KTUI.gsSaveOpenM3Filename  = f.getName();
                break;

                case c.MC:
                KTUI.gsSaveOpenMcDirectory = f.getParent();
                KTUI.gsSaveOpenMcFilename  = f.getName();
                break;

                case c.HEX:
                KTUI.gsOpenFirmwareDirectory = f.getParent();
                KTUI.gsOpenFirmwareFilename  = f.getName();
                break;

                default:
                // alle Dekoder und Kennlinie
                KTUI.gsSaveOpenDirectory = f.getParent();
                KTUI.gsSaveOpenFilename  = f.getName();
            }
        }
        else
        {
            bCancel = true;
            if( debugLevel >= 1 ) {
                System.out.println("bCancel = true;");
            }
            switch(Decoder)
            {
                case c.MC:
                    if( bLesen ) {
                        myMC.setMcRwProgess( 0 );
                        myMC.setMcRwInfo("read from file: cancelled by user");
                    } else { // schreiben
                        myMC.setMcRwProgess( 0 );
                        myMC.setMcRwInfo("write to file: cancelled by user");
                    }
                    break;
            }
        }
        this.dispose();
    }//GEN-LAST:event_jFileChooser1ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        if(!KTUI.bSpracheDE)
        {
            jLabel1.setText("last opened:");
        }
    }//GEN-LAST:event_formWindowOpened

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        if( debugLevel >= 1 ) {
            System.out.println("formWindowClosed bCancel["+bCancel+"] bOpen["+bLesen+"]");
        }
        KTUI.SODlocalSize = this.getSize();
        // KTUI.SODlocalSize = this.jFileChooser1.getSize();
        if( bCancel == true ) {
            return;
        }
        switch(Decoder)
        {
            case c.LD_G30:
            case c.LD_G32:
            case c.LD_W32:
            case c.LD_G32_2:
            case c.LD_W32_2:
                myLD30.filfilCVs();
                myLD30.updateTabs();
                break;

            case c.LD_G31:
                myLD31.filfilCVs();
                myLD31.updateTabs();
                break;

            case c.LD_W33:
            case c.LD_G33:
            case c.LD_G34:
            case c.FD_XL:
                myLD33.filfilCVs();
                myLD33.updateTabs();
                break;

            case c.FD_R:
            case c.FD_R2:
                myFDR.filfilCVs();
                myFDR.updateTabs();
               break;

            case c.LD_G31Plus:
            case c.LD_G33Plus:
            case c.LD_G34Plus:
            case c.LD_G36Plus:
                myLDG30erPlus.filfilCVs();
                myLDG30erPlus.updateTabs();
                break;

            case c.FD_M:
                myFDM.filfilCVs();
                myFDM.updateTabs();
                break;

            case c.FD_R_ex:
                myFDRex.filfilCVs();
                myFDRex.updateTabs();
                break;

            case c.FD_LED:
                myFDLED.filfilCVs();
                myFDLED.updateTabs();
                break;

            case c.WIB_30:
                myWIB.filfilCVs();
                myWIB.updateTabs();
                break;

            case c.KENN:
                if( bLesen ) {
                    if( myLD30 != null && bLesen ) {
                        System.out.println("Kenn Gelesen: "+ myLD30.ReturnString.length()+" Bytes");
                        myLD30.filfilKENN();
                        myLD30.updateTabs();
                    }
                    if( myLD31 != null && bLesen ) {
                        System.out.println("Kenn Gelesen: "+ myLD31.ReturnString.length()+" Bytes");
                        myLD31.filfilKENN();
                        myLD31.updateTabs();
                    }
                    if( myLD33 != null && bLesen ) {
                        System.out.println("Kenn Gelesen: "+ myLD33.ReturnString.length()+" Bytes");
                        myLD33.filfilKENN();
                        myLD33.updateTabs();
                    }
                }
                break;

            case c.M3:
                System.out.println("M3 Gelesen: "+myMC.ReturnString.length()+" Bytes bLesen="+bLesen);
                if( bLesen ) {
                    myMC.updateM3uid();
                    myMC.updateTabs();
                }
                break;

            case c.MC:
                if( bLesen ) {
                    System.out.println("MC Gelesen: "+myMC.ReturnString.length()+" Bytes bLesen="+bLesen);
                    myMC.callParser( jFileChooser1.getSelectedFile().getName() );
                    myMC.updateTabs();
                } else { // schreiben
                    myMC.setMcRwProgess( 100 );
                    myMC.setMcRwInfo("write to file: finished");
                }
                break;
 
           case c.HEX:
                if( bLesen ) {
                    System.out.println("Firmware (HEX) Gelesen: "+myMC.ReturnString.length()+" Bytes bLesen="+bLesen);
                    // Dateinamensfeld fuer Updates wurde bereits gesetzt und der Inhalt steht in ReturnString
                    myMC.updateTabs();
                    if( myMC.ReturnString.length() == 0 ) {
                        myMC.setFocusDateiAuswahl();
                    } else {
                        myMC.setFocusUpdStart();
                    }
                }
                break;
             default:
                System.out.println("WARNING: SaveOpenDialog: Unhandled Type on close Decoder="+Decoder);
        }
    }//GEN-LAST:event_formWindowClosed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        String s = null;
        if(bLesen)
        {
            if (KTUI.bSpracheDE) {
                switch (Decoder) {
                    case c.LD_G30:
                        jTextField1.setText("Einstellungen für LD-G-30 laden...");
                        s = KlarTextUI.gsConfigDirectory + "LastFiles30.lfs";
                        break;

                    case c.LD_G31:
                        jTextField1.setText("Einstellungen für LD-G-31 laden...");
                        s = KlarTextUI.gsConfigDirectory + "LastFiles31.lfs";
                        break;

                    case c.LD_G32:
                        jTextField1.setText("Einstellungen für LD-G-32 laden...");
                        s = KlarTextUI.gsConfigDirectory + "LastFiles32.lfs";
                        break;

                    case c.LD_G32_2:
                        jTextField1.setText("Einstellungen für LD-G-32.2 laden...");
                        s = KlarTextUI.gsConfigDirectory + "LastFiles32.lfs";
                        break;

                    case c.LD_W32:
                        s = KlarTextUI.gsConfigDirectory + "LastFiles32.lfs";
                        jTextField1.setText("Einstellungen für LD-W-32 laden...");
                        break;

                    case c.LD_W32_2:
                        s = KlarTextUI.gsConfigDirectory + "LastFiles32.lfs";
                        jTextField1.setText("Einstellungen für LD-W-32.2 laden...");
                        break;

                    case c.FD_R:
                        jTextField1.setText("Einstellungen für FD-R basic laden...");
                        s = KlarTextUI.gsConfigDirectory + "LastFilesFDR.lfs";
                        break;

                    case c.FD_R2:
                        jTextField1.setText("Einstellungen für FD-R basic 2 laden...");
                        s = KlarTextUI.gsConfigDirectory + "LastFilesFDR2.lfs";
                        break;

                    case c.LD_W33:
                        s = KlarTextUI.gsConfigDirectory + "LastFiles33.lfs";
                        jTextField1.setText("Einstellungen für LD-W-33 laden...");
                        break;

                    case c.LD_G33:
                        s = KlarTextUI.gsConfigDirectory + "LastFiles33.lfs";
                        jTextField1.setText("Einstellungen für LD-G-33 laden...");
                        break;

                    case c.LD_G34:
                        s = KlarTextUI.gsConfigDirectory + "LastFiles34.lfs";
                        jTextField1.setText("Einstellungen für LD-G-34 laden...");
                        break;

                    case c.LD_G31Plus:
                        s = KlarTextUI.gsConfigDirectory + "LastFiles31plus.lfs";
                        jTextField1.setText("Einstellungen für LD-G-31plus laden...");
                        break;

                    case c.LD_G33Plus:
                        s = KlarTextUI.gsConfigDirectory + "LastFiles33plus.lfs";
                        jTextField1.setText("Einstellungen für LD-G-33plus laden...");
                        break;

                    case c.LD_G34Plus:
                        s = KlarTextUI.gsConfigDirectory + "LastFiles34plus.lfs";
                        jTextField1.setText("Einstellungen für LD-G-34plus laden...");
                        break;

                    case c.LD_G36Plus:
                        s = KlarTextUI.gsConfigDirectory + "LastFiles36plus.lfs";
                        jTextField1.setText("Einstellungen für LD-G-36plus laden...");
                        break;

                    case c.FD_M:
                        s = KlarTextUI.gsConfigDirectory + "LastFilesFDM.lfs";
                        jTextField1.setText("Einstellungen für FD-M laden...");
                        break;

                    case c.FD_R_ex:
                        s = KlarTextUI.gsConfigDirectory + "LastFilesFDE.lfs";
                        jTextField1.setText("Einstellungen für FD-R extended laden...");
                        break;

                    case c.FD_XL:
                        s = KlarTextUI.gsConfigDirectory + "LastFilesFDM.lfs";
                        jTextField1.setText("Einstellungen für FD-XL laden...");
                        break;

                    case c.FD_LED:
                        s = KlarTextUI.gsConfigDirectory + "LastFilesFDLED.lfs";
                        jTextField1.setText("Einstellungen für FD-LED laden...");
                        break;

                    case c.WIB_30:
                        s = KlarTextUI.gsConfigDirectory + "LastFilesWIB.lfs";
                        jTextField1.setText("Einstellungen für WIB-31/32/33 laden...");
                        break;

                    case c.KENN:
                        jTextField1.setText("Motor-Kennlinie laden...");
                        s = KlarTextUI.gsConfigDirectory + "LastFilesKenn.lfs";
                        break;

                    case c.M3:
                        jTextField1.setText("M3 UIDs laden...");
                        s = KlarTextUI.gsConfigDirectory + "LastFilesM3uid.lfs";
                        break;

                    case c.MC:
                        jTextField1.setText("MC Konfiguration laden...");
                        s = KlarTextUI.gsConfigDirectory + "LastFilesMC.lfs";
                        break;

                    case c.HEX:
                        jTextField1.setText("Firmware-Datei laden...");
                        s = KlarTextUI.gsConfigDirectory + "LastFilesFirmware.lfs";
                        break;
                }
            } else {
                switch (Decoder) {
                    case c.LD_G30:
                        s = KlarTextUI.gsConfigDirectory + "LastFiles30.lfs";
                        jTextField1.setText("load CVs for LD-G-30...");
                        break;

                    case c.LD_G31:
                        s = KlarTextUI.gsConfigDirectory + "LastFiles31.lfs";
                        jTextField1.setText("load CVs for LD-G-31...");
                        break;

                    case c.LD_W32:
                        s = KlarTextUI.gsConfigDirectory + "LastFiles32.lfs";
                        jTextField1.setText("load CVs for LD-W-32...");
                        break;

                    case c.LD_W32_2:
                        s = KlarTextUI.gsConfigDirectory + "LastFiles32.lfs";
                        jTextField1.setText("load CVs for LD-W-32.2...");
                        break;

                    case c.LD_G32:
                        s = KlarTextUI.gsConfigDirectory + "LastFiles32.lfs";
                        jTextField1.setText("load CVs for LD-G-32...");
                        break;

                    case c.LD_G32_2:
                        s = KlarTextUI.gsConfigDirectory + "LastFiles32.lfs";
                        jTextField1.setText("load CVs for LD-G-32.2...");
                        break;

                    case c.FD_R:
                        s = KlarTextUI.gsConfigDirectory + "LastFilesFDR.lfs";
                        jTextField1.setText("load CVs for FD-R basic...");
                        break;

                    case c.FD_R2:
                        s = KlarTextUI.gsConfigDirectory + "LastFilesFDR2.lfs";
                        jTextField1.setText("load CVs for FD-R basic 2...");
                        break;

                    case c.LD_W33:
                        s = KlarTextUI.gsConfigDirectory + "LastFiles33.lfs";
                        jTextField1.setText("load CVs for LD-W-33...");
                        break;

                    case c.LD_G33:
                        s = KlarTextUI.gsConfigDirectory + "LastFiles33.lfs";
                        jTextField1.setText("load CVs for LD-G-33...");
                        break;

                    case c.LD_G34:
                        s = KlarTextUI.gsConfigDirectory + "LastFiles34.lfs";
                        jTextField1.setText("load CVs for LD-G-34...");
                        break;

                    case c.LD_G31Plus:
                        s = KlarTextUI.gsConfigDirectory + "LastFiles31plus.lfs";
                        jTextField1.setText("load CVs for LD-G-31plus...");
                        break;

                    case c.LD_G33Plus:
                        s = KlarTextUI.gsConfigDirectory + "LastFiles33plus.lfs";
                        jTextField1.setText("load CVs for LD-G-33plus...");
                        break;

                    case c.LD_G34Plus:
                        s = KlarTextUI.gsConfigDirectory + "LastFiles34plus.lfs";
                        jTextField1.setText("load CVs for LD-G-34plus...");
                        break;

                    case c.LD_G36Plus:
                        s = KlarTextUI.gsConfigDirectory + "LastFiles36plus.lfs";
                        jTextField1.setText("load CVs for LD-G-36plus...");
                        break;

                    case c.FD_M:
                        s = KlarTextUI.gsConfigDirectory + "LastFilesFDM.lfs";
                        jTextField1.setText("load CVs for FD-M...");
                        break;

                    case c.FD_R_ex:
                        s = KlarTextUI.gsConfigDirectory + "LastFilesFDE.lfs";
                        jTextField1.setText("load CVs for FD-R extended...");
                        break;

                    case c.FD_XL:
                        s = KlarTextUI.gsConfigDirectory + "LastFilesFDM.lfs";
                        jTextField1.setText("load CVs for FD-XL...");
                        break;

                    case c.FD_LED:
                        s = KlarTextUI.gsConfigDirectory + "LastFilesFDLED.lfs";
                        jTextField1.setText("load CVs for FD-LED...");
                        break;

                    case c.WIB_30:
                        s = KlarTextUI.gsConfigDirectory + "LastFilesWIB.lfs";
                        jTextField1.setText("load CVs for WIB-31/32/33...");
                        break;

                    case c.KENN:
                        s = KlarTextUI.gsConfigDirectory + "LastFilesKenn.lfs";
                        jTextField1.setText("load motor curve...");
                        break;

                    case c.M3:
                        s = KlarTextUI.gsConfigDirectory + "LastFilesM3uid.lfs";
                        jTextField1.setText("load M3 UIDs...");
                        break;

                    case c.MC:
                        s = KlarTextUI.gsConfigDirectory + "LastFilesMC.lfs";
                        jTextField1.setText("load M3 config...");
                        break;

                    case c.HEX:
                        s = KlarTextUI.gsConfigDirectory + "LastFilesFirmware.lfs";
                        jTextField1.setText("load firmware file...");
                        break;
                }
            }
            FileReader inputStream;
            try {
                inputStream = new FileReader(s);
                char[] cbuf = new char[10000];
                try {
                    int i = inputStream.read(cbuf);
                    if (i > 0)
                    {
                        s = new String(cbuf);
                        String[] strArr = s.substring(0,i).split("\r\n");
                        int n = strArr.length;
                        for(; n > 0; n--)
                        {
                            jLastFiles.addItem(strArr[n-1]);
                        }
                    }
                    inputStream.close();
                } catch (IOException ex) {
                    System.out.println("SOD file read error filename=["+s+"]" );
                    // Logger.getLogger(SaveOpenDialog.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (FileNotFoundException ex) {
                System.out.println("SOD file not found filename=["+s+"]" );
                // Logger.getLogger(SaveOpenDialog.class.getName()).log(Level.SEVERE, null, ex);
                // jFileChooser1.setSelectedFile(new File(decoderThis.KTUI.gsSaveOpenDirectory + "/" + decoderThis.KTUI.gsSaveOpenFilename));
                jFileChooser1.setSelectedFile(new File(""));
            }
        }
        else
        {
            if (KTUI.bSpracheDE) {
                switch (Decoder) {
                    case c.LD_G30:
                        s = KlarTextUI.gsConfigDirectory + "LastFiles30.lfs";
                        jTextField1.setText("Einstellungen für LD-G-30 speichern...");
                        break;

                    case c.LD_G31:
                        s = KlarTextUI.gsConfigDirectory + "LastFiles31.lfs";
                        jTextField1.setText("Einstellungen für LD-G-31 speichern...");
                        break;

                    case c.LD_W32:
                        s = KlarTextUI.gsConfigDirectory + "LastFiles32.lfs";
                        jTextField1.setText("Einstellungen für LD-W-32 speichern...");
                        break;

                    case c.LD_W32_2:
                        s = KlarTextUI.gsConfigDirectory + "LastFiles32.lfs";
                        jTextField1.setText("Einstellungen für LD-W-32.2 speichern...");
                        break;

                    case c.LD_G32:
                        s = KlarTextUI.gsConfigDirectory + "LastFiles32.lfs";
                        jTextField1.setText("Einstellungen für LD-G-32 speichern...");
                        break;

                    case c.LD_G32_2:
                        s = KlarTextUI.gsConfigDirectory + "LastFiles32.lfs";
                        jTextField1.setText("Einstellungen für LD-G-32.2 speichern...");
                        break;

                    case c.FD_R:
                        s = KlarTextUI.gsConfigDirectory + "LastFilesFDR.lfs";
                        jTextField1.setText("Einstellungen für FD-R basic speichern...");
                        break;

                    case c.FD_R2:
                        s = KlarTextUI.gsConfigDirectory + "LastFilesFDR2.lfs";
                        jTextField1.setText("Einstellungen für FD-R basic 2 speichern...");
                        break;

                    case c.LD_W33:
                        s = KlarTextUI.gsConfigDirectory + "LastFiles33.lfs";
                        jTextField1.setText("Einstellungen für LD-W-33 speichern...");
                        break;

                    case c.LD_G33:
                        s = KlarTextUI.gsConfigDirectory + "LastFiles33.lfs";
                        jTextField1.setText("Einstellungen für LD-G-33 speichern...");
                        break;

                    case c.LD_G34:
                        s = KlarTextUI.gsConfigDirectory + "LastFiles33.lfs";
                        jTextField1.setText("Einstellungen für LD-G-34 speichern...");
                        break;

                    case c.LD_G31Plus:
                        s = KlarTextUI.gsConfigDirectory + "LastFiles31plus.lfs";
                        jTextField1.setText("Einstellungen für LD-G-31plus speichern...");
                        break;

                    case c.LD_G33Plus:
                        s = KlarTextUI.gsConfigDirectory + "LastFiles33plus.lfs";
                        jTextField1.setText("Einstellungen für LD-G-33plus speichern...");
                        break;

                    case c.LD_G34Plus:
                        s = KTUI.gsConfigDirectory + "LastFiles34plus.lfs";
                        jTextField1.setText("Einstellungen für LD-G-34plus speichern...");
                        break;

                    case c.LD_G36Plus:
                        s = KlarTextUI.gsConfigDirectory + "LastFiles36plus.lfs";
                        jTextField1.setText("Einstellungen für LD-G-36plus speichern...");
                        break;

                    case c.FD_M:
                        s = KlarTextUI.gsConfigDirectory + "LastFilesFDM.lfs";
                        jTextField1.setText("Einstellungen für FD-M speichern...");
                        break;

                    case c.FD_R_ex:
                        s = KlarTextUI.gsConfigDirectory + "LastFilesFDE.lfs";
                        jTextField1.setText("Einstellungen für FD-R extended speichern...");
                        break;

                    case c.FD_XL:
                        s = KlarTextUI.gsConfigDirectory + "LastFilesFDM.lfs";
                        jTextField1.setText("Einstellungen für FD-XL speichern...");
                        break;

                    case c.FD_LED:
                        s = KlarTextUI.gsConfigDirectory + "LastFilesFDLED.lfs";
                        jTextField1.setText("Einstellungen für FD-LED speichern...");
                        break;

                    case c.WIB_30:
                        s = KlarTextUI.gsConfigDirectory + "LastFilesWIB.lfs";
                        jTextField1.setText("Einstellungen für WIB-31/32/33 speichern...");
                        break;

                    case c.KENN:
                        s = KlarTextUI.gsConfigDirectory + "LastFilesKenn.lfs";
                        jTextField1.setText("Motor-Kennlinie speichern...");
                        break;

                    case c.M3:
                        s = KlarTextUI.gsConfigDirectory + "LastFilesM3uid.lfs";
                        jTextField1.setText("M3 UIDs speichern...");
                        break;

                    case c.MC:
                        s = KlarTextUI.gsConfigDirectory + "LastFilesMC.lfs";
                        jTextField1.setText("MC Konfiguration speichern...");
                        break;

                    case c.HEX:
                        // keine speichern von Firmwaredateien
                        break;
                }
            } else {
                switch (Decoder) {
                    case c.LD_G30:
                        s = KlarTextUI.gsConfigDirectory + "LastFiles30.lfs";
                        jTextField1.setText("save CVs for LD-G-30...");
                        break;

                    case c.LD_G31:
                        s = KlarTextUI.gsConfigDirectory + "LastFiles31.lfs";
                        jTextField1.setText("save CVs for LD-G-31...");
                        break;

                    case c.LD_W32:
                        s = KlarTextUI.gsConfigDirectory + "LastFiles32.lfs";
                        jTextField1.setText("save CVs for LD-W-32...");
                        break;

                    case c.LD_W32_2:
                        s = KlarTextUI.gsConfigDirectory + "LastFiles32.lfs";
                        jTextField1.setText("save CVs for LD-W-32.2...");
                        break;

                    case c.LD_G32:
                        s = KlarTextUI.gsConfigDirectory + "LastFiles32.lfs";
                        jTextField1.setText("save CVs for LD-G-32...");
                        break;

                    case c.LD_G32_2:
                        s = KlarTextUI.gsConfigDirectory + "LastFiles32.lfs";
                        jTextField1.setText("save CVs for LD-G-32.2...");
                        break;

                    case c.FD_R:
                        s = KlarTextUI.gsConfigDirectory + "LastFilesFDR.lfs";
                        jTextField1.setText("save CVs for FD-R basic...");
                        break;

                    case c.FD_R2:
                        s = KlarTextUI.gsConfigDirectory + "LastFilesFDR2.lfs";
                        jTextField1.setText("save CVs for FD-R basic 2...");
                        break;

                    case c.LD_W33:
                        s = KlarTextUI.gsConfigDirectory + "LastFiles33.lfs";
                        jTextField1.setText("save CVs for LD-W-33...");
                        break;

                    case c.LD_G33:
                        s = KlarTextUI.gsConfigDirectory + "LastFiles33.lfs";
                        jTextField1.setText("save CVs for LD-G-33...");
                        break;

                    case c.LD_G34:
                        s = KlarTextUI.gsConfigDirectory + "LastFiles34.lfs";
                        jTextField1.setText("save CVs for LD-G-34...");
                        break;

                    case c.LD_G31Plus:
                        s = KlarTextUI.gsConfigDirectory + "LastFiles31plus.lfs";
                        jTextField1.setText("save CVs for LD-G-31plus...");
                        break;

                    case c.LD_G33Plus:
                        s = KlarTextUI.gsConfigDirectory + "LastFiles33plus.lfs";
                        jTextField1.setText("save CVs for LD-G-33plus...");
                        break;

                    case c.LD_G34Plus:
                        s = KlarTextUI.gsConfigDirectory + "LastFiles34plus.lfs";
                        jTextField1.setText("save CVs for LD-G-34plus...");
                        break;

                    case c.LD_G36Plus:
                        s = KlarTextUI.gsConfigDirectory + "LastFiles36plus.lfs";
                        jTextField1.setText("save CVs for LD-G-36plus...");
                        break;

                    case c.FD_M:
                        s = KlarTextUI.gsConfigDirectory + "LastFilesFDM.lfs";
                        jTextField1.setText("save CVs for FD-M...");
                        break;

                    case c.FD_R_ex:
                        s = KlarTextUI.gsConfigDirectory + "LastFilesFDE.lfs";
                        jTextField1.setText("save CVs for FD-R extended...");
                        break;

                    case c.FD_XL:
                        s = KlarTextUI.gsConfigDirectory + "LastFilesFDM.lfs";
                        jTextField1.setText("save CVs for FD-XL...");
                        break;

                    case c.FD_LED:
                        s = KlarTextUI.gsConfigDirectory + "LastFilesFDLED.lfs";
                        jTextField1.setText("save CVs for FD-LED...");
                        break;

                    case c.WIB_30:
                        s = KlarTextUI.gsConfigDirectory + "LastFilesWIB.lfs";
                        jTextField1.setText("save CVs for WIB-31/32/33...");
                        break;

                    case c.KENN:
                        s = KlarTextUI.gsConfigDirectory + "LastFilesKenn.lfs";
                        jTextField1.setText("save motor curve...");
                        break;

                    case c.M3:
                        s = KlarTextUI.gsConfigDirectory + "LastFilesM3uid.lfs";
                        jTextField1.setText("save M3 UIDs...");
                        break;

                    case c.MC:
                        s = KlarTextUI.gsConfigDirectory + "LastFilesMC.lfs";
                        jTextField1.setText("save MC config...");
                        break;

                    case c.HEX:
                        // no firmware save
                        break;
                }
            }
            jLastFiles.setVisible(false);
            jLabel1.setVisible(false);
        }
    }//GEN-LAST:event_formComponentShown

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JComboBox<String> jLastFiles;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
