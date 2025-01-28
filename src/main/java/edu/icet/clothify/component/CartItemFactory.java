package edu.icet.clothify.component;

import com.jfoenix.controls.JFXButton;
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
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class CartItemFactory {

    private static CartItemFactory instance;

    public static CartItemFactory getInstance() {
        return instance == null ? instance = new CartItemFactory() : instance;
    }

    public HBox createCartItem(Product product) {
        HBox cartItem = new HBox();
        cartItem.setAlignment(Pos.CENTER);
        cartItem.setPrefHeight(34.0);
        cartItem.setPrefWidth(378.0);
        cartItem.setSpacing(10);

        // Product Name Label
        Label nameLabel = new Label(product.getProductName());
        nameLabel.setPrefSize(174, 20);
        nameLabel.setTextFill(javafx.scene.paint.Color.valueOf("#d1d5db"));
        nameLabel.setFont(new Font(14));

        // Quantity Spinner
        Spinner<Integer> spinner = new Spinner<>();
        spinner.setPrefSize(48, 26);

        // Get unit price
        double unitPrice = product.getProductPrice();

        // Configure spinner with validation
        SpinnerValueFactory.IntegerSpinnerValueFactory valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(
                        1,
                        Math.max(product.getProductStock(), 1),
                        1
                );
        spinner.setValueFactory(valueFactory);

        // Price Label (initialize with quantity 1)
        Label priceLabel = new Label(String.format("%.2f", unitPrice * 1));
        priceLabel.setAlignment(Pos.CENTER_RIGHT);
        priceLabel.setPrefSize(90, 20);
        priceLabel.setTextFill(javafx.scene.paint.Color.valueOf("#d1d5db"));
        priceLabel.setFont(new Font(14));
        priceLabel.setPadding(new Insets(0, 0, 0, 7));

        // Add spinner listener for price updates
        spinner.valueProperty().addListener((obs, oldValue, newValue) -> {
            double totalPrice = unitPrice * newValue;
            priceLabel.setText(String.format("%.2f", totalPrice));

            // Prevent going below 1
            if(newValue < 1) {
                spinner.getValueFactory().setValue(1);
            }
        });

        // Delete Button
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
            System.err.println("Error loading delete icon: " + e.getMessage());
        }

        // Delete button action
        deleteButton.setOnAction(event -> {
            System.out.println("Deleted product details:\n" + product);
            HBox parent = (HBox) deleteButton.getParent().getParent();
            VBox parent1 = (VBox) parent.getParent();
            parent1.getChildren().remove(parent);
        });

        deletePane.getChildren().add(deleteButton);

        // Add all components to HBox
        cartItem.getChildren().addAll(
                nameLabel,
                spinner,
                priceLabel,
                deletePane
        );

        return cartItem;
    }
}