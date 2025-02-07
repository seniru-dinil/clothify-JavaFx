package edu.icet.clothify.controller.employee;

import com.jfoenix.controls.JFXComboBox;
import edu.icet.clothify.component.ProductCardFactory;
import edu.icet.clothify.component.ShoppingCartService;
import edu.icet.clothify.dto.*;
import edu.icet.clothify.entity.CustomerEntity;
import edu.icet.clothify.service.ServiceFactory;
import edu.icet.clothify.service.custom.CustomerService;
import edu.icet.clothify.service.custom.OrderDetailService;
import edu.icet.clothify.service.custom.OrderService;
import edu.icet.clothify.service.custom.ProductService;
import edu.icet.clothify.util.AlertHelper;
import edu.icet.clothify.util.InvoiceGenerator;
import edu.icet.clothify.util.ServiceType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.modelmapper.ModelMapper;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

public class PlaceOrderFormController implements Initializable {

    public VBox cartContainer;
    public ScrollPane scrollPane;
    @FXML
    private JFXComboBox<String> cmbSelectCustomer;
    @FXML
    private Label txtTotal;
    @FXML
    private VBox vboxContainer;


    @FXML
    void btnCompleOrderOnAction(ActionEvent event) {
        String selectedItem = cmbSelectCustomer.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, "Customer Error", "Select customer before place an order");
        } else {
            if (placeOrder()){
                placeOrderDetails();
                ShoppingCartService.getInstance().clearCart();
                AlertHelper.showSuccessAlert("Add","Order");
            }
        }
    }

    private boolean placeOrder() {
        OrderService service = ServiceFactory.getInstance().getService(ServiceType.ORDER);
       return service.addOrder(new Order(
                0,
                Integer.parseInt(cmbSelectCustomer.getSelectionModel().getSelectedItem().split(" ")[0]),
                ShoppingCartService.getInstance().getTotalPrice(),
                LocalDateTime.now()
        ));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ShoppingCartService.getInstance().initializeFXModels(cartContainer, txtTotal);
        setValuesToCombo();
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        loadProductCard();
    }

    public void loadProductCard() {
        List<Product> products = getProducts();
        HBox currentHBox = createNewHBox();
        for (Product product : products) {
            VBox productCard = ProductCardFactory.getInstance().createProductCard(product);
            currentHBox.getChildren().add(productCard);
            if (currentHBox.getChildren().size() >= 3) {
                currentHBox = createNewHBox();
            }
        }
    }

    private HBox createNewHBox() {
        HBox newHBox = new HBox();
        newHBox.setSpacing(10);  // Adjust spacing between cards
        newHBox.setAlignment(Pos.CENTER);
        newHBox.setPadding(new Insets(0, 26, 0, 0));
        vboxContainer.getChildren().add(newHBox);
        return newHBox;
    }

    public List<Product> getProducts() {
        ProductService service = ServiceFactory.getInstance().getService(ServiceType.PRODUCT);
        return service.getAllProducts();
    }

    public void setValuesToCombo() {
        CustomerService customerService = ServiceFactory.getInstance().getService(ServiceType.CUSTOMER);
        List<CustomerEntity> allCustomer = customerService.getAllCustomer();
        ObservableList<String> customerList = FXCollections.observableArrayList();
        allCustomer.forEach(customerEntity -> customerList.add(customerEntity.getCustomerID() + " " + customerEntity.getFirstName()));
        cmbSelectCustomer.setItems(customerList);
    }

    private void placeOrderDetails() {
        String s = cmbSelectCustomer.getSelectionModel().getSelectedItem().split(" ")[1];
        CustomerService customerService = ServiceFactory.getInstance().getService(ServiceType.CUSTOMER);
        CustomerEntity customer = customerService.getCustomer(s);
        Customer map =null;
        if(customer!=null){
             map=new ModelMapper().map(customer, Customer.class);
        }
        List<CartHelper> cartHelper = ShoppingCartService.getInstance().getCartHelper();
        OrderDetailService orderDetailService = ServiceFactory.getInstance().getService(ServiceType.ORDER_DETAIL);
        ProductService productService = ServiceFactory.getInstance().getService(ServiceType.PRODUCT);
        OrderService service = ServiceFactory.getInstance().getService(ServiceType.ORDER);
        Order lastOrder = service.getLastOrder();
        InvoiceGenerator.generateInvoice("clothify_invoice.pdf",map,lastOrder,cartHelper);
        for (CartHelper i : cartHelper) {
            Product product = i.getProduct();
            productService.updateProduct(new Product(
                    product.getProductID(),
                    product.getProductName(),
                    product.getProductPrice(),
                    product.getProductStock()-i.getQuantity(),
                    product.getProductImagePath(),
                    product.getProductCategoryID(),
                    product.getProductSupplierID(),
                    product.getProductDescription()
            ));
            orderDetailService.save(new OrderDetail(
                    0,
                    lastOrder.getOrderId(),
                    i.getProduct().getProductID(),
                    i.getQuantity(),
                    i.getProduct().getProductPrice()*i.getQuantity()
                    ));
        }

    }

}
