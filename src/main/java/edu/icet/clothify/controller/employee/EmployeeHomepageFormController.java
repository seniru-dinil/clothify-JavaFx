package edu.icet.clothify.controller.employee;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class EmployeeHomepageFormController {

    @FXML
    private VBox container;

    @FXML
    void btnHomeOnAction(ActionEvent event) {

    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) throws IOException {
        Node load = (Node) FXMLLoader.load(getClass().getResource("/view/employee/employeePlaceOrderForm.fxml"));
        container.getChildren().clear();
        container.getChildren().add(load);
    }

    @FXML
    void btnSettingsOnAction(ActionEvent event) {

    }

    @FXML
    void btnSignOutOnAction(ActionEvent event) throws IOException {
        closeThisWindow(event);
        loadMainWindow();
    }

    public void loadMainWindow() throws IOException {
        Object load = FXMLLoader.load(getClass().getResource("/view/main/mainLoginForm.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene((Parent) load));
        stage.show();
    }

    public void closeThisWindow(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage window = (Stage) source.getScene().getWindow();
        window.close();
    }

}
