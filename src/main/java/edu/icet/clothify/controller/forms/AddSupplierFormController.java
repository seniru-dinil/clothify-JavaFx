package edu.icet.clothify.controller.forms;

import com.jfoenix.controls.JFXComboBox;
import edu.icet.clothify.dto.Supplier;
import edu.icet.clothify.service.ServiceFactory;
import edu.icet.clothify.service.custom.SupplierService;
import edu.icet.clothify.util.ServiceType;
import edu.icet.clothify.util.SupplierUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class AddSupplierFormController {

    @FXML
    private JFXComboBox<?> cmbCategory;

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
                1
        ));

        SupplierUtil.getInstance().loadContainer();
        closeWindow(event);
    }


    public void closeWindow(ActionEvent event){
        Node source = (Node) event.getSource();
        Stage window = (Stage) source.getScene().getWindow();
        window.close();
    }

}

