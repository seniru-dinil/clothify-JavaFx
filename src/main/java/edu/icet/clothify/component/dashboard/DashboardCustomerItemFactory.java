package edu.icet.clothify.component.dashboard;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class DashboardCustomerItemFactory {
    private static DashboardCustomerItemFactory instance;

    private DashboardCustomerItemFactory() {
    }

    public static DashboardCustomerItemFactory getInstance() {
        if (instance == null) instance = new DashboardCustomerItemFactory();
        return instance;
    }

    public HBox createEmployeeItem(String customerName, Double totalSpent) {
        HBox hboxCustomer = new HBox(15);
        hboxCustomer.setPrefSize(250, 70);
        hboxCustomer.setStyle("-fx-background-color: rgba(55, 65, 81,0.5); -fx-background-radius:10px");
        hboxCustomer.setAlignment(Pos.CENTER);


        ImageView profileImage = new ImageView(new Image("/img/admin.png"));
        profileImage.setFitHeight(28);
        profileImage.setFitWidth(27);
        profileImage.setPreserveRatio(true);


        Circle avatarCircle = new Circle(23, Color.web("#4b5563"));
        avatarCircle.setStroke(Color.web("#4b5563"));


        StackPane avatarContainer = new StackPane();
        avatarContainer.getChildren().addAll(avatarCircle, profileImage);
        avatarContainer.setPrefSize(15, 24);


        Group group = new Group(avatarContainer);


        VBox textContainer = new VBox();
        textContainer.setAlignment(Pos.CENTER_LEFT);
        textContainer.setPrefSize(167, 70);


        Label lblCustomerName = new Label(customerName);
        lblCustomerName.setPrefSize(216, 0);
        lblCustomerName.setStyle("-fx-text-fill: white; -fx-font-size: 17px; -fx-font-weight: bold;");


        HBox hboxTotalSpent = new HBox(5);
        hboxTotalSpent.setPrefSize(130, 14);


        Label lblTotalSpent = new Label(String.valueOf(totalSpent));
        lblTotalSpent.setStyle("-fx-text-fill: #9ca3af;");


        Label lblSales = new Label("Sales");
        lblSales.setStyle("-fx-text-fill: #9ca3af;");


        hboxTotalSpent.getChildren().addAll(lblTotalSpent, lblSales);


        textContainer.getChildren().addAll(lblCustomerName, hboxTotalSpent);


        hboxCustomer.getChildren().addAll(group, textContainer);
        return hboxCustomer;
    }
}
