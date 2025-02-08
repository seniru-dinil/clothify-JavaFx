package edu.icet.clothify.controller.employee;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeHomepageFormController implements Initializable {

    @FXML
    private VBox container;


    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) throws IOException {
        loadPlaceOrderWindow();
    }

    public void loadPlaceOrderWindow() throws IOException {
        Node load = (Node) FXMLLoader.load(getClass().getResource("/view/employee/employeePlaceOrderForm.fxml"));
        container.getChildren().clear();
        container.getChildren().add(load);
    }

    @FXML
    void btnSettingsOnAction(ActionEvent event) throws IOException {
        Object load = FXMLLoader.load(getClass().getResource("/view/employee/employeeSettingsForm.fxml"));
        container.getChildren().clear();
        container.getChildren().add((Node) load);
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

    @SneakyThrows
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadPlaceOrderWindow();
    }
}
