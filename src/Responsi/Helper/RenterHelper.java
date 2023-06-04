/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Responsi.Helper;

import Responsi.Model.Renter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fachr
 */
public class RenterHelper {
    ConnectionDB conn;
    Renter r;
    private String id;
    public RenterHelper(ConnectionDB db) {
        this.conn = db;
    }
    public void inputRenter(Renter r) throws SQLException{
        this.r = r;
        String query="INSERT INTO `renter`(`name`, `id`, `contact`, `duration`, `bill`, `room`) VALUES ('"+r.getName()+"','"+r.getId()+"','"+r.getContact()+"','"+r.getDuration()+"','"+(r.getBill()*r.getDuration())+"','"+r.getRoom()+"')";
        conn.statement.executeUpdate(query);
    } 
    public List<Renter> getAllRent(){
        String query="SELECT * FROM RENTER";
        List<Renter> rent = new ArrayList<>();
        try {
            ResultSet rs = this.conn.statement.executeQuery(query);
            while (rs.next()) {                
                Renter r = new Renter();
                r.setName(rs.getString("name"));
                r.setId(rs.getString("id"));
                r.setContact(rs.getString("contact"));
                r.setDuration(rs.getInt("duration"));
                r.setBill(rs.getInt("bill"));
                r.setStatus(rs.getString("status"));
                r.setRoom(rs.getString("room"));
                rent.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RenterHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rent;
    }
    public void closeConnection() throws SQLException{
        conn.conn.close();
    }

    public void SetPaid(String id) throws SQLException {
        this.id = id;
        String query="UPDATE `renter` SET `status`='Paid' WHERE id='"+id+"'";
        conn.statement.executeUpdate(query);
    }

    public void hapusByID(String id) throws SQLException {
        this.id = id;
        String query="DELETE FROM `renter` WHERE id='"+id+"'";
        conn.statement.executeUpdate(query);
    }

    public void updateRenter(Renter r) throws SQLException {
        this.r = r;
        System.out.println("ID = "+r.getId());
        
        String query="UPDATE `renter` SET `name`='"+r.getName()+"',`contact`='"+r.getContact()+"' WHERE id='"+r.getId()+"'";
        conn.statement.executeUpdate(query);
    }
}
