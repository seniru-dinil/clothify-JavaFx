package edu.icet.clothify.util.dtoUtil;

import edu.icet.clothify.component.tableCard.CustomerTableCard;
import edu.icet.clothify.dto.Customer;
import edu.icet.clothify.entity.CustomerEntity;
import edu.icet.clothify.service.ServiceFactory;
import edu.icet.clothify.service.custom.CustomerService;
import edu.icet.clothify.service.custom.impl.CustomerServiceImpl;
import edu.icet.clothify.util.enums.ServiceType;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class CustomerUtil {

    @FXML
    private TextField txtId;

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
    private VBox container;

    private static CustomerUtil instance;
    private CustomerUtil(){}

    public static CustomerUtil getInstance(){
        return instance==null?instance=new CustomerUtil():instance;
    }

    public void initializeContainer(VBox container){
        this.container=container;
    }

    public void reloadContainer()  {
        CustomerService customerService = (CustomerServiceImpl) ServiceFactory.getInstance().getService(ServiceType.CUSTOMER);
        List<CustomerEntity> allCustomer = customerService.getAllCustomer();
        List<Customer> customers = new ArrayList<>();
        allCustomer.forEach(c-> customers.add(new ModelMapper().map(c,Customer.class)));
        populateCustomerCards(customers);
    }
    public void populateCustomerCards(List<Customer> customers) {
        container.getChildren().clear();
        for (Customer customer : customers) {
            AnchorPane customerPane = CustomerTableCard.getInstance().createCustomerPane(customer);
            container.getChildren().add(customerPane);
        }
    }

    public void initialize(TextField... args){
        txtFirstName=args[0];
        txtLastName=args[1];
        txtEmail= args[2];
        txtMobile=args[3];
        txtAddress=args[4];
        txtId = args[5];
    }

    public void setInitialValues(Customer customer){
        txtId.setText(customer.getCustomerID()+"");
        txtAddress.setText(customer.getAddress());
        txtEmail.setText(customer.getEmail());
        txtMobile.setText(customer.getMobileNumber());
        txtFirstName.setText(customer.getFirstName());
        txtLastName.setText(customer.getLastName());
    }
}
