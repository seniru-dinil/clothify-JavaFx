package edu.icet.clothify.controller.update;

import edu.icet.clothify.util.SupplierUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UpdateSupplierFormController implements Initializable {

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtMobile;

    @FXML
    private TextField txtName;


    @FXML
    void btnCancelOnAction(ActionEvent event) {
        closeWindow(event);
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException {
        SupplierUtil instance = SupplierUtil.getInstance();
        instance.updateSupplier();
        instance.loadContainer();
        closeWindow(event);
    }

    public void closeWindow(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage window = (Stage) source.getScene().getWindow();
        window.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SupplierUtil instance = SupplierUtil.getInstance();
        instance.initializeTextField(txtName, txtEmail, txtMobile, txtAddress);
    }
}
