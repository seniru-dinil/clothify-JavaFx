package edu.icet.clothify.controller.forms;

import edu.icet.clothify.dto.Customer;
import edu.icet.clothify.service.ServiceFactory;
import edu.icet.clothify.service.custom.CustomerService;
import edu.icet.clothify.util.ServiceType;
import edu.icet.clothify.util.CustomerUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class AddCustomerFormController {

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

    public  void closeWindow(ActionEvent event){
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.close();
    }



    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException {
        CustomerService service = ServiceFactory.getInstance().getService(ServiceType.CUSTOMER);
        Customer customer = new Customer(
                0,
                txtFirstName.getText(),
                txtLastName.getText(),
                txtEmail.getText(),
                txtMobile.getText(),
                txtAddress.getText()
        );
        service.addCustomer(customer);
        CustomerUtil.getInstance().reloadContainer();
        closeWindow(event);
    }

}
