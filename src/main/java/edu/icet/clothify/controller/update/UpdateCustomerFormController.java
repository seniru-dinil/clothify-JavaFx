package edu.icet.clothify.controller.update;

import edu.icet.clothify.dto.Customer;
import edu.icet.clothify.service.ServiceFactory;
import edu.icet.clothify.service.custom.CustomerService;
import edu.icet.clothify.util.enums.ServiceType;
import edu.icet.clothify.util.dtoUtil.CustomerUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UpdateCustomerFormController implements Initializable {

    public TextField txtId;
    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtMobile;

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        closeWindow(event);
    }

    public void closeWindow(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage window = (Stage) source.getScene().getWindow();
        window.close();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException {
        CustomerService service = ServiceFactory.getInstance().getService(ServiceType.CUSTOMER);
        boolean b = service.updateCustomer(new Customer(
                Integer.parseInt(txtId.getText()),
                txtFirstName.getText(),
                txtLastName.getText(),
                txtEmail.getText(),
                txtMobile.getText(),
                txtAddress.getText()
        ));
        System.out.println(b?" edited":"not edited");
        closeWindow(event);
        CustomerUtil.getInstance().reloadContainer();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CustomerUtil.getInstance().initialize(txtFirstName,txtLastName,txtEmail,txtMobile,txtAddress,txtId);
    }
}
