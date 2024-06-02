/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package hms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author user
 */
public class Supplier_Details extends javax.swing.JFrame {
    Connection con = null;
    ResultSet rs =null;
    ResultSet rss =null;
    PreparedStatement pst = null;
    PreparedStatement psts = null;
    
    
    
    public void reset(){
    
    }
    
    public void cal_total(){
    
        DefaultTableModel model = (DefaultTableModel) table2.getModel();
        int r = table2.getRowCount();
        int i = 0;
        int total=0;
        while(i<r){
            total = total + Integer.parseInt(table2.getValueAt(i, 5).toString());
            i = i + 1;
        }
        grandtotal.setText(String.valueOf(total));
        
    }
    
    public void combo_load(){
    
        try {
            
             pst = con.prepareStatement("select name from item_category");
             rs = pst.executeQuery();
             while(rs.next()){
             categorys.addItem(rs.getString("name"));
             }
            
        } catch (Exception e) {         
            System.out.println(e);            
        }    
    }
    
    public void table_load(){
        try {
             DefaultTableModel model = (DefaultTableModel) table1.getModel();
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
             DefaultTableModel model = (DefaultTableModel) table1.getModel();
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
             DefaultTableModel model = (DefaultTableModel) table1.getModel();
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
     * Creates new form Supplier_Details
     */
    public Supplier_Details() {
        initComponents();
        no.setEnabled(false);
        ino.setEnabled(false);
        itotal.setEnabled(false);
        con = Dbconnect.connect();
        combo_load();
        table_load();
        cal_total();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField6 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cregno = new javax.swing.JTextField();
        cname = new javax.swing.JTextField();
        clocation = new javax.swing.JTextField();
        ctel = new javax.swing.JTextField();
        cemail = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cptitle = new javax.swing.JComboBox<>();
        cpname = new javax.swing.JTextField();
        cpposition = new javax.swing.JTextField();
        cptel = new javax.swing.JTextField();
        txt8 = new javax.swing.JLabel();
        txt6 = new javax.swing.JLabel();
        txt7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        no = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        searchfors = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        searchbys = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        designs = new javax.swing.JComboBox<>();
        capacitys = new javax.swing.JComboBox<>();
        categorys = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        table1 = new javax.swing.JTable();
        jLabel16 = new javax.swing.JLabel();
        ino = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        iqty = new javax.swing.JTextField();
        txt10 = new javax.swing.JLabel();
        add = new javax.swing.JButton();
        txt9 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        iunitprice = new javax.swing.JTextField();
        txt11 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        itotal = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txt12 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        createdate = new com.toedter.calendar.JDateChooser();
        receivedate = new com.toedter.calendar.JDateChooser();
        status = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        txt14 = new javax.swing.JLabel();
        txt15 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        newno = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table2 = new javax.swing.JTable();
        jButton8 = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        grandtotal = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        txt13 = new javax.swing.JLabel();
        txt1 = new javax.swing.JLabel();
        txt4 = new javax.swing.JLabel();
        txt5 = new javax.swing.JLabel();
        txt = new javax.swing.JLabel();
        txt2 = new javax.swing.JLabel();
        txt3 = new javax.swing.JLabel();

        jTextField6.setText("jTextField6");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        jLabel1.setText("SUPPLIER DETAILS");

        jLabel2.setText("Reg No");

        jLabel3.setText("Company Name");

        jLabel4.setText("Location");

        jLabel5.setText("Tel.No");

        jLabel6.setText("Email Address");

        cregno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cregnoKeyReleased(evt);
            }
        });

