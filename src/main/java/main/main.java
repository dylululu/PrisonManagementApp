/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;



//import vn.viettuts.qlsv.view.DashBoardView;
import prisonmanagement.view.LoginView;
//import vn.viettuts.qlsv.controller.DashBoardController;
import prisonmanagement.controller.LoginController;
import java.io.*;
import java.awt.EventQueue;
public class main {
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {

                LoginView view = new LoginView();
                LoginController controller = new LoginController(view);
                // hiển thị màn hình login
                controller.showLoginView();
//                DashBoardView view = new DashBoardView();
//                DashBoardController controller = new DashBoardController(view);
//                controller.showDashView();
            }
        });
    }
}