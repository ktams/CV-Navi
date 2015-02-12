/*
 * jInfo.java
 *
 * Created on 20.06.2009, 16:16:40
 */

package my.KlarText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import static my.KlarText.KlarTextUI.debugLevel;

/**
 *
 * @author ktams
 */
public class jInfo extends javax.swing.JFrame {

    public KlarTextUI KTUI;

    /** Creates new form jInfo */
    public jInfo( KlarTextUI ktuiThis ) {
        if( ktuiThis == null ) {
            return;
        }
        KTUI = ktuiThis;
        if( KTUI.frameInstanceINFO != null ) {
            KTUI.frameInstanceINFO.toFront();
            KTUI.frameInstanceINFO.repaint();
            return;
        }
        KTUI.frameInstanceINFO = this;

        initComponents();
        setLocationRelativeTo(ktuiThis);
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

        jOK = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jInfoText = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jOK.setText("OK");
        jOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jOKActionPerformed(evt);
            }
        });

        jInfoText.setEditable(false);
        jInfoText.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jScrollPane1.setViewportView(jInfoText);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jOK, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(181, 181, 181))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jOK)
                .addGap(6, 6, 6))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jOKActionPerformed
        KTUI.frameInstanceINFO = null;
        this.dispose();
    }//GEN-LAST:event_jOKActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        InputStream is = null;
        BufferedReader br = null;
        int bytesRead = 0 ;
        try {
            char ac[] = new char[10000];
            int n;
            Class c = getClass();
            is = c.getResourceAsStream ("/CV_Navi.info");

            if( is != null) {
                br = new BufferedReader(new InputStreamReader(is));
                try {
                    // n = inputStream.read(ac, 0, 10000);
                    n = br.read(ac, 0, 10000);
                    ac[n+1] = 0;
                    bytesRead += n;
                } catch (IOException ex) {
                    jInfoText.setText("Info-Datei \"CV_Navi.info\" nicht gefunden.\nA\n"+ex);
                    return;
                }
                finally{
                    if( debugLevel >= 3 ) {
                        System.out.println("jInfo FINALLY A");
                    }
                }
                try {
                    br.close();
                } catch (IOException ex) {
                    jInfoText.setText("Info-Datei \"CV_Navi.info\" nicht gefunden.\nB\n"+ex);
                    return;
                }
                String str1 = new String(ac);
                jInfoText.setText(str1.substring(0, n));
                // Cursor ganz oben Positionieren
                jInfoText.setSelectionStart(0);
                jInfoText.setSelectionEnd(0);
            }

        }
        finally{
            if( debugLevel >= 3 ) {
                System.out.println("jInfo FINALLY B");
            }
            if( bytesRead == 0 )
                jInfoText.setText("Info-Datei \"CV_Navi.info\" nicht gefunden oder leer.");
            if (br != null) {
                try {
                    br.close();
                } catch (IOException ex) {
                    Logger.getLogger(jInfo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException ex) {
                    Logger.getLogger(jInfo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        setTitle( "Info" );
        jOK.grabFocus();
    }//GEN-LAST:event_formWindowOpened

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        KTUI.frameInstanceINFO = null;
        KTUI.setFocus();
    }//GEN-LAST:event_formWindowClosed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextPane jInfoText;
    private javax.swing.JButton jOK;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

}
