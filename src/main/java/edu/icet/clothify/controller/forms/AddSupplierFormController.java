package edu.icet.clothify.controller.forms;

import com.jfoenix.controls.JFXComboBox;
import edu.icet.clothify.dto.Supplier;
import edu.icet.clothify.service.ServiceFactory;
import edu.icet.clothify.service.custom.SupplierService;
import edu.icet.clothify.util.ServiceType;
import edu.icet.clothify.util.SupplierUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddSupplierFormController implements Initializable {

    @FXML
    private JFXComboBox<String> cmbCategory;

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
        SupplierService supplierService = ServiceFactory.getInstance().getService(ServiceType.SUPPLIER);
        supplierService.saveSupplier(new Supplier(
                0,
                txtName.getText(),
                txtEmail.getText(),
                txtMobile.getText(),
                txtAddress.getText(),
                Integer.parseInt((cmbCategory.getSelectionModel().getSelectedItem().toString().split(" "))[0])
        ));

        SupplierUtil.getInstance().loadContainer();
        closeWindow(event);
    }


    public void closeWindow(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage window = (Stage) source.getScene().getWindow();
        window.close();
    }

    public void setUppliers() {
        ObservableList<String> categoryList = FXCollections.observableArrayList();
        categoryList.add("1 kids");
        categoryList.add("2 gents");
        categoryList.add("3 ladies");
        cmbCategory.setItems(categoryList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setUppliers();
    }
}

