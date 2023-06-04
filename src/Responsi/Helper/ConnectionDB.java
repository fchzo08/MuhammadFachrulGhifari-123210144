/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Responsi.Helper;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.sql.*;

/**
 *
 * @author PC PRAKTIKUM
 */
public class ConnectionDB {
    String dbUrl = "jdbc:mysql://localhost/almaul_db";
    String username = "root";
    String password = "";
    public Connection conn;
    Statement statement;
    
    private static final java.util.logging.Logger LOG = java.util.logging.Logger.getLogger(ConnectionDB.class.getName());
    public ConnectionDB(){
        try{
            conn = DriverManager.getConnection(dbUrl, username, password);
            createStatement();
            LOG.info("Koneksi DB Dibuat");
        }
        catch(SQLException exception){
            System.out.println(exception.getMessage());
        }
    }
    
    public void createStatement(){
        try {
            this.statement = conn.createStatement();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(ConnectionDB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
    
}