/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package my.KlarText;

import java.util.Comparator;
import javax.swing.JLabel;
import javax.swing.SortOrder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import static my.KlarText.KlarTextUI.debugLevel;

/**
 *
 * @author Lothar
 */
public class M3_Liste extends javax.swing.JDialog {

    private MC mc = null;
    private int m3TableSelRow = -1;
    private int m3TableSelCol = -1;
    /**
     * Creates new form M3_Liste
     */
    public M3_Liste(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        mc = (MC) parent;

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        jTableM3.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        jTableM3.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );

        int rows = this.jTableM3.getRowCount();
        if( debugLevel >= 1 ) {
            System.out.println("M3_Liste rows="+rows+" mc.M3used="+mc.M3used);
        }

        TableModel tm = jTableM3.getModel();
        DefaultTableModel model=(DefaultTableModel) tm;
        for( int i = 0 ; i < mc.M3used ; i++ ){
            Object[] os = { mc.M3liste[0][i], mc.M3liste[1][i], mc.M3liste[2][i]};
            model.addRow(os);
            if( debugLevel >= 1 ) {
                System.out.println("M3_Liste addRow["+i+"]");
            }
        }

        rows = this.jTableM3.getRowCount();
        if( debugLevel >= 1 ) {
            System.out.println("M3_Liste rows="+rows);
        }

        TableRowSorter<DefaultTableModel> rowSorter = (TableRowSorter<DefaultTableModel>)jTableM3.getRowSorter();
        rowSorter.setComparator(0, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2)
            {
                String s1 = ""+o1;
                String s2 = ""+o2;
                if( ( s1.length() == 0 ) && ( s2.length() == 0 ) ) {
                    return 0;
                }
                boolean ascending = jTableM3.getRowSorter().getSortKeys().get(0).getSortOrder() == SortOrder.ASCENDING;
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
        rowSorter.setComparator(1, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2)
            {
                int n1 = mc.KTUI.checkAndGetStrNumRangeDef( null, o1, 1, c.MAX_M3_SID, 0, false);
                int n2 = mc.KTUI.checkAndGetStrNumRangeDef( null, o2, 1, c.MAX_M3_SID, 0, false);
                if( ( n1 == 0 ) && ( n2 == 0 ) ) {
                    return 0;
                }
                boolean ascending = jTableM3.getRowSorter().getSortKeys().get(0).getSortOrder() == SortOrder.ASCENDING;
                if( n1 == 0 ) {
                    if( ascending == true ) {
                        return 1;
                    } else {
                        return -1;
                    }
                }
                if( n2 == 0 ) {
                    if( ascending == true ) {
                        return -1;
                    } else {
                        return 1;
                    }
                }
                return n1 - n2 ;
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
                boolean ascending = jTableM3.getRowSorter().getSortKeys().get(0).getSortOrder() == SortOrder.ASCENDING;
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

        jTableM3.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
             public void valueChanged(ListSelectionEvent e) {
                m3TableSelRow = jTableM3.getSelectedRow();
                m3TableSelCol = jTableM3.getSelectedColumn();
                if( debugLevel >= 1 ) {
                    System.out.println("M3 ListSelectionListener valueChanged row="+m3TableSelRow+" col="+m3TableSelCol);
                }
            }
        });

