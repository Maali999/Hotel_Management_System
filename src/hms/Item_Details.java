/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package hms;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author user
 */
public class Item_Details extends javax.swing.JFrame {
    Connection con = null;
    ResultSet rs =null;
    ResultSet rss =null;
    PreparedStatement pst = null;
    PreparedStatement psts = null;
    
    public void table_load(){
        try {
             DefaultTableModel model = (DefaultTableModel) table.getModel();
             model.setRowCount(0);
             pst = con.prepareStatement("select * from item_details");
             rs = pst.executeQuery();
             while(rs.next()){
                 String cat = "";
                 psts = con.prepareStatement("select name from item_category where no = ?");
                 psts.setString(1, rs.getString("category"));
                 rss = psts.executeQuery();
                 if(rss.next()){
                     cat = rss.getString("name");
                 }
                 
                 model.addRow(new Object[]{rs.getString("no"), rs.getString("name"), cat, rs.getString("capacity"), rs.getString("design")});                              
             }
             
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void combo_load(){
    
        try {
            
             pst = con.prepareStatement("select name from item_category");
             rs = pst.executeQuery();
             while(rs.next()){
             category.addItem(rs.getString("name"));
             categorys.addItem(rs.getString("name"));
             }
            
        } catch (Exception e) {
            
            System.out.println(e);
            
        }    
    }
    
    
    public void reset(){
        no.setText("");
        txt1.setText("");
        name.setText("");
        txt2.setText("");
        category.setSelectedIndex(0);
        txt3.setText("");
        capacity.setSelectedIndex(0);
        design.setSelectedIndex(0);
        des.setText("");
        designs.setSelectedIndex(0);
        capacity.setSelectedIndex(0);
        design.setSelectedIndex(0);
        searchbys.setSelectedIndex(0);
        searchfors.setText("");
        designs.setSelectedIndex(0);
        capacitys.setSelectedIndex(0);
        categorys.setSelectedIndex(0);
        table_load();
    }
    
    /**
     * Creates new form Item_Details
     */
    public Item_Details() {
        initComponents();
        no.setEnabled(false);
        con = Dbconnect.connect();
        combo_load();
        table_load();
        
    }
    
    public void search(){
        
        String searchford = "";
        String designd = "";
        String capacityd = "";
        String categoryd = "";
        
        if(searchfors.getText().trim().equals("")){
            searchford = "%"+""+"%";
        }
        else{
            searchford = "%"+searchfors.getText().trim()+"%";
        }
        
        
        if(designs.getSelectedIndex()==0){
            designd = "%"+""+"%";
        }
        else{
            designd = (String) designs.getSelectedItem();
        }
        
        
        if(capacitys.getSelectedIndex()==0){
            capacityd = "%"+""+"%";
        }
        else{
            capacityd = (String) capacitys.getSelectedItem();
        }
        
        
        if(categorys.getSelectedIndex()==0){
            categoryd = "%"+""+"%";
        }
        else{
            
            
            try {
                 psts = con.prepareStatement("select no from item_category where name = ?");
                 psts.setString(1, (String) categorys.getSelectedItem());
                 rss = psts.executeQuery();
                 if(rss.next()){
                     categoryd  = rss.getString("no");
                 }
            } catch (Exception e) {
                System.out.println(e);
            }
            
        }   
        
        if(searchbys.getSelectedIndex()==0){
            
            try {
             DefaultTableModel model = (DefaultTableModel) table.getModel();
             model.setRowCount(0);
             pst = con.prepareStatement("select * from item_details where no like ? and design like ? and capacity like ? and category like ?");
             pst.setString(1, searchford);
             pst.setString(2, designd);
             pst.setString(3, capacityd);
             pst.setString(4, categoryd);
             rs = pst.executeQuery();
             while(rs.next()){

                 String cat = "";
                 psts = con.prepareStatement("select name from item_category where no = ?");
                 psts.setString(1, rs.getString("category"));
                 rss = psts.executeQuery();
                 if(rss.next()){
                     cat = rss.getString("name");
                 }
                 
                    model.addRow(new Object[]{rs.getString("no"), rs.getString("name"), cat, rs.getString("capacity"), rs.getString("design")});                            
             }
             
        } catch (Exception e) {
            System.out.println(e);
        }
            
        }
        else{
        
            try {
             DefaultTableModel model = (DefaultTableModel) table.getModel();
             model.setRowCount(0);
             pst = con.prepareStatement("select * from item_details where name like ? and design like ? and capacity like ? and category like ?");
             pst.setString(1, searchford);
             pst.setString(2, designd);
             pst.setString(3, capacityd);
             pst.setString(4, categoryd);
             rs = pst.executeQuery();
             while(rs.next()){

                 String cat = "";
                 psts = con.prepareStatement("select name from item_category where no = ?");
                 psts.setString(1, rs.getString("category"));
                 rss = psts.executeQuery();
                 if(rss.next()){
                     cat = rss.getString("name");
                 }
                 
                    model.addRow(new Object[]{rs.getString("no"), rs.getString("name"), cat, rs.getString("capacity"), rs.getString("design")});                            
             }
             
        } catch (Exception e) {
            System.out.println(e);
        }
            
        }
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        no = new javax.swing.JTextField();
        name = new javax.swing.JTextField();
        category = new javax.swing.JComboBox<>();
        capacity = new javax.swing.JComboBox<>();
        design = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        des = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        done = new javax.swing.JButton();
        modify = new javax.swing.JButton();
        remove = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        txt1 = new javax.swing.JLabel();
        txt2 = new javax.swing.JLabel();
        txt3 = new javax.swing.JLabel();
        reset = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        searchfors = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        searchbys = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        designs = new javax.swing.JComboBox<>();
        capacitys = new javax.swing.JComboBox<>();
        categorys = new javax.swing.JComboBox<>();
        newitem = new javax.swing.JButton();

        jButton4.setText("DASHBOARD");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        jLabel1.setText("ITEM DETAILS");

        jLabel2.setText("Item No");

        jLabel3.setText("Name");

        jLabel4.setText("Category");

        jLabel5.setText("Capacity");

        jLabel6.setText("Design");

        jLabel7.setText("Description");

        name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nameKeyReleased(evt);
            }
        });

