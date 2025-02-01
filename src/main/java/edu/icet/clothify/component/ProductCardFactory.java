package edu.icet.clothify.component;

import com.jfoenix.controls.JFXButton;
import edu.icet.clothify.dto.Product;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ProductCardFactory {

    private static ProductCardFactory instance;

    public static ProductCardFactory getInstance(){
        return instance==null?instance=new ProductCardFactory():instance;
    }

    public  VBox createProductCard(Product product) {
        // Main container
        VBox itemCard = new VBox();
        itemCard.setAlignment(Pos.TOP_CENTER);
        itemCard.setPrefHeight(314);
        itemCard.setPrefWidth(275);
        itemCard.setSpacing(9);
        itemCard.setStyle("-fx-background-color: #1F2937;");
        itemCard.setPadding(new Insets(15));

        // Product Image
        ImageView imageView = new ImageView();
        imageView.setFitHeight(161);
        imageView.setFitWidth(168);
        imageView.setPreserveRatio(true);
        imageView.setPickOnBounds(true);
        try {
            Image image = new Image(product.getProductImagePath());
            imageView.setImage(image);
        } catch (Exception e) {

        }

        // Text Information Container
        VBox textContainer = new VBox();
        textContainer.setAlignment(Pos.CENTER_LEFT);
        textContainer.setPrefWidth(254);
        textContainer.setPadding(new Insets(10, 0, 0, 0));

        // Product Name
        Label nameLabel = new Label(product.getProductName());
        nameLabel.setStyle("-fx-text-fill: WHITE; -fx-font-weight: BOLD; -fx-font-size: 14;");
        nameLabel.setPrefWidth(292);

        // Product Description
        Label descLabel = new Label(product.getProductDescription());
        descLabel.setStyle("-fx-text-fill: #9ca3af;");
        descLabel.setPrefSize(284, 18);

        textContainer.getChildren().addAll(nameLabel, descLabel);

        // Price and Stock Container
        HBox priceStockContainer = new HBox();
        priceStockContainer.setAlignment(Pos.CENTER_LEFT);
        priceStockContainer.setPrefSize(214, 34);

        // Price
        Label priceLabel = new Label(String.format("LKR %.2f", product.getProductPrice()));
        priceLabel.setStyle("-fx-text-fill: WHITE; -fx-font-weight: BOLD; -fx-font-size: 15;");
        priceLabel.setPrefSize(189, 26);

        // Stock Label
        Label stockTextLabel = new Label("Stock:");
        stockTextLabel.setStyle("-fx-text-fill: #9ca3af;");
        stockTextLabel.setAlignment(Pos.CENTER_RIGHT);
        stockTextLabel.setPrefSize(76, 18);

        // Stock Value
        Label stockValueLabel = new Label(String.valueOf(product.getProductStock()));
        stockValueLabel.setStyle("-fx-text-fill: #9ca3af;");
        stockValueLabel.setAlignment(Pos.CENTER_RIGHT);
        stockValueLabel.setPrefSize(69, 18);

        priceStockContainer.getChildren().addAll(priceLabel, stockTextLabel, stockValueLabel);

        // Add to Cart Button
        JFXButton addToCartButton = new JFXButton("Add to cart");
        addToCartButton.setStyle("-fx-background-color: #2563EB; -fx-text-fill: WHITE;");
        addToCartButton.setPrefSize(284, 29);
        addToCartButton.setPadding(new Insets(7,0,7,0));
        addToCartButton.setOnAction(event -> {
            ShoppingCartService.getInstance().addToCartContainer(product);
        });

        // Assemble all components
        itemCard.getChildren().addAll(
                imageView,
                textContainer,
                priceStockContainer,
                addToCartButton
        );

        return itemCard;
    }
}