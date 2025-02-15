package edu.icet.clothify.component.placeorder;

import com.jfoenix.controls.JFXButton;
import edu.icet.clothify.component.cart.ShoppingCartService;
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

        VBox itemCard = new VBox();
        itemCard.setAlignment(Pos.TOP_CENTER);
        itemCard.setPrefHeight(314);
        itemCard.setPrefWidth(275);
        itemCard.setSpacing(9);
        itemCard.setStyle("-fx-background-color: #1F2937; -fx-background-radius: 10px;");
        itemCard.setPadding(new Insets(15));


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


        VBox textContainer = new VBox();
        textContainer.setAlignment(Pos.CENTER_LEFT);
        textContainer.setPrefWidth(254);
        textContainer.setPadding(new Insets(10, 0, 0, 0));


        Label nameLabel = new Label(product.getProductName());
        nameLabel.setStyle("-fx-text-fill: WHITE; -fx-font-weight: BOLD; -fx-font-size: 14;");
        nameLabel.setPrefWidth(292);


        Label descLabel = new Label(product.getProductDescription());
        descLabel.setStyle("-fx-text-fill: #9ca3af;");
        descLabel.setPrefSize(284, 18);

        textContainer.getChildren().addAll(nameLabel, descLabel);


        HBox priceStockContainer = new HBox();
        priceStockContainer.setAlignment(Pos.CENTER_LEFT);
        priceStockContainer.setPrefSize(214, 34);


        Label priceLabel = new Label(String.format("LKR %.2f", product.getProductPrice()));
        priceLabel.setStyle("-fx-text-fill: WHITE; -fx-font-weight: BOLD; -fx-font-size: 15;");
        priceLabel.setPrefSize(189, 26);


        Label stockTextLabel = new Label("Stock:");
        stockTextLabel.setStyle("-fx-text-fill: #9ca3af;");
        stockTextLabel.setAlignment(Pos.CENTER_RIGHT);
        stockTextLabel.setPrefSize(76, 18);


        Label stockValueLabel = new Label(String.valueOf(product.getProductStock()));
        stockValueLabel.setStyle("-fx-text-fill: #9ca3af;");
        stockValueLabel.setAlignment(Pos.CENTER_RIGHT);
        stockValueLabel.setPrefSize(69, 18);

        priceStockContainer.getChildren().addAll(priceLabel, stockTextLabel, stockValueLabel);


        JFXButton addToCartButton = new JFXButton("Add to cart");
        addToCartButton.setStyle("-fx-background-color: #2563EB; -fx-text-fill: WHITE; -fx-background-radius:5px");
        addToCartButton.setPrefSize(284, 29);
        addToCartButton.setPadding(new Insets(7,0,7,0));
        if (product.getProductStock()<=0)addToCartButton.setDisable(true);
        addToCartButton.setOnAction(event -> {
            ShoppingCartService.getInstance().addToCartContainer(product);
        });



        itemCard.getChildren().addAll(
                imageView,
                textContainer,
                priceStockContainer,
                addToCartButton
        );

        return itemCard;
    }
}