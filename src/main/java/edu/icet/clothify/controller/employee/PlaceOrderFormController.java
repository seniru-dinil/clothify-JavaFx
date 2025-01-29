package edu.icet.clothify.controller.employee;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import edu.icet.clothify.component.ProductCardFactory;
import edu.icet.clothify.component.ShoppingCartService;
import edu.icet.clothify.dto.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PlaceOrderFormController implements Initializable {

    public VBox cartContainer;
    @FXML
    private JFXButton btnAddToCart;

    @FXML
    private JFXButton btnDeleteItemFromCart;

    @FXML
    private StackPane cmbCategory;

    @FXML
    private StackPane cmbCategory1;

    @FXML
    private JFXComboBox<?> cmbSelectCategory;

    @FXML
    private JFXComboBox<?> cmbSelectCustomer;

    @FXML
    private VBox itemCard;

    @FXML
    private VBox orderItemContainer;

    @FXML
    private Spinner<?> spinnerQty;

    @FXML
    private Label txtDescription;

    @FXML
    private Label txtName;

    @FXML
    private Label txtPrice;

    @FXML
    private TextField txtSearchProduct;

    @FXML
    private Label txtStock;

    @FXML
    private HBox txtTesting;

    @FXML
    private Label txtTotal;

    @FXML
    private VBox vboxContainer;

    @FXML
    void btnCompleOrderOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ShoppingCartService.getInstance().initializeCartContainer(cartContainer);
        loadProductCard();
    }

    public void loadProductCard() {
        List<Product> products = getProducts();
        products.forEach(product -> {
            VBox productCard = ProductCardFactory.getInstance().createProductCard(product);
            txtTesting.getChildren().add(productCard);
        });
    }

    public List<Product> getProducts() {
        List<Product> productList = new ArrayList<>();

        return productList;
    }
}
