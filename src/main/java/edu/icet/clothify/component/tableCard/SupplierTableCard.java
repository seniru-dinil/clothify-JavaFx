package edu.icet.clothify.component.tableCard;


import com.jfoenix.controls.JFXButton;
import edu.icet.clothify.dto.Supplier;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class SupplierTableCard {
    private SupplierTableCard(){}
    private static SupplierTableCard instance;
    public static SupplierTableCard getInstance(){
        return instance==null?instance=new SupplierTableCard():instance;
    }

    public AnchorPane createSupplierCard(Supplier supplier) {
        // Create the AnchorPane
        AnchorPane supplierPane = new AnchorPane();
        supplierPane.setPrefHeight(42.0);
        supplierPane.setPrefWidth(874.0);
        supplierPane.setStyle("-fx-background-color: #1F2937;");

        // Create Labels
        Label txtID = new Label(String.valueOf(supplier.getSupplierId()));
        txtID.setLayoutX(12.0);
        txtID.setLayoutY(12.0);
        txtID.setPrefHeight(18.0);
        txtID.setPrefWidth(58.0);
        txtID.setTextFill(Color.web("#d1d5db"));

        Label txtName = new Label(supplier.getSupplierName());
        txtName.setLayoutX(141.0);
        txtName.setLayoutY(12.0);
        txtName.setPrefHeight(18.0);
        txtName.setPrefWidth(145.0);
        txtName.setTextFill(Color.web("#d1d5db"));

        Label txtEmail = new Label(supplier.getSupplierEmail());
        txtEmail.setLayoutX(353.0);
        txtEmail.setLayoutY(12.0);
        txtEmail.setPrefHeight(18.0);
        txtEmail.setPrefWidth(145.0);
        txtEmail.setTextFill(Color.web("#d1d5db"));

        // Create ImageView based on category ID
        String imgPath;
        switch (supplier.getCategoryId()) {
            case 1:
                imgPath = "/img/img3.png";
                break;
            case 2:
                imgPath = "/img/img2.png";
                break;
            case 3:
                imgPath = "/img/img1.png";
                break;
            default:
                imgPath = "/img/default.png";
                break;
        }

        StackPane imagePane = new StackPane();
        imagePane.setLayoutX(585.0);
        imagePane.setLayoutY(5.0);
        imagePane.setPrefHeight(32.0);
        imagePane.setPrefWidth(33.0);
        imagePane.setStyle("-fx-background-color: #374151;");

        ImageView img = new ImageView(new Image(getClass().getResourceAsStream(imgPath)));
        img.setFitHeight(22.0);
        img.setFitWidth(27.0);
        img.setPreserveRatio(true);

        imagePane.getChildren().add(img);

        // Create HBox for Edit and Delete buttons
        HBox actionBox = new HBox();
        actionBox.setAlignment(javafx.geometry.Pos.CENTER);
        actionBox.setLayoutX(791.0);
        actionBox.setLayoutY(8.0);
        actionBox.setPrefHeight(26.0);
        actionBox.setPrefWidth(33.0);

        // Edit Button
        StackPane editPane = createIconButton("/img/edite.png",
                16, 16,
                24, 2,
                ()->handleEdit(supplier));

        // Delete Button
        StackPane deletePane = createIconButton("/img/delete.png",
                16, 16,
                24, 2,
                ()->handleDelete(supplier));


        actionBox.getChildren().addAll(editPane, deletePane);

        // Add all elements to the AnchorPane
        supplierPane.getChildren().addAll(txtID, txtName, txtEmail, imagePane, actionBox);

        return supplierPane;
    }




    private StackPane createIconButton(String imagePath, double imgWidth, double imgHeight, double btnWidth, double btnHeight,Runnable action) {
        StackPane pane = new StackPane();

        JFXButton button = new JFXButton();
        button.setPrefSize(btnWidth, btnHeight);
        button.setStyle("-fx-background-color: transparent;");
        button.setOnAction(e->action.run());
        ImageView icon = new ImageView();
        icon.setFitWidth(imgWidth);
        icon.setFitHeight(imgHeight);
        icon.setPreserveRatio(true);
        try {
            Image image = new Image(getClass().getResourceAsStream(imagePath));
            icon.setImage(image);
        } catch (Exception e) {
            System.err.println("Error loading image: " + e.getMessage());
        }

        pane.getChildren().addAll(icon,button);
        return pane;
    }

    private void handleEdit(Supplier supplier) {
        // Edit logic
        System.out.println("Editing supplier: " + supplier.getSupplierId());
    }

    private void handleDelete(Supplier supplier) {
        // Delete logic
        System.out.println("Deleting supplier: " + supplier.getSupplierId());
    }
}

