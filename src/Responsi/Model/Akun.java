/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Responsi.Model;
import Responsi.Helper.AkunHelper;
import Responsi.Helper.ConnectionDB;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author PC PRAKTIKUM
 */
public class Akun {
    private String username;
    private String password;
    private String role;

    public Akun(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String GetAkunByUsername() {
        AkunHelper ah = new AkunHelper(new ConnectionDB());
        try{
            ResultSet rs = ah.getAkunByUsername(username, password);
            if(rs!=null){
                username = rs.getString("username");
                password = rs.getString("password");
                this.role = rs.getString("role");
                return role;
            }
            else{
                return "";
            }
        }
        catch(SQLException ex){
            Logger.getLogger(Akun.class.getName()).log(Level.SEVERE, ex.getMessage());
        }
        return null;
    }
}
