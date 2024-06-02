/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package hms;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author user
 */
public class Item_Category_Details extends javax.swing.JFrame {
    Connection con = null;
    ResultSet rs =null;
    PreparedStatement pst = null;
    
    
    public void table_load(){
    
        try {
             DefaultTableModel model = (DefaultTableModel) table.getModel();
             model.setRowCount(0);
             pst = con.prepareStatement("select * from item_category");
             rs = pst.executeQuery();
             while(rs.next()){                
                 model.addRow(new Object[]{rs.getString("no"), rs.getString("name")});                              
             }
             
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void search(){
    
        if(searchby.getSelectedIndex()==1){
        
           try {
            //
             DefaultTableModel model = (DefaultTableModel) table.getModel();
             model.setRowCount(0);
             pst = con.prepareStatement("select * from item_category where no like ?");
             pst.setString(1, "%"+searchfor.getText().trim()+"%");
             rs = pst.executeQuery();
             while(rs.next()){                
                 model.addRow(new Object[]{rs.getString("no"), rs.getString("name")});                              
             }
             
        } catch (Exception e) {
            System.out.println(e);
        } 
            
        }
        
        if(searchby.getSelectedIndex()==2){
        
           try {
            //
             DefaultTableModel model = (DefaultTableModel) table.getModel();
             model.setRowCount(0);
             pst = con.prepareStatement("select * from item_category where name like ?");
             pst.setString(1, "%"+searchfor.getText().trim()+"%");
             rs = pst.executeQuery();
             while(rs.next()){                
                 model.addRow(new Object[]{rs.getString("no"), rs.getString("name")});                              
             }
             
        } catch (Exception e) {
            System.out.println(e);
        } 
            
        }
        
    }
    
    public void reset(){
        cnot.setText("");
        cnat.setText("");
        desct.setText("");
        txt.setText("");
        txt2.setText("");
        searchby.setSelectedIndex(0);
        txt3.setText("");
        searchfor.setText("");
        table_load();
    }

    /**
     * Creates new form Item_Category_Details
     */
    public Item_Category_Details() {
        initComponents();
        cnot.setEnabled(false);
        con = Dbconnect.connect();
        table_load();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cnot = new javax.swing.JTextField();
        cnat = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        desct = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        done = new javax.swing.JButton();
        modify = new javax.swing.JButton();
        remove = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        txt = new javax.swing.JLabel();
        txt2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        searchby = new javax.swing.JComboBox<>();
        searchfor = new javax.swing.JTextField();
        txt3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        jLabel1.setText("ITEM CATEGORY DETAILS");

        jLabel2.setText("Category No");

        jLabel3.setText("Category Name");

        jLabel4.setText("Description");

        cnot.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cnotKeyReleased(evt);
            }
        });

