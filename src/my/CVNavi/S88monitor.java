/*
 * S88monitor.java
 *
 *
 * @author Kersten Tams Copyright 2009-2018
 * @author Lothar Roth  Copyright 2012-2018
 *
 */

package my.CVNavi;

import static my.CVNavi.CVNavi.debugLevel;

/**
 *
 * @author lroth
 */
public class S88monitor extends javax.swing.JDialog {

    private MC mc = null;

    /**
     * Creates new form MC_S88monitor
     * @param parent
     * @param modal
     */
    public S88monitor(java.awt.Frame parent, boolean modal) {
        super(parent, modal);

        if( mc != null && mc.S88mon != null ) {
            // alraedy an instance running
            this.dispose();
            return;
        }
        mc = (MC) parent;

        initComponents();

        if( debugLevel > 0 ) {
            System.out.println("NEW S88MONITOR");
        }
        if( mc.KTUI.getNumS88() <= 0 ) {
            mc.KTUI.mbNoS88modules( mc );
            return;            
        }

        this.redrawNumbers();
        this.redrawValues();

        setLocationRelativeTo(parent);
        setVisible(true);
        if( mc.modulNr > 0 ) {
            jSpinnerS88monitor.setValue((Object) mc.modulNr);
            mc.initS88read();
            mc.startS88read();
        }
        mc.S88mon = this;
    }

    private void redrawNumbers() {
        if( mc.modulNr == 0 ) {
            this.jRadioButton01.setText("-");
            this.jRadioButton02.setText("-");
            this.jRadioButton03.setText("-");
            this.jRadioButton04.setText("-");
            this.jRadioButton05.setText("-");
            this.jRadioButton06.setText("-");
            this.jRadioButton07.setText("-");
            this.jRadioButton08.setText("-");
            this.jRadioButton09.setText("-");
            this.jRadioButton10.setText("-");
            this.jRadioButton11.setText("-");
            this.jRadioButton12.setText("-");
            this.jRadioButton13.setText("-");
            this.jRadioButton14.setText("-");
            this.jRadioButton15.setText("-");
            this.jRadioButton16.setText("-");
        } else if( mc.modulNr == 1 ) {
            this.jRadioButton01.setText( "<html><center><br>1</center></html>");
            this.jRadioButton02.setText( "<html><center><br>2</center></html>");
            this.jRadioButton03.setText( "<html><center><br>3</center></html>");
            this.jRadioButton04.setText( "<html><center><br>4</center></html>");
            this.jRadioButton05.setText( "<html><center><br>5</center></html>");
            this.jRadioButton06.setText( "<html><center><br>6</center></html>");
            this.jRadioButton07.setText( "<html><center><br>7</center></html>");
            this.jRadioButton08.setText( "<html><center><br>8</center></html>");
            this.jRadioButton09.setText( "<html><center>9<br></center></html>");
            this.jRadioButton10.setText( "<html><center>10<br></center></html>");
            this.jRadioButton11.setText( "<html><center>11<br></center></html>");
            this.jRadioButton12.setText( "<html><center>12<br></center></html>");
            this.jRadioButton13.setText( "<html><center>13<br></center></html>");
            this.jRadioButton14.setText( "<html><center>14<br></center></html>");
            this.jRadioButton15.setText( "<html><center>15<br></center></html>");
            this.jRadioButton16.setText( "<html><center>16<br></center></html>");
        } else {
            int base = (mc.modulNr-1)*16;
            this.jRadioButton01.setText( "<html><center>"+(base+1)+"<br>1</center></html>");
            this.jRadioButton02.setText( "<html><center>"+(base+2)+"<br>2</center></html>");
            this.jRadioButton03.setText( "<html><center>"+(base+3)+"<br>3</center></html>");
            this.jRadioButton04.setText( "<html><center>"+(base+4)+"<br>4</center></html>");
            this.jRadioButton05.setText( "<html><center>"+(base+5)+"<br>5</center></html>");
            this.jRadioButton06.setText( "<html><center>"+(base+6)+"<br>6</center></html>");
            this.jRadioButton07.setText( "<html><center>"+(base+7)+"<br>7</center></html>");
            this.jRadioButton08.setText( "<html><center>"+(base+8)+"<br>8</center></html>");
            this.jRadioButton09.setText( "<html><center>9<br>"+(base+9)+"</center></html>");
            this.jRadioButton10.setText( "<html><center>10<br>"+(base+10)+"</center></html>");
            this.jRadioButton11.setText( "<html><center>11<br>"+(base+11)+"</center></html>");
            this.jRadioButton12.setText( "<html><center>12<br>"+(base+12)+"</center></html>");
            this.jRadioButton13.setText( "<html><center>13<br>"+(base+13)+"</center></html>");
            this.jRadioButton14.setText( "<html><center>14<br>"+(base+14)+"</center></html>");
            this.jRadioButton15.setText( "<html><center>15<br>"+(base+15)+"</center></html>");
            this.jRadioButton16.setText( "<html><center>16<br>"+(base+16)+"</center></html>");
        }
    }

