/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.String;
import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.bson.Document;
import java.lang.String;
import java.net.UnknownHostException;
import java.util.logging.Level;

/**
 *
 * @author GZ
 */
public class OX extends javax.swing.JFrame {

    DBCollection table;  
    DB Data;
   
    public OX() {
        initComponents();
    }
    public static boolean booleancheckID = false;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_id = new javax.swing.JTextPane();
        txt_pass1 = new javax.swing.JPasswordField();
        txt_pass2 = new javax.swing.JPasswordField();
        btnSummit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setViewportView(txt_id);

        txt_pass1.setText("jPasswordField1");

        txt_pass2.setText("jPasswordField2");

        btnSummit.setText("summit");
        btnSummit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSummitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(143, 143, 143)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_pass2)
                    .addComponent(txt_pass1)
                    .addComponent(jScrollPane1)
                    .addComponent(btnSummit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(146, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txt_pass1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txt_pass2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSummit)
                .addContainerGap(81, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSummitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSummitActionPerformed
        
        checkID();
        checkEmpty();
        checkrepass();  
        
        if(booleancheckID){
            
           JOptionPane.showMessageDialog(this, "ID ถูกใช้ไปแล้ว");
            reset();
            
        }else{
            if(checkEmpty()){
                
                JOptionPane.showMessageDialog(this, "กรุณากรอกข้อมูลให้ครบ");
                reset();
             
            }else if(!checkEmpty()){
            if(!checkrepass()){
                
                JOptionPane.showMessageDialog(this, "Password ไม่ตรงกัน");
                reset();
                
                
            }else{
                
                addData();
                reset();
                
                }
             
            }
        }  
    }//GEN-LAST:event_btnSummitActionPerformed
    
    public boolean checkEmpty(){
        if(txt_id.getText().equals("")||txt_pass1.getText().equals("")){
           
            return true;
        }else {
            return false;
        }     
    }
    
    public void reset(){
        txt_id.setText("");
        txt_pass1.setText("");
        txt_pass2.setText("");
    }
    
    public boolean checkrepass(){
        if(txt_pass1.getText().equals(txt_pass2.getText())){
            return true;
        }else {
            return false;
        }
    }
    
    public void checkID(){
        
        MongoClientURI uri  = new MongoClientURI("mongodb://writer:writer1@ds145412.mlab.com:45412/mongoox");
        MongoClient client = new MongoClient(uri);
        MongoDatabase db = client.getDatabase(uri.getDatabase());
        
        MongoCollection<Document> user = db.getCollection("user");
       
        try {
            
            Document myDoc = user.find(eq("ID", txt_id.getText())).first();
            booleancheckID = true;
            
        } catch (Exception e) {
            booleancheckID = false;
        } 
    }
    
    public void addData(){
        MongoClientURI uri  = new MongoClientURI("mongodb://writer:writer1@ds145412.mlab.com:45412/mongoox");
        MongoClient client = new MongoClient(uri);
        MongoDatabase db = client.getDatabase(uri.getDatabase());
        MongoCollection<Document> user = db.getCollection("user");
        BasicDBObject document = new BasicDBObject();
        
        List<Document> seedData = new ArrayList<Document>();
     
        seedData.add(new Document("ID",txt_id.getText())
                .append("Pass", new String(txt_pass1.getPassword()))
                .append("Name", txt_id.getText())
               
        );
        user.insertMany(seedData);
        
        JOptionPane.showMessageDialog(this, "สมัครเรียบร้อย");
        
        client.close(); 
}

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
            java.util.logging.Logger.getLogger(OX.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OX.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OX.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OX.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OX().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSummit;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane txt_id;
    private javax.swing.JPasswordField txt_pass1;
    private javax.swing.JPasswordField txt_pass2;
    // End of variables declaration//GEN-END:variables
}
