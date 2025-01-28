package edu.icet.clothify.controller.model;


import edu.icet.clothify.component.tableCard.CustomerTableCard;
import edu.icet.clothify.dto.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CustomerCRUDFormController implements Initializable {

    @FXML
    private VBox customerContainer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadCustomersFromDatabase();
    }

    private void loadCustomersFromDatabase() {
        List<Customer> customers = new ArrayList<Customer>();
        customers.add(
                new Customer(1, "seniru", "dinil", "seniru@gmail.com", "07672896930", "Galle")
        );

        populateCustomerCards(customers);
    }

    private void populateCustomerCards(List<Customer> customers) {
        customerContainer.getChildren().clear();

        for (Customer customer : customers) {
            AnchorPane customerPane = CustomerTableCard.getInstance().createCustomerPane(customer);
            customerContainer.getChildren().add(customerPane);
        }
    }

    public void btnAddNewCustomerOnAction(ActionEvent actionEvent) throws IOException {
        Object load = FXMLLoader.load(getClass().getResource("/view/add.forms/addCustomerForm.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene((Parent) load));
        stage.show();
    }
}
