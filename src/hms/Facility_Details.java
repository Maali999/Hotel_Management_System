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
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;


public class Facility_Details extends javax.swing.JFrame {
    Connection con = null;
    ResultSet rs =null;
    ResultSet rss =null;
    PreparedStatement pst = null;
    PreparedStatement psts = null;
    
    
    public void search(){
        
        String category = "";
        String searchfor = "";
    
        if(fcat.getSelectedIndex()==0){
            category = "%";
        }
        else{
            try {
                psts = con.prepareStatement("select no from facility_category where name = ?");
                    psts.setString(1, fcat.getSelectedItem().toString().trim());
                    rss = psts.executeQuery();
                    if(rss.next()){
                        category = rss.getString("no");
                    }
            } catch (Exception e) {
                System.out.println(e);
            }
            
        }
 
        
        if(fsearchfor.getText().trim().equals("")){
            searchfor = "%";
        }
        else{
            searchfor = "%"+fsearchfor.getText().trim()+"%";
        }
        
        if(fsearchby.getSelectedIndex()==0){
            
                try {
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    model.setRowCount(0);
                    pst = con.prepareStatement("select * from facility_details where no like ? and category like ?");
                    pst.setString(1, searchfor);
                    pst.setString(2, category);
                    rs = pst.executeQuery();
                    while(rs.next()){
                    String cat = "";
                    psts = con.prepareStatement("select name from facility_category where no = ?");
                    psts.setString(1, rs.getString("category"));
                    rss = psts.executeQuery();
                    if(rss.next()){
                        cat = rss.getString("name");
                    }
                 
                    model.addRow(new Object[]{rs.getString("no"), rs.getString("name"), cat, rs.getInt("cost")});                              
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }      
            }
            
            if(fsearchby.getSelectedIndex()==1){
            
                try {
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    model.setRowCount(0);
                    pst = con.prepareStatement("select * from facility_details where name like ? and category like ?");
                    pst.setString(1, searchfor);
                    pst.setString(2, category);
                    rs = pst.executeQuery();
                    while(rs.next()){
                    String cat = "";
                    psts = con.prepareStatement("select name from facility_category where no = ?");
                    psts.setString(1, rs.getString("category"));
                    rss = psts.executeQuery();
                    if(rss.next()){
                        cat = rss.getString("name");
                    }
                 
                    model.addRow(new Object[]{rs.getString("no"), rs.getString("name"), cat, rs.getInt("cost")});                              
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }      
            }
    
    }
    
    
    public void table_load(){
        
        try {
             DefaultTableModel model = (DefaultTableModel) table.getModel();
             model.setRowCount(0);
             pst = con.prepareStatement("select * from facility_details");
             rs = pst.executeQuery();
             while(rs.next()){
                 String cat = "";
                 psts = con.prepareStatement("select name from facility_category where no = ?");
                 psts.setString(1, rs.getString("category"));
                 rss = psts.executeQuery();
                 if(rss.next()){
                     cat = rss.getString("name");
                 }
                 
                 model.addRow(new Object[]{rs.getString("no"), rs.getString("name"), cat, rs.getInt("cost")});                              
             }
             
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }
    
    public void combo_load(){
    
        try {
            
             pst = con.prepareStatement("select name from facility_category");
             rs = pst.executeQuery();
             while(rs.next()){
             fcategory.addItem(rs.getString("name"));
             fcat.addItem(rs.getString("name"));
             }
            
        } catch (Exception e) {
            
            System.out.println(e);
            
        }    
    }
    
    public void reset(){
    
        fno.setText("");
        txt1.setText("");
        fname.setText("");
        txt2.setText("");
        fcategory.setSelectedIndex(0);
        txt3.setText("");
        fcost.setText("");
        txt4.setText("");
        fdes.setText("");
        fcat.setSelectedIndex(0);
        fsearchby.setSelectedIndex(0);
        fsearchfor.setText("");
        table_load();
        
        
    }
    
    public Facility_Details() {
        initComponents();
        fno.setEnabled(false);
        con = Dbconnect.connect();
        table_load();
        combo_load();
    }
    


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField2 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jTextField4 = new javax.swing.JTextField();
        jYearChooser1 = new com.toedter.calendar.JYearChooser();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        fno = new javax.swing.JTextField();
        fname = new javax.swing.JTextField();
        fcost = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        fdes = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        done = new javax.swing.JButton();
        modify = new javax.swing.JButton();
        remove = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        reset = new javax.swing.JButton();
        fcategory = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        fcat = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        fsearchby = new javax.swing.JComboBox<>();
        fsearchfor = new javax.swing.JTextField();
        txt1 = new javax.swing.JLabel();
        txt2 = new javax.swing.JLabel();
        txt4 = new javax.swing.JLabel();
        txt3 = new javax.swing.JLabel();
        newb = new javax.swing.JButton();

        jTextField2.setText("jTextField2");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jTextField4.setText("jTextField4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel7.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        jLabel7.setText("FACILITY DETAILS");

        jLabel1.setText("No                 :");

        jLabel2.setText("Name            :");

        jLabel3.setText("Category      :");

        jLabel4.setText("Cost Rs.        :");

        jLabel5.setText("Description  :");

        fcost.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                fcostKeyReleased(evt);
            }
        });

        jLabel6.setText("/=");

        fdes.setColumns(20);
        fdes.setRows(5);
        jScrollPane1.setViewportView(fdes);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Facility No", "Name", "Category", "Cost"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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

        reset.setText("RESET");
        reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetActionPerformed(evt);
            }
        });

        fcategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select" }));

        jLabel8.setText("Category      :");

        fcat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All" }));
        fcat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fcatActionPerformed(evt);
            }
        });

        jLabel9.setText("Search by  :");

        jLabel10.setText("Search for :");

        fsearchby.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Facility No", "Facility Name" }));
        fsearchby.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fsearchbyActionPerformed(evt);
            }
        });

        fsearchfor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                fsearchforKeyReleased(evt);
            }
        });

        newb.setText("NEW");
        newb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newbActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(262, 262, 262)
                                .addComponent(done)
                                .addGap(44, 44, 44)
                                .addComponent(modify)
                                .addGap(35, 35, 35)
                                .addComponent(remove)
                                .addGap(35, 35, 35)
                                .addComponent(reset))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jButton4)))
                        .addGap(0, 356, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel7))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(25, 25, 25)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(fcost, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel6)
                                        .addGap(18, 18, 18)
                                        .addComponent(txt4, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(fno, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(fcategory, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(fname, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txt1, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                                            .addComponent(txt2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txt3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(newb)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(fsearchfor, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(fcat, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(fsearchby, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(fno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(newb))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(fname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(fcategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(fcost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)
                        .addComponent(jLabel8)
                        .addComponent(fcat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9)
                        .addComponent(fsearchby, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(fsearchfor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(done)
                    .addComponent(modify)
                    .addComponent(remove)
                    .addComponent(reset))
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

    private void newbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newbActionPerformed

        try {
            
            pst = con.prepareStatement("select no from facility_details");
            rs = pst.executeQuery();
            String last = "0";
            
            while(rs.next()){
            last = rs.getString("no");
            }
            
            Integer newnumber = Integer.parseInt(last)+1;
            String newno = String.valueOf(newnumber);
            reset();
            fno.setText(newno);
            
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
        // TODO add your handling code here:
    }//GEN-LAST:event_newbActionPerformed

    private void doneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doneActionPerformed

        if(fno.getText().trim().equals("")||fname.getText().trim().equals("")||fcategory.getSelectedIndex()==0 || fcost.getText().trim().equals("")){
        
            if(fno.getText().trim().equals("")){ txt1.setText("Empty"); }
            if(fname.getText().trim().equals("")){ txt2.setText("Empty"); }
            if(fcategory.getSelectedIndex()==0){ txt3.setText("Select a category"); }
            if(fcost.getText().trim().equals("")){ txt4.setText("Empty"); }
            
        }
        else if(txt4.getText().trim().equals("")){
            
            try {
                pst = con.prepareStatement("select * from facility_details where no = ?");
                pst.setString(1, fno.getText());
                rs = pst.executeQuery();
                
                if(rs.next()){
                    JOptionPane.showMessageDialog(null, "This Facility No already has a record.");
                }
                
                else{
                    String cat = "";
                    psts = con.prepareStatement("select no from facility_category where name = ?");
                    psts.setString(1, (String) fcategory.getSelectedItem());
                    rss = psts.executeQuery();
                    if(rss.next()){
                     cat = rss.getString("no");
                    }
                
                    pst = con.prepareStatement("insert into facility_details values(?,?,?,?,?)");
                    pst.setString(1, fno.getText().trim());
                    pst.setString(2, fname.getText().trim());
                    pst.setString(3, cat);
                    pst.setInt(4, Integer.parseInt(fcost.getText().trim()));
                    pst.setString(5, fdes.getText().trim());
                    pst.execute();
                    JOptionPane.showMessageDialog(null, "Successfully Added");
                    reset();
                }
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error");
                System.out.println(ex);
            }
            
        }
        
        // TODO add your handling code here:
    }//GEN-LAST:event_doneActionPerformed

    private void fcostKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fcostKeyReleased

       Pattern p = Pattern.compile("^[0-9]*$");
        Matcher m = p.matcher(fcost.getText().trim());
        
        if(m.find()){ 
            txt4.setText("");                          
        }
        
        else if(!m.find()){
            txt4.setText("Invalid");
        }
        
        if(fcost.getText().trim().equals("")){
            txt4.setText("Empty");
        }
        
        // TODO add your handling code here:
    }//GEN-LAST:event_fcostKeyReleased

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked

        
        
        int r = table.getSelectedRow();
        String selected = (String) table.getValueAt(r, 0);
        try {
            pst = con.prepareStatement("select * from facility_details where no = ?");
            pst.setString(1, selected);
            rs = pst.executeQuery();
            if(rs.next()){
                
                String cat = "select";
            
                psts = con.prepareStatement("select name from facility_category where no = ?");
                psts.setString(1, rs.getString("category"));
                rss = psts.executeQuery();
                if(rss.next()){
                    cat = rss.getString("name");
                }
                
                txt1.setText("");
                txt2.setText("");
                txt3.setText("");
                txt4.setText("");
                
                fno.setText(rs.getString("no"));
                fname.setText(rs.getString("name"));
                fcategory.setSelectedItem(cat);
                fcost.setText(rs.getString("cost"));
                fdes.setText(rs.getString("des"));
                
                                           
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
        // TODO add your handling code here:
    }//GEN-LAST:event_tableMouseClicked

    private void resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetActionPerformed

        reset();
        
        // TODO add your handling code here:
    }//GEN-LAST:event_resetActionPerformed

    private void modifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifyActionPerformed

        if(fno.getText().trim().equals("")||fname.getText().trim().equals("")||fcategory.getSelectedIndex()==0 || fcost.getText().trim().equals("")){
        
            if(fno.getText().trim().equals("")){ txt1.setText("Empty"); }
            if(fname.getText().trim().equals("")){ txt2.setText("Empty"); }
            if(fcategory.getSelectedIndex()==0){ txt3.setText("Select a category"); }
            if(fcost.getText().trim().equals("")){ txt4.setText("Empty"); }
            
        }
        else if(txt4.getText().trim().equals("")){
            
            try {
                pst = con.prepareStatement("select * from facility_details where no = ?");
                pst.setString(1, fno.getText());
                rs = pst.executeQuery();

                if(rs.next()){
                    String cat = "";
                    psts = con.prepareStatement("select no from facility_category where name = ?");
                    psts.setString(1, (String) fcategory.getSelectedItem());
                    rss = psts.executeQuery();
                    if(rss.next()){
                     cat = rss.getString("no");
                    }
                
                    pst = con.prepareStatement("update facility_details set name = ?, category = ?, cost = ?, des = ? where no = ?");
                    pst.setString(5, fno.getText().trim());
                    pst.setString(1, fname.getText().trim());
                    pst.setString(2, cat);
                    pst.setInt(3, Integer.parseInt(fcost.getText().trim()));
                    pst.setString(4, fdes.getText().trim());
                    pst.execute();
                    JOptionPane.showMessageDialog(null, "Successfully Updated");
                    reset();
                }
                else{
                    JOptionPane.showMessageDialog(null, "No record under this Facility No");
                }
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error");
                System.out.println(ex);
            }
            
        }
        
        // TODO add your handling code here:
    }//GEN-LAST:event_modifyActionPerformed

    private void removeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeActionPerformed

        if(fno.getText().trim().equals("")){
            txt1.setText("Empty");
        }
        else{
        try {
                pst = con.prepareStatement("select * from facility_details where no = ?");
                pst.setString(1, fno.getText());
                rs = pst.executeQuery();
                if(rs.next()){
                    pst = con.prepareStatement("delete from facility_details where no = ?");
                    pst.setString(1, fno.getText().trim());
                    pst.execute();
                    JOptionPane.showMessageDialog(null, "Successfully Deleted");
                    reset();
                }
                else{
                    JOptionPane.showMessageDialog(null, "There is no record under this Facility No");
                }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error");
            System.out.println(ex);
        }
        }       
        // TODO add your handling code here:
    }//GEN-LAST:event_removeActionPerformed

    private void fcatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fcatActionPerformed

        search();
        
        // TODO add your handling code here:
    }//GEN-LAST:event_fcatActionPerformed

    private void fsearchbyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fsearchbyActionPerformed

        search();
        
        // TODO add your handling code here:
    }//GEN-LAST:event_fsearchbyActionPerformed

    private void fsearchforKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fsearchforKeyReleased

        search();
        
        // TODO add your handling code here:
    }//GEN-LAST:event_fsearchforKeyReleased

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
            java.util.logging.Logger.getLogger(Facility_Details.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Facility_Details.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Facility_Details.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Facility_Details.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Facility_Details().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton done;
    private javax.swing.JComboBox<String> fcat;
    private javax.swing.JComboBox<String> fcategory;
    private javax.swing.JTextField fcost;
    private javax.swing.JTextArea fdes;
    private javax.swing.JTextField fname;
    private javax.swing.JTextField fno;
    private javax.swing.JComboBox<String> fsearchby;
    private javax.swing.JTextField fsearchfor;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField4;
    private com.toedter.calendar.JYearChooser jYearChooser1;
    private javax.swing.JButton modify;
    private javax.swing.JButton newb;
    private javax.swing.JButton remove;
    private javax.swing.JButton reset;
    private javax.swing.JTable table;
    private javax.swing.JLabel txt1;
    private javax.swing.JLabel txt2;
    private javax.swing.JLabel txt3;
    private javax.swing.JLabel txt4;
    // End of variables declaration//GEN-END:variables
}
