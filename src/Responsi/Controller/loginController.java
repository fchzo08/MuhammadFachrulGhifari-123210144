/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Responsi.Controller;


import Responsi.Helper.ConnectionDB;
import Responsi.Model.Akun;
import Responsi.View.AdminPageView;
import Responsi.View.LoginPageView;
import Responsi.View.RoomListView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC PRAKTIKUM
 */
public class loginController implements ActionListener{
    LoginPageView view;
    RoomListController roomListController;
    AdminPageController apc;
    private static final java.util.logging.Logger LOG = java.util.logging.Logger.getLogger(ConnectionDB.class.getName());

    public loginController(LoginPageView view) {
        this.view = view;
    }
     
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Akun akun = new Akun(view.getjTextField1(),view.getjPasswordField1());
        if(akun.GetAkunByUsername().equalsIgnoreCase("ADMIN")){
            view.setVisible(false);
            apc = AdminPageController.getInstance();
            LOG.info("Berpindah ke halaman admin");
            LOG.info("Login Berhasil");
            apc.showPage();
        }  else if(akun.GetAkunByUsername().equalsIgnoreCase("RENTER")){
            view.setVisible(false);
            roomListController = RoomListController.getInstance();
            LOG.info("Berpindah kehalaman list rooms");
            LOG.info("Login Berhasil");
            roomListController.showPage();
        }else{
            view.showMessage("Login Gagal Username/Password Salah");
        }
    }
}
