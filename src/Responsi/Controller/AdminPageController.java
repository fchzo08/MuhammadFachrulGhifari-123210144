/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Responsi.Controller;

import Responsi.Helper.ConnectionDB;
import Responsi.Helper.RenterHelper;
import Responsi.Helper.RoomHelper;
import Responsi.Model.Renter;
import Responsi.View.AdminPageView;
import Responsi.View.RenterDataView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author fachr
 */
public class AdminPageController implements ActionListener{
    private static AdminPageController instance;
    private static final java.util.logging.Logger LOG = java.util.logging.Logger.getLogger(ConnectionDB.class.getName());
    Renter rent;
    private AdminPageView view;
    private RenterDataView rdv;
    public AdminPageController(){
        rent = new Renter();
        view = new AdminPageView();
        view.getjButton1().addActionListener(this);
        loadAllRenter();
        
        
    }
    public static AdminPageController getInstance(){
        if(instance==null){
            instance = new AdminPageController();
        }
        return instance;
    }
    public void loadAllRenter(){
        List<Renter> rents = rent.getAllRents();
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("Name");
        dtm.addColumn("ID");
        dtm.addColumn("Contact");
        dtm.addColumn("Duration");
        dtm.addColumn("Bill");
        dtm.addColumn("Status");
        dtm.addColumn("Room");
        for(Renter r:rents){
            Object[] row = {
            r.getName(), r.getId(), r.getContact(), r.getDuration(),r.getBill(),r.getStatus(),r.getRoom()
            };
            dtm.addRow(row);
        }
        view.setTableModel(dtm);
         view.getTable().addMouseListener(new MouseAdapter() {
        
        @Override
        public void mouseClicked(MouseEvent e) {
            int row = view.getTable().getSelectedRow();
            rent.setName((String) view.getTable().getValueAt(row, 0));
            rent.setId((String) view.getTable().getValueAt(row, 1));
            rent.setContact((String) view.getTable().getValueAt(row, 2));
            rent.setDuration((int) view.getTable().getValueAt(row, 3));
            rent.setStatus((String) view.getTable().getValueAt(row, 5));
            rent.setRoom((String) view.getTable().getValueAt(row, 6));
            RenterHelper rh = new RenterHelper(new ConnectionDB());
            RoomHelper roh = new RoomHelper(new ConnectionDB());
            if("notPaid".equals(rent.getStatus())){
                int jawab = JOptionPane.showOptionDialog(null, 
                    "Mengubah Ke Paid?", 
                    "Konfirmasi", 
                    JOptionPane.YES_NO_OPTION, 
                    JOptionPane.QUESTION_MESSAGE, null, null, null);
                    if(jawab == JOptionPane.YES_OPTION){
                    try {
                        rh.SetPaid(rent.getId());
                        loadAllRenter();
                    } catch (SQLException ex) {
                        Logger.getLogger(AdminPageController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    }
            }else{
                Object[] options = { "Edit", "Hapus" };
                int jawab = JOptionPane.showOptionDialog(null, "Silahkan Konfirmasi?", "Konfirmasi",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                if (jawab == JOptionPane.YES_OPTION) {
                    System.out.println("ID = "+rent.getId()); 
                    EditRenterDataController erdc = new EditRenterDataController(rent);
                    view.setVisible(false);
                    erdc.showPage();
                } else if (jawab == JOptionPane.NO_OPTION) {
                    try {
                        rh.hapusByID(rent.getId());
                        roh.setEmptyRoom(rent.getRoom());
                        loadAllRenter();
                    } catch (SQLException ex) {
                        Logger.getLogger(AdminPageController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }});
    }
                 
    void showPage() {
    view.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==view.getjButton1()){
         System.exit(0);
        }
    }

}       

