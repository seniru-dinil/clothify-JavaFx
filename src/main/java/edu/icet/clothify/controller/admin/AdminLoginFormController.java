package edu.icet.clothify.controller.admin;

import edu.icet.clothify.dto.Admin;
import edu.icet.clothify.service.ServiceFactory;
import edu.icet.clothify.service.custom.AdminService;
import edu.icet.clothify.util.PasswordUtil;
import edu.icet.clothify.util.dtoUtil.AdminUtil;
import edu.icet.clothify.util.enums.ServiceType;
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
        Admin admin = getAdmin();
        if(admin!=null){
            if(PasswordUtil.getInstance().decryptPassword(admin.getPassword()).equals(txtPassword.getText().trim())){
                AdminUtil.getInstance().setAdminInstance(admin);
                setUpStage(event);
            }else{
//                AlertHelper.showPasswordMismatchError();
            }
        }else {
//            AlertHelper.showAlert(Alert.AlertType.ERROR,"Username Error","cannot find a admin with the username provided");
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

    public Admin getAdmin() {
        AdminService adminService = ServiceFactory.getInstance().getService(ServiceType.ADMIN);
        return adminService.getAdmin(txtEmail.getText().trim());
    }

}
