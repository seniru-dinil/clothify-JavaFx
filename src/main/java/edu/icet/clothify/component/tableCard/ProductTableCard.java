package edu.icet.clothify.component.tableCard;

import com.jfoenix.controls.JFXButton;
import edu.icet.clothify.dto.Product;
import edu.icet.clothify.service.ServiceFactory;
import edu.icet.clothify.service.custom.ProductService;
import edu.icet.clothify.util.dtoUtil.ProductUtil;
import edu.icet.clothify.util.enums.ServiceType;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ProductTableCard {
    private static ProductTableCard instance;

    ProductTableCard() {
    }

    public static ProductTableCard getInstance() {
        return instance == null ? instance = new ProductTableCard() : instance;
    }

    public AnchorPane createProductPane(Product product) {
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefSize(874, 42);
        anchorPane.setStyle("-fx-background-color: #1F2937;");


        Label txtID = createLabel(12.0, 12.0, 58.0, 18.0, String.valueOf(product.getProductID()));


        Label txtName = createLabel(104.0, 12.0, 145.0, 18.0, product.getProductName());


        TextField txtQuantity = createTextField(528.0, 10.0, 62.0, 18.0, String.valueOf(product.getProductStock()));
        txtQuantity.setAlignment(Pos.CENTER);
        txtQuantity.setOnAction(e -> {
            ProductService service = ServiceFactory.getInstance().getService(ServiceType.PRODUCT);
            service.updateProductByQuantity(Integer.parseInt(txtQuantity.getText()), product.getProductID());
        });


        TextField txtPrice = createTextField(408.0, 10.0, 62.0, 18.0, String.format("%.2f", product.getProductPrice()));
        txtPrice.setAlignment(Pos.CENTER_RIGHT);
        txtPrice.setOnAction(e -> {
            ProductService service = ServiceFactory.getInstance().getService(ServiceType.PRODUCT);
            service.updateProductByPrice(Double.parseDouble(txtPrice.getText()), product.getProductID());
        });


        StackPane imagePane = createCategoryImagePane(product.getProductCategoryID());


        Label txtAvailable = createStockLabel(product.getProductStock());


        HBox buttonBox = createButtonHBox(product, txtQuantity, txtPrice);

        anchorPane.getChildren().addAll(txtID, txtName, txtQuantity, txtPrice, imagePane, txtAvailable, buttonBox);
        return anchorPane;
    }

    private Label createLabel(double x, double y, double width, double height, String text) {
        Label label = new Label(text);
        label.setLayoutX(x);
        label.setLayoutY(y);
        label.setPrefSize(width, height);
        label.setTextFill(Color.web("#d1d5db"));
        return label;
    }

    private TextField createTextField(double x, double y, double width, double height, String text) {
        TextField textField = new TextField(text);
        textField.setLayoutX(x);
        textField.setLayoutY(y);
        textField.setPrefSize(width, height);
        textField.setStyle("-fx-background-color:#1F2937; -fx-text-fill: #d1d5db;");
        textField.setEditable(false);
        return textField;
    }

    private StackPane createCategoryImagePane(int categoryId) {
        String imageName;
        switch (categoryId) {
            case 1:
                imageName = "img3.png";
                break;
            case 2:
                imageName = "img1.png";
                break;
            case 3:
                imageName = "img2.png";
                break;
            default:
                imageName = "img1.png"; // Default image
        }

        StackPane stackPane = new StackPane();
        stackPane.setLayoutX(283.0);
        stackPane.setLayoutY(5.0);
        stackPane.setPrefSize(33.0, 32.0);
        stackPane.setStyle("-fx-background-color: #374151;");

        ImageView imageView = new ImageView();
        imageView.setFitWidth(27.0);
        imageView.setFitHeight(22.0);
        imageView.setPreserveRatio(true);

        try {
            Image image = new Image(getClass().getResourceAsStream("/img/" + imageName));
            imageView.setImage(image);
        } catch (Exception e) {
            System.err.println("Error loading category image: " + e.getMessage());
        }

        stackPane.getChildren().add(imageView);
        return stackPane;
    }

    private Label createStockLabel(int stock) {
        Label label = new Label();
        label.setLayoutX(644.0);
        label.setLayoutY(8.0);
        label.setPrefSize(77.0, 26.0);
        label.setAlignment(Pos.CENTER);
        label.setTextFill(Color.web("#d1d5db"));
        label.setFont(Font.font("System", FontWeight.BOLD, 12));

        if (stock <= 0) {
            label.setText("Out of Stock");
            label.setStyle("-fx-background-color: rgba(255, 0, 0, 0.5);");
        } else {
            label.setText("In Stock");
            label.setStyle("-fx-background-color: rgba(0, 255, 0, 0.5);");
        }

        return label;
    }

    private HBox createButtonHBox(Product product, TextField txtQuantity, TextField txtPrice) {
        HBox hbox = new HBox();
        hbox.setLayoutX(792.0);
        hbox.setLayoutY(8.0);
        hbox.setPrefSize(33.0, 26.0);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(5);


        StackPane editButton = createIconButton(
                "/img/edite.png",
                16.0, 16.0,
                24.0, 2.0,
                () -> handleEdite(product, txtQuantity, txtPrice)
        );


        StackPane deleteButton = createIconButton(
                "/img/delete.png",
                18.0, 20.0,
                22.0, 0.0,
                () -> handleDelete(product)
        );

        hbox.getChildren().addAll(editButton, deleteButton);
        return hbox;
    }

    private StackPane createIconButton(String imagePath, double imgWidth, double imgHeight,
                                       double btnWidth, double btnHeight, Runnable action) {
        StackPane pane = new StackPane();

        JFXButton button = new JFXButton();
        button.setPrefSize(btnWidth, btnHeight);
        button.setStyle("-fx-background-color: transparent;");
        button.setOnAction(t -> action.run());
        ImageView icon = new ImageView();
        icon.setFitWidth(imgWidth);
        icon.setFitHeight(imgHeight);
        icon.setPreserveRatio(true);

        try {
            Image image = new Image(getClass().getResourceAsStream(imagePath));
            icon.setImage(image);
        } catch (Exception e) {
            System.err.println("Error loading button image: " + e.getMessage());
        }

        pane.getChildren().addAll(icon, button);
        return pane;
    }

    public void handleDelete(Product product) {
        ProductService productService = ServiceFactory.getInstance().getService(ServiceType.PRODUCT);
        productService.deleteProduct(product.getProductID());
        ProductUtil.getInstance().getAllData();
    }

    public void handleEdite(Product product, TextField txtQuantity, TextField txtPrice) {
        txtQuantity.setEditable(true);
        txtPrice.setEditable(true);
    }
}