/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Responsi.Controller;

import Responsi.Helper.ConnectionDB;
import Responsi.Helper.RenterHelper;
import Responsi.Model.Renter;
import Responsi.View.RenterDataView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fachr
 */
public class EditRenterDataController implements ActionListener{
    private RenterDataView view;
    private Renter r;
    private RenterHelper rh;
    public EditRenterDataController(Renter r) {
        this.r = r;
        rh = new RenterHelper(new ConnectionDB());
        view = new RenterDataView();
        view.getjButton1().addActionListener(this);
        view.getjButton2().addActionListener(this);
        view.setjTFRT(r.getDuration());
        view.setjTFcontact(r.getContact());
        view.setjTFid(r.getId());
        view.setjTFname(r.getName());
    }
    
    void showPage() {
    view.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==view.getjButton1()){
            try {
                r.setName(view.getjTFname());
                r.setContact(view.getjTFcontact());
                System.out.println("Nama new = "+r.getName());
                System.out.println("ID new = "+r.getId()); 
                rh.updateRenter(r);
                view.setVisible(false);
                AdminPageController apc = new AdminPageController();
                apc.showPage();
            } catch (SQLException ex) {
                Logger.getLogger(EditRenterDataController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(e.getSource()==view.getjButton2()){
            System.exit(0);
        }
    }
    
}
