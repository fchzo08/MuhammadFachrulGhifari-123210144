/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Responsi.Controller;

import Responsi.Helper.ConnectionDB;
import Responsi.Model.Renter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import Responsi.Model.Rooms;
//import Responsi.View.RenterDataView;
import Responsi.View.RoomListView;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fachr
 */
public class RoomListController implements ActionListener{
    private static RoomListController instance;
    Rooms room;
    private RoomListView view;
    private RenterDataController rdc;
    private Renter rent;
    private static final java.util.logging.Logger LOG = java.util.logging.Logger.getLogger(ConnectionDB.class.getName());
    public RoomListController() {
        room = new Rooms();
        view = new RoomListView();
        loadAllRoom();
        view.getjButton1().addActionListener(this);
    }
    public static RoomListController getInstance(){
        if(instance==null){
            instance = new RoomListController();
        }
        return instance;
    }
public void loadAllRoom() {
    List<Rooms> rooms = room.getAllRooms();
    DefaultTableModel dtm = new DefaultTableModel();
    dtm.addColumn("Name");
    dtm.addColumn("Size");
    dtm.addColumn("Price");
    dtm.addColumn("Status");
    for (Rooms r : rooms) {
        Object[] row = { r.getName(), r.getSize(), r.getPrice(), r.getStatus() };
        dtm.addRow(row);
    }
    view.setTableModel(dtm);
    LOG.info("Successfully load data to table model");
    
    view.getTable().addMouseListener(new MouseAdapter() {
        
        @Override
        public void mouseClicked(MouseEvent e) {
            rent = new Renter();
            try {
                rent.closeConnection();
                int row = view.getTable().getSelectedRow();
                rent.setRoom((String) view.getTable().getValueAt(row, 0));
                rent.setBill((int) view.getTable().getValueAt(row, 2));
                if("empty".equals((String) view.getTable().getValueAt(row, 3))){
                    RenterDataController rdc = new RenterDataController(rent);
                    view.setVisible(false);
                    rdc = new RenterDataController(rent);
                    rdc.showPage();    
                }
            } catch (SQLException ex) {
                Logger.getLogger(RoomListController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    });
}

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==view.getjButton1()){
            System.exit(0);
        }
    }

    void showPage() {
    view.setVisible(true);
    }
    
}
