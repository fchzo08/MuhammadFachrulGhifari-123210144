/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Responsi.Helper;

import Responsi.Model.Rooms;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author fachr
 */
public class RoomHelper {
    ConnectionDB conn;
    private String name;
    private String room;
    public RoomHelper(ConnectionDB db) {
        this.conn = db;
    }
    
//    public int updateStatus(String name){
//        String query ="UPDATE `rooms` SET `status`='[value-4]' WHERE `name`='[value-1]'";
//    }
    
    public List<Rooms> getAllRooms(){
        String query="SELECT * FROM ROOMS";
        List<Rooms> rooms = new ArrayList<>();
        try {
            ResultSet rs = this.conn.statement.executeQuery(query);
            while (rs.next()) {                
                Rooms r = new Rooms();
                r.setName(rs.getString("name"));
                r.setPrice(rs.getInt("price"));
                r.setSize(rs.getString("size"));
                r.setStatus(rs.getString("status"));
                rooms.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoomHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rooms;
    }
    public void closeConnection() throws SQLException{
        conn.conn.close();
    }

    public void setNameInStatus(String room,String name) throws SQLException {
        this.name = name;
        this.room = room;
        String query = "UPDATE `rooms` SET `status`='"+name+"' WHERE name = '"+room+"'";
        conn.statement.executeUpdate(query);
    }

    public void setEmptyRoom(String room) throws SQLException {
        this.room = room;
        String query = "UPDATE `rooms` SET `status`= 'empty' WHERE name = '"+room+"'";
        conn.statement.executeUpdate(query);
    }
}
