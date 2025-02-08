package edu.icet.clothify.component.cart;

import com.jfoenix.controls.JFXButton;
import edu.icet.clothify.dto.CartHelper;
import edu.icet.clothify.dto.Product;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

public class CartItemFactory {

    private static CartItemFactory instance;

    public static CartItemFactory getInstance() {
        return instance == null ? instance = new CartItemFactory() : instance;
    }

    public HBox createCartItem(CartHelper helper) {
        Product product = helper.getProduct();
        HBox cartItem = new HBox();
        cartItem.setAlignment(Pos.CENTER);
        cartItem.setPrefHeight(34.0);
        cartItem.setPrefWidth(378.0);
        cartItem.setSpacing(10);


        Label nameLabel = new Label(product.getProductName());
        nameLabel.setPrefSize(174, 20);
        nameLabel.setTextFill(javafx.scene.paint.Color.valueOf("#d1d5db"));
        nameLabel.setFont(new Font(14));


        Spinner<Integer> spinner = new Spinner<>();
        spinner.setPrefSize(48, 26);
        spinner.setStyle("-fx-background:transparent;");


        double unitPrice = product.getProductPrice();


        SpinnerValueFactory.IntegerSpinnerValueFactory valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(
                        1,
                        Math.max(product.getProductStock(), 1),
                        helper.getQuantity()
                );
        spinner.setValueFactory(valueFactory);


        Label priceLabel = new Label(String.format("%.2f", unitPrice * helper.getQuantity()));
        priceLabel.setAlignment(Pos.CENTER_RIGHT);
        priceLabel.setPrefSize(90, 20);
        priceLabel.setTextFill(javafx.scene.paint.Color.valueOf("#d1d5db"));
        priceLabel.setFont(new Font(14));
        priceLabel.setPadding(new Insets(0, 0, 0, 7));


        spinner.valueProperty().addListener((obs, oldValue, newValue) -> {
            double totalPrice = unitPrice * newValue;
            priceLabel.setText(String.format("%.2f", totalPrice));
            helper.setQuantity(newValue);
            ShoppingCartService.getInstance().setTotal();
            if (helper.getProduct().getProductStock()<=newValue || newValue<1){
                spinner.getValueFactory().setValue(newValue);
                helper.setQuantity(newValue);
            }

        });


        StackPane deletePane = new StackPane();
        deletePane.setPrefSize(83, 150);
        deletePane.setAlignment(Pos.CENTER_RIGHT);
        deletePane.setPadding(new Insets(0, 12, 0, 0));

        JFXButton deleteButton = new JFXButton();
        try {
            ImageView deleteIcon = new ImageView(new Image("/img/delete.png"));
            deleteIcon.setFitHeight(22);
            deleteIcon.setFitWidth(22);
            deleteIcon.setPreserveRatio(true);
            deleteButton.setGraphic(deleteIcon);
        } catch (Exception e) {
            deleteButton.setText("X");
        }


        deleteButton.setOnAction(event -> {
            ShoppingCartService.getInstance().deleteFromCart(helper);
        });

        deletePane.getChildren().add(deleteButton);


        cartItem.getChildren().addAll(
                nameLabel,
                spinner,
                priceLabel,
                deletePane
        );

        return cartItem;
    }
}