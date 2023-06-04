/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Responsi.Model;

import Responsi.Helper.ConnectionDB;
import java.util.List;
import Responsi.Helper.RoomHelper;
import java.sql.SQLException;
/**
 *
 * @author fachr
 */
public class Rooms {
    private String name;
    private String size;
    private int price;
    private String status;
    RoomHelper rh;
    public Rooms() {
        rh = new RoomHelper(new ConnectionDB());
    }

    public void closeConnection() throws SQLException{
        rh.closeConnection();
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public List<Rooms> getAllRooms(){
       List<Rooms> rooms = rh.getAllRooms();
        return rooms;
    }
}