    public void redrawValues() {
        this.jRadioButton01.setSelected((mc.moduleValue & 0x0001)!=0);
        this.jRadioButton02.setSelected((mc.moduleValue & 0x0002)!=0);
        this.jRadioButton03.setSelected((mc.moduleValue & 0x0004)!=0);
        this.jRadioButton04.setSelected((mc.moduleValue & 0x0008)!=0);
        this.jRadioButton05.setSelected((mc.moduleValue & 0x0010)!=0);
        this.jRadioButton06.setSelected((mc.moduleValue & 0x0020)!=0);
        this.jRadioButton07.setSelected((mc.moduleValue & 0x0040)!=0);
        this.jRadioButton08.setSelected((mc.moduleValue & 0x0080)!=0);
        this.jRadioButton09.setSelected((mc.moduleValue & 0x0100)!=0);
        this.jRadioButton10.setSelected((mc.moduleValue & 0x0200)!=0);
        this.jRadioButton11.setSelected((mc.moduleValue & 0x0400)!=0);
        this.jRadioButton12.setSelected((mc.moduleValue & 0x0800)!=0);
        this.jRadioButton13.setSelected((mc.moduleValue & 0x1000)!=0);
        this.jRadioButton14.setSelected((mc.moduleValue & 0x2000)!=0);
        this.jRadioButton15.setSelected((mc.moduleValue & 0x4000)!=0);
        this.jRadioButton16.setSelected((mc.moduleValue & 0x8000)!=0);
    }

    public int getShownValue() {
        int value = 0;
        if( jRadioButton01.isSelected() ) { value |= 0x0001 ;};
        if( jRadioButton02.isSelected() ) { value |= 0x0002 ;};
        if( jRadioButton03.isSelected() ) { value |= 0x0004 ;};
        if( jRadioButton04.isSelected() ) { value |= 0x0008 ;};
        if( jRadioButton05.isSelected() ) { value |= 0x0010 ;};
        if( jRadioButton06.isSelected() ) { value |= 0x0020 ;};
        if( jRadioButton07.isSelected() ) { value |= 0x0040 ;};
        if( jRadioButton08.isSelected() ) { value |= 0x0080 ;};
        if( jRadioButton09.isSelected() ) { value |= 0x0100 ;};
        if( jRadioButton10.isSelected() ) { value |= 0x0200 ;};
        if( jRadioButton11.isSelected() ) { value |= 0x0400 ;};
        if( jRadioButton12.isSelected() ) { value |= 0x0800 ;};
        if( jRadioButton13.isSelected() ) { value |= 0x1000 ;};
        if( jRadioButton14.isSelected() ) { value |= 0x2000 ;};
        if( jRadioButton15.isSelected() ) { value |= 0x4000 ;};
        if( jRadioButton16.isSelected() ) { value |= 0x8000 ;};
        return value;
    }


