/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Responsi.Controller;

import Responsi.Helper.ConnectionDB;
import Responsi.Helper.RenterHelper;
import Responsi.Model.Renter;
import Responsi.Helper.RoomHelper;
import Responsi.View.RenterDataView;
import Responsi.View.RoomListView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.AncestorListener;

/**
 *
 * @author fachr
 */
public class RenterDataController implements ActionListener{
    private RenterDataView view;
    private Renter r;
    private RoomListController romListController;
    public RenterDataController(Renter r) {
    this.r = r;
    view = new RenterDataView();
    view.getjButton1().addActionListener(this);
    view.getjButton2().addActionListener(this);
    }
    
    void showPage() {
    view.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==view.getjButton1()){
            r.setName(view.getjTFname());
            r.setId(view.getjTFid());
            r.setContact(view.getjTFcontact());
            r.setDuration(Integer.parseInt(view.getjTFRT()));
            RenterHelper rh = new RenterHelper(new ConnectionDB());
            RoomHelper roh = new RoomHelper(new ConnectionDB());
            try {
                rh.inputRenter(r);
                roh.setNameInStatus(r.getRoom(),r.getName());
            } catch (SQLException ex) {
                Logger.getLogger(RenterDataController.class.getName()).log(Level.SEVERE, null, ex);
            }
            RoomListController rlc = new RoomListController();
            view.setVisible(false);
            rlc.showPage();
        }
        if(e.getSource()==view.getjButton2()){
            System.exit(0);
        }
    }
}
