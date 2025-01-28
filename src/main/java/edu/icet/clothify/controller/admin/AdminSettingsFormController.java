package edu.icet.clothify.controller.admin;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class AdminSettingsFormController {

    @FXML
    private ImageView adminImg;

    @FXML
    private JFXButton btnBrowseImg;

    @FXML
    private TextField txtConfirmPassword;

    @FXML
    private TextField txtCurrentPassword;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtNewPassword;

    @FXML
    void btnBrowseImgOnAction(ActionEvent event) {

    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {
//        closeThisWindow(event);
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
//        closeThisWindow(event);
    }

    public void closeThisWindow(Event event) {
        Node source = (Node) event.getSource();
        Stage window = (Stage) source.getScene().getWindow();
        window.close();
    }

}