        setLocationRelativeTo(parent);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableM3 = new javax.swing.JTable();
        jAdd = new javax.swing.JButton();
        jDel = new javax.swing.JButton();
        jDelAll = new javax.swing.JButton();
        jClose = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("M3 UID/SID-Tabelle");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jTableM3.setAutoCreateRowSorter(true);
        jTableM3.setFont(jTableM3.getFont().deriveFont(jTableM3.getFont().getSize()+4f));
        jTableM3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "M3UID (MAC, Hex):", "M3SID (Adresse):", "Beschreibung"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTableM3.setIntercellSpacing(new java.awt.Dimension(10, 10));
        jTableM3.setRowHeight(26);
        jTableM3.getTableHeader().setReorderingAllowed(false);
        jTableM3.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTableM3PropertyChange(evt);
            }
        });
        jScrollPane1.setViewportView(jTableM3);
        if (jTableM3.getColumnModel().getColumnCount() > 0) {
            jTableM3.getColumnModel().getColumn(0).setResizable(false);
            jTableM3.getColumnModel().getColumn(0).setPreferredWidth(100);
            jTableM3.getColumnModel().getColumn(1).setResizable(false);
            jTableM3.getColumnModel().getColumn(1).setPreferredWidth(50);
            jTableM3.getColumnModel().getColumn(2).setMinWidth(200);
            jTableM3.getColumnModel().getColumn(2).setPreferredWidth(300);
        }

        jAdd.setText("Hinzufügen");
        jAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAddActionPerformed(evt);
            }
        });

        jDel.setText("Löschen");
        jDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDelActionPerformed(evt);
            }
        });

        jDelAll.setText("Alle Löschen");
        jDelAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDelAllActionPerformed(evt);
            }
        });

        jClose.setText("Schließen");
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 690, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jDel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jClose, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDelAll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(jAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jDelAll)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 250, Short.MAX_VALUE)
                        .addComponent(jClose))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE)))
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(859, 467));
    }// </editor-fold>//GEN-END:initComponents

    private void jCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCloseActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jCloseActionPerformed

    private void jAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAddActionPerformed
        // TODO add your handling code here:
        if( jTableM3.isEditing() ) {
            jTableM3.getCellEditor().stopCellEditing();
        }
        int rows = this.jTableM3.getRowCount();
        if( debugLevel >= 1 ) {
            System.out.println("M3_Liste rows="+rows);
        }

        TableModel tm = jTableM3.getModel();
        DefaultTableModel model=(DefaultTableModel) tm;
        Object[] os = { "", "", ""};
        model.addRow(os);
        // echte Zeile in Tabelle anlegen
        mc.M3liste[0][mc.M3used] = "";
        mc.M3liste[1][mc.M3used] = "";
        mc.M3liste[2][mc.M3used] = "";
        mc.M3used++;

        rows = this.jTableM3.getRowCount();
        if( debugLevel >= 1 ) {
            System.out.println("M3_Liste rows="+rows+" mc.M3used="+mc.M3used);
        }

    }//GEN-LAST:event_jAddActionPerformed

    private void jDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDelActionPerformed
        // TODO add your handling code here:
        if( jTableM3.isEditing() ) {
            jTableM3.getCellEditor().stopCellEditing();
        }
        m3TableSelRow = jTableM3.getSelectedRow();
        m3TableSelCol = jTableM3.getSelectedColumn();
        int rows = this.jTableM3.getRowCount();

        if( debugLevel >= 1 ) {
            System.out.println("M3_Liste rows="+rows+" m3TableSelRow="+m3TableSelRow);
        }

        // JTable.convertRowIndexToModel();
        TableModel tm = jTableM3.getModel();
        DefaultTableModel model=(DefaultTableModel) tm;

        int[] selMulti = jTableM3.getSelectedRows();
        int selMultiLength = selMulti.length;
        jTableM3.getSelectionModel().clearSelection();
        if( debugLevel >= 1 ) {
            System.out.println("M3_Liste selMultiLength="+selMultiLength);
            for( int i = 0 ; i < selMultiLength ; i++ ) {
                System.out.println("M3_Liste A selMulti["+i+"]="+selMulti[i]+" viewRow="+jTableM3.convertRowIndexToModel(selMulti[i]));
            }
            for( int i = (selMultiLength-1) ; i >= 0 ; i-- ) {
                System.out.println("M3_Liste B selMulti["+i+"]="+selMulti[i]+" viewRow="+jTableM3.convertRowIndexToModel(selMulti[i]));
            }
        }

        for( int i = (selMultiLength-1) ; i >= 0 ; i-- ) {
            int row = selMulti[i];
            int viewRow = jTableM3.convertRowIndexToModel(row);
            if( debugLevel >= 1 ) {
                System.out.println("M3_Liste DELETE selMulti["+i+"]="+row+" viewRow="+viewRow);
            }
            model.removeRow(viewRow);
            mc.M3used--;

            if( selMultiLength == 1 ) {
                // set a selected row only if we had a single selection
                if( row == (rows -1) ) {
                    row--;
                }
                if( row >= 0 ) {
                    // set selection to previous selection
                    if( debugLevel >= 1 ) {
                        System.out.println("M3_Liste set selection to row="+row);
                    }
                    jTableM3.setRowSelectionInterval(row, row);
                }
            }
        }

        rows = this.jTableM3.getRowCount();
        if( debugLevel >= 1 ) {
            System.out.println("M3_Liste rows="+rows+" mc.M3used="+mc.M3used);
        }
    }//GEN-LAST:event_jDelActionPerformed

    private void jDelAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDelAllActionPerformed
        // TODO add your handling code here:
        if( jTableM3.isEditing() ) {
            jTableM3.getCellEditor().stopCellEditing();
        }
        int rows = this.jTableM3.getRowCount();
        if( debugLevel >= 1 ) {
            System.out.println("M3_Liste rows="+rows);
        }

        TableModel tm = jTableM3.getModel();
        DefaultTableModel model=(DefaultTableModel) tm;

        for( int i = 0 ; i < rows ; i++ ) {
            model.removeRow(0);
            mc.M3used = 0;
        }

        rows = this.jTableM3.getRowCount();
        if( debugLevel >= 1 ) {
            System.out.println("M3_Liste rows="+rows+" mc.M3used="+mc.M3used);
        }
    }//GEN-LAST:event_jDelAllActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:


    }//GEN-LAST:event_formWindowOpened

    private void jTableM3PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTableM3PropertyChange
        // TODO add your handling code here:
        if( debugLevel >= 1 ) {
            System.out.println("jTableM3PropertyChange col="+jTableM3.getEditingColumn()+" row="+jTableM3.getEditingRow());
        }
        int edRow = jTableM3.getEditingRow();
        int edCol = jTableM3.getEditingColumn();
        String str;
        if( edRow >= 0 && edCol >= 0 ) {
            switch( edCol ) {
                case 0: // UID
                    str = (""+jTableM3.getValueAt(edRow, edCol)).trim().toLowerCase().replaceAll("\\s","");
                    str = mc.checkM3uidValid( str );
                    if( str != null ) {
                        jTableM3.setValueAt(str, edRow, edCol);
                    } else {
                        // TODO Editor setzen , aber wie ??? ZZZ
                        System.out.println("jTableM3PropertyChange M3UID ERROR");
                    }
                    break;
                case 1: // SID
                    str = (""+jTableM3.getValueAt(edRow, edCol)).trim().toLowerCase().replaceAll("\\s","");
                    if( mc.KTUI.checkNumRange(str, 1, c.MAX_M3_SID) ) {
                        jTableM3.setValueAt(str, edRow, edCol);
                    } else {
                        // TODO Editor setzen , aber wie ??? ZZZ
                        System.out.println("jTableM3PropertyChange M3SID ERROR");
                    }
                    break;
                case 2: // Beschreibung
                    str = (""+jTableM3.getValueAt(edRow, edCol)).trim();
                    if( str.length() > 30 ) {
                        str = str.substring(0, 30);
                    }
                    jTableM3.setValueAt(str, edRow, edCol);
                    break;
            }
            System.out.println("nach PC: edRow="+edRow+" edCol="+edCol+" m3TableSelRow="+m3TableSelRow+" m3TableSelCol="+m3TableSelCol );
        }
    }//GEN-LAST:event_jTableM3PropertyChange

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        int rows = this.jTableM3.getRowCount();

        if( debugLevel >= 1 ) {
            System.out.println("M3_Liste Closed rows="+rows+" mc.M3used="+mc.M3used);
        }

        for( int row = 0 ; row < rows ; row++ ) {
            mc.M3liste[0][row] = ""+jTableM3.getValueAt(row, 0);
            mc.M3liste[1][row] = ""+jTableM3.getValueAt(row, 1);
            mc.M3liste[2][row] = ""+jTableM3.getValueAt(row, 2);
        }

        if( debugLevel >= 1 ) {
            System.out.println("M3_Liste Closed rows="+rows+" mc.M3used="+mc.M3used);
        }
    }//GEN-LAST:event_formWindowClosed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jAdd;
    private javax.swing.JButton jClose;
    private javax.swing.JButton jDel;
    private javax.swing.JButton jDelAll;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableM3;
    // End of variables declaration//GEN-END:variables
}
