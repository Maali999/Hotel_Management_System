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
public class Place_Details extends javax.swing.JFrame {
    Connection con = null;
    ResultSet rs =null;
    ResultSet rss =null;
    PreparedStatement pst = null;
    PreparedStatement psts = null;
    private String no;
    
    public void table_load(){
    
        try {
             DefaultTableModel model = (DefaultTableModel) table.getModel();
             model.setRowCount(0);
             pst = con.prepareStatement("select * from location_details");
             rs = pst.executeQuery();
             while(rs.next()){                
                 model.addRow(new Object[]{rs.getString("no"), rs.getString("name"), rs.getString("address")});                              
             }
             
        } catch (Exception e) {
            System.out.println(e);
        }
    
    }
    
    public void table2_load(){
        try {
             DefaultTableModel model = (DefaultTableModel) table2.getModel();
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
                 
                 model.addRow(new Object[]{rs.getString("no"), rs.getString("name"), cat, rs.getString("cost")});                              
             }
             
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void search(){
    
        if(lsearchfor.getText().trim().equals("")){
            table_load();
        }
        else{
        
            if(lsearchby.getSelectedIndex()==0){
            
                try {
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    model.setRowCount(0);
                    pst = con.prepareStatement("select * from location_details where no like ?");
                    pst.setString(1, "%"+lsearchfor.getText().trim()+"%");
                    rs = pst.executeQuery();
                    while(rs.next()){                
                        model.addRow(new Object[]{rs.getString("no"), rs.getString("name"), rs.getString("address")});                              
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }      
            }
            
            if(lsearchby.getSelectedIndex()==1){
            
                try {
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    model.setRowCount(0);
                    pst = con.prepareStatement("select * from location_details where name like ?");
                    pst.setString(1, "%"+lsearchfor.getText().trim()+"%");
                    rs = pst.executeQuery();
                    while(rs.next()){                
                        model.addRow(new Object[]{rs.getString("no"), rs.getString("name"), rs.getString("address")});                              
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }      
            }
            
            if(lsearchby.getSelectedIndex()==2){
            
                try {
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    model.setRowCount(0);
                    pst = con.prepareStatement("select * from location_details where address like ?");
                    pst.setString(1, "%"+lsearchfor.getText().trim()+"%");
                    rs = pst.executeQuery();
                    while(rs.next()){                
                        model.addRow(new Object[]{rs.getString("no"), rs.getString("name"), rs.getString("address")});                              
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }      
            }
            
        }
        
    }
    
    public void search_facility(){
        
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
                    DefaultTableModel model = (DefaultTableModel) table2.getModel();
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
                    DefaultTableModel model = (DefaultTableModel) table2.getModel();
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
    
    public void facility_combo_load(){
    
        try {
            
             pst = con.prepareStatement("select name from facility_category");
             rs = pst.executeQuery();
             while(rs.next()){
             fcat.addItem(rs.getString("name"));
             }
            
        } catch (Exception e) {
            
            System.out.println(e);
            
        }    
    }
    
    public void reset(){
        
    }
    
    public void fill_all(){
        DefaultTableModel model = (DefaultTableModel) table3.getModel();
        model.setRowCount(0);
        try {
            pst = con.prepareStatement("select place_details.no, place_details.lno, location_details.name, location_details.address, place_details.block, "
                    + "place_details.name, place_details.cost, place_details.floor, place_details.capacity, place_details.f1, place_details.f2, place_details.f3, "
                    + "place_details.f4, place_details.status, place_details.des from place_details join location_details on place_details.lno = location_details.no "
                    + "where place_details.no = ?");
            pst.setString(1, no);
            rs = pst.executeQuery();
            if(rs.next()){
            placeno.setText(rs.getString("place_details.no"));
            lno.setText(rs.getString("place_details.lno"));
            lname.setText(rs.getString("location_details.name"));
            address.setText(rs.getString("location_details.address"));
            block.setText(rs.getString("place_details.block"));
            placename.setText(rs.getString("place_details.name"));
            cost.setText(rs.getString("place_details.cost"));
            floor.setText(rs.getString("place_details.floor"));
            capacity.setText(rs.getString("place_details.capacity"));
            status.setSelectedItem(rs.getString("place_details.status"));
            des.setText(rs.getString("place_details.des"));
            if(!rs.getString("place_details.f1").equals("")){
                psts = con.prepareStatement("select facility_details.no, facility_details.name, facility_category.name, facility_details.cost   from facility_details "
                                            + "join facility_category on facility_category.no = facility_details.category where facility_details.no = ?");
                psts.setInt(1, Integer.parseInt(rs.getString("place_details.f1")));
                rss = psts.executeQuery();
                if(rss.next()){
                    model.addRow(new Object[]{1, rss.getString("facility_details.no"), rss.getString("facility_details.name"), rss.getString("facility_category.name"), rss.getInt("facility_details.cost")});
                }
            }
            if(!rs.getString("place_details.f2").equals("")){
                psts = con.prepareStatement("select facility_details.no, facility_details.name, facility_category.name, facility_details.cost   from facility_details "
                                            + "join facility_category on facility_category.no = facility_details.category where facility_details.no = ?");
                psts.setInt(1, Integer.parseInt(rs.getString("place_details.f2")));
                rss = psts.executeQuery();
                if(rss.next()){
                    model.addRow(new Object[]{2, rss.getString("facility_details.no"), rss.getString("facility_details.name"), rss.getString("facility_category.name"), rss.getInt("facility_details.cost")});
                }
            }
            if(!rs.getString("place_details.f3").equals("")){
                psts = con.prepareStatement("select facility_details.no, facility_details.name, facility_category.name, facility_details.cost   from facility_details "
                                            + "join facility_category on facility_category.no = facility_details.category where facility_details.no = ?");
                psts.setInt(1, Integer.parseInt(rs.getString("place_details.f3")));
                rss = psts.executeQuery();
                if(rss.next()){
                    model.addRow(new Object[]{3, rss.getString("facility_details.no"), rss.getString("facility_details.name"), rss.getString("facility_category.name"), rss.getInt("facility_details.cost")});
                }
            }
            if(!rs.getString("place_details.f4").equals("")){
                psts = con.prepareStatement("select facility_details.no, facility_details.name, facility_category.name, facility_details.cost   from facility_details "
                                            + "join facility_category on facility_category.no = facility_details.category where facility_details.no = ?");
                psts.setInt(1, Integer.parseInt(rs.getString("place_details.f4")));
                rss = psts.executeQuery();
                if(rss.next()){
                    model.addRow(new Object[]{4, rss.getString("facility_details.no"), rss.getString("facility_details.name"), rss.getString("facility_category.name"), rss.getInt("facility_details.cost")});
                }
            }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }
    
    
    /**
     * Creates new form Place_Details
     */
    public Place_Details() {
        initComponents();
        lno.setEnabled(false);
        lname.setEnabled(false);
        address.setEnabled(false);
        placeno.setEnabled(false);
        con = Dbconnect.connect();
        facility_combo_load();
        table_load();
        table2_load();
    }
    
    public Place_Details(String no) {
        this.no = no;
        initComponents();
        lno.setEnabled(false);
        lname.setEnabled(false);
        address.setEnabled(false);
        placeno.setEnabled(false);
        con = Dbconnect.connect();
        facility_combo_load();
        table_load();
        table2_load();
        fill_all();
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
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lno = new javax.swing.JTextField();
        lname = new javax.swing.JTextField();
        address = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        lsearchby = new javax.swing.JComboBox<>();
        lsearchfor = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        ltxt = new javax.swing.JLabel();
        place_div = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        txt6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        block = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        placename = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        cost = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        floor = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        capacity = new javax.swing.JTextField();
        txt1 = new javax.swing.JLabel();
        txt2 = new javax.swing.JLabel();
        txt3 = new javax.swing.JLabel();
        txt4 = new javax.swing.JLabel();
        txt5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        table3 = new javax.swing.JTable();
        jButton8 = new javax.swing.JButton();
        txt7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table2 = new javax.swing.JTable();
        fsearchby = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        fcat = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        fsearchfor = new javax.swing.JTextField();
        select = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        totalcost = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        status = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        des = new javax.swing.JTextArea();
        calculate = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        placeno = new javax.swing.JTextField();
        jButton9 = new javax.swing.JButton();
        txt = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        existing = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        jLabel1.setText("PLACE DETAILS");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Location", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jLabel2.setText("No");

        jLabel3.setText("Name");

        jLabel4.setText("Address");

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Location No", "Location Name", "Address"
            }
        ));
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(table);

        lsearchby.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Location No", "Location Name", "Address" }));

        lsearchfor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lsearchforKeyReleased(evt);
            }
        });

        jLabel26.setText("Search by  :");

        jLabel27.setText("Search for :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lname, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                            .addComponent(lno)
                            .addComponent(address)))
                    .addComponent(ltxt, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane4)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lsearchby, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lsearchfor, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(lno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(lname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(address, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 23, Short.MAX_VALUE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lsearchby, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lsearchfor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27)
                    .addComponent(ltxt, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        place_div.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Place", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Place Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jLabel5.setText("Block Name");

        block.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                blockKeyReleased(evt);
            }
        });

        jLabel7.setText("Name");

        placename.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                placenameKeyReleased(evt);
            }
        });

        jLabel15.setText("Cost Rs.");

        cost.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                costKeyReleased(evt);
            }
        });

        jLabel16.setText("/=");

        jLabel6.setText("Floor No");

        floor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                floorKeyReleased(evt);
            }
        });

        jLabel8.setText("Capacity | Qty");

        capacity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                capacityKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addGap(32, 32, 32))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(170, 170, 170)
                        .addComponent(txt6, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cost, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel6))
                                        .addGap(57, 57, 57))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addGap(47, 47, 47)))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(capacity, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                                    .addComponent(placename, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(block, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(floor))))))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel16)
                        .addGap(0, 189, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                            .addComponent(txt2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(block, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txt1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(placename, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txt2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15)
                        .addComponent(cost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel16))
                    .addComponent(txt3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(floor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txt4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(capacity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txt5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(202, 202, 202)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txt6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Select facilities"));

        table3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Facility No", "Name", "Category", "Cost"
            }
        ));
        jScrollPane5.setViewportView(table3);

        jButton8.setText("RESET");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        table2.setModel(new javax.swing.table.DefaultTableModel(
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
        table2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(table2);

        fsearchby.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Facility No", "Facility Name" }));
        fsearchby.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fsearchbyActionPerformed(evt);
            }
        });

        jLabel10.setText("Search by  :");

        jLabel9.setText("Category      :");

        fcat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All" }));
        fcat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fcatActionPerformed(evt);
            }
        });

        jLabel11.setText("Search for :");

        fsearchfor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                fsearchforKeyReleased(evt);
            }
        });

        select.setText("SELECT");
        select.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(15, 15, 15)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 591, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(43, 43, 43)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 534, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addGap(0, 0, Short.MAX_VALUE)
                                            .addComponent(jLabel11))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(jLabel9)
                                            .addGap(18, 18, 18)
                                            .addComponent(fcat, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel10)))
                                    .addGap(44, 44, 44)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(fsearchby, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(fsearchfor, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txt7, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(121, 121, 121))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jButton8)
                                .addGap(44, 44, 44)
                                .addComponent(select)))))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fsearchby, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel9)
                    .addComponent(fcat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(fsearchfor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(select)
                    .addComponent(jButton8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel17.setText("Total Cost Rs.");

        jLabel18.setText("/=");

        jLabel19.setText("Status");

        status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Available", "Not Available" }));

        jLabel20.setText("Description");

        des.setColumns(20);
        des.setRows(5);
        jScrollPane1.setViewportView(des);

        calculate.setText("SHOW");
        calculate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calculateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(jLabel20)
                    .addComponent(jLabel19))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(status, javax.swing.GroupLayout.Alignment.LEADING, 0, 192, Short.MAX_VALUE)
                            .addComponent(totalcost, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel18)
                        .addGap(32, 32, 32)
                        .addComponent(calculate))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(90, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(totalcost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(calculate))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout place_divLayout = new javax.swing.GroupLayout(place_div);
        place_div.setLayout(place_divLayout);
        place_divLayout.setHorizontalGroup(
            place_divLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(place_divLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(place_divLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        place_divLayout.setVerticalGroup(
            place_divLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(place_divLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(place_divLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(place_divLayout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        jLabel12.setText("Place No");

        jButton9.setText("NEW");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton4.setText("DASHBOARD");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        existing.setText("EXISTING");
        existing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                existingActionPerformed(evt);
            }
        });

        jButton5.setText("DONE");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("MODIFY");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("REMOVE");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1)
                            .addComponent(place_div, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(placeno, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton9)
                        .addGap(16, 16, 16)
                        .addComponent(existing)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(jButton5)
                        .addGap(63, 63, 63)
                        .addComponent(jButton6)
                        .addGap(50, 50, 50)
                        .addComponent(jButton7))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton4)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12)
                        .addComponent(placeno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton9)
                        .addComponent(existing))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton5)
                        .addComponent(jButton6)
                        .addComponent(jButton7)))
                .addGap(20, 20, 20)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(place_div, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked

        int r = table.getSelectedRow();
        String selected = (String) table.getValueAt(r, 0);
        try {
            pst = con.prepareStatement("select * from location_details where no = ?");
            pst.setString(1, selected);
            rs = pst.executeQuery();
            if(rs.next()){

                lno.setText(rs.getString("no"));
                lname.setText(rs.getString("name"));
                address.setText(rs.getString("address"));
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_tableMouseClicked

    private void lsearchforKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lsearchforKeyReleased

        search();

        // TODO add your handling code here:
    }//GEN-LAST:event_lsearchforKeyReleased

    private void table2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_table2MouseClicked

    private void fcatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fcatActionPerformed

        search_facility();
        
        // TODO add your handling code here:
    }//GEN-LAST:event_fcatActionPerformed

    private void fsearchbyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fsearchbyActionPerformed

        search_facility();
        
        // TODO add your handling code here:
    }//GEN-LAST:event_fsearchbyActionPerformed

    private void fsearchforKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fsearchforKeyReleased

        search_facility();
        
        // TODO add your handling code here:
    }//GEN-LAST:event_fsearchforKeyReleased

    private void selectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectActionPerformed

        int r = table2.getSelectedRow();
        String selected = (String) table2.getValueAt(r, 0);
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

                DefaultTableModel model = (DefaultTableModel) table3.getModel();
               if(table3.getRowCount()==0){              
                model.addRow(new Object[]{"1", rs.getString("no"), rs.getString("name"), cat, rs.getString("cost")});
                txt7.setText("");
               }
               else if(table3.getRowCount()==1){
               if(table3.getValueAt(0, 1).toString().trim().equals(rs.getString("no"))){
                   txt7.setText("This facility is already added.");
               }
               else{    
                model.addRow(new Object[]{"2", rs.getString("no"), rs.getString("name"), cat, rs.getString("cost")});
                txt7.setText("");
               }
               }
               else if(table3.getRowCount()==2){
               if(table3.getValueAt(0, 1).toString().trim().equals(rs.getString("no"))){
                   txt7.setText("This facility is already added.");
               }
               else if(table3.getValueAt(1, 1).toString().trim().equals(rs.getString("no"))){
                   txt7.setText("This facility is already added.");
               }
               else{
                model.addRow(new Object[]{"3", rs.getString("no"), rs.getString("name"), cat, rs.getString("cost")});
                txt7.setText("");
               }
               
               } 
               else if(table3.getRowCount()==3){
                if(table3.getValueAt(0, 1).toString().trim().equals(rs.getString("no"))){
                   txt7.setText("This facility is already added.");
                }
                else if(table3.getValueAt(1, 1).toString().trim().equals(rs.getString("no"))){
                   txt7.setText("This facility is already added.");
                }
                else if(table3.getValueAt(2, 1).toString().trim().equals(rs.getString("no"))){
                   txt7.setText("This facility is already added.");
                }
                else{
                model.addRow(new Object[]{"4", rs.getString("no"), rs.getString("name"), cat, rs.getString("cost")});
                txt7.setText("");
                }
               
               
               }
               else{
                   txt7.setText("Full!");
               }
            
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        
        // TODO add your handling code here:
    }//GEN-LAST:event_selectActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed

        DefaultTableModel model = (DefaultTableModel) table3.getModel();
        model.setRowCount(0);
        txt7.setText("");

// TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void blockKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_blockKeyReleased

        if(block.getText().trim().equals("")){
            txt1.setText("Empty");
        }else{
            txt1.setText("");
        }
        
        // TODO add your handling code here:
    }//GEN-LAST:event_blockKeyReleased

    private void placenameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_placenameKeyReleased

        if(placename.getText().trim().equals("")){
            txt2.setText("Empty");
        }else{
            txt2.setText("");
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_placenameKeyReleased

    private void costKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_costKeyReleased

        Pattern p = Pattern.compile("^[0-9]*$");
        Matcher m = p.matcher(cost.getText().trim());
        
        if(m.find()){ 
            txt3.setText("");                          
        }
        
        else if(!m.find()){
            txt3.setText("Invalid");
        }
        
        if(cost.getText().trim().equals("")){
            txt3.setText("Empty");
        }
        
        // TODO add your handling code here:
    }//GEN-LAST:event_costKeyReleased

    private void floorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_floorKeyReleased
       
        if(floor.getText().trim().equals("")){
            txt4.setText("Empty");
        }else{
            txt4.setText("");
        }
        
        // TODO add your handling code here:
    }//GEN-LAST:event_floorKeyReleased

    private void capacityKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_capacityKeyReleased

        Pattern p = Pattern.compile("^[0-9]*$");
        Matcher m = p.matcher(capacity.getText().trim());
        
        if(m.find()){ 
            txt5.setText("");                          
        }
        
        else if(!m.find()){
            txt5.setText("Invalid");
        }
        
        if(capacity.getText().trim().equals("")){
            txt5.setText("Empty");
        }
        
        // TODO add your handling code here:
    }//GEN-LAST:event_capacityKeyReleased

    private void calculateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calculateActionPerformed

        totalcost.setText("");
        
        if(cost.getText().trim().equals("") || !txt3.getText().trim().equals("")){
            if(cost.getText().trim().equals("")){
                txt3.setText("Empty");
            }
        }
        
        else if(table3.getRowCount()==0){
            txt7.setText("Select Facilities");
        }
        else{
            int x = table3.getRowCount();
            int i = 0;
            int facilitycost=0;
            while(i<x){
                facilitycost = facilitycost + Integer.valueOf(String.valueOf(table3.getValueAt(i, 4)));
                i++;
            }
            totalcost.setText(Integer.toString(facilitycost+Integer.valueOf(cost.getText().trim())));
        }
        
        // TODO add your handling code here:
    }//GEN-LAST:event_calculateActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        
    if(placeno.getText().trim().equals("") || lno.getText().trim().equals("") || block.getText().trim().equals("") || placename.getText().trim().equals("") || 
       cost.getText().trim().equals("") || floor.getText().trim().equals("") || capacity.getText().trim().equals("") || table3.getRowCount()==0){
    
        if(placeno.getText().trim().equals("")){
            txt.setText("Empty");
        }
        
        if(lno.getText().trim().equals("")){
            ltxt.setText("Empty");
        }
        
        if(block.getText().trim().equals("")){
            txt1.setText("Empty");
        }
        
        if(placename.getText().trim().equals("")){
            txt2.setText("Empty");
        }
        
        if(cost.getText().trim().equals("")){
            txt3.setText("Empty");
        }
        
        if(floor.getText().trim().equals("")){
            txt4.setText("Empty");
        }
        
        if(capacity.getText().trim().equals("")){
            txt5.setText("Empty");
        }
        
        if(table3.getRowCount()==0){
            txt7.setText("Select Facilities");
        }
    
    }
    
    else if(txt3.getText().trim().equals("") || txt5.getText().trim().equals("")){
    
        try {
                pst = con.prepareStatement("select * from place_details where no = ?");
                pst.setString(1, placeno.getText());
                rs = pst.executeQuery();
                
                if(rs.next()){
                    JOptionPane.showMessageDialog(null, "This Place No already has a record.");
                }
                
                else{
                
                    pst = con.prepareStatement("insert into place_details values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
                    pst.setString(1, placeno.getText().trim());
                    pst.setString(2, lno.getText().trim());
                    pst.setString(3, block.getText().trim());
                    pst.setString(4, placename.getText().trim());
                    pst.setInt(5, Integer.parseInt(cost.getText().trim()));
                    pst.setString(6, floor.getText().trim());
                    pst.setInt(7, Integer.parseInt(capacity.getText().trim()));                    
                    
                    switch(table3.getRowCount()){
                    
                        case 4 -> {
                            pst.setInt(8, Integer.parseInt(String.valueOf(table3.getValueAt(0, 1))));
                            pst.setInt(9, Integer.parseInt(String.valueOf(table3.getValueAt(1, 1))));
                            pst.setInt(10, Integer.parseInt(String.valueOf(table3.getValueAt(2, 1))));
                            pst.setInt(11, Integer.parseInt(String.valueOf(table3.getValueAt(3, 1))));
                        }
                        case 3 -> {
                            pst.setInt(8, Integer.parseInt(String.valueOf(table3.getValueAt(0, 1))));
                            pst.setInt(9, Integer.parseInt(String.valueOf(table3.getValueAt(1, 1))));
                            pst.setInt(10, Integer.parseInt(String.valueOf(table3.getValueAt(2, 1))));
                            pst.setString(11, "");
                        }
                        case 2 -> {
                            pst.setInt(8, Integer.parseInt(String.valueOf(table3.getValueAt(0, 1))));
                            pst.setInt(9, Integer.parseInt(String.valueOf(table3.getValueAt(1, 1))));
                            pst.setString(10, "");
                            pst.setString(11, "");
                        }
                        case 1 -> {
                            pst.setInt(8, Integer.parseInt(String.valueOf(table3.getValueAt(0, 1))));
                            pst.setString(9, "");
                            pst.setString(10, "");
                            pst.setString(11, "");
                        }
                    }
                    
                    pst.setString(12, status.getSelectedItem().toString());
                    pst.setString(13, des.getText().trim());
                    pst.execute();
                    JOptionPane.showMessageDialog(null, "Successfully Added");
                    reset();
                }
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error");
                System.out.println(ex);
            }
        
    }
        
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed

        if(placeno.getText().trim().equals("")){
            txt.setText("Empty");
        }
        else{
        try {
                pst = con.prepareStatement("select * from place_details where no = ?");
                pst.setString(1, placeno.getText());
                rs = pst.executeQuery();
                if(rs.next()){
                    pst = con.prepareStatement("delete from place_details where no = ?");
                    pst.setString(1, placeno.getText().trim());
                    pst.execute();
                    JOptionPane.showMessageDialog(null, "Successfully Deleted");
                    reset();
                }
                else{
                    JOptionPane.showMessageDialog(null, "There is no record under this Place No");
                }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error");
            System.out.println(ex);
        }
        }
        
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
      
        try {
            
            pst = con.prepareStatement("select no from place_details");
            rs = pst.executeQuery();
            String last = "0";
            
            while(rs.next()){
            last = rs.getString("no");
            }
            
            Integer newnumber = Integer.parseInt(last)+1;
            String newno = String.valueOf(newnumber);
            reset();
            placeno.setText(newno);
            
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

// TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

        if(placeno.getText().trim().equals("") || lno.getText().trim().equals("") || block.getText().trim().equals("") || placename.getText().trim().equals("") || 
       cost.getText().trim().equals("") || floor.getText().trim().equals("") || capacity.getText().trim().equals("") || table3.getRowCount()==0){
    
        if(placeno.getText().trim().equals("")){
            txt.setText("Empty");
        }
        
        if(lno.getText().trim().equals("")){
            ltxt.setText("Empty");
        }
        
        if(block.getText().trim().equals("")){
            txt1.setText("Empty");
        }
        
        if(placename.getText().trim().equals("")){
            txt2.setText("Empty");
        }
        
        if(cost.getText().trim().equals("")){
            txt3.setText("Empty");
        }
        
        if(floor.getText().trim().equals("")){
            txt4.setText("Empty");
        }
        
        if(capacity.getText().trim().equals("")){
            txt5.setText("Empty");
        }
        
        if(table3.getRowCount()==0){
            txt7.setText("Select Facilities");
        }
    
    }
    
    else if(txt3.getText().trim().equals("") || txt5.getText().trim().equals("")){
    
        try {
                pst = con.prepareStatement("select * from place_details where no = ?");
                pst.setString(1, placeno.getText());
                rs = pst.executeQuery();
                
                if(rs.next()){
                
                    pst = con.prepareStatement("update place_details set lno = ?, block = ?, name = ?, cost = ?, floor = ?, capacity = ?, f1 = ?, f2 = ?, f3 = ?, f4 = ?, status = ?, des = ? where no = ?");
                    pst.setString(13, placeno.getText().trim());
                    pst.setString(1, lno.getText().trim());
                    pst.setString(2, block.getText().trim());
                    pst.setString(3, placename.getText().trim());
                    pst.setInt(4, Integer.parseInt(cost.getText().trim()));
                    pst.setString(5, floor.getText().trim());
                    pst.setInt(6, Integer.parseInt(capacity.getText().trim()));                    
                    
                    switch(table3.getRowCount()){
                    
                        case 4 -> {
                            pst.setInt(7, Integer.valueOf(String.valueOf(table3.getValueAt(0, 1))));
                            pst.setInt(8, Integer.valueOf(String.valueOf(table3.getValueAt(1, 1))));
                            pst.setInt(9, Integer.valueOf(String.valueOf(table3.getValueAt(2, 1))));
                            pst.setInt(10, Integer.valueOf(String.valueOf(table3.getValueAt(3, 1))));
                        }
                        case 3 -> {
                            pst.setInt(7, Integer.valueOf(String.valueOf(table3.getValueAt(0, 1))));
                            pst.setInt(8, Integer.valueOf(String.valueOf(table3.getValueAt(1, 1))));
                            pst.setInt(9, Integer.valueOf(String.valueOf(table3.getValueAt(2, 1))));
                            pst.setString(10, "");
                        }
                        case 2 -> {
                            pst.setInt(7, Integer.valueOf(String.valueOf(table3.getValueAt(0, 1))));
                            pst.setInt(8, Integer.valueOf(String.valueOf(table3.getValueAt(1, 1))));
                            pst.setString(9, "");
                            pst.setString(10, "");
                        }
                        case 1 -> {
                            pst.setInt(7, Integer.valueOf(String.valueOf(table3.getValueAt(0, 1))));
                            pst.setString(8, "");
                            pst.setString(9, "");
                            pst.setString(10, "");
                        }
                    }
                    
                    pst.setString(11, status.getSelectedItem().toString());
                    pst.setString(12, des.getText().trim());
                    pst.execute();
                    JOptionPane.showMessageDialog(null, "Successfully Updated");
                    reset();
                }
                else{
                    JOptionPane.showMessageDialog(null, "There is no record under this Place No");
                }
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error");
                System.out.println(ex);
            }
        
    }
        
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void existingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_existingActionPerformed

        Places x = new Places("place_details");
        x.setVisible(true);
        this.dispose();
        
        // TODO add your handling code here:
    }//GEN-LAST:event_existingActionPerformed

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
            java.util.logging.Logger.getLogger(Place_Details.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Place_Details.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Place_Details.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Place_Details.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Place_Details().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField address;
    private javax.swing.JTextField block;
    private javax.swing.JButton calculate;
    private javax.swing.JTextField capacity;
    private javax.swing.JTextField cost;
    private javax.swing.JTextArea des;
    private javax.swing.JButton existing;
    private javax.swing.JComboBox<String> fcat;
    private javax.swing.JTextField floor;
    private javax.swing.JComboBox<String> fsearchby;
    private javax.swing.JTextField fsearchfor;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
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
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextField lname;
    private javax.swing.JTextField lno;
    private javax.swing.JComboBox<String> lsearchby;
    private javax.swing.JTextField lsearchfor;
    private javax.swing.JLabel ltxt;
    private javax.swing.JPanel place_div;
    private javax.swing.JTextField placename;
    private javax.swing.JTextField placeno;
    private javax.swing.JButton select;
    private javax.swing.JComboBox<String> status;
    private javax.swing.JTable table;
    private javax.swing.JTable table2;
    private javax.swing.JTable table3;
    private javax.swing.JTextField totalcost;
    private javax.swing.JLabel txt;
    private javax.swing.JLabel txt1;
    private javax.swing.JLabel txt2;
    private javax.swing.JLabel txt3;
    private javax.swing.JLabel txt4;
    private javax.swing.JLabel txt5;
    private javax.swing.JLabel txt6;
    private javax.swing.JLabel txt7;
    // End of variables declaration//GEN-END:variables
}
