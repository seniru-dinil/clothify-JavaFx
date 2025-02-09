package edu.icet.clothify.controller.admin;

import edu.icet.clothify.component.dashboard.DashboardCustomerItemFactory;
import edu.icet.clothify.component.dashboard.DashboardProductItemFactory;
import edu.icet.clothify.entity.CustomerEntity;
import edu.icet.clothify.entity.MostPurchasedProductEntity;
import edu.icet.clothify.service.ServiceFactory;
import edu.icet.clothify.service.custom.CustomerService;
import edu.icet.clothify.service.custom.ProductService;
import edu.icet.clothify.util.enums.ServiceType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import lombok.SneakyThrows;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class AdminDashboardFormController implements Initializable {

    public HBox bestContainer;
    public VBox vboxTopProductsContainer;
    public VBox vBoxBestCustomers;
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
        setVboxTopProductsContainer();
        setvBoxBestCustomers();
    }

    public void setVboxTopProductsContainer() {
        ProductService productService = ServiceFactory.getInstance().getService(ServiceType.PRODUCT);
        List<MostPurchasedProductEntity> mostPurchasedProducts = productService.getMostPurchasedProducts();
        if (mostPurchasedProducts.isEmpty()) {

        } else {
            vboxTopProductsContainer.getChildren().clear();
            for (MostPurchasedProductEntity p : mostPurchasedProducts) {
                HBox productItem = DashboardProductItemFactory.getInstance().createProductItem(p.getProduct().getProductName(), p.getTotalQuantity(), p.getProduct().getProductPrice() * p.getTotalQuantity(), p.getProduct().getProductImagePath());
                vboxTopProductsContainer.getChildren().add(productItem);
            }
        }
    }

    public void setvBoxBestCustomers() {
        CustomerService customerService = ServiceFactory.getInstance().getService(ServiceType.CUSTOMER);
        Map<CustomerEntity, Double> bestCustomers = customerService.getBestCustomers();
        if (bestCustomers.isEmpty()) {

        } else {
            vBoxBestCustomers.getChildren().clear();
            for (Map.Entry<CustomerEntity, Double> entry : bestCustomers.entrySet()) {
                CustomerEntity customer = entry.getKey();
                Double totalOrderValue = entry.getValue();
                HBox cHBox = DashboardCustomerItemFactory.getInstance().createEmployeeItem(customer.getFirstName() + " " + customer.getLastName(), totalOrderValue);
                vBoxBestCustomers.getChildren().add(cHBox);
            }
        }
    }
}
