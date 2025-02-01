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

public class AdminSignupFormController {

    @FXML
    private TextField txtConfirmPassword;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtPassword;

    @FXML
    void btnCreateAccountOnAction(ActionEvent event) throws IOException {
        AdminService adminService = ServiceFactory.getInstance().getService(ServiceType.ADMIN);
        adminService.addAdmin(new Admin(
                0,
                txtFirstName.getText(),
                txtLastName.getText(),
                txtEmail.getText(),
                PasswordUtil.getInstance().encryptPassword(txtPassword.getText())
        ));
        closeWindow(event);
        loadSigninWindow();
    }

    @FXML
    void btnSigninOnAction(MouseEvent event) throws IOException {
        closeWindow(event);
        loadSigninWindow();
    }

    private void closeWindow(Event event) {
        Node source = (Node) event.getSource();
        Stage window = (Stage) source.getScene().getWindow();
        window.close();
    }

    private void loadSigninWindow() throws IOException {
        Object load = FXMLLoader.load(getClass().getResource("/view/admin/adminLoginForm.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene((Parent) load));
        stage.show();
    }

}
