package edu.icet.clothify.controller.model;


import edu.icet.clothify.dto.Customer;
import edu.icet.clothify.service.ServiceFactory;
import edu.icet.clothify.service.custom.CustomerService;
import edu.icet.clothify.util.dtoUtil.CustomerUtil;
import edu.icet.clothify.util.enums.ServiceType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ManageCustomerFormController implements Initializable {

    public TextField txtSearchCustomerBar;
    @FXML
    private VBox customerContainer;

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CustomerUtil instance = CustomerUtil.getInstance();
        instance.initializeContainer(customerContainer);
        instance.reloadContainer();
    }

    public void btnAddNewCustomerOnAction(ActionEvent actionEvent) throws IOException {
        Object load = FXMLLoader.load(getClass().getResource("/view/add.forms/addCustomerForm.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene((Parent) load));
        stage.show();
    }

    public void txtSeachCustomer(KeyEvent keyEvent) {
        CustomerService customerService = ServiceFactory.getInstance().getService(ServiceType.CUSTOMER);
        List<Customer> customersByName = customerService.getCustomersByName(txtSearchCustomerBar.getText());
        CustomerUtil.getInstance().populateCustomerCards(customersByName);
    }
}
