package edu.icet.clothify.controller.admin;

import edu.icet.clothify.dto.Admin;
import edu.icet.clothify.service.ServiceFactory;
import edu.icet.clothify.service.custom.AdminService;
import edu.icet.clothify.util.PasswordUtil;
import edu.icet.clothify.util.ServiceType;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminLoginFormController {

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPassword;

    @FXML
    void btnForgotPasswordOnAction(MouseEvent event) throws IOException {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Object load = FXMLLoader.load(getClass().getResource("/view/admin/adminForgotPasswordForm.fxml"));
        currentStage.setScene(new Scene((Parent) load));
    }

    @FXML
    void btnSigninOnAction(ActionEvent event) throws IOException {
        if (getAdmin()){
            setUpStage(event);
        }else{
            System.out.println("wrong");
        }
    }

    private void setUpStage(Event event) throws IOException {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Object load = FXMLLoader.load(getClass().getResource("/view/admin/adminHomepageForm.fxml"));
        currentStage.setScene(new Scene((Parent) load));
    }

    @FXML
    void btnSignoutOnAction(MouseEvent event) throws IOException {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Object load = FXMLLoader.load(getClass().getResource("/view/admin/adminSignupForm.fxml"));
        currentStage.setScene(new Scene((Parent) load));
    }

    public boolean getAdmin(){
        AdminService adminService = ServiceFactory.getInstance().getService(ServiceType.ADMIN);
        Admin admin = adminService.getAdmin(txtEmail.getText().trim());
        String s = PasswordUtil.getInstance().decryptPassword(admin.getPassword());
        return s.equals(txtPassword.getText());
    }

}
