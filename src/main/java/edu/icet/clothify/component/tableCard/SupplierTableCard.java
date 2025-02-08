package edu.icet.clothify.component.tableCard;


import com.jfoenix.controls.JFXButton;
import edu.icet.clothify.dto.Supplier;
import edu.icet.clothify.service.ServiceFactory;
import edu.icet.clothify.service.custom.SupplierService;
import edu.icet.clothify.util.enums.ServiceType;
import edu.icet.clothify.util.dtoUtil.SupplierUtil;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class SupplierTableCard {
    private SupplierTableCard(){}
    private static SupplierTableCard instance;
    public static SupplierTableCard getInstance(){
        return instance==null?instance=new SupplierTableCard():instance;
    }

    public AnchorPane createSupplierCard(Supplier supplier) {

        AnchorPane supplierPane = new AnchorPane();
        supplierPane.setPrefHeight(42.0);
        supplierPane.setPrefWidth(874.0);
        supplierPane.setStyle("-fx-background-color: #1F2937;");


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


        String imgPath;
        switch (supplier.getCategoryId()) {
            case 1:
                imgPath = "/img/img3.png";
                break;
            case 2:
                imgPath = "/img/img1.png";
                break;
            case 3:
                imgPath = "/img/img2.png";
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


        HBox actionBox = new HBox();
        actionBox.setAlignment(javafx.geometry.Pos.CENTER);
        actionBox.setLayoutX(799.0);
        actionBox.setLayoutY(8.0);
        actionBox.setPrefHeight(26.0);
        actionBox.setPrefWidth(33.0);
        actionBox.setSpacing(3);


        StackPane editPane = createIconButton("/img/edite.png",
                16, 16,
                24, 2,
                ()-> {
                    try {
                        handleEdit(supplier);
                    } catch (IOException e) {

                    }
                });


        StackPane deletePane = createIconButton("/img/delete.png",
                16, 16,
                24, 2,
                ()-> {
                    try {
                        handleDelete(supplier);
                    } catch (SQLException e) {

                    }
                });


        actionBox.getChildren().addAll(editPane, deletePane);


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

        }

        pane.getChildren().addAll(icon,button);
        return pane;
    }

    private void handleEdit(Supplier supplier) throws IOException {
        Object load = FXMLLoader.load(getClass().getResource("/view/update/updateSupplier.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene((Parent) load));
        stage.show();
        SupplierUtil.getInstance().setValue(supplier);
    }

    private void handleDelete(Supplier supplier) throws SQLException {
        SupplierService supplierService = ServiceFactory.getInstance().getService(ServiceType.SUPPLIER);
        boolean b = supplierService.deleteSupplier(supplier.getSupplierId());
        SupplierUtil.getInstance().loadContainer();

    }
}