        cnat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cnatActionPerformed(evt);
            }
        });
        cnat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cnatKeyReleased(evt);
            }
        });

        desct.setColumns(20);
        desct.setRows(5);
        jScrollPane1.setViewportView(desct);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Category No", "Category Name"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(table);

        done.setText("DONE");
        done.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doneActionPerformed(evt);
            }
        });

        modify.setText("MODIFY");
        modify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifyActionPerformed(evt);
            }
        });

        remove.setText("REMOVE");
        remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeActionPerformed(evt);
            }
        });

        jButton4.setText("DASHBOARD");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton1.setText("RESET");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel5.setText("Search by  :");

        jLabel6.setText("Search for :");

        searchby.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "Category No", "Category Name" }));
        searchby.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchbyActionPerformed(evt);
            }
        });
        searchby.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                searchbyPropertyChange(evt);
            }
        });

        searchfor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchforKeyReleased(evt);
            }
        });

        jButton2.setText("NEW");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txt2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                                    .addComponent(cnot, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                                    .addComponent(cnat, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
            .addGroup(layout.createSequentialGroup()
                .addGap(290, 290, 290)
                .addComponent(done)
                .addGap(46, 46, 46)
                .addComponent(modify)
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(remove)
                        .addGap(49, 49, 49)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchfor, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchby, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(cnot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cnat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt2, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(113, 113, 113))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5)
                                .addComponent(searchby, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txt3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(searchfor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(54, 54, 54)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(done)
                    .addComponent(modify)
                    .addComponent(remove)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addGap(8, 8, 8))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        Dashboard x = new Dashboard();
        x.setVisible(true);
        this.dispose();
        
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void cnotKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cnotKeyReleased

       Pattern p = Pattern.compile("^[0-9]*$");
       Matcher m = p.matcher(cnot.getText().trim());
       
       if(m.find()){
                txt.setText("");
            }
       else if(!m.find()){
                txt.setText("Invalid Input");
       }
       if(cnot.getText().trim().equals("")){
           txt.setText("");
                   }
        
        // TODO add your handling code here:
    }//GEN-LAST:event_cnotKeyReleased

    private void doneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doneActionPerformed

        if(cnot.getText().trim().equals("")||cnat.getText().trim().equals("")){
        
            if(cnot.getText().trim().equals("")){
                txt.setText("Invalid Input");
            }
            if(cnat.getText().trim().equals("")){
                txt2.setText("Invalid Input");
            }
            
        }
        else if(txt.getText().equals("")){
            
            try {
                pst = con.prepareStatement("select * from item_category where no = ?");
                pst.setString(1, cnot.getText());
                rs = pst.executeQuery();
                
                if(rs.next()){
                    JOptionPane.showMessageDialog(null, "This item category number already has a record.");
                }
                
                else{
                
                    pst = con.prepareStatement("insert into item_category values(?,?,?)");
                    pst.setString(1, cnot.getText());
                    pst.setString(2, cnat.getText());
                    pst.setString(3, desct.getText());
                    pst.execute();
                    JOptionPane.showMessageDialog(null, "Successfully Added");
                    reset();
                }
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error");
            }
            
        }
        
        // TODO add your handling code here:
    }//GEN-LAST:event_doneActionPerformed

    private void modifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifyActionPerformed

        if(cnot.getText().trim().equals("")||cnat.getText().trim().equals("")){
        
            if(cnot.getText().trim().equals("")){
                txt.setText("Invalid Input");
            }
            if(cnat.getText().trim().equals("")){
                txt2.setText("Invalid Input");
            }
            
        }else if(txt.getText().equals("")){
            try {
                pst = con.prepareStatement("select * from item_category where no = ?");
                pst.setString(1, cnot.getText());
                rs = pst.executeQuery();
                if(rs.next()){
                    pst = con.prepareStatement("update item_category set name = ?,des = ?  where no = ?");
                    pst.setString(1, cnat.getText().trim());
                    pst.setString(2, desct.getText().trim());
                    pst.setString(3, cnot.getText().trim());
                    pst.execute();
                    JOptionPane.showMessageDialog(null, "Successfully Updated");
                    reset();
                }
                else{
                    JOptionPane.showMessageDialog(null, "There is no record under this category number");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error");
            }
  
        }
        
        
        
        
        // TODO add your handling code here:
    }//GEN-LAST:event_modifyActionPerformed

    private void removeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeActionPerformed

        if(cnot.getText().trim().equals("")){
                txt.setText("Invalid Input");
            }
        else if(txt.getText().equals("")){
            
            try {
                pst = con.prepareStatement("select * from item_category where no = ?");
                pst.setString(1, cnot.getText());
                rs = pst.executeQuery();
                if(rs.next()){
                    pst = con.prepareStatement("delete from item_category where no = ?");
                    pst.setString(1, cnot.getText().trim());
                    pst.execute();
                    JOptionPane.showMessageDialog(null, "Successfully Deleted");
                    reset();
                }
                else{
                    JOptionPane.showMessageDialog(null, "There is no record under this category number");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error");
            }
            
        }
        
        // TODO add your handling code here:
    }//GEN-LAST:event_removeActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked

        int r = table.getSelectedRow();
        String selected = (String) table.getValueAt(r, 0);
        try {
            pst = con.prepareStatement("select * from item_category where no = ?");
            pst.setString(1, selected);
            rs = pst.executeQuery();
            if(rs.next()){
            
                txt.setText("");
                txt2.setText("");
                
                cnot.setText(rs.getString("no"));
                cnat.setText(rs.getString("name"));
                desct.setText(rs.getString("des"));                             
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
        // TODO add your handling code here:
    }//GEN-LAST:event_tableMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        reset();
        
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void searchbyPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_searchbyPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_searchbyPropertyChange

    private void searchbyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchbyActionPerformed

        if(searchby.getSelectedIndex()==0){
            txt3.setText("Select an option");
            table_load();
        }
        else{
            txt3.setText("");
            search();
        }
        
        // TODO add your handling code here:
    }//GEN-LAST:event_searchbyActionPerformed

    private void searchforKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchforKeyReleased

        if(searchby.getSelectedIndex()==0){
            txt3.setText("Select an option");
        }
        else if(searchfor.getText().trim().equals("")){
            table_load();
        }
        else{
        
            search();
        }
        
        // TODO add your handling code here:
    }//GEN-LAST:event_searchforKeyReleased

    private void cnatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cnatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cnatActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        try {
            
            pst = con.prepareStatement("select no from item_category");
            rs = pst.executeQuery();
            String last = "0";
            
            while(rs.next()){
            last = rs.getString("no");
            }
            
            Integer newnumber = Integer.parseInt(last)+1;
            String newno = String.valueOf(newnumber);
            reset();
            cnot.setText(newno);
            
            
        } catch (Exception e) {
            System.out.println(e);
        } 
        
        
        
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void cnatKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cnatKeyReleased

        if(cnat.getText().trim().equals("")){
            txt2.setText("Invalid Input");
        }
        else{
            txt2.setText("");
        }
        
        // TODO add your handling code here:
    }//GEN-LAST:event_cnatKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Item_Category_Details.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Item_Category_Details.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Item_Category_Details.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Item_Category_Details.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Item_Category_Details().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField cnat;
    private javax.swing.JTextField cnot;
    private javax.swing.JTextArea desct;
    private javax.swing.JButton done;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton modify;
    private javax.swing.JButton remove;
    private javax.swing.JComboBox<String> searchby;
    private javax.swing.JTextField searchfor;
    private javax.swing.JTable table;
    private javax.swing.JLabel txt;
    private javax.swing.JLabel txt2;
    private javax.swing.JLabel txt3;
    // End of variables declaration//GEN-END:variables
}
