package edu.icet.clothify.controller.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LogAsFormController {

    @FXML
    void btnLogAsAdminOnAction(ActionEvent event) throws IOException {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Object load = FXMLLoader.load(getClass().getResource("/view/admin/adminLoginForm.fxml"));
        currentStage.setScene(new Scene((Parent) load));
    }

    @FXML
    void btnLogAsEmployeeOnAction(ActionEvent event) throws IOException {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Object load = FXMLLoader.load(getClass().getResource("/view/employee/employeeLoginForm.fxml"));
        currentStage.setScene(new Scene((Parent) load));
    }

}
