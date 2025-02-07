package edu.icet.clothify.controller.admin;

import edu.icet.clothify.util.DashboardUtil;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import lombok.SneakyThrows;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminDashboardFormController implements Initializable {

    public HBox bestContainer;
    @FXML
    private VBox container;

    @FXML
    private Label txtAdminName;

    @FXML
    private Label txtTotalOrders;

    @FXML
    private Label txtTotalProducts;

    @FXML
    private Label txtTotalSales;

    @FXML
    private Label txtTotalSuppliers;

    @SneakyThrows
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ResultSet totalNumberOfOrders = DashboardUtil.getInstance().getTotalNumberOfOrders();
        if (totalNumberOfOrders.next())txtTotalOrders.setText(String.valueOf(totalNumberOfOrders.getInt(1)));
        ResultSet totalNumberOfProducts = DashboardUtil.getInstance().getTotalNumberOfProducts();
        if (totalNumberOfProducts.next())txtTotalProducts.setText(String.valueOf(totalNumberOfProducts.getInt(1)));
        ResultSet totalNumberOfSuppliers = DashboardUtil.getInstance().getTotalNumberOfSuppliers();
        if (totalNumberOfSuppliers.next())txtTotalSuppliers.setText(String.valueOf(totalNumberOfSuppliers.getInt(1)));
        ResultSet totalSales = DashboardUtil.getInstance().getTotalSales();
        if (totalSales.next())txtTotalSales.setText(String.valueOf(totalSales.getInt(1)));
        getBestSellingProduct();
    }

    public void getBestSellingProduct() throws SQLException {
        ResultSet bestSellingProudct = DashboardUtil.getInstance().getBestSellingProudct();
        if (bestSellingProudct.next()){
            String name = bestSellingProudct.getString(2);
            String imagePath = bestSellingProudct.getString(5);
            int sales = bestSellingProudct.getInt(6);
            int stock = bestSellingProudct.getInt(4);
            VBox bestSellingProduct = createBestSellingProduct(imagePath, name, stock, sales);
            bestContainer.getChildren().add(bestSellingProduct);
        }
    }


    public  VBox createBestSellingProduct(String imagePath, String productName, int stock, int totalSales) {
        // Create VBox container
        VBox bestSellingProduct = new VBox();
        bestSellingProduct.setAlignment(Pos.TOP_CENTER);
        bestSellingProduct.setPrefSize(266, 339);
        bestSellingProduct.setStyle("-fx-background-color: #1F2937;");

        // Create ImageView for product image
        ImageView productImage = new ImageView(new Image(imagePath));
        productImage.setFitHeight(252);
        productImage.setFitWidth(231);
        productImage.setPickOnBounds(true);
        productImage.setPreserveRatio(true);
        VBox.setMargin(productImage, new Insets(30, 0, 0, 0)); // Top margin

        // HBox for product name and stock
        HBox productInfo = new HBox();
        productInfo.setAlignment(Pos.CENTER_LEFT);
        productInfo.setPrefSize(306, 56);
        VBox.setMargin(productInfo, new Insets(15, 0, 0, 0)); // Top margin
        productInfo.setPadding(new Insets(0, 10, 0, 10));

        // Label for product name
        Label txtProductName = new Label(productName);
        txtProductName.setPrefSize(166, 46);
        txtProductName.setTextFill(javafx.scene.paint.Color.WHITE);
        txtProductName.setFont(Font.font(17));

        // Label for "Stock :"
        Label stockLabel = new Label("Stock :");
        stockLabel.setPrefSize(42, 46);
        stockLabel.setTextFill(javafx.scene.paint.Color.web("#9ca3af"));
        stockLabel.setFont(Font.font(14));

        // Label for stock count
        Label txtStock = new Label(String.valueOf(stock));
        txtStock.setTextFill(javafx.scene.paint.Color.WHITE);
        txtStock.setFont(Font.font("System Bold", 22));

        // Add components to product info HBox
        productInfo.getChildren().addAll(txtProductName, stockLabel, txtStock);

        // HBox for total sales
        HBox salesInfo = new HBox();
        salesInfo.setAlignment(Pos.CENTER);
        salesInfo.setPrefSize(306, 56);

        // Label for "Total Sales :"
        Label salesLabel = new Label("Total Sales : ");
        salesLabel.setPrefSize(107, 46);
        salesLabel.setTextFill(javafx.scene.paint.Color.web("#9ca3af"));
        salesLabel.setFont(Font.font("System Bold Italic", 18));

        // Label for total sales value
        Label txtTotalSales = new Label(String.valueOf(totalSales));
        txtTotalSales.setTextFill(javafx.scene.paint.Color.WHITE);
        txtTotalSales.setFont(Font.font("System Bold", 30));

        // Add components to sales HBox
        salesInfo.getChildren().addAll(salesLabel, txtTotalSales);

        // Add components to main VBox
        bestSellingProduct.getChildren().addAll(productImage, productInfo, salesInfo);

        return bestSellingProduct;
    }
}
