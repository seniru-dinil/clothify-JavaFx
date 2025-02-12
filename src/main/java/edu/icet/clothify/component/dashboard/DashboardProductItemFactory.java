package edu.icet.clothify.component.dashboard;

import edu.icet.clothify.entity.MostPurchasedProductEntity;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class DashboardProductItemFactory {
    private static DashboardProductItemFactory instance;

    private DashboardProductItemFactory() {
    }

    public static DashboardProductItemFactory getInstance() {
        if (instance == null) {
            instance = new DashboardProductItemFactory();
        }
        return instance;
    }

    public HBox createProductItem(MostPurchasedProductEntity mostPurchasedProductEntity) {
        String productName = mostPurchasedProductEntity.getProduct().getProductName();
        String numberOfSales = String.valueOf(mostPurchasedProductEntity.getTotalQuantity());
        String totalAmount = String.valueOf(mostPurchasedProductEntity.getTotalQuantity()*mostPurchasedProductEntity.getProduct().getProductPrice());
        String imagePath=mostPurchasedProductEntity.getProduct().getProductImagePath();
        HBox hboxProduct = new HBox(15);
        hboxProduct.setPrefSize(543, 43);
        hboxProduct.setAlignment(Pos.CENTER_LEFT);
        hboxProduct.setStyle("-fx-background-color: rgba(55, 65, 81,0.5);");
        hboxProduct.setPadding(new Insets(15));
        hboxProduct.setStyle("-fx-background-color: rgba(55, 65, 81,0.5); -fx-background-radius:10px");


        StackPane imageContainer = new StackPane();
        imageContainer.setPrefSize(70, 42);
        imageContainer.setStyle("-fx-background-color: #4B5563; -fx-background-radius:5px");


        ImageView productImage = new ImageView(new Image(imagePath));
        productImage.setFitHeight(26);
        productImage.setFitWidth(26);
        productImage.setPreserveRatio(true);

        imageContainer.getChildren().add(productImage);


        VBox textContainer = new VBox();
        textContainer.setPrefSize(312, 44);


        Label lblProductName = new Label(productName);
        lblProductName.setPrefSize(216, 0);
        lblProductName.setStyle("-fx-text-fill: white; -fx-font-size: 17px; -fx-font-weight: bold;");


        HBox salesContainer = new HBox(5);
        salesContainer.setPrefSize(200, 100);


        Label lblNumberOfSales = new Label(numberOfSales);
        lblNumberOfSales.setStyle("-fx-text-fill: #9ca3af;");


        Label lblSales = new Label("Sales");
        lblSales.setStyle("-fx-text-fill: #9ca3af;");

        salesContainer.getChildren().addAll(lblNumberOfSales, lblSales);


        textContainer.getChildren().addAll(lblProductName, salesContainer);


        Label lblTotalAmount = new Label(totalAmount);
        lblTotalAmount.setPrefSize(132, 31);
        lblTotalAmount.setAlignment(Pos.CENTER_RIGHT);
        lblTotalAmount.setStyle("-fx-text-fill: white; -fx-font-size: 21px; -fx-font-weight: bold;");


        hboxProduct.getChildren().addAll(imageContainer, textContainer, lblTotalAmount);

        return hboxProduct;
    }
}
