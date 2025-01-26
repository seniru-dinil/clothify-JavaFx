package edu.icet.clothify.controller.admin;

import javafx.event.ActionEvent;
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
    void btnForgotPasswordOnAction(MouseEvent event) {
        System.out.println("forgot password");
    }

    @FXML
    void btnSigninOnAction(ActionEvent event) throws IOException {
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

}
