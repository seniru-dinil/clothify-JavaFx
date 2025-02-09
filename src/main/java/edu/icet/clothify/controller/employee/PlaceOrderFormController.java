package edu.icet.clothify.controller.employee;

import com.jfoenix.controls.JFXComboBox;
import edu.icet.clothify.component.cart.ShoppingCartService;
import edu.icet.clothify.component.placeorder.ProductCardFactory;
import edu.icet.clothify.dto.*;
import edu.icet.clothify.service.ServiceFactory;
import edu.icet.clothify.service.custom.CustomerService;
import edu.icet.clothify.service.custom.OrderDetailService;
import edu.icet.clothify.service.custom.OrderService;
import edu.icet.clothify.service.custom.ProductService;
import edu.icet.clothify.util.enums.ServiceType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

public class PlaceOrderFormController implements Initializable {

    public VBox cartContainer;
    public ScrollPane scrollPane;
    public TextField txtSearchProduct;
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
//            AlertHelper.showAlert(Alert.AlertType.ERROR, "Customer Error", "Select customer before place an order");
        } else {
            if (placeOrder()) {
                placeOrderDetails();
                ShoppingCartService.getInstance().clearCart();
//                AlertHelper.showSuccessAlert("Add","Order");
            }
        }
    }

    private boolean placeOrder() {
        OrderService service = ServiceFactory.getInstance().getService(ServiceType.ORDER);
        return service.addOrder(new Order(0, Integer.parseInt(cmbSelectCustomer.getSelectionModel().getSelectedItem().split(" ")[0]), ShoppingCartService.getInstance().getTotalPrice(), LocalDateTime.now()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ShoppingCartService.getInstance().initializeFXModels(cartContainer, txtTotal);
        setValuesToCombo();
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        loadProductCard(getProducts());
    }

    public void loadProductCard(List<Product> products) {
        vboxContainer.getChildren().clear();
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
        newHBox.setSpacing(10);
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
        List<Customer> allCustomer = customerService.getAllCustomer();
        ObservableList<String> customerList = FXCollections.observableArrayList();
        allCustomer.forEach(customerEntity -> customerList.add(customerEntity.getCustomerID() + " " + customerEntity.getFirstName()));
        cmbSelectCustomer.setItems(customerList);
    }

    public Customer getCustomer(){
        String s = cmbSelectCustomer.getSelectionModel().getSelectedItem().split(" ")[1];
        CustomerService customerService = ServiceFactory.getInstance().getService(ServiceType.CUSTOMER);
        return customerService.getCustomer(s);
    }

    private void placeOrderDetails() {
        Customer customer = getCustomer();
        List<CartHelper> cartHelper = ShoppingCartService.getInstance().getCartHelper();
        OrderDetailService orderDetailService = ServiceFactory.getInstance().getService(ServiceType.ORDER_DETAIL);
        ProductService productService = ServiceFactory.getInstance().getService(ServiceType.PRODUCT);
        OrderService service = ServiceFactory.getInstance().getService(ServiceType.ORDER);
        Order lastOrder = service.getLastOrder();
//        InvoiceGenerator.generateInvoice("clothify_invoice.pdf",map,lastOrder,cartHelper);
        for (CartHelper i : cartHelper) {
            Product product = i.getProduct();
            productService.updateProductByQuantity(product.getProductStock() - i.getQuantity(), product.getProductID());
            orderDetailService.save(new OrderDetail(0, lastOrder.getOrderId(), i.getProduct().getProductID(), i.getQuantity(), i.getProduct().getProductPrice() * i.getQuantity()));
        }

    }

    public void searchBarOnAction(KeyEvent keyEvent) {
        ProductService productService = ServiceFactory.getInstance().getService(ServiceType.PRODUCT);
        List<Product> productsByName = productService.getProductsByName(txtSearchProduct.getText());
        loadProductCard(productsByName);
    }
}
