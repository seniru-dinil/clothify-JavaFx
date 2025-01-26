package edu.icet.clothify.controller.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class AdminHomepageFormController {

    @FXML
    private VBox container;

    @FXML
    void btnCustomerOnAction(ActionEvent event) throws IOException {
        Object load = FXMLLoader.load(getClass().getResource("/view/admin/adminManageCustomerForm.fxml"));
        container.getChildren().clear();
        container.getChildren().addAll((Node) load);
    }

    @FXML
    void btnEmployeeOnAction(ActionEvent event) throws IOException {
        Object load = FXMLLoader.load(getClass().getResource("/view/admin/adminManageEmployeeForm.fxml"));
        container.getChildren().clear();
        container.getChildren().addAll((Node) load);
    }

    @FXML
    void btnHomeOnAction(ActionEvent event) throws IOException {
        Object load = FXMLLoader.load(getClass().getResource("/view/admin/adminDashboardForm.fxml"));
        container.getChildren().clear();
        container.getChildren().addAll((Node) load);
    }

    @FXML
    void btnProductsOnAction(ActionEvent event) throws IOException {
        Object load = FXMLLoader.load(getClass().getResource("/view/admin/adminManageProductForm.fxml"));
        container.getChildren().clear();
        container.getChildren().addAll((Node) load);
    }

    @FXML
    void btnReportsOnAction(ActionEvent event) {

    }

    @FXML
    void btnSettingsOnAction(ActionEvent event) throws IOException {
        Node load = (Node) FXMLLoader.load(getClass().getResource("/view/admin/adminSettingsForm.fxml"));
        container.getChildren().clear();
        container.getChildren().add(load);
    }

    @FXML
    void btnSupplierOnAction(ActionEvent event) throws IOException {
        Object load = FXMLLoader.load(getClass().getResource("/view/admin/adminManageSupplierForm.fxml"));
        container.getChildren().clear();
        container.getChildren().addAll((Node) load);
    }

}
