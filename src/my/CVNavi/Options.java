/*
 * Options.java
 *
 * Created on 24.01.2009, 18:34:06
 *
 * @author Kersten Tams Copyright 2009-2020
 * @author Lothar Roth  Copyright 2012-2020
 *
 */

package my.CVNavi;

import java.awt.Frame;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ktams
 */
public class Options extends javax.swing.JFrame {

    public CVNavi CVNavi;
    private boolean bSpracheNeu;
    private ResourceBundle bundle;

    /** Creates new form Options */
    public Options(CVNavi cvnaviThis) {
        if( cvnaviThis == null ) {
            return;
        }
        CVNavi = cvnaviThis;
        if( CVNavi.frameInstanceOPTIONS != null ) {
            CVNavi.frameInstanceOPTIONS.toFront();
            CVNavi.frameInstanceOPTIONS.repaint();
            return;
        }
        CVNavi.frameInstanceOPTIONS = this;

        initComponents();
        bundle = java.util.ResourceBundle.getBundle("my.CVNavi/Bundle");
        bSpracheNeu = CVNavi.bSpracheDE;
        //Datei lesen

        jZentrale.setSelectedIndex(CVNavi.getZentrale());
        // Schnittstellen bestimmen (evtl. doch nur unter Windows ?)

        Logger lg = Logger.getGlobal();
        String sl = "";
        String s1 = "";
        String s2 = "";
        if( lg != null ) {
            sl = lg.getName();
            Level lvPre = lg.getLevel();
            if( lvPre != null ) {
                s1 = lvPre.toString();
            }
            lg.setLevel(Level.ALL);
            Level lvPost = lg.getLevel();
            if( lvPost != null ) {
                s2 = lvPost.toString();
            }
        }
        System.out.println("Logger["+sl+"] lvPre["+s1+"] lvPost["+s2+"]");

        // Schnittstellenliste aufbauen
        // SchnitsstellenVorauswahl setzen
        jSchnittstelle.setSelectedItem(CVNavi.gsSchnittstelle);
        jBaudRate.setSelectedItem(Integer.toString(CVNavi.gsBaudRate));
        jMetal.setSelected(false);
        switch (CVNavi.gsLookAndFeel) {
            case "Metal":
                jMetal.setSelected(true);
                break;
            case "Motif":
                jMotif.setSelected(true);
                break;
            case "Nimbus":
                jNimbus.setSelected(true);
                break;
            case "Windows":
                jWindows.setSelected(true);
                break;
            default:
                jMetal.setSelected(true);
        }

        setLocationRelativeTo(cvnaviThis);
        setVisible(true);
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
        jPanelOptions = new javax.swing.JPanel();
        jZentrale = new javax.swing.JComboBox();
        jSchnittstelle = new javax.swing.JComboBox();
        jBaudRate = new javax.swing.JComboBox();
        jButtonSaveAndClose = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jMetal = new javax.swing.JRadioButton();
        jMotif = new javax.swing.JRadioButton();
        jNimbus = new javax.swing.JRadioButton();
        jWindows = new javax.swing.JRadioButton();
        jButtonAbbrechen = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("my/CVNavi/Bundle"); // NOI18N
        jPanelOptions.setBorder(javax.swing.BorderFactory.createTitledBorder(null, bundle.getString("Options.jPanelOptions.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        jPanelOptions.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jZentrale.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jZentrale.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "OpenDCC", "Intellibox", "MasterControl" }));
        jZentrale.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("Options.jZentrale.border.title"))); // NOI18N

        jSchnittstelle.setEditable(true);
        jSchnittstelle.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jSchnittstelle.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "COM1", "COM2", "COM3", "COM4", "COM5", "COM6", "COM7", "COM8", "COM9", "/dev/ttyS0", "/dev/ttyS1", "/dev/ttyS2", "/dev/ttyS3", "/dev/ttyS4", "/dev/ttyS5", "/dev/ttyS6", "/dev/ttyS7", "/dev/ttyUSB0", "/dev/ttyUSB1", "/dev/ttyUSB2", "/dev/ttyUSB3", "/dev/ttyUSB4", "/dev/ttyUSB5", "/dev/ttyUSB6", "/dev/ttyUSB7" }));
        jSchnittstelle.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("Options.jSchnittstelle.border.title"))); // NOI18N

        jBaudRate.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBaudRate.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1200", "2400", "4800", "9600", "14400", "19200", "38400", "57600" }));
        jBaudRate.setSelectedIndex(1);
        jBaudRate.setToolTipText(bundle.getString("Options.jBaudRate.toolTipText")); // NOI18N
        jBaudRate.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("Options.jBaudRate.border.title"))); // NOI18N

        jButtonSaveAndClose.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButtonSaveAndClose.setText(bundle.getString("Options.jButtonSaveAndClose.text")); // NOI18N
        jButtonSaveAndClose.setToolTipText(bundle.getString("Options.jButtonSaveAndClose.toolTipText")); // NOI18N
        jButtonSaveAndClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveAndCloseActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText(bundle.getString("Options.jLabel1.text")); // NOI18N

        buttonGroup1.add(jMetal);
        jMetal.setSelected(true);
        jMetal.setText(bundle.getString("Options.jMetal.text")); // NOI18N

        buttonGroup1.add(jMotif);
        jMotif.setText(bundle.getString("Options.jMotif.text")); // NOI18N

        buttonGroup1.add(jNimbus);
        jNimbus.setText(bundle.getString("Options.jNimbus.text")); // NOI18N
        jNimbus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jNimbusActionPerformed(evt);
            }
        });

        buttonGroup1.add(jWindows);
        jWindows.setText(bundle.getString("Options.jWindows.text")); // NOI18N

        jButtonAbbrechen.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButtonAbbrechen.setText(bundle.getString("Options.jButtonAbbrechen.text")); // NOI18N
        jButtonAbbrechen.setToolTipText(bundle.getString("Options.jButtonAbbrechen.toolTipText")); // NOI18N
        jButtonAbbrechen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAbbrechenActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelOptionsLayout = new javax.swing.GroupLayout(jPanelOptions);
        jPanelOptions.setLayout(jPanelOptionsLayout);
        jPanelOptionsLayout.setHorizontalGroup(
            jPanelOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelOptionsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSchnittstelle, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBaudRate, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jZentrale, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelOptionsLayout.createSequentialGroup()
                        .addGroup(jPanelOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jMetal)
                            .addComponent(jMotif)
                            .addComponent(jNimbus)
                            .addComponent(jWindows)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 111, Short.MAX_VALUE))
                    .addComponent(jButtonSaveAndClose, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonAbbrechen, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelOptionsLayout.setVerticalGroup(
            jPanelOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelOptionsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jZentrale, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSchnittstelle, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jBaudRate, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jMetal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jMotif)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jNimbus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jWindows)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jButtonAbbrechen)
                .addGap(18, 18, 18)
                .addComponent(jButtonSaveAndClose)
                .addContainerGap())
        );

        jBaudRate.getAccessibleContext().setAccessibleName(bundle.getString("Options.jBaudRate.AccessibleContext.accessibleName")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelOptions, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelOptions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSaveAndCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveAndCloseActionPerformed
        Properties prop = new Properties();
        CVNavi.bSpracheDE = bSpracheNeu;
        Boolean localChanges = false;


        try {
            // Load original file (with all options)
            prop.loadFromXML(new FileInputStream(CVNavi.gsConfigFilename));


            int oldZentrale = CVNavi.getZentrale();
            int newZentrale = jZentrale.getSelectedIndex();
            if( oldZentrale != newZentrale ) {
                CVNavi.setZentrale(newZentrale);
                localChanges = true;
            }
            if( ! CVNavi.gsSchnittstelle.equalsIgnoreCase(""+jSchnittstelle.getSelectedItem()) ) {
                // Schnittstelle 
                CVNavi.gsSchnittstelle = (String) jSchnittstelle.getSelectedItem();
                localChanges = true;
                CVNavi.gsSchnittstelle_was_forced = false;
            }
            int newBaudRate = Integer.parseInt( (String) jBaudRate.getSelectedItem() );
            if( CVNavi.gsBaudRate != newBaudRate ) {
                CVNavi.gsBaudRate = newBaudRate;
                localChanges = true;
            }
            
            CVNavi.fillMenuSelection();
            //set the properties value
            prop.setProperty("Zentrale", CVNavi.gsZentrale );
            prop.setProperty("Schnittstelle", CVNavi.gsSchnittstelle );
            prop.setProperty("BaudRate", Integer.toString( CVNavi.gsBaudRate )) ;

            if(     jMetal.isSelected())   prop.setProperty("LookAndFeel","Metal");
            else if(jMotif.isSelected())   prop.setProperty("LookAndFeel","Motif");
            else if(jNimbus.isSelected())  prop.setProperty("LookAndFeel","Nimbus");
            else if(jWindows.isSelected()) prop.setProperty("LookAndFeel","Windows");
            else prop.setProperty("LookAndFeel","Metal");

            if( ! prop.getProperty("LookAndFeel").equals( CVNavi.gsLookAndFeel )) {
                CVNavi.gsLookAndFeel = prop.getProperty("LookAndFeel");
                MsgBox messageBox = new MsgBox( (Frame) this.getParent(), true, this);
                messageBox.jLabel1.setText(bundle.getString("Hinweis"));
                messageBox.jLabel2.setText(bundle.getString("Options.newLook"));
                messageBox.jLabel3.setText(bundle.getString("Options.newFeel"));
                // messageBox.MBtimeout = 5000;
                messageBox.setVisible(true);
            }

            //save properties to project root folder
            prop.storeToXML(new FileOutputStream(CVNavi.gsConfigFilename), CVNavi.gsConfigComment);
        } catch (FileNotFoundException ex) {
            // Die XML-Config-Datei wird bei Bedarf beim Start des Programms in main() angelegt
            // deshalb hier keine weitere Behandlung
            Logger.getLogger(Options.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Options.class.getName()).log(Level.SEVERE, null, ex);
        }

        if( localChanges ) {
            CVNavi.verifyZentrale(true);
        }

        this.dispose();
    }//GEN-LAST:event_jButtonSaveAndCloseActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        if( bSpracheNeu != CVNavi.bSpracheDE) {
            int n = CVNavi.getDecoderChooser();
            bSpracheNeu = CVNavi.bSpracheDE;
            CVNavi.fillMenuSelection();
            formWindowOpened(null);
            repaint();
            CVNavi.setDecoderChooser(n);
        }
        CVNavi.frameInstanceOPTIONS = null;
        CVNavi.setFocus();
    }//GEN-LAST:event_formWindowClosed

    private void jNimbusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jNimbusActionPerformed
            MsgBox messageBox = new MsgBox( (Frame) this.getParent(), true, this);
            messageBox.jLabel1.setText(bundle.getString("Hinweis"));
            messageBox.jLabel2.setText(bundle.getString("Options.noLook"));
            messageBox.jLabel3.setText(bundle.getString("Options.noFeel"));
            messageBox.setVisible(true);
    }//GEN-LAST:event_jNimbusActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
