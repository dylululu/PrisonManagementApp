/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prisonmanagement.controller;

/**
 *
 * @author namlong
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import prisonmanagement.func.UserFunc;
import prisonmanagement.entity.User;
import prisonmanagement.view.DashboardView;
import prisonmanagement.view.LoginView;
//import qlsv_swing.qlsv.view.StudentView;

public class LoginController {
    private UserFunc userDao;
    private LoginView loginView;
    private DashboardView DashboardView;
    //private StudentView studentView;
    
    public LoginController(LoginView view) {
        this.loginView = view;
        this.userDao = new UserFunc();
        view.addLoginListener(new LoginListener());
    }
    
    public void showLoginView() {
        loginView.setVisible(true);
    }
    
    /**
     *
     * 
     * @author viettuts.vn
     */
    class LoginListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            User user = loginView.getUser();
            
            if (userDao.checkUser(user)) {
                // nếu đăng nhập thành công, mở màn hình quản lý sinh viên
               DashboardView = new DashboardView(null, null);
               DashboardView.setVisible(true);
               // StudentController studentController = new StudentController(studentView);
               // studentController.showStudentView();
                loginView.setVisible(false);
            } else {
                loginView.showMessage("username hoặc password không đúng.");
            }
        }
    }
}