        category.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select" }));

        capacity.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Regular", "Large", "Extra-large", "Small", "Extra-small" }));

        design.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Without-Design", "With-Design" }));

        des.setColumns(20);
        des.setRows(5);
        jScrollPane1.setViewportView(des);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Item No", "Name", "Category", "Capacity", "Design"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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

        jButton5.setText("DASHBOARD");

        reset.setText("RESET");
        reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetActionPerformed(evt);
            }
        });

        jLabel8.setText("Search by :");

        searchfors.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchforsKeyReleased(evt);
            }
        });

        jLabel9.setText("Search for :");

        searchbys.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item No", "Name" }));
        searchbys.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchbysActionPerformed(evt);
            }
        });

        jLabel10.setText("Design    :");

        jLabel11.setText("Capacity  :");

        jLabel12.setText("Category :");

        designs.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Without-Design", "With-Design" }));
        designs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                designsActionPerformed(evt);
            }
        });

        capacitys.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Regular", "Large", "Extra-large", "Small", "Extra-small" }));
        capacitys.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                capacitysActionPerformed(evt);
            }
        });

        categorys.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All" }));
        categorys.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categorysActionPerformed(evt);
            }
        });

        newitem.setText("NEW");
        newitem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newitemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton5)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(done)
                                .addGap(41, 41, 41)
                                .addComponent(modify)
                                .addGap(14, 14, 14))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(design, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(capacity, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(no, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(name, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(category, javax.swing.GroupLayout.Alignment.LEADING, 0, 169, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txt3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(txt2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                                                    .addComponent(txt1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(18, 18, 18)
                                                .addComponent(newitem))))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 516, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(remove)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addComponent(jLabel12))
                                        .addGap(9, 9, 9)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(39, 39, 39)
                                        .addComponent(reset))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(categorys, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(capacitys, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(designs, javax.swing.GroupLayout.Alignment.TRAILING, 0, 120, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(searchbys, 0, 150, Short.MAX_VALUE)
                                            .addComponent(searchfors))))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(no, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(newitem)
                                .addGap(8, 8, 8)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(category, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(capacity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(design, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(searchfors, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(designs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(searchbys, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(capacitys, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(categorys, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(done)
                    .addComponent(modify)
                    .addComponent(remove)
                    .addComponent(reset))
                .addGap(2, 2, 2)
                .addComponent(jButton5)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void newitemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newitemActionPerformed

        try {
            
            pst = con.prepareStatement("select no from item_details");
            rs = pst.executeQuery();
            String last = "0";
            
            while(rs.next()){
            last = rs.getString("no");
            }
            
            Integer newnumber = Integer.parseInt(last)+1;
            String newno = String.valueOf(newnumber);
            reset();
            no.setText(newno);
            
            
        } catch (Exception e) {
            System.out.println(e);
        } 

        // TODO add your handling code here:
    }//GEN-LAST:event_newitemActionPerformed

    private void nameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nameKeyReleased

        if(name.getText().trim().equals("")){
            txt2.setText("Empty");
        }
        else{
            txt2.setText("");
        }
        
        // TODO add your handling code here:
    }//GEN-LAST:event_nameKeyReleased

    private void doneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doneActionPerformed

        if(no.getText().trim().equals("")||name.getText().trim().equals("")||category.getSelectedIndex()==0){
        
            if(no.getText().trim().equals("")){ txt1.setText("Empty"); }
            if(name.getText().trim().equals("")){ txt2.setText("Empty"); }
            if(category.getSelectedIndex()==0){ txt3.setText("Select a category"); }
            
        }
        else{
            
            try {
                pst = con.prepareStatement("select * from item_details where no = ?");
                pst.setString(1, no.getText());
                rs = pst.executeQuery();
                
                if(rs.next()){
                    JOptionPane.showMessageDialog(null, "This item number already has a record.");
                }
                
                else{
                    String cat = "";
                    psts = con.prepareStatement("select no from item_category where name = ?");
                    psts.setString(1, (String) category.getSelectedItem());
                    rss = psts.executeQuery();
                    if(rss.next()){
                     cat = rss.getString("no");
                    }
                
                    pst = con.prepareStatement("insert into item_details values(?,?,?,?,?,?)");
                    pst.setString(1, no.getText().trim());
                    pst.setString(2, name.getText().trim());
                    pst.setString(3, cat);
                    pst.setString(4, (String) capacity.getSelectedItem());
                    pst.setString(5, (String) design.getSelectedItem());
                    pst.setString(6, des.getText().trim());
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

    private void resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetActionPerformed

        reset();
        
        // TODO add your handling code here:
    }//GEN-LAST:event_resetActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked

        
        int r = table.getSelectedRow();
        String selected = (String) table.getValueAt(r, 0);
        try {
            pst = con.prepareStatement("select * from item_details where no = ?");
            pst.setString(1, selected);
            rs = pst.executeQuery();
            if(rs.next()){
                
                String cat = "select";
            
                psts = con.prepareStatement("select name from item_category where no = ?");
                psts.setString(1, rs.getString("category"));
                rss = psts.executeQuery();
                if(rss.next()){
                    cat = rss.getString("name");
                }
                
                txt1.setText("");
                txt2.setText("");
                txt3.setText("");
                
                no.setText(rs.getString("no"));
                name.setText(rs.getString("name"));
                category.setSelectedItem(cat);
                capacity.setSelectedItem(rs.getString("capacity"));
                design.setSelectedItem(rs.getString("design"));
                des.setText(rs.getString("des"));
                
                                           
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
     
        
        // TODO add your handling code here:
    }//GEN-LAST:event_tableMouseClicked

    private void modifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifyActionPerformed

        
        if(no.getText().trim().equals("")||name.getText().trim().equals("")||category.getSelectedIndex()==0){
        
            if(no.getText().trim().equals("")){ txt1.setText("Empty"); }
            if(name.getText().trim().equals("")){ txt2.setText("Empty"); }
            if(category.getSelectedIndex()==0){ txt3.setText("Select a category"); }
            
        }
        else{
            
            try {
                pst = con.prepareStatement("select * from item_details where no = ?");
                pst.setString(1, no.getText());
                rs = pst.executeQuery();
                
                
                
                if (rs.next()){
                    String cat = "";
                    psts = con.prepareStatement("select no from item_category where name = ?");
                    psts.setString(1, (String) category.getSelectedItem());
                    rss = psts.executeQuery();
                    if(rss.next()){
                     cat = rss.getString("no");
                    }
                
                    pst = con.prepareStatement("update item_details set name = ?, category = ?, capacity = ?, design = ?, des = ? where no = ?");
                    pst.setString(6, no.getText().trim());
                    pst.setString(1, name.getText().trim());
                    pst.setString(2, cat);
                    pst.setString(3, (String) capacity.getSelectedItem());
                    pst.setString(4, (String) design.getSelectedItem());
                    pst.setString(5, des.getText().trim());
                    pst.execute();
                    JOptionPane.showMessageDialog(null, "Successfully Updated");
                    reset();
                }
                
                else{
                    JOptionPane.showMessageDialog(null, "There is no record under this Item No");
                }
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error");
                System.out.println(ex);
            }
            
        }
        
        
        // TODO add your handling code here:
    }//GEN-LAST:event_modifyActionPerformed

    private void removeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeActionPerformed
        if(no.getText().trim().equals("")){
            txt1.setText("Empty");
        }
        else{
        try {
                pst = con.prepareStatement("select * from item_details where no = ?");
                pst.setString(1, no.getText());
                rs = pst.executeQuery();
                if(rs.next()){
                    pst = con.prepareStatement("delete from item_details where no = ?");
                    pst.setString(1, no.getText().trim());
                    pst.execute();
                    JOptionPane.showMessageDialog(null, "Successfully Deleted");
                    reset();
                }
                else{
                    JOptionPane.showMessageDialog(null, "There is no record under this Item No");
                }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error");
            System.out.println(ex);
        }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_removeActionPerformed

    private void searchforsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchforsKeyReleased

        search();
        
        // TODO add your handling code here:
    }//GEN-LAST:event_searchforsKeyReleased

    private void designsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_designsActionPerformed

        search();
        
        // TODO add your handling code here:
    }//GEN-LAST:event_designsActionPerformed

    private void capacitysActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_capacitysActionPerformed

        search();
        
        // TODO add your handling code here:
    }//GEN-LAST:event_capacitysActionPerformed

    private void categorysActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categorysActionPerformed

        search();
        
        // TODO add your handling code here:
    }//GEN-LAST:event_categorysActionPerformed

    private void searchbysActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchbysActionPerformed

        search();
        
        // TODO add your handling code here:
    }//GEN-LAST:event_searchbysActionPerformed

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
            java.util.logging.Logger.getLogger(Item_Details.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Item_Details.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Item_Details.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Item_Details.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Item_Details().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> capacity;
    private javax.swing.JComboBox<String> capacitys;
    private javax.swing.JComboBox<String> category;
    private javax.swing.JComboBox<String> categorys;
    private javax.swing.JTextArea des;
    private javax.swing.JComboBox<String> design;
    private javax.swing.JComboBox<String> designs;
    private javax.swing.JButton done;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JButton modify;
    private javax.swing.JTextField name;
    private javax.swing.JButton newitem;
    private javax.swing.JTextField no;
    private javax.swing.JButton remove;
    private javax.swing.JButton reset;
    private javax.swing.JComboBox<String> searchbys;
    private javax.swing.JTextField searchfors;
    private javax.swing.JTable table;
    private javax.swing.JLabel txt1;
    private javax.swing.JLabel txt2;
    private javax.swing.JLabel txt3;
    // End of variables declaration//GEN-END:variables
}