/*        if(bSpracheNeu)
        {
            
            TitledBorder TB = (TitledBorder)jPanelOptions.getBorder();
            TB.setTitle("Optionen");
            TB = (TitledBorder)jZentrale.getBorder();
            TB.setTitle("Zentrale");
            TB = (TitledBorder)jSchnittstelle.getBorder();
            TB.setTitle("Schnittstelle");
            TB = (TitledBorder)jBaudRate.getBorder();
            TB.setTitle("Baudrate (seriell)");
            jButtonSaveAndClose.setText("Sichern");
            jButtonAbbrechen.setText("Abbrechen");
        }else {
            this.setTitle( "Options" );
            TitledBorder TB = (TitledBorder)jPanelOptions.getBorder();
            TB.setTitle("Options");
            TB = (TitledBorder)jZentrale.getBorder();
            TB.setTitle("control unit");
            TB = (TitledBorder)jSchnittstelle.getBorder();
            TB.setTitle("interface");
            TB = (TitledBorder)jBaudRate.getBorder();
            TB.setTitle("baud rate (serial)");
            jButtonSaveAndClose.setText("Save");
            jButtonAbbrechen.setText("Cancel");
        }*/
    }//GEN-LAST:event_formWindowOpened

    private void jButtonAbbrechenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAbbrechenActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonAbbrechenActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox jBaudRate;
    private javax.swing.JButton jButtonAbbrechen;
    private javax.swing.JButton jButtonSaveAndClose;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JRadioButton jMetal;
    private javax.swing.JRadioButton jMotif;
    private javax.swing.JRadioButton jNimbus;
    private javax.swing.JPanel jPanelOptions;
    private javax.swing.JComboBox jSchnittstelle;
    private javax.swing.JRadioButton jWindows;
    private javax.swing.JComboBox jZentrale;
    // End of variables declaration//GEN-END:variables

}
