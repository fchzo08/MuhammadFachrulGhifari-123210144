/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Responsi.Model;

import Responsi.Helper.ConnectionDB;
import Responsi.Helper.RenterHelper;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author fachr
*/
public class Renter {
    private String name;
    private String id;
    private String contact;
    private int duration;
    private int bill;
    private String status;
    private String room;
    RenterHelper rh;
    public Renter() {
        rh = new RenterHelper(new ConnectionDB());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getBill() {
        return bill;
    }

    public void setBill(int bill) {
        this.bill = bill;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
    public List<Renter> getAllRents(){
       List<Renter> rent = rh.getAllRent();
        return rent;
    }
    public void closeConnection() throws SQLException{
        rh.closeConnection();
    }
}
