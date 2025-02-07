package edu.icet.clothify.controller.admin;

import edu.icet.clothify.util.DashboardUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class AdminHomepageFormController implements Initializable {

    @FXML
    private VBox container;

    @FXML
    void btnCustomerOnAction(ActionEvent event) throws IOException {
        loadWindow("manageCustomerForm.fxml");
    }

    @FXML
    void btnEmployeeOnAction(ActionEvent event) throws IOException {
        loadWindow("manageEmployeeForm.fxml");
    }

    @FXML
    void btnHomeOnAction(ActionEvent event) throws IOException {
        loadWindow("adminDashboardForm.fxml");
    }

    @FXML
    void btnProductsOnAction(ActionEvent event) throws IOException {
        loadWindow("manageProductForm.fxml");
    }

    @FXML
    void btnReportsOnAction(ActionEvent event) {

    }

    @FXML
    void btnSettingsOnAction(ActionEvent event) throws IOException {
        loadWindow("adminSettingsForm.fxml");
    }

    @FXML
    void btnSupplierOnAction(ActionEvent event) throws IOException {
        loadWindow("manageSupplierForm.fxml");
    }

    public void loadDashBoard() throws IOException {
        loadWindow("adminDashboardForm.fxml");
    }

    public void loadWindow(String path) throws IOException {
        Object load = FXMLLoader.load(getClass().getResource("/view/admin/" + path));
        container.getChildren().clear();
        container.getChildren().addAll((Node) load);
    }

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDashBoard();
    }

    public void btnSignoutOnAction(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
        Stage window = (Stage) source.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/main/mainLoginForm.fxml"))));
        window.show();
    }
}
