package edu.icet.clothify.controller.employee;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import edu.icet.clothify.component.ProductCardFactory;
import edu.icet.clothify.component.ShoppingCartService;
import edu.icet.clothify.dto.Product;
import edu.icet.clothify.service.ServiceFactory;
import edu.icet.clothify.service.custom.ProductService;
import edu.icet.clothify.util.ServiceType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class PlaceOrderFormController implements Initializable {

    public VBox cartContainer;
    public ScrollPane scrollPane;
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
    private Label txtTotal;

    @FXML
    private VBox vboxContainer;

    

    @FXML
    void btnCompleOrderOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ShoppingCartService.getInstance().initializeCartContainer(cartContainer);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        try {
            loadProductCard();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadProductCard() throws SQLException {
        List<Product> products = getProducts();
        HBox currentHBox = createNewHBox(); // Create first HBox
        for (Product product : products) {
            // Create product card
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
        vboxContainer.getChildren().add(newHBox);
        return newHBox;
    }

    public List<Product> getProducts()  {
        ProductService service = ServiceFactory.getInstance().getService(ServiceType.PRODUCT);
        return service.getAllProducts();
    }
}
