/*
 * DecoderFilter.java
 *
 *
 * @author Kersten Tams Copyright 2009-2025
 * @author Lothar Roth  Copyright 2012-2025
 *
 */

package my.CVNavi;

import static my.CVNavi.CVNavi.debugLevel;

/**
 *
 * @author Lothar
 */
public class DecTest extends javax.swing.JDialog {

    /**
     * Creates new form DecTest
     */
    public int DecAddr = 3;
    private String F1_F8 = "XF 3, 0, 0, 0, 0, 0, 0, 0, 0}\r";
    private byte F0 = 0, F1 = 0, F2 = 0, F3 = 0, F4 = 0, F5 = 0, F6 = 0, F7 = 0, F8 = 0, F9 = 0, F10 = 0, F11 = 0, F12 = 0;
    private byte bDir = 1;
    private TwoWaySerialComm Com = null;
    private CVNavi CVNavi = null;

    public DecTest(java.awt.Frame parent, boolean modal, CVNavi cvnavi) {
        super(parent, modal);
        initComponents();
        if( CVNavi == null && cvnavi != null )
            CVNavi = cvnavi;
        pack();
        setLocationRelativeTo(parent);
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
        jPanel1 = new javax.swing.JPanel();
        jButtonExit = new javax.swing.JButton();
        jF0 = new javax.swing.JCheckBox();
        jF1 = new javax.swing.JCheckBox();
        jF2 = new javax.swing.JCheckBox();
        jF3 = new javax.swing.JCheckBox();
        jF4 = new javax.swing.JCheckBox();
        jF5 = new javax.swing.JCheckBox();
        jF6 = new javax.swing.JCheckBox();
        jF7 = new javax.swing.JCheckBox();
        jF8 = new javax.swing.JCheckBox();
        jF9 = new javax.swing.JCheckBox();
        jF10 = new javax.swing.JCheckBox();
        jF11 = new javax.swing.JCheckBox();
        jF12 = new javax.swing.JCheckBox();
        jFunktionen = new javax.swing.JLabel();
        jRichtung = new javax.swing.JLabel();
        jVor = new javax.swing.JRadioButton();
        jRueck = new javax.swing.JRadioButton();
        jDecType = new javax.swing.JLabel();
        jGeschwindigkeit = new javax.swing.JLabel();
        jSlider1 = new javax.swing.JSlider();
        jCheckBoxZentraleEinAus = new javax.swing.JCheckBox();
        jRichtung1 = new javax.swing.JLabel();
        jCV = new javax.swing.JTextField();
        jRichtung2 = new javax.swing.JLabel();
        jWertLabel = new javax.swing.JLabel();
        jWert = new javax.swing.JTextField();
        jButtonProg = new javax.swing.JButton();

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
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, bundle.getString("DecTest.jPanel1.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        jButtonExit.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButtonExit.setText(bundle.getString("DecTest.jButtonExit.text")); // NOI18N
        jButtonExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExitActionPerformed(evt);
            }
        });

        jF0.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF0.setText(bundle.getString("DecTest.jF0.text")); // NOI18N
        jF0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF0ActionPerformed(evt);
            }
        });

        jF1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF1.setText(bundle.getString("DecTest.jF1.text")); // NOI18N
        jF1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF1ActionPerformed(evt);
            }
        });

        jF2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF2.setText(bundle.getString("DecTest.jF2.text")); // NOI18N
        jF2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF2ActionPerformed(evt);
            }
        });

        jF3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF3.setText(bundle.getString("DecTest.jF3.text")); // NOI18N
        jF3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF3ActionPerformed(evt);
            }
        });

        jF4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF4.setText(bundle.getString("DecTest.jF4.text")); // NOI18N
        jF4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF4ActionPerformed(evt);
            }
        });

        jF5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF5.setText(bundle.getString("DecTest.jF5.text")); // NOI18N
        jF5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF5ActionPerformed(evt);
            }
        });

        jF6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF6.setText(bundle.getString("DecTest.jF6.text")); // NOI18N
        jF6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF6ActionPerformed(evt);
            }
        });

        jF7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF7.setText(bundle.getString("DecTest.jF7.text")); // NOI18N
        jF7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF7ActionPerformed(evt);
            }
        });

        jF8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF8.setText(bundle.getString("DecTest.jF8.text")); // NOI18N
        jF8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF8ActionPerformed(evt);
            }
        });

        jF9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF9.setText(bundle.getString("DecTest.jF9.text")); // NOI18N
        jF9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF9ActionPerformed(evt);
            }
        });

        jF10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF10.setText(bundle.getString("DecTest.jF10.text")); // NOI18N
        jF10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF10ActionPerformed(evt);
            }
        });

        jF11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF11.setText(bundle.getString("DecTest.jF11.text")); // NOI18N
        jF11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF11ActionPerformed(evt);
            }
        });

        jF12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jF12.setText(bundle.getString("DecTest.jF12.text")); // NOI18N
        jF12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jF12ActionPerformed(evt);
            }
        });

        jFunktionen.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFunktionen.setText(bundle.getString("DecTest.jFunktionen.text")); // NOI18N

        jRichtung.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRichtung.setText(bundle.getString("DecTest.jRichtung.text")); // NOI18N

        buttonGroup1.add(jVor);
        jVor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jVor.setSelected(true);
        jVor.setText(bundle.getString("DecTest.jVor.text")); // NOI18N
        jVor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jVorActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRueck);
        jRueck.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRueck.setText(bundle.getString("DecTest.jRueck.text")); // NOI18N
        jRueck.setActionCommand(bundle.getString("DecTest.jRueck.actionCommand")); // NOI18N
        jRueck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRueckActionPerformed(evt);
            }
        });

        jDecType.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jDecType.setText(bundle.getString("DecTest.jDecType.text")); // NOI18N

        jGeschwindigkeit.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jGeschwindigkeit.setText(bundle.getString("DecTest.jGeschwindigkeit.text")); // NOI18N

        jSlider1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jSlider1.setMaximum(126);
        jSlider1.setValue(0);
        jSlider1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider1StateChanged(evt);
            }
        });
        jSlider1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jSlider1MouseReleased(evt);
            }
        });

        jCheckBoxZentraleEinAus.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCheckBoxZentraleEinAus.setText(bundle.getString("DecTest.jCheckBoxZentraleEinAus.text")); // NOI18N
        jCheckBoxZentraleEinAus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxZentraleEinAusActionPerformed(evt);
            }
        });

        jRichtung1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRichtung1.setText(bundle.getString("DecTest.jRichtung1.text")); // NOI18N

        jCV.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCV.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jCV.setText(bundle.getString("DecTest.jCV.text")); // NOI18N

        jRichtung2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRichtung2.setText(bundle.getString("DecTest.jRichtung2.text")); // NOI18N

        jWertLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jWertLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jWertLabel.setText(bundle.getString("DecTest.jWertLabel.text")); // NOI18N

        jWert.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jWert.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jWert.setText(bundle.getString("DecTest.jWert.text")); // NOI18N

        jButtonProg.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButtonProg.setText(bundle.getString("DecTest.jButtonProg.text")); // NOI18N
        jButtonProg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonProgActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jF0)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jF1)
                                .addGap(18, 18, 18)
                                .addComponent(jF7))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jF2)
                                .addGap(18, 18, 18)
                                .addComponent(jF8))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jF3)
                                .addGap(18, 18, 18)
                                .addComponent(jF9))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jF4)
                                .addGap(18, 18, 18)
                                .addComponent(jF10))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jF5)
                                .addGap(18, 18, 18)
                                .addComponent(jF11))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jF6)
                                .addGap(18, 18, 18)
                                .addComponent(jF12))
                            .addComponent(jFunktionen))
                        .addGap(53, 53, 53)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRichtung)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jCheckBoxZentraleEinAus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonExit))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jVor)
                                        .addComponent(jRueck))
                                    .addGap(35, 35, 35)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jWertLabel)
                                        .addComponent(jRichtung2))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(12, 12, 12)
                                                .addComponent(jCV, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jRichtung1))
                                        .addComponent(jWert, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(jButtonProg))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jGeschwindigkeit))
                            .addComponent(jSlider1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jDecType)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jDecType)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jFunktionen)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jRichtung)
                        .addComponent(jRichtung1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jF0)
                    .addComponent(jVor)
                    .addComponent(jCV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRichtung2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jF1)
                    .addComponent(jF7)
                    .addComponent(jRueck)
                    .addComponent(jWert, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jWertLabel))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jF2)
                                    .addComponent(jF8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jF3)
                                    .addComponent(jF9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jF4)
                                    .addComponent(jF10))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jF5)
                                    .addComponent(jF11)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addComponent(jGeschwindigkeit)))
                        .addGap(2, 2, 2)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jF6)
                            .addComponent(jF12)
                            .addComponent(jCheckBoxZentraleEinAus)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jButtonProg)
                        .addGap(32, 32, 32)
                        .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonExit)))
                .addGap(31, 31, 31))
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

    private void jButtonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExitActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonExitActionPerformed

    private void jF0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF0ActionPerformed
        F0 = 0;
        bDir = 0;
        if(jVor.isSelected())
        {
            bDir = 1;
        }
        if(jF0.isSelected())
        {
            F0 = 1;
        }
        F1_F8 = "XL " + DecAddr + " " + jSlider1.getValue() + " " + F0 + " " + bDir + " " + F1 + " " + F2 + " " + F3 + " " + F4 + "\r";
        Com.write(F1_F8);
        CVNavi.flushReadBuffer(Com);
    }//GEN-LAST:event_jF0ActionPerformed

    private void jF1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF1ActionPerformed
        F1 = 0;
        if(jF1.isSelected())
        {
            F1 = 1;
        }
        F1_F8 = "XF " + DecAddr + " " + F1 + " " + F2 + " " + F3 + " " + F4 + " " + F5 + " " + F6 + " " + F7 + " " + F8 + "\r";
        Com.write(F1_F8);
        CVNavi.flushReadBuffer(Com);
    }//GEN-LAST:event_jF1ActionPerformed

    private void jF2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF2ActionPerformed
        F2 = 0;
        if(jF2.isSelected())
        {
            F2 = 1;
        }
        F1_F8 = "XF " + DecAddr + " " + F1 + " " + F2 + " " + F3 + " " + F4 + " " + F5 + " " + F6 + " " + F7 + " " + F8 + "\r";
        Com.write(F1_F8);
        CVNavi.flushReadBuffer(Com);
    }//GEN-LAST:event_jF2ActionPerformed

    private void jF3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF3ActionPerformed
        F3 = 0;
        if(jF3.isSelected())
        {
            F3 = 1;
        }
        F1_F8 = "XF " + DecAddr + " " + F1 + " " + F2 + " " + F3 + " " + F4 + " " + F5 + " " + F6 + " " + F7 + " " + F8 + "\r";
        Com.write(F1_F8);
        CVNavi.flushReadBuffer(Com);
    }//GEN-LAST:event_jF3ActionPerformed

    private void jF4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF4ActionPerformed
        F4 = 0;
        if(jF4.isSelected())
        {
            F4 = 1;
        }
        F1_F8 = "XF " + DecAddr + " " + F1 + " " + F2 + " " + F3 + " " + F4 + " " + F5 + " " + F6 + " " + F7 + " " + F8 + "\r";
        Com.write(F1_F8);
        CVNavi.flushReadBuffer(Com);
    }//GEN-LAST:event_jF4ActionPerformed

    private void jF5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF5ActionPerformed
        F5 = 0;
        if(jF5.isSelected())
        {
            F5 = 1;
        }
        F1_F8 = "XF " + DecAddr + " " + F1 + " " + F2 + " " + F3 + " " + F4 + " " + F5 + " " + F6 + " " + F7 + " " + F8 + "\r";
        Com.write(F1_F8);
        CVNavi.flushReadBuffer(Com);
    }//GEN-LAST:event_jF5ActionPerformed

    private void jF6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF6ActionPerformed
        F6 = 0;
        if(jF6.isSelected())
        {
            F6 = 1;
        }
        F1_F8 = "XF " + DecAddr + " " + F1 + " " + F2 + " " + F3 + " " + F4 + " " + F5 + " " + F6 + " " + F7 + " " + F8 + "\r";
        Com.write(F1_F8);
        CVNavi.flushReadBuffer(Com);
    }//GEN-LAST:event_jF6ActionPerformed

    private void jF7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF7ActionPerformed
        F7 = 0;
        if(jF7.isSelected())
        {
            F7 = 1;
        }
        F1_F8 = "XF " + DecAddr + " " + F1 + " " + F2 + " " + F3 + " " + F4 + " " + F5 + " " + F6 + " " + F7 + " " + F8 + "\r";
        Com.write(F1_F8);
        CVNavi.flushReadBuffer(Com);
    }//GEN-LAST:event_jF7ActionPerformed

    private void jF8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF8ActionPerformed
        F8 = 0;
        if(jF8.isSelected())
        {
            F8 = 1;
        }
        F1_F8 = "XF " + DecAddr + " " + F1 + " " + F2 + " " + F3 + " " + F4 + " " + F5 + " " + F6 + " " + F7 + " " + F8 + "\r";
        Com.write(F1_F8);
        CVNavi.flushReadBuffer(Com);
    }//GEN-LAST:event_jF8ActionPerformed

    private void jF9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF9ActionPerformed
        F9 = 0;
        if(jF9.isSelected())
        {
            F9 = 1;
        }
        F1_F8 = "XFX " + DecAddr + " " + F9 + " " + F10 + " " + F11 + " " + F12 + " 0 0\r";
        Com.write(F1_F8);
        CVNavi.flushReadBuffer(Com);
    }//GEN-LAST:event_jF9ActionPerformed

    private void jF10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF10ActionPerformed
        F10 = 0;
        if(jF10.isSelected())
        {
            F10 = 1;
        }
        F1_F8 = "XFX " + DecAddr + " " + F9 + " " + F10 + " " + F11 + " " + F12 + " 0 0\r";
        Com.write(F1_F8);
        CVNavi.flushReadBuffer(Com);
    }//GEN-LAST:event_jF10ActionPerformed

    private void jF11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF11ActionPerformed
        F11 = 0;
        if(jF11.isSelected())
        {
            F11 = 1;
        }
        F1_F8 = "XFX " + DecAddr + " " + F9 + " " + F10 + " " + F11 + " " + F12 + " 0 0\r";
        Com.write(F1_F8);
        CVNavi.flushReadBuffer(Com);
    }//GEN-LAST:event_jF11ActionPerformed

    private void jF12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jF12ActionPerformed
        F12 = 0;
        if(jF12.isSelected())
        {
            F12 = 1;
        }
        F1_F8 = "XFX " + DecAddr + " " + F9 + " " + F10 + " " + F11 + " " + F12 + " 0 0\r";
        Com.write(F1_F8);
        CVNavi.flushReadBuffer(Com);
    }//GEN-LAST:event_jF12ActionPerformed

    private void jVorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jVorActionPerformed
        jSlider1.setValue(0);
        bDir = 1;
        jSlider1.setValue(0);
        F1_F8 = "XL " + DecAddr + " " + 0 + " " + F0 + " " + bDir + " " + F1 + " " + F2 + " " + F3 + " " + F4 + "\r";
        Com.write(F1_F8);
        CVNavi.flushReadBuffer(Com);
    }//GEN-LAST:event_jVorActionPerformed

    private void jRueckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRueckActionPerformed
        jSlider1.setValue(0);
        bDir = 0;
        jSlider1.setValue(0);
        F1_F8 = "XL " + DecAddr + " " + 0 + " " + F0 + " " + bDir + " " + F1 + " " + F2 + " " + F3 + " " + F4 + "\r";
        Com.write(F1_F8);
        CVNavi.flushReadBuffer(Com);
    }//GEN-LAST:event_jRueckActionPerformed

    private void jSlider1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSlider1MouseReleased
        F0 = 0;
        bDir = 0;
        if(jVor.isSelected())
            bDir = 1;
        if(jF0.isSelected())
        {
            F0 = 1;
        }
        F1_F8 = "XL " + DecAddr + " " + jSlider1.getValue() + " " + F0 + " " + bDir + " " + F1 + " " + F2 + " " + F3 + " " + F4 + "\r";
        Com.write(F1_F8);
        CVNavi.flushReadBuffer(Com);
    }//GEN-LAST:event_jSlider1MouseReleased

    private void jCheckBoxZentraleEinAusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxZentraleEinAusActionPerformed
        if(jCheckBoxZentraleEinAus.isSelected()) {
            Com.write((byte)0x60);
        } else {
            Com.write((byte)0x61);
        }
    }//GEN-LAST:event_jCheckBoxZentraleEinAusActionPerformed

    private void jButtonProgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonProgActionPerformed
        Com.write((byte)0x60);  //Zentrale an
        jCheckBoxZentraleEinAus.setSelected(true);
        String str = "XPD " + DecAddr + " " + jCV.getText() + " " + jWert.getText() + "\r";
        Com.write(str);
        CVNavi.flushReadBuffer(Com);
    }//GEN-LAST:event_jButtonProgActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        Com = CVNavi.safelyOpenCom( this, Com );
        if( Com == null ) {
            return;
        }

        CVNavi.flushReadBuffer(Com);
        F1_F8 = "XL " + DecAddr + " " + jSlider1.getValue() + " " + F0 + " " + bDir + " " + F1 + " " + F2 + " " + F3 + " " + F4 + "\r";
        Com.write(F1_F8);
        CVNavi.flushReadBuffer(Com);
        F1_F8 = "XF " + DecAddr + " " + F1 + " " + F2 + " " + F3 + " " + F4 + " " + F5 + " " + F6 + " " + F7 + " " + F8 + "\r";
        Com.write(F1_F8);
        CVNavi.flushReadBuffer(Com);
        switch(CVNavi.getZentrale())
        {
            case c.cuOpenDCC: // OpenDCC
            case c.cuMasterControl1: // TamsMC
            case c.cuMasterControl2: // TamsMC2
                F1_F8 = "XFX " + DecAddr + " " + F9 + " " + F10 + " " + F11 + " " + F12 + " 0 0\r";
                Com.write(F1_F8);
                CVNavi.flushReadBuffer(Com);
                break;

            case c.cuIntellibox1: //IB geht nur bis F8
                if( debugLevel >= 1 ) {
                    System.out.println("DecTest formWindowOpened IB1 disable F9-F12" );
                }
                // TODO ab Version 2.x auch bis 28 !
                jF9.setEnabled(false);
                jF10.setEnabled(false);
                jF11.setEnabled(false);
                jF12.setEnabled(false);
                break;
        }
        Com.write((byte)0x61);
    }//GEN-LAST:event_formWindowOpened

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        Com = CVNavi.safelyCloseCom( this, Com );
    }//GEN-LAST:event_formWindowClosed

    private void jSlider1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider1StateChanged
        F0 = 0;
        bDir = 0;
        if(jVor.isSelected())
            bDir = 1;
        if(jF0.isSelected())
        {
            F0 = 1;
        }
        F1_F8 = "XL " + DecAddr + " " + jSlider1.getValue() + " " + F0 + " " + bDir + " " + F1 + " " + F2 + " " + F3 + " " + F4 + "\r";
        Com.write(F1_F8);
        CVNavi.flushReadBuffer(Com);
    }//GEN-LAST:event_jSlider1StateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButtonExit;
    private javax.swing.JButton jButtonProg;
    private javax.swing.JTextField jCV;
    private javax.swing.JCheckBox jCheckBoxZentraleEinAus;
    public javax.swing.JLabel jDecType;
    private javax.swing.JCheckBox jF0;
    private javax.swing.JCheckBox jF1;
    private javax.swing.JCheckBox jF10;
    private javax.swing.JCheckBox jF11;
    private javax.swing.JCheckBox jF12;
    private javax.swing.JCheckBox jF2;
    private javax.swing.JCheckBox jF3;
    private javax.swing.JCheckBox jF4;
    private javax.swing.JCheckBox jF5;
    private javax.swing.JCheckBox jF6;
    private javax.swing.JCheckBox jF7;
    private javax.swing.JCheckBox jF8;
    private javax.swing.JCheckBox jF9;
    private javax.swing.JLabel jFunktionen;
    private javax.swing.JLabel jGeschwindigkeit;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel jRichtung;
    private javax.swing.JLabel jRichtung1;
    private javax.swing.JLabel jRichtung2;
    private javax.swing.JRadioButton jRueck;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JRadioButton jVor;
    private javax.swing.JTextField jWert;
    private javax.swing.JLabel jWertLabel;
    // End of variables declaration//GEN-END:variables
}
