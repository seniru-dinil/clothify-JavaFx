package edu.icet.clothify.controller.model;


import edu.icet.clothify.component.tableCard.CustomerTableCard;
import edu.icet.clothify.dto.Customer;
import edu.icet.clothify.entity.CustomerEntity;
import edu.icet.clothify.service.ServiceFactory;
import edu.icet.clothify.service.custom.CustomerService;
import edu.icet.clothify.util.CustomerUtil;
import edu.icet.clothify.util.ServiceType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CustomerCRUDFormController implements Initializable {

    @FXML
    private VBox customerContainer;

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadCustomers();
        CustomerUtil.getInstance().initializeContainer(customerContainer);
    }

    private void getCustomersFromCustomerFactory() throws SQLException {
        CustomerService customerService =  ServiceFactory.getInstance().getService(ServiceType.CUSTOMER);
        List<CustomerEntity> allCustomer = customerService.getAllCustomer();
        List<Customer> customers = new ArrayList<>();
        allCustomer.forEach(c-> customers.add(new ModelMapper().map(c,Customer.class)));
        populateCustomerCards(customers);
    }

    private void populateCustomerCards(List<Customer> customers) {
        customerContainer.getChildren().clear();
        for (Customer customer : customers) {
            AnchorPane customerPane = CustomerTableCard.getInstance().createCustomerPane(customer);
            customerContainer.getChildren().add(customerPane);
        }
    }

    public void loadCustomers() throws SQLException {
        getCustomersFromCustomerFactory();
    }

    public void btnAddNewCustomerOnAction(ActionEvent actionEvent) throws IOException {
        Object load = FXMLLoader.load(getClass().getResource("/view/add.forms/addCustomerForm.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene((Parent) load));
        stage.show();
    }
}
