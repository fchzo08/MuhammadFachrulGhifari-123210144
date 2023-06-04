/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Responsi.Helper;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC PRAKTIKUM
 */
public class AkunHelper {
    ConnectionDB conn;

    public AkunHelper(ConnectionDB db) {
        this.conn = db;
    }
    private static final java.util.logging.Logger LOG = java.util.logging.Logger.getLogger(ConnectionDB.class.getName());

    public ResultSet getAkunByUsername(String username,String password){
        
        String query = "SELECT * FROM accounts WHERE USERNAME = \""+username+"\""+"AND PASSWORD = \""+password+"\"";
        try{
        
            ResultSet rs = this.conn.statement.executeQuery(query);
            if(rs.next()){
                LOG.info(rs.getString("role"));
                return rs;
            }
        }
        catch(SQLException ex){
            LOG.severe(ex.getMessage());
        }
        return null;
    }
}
