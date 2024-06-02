/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package hms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Full Time
 */
public class Places extends javax.swing.JFrame {
    Connection con = null;
    ResultSet rs =null;
    ResultSet rss =null;
    PreparedStatement pst = null;
    PreparedStatement psts = null;
    private String opened_by;

    public void load_locations(){
        try {
            
             pst = con.prepareStatement("select * from location_details");
             rs = pst.executeQuery();
             while(rs.next()){
             location.addItem(rs.getString("name"));
             }
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void load_blocks(){
        try {
            
             psts = con.prepareStatement("select no from location_details where name = ?");
             psts.setString(1, location.getSelectedItem().toString());
             rss = psts.executeQuery();
             if(rss.next()){
             pst = con.prepareStatement("select distinct block from place_details where lno = ?");
             pst.setInt(1, rss.getInt("no"));
             rs = pst.executeQuery();
             while(rs.next()){
             block.addItem(rs.getString("block"));
             }
             }
        } catch (Exception e) {
            System.out.println(e);
        }       
    }
    
    public void load_floors(){
            try {
            
             psts = con.prepareStatement("select no from location_details where name = ?");
             psts.setString(1, location.getSelectedItem().toString());
             rss = psts.executeQuery();
             if(rss.next()){
             pst = con.prepareStatement("select distinct floor from place_details where lno = ? and block = ?");
             pst.setInt(1, rss.getInt("no"));
             pst.setString(2, block.getSelectedItem().toString());
             rs = pst.executeQuery();
             while(rs.next()){
             floor.addItem(rs.getString("floor"));
             }
             }
        } catch (Exception e) {
            System.out.println(e);
        } 
    }
    
    public void load_capacities(){
    
        try {
            pst = con.prepareStatement("select distinct capacity from place_details order by capacity");
            rs = pst.executeQuery();
            while(rs.next()){
                mincap.addItem(String.valueOf(rs.getInt("capacity")));
                maxcap.addItem(String.valueOf(rs.getInt("capacity")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }
    
    public void search(){
    
        String locationd = "";
        
        if(location.getSelectedIndex()==0){
            locationd = "%";
        }else{
            locationd = location.getSelectedItem().toString();
        }
        
        String blockd = "";
        
        if(block.getSelectedIndex()==0){
            blockd = "%";
        }else{
            blockd = block.getSelectedItem().toString();
        }
        
        String floord = "";
        
        if(floor.getSelectedIndex()==0){
            floord = "%";
        }else{
            floord = floor.getSelectedItem().toString();
        }
        
        Integer mincapacity = 0;
        
        if(mincap.getSelectedIndex()==0){
            mincapacity = 0;
        }else{
            mincapacity = Integer.valueOf(mincap.getSelectedItem().toString());
        }
        
        Integer maxcapacity = 999999999;
        
        if(maxcap.getSelectedIndex()==0){
            maxcapacity = 999999999;
        }else{
            maxcapacity = Integer.valueOf(maxcap.getSelectedItem().toString());
        }
        
        String statusd = "";
        
        if(status.getSelectedIndex()==0){
            statusd = "%";
        }else{
            statusd = status.getSelectedItem().toString();
        }
        
        String searchford = "";
        
        if(searchfor.getText().trim().equals("")){
            searchford = "%";
        }else{
            searchford = "%"+searchfor.getText().trim()+"%";
        }
        
        try {
             DefaultTableModel model = (DefaultTableModel) table.getModel();
             model.setRowCount(0);
             
             if(searchby.getSelectedIndex()==0){
             pst = con.prepareStatement("SELECT place_details.no, place_details.name, location_details.name, location_details.address, place_details.block, "
                     + "place_details.floor, place_details.capacity, place_details.f1, place_details.f2, place_details.f3, place_details.f4, place_details.status "
                     + "from place_details join location_details on place_details.lno = location_details.no where location_details.name like ? and "
                     + "place_details.block like ? and place_details.floor like ? and place_details.capacity >= ? and place_details.capacity <= ? and place_details.status like ? and "
                     + "place_details.no like ? order by place_details.capacity");
             
             pst.setString(1, locationd);
             pst.setString(2, blockd);
             pst.setString(3, floord);
             pst.setInt(4, mincapacity);
             pst.setInt(5, maxcapacity);
             pst.setString(6, statusd);
             pst.setString(7, searchford);          
             rs = pst.executeQuery();
             }
             if(searchby.getSelectedIndex()==1){
             pst = con.prepareStatement("SELECT place_details.no, place_details.name, location_details.name, location_details.address, place_details.block, "
                     + "place_details.floor, place_details.capacity, place_details.f1, place_details.f2, place_details.f3, place_details.f4, place_details.status "
                     + "from place_details join location_details on place_details.lno = location_details.no where location_details.name like ? and "
                     + "place_details.block like ? and place_details.floor like ? and place_details.capacity >= ? and place_details.capacity <= ?  and place_details.status like ? and "
                     + "place_details.name like ? order by place_details.capacity");
             
             pst.setString(1, locationd);
             pst.setString(2, blockd);
             pst.setString(3, floord);
             pst.setInt(4, mincapacity);
             pst.setInt(5, maxcapacity);
             pst.setString(6, statusd);
             pst.setString(7, searchford);          
             rs = pst.executeQuery();   
             }
             while(rs.next()){
                 String fac1 = "";
                 if(rs.getString("place_details.f1").equals("")){
                     fac1 = "";
                 }else{
                 psts = con.prepareStatement("select name from facility_details where no = ?");
                 psts.setInt(1, Integer.parseInt(rs.getString("place_details.f1")));
                 rss = psts.executeQuery();
                 if(rss.next()){
                     fac1 = rss.getString("name");
                 }
                 }
                 
                 String fac2 = "";
                 if(rs.getString("place_details.f2").equals("")){
                     fac2 = "";
                 }else{
                 psts = con.prepareStatement("select name from facility_details where no = ?");
                 psts.setInt(1, Integer.parseInt(rs.getString("place_details.f2")));
                 rss = psts.executeQuery();
                 if(rss.next()){
                     fac2 = rss.getString("name");
                 }
                 }
                 
                 String fac3 = "";
                 if(rs.getString("place_details.f3").equals("")){
                     fac3 = "";
                 }else{
                 psts = con.prepareStatement("select name from facility_details where no = ?");
                 psts.setInt(1, Integer.parseInt(rs.getString("place_details.f3")));
                 rss = psts.executeQuery();
                 if(rss.next()){
                     fac3 = rss.getString("name");
                 }
                 }
                 
                 String fac4 = "";
                 if(rs.getString("place_details.f4").equals("")){
                     fac4 = "";
                 }else{
                 psts = con.prepareStatement("select name from facility_details where no = ?");
                 psts.setInt(1, Integer.parseInt(rs.getString("place_details.f4")));
                 rss = psts.executeQuery();
                 if(rss.next()){
                     fac4 = rss.getString("name");
                 }
                 }
                 
                 model.addRow(new Object[]{rs.getString("place_details.no"), rs.getString("place_details.name"), rs.getString("location_details.name"), rs.getString("location_details.address"), rs.getString("place_details.block"),
                                           rs.getString("place_details.floor"), rs.getInt("place_details.capacity"), fac1, fac2, fac3, fac4, rs.getString("place_details.status")});                              
             }
             
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }
    
    /**
     * Creates new form Places
     */
    public Places() {
        initComponents();
        con = Dbconnect.connect();
        load_locations();
        load_capacities();
        search();
        select.hide();
    }
    
    public Places(String opened_by) {
        this.opened_by = opened_by;
        initComponents();
        con = Dbconnect.connect();
        load_locations();
        load_capacities();
        search();
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        location = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        block = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        floor = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        mincap = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        searchby = new javax.swing.JComboBox<>();
        searchfor = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        select = new javax.swing.JButton();
        back = new javax.swing.JButton();
        dashborad = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        status = new javax.swing.JComboBox<>();
        txt = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        maxcap = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        location.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All" }));
        location.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                locationMouseClicked(evt);
            }
        });
        location.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                locationActionPerformed(evt);
            }
        });

        jLabel1.setText("Location :");

        jLabel2.setText("Block :");

        block.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All" }));
        block.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                blockMouseClicked(evt);
            }
        });
        block.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blockActionPerformed(evt);
            }
        });

        jLabel3.setText("Floor :");

        floor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All" }));
        floor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                floorActionPerformed(evt);
            }
        });

        jLabel4.setText("Min capacity :");

        mincap.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All" }));
        mincap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mincapActionPerformed(evt);
            }
        });

        jLabel5.setText("Search by   :");

        jLabel6.setText("Search for :");

        searchby.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Place No", "Place Name" }));
        searchby.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchbyActionPerformed(evt);
            }
        });

        searchfor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchforKeyReleased(evt);
            }
        });

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "No", "Name", "Location", "Address", "Block", "Floor", "Capacity", "Facility", "Facility", "Facility", "Facility", "Status"
            }
        ));
        jScrollPane1.setViewportView(table);

        select.setText("SELECT");
        select.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectActionPerformed(evt);
            }
        });

        back.setText("BACK");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        dashborad.setText("DASHBOARD");
        dashborad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dashboradActionPerformed(evt);
            }
        });

        jLabel7.setText("Status            :");

        status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Available", "Not Available" }));
        status.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statusActionPerformed(evt);
            }
        });

        jLabel8.setText("Max capacity :");

        maxcap.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All" }));
        maxcap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maxcapActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(dashborad)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(select)
                                .addGap(49, 49, 49)
                                .addComponent(back))
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel2)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(floor, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(533, 533, 533)
                                                .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(block, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(440, 440, 440)
                                                .addComponent(jLabel8)
                                                .addGap(18, 18, 18)
                                                .addComponent(maxcap, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(53, 53, 53)
                                                .addComponent(jLabel6)))
                                        .addGap(18, 18, 18)
                                        .addComponent(searchfor, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel7)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(location, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(442, 442, 442)
                                                .addComponent(jLabel4)))
                                        .addGap(18, 18, 18)
                                        .addComponent(mincap, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(50, 50, 50)
                                        .addComponent(jLabel5)
                                        .addGap(18, 18, 18)
                                        .addComponent(searchby, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(4, 4, 4))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(514, 514, 514)
                        .addComponent(txt, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(location, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(mincap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchby, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(block, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchfor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8)
                    .addComponent(maxcap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(floor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txt, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(select)
                    .addComponent(back)
                    .addComponent(dashborad))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void locationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_locationActionPerformed
       block.setSelectedIndex(0);
       floor.setSelectedIndex(0);
       
        int r = block.getItemCount();
            while(1<r){
            block.removeItemAt(1);
            r=r-1;
        }
       
        int i = floor.getItemCount();
            while(1<i){
            floor.removeItemAt(1);
            i=i-1;
        }
            
        if(location.getSelectedIndex()==0){
            block.setSelectedIndex(0);
            floor.setSelectedIndex(0);
        }
        else{
            load_blocks();
        }
        
        search();
        
        // TODO add your handling code here:
    }//GEN-LAST:event_locationActionPerformed

    private void blockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_blockActionPerformed
        floor.setSelectedIndex(0);
        int i = floor.getItemCount();
            while(1<i){
            floor.removeItemAt(1);
            i=i-1;
        }    
        if(block.getSelectedIndex()==0){
            floor.setSelectedIndex(0);
        }
        else{
            load_floors();
        }
        
        search();
        
        // TODO add your handling code here:
    }//GEN-LAST:event_blockActionPerformed

    private void locationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_locationMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_locationMouseClicked

    private void blockMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_blockMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_blockMouseClicked

    private void floorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_floorActionPerformed

        search();
        
        // TODO add your handling code here:
    }//GEN-LAST:event_floorActionPerformed

    private void mincapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mincapActionPerformed

        search();
        
        // TODO add your handling code here:
    }//GEN-LAST:event_mincapActionPerformed

    private void statusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statusActionPerformed

        search();
        
        // TODO add your handling code here:
    }//GEN-LAST:event_statusActionPerformed

    private void searchbyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchbyActionPerformed

        search();
        
        // TODO add your handling code here:
    }//GEN-LAST:event_searchbyActionPerformed

    private void searchforKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchforKeyReleased

        search();
        
        // TODO add your handling code here:
    }//GEN-LAST:event_searchforKeyReleased

    private void selectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectActionPerformed
        if(opened_by.equals("place_details")){
        int r = table.getSelectedRow();
        if(r==-1){
            txt.setText("Select a row.");
        }
        else{
            Place_Details x = new Place_Details(table.getValueAt(r, 0).toString());
            x.setVisible(true);
            this.dispose();
        }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_selectActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed

        if(opened_by == null){
            Dashboard x = new Dashboard();
            x.setVisible(true);
            this.dispose();
        }
        
        else if(opened_by.equals("place_details")){
            Place_Details x = new Place_Details();
            x.setVisible(true);
            this.dispose();
        }
        

        
        // TODO add your handling code here:
    }//GEN-LAST:event_backActionPerformed

    private void dashboradActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dashboradActionPerformed

        Dashboard x = new Dashboard();
        x.setVisible(true);
        this.dispose();
        
        // TODO add your handling code here:
    }//GEN-LAST:event_dashboradActionPerformed

    private void maxcapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maxcapActionPerformed

        search();

        // TODO add your handling code here:
    }//GEN-LAST:event_maxcapActionPerformed

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
            java.util.logging.Logger.getLogger(Places.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Places.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Places.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Places.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Places().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back;
    private javax.swing.JComboBox<String> block;
    private javax.swing.JButton dashborad;
    private javax.swing.JComboBox<String> floor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> location;
    private javax.swing.JComboBox<String> maxcap;
    private javax.swing.JComboBox<String> mincap;
    private javax.swing.JComboBox<String> searchby;
    private javax.swing.JTextField searchfor;
    private javax.swing.JButton select;
    private javax.swing.JComboBox<String> status;
    private javax.swing.JTable table;
    private javax.swing.JLabel txt;
    // End of variables declaration//GEN-END:variables
}