        /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelS88monitor = new javax.swing.JPanel();
        jRadioButton01 = new javax.swing.JRadioButton();
        jRadioButton02 = new javax.swing.JRadioButton();
        jRadioButton03 = new javax.swing.JRadioButton();
        jRadioButton04 = new javax.swing.JRadioButton();
        jRadioButton05 = new javax.swing.JRadioButton();
        jRadioButton06 = new javax.swing.JRadioButton();
        jRadioButton07 = new javax.swing.JRadioButton();
        jRadioButton08 = new javax.swing.JRadioButton();
        jRadioButton09 = new javax.swing.JRadioButton();
        jRadioButton10 = new javax.swing.JRadioButton();
        jRadioButton11 = new javax.swing.JRadioButton();
        jRadioButton12 = new javax.swing.JRadioButton();
        jRadioButton13 = new javax.swing.JRadioButton();
        jRadioButton14 = new javax.swing.JRadioButton();
        jRadioButton15 = new javax.swing.JRadioButton();
        jRadioButton16 = new javax.swing.JRadioButton();
        jLabelS88monitor = new javax.swing.JLabel();
        jSpinnerS88monitor = new javax.swing.JSpinner();
        jReset2zero = new javax.swing.JButton();
        jClose = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("my/CVNavi/Bundle"); // NOI18N
        setTitle(bundle.getString("S88monitor.title")); // NOI18N
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jRadioButton01.setText("<html>777<br>1</html>");
        jRadioButton01.setFocusPainted(false);
        jRadioButton01.setFocusable(false);
        jRadioButton01.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jRadioButton01.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jRadioButton01.setPreferredSize(new java.awt.Dimension(60, 60));
        jRadioButton01.setRequestFocusEnabled(false);
        jRadioButton01.setRolloverEnabled(false);
        jRadioButton01.setVerifyInputWhenFocusTarget(false);
        jRadioButton01.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jRadioButton01.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButton01MouseClicked(evt);
            }
        });

        jRadioButton02.setText("<html>777<br>1</html>");
        jRadioButton02.setFocusPainted(false);
        jRadioButton02.setFocusable(false);
        jRadioButton02.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jRadioButton02.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jRadioButton02.setPreferredSize(new java.awt.Dimension(60, 60));
        jRadioButton02.setRequestFocusEnabled(false);
        jRadioButton02.setRolloverEnabled(false);
        jRadioButton02.setVerifyInputWhenFocusTarget(false);
        jRadioButton02.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jRadioButton02.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButton02MouseClicked(evt);
            }
        });

        jRadioButton03.setText("<html>777<br>1</html>");
        jRadioButton03.setFocusPainted(false);
        jRadioButton03.setFocusable(false);
        jRadioButton03.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jRadioButton03.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jRadioButton03.setPreferredSize(new java.awt.Dimension(60, 60));
        jRadioButton03.setRequestFocusEnabled(false);
        jRadioButton03.setRolloverEnabled(false);
        jRadioButton03.setVerifyInputWhenFocusTarget(false);
        jRadioButton03.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jRadioButton03.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButton03MouseClicked(evt);
            }
        });

        jRadioButton04.setText("<html>777<br>1</html>");
        jRadioButton04.setFocusPainted(false);
        jRadioButton04.setFocusable(false);
        jRadioButton04.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jRadioButton04.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jRadioButton04.setPreferredSize(new java.awt.Dimension(60, 60));
        jRadioButton04.setRequestFocusEnabled(false);
        jRadioButton04.setRolloverEnabled(false);
        jRadioButton04.setVerifyInputWhenFocusTarget(false);
        jRadioButton04.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jRadioButton04.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButton04MouseClicked(evt);
            }
        });

        jRadioButton05.setText("<html>777<br>1</html>");
        jRadioButton05.setFocusPainted(false);
        jRadioButton05.setFocusable(false);
        jRadioButton05.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jRadioButton05.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jRadioButton05.setPreferredSize(new java.awt.Dimension(60, 60));
        jRadioButton05.setRequestFocusEnabled(false);
        jRadioButton05.setRolloverEnabled(false);
        jRadioButton05.setVerifyInputWhenFocusTarget(false);
        jRadioButton05.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jRadioButton05.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButton05MouseClicked(evt);
            }
        });

        jRadioButton06.setText("<html>777<br>1</html>");
        jRadioButton06.setFocusPainted(false);
        jRadioButton06.setFocusable(false);
        jRadioButton06.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jRadioButton06.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jRadioButton06.setPreferredSize(new java.awt.Dimension(60, 60));
        jRadioButton06.setRequestFocusEnabled(false);
        jRadioButton06.setRolloverEnabled(false);
        jRadioButton06.setVerifyInputWhenFocusTarget(false);
        jRadioButton06.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jRadioButton06.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButton06MouseClicked(evt);
            }
        });

        jRadioButton07.setText("<html>777<br>1</html>");
        jRadioButton07.setFocusPainted(false);
        jRadioButton07.setFocusable(false);
        jRadioButton07.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jRadioButton07.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jRadioButton07.setPreferredSize(new java.awt.Dimension(60, 60));
        jRadioButton07.setRequestFocusEnabled(false);
        jRadioButton07.setRolloverEnabled(false);
        jRadioButton07.setVerifyInputWhenFocusTarget(false);
        jRadioButton07.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jRadioButton07.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButton07MouseClicked(evt);
            }
        });

        jRadioButton08.setText("<html>777<br>1</html>");
        jRadioButton08.setFocusPainted(false);
        jRadioButton08.setFocusable(false);
        jRadioButton08.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jRadioButton08.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jRadioButton08.setPreferredSize(new java.awt.Dimension(60, 60));
        jRadioButton08.setRequestFocusEnabled(false);
        jRadioButton08.setRolloverEnabled(false);
        jRadioButton08.setVerifyInputWhenFocusTarget(false);
        jRadioButton08.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jRadioButton08.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButton08MouseClicked(evt);
            }
        });

        jRadioButton09.setText("<html>777<br>1</html>");
        jRadioButton09.setFocusPainted(false);
        jRadioButton09.setFocusable(false);
        jRadioButton09.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jRadioButton09.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jRadioButton09.setPreferredSize(new java.awt.Dimension(60, 60));
        jRadioButton09.setRequestFocusEnabled(false);
        jRadioButton09.setRolloverEnabled(false);
        jRadioButton09.setVerifyInputWhenFocusTarget(false);
        jRadioButton09.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jRadioButton09.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButton09MouseClicked(evt);
            }
        });

        jRadioButton10.setText("<html>777<br>1</html>");
        jRadioButton10.setFocusPainted(false);
        jRadioButton10.setFocusable(false);
        jRadioButton10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jRadioButton10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jRadioButton10.setPreferredSize(new java.awt.Dimension(60, 60));
        jRadioButton10.setRequestFocusEnabled(false);
        jRadioButton10.setRolloverEnabled(false);
        jRadioButton10.setVerifyInputWhenFocusTarget(false);
        jRadioButton10.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jRadioButton10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButton10MouseClicked(evt);
            }
        });

        jRadioButton11.setText("<html>777<br>1</html>");
        jRadioButton11.setFocusPainted(false);
        jRadioButton11.setFocusable(false);
        jRadioButton11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jRadioButton11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jRadioButton11.setPreferredSize(new java.awt.Dimension(60, 60));
        jRadioButton11.setRequestFocusEnabled(false);
        jRadioButton11.setRolloverEnabled(false);
        jRadioButton11.setVerifyInputWhenFocusTarget(false);
        jRadioButton11.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jRadioButton11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButton11MouseClicked(evt);
            }
        });

        jRadioButton12.setText("<html>777<br>1</html>");
        jRadioButton12.setFocusPainted(false);
        jRadioButton12.setFocusable(false);
        jRadioButton12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jRadioButton12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jRadioButton12.setPreferredSize(new java.awt.Dimension(60, 60));
        jRadioButton12.setRequestFocusEnabled(false);
        jRadioButton12.setRolloverEnabled(false);
        jRadioButton12.setVerifyInputWhenFocusTarget(false);
        jRadioButton12.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jRadioButton12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButton12MouseClicked(evt);
            }
        });

        jRadioButton13.setText("<html>777<br>1</html>");
        jRadioButton13.setFocusPainted(false);
        jRadioButton13.setFocusable(false);
        jRadioButton13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jRadioButton13.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jRadioButton13.setPreferredSize(new java.awt.Dimension(60, 60));
        jRadioButton13.setRequestFocusEnabled(false);
        jRadioButton13.setRolloverEnabled(false);
        jRadioButton13.setVerifyInputWhenFocusTarget(false);
        jRadioButton13.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jRadioButton13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButton13MouseClicked(evt);
            }
        });

        jRadioButton14.setText("<html>777<br>1</html>");
        jRadioButton14.setFocusPainted(false);
        jRadioButton14.setFocusable(false);
        jRadioButton14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jRadioButton14.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jRadioButton14.setPreferredSize(new java.awt.Dimension(60, 60));
        jRadioButton14.setRequestFocusEnabled(false);
        jRadioButton14.setRolloverEnabled(false);
        jRadioButton14.setVerifyInputWhenFocusTarget(false);
        jRadioButton14.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jRadioButton14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButton14MouseClicked(evt);
            }
        });

        jRadioButton15.setText("<html>777<br>1</html>");
        jRadioButton15.setFocusPainted(false);
        jRadioButton15.setFocusable(false);
        jRadioButton15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jRadioButton15.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jRadioButton15.setPreferredSize(new java.awt.Dimension(60, 60));
        jRadioButton15.setRequestFocusEnabled(false);
        jRadioButton15.setRolloverEnabled(false);
        jRadioButton15.setVerifyInputWhenFocusTarget(false);
        jRadioButton15.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jRadioButton15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButton15MouseClicked(evt);
            }
        });

        jRadioButton16.setText("<html>777<br>1</html>");
        jRadioButton16.setFocusPainted(false);
        jRadioButton16.setFocusable(false);
        jRadioButton16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jRadioButton16.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jRadioButton16.setPreferredSize(new java.awt.Dimension(60, 60));
        jRadioButton16.setRequestFocusEnabled(false);
        jRadioButton16.setRolloverEnabled(false);
        jRadioButton16.setVerifyInputWhenFocusTarget(false);
        jRadioButton16.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jRadioButton16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButton16MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanelS88monitorLayout = new javax.swing.GroupLayout(jPanelS88monitor);
        jPanelS88monitor.setLayout(jPanelS88monitorLayout);
        jPanelS88monitorLayout.setHorizontalGroup(
            jPanelS88monitorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelS88monitorLayout.createSequentialGroup()
                .addGroup(jPanelS88monitorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanelS88monitorLayout.createSequentialGroup()
                        .addComponent(jRadioButton09, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jRadioButton11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanelS88monitorLayout.createSequentialGroup()
                        .addComponent(jRadioButton01, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton02, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton03, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelS88monitorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton04, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jRadioButton12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelS88monitorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelS88monitorLayout.createSequentialGroup()
                        .addComponent(jRadioButton05, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton06, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton07, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanelS88monitorLayout.createSequentialGroup()
                        .addComponent(jRadioButton13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelS88monitorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jRadioButton16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jRadioButton08, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanelS88monitorLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jRadioButton01, jRadioButton02, jRadioButton03, jRadioButton04, jRadioButton05, jRadioButton06, jRadioButton07, jRadioButton08, jRadioButton09, jRadioButton10, jRadioButton11, jRadioButton12, jRadioButton13, jRadioButton14, jRadioButton15, jRadioButton16});

        jPanelS88monitorLayout.setVerticalGroup(
            jPanelS88monitorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelS88monitorLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanelS88monitorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jRadioButton12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelS88monitorLayout.createSequentialGroup()
                        .addComponent(jRadioButton04, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(60, 60, 60))
                    .addGroup(jPanelS88monitorLayout.createSequentialGroup()
                        .addGroup(jPanelS88monitorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jRadioButton05, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jRadioButton06, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jRadioButton07, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jRadioButton08, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jRadioButton14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jRadioButton15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jRadioButton16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelS88monitorLayout.createSequentialGroup()
                        .addGroup(jPanelS88monitorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jRadioButton02, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jRadioButton03, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jRadioButton01, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelS88monitorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jRadioButton09, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jRadioButton11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jRadioButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanelS88monitorLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jRadioButton01, jRadioButton02, jRadioButton03, jRadioButton04, jRadioButton05, jRadioButton06, jRadioButton07, jRadioButton08, jRadioButton09, jRadioButton10, jRadioButton11, jRadioButton12, jRadioButton13, jRadioButton14, jRadioButton15, jRadioButton16});

        jRadioButton01.getAccessibleContext().setAccessibleName("");
        jRadioButton01.getAccessibleContext().setAccessibleDescription("");
        jRadioButton02.getAccessibleContext().setAccessibleName("");
        jRadioButton02.getAccessibleContext().setAccessibleDescription("");
        jRadioButton03.getAccessibleContext().setAccessibleName("");
        jRadioButton03.getAccessibleContext().setAccessibleDescription("");
        jRadioButton04.getAccessibleContext().setAccessibleName("");
        jRadioButton04.getAccessibleContext().setAccessibleDescription("");
        jRadioButton05.getAccessibleContext().setAccessibleName("");
        jRadioButton05.getAccessibleContext().setAccessibleDescription("");
        jRadioButton06.getAccessibleContext().setAccessibleName("");
        jRadioButton06.getAccessibleContext().setAccessibleDescription("");
        jRadioButton07.getAccessibleContext().setAccessibleName("");
        jRadioButton07.getAccessibleContext().setAccessibleDescription("");
        jRadioButton08.getAccessibleContext().setAccessibleName("");
        jRadioButton08.getAccessibleContext().setAccessibleDescription("");
        jRadioButton09.getAccessibleContext().setAccessibleName("");
        jRadioButton09.getAccessibleContext().setAccessibleDescription("");
        jRadioButton10.getAccessibleContext().setAccessibleName("");
        jRadioButton10.getAccessibleContext().setAccessibleDescription("");
        jRadioButton11.getAccessibleContext().setAccessibleName("");
        jRadioButton11.getAccessibleContext().setAccessibleDescription("");
        jRadioButton12.getAccessibleContext().setAccessibleName("");
        jRadioButton12.getAccessibleContext().setAccessibleDescription("");
        jRadioButton13.getAccessibleContext().setAccessibleName("");
        jRadioButton13.getAccessibleContext().setAccessibleDescription("");
        jRadioButton14.getAccessibleContext().setAccessibleName("");
        jRadioButton14.getAccessibleContext().setAccessibleDescription("");
        jRadioButton15.getAccessibleContext().setAccessibleName("");
        jRadioButton15.getAccessibleContext().setAccessibleDescription("");
        jRadioButton16.getAccessibleContext().setAccessibleName("");
        jRadioButton16.getAccessibleContext().setAccessibleDescription("");

        jLabelS88monitor.setText(bundle.getString("S88monitor.jLabelS88monitor.text")); // NOI18N

        jSpinnerS88monitor.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinnerS88monitorStateChanged(evt);
            }
        });

        jReset2zero.setText(bundle.getString("S88monitor.RESET0")); // NOI18N
        jReset2zero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jReset2zeroActionPerformed(evt);
            }
        });

        jClose.setText(bundle.getString("S88monitor.CLOSE")); // NOI18N
        jClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCloseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelS88monitor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelS88monitor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinnerS88monitor, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jReset2zero)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jClose)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabelS88monitor)
                    .addComponent(jSpinnerS88monitor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanelS88monitor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jReset2zero)
                    .addComponent(jClose))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jReset2zeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jReset2zeroActionPerformed
        // TODO add your handling code here:
        if( debugLevel > 0 ) {
            System.out.println( "jReset2zeroActionPerformed: "+evt.paramString() );
        }
        mc.moduleValue = 0;
        redrawValues();
    }//GEN-LAST:event_jReset2zeroActionPerformed

    private void jCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCloseActionPerformed
        // "Close" was pressed
        if( debugLevel > 0 ) {
            System.out.println("S88monitor: jCloseActionPerformed");
        }
        // calls formWindowClosed
        this.dispose();
    }//GEN-LAST:event_jCloseActionPerformed

    private void jSpinnerS88monitorStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinnerS88monitorStateChanged
        int currNr = (Integer) this.jSpinnerS88monitor.getValue();
        int prevNr = mc.modulNr;
        if( debugLevel > 0 ) {
            System.out.println("jSpinnerS88monitorStateChanged: value="+currNr );
        }
        
        if( currNr < 1 ) {
            // lower bound
            currNr = 1;
            this.jSpinnerS88monitor.setValue((Object) currNr);
        }
        if( currNr > mc.KTUI.getNumS88() ) {
            // upper bound
            currNr = mc.KTUI.getNumS88();
            this.jSpinnerS88monitor.setValue((Object) currNr);
        }

        // prevNr and currNr are in valid range [1..52]
        if( prevNr != currNr ) {
            // moduleNr changed
            mc.pauseS88read();
            mc.modulNr = currNr; // activate new module
            mc.moduleValue = 0; // reset values
            if( prevNr == 0 ) {
                mc.initS88read();
            }
            if( currNr > 0 ){
                mc.startS88read();
            }
            if( currNr == 0 ) {
                mc.stopS88read();            
            }
            redrawNumbers();
        }
        redrawValues();
    }//GEN-LAST:event_jSpinnerS88monitorStateChanged

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // stop feedback reader
        if( debugLevel > 0 ) {
            System.out.println("S88monitor: formWindowClosed");
        }
        mc.stopS88read();
        // invalidate s88 monitor instance in mc instance
        mc.S88mon = null;
    }//GEN-LAST:event_formWindowClosed

    private void jRadioButton01MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton01MouseClicked
        redrawValues();
    }//GEN-LAST:event_jRadioButton01MouseClicked

    private void jRadioButton02MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton02MouseClicked
        redrawValues();
    }//GEN-LAST:event_jRadioButton02MouseClicked

    private void jRadioButton03MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton03MouseClicked
        redrawValues();
    }//GEN-LAST:event_jRadioButton03MouseClicked

    private void jRadioButton04MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton04MouseClicked
        redrawValues();
    }//GEN-LAST:event_jRadioButton04MouseClicked

    private void jRadioButton05MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton05MouseClicked
        redrawValues();
    }//GEN-LAST:event_jRadioButton05MouseClicked

    private void jRadioButton06MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton06MouseClicked
        redrawValues();
    }//GEN-LAST:event_jRadioButton06MouseClicked

    private void jRadioButton07MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton07MouseClicked
        redrawValues();
    }//GEN-LAST:event_jRadioButton07MouseClicked

    private void jRadioButton08MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton08MouseClicked
        redrawValues();
    }//GEN-LAST:event_jRadioButton08MouseClicked

    private void jRadioButton09MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton09MouseClicked
        redrawValues();
    }//GEN-LAST:event_jRadioButton09MouseClicked

    private void jRadioButton10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton10MouseClicked
        redrawValues();
    }//GEN-LAST:event_jRadioButton10MouseClicked

    private void jRadioButton11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton11MouseClicked
        redrawValues();
    }//GEN-LAST:event_jRadioButton11MouseClicked

    private void jRadioButton12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton12MouseClicked
        redrawValues();
    }//GEN-LAST:event_jRadioButton12MouseClicked

    private void jRadioButton13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton13MouseClicked
        redrawValues();
    }//GEN-LAST:event_jRadioButton13MouseClicked

    private void jRadioButton14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton14MouseClicked
        redrawValues();
    }//GEN-LAST:event_jRadioButton14MouseClicked

    private void jRadioButton15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton15MouseClicked
        redrawValues();
    }//GEN-LAST:event_jRadioButton15MouseClicked

    private void jRadioButton16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton16MouseClicked
        redrawValues();
    }//GEN-LAST:event_jRadioButton16MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jClose;
    private javax.swing.JLabel jLabelS88monitor;
    private javax.swing.JPanel jPanelS88monitor;
    private javax.swing.JRadioButton jRadioButton01;
    private javax.swing.JRadioButton jRadioButton02;
    private javax.swing.JRadioButton jRadioButton03;
    private javax.swing.JRadioButton jRadioButton04;
    private javax.swing.JRadioButton jRadioButton05;
    private javax.swing.JRadioButton jRadioButton06;
    private javax.swing.JRadioButton jRadioButton07;
    private javax.swing.JRadioButton jRadioButton08;
    private javax.swing.JRadioButton jRadioButton09;
    private javax.swing.JRadioButton jRadioButton10;
    private javax.swing.JRadioButton jRadioButton11;
    private javax.swing.JRadioButton jRadioButton12;
    private javax.swing.JRadioButton jRadioButton13;
    private javax.swing.JRadioButton jRadioButton14;
    private javax.swing.JRadioButton jRadioButton15;
    private javax.swing.JRadioButton jRadioButton16;
    private javax.swing.JButton jReset2zero;
    private javax.swing.JSpinner jSpinnerS88monitor;
    // End of variables declaration//GEN-END:variables
}
