package edu.icet.clothify.controller.employee;

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

public class EmployeeLoginFormController {

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPassword;

    @FXML
    void btnForgotPasswordOnAction(MouseEvent event) {
           closeThisWindow(event);
    }

    @FXML
    void btnSigninOnAction(ActionEvent event) throws IOException {
        closeThisWindow(event);
        Object load = FXMLLoader.load(getClass().getResource("/view/employee/employeeHomepageForm.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene((Parent) load));
        stage.show();
    }

    public void closeThisWindow(Event event){
        Node source = (Node) event.getSource();
        Stage window = (Stage) source.getScene().getWindow();
        window.close();
    }

}