        cname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cnameKeyReleased(evt);
            }
        });

        clocation.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                clocationKeyReleased(evt);
            }
        });

        ctel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ctelKeyReleased(evt);
            }
        });

        cemail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cemailKeyReleased(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Contact Person's Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jLabel7.setText("Name");

        jLabel8.setText("Position");

        jLabel9.setText("Tel.No");

        cptitle.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mr.", "Ms.", "Mrs.", "Rev." }));

        cpname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cpnameKeyReleased(evt);
            }
        });

        cpposition.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cppositionKeyReleased(evt);
            }
        });

        cptel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cptelKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cpposition, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt7, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cptel, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt8, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(cptitle, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cpname, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt6, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(8, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(cptitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cpname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cpposition, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8)))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cptel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

        jLabel10.setText("Order No");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Item Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jLabel11.setText("Search by :");

        searchfors.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchforsKeyReleased(evt);
            }
        });

        jLabel12.setText("Search for :");

        searchbys.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item No", "Name" }));
        searchbys.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchbysActionPerformed(evt);
            }
        });

        jLabel13.setText("Design    :");

        jLabel14.setText("Capacity  :");

        jLabel15.setText("Category :");

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

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

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
        table1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(table1);

        jLabel16.setText("Item No        :");

        ino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inoActionPerformed(evt);
            }
        });

        jLabel17.setText("Quantity       :");

        iqty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                iqtyKeyReleased(evt);
            }
        });

        add.setText("ADD");
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });

        jLabel20.setText("Unit PriceRs. :");

        iunitprice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                iunitpriceKeyReleased(evt);
            }
        });

        jLabel22.setText("Total   Rs.     :");

        jLabel23.setText("/=");

        jLabel25.setText("/=");

        jButton9.setText("CALCULATE");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(capacitys, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(252, 252, 252)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(designs, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(categorys, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(searchbys, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(searchfors, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addGap(18, 18, 18)
                                .addComponent(ino, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(10, 10, 10))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel20)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(iunitprice, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabel23)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txt10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(iqty, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(29, 29, 29)
                                        .addComponent(txt11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(add)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(txt12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(itotal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel25)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButton9)))
                                        .addGap(0, 0, Short.MAX_VALUE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 662, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel16)
                                .addComponent(ino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txt9, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel20)
                                    .addComponent(iunitprice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel23))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel17)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(iqty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txt11, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(txt10, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(itotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25)
                    .addComponent(jButton9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(designs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)
                            .addComponent(searchbys, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)
                            .addComponent(categorys, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(capacitys, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel12)
                                .addComponent(searchfors, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel14)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txt12, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(add)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel27.setText("Create Date");

        jLabel28.setText("Receive Date");

        jLabel29.setText("Status");

        receivedate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                receivedateMouseClicked(evt);
            }
        });
        receivedate.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                receivedatePropertyChange(evt);
            }
        });

        status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Incomplete", "Complete" }));

        jButton1.setText("DONE");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("MODIFY");

        jButton3.setText("REMOVE");

        jButton10.setText("RESET");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28)
                    .addComponent(jLabel27))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(receivedate, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(createdate, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt15, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                    .addComponent(txt14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(17, 17, 17)
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(115, 115, 115)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2)
                    .addComponent(jButton10))
                .addGap(50, 50, 50))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3)
                            .addComponent(jButton10)
                            .addComponent(txt15, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel27)
                            .addComponent(createdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel29)
                                .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txt14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(receivedate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28))))
        );

        jButton4.setText("DASHBOARD");

        newno.setText("NEW");
        newno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newnoActionPerformed(evt);
            }
        });

        jButton6.setText("SEARCH");

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Added Items"));

        table2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Item No", "Item Name", "Unit Price", "Quantity", "Item Total"
            }
        ));
        jScrollPane1.setViewportView(table2);

        jButton8.setText("RESET");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jLabel26.setText("Total   :");

        jLabel32.setText("/=");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 520, Short.MAX_VALUE)
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(grandtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel32)
                        .addGap(18, 18, 18)
                        .addComponent(txt13, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addComponent(jButton8))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton8)
                        .addComponent(jLabel26)
                        .addComponent(grandtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel32))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton4)
                            .addComponent(jLabel1)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cemail, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(ctel, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(clocation, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cname, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addComponent(jLabel2)
                                            .addGap(77, 77, 77))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel10)
                                            .addGap(67, 67, 67)))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(cregno, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(no, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap(1252, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(307, 307, 307)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(newno)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txt3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txt2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txt5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txt4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButton6)
                                        .addGap(18, 18, 18)
                                        .addComponent(txt1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(23, 23, 23)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 951, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(no, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(cregno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(cname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(clocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(ctel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(cemail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(newno)
                            .addComponent(txt, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton6)
                            .addComponent(txt1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(355, 355, 355)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(jButton4)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchforsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchforsKeyReleased

        search();
        
// TODO add your handling code here:
    }//GEN-LAST:event_searchforsKeyReleased

    private void searchbysActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchbysActionPerformed

        search();

// TODO add your handling code here:
    }//GEN-LAST:event_searchbysActionPerformed

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

    private void table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table1MouseClicked

        int r  = table1.getSelectedRow();
        ino.setText(table1.getValueAt(r, 0).toString());
        
// TODO add your handling code here:
    }//GEN-LAST:event_table1MouseClicked

    private void inoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inoActionPerformed

    private void cregnoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cregnoKeyReleased

        Pattern p = Pattern.compile("^[0-9A-Z]+$");
        Matcher m = p.matcher(cregno.getText().trim());
        
        if(m.find()){ 
            txt1.setText("");                          
        }
        
        else if(!m.find()){
            txt1.setText("Invalid");
        }
        
        if(cregno.getText().trim().equals("")){
            txt1.setText("");
        }
        
        // TODO add your handling code here:
    }//GEN-LAST:event_cregnoKeyReleased

    private void ctelKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ctelKeyReleased

        Pattern p = Pattern.compile("^\\d{10}$");
        Matcher m = p.matcher(ctel.getText().trim());
        
        if(m.find()){ 
            txt4.setText("");                          
        }
        
        else if(!m.find()){
            txt4.setText("Invalid");
        }
        
        if(ctel.getText().trim().equals("")){
            txt4.setText("");
        }
        
        // TODO add your handling code here:
    }//GEN-LAST:event_ctelKeyReleased

    private void cemailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cemailKeyReleased
       
        Pattern p = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
        Matcher m = p.matcher(cemail.getText().trim());
        
        if(m.find()){ 
            txt5.setText("");                          
        }
        
        else if(!m.find()){
            txt5.setText("Invalid");
        }
        
        if(cemail.getText().trim().equals("")){
            txt5.setText("");
        }
        
        // TODO add your handling code here:
    }//GEN-LAST:event_cemailKeyReleased

    private void cptelKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cptelKeyReleased

        Pattern p = Pattern.compile("^\\d{10}$");
        Matcher m = p.matcher(cptel.getText().trim());
        
        if(m.find()){ 
            txt8.setText("");                          
        }
        
        else if(!m.find()){
            txt8.setText("Invalid");
        }
        
        if(cptel.getText().trim().equals("")){
            txt8.setText("");
        }
        
// TODO add your handling code here:
    }//GEN-LAST:event_cptelKeyReleased

    private void iunitpriceKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_iunitpriceKeyReleased

        itotal.setText("");
        
        Pattern p = Pattern.compile("^[0-9]*$");
        Matcher m = p.matcher(iunitprice.getText().trim());
        
        if(m.find()){ 
            txt10.setText("");                          
        }
        
        else if(!m.find()){
            txt10.setText("Invalid");
        }
        
        if(iunitprice.getText().trim().equals("")){
            txt10.setText("");
        }
        
        // TODO add your handling code here:
    }//GEN-LAST:event_iunitpriceKeyReleased

    private void iqtyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_iqtyKeyReleased

        itotal.setText("");
        
        Pattern p = Pattern.compile("^[0-9]*$");
        Matcher m = p.matcher(iqty.getText().trim());
        
        if(m.find()){ 
            txt11.setText("");                          
        }
        
        else if(!m.find()){
            txt11.setText("Invalid");
        }
        
        if(iqty.getText().trim().equals("")){
            txt11.setText("");
        }
        
        // TODO add your handling code here:
    }//GEN-LAST:event_iqtyKeyReleased

    private void receivedateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_receivedateMouseClicked
 
        // TODO add your handling code here:
    }//GEN-LAST:event_receivedateMouseClicked

    private void receivedatePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_receivedatePropertyChange
    Date create = createdate.getDate();
    if(create==null){

    }
    else{

        create.setHours(0);
        create.setMinutes(0);
        create.setSeconds(0);
        Date receive = receivedate.getDate();
        receive.setHours(0);
        receive.setMinutes(0);
        receive.setSeconds(0);
        if(receive.before(create)){
            txt15.setText("Invalid");
        }
        else{
            txt15.setText("");
        }
    }
        // TODO add your handling code here:
    }//GEN-LAST:event_receivedatePropertyChange

    private void newnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newnoActionPerformed
        reset();
        
        try {          
            pst = con.prepareStatement("select no from supplier_details");
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

        Date today = new Date();
        today.setHours(0);
        today.setMinutes(0);
        today.setSeconds(0);
        createdate.setDate(today);
        
        // TODO add your handling code here:
    }//GEN-LAST:event_newnoActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed

        if(iunitprice.getText().trim().equals("")||iqty.getText().trim().equals("")){
        
            if(iunitprice.getText().trim().equals("")){txt10.setText("Empty");}
            if(iqty.getText().trim().equals("")){txt11.setText("Empty");}
        }
        else if(txt10.getText().trim().equals("") && txt11.getText().trim().equals("")){
        itotal.setText(String.valueOf(Integer.valueOf(iunitprice.getText())*Integer.valueOf(iqty.getText())));
        txt12.setText("");
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed

        if(ino.getText().trim().equals("") || itotal.getText().trim().equals("")){
        
            if(ino.getText().trim().equals("")){txt9.setText("Empty");}
            if(itotal.getText().trim().equals("")){txt12.setText("Empty");}
            
        }
        else{
            DefaultTableModel model = (DefaultTableModel) table2.getModel();
            int r = table2.getRowCount();
            if(r<4){
                model.addRow(new Object[]{r+1 , ino.getText().trim(), "hi", iunitprice.getText().trim(), iqty.getText().trim(), itotal.getText().trim()});
                ino.setText("");
                iunitprice.setText("");
                iqty.setText("");
                itotal.setText("");
                txt9.setText("");
                txt10.setText("");
                txt11.setText("");
                txt12.setText("");
                txt13.setText("");
                cal_total();
            }else{
                txt13.setText("Full!");
            }
        }
        
        // TODO add your handling code here:
    }//GEN-LAST:event_addActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed

        DefaultTableModel model = (DefaultTableModel) table2.getModel();
        model.setRowCount(0);
        cal_total();
        txt13.setText("");
        
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        if(no.getText().trim().equals("")||cregno.getText().trim().equals("")||cname.getText().trim().equals("")||clocation.getText().trim().equals("")
           ||ctel.getText().trim().equals("")||cemail.getText().trim().equals("")|| cpname.getText().trim().equals("")||cpposition.getText().trim().equals("")
           ||cptel.getText().trim().equals("")||table2.getRowCount()==0||createdate.getDate()==null||receivedate.getDate()==null){
            if(no.getText().trim().equals("")){txt.setText("Empty");}
            if(cregno.getText().trim().equals("")){txt1.setText("Empty");}
            if(cname.getText().trim().equals("")){txt2.setText("Empty");}
            if(clocation.getText().trim().equals("")){txt3.setText("Empty");}
            if(ctel.getText().trim().equals("")){txt4.setText("Empty");}
            if(cemail.getText().trim().equals("")){txt5.setText("Empty");}
            if(cpname.getText().trim().equals("")){txt6.setText("Empty");}
            if(cpposition.getText().trim().equals("")){txt7.setText("Empty");}
            if(cptel.getText().trim().equals("")){txt8.setText("Empty");}
            if(table2.getRowCount()==0){txt13.setText("Empty");}
            if(createdate.getDate()==null){txt14.setText("Empty");}
            if(receivedate.getDate()==null){txt15.setText("Empty");}
        }
        else if(txt4.getText().trim().equals("") && txt5.getText().trim().equals("") && txt8.getText().trim().equals("") && txt15.getText().trim().equals("")){
            
            try {
                pst = con.prepareStatement("select * from supplier_details where no = ?");
                pst.setString(1, no.getText());
                rs = pst.executeQuery();
                
                if(rs.next()){
                    JOptionPane.showMessageDialog(null, "This Supplier No already has a record.");
                }
                
                else{                
                    pst = con.prepareStatement("insert into supplier_details values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                    pst.setString(1, no.getText().trim());
                    pst.setString(2, cregno.getText().trim());
                    pst.setString(3, cname.getText().trim());
                    pst.setString(4, clocation.getText().trim());
                    pst.setString(5, ctel.getText().trim());
                    pst.setString(6, cemail.getText().trim());
                    pst.setString(7, cptitle.getSelectedItem().toString());
                    pst.setString(8, cpname.getText().trim());
                    pst.setString(9, cpposition.getText().trim());
                    pst.setString(10, cptel.getText().trim());
                    
                    switch(table2.getRowCount()){
                    
                        case 4 -> {
                            pst.setString(11, String.valueOf(table2.getValueAt(0, 1)));
                            pst.setInt(12, Integer.parseInt(String.valueOf(table2.getValueAt(0, 3))));
                            pst.setInt(13, Integer.parseInt(String.valueOf(table2.getValueAt(0, 4))));
                            pst.setInt(14, Integer.parseInt(String.valueOf(table2.getValueAt(0, 5))));
                            pst.setString(15, String.valueOf(table2.getValueAt(1, 1)));
                            pst.setInt(16, Integer.parseInt(String.valueOf(table2.getValueAt(1, 3))));
                            pst.setInt(17, Integer.parseInt(String.valueOf(table2.getValueAt(1, 4))));
                            pst.setInt(18, Integer.parseInt(String.valueOf(table2.getValueAt(1, 5))));
                            pst.setString(19, String.valueOf(table2.getValueAt(2, 1)));
                            pst.setInt(20, Integer.parseInt(String.valueOf(table2.getValueAt(2, 3))));
                            pst.setInt(21, Integer.parseInt(String.valueOf(table2.getValueAt(2, 4))));
                            pst.setInt(22, Integer.parseInt(String.valueOf(table2.getValueAt(2, 5))));
                            pst.setString(23, String.valueOf(table2.getValueAt(3, 1)));
                            pst.setInt(24, Integer.parseInt(String.valueOf(table2.getValueAt(3, 3))));
                            pst.setInt(25, Integer.parseInt(String.valueOf(table2.getValueAt(3, 4))));
                            pst.setInt(26, Integer.parseInt(String.valueOf(table2.getValueAt(3, 5))));
                        }
                        case 3 -> {
                            pst.setString(11, String.valueOf(table2.getValueAt(0, 1)));
                            pst.setInt(12, Integer.parseInt(String.valueOf(table2.getValueAt(0, 3))));
                            pst.setInt(13, Integer.parseInt(String.valueOf(table2.getValueAt(0, 4))));
                            pst.setInt(14, Integer.parseInt(String.valueOf(table2.getValueAt(0, 5))));
                            pst.setString(15, String.valueOf(table2.getValueAt(1, 1)));
                            pst.setInt(16, Integer.parseInt(String.valueOf(table2.getValueAt(1, 3))));
                            pst.setInt(17, Integer.parseInt(String.valueOf(table2.getValueAt(1, 4))));
                            pst.setInt(18, Integer.parseInt(String.valueOf(table2.getValueAt(1, 5))));
                            pst.setString(19, String.valueOf(table2.getValueAt(2, 1)));
                            pst.setInt(20, Integer.parseInt(String.valueOf(table2.getValueAt(2, 3))));
                            pst.setInt(21, Integer.parseInt(String.valueOf(table2.getValueAt(2, 4))));
                            pst.setInt(22, Integer.parseInt(String.valueOf(table2.getValueAt(2, 5))));
                            pst.setString(23, "");
                            pst.setInt(24, 0);
                            pst.setInt(25, 0);
                            pst.setInt(26, 0);                        }
                        case 2 -> {
                            pst.setString(11, String.valueOf(table2.getValueAt(0, 1)));
                            pst.setInt(12, Integer.parseInt(String.valueOf(table2.getValueAt(0, 3))));
                            pst.setInt(13, Integer.parseInt(String.valueOf(table2.getValueAt(0, 4))));
                            pst.setInt(14, Integer.parseInt(String.valueOf(table2.getValueAt(0, 5))));
                            pst.setString(15, String.valueOf(table2.getValueAt(1, 1)));
                            pst.setInt(16, Integer.parseInt(String.valueOf(table2.getValueAt(1, 3))));
                            pst.setInt(17, Integer.parseInt(String.valueOf(table2.getValueAt(1, 4))));
                            pst.setInt(18, Integer.parseInt(String.valueOf(table2.getValueAt(1, 5))));
                            pst.setString(19, "");
                            pst.setInt(20, 0);
                            pst.setInt(21, 0);
                            pst.setInt(22, 0);
                            pst.setString(23, "");
                            pst.setInt(24, 0);
                            pst.setInt(25, 0);
                            pst.setInt(26, 0);
                        }
                        case 1 -> {
                            pst.setString(11, String.valueOf(table2.getValueAt(0, 1)));
                            pst.setInt(12, Integer.parseInt(String.valueOf(table2.getValueAt(0, 3))));
                            pst.setInt(13, Integer.parseInt(String.valueOf(table2.getValueAt(0, 4))));
                            pst.setInt(14, Integer.parseInt(String.valueOf(table2.getValueAt(0, 5))));
                            pst.setString(15, "");
                            pst.setInt(16, 0);
                            pst.setInt(17, 0);
                            pst.setInt(18, 0);
                            pst.setString(19, "");
                            pst.setInt(20, 0);
                            pst.setInt(21, 0);
                            pst.setInt(22, 0);
                            pst.setString(23, "");
                            pst.setInt(24, 0);
                            pst.setInt(25, 0);
                            pst.setInt(26, 0);
                        }
                    }
                    pst.setInt(27, Integer.parseInt(grandtotal.getText().trim()));
                    
                    java.util.Date crdate = createdate.getDate();
                    java.sql.Date cdate = new java.sql.Date(crdate.getTime());
                    pst.setDate(28, cdate);
                    
                    java.util.Date redate = createdate.getDate();
                    java.sql.Date rdate = new java.sql.Date(redate.getTime());
                    pst.setDate(29, rdate);
                    
                    pst.setString(30, status.getSelectedItem().toString());
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
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cnameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cnameKeyReleased

        txt2.setText("");
        
        // TODO add your handling code here:
    }//GEN-LAST:event_cnameKeyReleased

    private void clocationKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_clocationKeyReleased

        txt3.setText("");
        
        // TODO add your handling code here:
    }//GEN-LAST:event_clocationKeyReleased

    private void cpnameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cpnameKeyReleased

        txt6.setText("");
        
        // TODO add your handling code here:
    }//GEN-LAST:event_cpnameKeyReleased

    private void cppositionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cppositionKeyReleased

        txt7.setText("");
        
        // TODO add your handling code here:
    }//GEN-LAST:event_cppositionKeyReleased

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
            java.util.logging.Logger.getLogger(Supplier_Details.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Supplier_Details.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Supplier_Details.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Supplier_Details.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Supplier_Details().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add;
    private javax.swing.JComboBox<String> capacitys;
    private javax.swing.JComboBox<String> categorys;
    private javax.swing.JTextField cemail;
    private javax.swing.JTextField clocation;
    private javax.swing.JTextField cname;
    private javax.swing.JTextField cpname;
    private javax.swing.JTextField cpposition;
    private javax.swing.JTextField cptel;
    private javax.swing.JComboBox<String> cptitle;
    private com.toedter.calendar.JDateChooser createdate;
    private javax.swing.JTextField cregno;
    private javax.swing.JTextField ctel;
    private javax.swing.JComboBox<String> designs;
    private javax.swing.JTextField grandtotal;
    private javax.swing.JTextField ino;
    private javax.swing.JTextField iqty;
    private javax.swing.JTextField itotal;
    private javax.swing.JTextField iunitprice;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JButton newno;
    private javax.swing.JTextField no;
    private com.toedter.calendar.JDateChooser receivedate;
    private javax.swing.JComboBox<String> searchbys;
    private javax.swing.JTextField searchfors;
    private javax.swing.JComboBox<String> status;
    private javax.swing.JTable table1;
    private javax.swing.JTable table2;
    private javax.swing.JLabel txt;
    private javax.swing.JLabel txt1;
    private javax.swing.JLabel txt10;
    private javax.swing.JLabel txt11;
    private javax.swing.JLabel txt12;
    private javax.swing.JLabel txt13;
    private javax.swing.JLabel txt14;
    private javax.swing.JLabel txt15;
    private javax.swing.JLabel txt2;
    private javax.swing.JLabel txt3;
    private javax.swing.JLabel txt4;
    private javax.swing.JLabel txt5;
    private javax.swing.JLabel txt6;
    private javax.swing.JLabel txt7;
    private javax.swing.JLabel txt8;
    private javax.swing.JLabel txt9;
    // End of variables declaration//GEN-END:variables
}
